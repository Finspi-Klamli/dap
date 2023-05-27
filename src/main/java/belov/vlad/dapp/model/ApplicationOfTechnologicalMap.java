package belov.vlad.dapp.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "applications_of_technological_maps")
public class ApplicationOfTechnologicalMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "map_version_id", nullable = false)
    private VersionTechnologicalCard versionTechnologicalCard;

    @NotEmpty(message = "Поле 'версия' не может быть пустым")
    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDate dateOfCreation;

    @NotEmpty(message = "Поле 'комментрарий' не может быть пустым")
    @Column(name = "comment", nullable = false)
    private String comment;

    @Transient
    public TechnologicalCard technologicalCard;

    public TechnologicalCard getTechnologicalCard() {
        return technologicalCard;
    }

    public void setTechnologicalCard(TechnologicalCard technologicalCard) {
        this.technologicalCard = technologicalCard;
    }

    public ApplicationOfTechnologicalMap() {

    }
}
