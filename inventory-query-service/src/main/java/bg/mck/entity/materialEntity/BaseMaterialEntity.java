package bg.mck.entity.materialEntity;

import org.springframework.data.annotation.Id;

public class BaseMaterialEntity {

    @Id
    private String id;

    public BaseMaterialEntity() {
    }

    public BaseMaterialEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BaseMaterialEntity setId(String id) {
        this.id = id;
        return this;
    }


}
