package com.drnavalhabarbershop.resourceserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "home")
public class Home {

    @Id
    private String id;
    private String welcomeMessage;
    private String aboutBarbershop;
    private Map<String, String> address;
}
