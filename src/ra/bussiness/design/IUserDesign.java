package ra.bussiness.design;

import ra.bussiness.dto.FormLogin;
import ra.bussiness.dto.FormRegister;
import ra.bussiness.dto.UserInfo;

public interface IUserDesign {
    UserInfo login(FormLogin request);
    void register(FormRegister request);
}
