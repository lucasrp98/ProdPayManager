package br.com.prodpaymanager.models.payment;

import br.com.prodpaymanager.models.sale.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paymentsale")
public class PaymentSaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentsale_seq")
    @SequenceGenerator(name = "paymentsale_seq", sequenceName = "paymentsale_seq", allocationSize = 1)
    private int id;

    @Column(name = "form_payment")
    private String formPayment;

    @Column(name = "value_total")
    private double valueTotal;

    @Column(name = "number_installments")
    private Integer numberInstallments;

    @Column(name = "date_sale")
    private Date dateSale;

    @Column(name = "day_expiration_sale_installment")
    private Integer dayExpirationSaleInstallment;

    @Column(name = "day_expiration_installment")
    private Integer clientId;

    @OneToMany(mappedBy = "paymentSaleEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleEntity> sales;
}
