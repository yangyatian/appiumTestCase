package gaode.gaode.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import basePage.HomePage;
import baseInit.BaseInit;


public class TestSetting  extends BaseInit{
  @Test
  public void testSettingMap(){
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  HomePage homepage = new HomePage(driver);
	  homepage.toSetting();
  }

  @AfterMethod
  public void afterMethod() {
  }

}
