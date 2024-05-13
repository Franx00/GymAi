package ProjectGym.GymAiBackend.configuration;

import ProjectGym.GymAiBackend.models.entity.UserEntity;
import ProjectGym.GymAiBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //CHIAMA I DETTAGLI DELL'UTENTE IN BASE AL NOME
        UserEntity user = userRepository.findByusername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username or password not found");
        }

        return new CustomUserDetails(user.getUsername(),user.getPassword(),authorities(),user.getFullname());

    }
}
