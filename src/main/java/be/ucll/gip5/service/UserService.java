package be.ucll.gip5.service;

import be.ucll.gip5.dto.UserDTO;
import be.ucll.gip5.entity.User;
import be.ucll.gip5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
            throw new ClassNotFoundException("Username is empty.");
        } else if (user.getEmail().trim().length() == 0 || user.getEmail().equals("")) {
            throw new ClassNotFoundException("Email is empty.");
        }else if (user.getPassword().trim().length() == 0 || user.getPassword().equals("")){
            throw new ClassNotFoundException("Password is empty.");
        } else if (user.getEmail().equals(userRepository.findByEmail(user.getEmail()))) {
            throw new ClassNotFoundException("Email already exists.");
        }

        if (user.getRoles().isEmpty()) user.setRoles("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
    public void editUser(UserDTO dto, Long id) throws Exception{
        User user = userRepository.findAllByUserId(id);
        if (user != null){
            user = dtoConverter.UserEntityToEntity(user,dto);
            userRepository.save(user);
        }else {
            throw new ClassNotFoundException("Person not found");
        }
    }
    public void deleteUserById(Long id) throws Exception{
        User user = userRepository.findAllByUserId(id);
        if (user != null){
            userRepository.delete(user);
        }else {
            throw new ClassNotFoundException("Person not found");
        }
    }
//    public List<UserDTO> getAllUsers(){
//        List<User> userList = userRepository.findAll();
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for(User p: userList){
//            userDTOS.add(dtoConverter.UserEntityToDTO(p));
//        }
//        return userDTOS;
//    }
//    public UserDTO getUserById(Long id) throws Exception{
//        User user = userRepository.findAllByUserId(id);
//        if (user != null){
//            return dtoConverter.UserEntityToDTO(user);
//        }else {
//            throw new ClassNotFoundException("Person not found");
//        }
//    }
}
