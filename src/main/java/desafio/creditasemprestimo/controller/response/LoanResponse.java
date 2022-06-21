package desafio.creditasemprestimo.controller.response;

import desafio.creditasemprestimo.model.Loan;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoanResponse {

    private String customer;
    private List<Loan> loans;

}
