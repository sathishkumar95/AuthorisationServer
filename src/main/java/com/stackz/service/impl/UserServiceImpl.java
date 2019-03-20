package com.stackz.service.impl;

import com.stackz.model.Role;
import com.stackz.model.User;
import com.stackz.model.UserDao;
import com.stackz.repository.RoleRepository;
import com.stackz.repository.UserRepository;
import com.stackz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleRepository;

	public UserDao loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new UserDao(user.getUsername(), user.getPassword(), getAuthority(user),getPermissions(user));
	}

	private List<SimpleGrantedAuthority> getAuthority(User user) {
//		Role role = roleRepository.findByUsername(user.getUsername());
//		return role.getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toList());
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

	private List<String> getPermissions(User user) {
		Role role = roleRepository.findByUsername(user.getUsername());
		return role.getPermissions();
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public void delete(String id) {
		userDao.deleteById(id);
	}

    public User save(User user) {
        return userDao.save(user);
    }
}
