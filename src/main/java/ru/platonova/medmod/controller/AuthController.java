package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.config.jwt.JwtUtils;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.entity.EmployeeRole;
import ru.platonova.medmod.pojo.JwtResponse;
import ru.platonova.medmod.pojo.MessageResponse;
import ru.platonova.medmod.pojo.SignInRequest;
import ru.platonova.medmod.pojo.SignUpRequest;
import ru.platonova.medmod.repository.EmployeeRepo;
import ru.platonova.medmod.repository.RoleRepo;
import ru.platonova.medmod.service.EmployeeDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        EmployeeDetailsImpl userDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.get(0)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (employeeRepo.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }


        Employee user = new Employee(signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getPersonell(),
                signupRequest.getName(), signupRequest.getSurName(), signupRequest.getPhone(),
                signupRequest.getGender(), signupRequest.getBirthDate());

        String reqRoles = signupRequest.getRole();
        EmployeeRole role;

        if (reqRoles == null) {
            EmployeeRole employeeRole = roleRepo.findByRoleName("ROLE_physician")
                    .orElseThrow(()->new UsernameNotFoundException("No such role"));
            role = employeeRole;

        } else {
                switch (reqRoles) {
                    case "physician":
                        EmployeeRole physicianRole = roleRepo.findByRoleName("ROLE_physician")
                                .orElseThrow(()->new UsernameNotFoundException("No such role"));
                        role = physicianRole;

                        break;
                    case "nurse":
                        EmployeeRole nurseRole = roleRepo.findByRoleName("ROLE_nurse")
                                .orElseThrow(()->new UsernameNotFoundException("No such role"));
                        role = nurseRole;
                        break;

                    default:
                        EmployeeRole employeeRole = roleRepo.findByRoleName("ROLE_nurse")
                                .orElseThrow(()->new UsernameNotFoundException("No such role"));
                        role = employeeRole;
                }
        }
        user.setRoleId(role);
        employeeRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
