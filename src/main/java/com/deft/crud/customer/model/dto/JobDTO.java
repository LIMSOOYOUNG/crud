package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDTO implements Serializable {

    private String jobCode;
    private String jobName;
    private String jobStatus;
}
