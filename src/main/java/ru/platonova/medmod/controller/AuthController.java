package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.config.jwt.JwtUtils;
import ru.platonova.medmod.entity.ERole;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.entity.Role;
import ru.platonova.medmod.pojo.*;
import ru.platonova.medmod.repository.EmployeeRepo;
import ru.platonova.medmod.repository.RoleRepo;
import ru.platonova.medmod.service.EmployeeDetailsImpl;
import ru.platonova.medmod.service.EmployeeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Autowired
    EmployeeService service;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody SignInRequest signInRequest) {
        System.out.println(passwordEncoder.encode(signInRequest.getPassword()));
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
                roles));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequest request) {
        System.out.println(request.getEmpl().getPassword());
        System.out.println(request.getSignInRequest().getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getSignInRequest().getUsername(),
                        request.getSignInRequest().getPassword()));
        System.out.println(authentication.isAuthenticated());
        if(authentication.isAuthenticated()) {
            if(!request.getEmpl().getPassword().isEmpty()) {
                request.getEmpl().setPassword(passwordEncoder.encode(request.getEmpl().getPassword()));
            }
            else {
                request.getEmpl().setPassword(passwordEncoder.encode(request.getSignInRequest().getPassword()));
            }
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepo
                    .findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
            Employee user = EmployeeDTO.toEntity(request.getEmpl());
            user.setRoles(roles);
            System.out.println(employeeRepo.save(user));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            EmployeeDetailsImpl userDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
            List<String> role = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            System.out.println(jwt);
            System.out.println(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    role));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Ошибка обновления данных, неверно введен пароль"));
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

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepo
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "mod":
                        Role modRole = roleRepo
                                .findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepo
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        employeeRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
