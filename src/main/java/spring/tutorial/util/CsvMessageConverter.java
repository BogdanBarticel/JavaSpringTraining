package spring.tutorial.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import spring.tutorial.exception.OrderNotCreatedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class CsvMessageConverter extends AbstractGenericHttpMessageConverter<List> {

    private static final Logger log = LoggerFactory.getLogger(CsvMessageConverter.class);

    public CsvMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected void writeInternal(List list, Type type, HttpOutputMessage outputMessage) throws IOException {
        ParameterizedType paramType = (ParameterizedType) type;
        try {
            toCSV(Class.forName(paramType.getActualTypeArguments()[0].getTypeName()), list, outputMessage.getBody());
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected List readInternal(Class<? extends List> clazz, HttpInputMessage inputMessage) throws IOException {
        return fromCSV(clazz, inputMessage.getBody());
    }

    @Override
    public List read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException {
        ParameterizedType paramType = (ParameterizedType) type;
        try {
            return fromCSV(Class.forName(paramType.getActualTypeArguments()[0].getTypeName()), inputMessage.getBody());
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage());
        }
        throw new OrderNotCreatedException();
    }

    private void toCSV(Class clazz, List list, OutputStream outputStream) throws IOException {
            final CsvMapper mapper = new CsvMapper();
            final CsvSchema schema = mapper.schemaFor(clazz);
            mapper.writer(schema.withUseHeader(true)).writeValuesAsArray(outputStream).writeAll(list);
            outputStream.close();
    }

    private List fromCSV(Class clazz, InputStream inputStream) throws IOException {
        final CsvMapper mapper = new CsvMapper();
        final CsvSchema schema = mapper.schemaFor(clazz);
        MappingIterator mappingIterator = mapper.readerFor(clazz).with(schema).readValues(inputStream);
        return mappingIterator.readAll();


    }
}
