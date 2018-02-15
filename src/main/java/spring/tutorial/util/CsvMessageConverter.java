package spring.tutorial.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CsvMessageConverter extends AbstractGenericHttpMessageConverter<List> {

    public CsvMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected void writeInternal(List list, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected List readInternal(Class<? extends List> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        final CsvMapper mapper = new CsvMapper();
        final CsvSchema schema = mapper.schemaFor(clazz);
        return mapper.readerFor(clazz).with(schema).readValue(inputMessage.getBody());
    }

    @Override
    public List read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
}
