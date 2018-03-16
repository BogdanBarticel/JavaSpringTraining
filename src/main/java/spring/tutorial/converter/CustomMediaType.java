package spring.tutorial.converter;

import org.springframework.http.MediaType;

public class CustomMediaType {

    private CustomMediaType() {
    }

    public static final String TEXT_CSV = "text/csv";

    public static final MediaType TEXT_CSV_TYPE = new MediaType("text", "csv");
}
