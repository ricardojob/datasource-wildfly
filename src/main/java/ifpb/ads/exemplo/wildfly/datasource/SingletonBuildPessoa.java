package ifpb.ads.exemplo.wildfly.datasource;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/03/2017, 11:05:59
 */
@Singleton
@Startup
public class SingletonBuildPessoa {

    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init(){
        em.persist(new Pessoa("Kiko"));
        em.persist(new Pessoa("Chaves"));
        em.persist(new Pessoa("Chiquinha"));
    }
}
