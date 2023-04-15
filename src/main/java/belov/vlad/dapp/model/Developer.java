package belov.vlad.dapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;

}
