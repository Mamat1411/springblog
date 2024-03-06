package com.springexercise.springblog.users.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springexercise.springblog.users.dtos.responses.UserResponseDto;
import com.springexercise.springblog.users.entities.User;
import com.springexercise.springblog.users.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    //Get API Key from application.properties
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    UserService userService;

    //API Get All Users using Header (API Key)
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(@RequestHeader("api-key") String token) {
        List<UserResponseDto> response = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        Map<String, Object> resultMap = new HashMap<>();

        if (token.equals(apiKey)) {
            try {
                for (int i = 0; i < users.size(); i++) {
                    UserResponseDto userResponseDto = new UserResponseDto();
                    userResponseDto.setName(users.get(i).getName());
                    userResponseDto.setEmail(users.get(i).getEmail());

                    response.add(userResponseDto);
                }
                resultMap.put("status", "200");
                resultMap.put("message", "Data Successfully Acquired");
                resultMap.put("data", response);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            } catch (Exception e) {
                resultMap.put("status", "500");
                resultMap.put("message", "Failed Acquiring Data");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resultMap.put("status", "401");
            resultMap.put("message", "Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
