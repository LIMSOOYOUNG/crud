package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessActivityDTO implements Serializable {

    private int activityNo;
    private String businessSubject;
    private String businessType;
    private String businessLocation;
    private String businessPurpose;
    private String businessContents;
    private String writingDate;
    private String activityStartTime;
    private String activityEndTime;
    private int customerNo;
    private int empNo;

    private EmpInfoDTO empInfo;


}
