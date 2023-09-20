package belov.vlad.dapp.controller.admin;


import belov.vlad.dapp.model.ManufacturingProcess;
import belov.vlad.dapp.services.EquipmentService;
import belov.vlad.dapp.services.ManufacturingProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/manufacturing-processes")
public class ManufacturingProcessController {
    private final ManufacturingProcessService manufacturingProcessService;
    private final EquipmentService equipmentService;

    public ManufacturingProcessController(ManufacturingProcessService manufacturingProcessService, EquipmentService equipmentService) {
        this.manufacturingProcessService = manufacturingProcessService;
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public String getManufacturingProcessesPage(Model model){
        model.addAttribute("manufacturingProcesses", manufacturingProcessService.findAll());
        return "admin/manufacturing-process/manufacturing-processes";
    }

    @GetMapping("/{id}/update")
    public String getManufacturingProcessUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("equipments",equipmentService.findAll());
        model.addAttribute("manufacturingProcess",manufacturingProcessService.findById(id));
        return "admin/manufacturing-process/update";
    }
    @PatchMapping("/{id}/update")
    public String manufacturingProcessUpdate(@PathVariable("id") Long id, @ModelAttribute("manufacturingProcess") @Valid ManufacturingProcess manufacturingProcess, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "manufacturing-process/update";
        manufacturingProcessService.update(id,manufacturingProcess);
        return "redirect:/admin/manufacturing-processes";
    }
    @GetMapping("/create")
    public String getManufacturingProcessCreatePage(@ModelAttribute("manufacturingProcess") ManufacturingProcess manufacturingProcess, Model model){
        model.addAttribute("equipments",equipmentService.findAll());
        return "admin/manufacturing-process/create";
    }
    @PostMapping("/create")
    public String createManufacturingProcess(Model model,@ModelAttribute("manufacturingProcess") @Valid ManufacturingProcess manufacturingProcess, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            model.addAttribute("equipments",equipmentService.findAll());
            return "admin/manufacturing-process/create";
        }
        manufacturingProcessService.save(manufacturingProcess);
        return "redirect:/admin/manufacturing-processes";
    }
}
