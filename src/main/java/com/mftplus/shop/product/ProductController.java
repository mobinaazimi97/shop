package com.mftplus.shop.product;

import com.mftplus.shop.product.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID uuid,
                                                    @RequestBody ProductDto productDto) {
        ProductDto updated = productService.update(uuid, productDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productService.findAll();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID uuid) {
        productService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productService.getByUuid(uuid));
    }

    //TODO debugging...
//    @GetMapping("/group/{productGroupUuid}")
//    public ResponseEntity<ProductDto> getByProductGroupId(@PathVariable UUID productGroupId) {
//        ProductDto result = productService.findByProductGroupId(productGroupId);
//        return ResponseEntity.ok(result);
//    }

    //TODO debugging...
//    @GetMapping("/group/{title}")
//    public ResponseEntity<ProductDto> getByProductGroupTile(@PathVariable String title) {
//        ProductDto result = productService.findByProductGroupTitle(title);
//        return ResponseEntity.ok(result);
//    }

//For View Controller
    //@GetMapping("/products/new")
    //public String showCreateProductForm(Model model) {
    //    List<ProductGroup> groups = productGroupRepository.findByIsDeletedFalse();
    //    model.addAttribute("productDto", new ProductDto());
    //    model.addAttribute("productGroups", groups);
    //    return "product-form";
    //}

}
