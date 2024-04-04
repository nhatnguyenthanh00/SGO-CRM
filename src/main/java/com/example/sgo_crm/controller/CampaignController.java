package com.example.sgo_crm.controller;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.Campaign;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.service.impl.CampaignServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CampaignController {

    private final CampaignServiceImpl campaignService;
    @Autowired
    public CampaignController(CampaignServiceImpl campaignService){
        this.campaignService = campaignService;
    }
    @PostMapping(value = "/add")
    public ResponseEntity<?> addCampaign(@Valid @RequestBody CampaignAddRequest campaignAddRequest,
                                         BindingResult result){
        if(result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        Campaign campaign = campaignService.addCampaign(campaignAddRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().statusCode(201).message("Thêm thành công chiến dịch").data(campaign).build());
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<?> handleUsernameExistsException(UsernameExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(DataSaveException.class)
    public ResponseEntity<?> handleDataSaveException(DataSaveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
