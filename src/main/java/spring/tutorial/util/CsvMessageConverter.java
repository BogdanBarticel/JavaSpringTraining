package spring.tutorial.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import spring.tutorial.model.pojo.StockPojo;

import java.io.IOException;
import java.lang.reflect.Type;

public class CsvMessageConverter<T> extends AbstractGenericHttpMessageConverter<T> {

    public CsvMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        final CsvMapper mapper = new CsvMapper();
        final CsvSchema schema = mapper.schemaFor(clazz);
        return mapper.readerFor(clazz).with(schema).readValue(inputMessage.getBody());
    }

    @Override
    protected void writeInternal(T arg, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }



    @Override
    public Object read(Type type, Class contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
}
