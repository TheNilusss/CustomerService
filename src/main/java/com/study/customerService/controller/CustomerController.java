package com.study.customerService.controller;

import com.study.customerService.entity.Customer;
import com.study.customerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/generateCustomer")
    String createCustomer(@RequestParam final int count)
    {
        for (int i = 0; i < count; i++)
        {
            m_repository.save(new Customer(m_firstNames[m_randomGen.nextInt(m_countNames)], m_lastNames[m_randomGen.nextInt(m_countNames)]));
        }

        return + count +" Customer wurden hinzugefügt!";
    }

    @GetMapping("/createCustomer")
    String createCustomer(@RequestParam final String firstName,@RequestParam final String lastName)
    {
        m_repository.save(new Customer(firstName, lastName));
        Customer l_customer = m_repository.findByFirstName(firstName);
        return l_customer.toString() + " wurde erfolgreich der Datenbank hinzugefügt!";
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
}
