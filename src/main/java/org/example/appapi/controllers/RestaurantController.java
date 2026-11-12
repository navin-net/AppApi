package org.example.appapi.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appapi.dto.request.RestaurantRequest;
import org.example.appapi.dto.response.BaseResponse;
import org.example.appapi.dto.response.RestaurantResponse;
import org.example.appapi.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(BaseResponse.success(
                "Get all restaurants success","ទទួលបានជោគជ័យសម្រាប់ភោជនីយដ្ឋានទាំងអស់", restaurantService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable @Min(1) Integer id) {
        log.info("Get restaurant by id {}", id);
        return ResponseEntity.ok(BaseResponse.success(
                "Get restaurants by id success","Get restaurants by id success", restaurantService.getById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody RestaurantRequest request) {
        return ResponseEntity.ok(BaseResponse.success(
                "Create restaurant success", restaurantService.save(request)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Integer id,
                                               @Valid @RequestBody RestaurantRequest request) {
        return ResponseEntity.ok(BaseResponse.success(
                "Update restaurant success", restaurantService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Integer id) {
        restaurantService.delete(id);
        return ResponseEntity.ok(BaseResponse.success("Delete restaurant success", restaurantService.getById(id)));
    }




}
