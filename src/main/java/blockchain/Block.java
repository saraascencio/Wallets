package blockchain;

/**
 *
 * @author carlos
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Block implements Serializable {

    private int id;
    private int nonce;
    private long timeStamp;
    private String hash; // según Nakamoto (2008) es el Root Hash
    private String previousHash;
    private ArrayList<Transaction> aTransactions;
    //private String monedaRemitente;
    //private String monedaReceptor;

    public Block(int pId, String pPrevHash) {
        this.id = pId;
        this.timeStamp = new Date().getTime();
        this.previousHash = pPrevHash;
        this.aTransactions = new ArrayList<>();
        this.nonce = -1;
        this.hash = null;
    }

    public Block() {
        this.timeStamp = new Date().getTime();
        this.aTransactions = new ArrayList<>();
        this.nonce = -1;
        this.hash = null;
        this.id = -1;
    }

    public boolean register(int pNonce, String pHash) {
        if ((this.id > -1) && (this.nonce < 0) && (this.hash == null)) {
            this.nonce = pNonce;
            this.hash = pHash;
            return true;
        } else {
            return false;
        }
    }

    public void setTransaction(String pSender, double pAmount, String pReceiver, String pMotivo, String pMonedaRemitente, String pMonedaReceptor) {
    // Si no se pasa el motivo, lo dejamos vacío o con un valor por defecto
    if (pMotivo == null || pMotivo.isEmpty()) {
        pMotivo = "";  // o cualquier valor por defecto como "Sin Motivo"
    }

    // Agregar la transacción con las monedas
    this.aTransactions.add(new Transaction(
            this.aTransactions.size(), pSender, pReceiver, pAmount, pMotivo, pMonedaRemitente, pMonedaReceptor
    ));
}


    public void setTransaction(Transaction pTran) {
    // Agregar la transacción tal cual está, incluyendo las monedas
    this.aTransactions.add(new Transaction(
            this.aTransactions.size(), 
            pTran.getSender(), 
            pTran.getReceiver(), 
            pTran.getAmount(), 
            pTran.getMotivo(),
            pTran.getMonedaRemitente(),  // Agregar la moneda del remitente
            pTran.getMonedaReceptor() // Agregar la moneda del receptor
    ));
}

    public Transaction getTransaction(int pId) {
        return this.aTransactions.get(pId);
    }

    public int countTransactions() {
        return this.aTransactions.size();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nonce
     */
    public int getNonce() {
        return nonce;
    }

    /**
     * @return the timeStamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /*public String getMonedaRemitente() {
        return monedaRemitente;
    }

    public String getMonedaReceptor() {
        return monedaReceptor;
    }*/
    /**
     * @return the previousHash
     */
    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public String toString() {
        String sCad = Integer.toString(id) + Long.toString(timeStamp)
                + this.previousHash;
        for (int i = 0; i < this.aTransactions.size(); i++) {
            sCad = sCad + this.aTransactions.get(i).toString();
        }
        return sCad;
    }
}
