package com.maven.tutorial.test.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static Integer customerId = 0;
    private static List<Customer> customers = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {

        /* // This block of code is only when we dont have a @Post annotation
        Customer customer = new Customer();
        customer.setId(String.valueOf(customerId++));
        customer.setFirstName("First Name: " + customerId);
        customer.setLastName("Last Name: " + customerId);

        customers.add(customer);
        */
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customer.setId(String.valueOf(customerId++));
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst().map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
