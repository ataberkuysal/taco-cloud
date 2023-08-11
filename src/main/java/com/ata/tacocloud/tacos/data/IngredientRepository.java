package com.ata.tacocloud.tacos.data;

import com.ata.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository
extends CrudRepository<Ingredient, String> {
}
