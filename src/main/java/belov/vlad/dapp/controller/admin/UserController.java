package belov.vlad.dapp.controller.admin;

import belov.vlad.dapp.model.Status;
import belov.vlad.dapp.model.User;
import belov.vlad.dapp.services.UserDataChangeServiceImpl;
import belov.vlad.dapp.services.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('admin')")
public class UserController {
    private final UserServiceImpl userService;
    private final UserDataChangeServiceImpl userDataChangeService;
    public UserController(UserServiceImpl userService, UserDataChangeServiceImpl userDataChangeService) {
        this.userService = userService;
        this.userDataChangeService = userDataChangeService;
    }
    @GetMapping()   //ready
    public String getUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "admin/users/users";
    }
    @GetMapping("/{id}")    //ready
    public String getUserInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "admin/users/userinfo";
    }
    @GetMapping("/new") //ready
    public String newUser(@ModelAttribute("user") User user, Model model){
        return "admin/users/new";
    }
    @PostMapping("/new")    //ready
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(userService.findByEmail(user.getEmail()) != null){
            FieldError error = new FieldError("user", "email", "Почта уже существует");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors())
            return "admin/users/new";

        user.setStatus(Status.ACTIVE);
        userService.create(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getById(Integer.toUnsignedLong(id)));
        return "admin/users/edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") @Valid User newUser, BindingResult bindingResult){
        User oldUser = userService.findByEmail(newUser.getEmail());
        if(oldUser != null && newUser.getId()!= oldUser.getId()){
            FieldError error = new FieldError("user", "email", "Почта уже существует");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            return "admin/users/edit";
        }

        userDataChangeService.saveUserDataChange(oldUser,newUser);

        userService.update(newUser);
        return "redirect:/admin/users/"+id;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/user-data-changes")
    public String getUserDataChangesPage(Model model){
        model.addAttribute("data", userDataChangeService.getAllUserDataChanges());
    return "admin/users/users-data-changes";
    }
}
