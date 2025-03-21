package br.com.prodpaymanager.models.sale;

import br.com.prodpaymanager.models.seller.SellerEntity;
import br.com.prodpaymanager.models.client.ClientEntity;
import br.com.prodpaymanager.models.product.ProductEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "sale")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
    @SequenceGenerator(name = "sale_seq", sequenceName = "sale_seq", allocationSize = 1)
    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data_venda;

    private double valor_venda;

    private int quantidade;

    private double valor_frete;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = true)
    private SellerEntity sellerEntity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_has_ssale", joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntityList;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
