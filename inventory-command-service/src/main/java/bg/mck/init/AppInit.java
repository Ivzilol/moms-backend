package bg.mck.init;

import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.repository.FastenerRepository;
import bg.mck.service.MaterialRegisterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final MaterialRegisterService inventoryService;
    private final FastenerRepository fastenerRepository;

    public AppInit(MaterialRegisterService inventoryService, FastenerRepository fastenerRepository) {
        this.inventoryService = inventoryService;
        this.fastenerRepository = fastenerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        inventoryService.initCategory();

//        FastenerEntity fastenerEntity = new FastenerEntity();
//        fastenerEntity.setName("Fastener1");
//        fastenerEntity.setLength("15.50");
//
//        fastenerRepository.save(fastenerEntity);

    }
}
