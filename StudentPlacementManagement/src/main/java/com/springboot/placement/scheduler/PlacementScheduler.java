package com.springboot.placement.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.placement.enums.ApplicationStatus;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.CompanyRepository;
import com.springboot.placement.repository.JobDriveRepository;
import com.springboot.placement.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlacementScheduler {

	private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobDriveRepository jobDriveRepository;
    private final ApplicationRepository applicationRepository;
    
 // Placement report every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void placementReport() {

        long totalStudents = studentRepository.count();
        long totalCompanies = companyRepository.count();
        long totalJobDrives = jobDriveRepository.count();
        long totalApplications = applicationRepository.count();

        // Application status statistics
        long selectedApplications = applicationRepository.countByStatus(ApplicationStatus.SELECTED);
        long rejectedApplications = applicationRepository.countByStatus(ApplicationStatus.REJECTED);
        long pendingApplications = applicationRepository.countByStatus(ApplicationStatus.APPLIED);


        System.out.println();

        System.out.println("=================================================");
        System.out.println("              PLACEMENT REPORT");
        System.out.println("=================================================");
        System.out.println("Time                  : " + LocalDateTime.now());
        System.out.println("Total Students        : " + totalStudents);
        System.out.println("Total Companies       : " + totalCompanies);
        System.out.println("Total Job Drives      : " + totalJobDrives);
        System.out.println("Total Applications    : " + totalApplications);
        System.out.println("-----------------------------------------------");
        System.out.println("Selected Applications : " + selectedApplications);
        System.out.println("Rejected Applications : " + rejectedApplications);
        System.out.println("Pending Applications  : " + pendingApplications);
        System.out.println("-----------------------------------------------");
        System.out.println("Report Status         : Completed");
        System.out.println("=================================================");
    }
}