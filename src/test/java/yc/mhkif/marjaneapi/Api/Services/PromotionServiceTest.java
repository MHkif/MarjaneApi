package yc.mhkif.marjaneapi.Api.Services;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import yc.mhkif.marjaneapi.DTOs.Requests.PromotionRequest;
import yc.mhkif.marjaneapi.Entities.*;
import yc.mhkif.marjaneapi.Repositories.PromotionRepository;
import yc.mhkif.marjaneapi.Services.Implementations.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class PromotionServiceTest {

    @Mock
    private StockServiceImpl stockService;
    @Mock
    private ManagerServiceImpl managerService;

    @Mock
    private PromotionRepository repository;

    @InjectMocks
    private PromotionServiceImpl promotionService;

    // Note :
    // It ensures that Mockito annotations are processed before each test method is run,
    // setting up the mocks and preparing the environment for testing.
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveMethod_WhenValidPromotion() {
        int qnt =  27;

        Manager manager = Manager.builder()
                .cin("SD708903")
                .build();

        Department department = Department.builder()
                .manager(manager)
                .build();

        Category category = Category.builder()
                .department(department)
                //.name("Computers & Accessories")
                .name("Category B")
                .build();

        Product product = Product.builder().category(category).build();

        PromotionRequest promotionRequest = PromotionRequest.builder()
                .id(1L)
                .admin(new ProxyAdmin())
                .product(product)
                .percentage(BigDecimal.valueOf(30))
                //.percentage(BigDecimal.valueOf(15L)) // category = Computers & Accessories
                //.percentage(BigDecimal.valueOf(70)) // qnt < 20
                .centers(List.of(new Center()))
                .build();

        when(stockService.findByProduct(any())).thenReturn( Stock.builder().id(0L).quantity(qnt).build());
        when(managerService.findByCIN(any())).thenReturn(Optional.ofNullable(Manager.builder().cin("SD605395").build()));

        Optional<Promotion> savedPromotion = promotionService.save(promotionRequest);
        assertTrue(savedPromotion.isPresent());
    }

    @Test
    public void testFindByIdMethod_WhenPromotionExists() {
        Long promotionId = 1L;
        Promotion expectedPromotion = Promotion.builder().id(promotionId).build();

        when(repository.findById(promotionId)).thenReturn(Optional.of(expectedPromotion));

        Optional<Promotion> actualPromotionOptional = promotionService.findById(promotionId);

        assertTrue(actualPromotionOptional.isPresent());

        Promotion actualPromotion = actualPromotionOptional.get();
        assertEquals(expectedPromotion, actualPromotion);

    }

    @Test
    public void testFindByIdMethod_WhenPromotionDoesNotExist() {
        Long nonExistentPromotionId = 100L; // ID of a non-existent promotion

        when(repository.findById(nonExistentPromotionId)).thenReturn(Optional.empty());

        Optional<Promotion> actualPromotionOptional = promotionService.findById(nonExistentPromotionId);

        assertFalse(actualPromotionOptional.isPresent());
    }

    @Test
    public void testFindAllMethod() {
        List<Promotion> expectedPromotions = Arrays.asList(
                Promotion.builder().id(1L).build(),
                Promotion.builder().id(2L).build()
        );
        when(repository.findAll()).thenReturn(expectedPromotions);

        List<Promotion> actualPromotions = promotionService.findAll();

        assertNotNull(actualPromotions);

        assertEquals(expectedPromotions.size(), actualPromotions.size());

        assertIterableEquals(expectedPromotions, actualPromotions);
    }

}
