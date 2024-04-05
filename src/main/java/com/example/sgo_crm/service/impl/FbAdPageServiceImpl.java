package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.DTO.ListPageDTO;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.ConversationResponse;
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

    private List<ConversationResponse.Data> getFbAdPageConversations(String pageId) {
        String url = "https://graph.facebook.com/v19.0/" + pageId + "/conversations?access_token=" + PAGE_ACCESS_TOKEN;
        ConversationResponse response = restTemplate.getForObject(url, ConversationResponse.class);

        List<ConversationResponse.Data> data = null;

        if (response != null && response.getData() != null) {
            data = response.getData();
        }
        return data;
    }

    @Override
    public APIResponse getFbAdPages(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ListPageDTO> fbAdPages = fbAdPageRepository.getFbAdPagesBy(pageable);

        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .data(fbAdPages.getContent())
                .build();

        for(ListPageDTO listPageDTO:fbAdPages.getContent()) {
            listPageDTO.setNumberOfConversations(
                    getFbAdPageConversations(listPageDTO.getFbAdPageId()).size()
            );
        }

        apiResponse.setMessage(!fbAdPages.getContent().isEmpty() ? "Danh sách tài khoảng quảng cáo" : "Danh sách rỗng");

        return apiResponse;
    }
}
