package com.example.sgo_crm.DTO;

import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.model.User;
import lombok.Data;

@Data
public class ListPageDTO {

    private Long id;
    private String fbAdPageId;
    private String name;
    private int numberOfConversations;
    private User user;
    private Campaign campaign;

    public ListPageDTO(Long id, String fbAdPageId, String name, User user, Campaign campaign) {
        this.id = id;
        this.fbAdPageId = fbAdPageId;
        this.name = name;
        this.user = user;
        this.campaign = campaign;
    }
}
