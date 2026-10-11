package com.springboot.placement.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.enums.JobDriveStatus;
import com.springboot.placement.repository.JobDriveRepository;

@Component
public class JobDriveScheduler {

	private static final Logger log = LoggerFactory.getLogger(JobDriveScheduler.class);

	@Autowired
	JobDriveRepository jobDriveRepository;
	
	 @Scheduled(cron = "0 0 0 * * *")    // every midnight 12AM application deadline status check
//	 @Scheduled(fixedRate = 30000)
	    public void closeExpiredJobDrives() {
	        log.info("Job Drive Scheduler started...");

	        List<JobDrive> openDrives = jobDriveRepository.findByStatus(JobDriveStatus.OPEN);
	        LocalDate today = LocalDate.now();
	        for (JobDrive drive : openDrives) {
	            if (drive.getApplicationDeadline() != null && drive.getApplicationDeadline().isBefore(today)) {
	                drive.setStatus(JobDriveStatus.CLOSED);
	                jobDriveRepository.save(drive);
	                log.info("Job Drive ID {} closed because deadline expired", drive.getJobDriveId());
	            }
	        }
	        log.info("Job Drive Scheduler completed.");
	    }
	}
	

