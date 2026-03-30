package com.project.purchasesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.purchasesystem.entity.Material;
import com.project.purchasesystem.entity.Mrp;
import com.project.purchasesystem.entity.User;
import com.project.purchasesystem.repository.MaterialRepository;
import com.project.purchasesystem.service.MrpService;
import com.project.purchasesystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MrpService mrpService;

    // LOGIN PAGE
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // LOGIN LOGIC
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session) {

        User user = userService.loginUser(email, password);

        if (user != null) {

            // 🔥 STORE ROLE + USER
            session.setAttribute("role", user.getDepartment());
            session.setAttribute("user", user);   // ⭐ IMPORTANT

            String dept = user.getDepartment();

            if (dept.equalsIgnoreCase("PRODUCTION")) {
                return "redirect:/production";
            } else if (dept.equalsIgnoreCase("WAREHOUSE")) {
                return "redirect:/warehouse";
            } else if (dept.equalsIgnoreCase("PURCHASE")) {
                return "redirect:/purchase";
            } else if (dept.equalsIgnoreCase("FINANCE")) {
                return "redirect:/finance";
            }
        }

        return "redirect:/";
    }

    // DASHBOARD (optional)
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // PRODUCTION PAGE
    @GetMapping("/production")
    public String productionPage(Model model) {
        model.addAttribute("materials", materialRepository.findAll());
        return "production";
    }

    // OTHER ROLE PAGES
    @GetMapping("/warehouse")
    public String warehousePage() {
        return "warehouse";
    }

    @GetMapping("/purchase")
    public String purchasePage() {
        return "purchase";
    }

    @GetMapping("/finance")
    public String financePage() {
        return "finance";
    }

    // VIEW MRP LIST
    @GetMapping("/mrpList")
    public String viewMrp(Model model, HttpSession session) {

        model.addAttribute("mrps", mrpService.getAllMrp());

        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);

        return "mrp-list";
    }

    // 🔥 CREATE MRP (PRODUCTION)
    @PostMapping("/mrp/save")
    public String saveMrp(@RequestParam Long materialId,
                         @RequestParam int requiredQuantity,
                         @RequestParam Long orderId,
                         HttpSession session) {

        // 🔥 GET LOGGED-IN USER
        User user = (User) session.getAttribute("user");

        Material material = materialRepository.findById(materialId).orElse(null);

        Mrp mrp = new Mrp();
        mrp.setMaterial(material);
        mrp.setRequiredQuantity(requiredQuantity);
        mrp.setOrderId(orderId);

        // 🔥 SET CREATED BY
        mrp.setCreatedBy(user);

        mrpService.createMrp(mrp);

        return "redirect:/mrpList";
    }

    // WAREHOUSE UPDATE
    @PostMapping("/updateStock/{id}")
    public String updateStock(@PathVariable Long id,
                             @RequestParam int stock,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");

        mrpService.updateStock(id, stock, user);

        return "redirect:/mrpList";
    }

    // PURCHASE
    @GetMapping("/generateRequest/{id}")
    public String generateRequest(@PathVariable Long id,
                                 HttpSession session) {

        User user = (User) session.getAttribute("user");

        mrpService.generatePurchaseRequest(id, user);

        return "redirect:/mrpList";
    }

    // FINANCE
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id,
                          @RequestParam boolean approved,
                          HttpSession session) {

        User user = (User) session.getAttribute("user");

        mrpService.approveRequest(id, approved, user);

        return "redirect:/mrpList";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "redirect:/";
    }
}