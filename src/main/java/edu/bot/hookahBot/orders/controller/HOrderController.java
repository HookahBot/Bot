package edu.bot.hookahBot.orders.controller;

import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.customers.repository.CustomerRepo;
import edu.bot.hookahBot.orders.model.HOrder;
import edu.bot.hookahBot.orders.repository.HOrderRepo;
import edu.bot.hookahBot.points.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HOrderController {
    @Autowired
    private HOrderRepo orderRepo;


    @GetMapping("/orders")
    public List<HOrder> getAllOrders() {
        return (List<HOrder>) orderRepo.findAll();
    }

    @PostMapping("/orders")
    public HOrder addOrder(@RequestBody HOrder order) {
        return orderRepo.save(order);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        HOrder order = orderRepo.findById(id).get();

        orderRepo.delete(order);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/orders/{id}")
    public HOrder updateOrder(@PathVariable Long id,
                                   @Valid @RequestBody HOrder orderDetails) {

        HOrder order = orderRepo.findById(id).get();

        order.setHardness(orderDetails.getHardness());
        order.setLabel(orderDetails.getLabel());
        order.setPrice(orderDetails.getPrice());
        order.setTaste(orderDetails.getTaste());
        order.setAccepted(orderDetails.getAcceptance());

        HOrder updatedOrder = orderRepo.save(order);
        return updatedOrder;
    }

    @GetMapping("/orders/{id}")
    public HOrder getOrderById(@PathVariable Long id) {
        return orderRepo.findById(id).get();
    }

    @GetMapping("/orders/{id}/customer")
    public Customer getCustomerOfOrder(@PathVariable Long id) {
        HOrder order = orderRepo.findById(id).get();
        return order.getCustomer();
    }

    @GetMapping("/orders/{id}/point")
    public Point getPointOfOrder(@PathVariable Long id) {
        HOrder order = orderRepo.findById(id).get();
        return order.getPoint();
    }
}