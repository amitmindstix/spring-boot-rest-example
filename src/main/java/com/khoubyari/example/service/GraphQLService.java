
package com.khoubyari.example.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.khoubyari.example.dao.jpa.HotelRepository;
import com.khoubyari.example.datafetcher.AllHotelDataFetcher;
import com.khoubyari.example.datafetcher.HotelDataFetcher;
import com.khoubyari.example.domain.Hotel;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

    @Value("classpath:hotel.graphql")
    Resource resource;
    private GraphQL graphQL;
    
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    private AllHotelDataFetcher allHotelDataFetcher;
    @Autowired
    private HotelDataFetcher hotelDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository
        loadDataIntoMongo();

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoMongo() {

        Stream.of(
                new Hotel(1, "Prashant", "Developer","Nanded", 4),
                new Hotel(2, "Aniket", "Devops","Aurangabad", 5),
                new Hotel(3, "Kalpesh", "Automation","Sangli", 4)
        ).forEach(hotel -> {
            hotelRepository.save(hotel);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allHotels", allHotelDataFetcher)
                        .dataFetcher("hotel", hotelDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}