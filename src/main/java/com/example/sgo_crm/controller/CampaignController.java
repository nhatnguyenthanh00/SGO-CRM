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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        Campaign campaign = campaignService.addCampaign(campaignAddRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().statusCode(201).message("Thêm thành công chiến dịch").data(campaign).build());
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getDetailCampaign(@PathVariable Long campaignId) {
        Campaign campaign = campaignService.getCampaign(campaignId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.builder().statusCode(200).message("Thông tin chi tiết chiến dịch").data(campaign).build());
    }

    @GetMapping("")
    public ResponseEntity<?> getCampaigns(@RequestParam(defaultValue = "1", required = false) int page) {
        Page<Campaign> campaigns = campaignService.getCampaigns(page);

        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .message("Thành công lấy danh sách chiến dịch")
                .data(campaigns.getContent()).build();

        if(campaigns.getContent().isEmpty()) {
            apiResponse.setMessage("Danh sách chiến dịch rỗng");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterCampaigns(@RequestParam(value = "status",required = false) int status,
                                             @RequestParam(defaultValue = "1", required = false) int page) {
        Page<Campaign> campaigns = campaignService.filterCampaigns(status, page);

        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .message("Danh sách chiến dịch")
                .data(campaigns.getContent()).build();

        if(campaigns.getContent().isEmpty()) {
            apiResponse.setMessage("Danh sách chiến dịch rỗng");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id){
        campaignService.deleteCampaign(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete campaign successful.");
    }

    @PatchMapping(value = "/{id}/update")
    public ResponseEntity<?> updateCampaign(@PathVariable Long id,@Valid @RequestBody CampaignAddRequest campaignAddRequest,
                                         BindingResult result){
        if(result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        Campaign campaign = campaignService.updateCampaign(id,campaignAddRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.builder().statusCode(200).message("Sửa thành công chiến dịch").data(campaign).build());
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
