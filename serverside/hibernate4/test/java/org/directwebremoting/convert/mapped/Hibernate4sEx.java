package org.directwebremoting.convert.mapped;

import java.util.HashSet;
import java.util.Set;

import org.directwebremoting.util.CompareUtil;

/**
 * A hibernate bean to fit the declaration in {@link org.directwebremoting.hibernate.Database}
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class Hibernate4sEx
{
    public Hibernate4sEx()
    {
    }

    public Hibernate4sEx(Integer id)
    {
        this.id = id;
    }

    public Hibernate4sEx(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Hibernate4sEx(Integer id, String name, Set<Hibernate4sNestEx> children)
    {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Hibernate4sNestEx> getChildren()
    {
        return children;
    }

    public void setChildren(Set<Hibernate4sNestEx> child)
    {
        this.children = child;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Hibernate4Ex[id=" + getId() + ",name=" + getName() + ",children=" + getChildren().size() + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj == this)
        {
            return true;
        }

        Class<? extends Hibernate4sEx> thisClass = this.getClass();
        Class<?> thatClass = obj.getClass();
        if (!thisClass.isAssignableFrom(thatClass) && !thatClass.isAssignableFrom(thisClass))
        {
            return false;
        }

        Hibernate4sEx that = (Hibernate4sEx) obj;

        if (!CompareUtil.equals(this.getId(), that.getId()))
        {
            return false;
        }

        // Normally .equals should only have to test PK for equality with a DB
        // but we want our tests to be tighter ...

        if (!CompareUtil.equals(this.getName(), that.getName()))
        {
            return false;
        }

        if (!CompareUtil.equals(this.getChildren(), that.getChildren()))
        {
            return false;
        }

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        int reply = 7423;
        reply += (id == null) ? 382 : id.hashCode();
        reply += (name == null) ? 423 : name.hashCode();
        reply += (children == null) ? 423 : children.hashCode();
        return reply;
    }

    private Integer id;

    private String name;

    private Set<Hibernate4sNestEx> children = new HashSet<Hibernate4sNestEx>();
}
