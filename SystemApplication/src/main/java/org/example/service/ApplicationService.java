package org.example.service;

import org.example.model.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    void send(Application application);

    List<Application> viewApplicationsAsc();

    List<Application> viewApplications();

    List<Application> viewApplicationsDsc();

    List<Application> recentApplications(int digit);

    List<Application> oldApplications(int digit);

    Optional<Application> viewApplicationById(Long applicationid);

    List<Application> viewFilteredApplicationsByStatus(String status);
}
