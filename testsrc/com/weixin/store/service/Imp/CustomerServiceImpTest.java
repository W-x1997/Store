package com.weixin.store.service.Imp;

import com.weixin.store.domain.Customer;
import com.weixin.store.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest {

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService=new CustomerServiceImp();
    }

    @AfterEach
    void tearDown() {
        customerService=null;
    }

    @Test
    void login() throws ClassNotFoundException {
        Customer customer=new Customer();
        customer.setId("1");
        customer.setPassword("akuiuy");

        assertTrue(customerService.login(customer));

    }

    @Test
    void register() {
    }
}