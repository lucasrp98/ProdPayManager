package br.com.prodpaymanager.models.product;

import br.com.prodpaymanager.models.piece.PieceEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
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
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private int id;

    @Column(name = "nameProduct")
    private String nameProduct;

    @Column(name = "valueProduct")
    private double valueProduct;

    @Column(name = "custProduct")
    private double custProduct;

    @Column(name = "quantProduct")
    private int quantProduct;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_has_piece", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "piece_id"))
    private List<PieceEntity> pieces;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_has_sale", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sale_id"))
    private List<SaleEntity> saleEntityList;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
