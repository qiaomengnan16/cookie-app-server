package com.app.server.dao;

import com.app.server.model.Illness;
import com.app.server.model.Medicine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MedicineDao {

    private final Map<String, Medicine> MEDICINE_TABLE;

    public MedicineDao() {
        MEDICINE_TABLE = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            Medicine medicine = new Medicine();
            medicine.setId(i + "");
            medicine.setName("药品: " + 0);
            MEDICINE_TABLE.put(medicine.getId(), medicine);
        }
    }

    public void saveMedicine(Medicine medicine) {
        MEDICINE_TABLE.put(medicine.getId(), medicine);
    }

    public Medicine getMedicine(String id) {
        return MEDICINE_TABLE.get(id);
    }

    public List<Medicine> list() {
        return new ArrayList<>(MEDICINE_TABLE.values());
    }

}
