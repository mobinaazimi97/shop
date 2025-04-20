package com.mftplus.shop.person.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mftplus.shop.enums.Gender;
import com.mftplus.shop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString


@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @JsonProperty("ردیف :")
    private Long id;

    @Column(name = "person_firstName", length = 30)
    @JsonProperty("نام :") //todo --> not work!
    private String name;

    @Column(name = "person_lastName", length = 40)
    @JsonProperty("نام خانوادگی :")
    private String family;

    @Column(name = "person_national_id", length = 10)
    @JsonProperty("کد ملی :")
    private String nationalId;

    @Column(name = "person_birth_date")
    @JsonProperty("تاریخ تولد :")
    private LocalDate birthDate;

    @Column(name = "person_phone_number", length = 13)
    @JsonProperty("شماره تماس :")
    private String phoneNumber;

    @Column(name = "person_address", length = 200)
    @JsonProperty("ادرس :")
    private String address;

    @Column(name = "person_postal_code", length = 30)
    @JsonProperty("کد پستی :")
    private String postalCode;

    @Column(name = "person_gender")
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty("جنسیت :")
    private Gender gender;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "person_users", foreignKey = @ForeignKey(name = "user_fk"))
    @JsonProperty("اطلاعات کاربر :")
    private User user;

}


