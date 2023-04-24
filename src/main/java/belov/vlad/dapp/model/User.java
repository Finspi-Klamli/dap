package belov.vlad.dapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле 'почта' не может быть пустым")
    @Email(message = "Неверный формат почты")
    @Column(name = "email")
    @Size(max = 255)
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public void changeFields(User newFieldsUser){
        this.email = newFieldsUser.getEmail();
        this.password = newFieldsUser.getPassword();
        this.firstName = newFieldsUser.getFirstName();
        this.lastName = newFieldsUser.getLastName();
    }
}
