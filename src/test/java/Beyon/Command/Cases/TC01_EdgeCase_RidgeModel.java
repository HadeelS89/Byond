package Beyon.Command.Cases;

import com.beyond.common.Base;
import com.beyond.helpers.CommandHelpers;
import com.beyond.helpers.ReadWriteHelper;
import com.beyond.pagesORCmds.commands.StepDef;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC01_EdgeCase_RidgeModel {
    StepDef stepDef;



    @Parameters({"CmdTest2","PROJECT_NAME"})
    @Test(description = "gat all images ")
    public void getAllImages(String CmdTest2,String PROJECT_NAME) throws Exception {

        stepDef = new StepDef();

        System.out.println("PROJECT_NAME" + PROJECT_NAME);
        stepDef.runDockerCMD(ReadWriteHelper.readCommand(CmdTest2));
    }

    @Parameters({"digestCMd"})
    @Test(description = "digest images ",priority = 1)
    public void digestImages(String CmdTest2) throws Exception {

        stepDef = new StepDef();


        stepDef.runDockerCMD1(ReadWriteHelper.readCommand(CmdTest2));
    }
    @Parameters({"getImageNme"})
    @Test(description = "get image name ",priority = 2)
    public void imageName(String imageName) throws Exception {

        stepDef = new StepDef();


        stepDef.runDockerCMD1(ReadWriteHelper.readCommand(imageName));
    }


//    @Parameters({"getImageNme"})
//    @Test(description = "test data2 ",priority = 1)
//    public void imageNametest(String imageName) throws Exception {
//
//        stepDef = new StepDef();
//
//
//        stepDef.runDockerCMD2(ReadWriteHelper.readCommand(imageName));
//    }


}
