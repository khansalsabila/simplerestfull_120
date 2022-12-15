/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wc.s.ProjectRestFull;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER DJOGJA
 */
@RestController
public class ProductController {
    
    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product clothes = new Product();
        clothes.setId("1");
        clothes.setName("Taehyung");
        clothes.setQty("2");
        clothes.setPrice("150.000");
        productRepo.put(clothes.getId(), clothes);

        Product skrits = new Product();
        skrits.setId("2");
        skrits.setName("BlackPink");
        skrits.setQty("3");
        skrits.setPrice("300.000");
        productRepo.put(skrits.getId(), skrits);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        //kondisi dimana jika  productrepo dengan nilai id yg dicari tidak ada, maka data tidak dapat diupdate
        if(!productRepo.containsKey(id)){
        return new ResponseEntity<>("product Tidak ada, cek lagi", HttpStatus.NOT_FOUND);}
       
        //kondisi dimana productrepo nilai id ada dan sama maka data bisa di update
        else{
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("product is update successfully", HttpStatus.OK);}
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        
        //kondisi dimana HashMap product repo diambil key object ID nya di product
        //jika productrepo sama dgn nilai id maka akan return id tidak boleh sama
        if(productRepo.containsKey(product.getId())) {
        return new ResponseEntity<>("ID product tidak boleh sama,tolong periksa kembali", HttpStatus.OK);}
        
        //jika ternyata id productrepo tidak ada yang sama maka created berhasil
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);}
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}

    

