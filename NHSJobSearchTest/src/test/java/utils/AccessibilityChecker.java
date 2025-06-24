package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AccessibilityChecker {

    public static void injectLocalAxe(WebDriver driver) throws Exception {
        String axeScript = new String(
            Files.readAllBytes(Paths.get("src/test/resources/axe.min.js")),
            StandardCharsets.UTF_8
        );
        ((JavascriptExecutor) driver).executeScript(axeScript);
    }

    public static void runAxeCheck(WebDriver driver) throws Exception {
        injectLocalAxe(driver);
        String result = (String) ((JavascriptExecutor) driver).executeAsyncScript(
            "const callback = arguments[arguments.length - 1];" +
            "axe.run().then(results => callback(JSON.stringify(results)));"
        );
        System.out.println("Axe Results: " + result);
    }
}
