package com.example.sgo_crm.controller;

import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.service.impl.FbAdPageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pages")
public class PageController {

    private final FbAdPageServiceImpl fbAdPageService;

    public PageController(FbAdPageServiceImpl fbAdPageService) {
        this.fbAdPageService = fbAdPageService;
    }

    @GetMapping()
    public ResponseEntity<?> getfbAdPages(@RequestParam(defaultValue = "1", required = false) int page) {
        APIResponse apiResponse = fbAdPageService.getFbAdPages(page);
        return ResponseEntity.ok().body(apiResponse);
    }
}
