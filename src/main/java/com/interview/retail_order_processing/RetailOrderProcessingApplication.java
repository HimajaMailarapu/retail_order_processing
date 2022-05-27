package com.interview.retail_order_processing;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class RetailOrderProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailOrderProcessingApplication.class, args);
	}

	@SuppressWarnings("unchecked")
	private static Boolean setupTokens() {
		LoggerFactory.getLogger("setupTokens").info("Inside setupTokens");
		try {
			if (System.getenv().containsKey("b64secrets")) {
				List secretNames = Arrays.asList(System.getenv("b64secrets").split(","));
				secretNames.forEach(name -> {
					try {
						LoggerFactory.getLogger("setupTokens").info(String.format("Reading secret: %s", name));
						String b64 = System.getenv((String) name);
						LoggerFactory.getLogger("setupTokens")
								.info(String.format("Secret length: %d", b64.length()));
						byte[] decoded = Base64.getDecoder().decode(b64);
						Files.write(Paths.get("/tmp/" + name), decoded);
						LoggerFactory.getLogger("setupTokens").info(String.format("Wrote to: /tmp/%s", name));
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				});
			} else {
				LoggerFactory.getLogger("setupTokens").error("no b64 secrets found");
				return false;
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
