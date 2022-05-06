package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.Authorities;
import com.laioffer.onlineOrder.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(customer);
            session.save(authorities);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Customer.class);
            customer = (Customer) criteria.add(Restrictions.eq("email", email)).uniqueResult();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return customer;
    }

}


