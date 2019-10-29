package com.stackroute.recommendationservice.service;


import com.stackroute.recommendationservice.model.OperationDTO;
import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;

import java.util.Collection;

public interface RecommendationCommandService {

    public Collection<User> addUser(User user) ;

    public Collection<Recipe> addRecipe(Recipe recipe);

    Collection<User> createLikedOrTried(OperationDTO operationDTO);
}
