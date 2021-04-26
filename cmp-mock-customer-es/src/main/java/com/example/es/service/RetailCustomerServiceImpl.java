package com.example.es.service;

import com.example.es.model.RetailCustomer;
import com.example.es.dao.RetailCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

@Service
public class RetailCustomerServiceImpl implements RetailCustomerService {

    @Autowired
    public RetailCustomerRepository retailCustomerRepository;

    @Transactional
    public Iterable<RetailCustomer> getAllCustomers(){
        return retailCustomerRepository.findAll();
    }


    @Autowired
    public RestTemplate restTemplate;

    @Transactional
    public ResponseEntity getCustomersById(Long customerID) {
        if(retailCustomerRepository.findById(customerID).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(retailCustomerRepository.findById(customerID).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated RetailCustomer retailCustomer){

        retailCustomerRepository.save(retailCustomer);
        if(retailCustomerRepository.findById(retailCustomer.getEsCustomerID()).isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(retailCustomerRepository.findById(retailCustomer.getEsCustomerID()).get());

        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Adding Customer");
        }

    }

}
