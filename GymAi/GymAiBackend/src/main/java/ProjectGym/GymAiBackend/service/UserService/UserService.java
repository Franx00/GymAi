package ProjectGym.GymAiBackend.service.UserService;


import ProjectGym.GymAiBackend.models.dto.UserDto;
import ProjectGym.GymAiBackend.models.entity.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);
    UserEntity save(UserDto userDto);

}