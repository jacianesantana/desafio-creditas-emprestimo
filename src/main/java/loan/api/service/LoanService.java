package loan.api.service;

import loan.api.controller.response.LoanResponse;
import loan.api.model.Customer;
import loan.api.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static loan.api.model.LoanType.*;

@Service
@RequiredArgsConstructor
public class LoanService {

    private static final BigDecimal MIN_VALUE = BigDecimal.valueOf(3000);
    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(5000);
    private static final Integer AGE = 30;
    private static final String LOCATION = "SP";


    public LoanResponse eligibility(Customer customer) {
        var loans = new ArrayList<Loan>();

        isEligibleForPersonal(customer, loans);
        isEligibleForWithGuarantee(customer, loans);
        isEligibleForConsigned(customer, loans);

        return LoanResponse.builder()
                .customer(customer.getName())
                .loans(loans)
                .build();
    }

    private void isEligibleForPersonal(Customer customer, List<Loan> loans) {
       loans.add(Loan.builder().type(PERSONAL).taxes(PERSONAL.getTaxes()).build());
    }

    private void isEligibleForWithGuarantee(Customer customer, List<Loan> loans) {
        if (customer.getIncome().compareTo(MIN_VALUE) <= 0
                && customer.getAge() < AGE
                && customer.getLocation().equals(LOCATION)) {
            loans.add(Loan.builder().type(WITH_GUARANTEE).taxes(WITH_GUARANTEE.getTaxes()).build());
        }
        if (customer.getIncome().compareTo(MIN_VALUE) > 0
                && customer.getIncome().compareTo(MAX_VALUE) < 0
                && customer.getLocation().equals(LOCATION)) {
            loans.add(Loan.builder().type(WITH_GUARANTEE).taxes(WITH_GUARANTEE.getTaxes()).build());
        }
        if (customer.getIncome().compareTo(MAX_VALUE) >= 0
                && customer.getAge() < AGE) {
            loans.add(Loan.builder().type(WITH_GUARANTEE).taxes(WITH_GUARANTEE.getTaxes()).build());
        }
    }

    private void isEligibleForConsigned(Customer customer, List<Loan> loans) {
        if (customer.getIncome().compareTo(MAX_VALUE) >= 0) {
            loans.add(Loan.builder().type(CONSIGNED).taxes(CONSIGNED.getTaxes()).build());
        }
    }

}
