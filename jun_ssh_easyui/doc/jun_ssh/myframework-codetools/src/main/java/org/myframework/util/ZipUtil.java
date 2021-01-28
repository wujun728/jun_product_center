package org.myframework.util;

import java.io.*; 
import java.util.zip.*; 
/** 
* 
*@author Wujun
*@date   2008-8-3 
*@Usage: 
*    :java Zip -zip "directoryName" 
*    :java Zip -unzip "fileName.zip" 
*/ 

public class ZipUtil{ 
    private ZipInputStream zipIn;      // Zip 
    private ZipOutputStream zipOut;     // Zip 
    private ZipEntry        zipEntry; 
    private static int      bufSize;    //size of bytes 
    private byte[]          buf; 
    private int             readedBytes; 
     
    public ZipUtil(){ 
        this(512); 
    } 

    public ZipUtil(int bufSize){ 
        this.bufSize = bufSize; 
        this.buf = new byte[this.bufSize]; 
    } 
     
    public void doZip(String zipDirectory){//zipDirectoryPath: 
        File file; 
        File zipDir; 

        zipDir = new File(zipDirectory); 
        String zipFileName = zipDir.getName() + ".zip";//  
        System.out.println(zipFileName);
        try{ 
            this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName))); 
            handleDir(zipDir , this.zipOut); 
            this.zipOut.close(); 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 

    private void handleDir(File dir , ZipOutputStream zipOut)throws IOException{ 
        FileInputStream fileIn; 
        File[] files; 

        files = dir.listFiles(); 
     
        if(files.length == 0){ 
            this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/")); 
            this.zipOut.closeEntry(); 
        } 
        else{
            for(File fileName : files){ 

                if(fileName.isDirectory()){ 
                    handleDir(fileName , this.zipOut); 
                } 
                else{ 
                    fileIn = new FileInputStream(fileName); 
                    this.zipOut.putNextEntry(new ZipEntry(fileName.toString())); 

                    while((this.readedBytes = fileIn.read(this.buf))>0){ 
                        this.zipOut.write(this.buf , 0 , this.readedBytes); 
                    } 

                    this.zipOut.closeEntry(); 
                } 
            } 
        } 
    } 

    public void unZip(String unZipfileName){//unZipfileName 
        FileOutputStream fileOut; 
        File file; 

        try{ 
            this.zipIn = new ZipInputStream (new BufferedInputStream(new FileInputStream(unZipfileName))); 

            while((this.zipEntry = this.zipIn.getNextEntry()) != null){ 
                file = new File(this.zipEntry.getName()); 

                if(this.zipEntry.isDirectory()){ 
                    file.mkdirs(); 
                } 
                else{ 
                    File parent = file.getParentFile(); 
                    if(!parent.exists()){ 
                        parent.mkdirs(); 
                    } 

                    fileOut = new FileOutputStream(file); 
                    while(( this.readedBytes = this.zipIn.read(this.buf) ) > 0){ 
                        fileOut.write(this.buf , 0 , this.readedBytes ); 
                    } 
                    fileOut.close(); 
                } 
                this.zipIn.closeEntry();     
            } 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 

    public void setBufSize(int bufSize){ 
        this.bufSize = bufSize; 
    } 

    public static void main(String[] args)throws Exception{ 
        if(args.length==2){ 
            String name = args[1]; 
            ZipUtil zip = new ZipUtil(); 

            if(args[0].equals("-zip")) 
                zip.doZip(name); 
            else if(args[0].equals("-unzip")) 
                zip.unZip(name); 
        } 
        else{ 
            System.out.println("Usage:"); 
            System.out.println(" :java Zip -zip C:/temp"); 
            System.out.println(" :java Zip -unzip http://www.feng123.com /fileName.zip"); 
            throw new Exception("Arguments error!"); 
        } 
    } 
}
