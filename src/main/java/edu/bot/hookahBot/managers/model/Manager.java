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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

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

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Point> points = new ArrayList<>();

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

    public List<UUID> getAllIds(List<Point> points) {
        List<UUID> ids = new ArrayList<>();
        for (Point point : points) {
            ids.add(point.getId());
        }
        return ids;
    }

    public UUID getId() {
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

    public List<Point> getPoints() {
        return points;
    }

    public void setId(UUID id) {
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

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoints(Point point) {
        points.add(point);
    }
}
