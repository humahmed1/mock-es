package com.example.es.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RetailCustomer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID for the customer")
    @JsonProperty
    private Long esCustomerID;

    @Column
    @ApiModelProperty(value = "The customers name")
    @JsonProperty
    private String esName;

    @Column
    @ApiModelProperty(value = "Identifies whether the customer is a senior citizen or not")
    @JsonProperty
    private Boolean esIsSenior;

    public RetailCustomer(Long esCustomerID, String esName, Boolean esIsSenior) {
        this.esCustomerID = esCustomerID;
        this.esName = esName;
        this.esIsSenior = esIsSenior;
    }

    public RetailCustomer(){}

    public RetailCustomer(RetailCustomer customer) {
        this.setEsCustomerID(customer.getEsCustomerID());
        this.setEsName(customer.getEsName());
        this.setEsIsSenior(customer.getEsIsSenior());
    }
}
