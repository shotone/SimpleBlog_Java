package ge.shota.blog.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")
    private Long id;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "delete_time")
    @JsonIgnore
    private LocalDateTime deleteTime;

    public void invalidate() {
        this.deleteTime = LocalDateTime.now(ZoneOffset.systemDefault());
    }

    @JsonIgnore
    public boolean isInvalidated() {
        return this.deleteTime != null;
    }
}