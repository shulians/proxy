package com.example.proxy.controller;

import com.example.proxy.dto.response.CurrentConditionResDTO;
import com.example.proxy.exception.TechnicalException;
import com.example.proxy.service.ICurrentConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentcondition")
public class CurrentConditionController {
    ICurrentConditionService service;

    @Autowired
    public CurrentConditionController(ICurrentConditionService service) {
        this.service = service;
    }

    @GetMapping("/{locationKey}")
    public ResponseEntity<CurrentConditionResDTO> getCurrentConditionByLocationKey(@PathVariable("locationKey")String locationKey) throws TechnicalException {
       return ResponseEntity.ok(service.getByLocationKey(locationKey));
    }
}
