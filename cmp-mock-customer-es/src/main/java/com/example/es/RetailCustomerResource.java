package com.example.es;

import com.example.es.model.RetailCustomer;
import com.example.es.service.RetailCustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/es")
public class RetailCustomerResource {


    @Autowired
    public RetailCustomerServiceImpl cmpRetailService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/customer/{id}")
    public ResponseEntity<RetailCustomer> getCustomer(@PathVariable("id") Long customerID) {

       // log.info("Get customerID {} ", customerID);
        return cmpRetailService.getCustomersById(customerID);

    }

    @PostMapping("/customer")
    private ResponseEntity<RetailCustomer> persistResponse(
            @Validated @RequestBody final RetailCustomer retailCustomer) {
        //log.info("Posted new customer with id {} ", retailCustomer.getEsCustomerID());
       return cmpRetailService.create(retailCustomer);
    }
}
