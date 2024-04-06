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
    @JsonFormat(pattern="dd-MM-yyyy")
    private String startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private String endDate;
    private int status = -1;
}
