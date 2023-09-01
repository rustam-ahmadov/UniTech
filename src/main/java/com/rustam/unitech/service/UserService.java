package com.rustam.unitech.service;

import com.rustam.unitech.entity.User;
import com.rustam.unitech.enums.ResponseDetails;
import com.rustam.unitech.exception.UniTechException;
import com.rustam.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.
                findByEmail(username)
                .orElseThrow(() -> new UniTechException(ResponseDetails.USER_NOT_FOUND));
    }

    public boolean isExist(String username) {
        return repository.findByEmail(username).isPresent();
    }

    public User create(User user) {
        boolean isUserExist = isExist(user.getEmail());
        if (isUserExist)
            throw new UniTechException(ResponseDetails.USERNAME_EXIST);

        return repository.save(user);
    }
}
