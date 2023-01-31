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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
public class UserServiceDB implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User>User = userRepository.findByEmail(username);
    if(User.isEmpty())
      throw new UsernameNotFoundException("Exception");
    User foundUser = User.get();
    return new org.springframework.security.core.userdetails.User(foundUser.getEmail(),
                foundUser.getPassword(),
                buildUserAuthority(foundUser.getRoles()));
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
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userDto.setRoles(Arrays.asList(defaultRole));
    userRepository.save(userDto);
    return true;
  }
  public boolean emailExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
  public List<GrantedAuthority> buildUserAuthority(List<Role> roles){
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for(Role role : roles){
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return grantedAuthorities;
  }
}
