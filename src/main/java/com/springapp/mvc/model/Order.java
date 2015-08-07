package com.springapp.mvc.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> itemList;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Order() {
    }

    public Order(int id, Set<OrderItem> itemList, Date date) {
        this.id = id;
        this.itemList = itemList;
        this.date = date;
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
}
