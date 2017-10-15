package com.tutorialspoint.lucene;

import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_TermQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultTermQuery="";
   boolean queryOptions;
   Term term;
/*
   public static void main(String[] args) {
      LuceneTester_TermQuery testerTQ;
      try {
         testerTQ = new LuceneTester_TermQuery();
         testerTQ.searchUsingTermQuery("record4.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingTermQuery(
      String searchQuery)throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create a term to search file name
      if(queryOptions){
          Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
          this.term = term;
      }else{
          Term term = new Term(LuceneConstants.CONTENTS, searchQuery);
          this.term = term;
      }
      //Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
      //create the term query object
      Query query = new TermQuery(term);
      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

     // System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms");
     this.resultTermQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
       //  System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
       
       this.resultTermQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   
   public String getResultTermQuery(){
   
       return "------------------------------------------------------\n"+"TermQuery Logs\n"+"------------------------------------------------------\n"+ this.resultTermQuery;
   
   }
}