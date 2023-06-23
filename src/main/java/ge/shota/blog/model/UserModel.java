package ge.shota.blog.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserModel {

    private String userName;
    private String password;

    @Builder.Default
    private boolean enabled = true;

}
