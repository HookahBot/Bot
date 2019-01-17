package edu.bot.hookahBot.users.dao.metamodel;

import edu.bot.hookahBot.users.dao.metamodel.enteties.Attribute;
import edu.bot.hookahBot.users.dao.metamodel.enteties.ObjectType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends CrudRepository<Attribute, Long> {
    Optional<Attribute> findByNameAndObjectType(String name, ObjectType objectType);
}
