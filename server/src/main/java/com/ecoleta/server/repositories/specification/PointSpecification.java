package com.ecoleta.server.repositories.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecoleta.server.model.point.Point;

public class PointSpecification {

    private PointSpecification() {}

    public static Specification<Point> inCity(String city){
        return (root, query, builder) -> {
            return builder.equal(root.get("city"), city);
        };
    }

    public static Specification<Point> inUf(String uf){
        return (root, query, builder) -> {
            return builder.equal(root.get("uf"), uf);
        };
    }

    public static Specification<Point> hasItems(List<Integer> items){
        return (root, query, builder) -> {
            return root.join("items").get("id").in(items);
        };
    }

}
