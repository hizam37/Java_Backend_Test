package org.example.controller;

import org.example.model.Application;
import org.example.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(path = "/applications")
    public List<Application> viewApplications() {

        return applicationService.viewApplications();

    }

    @GetMapping(path = "/applications/Asc")
    public List<Application> viewApplicationsAsc() {
        return applicationService.viewApplicationsAsc();
    }

    @GetMapping(path = "/applications/Dsc")
    public List<Application> viewApplicationsDsc() {
        return applicationService.viewApplicationsDsc();
    }

    @GetMapping(path = "/applications/old/{digit}")
    public List<Application> oldApplications(@PathVariable("digit") int digit) {
        return applicationService.oldApplications(digit);
    }

    @GetMapping(path = "/applications/resent/{digit}")
    public List<Application> topApplications(@PathVariable("digit") int digit) {
        return applicationService.recentApplications(digit);
    }


    @PostMapping(path = "/applications")
    public ResponseEntity<Application> sendApplication(@RequestBody Application application) {
        applicationService.send(application);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable("id") Long applicationId) {
        Optional<Application> getApplicationByid = applicationService.viewApplicationById(applicationId);

        return getApplicationByid.map(value -> ResponseEntity.ok((Application) value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path = "/applications/status/{status}")
    public List<Application> getFilteredApplicationsByStatus(@PathVariable("status") String status) {
       return applicationService.viewFilteredApplicationsByStatus(status);
    }
}
