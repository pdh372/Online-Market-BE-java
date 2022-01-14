package com.example.demo.Product;

import com.example.demo.NhanVien.NhanVienEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<?> getProductsByShop(HttpServletRequest request){
        String store = request.getParameter("store");

        List<ProductEntity> products =  productRepository.findByStore(new ObjectId(store));

        if(products.size() > 0)
            return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

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
    public ResponseEntity<?> postProduct(@RequestBody ProductEntity product){
        try {
            productRepository.save(product);
            return new ResponseEntity<ProductEntity>(product, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
