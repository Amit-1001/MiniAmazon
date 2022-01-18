package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPass")
    private String userPass;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userAddress")
    private String userAddress;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST},mappedBy = "member")
    private List<Product> productList;


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }


    public Member(String userName, String userPass, String userEmail, String userAddress) {
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
    }
}
