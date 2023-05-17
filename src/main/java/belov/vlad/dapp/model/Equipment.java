package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Data
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "поле 'название' не может быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "поле 'описание' не может быть пустым")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "equipment")
    private List<ManufacturingProcess> manufacturingProcessList;

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name +  '}';
    }
}

