package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JsonController {

    @GetMapping("/json")
    public String json(@RequestParam(name="age", required=false, defaultValue="23") int age,
                       @RequestParam(name = "name", required = false, defaultValue = "이하원") String name,
                       Model model) {
        model.addAttribute("age", age);
        model.addAttribute("name", name);
        return "json";
    }

}
