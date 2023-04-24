package belov.vlad.dapp.services;

import belov.vlad.dapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getById(Long id);
    User findByEmail(String email);
    List<User> findAll();
    User create(User user);
    void update(User user);
}
