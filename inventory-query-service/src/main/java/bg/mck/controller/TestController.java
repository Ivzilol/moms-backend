package bg.mck.controller;

import bg.mck.entity.materialEntity.*;
import bg.mck.repository.material.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final MetalRepository metalRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;

    public TestController(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, MetalRepository metalRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
    }

    @GetMapping("/inventory/fastener/{name}")
    public FastenerEntity getFastenerByName(@PathVariable String name) {
        return fastenerRepository.findByName(name);
    }

    @GetMapping("/inventory/galvanizedSheet/{name}")
    public GalvanisedSheetEntity getGalvanizedSheetByName(@PathVariable String name) {
        return galvanisedSheetRepository.findByName(name);
    }

    @GetMapping("/inventory/insulation/{name}")
    InsulationEntity getInsulationEntityByName(@PathVariable String name) {
        return  insulationRepository.findByName(name);
    }

    @GetMapping("/inventory/metal/{name}")
    MetalEntity getMetalEntityByName(@PathVariable String name) {
        return metalRepository.findByName(name);
    }

    @GetMapping("/inventory/panel/{name}")
    PanelEntity getPanelEntityByName(@PathVariable String name) {
        return panelRepository.findByName(name);
    }

    @GetMapping("/inventory/rebar/{name}")
    RebarEntity getRebarEntityByName(@PathVariable String name) {
        return rebarRepository.findByName(name);
    }

    @GetMapping("/inventory/set/{name}")
    SetEntity getSetEntityByName(@PathVariable String name) {
        return setRepository.findByName(name);
    }

    @GetMapping("/inventory/unspecified/{name}")
    UnspecifiedEntity getUnspecifiedEntityByName(@PathVariable String name) {
        return unspecifiedRepository.findByName(name);
    }
}
