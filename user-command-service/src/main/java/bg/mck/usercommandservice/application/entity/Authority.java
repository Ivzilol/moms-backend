package bg.mck.usercommandservice.application.entity;

import bg.mck.usercommandservice.application.enums.AuthorityEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;


    public Authority() {
    }

    public Authority(Authority other) {
        this.id = other.id;
        this.authority = other.authority;
    }

    public Long getId() {
        return id;
    }

    public Authority setId(Long id) {
        this.id = id;
        return this;
    }

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public Authority setAuthority(AuthorityEnum authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(id, authority1.id) && authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority=" + authority +
                '}';
    }
}
