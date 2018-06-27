package hackday.southampton.eurek8s;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class Eurek8sApplication {

	Logger log = LoggerFactory.getLogger(Eurek8sApplication.class);
	@Bean
	public Config kubernetesClientConfig() {
		Config base = Config.autoConfigure(null);
		Config properties = new ConfigBuilder(base)
				//Only set values that have been explicitly specified
				.withMasterUrl("https://35.234.137.39")
				.withNamespace("default")
				.withApiVersion("v1")
				.build();

		return properties;
	}

	@Bean
	public KubernetesClient kubernetesClient(Config config) {
		return new DefaultKubernetesClient(config);
	}



	public static void main(String[] args) {

		SpringApplication.run(Eurek8sApplication.class, args);
	}
}
