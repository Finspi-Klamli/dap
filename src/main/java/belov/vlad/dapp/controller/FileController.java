package belov.vlad.dapp.controller;

import belov.vlad.dapp.model.FileData;
import belov.vlad.dapp.model.VersionTechnologicalCard;
import belov.vlad.dapp.services.FileDataServiceImpl;
import belov.vlad.dapp.services.VersionTechnologicalCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class FileController {

    private final FileDataServiceImpl fileDataService;
    private final VersionTechnologicalCardService versionTechnologicalCardService;

    public FileController(FileDataServiceImpl fileDataService,VersionTechnologicalCardService versionTechnologicalCardService) {
        this.fileDataService = fileDataService;
        this.versionTechnologicalCardService = versionTechnologicalCardService;
    }
    @GetMapping("/pdf/{id}/{version}")
    public void openPdf(HttpServletResponse response, @PathVariable("id") Long id,
                            @PathVariable("version") String version) throws IOException {
        VersionTechnologicalCard versionTechnologicalCard = versionTechnologicalCardService
                .findByVersionAndTechnologicalCards(id, version);
        FileData fileData = versionTechnologicalCard.getFileData();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + fileData.getName());
        response.getOutputStream().write(fileData.getData());
        response.getOutputStream().flush();
    }
}