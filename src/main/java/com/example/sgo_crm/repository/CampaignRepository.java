package com.example.sgo_crm.repository;

import com.example.sgo_crm.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Campaign findCampaignByCampaignName(String campaignName);

    Campaign findCampaignByCampaignIdAndCampaignName(Long campaignId, String campaignName);

    Optional<Campaign> getCampaignByCampaignId(Long campaignId);

    Page<Campaign> findAll(Pageable pageable);

    @Query("SELECT c FROM Campaign c " +
            "WHERE (:id IS NULL OR c.campaignId = :id) AND " +
            "(:name IS NULL OR :name = '' OR c.campaignName LIKE %:name%)")
    Page<Campaign> findCampaignsByCampaignIdAndCampaignName(@Param("id") Long id, @Param("name") String name, Pageable pageable);

    @Query("SELECT c FROM Campaign c WHERE c.status = :status")
    Page<Campaign> filterCampaignsByStatus(@Param("status") int status, Pageable pageable);
}
