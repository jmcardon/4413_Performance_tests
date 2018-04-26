package restClient;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class PfSClient {



	public static void main(String[] argv){
		//create a client
		Client client = ClientBuilder.newClient();
		//create a web target
		WebTarget wt=client.target("http://localhost:8080/RESTSample/rest/pfs/plant/");
		//add the query parameter
		wt.queryParam("plantName", "tulip");	
		//create a request and invoke get; the argument of get is the type of return
		String price = wt.request(MediaType.TEXT_PLAIN_TYPE)
		        .get(String.class);
		System.out.println("GET plant/?plantName=tulip returns price=" + price);
		
		//similar to above but different target and a chain of 2 query parameters
		String catalog=client.target("http://localhost:8080/RESTSample/rest/pfs/plant/add/")
				  .queryParam("plantName", "x").queryParam("price","$24" )	
		          .request(MediaType.TEXT_PLAIN_TYPE)
		          .get(String.class);
		System.out.println(catalog);
		
		//prepare a post method; first create a form with 2 parameters
		Form form = new Form();
		form.param("plantName", "foo");
		form.param("price", "$99");
		
		//call a post method with an Entity argument..
		catalog=client.target("http://localhost:8080/RESTSample/rest/pfs/plant/create/")	
		          .request(MediaType.TEXT_PLAIN_TYPE)
		          .post((Entity.entity(form,MediaType.TEXT_PLAIN_TYPE)), String.class);
		System.out.println(catalog);         
		
		client.close();	
	}
}
