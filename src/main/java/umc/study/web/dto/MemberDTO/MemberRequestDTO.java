package umc.study.web.dto.MemberDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;
import java.util.List;

public class MemberRequestDTO {
    @Getter
    @Setter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        String email;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @NotNull
        String password;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
        @NotNull
        Role role;
    }
}
