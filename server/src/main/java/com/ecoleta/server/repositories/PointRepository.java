package com.ecoleta.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecoleta.server.model.point.Point;



public interface PointRepository extends JpaRepository<Point, Integer>, JpaSpecificationExecutor<Point> {

}
