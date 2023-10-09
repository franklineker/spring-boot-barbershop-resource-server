package br.com.drnavalha.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum UserType {

    BARBER(1, "BARBEIRO"), ADMIN(2, "ADMIN"), CLIENT(3, "CLIENTE");

    private final int code;
    private final String description;

    public static UserType ofCode(Integer code) {
        return Stream.of(values())
                .filter(userType -> Objects.equals(userType.code, code))
                .findAny()
                .orElse(null);
    }
}
