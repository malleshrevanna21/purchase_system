package com.project.purchasesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.purchasesystem.entity.Mrp;
import com.project.purchasesystem.entity.User;
import com.project.purchasesystem.service.MrpService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/mrp")
public class MrpController {

    @Autowired
    private MrpService mrpService;

    // CREATE
    @PostMapping("/create")
    public Mrp createMrp(@RequestBody Mrp mrp, HttpSession session) {

        User user = (User) session.getAttribute("user");

        mrp.setCreatedBy(user);

        return mrpService.createMrp(mrp);
    }

    // VIEW
    @GetMapping("/all")
    public List<Mrp> getAllMrp() {
        return mrpService.getAllMrp();
    }

    // UPDATE STOCK
    @PutMapping("/update-stock/{id}")
    public Mrp updateStock(@PathVariable Long id,
                           @RequestParam int stock,
                           HttpSession session) {

        User user = (User) session.getAttribute("user");

        return mrpService.updateStock(id, stock, user);
    }

    // GENERATE
    @PutMapping("/generate-request/{id}")
    public Mrp generateRequest(@PathVariable Long id,
                              HttpSession session) {

        User user = (User) session.getAttribute("user");

        return mrpService.generatePurchaseRequest(id, user);
    }

    // APPROVE
    @PutMapping("/approve/{id}")
    public Mrp approveRequest(@PathVariable Long id,
                             @RequestParam boolean approved,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");

        return mrpService.approveRequest(id, approved, user);
    }
}