package edu.bot.hookahBot.users.dao.repositories;

import edu.bot.hookahBot.users.dao.metamodel.AttributeRepository;
import edu.bot.hookahBot.users.dao.metamodel.ObjectRepository;
import edu.bot.hookahBot.users.dao.metamodel.ObjectTypeRepository;
import edu.bot.hookahBot.users.dao.metamodel.ParamRepository;
import edu.bot.hookahBot.users.dao.metamodel.enteties.Attribute;
import edu.bot.hookahBot.users.dao.metamodel.enteties.ObjectType;
import edu.bot.hookahBot.users.dao.metamodel.enteties.Param;
import edu.bot.hookahBot.users.dao.metamodel.enteties.Object;
import edu.bot.hookahBot.users.dao.models.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MetaModelUserRepository implements ModelRepository<User> {
    public static final String USERS_TABLE_NAME = "User";
    public static final String USER_AGE_FIELD_NAME = "age";
    public static final String USER_NAME_FIELD_NAME = "name";

    private AttributeRepository attributeRepository;
    private ObjectRepository objectRepository;
    private ParamRepository paramRepository;
    private ObjectTypeRepository objectTypeRepository;

    public MetaModelUserRepository(AttributeRepository attributeRepository, ObjectRepository objectRepository, ParamRepository paramRepository, ObjectTypeRepository objectTypeRepository) {
        this.attributeRepository = attributeRepository;
        this.objectRepository = objectRepository;
        this.paramRepository = paramRepository;
        this.objectTypeRepository = objectTypeRepository;
    }

    @Override
    public List<User> getAll() {
        ObjectType userType = createObjectTypeIfAbsent(USERS_TABLE_NAME, objectTypeRepository);

        List<Object> allUsersObj = objectRepository.findAllByObjectType(userType);
        return allUsersObj.stream()
                .mapToLong(Object::getObjectId)
                .mapToObj(this::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(User entity) {
        ObjectType userType = createObjectTypeIfAbsent(USERS_TABLE_NAME, objectTypeRepository);

        Optional<Object> userObject = objectRepository.findByObjectIdAndAndObjectType(entity.getId(), userType);

        Optional<Attribute> ageAttribute = attributeRepository.findByNameAndObjectType(USER_AGE_FIELD_NAME, userType);
        Optional<Attribute> nameAttribute = attributeRepository.findByNameAndObjectType(USER_NAME_FIELD_NAME, userType);

        userObject.ifPresent(userObj ->
                ageAttribute.ifPresent(ageAttr -> {
                    Optional<Param> ageParam = paramRepository.findByAttributeAndObject(ageAttr, userObj);
                    Optional<Param> newAgeParam = ageParam.map(ageP ->
                            Param.builder()
                                    .id(ageP.getId())
                                    .object(userObj)
                                    .attribute(ageAttr)
                                    .numbValue(entity.getAge())
                                    .build()
                    );
                    newAgeParam.ifPresent(paramRepository::save);
                })
        );

        userObject.ifPresent(userObj ->
                nameAttribute.ifPresent(nameAttr -> {
                    Optional<Param> nameParam = paramRepository.findByAttributeAndObject(nameAttr, userObj);
                    Optional<Param> newNameParam = nameParam.map(nameP ->
                            Param.builder()
                                    .id(nameP.getId())
                                    .object(userObj)
                                    .attribute(nameAttr)
                                    .textValue(entity.getName())
                                    .build()
                    );
                    newNameParam.ifPresent(paramRepository::save);
                })
        );
    }

    @Override
    public  Optional<User> get(long id) {
        ObjectType userType = createObjectTypeIfAbsent(USERS_TABLE_NAME, objectTypeRepository);

        Optional<Object> userObject = objectRepository.findByObjectIdAndAndObjectType(id, userType);

        Optional<Attribute> ageAttribute = attributeRepository.findByNameAndObjectType(USER_AGE_FIELD_NAME, userType);
        Optional<Attribute> nameAttribute = attributeRepository.findByNameAndObjectType(USER_NAME_FIELD_NAME, userType);

        Optional<Param> ageParam = ageAttribute.flatMap(ageAttr ->
                userObject.flatMap(userObj -> paramRepository.findByAttributeAndObject(ageAttr, userObj))
        );
        Optional<Param> nameParam = nameAttribute.flatMap(nameAttr ->
                userObject.flatMap(userObj -> paramRepository.findByAttributeAndObject(nameAttr, userObj))
        );

        Optional<User> user = userObject.flatMap(userObj ->
                ageParam.flatMap(ageP ->
                        nameParam.map(nameP ->
                                User.builder()
                                        .id(userObj.getObjectId())
                                        .age(ageP.getNumbValue())
                                        .name(nameP.getTextValue())
                                        .build()
                        )
                )
        );
        return user;
    }

    @Transactional
    @Override
    public void save(User user) {
        ObjectType userType = createObjectTypeIfAbsent(USERS_TABLE_NAME, objectTypeRepository);

        Object userObject = objectRepository.findByObjectIdAndAndObjectType(user.getId(), userType).orElseGet(() -> {
            Object object = new Object(user.getId(), userType);
            objectRepository.save(object);
            return object;
        });

        Attribute ageAttribute = createAttributeIfAbsent(USER_AGE_FIELD_NAME, userType, attributeRepository);
        Attribute nameAttribute = createAttributeIfAbsent(USER_NAME_FIELD_NAME, userType, attributeRepository);

        Param ageParam = Param.builder()
                .attribute(ageAttribute)
                .object(userObject)
                .numbValue(user.getAge())
                .build();
        paramRepository.save(ageParam);

        Param nameParam = Param.builder()
                .attribute(nameAttribute)
                .object(userObject)
                .textValue(user.getName())
                .build();
        paramRepository.save(nameParam);
    }


    @Transactional
    @Override
    public void delete(User user) {
        ObjectType userType = createObjectTypeIfAbsent(USERS_TABLE_NAME, objectTypeRepository);

        Optional<Object> userObject = objectRepository.findByObjectIdAndAndObjectType(user.getId(), userType);

        Optional<Attribute> ageAttribute = attributeRepository.findByNameAndObjectType(USER_AGE_FIELD_NAME, userType);
        Optional<Attribute> nameAttribute = attributeRepository.findByNameAndObjectType(USER_NAME_FIELD_NAME, userType);

        userObject.ifPresent(userObj -> {
            ageAttribute.ifPresent(ageAttr -> {
                Optional<Param> ageParam = paramRepository.findByAttributeAndObject(ageAttr, userObj);
                ageParam.ifPresent(paramRepository::delete);
            });

            nameAttribute.ifPresent(nameAttr -> {
                Optional<Param> nameParam = paramRepository.findByAttributeAndObject(nameAttr, userObj);
                nameParam.ifPresent(paramRepository::delete);
            });
        });
    }

    private ObjectType createObjectTypeIfAbsent(String tableName, ObjectTypeRepository objectTypeRepository) {
        return objectTypeRepository.findByName(tableName).orElseGet(() -> {
            ObjectType objectType = new ObjectType(tableName);
            objectTypeRepository.save(objectType);
            return objectType;
        });
    }

    private Attribute createAttributeIfAbsent(String attributeName, ObjectType objectType, AttributeRepository attributeRepository) {
        return attributeRepository.findByNameAndObjectType(attributeName, objectType).orElseGet(() -> {
            Attribute attribute = new Attribute(attributeName, objectType);
            attributeRepository.save(attribute);
            return attribute;

        });
    }
}
