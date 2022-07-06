package loan.api.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Customer {

    private String name;
    private String cpf;
    private Integer age;
    private String location;
    private BigDecimal income;

}
