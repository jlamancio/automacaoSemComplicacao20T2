package transferencia;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class ContaTest {

    Cliente flavia, joseLuis;
    Conta ctFlavia, ctJLuis;

    @BeforeEach
    void setUp() {

        flavia = new Cliente("Flavia Boconcelo","16820750870","12345678");
        joseLuis = new Cliente("José Luis","01315802805","13997532");

        ctFlavia = new Conta("1234","567890",2500.00,flavia);
        ctJLuis = new Conta("5305","013335",1500.00,joseLuis);

    }

    @Test
    public void realizarTransacaoValidaFlJl() {
        ctFlavia.reaizarTransferencia(1000.00, ctJLuis);
        Assertions.assertEquals(1500.00, ctFlavia.getSaldo());
    }
    @Test
    public void realizarTransacaoInvalidaFlJl() {
        boolean resultado = ctFlavia.reaizarTransferencia(2501.00, ctJLuis);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void realizarTransacaoValidaJlFl() {
        ctJLuis.reaizarTransferencia(1000.00, ctFlavia);
        Assertions.assertEquals(500.00, ctJLuis.getSaldo());
    }
    @Test
    public void realizarTransacaoInvalidaJlFl() {
        boolean resultado = ctJLuis.reaizarTransferencia(1501.00, ctFlavia);
        Assertions.assertFalse(resultado);
    }

}