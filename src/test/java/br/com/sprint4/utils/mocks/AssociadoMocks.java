package br.com.sprint4.utils.mocks;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Sexo;

import static br.com.sprint4.utils.mocks.PartidoMocks.getPartidoMock;

public class AssociadoMocks {
    private static Associado associado = new Associado();
    public static Associado getAssociadoComPartidoMock(){
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.GOVERNADOR);
        associado.setSexo(Sexo.MASCULINO);
        associado.setPartido(getPartidoMock());
        return associado;
    }

    public static Associado getAssociadoSemPartidoMock(){
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.GOVERNADOR);
        associado.setSexo(Sexo.MASCULINO);
        return associado;
    }
}
