package com._Abdelaziz26.Game.Utility;

import com._Abdelaziz26.Game.Model.Game;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameSpecifications {

    public static Specification<Game> getSpecifications(Map<String, Object> filters) {

        Specification<Game> specs = Specification.where(null);

        if(filters.containsKey("search"))
            specs = Specification.where(hasNameLike((String) filters.get("search")));

        if(filters.containsKey("genre"))
            specs = specs.and(hasGenre((String) filters.get("genre")));

        if(filters.containsKey("platform"))
            specs = specs.and(hasPlatform((String) filters.get("platform")));

        if(filters.containsKey("minPrice"))
            specs = specs.and(hasMinPrice((Double) filters.get("minPrice")));

        if (filters.containsKey("maxPrice"))
            specs = specs.and(hasMaxPrice((Double) filters.get("maxPrice")));

        return specs;
    }


    private static Specification<Game> hasNameLike(String name){
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Game> hasGenre(String genre){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre").get("name"), genre));
    }

    private static Specification<Game> hasPlatform(String platform){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("platform").get("name"), platform));
    }

    private static Specification<Game> hasMaxPrice(double minPrice){
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("minPrice"), minPrice);
    }

    private static Specification<Game> hasMinPrice(double maxPrice){
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("maxPrice"), maxPrice);
    }

}
