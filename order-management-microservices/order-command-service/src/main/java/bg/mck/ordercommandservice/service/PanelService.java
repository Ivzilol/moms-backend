package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.PanelMapper;
import bg.mck.ordercommandservice.repository.PanelRepository;
import org.springframework.stereotype.Service;

@Service
public class PanelService {
    private final PanelRepository panelRepository;
    private final PanelMapper panelMapper;

    public PanelService(PanelRepository panelRepository, PanelMapper panelMapper) {
        this.panelRepository = panelRepository;
        this.panelMapper = panelMapper;
    }
}
