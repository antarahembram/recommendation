package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Recipe;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecommendationCommandRepositoryRecipe  extends Neo4jRepository<Recipe,Long> {

    @Query("WITH {recipe} as props create (r:Recipe) set r.name=props.name set r.publishedBy=props.publishedBy set r.cuisine=props.cuisine set r.course=props.course set r.serves=props.serves with r,props unwind props.ingredients as i with r,i,props  match (k:IngredientName{name:i.name}) merge(k)-[:used_in{quantity:i.quantity,unit:i.unit}]->(r) with r,props  with r,props  unwind props.procedure as p with r,p unwind p.stages as stages with r,stages  merge(s:StageName{name:stages.stageName})  merge(s)-[:stage_of]->(r) with s match (m:Stage) with s,m merge(s)-[:is_a]->(m) ")
    Collection<Recipe> createRecipe(@Param("recipe") Recipe recipe);

    @Query("MATCH (r:Recipe{name:{recipe}.name}),(u:User{userName:r.publishedBy}) MERGE (u)-[:published]->(r) return r")
    Collection<Recipe> createPublished(@Param("recipe") Recipe recipe);

    @Query("MATCH (r:Recipe{name:{recipe}.name}),(c:Course{name:r.course}) MERGE (r)-[:type_of]->(c)")
    void linkCourse(@Param("recipe") Recipe recipe);

//    @Query("MATCH (r:Recipe{name:{recipe}.name}) UNWIND {ingredients} AS ing  MATCH (i:IngredientName{name:ing.name}) MERGE (i)-[:used_in]->(r)")
//    void addIngredients(@Param("ingredients")List<Ingredients> ingredients);

}
