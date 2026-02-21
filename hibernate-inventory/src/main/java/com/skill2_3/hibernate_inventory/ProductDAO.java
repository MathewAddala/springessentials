package com.skill2_3.hibernate_inventory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAO {
	
	public void sortByPriceAsc() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product order by price asc", Product.class
	    ).list();
	    list.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	    session.close();
	}
	
	public void sortByPriceDesc() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product order by price desc", Product.class
	    ).list();
	    list.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	    session.close();
	}
	
	public void sortByQuantityDesc() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product order by quantity desc", Product.class
	    ).list();
	    list.forEach(p -> System.out.println(p.getName() + " Qty:" + p.getQuantity()));
	    session.close();
	}
	
	public void firstThreeProducts() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery("from Product", Product.class)
	            .setFirstResult(0)
	            .setMaxResults(3)
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}
	
	public void nextThreeProducts() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery("from Product", Product.class)
	            .setFirstResult(3)
	            .setMaxResults(3)
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}
	
	public void countTotalProducts() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Long count = session.createQuery(
	            "select count(p) from Product p", Long.class
	    ).uniqueResult();
	    System.out.println("Total Products: " + count);
	    session.close();
	}
	
	public void countAvailableProducts() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Long count = session.createQuery(
	            "select count(p) from Product p where p.quantity > 0", Long.class
	    ).uniqueResult();
	    System.out.println("Available Products: " + count);
	    session.close();
	}
	
	public void countByDescription() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Object[]> list = session.createQuery(
	            "select description, count(p) from Product p group by description",
	            Object[].class
	    ).list();
	    for (Object[] row : list) {
	        System.out.println(row[0] + " -> " + row[1]);
	    }
	    session.close();
	}
	
	public void minMaxPrice() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Object[] result = session.createQuery(
	            "select min(price), max(price) from Product",
	            Object[].class
	    ).uniqueResult();
	    System.out.println("Min Price: " + result[0]);
	    System.out.println("Max Price: " + result[1]);
	    session.close();
	}
	
	public void groupByDescription() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Object[]> list = session.createQuery(
	            "select description, sum(quantity) from Product group by description",
	            Object[].class
	    ).list();
	    for (Object[] row : list) {
	        System.out.println(row[0] + " Total Qty: " + row[1]);
	    }
	    session.close();
	}
	
	public void priceRange(double min, double max) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product where price between :min and :max",
	            Product.class
	    )
	            .setParameter("min", min)
	            .setParameter("max", max)
	            .list();
	    list.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	    session.close();
	}
	
	public void nameStartsWith(String prefix) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product where name like :prefix",
	            Product.class
	    )
	            .setParameter("prefix", prefix + "%")
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}
	
	public void nameEndsWith(String suffix) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product where name like :suffix",
	            Product.class
	    )
	            .setParameter("suffix", "%" + suffix)
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}
	
	public void nameContains(String pattern) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product where name like :pattern",
	            Product.class
	    )
	            .setParameter("pattern", "%" + pattern + "%")
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}
	
	public void nameLength(int length) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Product> list = session.createQuery(
	            "from Product where length(name) = :len",
	            Product.class
	    )
	            .setParameter("len", length)
	            .list();
	    list.forEach(p -> System.out.println(p.getName()));
	    session.close();
	}

    public void addProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    public Product getProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    public void updateProduct(Long id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            product.setPrice(price);
            product.setQuantity(quantity);
        }
        tx.commit();
        session.close();
    }

    public void deleteProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.remove(product);
        }
        tx.commit();
        session.close();
    }
}