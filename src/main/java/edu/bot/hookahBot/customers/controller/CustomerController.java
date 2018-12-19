package edu.bot.hookahBot.customers.controller;


import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.customers.repository.CustomerRepo;
import edu.bot.hookahBot.orders.model.HOrder;
import edu.bot.hookahBot.orders.repository.HOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private HOrderRepo orderRepo;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepo.findAll();
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepo.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id).get();

        customerRepo.delete(customer);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                           @Valid @RequestBody Customer customerDetails) {

        Customer customer = customerRepo.findById(id).get();

        customer.setTgId(customerDetails.getTgId());
        customer.setTgName(customerDetails.getTgName());
        customer.setPhone(customerDetails.getPhone());

        Customer updatedCust = customerRepo.save(customer);
        return updatedCust;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepo.findById(id).get();
    }

    @GetMapping("/customers/{id}/orders")
    public List<HOrder> getOrdersOfCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id).get();
        return customer.getOrders();
    }

    @PostMapping("/customers/{idCustomer}/orders/{idOrder}")
    public void addOrderToCustomer(@PathVariable Long idCustomer,
                                 @PathVariable Long idOrder) {
        Customer customer = customerRepo.findById(idCustomer).get();
        HOrder order = orderRepo.findById(idOrder).get();
        customer.addOrders(order);
        customer.setOrders(customer.getOrders());
        customerRepo.save(customer);
        orderRepo.save(order);
    }
}