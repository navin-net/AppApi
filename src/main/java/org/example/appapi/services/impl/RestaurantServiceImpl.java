package org.example.appapi.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.appapi.dto.request.RestaurantRequest;
import org.example.appapi.dto.response.RestaurantResponse;
import org.example.appapi.exceptions.ErrorCode;
import org.example.appapi.exceptions.GlobalExceptionHandler;
import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Restaurant;
import org.example.appapi.repositories.RestaurantRepository;
import org.example.appapi.services.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.appapi.exceptions.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new WebException(NOT_FOUND));
    }

    @Override
    public Restaurant save(RestaurantRequest request) {
        if (restaurantRepository.existsByNameIgnoreCase(request.getName().trim())) {
            throw new WebException(ErrorCode.DUPLICATE_DATA);
        }
        Restaurant restaurant = new Restaurant();
        apply(restaurant, request);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Integer id, RestaurantRequest request) {

        Restaurant restaurant = getById(id);
        if(restaurantRepository.existsByNameIgnoreCase(request.getName().trim())) {
            throw new WebException(ErrorCode.DUPLICATE_DATA);
        }
        apply(restaurant, request);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Integer id) throws WebException {
        if (!restaurantRepository.existsById(id)) {
            throw new WebException(
                    ErrorCode.NOT_FOUND,
                    String.valueOf(id)
            );
        }
        restaurantRepository.deleteById(id);
    }

    private void apply(Restaurant restaurant, RestaurantRequest request) {
        restaurant.setName(request.getName().trim());
        restaurant.setPhone(request.getPhone());
        restaurant.setEmail(request.getEmail());
        restaurant.setAddress(request.getAddress());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setCurrency(request.getCurrency() != null ? request.getCurrency() : "USD");
        restaurant.setActive(request.getActive() == null || request.getActive());
    }

}
