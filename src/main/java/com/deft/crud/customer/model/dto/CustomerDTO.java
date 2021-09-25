package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerDTO implements Serializable {

    private int customerNo;
    private int customerCompanyNo;
    private int empNo;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
    private String customerFax;

    private ExtCustomerDetailDTO extCustomerDetail;
    private AnaCustomerDetailDTO anaCustomerDetail;
    private EmpInfoDTO empInfo;

    private List<CustomerProductDTO> customerProductList;

    public CustomerDTO() {}

    public CustomerDTO(int customerNo, int customerCompanyNo, int empNo, String customerName, String customerEmail, String customerAddress, String customerPhone, String customerFax, ExtCustomerDetailDTO extCustomerDetail, AnaCustomerDetailDTO anaCustomerDetail, EmpInfoDTO empInfo, List<CustomerProductDTO> customerProductList) {
        this.customerNo = customerNo;
        this.customerCompanyNo = customerCompanyNo;
        this.empNo = empNo;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerFax = customerFax;
        this.extCustomerDetail = extCustomerDetail;
        this.anaCustomerDetail = anaCustomerDetail;
        this.empInfo = empInfo;
        this.customerProductList = customerProductList;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getCustomerCompanyNo() {
        return customerCompanyNo;
    }

    public void setCustomerCompanyNo(int customerCompanyNo) {
        this.customerCompanyNo = customerCompanyNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public ExtCustomerDetailDTO getExtCustomerDetail() {
        return extCustomerDetail;
    }

    public void setExtCustomerDetail(ExtCustomerDetailDTO extCustomerDetail) {
        this.extCustomerDetail = extCustomerDetail;
    }

    public AnaCustomerDetailDTO getAnaCustomerDetail() {
        return anaCustomerDetail;
    }

    public void setAnaCustomerDetail(AnaCustomerDetailDTO anaCustomerDetail) {
        this.anaCustomerDetail = anaCustomerDetail;
    }

    public EmpInfoDTO getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(EmpInfoDTO empInfo) {
        this.empInfo = empInfo;
    }

    public List<CustomerProductDTO> getCustomerProductList() {
        return customerProductList;
    }

    public void setCustomerProductList(List<CustomerProductDTO> customerProductList) {
        this.customerProductList = customerProductList;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerNo=" + customerNo +
                ", customerCompanyNo=" + customerCompanyNo +
                ", empNo=" + empNo +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerFax='" + customerFax + '\'' +
                ", extCustomerDetail=" + extCustomerDetail +
                ", anaCustomerDetail=" + anaCustomerDetail +
                ", empInfo=" + empInfo +
                ", customerProductList=" + customerProductList +
                '}';
    }
}
