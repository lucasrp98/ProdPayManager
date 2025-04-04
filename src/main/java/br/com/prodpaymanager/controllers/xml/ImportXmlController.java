package br.com.prodpaymanager.controllers.xml;

import br.com.prodpaymanager.interfaces.xml.IExtractPieceXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class ImportXmlController {

    @Autowired
    IExtractPieceXmlService iExtractPieceXmlService;

    @PostMapping("/importxml")
    public ResponseEntity<Object> importXml(@RequestBody String xml) {
        try {
            var response = this.iExtractPieceXmlService.getPieceXml(xml);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
