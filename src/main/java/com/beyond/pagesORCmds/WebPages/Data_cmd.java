package com.beyond.pagesORCmds.WebPages;

import com.beyond.helpers.ActionsHelper;

public interface Data_cmd {

     int waitTime =60;
     String getLatestBuild ="/usr/local/bin/docker image pull";
     String createProjectTag ="Create Project";

     String projectName="Auto Had "+ ActionsHelper.generateRandomText();
     String descriptionField=" aut by Hadeel";



}
