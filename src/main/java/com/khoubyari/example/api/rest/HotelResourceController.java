
package com.khoubyari.example.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoubyari.example.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/rest/hotels")
@RestController
public class HotelResourceController {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllHotels(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}