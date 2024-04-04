package com.example.sgo_crm.service;

import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.request.CampaignAddRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CampaignService {
    void saveCampaign(Campaign campaign);

    Campaign addCampaign(CampaignAddRequest campaignAddRequest);

    Campaign getCampaign(Long id);

    Page<Campaign> getCampaigns(int page);
}
