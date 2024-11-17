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
    double positiveAmount = 0; // Cantidad recibida
    double negativeAmount = 0; // Cantidad enviada

    
    for (int i = 0; i < this.blockChain.size(); i++) {
        Block currentBlock = this.blockChain.get(i);

        
        for (int j = 0; j < currentBlock.countTransactions(); j++) {
            Transaction transaction = currentBlock.getTransaction(j);

           
            if (transaction.getReceiver().trim().equalsIgnoreCase(pClient.trim())) {
                positiveAmount += transaction.getAmount();
            }

            
            if (transaction.getSender().trim().equalsIgnoreCase(pClient.trim())) {
                negativeAmount += transaction.getAmount();
            }
        }
    }

    // Retornar saldo neto (recibido - enviado), sin conversión de moneda
    return positiveAmount - negativeAmount;
}

    
    public double getBalance2(String pClient, String sMonedaCliente) {
    double positiveAmount = 0; // Cantidad recibida
    double negativeAmount = 0; // Cantidad enviada

    // Recorremos todas las transacciones en la cadena de bloques
    for (int i = 0; i < this.blockChain.size(); i++) {
        Block currentBlock = this.blockChain.get(i);

        for (int j = 0; j < currentBlock.countTransactions(); j++) {
            Transaction transaction = currentBlock.getTransaction(j);

            // Si el cliente es el receptor de la transacción
            // Italia
            //Japon, la moneda ya va en YEN
            if (transaction.getReceiver().trim().equalsIgnoreCase(pClient.trim())) {
                double amountReceived = transaction.getAmount();
                String sMonedaTransaccion = transaction.getMonedaReceptor(); // Moneda del receptor (de la transacción)

                // Si la moneda de la transacción es diferente a la moneda del cliente, se convierte
                if (!sMonedaCliente.equals(sMonedaTransaccion)) {
                    amountReceived = convertirMoneda(amountReceived, sMonedaTransaccion, sMonedaCliente);
                }

                positiveAmount += amountReceived;
            }

            // Si el cliente es el remitente de la transacción
            // USA 
            //USA
            if (transaction.getSender().trim().equalsIgnoreCase(pClient.trim())) {
                double amountSent = transaction.getAmount();
                String sMonedaTransaccion = transaction.getMonedaReceptor(); // Moneda del remitente (de la transacción)

                // Si la moneda de la transacción es diferente a la moneda del cliente, se convierte
                if (!sMonedaCliente.equals(sMonedaTransaccion)) {
                    amountSent = convertirMoneda(amountSent, sMonedaTransaccion, sMonedaCliente);
                }

                negativeAmount += amountSent;
            }
        }
    }

    // Retornamos el saldo neto (recibido - enviado), ya convertido a la moneda del cliente
    return positiveAmount - negativeAmount;
}


    
    

// Método para realizar la conversión de monedas según las tasas de cambio
private double convertirMoneda(double amount, String monedaOrigen, String monedaDestino) {
    double tasaConversion = 1.0; // Valor por defecto (si no hay conversión)

    // Lógica para manejar las conversiones
    if (monedaOrigen.equals("$") && monedaDestino.equals("€")) {
        tasaConversion = 0.85; // Ejemplo de tasa de conversión de USD a EUR
    } else if (monedaOrigen.equals("€") && monedaDestino.equals("$")) {
        tasaConversion = 1.18; // Ejemplo de tasa de conversión de EUR a USD
    } else if (monedaOrigen.equals("$") && monedaDestino.equals("¥")) {
        tasaConversion = 110.0; // Ejemplo de tasa de conversión de USD a JPY
    } else if (monedaOrigen.equals("¥") && monedaDestino.equals("$")) {
        tasaConversion = 0.0091; // Ejemplo de tasa de conversión de JPY a USD
    } else if (monedaOrigen.equals("$") && monedaDestino.equals("MX$")) {
        tasaConversion = 20.0; // Ejemplo de tasa de conversión de USD a MXN
    } else if (monedaOrigen.equals("MX$") && monedaDestino.equals("$")) {
        tasaConversion = 0.05; // Ejemplo de tasa de conversión de MXN a USD
    } else if (monedaOrigen.equals("¥") && monedaDestino.equals("€")) {
        tasaConversion = 0.0077; // Ejemplo de tasa de conversión de JPY a EUR
    } else if (monedaOrigen.equals("€") && monedaDestino.equals("¥")) {
        tasaConversion = 130.0; // Ejemplo de tasa de conversión de EUR a JPY
    } else {
        System.out.println("No se necesita conversión. Moneda Origen: '" + monedaOrigen + "' Moneda Destino: '" + monedaDestino + "'");
    }

    // Realizamos la conversión
    return amount * tasaConversion;
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
    public String transactionReport(int nBlock, int[] transactionCounter) {
    // Verificar que el índice del bloque sea válido
    if (nBlock < 0 || nBlock >= this.blockChain.size()) {
        return "Bloque no encontrado.";
    }

    StringBuilder sCad = new StringBuilder();  
    Block blk = this.blockChain.get(nBlock);

    for (int i = 0; i < blk.countTransactions(); i++) {
        Transaction tran = blk.getTransaction(i);
        sCad.append("\tTransaccion #")
            .append(transactionCounter[0]++) // Usar y actualizar el contador global
            .append(": ")
            .append(tran.getMonedaReceptor())
            .append(" ")
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
