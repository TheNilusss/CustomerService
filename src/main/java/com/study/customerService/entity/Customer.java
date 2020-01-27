package com.study.customerService.entity;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public String CN; //CustomerName

    public Customer() {}

    public Customer(String firstName, String lastName,String CN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CN = CN;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', CustomerName='%s']",
                id, firstName, lastName,CN);
    }

}
