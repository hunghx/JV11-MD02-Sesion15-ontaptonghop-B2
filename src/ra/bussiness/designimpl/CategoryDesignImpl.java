package ra.bussiness.designimpl;

import ra.bussiness.dao.ICategoryDao;
import ra.bussiness.daoimpl.CategoryDaoImpl;
import ra.bussiness.design.ICategoryDesign;
import ra.bussiness.dto.CategoryAdd;
import ra.bussiness.entity.Categories;

import java.util.List;

public class CategoryDesignImpl implements ICategoryDesign {
    private static final ICategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Categories> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Categories findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Categories entity) {
        categoryDao.save(entity);
    }

    @Override
    public void add(CategoryAdd dto) {

    }

    @Override
    public void deleteById(Integer id) {
        boolean isExist = categoryDao.existProductByCategoryId(id);
        if(isExist){
            throw new RuntimeException("Ko thể xóa danh mục được vì chưa sản phẩm");
        }
        categoryDao.deleteById(id);
    }

    @Override
    public void toggleStatus(int id) {
        categoryDao.toggleStatus(id);
    }

    @Override
    public List<Categories> findByName(String name) {
        return categoryDao.findByName(name);
    }
}
