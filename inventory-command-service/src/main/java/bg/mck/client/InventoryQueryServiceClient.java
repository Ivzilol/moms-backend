package bg.mck.client;

import bg.mck.dto.InventoryItemDetailsDTO;
import bg.mck.entity.materialEntity.*;
import bg.mck.events.construction.BaseConstructionEvent;
import bg.mck.events.material.BaseMaterialEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.service.BaseServiceEvent;
import bg.mck.events.transport.BaseTransportEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-QUERY-SERVICE")
public interface InventoryQueryServiceClient {


    @PostMapping("/inventory/materials/events")
    <T extends BaseMaterialEvent> void sendMaterialEvent(@RequestBody MaterialEvent<T> data, @RequestHeader("Event-Type") String eventType,
                                                         @RequestHeader("Material-Type") String materialType);

    @PostMapping("/inventory/services/events")
    void sendServiceEvent(@RequestBody BaseServiceEvent data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/inventory/transports/events")
    void sendTransportEvent(@RequestBody BaseTransportEvent data, @RequestHeader("Event-Type") String eventType);

    @PostMapping("/inventory/constructions/events")
    void sendConstructionEvent(@RequestBody BaseConstructionEvent data, @RequestHeader("Event-Type") String eventType);

    @GetMapping("/inventory/items/{id}")
    InventoryItemDetailsDTO getInventoryItemById(@PathVariable("id") Long id);

    @GetMapping("inventory/items/{id}/exists")
    boolean existsInventoryItemById(@PathVariable("id") Long id);

    @GetMapping("/inventory/fastener/{name}")
    FastenerEntity getFastenerByName(@PathVariable("name") String name);

    @GetMapping("/inventory/galvanizedSheet/{name}")
    GalvanisedSheetEntity getGalvanizedSheetByName(@PathVariable String name);

    @GetMapping("/inventory/insulation/{name}")
    InsulationEntity getInsulationEntityByName(@PathVariable String name);

    @GetMapping("/inventory/metal/{name}")
    MetalEntity getMetalEntityByName(@PathVariable String name);

    @GetMapping("/inventory/panel/{name}")
    PanelEntity getPanelEntityByName(@PathVariable String name);

    @GetMapping("/inventory/rebar/{name}")
    RebarEntity getRebarEntityByName(@PathVariable String name);

    @GetMapping("/inventory/set/{name}")
    SetEntity getSetEntityByName(@PathVariable String name);

    @GetMapping("/inventory/unspecified/{name}")
    UnspecifiedEntity getUnspecifiedEntityByName(@PathVariable String name);
}