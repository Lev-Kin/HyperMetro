package metro.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class JsonUtils {

    private JsonUtils() {
    }

    public static JsonObject parseFromFile(Path path) throws IOException {
        try (Reader reader = Files.newBufferedReader(path)) {
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(reader);
            return json.getAsJsonObject();
        }
    }
}
