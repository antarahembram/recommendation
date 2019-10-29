package com.stackroute.recommendationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.model.OperationDTO;
import com.stackroute.recommendationservice.model.Rating;
import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.RecommendationCommandRepoOperation;
import com.stackroute.recommendationservice.repository.RecommendationCommandRepository;
import com.stackroute.recommendationservice.repository.RecommendationCommandRepositoryRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecommendationCommandServiceImpl implements RecommendationCommandService{

    @Autowired
    private  RecommendationCommandRepository recommendationCommandRepository;

    public RecommendationCommandServiceImpl(RecommendationCommandRepository recommendationCommandRepository) {
        this.recommendationCommandRepository = recommendationCommandRepository;
    }



    @Override
    public Collection<User> addUser(User user){
        recommendationCommandRepository.createUser(user);
        recommendationCommandRepository.staysIn(user);
        return recommendationCommandRepository.interestedCuisine(user,user.getCuisine());


    }

    @Override
    public Collection<Recipe> addRecipe(Recipe recipe) {
        recommendationCommandRepository.createRecipe(recipe);
         recommendationCommandRepository.linkCourse(recipe);
        return  recommendationCommandRepository.createPublished(recipe);

    }


    @Override
    public Collection<User> createLikedOrTried(OperationDTO operationDTO) {

        if(operationDTO.getOperation().equals("liked"))
            return recommendationCommandRepository.createLiked(operationDTO.getUser(),operationDTO.getRecipe());
        else if(operationDTO.getOperation().equals("tried"))
            return recommendationCommandRepository.createTried(operationDTO.getUser(),operationDTO.getRecipe());
        else
            return recommendationCommandRepository.deleteLiked(operationDTO.getUser(),operationDTO.getRecipe());
    }

}
