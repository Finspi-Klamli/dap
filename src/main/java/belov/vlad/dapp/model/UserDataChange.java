package belov.vlad.dapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_data_changes")
public class UserDataChange {
    public UserDataChange(Long id, User user, String fieldName, String oldValue, String newValue) {
        this.id = id;
        this.user = user;
        this.changeDate = LocalDateTime.now();
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "old_value", nullable = false)
    private String oldValue;

    @Column(name = "new_value", nullable = false)
    private String newValue;

    public UserDataChange() {
        this.changeDate = LocalDateTime.now();
    }
}