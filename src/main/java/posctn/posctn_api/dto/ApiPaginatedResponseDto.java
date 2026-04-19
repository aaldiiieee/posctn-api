package posctn.posctn_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiPaginatedResponseDto<T> {
    private List<T> items;
    private int page;
    private int totalPages;
    private long totalItems;
    private String next;
    private String prev;
}
