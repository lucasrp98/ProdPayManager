package br.com.prodpaymanager.services.product;

import br.com.prodpaymanager.dto.product.ProductCreationDTO;
import br.com.prodpaymanager.interfaces.product.ICreateProductService;
import br.com.prodpaymanager.models.piece.PieceEntity;
import br.com.prodpaymanager.models.product.ProductEntity;
import br.com.prodpaymanager.repositories.piece.PieceRepository;
import br.com.prodpaymanager.repositories.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PieceRepository pieceRepository;

    public ProductEntity saveProduct(ProductCreationDTO productCreationDTO) {

        List<Integer> pieceIds = new ArrayList<>(productCreationDTO.getPiecesWithQuantity().keySet());

        List<PieceEntity> pieceEntities = toCheckPecaExists(pieceIds);

        for (PieceEntity piece : pieceEntities) {
            int quantidadeUtilizada = productCreationDTO.getPiecesWithQuantity().get(piece.getId());
            piece.setQuantityUsed(quantidadeUtilizada);
        }

        double sumPieces = toCheckCustProduct(pieceEntities);

        ProductEntity productEntity = productCreationDTO.toProductEntity(pieceEntities, sumPieces);

        updatePieceStock(pieceEntities);

        return this.productRepository.save(productEntity);
    }

    public List<PieceEntity> toCheckPecaExists(List<Integer> list_pieces) {
        List<PieceEntity> foundPieces = this.pieceRepository.findAllById(list_pieces);

        if (foundPieces.size() != list_pieces.size()) {
            throw new EntityNotFoundException("Algumas peças não foram encontradas no banco de dados.");
        }

        return foundPieces;
    }

    public double toCheckCustProduct(List<PieceEntity> list_pieces) {
        return list_pieces.stream()
                .mapToDouble(piece -> piece.getVUnCom() * piece.getQCom())
                .sum();
    }

    @Transactional
    public void updatePieceStock(List<PieceEntity> pieceEntities) {
        for (PieceEntity piece : pieceEntities) {
            piece.setQCom(piece.getQCom() - piece.getQuantityUsed());
        }
        pieceRepository.saveAll(pieceEntities);
    }


}
