package loan.api.controller.response;

import loan.api.model.Loan;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoanResponse {

    private String customer;
    private List<Loan> loans;

}
