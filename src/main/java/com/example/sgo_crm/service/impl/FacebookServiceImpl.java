package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdAccountDetailResponse;
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
    public FbAdAccountDetailResponse.Data getFacebookAdAccount(String userAccessToken, String adAccountId) {
        String pagesUrl = "https://graph.facebook.com/v19.0/ " + adAccountId + "/insights?fields=spend&access_token=" + userAccessToken;
        FbAdAccountDetailResponse fbAdAccountDetailResponse = restTemplate.getForObject(pagesUrl, FbAdAccountDetailResponse.class);

        FbAdAccountDetailResponse.Data data = null;


        if(fbAdAccountDetailResponse != null && fbAdAccountDetailResponse.getData() != null) {
            data = fbAdAccountDetailResponse.getData();
        }

        return data;
    }

//    @Override
//    public List<FbAdAccountDetailResponse.Data> getFbAdAccountsInsights(String adAccountId, String userAccessToken) {
//        String pagesUrl = "https://graph.facebook.com/v19.0/" + adAccountId + "/insights?fields=spend,date_start,date_stop&access_token=" + userAccessToken;
//        FbAdAccountDetailResponse fbAdAccountDetailResponse = restTemplate.getForObject(pagesUrl, FbAdAccountDetailResponse.class);
//
//        List<FbAdAccountDetailResponse.Data> data = null;
//
//        if(fbAdAccountDetailResponse != null && fbAdAccountDetailResponse.getData() != null) {
//            data = fbAdAccountDetailResponse.getData();
//        }
//
//        return data;
//    }
}
