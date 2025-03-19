package ra.bussiness.dao;

import ra.bussiness.dto.FormLogin;
import ra.bussiness.dto.UserInfo;
import ra.bussiness.entity.User;

public interface IUserDao extends IGenericDao<User , Integer> {
    UserInfo login(FormLogin request);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    boolean existByPhoneNumber(String phone);
    User findByUsername(String username);

}
