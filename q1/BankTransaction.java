import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankTransaction {

    // Update with your actual database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/lenden";
    private static final String USER = "root";       // Default XAMPP/MySQL username
    private static final String PASSWORD = "";       // Default XAMPP/MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try {
            // 1. Establish connection to the 'lenden' database
            // Note: Make sure the MySQL JDBC Driver is in your classpath
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // 2. Set auto-commit to false for transaction management
            conn.setAutoCommit(false);

            // 3. Accept account_number and amount from the user
            System.out.print("Enter your account number: ");
            int fromAccount = scanner.nextInt();
            
            System.out.print("Enter amount to transfer to Account 102: ");
            double amount = scanner.nextDouble();

            // 4. Check the balance of the source account
            String checkBalanceSql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkBalanceSql);
            checkStmt.setInt(1, fromAccount);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                if (currentBalance >= amount) {
                    // 5. Debit the amount from the given account
                    String debitSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                    PreparedStatement debitStmt = conn.prepareStatement(debitSql);
                    debitStmt.setDouble(1, amount);
                    debitStmt.setInt(2, fromAccount);
                    int rowsUpdated1 = debitStmt.executeUpdate();

                    // 6. Credit the same amount to account 102
                    String creditSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = 102";
                    PreparedStatement creditStmt = conn.prepareStatement(creditSql);
                    creditStmt.setDouble(1, amount);
                    int rowsUpdated2 = creditStmt.executeUpdate();

                    // 7. Verify both operations succeeded
                    if (rowsUpdated1 > 0 && rowsUpdated2 > 0) {
                        conn.commit();
                        System.out.println("Transaction Successful");
                    } else {
                        conn.rollback();
                        System.out.println("Transaction Failed");
                    }
                } else {
                    // Insufficient balance scenario
                    conn.rollback();
                    System.out.println("Transaction Failed");
                }
            } else {
                // Account not found scenario
                System.out.println("Account not found");
                conn.rollback();
                System.out.println("Transaction Failed");
            }

        } catch (Exception e) {
            // 8. Rollback on any SQL or Runtime Exception
            System.out.println("Error occurred: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction Failed");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 9. Clean up resources
            try {
                if (conn != null) {
                    conn.close();
                }
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
