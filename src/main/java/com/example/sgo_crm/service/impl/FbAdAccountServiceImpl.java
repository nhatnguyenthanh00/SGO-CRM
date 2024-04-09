package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.model.FbAdAccount;
import com.example.sgo_crm.repository.FbAdAccountRepository;
import com.example.sgo_crm.service.FbAdAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FbAdAccountServiceImpl implements FbAdAccountService {

    private final FbAdAccountRepository fbAdAccountRepository;

    @Autowired
    public FbAdAccountServiceImpl(FbAdAccountRepository fbAdAccountRepository) {
        this.fbAdAccountRepository = fbAdAccountRepository;
    }

    @Override
    public List<FbAdAccount> getFbAdAccountByPageId(Long pageId) {
        return fbAdAccountRepository.getFbAdAccountsByFbAdPageId(pageId);
    }
}
