package com.projectss.theUltimateTodo.chatbot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectss.theUltimateTodo.user.domain.User;
import com.projectss.theUltimateTodo.user.repository.UserRepository;
import com.projectss.theUltimateTodo.memo.domain.Directory;
import com.projectss.theUltimateTodo.memo.domain.Memo;
import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.dto.DirectoryRequest;
import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
import com.projectss.theUltimateTodo.memo.repository.DirectoryRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatBotService {

    private final UserRepository userRepository;
    private final MemoStoreRepository memoStoreRepository;
    private final DirectoryRepository directoryRepository;
    private final MemoRepository memoRepository;

    public String register(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            // intent의 name 값 가져오기
            String intentName = jsonNode.path("intent").path("name").asText();
            // app_user_id 값 가져오기
            String profile = jsonNode.path("action").path("params").path("profile").asText();
            JsonNode profileNode = objectMapper.readTree(profile);
            String appUserId = profileNode.path("app_user_id").asText();
            log.info("profile : {}, appUserId : {}", profile, appUserId);
            // openId 값 가져오기
            String openId = jsonNode.path("userRequest").path("user").path("id").asText();

            log.info("intentName : {}, appUserId : {}, openId {}", intentName, appUserId, openId);

            // intent의 name이 "인증 블록"인지 확인
            if ("인증 블록".equals(intentName)) {

                if (!userRepository.existsById(appUserId)) {
                    log.info("회원가입이 되지 않은 회원입니다. ");
                    return "{\n" +
                            "    \"version\": \"2.0\",\n" +
                            "    \"template\": {\n" +
                            "        \"outputs\": [\n" +
                            "            {\n" +
                            "                \"simpleText\": {\n" +
                            "                    \"text\": \"가입되지 않은 회원입니다. 사이트에서 먼저 가입을 진행해주세요.\"\n" +
                            "                }\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}";
                }

                if (userRepository.existsUserByOpenId(openId)) {
                    log.info("이미 가입된 회원");
                    return "{\n" +
                            "    \"version\": \"2.0\",\n" +
                            "    \"template\": {\n" +
                            "        \"outputs\": [\n" +
                            "            {\n" +
                            "                \"simpleText\": {\n" +
                            "                    \"text\": \"이미 가입된 회원입니다. 이곳에 쓰는 글이 메모에 저장됩니다.\"\n" +
                            "                }\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}";
                }
                User user = userRepository.findById(appUserId)
                        .orElseThrow(() -> new RuntimeException("처리 중 오류가 발생하였습니다."));
                user.setOpenId(openId);
                userRepository.save(user);
            }
            log.info("success logic");
            return "{\n" +
                    "    \"version\": \"2.0\",\n" +
                    "    \"template\": {\n" +
                    "        \"outputs\": [\n" +
                    "            {\n" +
                    "                \"simpleText\": {\n" +
                    "                    \"text\": \"회원가입이 완료되었습니다. 앞으로 이곳에 쓰는 글이 메모에 저장됩니다.\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
            // 처리가 완료되면 응답을 반환

        } catch (IOException e) {
            log.info("catch 구문 : {}", e.toString());
            e.printStackTrace();
            // 처리 중 오류가 발생한 경우 예외 처리
            return "{\n" +
                    "    \"version\": \"2.0\",\n" +
                    "    \"template\": {\n" +
                    "        \"outputs\": [\n" +
                    "            {\n" +
                    "                \"simpleText\": {\n" +
                    "                    \"text\": \"처리 중 오류가 발생했습니다.\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
        }

    }

    public String fallback(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            // openId 값 가져오기
            String openId = jsonNode.path("userRequest").path("user").path("id").asText();
            String utterance = jsonNode.path("userRequest").path("utterance").asText();
            log.info("openID : {}, utterance : {} ", openId, utterance);
            if (!userRepository.existsUserByOpenId(openId)) {
                return "{\n" +
                        "\"version\": \"2.0\",\n" +
                        "  \"template\": {\n" +
                        "    \"outputs\": [\n" +
                        "      {\n" +
                        "        \"simpleText\": {\n" +
                        "          \"text\": \"서비스를 이용하기 위해서는 회원 가입이 필요합니다. 아래의 버튼을 클릭하거나, 회원가입이라고 입력해주세요\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"quickReplies\": [\n" +
                        "      {\n" +
                        "        \"messageText\": \"회원가입\",\n" +
                        "        \"action\": \"message\",\n" +
                        "        \"label\": \"회원가입\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}";
            } else {
                User user = userRepository.findUserByOpenId(openId)
                        .orElseThrow(() -> new IllegalStateException("no user by open id"));

                MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(user.getUserEmail())
                        .orElseThrow(() -> new IllegalStateException("no memo store by user email"));

                Optional<Directory> optionalMobileDirectory = memoStore.getDirectories()
                        .stream()
                        .filter(i -> i.getName().equals("mobile-directory"))
                        .findFirst();

                Memo memo = new Memo(new MemoRequest(LocalDateTime.now() + " memo", utterance));
                memoRepository.save(memo);
                if (optionalMobileDirectory.isEmpty()) {
                    Directory mobileDirectory = new Directory(new DirectoryRequest("mobile-directory"));
                    directoryRepository.save(mobileDirectory);
                    memoStore.saveDirectory(mobileDirectory);

                    mobileDirectory.saveMemo(memo);
                    memoStoreRepository.save(memoStore);
                } else {
                    Directory mobileDirectory = optionalMobileDirectory.get();
                    mobileDirectory.saveMemo(memo);
                    directoryRepository.save(mobileDirectory);
                }

                return "{\n" +
                        "    \"version\": \"2.0\",\n" +
                        "    \"template\": {\n" +
                        "        \"outputs\": [\n" +
                        "            {\n" +
                        "                \"simpleText\": {\n" +
                        "                    \"text\": \" 메모에 저장되었습니다. \"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}";
            }

        } catch (IOException e) {
            log.info("catch 구문 : {}", e.toString());
            e.printStackTrace();
            // 처리 중 오류가 발생한 경우 예외 처리
            return "{\n" +
                    "    \"version\": \"2.0\",\n" +
                    "    \"template\": {\n" +
                    "        \"outputs\": [\n" +
                    "            {\n" +
                    "                \"simpleText\": {\n" +
                    "                    \"text\": \"처리 중 오류가 발생했습니다.\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
        }

    }

}
