package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_FuzzyQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultFuzzyQuery ="";
   boolean queryOptions;
   Term term;
/*
   public static void main(String[] args) {
      LuceneTester_FuzzyQuery tester;
      try {
         tester = new LuceneTester_FuzzyQuery();
         tester.searchUsingFuzzyQuery("cord3.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
   */
   public void searchUsingFuzzyQuery(String searchQuery)
      throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create a term to search file name
     // System.out.println(queryOptions);
      if(queryOptions){
          Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
          this.term = term;
      }else{
          Term term = new Term(LuceneConstants.CONTENTS, searchQuery);
           this.term = term;
      }
    //  Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
      //create the term query object
      
      Query query = new FuzzyQuery(term);
      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

     // System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms");
      this.resultFuzzyQuery += hits.totalHits + " documents found. Time :" + (endTime - startTime) + "ms\n";

      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
        // System.out.print("Score: "+ scoreDoc.score + " ");
         this.resultFuzzyQuery += "Score: "+ scoreDoc.score + " \n";
        // System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
         this.resultFuzzyQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   public String getResultFuzzyQuery(){
   
   return "------------------------------------------------------\n"+"FuzzyQuery Logs\n"+"------------------------------------------------------\n"+this.resultFuzzyQuery;
   
   
   }
}