package ra.bussiness.design;

import ra.bussiness.dto.ProductDto;
import ra.bussiness.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDesign extends IGenericDesign<Products, String> {
    List<ProductDto> findAllProductDetail();
    List<Products> filterByPrice(BigDecimal start, BigDecimal end);
}
