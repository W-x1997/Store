package com.weixin.store.dao.Imp;

import static org.junit.jupiter.api.Assertions.*;

import com.weixin.store.dao.CustomerDao;
import com.weixin.store.domain.Customer;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

class CustomerDaoImpJdbcTest {


    CustomerDao  dao;

    @BeforeEach
    void setUp() {
        dao=new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao=null;
    }

    @Test
    void findByPK() throws ClassNotFoundException {
        Customer customer=dao.findByPK("1");


        assertNotNull(customer);
        assertEquals("1",customer.getId());
        assertEquals("weixin",customer.getName());
        assertEquals("123456",customer.getPassword());
        assertEquals("jiangsu",customer.getAddress());
        assertEquals("18795907808",customer.getPhone());
        assertEquals(19970929,customer.getBirthday().getTime());
    }

    @Test
    void findAll() throws ClassNotFoundException {
        List<Customer> list=dao.findAll();
        assertEquals(list.size(),1);


    }

    @Test
    void create() throws ClassNotFoundException {

        Customer customer=new Customer();
        customer.setId("2");
        customer.setName("Stella");
        customer.setPhone("13678906789");
        customer.setBirthday(new Date(78787878));
        customer.setPassword("jujhsk");
        customer.setAddress("beijing");

       dao.create(customer);
       Customer customer1=dao.findByPK("2");

        assertNotNull(customer);
        assertEquals(customer1.getId(),customer.getId());
        assertEquals(customer1.getName(),customer.getName());
        assertEquals(customer1.getPassword(),customer.getPassword());
        assertEquals(customer1.getAddress(),customer.getAddress());
        assertEquals(customer1.getPhone(),customer.getPhone());
        assertEquals(customer1.getBirthday().getTime(),customer.getBirthday().getTime());
    }

    @Test
    void modify() throws ClassNotFoundException {
        Customer customer=new Customer();
        customer.setId("1");
        customer.setName("Jimmy");
        customer.setPhone("15678898678");
        customer.setBirthday(new Date(78787878));
        customer.setPassword("akuiuy");
        customer.setAddress("beijing");

        dao.modify(customer);

        Customer customer1=dao.findByPK("1");

        assertNotNull(customer);
        assertEquals(customer1.getId(),customer.getId());
        assertEquals(customer1.getName(),customer.getName());
        assertEquals(customer1.getPassword(),customer.getPassword());
        assertEquals(customer1.getAddress(),customer.getAddress());
        assertEquals(customer1.getPhone(),customer.getPhone());
        assertEquals(customer1.getBirthday().getTime(),customer.getBirthday().getTime());




    }

    @Test
    void remove() throws ClassNotFoundException {
        dao.remove("2");
        Customer customer=dao.findByPK("2");
        assertNull(customer);

    }
}