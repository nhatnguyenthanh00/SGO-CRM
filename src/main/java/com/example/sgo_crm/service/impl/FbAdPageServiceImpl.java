package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.DTO.ListPageDTO;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdPageResponse;
import org.springframework.web.client.RestTemplate;
import com.example.sgo_crm.repository.FbAdPageRepository;
import com.example.sgo_crm.service.FbAdPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class FbAdPageServiceImpl implements FbAdPageService {

    private final FbAdPageRepository fbAdPageRepository;

    private final RestTemplate restTemplate;

    @Value("${facebook.access-token}")
    private String PAGE_ACCESS_TOKEN;

    @Autowired
    public FbAdPageServiceImpl(FbAdPageRepository fbAdPageRepository, RestTemplate restTemplate) {
        this.fbAdPageRepository = fbAdPageRepository;
        this.restTemplate = restTemplate;
    }

    private List<ConversationResponse.Data> getFbAdPageConversations(String pageAccessToken, String pageId) {
        String url = "https://graph.facebook.com/v19.0/" + pageId + "/conversations?access_token=" + pageAccessToken;
        ConversationResponse response = restTemplate.getForObject(url, ConversationResponse.class);

        List<ConversationResponse.Data> data = null;

        if (response != null && response.getData() != null) {
            data = response.getData();
        }
        return data;
    }

    private List<FbAdPageResponse.Data> getFbAdPagesOfUser(String userAccessToken) {
        String pagesUrl = "https://graph.facebook.com/v19.0/me/accounts?fields=id,access_token&access_token=" + userAccessToken;
        FbAdPageResponse fbAdPageResponse = restTemplate.getForObject(pagesUrl, FbAdPageResponse.class);

        List<FbAdPageResponse.Data> data = null;

        if (fbAdPageResponse != null && fbAdPageResponse.getData() != null) {
            data = fbAdPageResponse.getData();
        }
        return data;
    }

    @Override
    public APIResponse getFbAdPages(String userAccessToken, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ListPageDTO> fbAdPages = fbAdPageRepository.getFbAdPagesBy(pageable);

        List<FbAdPageResponse.Data> data = getFbAdPagesOfUser(userAccessToken);
        for(FbAdPageResponse.Data fbAdResponse:data) {
            for(ListPageDTO listPageDTO:fbAdPages.getContent()) {
                if(fbAdResponse.getId().equals(listPageDTO.getFbAdPageId())) {
                    listPageDTO.setNumberOfConversations(
                        getFbAdPageConversations(fbAdResponse.getAccess_token(), listPageDTO.getFbAdPageId()).size()
                    );
                }
            }
        }

        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .data(fbAdPages.getContent())
                .build();

        apiResponse.setMessage(!fbAdPages.getContent().isEmpty() ? "Danh sách tài khoảng quảng cáo" : "Danh sách rỗng");

        return apiResponse;
    }
}
