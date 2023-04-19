package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.User;
import belov.vlad.dapp.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin')")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
