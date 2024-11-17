package umc.study.web.dto;

import lombok.Getter;
import umc.study.domain.enums.Gender;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    public static class JoinDto {
        String name;
        Integer gender;
        String email;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String password;
        String address;
        String specAddress;
        List<Long> preferCategory;
    }
}
