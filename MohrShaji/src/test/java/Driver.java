import com.MohrShaji.application.UserManager;
import com.MohrShaji.application.ReimbursementManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Driver {
    static UserManager mu = new UserManager();
    static ReimbursementManager rm = new ReimbursementManager();

    public static void main(String[] args) {
        rm.createReimbursement(0, 100, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "description", 25, 25, 1, 1);
    }
}
