package edu.bot.hookahBot.customers.controller;


import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.customers.repository.CustomerRepo;
import edu.bot.hookahBot.orders.model.Order;
import edu.bot.hookahBot.orders.repository.OrderRepo;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Context;

import org.springframework.test.web.client.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;


    @GetMapping("/customers")
    public @ResponseBody
    List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepo.findAll();
    }

    @PostMapping("/customers")
    public @ResponseBody void addCustomer(@RequestBody Customer customer) {
        customerRepo.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public @ResponseBody void deleteCustomer(@PathVariable Long id) {
        customerRepo.deleteById(id);
    }

    @PutMapping("/customers/{id}")
    public @ResponseBody Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        customer.setId(id);
        customerRepo.save(customer);
        return customer;
    }

    @GetMapping("/customers/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable Long id) {
        return customerRepo.findById(id).get();
    }

    @GetMapping("/customers/{id}/orders")
    public @ResponseBody List<Order> getOrdersOfCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id).get();
        return customer.getOrders();
    }

    @PostMapping("/customers/{idCustomer}/order/{idOrder}")
    public @ResponseBody void addOrderToCustomer(@PathVariable Long idCustomer,
                                 @PathVariable Long idOrder) {
        Customer customer = customerRepo.findById(idCustomer).get();
        Order order = orderRepo.findById(idOrder).get();
        customer.addOrders(order);
        customer.setOrders(customer.getOrders());
        customerRepo.save(customer);
        orderRepo.save(order);
    }
}