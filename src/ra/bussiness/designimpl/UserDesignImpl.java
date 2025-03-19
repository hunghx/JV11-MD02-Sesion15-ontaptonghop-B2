package ra.bussiness.designimpl;

import org.mindrot.jbcrypt.BCrypt;
import ra.bussiness.dao.IUserDao;
import ra.bussiness.daoimpl.UserDaoImpl;
import ra.bussiness.design.IUserDesign;
import ra.bussiness.dto.FormLogin;
import ra.bussiness.dto.FormRegister;
import ra.bussiness.dto.UserInfo;
import ra.bussiness.entity.User;
import ra.bussiness.exception.UsernameExistException;

public class UserDesignImpl implements IUserDesign {
    private static final IUserDao userDao = new UserDaoImpl();
    @Override
    public UserInfo login(FormLogin request) {
//        return userDao.login(request);
        // lấy ra user thông qua username
        User user = userDao.findByUsername(request.getUsername());
        if (user == null) {
            return null;
        }
        // so khớp mật khẩu
        boolean isMatch = BCrypt.checkpw(request.getPassword(),user.getPassword());
        if (isMatch) {
            // thanh cong
            UserInfo userInfo = new UserInfo(user.getUserId(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getFullName());
            return userInfo;
        }
        return null;
    }

    @Override
    public void register(FormRegister request) {
        if(userDao.existByUsername(request.getUsername())){
            throw new UsernameExistException("Username already exists");
        }
        // biến đổi từu dto -> entity
        User user = new User();
        // thêm các giá trị vào
        user.setUsername(request.getUsername());
        // mã hóa password
        String hashPassword = BCrypt.hashpw(request.getPassword(),BCrypt.gensalt(5));
        user.setPassword(hashPassword);
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setFullName(request.getFullName());
        userDao.save(user);
    }
}
