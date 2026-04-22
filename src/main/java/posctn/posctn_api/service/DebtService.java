
package posctn.posctn_api.service;

import posctn.posctn_api.dto.response.DebtResponseDto;
import posctn.posctn_api.dto.response.DebtSummaryResponseDto;

import java.util.List;

public interface DebtService {

    /**
     * Get all data debts
     * @return
     */
    List<DebtResponseDto> getAllDebts();

    /**
     * Get all debt summary
     * @return
     */
    List<DebtSummaryResponseDto> getAllDebtSummary();
}
