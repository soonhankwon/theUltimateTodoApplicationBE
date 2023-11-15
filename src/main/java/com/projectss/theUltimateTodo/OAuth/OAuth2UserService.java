//package com.projectss.theUltimateTodo.OAuth;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Map;
//
////https://kim-jong-hyun.tistory.com/150
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class OAuth2UserService extends DefaultOAuth2UserService {
//    private final UserRepository userRepository;
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        //이 때 이미 access token 이 정상적으로 발급되어있음
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        // Role generate
//        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
//
//        // nameAttributeKey
//        String userNameAttributeName = userRequest.getClientRegistration()
//                .getProviderDetails()
//                .getUserInfoEndpoint()
//                .getUserNameAttributeName();
//
//        log.info("auth : {}, OAuth2User attr {}, auth : {}  userName : {}",authorities,oAuth2User.getAttributes(),oAuth2User.getAuthorities(),userNameAttributeName);
//
////        DB 저장 로직
//
//
//
//        if(!userRepository.existsById(oAuth2User.getAttributes().get("id"))){
//
//        Map<String, Object> oauth2Attributes = oAuth2User.getAttributes();
//        String id = oauth2Attributes.get("id").toString();
//            Map<String, Object> properties = (Map<String, Object>) oauth2Attributes.get("properties");
//            String nickname = properties.get("nickname").toString();
//            String profileImageUrl = properties.get("profile_image").toString();
//            String thumbnailImageUrl = properties.get("thumbnail_image").toString();
//
//        User user = User.builder()
//                .id(id)
//                .nickname(nickname)
//                .profile_image(profileImageUrl)
//                .thumbnail_image(thumbnailImageUrl)
//                .role("ROLE_USER")
//                .build();
//            userRepository.save(user);
//        }
//
//        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
//    }
//}
