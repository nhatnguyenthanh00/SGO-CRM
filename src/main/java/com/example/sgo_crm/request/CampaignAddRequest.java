package com.example.sgo_crm.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignAddRequest {
    private String campaignName;
    private Date startDate;
    private Date endDate;
}
