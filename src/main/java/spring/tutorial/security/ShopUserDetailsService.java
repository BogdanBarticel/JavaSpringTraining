package spring.tutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.tutorial.model.User;
import spring.tutorial.model.UserRole;
import spring.tutorial.repository.UserRepository;
import spring.tutorial.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class ShopUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private UserRoleRepository roleRepository;

    @Autowired
    public ShopUserDetailsService (UserRepository userRepository, UserRoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            List<UserRole> userRoleList = roleRepository.findUserRoleByUserId(user.getId());
            List<String> roleList = new ArrayList<>();
            for(UserRole role : userRoleList){
                roleList.add(role.getRole());
            }
            return new ShopUserDetails(user, roleList);
        }

    }

}
