package ru.javaLessonTask.calculator.client.cryptionAndDecryption;


import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Cryptographer{
    //creature key encryption
    private final static class SecretKeyProgramm implements SecretKey{

        private byte[] key = new byte[]{-63,3,-20,9,40,56,75,-5}; //ключ

        @Override
        public String getAlgorithm() {
            return "DES";
        }

        @Override
        public String getFormat() {
            return "RAW";
        }

        @Override
        public byte[] getEncoded() {
            return key;
        }
    }
    private static SecretKey key;
    private static Cipher ecipher;
    private static Cipher dcipher;

    //algorithm DES on based SecretKeyProgram
    static {
        try {
            key=KeyGenerator.getInstance("DES").generateKey();
            key=new SecretKeyProgramm();
            ecipher=Cipher.getInstance("DES");
            dcipher=Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE,key);
            dcipher.init(Cipher.DECRYPT_MODE,key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
    //encryption
    public String encrypt(String str){
        try {
            byte[] utf8=str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    //decription
    public String decrypt(String str){
        try {
            byte[] dec =new sun.misc.BASE64Decoder().decodeBuffer(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8,"UTF8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    }
