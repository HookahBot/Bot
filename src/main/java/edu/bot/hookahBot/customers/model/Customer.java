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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long cust_tg_id;

    @NotNull
    private String cust_tg_username;

    @NotNull
    private String phone;

    @OneToMany
    @JoinColumn(name = "id_cust")
    private List<Order> orders;

    public Customer(@NotNull Long cust_tg_id, @NotNull String cust_tg_username, @NotNull String phone) {
        this.cust_tg_id = cust_tg_id;
        this.cust_tg_username = cust_tg_username;
        this.phone = phone;
    }

    public Customer(){

    }

    public List<Long> getAllIds(List<Order> orders) {
        List<Long> ids = new ArrayList<>();
        for (Order order : orders) {
            ids.add(order.getId());
        }
        return ids;
    }

    public Long getId() {
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

    public void setId(Long id) {
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

