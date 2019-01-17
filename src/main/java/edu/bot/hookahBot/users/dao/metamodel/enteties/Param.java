package edu.bot.hookahBot.users.dao.metamodel.enteties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Param {
    @Id @GeneratedValue
    private Long id;
    private String textValue;
    private int numbValue;
    private Date dateValue;
    @OneToOne
    private Attribute attribute;
    @OneToOne
    private Object object;
}