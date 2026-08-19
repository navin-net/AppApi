package org.example.appapi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Category;
import org.example.appapi.model.response.BaseResponse;
import org.example.appapi.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private  BaseResponse baseResponse;

    @GetMapping("/allcategories")
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

    @GetMapping("/getcatgoryId/{id}")
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

    @PostMapping("/categories/create")
    public ResponseEntity<BaseResponse> createCategory(@RequestBody Category req) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("Create category: {}", req);
            categoryService.Create(req);
            response.setCode("SUCCESS");
            response.setMessage("Category created successfully");
            response.setMessageKh("បង្កើតប្រភេទទំនិញបានជោគជ័យ");
            response.setData(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (WebException e) {
            log.warn("Create category failed: {}", e.getMessage());
            response.setCode(e.getCode());
            response.setData(req);
            response.setMessage(e.getMessage());
            response.setMessageKh(e.getMessageKh());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Unexpected error while creating category", e);
            response.setCode("ERR-500");
            response.setMessage("Internal server error");
            response.setMessageKh("មានបញ្ហាក្នុងប្រព័ន្ធ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/categories/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category req){
        try{
            log.info("Intercept Update  {}",req);
            categoryService.Update(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping("/categories/delete/{id}")
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
