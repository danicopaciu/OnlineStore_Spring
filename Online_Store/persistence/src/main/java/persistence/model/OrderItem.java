package persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Product product;
    @Column
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private double subTotal;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity, Order order, double subTotal) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
