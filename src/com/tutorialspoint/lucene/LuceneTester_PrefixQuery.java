package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_PrefixQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultPrefixQuery = "";
   boolean queryOptions;
   Term term;
/*
   public static void main(String[] args) {
      LuceneTester_PrefixQuery testerPrefix;
      try {
         testerPrefix = new LuceneTester_PrefixQuery();
         testerPrefix.searchUsingPrefixQuery("record1");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingPrefixQuery(String searchQuery)
      throws IOException, ParseException{
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
      
      
      
     // Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
      //create the term query object
      Query query = new PrefixQuery(term);
      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

    //  System.out.println(hits.totalHits +
    //     " documents found. Time :" + (endTime - startTime) + "ms");
    
    this.resultPrefixQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
        // System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
         this.resultPrefixQuery +="File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   public String getResultPrefixQuery(){
   
    return "------------------------------------------------------\n"+"PrefixQuery Logs\n"+"------------------------------------------------------\n"+this.resultPrefixQuery;
   }
}