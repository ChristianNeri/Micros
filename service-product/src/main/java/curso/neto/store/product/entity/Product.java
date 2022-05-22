package curso.neto.store.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor //Anotacion de Lombok para generar un constructor lleno
@NoArgsConstructor //Genera un constructor vacio
@Builder //Permite crear nuevas instancias de nuestra entidad
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "El nombre del campo no debe ser vacio")
    private String name;
    private String description;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;
    private Double price;
    private String status;
    @Column(name = "create_at") //Si la variable no se llama igual a la variable que se encuengra en la base de datos se utiliza esta anotacion
    @Temporal(TemporalType.TIMESTAMP) //??
    private Date createAt;

    @NotNull(message = "La categoria no puede estra vacia")
    @ManyToOne(fetch = FetchType.LAZY) //relacion de muchos a uno FetchType.LAZY carga de manera lenta los valores de las categorias
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
}
