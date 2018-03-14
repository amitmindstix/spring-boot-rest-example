
package com.khoubyari.example.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khoubyari.example.dao.jpa.HotelRepository;
import com.khoubyari.example.domain.Hotel;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllHotelDataFetcher implements DataFetcher<List<Hotel>> {

    @Autowired
    HotelRepository HotelRepository;
    @Override
    public List<Hotel> get(DataFetchingEnvironment dataFetchingEnvironment) {
        // TODO Auto-generated method stub
        return HotelRepository.findAll();
    }

}
