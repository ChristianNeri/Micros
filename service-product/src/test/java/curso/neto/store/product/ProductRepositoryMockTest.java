package curso.neto.store.product;

import curso.neto.store.product.entity.Category;
import curso.neto.store.product.entity.Product;
import curso.neto.store.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void whenFindByCategoria_thenReturnListProducto(){
        Product product01 = Product.builder()
                .name("Computadora")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("9"))
                .price(Double.parseDouble("2700"))
                .status("Creado")
                .createAt(new Date()).build();
        productRepository.save(product01);

        List<Product> encontrados = productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(encontrados.size()).isEqualTo(1);
    }
}
