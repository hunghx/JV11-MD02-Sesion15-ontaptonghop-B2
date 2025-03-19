package ra.bussiness.dto;

import ra.bussiness.config.InputMethods;

public class FormRegister {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address  ;
    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void inputData(){
        // nhaapj thoong tin
        System.out.println("Nhập username : ");
        this.username = InputMethods.getString();
        System.out.println("Nhập password : ");
        this.password = InputMethods.getString();
        System.out.println("Nhập email : ");
        this.email = InputMethods.getString();
        System.out.println("Nhập số điện thoại : ");
        this.phoneNumber = InputMethods.getString();
        System.out.println("Nhập đia chỉ : ");
        this.address = InputMethods.getString();
        System.out.println("Nhập họ và tên : ");
        this.fullName = InputMethods.getString();

    }
}
