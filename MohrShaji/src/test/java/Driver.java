import com.MohrShaji.application.ReimbursementManager;
import com.MohrShaji.application.UserManager;
import com.MohrShaji.model.Reimbursement;
import com.MohrShaji.model.User;

import java.sql.Timestamp;
import java.time.Instant;

public class Driver {

    static UserManager um = new UserManager();

    public static void main(String[] args) {
        User u = new User(247,"resTest","pasword","test","res","res@gmail.com",79);
        Reimbursement re = new Reimbursement();
        ReimbursementManager rm = new ReimbursementManager();
        Timestamp instant= Timestamp.from(Instant.now());

        //mu.createUser(4,"resTest","pasword","test","res","res@gmail.com",79);
      rm.createReimbursement(79,3000, instant,instant,"gee",0,0,3,5);
       // rm.listReimbursment();
       //rm.updateReimbursement(84,3000,4);
        //rm.deleteReimbursement(2);
       // rm.getById(345);
       // rm.getByUserId(26);
      //mu.createUser(1,"noelshaji","password","noel","shaji","nshaji916@gmail.com",24);
       //mu.createUser(247,"resTest","pasword","test","res","res@gmail.com",79);

      // rm.createReimbursment(5,2444,instant,instant,"test",2,1,3,5);
       // rm.updateReimbursement(76,3000,8);
       // mu.createUser(2445,"asdasdl","pasword","nshaj","shaji","nshaji916@gmail.com",97);*/

        //  tx.commit();
      // mu.listUsers();
      //mu.listUsers();
        //mu.deleteUser(241);
       // mu.updateUser(245,"updatename");

       // mu.getByUserId(245);
        //mu.getByUsername("asdasdl");

    }
}
