package com.mftplus.shop.product;

import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
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

//    @PostMapping
//    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
//        ProductDto saveProductDto = productService.save(productDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saveProductDto);
//    }
//
//    @PutMapping("/{uuid}")
//    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID uuid, @RequestBody ProductDto productDto) {
//        ProductDto updatedProductDto = productService.update(uuid, productDto);
//        return ResponseEntity.ok(updatedProductDto);
//    }

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

    @GetMapping("/productGroups/{uuid}")
    public ResponseEntity<List<ProductDto>> getProductsByGroup(@PathVariable UUID uuid) {
        List<ProductDto> products = productService.findByProductGroup(uuid);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productService.findByProductGroup(uuid));
    }

//    @GetMapping("/productGroups")
//    public ResponseEntity<List<ProductGroupDto>> getAllProductGroups() {
//        List<ProductGroupDto> groups = productService.getAllActiveGroups();
//
//        if (groups.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(groups);
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
