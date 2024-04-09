package com.example.sgo_crm.service;

import com.example.sgo_crm.model.FbAdAccount;

import java.util.List;

public interface FbAdAccountService {

    List<FbAdAccount> getFbAdAccountByPageId(Long pageId);

}
