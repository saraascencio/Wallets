
package blockchain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Transaction implements Serializable
{
    private int id;
    private long timeStamp;
    private String sender;
    private String receiver;
    private double amount;
    private String motivo; 
    private String monedaRemitente;
    private String monedaReceptor;
    /*aca se agrego*/
    
      
    public Transaction(int pId, String pSender, 
            String pReceiver, double pAmount)
    {
      this.id=pId;  
      this.timeStamp= new Date().getTime();
      this.sender=pSender;
      this.receiver=pReceiver;
      this.amount=pAmount;

    }
    
    public Transaction(int pId, String pSender, 
            String pReceiver, double pAmount, String pMotivo)
    {
      this.id=pId;  
      this.timeStamp= new Date().getTime();
      this.sender=pSender;
      this.receiver=pReceiver;
      this.amount=pAmount;
      this.motivo = pMotivo;
    }
    
       public Transaction(int id, String sender, String receiver, double amount, String motivo, String monedaRemitente, String monedaReceptor) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.motivo = motivo;
        this.monedaRemitente = monedaRemitente;
        this.monedaReceptor = monedaReceptor;
    }
  

    @Override
    public String toString() {
        return Integer.toString(id)+
                Long.toString(timeStamp)+
                sender+receiver+
                Double.toString(amount)+ motivo+
                monedaRemitente+monedaReceptor;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the timeStamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
     public String getMotivo() {
        return motivo;
    }
     
     public String getMonedaRemitente() {
        return monedaRemitente;
    }

    public String getMonedaReceptor() {
        return monedaReceptor;
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
