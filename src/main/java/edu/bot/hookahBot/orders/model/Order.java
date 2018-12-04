package edu.bot.hookahBot.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.points.model.Point;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_point")
    private Point point;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_cust")
    private Customer customer;

    @NotNull
    private String taste;

    @NotNull
    private String hardness;

    @NotNull
    private String label;

    @NotNull
    private Double price;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date orderUpdate;

    @NotNull
    private boolean isAccepted = false;

    public Order(@NotNull String taste, @NotNull String hardness, @NotNull String label,
                 @NotNull Double price, @NotNull Date orderTime, @NotNull Date orderUpdate,
                 @NotNull boolean isAccepted) {
        this.taste = taste;
        this.hardness = hardness;
        this.label = label;
        this.price = price;
        this.orderTime = orderTime;
        this.orderUpdate = orderUpdate;
        this.isAccepted = isAccepted;
    }

    public Order() {

    }

    public UUID getId() {
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

    public Date getOrderTime() {
        return orderTime;
    }

    public Date getOrderUpdate() {
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

    public void setId(UUID id) {
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

    public void setOrderTime(Date time) {
        this.orderTime = time;
    }

    public void setOrderUpdate(Date time) {
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
