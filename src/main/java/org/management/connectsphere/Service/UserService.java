package org.management.connectsphere.Service;

import lombok.RequiredArgsConstructor;
import org.management.connectsphere.Repository.UserRepo;
import org.management.connectsphere.entites.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    public void storeRegistration(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
