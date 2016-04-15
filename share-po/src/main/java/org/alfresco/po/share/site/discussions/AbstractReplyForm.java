package org.alfresco.po.share.site.discussions;

import org.alfresco.po.PageElement;
import org.alfresco.po.share.FactorySharePage;
import org.alfresco.po.share.exception.ShareException;
import org.alfresco.po.share.site.document.TinyMceEditor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Reply form page object
 * relating to Share Discussions page
 *
 * @author Marina Nenadovets
 */
@SuppressWarnings("unused")
public abstract class AbstractReplyForm extends PageElement
{
    private Log logger = LogFactory.getLog(this.getClass());

    private TinyMceEditor tinyMceEditor;
    private static final By SUBMIT_BTN = By.cssSelector("span[class~='yui-submit-button']");
    private static final By CANCEL_BTN = By.cssSelector("span[class~='yui-push-button']");


    public TinyMceEditor getTinyMceEditor()
    {
        return tinyMceEditor;
    }

    protected void click(By locator)
    {
        WebElement element = findAndWait(locator);
        element.click();
    }

    /**
     * Method for inserting text into the Reply form
     *
     * @param txtLines
     */
    public void insertText(String txtLines)
    {
        try
        {
            waitUntilElementClickable(SUBMIT_BTN, getDefaultWaitTime());
            tinyMceEditor.setText(txtLines);
        }
        catch (TimeoutException toe)
        {
            throw new ShareException("Time out finding #tinymce", toe);
        }
    }

    /**
     * Method for clicking Submit button
     *
     * @return Topic view page
     */
    public TopicViewPage clickSubmit()
    {
        try
        {
            findAndWait(SUBMIT_BTN).click();
            return factoryPage.instantiatePage(driver, TopicViewPage.class).render();
        }
        catch (NoSuchElementException nse)
        {
            throw new ShareException("Cannot find Submit button");
        }
        catch (TimeoutException te)
        {
            throw new ShareException("The operation has timed out");
        }
    }

}
