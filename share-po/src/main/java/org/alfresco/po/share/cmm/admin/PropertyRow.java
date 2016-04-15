
package org.alfresco.po.share.cmm.admin;

import org.alfresco.po.share.admin.ActionsSet;
import org.openqa.selenium.WebElement;

/**
 * Represents a table row in the Custom Models Properties List View.
 * 
 * @author Richard Smith
 */
public class PropertyRow
{

    private WebElement nameElement;

    private String name;

    private String displayLabel;

    private String datatype;

    private String mandatory;

    private String defaultValue;

    private String multiValue;

    private ActionsSet actions;

    /**
     * Instantiates a new property row.
     */
    public PropertyRow()
    {
    }

    /**
     * Instantiates a new custom model row.
     * 
     * @param name the Custom Model name
     */
    public PropertyRow(String name)
    {
        this.name = name;
    }

    /**
     * Instantiates a new property row.
     * 
     * @param nameElement the name element
     * @param name the name
     * @param type the type
     * @param actions the actions
     */
    public PropertyRow(WebElement nameElement, String displayLabel, String datatype, String mandatory, String defaultValue, String multiValue,
            ActionsSet actions)
    {
        super();
        this.nameElement = nameElement;
        this.name = this.nameElement.getText().trim();
        this.displayLabel = displayLabel;
        this.datatype = datatype;
        this.mandatory = mandatory;
        this.defaultValue = defaultValue;
        this.multiValue = multiValue;
        this.actions = actions;
    }

    /**
     * Gets the name element.
     * 
     * @return the name element
     */
    public WebElement getNameElement()
    {
        return nameElement;
    }

    /**
     * Sets the name element.
     * 
     * @param nameElement the new name element
     */
    public void setNameElement(WebElement nameElement)
    {
        this.nameElement = nameElement;
        this.name = this.nameElement.getText().trim();
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the display label.
     * 
     * @return the display label
     */
    public String getDisplayLabel()
    {
        return displayLabel;
    }

    /**
     * Sets the display label.
     * 
     * @param displayLabel the new display label
     */
    public void setDisplayLabel(String displayLabel)
    {
        this.displayLabel = displayLabel;
    }

    /**
     * Gets the datatype.
     * 
     * @return the datatype
     */
    public String getDatatype()
    {
        return datatype;
    }

    /**
     * Sets the datatype.
     * 
     * @param datatype the new datatype
     */
    public void setDatatype(String datatype)
    {
        this.datatype = datatype;
    }

    /**
     * Gets the mandatory.
     * 
     * @return the mandatory
     */
    public String getMandatory()
    {
        return mandatory;
    }

    /**
     * Sets the mandatory.
     * 
     * @param mandatory the new mandatory
     */
    public void setMandatory(String mandatory)
    {
        this.mandatory = mandatory;
    }

    /**
     * Gets the default value.
     * 
     * @return the default value
     */
    public String getDefaultValue()
    {
        return defaultValue;
    }

    /**
     * Sets the default value.
     * 
     * @param defaultValue the new default value
     */
    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the multi value.
     * 
     * @return the multi value
     */
    public String getMultiValue()
    {
        return multiValue;
    }

    /**
     * Sets the multi value.
     * 
     * @param multiValue the new multi value
     */
    public void setMultiValue(String multiValue)
    {
        this.multiValue = multiValue;
    }

    /**
     * Gets the actions.
     * 
     * @return the actions
     */
    public ActionsSet getActions()
    {
        return actions;
    }

    /**
     * Sets the actions.
     * 
     * @param actions the new actions
     */
    public void setActions(ActionsSet actions)
    {
        this.actions = actions;
    }

}