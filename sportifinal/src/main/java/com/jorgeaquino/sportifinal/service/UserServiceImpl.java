package com.jorgeaquino.sportifinal.service;

import com.jorgeaquino.sportifinal.model.User;
import com.jorgeaquino.sportifinal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El email '" + user.getEmail() + "' ya está en uso");
        }
        if (userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el nombre '" + user.getFirstName() + " " + user.getLastName() + "'");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        Optional<User> userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(id)) {
            throw new RuntimeException("El email '" + user.getEmail() + "' ya está en uso por otro usuario");
        }

        Optional<User> userWithSameName = userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName());
        if (userWithSameName.isPresent() && !userWithSameName.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe un usuario con el nombre '" + user.getFirstName() + " " + user.getLastName() + "'");
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
                return userRepository.save(existinUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
