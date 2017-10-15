package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_TermRangeQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultTermRangeQuery = "";
   boolean queryOptions;
   Query query;
/*
   public static void main(String[] args) {
      LuceneTester_TermRangeQuery testerTRQ;
      try {
         testerTRQ = new LuceneTester_TermRangeQuery();
         testerTRQ.searchUsingTermRangeQuery("record2.txt","record6.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingTermRangeQuery(String searchQueryMin,
      String searchQueryMax)throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create the term query object
      if(queryOptions){
          Query query = new TermRangeQuery(LuceneConstants.FILE_NAME,searchQueryMin,searchQueryMax,true,false);
          this.query = query;
      }else{
          Query query = new TermRangeQuery(LuceneConstants.CONTENTS,searchQueryMin,searchQueryMax,true,false);
          this.query = query;
      }
      
      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

      //System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms");
      this.resultTermRangeQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
        // System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
        this.resultTermRangeQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   public String getResultTermRangeQuery(){
   
   return "------------------------------------------------------\n"+"TermRangeQuery Logs\n"+"------------------------------------------------------\n"+this.resultTermRangeQuery;
   
   
   }
}