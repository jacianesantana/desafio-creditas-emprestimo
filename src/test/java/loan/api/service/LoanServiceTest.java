package loan.api.service;

import loan.api.controller.request.LoanRequest;
import loan.api.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static loan.api.model.LoanType.*;

@ExtendWith(SpringExtension.class)
class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Test
    void eligibilityShouldReturnOnlyPersonalLoan_WithIncomeLessOrEqual3000() {
        var loanRequest = loanBuilder();
        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(1);
        Assertions.assertThat(response.getLoans().get(0).getType()).isEqualTo(PERSONAL);
        Assertions.assertThat(response.getLoans().get(0).getTaxes()).isEqualTo(4);
    }

    @Test
    void eligibilityShouldReturnPersonalLoanAndLoanWithGuarantee_WithIncomeLessOrEqual3000() {
        var loanRequest = loanBuilder();
        loanRequest.getCustomer().setAge(29);
        loanRequest.getCustomer().setLocation("SP");

        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(2);
        Assertions.assertThat(response.getLoans().get(1).getType()).isEqualTo(WITH_GUARANTEE);
        Assertions.assertThat(response.getLoans().get(1).getTaxes()).isEqualTo(3);
    }

    @Test
    void eligibilityShouldReturnOnlyPersonalLoan_WithIncomeLessThan5000() {
        var loanRequest = loanBuilder();
        loanRequest.getCustomer().setIncome(BigDecimal.valueOf(4000));

        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(1);
        Assertions.assertThat(response.getLoans().get(0).getType()).isEqualTo(PERSONAL);
        Assertions.assertThat(response.getLoans().get(0).getTaxes()).isEqualTo(4);
    }

    @Test
    void eligibilityShouldReturnPersonalLoanAndLoanWithGuarantee_WithIncomeLessThan5000() {
        var loanRequest = loanBuilder();
        loanRequest.getCustomer().setIncome(BigDecimal.valueOf(4000));
        loanRequest.getCustomer().setLocation("SP");

        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(2);
        Assertions.assertThat(response.getLoans().get(1).getType()).isEqualTo(WITH_GUARANTEE);
        Assertions.assertThat(response.getLoans().get(1).getTaxes()).isEqualTo(3);
    }

    @Test
    void eligibilityShouldReturnOnlyPersonalLoan_WithIncomeBiggerOrEqual5000() {
        var loanRequest = loanBuilder();
        loanRequest.getCustomer().setIncome(BigDecimal.valueOf(5000));

        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(2);
        Assertions.assertThat(response.getLoans().get(0).getType()).isEqualTo(PERSONAL);
        Assertions.assertThat(response.getLoans().get(1).getType()).isEqualTo(CONSIGNED);
        Assertions.assertThat(response.getLoans().get(0).getTaxes()).isEqualTo(4);
        Assertions.assertThat(response.getLoans().get(1).getTaxes()).isEqualTo(2);
    }

    @Test
    void eligibilityShouldReturnPersonalLoanAndLoanWithGuarantee_WithIncomeBiggerOrEqual5000() {
        var loanRequest = loanBuilder();
        loanRequest.getCustomer().setIncome(BigDecimal.valueOf(5000));
        loanRequest.getCustomer().setAge(29);

        var response = loanService.eligibility(loanRequest.getCustomer());

        Assertions.assertThat(response.getLoans()).hasSize(3);
        Assertions.assertThat(response.getLoans().get(1).getType()).isEqualTo(WITH_GUARANTEE);
        Assertions.assertThat(response.getLoans().get(1).getTaxes()).isEqualTo(3);
    }

    private LoanRequest loanBuilder() {
        var customer = new Customer();
        customer.setAge(30);
        customer.setCpf("012.345.678-90");
        customer.setIncome(BigDecimal.valueOf(3000));
        customer.setLocation("SE");
        customer.setName("Jaciane");

        var loanRequest = new LoanRequest();
        loanRequest.setCustomer(customer);

        return loanRequest;
    }

}