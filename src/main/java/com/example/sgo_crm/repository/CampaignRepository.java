package com.example.sgo_crm.repository;

import com.example.sgo_crm.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Campaign findCampaignByCampaignName(String campaignName);

    Optional<Campaign> getCampaignByCampaignId(Long campaignId);
}
