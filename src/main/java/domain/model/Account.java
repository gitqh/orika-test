package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hqu
 */
@Data
@AllArgsConstructor
public class Account {
    private Integer sum;
    private String accountName;
}
