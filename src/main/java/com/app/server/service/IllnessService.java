package com.app.server.service;

import com.app.server.dao.IllnessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IllnessService {

    @Autowired
    private IllnessDao illnessDao;

    // 看病
    public void lookIllness() {

    }

}
