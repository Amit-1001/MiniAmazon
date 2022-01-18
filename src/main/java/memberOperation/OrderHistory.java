package memberOperation;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import entity.Admin;
import entity.Member;
import entity.Order;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrderHistory {
    public void orders(Integer id){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Member member = session.get(Member.class,id);

           String userName = member.getUserName();

            List<Order> orders = session.createQuery("from Order o where o.memberName ='"+userName+"'").getResultList();
            System.out.println("==============================Order History=====================");
            for (Order order:orders){
                System.out.println(order);
            }




            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
