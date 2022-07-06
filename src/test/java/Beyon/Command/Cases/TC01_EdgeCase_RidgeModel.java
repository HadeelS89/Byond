package Beyon.Command.Cases;

import com.beyond.common.Base;
import com.beyond.helpers.ReadWriteHelper;
import com.beyond.pagesORCmds.commands.CommandMethods;
import com.beyond.pagesORCmds.commands.StepDef;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC01_EdgeCase_RidgeModel  {
    CommandMethods commandMethods;

    @Parameters({"CmdTest0"})
    @Test(description = "get latest image test  ")
    public void getImage(String CmdTest0) throws Exception {

        commandMethods = new CommandMethods();
        String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
        String projectName = System.getProperty("project_name");//image name

        commandMethods.runDocker_firstCommand(ReadWriteHelper.readCommand(CmdTest0)
                + " " + projectName);
        System.out.println("test command = " + ReadWriteHelper.readCommand(CmdTest0) );
    }

    @Parameters({"getLatest_Image"})
    @Test(description = "get latest image test  ")
    public void getLatestImage(String getLatest_Image) throws Exception {

        commandMethods = new CommandMethods();
        String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
        String projectName = System.getProperty("project_name");//image name

        commandMethods.runDocker_firstCommand(ReadWriteHelper.readCommand(getLatest_Image)
        + " " + projectName +":"+ dockerImageNumber);
        System.out.println("test command = " + ReadWriteHelper.readCommand(getLatest_Image) );
    }


}
