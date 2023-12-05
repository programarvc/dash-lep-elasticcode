package com.br.agilize.dash.exception;

public class Excecao {
    public static void main(String[] args) {
        if (args.length > 0) {
            int num = Integer.parseInt(args[0]);

            System.out.println("O dobro de "+num+" é "+num*2);
        } else {
            System.out.println("Vai Javai!");
        }
    }
}
