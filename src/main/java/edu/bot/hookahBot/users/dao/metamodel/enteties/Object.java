package edu.bot.hookahBot.users.dao.metamodel.enteties;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Object {
    @Id @GeneratedValue
    private Long id;
    @NonNull
    private Long objectId;
    @OneToOne
    @NonNull
    private ObjectType objectType;
}