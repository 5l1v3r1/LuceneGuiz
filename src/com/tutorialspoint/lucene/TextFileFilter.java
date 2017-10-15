package com.tutorialspoint.lucene;
	import java.io.File;
	import java.io.FileFilter;
	import java.util.regex.Pattern;
	public class TextFileFilter implements FileFilter {
            
                NewJFrame1 nj1 = new NewJFrame1();
 
		@ Override
                
                
		public boolean accept(File pathname) {

                        /*
			 Pattern p = Pattern.compile("\\.(txt|docx|xlsx|pptx)$");
			 String path = pathname.getName().toLowerCase();		
			 return p.matcher(path).find();
			*/
                        
                        if(nj1.EXTENSION.equals(".txt")){
                            return pathname.getName().toLowerCase().endsWith(nj1.EXTENSION);
                        }else if(nj1.EXTENSION.equals(".docx")){
                            return pathname.getName().toLowerCase().endsWith(nj1.EXTENSION);
                        }else if(nj1.EXTENSION.equals(".pptx")){
                            return pathname.getName().toLowerCase().endsWith(nj1.EXTENSION);
                        }else if(nj1.EXTENSION.equals(".xlsx")){
                            return pathname.getName().toLowerCase().endsWith(nj1.EXTENSION);
                        }else if(nj1.EXTENSION.equals(".doc")){
                            return pathname.getName().toLowerCase().endsWith(nj1.EXTENSION);
                        }else{
                             Pattern p = Pattern.compile("\\.(txt|docx|xlsx|pptx|doc)$");
                             String path = pathname.getName().toLowerCase();		
                             return p.matcher(path).find();
                        }
			
                        
                
                
                }
                

                
                
}