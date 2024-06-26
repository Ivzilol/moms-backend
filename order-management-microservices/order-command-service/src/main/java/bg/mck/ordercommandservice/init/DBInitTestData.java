package bg.mck.ordercommandservice.init;

import bg.mck.ordercommandservice.dto.UserDetailsDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.material.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.entity.material.FastenerEntity;
import bg.mck.ordercommandservice.entity.service.ServiceEntity;
import bg.mck.ordercommandservice.entity.transport.TransportEntity;
import bg.mck.ordercommandservice.repository.ConstructionSiteRepository;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import bg.mck.ordercommandservice.repository.MaterialRepository;
import bg.mck.ordercommandservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DBInitTestData implements CommandLineRunner {

    private final FastenerRepository fastenerRepository;
    private final OrderRepository orderRepository;
    private final MaterialRepository materialRepository;
    private final ConstructionSiteRepository constructionSiteRepository;

    public DBInitTestData(FastenerRepository fastenerRepository, OrderRepository orderRepository, MaterialRepository materialRepository, ConstructionSiteRepository constructionSiteRepository) {
        this.fastenerRepository = fastenerRepository;
        this.orderRepository = orderRepository;
        this.materialRepository = materialRepository;
        this.constructionSiteRepository = constructionSiteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // НОМЕР НА ОБЕКТ
        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setConstructionNumber("18.21.1")
                .setName("СГРАДА В");
        constructionSiteRepository.save(constructionSiteEntity);

        // ПОТРЕБИТЕЛ
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setEmail("test@gmail.com");


        // МАТЕРИАЛИ
        FastenerEntity fastenerEntity1 = new FastenerEntity();
        fastenerEntity1.setQuantity(15.0)
                .setDescription("Болт")
                .setDiameter(10.0)
                .setLength(100.0);

        FastenerEntity fastenerEntity2 = new FastenerEntity();
        fastenerEntity2.setQuantity(15.0)
                .setDescription("Гайка")
                .setDiameter(10.0);

        GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
        galvanisedSheetEntity.setQuantity(25.0);

        _MaterialEntity materialEntity = new _MaterialEntity();
        materialEntity.setFasteners(Set.of(fastenerEntity1, fastenerEntity2));
        materialEntity.setGalvanisedSheets(Set.of(galvanisedSheetEntity));

        //ТРАНСПОРТ
        TransportEntity transportEntity = new TransportEntity();
        transportEntity.setDescription("Камион за превоз на материали")
                .setDistance(10.0);

        // УСЛУГИ
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setDescription("Армировка на стени")
                .setPrice(100.0);


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber("123")
                .setUsername(userDetailsDTO.getEmail())
                .setOrderDate(LocalDateTime.now())

                .setMaterials(materialEntity)
                .setServices(serviceEntity)
                .setTransports(transportEntity)

                .setConstructionSite(constructionSiteEntity)
                .setOrderDescription("АРМИРОВКА ВЕРТИКАЛИ СТЕНИ ОТ КОТА -11.10 ДО КОТА -7.10 - СГРАДА В (Ст. 8b - Ст. 20b; Ст. 22b; Ст. 23b")
                .setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(orderEntity);


    }
}
