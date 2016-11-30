package com.example.julia.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by João on 23/11/2016.
 */
public class Choppada {
    private String nome;
    private String local;


    private Choppada(){}

    public Choppada(String nome, String local){
        this.nome = nome;this.local = local;
    }

    //Métodos pega/muda
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //"Converte" o objeto para HashMap/Dicionário/JSON/o-nome-que-você-quiser.
    //O problema está em dar update no Banco de Dados,
    //já que quando escreve-se nele, não se armazena mais uma Choppada, e sim Strings.
    //Dessa forma, o método adotado para mostrar as choppadas fica obsoleto, pois ele consiste em
    //pegar Choppada do Banco de Dados,e não Strings.
    //Bom, pelo menos é isso que eu acho. Para mais informações, ver a classe MostrarChoppadas.
    public Map<String, Object> toMap(){
        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("nome",nome); resultado.put("local",local);
        return resultado;
    }



}
