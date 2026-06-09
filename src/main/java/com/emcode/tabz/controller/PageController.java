package com.emcode.tabz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/api/tab/{tabId}/claim-page")
    public String claimPage(@PathVariable Long tabId, Model model) {
        model.addAttribute("tabId", tabId);
        return "claim";
    }
}
