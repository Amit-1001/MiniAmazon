package login;

import entity.Admin;
import entity.Member;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Login {
    public Scanner in = new Scanner(System.in);

    public Integer checkAdmin() {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();



        Integer adminId = null;
        try {
            session.beginTransaction();
             adminId = getAdmin(session);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e);
            return adminId;
        } finally {
            session.close();
            sessionFactory.close();
        }
        return adminId;
    }


    public Integer checkMember() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Integer memberId = null;

        try {
            session.beginTransaction();
            memberId = getMember(session);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e);
            return memberId;
        } finally {
            session.close();
            sessionFactory.close();
        }
        return memberId;

    }

    private Integer getMember(Session session) {
        String userName;
        String userPass;
        try {
            System.out.println("Enter Name:");
            userName = in.next();
            System.out.println("Enter Password:");
            userPass = in.next();

            Query<Member> query = session.createQuery("from Member m where m.userName ='" + userName + "' and m.userPass='"+userPass+"'");

            List<Member> members = query.getResultList();

            for (Member member:members){
                if(member.getUserName().equals(userName) && member.getUserPass().equals(userPass)){
                    return member.getId();
                }
            }


        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        return null;
    }



    private Integer getAdmin(Session session) {
        String userName;
        String userPass;

        try {
            System.out.println("Enter Name:");
            userName =in.next();
            System.out.println("Enter Password:");
            userPass = in.next();

            Query<Admin> query = session.createQuery("from Admin a where a.userName ='" + userName + "' and a.userPass='"+userPass+"'");
            List<Admin> admins = query.getResultList();


            for (Admin admin:admins){
                System.out.println(admin.getUserName());
               if(admin.getUserName().equals(userName) && admin.getUserPass().equals(userPass) ){
                   return admin.getId();
               }
            }



        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return  null;
        }
        return null;

    }
}
