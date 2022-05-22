package curso.neto.store.product.entity;


import lombok.*;

import javax.persistence.*;

@Entity //Para definir a la clase como una entidad se utiliza esta anotacion
@Table(name = "tbl_categories")  //Si el nombre de la taba es distinta a la de la clase se utiliza esta anotacion
//@Getter //Esta anotacion por medio de la dependencia o plugin Lombok crea por detras los getters de las variables en la clase
//@Setter //Esta anotacion por medio de la dependencia o plugin Lombok crea por detras los setters de las variables en la clase
@Data //Esta anotacion de Lombok genera los setters y getters y otros mas de las variables que estan en la clase
@AllArgsConstructor//Anotacion para indicar la creacion de un constructor con los datos
@NoArgsConstructor//Anotacion para indicar un constructor vacio
@Builder
public class Category {
    @Id //esta anotacion se utiliza para identificar que atributo es la llave primaria dentro de la clase
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Esta anotacion es usada para indicar que el id sera autoincrementable
    private Long id;
    private String name;

}
