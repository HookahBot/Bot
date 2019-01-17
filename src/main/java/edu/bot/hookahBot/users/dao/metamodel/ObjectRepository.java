package edu.bot.hookahBot.users.dao.metamodel;

import edu.bot.hookahBot.users.dao.metamodel.enteties.Object;
import edu.bot.hookahBot.users.dao.metamodel.enteties.ObjectType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjectRepository extends CrudRepository<Object, Long> {
    Optional<Object> findByObjectIdAndAndObjectType(long objectId, ObjectType objectType);
    List<Object> findAllByObjectType (ObjectType objectType);
    List<Object> findAll();
}