package br.com.prodpaymanager.dto.piece;

import br.com.prodpaymanager.models.piece.PieceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PieceCreationDTO {

    private String cEAN;

    private String xProd;

    private int qCom;

    private double vUnCom;

    public PieceEntity toPieceEntity() {
        PieceEntity pieceEntity = new PieceEntity();
        pieceEntity.setCEAN(this.getCEAN());
        pieceEntity.setXProd(this.getXProd());
        pieceEntity.setQCom(this.getQCom());
        pieceEntity.setVUnCom(this.getVUnCom());
        return pieceEntity;
    }
}
