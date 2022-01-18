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
import java.util.Scanner;

public class CreateOrder {

    public void orderProduct(Integer id) {
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
            ProductList productList = new ProductList();
            productList.showList();

            // getting member info


            Integer quantity;
            Integer productId;

            Member member = session.get(Member.class,id);



            System.out.println("Enter Product Id");
            productId = in.nextInt();


            System.out.println("Enter Quantity");
            quantity = in.nextInt();

            Product product = session.get(Product.class,productId); //Access given product ID
            if(product.getProductStock()<quantity){
                System.out.println("Enter less quantity!");
                quantity = in.nextInt();
            }


           // List<Product> products = session.createQuery("from Product p where p.productId='"+productId+"'").getResultList();


            String memberName = member.getUserName();
            String productName = product.getProductName();
            Integer productPrice = product.getProductPrice();
            Integer productDiscount = product.getDiscount();

            Integer resultDiscount = productPrice - (product.getDiscount()*productPrice)/100;
            Integer productPriceAfterDiscount = resultDiscount;

            Integer productQuantity = quantity;
            Integer totalPrice = productPriceAfterDiscount*productQuantity;
            String memberAddress = member.getUserAddress();

            //update Stock
            Integer stock = product.getProductStock();
            session.createQuery("update Product p set p.productStock='"+(stock-quantity)+"' where p.productId ='"+productId+"'").executeUpdate();

           Order order = new Order(memberName,productName,productPrice,productDiscount,productPriceAfterDiscount,productQuantity,totalPrice,memberAddress);

          // Order order = new Order("Max","Dell X12",100000,10,90000,1,90000,"pune");

            session.save(order);//

            session.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            System.out.println("Order placed!");
            session.close();
        }

    }
}
