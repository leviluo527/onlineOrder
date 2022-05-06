package com.laioffer.onlineOrder.service;

import com.laioffer.onlineOrder.dao.CustomerDao;
import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);

        customer.setEnabled(true);
        customerDao.signUp(customer);
    }

    public void signIn(Customer customer) {
        Customer email = getCustomer(customer.getEmail());
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

}

