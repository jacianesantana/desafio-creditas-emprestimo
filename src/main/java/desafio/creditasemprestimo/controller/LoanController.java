package desafio.creditasemprestimo.controller;

import desafio.creditasemprestimo.controller.request.LoanRequest;
import desafio.creditasemprestimo.controller.response.LoanResponse;
import desafio.creditasemprestimo.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> eligibility(@RequestBody LoanRequest loanRequest) {
        return new ResponseEntity<>(loanService.eligibility(loanRequest), HttpStatus.CREATED);
    }

}
