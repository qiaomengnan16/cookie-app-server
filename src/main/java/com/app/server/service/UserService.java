package com.app.server.service;

import com.app.server.dao.UserDao;
import com.app.server.dao.UserIllnessDao;
import com.app.server.dao.UserMedicineDao;
import com.app.server.model.User;
import com.app.server.model.UserIllness;
import com.app.server.model.UserMedicine;
import com.app.server.util.ServiceException;
import com.app.server.util.SessionUtils;
import com.app.server.util.UUIDUtils;
import com.app.server.util.UserMedicineStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private SessionUtils sessionUtils;

    @Resource
    private UserMedicineDao userMedicineDao;

    @Resource
    private UserIllnessDao userIllnessDao;

    // 注冊
    public void register(User user) {
        this.checkRegister(user);
        this.userDao.saveUser(user);
    }

    private void checkRegister(User user) {
        // 身份证号如果为空
        if (StringUtils.isEmpty(user.getCardNo())) {
            throw new ServiceException("请输入身份证号");
        }
        // 姓名如果为空
        if (StringUtils.isEmpty(user.getRealName())) {
            throw new ServiceException("请输入姓名");
        }
        // 次数校验弱化, 用做demo, 只判断是否是18位
        if (user.getCardNo().length() != 18) {
            throw new ServiceException("身份证号码错误");
        }
        // 如果身份证号已经存在
        if (userDao.getUser(user.getCardNo()) != null) {
            throw new ServiceException("该身份证号已经被注册");
        }
        user.setId(UUIDUtils.generateUuid());
    }

    // 登录
    public String login(User user) {
        User dbUser = this.checkLogin(user);
        return this.sessionUtils.saveAuth(dbUser);
    }

    private User checkLogin(User user) {
        // 身份证号如果为空
        if (StringUtils.isEmpty(user.getCardNo())) {
            throw new ServiceException("请输入身份证号");
        }
        // 姓名如果为空
        if (StringUtils.isEmpty(user.getRealName())) {
            throw new ServiceException("请输入姓名");
        }
        // 验证身份证和姓名是否正确
        User dbUser = this.userDao.getUser(user.getCardNo());
        if (dbUser == null || !user.getRealName().equals(dbUser.getRealName())) {
            throw new ServiceException("身份证号或者姓名错误");
        }
        return dbUser;
    }

    // 用户看病历史
    public List<UserIllness> userIllnessList() {
        return this.userIllnessDao.getUserIllness(this.sessionUtils.getAuth().getId());
    }

    // 用户领药历史
    public List<UserMedicine> userMedicineList() {
        return this.userMedicineDao.getUserMedicine(this.sessionUtils.getAuth().getId());
    }

    // 用户领药历史
    public void userMedicineConfirm(String id) {
        UserMedicine userMedicine = this.userMedicineDao.getUserMedicineById(this.sessionUtils.getAuth().getId(), id);
        if (userMedicine != null) {
            userMedicine.setStatus(UserMedicineStatus.YES.getType());
        }
    }

}
