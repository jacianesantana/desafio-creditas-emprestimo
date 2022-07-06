package loan.api.controller.request;

import loan.api.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @Valid
    private Customer customer;

}
