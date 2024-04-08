package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdAccountResponse;
import com.example.sgo_crm.model.FbAdPageResponse;
import com.example.sgo_crm.service.FaceBookService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FacebookServiceImpl implements FaceBookService {

    private final RestTemplate restTemplate;

    public FacebookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ConversationResponse.Data> getFbAdPageConversations(String pageAccessToken, String pageId) {
        String url = "https://graph.facebook.com/v19.0/" + pageId + "/conversations?access_token=" + pageAccessToken;
        ConversationResponse response = restTemplate.getForObject(url, ConversationResponse.class);

        List<ConversationResponse.Data> data = null;

        if (response != null && response.getData() != null) {
            data = response.getData();
        }
        return data;
    }

    @Override
    public List<FbAdPageResponse.Data> getFbAdPagesOfUser(String userAccessToken) {
        String pagesUrl = "https://graph.facebook.com/v19.0/me/accounts?fields=id,access_token&access_token=" + userAccessToken;
        FbAdPageResponse fbAdPageResponse = restTemplate.getForObject(pagesUrl, FbAdPageResponse.class);

        List<FbAdPageResponse.Data> data = null;

        if (fbAdPageResponse != null && fbAdPageResponse.getData() != null) {
            data = fbAdPageResponse.getData();
        }
        return data;
    }

    @Override
    public List<FbAdAccountResponse.Data> getFbAdAccountsOfUser(String userAccessToken) {
        String pagesUrl = "https://graph.facebook.com/v19.0/me?fields=adaccounts&access_token=" + userAccessToken;
        FbAdAccountResponse fbAdAccountResponse = restTemplate.getForObject(pagesUrl, FbAdAccountResponse.class);

        List<FbAdAccountResponse.Data> data = null;

        if(fbAdAccountResponse != null && fbAdAccountResponse.getData() != null) {
            data = fbAdAccountResponse.getData();
        }

        return data;
    }
}
