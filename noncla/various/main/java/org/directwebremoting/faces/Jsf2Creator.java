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

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.extend.Creator;

/**
 * This is a DWR creator implementation, to allow dwr beans to be allocated into
 * JSF scopes and into jeffs3 specific scope (i.e. the flow scope).
 * JSF 2 and above.
 *
 * @author Pierpaolo Follia
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 * @author Vinicius Melo [vdmelo at gmail dot com]
 * @author David Marginian [david at butterdev dot com]
 */
public class Jsf2Creator extends AbstractJsfCreator implements Creator
{
    /* (non-Javadoc)
     * @see org.directwebremoting.Creator#getInstance()
     */
    @Override
    public Object getInstance() throws InstantiationException
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null)
        {
            log.error("Object " + getManagedBeanName() + " cannot be created since the faces context is null");
            return null;
        }
        Object resolvedObject = null;
        if (isVBExpression(getManagedBeanName()))
        {
            ValueExpression ve = facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(), "#{" + getManagedBeanName() + "}", Object.class);
            if (null != ve)
            {
                resolvedObject = ve.getValue(facesContext.getELContext());
            }
        }
        else
        {
            resolvedObject = facesContext.getELContext().getELResolver().getValue(facesContext.getELContext(), null, getManagedBeanName());
        }
        return resolvedObject;
    }

    /**
     * The log stream
     */
    private static final Log log = LogFactory.getLog(Jsf2Creator.class);
}
