package com.ninjagoldgame.ninjagoldgame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NinjaGoldController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("gold") == null) {
            session.setAttribute("gold", 0);
        }
        if (session.getAttribute("activities") == null) {
            session.setAttribute("activities", new ArrayList<String>());
        }

        model.addAttribute("gold", session.getAttribute("gold"));
        model.addAttribute("activities", session.getAttribute("activities"));
        return "index";
    }

    @PostMapping("/process")
    public String process(HttpSession session, String action) {
        int gold = (int) session.getAttribute("gold");
        @SuppressWarnings("unchecked")
        List<String> activities = (List<String>) session.getAttribute("activities");
        Random rand = new Random();
        int amount = 0;
        String activity = "";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM d yyyy h:mm a"));

        switch (action) {
            case "farm" -> {
                amount = rand.nextInt(11) + 10;
                activity = "You entered a farm and earned " + amount + " gold. (" + timestamp + ")";
            }
            case "cave" -> {
                amount = rand.nextInt(6) + 5;
                activity = "You entered a cave and earned " + amount + " gold. (" + timestamp + ")";
            }
            case "house" -> {
                amount = rand.nextInt(4) + 2;
                activity = "You entered a house and earned " + amount + " gold. (" + timestamp + ")";
            }
            case "quest" -> {
                amount = rand.nextInt(101) - 50;
                if (amount >= 0) {
                    activity = "You completed a quest and earned " + amount + " gold. (" + timestamp + ")";
                } else {
                    activity = "You failed a quest and lost " + Math.abs(amount) + " gold. (" + timestamp + ")";
                }
            }
            case "spa" -> {
                amount = -(rand.nextInt(16) + 5);
                activity = "You entered a spa and lost " + Math.abs(amount) + " gold. (" + timestamp + ")";
            }
        }

        gold += amount;
        activities.add(activity);
        session.setAttribute("gold", gold);
        session.setAttribute("activities", activities);

        return "redirect:/";
    }
}
