package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.repository.CampaignRepository;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.service.CampaignService;
import com.example.sgo_crm.util.AppConstants;
import com.example.sgo_crm.util.Validate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CampaignServiceImpl implements CampaignService {

    private  final CampaignRepository campaignRepository;

    private final Validate validate;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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
        try {
            dateFormat.setLenient(false);
            Date startDate = dateFormat.parse(campaignAddRequest.getStartDate());
            Date endDate = dateFormat.parse(campaignAddRequest.getEndDate());

            validate.isValidData(campaignAddRequest.getCampaignName(),startDate,endDate);
            if (campaignRepository.findCampaignByCampaignName(campaignAddRequest.getCampaignName()) != null) {
                throw new UsernameExistsException(AppConstants.CAMPAIGNNAME_IS_ALREADY_EXIST);
            }
            try {
                Campaign campaign = Campaign.builder()
                        .campaignName(campaignAddRequest.getCampaignName())
                        .startDate(startDate)
                        .endDate(endDate)
                        .status(-1)
                        .build();
                saveCampaign(campaign);
                return campaign;
            } catch (DataSaveException e) {
                throw new DataSaveException(AppConstants.ERROR_SAVING);
            }
        }
        catch (ParseException e){
            throw new InvalidFormatException(AppConstants.DATE_IS_INVALID);
        }
    }
}
