package edu.bot.hookahBot.Repositories.customer.repository;

import edu.bot.hookahBot.customers.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> { }
