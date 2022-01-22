package com.example.demo.Product;

import com.example.demo.NhanVien.NhanVienEntity;
import org.bson.types.ObjectId;
import com.example.demo.Store.StoreEntity;
import com.example.demo.Store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    StoreRepository storeRepository;

    @DeleteMapping("/{_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("_id") String _id) {
        Optional<ProductEntity> product = productRepository.findById(_id);
        if(product.isPresent()){
            ProductEntity resProduct = product.get();
            productRepository.deleteById(_id);

            return new ResponseEntity<ProductEntity>(resProduct, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Not Found id: " + _id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> postProduct(@RequestBody ProductEntity product) {
        try {
            productRepository.save(product);
            return new ResponseEntity<ProductEntity>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{_id}")
    public ResponseEntity<?> putProduct(@PathVariable("_id") String _id, @RequestBody ProductEntity newProduct){
        Optional<ProductEntity> product = productRepository.findById(_id);
        if(product.isPresent()){

            ProductEntity resProduct = product.get();
            resProduct.setName(newProduct.getName());
            resProduct.setDescription(newProduct.getDescription());
            resProduct.setPrice(newProduct.getPrice());
            resProduct.setUnit(newProduct.getUnit());
            resProduct.setCategory(newProduct.getCategory());
            return new ResponseEntity<ProductEntity>(productRepository.save(resProduct), HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Not Found id: " + _id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<?> getProducts(HttpServletRequest request) {
        String store = request.getParameter("store");

        if(store != null){
            List<ProductEntity> products =  productRepository.findByStore(store);

            if(products.size() > 0)
                return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
            else
                return new ResponseEntity<>("Not Found gì hết" + store, HttpStatus.NOT_FOUND);
        } else {
            List<ProductEntity> products =  productRepository.findAll();

            if(products.size() > 0)
                return new ResponseEntity<>(products, HttpStatus.OK);
            else
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
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

        ProductStoreRequestEntity temp = new ProductStoreRequestEntity();
        if(product.isPresent()) {
            Optional<StoreEntity> store = storeRepository.findById(product.get().getStore());
            temp.setProduct(product.get());
            store.ifPresent(temp::setStore);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
