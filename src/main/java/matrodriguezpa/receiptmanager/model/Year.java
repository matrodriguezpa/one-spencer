package matrodriguezpa.receiptmanager.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
//import matrodriguezpa.allexpence.dao.ProjectDAO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Year {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Project projectId;

    private String name;
    private int YEAR;

    private final List<Month> months = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{"
                + "id=" + (this.getId() != null ? this.getId() : "null")
                + ", name='" + (this.getName() != null ? this.getName() : "") + '\''
                + ", Year='" + this.getYEAR() + '\''
                + '}';
    }

}
