package meetme.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.sql.Timestamp;

public class LocalDateDeserializer extends StdDeserializer<Timestamp> {

    protected LocalDateDeserializer() {
        super(Timestamp.class);
    }

    @Override
    public Timestamp deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return new Timestamp(parser.readValueAs(Long.class));
    }
}