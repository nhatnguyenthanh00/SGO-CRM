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

    private final FacebookServiceImpl facebookService;

    @Autowired
    public FbAdPageServiceImpl(FbAdPageRepository fbAdPageRepository, FacebookServiceImpl facebookService) {
        this.fbAdPageRepository = fbAdPageRepository;
        this.facebookService = facebookService;
    }


    @Override
    public APIResponse getFbAdPages(String userAccessToken, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ListPageDTO> fbAdPages = fbAdPageRepository.getFbAdPagesBy(pageable);

        List<FbAdPageResponse.Data> data = facebookService.getFbAdPagesOfUser(userAccessToken);
        for(FbAdPageResponse.Data fbAdResponse:data) {
            for(ListPageDTO listPageDTO:fbAdPages.getContent()) {
                if(fbAdResponse.getId().equals(listPageDTO.getFbAdPageId())) {
                    listPageDTO.setNumberOfConversations(
                        facebookService.getFbAdPageConversations(fbAdResponse.getAccess_token(), listPageDTO.getFbAdPageId()).size()
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

    @Override
    public APIResponse getFbAdPage(String userAccessToken, Long pageId) {
        return APIResponse.builder()
                .statusCode(200)
                .message("Chi tiết page quảng cáo")
                .data(fbAdPageRepository.getFbAdPageByPageId(pageId)).build();
    }
}
