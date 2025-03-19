package ra.bussiness.dto;

import java.util.Date;

public class UserInfo {
    private int userId;
    private String email;
    private String phoneNumber;
    private String address;
    private String fullName;

    public UserInfo(int userId, String email, String phoneNumber, String address, String fullName) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
