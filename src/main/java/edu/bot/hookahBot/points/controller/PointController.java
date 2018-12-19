package edu.bot.hookahBot.points.controller;
import edu.bot.hookahBot.customers.model.Customer;
import edu.bot.hookahBot.managers.model.Manager;
import edu.bot.hookahBot.managers.repository.ManagerRepo;
import edu.bot.hookahBot.orders.model.HOrder;
import edu.bot.hookahBot.orders.repository.HOrderRepo;
import edu.bot.hookahBot.points.model.Point;
import edu.bot.hookahBot.points.repository.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PointController {

    @Autowired
    private HOrderRepo orderRepository;

    @Autowired
    private PointRepo pointRepository;

    @GetMapping("/points")
    public List<Point> getAllPoints() {
        return (List<Point>) pointRepository.findAll();
    }

    @PostMapping("/points")
    public Point addPoint(@RequestBody Point point) {
        return pointRepository.save(point);
    }

    @DeleteMapping("/points/{id}")
    public void deletePoint(@PathVariable Long id) {
        pointRepository.deleteById(id);
    }

    @PutMapping("/points/{id}")
    public Point updatePoint(@RequestBody Point point, @PathVariable Long id) {
        point.setId(id);
        pointRepository.save(point);
        return point;
    }

    @GetMapping("/points/{id}")
    public Point getPoint(@PathVariable Long id) {
        return pointRepository.findById(id).get();
    }

    @GetMapping("/points/{id}/managers")
    public Manager getManagerOfPoint(@PathVariable Long id) {
        Point point = pointRepository.findById(id).get();
        return point.getManager();
    }

    @GetMapping("/points/{id}/orders")
    public List<HOrder> getOrdersOfPoint(@PathVariable Long id) {
        Point point = pointRepository.findById(id).get();
        return point.getOrders();
    }

    @PostMapping("/points/{idPoint}/orders/{idOrder}")
    public void addPointToCustomer(@PathVariable Long idPoint,
                                   @PathVariable Long idOrder) {
        Point point = pointRepository.findById(idPoint).get();
        HOrder order = orderRepository.findById(idOrder).get();
        point.addOrders(order);
        point.setOrders(point.getOrders());
        pointRepository.save(point);
        orderRepository.save(order);
    }
}