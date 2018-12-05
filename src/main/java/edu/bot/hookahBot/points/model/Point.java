package edu.bot.hookahBot.points.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.bot.hookahBot.managers.model.Manager;
import edu.bot.hookahBot.orders.model.Order;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "point")
@EntityListeners(AuditingEntityListener.class)
public class Point implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_man")
    private Manager manager;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private Double rating;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Order> orders = new ArrayList<>();

    public Point(@NotNull String name, @NotNull String address, Double rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public Point() {

    }

    public List<UUID> getAllIds(List<Order> orders) {
        List<UUID> ids = new ArrayList<>();
        for (Order order : orders) {
            ids.add(order.getId());
        }
        return ids;
    }

    public UUID getId() {
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(UUID id) {
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrders(Order order) {
        orders.add(order);
    }
}
