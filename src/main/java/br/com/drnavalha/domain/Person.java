package br.com.drnavalha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String cpf;
    private String name;
    private String phone;
    private String email;
    private String address;

}
