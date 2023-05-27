package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "favorite_maps")
public class FavoriteMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "version_map_id")
    private VersionTechnologicalCard versionTechnologicalCard;

    public FavoriteMap(User user, VersionTechnologicalCard versionTechnologicalCard) {
        this.user = user;
        this.versionTechnologicalCard = versionTechnologicalCard;
    }

    public FavoriteMap() {
    }

    @Override
    public String toString() {
        return "FavoriteMap{" +
                "id=" + id +
                ", user=" + user.getEmail() +
                ", versionTechnologicalCard=" + versionTechnologicalCard.getVersion() +
                '}';
    }
}
