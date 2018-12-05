package edu.bot.hookahBot.managers.model;


import edu.bot.hookahBot.points.model.Point;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "manager")
@EntityListeners(AuditingEntityListener.class)
public class Manager implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long man_tg_id;

    @NotNull
    private String man_tg_username;

    @NotNull
    private String idDoc;

    @NotNull
    private String phone;

    public Manager(@NotNull String name, @NotNull Long man_tg_id,
                   @NotNull String man_tg_username, @NotNull String idDoc, @NotNull String phone) {
        this.name = name;
        this.man_tg_id = man_tg_id;
        this.man_tg_username = man_tg_username;
        this.idDoc = idDoc;
        this.phone = phone;
    }

    public Manager() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTgId() {
        return man_tg_id;
    }

    public String getTgName() {
        return man_tg_username;
    }

    public String getDoc() {
        return idDoc;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTgId(Long id) {
        this.man_tg_id = id;
    }

    public void setTgName(String name) {
        this.man_tg_username = name;
    }

    public void setDoc(String doc) {
        this.idDoc = doc;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
