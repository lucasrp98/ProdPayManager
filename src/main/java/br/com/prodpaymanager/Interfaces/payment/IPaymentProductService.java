package br.com.prodpaymanager.Interfaces.payment;

import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;

public interface IPaymentProductService {
    void savePayment(PaymentCreationDTO paymentCreationDTO);
}
