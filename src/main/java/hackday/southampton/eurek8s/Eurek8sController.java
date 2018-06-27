package hackday.southampton.eurek8s;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ollie Hughes
 */
@RestController
public class Eurek8sController {

	private KubernetesClient kubernetesClient;

	public Eurek8sController(KubernetesClient kubernetesClient) {
		this.kubernetesClient = kubernetesClient;
	}

	@GetMapping("/status")
	public String getStatus(){
		return kubernetesClient.apps().toString();
	}

}
