package com.beyond.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beyond.common.Base;
import com.beyond.helpers.ActionsHelper;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public class ExtentManager extends Base {




// add environment param to java project m
    private  static String dockerImageNumber = System.getProperty("dockerImageNumber");//this need to be added
    static String jenkinsRunNumber = System.getProperty("jenkinsRunNumber");
    static String  jenkins_build=System.getenv("BUILD_NUMBER");//
    private static ExtentReports extent;
    private static String reportClassName;
    //reports/${ProjectName}/${ImageNumber}/${JenkinsNumber}
    public static String reportFileName = String.format("%s_%s-%s-%s.html",//%s_%s_%sReport-%s-%s.html"
            ActionsHelper.projectName() ,jenkins_build,ActionsHelper.getTodayDate(),System.currentTimeMillis());
    public static String reportImageName = String.format("Beyond-Automation_Img-%s-%s",
            ActionsHelper.getTodayDate(),System.currentTimeMillis());
    public static String path = System.getProperty("user.dir") + "/src/main/resources/Reports/";

    public static ExtentReports getInstance() throws GeneralSecurityException, MessagingException {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() throws GeneralSecurityException, MessagingException {
        StateHelper.setStepState("reportName", reportFileName);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path + reportFileName);
        htmlReporter.config().setTestViewChartLocation( ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme( Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        return extent;
    }

    public static void main(String[] args) throws GeneralSecurityException, MessagingException {
        createInstance();
    }

}
