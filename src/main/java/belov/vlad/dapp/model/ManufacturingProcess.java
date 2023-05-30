package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "manufacturing_process")
public class ManufacturingProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @NotEmpty(message = "поле 'название' не может быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "поле 'описание' не может быть пустым")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "manufacturingProcess")
    private List<Product> products;

    @Override
    public String toString() {
        return equipment.getName() +": "+name;
    }
}