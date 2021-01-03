package com.app.server.service;

import com.app.server.dao.IllnessDao;
import com.app.server.dao.MedicineDao;
import com.app.server.dao.UserIllnessDao;
import com.app.server.dao.UserMedicineDao;
import com.app.server.model.Illness;
import com.app.server.model.Medicine;
import com.app.server.model.UserIllness;
import com.app.server.model.UserMedicine;
import com.app.server.util.ServiceException;
import com.app.server.util.SessionUtils;
import com.app.server.util.UUIDUtils;
import com.app.server.util.UserMedicineStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IllnessService {

    @Resource
    private IllnessDao illnessDao;

    @Resource
    private MedicineDao medicineDao;

    @Resource
    private SessionUtils sessionUtils;

    @Resource
    private UserMedicineDao userMedicineDao;

    @Resource
    private UserIllnessDao userIllnessDao;

    public Illness get(String id) {
        return this.illnessDao.getIllness(id);
    }

    public List<Illness> list() {
        return this.illnessDao.list();
    }

    public void confirm(String id) {
        // 验证数据
        Illness illness = this.illnessDao.getIllness(id);
        if (illness == null) {
            throw new ServiceException("未获取到相关病情数据");
        }
        Medicine medicine = this.medicineDao.getMedicine(illness.getMedicineId());
        if (medicine == null) {
            throw new ServiceException("未获取到相关药品数据");
        }
        // 保存用户看病历史
        UserIllness userIllness = new UserIllness();
        userIllness.setId(UUIDUtils.generateUuid());
        userIllness.setIllnessId(illness.getId());
        userIllness.setUserId(sessionUtils.getAuth().getId());
        userIllness.setIllnessName(illness.getName());
        this.userIllnessDao.saveUserIllness(userIllness);
        // 保存发放的药品
        UserMedicine userMedicine = new UserMedicine();
        userMedicine.setId(UUIDUtils.generateUuid());
        userMedicine.setMedicineId(illness.getMedicineId());
        userMedicine.setUserId(sessionUtils.getAuth().getId());
        // 状态初始化为未领取
        userMedicine.setStatus(UserMedicineStatus.NO.getType());
        userMedicine.setMedicineName(medicine.getName());
        this.userMedicineDao.saveUserMedicine(userMedicine);
    }

}
