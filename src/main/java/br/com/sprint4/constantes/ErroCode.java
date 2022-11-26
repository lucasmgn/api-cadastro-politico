package br.com.sprint4.constantes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErroCode {

    PARTIDO_NAO_ENCONTRADO("Partido não encontrado"),
    ASSOCIADO_NAO_ENCONTRADO("Associado não encontrado"),
    BAD_REQUEST("Requisição Inválida"),
    PARAMETRO_INVALIDO("Parâmetro Inválido"),
    ERRO_DE_SISTEMA("Erro de sistema"),
    ENTIDADE_EM_USO("Entidade em uso");

    private final String message;

}