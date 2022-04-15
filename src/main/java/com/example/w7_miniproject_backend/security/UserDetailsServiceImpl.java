package com.example.w7_miniproject_backend.security;

import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + nickname));

        return new UserDetailsImpl(user);
    }
}