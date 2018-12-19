package edu.bot.hookahBot.managers.controller;


import edu.bot.hookahBot.managers.model.Manager;
import edu.bot.hookahBot.managers.repository.ManagerRepo;
import edu.bot.hookahBot.points.model.Point;
import edu.bot.hookahBot.points.repository.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerController {
    @Autowired
    private ManagerRepo managerRepository;

    @Autowired
    private PointRepo pointRepository;

    @GetMapping("/managers")
    public List<Manager> getAllManagers() {
        return (List<Manager>) managerRepository.findAll();
    }

    @PostMapping("/managers")
    public Manager addManager(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @DeleteMapping("/manager/{id}")
    public void deleteManager(@PathVariable Long id) {
        managerRepository.deleteById(id);
    }

    @PutMapping("/managers/{id}")
    public Manager updateManager(@RequestBody Manager manager, @PathVariable Long id) {
        manager.setId(id);
        managerRepository.save(manager);
        return manager;
    }

    @GetMapping("/managers/{id}")
    public Manager getManager(@PathVariable Long id) {
        return managerRepository.findById(id).get();
    }

    @GetMapping("/managers/{id}/points")
    public List<Point> getPointsOfManager(@PathVariable Long id) {

        Manager manager = managerRepository.findById(id).get();
        return manager.getPoints();
    }

    @PostMapping("/managers/{idMan}/points/{idPoint}")
    public void addPointToManager(@PathVariable Long idMan,
                                  @PathVariable Long idPoint) {
        Manager manager = managerRepository.findById(idMan).get();
        Point point = pointRepository.findById(idPoint).get();
        manager.addPoint(point);
        manager.setPoints(manager.getPoints());
        point.setManager(manager);
        managerRepository.save(manager);
        pointRepository.save(point);
    }

    //heroku_6c2a62046807a3e@eu-cdbr-west-02.cleardb.net
    //eu-cdbr-west-02.cleardb.net
    //heroku_6c2a62046807a3e
    //jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_6c2a62046807a3e?reconnect=true
}