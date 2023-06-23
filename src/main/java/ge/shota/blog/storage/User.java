package ge.shota.blog.storage;

import ge.shota.blog.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "blog", name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's authorities/roles (e.g., as a list of SimpleGrantedAuthority objects)
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true; // Return true if the user account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Return true if the user account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Return true if the user's credentials are not expired
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public User(UserModel model) {
        this.userName = model.getUserName();
        this.password = model.getPassword();
    }



}
