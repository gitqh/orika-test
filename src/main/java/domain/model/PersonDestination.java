package domain.model;

import java.util.List;
import lombok.Data;

/**
 * @author hqu
 */
@Data
public class PersonDestination {
    private Long uid;
    private String uname;
    private String uscore;
    private String usex;
    private List<AddressDetail> uaddressDetails;
    private Integer usum;
    private String uaccountName;
}
