package br.com.sprint4.utils.mocks;

import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Ideology;
import br.com.sprint4.dto.request.PartyInputDTO;

public class PartyMocks {

    public static Party getPartyMock(){
        var party = new Party();
        party.setId(1L);
        party.setName("Teste");
        party.setAcronym("PM");
        party.setIdeology(Ideology.CENTER);
        return party;
    }

    public static PartyInputDTO getPartyInputMock(){
        var party = new PartyInputDTO();
        party.setName("Teste");
        party.setAcronym("PM");
        party.setIdeology(Ideology.CENTER);
        return party;
    }
}
