package restClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import bean.BookBean;

public class BookStoreClient {


    public static void main(String[] args) {

        //create a client
        Client client = ClientBuilder.newClient();
        BookBean s = client.target("http://localhost:8080/bookStore/getProductInfoTest?bookId=b001")
                .request(MediaType.TEXT_PLAIN)
                .get(BookBean.class);

        assert s.getPrice() == 20;
    }

}
