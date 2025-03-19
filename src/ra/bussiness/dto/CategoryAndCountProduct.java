package ra.bussiness.dto;

public class CategoryAndCountProduct {
    private String categoryName;
    private int productCount;

    public CategoryAndCountProduct(String categoryName, int productCount) {
        this.categoryName = categoryName;
        this.productCount = productCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void displayData(){
        System.out.printf("|%-20s|%10d|\n",categoryName,productCount);
        System.out.printf("+%s+%s+\n","-".repeat(20),"-".repeat(10));
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
