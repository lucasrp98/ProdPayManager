package br.com.prodpaymanager.models.piece;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "piece")
public class PieceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "piece_seq")
    @SequenceGenerator(name = "piece_seq", sequenceName = "piece_seq", allocationSize = 1)
    private int id;

    @Column(name = "cean")
    private String cEAN;

    @Column(name = "xprod")
    private String xProd;

    @Column(name = "qcom")
    private int qCom;

    @Column(name = "vuncom")
    private double vUnCom;

    @Transient //
    private int quantityUsed;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
