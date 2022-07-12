package Beyon.Command.Cases;

import com.beyond.helpers.ActionsHelper;
import com.beyond.helpers.ReadWriteHelper;
import com.beyond.pagesORCmds.commands.CommandMethods;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


@Listeners
public class Infill_HappyCase {

    String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
    String projectName = System.getProperty("project_name");//image name
    public static String dirPath = System.getProperty("user.dir");
    CommandMethods commandMethods;

    /*

    Given get latest image
    When Execute intilize study command
    Then Verify the study folder should be created
    When Copy dataset from NAS drive
    And Execute make featuer command
    Then Delete The created study folder

     */
    @Parameters({"getLatest_Image"})
    @Test(description = "get latest image from docker file  ", priority = 0)
    public void getLatestImage(String getLatest_Image) throws Exception {

        commandMethods = new CommandMethods();


        commandMethods.runDocker_Command(ReadWriteHelper.readCommand(getLatest_Image)
                + " " + projectName + ":" + dockerImageNumber);
        System.out.println("test command = " + ReadWriteHelper.readCommand(getLatest_Image)
                + " " + projectName + ":" + dockerImageNumber);
        // Assert.assertTrue();
    }


    //"macDockerDirPath", "projectDirPath", "buildNumberPath", "infillIntilzeStudy"

//docker run -it ubuntu:16 perfecta-nlg make tests

    @Parameters({"infill_InitializeStudy"})
    @Test(description = "Execute initialize study command  ", priority = 1)
    public void initialise_Study(String infill_InitializeStudy) throws Exception {

        commandMethods = new CommandMethods();


        commandMethods.RunContainerAndEx(ReadWriteHelper.readCommand("dockerPath"),
                ReadWriteHelper.readCommand("projectDirPath").replace("${dirPath}", dirPath), projectName + ":" + dockerImageNumber
                , ReadWriteHelper.readCommand(infill_InitializeStudy));
        String expectedResult = "Closed: initialize-study";

        String actualResults = Files.readString(Paths.get(dirPath + "/src/main/resources/DataProvider/ActualResults.txt"));

        System.out.println("actualResults" + actualResults);
        assertThat(actualResults, containsString(expectedResult));

        commandMethods.file.delete();// delete the file
    }

    @Parameters({"studyFolderName"})
    @Test(description = "Verify the study folder is created ", priority = 2)
    public void verifyStudyFolder(String fileName) throws Exception {

// return true if file here
        Assert.assertTrue(ActionsHelper.isFileDownloaded(ReadWriteHelper.readCommand(fileName), "false"));
    }


    @Parameters({"studyFolderName"})
    @Test(description = "Copy dataset from NAS drive ", priority = 3)
    public void copyFile(String fileName) throws Exception {

// return true if file here
        commandMethods = new CommandMethods();
        commandMethods.copyFiles(ReadWriteHelper.readCommand(fileName), "Study");
    }

    @Parameters({"studyFolderName"})
    @Test(description = "Delete The created study folder ", priority = 4)
    public void deleteFile(String fileName) throws Exception {
        FileUtils.forceDelete(new File("src/main/resources/DataProvider/" + ReadWriteHelper.readCommand(fileName)));
        Thread.sleep(3000);
// return true if file here
        Assert.assertTrue(!ActionsHelper.isFileDownloaded(ReadWriteHelper.readCommand(fileName), "false"));
    }
}
