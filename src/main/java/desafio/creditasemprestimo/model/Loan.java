package desafio.creditasemprestimo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan {

    private LoanType type;
    private Integer taxes;

}
