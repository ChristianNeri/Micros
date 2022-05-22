package curso.neto.store.product.service;

import curso.neto.store.product.entity.Category;
import curso.neto.store.product.entity.Product;
import curso.neto.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor //inyeccion de dependencias por constructor
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "El producto con el id [ " + id + "] no existe"
        ));
    }

    @Override
    public Product createProduct(Product product) {

        product.setStatus("CREADO");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product pdt = getProduct(product.getId());
        if(null == pdt){
            return null;
        }
        pdt.setName(product.getName());
        pdt.setDescription(product.getDescription());
        pdt.setCategory(product.getCategory());
        pdt.setPrice(product.getPrice());
        return productRepository.save(pdt);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product pdt = getProduct((id));
        if(null == pdt){
            return null;
        }
        pdt.setStatus("BORRADO");
        return productRepository.save(pdt);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product pdt = getProduct((id));
        if(null == pdt){
            return null;
        }
        Double stock = pdt.getStock() + quantity;
        pdt.setStock(stock);
        return productRepository.save(pdt);
    }
}
