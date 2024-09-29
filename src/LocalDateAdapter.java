import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParseException;
import java.time.LocalDate;

public class LocalDateAdapter {
    public static final JsonSerializer<LocalDate> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.toString());

    public static final JsonDeserializer<LocalDate> DESERIALIZER = (json, typeOfT, context) -> {
        try {
            return LocalDate.parse(json.getAsString());
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    };
}