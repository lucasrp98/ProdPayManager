package br.com.prodpaymanager.services.payment;

import br.com.prodpaymanager.Interfaces.payment.IPaymentProductService;
import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;
import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.repositories.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentProductService implements IPaymentProductService{

    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public void savePayment(PaymentCreationDTO paymentCreationDTO) {
        PaymentEntity paymentEntity = paymentCreationDTO.toProductEntity();
        this.paymentRepository.save(paymentEntity);
    }
}
