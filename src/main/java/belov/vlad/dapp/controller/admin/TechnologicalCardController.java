package belov.vlad.dapp.controller.admin;


import belov.vlad.dapp.model.Product;
import belov.vlad.dapp.model.TechnologicalCard;
import belov.vlad.dapp.services.ProductsService;
import belov.vlad.dapp.services.TechnologicalCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/technological-cards")
public class TechnologicalCardController {
    private final TechnologicalCardService technologicalCardService;
    private final ProductsService productsService;

    public TechnologicalCardController(TechnologicalCardService technologicalCardService, ProductsService productsService) {
        this.technologicalCardService = technologicalCardService;
        this.productsService = productsService;
    }

    @GetMapping
    public String getTechnologicalCardPage(Model model){
        model.addAttribute("technologicalCards", technologicalCardService.findAll());
        return "technological-card/technological-cards";
    }
    @GetMapping("/create")
    public String getTechnologicalCardCreatePage(@ModelAttribute("technologicalCard") TechnologicalCard technologicalCard, Model model){
        model.addAttribute("products", productsService.findProductWithoutCard());
        return "technological-card/create";
    }
    @PostMapping("/create")
    public String createTechnologicalCard(Model model, @ModelAttribute("technologicalCard") @Valid TechnologicalCard technologicalCard, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("error");
            model.addAttribute("products", productsService.findProductWithoutCard());
            return "technological-card/create";
        }
        technologicalCardService.save(technologicalCard);
        return "redirect:/admin/technological-cards";
    }
    @GetMapping("/{id}/update")
    public String getEquipmentsUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("technologicalCard", technologicalCardService.findById(id));
        model.addAttribute("products", productsService.findProductWithoutCard());
        return "technological-card/update";
    }
    @PatchMapping("/{id}/update")
    public String equipmentUpdate(Model model, @PathVariable("id") Long id, @ModelAttribute("technologicalCard") @Valid TechnologicalCard technologicalCard, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("err");
            model.addAttribute("products", productsService.findProductWithoutCard());
            return "technological-card/update";
        }
        System.out.println("upda");
        technologicalCardService.update(id,technologicalCard);
        return "redirect:/admin/technological-cards";
    }

}
