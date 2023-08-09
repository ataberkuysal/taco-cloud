package com.ata.tacocloud.tacos.data;

import com.ata.tacocloud.tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save (Ingredient ingredient);
}
