package security.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aniket.exception.ResourceNotFoundException;

import security.LoadBalancerConfiguration;
import security.pojo.TrainDetails;


@FeignClient(value="Admin-Service",url="http://localhost:8001")
@LoadBalancerClient(name = "Admin-Service",
configuration=LoadBalancerConfiguration.class)
public interface AdminProxy {
	
	@GetMapping(value="/show",produces = "application/json")
	public ResponseEntity<List<TrainDetails>> viewAll(@RequestHeader("Authorization") String authorization);
	
	@PostMapping(value = "/add",produces = "application/json")
	public ResponseEntity<?> addtrain(@RequestBody TrainDetails td,@RequestHeader("Authorization") String authorization);
	
	@PutMapping("/update/{id}/{destination}")
	public ResponseEntity<?> updateTrain(@PathVariable int id, @PathVariable String destination,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException;
	
	@DeleteMapping("/delete/{id}")
	public String deleteTrain(@PathVariable int id,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException;
		

}
