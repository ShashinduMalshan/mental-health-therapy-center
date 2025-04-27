package com.service.mental_health_therapy_center.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvoiceDto {
    double amount;
    double balanceDue;
    double dueAmount;
    String patientId;
    String patientName;
    String programId;
    String programName;
    String therapistName;

}
