package adminOperation;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.ManyToOne;
import java.util.Scanner;

public class UpdateProduct {
   static Scanner in = new Scanner(System.in);
    public void updateProduct(Integer id){

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {


               System.out.println("==========================Available products==================");
               ViewProduct v = new ViewProduct();
               v.getProduct(id);

               System.out.println("=============================Enter choice you want to update===================");
               System.out.println("1.Name 2.Manufacture 3.Price 4.Discount 5.Total stock available");
               System.out.println("Enter Choice:");

               int c = in.nextInt();
               System.out.println(c);
               switch(c){
                   case 1:
                       changeName(id, session);
                       break;
                   case 2:
                       changeManuFacturer(id,session);
                       break;
                   case 3:
                       changePrice(id,session);
                       break;
                   case 4:
                       changeDiscount(id,session);
                       break;
                   case 5:
                       changeStock(id,session);
                       break;
                   default:
                       System.out.println("Enter valid choice");
               }



        }catch (Exception e){
            session.getTransaction().rollback();
        }
    }

    private void changeStock(Integer id, Session session) {
        Integer stock;
        Integer productId;
        session.beginTransaction();
        try {
            System.out.println("Enter Product Id:");
            productId =in.nextInt();
            System.out.println("Enter New Stock:");
            stock = in.nextInt();

            Product product = session.get(Product.class,productId);
            product.setProductStock(stock);
            session.save(product);
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            System.out.println("Value Updated!!");
            session.close();
        }

    }

    private void changeDiscount(Integer id, Session session) {
        Integer productId;
        session.beginTransaction();
        //Admin admin = session.get(Admin.class,id);
        try {
            System.out.println("Enter Product ID:");
            productId = in.nextInt();

            System.out.println("Enter New Discount Name:");
            Integer price = in.nextInt();

            session.createQuery("update Product p set p.Discount='"+price+"' where p.productId ='"+productId+"'").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    private void changePrice(Integer id, Session session) {
        Integer productId;
        session.beginTransaction();
        //Admin admin = session.get(Admin.class,id);
        try {
            System.out.println("Enter Product ID:");
            productId = in.nextInt();

            System.out.println("Enter New Price:");
            Integer price = in.nextInt();

            session.createQuery("update Product p set p.productPrice='"+price+"' where p.productId ='"+productId+"'").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static   void changeManuFacturer(Integer id, Session session) {

        Integer productId;
        session.beginTransaction();
        //Admin admin = session.get(Admin.class,id);
        try {
            System.out.println("Enter Product ID:");
            productId = in.nextInt();

            System.out.println("Enter New Manufacturer Name:");
            in.nextLine();
            String name = in.nextLine();

            session.createQuery("update Product p set p.ManufacturerName='"+name+"' where p.productId ='"+productId+"'").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void changeName(Integer id, Session session) {


                Integer productId;
                session.beginTransaction();
                //Admin admin = session.get(Admin.class,id);
              try {
                  System.out.println("Enter Product ID:");
                  productId = in.nextInt();

                  System.out.println("Enter New Product Name:");
                  in.nextLine();
                  String name = in.nextLine();

                  session.createQuery("update Product p set p.productName='"+name+"' where p.productId ='"+productId+"'").executeUpdate();
                  session.getTransaction().commit();
              }catch (Exception e){
                  session.getTransaction().rollback();
                  e.printStackTrace();
              }finally {
                  session.close();
              }

    }
}
