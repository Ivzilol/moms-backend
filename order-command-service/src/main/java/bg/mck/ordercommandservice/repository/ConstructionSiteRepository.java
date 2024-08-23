package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSiteEntity, Long> {
    Optional<ConstructionSiteEntity> findByConstructionNumberAndName(String constructionNumber, String name);

    Optional<ConstructionSiteEntity> findByName(String name);

    Optional<ConstructionSiteEntity> findByConstructionNumber(String constructionNumber);

//    @Query(nativeQuery = true,
//            value = """
//                    select *
//                    from orders
//                    join mck_orders.construction_sites cs on cs.id = orders.construction_site_id
//                    where cs.id = :id
//                    """)
//    Boolean existsByIdAndOrdersIsNotNull(Long id);

    @Query(nativeQuery = true,
            value = """
               select case when exists (
                   select 1
                   from orders o
                   join construction_sites cs on cs.id = o.construction_site_id
                   where cs.id = :id
               ) then 1 else 0 end
               """)
    int existsByIdAndOrdersIsNotNull(@Param("id") Long id);

    boolean existsByName(String name);

    boolean existsByConstructionNumber(String number);

    @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ConstructionSiteEntity cs WHERE cs.name = :name AND cs.constructionNumber = :number")
    boolean existsByNameAndConstructionNumber(String name, String number);
}
