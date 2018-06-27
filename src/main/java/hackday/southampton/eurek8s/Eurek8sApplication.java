package hackday.southampton.eurek8s;

import javax.annotation.PostConstruct;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = EurekaClientAutoConfiguration.class)
public class Eurek8sApplication {

	Logger log = LoggerFactory.getLogger(Eurek8sApplication.class);
	@Bean
	public Config kubernetesClientConfig() {
		Config base = Config.autoConfigure(null);
		Config properties = new ConfigBuilder(base)
				//Only set values that have been explicitly specified
				.withMasterUrl("35.234.137.39")
				.withApiVersion("v1")
				.withNamespace("default")
//				.withClientKeyFile("~/.kube/config")
//
//				.withCaCertFile()
//				.withCaCertData()
//
//				.withClientKeyFile()
//				.withClientKeyData()
//
//				.withClientCertFile()
//				.withClientCertData()
//
//				//No magic is done for the properties below so we leave them as is.
//				.withClientKeyAlgo()
//				.withClientKeyPassphrase()
//				.withConnectionTimeout()
//				.withRequestTimeout()
//				.withRollingTimeout()
//				.withTrustCerts()
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
