package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "unspecified")
public class UnspecifiedEntity {

    private String id;

    private String name;

    public UnspecifiedEntity() {
    }

    public UnspecifiedEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
