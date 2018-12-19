package edu.bot.hookahBot.points.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.bot.hookahBot.managers.model.Manager;
import edu.bot.hookahBot.orders.model.HOrder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "point")
@EntityListeners(AuditingEntityListener.class)
public class Point implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "man_id")
    private Manager manager;

     
    private String name;

     
    private String address;

    private Double rating;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HOrder> orders = new ArrayList<>();

    public Point(  String name,   String address, Double rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public Point() {

    }

    public List<Long> getAllIds(List<HOrder> orders) {
        List<Long> ids = new ArrayList<>();
        for (HOrder order : orders) {
            ids.add(order.getId());
        }
        return ids;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getRating() {
        return rating;
    }

    public Manager getManager() {
        return manager;
    }

    public List<HOrder> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setOrders(List<HOrder> orders) {
        this.orders = orders;
    }

    public void addOrders(HOrder order) {
        orders.add(order);
    }
}