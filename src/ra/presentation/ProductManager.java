package ra.presentation;

import ra.bussiness.config.InputMethods;
import ra.bussiness.design.IProductDesign;
import ra.bussiness.designimpl.ProductDesignImpl;
import ra.bussiness.dto.ProductDto;
import ra.bussiness.entity.Categories;
import ra.bussiness.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public class ProductManager {
    private static final IProductDesign productDesign = new ProductDesignImpl();

    public static void showProductMenu() {
        while (true) {
            System.out.println("+-------------------Product  Menu------------------------+");
            System.out.println("+ 1. Thêm mới sản phẩm                                   +");
            System.out.println("+ 2. Hiển thị danh sách sản phẩm                         +");
            System.out.println("+ 3. Cập nhật thông tin                                  +");
            System.out.println("+ 4. Xóa sản phẩm                                        +");
            System.out.println("+ 5. Sắp xếp sản phẩm theo gia                            +");
            System.out.println("+ 6. Tìm kiếm theo ten                                   +");
            System.out.println("+ 7. Tìm kếm trong khoảng giá                            +");
            System.out.println("+ 8. Quay lại                                            +");
            System.out.println("+--------------------------------------------------------+");

            System.out.println("Nhập lựa chon : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // Nhập số lượng danh mục cần thêm
                    System.out.println(" Nhập số lương sản phẩm cần thêm : ");
                    int n = InputMethods.getInteger();
                    for (int i = 1; i <= n; i++) {
                        System.out.println("---------- Nhập  sản phẩm thứ " + i + "-----------");
                        Products pro = new Products();
//                        pro.inputData();
                        // lưu vào database
                        productDesign.save(pro);
                    }
                    System.out.printf("Thêm mới thành công %d san phẩm \n", n);
                    break;
                case 2:
                    List<ProductDto> productDtoList = productDesign.findAllProductDetail();
                    if (productDtoList.isEmpty()) {
                        System.err.println("Không có thông tin san pham ");
                    } else {
                        System.out.printf("+%s+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(5), "-".repeat(10), "-".repeat(20));
                        System.out.printf("|%-4s|%-20s|%-5s|%-10s|%-20s|\n", "ID", "Product Name", "Price", "Status", "Catalog Name");
                        System.out.printf("+%s+%s+%s+%s+%s+\n", "-".repeat(4), "-".repeat(20), "-".repeat(5), "-".repeat(10), "-".repeat(20));
                        productDtoList.forEach(ProductDto::displayData);
                    }
                    break;
                case 3:
                    System.out.println("Nhập mã sản phẩm cần cập nhật");
                    String idEdit = InputMethods.getString();
                    Products proEdit = productDesign.findById(idEdit);
                    if (proEdit == null) {
                        System.err.println("ko tìm tấy id");
                    } else {
                        System.out.println("Thông tin cũ");
//
                        System.out.println("Nhập thông tin mới");
//
                        productDesign.save(proEdit);
                        System.out.println("Câp nhật thành công");
                    }
                    break;
                case 4:
                    System.out.println("Nhập mã sản phẩm cần xóa");
                    String idDelete = InputMethods.getString();

                    if (productDesign.findById(idDelete) == null) {
                        System.err.println("ko tìm tấy id");
                    } else {
                        productDesign.deleteById(idDelete);
                        System.out.println("Xóa thành công");
                    }
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    System.out.println("Nhập giá bắt đàu");
                    BigDecimal startPrice = InputMethods.getBigDecimal();
                    System.out.println("Nhập giá kết thúc");
                    BigDecimal endPrice = InputMethods.getBigDecimal();
                    try {
                        List<Products> filterList = productDesign.filterByPrice(startPrice, endPrice);
                        if (filterList.isEmpty()) {
                            System.out.println("ko có sp nào khớp");
                        } else {
                            System.out.println("-----------Danh sách sp là ----------------");
                            filterList.forEach(System.out::println);
                        }
                    }catch (IllegalArgumentException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Vui lòng nhập lựa chọn từ 1 đến 6.");
            }
        }
    }
}
