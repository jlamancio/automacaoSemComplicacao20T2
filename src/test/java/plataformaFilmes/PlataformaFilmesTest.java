package plataformaFilmes;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlataformaFilmesTest {

    static String token;

    @Test
    public void validarLogin(){

        RestUtils.setBaseUri("http://amancio:8080/");          /* URL e Endpoint   */

        String json = "{" +
                "    \"email\": \"aluno@email.com\"," +
                "    \"senha\": \"123456\"" +
                "}";

          Response response = RestUtils.post(json, ContentType.JSON,  "auth");

            Assertions.assertEquals(200, response.statusCode());
            String token = response.jsonPath().get("token");
            System.out.println(token);

    }

    @BeforeAll
    public static void validarLoginMap(){

        RestUtils.setBaseUri("http://amancio:8080/");          /* URL e Endpoint   */
        Map<String, String> map = new HashMap<>();
        map.put("email", "aluno@email.com");
        map.put("senha", "123456");


        Response response = RestUtils.post(map, ContentType.JSON,  "auth");

        Assertions.assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");
        System.out.println(token);
    }
    
    @Test
    public void validarConsultaCategorias(){
        
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+token);

        Response response = RestUtils.get(header, "categorias");
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("Terror", response.jsonPath().get("tipo[2]"));

        List<String> tiposDeFilmes = response.jsonPath().get("tipo");
        Assertions.assertTrue(tiposDeFilmes.contains("Terror"), "Não foi encontrada a categoria Romance");

    }



}
