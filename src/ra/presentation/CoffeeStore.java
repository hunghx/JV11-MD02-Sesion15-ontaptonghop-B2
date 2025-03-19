package ra.presentation;


import ra.bussiness.config.InputMethods;
import ra.bussiness.entity.Categories;

public class CoffeeStore { // Main Class
    public static void main(String[] args) {
        showAuthenticationMenu();
    }

    public static void showAuthenticationMenu(){
        while (true) {
            System.out.println("+-------------------Authentication Menu------------------------+");
            System.out.println("+ 1. Đăng nhập                                                 +");
            System.out.println("+ 2. Đăng ký tài khoản                                         +");
            System.out.println("+ 3. Thoát                                                     +");
            System.out.println("+--------------------------------------------------------------+");

            System.out.println("Nhập lựa chọn : ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    // đăng nhập
                    boolean isLogin = AuthenticationManager.login();
                    if (isLogin){
                        System.out.println("Đăng nhập thành công");
                        System.out.printf("Chào mừng bạn %s quay lại ứng dụng \n", AuthenticationManager.userLogin.getFullName());
                        runMainMenu();
                    } else {
                        System.err.println("Sai tài khoản hoặc mat khẩu");
                    }
                    break;
                case 2:
                    // đăng ký
                    AuthenticationManager.register();
                    break;
                case 3:
                    System.out.println("Thoát");
                    return;
                default:
                    System.err.println("Nhập ko đúng , vui long nhập lại");
            }
        }
    }

    public static void runMainMenu(){
        while (true){
            System.out.println("+-------------------Main Menu------------------------+");
            System.out.println("+ 1. Quản lý danh mục                                +");
            System.out.println("+ 2. Quản lý sản phẩm                                +");
            System.out.println("+ 3. Đăng xuất                                       +");
            System.out.println("+----------------------------------------------------+");

            System.out.println("Nhập lựa chọn : ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    // menu danh mục
                    CategoryManager.showCategoryMenu();
                    break;
                case 2:
                    // menu sản phẩm
                    ProductManager.showProductMenu();
                    break;
                case 3:
                    AuthenticationManager.logout();
                    return;
                  // logout
                default:
                    System.err.println("Nhập ko đúng , vui long nhập lại");
            }
        }
    }
}
