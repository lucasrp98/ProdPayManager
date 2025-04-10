package br.com.prodpaymanager.dto.payment;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.models.payment.PaymentSaleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentSalesDTO {

    private String formPayment;

    private double valueTotal;

    private Integer numberInstallments;

    private Date dateSale;

    private Integer dayExpirationSaleInstallment;

    private Integer saleID;

    public PaymentSaleEntity toPaymentSaleEntity() {
        PaymentSaleEntity paymentSaleEntity = new PaymentSaleEntity();
        paymentSaleEntity.setFormPayment(this.formPayment);
        paymentSaleEntity.setValueTotal(this.valueTotal);
        paymentSaleEntity.setNumberInstallments(this.numberInstallments);
        paymentSaleEntity.setDateSale(this.dateSale);
        paymentSaleEntity.setDayExpirationSaleInstallment(this.dayExpirationSaleInstallment);
        return paymentSaleEntity;
    }

}
