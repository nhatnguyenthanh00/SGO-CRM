package com.example.sgo_crm.service;

import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.request.CampaignAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignService {
    void saveCampaign(Campaign campaign);

    Campaign addCampaign(CampaignAddRequest campaignAddRequest);

    Campaign updateCampaign(Long id,CampaignAddRequest campaignAddRequest);

    Campaign getCampaign(Long id);

    Page<Campaign> getCampaigns(int page);

    Page<Campaign> findCampaigns(String id, String name,int page);

    Page<Campaign> filterCampaigns(String status, int page);

    void assignUsersToCampaign(List<String> userIds, Long campaignId);
}
