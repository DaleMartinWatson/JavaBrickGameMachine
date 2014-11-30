package ua.ilnicki.jbgm.data.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;
import ua.ilnicki.jbgm.data.DataCluster;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DataClusterSerialiser implements JsonSerializer<DataCluster>, JsonDeserializer<DataCluster>
{

    @Override
    public JsonElement serialize(DataCluster dataCluster, Type typeOfSrc, JsonSerializationContext context)
    {
        final JsonArray rootJsonObject = new JsonArray();

        HashMap<String, Object> dataSet;

        try
        {
            Field f = dataCluster.getClass().getDeclaredField("dataSet");
            f.setAccessible(true);
            dataSet = (HashMap) f.get(dataCluster);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            throw new JsonSyntaxException(e);
        }

        for (Entry<String, Object> entry : dataSet.entrySet())
        {
            final JsonObject entryJsonObject = new JsonObject();
            
            entryJsonObject.addProperty("key", entry.getKey());
            entryJsonObject.addProperty("type", entry.getValue().getClass().getName());
            entryJsonObject.add("value", context.serialize(entry.getValue()));
            
            rootJsonObject.add(entryJsonObject);
        }
        
        return rootJsonObject;
    }

    @Override
    public DataCluster deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        HashMap<String, Object> dataSet = new HashMap<>();
        
        for(JsonElement element : json.getAsJsonArray())
        {
            try
            {
                JsonObject object = element.getAsJsonObject();
                
                String key = object.get("key").getAsString();
                Class type = Class.forName(object.get("type").getAsString());
                Object value = context.deserialize(object.get("value"), type);
                
                dataSet.put(key, value);
            }
            catch (ClassNotFoundException e)
            {
                throw new JsonParseException(e);
            }
        }
        
        return new DataCluster(dataSet);
    }

}
