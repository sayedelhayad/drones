package com.test.drones.job;

import com.test.drones.service.DronesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.rmi.UnexpectedException;
import java.util.Date;

/**
 * Defines cron jobs
 *
 * @author sayed_abdelhafez
 */
@Component
public class CheckBatteriesJob {
    // Define a logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckBatteriesJob.class);

    @Autowired
    private DronesService dronesService;

    /**
     * This cron job to run at the minute 30 of each hour and checks drones batteries
     *
     * @throws UnexpectedException
     */
    @Scheduled(cron = "0 0,28 * * * ?")
    public void checkBatteries() throws UnexpectedException {
        LOGGER.info("checkBatteries cronjob started: " + new Date());
        dronesService.checkBatteries();
        LOGGER.info("checkBatteries cronjob finished: " + new Date());
    }
}