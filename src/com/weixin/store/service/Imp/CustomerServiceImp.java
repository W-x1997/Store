package com.weixin.store.service.Imp;

import com.weixin.store.dao.CustomerDao;
import com.weixin.store.dao.Imp.CustomerDaoImpJdbc;
import com.weixin.store.domain.Customer;
import com.weixin.store.service.CustomerService;
import com.weixin.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDao customerDao=new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer) throws ClassNotFoundException {

        Customer dbcustomer=customerDao.findByPK(customer.getId());
        if(dbcustomer.getPassword().equals(customer.getPassword())){

            //登录成功
            customer.setPhone(dbcustomer.getPhone());
            customer.setAddress(dbcustomer.getAddress());
            customer.setName(dbcustomer.getName());
            customer.setBirthday(dbcustomer.getBirthday());



            return true;
        }

        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException, ClassNotFoundException {
        Customer dbCustomer=customerDao.findByPK(customer.getId());

        if(dbCustomer!=null){
            throw new ServiceException("客户ID："+customer.getId()+"已经存在！");

        }

        //注册
        customerDao.create(customer);

    }
}
