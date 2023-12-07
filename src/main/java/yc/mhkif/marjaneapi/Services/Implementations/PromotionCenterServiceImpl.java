package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Promotion;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Repositories.PromotionCenterRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IPromotionCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionCenterServiceImpl implements IPromotionCenterService {

    private PromotionCenterRepository repository;

    @Autowired
    public PromotionCenterServiceImpl(PromotionCenterRepository repository ) {
        this.repository = repository;
    }


    @Override
    public Page<PromotionCenter> getPagesByManager(Manager manager,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PromotionCenter> promotions = this.repository.findPageableByManager(manager,pageRequest);
        return promotions;
    }

    public Page<PromotionCenter> getPagesByAdmin(ProxyAdmin proxyAdmin,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PromotionCenter> promotions = this.repository.findPageableByAdmin(proxyAdmin.getCin(),pageRequest);
        return promotions;
    }

    @Override
    public Optional<PromotionCenter> findById(PromotionCenterId id) {

        return repository.findById(id);
    }

    @Override
    public List<PromotionCenter> findAll() {
        return repository.findAll();
    }

    @Override
    public List<PromotionCenter> findAllPromsByManager(Manager manager) {
        return repository.findAllByManager(manager);

    }

    @Override
    public Optional<PromotionCenter> save(PromotionCenterDTO promotionDto) {
        PromotionCenter promotionCenter = repository.save(mapToEntity(promotionDto));
        return Optional.of(promotionCenter);
    }

    @Override
    public void delete(PromotionCenterId id) {

    }

    @Override
    public PromotionCenterDTO mapToDTO(PromotionCenter promotion) {
        return PromotionCenterDTO.builder().
                id(promotion.getId())
                .productPromotion(promotion.getPromotion())
                .center(promotion.getCenter())
                .manager(promotion.getManager())
                .status(promotion.getStatus())
                .performedAt(promotion.getPerformedAt())
                .build();
    }

    @Override
    public PromotionCenter mapToEntity(PromotionCenterDTO promotion) {
        PromotionCenter promotionCenter = new PromotionCenter();
        promotionCenter.setId(promotion.getId());
        promotionCenter.setPromotion(promotion.getProductPromotion());
        promotionCenter.setCenter(promotion.getCenter());
        promotionCenter.setManager(promotion.getManager());
        promotionCenter.setStatus(promotion.getStatus());
        promotionCenter.setPerformedAt(promotion.getPerformedAt());
        return promotionCenter;
    }
}
