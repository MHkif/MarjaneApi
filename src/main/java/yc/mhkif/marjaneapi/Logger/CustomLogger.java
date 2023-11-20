package yc.mhkif.marjaneapi.Logger;


import com.fasterxml.jackson.databind.ObjectMapper;
        import org.springframework.stereotype.Component;

        import java.io.FileWriter;
        import java.io.IOException;
        import java.time.LocalDateTime;
        import java.util.HashMap;
        import java.util.Map;

@Component
public class CustomLogger {

    private final ObjectMapper objectMapper;

    public CustomLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void logAction(String methodName, String action) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("timestamp", LocalDateTime.now());
        logData.put("method", methodName);
        logData.put("action", action);

        try (FileWriter fileWriter = new FileWriter("src/main/logger.json", true)) {
            String logEntry = objectMapper.writeValueAsString(logData);
            fileWriter.write("\n");
            fileWriter.write(logEntry);
            //objectMapper.writeValue(fileWriter, logData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void closeLogger() {
        try (FileWriter fileWriter = new FileWriter("src/main/logger.json", true)) {
            fileWriter.write(",\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
