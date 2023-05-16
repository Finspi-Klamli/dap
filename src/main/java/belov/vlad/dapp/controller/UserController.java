package belov.vlad.dapp.controller;

import belov.vlad.dapp.config.SecurityUtils;
import belov.vlad.dapp.model.User;
import belov.vlad.dapp.services.UserDataChangeServiceImpl;
import belov.vlad.dapp.services.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
public class UserController {
    private final UserServiceImpl userService;
    private final UserDataChangeServiceImpl userDataChangeService;

    public UserController(UserServiceImpl userService, UserDataChangeServiceImpl userDataChangeService) {
        this.userService = userService;
        this.userDataChangeService = userDataChangeService;
    }

    @GetMapping()
    public String homePageNotSignedIn() {
        return "pages/home/homeNotSignedIn";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        if (currentUser == null) {
            System.out.println("NULLLLLLLLLLLLLLLLLLLLLL");
        }
        System.out.println(SecurityUtils.isAdmin());
        User user = userService.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String edit(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("user", userService.findByEmail(currentUser.getUsername()));
        return "user/editinfo";
    }

    @PatchMapping("/profile/edit")
    public String update(@AuthenticationPrincipal UserDetails currentUser, @ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
        User oldUser = userService.findByEmail(currentUser.getUsername());
        if (oldUser != null && newUser.getId() != oldUser.getId()) {
            FieldError error = new FieldError("user", "email", "Почта уже существует");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors())
            return "user/editinfo";
        userDataChangeService.saveUserDataChange(oldUser, newUser);
        userService.update(newUser);
        return "redirect:/profile";
    }

    @GetMapping("/profile/change-password")
    public String getEditpasPage(Model model) {
        model.addAttribute("user", new User());
        return "user/editpas";
    }

    @PatchMapping("/profile/change-password")
    public String processChangePasswordForm(@AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam("currentPassword") String currentPassword,
                                            @RequestParam("newPassword") String newPassword,
                                            @RequestParam("confirmPassword") String confirmPassword,
                                            RedirectAttributes redirectAttributes) {

        User user = userService.findByEmail(currentUser.getUsername());

        if (!userService.checkPassword(user, currentPassword)) {
            redirectAttributes.addFlashAttribute("error", "Invalid current password");
            return "redirect:/change-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match");
            return "redirect:/change-password";
        }
        userService.changePassword(user, newPassword);
        redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        return "redirect:/profile";
    }
    @GetMapping("technological-maps")
    public String getThechnicalMapsPage(){
        return "technological-maps";
    }
}
