package com.stackroute.recommendationservice.model;

import lombok.*;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "User")
public class User {
    @Id
    @Property(name="userName")
    private String userName;

    @Property(name="name")
    private String name;

    @Property(name="email")
    private String email;

    @Property(name="city")
    private String city;

    @Property(name="state")
    private String state;
    @Property(name = "gender")
    private String gender;
    @Property(name = "age")
   private String age;

    @Property(name="image")
    private String image;
    @Property(name="address")
    private String address;
    private List<Recipe> recipesLiked;
    private List<Recipe> recipesTried;
    private List<Recipe> recipesPublished;
    private List<Recipe> recipesRated;

    @Property(name="cuisine")
    private List<String> cuisine;


}
