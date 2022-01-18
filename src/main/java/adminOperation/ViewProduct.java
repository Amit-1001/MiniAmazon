package adminOperation;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ViewProduct {
    public void getProduct(Integer id){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Admin admin = session.get(Admin.class,id);
            System.out.println("=========================Product List=========================");
            for(Product product: admin.getProductList()){
                System.out.println(product.getProductDetail());
            }



        }catch (Exception e){
            session.beginTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
