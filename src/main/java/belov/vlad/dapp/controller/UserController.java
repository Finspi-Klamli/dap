package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.*;
import belov.vlad.dapp.services.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller

//@PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
public class UserController {
    private final UserServiceImpl userService;
    private final EquipmentService equipmentService;
    private final VersionTechnologicalCardService versionTechnologicalCardService;
    private final FavoriteMapsService favoriteMapsService;

    public UserController(UserServiceImpl userService, EquipmentService equipmentService,
                          VersionTechnologicalCardService versionTechnologicalCardService, FavoriteMapsService favoriteMapsService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.versionTechnologicalCardService = versionTechnologicalCardService;
        this.favoriteMapsService = favoriteMapsService;
    }

    @GetMapping()
    public String homePageNotSignedIn() {
        return "pages/home/homeNotSignedIn";
    }

//    @GetMapping("/profile")
//    public String getProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
//        if (currentUser == null) {
//            System.out.println("NULLLLLLLLLLLLLLLLLLLLLL");
//        }
//        System.out.println(SecurityUtils.isAdmin());
//        User user = userService.findByEmail(currentUser.getUsername());
//        model.addAttribute("user", user);
//        return "user/profile";
//    }
//
//    @GetMapping("/profile/edit")
//    public String edit(@AuthenticationPrincipal UserDetails currentUser, Model model) {
//        model.addAttribute("user", userService.findByEmail(currentUser.getUsername()));
//        return "user/editinfo";
//    }
//
//    @PatchMapping("/profile/edit")
//    public String update(@AuthenticationPrincipal UserDetails currentUser, @ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
//        User oldUser = userService.findByEmail(currentUser.getUsername());
//        if (oldUser != null && newUser.getId() != oldUser.getId()) {
//            FieldError error = new FieldError("user", "email", "Почта уже существует");
//            bindingResult.addError(error);
//        }
//        if (bindingResult.hasErrors())
//            return "user/editinfo";
//        userDataChangeService.saveUserDataChange(oldUser, newUser);
//        userService.update(newUser);
//        return "redirect:/profile";
//    }
//
//    @GetMapping("/profile/change-password")
//    public String getEditpasPage(Model model) {
//        model.addAttribute("user", new User());
//        return "user/editpas";
//    }
//
//    @PatchMapping("/profile/change-password")
//    public String processChangePasswordForm(@AuthenticationPrincipal UserDetails currentUser,
//                                            @RequestParam("currentPassword") String currentPassword,
//                                            @RequestParam("newPassword") String newPassword,
//                                            @RequestParam("confirmPassword") String confirmPassword,
//                                            RedirectAttributes redirectAttributes) {
//
//        User user = userService.findByEmail(currentUser.getUsername());
//
//        if (!userService.checkPassword(user, currentPassword)) {
//            redirectAttributes.addFlashAttribute("error", "Invalid current password");
//            return "redirect:/change-password";
//        }
//        if (!newPassword.equals(confirmPassword)) {
//            redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match");
//            return "redirect:/change-password";
//        }
//        userService.changePassword(user, newPassword);
//        redirectAttributes.addFlashAttribute("success", "Password changed successfully");
//        return "redirect:/profile";
//    }
    @GetMapping("/technological-maps")
    public String getThechnicalCardsPage(Model model)
    {
        List<Equipment> equipments = equipmentService.findAll();
        model.addAttribute("equipments", equipments);
        return "technological-maps";
    }
    @PostMapping ("/technological-maps/add")
    public String addToFavorite(@AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request, @RequestParam("card_id") Long card_id, @RequestParam("card_version") String card_version) {
        String referer = request.getHeader("Referer");
        User user = userService.findByEmail(currentUser.getUsername());
        VersionTechnologicalCard versionTechnologicalCard = versionTechnologicalCardService
                .findByVersionAndTechnologicalCards(card_id, card_version);
        favoriteMapsService.create(user, versionTechnologicalCard);
        return "redirect:" + referer;
    }
    @GetMapping("/technological-maps/documentation")
    public String getTechnologicalMapsDocumentation(Model model){
        List<Equipment> equipments = equipmentService.findAll();
        model.addAttribute("equipments", equipments);
        return "documentation";
    }
    @GetMapping("/technological-maps/favorites")
    public String getFavoriteMapsPage(@AuthenticationPrincipal UserDetails currentUser, Model model){
        List<FavoriteMap> favoriteMaps = favoriteMapsService.findByUserEmail(currentUser.getUsername());
        List<VersionTechnologicalCard> versionTechnologicalCards = new ArrayList<>();
        for (FavoriteMap fm : favoriteMaps){
            versionTechnologicalCards.add(fm.getVersionTechnologicalCard());
        }
        model.addAttribute("maps", versionTechnologicalCards);
        return "favorite-maps";
    }
    @GetMapping("/technological-maps/submit-card-for-approval")
    public String getSubmitCardForApprovalPage(){

        return "submit-card-for-approval";
    }
}
