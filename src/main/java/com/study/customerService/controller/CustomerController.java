package com.study.customerService.controller;

import com.study.customerService.entity.Customer;
import com.study.customerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
public class CustomerController {

    public static final String[] m_firstNames = {"Maria", "Joseph", "Sebastian", "Xenia", "Manfred"};
    public static final String[] m_lastNames = {"Wolfgang", "Höttges", "Mix","Heinisch","Förster"};
    public static final int m_countNames = 5;

    Random m_randomGen = new Random();

    @Autowired
    private CustomerRepository m_repository;

    @RequestMapping("/")
    String caseDefault()
    {
        return "<h1> CustomerService </h1>";
    }

    @GetMapping("/showDb")
    String showDb()
    {
        String l_string = "";
        for (Customer customer : m_repository.findAll()) {
            l_string += customer.toString() + "<br>";
        }
        return l_string;
    }

    @PostMapping("/createCustomer")
    void createCustomer(@RequestParam final String FN,@RequestParam final String LN,@RequestParam final String CN)
    {
        m_repository.save(new Customer(FN ,LN ,CN ));

        System.out.println("Customer created: " + CN);
    }

    @GetMapping("/getCID")
    String getCID(@RequestParam final String CN)
    {
        Customer l_customer = m_repository.findByCN(CN);

        System.out.println("send CID: " + l_customer.id + " From " + l_customer.CN);
        return l_customer.id;
    }

    @GetMapping("/getCustomer")
    Customer getCustomer(@RequestParam final String CID)
    {
        Customer l_customer = m_repository.findByCN(CID);

        System.out.println("send Customer: " + l_customer.CN);
        return l_customer;
    }
}
