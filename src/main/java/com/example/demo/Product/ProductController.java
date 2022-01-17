package com.example.demo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<?> getProducts(){
        List<ProductEntity> products =  productRepository.findAll();

        if(products.size() > 0)
                return new ResponseEntity<>(products, HttpStatus.OK);
        else
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{keyWord}")
    public ResponseEntity<?> searchProducts(@PathVariable("keyWord") String keyWord){
        String key = ".*" + keyWord + ".*";
        //String key2 =
        List<ProductEntity> products =  productRepository.findByNameRegex(key);

        if(products.size() > 0)
            return new ResponseEntity<>(products, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") String productId){
        Optional<ProductEntity> product =  productRepository.findById(productId);

        if(product.isPresent())
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
