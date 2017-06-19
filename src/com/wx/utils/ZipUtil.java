package com.wx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipUtil {
	
	public static void unZipTest(){
		 long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream("C:\\Users\\jinyonghang\\Desktop\\bankparsenew.zip"));//输入源zip路径  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\Users\\jinyonghang\\Desktop"; //输出路径（文件夹目录）  
	            File Fout=null;  
	            ZipEntry entry;  
	            try {
	                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
	                    Fout=new File(Parent,entry.getName());  
	                    if(!Fout.exists()){  
	                        (new File(Fout.getParent())).mkdirs();  
	                    }  
	                    FileOutputStream out=new FileOutputStream(Fout);  
	                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
	                    int b;  
	                    while((b=Bin.read())!=-1){  
	                        Bout.write(b);  
	                    }  
	                    Bout.close();  
	                    out.close();  
	                    System.out.println(Fout+"解压成功");      
	                }  
	                Bin.close();  
	                Zin.close();  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        long endTime=System.currentTimeMillis();  
	        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
	    
	}

	 /** 
    * 解压文件到指定目录 
    * @param zipFile 
    * @param descDir 
    * @author isea533 
    */  
   @SuppressWarnings("rawtypes")  
   public static void unZipFiles(File zipFile,String descDir)throws IOException{  
       File pathFile = new File(descDir);  
       if(!pathFile.exists()){  
           pathFile.mkdirs();  
       }  
       @SuppressWarnings("resource")
		ZipFile zip = new ZipFile(zipFile);  
       for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
           ZipEntry entry = (ZipEntry)entries.nextElement();  
           String zipEntryName = entry.getName();  
           InputStream in = zip.getInputStream(entry);  
           String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
           //判断路径是否存在,不存在则创建文件路径  
           File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
           if(!file.exists()){  
               file.mkdirs();  
           }  
           //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
           if(new File(outPath).isDirectory()){  
               continue;  
           }  
           //输出文件路径信息  
           System.out.println(outPath);  
             
           OutputStream out = new FileOutputStream(outPath);  
           byte[] buf1 = new byte[1024];  
           int len;  
           while((len=in.read(buf1))>0){  
               out.write(buf1,0,len);  
           }  
           in.close();  
           out.close();  
           }  
       System.out.println("******************解压完毕********************");  
   }  
   
   public static void MoveFolderAndFileWithSelf(String from, String to) throws Exception {  
       try {  
           File dir = new File(from);  
           // 目标 
           File moveDir = new File(to);  
           if(dir.isDirectory()){  
               if (!moveDir.exists()) {  
                   moveDir.mkdirs();  
               }  
           }else{  
               File tofile = new File(to);  
               dir.renameTo(tofile);  
               return;  
           }  
             
           //System.out.println("dir.isDirectory()"+dir.isDirectory());  
           //System.out.println("dir.isFile():"+dir.isFile());  
             
           // 文件一览  
           File[] files = dir.listFiles();  
           if (files == null)  
               return;  
 
           // 文件移动  
           for (int i = 0; i < files.length; i++) {  
               System.out.println("文件名："+files[i].getName());  
               if (files[i].isDirectory()) {  
                   MoveFolderAndFileWithSelf(files[i].getPath(), to);  
                   // 成功，删除原文件  
                   files[i].delete();  
               }  
               File moveFile = new File(moveDir.getPath() + "/" + files[i].getName());  
               // 目标文件夹下存在的话，删除  
               if (moveFile.exists()) {  
                   moveFile.delete();  
               }  
               files[i].renameTo(moveFile);  
           }  
           dir.delete();  
       } catch (Exception e) {  
           throw e;  
       }  
   }  


}
