package com.example.sgo_crm.repository;

import com.example.sgo_crm.model.FbAdPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<FbAdPage, Long> {
}
