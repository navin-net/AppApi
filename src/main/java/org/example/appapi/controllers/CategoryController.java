package org.example.appapi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appapi.model.Category;
import org.example.appapi.model.response.BaseResponse;
import org.example.appapi.repositories.CategoryRepository;
import org.example.appapi.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CategoryController {


    private final CategoryService categoryService;
    private  BaseResponse baseResponse;



    @GetMapping("/api/allcategories")
    public ResponseEntity<Object> getAllCategory(){
        log.info("Intercept get all category");
        baseResponse  = new BaseResponse();
        try {
        var list = categoryService.getAllCategories();
        baseResponse.setCode("200");
        baseResponse.setMessage("Get all categories success");
        baseResponse.setMessageKh("Get all categories success");
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            log.info("while error get all category {}",e.getLocalizedMessage());
            baseResponse.setCode("500");
            baseResponse.setMessage("Get all categories unsuccess");
            baseResponse.setMessageKh("Get all categories unsuccess");
            return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }


    @GetMapping("/api/getcatgoryId/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Integer id){
        log.info("Intercept get by category id {}",id);
        baseResponse  = new BaseResponse();
        try {
            var category = categoryService.getCategoryById(id);
            baseResponse.setCode("200");
            baseResponse.setMessage("Get by id categories success");
            baseResponse.setMessageKh("Get by id categories success");
            baseResponse.setData(category);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            log.info("while error get  category by id {}",e.getLocalizedMessage());
            baseResponse.setCode("500");
            baseResponse.setMessage("Get by id categories unsuccess");
            baseResponse.setMessageKh("Get by id categories unsuccess");
            return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/categories/create")
    public ResponseEntity<Object> createCategory(@RequestBody Category req){
        try{
            log.info("Intercept Create category  {}",req);
            categoryService.Create(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }


    @PostMapping("/api/categories/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category req){
        try{
            log.info("Intercept Update  {}",req);
            categoryService.Update(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/categories/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id) {
        try {
            log.info("Intercept Delete  {}",id);
            categoryService.Delete(id);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting category {}", id, e);

            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
