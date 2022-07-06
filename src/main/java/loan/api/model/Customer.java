package loan.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @CPF
    private String cpf;
    @NotNull
    @Min(value = 18)
    @Max(value = 100)
    private Integer age;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @NotNull
    private BigDecimal income;

}
