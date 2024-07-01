package com.ecoleta.server.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecoleta.server.model.point.Point;
import com.ecoleta.server.model.point.PointRequestDTO;
import com.ecoleta.server.model.point.PointResponseDTO;
import com.ecoleta.server.service.PointService;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/points")

public class PointController {

    @Autowired
    private PointService pointService;
    
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Point> create(
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("whatsapp") String whatsapp,
                                    @RequestParam("latitude") double latitude,
                                    @RequestParam("longitude") double longitude,
                                    @RequestParam("city") String city,
                                    @RequestParam("uf") String uf,
                                    @RequestParam("items") String items) throws IOException{
        PointRequestDTO body = new PointRequestDTO(image, name, email, whatsapp, latitude, longitude, city, uf, items);
        Point point = this.pointService.createPoint(body);
        return ResponseEntity.ok(point);        
    }

    @GetMapping
    public ResponseEntity<List<PointResponseDTO>> get(
        @RequestParam("city") String city,
        @RequestParam("uf") String uf,
        @RequestParam("items") String items){
            return ResponseEntity.ok(pointService.getPoints(city, uf, items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointResponseDTO> getById(
        @PathVariable int id) throws UnknownHostException{
            return ResponseEntity.ok(pointService.getPointById(id));
    }

}
