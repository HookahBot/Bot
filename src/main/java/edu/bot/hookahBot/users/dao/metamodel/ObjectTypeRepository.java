package edu.bot.hookahBot.users.dao.metamodel;

import edu.bot.hookahBot.users.dao.metamodel.enteties.ObjectType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObjectTypeRepository extends CrudRepository<ObjectType, Long> {
    Optional<ObjectType> findByName(String name);
}