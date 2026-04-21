package posctn.posctn_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posctn.posctn_api.dto.request.KasbonCreateRequestDto;
import posctn.posctn_api.dto.response.ApiResponseDto;
import posctn.posctn_api.dto.response.TransactionResponseDto;
import posctn.posctn_api.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<TransactionResponseDto>> createKasbon(
            @RequestBody KasbonCreateRequestDto request) {
        TransactionResponseDto data = transactionService.createKasbon(request);
        ApiResponseDto<TransactionResponseDto> response = new ApiResponseDto<>(
                200, "Kasbon berhasil dibuat", data);
        return ResponseEntity.ok(response);
    }
}
