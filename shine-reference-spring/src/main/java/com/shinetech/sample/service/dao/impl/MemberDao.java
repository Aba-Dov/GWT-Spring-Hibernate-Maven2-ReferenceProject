package com.shinetech.sample.service.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shinetech.sample.service.domain.Member;

/**
 * The decision not to hide the Data Access Layer behind an interface layer is 
 * deliberate in this architecture. Data is exposed to other systems via the Service 
 * layer and not the Data Layer, even with this design a different database can be 
 * connected to by changing the dialect in the hibernate config. I find the 
 * "in memory" testing for junit tests superior to using the likes of Easy Mock and
 * provides more robust testing.
 * Finally, for years I have coded to the interface of the Data Layer and never 
 * once has this data layer changed implementation to a none hibernate implementation,
 * adding to an extra layer to maintain for no purpose. I believe its important 
 * to code to interfaces "when its appropriate" for loose coupling - and not as 
 * an unmovable design decision which can lead to code bloat.
 * 
 * @version $Id: $
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 */
public class MemberDao extends HibernateDaoSupport 
{
    private static final Log LOG = LogFactory.getLog(MemberDao.class);
    
    /**
     * @see com.shinetech.sample.service.dao.IMemberDao#countAll()
     */
    public long countAll()
    {
        LOG.info("countAll()");
        String hql = "SELECT COUNT(*) FROM " + Member.class.getName();
        Query query = getSession().createQuery(hql);
        Long result = (Long) query.uniqueResult();
        return result.longValue();
        //return getHibernateTemplate().loadAll(Member.class).size();
    }

    /**
     * @see com.shinetech.sample.service.dao.IMemberDao#delete(com.shinetech.sample.service.domain.Member)
     */
    public void delete(Member member)
    {
        LOG.info("delete() : " + member);
        String hql = "DELETE FROM " + Member.class.getName() + " WHERE ID=" + member.getId();
        int result = getSession().createQuery(hql).executeUpdate();
        LOG.info("result : " + result);
        //getHibernateTemplate().delete(member);
    }

    /**
     * Get the object with this id.
     * @param id The id.
     */
    public Member get(long id)
    {
        LOG.info("get() : " + id);
        return (Member)getHibernateTemplate().get(Member.class, id);
    }
    
    /**
     * Save or update this member 
     * @param member The member.
     */
    public void saveOrUpdate(Member member)
    {
        LOG.info("saveOrUpdate(): " + member);
        getHibernateTemplate().saveOrUpdate(member);
        LOG.info("Member saved with ID : " + member.getId());
    }

}
