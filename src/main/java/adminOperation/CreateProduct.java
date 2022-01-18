package adminOperation;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class CreateProduct {

    public void createProduct(Integer id){

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            System.out.println("Admin Id:"+id);
            setProduct(id,session);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }


    }

    private void setProduct(Integer id, Session session) {
        Scanner in = new Scanner(System.in);
        String productName;
        String ManufacturerName;
        Integer productPrice;
        Integer Discount;
        Integer productStock;
        System.out.println("=====================Create Product=====================");
        System.out.println("Enter 0 to quite or 1 to continue:");
        Integer choice = in.nextInt();

        while (choice!=0){
            System.out.print("Enter Product Name:\n");
            in.nextLine();
            productName = in.nextLine();
            System.out.println("Enter Manufacturer Name:\n");
            ManufacturerName = in.next();

            System.out.println("Enter Product Price:");
            productPrice  = in.nextInt();

            System.out.println("Enter Product Discount");
            Discount = in.nextInt();

            System.out.println("Enter Stock:");
            productStock = in.nextInt();

            Admin admin = session.get(Admin.class,id);

            Product product = new Product(productName,ManufacturerName,productPrice,Discount,productStock);

           product.setAdmin(admin);

            session.save(product);

            System.out.println("-------------------Product Added successfully-----------------------");
            System.out.println("Do you want to continue:1=Yes/0=No:");
            choice = in.nextInt();
        }



    }
}
