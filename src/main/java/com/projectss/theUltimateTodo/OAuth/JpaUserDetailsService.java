package com.projectss.theUltimateTodo.OAuth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(userId)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("userId not found: " + userId));
    }
}


// Security 에서 user detail
// 실제로 어떻게 사용이 되는지, 다른곳에서 쓰이는 곳은 있는지
// Security context 에 저장이 되는지

