package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.GalvanisedSheetMapper;
import bg.mck.ordercommandservice.repository.GalvanisedSheetRepository;
import org.springframework.stereotype.Service;

@Service
public class GalvanisedSheetService {
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final GalvanisedSheetMapper galvanisedSheetMapper;

    public GalvanisedSheetService(GalvanisedSheetRepository galvanisedSheetRepository, GalvanisedSheetMapper galvanisedSheetMapper) {
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
    }
}
