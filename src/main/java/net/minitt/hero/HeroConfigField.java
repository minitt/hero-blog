package net.minitt.hero;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hero")
public class HeroConfigField {
	
	private String signatureKey;

	public String getSignatureKey() {
		return signatureKey;
	}

	public void setSignatureKey(String signatureKey) {
		this.signatureKey = signatureKey;
	}
}
