package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private TechnologicalCard technologicalCards;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status_id")
    private Long statusId;

    @Lob
    @Column(name = "file")
    private byte[] file;

}