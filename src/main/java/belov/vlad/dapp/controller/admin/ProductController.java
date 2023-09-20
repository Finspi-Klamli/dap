package belov.vlad.dapp.controller.admin;

import belov.vlad.dapp.model.Product;
import belov.vlad.dapp.services.ManufacturingProcessService;
import belov.vlad.dapp.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductsService productsService;
    private final ManufacturingProcessService manufacturingProcessService;

    public ProductController(ProductsService productsService, ManufacturingProcessService manufacturingProcessService) {
        this.productsService = productsService;
        this.manufacturingProcessService = manufacturingProcessService;
    }

    @GetMapping
    public String getProductPage(Model model){
        model.addAttribute("products", productsService.findAll());
        return "admin/product/products";
    }
    @GetMapping("/{id}/update")
    public String getProductUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("manufacturingProcesses",manufacturingProcessService.findAll());
        model.addAttribute("product", productsService.findById(id));
        return "admin/product/update";
    }
    @PatchMapping("/{id}/update")
    public String productUpdate(Model model, @PathVariable("id") Long id, @ModelAttribute("equipment") @Valid Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturingProcesses",manufacturingProcessService.findAll());
            return "admin/product/update";
        }
        productsService.update(id,product);
        return "redirect:/admin/products";
    }
    @GetMapping("/create")
    public String getProductCreatePage(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("manufacturingProcesses", manufacturingProcessService.findAll());
        return "admin/product/create";
    }
    @PostMapping("/create")
    public String createProduct(Model model, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("error");
            model.addAttribute("manufacturingProcesses", manufacturingProcessService.findAll());
            return "admin/product/create";
        }
        productsService.save(product);
        return "redirect:/admin/products";
    }
}
