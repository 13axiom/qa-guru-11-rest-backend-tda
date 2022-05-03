package qa.guru.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qa.guru.restbackend.domain.LoginInfo;
import qa.guru.restbackend.domain.UserInfo;
import qa.guru.restbackend.exception.InvalidUsernameException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BankController {

    private Map<String, UserInfo> users = Map.of(
            "Dima", UserInfo.builder().userName("Dima").build(),
            "Olga", UserInfo.builder().userName("Olga").build(),
            "Ivan", UserInfo.builder().userName("Ivan").build()
    );

    @PostMapping("user/login")
    @ApiOperation("авторизация")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Dima")) {
            return UserInfo.builder()
                    .loginDate(new Date())
                    .userName(loginInfo.getUserName())
                    .build();
        } else {
            throw new InvalidUsernameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Получение всех юзеров")
    public List<UserInfo> getAllUsersInfo() {
        List<UserInfo> result = new ArrayList<>();
        for (Map.Entry<String, UserInfo> entry : users.entrySet()) {
            result.add(entry.getValue());
        }


        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
