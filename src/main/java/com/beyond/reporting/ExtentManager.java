package com.beyond.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beyond.common.Base;
import com.beyond.helpers.ActionsHelper;
import org.testng.annotations.*;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public class ExtentManager extends Base {



// add environment param to java project m
    private  static String dockerImageNumber = System.getProperty("image_name");//TAG//this need to be added
    static String projectName = System.getProperty("project_name");//image name
    static String  jenkins_build=System.getProperty("build_number","12");//
    private static ExtentReports extent;
    private static String reportClassName;


    public static String reportFileNameNew =String.format("%s/%s/%s.html",
            projectName,jenkins_build,dockerImageNumber);

    public static String reportImageName = String.format("Beyond-Automation_Img-%s-%s",
            ActionsHelper.getTodayDate(),System.currentTimeMillis());
    public static String path = System.getProperty("user.dir") + "/src/main/resources/Reports/";





    public static String reportFileName = String.format("%s-%s-%s-%s.html",//%s_%s_%sReport-%s-%s.html"
            projectName,jenkins_build,dockerImageNumber,ActionsHelper.getTodayDate());
    public static ExtentReports createInstance() throws GeneralSecurityException, MessagingException {
        StateHelper.setStepState("reportName",
                reportFileName);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path +reportFileName);
        htmlReporter.config().setTestViewChartLocation( ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme( Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        return extent;
    }
// run docker inside mvn
    // run maven inside docker

    public static void main(String[] args) throws GeneralSecurityException, MessagingException {
        System.out.println(projectName);
    }

}
