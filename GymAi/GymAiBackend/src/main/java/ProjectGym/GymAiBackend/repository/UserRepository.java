package ProjectGym.GymAiBackend.repository;


import ProjectGym.GymAiBackend.models.dto.UserDto;
import ProjectGym.GymAiBackend.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByusername(String username);
    UserEntity save(UserDto userDto);
}
