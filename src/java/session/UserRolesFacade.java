/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Role;
import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {
    @EJB private RoleFacade roleFacade;
            
    @PersistenceContext(unitName = "SPTV20WebLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public boolean isRole(String roleName, User authUser) {
        List<String> listRoleNames = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :authUser")
                .setParameter("authUser", authUser)
                .getResultList();
        if(listRoleNames.contains(roleName)){
            return true;
        }else{
            return false;
        }
    }
    
}
