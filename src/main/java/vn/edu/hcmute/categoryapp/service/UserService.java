package vn.edu.hcmute.categoryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.edu.hcmute.categoryapp.entity.User;
import vn.edu.hcmute.categoryapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy User có id = " + id));
    }
}