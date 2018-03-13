package com.khoubyari.example.dao.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.khoubyari.example.domain.Hotel;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
/*public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
    Hotel findHotelByCity(String city);
    Page findAll(Pageable pageable);
}
*/

public interface HotelRepository extends MongoRepository<Hotel, Long> {
    /*Hotel findHotelByCity(String city);
    Page findAll(Pageable pageable)*/;
}