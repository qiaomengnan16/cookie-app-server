package com.app.server.dao;

import com.app.server.model.Illness;
import com.app.server.model.Medicine;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MedicineDao {

    private final Map<String, Medicine> MEDICINE_TABLE;

    public MedicineDao() {
        MEDICINE_TABLE = new ConcurrentHashMap<>();
    }

    public void saveMedicine(Medicine medicine) {
        MEDICINE_TABLE.put(medicine.getId(), medicine);
    }

    public Medicine getMedicine(Medicine medicine) {
        return MEDICINE_TABLE.get(medicine.getId());
    }

}
