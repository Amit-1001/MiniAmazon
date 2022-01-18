package login;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Register {
    private static Scanner in =new Scanner(System.in);

   public boolean createMember() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

       boolean success = false;
        try {

            session.beginTransaction();
            success = setMemberData(session);
            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e);
            return success;
        }finally {
            session.close();
            sessionFactory.close();
        }
       return (success);

    }


    public  boolean createAdmin() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        boolean success = false;
        try {
            session.beginTransaction();

             success =  setAdminData(session);

            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e);
            return success;
        }finally {

            session.close();
            sessionFactory.close();

        }

        return success;

    }

    private boolean setAdminData(Session session) {
        String adminName;
        String adminPass;
        String adminEmail;
        String adminAddress;

        System.out.println("-------------------Enter Details------------------");
        System.out.println("Enter Name:");
        adminName = in.next();

        System.out.println("Enter Password:");
        adminPass = in.next();

        System.out.println("Enter Email:");
        adminEmail = in.next();

        System.out.print("Enter  Address:");
        in.nextLine();
        adminAddress = in.nextLine();

        Admin admin = new Admin(adminName,adminPass,adminEmail,adminAddress);
        try {
            session.save(admin);
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
     return true;
    }


    private static boolean setMemberData(Session session) {

        String memberName;
        String memberPass;
        String memberEmail;
        String memberAddress;

        System.out.println("-------------------Enter Details------------------");
        System.out.println("Enter Name:");
        memberName = in.next();

        System.out.println("Enter Password:");
        memberPass = in.next();

        System.out.println("Enter Email:");
        memberEmail = in.next();

        System.out.print("Enter  Address:");
        in.nextLine();
        memberAddress = in.nextLine();


        Member member = new Member(memberName, memberPass, memberEmail, memberAddress);
        try {
            session.save(member);
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

}
