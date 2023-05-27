package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "manufacturing_process_id", nullable = false)
    private ManufacturingProcess manufacturingProcess;

    @OneToOne(mappedBy = "product")
    private TechnologicalCard technologicalCard;

    @Override
    public String toString() {
        return name;
    }
}
