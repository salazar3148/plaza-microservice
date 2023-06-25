package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class TraceabilityRequestDto {
    private Long orderId;
    private Long customerId;
    private String customerMail;
    private LocalDateTime date;
    private String oldStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeMail;
}
