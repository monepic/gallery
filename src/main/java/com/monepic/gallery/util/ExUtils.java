package com.monepic.gallery.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.util.StringUtils;

public final class ExUtils {

    private ExUtils() {}

    /**
     *  <p><b>From JCIP</b> page 98</p>
     * @param t
     * @return t if a RuntimeException, IllegalStateException if a checked Exception. Errors are rethrown
     */
    public static RuntimeException launderThrowable(Throwable t) {

        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not Unchecked", t);
    }


    /**
     * 
     * @param t
     * @return the message or the class name if message is blank
     */
    public static String getMessage(Throwable t) {

        if(t == null) { return null; }

        String msg = t.getMessage();

        return StringUtils.hasText(msg) ? msg : t.getClass().getSimpleName();

    }

    /* from Apache commons */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter  pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}

