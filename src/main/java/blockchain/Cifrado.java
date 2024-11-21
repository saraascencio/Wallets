/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockchain;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author carlos
 */
public class Cifrado 
{
    private SecretKeySpec llave;
    private Cipher oCifrado;    //encriptador
    private Cipher oDescifrado; //desencriptador
    private String clave;
    
    public Cifrado() {
        this.clave = "clave$2024";
        inicializarCifrado();
    }

    public Cifrado(String pClave) {
        this.clave = pClave;
        inicializarCifrado();
    }

   
    private void inicializarCifrado() {
        try {
            MessageDigest oHash = MessageDigest.getInstance("SHA-256");
            byte[] aBytes = oHash.digest(clave.getBytes("UTF-8"));
            byte[] aBytes32 = Arrays.copyOf(aBytes, 32);
            this.llave = new SecretKeySpec(aBytes32, "AES");
            
            this.oCifrado = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.oDescifrado = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
    }
    
    public Block cifrarBloque(Block pBlk)
    {
        try
        {
            pBlk.toString();
            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public String encriptar(String pCadena) throws Exception {
    
    SecureRandom random = new SecureRandom();
    byte[] ivBytes = new byte[16]; 
    random.nextBytes(ivBytes);
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

  
    this.oCifrado.init(Cipher.ENCRYPT_MODE, this.llave, ivSpec);

   
    byte[] aBytes = pCadena.getBytes("UTF-8");
    byte[] aBytesEnc = this.oCifrado.doFinal(aBytes);

   
    byte[] ivAndCiphertext = new byte[ivBytes.length + aBytesEnc.length];
    System.arraycopy(ivBytes, 0, ivAndCiphertext, 0, ivBytes.length);
    System.arraycopy(aBytesEnc, 0, ivAndCiphertext, ivBytes.length, aBytesEnc.length);

   
    return Base64.getEncoder().encodeToString(ivAndCiphertext);
     }

    public String desencriptar(String pCadena) throws Exception {
 
    byte[] ivAndCiphertext = Base64.getDecoder().decode(pCadena);

   
    byte[] ivBytes = Arrays.copyOfRange(ivAndCiphertext, 0, 16);
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
    byte[] ciphertext = Arrays.copyOfRange(ivAndCiphertext, 16, ivAndCiphertext.length);  
    this.oDescifrado.init(Cipher.DECRYPT_MODE, this.llave, ivSpec);
    byte[] aBytesDec = this.oDescifrado.doFinal(ciphertext);
    return new String(aBytesDec, "UTF-8");
}

    /**
     * @return the oCifrado
     */
    public Cipher getoCifrado() {
        return oCifrado;
    }

    /**
     * @return the oDescifrado
     */
    public Cipher getoDescifrado() {
        return oDescifrado;
    }
}
    

