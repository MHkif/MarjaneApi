package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Stock;

public interface IStockService {

    public Stock findByProduct(Product product);
}
