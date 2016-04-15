
package org.alfresco.po.share.steps;

/**
 * Test Class to test UserProfileActions > utils
 * 
 * @author mbhave
 */

import java.io.File;

import org.alfresco.po.AbstractTest;
import org.alfresco.po.share.exception.UnexpectedSharePageException;
import org.alfresco.po.share.user.TrashCanValues;
import org.alfresco.po.exception.PageOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserProfileActionsTest extends AbstractTest
{
	@Autowired SiteActions siteActions;
    @Autowired UserProfileActions userActions;
    private String siteName;
    private File file;

    @BeforeClass(groups = "Enterprise-only")
    public void setup() throws Exception
    {
        siteName = "site" + System.currentTimeMillis();
        file = siteUtil.prepareFile();;
        
        loginAs("admin", "admin");
        
        siteActions.createSite(driver, siteName, siteName, "Public");
        siteActions.openDocumentLibrary(driver);
        siteActions.uploadFile(driver, file);
        siteActions.deleteContentInDocLib(driver, file.getName());
    }
    
    @Test(priority=1)
    public void testNavigateToTrashCan() throws Exception
    {
        try
        {
            // Without navigating to MyProfile page, this action should return UnexpectedSharePageException
            userActions.navigateToTrashCan(driver);
        }
        catch(UnexpectedSharePageException e)
        {
            Assert.assertTrue(e.getMessage().contains("TrashCanPage"));
        }
    }
    
    @Test(priority=2, expectedExceptions=PageOperationException.class)
    public void testDeleteFromTrashCanNoFile() throws Exception
    {
        String fileName = "file" + System.currentTimeMillis();
        userActions.navigateToTrashCan(driver);
        userActions.deleteFromTrashCan(driver, TrashCanValues.FILE, fileName, "documentLibrary");
    }

    
    @Test(priority=3)
    public void testDeleteFromTrashCanFileFound() throws Exception
    {
        userActions.navigateToTrashCan(driver);
        userActions.deleteFromTrashCan(driver, TrashCanValues.FILE, file.getName(), "documentLibrary");
    }
        
}
