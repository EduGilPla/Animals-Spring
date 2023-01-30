package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.models.Role;
import cifpcm.es.AnimalsApp.models.User;
import cifpcm.es.AnimalsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
public class UserServiceDB implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(username);
        if(foundUser == null)
            return null;
        return new org.springframework.security.core.userdetails.User(foundUser.getName(),
                foundUser.getPassword(),
                getAuthorities(foundUser.getRoles()));
    }
    public boolean registerUser(User userDto){
        if(emailExists(userDto.getEmail())){
            return false;
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        return true;
    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
