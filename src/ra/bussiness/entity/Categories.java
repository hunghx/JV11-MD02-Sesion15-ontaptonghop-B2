package ra.bussiness.entity;

import ra.bussiness.config.InputMethods;

public class Categories {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Categories() {
    }

    public Categories(int categoryId, String categoryName, String description, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(){
        System.out.println("Nhập tên danh mục mới : ");
        this.categoryName = InputMethods.getString();
        System.out.println("Nhập mô tả danh mục :" );
        this.description = InputMethods.getString();
    }
    public void displayData(){
        System.out.printf("|%-4d|%-20s|%-20s|%-10s|\n",this.categoryId, this.categoryName, this.description, this.status?"Active":"InActive" );
        System.out.printf("+%s+%s+%s+%s+\n","-".repeat(4),"-".repeat(20),"-".repeat(20),"-".repeat(10));
    }
    private String subStringName(String str, int max){
        return str.length()>max? (str.substring(0,max-3)+"...") : str;
    }
}
