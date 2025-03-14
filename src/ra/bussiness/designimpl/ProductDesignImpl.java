package ra.bussiness.designimpl;

import ra.bussiness.config.GenerateId;
import ra.bussiness.dao.IProductDao;
import ra.bussiness.daoimpl.ProductDaoImpl;
import ra.bussiness.design.IProductDesign;
import ra.bussiness.dto.ProductDto;
import ra.bussiness.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public class ProductDesignImpl implements IProductDesign {
    private static final IProductDao productDao = new ProductDaoImpl();
    @Override
    public List<ProductDto> findAllProductDetail() {
        return productDao.findAllProductDetail();
    }

    @Override
    public List<Products> findAll() {
        return productDao.findAll();
    }

    @Override
    public Products findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void save(Products entity) {
        if(entity.getProductId() == null){
            // thêm moi
            // tạo id ngẫu nhiên ko trung lăp
            while(true){
                String newId = GenerateId.generateProductId('C');
                if(productDao.findById(newId)==null){
                    break;
                }
            }
        }
        productDao.save(entity);
    }

    @Override
    public void deleteById(String id) {
        productDao.deleteById(id);
    }

    @Override
    public List<Products> filterByPrice(BigDecimal start, BigDecimal end) {
        if(start.compareTo(end)>0){
            throw new IllegalArgumentException("Giá bắt đầu phải nhỏ hơn giá kết thúc");
        }
        return productDao.filterByPrice(start, end);
    }
}
