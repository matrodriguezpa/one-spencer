package matrodriguezpa.receiptmanager.model;

import java.util.ArrayList;
import java.util.List;

import matrodriguezpa.receiptmanager.util.DBConectionUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Month {

    private Long id;
    private Long yearId;
    private Year projectId;

    private int MONTH;

    private List<Expense> expense = new ArrayList<>();

    @Override
    public String toString() {
        return "Month{"
                + "id=" + (id != null ? id : "null")
                + ", yearId=" + (projectId != null ? projectId.getId() : "null")
                + ", yearTag='" + (projectId != null && projectId.getTag() != null ? projectId.getTag() : "") + '\''
                + ", month=" + MONTH
                + '}';
    }
}
