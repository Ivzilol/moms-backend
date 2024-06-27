package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.SetMapper;
import bg.mck.ordercommandservice.repository.SetRepository;
import org.springframework.stereotype.Service;

@Service
public class SetService {
    private final SetRepository setRepository;
    private final SetMapper setMapper;

    public SetService(SetRepository setRepository, SetMapper setMapper) {
        this.setRepository = setRepository;
        this.setMapper = setMapper;
    }
}
