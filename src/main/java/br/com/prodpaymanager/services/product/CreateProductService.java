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
import java.util.Optional;

@Service
public class CreateProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PieceRepository pieceRepository;

    public ProductEntity saveProduct(ProductCreationDTO productCreationDTO) {

        List<Integer> pieceIds = new ArrayList<>(productCreationDTO.getPiecesWithQuantity().keySet());

        List<PieceEntity>  pieceEntities = toCheckPecaExists(pieceIds);

//        if(productCreationDTO.getQuantProduct() > 1) {
//            pieceEntities = toUpdateQuantityPiece(productCreationDTO.getQuantProduct(),pieceEntities);
//        }

        for (PieceEntity piece : pieceEntities) {
            int quantityUsed = productCreationDTO.getPiecesWithQuantity().get(piece.getId());

            quantityUsed = quantityUsed * productCreationDTO.getQuantProduct();

            if (piece.getQCom() < quantityUsed) {
                throw new IllegalArgumentException("Estoque insuficiente para a peça ID: " + piece.getId());
            }
            piece.setQuantityUsed(quantityUsed);
        }

        double sumPieces = toCheckCustProduct(pieceEntities, productCreationDTO);

        ProductEntity productEntity = productCreationDTO.toProductEntity(pieceEntities, sumPieces);

        Optional<ProductEntity> existingProductOpt = this.productRepository.findByNameProduct(productEntity.getNameProduct());

        if (existingProductOpt.isPresent()) {
            ProductEntity existingProduct = existingProductOpt.get();

            toAddAmount(existingProduct, productEntity.getQuantProduct());

            existingProduct.setCustProduct(sumPieces);

            updatePieceStock(pieceEntities);

            return this.productRepository.save(existingProduct);
        }

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

    public double toCheckCustProduct(List<PieceEntity> list_pieces, ProductCreationDTO productCreationDTO) {
        return list_pieces.stream()
                .mapToDouble(piece -> piece.getVUnCom() * productCreationDTO.getPiecesWithQuantity().get(piece.getId()))
                .sum();
    }

    @Transactional
    public void updatePieceStock(List<PieceEntity> pieceEntities) {
        for (PieceEntity piece : pieceEntities) {
            piece.setQCom(piece.getQCom() - piece.getQuantityUsed());
        }
        pieceRepository.saveAll(pieceEntities);
    }

    public List<PieceEntity> toUpdateQuantityPiece(Integer quantity, List<PieceEntity> list_pieces){
        for (PieceEntity piece : list_pieces) {
            piece.setQuantityUsed(piece.getQuantityUsed() * quantity);
        }
        return list_pieces;
    }

    public void toAddAmount(ProductEntity productEntity, int additionalAmount){
        int currentAmount = productEntity.getQuantProduct();
        productEntity.setQuantProduct(currentAmount + additionalAmount);
    }


}
