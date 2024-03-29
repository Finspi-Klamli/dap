package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.*;
import belov.vlad.dapp.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.stream.Collectors;

@Controller
public class WorkWithTechnologicalCardController {
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final VersionTechnologicalCardService versionTechnologicalCardService;
    private final FavoriteMapsService favoriteMapsService;
    private final FileDataService fileDataService;
    private final TechnologicalCardService technologicalCardService;
    private final ApplicationOfTechnologicalMapService applicationOfTechnologicalMapService;
    public WorkWithTechnologicalCardController(UserServiceImpl userService, EquipmentService equipmentService,
                                               VersionTechnologicalCardService versionTechnologicalCardService, FavoriteMapsService favoriteMapsService, FileDataService fileDataService, TechnologicalCardService technologicalCardService, ApplicationOfTechnologicalMapService applicationOfTechnologicalMapService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.versionTechnologicalCardService = versionTechnologicalCardService;
        this.favoriteMapsService = favoriteMapsService;
        this.fileDataService = fileDataService;
        this.technologicalCardService = technologicalCardService;
        this.applicationOfTechnologicalMapService = applicationOfTechnologicalMapService;
    }
    @PreAuthorize("hasAuthority('user')")
    @GetMapping()
    public String getHomePage() {
        return "pages/home/home-page";
    }
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/technological-maps")  // Страница со списком активных карт
    public String getTechnicalCardsPage(Model model) {
        List<Equipment> equipments = equipmentService.findAll();
        model.addAttribute("equipments", equipments);
        return "technological-maps";
    }
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/technological-maps/add") // Добавить в избранное
    public String addToFavorite(@AuthenticationPrincipal UserDetails currentUser,
                                HttpServletRequest request,
                                @RequestParam("card_id") Long card_id,
                                @RequestParam("card_version") String card_version) {
        String referer = request.getHeader("Referer");
        User user = userService.findByEmail(currentUser.getUsername());
        VersionTechnologicalCard versionTechnologicalCard = versionTechnologicalCardService
                .findByVersionAndTechnologicalCards(card_id, card_version);
        favoriteMapsService.create(user, versionTechnologicalCard);
        return "redirect:" + referer;
    }
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/technological-maps/delete") // Удалить из избранного со страницы "Избранное"
    public String deleteToFavorite(@AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request,
                                   @RequestParam("card_id") Long card_id, @RequestParam("card_version") String card_version) {
        String referer = request.getHeader("Referer");
        User user = userService.findByEmail(currentUser.getUsername());
        VersionTechnologicalCard versionTechnologicalCard = versionTechnologicalCardService
                .findByVersionAndTechnologicalCards(card_id, card_version);
        favoriteMapsService.delete(user, versionTechnologicalCard);
        return "redirect:" + referer;
    }
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/technological-maps/documentation")    // Страница со всеми версиями технологических карт
    public String getTechnologicalMapsDocumentationPage(Model model) {
        List<Equipment> equipments = equipmentService.findAll();
        model.addAttribute("equipments", equipments);
        return "documentation";
    }
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/technological-maps/favorites")    // Страница с избранными
    public String getFavoriteMapsPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        List<FavoriteMap> favoriteMaps = favoriteMapsService.findByUserEmail(currentUser.getUsername());
        List<VersionTechnologicalCard> versionTechnologicalCards = new ArrayList<>();
        for (FavoriteMap fm : favoriteMaps) {
            versionTechnologicalCards.add(fm.getVersionTechnologicalCard());
        }
        model.addAttribute("maps", versionTechnologicalCards);
        return "favorite-maps";
    }


    //-------------------------------applicer------
    @PreAuthorize("hasAuthority('applicant')")
    @GetMapping("/technological-maps/submit-card-for-approval") // Страница с подачей заявки
    public String getSubmitCardForApprovalPage(Model model) {
        ApplicationOfTechnologicalMap applicationOfTechnologicalMap = new ApplicationOfTechnologicalMap();
        model.addAttribute("technologicalCards", technologicalCardService.findAll());
        model.addAttribute("applicationOfTechnologicalMap", applicationOfTechnologicalMap);
        return "submit-card-for-approval";
    }
    @PreAuthorize("hasAuthority('applicant')")
    @GetMapping("/technological-maps/submit-card-for-approval/applications") // страница ваши заявки техн.карт
    public String getApplicationsPage(@AuthenticationPrincipal UserDetails currentuser, Model model) {
        model.addAttribute("applicationOfTechnologicalMaps", applicationOfTechnologicalMapService
                .findByUserId(userService.findByEmail(currentuser.getUsername()).getId()));
        return "applications";
    }

    @PreAuthorize("hasAuthority('applicant')")
    @PostMapping("/technological-maps/submit-card-for-approval")    // создание заявки на сервере
    public String createApplication(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                                    @AuthenticationPrincipal UserDetails currentUser, Model model,
                                    @ModelAttribute("applicationOfTechnologicalMap") @Valid
                                        ApplicationOfTechnologicalMap applicationOfTechnologicalMap, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("technologicalCards", technologicalCardService.findAll());
            return "submit-card-for-approval";
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
                applicationOfTechnologicalMap.getTechnologicalCard(), localDate, user, StatusTechnologicalCard.AWAITING_CONFIRMATION,
                fileDataService.getFileById(pdfId).orElse(null));
        versionTechnologicalCardService.save(versionTechnologicalCard);
        applicationOfTechnologicalMap.setVersionTechnologicalCard(versionTechnologicalCard);
        applicationOfTechnologicalMap.setStatus(ApplicationStatus.AWAITING_CONFIRMATION);
        applicationOfTechnologicalMap.setDateOfCreation(localDate);
        applicationOfTechnologicalMapService.save(applicationOfTechnologicalMap);
        return "redirect:/technological-maps/documentation";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/technological-maps/applications/statement") // список всех заявок админ
    public String getApplicationsStatementPage(Model model) {
        model.addAttribute("applicationOfTechnologicalMaps", applicationOfTechnologicalMapService.findAll());
        return "applications-statement";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/technological-maps/applications/statement/actual") //Карты, которые ожидают подтверждения админ
    public String getActualApplicationsStatementPage(Model model) {
        model.addAttribute("applicationOfTechnologicalMaps", applicationOfTechnologicalMapService.findAll().stream().
                filter(am -> am.getStatus().equals(ApplicationStatus.AWAITING_CONFIRMATION)).collect(Collectors.toList()));
        return "actual-applications-statement";
    }
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/technological-maps/applications/statement/actual/statement/{id}") //Заявка на подтверждение админ
    public String getStatementApplicationPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("applicationOfTechnologicalMap", applicationOfTechnologicalMapService.findById(Integer.toUnsignedLong(id)));
        return "actual-application";
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/technological-maps/applications/statement/actual/statement/{id}/create") // Создать заявку админ
    public String createMaps(@PathVariable("id") Long id) {
        // поменять статус заявки
        ApplicationOfTechnologicalMap atp = applicationOfTechnologicalMapService.findById(id);
        atp.setStatus(ApplicationStatus.ACCEPTED);
        // поменять статус версии тех карты и прошлой
        atp.getVersionTechnologicalCard().setStatusTechnologicalCard(StatusTechnologicalCard.ACTIVE);
        String cardName = atp.getVersionTechnologicalCard().getTechnologicalCard().getName();
        VersionTechnologicalCard vtc = versionTechnologicalCardService.findActiveByMapName(cardName);
        vtc.setStatusTechnologicalCard(StatusTechnologicalCard.OBSOLETE);
        versionTechnologicalCardService.save(vtc);
        applicationOfTechnologicalMapService.save(atp);
        // поменять в тех картах ласт версию
        TechnologicalCard tc = technologicalCardService.findByName(cardName);
        tc.setLastVersion(atp.getVersion());
        technologicalCardService.save(tc);
        // 3 в версии тех карт 1 тех карта  10 в заявках
        return "redirect:/technological-maps/applications/statement/actual";
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/technological-maps/applications/statement/actual/statement/{id}/reject") //Отклонить заявку admin panel
    public String rejectApplication(@PathVariable("id") Long id) {
        ApplicationOfTechnologicalMap atm = applicationOfTechnologicalMapService.findById(id);
        atm.setStatus(ApplicationStatus.REJECTED);
        applicationOfTechnologicalMapService.save(atm);
        return "redirect:/technological-maps/applications/statement/actual";
    }
}