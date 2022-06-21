package desafio.creditasemprestimo.controller.request;

import desafio.creditasemprestimo.model.Customer;
import lombok.Data;

@Data
public class LoanRequest {

    private Customer customer;

}
