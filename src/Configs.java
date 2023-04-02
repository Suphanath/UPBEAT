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

    private Configs() {
        Properties props = new Properties();
        try {
            FileInputStream fInput = new FileInputStream("src/configs.txt");
            props.load(fInput);
            fInput.close();
        } catch (IOException e) {
            System.err.println("Can not read this file.");
            return;
        }
        m = Integer.parseInt(props.getProperty("m"));
        n = Integer.parseInt(props.getProperty("n"));
        init_plan_min = Long.parseLong(props.getProperty("init_plan_min"));
        init_plan_sec = Long.parseLong(props.getProperty("init_plan_sec"));
        init_budget = Long.parseLong(props.getProperty("init_budget"));
        init_center_dep = Integer.parseInt(props.getProperty("init_center_dep"));
        plan_rev_min = Integer.parseInt(props.getProperty("plan_rev_min"));
        plan_rev_sec = Integer.parseInt(props.getProperty("plan_rev_sec"));
        rev_cost = Integer.parseInt(props.getProperty("rev_cost"));
        max_dep = Integer.parseInt(props.getProperty("max_dep"));
        interest_pct = Integer.parseInt(props.getProperty("interest_pct"));
        start_deposit = Integer.parseInt(props.getProperty("start_deposit", "0"));
    }

    public static Configs conf(){
        if (conf == null) {
            conf = new Configs();
        }
        return conf;
    }
}
