package posctn.posctn_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posctn.posctn_api.dto.response.ApiResponseDto;
import posctn.posctn_api.dto.response.DebtResponseDto;
import posctn.posctn_api.dto.response.DebtSummaryResponseDto;
import posctn.posctn_api.service.DebtService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
public class DebtController {

    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<DebtResponseDto>>> getAllDebts() {
        List<DebtResponseDto> data = debtService.getAllDebts();
        ApiResponseDto<List<DebtResponseDto>> responseDto = new ApiResponseDto<>(
                200, "Data berhasil di ambil", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponseDto<List<DebtSummaryResponseDto>>> getDebtSummary() {
        List<DebtSummaryResponseDto> data = debtService.getAllDebtSummary();
        ApiResponseDto<List<DebtSummaryResponseDto>> responseDto = new ApiResponseDto<>(
                200, "Data berhasil di ambil", data);
        return ResponseEntity.ok(responseDto);
    }
}
