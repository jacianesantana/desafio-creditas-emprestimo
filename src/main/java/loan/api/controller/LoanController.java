package loan.api.controller;

import loan.api.controller.request.LoanRequest;
import loan.api.controller.response.LoanResponse;
import loan.api.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> eligibility(@Valid @RequestBody LoanRequest loanRequest) {
        return new ResponseEntity<>(loanService.eligibility(loanRequest.getCustomer()), HttpStatus.OK);
    }

}
