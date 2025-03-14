package ra.bussiness.dao;

import ra.bussiness.dto.ProductDto;
import ra.bussiness.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDao extends IGenericDao<Products, String> {
    List<ProductDto> findAllProductDetail();
    List<Products> filterByPrice(BigDecimal start, BigDecimal end);
}
