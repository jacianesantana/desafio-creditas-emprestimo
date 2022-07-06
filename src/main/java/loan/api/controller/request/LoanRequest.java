package loan.api.controller.request;

import loan.api.model.Customer;
import lombok.Data;

@Data
public class LoanRequest {

    private Customer customer;

}
