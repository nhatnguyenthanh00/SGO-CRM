package com.example.sgo_crm.repository;

import com.example.sgo_crm.model.FbAdAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FbAdAccountRepository extends JpaRepository<FbAdAccount, Long> {

    @Query("SELECT faa FROM FbAdAccount faa WHERE faa.fbAdPage.pageId = :pageId")
    List<FbAdAccount> getFbAdAccountsByFbAdPageId(@Param("pageId") Long pageId);

}
