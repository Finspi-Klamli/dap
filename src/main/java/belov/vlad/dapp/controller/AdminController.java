package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.User;
import belov.vlad.dapp.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('admin')")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/users")
    public String getUsersPage(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "users";
    }
    @GetMapping("/users/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model){
        Optional<User> u = userRepository.findById(Integer.toUnsignedLong(id));
        model.addAttribute("user", u);
        return "userInfo";
    }

}
