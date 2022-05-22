package curso.neto.store.product;

import curso.neto.store.product.entity.Category;
import curso.neto.store.product.entity.Product;
import curso.neto.store.product.repository.ProductRepository;
import curso.neto.store.product.service.ProductService;
import curso.neto.store.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock //Patra datos Mockeados (Dummys??)
    private ProductRepository productRepository;

    private ProductService productService;


    @BeforeEach // para indicar que esto se debe de ejecutar antes las pruebas unitarias
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product Play = Product.builder() //indicamos la creacion de un constructor
                .id(1L)
                .name("Playstation")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("15.5"))
                .stock(Double.parseDouble("10"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(Play));
        Mockito.when(productRepository.save(Play)).thenReturn(Play);
    }

    @Test
    public void whenValidGetid_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Playstation");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(18);
    }
}
