package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.FileData;
import belov.vlad.dapp.repository.FileDataRepository;
import belov.vlad.dapp.services.FileDataServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileController {

    private final FileDataServiceImpl fileDataService;

    public FileController(FileDataServiceImpl fileDataService) {
        this.fileDataService = fileDataService;
    }

    @GetMapping("/pdf")
    public String getPdfFiles(Model model) {
        model.addAttribute("pdfFiles", fileDataService.findAll());
        return "pdf";
    }

    @PostMapping("/pdf/upload")
    public String uploadPdfFile(@RequestParam("file") MultipartFile file,
                                RedirectAttributes redirectAttributes) {
        try {
            FileData pdfFile = new FileData();
            pdfFile.setName(file.getOriginalFilename());
            pdfFile.setData(file.getBytes());
            fileDataService.save(pdfFile);
            redirectAttributes.addFlashAttribute("successMessage", "Файл успешно загружен");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка загрузки файла");
        }
        return "redirect:/pdf";
    }
}