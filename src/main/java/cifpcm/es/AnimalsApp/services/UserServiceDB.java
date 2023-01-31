package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.models.Role;
import cifpcm.es.AnimalsApp.models.User;
import cifpcm.es.AnimalsApp.repositories.RoleRepository;
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
  @Autowired
  private RoleRepository roleRepository;
  private final BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User>User = userRepository.findByEmail(username);
    if(User.isEmpty())
      return null;
    User foundUser = User.get();
    return new org.springframework.security.core.userdetails.User(foundUser.getName(),
        foundUser.getPassword(),
        getAuthorities(foundUser.getRoles()));
  }
  public boolean registerUser(User userDto){
    if(emailExists(userDto.getEmail())){
      return false;
    }
    Optional<Role> roleQuery = roleRepository.findByName("ROLE_USER");
    Role defaultRole;
    if(roleQuery.isEmpty())
      defaultRole = new Role("ROLE_USER");
    else
      defaultRole = roleQuery.get();
    userDto.setPassword(Encoder.encode(userDto.getPassword()));
    userDto.setRoles(Arrays.asList(defaultRole));
    userRepository.save(userDto);
    return true;
  }
  private boolean emailExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
  private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role: roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }
}
