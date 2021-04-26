package com.example.es.service;

import com.example.es.model.RetailCustomer;
import org.springframework.http.ResponseEntity;

public interface RetailCustomerService {

    ResponseEntity<RetailCustomer> create(RetailCustomer retailCustomer);

    ResponseEntity<RetailCustomer> getCustomersById(Long customerID);

}
