package br.com.prodpaymanager.controllers.client;

import br.com.prodpaymanager.dto.client.ClientCreationDTO;
import br.com.prodpaymanager.services.client.CreteClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class CreateClientController {

    @Autowired
    CreteClientService creteClientService;

    @PostMapping("/create")
    public ResponseEntity<Object> createClient(@RequestBody ClientCreationDTO clientCreationDTO) {
        try {
            this.creteClientService.creteClient(clientCreationDTO);
            return ResponseEntity.ok().body("Cliente salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
