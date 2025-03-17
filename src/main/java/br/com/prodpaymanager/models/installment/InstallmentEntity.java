package br.com.prodpaymanager.models.installment;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "installment")
public class InstallmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installment_seq")
    @SequenceGenerator(name = "installment_seq", sequenceName = "installment_seq", allocationSize = 1)
    private int id;

    @Column(name = "installment_number")
    private int installmentNumber;

    @Column(name = "installment_value")
    private double installmentValue;

    @Column(name = "date_expiration_installment")
    private LocalDateTime dateExpirationInstallment;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentEntity paymentEntity;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
