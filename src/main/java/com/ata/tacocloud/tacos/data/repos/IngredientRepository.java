package com.ata.tacocloud.tacos.data.repos;

import com.ata.tacocloud.tacos.data.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository
extends CrudRepository<Ingredient, String> {
}
