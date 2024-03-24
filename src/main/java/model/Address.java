package model;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
@Data
@XmlRootElement(name="xmlcep")
public class Address {
    private String cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi, name;
}
