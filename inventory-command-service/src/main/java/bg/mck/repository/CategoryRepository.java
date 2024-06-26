package bg.mck.repository;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByMaterialType(MaterialType materialType);
}
