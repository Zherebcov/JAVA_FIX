package ru.ivmiit.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.service.forms.UserForm;
import ru.ivmiit.service.models.Role;
import ru.ivmiit.service.models.State;
import ru.ivmiit.service.models.User;
import ru.ivmiit.service.repositories.UsersRepository;

import java.util.List;

/**
 * 25.04.2018
 * UsersServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .hashPassword(hashPassword)
                .login(userForm.getLogin())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        usersRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findOne(Long userId) {
        return usersRepository.findOne(userId);
    }
}
