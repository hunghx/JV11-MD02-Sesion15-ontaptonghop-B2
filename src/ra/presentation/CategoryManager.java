package ra.presentation;

import ra.bussiness.config.InputMethods;
import ra.bussiness.design.ICategoryDesign;
import ra.bussiness.designimpl.CategoryDesignImpl;
import ra.bussiness.entity.Categories;

import java.util.List;

public class CategoryManager {
    private static final ICategoryDesign categoryDesign = new CategoryDesignImpl();

    public static void showCategoryMenu() {
        while (true) {
            System.out.println("+-------------------Category Menu------------------------+");
            System.out.println("+ 1. Thêm mới danh mục                                   +");
            System.out.println("+ 2. Hiển thị danh sách danh mục                         +");
            System.out.println("+ 3. Cập nhật thông tin                                  +");
            System.out.println("+ 4. Xóa danh mục                                        +");
            System.out.println("+ 5. Cập nhật trạng thái                                 +");
            System.out.println("+ 6. Tìm kiếm theo ten                                   +");
            System.out.println("+ 7. Quay lại                                            +");
            System.out.println("+--------------------------------------------------------+");

            System.out.println("Nhập lựa chon : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // Nhập số lượng danh mục cần thêm
                    System.out.println(" Nhập số lươg danh mục cần thêm : ");
                    int n = InputMethods.getInteger();
                    for (int i = 1; i <= n; i++) {
                        System.out.println("---------- Nhập danh mục thứ " + i + "-----------");
                        Categories cat = new Categories();
                        cat.inputData();
                        // lưu vào database
                        categoryDesign.save(cat);
                    }
                    System.out.printf("Thêm mới thành công %d danh mục \n", n);
                    break;
                case 2:
                    List<Categories> categories = categoryDesign.findAll();
                    if (categories.isEmpty()) {
                        System.err.println("Không có thông tin ");
                    } else {
                        System.out.printf("+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(20), "-".repeat(10));
                        System.out.printf("|%-4s|%-20s|%-20s|%-10s|\n", "ID", "CategoryName", "Description", "Status");
                        System.out.printf("+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(20), "-".repeat(10));
                        categories.forEach(Categories::displayData);
                    }
                    break;
                case 3:
                    System.out.println("Nhập mã danh mục cần cập nhật");
                    int idEdit = InputMethods.getInteger();
                    Categories catEdit = categoryDesign.findById(idEdit);
                    if (catEdit == null) {
                        System.err.println("ko tìm tấy id");
                    } else {
                        System.out.println("Thông tin cũ");
                        catEdit.displayData();
                        System.out.println("Nhập thông tin mới");
                        catEdit.inputData();
                        categoryDesign.save(catEdit);
                        System.out.println("Câ nhật thành công");
                    }
                    break;
                case 4:
                    System.out.println("Nhập mã danh mục cần xóa");
                    int idDelete = InputMethods.getInteger();
                    Categories cat = categoryDesign.findById(idDelete);
                    if (cat == null) {
                        System.err.println("ko tìm tấy id");
                    } else {
                        try {
                            categoryDesign.deleteById(idDelete);
                            System.out.println("Xóa thành công");
                        }catch (RuntimeException e){
                            System.err.println(e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Nhập mã danh mục cần tăt mở trạng thái");
                    int id = InputMethods.getInteger();
                    Categories catFind = categoryDesign.findById(id);
                    if (catFind == null) {
                        System.err.println("ko tìm tấy id");
                    } else {
                        categoryDesign.toggleStatus(id);
                        System.out.println("cập nhật thành công");
                    }
                    break;
                case 6:
                    System.out.println("Nhap ten can tim kiem");
                    String name = InputMethods.getString();
                    List<Categories> categoriesList = categoryDesign.findByName(name);
                    if (categoriesList.isEmpty()) {
                        System.err.println("Không có thông tin ");
                    } else {
                        System.out.printf("+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(20), "-".repeat(10));
                        System.out.printf("|%-4s|%-20s|%-20s|%-10s|\n", "ID", "CategoryName", "Description", "Status");
                        System.out.printf("+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(20), "-".repeat(10));
                        categoriesList.forEach(Categories::displayData);
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Vui lòng nhập lựa chọn từ 1 đến 6.");
            }
        }
    }
}
