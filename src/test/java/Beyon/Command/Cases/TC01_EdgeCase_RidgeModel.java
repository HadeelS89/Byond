package Beyon.Command.Cases;

import com.beyond.helpers.CommandHelpers;
import com.beyond.helpers.ReadWriteHelper;
import com.beyond.pagesORCmds.commands.StepDef;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC01_EdgeCase_RidgeModel {
    StepDef stepDef;


@Parameters({"CmdTest"})
    @Test(description = "test data ")
    public void testHadeel(String CmdTest) throws Exception {

 stepDef = new StepDef();


stepDef.runDockerCMD(ReadWriteHelper.readCommand(CmdTest));
    }

    @Parameters({"CmdTest2"})
    @Test(description = "test data2 ",priority = 1)
    public void test2(String CmdTest2) throws Exception {

        stepDef = new StepDef();


        stepDef.runDockerCMD(ReadWriteHelper.readCommand(CmdTest2));
    }

}
