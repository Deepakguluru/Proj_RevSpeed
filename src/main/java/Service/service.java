package Service;

import com.revature.entity.subscriptionplans;
import com.revature.entity.subscriptions;
import com.revature.entity.user;

import java.util.List;

public interface service {
    public String signUp(user userDetails);
    public void login(String userName );

    public void getPlanDetails(String name);

    public void  getSubscriptions();
}
