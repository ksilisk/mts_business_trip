package com.hackathon.mts.tripresourceserver.service;

import com.hackathon.mts.dto.resource.ApplicationDTO;
import com.hackathon.mts.tripresourceserver.client.EmployeeDirectoryClient;
import com.hackathon.mts.tripresourceserver.entity.Application;
import com.hackathon.mts.tripresourceserver.repository.ApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final EmployeeDirectoryClient employeeDirectoryClient;

    @Transactional(readOnly = true)
    public List<ApplicationDTO> getAllApplicationsForUser(String username) {
        List<Application> applications = applicationRepository.getApplicationByUsername(username).orElseThrow();
        return applications.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ApplicationDTO toDTO(Application application){
        return modelMapper.map(application, ApplicationDTO.class);
    }

    private Application toApplication(ApplicationDTO applicationDTO){
        Application application = modelMapper.map(applicationDTO, Application.class);
        application.setMasterUsername(employeeDirectoryClient.getEmployee(applicationDTO.getUsername()).getMasterUsername());
        return application;
    }

    public Application saveApplication(ApplicationDTO applicationDTO){
        return applicationRepository.save(toApplication(applicationDTO));
    }

    @Transactional(readOnly = true)
    public List<ApplicationDTO> getAllApplicationsByStatus(String status){
        List<Application> applicationList = applicationRepository.getApplicationByStatus(status).orElseThrow();
        return applicationList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApplicationDTO> getAllApplicationsByMasterUsername(String masterUsername){
        List<Application> applicationList = applicationRepository.getApplicationByStatus(masterUsername).orElseThrow();
        return applicationList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ApplicationDTO getApplicationById(long id){
        return toDTO(applicationRepository.getApplicationById(id).orElseThrow(() -> new EntityNotFoundException("entity not found")));
    }

    public Application getApplicationEntity(long id){
        return applicationRepository.getApplicationById(id).orElseThrow(() -> new EntityNotFoundException("entity not found"));
    }

    public Application updateApplication(Application application){
        return applicationRepository.save(application);
    }

    public List<ApplicationDTO> getApplicationsByStatusAnsUsername(String status, String username){
        List<Application> applicationList = applicationRepository.getApplicationsByStatusAndUsername(status, username)
                .orElseThrow(() -> new EntityNotFoundException("applications bot found"));
        return applicationList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
