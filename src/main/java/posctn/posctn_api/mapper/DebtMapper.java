package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.response.DebtPaymentResponseDto;
import posctn.posctn_api.dto.response.DebtResponseDto;
import posctn.posctn_api.dto.response.DebtSummaryResponseDto;
import posctn.posctn_api.model.DebtModel;
import posctn.posctn_api.model.DebtPaymentModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DebtMapper {

    public DebtResponseDto toDto(DebtModel debt) {
        return DebtResponseDto.builder()
                .id(debt.getId())
                .customerName(debt.getCustomer().getName())
                .transactionId(debt.getTransaction().getId())
                .amount(debt.getAmount())
                .remainingAmount(debt.getRemainingAmount())
                .status(debt.getStatus())
                .createdAt(debt.getCreatedAt())
                .payments(toPaymentDtoList(debt.getPayments()))
                .build();
    }

//    public DebtSummaryResponseDto toDebtSummaryDto(DebtModel debt) {
//        return DebtSummaryResponseDto.builder()
//                .customerId(debt.getCustomer().getId())
//                .customerName(debt.getCustomer().getName())
//                .totalDebt(debt)
//    }

    private List<DebtPaymentResponseDto> toPaymentDtoList(List<DebtPaymentModel> payments) {
        if (payments == null) return Collections.emptyList();
        return payments.stream()
                .map(this::toPaymentDto)
                .collect(Collectors.toList());
    }

    private DebtPaymentResponseDto toPaymentDto(DebtPaymentModel payment) {
        return DebtPaymentResponseDto.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
