package nl.kristalsoftware.kristalcms.core.session;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 15/12/15.
 */
public class TXEnforcer {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object beginAndCommit(InvocationContext ic) throws Exception {
        try {
            logger.info("start transaction");
            Object retVal = ic.proceed();
            logger.info("commit transaction");
            return retVal;
//        } catch (RollbackException e) {
//            logger.severe("---------caught in interceptor " + e.getCause());
//            throw e;
        } catch (RuntimeException e) {
            logger.severe("rollback please....");
            throw e;
        }
    }

}
