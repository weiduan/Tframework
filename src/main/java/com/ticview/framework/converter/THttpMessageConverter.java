package com.ticview.framework.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticview.framework.response.ResponseUtil;
import com.ticview.framework.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by weiduan on 2015/11/26.
 */
public class THttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(THttpMessageConverter.class);

    private final String LOG_PATTERN = "返回给数据:{%s}";

    private String jsonPrefix;

    public THttpMessageConverter() {
        this(Jackson2ObjectMapperBuilder.json().build());
    }

    public THttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, MediaType.APPLICATION_JSON_UTF8,
                new MediaType("application", "*+json", DEFAULT_CHARSET));
    }

    public THttpMessageConverter(ObjectMapper objectMapper, MediaType... supportedMediaTypes) {
        super(objectMapper, supportedMediaTypes);
    }

    public void setJsonPrefix(String jsonPrefix) {
        this.jsonPrefix = jsonPrefix;
    }

    public void setPrefixJson(boolean prefixJson) {
        this.jsonPrefix = (prefixJson ? ")]}', " : null);
    }

    @Override
    protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
        if (this.jsonPrefix != null) {
            generator.writeRaw(this.jsonPrefix);
        }
        String jsonpFunction =
                (object instanceof MappingJacksonValue ? ((MappingJacksonValue) object).getJsonpFunction() : null);
        if (jsonpFunction != null) {
            generator.writeRaw("/**/");
            generator.writeRaw(jsonpFunction + "(");
        }
    }

    @Override
    protected void writeSuffix(JsonGenerator generator, Object object) throws IOException {
        String jsonpFunction =
                (object instanceof MappingJacksonValue ? ((MappingJacksonValue) object).getJsonpFunction() : null);
        if (jsonpFunction != null) {
            generator.writeRaw(");");
        }
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (!(object instanceof Result)) {
            object = ResponseUtil.getSuccessResult(object);
        }
        System.out.println(String.format(LOG_PATTERN, object));
        logger.debug(String.format(LOG_PATTERN, object));
        super.writeInternal(object, type, outputMessage);
    }
}
