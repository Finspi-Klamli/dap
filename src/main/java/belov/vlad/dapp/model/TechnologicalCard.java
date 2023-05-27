package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
        return "TechnologicalCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lastVersion='" + lastVersion + '\'' +
                ", product=" + product +
                ", versionTechnologicalCards=" + versionTechnologicalCards +
                '}';
    }
}
