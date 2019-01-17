package edu.bot.hookahBot.users.dao.metamodel.enteties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity

public class Attribute {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @OneToOne
    @NonNull
    private ObjectType objectType;
}