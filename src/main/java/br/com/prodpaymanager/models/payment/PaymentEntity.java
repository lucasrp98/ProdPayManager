package br.com.prodpaymanager.models.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    private int id;

    @Column(name = "form_payment")
    private String formPayment;

    @Column(name = "value_total")
    private double valueTotal;

    @Column(name = "number_installments")
    private Integer numberInstallments;

    @Column(name = "date_purchase")
    private Date datePurchase;

    @Column(name = "day_expiration_installment")
    private Integer dayExpirationInstallment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
