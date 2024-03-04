package org.example.service.serviceImp;
import org.example.model.Application;
import org.example.repository.ApplicationRepository;
import org.example.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ApplicationServiceImp implements ApplicationService {

    @Autowired
    public ApplicationRepository applicationRepository;

    @Override
    public void send(Application application) {

        applicationRepository.save(application);

    }

    @Override
    public List<Application> viewApplicationsAsc() {

        List<Application> applicationListAsc = (List<Application>) applicationRepository.findAll();

        applicationListAsc.sort((o1, o2) -> o1.getCreationDate().compareTo(o2.getCreationDate()));

        return applicationListAsc;
    }


    @Override
    public List<Application> viewApplications() {
        List<Application> getApplications = (List<Application>) applicationRepository.findAll();
        return Collections.synchronizedList(getApplications);

    }

    @Override
    public List<Application> viewApplicationsDsc() {

        List<Application> applicationListDsc = (List<Application>) applicationRepository.findAll();

        applicationListDsc.sort(Comparator.comparing(Application::getCreationDate).reversed());

        return applicationListDsc;
    }

    @Override
    public List<Application> recentApplications(int digit) {

        List<Application> applicationListDsc = (List<Application>) applicationRepository.findAll();

        applicationListDsc.sort(Comparator.comparing(Application::getCreationDate).reversed());

        return applicationListDsc.subList(0, digit);
    }

    @Override
    public List<Application> oldApplications(int digit) {

        List<Application> applicationListAsc = (List<Application>) applicationRepository.findAll();

        applicationListAsc.sort((o1, o2) -> o1.getCreationDate().compareTo(o2.getCreationDate()));

        return applicationListAsc.subList(0, digit);
    }


    @Override
    public Optional<Application> viewApplicationById(Long applicationid) {

        return applicationRepository.findById(applicationid);

    }

    @Override
    public List<Application> viewFilteredApplicationsByStatus(String status) {

        List<Application> allApplications = (List<Application>) applicationRepository.findAll();

        return allApplications.stream()
                .filter(application -> application.getStatus().toString().contains(status))
                .collect(Collectors.toList());
    }



}
