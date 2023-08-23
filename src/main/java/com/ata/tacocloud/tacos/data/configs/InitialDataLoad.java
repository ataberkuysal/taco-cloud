package com.ata.tacocloud.tacos.data.configs;

import com.ata.tacocloud.tacos.data.entities.Ingredient;
import com.ata.tacocloud.tacos.data.entities.TacoOrder;
import com.ata.tacocloud.tacos.data.repos.IngredientRepository;
import com.ata.tacocloud.tacos.data.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InitialDataLoad {

    @Bean
    public ApplicationRunner initializeBaseData(IngredientRepository ingredientRepository, OrderRepository orderRepository) {

        return args -> {

            //base ingredients
            //ingredientRepository.save(Ingredient.builder().id("FLTO").name("Flour Tortilla").type(Ingredient.Type.WRAP).build());

            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));


            //orderRepository.save(new TacoOrder());

        };

    }
}
