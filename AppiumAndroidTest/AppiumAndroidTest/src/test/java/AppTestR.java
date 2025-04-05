import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTestR {
    static AppiumDriver driver;

    public static void main(String[] args) {
        try {
            runClinicAppTest();
            System.out.println("✅ Prueba 1: App clínica ejecutada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error en la prueba 1 (App clínica): " + e.getMessage());
        }

        try {
            runAddAlarmTest();
            System.out.println("✅ Prueba 2: Alarma agregada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error en la prueba 2 (Agregar alarma): " + e.getMessage());
        }

        try {
            runAddTimeZoneTest();
            System.out.println("✅ Prueba 3: Zona horaria agregada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error en la prueba 3 (Zona horaria): " + e.getMessage());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public static void runClinicAppTest() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_x86_64")
                .setUdid("emulator-5554")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("org.simple.clinic.staging")
                .setAppActivity("org.simple.clinic.setup.SetupActivity")
                .setAutoGrantPermissions(true);

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("org.simple.clinic.staging:id/nextButton"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("org.simple.clinic.staging:id/getStartedButton"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='AGREE AND CONTINUE']"))).click();
    }

    public static void runAddAlarmTest() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_x86_64")
                .setUdid("emulator-5554")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.google.android.deskclock")
                .setAppActivity("com.android.deskclock.DeskClock")
                .setAutoGrantPermissions(true);

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.deskclock:id/navigation_bar_item_icon_view"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.deskclock:id/fab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.deskclock:id/material_timepicker_ok_button"))).click();
    }

    public static void runAddTimeZoneTest() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_x86_64")
                .setUdid("emulator-5554")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.google.android.deskclock")
                .setAppActivity("com.android.deskclock.DeskClock")
                .setAutoGrantPermissions(true);

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Clock']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.deskclock:id/fab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.deskclock:id/open_search_view_edit_text"))).click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.deskclock:id/open_search_view_edit_text")));
        searchInput.sendKeys("Rio de Janeiro");

        WebElement rioDeJaneiroOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Rio de Janeiro')]")));
        rioDeJaneiroOption.click();
    }
}
