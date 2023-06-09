/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.directwebremoting.faces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.extend.AbstractCreator;
import org.directwebremoting.util.LocalUtil;

/**
 * This is a DWR creator implementation, to allow dwr beans to be allocated into
 * JSF scopes and into jeffs3 specific scope (i.e. the flow scope)
 * @author Pierpaolo Follia
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 * @author Vinicius Melo [vdmelo at gmail dot com]
 * @author David Marginian [david at butterdev dot com]
 */
public abstract class AbstractJsfCreator extends AbstractCreator
{
    /* (non-Javadoc)
     * @see org.directwebremoting.Creator#getType()
     */
    public Class<?> getType()
    {
        if (instanceType == null)
        {
            try
            {
                instanceType = getInstance().getClass();
            }
            catch (InstantiationException ex)
            {
                log.error("Failed to instansiate object to detect type.", ex);
                return Object.class;
            }
        }
        return instanceType;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.Creator#getInstance()
     */
    public abstract Object getInstance() throws InstantiationException;

    /**
     * Determine whether String is a value binding expression or not.
     * @param expression The expression to test for value bindingness
     * @return true if the expression contains a VB expression
     */
    public static boolean isVBExpression(String expression)
    {
        if (expression == null)
        {
            return false;
        }

        int start = expression.indexOf("#{");
        int end = expression.indexOf('}');

        return start != -1 && start < end;
    }

    /**
     * @return Returns the managedBeanName.
     */
    public String getManagedBeanName()
    {
        return managedBeanName;
    }

    /**
     * @param managedBeanName The managedBeanName to set.
     */
    public void setManagedBeanName(String managedBeanName)
    {
        this.managedBeanName = managedBeanName;
    }

    /**
     * What sort of class do we create?
     * @param classname The name of the class
     */
    public void setClass(String classname)
    {
        try
        {
            this.instanceType = LocalUtil.classForName(classname);
        }
        catch (ClassNotFoundException ex)
        {
            throw new IllegalArgumentException("Creator.ClassNotFound");
        }
    }

    /**
     * The name of the bean to get from the FacesContext
     */
    private String managedBeanName;

    /**
     * The cached type of bean that we are creating
     */
    private Class<?> instanceType;

    /**
     * The log stream
     */
    private static final Log log = LogFactory.getLog(AbstractJsfCreator.class);
}

