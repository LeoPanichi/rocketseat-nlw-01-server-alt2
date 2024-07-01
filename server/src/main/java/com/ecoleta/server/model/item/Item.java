package com.ecoleta.server.model.item;

import java.util.Set;

import com.ecoleta.server.model.point.Point;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private int id;

    private String image;
    private String title;

    @ManyToMany(mappedBy = "items")
    @JsonIgnoreProperties(value = "items")
    private Set<Point> points;
}
