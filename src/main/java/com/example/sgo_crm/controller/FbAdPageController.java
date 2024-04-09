package com.example.sgo_crm.controller;

import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.service.impl.FbAdPageServiceImpl;
import org.springframework.http.HttpStatus;
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

    // còn phần tra cứu theo ngày
    @GetMapping("/{pageId}")
    public ResponseEntity<?> getfbAdPageByPageId(@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authorizedClient,
                                                 @PathVariable Long pageId) {

        // lay user access token
        String userAccessToken = authorizedClient.getAccessToken().getTokenValue().toString();
        APIResponse apiResponse = fbAdPageService.getFbAdPage(userAccessToken, pageId);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findfbAdPage(@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authorizedClient,
                                          @RequestParam(value = "id", required = false) String id,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "numberOfConversation", required = false) String numberOfConversation,
                                          @RequestParam(defaultValue = "1", required = false) int page) {

        // lay user access token
        String userAccessToken = authorizedClient.getAccessToken().getTokenValue().toString();
        APIResponse apiResponse = fbAdPageService.findFbAdPages(userAccessToken, id, name, numberOfConversation, page);
        return ResponseEntity.ok().body(apiResponse);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
