package curso.neto.store.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import curso.neto.store.product.entity.Category;
import curso.neto.store.product.entity.Product;
import curso.neto.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = ("/api/products"))
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "category/{categoryId}")
    //localhost:8091/api/products/category/1   localhost:8091/api/products
    public ResponseEntity<List<Product>> listProductsByIdCategory(@PathVariable("categoryId") Long categoryId){
        List<Product> products = new ArrayList<>();
        if(null == categoryId){
            products = productService.listAllProducts();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping //Tambien obtiene la categoria por id por la via de un Param
    //localhost:8091/api/products?category=2   localhost:8091/api/products
    public ResponseEntity<List<Product>> listProducts(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();
        if(null == categoryId){
            products = productService.listAllProducts();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}") //localhost:8091/api/products/2
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(null == product){
            return  ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping
    /*{
        "name": "Nike Air Jordan",
            "description": "Nike Air Jordan Loop Edition ",
            "stock": 50,
            "price": 2700,
            "category": {
        "id": 1,
                "name": "shoes"
    }
    }*/
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult resultado){
        if (resultado.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(resultado));
        }
        Product productCrear = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCrear);
    }

    @PutMapping(path = "/{id}")
    public  ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productAct = productService.updateProduct(product);
        if(productAct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productAct);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if(productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

    @GetMapping(value = "/stock/{id}")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,@RequestParam (name = "quantity",required = true) Double quantity){
        Product productUpt = productService.updateStock(id,quantity);
        if(productUpt == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productUpt);
    }
    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMensaje errorMensaje = ErrorMensaje.builder()
                .code("01")
                .mensaje(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try {
            json = mapper.writeValueAsString(errorMensaje);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }//fin Try Catch
        return json;

    }//Fin formtaMessage
}
