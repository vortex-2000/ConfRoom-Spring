package com.confRoom.jwtUtils;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

import com.confRoom.repository.UserRepository; 
@Service
public class JwtUserDetailsService implements UserDetailsService { 
	@Autowired	
	private UserRepository userRepo; 
   @Override 
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   
	   Optional<com.confRoom.model.User> userOp=userRepo.findByUserName(username);
	   
	   
      if (userOp.get()!=null) { 
    	  com.confRoom.model.User user=userOp.get();
    	  
    	  
    	  Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
          for(String role: user.getRoles()){
              authorities.add(new SimpleGrantedAuthority(role));
              //logger.debug("role" + role + " role.getRole()" + (role.getRole()));
          }
    	  
         return new User(user.getUserName(), 
            user.getPassword(),
            authorities); 
      } else { 
         throw new UsernameNotFoundException("User not found with username: " + username); 
      } 
   } 
}