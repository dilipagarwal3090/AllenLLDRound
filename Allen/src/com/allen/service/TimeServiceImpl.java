package com.allen.service;

import com.allen.interfaces.TimeService;

import java.util.Date;

public class TimeServiceImpl implements TimeService {

    @Override
    public boolean isDealExpired(Date endTime) {
        Date currentTime = new Date();
        return currentTime.after(endTime);
    }


}
