package edu.bot.hookahBot.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.points.model.Point;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "horder")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"order_time", "order_update"},
        allowGetters = true)
public class HOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horder_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer customer;

     
    private String taste;

     
    private String hardness;

     
    private String label;

     
    private Double price;

    @Column(updatable = false)
    private String orderTime;

    private String orderUpdate;

    @PrePersist
    protected void onCreate() {
        orderTime = LocalDateTime.now().toString();
        orderUpdate = LocalDateTime.now().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        orderUpdate = LocalDateTime.now().toString();
    }

    private boolean isAccepted;

    public HOrder(  String taste,   String hardness,   String label,
                    Double price) {
        this.taste = taste;
        this.hardness = hardness;
        this.label = label;
        this.price = price;
        this.isAccepted = false;
    }

    public HOrder() {

    }

    public Long getId() {
        return id;
    }

    public String getTaste() {
        return taste;
    }

    public String getHardness() {
        return hardness;
    }

    public String getLabel() {
        return label;
    }

    public Double getPrice() {
        return price;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getOrderUpdate() {
        return orderUpdate;
    }

    public boolean getAcceptance(){
        return isAccepted;
    }

    public Point getPoint() {
        return point;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrderTime(String time) {
        this.orderTime = time;
    }

    public void setOrderUpdate(String time) {
        this.orderUpdate= time;
    }

    public void setAccepted(boolean flag) {
        this.isAccepted = flag;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
