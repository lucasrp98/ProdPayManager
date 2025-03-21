package br.com.prodpaymanager.controllers.seller;

import br.com.prodpaymanager.dto.seller.SellerCreationDTO;
import br.com.prodpaymanager.services.seller.CreateSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class CreateSellerController {

    @Autowired
    CreateSellerService iCreateSellerService;

    @PostMapping("/create")
    public ResponseEntity<Object> createClient(@RequestBody SellerCreationDTO sellerCreationDTO) {
        try {
            this.iCreateSellerService.creteSeller(sellerCreationDTO);
            return ResponseEntity.ok().body("Vendedor salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
