package belov.vlad.dapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле 'почта' не может быть пустым")
    @Email(message = "Неверный формат почты")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Поле 'пароль' не может быть пустым")
    @Column(name = "password")
    @Size(max = 255)
    private String password;

    @NotEmpty(message = "Поле 'имя' не может быть пустым")
    @Column(name = "first_name")
    @Size(max = 50)
    private String firstName;

    @NotEmpty(message = "Поле 'фамилия' не может быть пустым")
    @Column(name = "last_name")
    @Size(max = 100)
    private String lastName;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user")
    private List<FavoriteMap> favoriteMaps;

    public void changeFields(User newFieldsUser){
        this.email = newFieldsUser.getEmail();
        this.password = newFieldsUser.getPassword();
        this.firstName = newFieldsUser.getFirstName();
        this.lastName = newFieldsUser.getLastName();
        this.role = newFieldsUser.getRole();
        this.status = newFieldsUser.getStatus();
    }


}
