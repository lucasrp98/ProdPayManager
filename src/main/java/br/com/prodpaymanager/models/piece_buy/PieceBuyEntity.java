package br.com.prodpaymanager.models.piece_buy;

import br.com.prodpaymanager.models.buy.BuyEntity;
import br.com.prodpaymanager.models.piece.PieceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "piece_has_buy")
public class PieceBuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "piece_buy_seq")
    @SequenceGenerator(name = "piece_buy_seq", sequenceName = "piece_buy_seq", allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buy_id", nullable = false)
    private BuyEntity buy;

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private PieceEntity piece;

    @Column(name = "qcom")
    private int qCom;
    @Column(name = "vuncom")
    private String vUnCom;
}
