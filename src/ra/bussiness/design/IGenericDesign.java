package ra.bussiness.design;

import java.util.List;

public interface IGenericDesign<T, E>{
    List<T> findAll();
    T findById(E id);
    void save(T entity); // vừa thêm mới vừa update
    void deleteById(E id);
}
