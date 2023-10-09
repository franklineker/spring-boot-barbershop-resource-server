package br.com.drnavalha.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChairRequest {

    private String barberID;
    private String barberName;
    private String startDate;
    private String endDate;
}
