package ProjectGym.GymAiBackend.service.UserServiceImpl;



import ProjectGym.GymAiBackend.models.dto.UserDto;
import ProjectGym.GymAiBackend.models.entity.UserEntity;
import ProjectGym.GymAiBackend.repository.UserRepository;
import ProjectGym.GymAiBackend.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByusername(username);
    }

    //UTENTE HA DTO MA SI MAPPA NEL DB COME ENTITY
    @Override
    public UserEntity save(UserDto userDto) {
        UserEntity mappedUserEntity = new UserEntity(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getFullname());
        return userRepository.save(mappedUserEntity);
    }
}