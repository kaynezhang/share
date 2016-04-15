package org.alfresco.po.share.site.document;

import java.io.File;
import java.io.IOException;

import org.alfresco.po.AbstractTest;
import org.alfresco.po.share.site.SitePage;
import org.alfresco.po.share.site.UploadFilePage;

import org.alfresco.test.FailedTestListener;
import org.alfresco.po.exception.PageRenderTimeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Basic integration test to verify that document previews are using the PdfJs viewer.
 * 
 * <p>Tests PDF, DOC, DOCX and TXT file types</p>
 * 
 * @author Will Abson
 */
@Listeners(FailedTestListener.class)
@Test(groups={"alfresco-one"})
public class DocumentDetailsPagePreviewTest extends AbstractDocumentTest
{
    private String siteName;
    private SitePage site;
    private static Log logger = LogFactory.getLog(AbstractTest.class);

    /**
     * Pre test setup of a dummy file to upload.
     * 
     * @throws Exception
     */
    @BeforeClass
    public void prepare() throws Exception
    {
        siteName = "Site-1" + System.currentTimeMillis();
        loginAs(username, password);
        siteUtil.createSite(driver, username, password, siteName, "", "Public");
        site = resolvePage(driver).render();
    }

    @AfterClass
    public void deleteSite()
    {
        siteUtil.deleteSite(username, password, siteName);
    }

    /**
     * Test that a PDF document is correctly displayed using the PdfJs previewer
     * 
     * @throws IOException
     */
    @Test
    public void testPdfPreview() throws Exception
    {
        File file1 = siteUtil.prepareFile("File-1"+System.currentTimeMillis(), "This is a sample file", ".pdf");
        DocumentLibraryPage documentLibPage = site.getSiteNav().selectDocumentLibrary().render();
        UploadFilePage uploadForm = documentLibPage.getNavigation().selectFileUpload().render();
        documentLibPage = uploadForm.uploadFile(file1.getCanonicalPath()).render();
        documentLibPage.render();
        DocumentDetailsPage docDetailsPage = selectDocument(file1).render();

        if(logger.isDebugEnabled())
        {
            logger.debug("Previewer class name: " + docDetailsPage.getPreviewerClassName());
        }

        Assert.assertTrue(docDetailsPage.isDocumentDetailsPage());
        Assert.assertTrue(docDetailsPage.getPreviewerClassName().endsWith("PdfJs"));

        try
        {
            docDetailsPage.getPdfJsPreview();
        }
        catch(PageRenderTimeException e)
        {
            Assert.assertTrue(false, "PdfJs preview could not be rendered");
        }
    }

}
