package ra.bussiness.dao;

import ra.bussiness.entity.Categories;

import java.util.List;

public interface ICategoryDao extends IGenericDao<Categories , Integer> {
    void toggleStatus(int id);
    List<Categories> findByName(String name);
    boolean existProductByCategoryId(int categoryId);
}
