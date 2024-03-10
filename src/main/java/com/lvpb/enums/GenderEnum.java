package com.lvpb.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {

    UN_KNOW(0,"保密"),
    MAN(1,"男"),
    WOMAN(2,"女");
    private final int code;

    private final String gender;
}
