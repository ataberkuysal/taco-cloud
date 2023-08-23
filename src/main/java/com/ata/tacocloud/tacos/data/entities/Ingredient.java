package com.ata.tacocloud.tacos.data.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@Data
@Document(collection = "ingredients")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private  String id;
    private  String name;
    private  Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    /*Ingredient withId(String id){
        return new Ingredient(id, this.name, this.type);
    }*/
}
