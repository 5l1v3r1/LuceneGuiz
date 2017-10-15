package com.tutorialspoint.lucene;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import java.util.Scanner; 
public class LuceneTester_Search {
	String indexDir ;
	//String dataDir = "D:\\IR Material-20170911T002619Z-001\\IR Material\\Data1";
        
        String searchResult;
	Searcher searcher;

	public void search(String searchQuery) throws IOException, ParseException{
		searcher = new Searcher(indexDir);
		long startTime = System .currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System .currentTimeMillis();
		//System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) +" m s");
                this.searchResult = hits.totalHits +" documents found. Time :" + (endTime - startTime) +" m s"+"\n";
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			//System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
                        this.searchResult += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
		}
		searcher.close();
	}
        
        public String getSearchResult(){
        
            return "------------------------------------------------------\n"+"Indexing Logs\n"+"------------------------------------------------------\n"+this.searchResult;
        
        }
}