package org.example.appapi.services;

import org.example.appapi.dto.request.RestaurantRequest;
import org.example.appapi.dto.response.RestaurantResponse;
import org.example.appapi.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    Restaurant getById(Integer id);

    Restaurant save(RestaurantRequest request);

    Restaurant update(Integer id, RestaurantRequest request);

    void delete(Integer id);

}
