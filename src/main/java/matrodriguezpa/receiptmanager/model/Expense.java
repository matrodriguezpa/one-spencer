package matrodriguezpa.receiptmanager.model;

import matrodriguezpa.receiptmanager.util.DBConectionUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    private Long id;

    private Long ProjectId;
    private Long yearId;
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
        Long projectId = this.getProjectId();
        Long yearIdField = this.getYearId();
        Long monthIdField = this.getMonthId();

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
                + ", projectId=" + (projectId != null ? projectId : "null")
                + ", yearId=" + (yearIdField != null ? yearIdField : "null")
                + ", monthId=" + (monthIdField != null ? monthIdField : "null")
                + ", date='" + date + '\''
                + ", company='" + company + '\''
                + ", amount=" + String.format("%.2f", amount)
                + ", type='" + type + '\''
                + ", matrix='" + matrix + '\''
                + ", payment='" + payment + '\''
                + '}';
    }

}