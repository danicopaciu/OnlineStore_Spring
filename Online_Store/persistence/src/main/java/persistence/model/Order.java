package persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OrderItem> itemList;
    @Column(name = "order_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "cost")
    private double cost;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Order() {
    }

    public Order(int id, Set<OrderItem> itemList, Date date, double cost) {
        this.id = id;
        this.itemList = itemList;
        this.date = date;
        this.cost = cost;
    }

    public Order(Set<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(Set<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
