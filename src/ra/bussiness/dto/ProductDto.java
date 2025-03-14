package ra.bussiness.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String productId;
    private String productName;
    private BigDecimal price;
    private byte status;
    private String categoryName;

    public ProductDto(String productId, String productName, BigDecimal price, byte status, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.categoryName = categoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void displayData(){
        System.out.printf("|%-4s|%-20s|%-5s|%-10s|%-20s|\n",productId,productName,price,getStatusName(),categoryName);
        System.out.printf("+%s+%s+%s+%s+%s+\n","-".repeat(4),"-".repeat(20),"-".repeat(5),"-".repeat(10),"-".repeat(20));
    }

    private String getStatusName(){
        // Java 14 +
        return switch (status){
            case 0 -> "Đang bán";
            case 1 -> "Hêt hàng";
            case 2 -> "Không bán";
            default -> "Unknown";
        };
    }
}
