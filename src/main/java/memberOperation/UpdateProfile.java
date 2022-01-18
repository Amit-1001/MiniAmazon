package memberOperation;

import entity.Admin;
import entity.Member;
import entity.Order;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class UpdateProfile {

    public void changeData(Integer id){
        Scanner in =new Scanner(System.in);
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
            String password;
            String address;
            System.out.println("============================Update Profile======================");
            System.out.println("1.Change Password 2.Change Address");
            int ch = in.nextInt();

            if(ch == 1){
                System.out.println("Enter New Password");
                password =in.next();
                member.setUserPass(password); //
                session.save(member);
                System.out.println("Password Updated!!");
            }else if(ch==2){
                System.out.print("Enter New Address");
                in.nextLine();
                address = in.nextLine();
                session.save(address);
                System.out.println("Address changed!!");

            }

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }
    }
}
