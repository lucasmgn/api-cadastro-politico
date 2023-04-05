package br.com.sprint4.utils.mocks;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.enums.Office;
import br.com.sprint4.enums.Sex;
import br.com.sprint4.dto.request.AssociateInputDTO;

import static br.com.sprint4.utils.mocks.PartyMocks.getPartyMock;

public class AssociateMocks {
    private static Associate associate = new Associate();
    public static Associate getAssociateWithPartyMock(){
        associate = new Associate();
        associate.setId(1L);
        associate.setName("Teste");
        associate.setOffice(Office.GOVERNOR);
        associate.setSex(Sex.MALE);
        associate.setParty(getPartyMock());
        return associate;
    }

    public static Associate getAssociateWithOutPartyMock(){
        associate = new Associate();
        associate.setId(1L);
        associate.setName("Teste");
        associate.setOffice(Office.GOVERNOR);
        associate.setSex(Sex.MALE);
        return associate;
    }

    public static AssociateInputDTO getAssociateInputWithOutPartyMock(){
        AssociateInputDTO associate = new AssociateInputDTO();
        associate.setName("Teste");
        associate.setOffice(Office.GOVERNOR);
        associate.setSex(Sex.MALE);
        return associate;
    }
}
