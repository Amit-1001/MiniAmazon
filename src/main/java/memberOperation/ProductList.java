package memberOperation;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductList {

    public void showList(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            List<Product> productList  = session.createQuery("from Product").getResultList();
            System.out.println("======================Available Products==============");
            for (Product product:productList){
                System.out.println(product);
            }

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }
}
