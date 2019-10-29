package com.stackroute.recommendationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.model.OperationDTO;
import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner {

    @Autowired
    private RecommendationCommandService recommendationCommandService;

//    @RabbitListener(queues = "recommendation")
//    public void recievedMessage(String in) throws JsonProcessingException {
//        OperationDTO operationDTO=new ObjectMapper().readValue(in,OperationDTO.class);
//        System.out.println("Recieved Message From RabbitMQ: " + in);
//
//       recommendationCommandService.createLikedOrTried(operationDTO);
//    }

    @RabbitListener(queues = "user-profile")
    public void recievedUserMessage(User user)  {
//        User user=new ObjectMapper().readValue(in, User.class);
        System.out.println("Recieved Message From RabbitMQ: " + user.toString()+user);

       // recommendationCommandService.addUser(user);
    }

//    @RabbitListener(queues = "recipe")
//    public void recievedRecipeMessage(String in) throws JsonProcessingException {
//        Recipe recipe =new ObjectMapper().readValue(in, Recipe.class);
//        System.out.println("Recieved Message From RabbitMQ: " + in);
//
//        recommendationCommandService.addRecipe(recipe);
//    }
}
