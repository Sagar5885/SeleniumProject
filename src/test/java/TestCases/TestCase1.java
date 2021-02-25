package TestCases;

import PageObject.BaseClass;
import PageObject.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseClass {
    @Test
    public void home() throws InterruptedException {
        Assert.assertTrue(driver.getTitle().equals("Google"));

        HomePage hp = new HomePage(driver);
        hp.clickAbout();
        Assert.assertTrue(driver.getTitle().equals("Google - About Google, Our Culture & Company News"));
        //Fail this assertion to capture screenshot.

        Thread.sleep(3000);
        logger.info("Test Done!");
    }

}
