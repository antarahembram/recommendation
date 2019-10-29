package com.stackroute.recommendationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.recommendationservice.model.OperationDTO;
import com.stackroute.recommendationservice.model.Recipe;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.service.RecommendationCommandService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1")
public class RecommendationCommandController {


    @Autowired
    RecommendationCommandService recommendationCommandService;
    RabbitTemplate rabbitTemplate;
    @PostMapping("/user")
    public  ResponseEntity<Collection<User>> user(@RequestBody User user) throws JsonProcessingException, InterruptedException {

        ResponseEntity responseEntity;

        return new ResponseEntity<Collection<User>>(recommendationCommandService.addUser(user),HttpStatus.CREATED);


    }

    @PostMapping("/recipe")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) throws JsonProcessingException {

        ResponseEntity responseEntity;

        return new ResponseEntity<Collection<Recipe>>(recommendationCommandService.addRecipe(recipe),HttpStatus.CREATED);
    }


    @PostMapping("/dto")
    public ResponseEntity<?> hhh(@RequestBody OperationDTO operationDTO)
    {
       // new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        return new ResponseEntity<Collection<User>>(recommendationCommandService.createLikedOrTried(operationDTO),HttpStatus.OK);
    }

//
//    @GetMapping("/dd")
//    public ResponseEntity<?> hhhh(@RequestBody User user)
//    {
//        return new ResponseEntity<Collection<User>>(recommendationCommandService.jjj(user),HttpStatus.OK);
//    }
//
//
    @PostMapping("/operation")
    public ResponseEntity<?> operation(@RequestBody OperationDTO operationDTO)
    {
        return new ResponseEntity<Collection<User>>(recommendationCommandService.createLikedOrTried(operationDTO),HttpStatus.OK);
    }

//    @GetMapping("/recipepub")
//    public ResponseEntity<?> publish()
//    {
//        return new ResponseEntity<Collection<User>>(recommendationCommandService.getPublished(),HttpStatus.OK);
//    }


}
