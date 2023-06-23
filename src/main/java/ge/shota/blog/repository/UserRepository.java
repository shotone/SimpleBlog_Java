package ge.shota.blog.repository;

import ge.shota.blog.storage.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameAndDeleteTimeIsNull(String username);

    Optional<User> findByUserName(String username);

}
