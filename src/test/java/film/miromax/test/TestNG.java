package film.miromax.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {
    String name;
    public TestNG(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test(dependsOnMethods = "test2")
    public void test1() {;
    }

    @Test(priority = 0)
    public void test2() {
        System.out.println(5 / 0);
    }

    @Test(dependsOnMethods = {"test2"},
            alwaysRun = true)
    public void testWillAlwaysRun() {
        System.out.println("test depends on failed test, but it runs anyway");
        System.out.println(beforeMethod());
    }

    @BeforeMethod
    public String beforeMethod() {
        System.out.println("Before test");
        TestNG test = new TestNG("Some name");
        return test.getName();
    }

    @AfterMethod
    public void afterMethod() {

    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }
}