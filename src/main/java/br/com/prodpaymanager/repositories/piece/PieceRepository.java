package br.com.prodpaymanager.repositories.piece;

import br.com.prodpaymanager.models.piece.PieceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PieceRepository extends JpaRepository<PieceEntity, Integer> {
    Optional<PieceEntity> findBycEAN(String cEAN);
}
