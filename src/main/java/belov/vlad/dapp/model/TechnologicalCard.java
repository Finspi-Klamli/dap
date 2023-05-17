package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technological_cards")
@Data
public class TechnologicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "last_version")
    private String lastVersion;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany
    @JoinColumn(name = "technologicalCards", nullable = false)
    private List<VersionTechnologicalCard> versionTechnologicalCard;

}
