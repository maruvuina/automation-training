package com.epam.ta.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 50;
    protected WebDriver driver;

    public AbstractPage (WebDriver driver) {
        this.driver = driver;
    }

    public abstract AbstractPage openPage();
}
