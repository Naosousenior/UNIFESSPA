package servicosTecnicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Env {
    private static final Map<String, String> envMap = new HashMap<>();

    // Carrega o arquivo .env uma vez no início da aplicação
    public static void load(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                // Ignora linhas vazias e comentários
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] keyValue = line.split("=", 2);
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        // Remove aspas se presentes
                        if (value.startsWith("\"") && value.endsWith("\"")) {
                            value = value.substring(1, value.length() - 1);
                        }
                        envMap.put(key, value);
                    }
                }
            }
        }
    }

    // Obtém um valor do ambiente
    public static String get(String key) {
        return envMap.get(key);
    }

    // Obtém um valor com fallback padrão
    public static String get(String key, String defaultValue) {
        return envMap.getOrDefault(key, defaultValue);
    }
}
