package br.com.prodpaymanager.services.payment;

import br.com.prodpaymanager.dto.payment.PaymentSalesDTO;
import br.com.prodpaymanager.interfaces.payment.IPaymentSaleService;
import br.com.prodpaymanager.models.buy.BuyEntity;
import br.com.prodpaymanager.models.installment.InstallmentEntity;
import br.com.prodpaymanager.models.installment.InstallmentSaleEntity;
import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.models.payment.PaymentSaleEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
import br.com.prodpaymanager.repositories.installment.InstallmentSaleRepository;
import br.com.prodpaymanager.repositories.payment.PaymentSaleRepository;
import br.com.prodpaymanager.repositories.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;

@Service
public class PaymentSaleService implements IPaymentSaleService {

    @Autowired
    PaymentSaleRepository paymentSaleRepository;

    @Autowired
    InstallmentSaleRepository installmentSaleRepository;

    @Autowired
    SaleRepository saleRepository;

    @Override
    public void savePaymentSale(PaymentSalesDTO paymentSalesDTO) {
        PaymentSaleEntity paymentSaleEntity = paymentSalesDTO.toPaymentSaleEntity();
        this.paymentSaleRepository.save(paymentSaleEntity);

        saveInstallment(paymentSaleEntity);

        Optional<SaleEntity> optionalSale = saleRepository.findById(paymentSalesDTO.getSaleID());

        if (optionalSale.isPresent()) {
            SaleEntity saleEntity = optionalSale.get();
            saleEntity.setPaymentSaleEntity(paymentSaleEntity);
            saleRepository.save(saleEntity);
        } else {
            throw new RuntimeException("Compra não encontrada para o ID: " + paymentSalesDTO.getSaleID());
        }


    }

    public void saveInstallment(PaymentSaleEntity paymentSaleEntity){
        List<InstallmentSaleEntity> installments = new ArrayList<>();
        double valorParcela = paymentSaleEntity.getValueTotal() / paymentSaleEntity.getNumberInstallments();

        Date dataCompra = paymentSaleEntity.getDateSale();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataCompra);

        for (int i = 1; i <= paymentSaleEntity.getNumberInstallments(); i++) {
            InstallmentSaleEntity installment = new InstallmentSaleEntity();
            installment.setInstallmentNumber(i);
            installment.setInstallmentValue(valorParcela);

            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, paymentSaleEntity.getDayExpirationSaleInstallment());

            installment.setDateExpirationInstallment(calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay());

            installment.setPaymentSaleEntity(paymentSaleEntity);
            installments.add(installment);
            this.installmentSaleRepository.save(installment);
        }
    }
}
