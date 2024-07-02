package bg.mck.userqueryservice.application.entity;


import org.springframework.data.annotation.Id;

public class BaseEntity {

    @Id
    private String id;

    public BaseEntity(String id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }
}
