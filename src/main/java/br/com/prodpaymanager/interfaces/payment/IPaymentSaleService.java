package br.com.prodpaymanager.interfaces.payment;

import br.com.prodpaymanager.dto.payment.PaymentSalesDTO;

public interface IPaymentSaleService {
    void savePaymentSale(PaymentSalesDTO paymentSalesDTO);
}
