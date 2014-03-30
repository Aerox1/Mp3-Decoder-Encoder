/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package audio;

/**
 *
 * @author Arash
 */
public class Mp3Header {
    
    byte[] header ;
    String[] string_header;

    public Mp3Header(){ 
        
        header = new byte[4];
        string_header = new String[4];
    }
    
    public Mp3Header(byte[] b) {
        
        header = new byte[4];
        string_header = new String[4];
        
        header[0] = b[0];
        header[1] = b[1];
        header[2] = b[2];
        header[3] = b[3];
        
        string_header[0]=String.format("%8s", Integer.toBinaryString(header[0] & 0xFF)).replace(' ', '0');
        string_header[1]=String.format("%8s", Integer.toBinaryString(header[1] & 0xFF)).replace(' ', '0');
        string_header[2]=String.format("%8s", Integer.toBinaryString(header[2] & 0xFF)).replace(' ', '0');
        string_header[3]=String.format("%8s", Integer.toBinaryString(header[3] & 0xFF)).replace(' ', '0');
    }
    
    public void setHeader(byte[] b)
    {
        header[0] = b[0];
        header[1] = b[1];
        header[2] = b[2];
        header[3] = b[3];
        
        string_header[0]=String.format("%8s", Integer.toBinaryString(header[0] & 0xFF)).replace(' ', '0');
        string_header[1]=String.format("%8s", Integer.toBinaryString(header[1] & 0xFF)).replace(' ', '0');
        string_header[2]=String.format("%8s", Integer.toBinaryString(header[2] & 0xFF)).replace(' ', '0');
        string_header[3]=String.format("%8s", Integer.toBinaryString(header[3] & 0xFF)).replace(' ', '0');
    
        
    }
    
    public String getMpegVersionId()
    {
        if (string_header[1].substring(3, 5).equals("00"))
            return "MPEG Version 2.5 ";
        else if (string_header[1].substring(3, 5).equals("01"))
            return "Reserved";
        else if (string_header[1].substring(3, 5).equals("10"))
            return "MPEG Version 2";
        else if (string_header[1].substring(3, 5).equals("11"))
            return "MPEG Version 1";
        return "Error";
    }
    //******//
    
    public String getLayer()
    {
        if(string_header[1].substring(5, 7).equals("00"))
            return "Reserved";
        else if(string_header[1].substring(5, 7).equals("01"))
            return "Layer 111";
        else if(string_header[1].substring(5 ,7).equals("10"))
            return "Layer 11";
        else if(string_header[1].substring(5 , 7).equals("11"))
            return "Layer 1";
        return "Error";
    }
    public int getFrameSize()
    {
        return (int)((144*getBitRate()*1000)/getRateFrequency()) + getPadding();
    }
    
    public int getBitRate()
    {
        if(string_header[2].substring(0, 4).equals("0000"))
            return 0;
        else if(string_header[2].substring(0, 4).equals("0001"))
            return 32;
        else if(string_header[2].substring(0, 4).equals("0010"))
            return 40;
        else if(string_header[2].substring(0, 4).equals("0011"))
            return 48;
        else if(string_header[2].substring(0, 4).equals("0100"))
            return 56;
        else if(string_header[2].substring(0, 4).equals("0101"))
            return 64;   
        else if(string_header[2].substring(0, 4).equals("0110"))
            return 80;
        else if(string_header[2].substring(0, 4).equals("0111"))
            return 96;
        else if(string_header[2].substring(0, 4).equals("1000"))
            return 112;
        else if(string_header[2].substring(0, 4).equals("1001"))
            return 128;
        else if(string_header[2].substring(0, 4).equals("1010"))
            return 160;
        else if(string_header[2].substring(0, 4).equals("1011"))
            return 192;
        else if(string_header[2].substring(0, 4).equals("1100"))
            return 224;
        else if(string_header[2].substring(0, 4).equals("1101"))
            return 256;
        else if(string_header[2].substring(0, 4).equals("1110"))
            return 320;
        else if(string_header[2].substring(0, 4).equals("1111"))
            return 10000000;
        return -1;
    }
    
    ////*******////
    
    public int getRateFrequency()
    {
         if(string_header[2].substring(4, 6).equals("00"))
             return 44100;
         else if(string_header[2].substring(4, 6).equals("01"))
             return 48000;
         else if(string_header[2].substring(4, 6).equals("10"))
             return 32000;
         else if(string_header[2].substring(4, 6).equals("11"))
             return 11111;
         return -1;

    }
    
    ///******///
    
    public int getPadding()
    {
        if(string_header[2].charAt(6) == '0')
            return 0;
        return 1;
    }
    
    public boolean isHeader()
    {
        if (string_header[0].equals("11111111") && string_header[1].substring(0,3).equals("111"))
            return true ;
        return false ;
    }
    
    
   
    
}
