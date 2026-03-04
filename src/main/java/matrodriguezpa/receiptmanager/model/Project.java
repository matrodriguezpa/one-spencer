package matrodriguezpa.receiptmanager.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    private Long id;
    private String name;
    
    private final List<Year> years = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", years=");
        if (years == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < years.size(); i++) {
                sb.append(years.get(i));
                if (i < years.size() - 1) sb.append(", ");
            }
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
