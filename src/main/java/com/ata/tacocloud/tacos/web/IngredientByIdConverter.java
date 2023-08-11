package com.ata.tacocloud.tacos.web;


import com.ata.tacocloud.tacos.Ingredient;
import com.ata.tacocloud.tacos.Ingredient.Type;
import com.ata.tacocloud.tacos.data.IngredientRepository;
import com.ata.tacocloud.tacos.data.JdbcIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final JdbcIngredientRepository jdbcIngredientRepository;

    public IngredientByIdConverter(JdbcIngredientRepository jdbcIngredientRepository) {
        this.jdbcIngredientRepository = jdbcIngredientRepository;
    }
    @Override
    public Ingredient convert(String id) {
        return jdbcIngredientRepository.findById(id).orElse(null);
    }
}
