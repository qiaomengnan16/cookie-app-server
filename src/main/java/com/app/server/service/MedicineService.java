package com.app.server.service;

import com.app.server.dao.MedicineDao;
import com.app.server.model.Medicine;
import com.app.server.util.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicineService {

    @Resource
    private MedicineDao medicineDao;

    // 拿药
    public Medicine get(String id) {
        return this.medicineDao.getMedicine(id);
    }

    public List<Medicine> list() {
        return this.medicineDao.list();
    }

}
