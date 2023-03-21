package be.ucll.gip5.service;

import be.ucll.gip5.dto.UserDTO;
import be.ucll.gip5.entity.User;
import be.ucll.gip5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserDTO dto) throws ClassNotFoundException {
        User user = dtoConverter.UserDTOToEntity(dto);

        if (user.getUsername().trim().length() == 0 || user.getUsername().equals("")) {
            throw new IllegalArgumentException("Username is empty.");
        } else if (user.getEmail().trim().length() == 0 || user.getEmail().equals("")) {
            throw new IllegalArgumentException("Email is empty.");
        }else if (user.getPassword().trim().length() == 0 || user.getPassword().equals("")){
            throw new IllegalArgumentException("Password is empty.");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        if (user.getRoles() == null ||
                !user.getRoles().equals("ADMIN") ||
                !user.getRoles().equals("USER"))
            user.setRoles("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
    public void editUser(UserDTO dto, Long id) throws Exception{
        User user = userRepository.findAllByUserId(id);

        if (user == null) throw new ClassNotFoundException("User not found.");

        user = dtoConverter.UserEntityToEntity(user,dto);
        userRepository.save(user);
    }
    public void deleteUserById(Long id) throws Exception{
        User user = userRepository.findAllByUserId(id);

        if (user == null) throw new ClassNotFoundException("User not found.");

        userRepository.delete(user);

    }
    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User p: userList){
            userDTOS.add(dtoConverter.UserEntityToDTO(p));
        }
        return userDTOS;
    }
    public UserDTO getUserById(Long id) throws Exception{
        User user = userRepository.findAllByUserId(id);
        if (user != null){
            return dtoConverter.UserEntityToDTO(user);
        }else {
            throw new ClassNotFoundException("Person not found");
        }
    }

    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);

        if (user == null) throw new ClassNotFoundException("User not found.");

        return dtoConverter.UserEntityToDTO(user);
    }

    public void userLogin (String email, String pass) throws ClassNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if (user == null) throw new ClassNotFoundException("User not found.");

        if (!user.getEmail().equals(email) || !user.getPassword().equals(pass)) {
            throw new IllegalArgumentException("Email or password are incorrect.");
        }

    }
}
