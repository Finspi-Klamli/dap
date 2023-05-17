package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "file_data")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "data", columnDefinition = "bytea")
    private byte[] data;

    @Column(name = "name")
    private String name;

    public void setDataFromBigInteger(BigInteger value) {
        this.data = value.toByteArray();
    }
}
