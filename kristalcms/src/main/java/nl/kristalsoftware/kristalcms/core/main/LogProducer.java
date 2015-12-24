package nl.kristalsoftware.kristalcms.core.main;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 11-04-15.
 */
@ApplicationScoped
public class LogProducer {

    @Produces
    public Logger producer(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }

}
