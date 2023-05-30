package belov.vlad.dapp.controller.admin;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.services.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/equipments")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @GetMapping
    public String getEquipmentsPage(Model model){
        model.addAttribute("equipments", equipmentService.findAll());
        return "equipment/equipments";
    }
    @GetMapping("/{id}/update")
    public String getEquipmentsUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("equipment",equipmentService.findById(id));
        return "equipment/update";
    }
    @PatchMapping("/{id}/update")
    public String equipmentUpdate(@PathVariable("id") Long id, @ModelAttribute("equipment") @Valid Equipment equipment, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "equipment/update";
        equipmentService.update(id,equipment);
        return "redirect:/admin/equipments";
    }
    @GetMapping("/create")
    public String getEquipmentsCreatePage(@ModelAttribute("equipment") Equipment equipment){
        return "equipment/create";
    }
    @PostMapping("/create")
    public String createEquipments(@ModelAttribute("equipment") @Valid Equipment equipment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "equipment/create";
        equipmentService.save(equipment);
        return "redirect:/admin/equipments";
    }
}

