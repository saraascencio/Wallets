package blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private ArrayList<Block> blockChain;
    private int complexity;
    private String proofOfWork;

    public BlockChain(int iComplexity, String proofChar) {
        this.blockChain = new ArrayList<>();
        this.complexity = iComplexity;
        this.proofOfWork = "";
        for (int i = 0; i < this.complexity; i++) {
            this.proofOfWork += proofChar;
        }
    }

    public List<Block> getBlockChain() {
        return this.blockChain;
    }

    public boolean blockExist(Block blk) {
        for (int i = 0; i < this.blockChain.size(); i++) {
            if (this.blockChain.get(i).getId() == blk.getId()) {
                return true;
            }
        }
        return false;
    }

    public Block getBlock(int index) {
        return this.blockChain.get(index);
    }

    public Block getLastBlock() {
        return this.blockChain.get(this.blockChain.size() - 1);
    }

    public int size() {
        return this.blockChain.size();
    }

    public boolean createGenesis(double pInitialBalance, String pClient) {
        if (this.size() < 1) {
            // Bloque génesis: no pasa motivo, el valor por defecto será ""
            Block tmpBlock = new Block(0, "0000000000000000000000000000000000000000000000000000000000000000");

            if (pInitialBalance > 0) {
                tmpBlock.setTransaction("0000GeNeSiS", pInitialBalance, pClient, "", "", "");  // Aquí se pasa un motivo vacío
            }

            this.blockChain.add(tmpBlock);
            this.mineBlock();
            return true;
        }
        return false;
    }

    public boolean createGenesis() {
        if (this.size() < 1) {
            Block tmpBlock = new Block(0, "0000000000000000000000000000000000000000000000000000000000000000");
            this.blockChain.add(tmpBlock);
            this.mineBlock();
            return true;
        }
        return false;
    }

    public void createBlock() {
        String prevHash = this.blockChain.get(this.blockChain.size() - 1).getHash();
        this.blockChain.add(new Block(this.blockChain.size(), prevHash));
    }

    public double getBalance(String pClient) {
        double positiveAmount = 0;
        double negativeAmount = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.getBlock(i).countTransactions(); j++) {
                if (this.getBlock(i).getTransaction(j).getReceiver().equals(pClient)) {
                    positiveAmount
                            += this.getBlock(i).getTransaction(j).getAmount();
                } else if (this.getBlock(i).getTransaction(j).getSender().equals(pClient)) {
                    negativeAmount
                            += this.getBlock(i).getTransaction(j).getAmount();
                }
            }
        }
        return positiveAmount - negativeAmount;
    }

    
    public boolean getProofOfWork_overBlock(Block blk) {
        String cad = blk.toString();
        int nonce = blk.getNonce();
        String sHash = "";
        sHash = this.generateHash(cad + Integer.toString(nonce));
        if (sHash.equals(blk.getHash())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addProvedBlock(Block blk) {
        if (!this.blockExist(blk)) {
            if (this.getProofOfWork_overBlock(blk)) {
                this.blockChain.add(blk);
                return true;
            }
        }
        return false;
    }

    public void mineBlock() {
        String cad
                = this.blockChain.get(this.blockChain.size() - 1).toString();
        int nonce = 0;
        String sHash = "";
        while (true) {
            sHash = this.generateHash(cad + Integer.toString(nonce));
            if (sHash.subSequence(0, complexity).equals(this.proofOfWork)) {
                this.blockChain.get(this.blockChain.size() - 1).register(nonce, sHash);
                break;
            }
            nonce++;
        }

    }

    private String generateHash(String pCad) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pCad.getBytes("UTF-8"));
            StringBuffer hexadecimalString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                if (hexadecimal.length() == 1) {
                    hexadecimalString.append('0');
                }
                hexadecimalString.append(hexadecimal);
            }
            return hexadecimalString.toString();
        } catch (Exception ee) {
            return null;
        }
    }

    //➜
    public String transactionReport(int nBlock) {
    // Verificar que el índice del bloque sea válido
    if (nBlock < 0 || nBlock >= this.blockChain.size()) {
        return "Bloque no encontrado.";
    }

    StringBuilder sCad = new StringBuilder();  // Usar StringBuilder para mayor eficiencia
    Block blk = this.blockChain.get(nBlock);

    // Iterar sobre las transacciones del bloque
    for (int i = 0; i < blk.countTransactions(); i++) {
        // Asegurarse de que no se acceda a un índice fuera de rango
        Transaction tran = blk.getTransaction(i);
        sCad.append("\tTransaccion #")
            .append(tran.getId())
            .append(": $")
            .append(tran.getAmount())
            .append(".\t(")
            .append(tran.getSender())
            .append(" ---> ")
            .append(tran.getReceiver())
            .append(")\n")
            .append("Motivo: ")
            .append(tran.getMotivo())
            .append("\n");
    }

    return sCad.toString();
}

    @Override
    public String toString() {

        String blockChain = "";

        for (Block block : this.blockChain) {
            blockChain += block.toString() + "\n";
        }

        return blockChain;
    }
}
