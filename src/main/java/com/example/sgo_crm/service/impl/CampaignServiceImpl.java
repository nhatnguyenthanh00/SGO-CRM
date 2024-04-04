package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.repository.CampaignRepository;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.service.CampaignService;
import com.example.sgo_crm.util.AppConstants;
import com.example.sgo_crm.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService {

    private  final CampaignRepository campaignRepository;

    private final Validate validate;
    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository, Validate validate){
        this.campaignRepository = campaignRepository;
        this.validate = validate;
    }

    @Override
    public void saveCampaign(Campaign campaign){
        campaignRepository.save(campaign);
    }

    @Override
    public Campaign addCampaign(CampaignAddRequest campaignAddRequest) {
        validate.isValidData(campaignAddRequest);
        if(campaignRepository.findCampaignByCampaignName(campaignAddRequest.getCampaignName())!=null){
            throw new UsernameExistsException(AppConstants.CAMPAIGNNAME_IS_ALREADY_EXIST);
        }
        try {
            Campaign campaign = Campaign.builder()
                    .campaignName(campaignAddRequest.getCampaignName())
                    .startDate(campaignAddRequest.getStartDate())
                    .endDate(campaignAddRequest.getEndDate())
                    .status(-1)
                    .build();
            saveCampaign(campaign);
            return campaign;
        }catch (DataSaveException e) {
            throw new DataSaveException(AppConstants.ERROR_SAVING);
        }
    }
}
