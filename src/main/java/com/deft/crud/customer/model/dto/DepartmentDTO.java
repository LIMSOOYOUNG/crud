package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO implements Serializable {

    private String deptCode;
    private String deptName;
    private String deptFax;
    private String deptTel;
    private String deptStatus;
}
