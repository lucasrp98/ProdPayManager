package br.com.prodpaymanager.interfaces.payment;

import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;

public interface IPaymentPieceService {
    void savePayment(PaymentCreationDTO paymentCreationDTO);
}
