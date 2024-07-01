package com.ecoleta.server.model.point;

import java.util.Set;

import com.ecoleta.server.model.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "point")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    @Id
    @GeneratedValue
    private int id;
    private String image;
    private String name;
    private String email;
    private String whatsapp;
    private double latitude;
    private double longitude;
    private String city;
    private String uf;

    @ManyToMany
    @JoinTable(
        name = "point2item", 
        joinColumns = @JoinColumn(name = "point_id"), 
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonIgnoreProperties(value = "points")
    private Set<Item> items;
}
