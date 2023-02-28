package plataformaFilmes;

import Maps.LoginMap;
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
        LoginMap.initLogin();

          Response response = RestUtils.post(LoginMap.getLogin(), ContentType.JSON,  "auth");

          Assertions.assertEquals(200, response.statusCode());
          LoginMap.token = response.jsonPath().get("token");

    }

    @BeforeAll
    public static void validarLoginMap(){

        RestUtils.setBaseUri("http://amancio:8080/");          /* URL e Endpoint   */
        LoginMap.initLogin();

        Response response = RestUtils.post(LoginMap.getLogin(), ContentType.JSON,  "auth");

        Assertions.assertEquals(200, response.statusCode());
        LoginMap.token = response.jsonPath().get("token");

    }
    
    @Test
    public void validarConsultaCategorias(){
        
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+LoginMap.token);

        Response response = RestUtils.get(header, "categorias");
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("Terror", response.jsonPath().get("tipo[2]"));

        List<String> listTipo = response.jsonPath().get("tipo");
        Assertions.assertTrue(listTipo.contains("Terror"),"Não foi encontrada a categoria Romance");

    }



}
