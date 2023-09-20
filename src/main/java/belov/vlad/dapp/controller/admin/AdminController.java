package belov.vlad.dapp.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/admin")
public class AdminController {//a@1 a user  u@r u - applicant
    @GetMapping
    public String getAdminPage(){
        return "admin/admin";
    }
}
