package curso.neto.store.product.controller;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMensaje {
    private String code;
    private List<Map <String, String>> mensaje;
}
