package br.com.prodpaymanager.dto.payment;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreationDTO {

    private String formPayment;

    private double valueTotal;

    private Integer numberInstallments;

    private Date datePurchase;

    private Integer dayExpirationInstallment;

    private Integer buyId;

    public PaymentEntity toProductEntity() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setFormPayment(this.formPayment);
        paymentEntity.setValueTotal(this.valueTotal);
        paymentEntity.setNumberInstallments(this.numberInstallments);
        paymentEntity.setDatePurchase(this.datePurchase);
        paymentEntity.setDayExpirationInstallment(this.dayExpirationInstallment);
        return paymentEntity;
    }

}
