package com.tutorialspoint.lucene;
import java.io.IOException;
public class LuceneTester {
	//String indexDir = "D:\\IR Material-20170911T002619Z-001\\IR Material\\Index2";
	//String dataDir = "D:\\IR Material-20170911T002619Z-001\\IR Material\\Data1";
    
        String indexDir ;
	String dataDir ;
	Indexer indexer;
        long startTime;
        long endTime;
        int numIndexed;
        String indexerLog;
        /*
	public void StartIndex() {
		LuceneTester tester;
		try {
			tester = new LuceneTester();
			tester.createIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	public void createIndex() throws IOException{
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System .currentTimeMillis();
                this.startTime = startTime;
                this.endTime = endTime;
                this.numIndexed = numIndexed;
                this.indexerLog = indexer.indexerLogs;
		indexer.close();
		//System.out.println(this.numIndexed+" File indexed, time taken: "+(this.startTime-this.endTime)+" m s");
                
	}
        
        public String getIndexResult(){
        
            return (this.indexerLog  +  "\n" + this.numIndexed+" File indexed, time taken: "+(this.endTime-this.startTime)+" m s");
        
        }


        
        
}