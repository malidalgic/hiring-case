package com.carla.hiringcase.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compensation {

    private LocalDateTime timestamp;
    private String ageRange;
    private String industry;
    private String jobTitle;
    private int annualSalary;
    private String currency;
    private String location;
    private String workExperience;
    private String additionalContext;
    private String otherCurrency;
}
