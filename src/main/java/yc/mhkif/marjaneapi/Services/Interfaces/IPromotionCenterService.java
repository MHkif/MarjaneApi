package yc.mhkif.marjaneapi.Services.Interfaces;

import org.springframework.data.domain.Page;
import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;

import java.util.List;
import java.util.Optional;

public interface IPromotionCenterService {

    List<PromotionCenter> findAllPromsByManager(Manager manager);
    Optional<PromotionCenter> save(PromotionCenterDTO promotion);
    Optional<PromotionCenter>  findById(PromotionCenterId id);
    List<PromotionCenter> findAll();
    Page<PromotionCenter> getPagesByManager(Manager manager,int page, int size);
    void delete(PromotionCenterId id);

    PromotionCenterDTO mapToDTO(PromotionCenter promotion);

    PromotionCenter mapToEntity(PromotionCenterDTO promotion);
}
