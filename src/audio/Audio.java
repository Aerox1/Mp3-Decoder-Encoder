
package audio;

/**
 *
 * @author Arash
 * 
 * This is about encoding .mp3 files . the Mp3Header class gets info about Mp3
 * file then in you can use it in this class . first you find the headers then
 * in data section there is a method called Encode which here is a very simple
 * one . you can replace with whatever you want . and also there is another one
 * which called Decode that do the reverse thing .the point of this project is 
 * that the encoded file can also be played in media players because header
 * doesnt change.it works perfectly but some mp3 files that have dirty metadata
 * tags cause it take a little long for encoding . what you need to do is just
 * skip the metadata . which very simple .  In Mp3Header class you can find most
 * of info about the mp3 file if you need them.
 */        
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

        
public class Audio {
 
    static byte[] bytes , bytes2 ;
    static Mp3Header h ;
    static int bitrate = 0;
    static int Frequency = 0;
    static String Version = "";
    static String Layer = "";
    static String filelocation = "";
    static String filename = "";

    
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                 "MP3", "mp3");
         chooser.setFileFilter(filter);
         int returnVal = chooser.showOpenDialog(null);
         if(returnVal == JFileChooser.APPROVE_OPTION) {
             filelocation = chooser.getSelectedFile().getPath();
             filename = chooser.getSelectedFile().getName();
         }
         
        File file = new File(filelocation);
        FileInputStream fis = new FileInputStream(file);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); 
                
            }
        } catch (IOException ex) {
           
        }
        bytes = bos.toByteArray();
        bytes2 = new byte[4];
        h = new Mp3Header();
        
        getInfo();
        
        System.out.println(bitrate);
        
        Encode();
        Decode();
        
    }//end main
    
    public static void Encode() throws FileNotFoundException, IOException
    {
         //****  ENCODE ****//
        int k=0;
        int framesize=0;
        for(int i = 0 ; i< bytes.length - 4 ; i++){
            
            k = i;
            for( int j=0 ; j<4 ; j++)
            {
                bytes2[j] = bytes[k];
                k++;
            }
            h.setHeader(bytes2);
            if (h.isHeader())
            {
                framesize=h.getFrameSize();
                codeBytes( i+4 , framesize + i);
                i = framesize + i -1;
            }
            
        }

        //*****//
        
        File someFile = new File("ENCODED" +filename);
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }//end Encode
    
    public static void Decode() throws FileNotFoundException, IOException
    {
                //****** DECODE ******//
        
        int k=0;
        int framesize=0;
        for(int i = 0 ; i< bytes.length - 4 ; i++){
            
            k = i;
            for( int j=0 ; j<4 ; j++)
            {
                bytes2[j] = bytes[k];
                k++;
            }
            h.setHeader(bytes2);
            if (h.isHeader())
            {
                framesize=h.getFrameSize();
                code2Bytes( i+4 , framesize + i);
                i = framesize + i -1;
            }
            
        }
        
        File someFile2 = new File("DECODED" + filename);
        FileOutputStream fos2 = new FileOutputStream(someFile2);
        fos2.write(bytes);
        fos2.flush();
        fos2.close();
    }//end Decode
    public static void codeBytes(int start , int end)
    {
        for(int i = start ; i < end ; i++)
            bytes[i]-= 5;
    }
    
    public static void code2Bytes(int start , int end)
    {
        for(int i = start ; i < end ; i++)
            bytes[i]+= 5;
    }
    
    public static void getInfo()
    {
        boolean find = false ;
        int k=0;
        for(int i = 0 ; i< bytes.length - 4 && !find; i++){
            k = i;
            for( int j=0 ; j<4 ; j++)
            {
                bytes2[j] = bytes[k];
                k++;
            }
            h.setHeader(bytes2);
            if (h.isHeader())
            {
                bitrate = h.getBitRate();
                Frequency = h.getRateFrequency();
                Version = h.getMpegVersionId();
                Layer = h.getLayer();
                find = true;
            }
            
        }//end for
    }//end getInfo
  
}//end class