package yc.mhkif.marjaneapi.Services.Observables;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;
import yc.mhkif.marjaneapi.Services.Interfaces.PromotionObserver;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class PromotionPublisher {

    private final List<PromotionObserver> observers = new ArrayList<>();


    // When this method is called it will notify Subscribers PromotionObservers
    public void promotion_notifier(PromotionNotifierStatus EventType, PromotionResponse promotion){
        observers.forEach(observer -> observer.promotionMade(EventType, promotion));
    }

    public void subscribeAnObserver(PromotionObserver observer){
        observers.add(observer);
    }

    public void unSubscribeAnObserver(PromotionObserver observer){
        observers.remove(observer);
    }
}
