import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN
            = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public BankTransaction parseFrom(final String line) {
        String[] columns = line.split(",");
        final LocalDate localDate = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];
        return new BankTransaction(localDate, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        for (String line:
             lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}
