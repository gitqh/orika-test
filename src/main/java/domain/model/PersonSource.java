package domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hqu
 */
@Data
@AllArgsConstructor
public class PersonSource {
    private PersonSourceA personSourceA;
    private PersonSourceB personSourceB;
    private List<Address> addresses;
    private Account account;
}
