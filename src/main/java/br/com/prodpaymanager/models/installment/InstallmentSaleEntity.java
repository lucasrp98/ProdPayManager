package br.com.prodpaymanager.models.installment;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.models.payment.PaymentSaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "installmentsale")
public class InstallmentSaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installmentsale_seq")
    @SequenceGenerator(name = "installmentsale_seq", sequenceName = "installmentsale_seq", allocationSize = 1)
    private int id;

    @Column(name = "installment_number")
    private int installmentNumber;

    @Column(name = "installment_value")
    private double installmentValue;

    @Column(name = "date_expiration_installment")
    private LocalDateTime dateExpirationInstallment;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentSaleEntity paymentSaleEntity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
