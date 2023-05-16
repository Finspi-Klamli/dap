package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Status(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    public Status() {

    }
}