package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.Role;
import belov.vlad.dapp.model.Status;
import belov.vlad.dapp.model.User;
import belov.vlad.dapp.repository.UserRepository;
import belov.vlad.dapp.services.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('admin')")
public class AdminUserController {
    private final UserServiceImpl userService;

    public AdminUserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsersPage(Model model){
        model.addAttribute("users",userService.findAll());
        return "users/users";
    }
    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "users/userInfo";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        User user = new User();
        user.setStatus(Status.ACTIVE);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "users/new";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.create(user);
        return "redirect:/admin/users";
    }
        //c |  u | d
}
