package yc.mhkif.marjaneapi.Controllers;

import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.PromotionRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Entities.*;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;
import yc.mhkif.marjaneapi.Enums.PromotionStatus;
import yc.mhkif.marjaneapi.Observer_Pattern.*;
import yc.mhkif.marjaneapi.Services.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "marjane/api/v1")
public class PromotionController {

    private ProductPromotionServiceImpl prodPromoService;
    private PromotionCenterServiceImpl promoCenterService;
    private ProxyAdminServiceImpl proxyAdminService;
    private PromotionPublisher promotionManager;

    @Autowired
    public PromotionController(
            ProductPromotionServiceImpl service, PromotionCenterServiceImpl promotionCenterService,
            ProxyAdminServiceImpl proxyAdminService, PromotionPublisher promotionManager) {
        this.prodPromoService = service;
        this.promoCenterService = promotionCenterService;
        this.proxyAdminService = proxyAdminService;
        this.promotionManager = promotionManager;
    }



    @PostMapping(value = "/promotions/products/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionResponse> savePromotion(@RequestBody PromotionRequest request,
                                                           @RequestHeader Map<String, String> headers) {
        if(headers.get("token") != null){
            Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN(headers.get("token"));
            if (proxyAdmin.isEmpty()){
                throw new IllegalStateException("Proxy Admin Not Found With This Cin : "+ headers.get("token"));
            }
            request.setAdmin(proxyAdmin.get());
            Optional<Promotion> promotion = prodPromoService.save(request);
            if(promotion.isPresent()){
                promotionManager.subscribeAnObserver(new ManagerNotifier());
                promotionManager.subscribeAnObserver(new PromotionEventLogger());
                promotionManager.promotion_notifier(PromotionNotifierStatus.NEW_PROMOTION, prodPromoService.mapToDTO(promotion.get()));
            }
            return promotion.map(
                            productPromotion -> new ResponseEntity<>(
                                    prodPromoService.mapToDTO(productPromotion), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }else{
            throw new IllegalStateException("Token Authentication for Proxy Admin Not Found ");
        }
    }


    @PostMapping(value = "/promotions/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionResponse> updatePromotion(@PathVariable("id") Long id) {
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("SQ570980");
        if (proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Promotion promotion;
        promotion = prodPromoService.findById(id).
                orElseThrow(() -> new IllegalStateException("Promotion not found with ID "+ id));

        return prodPromoService.update(new PromotionRequest()).map(
                        productPromotion -> new ResponseEntity<>(
                                prodPromoService.mapToDTO(productPromotion), HttpStatus.OK)
                ).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    @GetMapping(value = "/promotions/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionResponse> getPromotion(@PathVariable("id") Long id, @RequestHeader Map<String, String> headers){
        if(headers.get("token") != null ){
            Optional<Promotion> promotion = prodPromoService.findById(id);
            return promotion.map(productPromotion -> new ResponseEntity<>(prodPromoService.mapToDTO(productPromotion), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }else{
            throw new IllegalStateException("Token Authentication for Super Admin Not Found ");
        }
    }


    @GetMapping(value = "/promotions/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PromotionResponse> getAllPromotions(@RequestHeader Map<String, String> headers){
        if(headers.get("token") == null ){
            throw new IllegalStateException("Token Authentication for Super Admin Not Found ");
        }
        return prodPromoService.findAll()
                .stream()
                .map(prodPromoService::mapToDTO)
                .toList();
    }


    @GetMapping(value = "/promotions/{centerId}/{promoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionCenterDTO> getPromotionCenter(@PathVariable("centerId") Long centerId, @PathVariable("promoId") Long promoId
            , @RequestHeader Map<String, String> headers){
        if(headers.get("token") == null ){
            throw new IllegalStateException("Token Authentication for Super Admin Not Found ");
        }
        Optional<PromotionCenter> promotionCenter = promoCenterService.findById(new PromotionCenterId(centerId, promoId));
        return promotionCenter.map(promoCenter -> new ResponseEntity<>(promoCenterService.mapToDTO(promoCenter), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> getAllPromotionsWithCenters(@RequestHeader Map<String, String> headers){
        if(headers.get("token") == null ){
            throw new IllegalStateException("Token Authentication for Super Admin Not Found ");
        }

        List<PromotionCenterDTO> promotions = promoCenterService.findAll()
                .stream()
                .map(promoCenterService::mapToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }


    @PostMapping(value = "/promotions/apply", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> ApplyToPromotions(@RequestBody List<PromotionCenterDTO>  promotions, @RequestHeader Map<String, String> headers){
        if(headers.get("token") == null ){
            throw new IllegalStateException("Token Authentication for Manager Not Found ");
        }
        List<PromotionCenterDTO> allPromotions =  promoCenterService.findAll()
                .stream()
                .map(promoCenterService::mapToDTO)
                .toList();

        List<PromotionCenterDTO> promotionsToUpdate = promotions.stream()
                .filter(promotionCenterDTO -> allPromotions.stream().noneMatch(allPromotion ->
                                        allPromotion.getId().equals(promotionCenterDTO.getId()) &&
                                                allPromotion.getStatus() == promotionCenterDTO.getStatus())).toList();

        List<PromotionCenterDTO> promotionsList =  promotionsToUpdate.stream()
                .peek(promotionCenterDTO -> {
                    promotionCenterDTO.setPerformedAt(LocalDateTime.now());
                    promoCenterService.save(promotionCenterDTO);

                    // TODO Subscribe An Observer
                    promotionManager.subscribeAnObserver(new ProxyAdminNotifier());

                    if(!promotionCenterDTO.getStatus().equals(PromotionStatus.PENDING)){
                        if(promotionCenterDTO.getStatus().equals(PromotionStatus.ACCEPTED)){
                            promotionManager.promotion_notifier(
                                    PromotionNotifierStatus.PROMOTION_ACCEPTED, prodPromoService
                                            .mapToDTO(promotionCenterDTO.getProductPromotion()));
                        } else{
                            promotionManager.promotion_notifier(
                                    PromotionNotifierStatus.PROMOTION_REFUSED, prodPromoService
                                            .mapToDTO(promotionCenterDTO.getProductPromotion()));
                        }
                    }


                }).collect(Collectors.toList());
        return ResponseEntity.ok(promotionsList);
    }

}
