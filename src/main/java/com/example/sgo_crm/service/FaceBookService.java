package com.example.sgo_crm.service;

import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdAccountDetailResponse;
import com.example.sgo_crm.model.FbAdAccountResponse;
import com.example.sgo_crm.model.FbAdPageResponse;

import java.util.List;

public interface FaceBookService {

    List<ConversationResponse.Data> getFbAdPageConversations(String pageAccessToken, String pageId);

    List<FbAdPageResponse.Data> getFbAdPagesOfUser(String userAccessToken);

    FbAdAccountDetailResponse.Data getFacebookAdAccount(String userAccessToken, String adAccountId);

    //List<FbAdAccountDetailResponse.Data> getFbAdAccountsInsights(String adAccountId, String userAccessToken);
}
