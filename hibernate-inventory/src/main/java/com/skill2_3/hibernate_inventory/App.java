package com.skill2_3.hibernate_inventory;

public class App {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

      
        dao.addProduct(new Product("Mouse", "Accessories", 800, 50));
        dao.addProduct(new Product("Keyboard", "Accessories", 1200, 40));
        dao.addProduct(new Product("Monitor", "Electronics", 15000, 12));
        dao.addProduct(new Product("Tablet", "Electronics", 30000, 5));
        dao.addProduct(new Product("Charger", "Accessories", 500, 0));
        dao.addProduct(new Product("Speaker", "Audio", 4000, 18));

        dao.sortByPriceAsc();
        dao.sortByPriceDesc();
        dao.sortByQuantityDesc();

        dao.firstThreeProducts();
        dao.nextThreeProducts();

        dao.countTotalProducts();
        dao.countAvailableProducts();
        dao.countByDescription();
        dao.minMaxPrice();

        dao.groupByDescription();
        dao.priceRange(1000, 20000);

        dao.nameStartsWith("M");
        dao.nameEndsWith("r");
        dao.nameContains("one");
        dao.nameLength(6);

        HibernateUtil.getSessionFactory().close();
    }
}