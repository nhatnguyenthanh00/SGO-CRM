package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.DTO.ListPageDTO;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.ConversationResponse;
import com.example.sgo_crm.model.FbAdAccountResponse;
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
import java.util.ArrayList;
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

        List<FbAdAccountResponse.Data> data = facebookService.getFbAdAccountsOfUser(userAccessToken);
        //fbAdPageRepository.getFbAdPageByPageId(pageId)
        return APIResponse.builder()
                .statusCode(200)
                .message("Chi tiết page quảng cáo")
                .data(data).build();
    }

    @Override
    public APIResponse findFbAdPages(String userAccessToken, String id, String name, String numberOfConversation, int page) {

        APIResponse response = getFbAdPages(userAccessToken, page);

        if (id.equals("") && name.equals("") && numberOfConversation.equals("")) {
            return response;
        }

        Long pageId = 0L;
        if(!id.equals("")) {
            try {
                pageId = Long.parseLong(id);
            }catch (NumberFormatException e) {
                throw new InvalidFormatException("Page id không hợp lệ");
            }
        }

        int noc = 0;
        if(!numberOfConversation.equals("")) {
            try {
                noc = Integer.parseInt(numberOfConversation);
            }catch (NumberFormatException e) {
                throw new InvalidFormatException("Số lượng tin nhắn không hợp lệ");
            }
        }

        List<ListPageDTO> listPageDTOS = (List<ListPageDTO>) response.getData();
        List<ListPageDTO> rs = new ArrayList<>();

        for(ListPageDTO listPageDTO:listPageDTOS) {
            if(listPageDTO.getId().equals(pageId) ||
                    listPageDTO.getName().contains(name) ||
                    listPageDTO.getNumberOfConversations() == noc) {
                rs.add(listPageDTO);
            }
        }

        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .data(rs).build();

        apiResponse.setMessage(!rs.isEmpty() ? "Danh sách tài khoảng quảng cáo" : "Không có kết quả tìm kiếm");

        return apiResponse;
    }
}
