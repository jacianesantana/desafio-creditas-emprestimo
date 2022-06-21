package desafio.creditasemprestimo.service;

import desafio.creditasemprestimo.controller.request.LoanRequest;
import desafio.creditasemprestimo.controller.response.LoanResponse;
import desafio.creditasemprestimo.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static desafio.creditasemprestimo.model.LoanType.*;

@Service
@RequiredArgsConstructor
public class LoanService {

    public LoanResponse eligibility(LoanRequest loanRequest) {
        var loans = new ArrayList<Loan>();
        loans.add(Loan.builder().type(EMPRESTIMO_PESSOAL).taxes(EMPRESTIMO_PESSOAL.getTaxes()).build());

        addLoanForIncomeLessOrEqual3000(loanRequest, loans);
        addLoanForIncomeLessThan5000(loanRequest, loans);
        addLoanForIncomeBiggerOrEqual5000(loanRequest, loans);

        return LoanResponse.builder()
                .customer(loanRequest.getCustomer().getName())
                .loans(loans)
                .build();
    }

    private void addLoanForIncomeLessOrEqual3000(LoanRequest loanRequest, List<Loan> loans) {
        if (loanRequest.getCustomer().getIncome().longValue() <= 3000) {
            if (loanRequest.getCustomer().getAge() < 30 && loanRequest.getCustomer().getLocation().equals("SP")) {
                loans.add(Loan.builder().type(EMPRESTIMO_COM_GARANTIA).taxes(EMPRESTIMO_COM_GARANTIA.getTaxes()).build());
            }
        }
    }

    private void addLoanForIncomeLessThan5000(LoanRequest loanRequest, List<Loan> loans) {
        if (loanRequest.getCustomer().getIncome().longValue() > 3000 && loanRequest.getCustomer().getIncome().longValue() < 5000) {
            if (loanRequest.getCustomer().getLocation().equals("SP")) {
                loans.add(Loan.builder().type(EMPRESTIMO_COM_GARANTIA).taxes(EMPRESTIMO_COM_GARANTIA.getTaxes()).build());
            }
        }
    }

    private void addLoanForIncomeBiggerOrEqual5000(LoanRequest loanRequest, List<Loan> loans) {
        if (loanRequest.getCustomer().getIncome().longValue() >= 5000) {
            loans.add(Loan.builder().type(CONSIGNADO).taxes(CONSIGNADO.getTaxes()).build());
            if (loanRequest.getCustomer().getAge() < 30) {
                loans.add(Loan.builder().type(EMPRESTIMO_COM_GARANTIA).taxes(EMPRESTIMO_COM_GARANTIA.getTaxes()).build());
            }
        }
    }

}
