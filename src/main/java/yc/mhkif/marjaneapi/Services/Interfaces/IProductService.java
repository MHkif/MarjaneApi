package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Stock;

public interface IProductService {
    public Product findByCategory(Product product);

}
