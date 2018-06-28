package com.api.sptransconsumer.dto;

public interface Links {

    String LINK = "http://api.olhovivo.sptrans.com.br/v2.1";
    String URI_AUTENTICATE = "/Login/Autenticar?token=";

    String LINHA_BUSCAR = "/Linha/Buscar?termosBusca=";
    String LINHA_BUSCAR_LINHA_SENTIDO[] = {"/Linha/BuscarLinhaSentido?termosBusca=", "&sentido="};

}
