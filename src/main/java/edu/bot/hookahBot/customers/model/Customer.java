package edu.bot.hookahBot.customers.model;

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
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull
    private Long cust_tg_id;

    @NotNull
    private String cust_tg_username;

    @NotNull
    private String phone;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Order> orders = new ArrayList<>();

    public Customer(@NotNull Long cust_tg_id, @NotNull String cust_tg_username, @NotNull String phone) {
        this.cust_tg_id = cust_tg_id;
        this.cust_tg_username = cust_tg_username;
        this.phone = phone;
    }

    public Customer() {

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

    public Long getTgId() {
        return cust_tg_id;
    }

    public String getTgName() {
        return cust_tg_username;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTgId(Long id) {
        this.cust_tg_id = id;
    }

    public void setTgName(String name) {
        this.cust_tg_username = name;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrders(Order order) {
        orders.add(order);
    }
}

