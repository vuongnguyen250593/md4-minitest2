package cg.controller;

import cg.model.Product;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ResponseEntity<Page<Product>> showAll(Pageable pageable) {
        Page<Product> products = iProductService.findAll(pageable);
        if (products.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showOne(@PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product product1 = iProductService.save(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> aditProduct(@RequestBody Product productEidt, @PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productEidt.setId(product.get().getId());
        productEidt = iProductService.save(productEidt);
        return new ResponseEntity<>(productEidt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.delete(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> showAllByName(@RequestParam("search") String search) {
        Iterable<Product> products = iProductService.findAllByName(search);
        if (!products.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
