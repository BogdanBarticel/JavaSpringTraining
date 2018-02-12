package spring.tutorial.util;

import org.springframework.http.MediaType;

public class CustomMediaType {

    public final static String TEXT_CSV = "text/csv";

    public final static MediaType TEXT_CSV_TYPE = new MediaType("text", "csv");
}
