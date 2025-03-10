package br.com.prodpaymanager.models.buy;

import br.com.prodpaymanager.dto.piece.PieceCreationDTO;
import br.com.prodpaymanager.models.payment.PaymentEntity;
import br.com.prodpaymanager.models.piece.PieceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "piece_has_buy", joinColumns = @JoinColumn(name = "buy_id"),
            inverseJoinColumns = @JoinColumn(name = "piece_id"))
    private List<PieceEntity> pieceEntity;

    @OneToOne
    private PaymentEntity paymentEntity;

    @Column(name = "qcom")
    private int qCom;
    @Column(name = "vuncom")
    private String vUnCom;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
