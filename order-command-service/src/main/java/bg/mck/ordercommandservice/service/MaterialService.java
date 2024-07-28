package bg.mck.ordercommandservice.service;

import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    private final FastenerService fastenerService;
    private final GalvanisedSheetService galvanisedSheetService;
    private final InsulationService insulationService;
    private final MetalService metalService;
    private final PanelService panelService;
    private final RebarService rebarService;
    private final UnspecifiedService unspecifiedService;
    private final ServiceService serviceService;
    private final TransportService transportService;
    private final SetService setService;

    public MaterialService(FastenerService fastenerService, GalvanisedSheetService galvanisedSheetService, InsulationService insulationService, MetalService metalService, PanelService panelService, RebarService rebarService, UnspecifiedService unspecifiedService, ServiceService serviceService, TransportService transportService, SetService setService) {
        this.fastenerService = fastenerService;
        this.galvanisedSheetService = galvanisedSheetService;
        this.insulationService = insulationService;
        this.metalService = metalService;
        this.panelService = panelService;
        this.rebarService = rebarService;
        this.unspecifiedService = unspecifiedService;
        this.serviceService = serviceService;
        this.transportService = transportService;
        this.setService = setService;
    }

    public void deleteMaterial(Long id, String materialType) {
        switch (materialType) {
            case "FASTENERS":
                fastenerService.cancelFastener(id);
                break;
            case "GALVANIZED_SHEET":
                galvanisedSheetService.cancelGalvanisedSheet(id);
                break;
            case "INSULATION":
                insulationService.cancelInsulation(id);
                break;
            case "METAL":
                metalService.cancelMetal(id);
                break;
            case "PANELS":
                panelService.cancelPanel(id);
                break;
            case "REBAR":
                rebarService.cancelRebar(id);
                break;
            case "UNSPECIFIED":
                unspecifiedService.cancelUnspecified(id);
                break;
            case "SERVICE":
                serviceService.cancelService(id);
                break;
            case "TRANSPORT":
                transportService.cancelTransport(id);
                break;
            case "SET":
                setService.cancelSet(id);
                break;
            default:
                throw new IllegalArgumentException("Invalid material type: " + materialType);
        }
    }

}
