package HTM;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

//import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ListIterator;

import javax.naming.AuthenticationException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import jxl.write.WriteException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import com.relevantcodes.extentreports.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
public class HotelManage {
public static WebDriver driver;
static Workbook wbook;
private static String BASE_URL = "http://localhost:5050";
static WritableWorkbook wwbCopy;
static String ExecutedTestCasesSheet;
static Sheet shSheet;
public static ExtentReports report;
public static ExtentTest logger;
public static String h_name;
public static String loc;
public static String r_type;
public static String a_date;
public static String d_date;
public static String total_rooms;
public static String final_price;
public static String first_name;
public static String last_name;
public static String order;
public static String URL;
public static String Execution="";
public static String newKey;
@Test
public void initialDriver() throws InterruptedException {
		// TODO Auto-generated method stub
        report=new ExtentReports("C:\\HotelManagement\\E2E_Automation.html");
       
         // System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
         // driver = new InternetExplorerDriver();
          
         logger=report.startTest("HotelBooking"); 
         logger.log(LogStatus.INFO,"Browser Started");
         popupmessage("E2E GUI Automation","E2E Test set-Downlaod TestCases from Zephyr is initiated",5000);
         Thread.sleep(15000);
         popupmessage("E2E GUI Automation","E2E Test set-Downlaod TestCases from Zephyr is Completed",5000);
         driver=new FirefoxDriver();
          try{
        	    wbook = Workbook.getWorkbook(new File("C://HotelManagement//DataTable//Test_Case1.xls"));
        	  //  wwbCopy = Workbook.createWorkbook(new File("C://Users//Administrator//workspace//HotelManagement//DataTable//Test_Case.xls"), wbook);
        	    shSheet = wbook.getSheet(0);
        	    int RC = shSheet.getRows();
        	    System.out.println(RC);
        	    popupmessage("E2E GUI Automation","Test Case Execution Started",5000);
        	    for (int i =0 ; i < RC ; i++)
        	    {
        	    	Cell shRowExecute= shSheet.getCell(3,i);
        	    	String ExecuteResult = shRowExecute.getContents();
        	    	System.out.println(ExecuteResult);
        	    	
        	    	
        	    	if (ExecuteResult.equalsIgnoreCase("Y"))
        	    	{
        	    		
        	    		Cell TestFlowCell = shSheet.getCell(2,i);
        	    		String TestFlow = TestFlowCell.getContents();
        	    		String[] splTestFlow = TestFlow.split(",");
        	    		HotelManage Ht = new HotelManage();
        	    		Cell UrlCell=shSheet.getCell(4, i);
        	    		HotelManage.URL = UrlCell.getContents();
        	    		System.out.println("URL check"+HotelManage.URL);
        	    		for (int le = 0 ; le < splTestFlow.length; le++)
        	    		{
        	    			System.out.println(splTestFlow[le].toUpperCase());
        	    			 switch (splTestFlow[le].toUpperCase()) {
        	    			
        	    			 	        case "LOGIN":
                                             Ht.Login();
        	    			 
        	    			 	            break;
        	    			 
        	    			 	        case "SEARCH":
                                             Ht.Search();
        	    			 	            break;
        	    			 	            
        	    			 	       case "BOOK":
                                           Ht.Book();
      	    			 	            break;
      	    			 	            
        	    			 	      case "CANCEL":
                                          Ht.Cancel();
     	    			 	            break;
     	    			 	            
        	    			 	        default :
        	    			 	        	System.out.println("unknown flow");
        	    			 	        break;
        	    			 }
        	    		}
        	    		
        	    		
        	    	}

        	    	
        	    	
        	    	
        	    }
        	    
        	    
        	    getscreenshot("Room Cancel");
        	  driver.quit();
        	  popupmessage("E2E GUI Automation","HTML report Generation",5000);
        	    System.out.println("done...");
        	    report.endTest(logger);
        	    report.flush();
        	    popupmessage("E2E GUI Automation","Test Execution Done'",5000);
        	    }
        	    catch(Exception e)
        	    {
        	        e.printStackTrace();
        	    }
        	    
          
          
          
	}
public void popupmessage(String dialogname,String Message,int Timervalue)
{
	JOptionPane pane=new JOptionPane(Message,JOptionPane.INFORMATION_MESSAGE);
	final JDialog dialog=pane.createDialog(dialogname);
	dialog.addWindowListener(null);
	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	Timer timer=new Timer(Timervalue,new ActionListener()
			{
		public void actionPerformed(ActionEvent e)
		{
			dialog.setVisible(false);
			dialog.dispose();
		}
			});
	timer.start();
	dialog.setVisible(true);
}
public void Login() throws Exception
{
	System.out.println("***Login****");
	
	 driver.get("http://adactin.com/HotelApp/index.php");
	 logger.log(LogStatus.INFO,"Navigated to Adactin Site");
	 driver.manage().window().maximize();
	 
	driver.findElement(By.id("username")).sendKeys("Username");
	 logger.log(LogStatus.INFO,"Entered Username");
	
	Thread.sleep(3000);
	driver.findElement(By.id("password")).sendKeys("Password");
	 logger.log(LogStatus.INFO,"Entered password");
	Thread.sleep(3000);
	getscreenshot("Entered User Credentials");
	driver.findElement(By.id("login")).click();
	 logger.log(LogStatus.INFO,"Click Login button");
}

public void Search() throws Exception
{

	Thread.sleep(5000);
/*
	WebElement myDynamicElement = (new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeSelected(locator)(By.id("location")));
	
Select ss = new Select(myDynamicElement);
ss.selectByIndex(2);*/
	
	Select ss = new Select(driver.findElement(By.name("location")));
	ss.selectByIndex(2);
	 logger.log(LogStatus.INFO,"Select Location");
Select ss1 = new Select(driver.findElement(By.name("hotels")));
ss1.selectByIndex(2);
logger.log(LogStatus.INFO,"Select hotels");
Select ss2 = new Select(driver.findElement(By.id("room_type")));
ss2.selectByIndex(2);
logger.log(LogStatus.INFO,"Select roomtype");
Select ss3 = new Select(driver.findElement(By.id("room_nos")));
ss3.selectByIndex(2);
logger.log(LogStatus.INFO,"Select room nos");
Select ss4 = new Select(driver.findElement(By.id("adult_room")));
ss4.selectByIndex(2);
logger.log(LogStatus.INFO,"Select adult_room");
Select ss5 = new Select(driver.findElement(By.id("child_room")));
ss5.selectByIndex(2);
logger.log(LogStatus.INFO,"Select childroom");

getscreenshot("Room Search");
driver.findElement(By.id("Submit")).click();
logger.log(LogStatus.INFO,"Click Submit");
}
public void Book() throws Exception
{
	driver.findElement(By.id("radiobutton_0")).sendKeys(Keys.SPACE);
	logger.log(LogStatus.INFO,"Select radiobutton");

	driver.findElement(By.id("continue")).sendKeys(Keys.ENTER);
	logger.log(LogStatus.INFO,"clicked continue");

	driver.findElement(By.id("first_name")).sendKeys("Automation");
	logger.log(LogStatus.INFO,"Enter first name");

	driver.findElement(By.id("last_name")).sendKeys("Verizon");
	logger.log(LogStatus.INFO,"enter lastname");

	driver.findElement(By.id("address")).sendKeys("Chennai");
	logger.log(LogStatus.INFO,"enter address");

	driver.findElement(By.id("cc_num")).sendKeys("1234567891234567");
	logger.log(LogStatus.INFO,"enter cc number");

	Select ss6 = new Select(driver.findElement(By.id("cc_type")));
	ss6.selectByIndex(2);
	logger.log(LogStatus.INFO,"enter cctype");

	Select ss7 = new Select(driver.findElement(By.id("cc_exp_month")));
	ss7.selectByIndex(2);
	logger.log(LogStatus.INFO,"enter expmonth");

	Select ss8 = new Select(driver.findElement(By.id("cc_exp_year")));
	ss8.selectByIndex(2);
	logger.log(LogStatus.INFO,"enter ccexpyear");

	Select ss9 = new Select(driver.findElement(By.id("cc_exp_year")));
	ss9.selectByIndex(2);


	driver.findElement(By.id("cc_cvv")).sendKeys("110");
	logger.log(LogStatus.INFO,"enter cvv");
	getscreenshot("RoomBooked");
	driver.findElement(By.id("book_now")).sendKeys(Keys.ENTER);
	Thread.sleep(5000);
	logger.log(LogStatus.INFO,"clicked book now");
	getscreenshot("Booking confirmation");
	h_name = driver.findElement(By.name("hotel_name")).getAttribute("value");
	loc = driver.findElement(By.name("location")).getAttribute("value");
	r_type = driver.findElement(By.name("room_type")).getAttribute("value");
	a_date = driver.findElement(By.name("arrival_date")).getAttribute("value");
	d_date = driver.findElement(By.name("departure_date")).getAttribute("value");
	total_rooms = driver.findElement(By.name("total_rooms")).getAttribute("value");
	final_price = driver.findElement(By.name("final_price")).getAttribute("value");
	first_name = driver.findElement(By.name("first_name")).getAttribute("value");
	last_name = driver.findElement(By.name("last_name")).getAttribute("value");

	

	String auth = new String(Base64.encode("adhitya:polkmniujh"));
		if(Execution.isEmpty()){
		

	String createIssueData = "{\"fields\":{\"project\":{\"key\":\"HAC\"},\"summary\":\"Lastname field is not displayed\",\"issuetype\":{\"name\":\"Bug\"}}}";
	String issue = invokePostMethod(auth, BASE_URL+"/rest/api/2/issue", createIssueData);
	System.out.println(issue);
	JSONObject issueObj = new JSONObject(issue);
	newKey = issueObj.getString("key");
	System.out.println("Key:"+newKey);
	Execution="Done";
	popupmessage("E2E GUI Automation","Issue "+newKey+" is created in JIRA",5000);
		}
		else
		{
			invokeDeleteMethod(auth, BASE_URL+"/rest/api/2/issue/"+newKey);
			popupmessage("E2E GUI Automation","Issue "+newKey+" is closed in JIRA",5000);
		}
	
			
	order = driver.findElement(By.name("order_no")).getAttribute("value");
	database();
	
}
private static void invokeDeleteMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
	Client client = Client.create();
	WebResource webResource = client.resource(url);
	ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
			.accept("application/json").delete(ClientResponse.class);
	int statusCode = response.getStatus();
	if (statusCode == 401) {
		throw new AuthenticationException("Invalid Username or Password");
	}
}
public void Cancel () throws Exception
{
	driver.findElement(By.linkText("Booked Itinerary")).click();
	logger.log(LogStatus.INFO,"clicked booked itinerary");
	try
	{
	driver.findElement(By.id("check_all")).click();
	}
	catch(Exception e)
	{
	
	List<WebElement> ch = driver.findElements(By.xpath(".//input[@type='checkbox']"));
	for(WebElement ele : ch)
	{
		ele.click();
	}
	}
	driver.findElement(By.name("cancelall")).click();
  Thread.sleep(3000);
  driver.switchTo().alert().accept();
  try{
  driver.switchTo().alert().accept();
  }
  catch(Exception e){
	  
  }
	Robot rb = new Robot();
	rb.keyPress(KeyEvent.VK_ENTER);
	//rb.keyRelease(keycode);
	
	logger.log(LogStatus.INFO,"canceled all");
	
}

public void getscreenshot(String screenshot) throws Exception 
{
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File("D:\\"+screenshot+".png"));
        String image=logger.addScreenCapture("D:\\"+screenshot+".png");
        logger.log(LogStatus.PASS,image);
}


public void database() throws SQLException
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Connection conn = null;
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adhitya","Adhitya", "12abcd");
		Statement st=conn.createStatement();
		System.out.println("entered database");
		st.executeUpdate("insert into book_details values('"+h_name+"','"+loc+"','"+r_type+"','"+a_date+"','"+d_date+"','"+total_rooms+"','"+final_price+"','"+first_name+"','"+last_name+"','"+order+"');");
		System.out.println("inserted values");
		//ResultSet rs= st.executeQuery("Select *from book_details");
		/*while(rs.next())
		{
		System.out.println(rs.getString("name"));
		}*/
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	conn.close();
	 popupmessage("E2E GUI Automation","Booking details inserted into Mysql Database",5000);

}
private static String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
	Client client = Client.create();
	WebResource webResource = client.resource(url);
	ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
			.accept("application/json").post(ClientResponse.class, data);
	int statusCode = response.getStatus();
	if (statusCode == 401) {
		throw new AuthenticationException("Invalid Username or Password");
	}
	return response.getEntity(String.class);
}

}