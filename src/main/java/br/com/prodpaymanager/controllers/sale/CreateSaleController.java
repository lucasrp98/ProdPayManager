package br.com.prodpaymanager.controllers.sale;

import br.com.prodpaymanager.dto.sale.SaleCreationDTO;
import br.com.prodpaymanager.services.sale.CreateSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class CreateSaleController {

    @Autowired
    CreateSaleService createSaleService;

    @PostMapping("/create")
    public ResponseEntity<Object> createSale(@RequestBody SaleCreationDTO saleCreationDTO) {
        try {
            this.createSaleService.creteSale(saleCreationDTO);
            return ResponseEntity.ok().body("Venda salva com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
