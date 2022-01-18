import adminOperation.CreateProduct;
import adminOperation.UpdateProduct;
import adminOperation.ViewProduct;
import login.Login;
import login.Register;
import memberOperation.CreateOrder;
import memberOperation.OrderHistory;
import memberOperation.UpdateProfile;

import java.util.Scanner;

public class main {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

            System.out.println("===================================================================");
            System.out.println("\t\t\tWelcome to MiniAmazon");
            System.out.println("===================================================================");
            System.out.println("1.Login \n2.Register");
            System.out.println("Enter Choice:");
            int ch = in.nextInt();
            switch(ch){
                case 1:
                    //Login
                    System.out.println("::::Login Account::::");
                    System.out.println("1.Admin \n2.Member");
                    System.out.println("Enter Choice:");
                    int loginChoice = in.nextInt();
                    Login login = new Login();
                    if(loginChoice == 1)
                    {
                        Integer id = login.checkAdmin();
                        if(id!=null){
                            operations(id);

                        }
                        else {
                            System.out.println("Login failed");
                            System.exit(0);
                        }
                    }else if(loginChoice == 2){
                        Integer id = login.checkMember();
                        if(id!=null){
                           memberOperations(id);
                        }
                        else {
                            System.out.println("Login failed");
                            System.exit(0);
                        }
                    }
                    else{
                        System.out.println("Please enter valid choice");
                    }
                    break;
                case 2:
                    //Registration
                    System.out.println("::::Login Account::::");
                    System.out.println("1.Admin \n2.Member");
                    System.out.println("Enter Choice:");
                    int accountType = in.nextInt();
                    Register register = new Register();
                    if(accountType == 1)
                    {
                        boolean success = register.createAdmin();
                        if(success){
                            System.out.println("Admin registered successfully");
                        }
                        else {
                            System.out.println("Registration failed");
                            System.exit(0);
                        }
                    }else if(accountType == 2){
                        boolean success = register.createMember();
                        if(success){
                            System.out.println("Member registered successfully");
                        }
                        else {
                            System.out.println("Registration failed");
                            System.exit(0);
                        }
                    }
                    else{
                        System.out.println("Please enter valid choice");
                    }
                    break;
                default:
                    System.out.println("Please enter valid choice");

            }

    }

    private static void memberOperations(Integer id) {
        Scanner in = new Scanner(System.in);

        System.out.println("---------------------------Operations----------------------");
        System.out.println("1.Create order 2.View order History 3.Update profile  4.Log out");
        int ch = in.nextInt();

        switch (ch){
            case 1:
                CreateOrder c = new CreateOrder();
                c.orderProduct(id);
                break;
            case 2:
                OrderHistory o = new OrderHistory();
                o.orders(id);
                break;
            case 3:
                UpdateProfile u = new UpdateProfile();
                u.changeData(id);
                break;

            case 4:
                System.exit(0);
            default:
                System.out.println("Enter valid choice:");

        }
    }

    private static void operations(Integer id) {
        Scanner in = new Scanner(System.in);

           System.out.println("---------------------------Operations----------------------");
           System.out.println("1.Create Product 2.View Product List 3.Update Product 5.Log out");
           int ch = in.nextInt();

           switch (ch){
               case 1:
                   CreateProduct c = new CreateProduct();
                   c.createProduct(id);
                   break;
               case 2:
                   ViewProduct v = new ViewProduct();
                   v.getProduct(id);
                   break;
               case 3:
                   UpdateProduct u = new UpdateProduct();
                   u.updateProduct(id);
                   break;
               case 5:
                   System.exit(0);
               default:
                   System.out.println("Enter valid choice:");

           }

    }
}
