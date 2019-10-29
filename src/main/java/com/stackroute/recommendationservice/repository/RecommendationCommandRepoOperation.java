package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.OperationDTO;
import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecommendationCommandRepoOperation extends Neo4jRepository<OperationDTO,Integer> {
    @Query("MATCH (u:User)-[r:published]->(m:Recipe) return u")
    Collection<User> getLiked();

    @Query("MATCH (u:User{userName:{user}.userName}) MATCH (m:Recipe{name:{recipe}.name}) MERGE (u)-[:liked]->(m) return u")
    Collection<User> createLiked(@Param("user")User user, @Param("recipe")Recipe recipe);

    @Query("MATCH (u:User{userName:{user}.userName}) , (m:Recipe{name:{recipe}.name}) MERGE (u)-[:tried]->(m) return u")
    Collection<User> createTried(@Param("user")User user, @Param("recipe")Recipe recipe);

    @Query("MATCH (u:User{userName:{user}.userName})-[r:liked]->(m:Recipe{name:{recipe}.name}) DELETE r")
    Collection<User> deleteLiked(@Param("user")User user, @Param("recipe")Recipe recipe);


}
