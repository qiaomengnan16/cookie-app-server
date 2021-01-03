package com.app.server.dao;

import com.app.server.model.Illness;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class IllnessDao {

    private final Map<String, Illness> ILLNESS_TABLE;

    public IllnessDao() {
        ILLNESS_TABLE = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            Illness illness = new Illness();
            illness.setId( i + "");
            illness.setName("疾病: " + i);
            illness.setMedicineId(illness.getId());
            ILLNESS_TABLE.put(illness.getId(), illness);
        }
    }

    public void saveIllness(Illness illness) {
        ILLNESS_TABLE.put(illness.getId(), illness);
    }

    public Illness getIllness(String id) {
        return ILLNESS_TABLE.get(id);
    }

    public List<Illness> list() {
        return new ArrayList<>(ILLNESS_TABLE.values());
    }

}
