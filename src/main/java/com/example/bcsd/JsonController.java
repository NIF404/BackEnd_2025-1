package com.example.bcsd;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonController {

    @GetMapping("/json")
    public Map<String, Object> json() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 26);
        map.put("name", "허준기");
        return map;
    }
}
