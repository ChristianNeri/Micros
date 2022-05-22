package curso.neto.store.product.repository;

import curso.neto.store.product.entity.Category;
import curso.neto.store.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { //JpaRepository espera una entidad y su tipo de dato de la llave primaria
    public List<Product> findByCategory(Category category);

   // public Optional<Product> findById(Long id);

   /* @Query("SELECT p FROM producto p WHERE p.name=?1")
    Optional<Product> findByName(String correo);*/
}
