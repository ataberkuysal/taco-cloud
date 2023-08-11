package com.ata.tacocloud.tacos.web;


import com.ata.tacocloud.tacos.Ingredient;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

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
