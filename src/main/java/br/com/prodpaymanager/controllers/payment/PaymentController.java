package br.com.prodpaymanager.controllers.payment;

import br.com.prodpaymanager.interfaces.payment.IPaymentPieceService;
import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class PaymentController {

    @Autowired
    IPaymentPieceService iPaymentPieceService;

    @PostMapping("/payment")
    public ResponseEntity<Object> paymentProduct(@RequestBody PaymentCreationDTO paymentCreationDTO) {
        try {
            this.iPaymentPieceService.savePayment(paymentCreationDTO);
            return ResponseEntity.ok().body("Pagamento salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
