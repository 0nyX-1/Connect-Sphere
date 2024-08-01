package org.management.connectsphere.Mapper;

import org.management.connectsphere.entites.User;
import org.management.connectsphere.forms.UserForm;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(UserForm userForm){
        return User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .enabled(true)
                .build();
    }
}
