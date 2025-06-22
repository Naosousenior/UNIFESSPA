package servicosTecnicos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public abstract class Encodador {
	public static String toBase64(Path arquivo) throws IOException {
		byte[] bytes = Files.readAllBytes(arquivo);
		
		return Base64.getEncoder().encodeToString(bytes);
	}
}
