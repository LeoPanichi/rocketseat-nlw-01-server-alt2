package com.ecoleta.server.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecoleta.server.model.item.ItemResponseDTO;
import com.ecoleta.server.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getById(
        @PathVariable int id) throws UnknownHostException{
            return ResponseEntity.ok(itemService.getPointById(id));
    }

}
