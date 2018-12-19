package edu.bot.hookahBot.customers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.bot.hookahBot.orders.model.HOrder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long id;

     
    private Long cust_tg_id;

     
    private String cust_tg_username;

     
    private String phone;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HOrder> orders = new ArrayList<>();

    public Customer(  Long cust_tg_id,   String cust_tg_username,   String phone) {
        this.cust_tg_id = cust_tg_id;
        this.cust_tg_username = cust_tg_username;
        this.phone = phone;
    }

    public Customer(){

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

    public Long getTgId() {
        return cust_tg_id;
    }

    public String getTgName() {
        return cust_tg_username;
    }

    public String getPhone() {
        return phone;
    }

    public List<HOrder> getOrders() {
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOrders(List<HOrder> orders) {
        this.orders = orders;
    }

    public void addOrders(HOrder order) {
        orders.add(order);
    }
}

