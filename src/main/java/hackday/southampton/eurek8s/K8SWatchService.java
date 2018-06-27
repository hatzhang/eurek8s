package hackday.southampton.eurek8s;

import com.netflix.discovery.EurekaClient;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ollie Hughes
 */
@Configuration
public class K8SWatchService {

	Logger log = LoggerFactory.getLogger(Eurek8sApplication.class);
	private KubernetesClient kubernetesClient;
	private DiscoveryClient discoveryClient;
	private EurekaClient eurekaClient;
	private EurekaInstanceConfigBean instanceConfig;
	private PeerAwareInstanceRegistry instanceRegistry;


	public K8SWatchService(KubernetesClient kubernetesClient, DiscoveryClient discoveryClient, EurekaClient eurekaClient, EurekaInstanceConfigBean instanceConfig, PeerAwareInstanceRegistry instanceRegistry) {
		this.kubernetesClient = kubernetesClient;
		this.discoveryClient = discoveryClient;
		this.eurekaClient = eurekaClient;
		this.instanceConfig = instanceConfig;
		this.instanceRegistry = instanceRegistry;
	}


}
