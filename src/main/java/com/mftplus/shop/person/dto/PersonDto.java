package com.mftplus.shop.person.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class PersonDto {
    private String name;
    private String family;
    private String phoneNumber;
//    private String gender;
}
