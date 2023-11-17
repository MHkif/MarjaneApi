package yc.mhkif.marjaneapi.Services.Implementations;

import jakarta.transaction.Transactional;
import yc.mhkif.marjaneapi.DTOs.PromotionDTO;
import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.PromotionRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Promotion;
import yc.mhkif.marjaneapi.Repositories.ProductPromotionRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductPromotionServiceImpl implements IProductPromotionService {

    private ProductPromotionRepository repository;
    private StockServiceImpl stockService;
    private PromotionCenterServiceImpl promotionCenterService;
    private ManagerServiceImpl managerService;


    @Autowired
    public ProductPromotionServiceImpl(ProductPromotionRepository repository,
                                       PromotionCenterServiceImpl promotionCenterService,
                                       ManagerServiceImpl managerService,
                                       StockServiceImpl stockService) {
        this.repository = repository;
        this.promotionCenterService = promotionCenterService;
        this.managerService = managerService;
        this.stockService = stockService;
    }


    @Override
    public Optional<Promotion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Promotion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Promotion> save(PromotionRequest promotion) {
        int Qnt = stockService.findByProduct(promotion.getProduct()).getQuantity();
        if(Qnt <= 0){
            throw  new IllegalStateException("Quantité en stock : 0 , Ce Produit n'est pas disponible , Vérifier le stock");
        }
        else if(promotion.getProduct().getCategory().getName().equals("Computers & Accessories") && promotion.getPercentage().intValue() > 15){
            throw  new IllegalStateException("La promotion des produits multimédia ne doit pas dépasser 15%.");
        }
        else if(promotion.getPercentage().intValue() > 50 && Qnt >= 20){
            throw  new IllegalStateException("Chaque promotion ne doit pas dépasser 50 % du prix du produit.");
        }
        else if(promotion.getPercentage().intValue() > 70){
            throw  new IllegalStateException("Chaque promotion ne doit pas dépasser 70 % du prix du produit.");
        }
        else {

            Promotion productPromotion = mapToEntity(promotion);
            Optional<Manager> manager = managerService.findByCIN(productPromotion.getProduct().getCategory().getDepartment().getManager().getCin());

            if (manager.isPresent()) {
                repository.save(productPromotion);
                List<PromotionCenterDTO> promotionCenterDTOs = promotion.getCenters().stream()
                        .map(center -> PromotionCenterDTO.builder()
                                        .id(new PromotionCenterId(productPromotion.getId(), center.getId()))
                                        .productPromotion(productPromotion)
                                        .center(Optional.of(center).orElseThrow(() -> new IllegalStateException("Center not found with ID " + center.getId())))
                                        .manager(manager.orElseThrow(() -> new IllegalStateException("Manager not found")))
                                        .performedAt(null)
                                        .build()).toList();
                promotionCenterDTOs.forEach(promotionCenterService::save);

                return Optional.of(productPromotion);
            } else {
                return Optional.empty();
            }
        }

    }

    @Transactional
    @Override
    public Optional<Promotion> update(PromotionRequest promotionRequest) {
        int Qnt = stockService.findByProduct(promotionRequest.getProduct()).getQuantity();
        if(Qnt <= 0){
            throw  new IllegalStateException("Quantité en stock : 0 , Ce Produit n'est pas disponible , Vérifier le stock");
        }
        else if(promotionRequest.getProduct().getCategory().getName().equals("Computers & Accessories") && promotionRequest.getPercentage().intValue() > 15){
            throw  new IllegalStateException("La promotion des produits multimédia ne doit pas dépasser 15%.");
        }
        else if(promotionRequest.getPercentage().intValue() > 50 && Qnt >= 20){
            throw  new IllegalStateException("Chaque promotion ne doit pas dépasser 50 % du prix du produit.");
        }else {

            Optional<Manager> manager = managerService.findByCIN(promotionRequest.getProduct().getCategory().getDepartment().getManager().getCin());

                List<PromotionCenterDTO> promotionCenterDTOs = promotionRequest.getCenters().stream()
                        .map(center -> PromotionCenterDTO.builder()
                                .id(new PromotionCenterId(promotionRequest.getId(), center.getId()))
                                .productPromotion(mapToEntity(promotionRequest))
                                .center(Optional.of(center).orElseThrow(() -> new IllegalStateException("Center not found with ID " + center.getId())))
                                .manager(manager.orElseThrow(() -> new IllegalStateException("Manager not found")))
                                .performedAt(null)
                                .build()).toList();
                promotionCenterDTOs.forEach(promotionCenterService::save);

                return Optional.of(mapToEntity(promotionRequest));

        }

    }


    @Override
    public void delete(Long id) {

    }

    public PromotionResponse mapToDTO(Promotion promotion){
        if (promotion == null) {
            return null;
        }
        return PromotionResponse.builder()
                .id(promotion.getId())
                .product(promotion.getProduct())
                .percentage(promotion.getPercentage())
                .createdAt(promotion.getCreatedAt())
                .startAt(promotion.getStartAt())
                .endAt(promotion.getEndAt())
                .build();
    }

    public Promotion mapToEntity(PromotionRequest promotion){
        if (promotion == null) {
            return null;
        }
        Promotion productPromotion =  new Promotion();
        productPromotion.setId(promotion.getId());
        productPromotion.setAdmin(promotion.getAdmin());
        productPromotion.setProduct(promotion.getProduct());
        productPromotion.setPercentage(promotion.getPercentage());
        productPromotion.setCreatedAt(promotion.getCreatedAt());
        productPromotion.setStartAt(promotion.getStartAt());
        productPromotion.setEndAt(promotion.getEndAt());

        return productPromotion;
    }
}
