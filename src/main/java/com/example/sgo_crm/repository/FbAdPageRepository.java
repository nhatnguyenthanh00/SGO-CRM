package com.example.sgo_crm.repository;

import com.example.sgo_crm.DTO.ListPageDTO;
import com.example.sgo_crm.model.FbAdPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FbAdPageRepository extends JpaRepository<FbAdPage, Long> {

    @Query("SELECT new com.example.sgo_crm.DTO.ListPageDTO(fap.pageId, fap.fbPageId, fap.pageName, fap.user, fap.campaign) FROM FbAdPage fap")
    Page<ListPageDTO> getFbAdPagesBy(Pageable pageable);
}
