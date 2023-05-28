package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.*;
import belov.vlad.dapp.services.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller

//@PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
public class UserController {
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final VersionTechnologicalCardService versionTechnologicalCardService;
    private final FavoriteMapsService favoriteMapsService;
    private final FileDataService fileDataService;
    private final TechnologicalCardService technologicalCardService;
    private final ApplicationOfTechnologicalMapService applicationOfTechnologicalMapService;

    public UserController(UserServiceImpl userService, EquipmentService equipmentService,
                          VersionTechnologicalCardService versionTechnologicalCardService, FavoriteMapsService favoriteMapsService, FileDataService fileDataService, TechnologicalCardService technologicalCardService, ApplicationOfTechnologicalMapService applicationOfTechnologicalMapService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.versionTechnologicalCardService = versionTechnologicalCardService;
        this.favoriteMapsService = favoriteMapsService;
        this.fileDataService = fileDataService;
        this.technologicalCardService = technologicalCardService;
        this.applicationOfTechnologicalMapService = applicationOfTechnologicalMapService;
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
    public String getSubmitCardForApprovalPage(Model model){
        ApplicationOfTechnologicalMap applicationOfTechnologicalMap = new ApplicationOfTechnologicalMap();
        System.out.println(technologicalCardService.findAll());
        System.out.println("SDASDASDASDSADASDASDASD");
        model.addAttribute("technologicalCards", technologicalCardService.findAll());
        model.addAttribute("applicationOfTechnologicalMap",applicationOfTechnologicalMap);
        return "submit-card-for-approval";
    }

    @GetMapping("/technological-maps/submit-card-for-approval/applications")
    public String getApplicationsPage(@AuthenticationPrincipal UserDetails currentuser, Model model){
        model.addAttribute("applicationOfTechnologicalMaps", applicationOfTechnologicalMapService
                .findByUserId(userService.findByEmail(currentuser.getUsername()).getId()));
        return "applications";
    }

    @PostMapping("/technological-maps/submit-card-for-approval")
    public String createApplication( @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails currentUser, Model model,
                                     @ModelAttribute("applicationOfTechnologicalMap")@Valid ApplicationOfTechnologicalMap applicationOfTechnologicalMap, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("technologicalCards", technologicalCardService.findAll());
            System.out.println("dsadasd");
            return "submit-card-for-approval";
//                return "admin/users/new";
        }
        Long pdfId = null;
        try {
            FileData pdfFile = new FileData();
            pdfFile.setName(file.getOriginalFilename());
            pdfFile.setData(file.getBytes());
            pdfId = fileDataService.save(pdfFile);
            redirectAttributes.addAttribute("successMessage", "Файл успешно загружен");
        } catch (IOException e) {
            redirectAttributes.addAttribute("errorMessage", "Ошибка загрузки файла");
        }

        LocalDate localDate = LocalDate.now();
        User user = userService.findByEmail(currentUser.getUsername());
        applicationOfTechnologicalMap.setUser(user);
        VersionTechnologicalCard versionTechnologicalCard = new VersionTechnologicalCard(applicationOfTechnologicalMap.getVersion(),
                        applicationOfTechnologicalMap.getTechnologicalCard(), localDate, user, StatusTechnologicalCard.AWAITING_CONFIRMATION, fileDataService.getFileById(pdfId).get());
        versionTechnologicalCardService.save(versionTechnologicalCard);
        applicationOfTechnologicalMap.setVersionTechnologicalCard(versionTechnologicalCard);
        applicationOfTechnologicalMap.setStatus(ApplicationStatus.AWAITING_CONFIRMATION);
        applicationOfTechnologicalMap.setDateOfCreation(localDate);
        applicationOfTechnologicalMapService.save(applicationOfTechnologicalMap);
        return "redirect:/technological-maps/documentation";
    }
 }
