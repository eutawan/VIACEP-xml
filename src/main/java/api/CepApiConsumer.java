package api;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.Address;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CepApiConsumer {
    public  Address getAddress(String cep, String name) throws IOException, InterruptedException, JAXBException {
        // configura a url da api
        var url = "http://viacep.com.br/ws/".concat(cep).concat("/xml/");

        // cria o objeto responsável pela request
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        // cria o objeto response da request
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JAXBContext jaxbContext = JAXBContext.newInstance(Address.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Address address = (Address) jaxbUnmarshaller.unmarshal(new StringReader(response.body()));

        return address;
    }
}



