package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectsForTask {

    private static WebElement element = null;
    private static List<WebElement> elements = null;

    public static List<WebElement> headerMenuItems(WebDriver driver){
        elements = driver.findElements(By.cssSelector("a[class='dropdown-toggle']"));
        return elements;
    }

    public static WebElement simpleFormLink(WebDriver driver){
        element = driver.findElement(By.cssSelector("a[href='./basic-first-form-demo.html']"));
        return element;
    }

    public static WebElement checkBoxFormLink(WebDriver driver){
        element = driver.findElement(By.cssSelector("a[href='./basic-checkbox-demo.html']"));
        return element;
    }

    public static WebElement ajaxFormLink(WebDriver driver){
        element = driver.findElement(By.cssSelector("a[href='./ajax-form-submit-demo.html']"));
        return element;
    }

    public static WebElement alertFormLink(WebDriver driver){
        element = driver.findElement(By.cssSelector("a[href='./bootstrap-alert-messages-demo.html']"));
        return element;
    }

    public static WebElement enterATextbox(WebDriver driver){
        element = driver.findElement(By.id("sum1"));
        return element;
    }

    public static WebElement enterBTextbox(WebDriver driver){
        element = driver.findElement(By.id("sum2"));
        return element;
    }

    public static WebElement nameTextbox(WebDriver driver){
        element = driver.findElement(By.cssSelector("input[class='form-control']"));
        return element;
    }

    public static WebElement commentTextArea(WebDriver driver){
        element = driver.findElement(By.cssSelector("textarea[class='form-control']"));
        return element;
    }

    public static WebElement getTotalButton(WebDriver driver){
        element = driver.findElement(By.cssSelector("button[onclick='return total()']"));
        return element;
    }

    public static WebElement submitButton(WebDriver driver){
        element = driver.findElement(By.id("btn-submit"));
        return element;
    }

    public static WebElement autoCloseableSuccess(WebDriver driver){
        element = driver.findElement(By.id("autoclosable-btn-success"));
        return element;
    }

    public static WebElement totalValue(WebDriver driver){
        element = driver.findElement(By.id("displayvalue"));
        return element;
    }

    public static WebElement singleCheckbox(WebDriver driver){
        element = driver.findElement(By.id("isAgeSelected"));
        return element;
    }

    public static WebElement successMessage(WebDriver driver){
        element = driver.findElement(By.id("txtAge"));
        return element;
    }

    public static WebElement submitSuccess(WebDriver driver){
        element = driver.findElement(By.id("submit-control"));
        return element;
    }

}