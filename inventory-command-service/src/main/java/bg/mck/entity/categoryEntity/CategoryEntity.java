package bg.mck.entity.categoryEntity;

import bg.mck.enums.MaterialType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column()
    private MaterialType materialType;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, MaterialType materialType) {
        this.id = id;
        this.materialType = materialType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id) && materialType == that.materialType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialType);
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", materialType=" + materialType +
                '}';
    }
}
