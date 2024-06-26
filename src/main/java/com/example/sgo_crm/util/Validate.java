package com.example.sgo_crm.util;

import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.request.CampaignAddRequest;
import com.example.sgo_crm.request.UserRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class Validate {

    private static final String PHONE_NUMBER_REGEX = "^0[1-9][0-9]{8}$";

    private static final String FULLNAME_REGEX = "^[a-zA-ZÀ-ỹ\\s]+$";

    private static final String USERNAMR_REGEX = "^[a-zA-Z0-9]*$";

    private static final String PASSWORD_REGEX = "\\S+$";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void isValidData(UserRequest request) {
        isValidPhone(request.getPhonenumber());
        isValidFullname(request.getFullname());
        isValidUsername(request.getUsername());
        isValidPassword(request.getPassword());

        if(request.getRoles().isEmpty()) {
            throw new InvalidFormatException("Role không được để trống");
        }
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

    public void isValidData(String campaignName, Date startDate, Date endDate, int status){
        isValidCampaignName(campaignName);
        isValidCampaignDate(startDate,endDate);
        isValidCampaignStatus(status);
    }

    public void isValidCampaignName(String campaignName) {
        if(campaignName.isEmpty()) {
            throw new InvalidFormatException("Tên chiến dịch không được để trống");
        }

    }

    public void isValidCampaignDate(Date startDate, Date endDate){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);

        Date currentDate = calendar.getTime();
        if(currentDate.after(startDate)){
            throw new InvalidFormatException("Ngày bắt đầu chiến dịch không trước ngày hiện tại");
        }
        if(!endDate.after(startDate)){
            throw new InvalidFormatException("Ngày kết thúc chiến dịch phải sau ngày bắt đầu chiến dịch");
        }
    }

    public void isValidCampaignStatus(int status){
        if(status<-1 || status >1)
            throw new InvalidFormatException("Status chiến dịch không hợp lệ");
    }

}
