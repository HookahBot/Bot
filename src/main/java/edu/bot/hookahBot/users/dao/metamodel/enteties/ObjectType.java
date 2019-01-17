package edu.bot.hookahBot.users.dao.metamodel.enteties;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class ObjectType {
    @Id @GeneratedValue
    private Long id;
    @NonNull
    private String name;
}