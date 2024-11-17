/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockchain;

/**
 *
 * @author carlos
 */
public class NodeData {
    private String nodeName;
    private String IPAddress;
    private int    socketNum;
    private String currency;  
  
    
    public NodeData(String pnodeName, String pIPAddress, int psocketNum)
    {
        this.nodeName= pnodeName;
        this.IPAddress= pIPAddress;
        this.socketNum= psocketNum;
    }
    
    public NodeData(String pnodeName, String pIPAddress, int psocketNum, String pCurrency)
    {
        this.nodeName= pnodeName;
        this.IPAddress= pIPAddress;
        this.socketNum= psocketNum;
        this.currency = pCurrency;
    }
    
    
    public void setCurrency(String currency) {
    this.currency = currency;
    }
    
    public String getCurrency() {
        return currency;
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @return the IPAddress
     */
    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * @return the socketNum
     */
    public int getSocketNum() {
        return socketNum;
    }
    
}
