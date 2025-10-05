package com.mftplus.shop.order.service;

import com.mftplus.shop.order.entity.Card;
import com.mftplus.shop.order.entity.Check;
import com.mftplus.shop.order.entity.Payment;
import com.mftplus.shop.order.dto.CardDto;
import com.mftplus.shop.order.dto.CheckDto;
import com.mftplus.shop.order.dto.PaymentDto;
import com.mftplus.shop.order.mapper.PaymentMapper;
import com.mftplus.shop.order.repository.PaymentRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final UuidMapper uuidMapper;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public PaymentService(UuidMapper uuidMapper, PaymentMapper paymentMapper, PaymentRepository paymentRepository) {
        this.uuidMapper = uuidMapper;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentDto savePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto, "Payment");
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toDto(saved, "Payment");
    }

    @Transactional
    public Optional<PaymentDto> findById(UUID uuid) {
        Long id = uuidMapper.map(uuid, "Payment");
        Payment payment = paymentRepository.findById(id).orElse(null);
        return Optional.ofNullable(paymentMapper.toDto(payment, "Payment"));
    }

    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.toDtoList(payments, "Payment");
    }

    @Transactional
    public PaymentDto updatePayment(UUID uuid, PaymentDto paymentDto) {
        Long id = uuidMapper.map(uuid, "Payment");
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment with id " + uuid + " not found"));

        existingPayment.setAmount(paymentDto.getAmount());
        existingPayment.setDateTime(paymentDto.getDateTime());
        existingPayment.setDeleted(paymentDto.isDeleted());

        if (existingPayment instanceof Card && paymentDto instanceof CardDto) {
            Card existingCard = (Card) existingPayment;
            CardDto cardDto = (CardDto) paymentDto;

            existingCard.setBankName(cardDto.getBankName());
            existingCard.setCardNumber(cardDto.getCardNumber());

        } else if (existingPayment instanceof Check && paymentDto instanceof CheckDto) {
            Check existingCheck = (Check) existingPayment;
            CheckDto checkDto = (CheckDto) paymentDto;

            existingCheck.setBankName(checkDto.getBankName());
            existingCheck.setCheckNumber(checkDto.getCheckNumber());
        }
        Payment updatedPayment = paymentRepository.save(existingPayment);
        return paymentMapper.toDto(updatedPayment, "Payment");
    }


    public void refundPayment(UUID paymentUuid) {
        Long id = uuidMapper.map(paymentUuid, "Payment");
        paymentRepository.findById(id).ifPresent(payment -> {
            payment.setDeleted(true);
            paymentRepository.save(payment);
        });
    }

    @Transactional
    public void logicalRemove(UUID uuid) {
        Long id = uuidMapper.map(uuid, "Payment");
        paymentRepository.logicalRemove(id);

    }

}
