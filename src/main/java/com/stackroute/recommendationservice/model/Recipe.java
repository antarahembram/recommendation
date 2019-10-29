package com.stackroute.recommendationservice.model;

import lombok.*;
import org.neo4j.ogm.annotation.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label="Recipe")
public class Recipe {

    @Id
    @Property(name="name")
    private String name;

    @Property(name="serves")
    private int serves;

    @Property(name="publishedBy")
    private String publishedBy;
    private List<Ingredients> ingredients;
    private Procedure procedure;
    @Property(name="cuisine")
    private String cuisine;
    @Property(name="course")
    private String course;
    private List<String> images;
    @Property(name="cookingTime")
    private int cookingTime;
    private double rating;
    private BigDecimal price;
    private LocalDate publishedOn;
    private List<Comment> comments;
    private List<Rating> ratings;


}
