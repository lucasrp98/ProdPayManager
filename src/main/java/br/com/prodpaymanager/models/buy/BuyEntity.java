package br.com.prodpaymanager.models.buy;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.models.piece_buy.PieceBuyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "buy")
public class BuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buy_seq")
    @SequenceGenerator(name = "buy_seq", sequenceName = "buy_seq", allocationSize = 1)
    private int id;

    @OneToMany(mappedBy = "buy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceBuyEntity> pieceBuyEntities = new ArrayList<>();

    @OneToOne
    private PaymentEntity paymentEntity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
