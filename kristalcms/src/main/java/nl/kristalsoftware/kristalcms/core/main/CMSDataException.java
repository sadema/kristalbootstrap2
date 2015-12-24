package nl.kristalsoftware.kristalcms.core.main;

/**
 * Created by sjoerdadema on 16-09-15.
 */
public class CMSDataException extends Exception {
    public CMSDataException() {
        super();
    }

    public CMSDataException(String message) {
        super(message);
    }

    public CMSDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CMSDataException(Throwable cause) {
        super(cause);
    }

    protected CMSDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
