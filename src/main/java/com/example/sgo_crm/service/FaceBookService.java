package com.example.sgo_crm.service;

import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdAccountResponse;
import com.example.sgo_crm.model.FbAdPageResponse;

import java.util.List;

public interface FaceBookService {

    List<ConversationResponse.Data> getFbAdPageConversations(String pageAccessToken, String pageId);

    List<FbAdPageResponse.Data> getFbAdPagesOfUser(String userAccessToken);

    List<FbAdAccountResponse.Data> getFbAdAccountsOfUser(String userAccessToken);
}
