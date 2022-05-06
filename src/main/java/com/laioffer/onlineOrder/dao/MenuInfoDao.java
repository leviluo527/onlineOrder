package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            return session.createCriteria(Restaurant.class)
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                    .list();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }


    public List<MenuItem> getMenuItems(int restaurantId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if(restaurant != null) {
                return  restaurant.getMenuItemList();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }


    public MenuItem getMenuItem(int menuItemId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            MenuItem menuItem = session.get(MenuItem.class, menuItemId);
            return menuItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return null;
    }

}

