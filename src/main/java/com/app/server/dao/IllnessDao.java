package com.app.server.dao;

import com.app.server.model.Illness;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class IllnessDao {

    private final Map<String, Illness> ILLNESS_TABLE;

    public IllnessDao() {
        ILLNESS_TABLE = new ConcurrentHashMap<>();
    }

    public void saveIllness(Illness illness) {
        ILLNESS_TABLE.put(illness.getId(), illness);
    }

    public Illness getIllness(Illness illness) {
        return ILLNESS_TABLE.get(illness.getId());
    }

}
