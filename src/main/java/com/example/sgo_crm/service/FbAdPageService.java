package com.example.sgo_crm.service;

import com.example.sgo_crm.model.APIResponse;

public interface FbAdPageService {

    APIResponse getFbAdPages(String userAccessToken, int page);

    APIResponse getFbAdPage(String userAccessToken, Long pageId);

    APIResponse findFbAdPages(String userAccessToken, String id, String name, String numberOfConversation, int page);
}
