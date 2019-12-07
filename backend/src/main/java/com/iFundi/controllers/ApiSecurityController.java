package com.iFundi.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.iFundi.config.ResourceConfig;
import com.iFundi.models.ApiResponse;
import com.iFundi.models.ApiSecurity;
import com.iFundi.repositories.ApiKeyRepository;

/**
 * Created by CLLSDJACKT013 on 1/29/2019.
 */
@RestController
@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class ApiSecurityController {
	/*
	 * @Value("${api.ITERATIONS}") private String ITERATIONS;
	 * 
	 * @Value("${api.KEY_LENGTH}") private String KEY_LENGTH;
	 */
	Properties props = new Properties();
	private String ITERATIONS = props.getProperty("ITERATIONS");
	private String KEY_LENGTH = props.getProperty("KEY_LENGTH");

	@Autowired
	private ApiKeyRepository apiKeyRepository;
	private Logger logger = LoggerFactory.getLogger(ApiSecurityController.class);

	@ResponseBody
	@RequestMapping(path = "/generatehashkey", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity generateHashKey(@RequestBody String requestString) {
		try {
			ApiSecurity apiSecurityRequest = new Gson().fromJson(requestString, ApiSecurity.class);
			logger.info("-----------------------");
			logger.info(new Gson().toJson(apiSecurityRequest));
			ApiSecurity apiSecurity = getHashedPassword(apiSecurityRequest.getApi_key(),
					apiSecurityRequest.getUsername(), apiSecurityRequest.getPassword().toCharArray(),
					apiSecurityRequest.getAction(), generateSalt(apiSecurityRequest.getApi_key()));
			logger.info("-----------------------");
			logger.info(new Gson().toJson(apiSecurity));
			// ApiSecurity apiSecurity =
			// compasSignatureGenerator.getHashedPassword("jonahkipropkimutaikipkeuelishakopchepkemoi","Kiprop34@","Kiprop34@".toCharArray(),"test",compasSignatureGenerator.generateSalt("jonahkipropkimutaikipkeuelishakopchepkemoi"));
			apiKeyRepository.save(apiSecurity);
			return ResponseEntity.status(201)
					.body(new Gson().toJson(new ApiResponse(true, "created successfully", apiSecurity)));
		} catch (Exception e) {
			return ResponseEntity.status(201)
					.body(new Gson().toJson(new ApiResponse(false, "failed to create hashstring", new ApiSecurity())));
		}

	}

	@ResponseBody
	@RequestMapping(path = "/updatehashkey", method = RequestMethod.POST, produces = "application/json")
	/* ("action should not change at all") */
	public ResponseEntity updateHashKey(@RequestBody String requestString) {
		try {
			ApiSecurity apiSecurityRequest = new Gson().fromJson(requestString, ApiSecurity.class);
			ApiSecurity apiSecurity = getHashedPassword(apiSecurityRequest.getApi_key(),
					apiSecurityRequest.getUsername(), apiSecurityRequest.getPassword().toCharArray(),
					apiSecurityRequest.getAction(), generateSalt(apiSecurityRequest.getApi_key()));
			// ApiSecurity apiSecurity =
			// compasSignatureGenerator.getHashedPassword("jonahkipropkimutaikipkeuelishakopchepkemoi","Kiprop34@","Kiprop34@".toCharArray(),"test",compasSignatureGenerator.generateSalt("jonahkipropkimutaikipkeuelishakopchepkemoi"));
			apiKeyRepository.updateAPiKeyByAction(apiSecurity, apiSecurity.getAction());
			return ResponseEntity.status(201).body(new Gson().toJson(new ApiResponse(true, "", new ApiSecurity())));
		} catch (Exception e) {
			return ResponseEntity.status(201)
					.body(new Gson().toJson(new ApiResponse(false, "failed", new ApiSecurity())));
		}
	}

	@ResponseBody
	@RequestMapping(path = "/getapikey", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity getHashKeyByAction(@RequestBody String requestString) {
		try {
			ApiSecurity apiSecurityRequest = new Gson().fromJson(requestString, ApiSecurity.class);
			ApiSecurity apiSecurity = apiKeyRepository.findAPiKeyByAction(apiSecurityRequest.getAction());
			apiSecurity.setHash_string(DatatypeConverter.printBase64Binary(apiSecurity.getHash()));
			return ResponseEntity.status(201).body(new Gson().toJson(new ApiResponse(true, "success", apiSecurity)));
		} catch (Exception e) {
			return ResponseEntity.status(201)
					.body(new Gson().toJson(new ApiResponse(false, "failed...", new ApiSecurity())));
		}
	}

	public ApiSecurity getHashedPassword(String apiKey, String username, char[] password, String action, byte[] salt) {
		String actionedpassword = password.toString().concat(action);
		PBEKeySpec spec = new PBEKeySpec(actionedpassword.toCharArray(), salt, Integer.parseInt(ITERATIONS),
				Integer.parseInt(KEY_LENGTH));
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			ApiSecurity apiSecurity = new ApiSecurity(apiKey, username, skf.generateSecret(spec).getEncoded(), "",
					action.toString());
			return apiSecurity;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	public byte[] generateSalt(String api_key) {
		char[] dest = new char[api_key.length()];
		api_key.getChars(0, api_key.length(), dest, 0);
		return dest.toString().getBytes();
	}
}
