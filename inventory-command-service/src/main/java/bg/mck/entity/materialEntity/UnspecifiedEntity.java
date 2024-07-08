package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "unspecified")
public class UnspecifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;

   @ManyToOne
   private CategoryEntity category;

    public UnspecifiedEntity() {
    }

    public UnspecifiedEntity(Long id, String name, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
