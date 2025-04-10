package br.com.prodpaymanager.controllers.payment;

import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;
import br.com.prodpaymanager.dto.payment.PaymentSalesDTO;
import br.com.prodpaymanager.interfaces.payment.IPaymentSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class PaymentSalesController {

    @Autowired
    IPaymentSaleService iPaymentSaleService;

    @PostMapping("/payment")
    public ResponseEntity<Object> paymentProduct(@RequestBody PaymentSalesDTO paymentSalesDTO) {
        try {
            this.iPaymentSaleService.savePaymentSale(paymentSalesDTO);
            return ResponseEntity.ok().body("Pagamento salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
