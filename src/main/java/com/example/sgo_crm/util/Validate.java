package com.example.sgo_crm.util;

import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class Validate {

    private static final String PHONE_NUMBER_REGEX = "^0[1-9][0-9]{8}$";

    private static final String FULLNAME_REGEX = "^[a-zA-ZÀ-ỹ\\s]+$";

    private static final String USERNAMR_REGEX = "^[a-zA-Z0-9]*$";

    private static final String PASSWORD_REGEX = "\\S+$";

    public void isValidData(UserRequest request) {
        isValidPhone(request.getPhonenumber());
        isValidFullname(request.getFullname());
        isValidUsername(request.getUsername());
        isValidPassword(request.getPassword());
    }

    public void isValidPhone(String phonenumber) {
        if(phonenumber.isEmpty()) {
            throw new InvalidFormatException("Số điện thoại không được để trống");
        }

        if(phonenumber.length() != 10) {
            throw new InvalidFormatException("Số điện thoại chỉ gồm 10 chữ số");
        }

        if(!Pattern.compile(PHONE_NUMBER_REGEX).matcher(phonenumber).matches()) {
            throw new InvalidFormatException("Số điện thoại không hợp lệ");
        }
    }

    public void isValidFullname(String fullname) {
        if(fullname.isEmpty()) {
            throw new InvalidFormatException("Họ và tên không được để trống");
        }

        if(!Pattern.compile(FULLNAME_REGEX).matcher(fullname).matches()) {
            throw new InvalidFormatException("Họ và tên không chứa ký tự đặc biệt");
        }
    }

    public void isValidUsername(String username) {
        if(username.isEmpty()) {
            throw new InvalidFormatException("Username không được để trống");
        }

        if(username.length() < 8 || username.length() > 15) {
            throw new InvalidFormatException("Username phải có từ 8-15 ký tự");
        }

        if(!Pattern.compile(USERNAMR_REGEX).matcher(username).matches()) {
            throw new InvalidFormatException("Username không chứa khoảng trắng và ký tự đặc biệt");
        }
    }

    public void isValidPassword(String password) {
        if(password.isEmpty()) {
            throw new InvalidFormatException("Password không được để trống");
        }

        if(password.length() < 8) {
            throw new InvalidFormatException("Password phải có ít nhất 8 ký tự");
        }

        if(!Pattern.compile(PASSWORD_REGEX).matcher(password).matches()) {
            throw new InvalidFormatException("Password không được chứa khoảng trắng");
        }
    }

    public void isValidData(String campaignName, Date startDate, Date endDate){
        isValidCampaignName(campaignName);
        isValidCampaignDate(startDate,endDate);
    }

    public void isValidCampaignName(String campaignName) {
        if(campaignName.isEmpty()) {
            throw new InvalidFormatException("Tên chiến dịch không được để trống");
        }

        if(!Pattern.compile(FULLNAME_REGEX).matcher(campaignName).matches()) {
            throw new InvalidFormatException("Tên chiến dịch không chứa ký tự đặc biệt");
        }
    }

    public void isValidCampaignDate(Date startDate, Date endDate){


        Date currentDate = new Date();
        if(currentDate.after(startDate)){
            throw new InvalidFormatException("Ngày bắt đầu chiến dịch không trước ngày hiện tại");
        }
        if(!endDate.after(startDate)){
            throw new InvalidFormatException("Ngày kết thúc chiến dịch phải sau ngày bắt đầu chiến dịch");
        }
    }

}
