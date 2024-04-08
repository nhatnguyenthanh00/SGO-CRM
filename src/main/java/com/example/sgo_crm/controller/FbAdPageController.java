package com.example.sgo_crm.controller;

import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.service.impl.FbAdPageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pages")
public class FbAdPageController {

    private final FbAdPageServiceImpl fbAdPageService;

    public FbAdPageController(FbAdPageServiceImpl fbAdPageService) {
        this.fbAdPageService = fbAdPageService;
    }

    @GetMapping()
    public ResponseEntity<?> getfbAdPages(@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authorizedClient,
                                          @RequestParam(defaultValue = "1", required = false) int page) {

        // lay user access token
        String userAccessToken = authorizedClient.getAccessToken().getTokenValue().toString();

        APIResponse apiResponse = fbAdPageService.getFbAdPages(userAccessToken, page);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{pageId}")
    public ResponseEntity<?> getfbAdPageByPageId(@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authorizedClient,
                                                 @PathVariable Long pageId) {

        // lay user access token
        String userAccessToken = authorizedClient.getAccessToken().getTokenValue().toString();
        APIResponse apiResponse = fbAdPageService.getFbAdPage(userAccessToken, pageId);
        return ResponseEntity.ok().body(apiResponse);
    }
}
