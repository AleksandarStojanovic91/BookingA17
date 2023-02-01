package pages.kp;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;

public class KPCategoryPage extends BasePage {

    public KPCategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    String categoryTab = "//ol[contains(@class,'Tabs_tabList')]//a[text()='$$']";

    public void verifyCategoryTab(String category){
        Assert.assertEquals(driver.findElement(By.xpath(categoryTab.replace("$$",category))).getText(),category);
    }

    public void verifyCategoryResults(){
        Assert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'IndexPage_content')]")).size() > 0, "Verify result columns");
        Assert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'IndexPage_content')]/div[1]")).size() > 0, "Verify column 1 results");
        Assert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'IndexPage_content')]/div[2]")).size() > 0, "Verify column 1 results");
    }

    public void verifyURL(String category) {
        Assert.assertTrue(driver.getCurrentUrl().contains(formatStringToURL(category)),"Verify url for category: "+category);
    }

    public String formatStringToURL(String category){
        String categoryURLFormat = category.replace(",","").toLowerCase();

        if(category.contains(" | ")){
            categoryURLFormat = categoryURLFormat.replace(" | ","-");
        }

        if(categoryURLFormat.contains(" ")){
            categoryURLFormat = categoryURLFormat.replace(" ","-");
        }

//        String[] searchLetters = {"š","č","ć","đ","ž"};
//        String[] replaceLetters = {"s","c","c","dj","z"};
//
//        for(int i = 0; i<searchLetters.length; i++){
//            if(categoryURLFormat.contains(searchLetters[i])){
//                categoryURLFormat = categoryURLFormat.replace(searchLetters[i],replaceLetters[i]);
//            }
//        }

        String normalized = StringUtils.stripAccents(categoryURLFormat);
        System.out.println("Original: "+category);
        System.out.println("Normalized: "+normalized);
        return normalized;
    }

}