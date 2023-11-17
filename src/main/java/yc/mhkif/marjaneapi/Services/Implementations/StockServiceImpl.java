package yc.mhkif.marjaneapi.Services.Implementations;

import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Stock;
import yc.mhkif.marjaneapi.Repositories.StockRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements IStockService {

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock findByProduct(Product product) {
        return stockRepository.findByProduct(product);
    }
}
