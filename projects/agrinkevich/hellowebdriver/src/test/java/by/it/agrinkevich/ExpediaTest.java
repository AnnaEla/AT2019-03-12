package by.it.agrinkevich;

import by.it.agrinkevich.pages.FactoryResultPage;
import by.it.agrinkevich.pages.FactoryStartPage;
import by.it.agrinkevich.pages.ResultPage;
import by.it.agrinkevich.pages.StartPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ExpediaTest {

    private WebDriver driver;


    @Before
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void taskA() throws Exception {
        driver.get("https://www.expedia.com/");

        StartPage startPage = new StartPage(driver);

        ResultPage resultPage = startPage
                .selectSearchFlightMode()
                .selectOneWay()
                .setOrigin("Minsk, Belarus (MSQ-All Airports)")
                .setDestination("Moscow, Russia (MOW-All Airports)")
                .setDepartingDate("05/24/2019")
                .getSearch();

        int resultSearchCount = resultPage.getResultSearchCount();
        Assert.assertTrue(resultSearchCount > 1);
    }

    @Test
    public void taskB() throws InterruptedException, ParseException {
        driver.get("https://www.expedia.com/");

        FactoryStartPage factoryStartPage = new FactoryStartPage(driver);

        factoryStartPage
               /* .openAccountDropDown()
                .openSignInForm()
                .setEmail("rof1c@wimsg.com")
                .setPassword("Aaaa1111")
                .doLogin()
                */
                .selectSearchFlightMode()
                .selectRoundTrip()
                .setOrigin("Minsk, Belarus (MSQ-All Airports)")
                .setDestination("Moscow, Russia (MOW-All Airports)")
                .setDepartingDate(30)
                .setReturningDate(45)
                .setTwoAdult()
                .getSearch();

        FactoryResultPage factoryResultPage = new FactoryResultPage(driver);
        List<WebElement> pricesWebElement = factoryResultPage.getPrices();
        List<String> strPrices = pricesWebElement.stream().map(element -> element.getText()).collect(Collectors.toList());
        List<Integer> num = new ArrayList<>();
        Iterator<String> iter = strPrices.iterator();
        while (iter.hasNext()){
            String str = iter.next();
            str = str.replaceAll("[$,]+","");
            num.add(Integer.parseInt(str));
        }
        Iterator<Integer> iterNum = num.iterator();
        boolean isPriceLower100 = false;
        while (iterNum.hasNext()){
            Integer myInteger = iterNum.next();
            int myInt = myInteger.intValue();
            if (myInt < 100){
                isPriceLower100 = true;
                break;
            }
        }
        Assert.assertEquals("Prices are lower then 100$ ",false, isPriceLower100);

    }

    @After
    public void tearDownBrowser() {
        driver.quit();
    }
}
