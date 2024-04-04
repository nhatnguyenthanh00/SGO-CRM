package com.example.sgo_crm.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignAddRequest {
    private String campaignName;
    private String startDate;
    private String endDate;
}
