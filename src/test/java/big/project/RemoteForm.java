package big.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RemoteForm {
    private String staffCode;
    private String staffName;
    private String timeCreation;
    private String timeRequest;
    private String workplace;
    private String status;
    private String handledByStaff;
    private int editAction;
    private int showAction;
    private int deleteAction;

}
