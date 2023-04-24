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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("roles", Role.values());
        return "users/new";
    }
    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "users/new";

        user.setStatus(Status.ACTIVE);
        userService.create(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("user ") User user){
        userService.update(user);
        return "redirect:/admin/"+id;
    }
        //c |  u | d
}
