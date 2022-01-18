package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orderHistory")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "memberName")
    private String memberName;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private Integer productPrice;

    @Column(name = "productDiscount")
    private Integer productDiscount;

    @Column(name = "priceAfterDiscount")
    private Integer priceAfterDiscount;

    @Column(name = "productQuantity")
    private Integer productQuantity;

    @Column(name = "totalCost")
    private Integer totalCost;

    @Column(name = "memberAddress")
    private String memberAddress;

    public Order(String memberName, String productName, Integer productPrice, Integer productDiscount, Integer priceAfterDiscount, Integer productQuantity, Integer totalCost, String memberAddress) {
        this.memberName = memberName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.productQuantity = productQuantity;
        this.totalCost = totalCost;
        this.memberAddress = memberAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", memberName='" + memberName + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDiscount=" + productDiscount +"%"+'\''+
                ", priceAfterDiscount=" + priceAfterDiscount +
                ", productQuantity=" + productQuantity +
                ", totalCost=" + totalCost +
                ", memberAddress='" + memberAddress + '\'' +
                '}';
    }
}
