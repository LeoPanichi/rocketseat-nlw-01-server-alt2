package com.ecoleta.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoleta.server.model.item.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
