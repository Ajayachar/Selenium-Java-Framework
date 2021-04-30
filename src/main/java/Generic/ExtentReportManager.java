package Generic;

import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports Extent;

    public synchronized static ExtentReports getReporter(String Tname) {
        //Create extent report
        Extent = new ExtentReports(Tname, true);
        Extent.addSystemInfo("Host Name", "Jupiter Toys")
                .addSystemInfo("Env", "Autmation")
                .addSystemInfo("User Name", "Ajay Mallamar");
        Extent.loadConfig(new File("resources/extent-config.xml"));
        return Extent;
    }

}
