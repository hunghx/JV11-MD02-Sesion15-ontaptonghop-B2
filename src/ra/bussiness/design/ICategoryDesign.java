package ra.bussiness.design;

import ra.bussiness.dto.CategoryAdd;
import ra.bussiness.dto.CategoryAndCountProduct;
import ra.bussiness.entity.Categories;

import java.util.List;

public interface ICategoryDesign extends IGenericDesign<Categories , Integer> {
    void toggleStatus(int id);
    List<Categories> findByName(String name);
    void add(CategoryAdd dto);
    List<CategoryAndCountProduct>  countProductGroupByCategory();
}
