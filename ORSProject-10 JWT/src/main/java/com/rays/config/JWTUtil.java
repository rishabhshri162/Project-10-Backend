package com.rays.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JWTUtil class for handling JWT operations.
 * 
 * This class provides functionality for:
 * - Generating JWT tokens
 * - Validating JWT tokens
 * - Extracting claims (loginId, userId, role)
 * 
 * It uses HMAC SHA256 algorithm for signing tokens.
 * 
 * @author Rishabh Shrivastava
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration; // in milliseconds

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Generates JWT token with user details.
	 * 
	 * @param userId user ID
	 * @param loginId login ID
	 * @param role user role
	 * @return JWT token
	 * @throws Exception if error occurs
	 */
	public String generateToken(Long userId, String loginId, String role) throws Exception {
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + jwtExpiration;

		Map<String, Object> header = new HashMap<>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");

		Map<String, Object> payload = new HashMap<>();
		payload.put("sub", loginId);
		payload.put("userId", userId);
		payload.put("role", role);
		payload.put("iat", nowMillis);
		payload.put("exp", expMillis);

		String headerBase64 = encodeUrl(objectMapper.writeValueAsString(header));
		String payloadBase64 = encodeUrl(objectMapper.writeValueAsString(payload));
		String signatureBase64 = sign(headerBase64 + "." + payloadBase64, jwtSecret);

		return String.join(".", headerBase64, payloadBase64, signatureBase64);
	}

	/**
	 * Validates JWT token.
	 * 
	 * @param token JWT token
	 * @param expectedLoginId expected login ID
	 * @return true if valid
	 * @throws Exception if invalid
	 */
	public boolean validateToken(String token, String expectedLoginId) throws Exception {
		String[] parts = token.split("\\.");
		if (parts.length != 3) {
			throw new Exception("Invalid JWT token");
		}

		String payloadJson = decode(parts[1]);
		String tokenLoginId = extractField(payloadJson, "sub");
		String expectedSignature = sign(parts[0] + "." + parts[1], jwtSecret);

		if (!expectedSignature.equals(parts[2])) {
			throw new Exception("JWT signature does not match");
		}

		if (!expectedLoginId.equals(tokenLoginId)) {
			throw new Exception("JWT subject (loginId) does not match");
		}

		if (isTokenExpired(payloadJson)) {
			throw new Exception("JWT token has expired");
		}

		return true;
	}

	/**
	 * Extracts loginId from token.
	 */
	public String extractLoginId(String token) {
		return extractField(decode(token.split("\\.")[1]), "sub");
	}

	/**
	 * Extracts userId from token.
	 */
	public Long extractUserId(String token) {
		return Long.parseLong(extractField(decode(token.split("\\.")[1]), "userId"));
	}

	/**
	 * Extracts role from token.
	 */
	public String extractRole(String token) {
		return extractField(decode(token.split("\\.")[1]), "role");
	}

	/**
	 * Checks if token is expired.
	 */
	private boolean isTokenExpired(String payloadJson) {
		long exp = Long.parseLong(extractField(payloadJson, "exp"));
		return exp < (System.currentTimeMillis() / 1000);
	}

	/**
	 * Extracts field value from JSON payload.
	 */
	private String extractField(String json, String field) {
		try {
			Map<String, Object> map = objectMapper.readValue(json, Map.class);
			return String.valueOf(map.get(field));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Generates HMAC SHA256 signature.
	 */
	private String sign(String data, String key) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
		return encodeUrl(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
	}

	/**
	 * Encodes string to Base64 URL format.
	 */
	private String encodeUrl(String data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Encodes byte array to Base64 URL format.
	 */
	private String encodeUrl(byte[] data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
	}

	/**
	 * Decodes Base64 URL string.
	 */
	private String decode(String data) {
		return new String(Base64.getUrlDecoder().decode(data), StandardCharsets.UTF_8);
	}
}