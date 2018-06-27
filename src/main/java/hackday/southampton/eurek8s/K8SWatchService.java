package hackday.southampton.eurek8s;

import javax.annotation.PostConstruct;

import io.fabric8.kubernetes.api.model.Endpoints;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 * @author Ollie Hughes
 */
@Service
public class K8SWatchService {

	Logger log = LoggerFactory.getLogger(Eurek8sApplication.class);
	KubernetesClient kubernetesClient;

	public K8SWatchService(KubernetesClient kubernetesClient) {
		this.kubernetesClient = kubernetesClient;
	}

	@PostConstruct
	public void setupWatch() {
		kubernetesClient.services().watch(new Watcher<io.fabric8.kubernetes.api.model.Service>() {
			@Override
			public void eventReceived(Watcher.Action action, io.fabric8.kubernetes.api.model.Service resource) {
				log.info("Received service event: {}", action.name());
				log.info("Service details {}", resource.getStatus());
			}

			@Override
			public void onClose(KubernetesClientException cause) {
				throw new RuntimeException(cause);
			}

		});
		kubernetesClient.endpoints().watch(new Watcher<Endpoints>() {
			@Override
			public void eventReceived(Action action, Endpoints resource) {
				log.info("Received endpoint event: {}", action.name());
				log.info("Endpoint details {}", resource.getSubsets());

			}

			@Override
			public void onClose(KubernetesClientException cause) {
				throw new RuntimeException(cause);
			}
		});
	}
}
