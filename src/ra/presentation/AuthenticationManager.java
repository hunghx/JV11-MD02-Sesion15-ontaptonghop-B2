package ra.presentation;

import ra.bussiness.config.InputMethods;
import ra.bussiness.design.IUserDesign;
import ra.bussiness.designimpl.UserDesignImpl;
import ra.bussiness.dto.FormLogin;
import ra.bussiness.dto.FormRegister;
import ra.bussiness.dto.UserInfo;
import ra.bussiness.exception.UsernameExistException;

public class AuthenticationManager {
    private static final IUserDesign userDesign = new UserDesignImpl();
    public static UserInfo userLogin = null;
    public static boolean login(){
        System.out.println("----------------Đăng Nhập-------------------");
        System.out.print("Tên đăng nhập: ");
        String username = InputMethods.getString();
        System.out.print("Mật khẩu: ");
        String password = InputMethods.getString();
        FormLogin request = new FormLogin(username,password);
        UserInfo userInfo = userDesign.login(request);
        if(userInfo!=null){
            userLogin = userInfo;
            return true;
        }
        return false;
    }
    public static void register(){
        System.out.println("----------------Đăng ký tài khoản-------------------");
        FormRegister request = new FormRegister();
        request.inputData();
        while(true) {
            try {
                userDesign.register(request);
                System.out.println("Đăng ký thành công! Vui lòng đăng nhập");
                return;
            } catch (UsernameExistException e) {
                System.err.println(e.getMessage());
                System.out.println("Vui lòng nhập lại tên đăng nhập");
                request.setUsername(InputMethods.getString());
            }
        }
    }

    public static void logout(){
        userLogin = null;
    }
}
