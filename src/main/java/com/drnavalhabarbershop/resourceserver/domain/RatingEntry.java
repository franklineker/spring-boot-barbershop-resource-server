package com.drnavalhabarbershop.resourceserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntry {

    private Client client;
    private double rating;
}
