package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecommendationCommandRepository extends Neo4jRepository<User,Integer> {

    @Query("create (u:User{user})")
    User createUser(@Param("user") User user);

    @Query("MATCH (u:User{userName:{user}.userName}) with u unwind {cuisine} as cui MERGE(c:Cuisine{name:cui}) MERGE (u)-[:interested_in]->(c) return u")
    Collection<User> interestedCuisine(@Param("user") User user ,@Param("cuisine") List<String> cuisine);

    @Query("MATCH (u:User{userName:{user}.userName}),(s:State{name:{user}.state}) MERGE (u)-[:stays_in]->(s)")
    void staysIn(@Param("user") User user);

    @Query("MATCH (u:User{userName:{user}.userName})-[r]->() delete r,u ")
    void deleteUser(@Param("user") User user);

    @Query ("MATCH (u:User{userName:{user}.userName}) set u.name={user}.name set u.age={user}.age set u.image={user}.image set u.address={user}.address set u.gender={user}.gender set u.city={user}.city set u.state={user}.state with u match (u)-[r:stays_in]->() delete r with u match (s:State{name:u.state}) merge (u)-[r:stays_in]->(s) return u")
    Collection<User> updateUser(@Param("user") User user);




    @Query("WITH {recipe} as props create (r:Recipe) set r.name=props.name set r.publishedBy=props.publishedBy set r.cuisine=props.cuisine set r.course=props.course set r.serves=props.serves with r,props unwind props.ingredients as i with r,i,props  match (k:IngredientName{name:i.name}) merge(k)-[:used_in{quantity:i.quantity,unit:i.unit}]->(r) with r,props  with r,props  unwind props.procedure as p with r,p unwind p.stages as stages with r,stages  merge(s:StageName{name:stages.stageName})  merge(s)-[:stage_of]->(r) with s match (m:Stage) with s,m merge(s)-[:is_a]->(m) ")
    Collection<Recipe> createRecipe(@Param("recipe") Recipe recipe);

    @Query("MATCH (r:Recipe{name:{recipe}.name}),(u:User{userName:r.publishedBy}) MERGE (u)-[:published]->(r) return r")
    Collection<Recipe> createPublished(@Param("recipe") Recipe recipe);

    @Query("MATCH (r:Recipe{name:{recipe}.name}),(c:Course{name:r.course}) MERGE (r)-[:type_of]->(c)")
    void linkCourse(@Param("recipe") Recipe recipe);



    @Query("MATCH (u:User)-[r:published]->(m:Recipe) return u")
    Collection<User> getLiked();

    @Query("MATCH (u:User{userName:{user}.userName}) MATCH (m:Recipe{name:{recipe}.name}) MERGE (u)-[:liked]->(m) return u")
    Collection<User> createLiked(@Param("user")User user, @Param("recipe") Recipe recipe);

    @Query("MATCH (u:User{userName:{user}.userName}) , (m:Recipe{name:{recipe}.name}) MERGE (u)-[:tried]->(m) return u")
    Collection<User> createTried(@Param("user")User user, @Param("recipe")Recipe recipe);

    @Query("MATCH (u:User{userName:{user}.userName})-[r:liked]->(m:Recipe{name:{recipe}.name}) DELETE r")
    Collection<User> deleteLiked(@Param("user")User user, @Param("recipe")Recipe recipe);

}
