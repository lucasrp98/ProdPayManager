package br.com.prodpaymanager.controllers.product;

import br.com.prodpaymanager.dto.product.ProductCreationDTO;
import br.com.prodpaymanager.interfaces.product.ICreateProductService;
import br.com.prodpaymanager.services.product.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    CreateProductService createProductService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreationDTO productCreationDTO) {
        try {
            this.createProductService.saveProduct(productCreationDTO);
            return ResponseEntity.ok().body("Produto salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
