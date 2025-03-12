package br.com.prodpaymanager.services.piece;

import br.com.prodpaymanager.dto.piece.PieceCreationDTO;
import br.com.prodpaymanager.models.piece.PieceEntity;
import br.com.prodpaymanager.repositories.piece.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreatePieceService {

    @Autowired
    private PieceRepository pieceRepository;

    public PieceEntity saveProduct(PieceCreationDTO pieceCreationDTO) {

        PieceEntity pieceEntity = pieceCreationDTO.toPieceEntity();

        Optional<PieceEntity> existingPieceOpt = this.pieceRepository.findBycEAN(pieceEntity.getCEAN());

        if (existingPieceOpt.isPresent()) {
            PieceEntity existingPiece = existingPieceOpt.get();

            toAddAmount(existingPiece, pieceEntity.getQCom());

            if (pieceEntity.getVUnCom() != null && !pieceEntity.getVUnCom().equals(existingPiece.getVUnCom())) {
                existingPiece.setVUnCom(pieceEntity.getVUnCom());
            }

            if (pieceEntity.getVProd() != null && !pieceEntity.getVProd().equals(existingPiece.getVProd())) {
                existingPiece.setVProd(pieceEntity.getVProd());
            }

            return this.pieceRepository.save(existingPiece);
        }

        return this.pieceRepository.save(pieceEntity);
    }

    public void toAddAmount(PieceEntity piece, int additionalAmount){
        int currentAmount = piece.getQCom();
        piece.setQCom(currentAmount + additionalAmount);
    }
}
