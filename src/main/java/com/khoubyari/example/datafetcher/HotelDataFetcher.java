
package com.khoubyari.example.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khoubyari.example.dao.jpa.HotelRepository;
import com.khoubyari.example.domain.Hotel;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class HotelDataFetcher implements DataFetcher<Hotel> {

    @Autowired
    HotelRepository hotelRepository;
    
    @Override
    public Hotel get(DataFetchingEnvironment dataFetchingEnvironment) {
        // TODO Auto-generated method stub
        int isn=dataFetchingEnvironment.getArgument("id");
        return hotelRepository.findOne((long) isn);
    }

}
