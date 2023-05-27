package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "version_technological_cards")
public class VersionTechnologicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private String version;

    @ManyToOne
    @JoinColumn(name = "technological_cards_id", nullable = false)
    private TechnologicalCard technologicalCard;

    @OneToMany(mappedBy = "versionTechnologicalCard")
    List<FavoriteMap> favoriteMaps;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusTechnologicalCard statusTechnologicalCard;

    @OneToOne
    @JoinColumn(name = "file_data_id")
    private FileData fileData;

    public VersionTechnologicalCard(String version, TechnologicalCard technologicalCard, LocalDate dateOfCreation, User user, StatusTechnologicalCard statusTechnologicalCard, FileData fileData) {
        this.version = version;
        this.technologicalCard = technologicalCard;
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.fileData = fileData;
        this.statusTechnologicalCard = statusTechnologicalCard;
    }

    public VersionTechnologicalCard() {

    }

    @Override
    public String toString() {
        return "VersionTechnologicalCard{" +
                "version='" + version +'}';
    }
}