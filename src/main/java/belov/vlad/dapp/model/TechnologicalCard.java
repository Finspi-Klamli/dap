package belov.vlad.dapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "technological_cards")
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

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "technologicalCard")
    private List<VersionTechnologicalCard> versionTechnologicalCards;
    public List<VersionTechnologicalCard> getVersionTechnologicalCards() {
        if(versionTechnologicalCards == null)
            return null;
        return versionTechnologicalCards;
    }
    @Override
    public String toString() {
        return  name +  "  последняя версия: " + lastVersion;
    }
}
