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
package org.directwebremoting.server.servlet2;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.extend.ContainerAbstraction;
import org.directwebremoting.extend.RealScriptSession;
import org.directwebremoting.extend.ScriptConduit;
import org.directwebremoting.extend.ServerLoadMonitor;
import org.directwebremoting.extend.Sleeper;
import org.directwebremoting.impl.DefaultServerLoadMonitor;
import org.directwebremoting.impl.ThreadWaitSleeper;

/**
 * An abstraction of the servlet container that just follows the standards
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class Servlet24ContainerAbstraction implements ContainerAbstraction
{
    /* (non-Javadoc)
     * @see org.directwebremoting.dwrp.ContainerAbstraction#isNativeEnvironment(javax.servlet.ServletConfig)
     */
    public boolean isNativeEnvironment(ServletConfig servletConfig)
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.dwrp.ContainerAbstraction#getServerLoadMonitorImplementation()
     */
    public Class<? extends ServerLoadMonitor> getServerLoadMonitorImplementation()
    {
        return DefaultServerLoadMonitor.class;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.extend.ContainerAbstraction#isResponseCompleted(javax.servlet.http.HttpServletRequest)
     */
    public boolean handleResumedRequest(HttpServletRequest request)
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.extend.ContainerAbstraction#createSleeper()
     */
    public Sleeper createSleeper(HttpServletRequest request, HttpServletResponse response, RealScriptSession scriptSession, ScriptConduit conduit) throws IOException
    {
        return new ThreadWaitSleeper(response, scriptSession, conduit);
    }
}
