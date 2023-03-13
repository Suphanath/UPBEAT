import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configs {

    public int m;
    public int n;
    public long init_plan_min;
    public long init_plan_sec;
    public long init_budget;
    public int init_center_dep;
    public int plan_rev_min;
    public int plan_rev_sec;
    public int rev_cost;
    public int max_dep;
    public int interest_pct;
    public int start_deposit;

    private static Configs conf;


    /*need to change
    need to change
    need to change
    need to change
    need to change
    need to change
    need to change
     */
    private Configs() {
        m=20;
        n=15;
        init_plan_min=5;
        init_plan_sec=0;
        init_budget=10000;
        init_center_dep=100;
        plan_rev_min=30;
        plan_rev_sec=0;
        rev_cost=100;
        max_dep=1000000;
        interest_pct=5;
    }

    public static Configs conf(){
        if (conf == null) {
            conf = new Configs();
        }
        return conf;
    }
}
