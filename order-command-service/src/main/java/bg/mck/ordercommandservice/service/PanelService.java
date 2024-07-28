package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.PanelEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.PanelMapper;
import bg.mck.ordercommandservice.repository.PanelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanelService {
    private final PanelRepository panelRepository;
    private final PanelMapper panelMapper;

    public PanelService(PanelRepository panelRepository, PanelMapper panelMapper) {
        this.panelRepository = panelRepository;
        this.panelMapper = panelMapper;
    }

    public void cancelPanel(Long id) {
        Optional<PanelEntity> PanelById = panelRepository.findById(id);
        if (PanelById.isEmpty()){
            throw new IllegalArgumentException("Panel with id " + id + " not found");
        }
        PanelById.get().setMaterialStatus(MaterialStatus.CANCELED);
        panelRepository.save(PanelById.get());
    }
}
