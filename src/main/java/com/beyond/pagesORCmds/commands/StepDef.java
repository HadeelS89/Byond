package com.beyond.pagesORCmds.commands;




import java.io.IOException;

import com.beyond.helpers.ReadWriteHelper;

// done
public class StepDef {
	

	public static String dirPath;
	
	 


	//@Given("Execute docker login command")
	public void runDockerCMD(String loginCommand) throws Exception {

		//String dockerDirPath = configFileReader.properties.getProperty(prop1);
		//String loginCommand = ReadWriteHelper.readCommand("loginToDockerCommand");
		
	//	CommandMethods.runDocker_Command(loginCommand);
		
	}

	public void runDockerCMD1(String loginCommand) throws Exception {

		//String dockerDirPath = configFileReader.properties.getProperty(prop1);
		//String loginCommand = ReadWriteHelper.readCommand("loginToDockerCommand");

		CommandMethods.runDockerCommand1(loginCommand);

	}
	public void runDockerCMD2(String loginCommand) throws Exception {

		//String dockerDirPath = configFileReader.properties.getProperty(prop1);
		//String loginCommand = ReadWriteHelper.readCommand("loginToDockerCommand");

		CommandMethods.getContainerID(loginCommand);

	}

	//@When("Start the Container")
	public void startContainer() throws IOException, InterruptedException
{
		dirPath = System.getProperty("user.dir");

//		ConfigFileReader configFileReader= new ConfigFileReader();
//


//		String Command = configFileReader.properties.getProperty("runContainer");
//
//		String exCommand = Command.replace("${dirPath}", dirPath);
//
//		System.out.println(exCommand);
//
		CommandMethods.exCommand();

	}

	//@And("Get the container ID and save it in gloabal variable")
	public  void testH(String getContainerID) throws IOException, InterruptedException
	{
// path for docker



		CommandMethods.runCmd(getContainerID);


	}


	public void testH1() throws IOException, InterruptedException {

		CommandMethods.RunCommandsInsidContainer("");
	}

	//@And("Execute the container then initialize study")
	public void executeDocker(String infillIntilzeStudy) throws IOException, InterruptedException
	{

		CommandMethods.RunCommandsInsidContainer(infillIntilzeStudy);




	}

	//@Then("Verify the study folder should be created")
	public  void checkStudyIsExist() throws InterruptedException
	{

		CommandMethods.checkExistOfStudy();


		  }

		 // @And("Delete The created study folder")
		  public  void deleteStudy() throws IOException
		  {


			  CommandMethods.deleteTheStudy();

		  }


		  //@When("Copy dataset from NAS drive")
		  public  void copyDataSet() throws IOException, InterruptedException
		  {


			//  CommandMethods.copyFiles();
			  Thread.sleep(1500);

	     }

      // 	 @And("Execute the container and make featuer")
			public  void exMakeFeatuer(String makeFeatuer) throws IOException, InterruptedException
			{



   		   CommandMethods.RunCommandsInsidContainer(makeFeatuer);

		}

		//	@And("Delete the running container")
			public static void deleteRunningContainer() throws IOException, InterruptedException
			{



				String deleteCommand = ReadWriteHelper.readCommand("deleteContainer");

				//CommandHelpers.runDocker_Command(deleteCommand);

			}



			//@When("Restart the container")
			public static void restartContainer() throws IOException, InterruptedException
			{



				String startCommand = ReadWriteHelper.readCommand("rerunContainer");

			//	CommandHelpers.runDocker_Command(startCommand);

			}

			//@And("Execute the container and create Study")
			public static void exCreateStudy(String runCMD_Label) throws IOException, InterruptedException
			{



		   		   CommandMethods.RunCommandsInsidContainerUsingLabels(runCMD_Label);

			}

			//@And("Execute the container and train model")
			public static void exTrainModel() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleRF");

			}

		//	@And("Execute the container and predict plan")
			public static void exPredictPlan() throws IOException, InterruptedException
			{



		   		   CommandMethods.runPredictPlan("predictModel","plan-24-10-MBH","model663");

			}

			//@And("Execute the container and train model templateSimpleRidge")
			public static void exTrainModeltemplateSimpleRidge() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleRidge");

			}


		//	@Given("Update JSON File simpleRidge")
			public static void UpdateSimpleRidge() throws IOException // what is this for ?
			{



				CommandMethods.copyFileContent("simpleRidgePath", "orginalSimpleRidgePath");

			}

		//	@Given("Update JSON File simpleBridge")
			public static void UpdateSimpleBridge() throws IOException
			{



				CommandMethods.copyFileContent("simpleBridgePath", "orginalSimpleBridgePath");

			}

		//	@And("Execute the container and train model templateSimpleBridge")
			public static void exTrainModeltemplateSimpleBridge() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleRidge");

			}

			//@Given("Update JSON File Simplegboost")
			public static void UpdateSimplegboost() throws IOException
			{



				CommandMethods.copyFileContent("SimpleGboostPath", "orginalSimpleGboostPath");

			}

		//	@And("Execute the container and train model templateGboost")
			public static void exTrainModeltemplateSimpleGboost() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateGboost");

			}

		//	@Given("Update JSON File SimpleCboost")
			public static void UpdateSimpleCboost() throws IOException
			{



				CommandMethods.copyFileContent("SimpleCboostPath", "orginalSimpleCboostPath");

			}

			//@And("Execute the container and train model SimpleCboost")
			public static void exTrainModeltemplateSimpleCboost() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateGboost");

			}

			//@Given("Update JSON File SimpleRf")
			public static void UpdateSimpleRf() throws IOException
			{



				CommandMethods.copyFileContent("SimpleRfPath", "orginalSimpleRfPath");

			}

			//@And("Execute the container and train model SimpleRf")
			public static void exTrainModeltemplateSimpleRf() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleRf");
			}

		//	@Given("Update JSON File SimpleLasso")
			public static void UpdateSimpleLasso() throws IOException
			{



				CommandMethods.copyFileContent("SimpleLassoPath", "orginalSimpleLassoPath");

			}

			//@And("Execute the container and train model SimpleLasso")
			public static void exTrainModeltemplateSimpleLasso() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleLasso");
			}


		//	@Given("Update JSON File SimpleEnet")
			public static void UpdateSimpleEnet() throws IOException
			{



				CommandMethods.copyFileContent("SimpleEnetPath", "orginalSimpleEnetPath");

			}

			//@And("Execute the container and train model SimpleEnet")
			public static void exTrainModeltemplateSimpleEnet() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleEnet");
			}

			//@Given("Update JSON File SimpleKflow")
			public static void UpdateSimpleKflow() throws IOException
			{



				CommandMethods.copyFileContent("SimpleKflowPath", "orginalSimpleKflowPath");

			}

		//	@And("Execute the container and train model SimpleKflow")
			public static void exTrainModeltemplateSimpleKflow() throws IOException, InterruptedException
			{



		   		   CommandMethods.runTrainModel("trainModel","model663","templateSimpleKflow");
			}

			//@Given("Update JSON File SimpleRf by wrong data")
			public static void UpdateSimpleRfWrongData() throws IOException
			{



				CommandMethods.copyFileContent("SimpleRFWrongDataPath", "orginalSimpleRfPath");

			}

			//@Given("Update JSON File SimpleKflow by wrong data")
			public static void UpdateSimpleKflowWrongData() throws IOException
			{



				CommandMethods.copyFileContent("SimpleKflowWrongDataPath", "orginalSimpleKflowPath");

			}

			//@Given("Update JSON File SimpleEnet by wrong data")
			public static void UpdateSimpleEnetWrongData() throws IOException
			{



				CommandMethods.copyFileContent("SimpleEnetWrongDataPath", "orginalSimpleEnetPath");

			}

		//	@Given("Update JSON File SimpleKflow by out of range values")
			public static void UpdateSimpleKflowOutOfRangeValues() throws IOException
			{



				CommandMethods.copyFileContent("SimpleKflowOutOfRangeValuesPath", "orginalSimpleKflowPath");

			}

			//@Given("Update JSON File SimpleLasso by incconrect types")
			public static void UpdateSimpleLassoIncconrectTypes() throws IOException
			{



				CommandMethods.copyFileContent("SimpleLassoIncorrectTypesPath", "orginalSimpleLassoPath");

			}

			//@Given("Update JSON File Simplegboost by remove required parameters")
			public static void UpdateSimplegboostRemoveParameters() throws IOException
			{



				CommandMethods.copyFileContent("SimpleGboostMissingParametresPath", "orginalSimpleGboostPath");

			}

			//@Given("Update JSON File simpleBridge by using empty model")
			public static void UpdateSimpleBridgeEmptyModel() throws IOException
			{



				CommandMethods.copyFileContent("SimpleBridgeEmptyModelPath", "orginalSimpleBridgePath");

			}
			
			
}
			
		
	

	
	

