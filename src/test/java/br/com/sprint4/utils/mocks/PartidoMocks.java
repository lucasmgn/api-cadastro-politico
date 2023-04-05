package br.com.sprint4.utils.mocks;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;

public class PartidoMocks {

    public static Partido getPartidoMock(){
        var partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
        return partido;
    }
}
