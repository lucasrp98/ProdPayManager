package br.com.prodpaymanager.services.payment;

import br.com.prodpaymanager.Interfaces.payment.IPaymentPieceService;
import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;
import br.com.prodpaymanager.models.buy.BuyEntity;
import br.com.prodpaymanager.models.installment.InstallmentEntity;
import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.repositories.buy.BuyRepository;
import br.com.prodpaymanager.repositories.installment.InstallmentRepository;
import br.com.prodpaymanager.repositories.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;

@Service
public class PaymentPieceService implements IPaymentPieceService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    BuyRepository buyRepository;

    @Override
    public void savePayment(PaymentCreationDTO paymentCreationDTO) {
        PaymentEntity paymentEntity = paymentCreationDTO.toProductEntity();
        this.paymentRepository.save(paymentEntity);
        saveInstallment(paymentEntity);

        Optional<BuyEntity> optionalBuy = buyRepository.findById(paymentCreationDTO.getBuyId());

        if (optionalBuy.isPresent()) {
            BuyEntity buy = optionalBuy.get();
            buy.setPaymentEntity(paymentEntity);
            buyRepository.save(buy);
        } else {
            throw new RuntimeException("Compra não encontrada para o ID: " + paymentCreationDTO.getBuyId());
        }
    }

    public void saveInstallment(PaymentEntity paymentEntity){
        List<InstallmentEntity> installments = new ArrayList<>();
        double valorParcela = paymentEntity.getValueTotal() / paymentEntity.getNumberInstallments();

        Date dataCompra = paymentEntity.getDatePurchase();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataCompra);

        for (int i = 1; i <= paymentEntity.getNumberInstallments(); i++) {
            InstallmentEntity installment = new InstallmentEntity();
            installment.setInstallmentNumber(i);
            installment.setInstallmentValue(valorParcela);

            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, paymentEntity.getDayExpirationInstallment());

            installment.setDateExpirationInstallment(calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay());

            installment.setPaymentEntity(paymentEntity);
            installments.add(installment);
            this.installmentRepository.save(installment);
        }
    }
}
