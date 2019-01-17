package edu.bot.hookahBot.users.dao.metamodel;


import edu.bot.hookahBot.users.dao.metamodel.enteties.Attribute;
import edu.bot.hookahBot.users.dao.metamodel.enteties.Object;
import edu.bot.hookahBot.users.dao.metamodel.enteties.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParamRepository extends CrudRepository<Param, Long> {
    Optional<Param> findByAttributeAndObject(Attribute attribute, Object object);
}