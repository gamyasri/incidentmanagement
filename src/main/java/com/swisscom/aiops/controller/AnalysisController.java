package com.swisscom.aiops.controller;

import com.swisscom.aiops.models.IncRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inc")
public class AnalysisController {

    @PostMapping(path="/analysis", produces = "application/json")
    public String getTeam(@RequestBody IncRequest incRequest) {
        return "Wireless_Scrat";
    }
}
