package posctn.posctn_api.dto;

import lombok.*;
import org.apache.commons.io.input.ObservableInputStream;

import java.util.List;

/**
 * Slice for infinite scrolling frontend
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SliceResponseDto<T> {
    private List<T> items;
    private int page;
    private boolean hasNext;
}
