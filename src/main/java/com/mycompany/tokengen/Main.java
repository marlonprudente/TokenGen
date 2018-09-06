/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tokengen;

import HashGen.*;
import java.security.*;
import java.math.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marlon Prudente <m.prudente at btc-banco.com>
 * <marlonoliveira at alunos.utfpr.edu.br>
 */
public class Main {
    
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(1));
        return result.toString();
    }
    public static void main(String[] args) {
        String s = "seed";
        String login = "marlon";
        String senha = "senha1";
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond()/10;
        List<String> listaHash = new ArrayList<String>();
        Hash hg = new Hash();
        String hashed = Hash.stringHexa(Hash.gerarHash(login + timeStampSeconds + s, "MD5"));
        BigInteger decimal = new BigInteger(bytesToHex(Hash.gerarHash(login + timeStampSeconds + s, "MD5")),16);
        System.out.println("toDecimal: " + decimal);
        System.out.println("Timestamp: " + timeStampSeconds);
        for (int i = 0; i < 5; i++) {            
            System.out.println("Hashes on List: " + hashed);
            listaHash.add(hashed);
            hashed = Hash.stringHexa(Hash.gerarHash(hashed + timeStampSeconds + s, "MD5"));
        }

//    System.out.println("MD5: " + hg.stringHexa(hg.gerarHash(s, "MD5")));
//    System.out.println("SHA-1: " + hg.stringHexa(hg.gerarHash(s, "SHA-1")));
//    System.out.println("SHA-256: " + hg.stringHexa(hg.gerarHash(s, "SHA-256")));
        Scanner scan = new Scanner(System.in);
        String auth;
        
//        while(true){
//                    System.out.println("TimeStamp: " + timeStampSeconds/10);
//            try {
//                instant = Instant.now();
//                timeStampSeconds = instant.getEpochSecond();
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        while (true) {
            System.out.println("Digite o login: ");
            auth = scan.nextLine();
            System.out.println("AUTH: " + auth.trim());
            System.out.println("Original: " + (login).trim());
            instant = Instant.now();
            timeStampSeconds = instant.getEpochSecond()/10;
            System.out.println("Timestamp Auth: " + timeStampSeconds);
            String teste = Hash.stringHexa(Hash.gerarHash(auth.trim() + timeStampSeconds + s, "MD5"));
            System.out.println("Hashed: " + teste);
            if (listaHash.contains(teste)) {
                System.out.println("Math");
            } else {
                System.out.println("Token inválido");
                s = "senha2";
                listaHash = new ArrayList<String>();

                for (int i = 0; i < 5; i++) {
                    listaHash.add(hg.stringHexa(hg.gerarHash(login + s, "MD5")));
                }

            }
        }

    }

}
