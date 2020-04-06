package com.ybcompany.demo.dto;

import com.ybcompany.demo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String fullName;

    /**
     * Creating Data Transfer Object from User
     *
     * @param user
     * @return UserDto
     */
    public static UserDto from(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .build();

    }

    /**
     * Creating Data Transfer Object from Users list
     *
     * @param users
     * @return List<UserDto>
     */
    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());

    }


}
