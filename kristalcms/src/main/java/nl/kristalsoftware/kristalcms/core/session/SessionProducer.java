package nl.kristalsoftware.kristalcms.core.session;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 09/12/15.
 */
@ApplicationScoped
public class SessionProducer {

    @Inject
    private Logger logger;

    @Resource(mappedName="java:/jcr/kristalcms")
    private Repository repository;

    @Produces
    public Session createSession() {
        Session session = null;
        try {
            logger.info("createSession");
            session = repository.login();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return session;
    }

    public void logoutSession(@Disposes final Session session) {
        logger.info("logoutSession");
        session.logout();
    }


}
