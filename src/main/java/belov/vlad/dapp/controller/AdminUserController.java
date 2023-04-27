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
import org.springframework.validation.FieldError;
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

    @GetMapping()   //ready
    public String getUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "users/users";
    }
    @GetMapping("/{id}")    //ready
    public String getUserInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "users/userInfo";
    }
    @GetMapping("/new") //ready
    public String newUser(@ModelAttribute("user") User user, Model model){
        return "users/new";
    }
    @PostMapping("/new")    //ready
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(userService.findByEmail(user.getEmail()) != null){
            FieldError error = new FieldError("user", "email", "Почта уже существует");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors())
            return "users/new";

        user.setStatus(Status.ACTIVE);
        userService.create(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        System.out.println("GETMAPPING");
        System.out.println(userService.getById(Integer.toUnsignedLong(id)));
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "users/edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        User u = userService.findByEmail(user.getEmail());
        if(u != null && user.getId()!= u.getId()){
            FieldError error = new FieldError("user", "email", "Почта уже существует");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors())
            return "users/edit";

        System.out.println("patchMAPPING");
        System.out.println(user);
        userService.update(user);
        return "redirect:/admin/users/"+id;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
