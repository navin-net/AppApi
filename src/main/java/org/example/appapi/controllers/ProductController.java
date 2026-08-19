package org.example.appapi.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Category;
import org.example.appapi.model.Product;
import org.example.appapi.model.response.BaseResponse;
import org.example.appapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private BaseResponse baseResponse;

    @GetMapping("/product")
    public ResponseEntity<Object> getAllProducts(){
        BaseResponse baseResponse = new BaseResponse();
        try {
            var products = productService.getAllProducts();
            baseResponse.setCode("200");
            baseResponse.setMessage("Get all products success");
            baseResponse.setMessageKh("Get all products success");
            baseResponse.setData(products);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error getting all products", e);
            baseResponse.setCode("500");
            baseResponse.setMessage("Get all products unsuccess");
            baseResponse.setMessageKh("Get all products unsuccess");
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id){
        log.info("Intercept get by product id {}",id);
        baseResponse  = new BaseResponse();
        try {
            var product = productService.getProductById(id);
            baseResponse.setCode("200");
            baseResponse.setMessage("Get by id product success");
            baseResponse.setMessageKh("Get by id product success");
            baseResponse.setData(product);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Throwable e) {
            log.info("while error get  product by id {}",e.getLocalizedMessage());
            baseResponse.setCode("500");
            baseResponse.setMessage("Get by id product unsuccess");
            baseResponse.setMessageKh("Get by id product unsuccess");
            return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/product/category/{id}")
    public ResponseEntity<Object> getProductByCategory(@PathVariable("id") Integer id){
        log.info("Intercept get by category product id {}", id);
        baseResponse = new BaseResponse();
        try {
            var products = productService.getProductsByCategoryId(id);
            baseResponse.setCode("200");
            baseResponse.setMessage("Get by category product success");
            baseResponse.setMessageKh("Get by category product success");
            baseResponse.setData(products);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error getting products by category id {}", e.getLocalizedMessage());
            baseResponse.setCode("500");
            baseResponse.setMessage("Get by category product unsuccess");
            baseResponse.setMessageKh("Get by category product unsuccess");
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product/create")
    public ResponseEntity<BaseResponse> createProduct(@RequestBody Product req) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("Create product: {}", req);
            productService.Create(req);
            response.setCode("SUCCESS");
            response.setMessage("product created successfully");
            response.setMessageKh("បង្កើតប្រភេទទំនិញបានជោគជ័យ");
            response.setData(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (WebException e) {
            log.warn("Create product failed: {}", e.getMessage());
            response.setCode(e.getCode());
//            response.setData(req);
            response.setMessage(e.getMessage());
            response.setMessageKh(e.getMessageKh());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Unexpected error while creating product", e);
            response.setCode("ERR-500");
            response.setMessage("Internal server error");
            response.setMessageKh("មានបញ្ហាក្នុងប្រព័ន្ធ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Controller
    @PutMapping("/product/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Product req){
        BaseResponse baseResponse = new BaseResponse();
        try{
            log.info("Intercept Update {}", req);
            productService.Update(req);
            baseResponse.setCode("200");
            baseResponse.setMessage("Update product success");
            baseResponse.setMessageKh("Update product success");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error updating product", e);
            baseResponse.setCode("500");
            baseResponse.setMessage("Update product unsuccess");
            baseResponse.setMessageKh("Update product unsuccess");
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    

}
