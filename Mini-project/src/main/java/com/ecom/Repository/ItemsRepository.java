package com.ecom.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Items;

public interface ItemsRepository extends JpaRepository<Items , Integer> {



    Optional<List<Items>> findAllByNameContains(String name);

    Optional< Items > findByName(String name);

    Optional<List<Items>> findAllByNameAndPrice(String name, Double price);

    Optional<List<Items>> findAllByNameAndPriceIsBetween(String name, Double price, Double price2);

    Optional<List<Items>> findAllByNameContainsOrderByPriceDesc(String name);

    boolean existsByImage(String url);

    Optional<List<Items>> findAllByNameContainsOrderByPriceAsc(String name);

    Optional<List<Items>> findAllByCategory(String category);
}
