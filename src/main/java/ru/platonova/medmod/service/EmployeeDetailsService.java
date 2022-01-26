package ru.platonova.medmod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.repository.EmployeeRepo;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("There is no user with username:" + username));
        return EmployeeDetailsImpl.build(employee);
    }
}
