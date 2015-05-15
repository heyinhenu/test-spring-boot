package demo.views;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import demo.ext.HandlebarsHelper;
import demo.ext.JavaLogable;

@Component
@HandlebarsHelper("formatDate")
public class FormatDateHelper implements Helper<Date>, JavaLogable {

    @Override
    public CharSequence apply(Date context, Options options) throws IOException {
        if (context == null) {
            return "";
        }
        String pattern = options.param(0, "yyyy-MM-dd");
        return new SimpleDateFormat(pattern).format(context);
    }
}
