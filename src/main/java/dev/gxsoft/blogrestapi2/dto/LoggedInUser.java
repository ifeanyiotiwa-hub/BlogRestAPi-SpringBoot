package dev.gxsoft.blogrestapi2.dto;


import lombok.*;

@Data
@RequiredArgsConstructor
public class LoggedInUser {
    String email;
    String password;
}
