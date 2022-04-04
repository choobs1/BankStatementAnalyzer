import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(String fileName, final BankStatementParser bankStatementParser) throws IOException{
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        collectSummary(bankStatementProcessor);
    }

    private void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total of all transactions is: " +
                bankStatementProcessor.calulateTotalAmount());
        System.out.println("The total amount of transactions in January is: " +
                bankStatementProcessor.calculateInMonth(Month.JANUARY));
        System.out.println("The total amount of transactions in February is: " +
                bankStatementProcessor.calculateInMonth(Month.FEBRUARY));
        System.out.println("The total salary is: " +
                bankStatementProcessor.calculateForCategory("Salary"));
    }
}
