package belov.vlad.dapp.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "file_data")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "data", columnDefinition = "bytea")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "fileData")
    private VersionTechnologicalCard versionTechnologicalCard;

}
