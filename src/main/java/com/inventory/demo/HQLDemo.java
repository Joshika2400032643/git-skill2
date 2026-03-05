package com.inventory.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        // Insert sample data (run once)
        ProductDataLoader.loadSampleProducts(session);

        // Sort by price ASC
        sortProductsByPriceAscending(session);

        // Sort by price DESC
        sortProductsByPriceDescending(session);

        // Pagination
        getFirstThreeProducts(session);

        // Count products
        countTotalProducts(session);

        session.close();
        factory.close();
    }

    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";

        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> list = query.list();

        System.out.println("Products sorted by price ASC");

        for(Product p : list)
            System.out.println(p);
    }

    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";

        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> list = query.list();

        System.out.println("Products sorted by price DESC");

        for(Product p : list)
            System.out.println(p);
    }

    public static void getFirstThreeProducts(Session session) {

        String hql = "FROM Product";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(0);
        query.setMaxResults(3);

        List<Product> list = query.list();

        System.out.println("First 3 products:");

        for(Product p : list)
            System.out.println(p);
    }

    public static void countTotalProducts(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p";

        Query<Long> query = session.createQuery(hql, Long.class);

        Long count = query.uniqueResult();

        System.out.println("Total Products = " + count);
    }
}