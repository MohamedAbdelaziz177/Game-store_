package com._Abdelaziz26.Game.Utility;

import com._Abdelaziz26.Game.Model.Game;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecifications {

    public static Specification<Game> hasField(String field, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(field), value);
    }

    public static Specification<Game> hasFieldLike(String field, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + value + "%");
    }
}
