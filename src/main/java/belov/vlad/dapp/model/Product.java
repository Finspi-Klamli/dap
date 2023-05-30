package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "поле 'имя' не может быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "поле 'описание' не может быть пустым")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "manufacturing_process_id", nullable = false)
    private ManufacturingProcess manufacturingProcess;

    @OneToOne(mappedBy = "product")
    private TechnologicalCard technologicalCard;

    public Product() {
    }

    @Override
    public String toString() {
        return name;
    }
}
