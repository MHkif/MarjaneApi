package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;

import java.util.List;
import java.util.Optional;

public interface IPromotionCenterService {

    List<PromotionCenter> findAllPromsByManager(Manager manager);
    Optional<PromotionCenter> save(PromotionCenterDTO promotion);
    Optional<PromotionCenter>  findById(PromotionCenterId id);
    List<PromotionCenter> findAll();
    void delete(PromotionCenterId id);

    PromotionCenterDTO mapToDTO(PromotionCenter promotion);

    PromotionCenter mapToEntity(PromotionCenterDTO promotion);
}
