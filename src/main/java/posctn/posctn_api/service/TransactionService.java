package posctn.posctn_api.service;

import posctn.posctn_api.dto.request.KasbonCreateRequestDto;
import posctn.posctn_api.dto.response.TransactionResponseDto;

public interface TransactionService {

    /**
     * Create kasbon
     * @param request
     * @return
     */
    TransactionResponseDto createKasbon(KasbonCreateRequestDto request);
}
