package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(" ");
                String recordName = parts[1];

                if (name.equals(recordName)) {
                    LocalDate recordDate = LocalDate.parse(parts[0], formatter);
                    if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        totalSalary += hours * rate;
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
