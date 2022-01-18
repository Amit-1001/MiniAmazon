package entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@NoArgsConstructor
@Table(name = "product")
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "ManufacturerName")
    private String ManufacturerName;

    @Column(name = "productPrice")
    private Integer productPrice;

    @Column(name = "Discount")
    private Integer Discount;

    @Column(name = "productStock")
    private Integer productStock;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "adminId")
    private Admin admin;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "memberId")
    private Member member;



    public Product(String productName, String manufacturerName, Integer productPrice, Integer discount, Integer productStock) {
        this.productName = productName;
        ManufacturerName = manufacturerName;
        this.productPrice = productPrice;
        Discount = discount;
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", productId=" + productId + '\''+
                ", productName='" + productName + '\'' +
                ", ManufacturerName='" + ManufacturerName + '\'' +
                ", productPrice=" + productPrice +
                ", Discount='" + Discount + '\'' +
                ", productStock=" + productStock +
                '}';
    }

    public String getProductDetail(){
       return "Product{" +
                "Created By:"+admin.getUserName() +
                ", productId=" + productId + '\''+
                ", productName='" + productName + '\'' +
                ", ManufacturerName='" + ManufacturerName + '\'' +
                ", productPrice=" + productPrice +
                ", Discount='" + Discount + '\'' +
                ", productStock=" + productStock +
                '}';

    }



}
