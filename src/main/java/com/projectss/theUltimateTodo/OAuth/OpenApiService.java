package com.projectss.theUltimateTodo.OAuth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectss.theUltimateTodo.memo.service.MemoStoreService;
import com.projectss.theUltimateTodo.todo.service.TodoStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenApiService {


    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final MemoStoreService memoStoreService;
    private final TodoStoreService todoStoreService;

    @Value("${kakao.rest-key}")
    private String kakaoKey;
    @Value("${kakao.client-secret}")
    private String clientSecret;



    public String getToken(String code){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();

        body.add("grant_type","authorization_code");
        body.add("client_id", kakaoKey);
        body.add("redirect_uri","http://localhost:5173/openApi/kakao");
        body.add("code",code);
        body.add("client_secret",clientSecret);


        LoginResponseDto loginResponseDto = restTemplate.postForObject(
                "https://kauth.kakao.com/oauth/token",
                new HttpEntity<>(body,headers),
                LoginResponseDto.class);
        log.info(loginResponseDto.toString());

        String token = getUserInfo(loginResponseDto.access_token(),restTemplate);
        return token;
    }
    public String getUserInfo(String token,RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("Authorization", "Bearer "+token);

        ResponseEntity<String> response =
                restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
                        HttpMethod.GET,
                        new HttpEntity<>(null, headers),
                        String.class);

        String body = response.getBody();
        log.info(body.toString());
        String myToken = "";

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);
            String id = jsonNode.get("id").asText();
            String nickname = jsonNode.get("properties").get("nickname").asText();
            String profileImage = jsonNode.get("properties").get("profile_image").asText();
            String thumbnailImage = jsonNode.get("properties").get("thumbnail_image").asText();
            String email = jsonNode.get("kakao_account").get("email").asText();

            saveUser(id,nickname,profileImage,thumbnailImage,email);
            myToken = tokenService.generateToken(id,nickname,profileImage,thumbnailImage,email);
        }catch (Exception e){
            e.printStackTrace();
        }


        return myToken;

    }

    public void saveUser(String id, String nickname, String profileImage, String thumbnailImage,String email){
        User newUser = User.builder()
                .id(id)
                .nickname(nickname)
                .profile_image(profileImage)
                .thumbnail_image(thumbnailImage)
                .snsId(id)
                .snsType("kakao")
                .role("ROLE_USER")
                .userEmail(email)
                .build();

        userRepository.save(newUser);
        //유저 DB 저장시 유저의 메모스토어 생성
        memoStoreService.createMemoStoreByUser(email);
        todoStoreService.createTodoStoreByUser(email);
    }
//
//
}