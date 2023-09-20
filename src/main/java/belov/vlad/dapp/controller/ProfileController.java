package belov.vlad.dapp.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('applicant')")
@RequestMapping("/profile")
public class ProfileController {
    private final UserDataChangeServiceImpl userDataChangeService;
    private final UserServiceImpl userService;

    public ProfileController(UserDataChangeServiceImpl userDataChangeService, UserServiceImpl userService) {
        this.userDataChangeService = userDataChangeService;
        this.userService = userService;
    }
    @GetMapping()
    public String getProfilePage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        if (currentUser == null) {
        }
        User user = userService.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user/profile";
    }
    @GetMapping("/update")
    public String getUpdateProfilePage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("user", userService.findByEmail(currentUser.getUsername()));
        return "user/editinfo";
    }
    @PatchMapping("/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails currentUser, @ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
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
    @GetMapping("/update-password")
    public String getUpdatePasswordPage(Model model) {
        model.addAttribute("user", new User());
        return "user/editpas";
    }
    @PatchMapping("/update-password")
    public String updatePassword(@AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam("currentPassword") String currentPassword,
                                            @RequestParam("newPassword") String newPassword,
                                            @RequestParam("confirmPassword") String confirmPassword,
                                            RedirectAttributes redirectAttributes) {

        User user = userService.findByEmail(currentUser.getUsername());

        if (!userService.checkPassword(user, currentPassword)) {
            redirectAttributes.addFlashAttribute("error", "Invalid current password");
            return "user/editpas";
        }
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match");
            return "user/editpas";
        }
        userService.changePassword(user, newPassword);
        redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        return "redirect:/profile";
    }
}
