package Beyon.Command.Cases;

import com.beyond.helpers.ReadWriteHelper;
import com.beyond.pagesORCmds.commands.CommandMethods;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestFirstcmd3 {
    CommandMethods commandMethods;
//login image
    // excute cont
    // run
    //url
    //logout
    @Parameters({"CmdTest0"})
    @Test(description = "get all available  image test  ")
    public void getAllImages(String CmdTest0) throws Exception {

        commandMethods = new CommandMethods();
        String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
        String projectName = System.getProperty("project_name");//image name

        commandMethods.runDocker_Command(ReadWriteHelper.readCommand(CmdTest0)
                + " " + projectName);
        System.out.println("test command getAllImage= " + ReadWriteHelper.readCommand(CmdTest0) + " " + projectName );
    }

    @Parameters({"getLatest_Image"})
    @Test(description = "get latest image test  ",priority = 1)
    public void getLatestImage(String getLatest_Image) throws Exception {

        commandMethods = new CommandMethods();
        String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
        String projectName = System.getProperty("project_name");//image name

        commandMethods.runDocker_Command(ReadWriteHelper.readCommand(getLatest_Image)
        + " " + projectName +":"+ dockerImageNumber);
        System.out.println("test command = " + ReadWriteHelper.readCommand(getLatest_Image)
                + " " + projectName +":"+ dockerImageNumber);
       // Assert.assertTrue();
    }

//docker run -it ubuntu:16 perfecta-nlg make tests

    @Parameters({"getLatest_Image"})
    @Test(description = "get latest image test  ",priority = 2)
    public void get_newcmd(String cmd3) throws Exception {

        commandMethods = new CommandMethods();
        String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
        String projectName = System.getProperty("project_name");//image name

        commandMethods.runDocker_Command(ReadWriteHelper.readCommand(cmd3)
                + " " + projectName +":"+ dockerImageNumber);
        System.out.println("test command = " + ReadWriteHelper.readCommand(cmd3)
                + " " + projectName +":"+ dockerImageNumber+"perfecta-nlg make tests");
        // Assert.assertTrue();
    }
}
