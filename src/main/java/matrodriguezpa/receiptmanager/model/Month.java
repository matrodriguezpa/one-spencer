
package matrodriguezpa.receiptmanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Camilo
 */
public class Month {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Year projectId;

    private int MONTH;

    private List<Expense> expense = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{"
                + '}';
    }

}
