package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.DTO.UserDTO;
import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.model.User;
import com.example.sgo_crm.repository.CampaignRepository;
import com.example.sgo_crm.repository.UserRepository;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.service.CampaignService;
import com.example.sgo_crm.util.AppConstants;
import com.example.sgo_crm.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CampaignServiceImpl implements CampaignService {

    private  final CampaignRepository campaignRepository;


    private final UserServiceImpl userService;

    private final Validate validate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository, UserServiceImpl userService, Validate validate){
        this.campaignRepository = campaignRepository;
        this.userService = userService;
        this.validate = validate;
    }

    @Override
    public void saveCampaign(Campaign campaign){
        campaignRepository.save(campaign);
    }

    @Override
    public Campaign addCampaign(CampaignAddRequest campaignAddRequest) {
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            dateFormat.setLenient(false);
            startDate = dateFormat.parse(campaignAddRequest.getStartDate());
            endDate = dateFormat.parse(campaignAddRequest.getEndDate());
        }
        catch (ParseException e){
            throw new InvalidFormatException(AppConstants.DATE_IS_INVALID);
        }
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

    @Override
    public Campaign updateCampaign(Long id, CampaignAddRequest campaignAddRequest){
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            dateFormat.setLenient(false);
            startDate = dateFormat.parse(campaignAddRequest.getStartDate());
            endDate = dateFormat.parse(campaignAddRequest.getEndDate());
        }
        catch (ParseException e){
            throw new InvalidFormatException(AppConstants.DATE_IS_INVALID);
        }
            validate.isValidData(campaignAddRequest.getCampaignName(),startDate,endDate);
            Campaign existedCampaign = campaignRepository.findCampaignByCampaignName(campaignAddRequest.getCampaignName());
            if(existedCampaign!=null && existedCampaign.getCampaignId()!=id) {
                throw new UsernameExistsException(AppConstants.CAMPAIGNNAME_IS_ALREADY_EXIST);
            }
            try {
                Campaign campaign = campaignRepository.findById(id).get();
                if(campaign == null) return null;
                campaign.setCampaignName(campaignAddRequest.getCampaignName());
                campaign.setStatus(campaignAddRequest.getStatus());
                campaign.setStartDate(startDate);
                campaign.setEndDate(endDate);
                saveCampaign(campaign);
                return campaign;
            } catch (DataSaveException e) {
                throw new DataSaveException(AppConstants.ERROR_SAVING);
            }
    }

    @Override
    public Campaign getCampaign(Long id) {
        return campaignRepository.getCampaignByCampaignId(id).get();
    }

    @Override
    public Page<Campaign> getCampaigns(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return campaignRepository.findAll(pageable);
    }

    @Override
    public Page<Campaign> findCampaigns(Long id, String name, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        if(id == null && name.equals("")) {
            return campaignRepository.findAll(pageable);
        }
        return campaignRepository.findCampaignsByCampaignIdAndCampaignName(id, name.trim(), pageable);
    }

    @Override
    public Page<Campaign> filterCampaigns(int status, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return campaignRepository.filterCampaignsByStatus(status, pageable);
    }

    public void deleteCampaign(Long id){
        campaignRepository.deleteById(id);
    }

    @Override
    public void assignUsersToCampaign(List<String> userIds, Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElse(null);
        if (campaign != null) {
            List<User> users = userService.findAllById(userIds);
            campaign.getUsers().clear();
            campaign.getUsers().addAll(users);
            for (User user : users) {
                user.getCampaigns().add(campaign);
            }
            campaignRepository.save(campaign);
        }
    }

    public Set<UserDTO> getAllUserByCampaign(Long campaignId){
        Campaign campaign = campaignRepository.findById(campaignId).orElse(null);
        if(campaign == null) return null;
        Set<User> users = campaign.getUsers();
        Set<UserDTO> userDTOSet = new HashSet<>();
        for(User user : users){
            UserDTO userDTO = userService.changeToDTO(user);
            userDTOSet.add(userDTO);
        }
        return userDTOSet;
    }

}
