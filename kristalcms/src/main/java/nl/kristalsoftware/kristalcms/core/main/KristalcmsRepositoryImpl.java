package nl.kristalsoftware.kristalcms.core.main;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jcr.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 24-05-15.
 */
//@ApplicationScoped
@Singleton
@Startup
public class KristalcmsRepositoryImpl implements BaseRepository {

    @Inject
    private Logger logger;

//    @Resource(mappedName="java:/jcr/kristalcms")
//    private Repository repository;

    @Inject
    Session session;

    public KristalcmsRepositoryImpl() {}

    @PostConstruct
    public void init() {
        logger.info("init postconstruct in KristalcmsRepositoryImpl");
        try {
//            Session session = this.createSession();
            Node rootNode = session.getRootNode();
            if (!rootNode.hasNode("cms")) {
                logger.info("No cms node available, import.....");
                this.importXML(session, "kristalcms.xml");
            }
            else {
                logger.info("The cms node found");
                this.exportXML(session);
            }
//            this.logoutSession(session);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

//    @RequestScoped
//    @Produces
//    public Session createSession() {
//        Session session = null;
//        try {
//            logger.info("createSession");
//            session = repository.login();
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        }
//        return session;
//    }
//
//    public void logoutSession(@Disposes final Session session) {
//        logger.info("logoutSession");
//        session.logout();
//    }
//
    private void importXML(Session session, String xmlFileName) {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream is = loader.getResourceAsStream(xmlFileName);
        if (is != null) {
            try {
                session.importXML("/", is, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
                session.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("The file " + xmlFileName + " not found.");
        }
    }

    private void exportXML(Session session) {
        try {
            session.exportSystemView("/cms", new FileOutputStream(new File("/Volumes/LaCie/tmp_output/kristalbootstrap/kristalcms/cms.xml")), false, false);
            //session.exportSystemView("/config", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/config.xml")), false, false);
            //session.exportSystemView("/orders", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/orders.xml")), false, false);
            //session.exportSystemView("/countries", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/dealers.xml")), false, false);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}
