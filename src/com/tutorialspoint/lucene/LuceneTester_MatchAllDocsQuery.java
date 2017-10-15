package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_MatchAllDocsQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultMatchAllDocsQuery="";
/*
   public static void main(String[] args) {
      LuceneTester_MatchAllDocsQuery testerAD;
      try {
         testerAD = new LuceneTester_MatchAllDocsQuery();
         testerAD.searchUsingMatchAllDocsQuery("");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingMatchAllDocsQuery(String searchQuery)
      throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create the term query object
      Query query = new MatchAllDocsQuery(searchQuery);
      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

      //System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms");
      this.resultMatchAllDocsQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
         //System.out.print("Score: "+ scoreDoc.score + " ");
         this.resultMatchAllDocsQuery += "Score: "+ scoreDoc.score + " \n";
         //System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
         this.resultMatchAllDocsQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   
   public String getResultMatchAllDocsQuery(){
   
    return "------------------------------------------------------\n"+"MatchAllDocsQuery Logs\n"+"------------------------------------------------------\n"+this.resultMatchAllDocsQuery;
   
   }
}