package br.com.prodpaymanager.controllers.xml;

import br.com.prodpaymanager.repositories.xml.IExtractProductXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class ImportXmlController {

    @Autowired
    IExtractProductXmlService iExtractProductXmlService;

    @PostMapping("/importxml")
    public ResponseEntity<Object> importXml(@RequestBody String xml) {
        try {
            var response = this.iExtractProductXmlService.getProductXml(xml);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
