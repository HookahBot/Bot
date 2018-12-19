package edu.bot.hookahBot.points.repository;

import edu.bot.hookahBot.points.model.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface PointRepo extends CrudRepository<Point, Long> {}
