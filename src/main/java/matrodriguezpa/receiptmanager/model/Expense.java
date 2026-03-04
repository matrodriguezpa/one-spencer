package matrodriguezpa.receiptmanager.model;

import matrodriguezpa.receiptmanager.dao.DBConectionUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense extends DBConectionUtil {

    private Long id;
    private Long MonthId;

    private int YEAR;
    private int MONTH;
    private int DAY;

    private String company;
    private double amount;
    private String type;
    private String matrix;
    private String payment;

    @Override
    public String toString() {
        Long id = this.getId();
        Integer year = this.getYEAR();
        Integer month = this.getMONTH();
        Integer day = this.getDAY();
        String company = this.getCompany() != null ? this.getCompany() : "";
        double amount = this.getAmount();
        String type = this.getType() != null ? this.getType() : "";
        String matrix = this.getMatrix() != null ? this.getMatrix() : "";
        String payment = this.getPayment() != null ? this.getPayment() : "";

        String date = String.format("%04d-%02d-%02d", year, month, day);

        return "Expense{"
                + "id=" + (id != null ? id : "null")
                + ", company='" + company + '\''
                + ", amount=" + String.format("%.2f", amount)
                + ", type='" + type + '\''
                + ", matrix='" + matrix + '\''
                + ", payment='" + payment + '\''
                + '}';
    }

}
