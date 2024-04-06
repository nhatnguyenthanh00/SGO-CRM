package com.example.sgo_crm.DTO;

import lombok.Data;

@Data
public class DetailFbAdPageDTO {

    private String fbAdPageId;

    private String fbAdPageName;

    private String spend;

    public DetailFbAdPageDTO(String fbAdPageId, String fbAdPageName) {
        this.fbAdPageId = fbAdPageId;
        this.fbAdPageName = fbAdPageName;
    }
}
