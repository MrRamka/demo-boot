package com.ybcompany.demo.dto.api;

import com.ybcompany.demo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUsersDto {
    private List<UserDto> data;
}
