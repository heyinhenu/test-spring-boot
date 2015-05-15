package demo.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface JavaLogable {

    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
