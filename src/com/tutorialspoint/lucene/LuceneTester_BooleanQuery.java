package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_BooleanQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultBooleanQuery="";
   boolean queryOptions;
   boolean mustOrNot1;
   boolean mustOrNot2;
   Term term1;
   Term term2;
/*
   public static void main(String[] args) {
      LuceneTester_BooleanQuery tester;
      try {
         tester = new LuceneTester_BooleanQuery();
         tester.searchUsingBooleanQuery("record1.txt","record1");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingBooleanQuery(String searchQuery1,
      String searchQuery2)throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create a term to search file name
      
      if(queryOptions){
          Term term1 = new Term(LuceneConstants.FILE_NAME, searchQuery1);
          Term term2 = new Term(LuceneConstants.FILE_NAME, searchQuery2);
          this.term1 = term1;
          this.term2 = term2;
      }else{
          Term term1 = new Term(LuceneConstants.CONTENTS, searchQuery1);
          Term term2 = new Term(LuceneConstants.CONTENTS, searchQuery2);
          this.term1 = term1;
          this.term2 = term2;
      }
      
      
      
      //create the term query object
      Query query1 = new TermQuery(term1);

      
      //create the term query object
      Query query2 = new PrefixQuery(term2);

      BooleanQuery query = new BooleanQuery();
      
      if(mustOrNot1){
          query.add(query1,BooleanClause.Occur.MUST);
      }else{
          query.add(query1,BooleanClause.Occur.MUST_NOT);
      }
      
      if(mustOrNot2){
          query.add(query2,BooleanClause.Occur.MUST);
      }else{
          query.add(query2,BooleanClause.Occur.MUST_NOT);
      }
      

      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

     // System.out.println(hits.totalHits +
     //       " documents found. Time :" + (endTime - startTime) + "ms");
      
      this.resultBooleanQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
        // System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
        this.resultBooleanQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
      }
      searcher.close();
   }
   
   
   public String getResultBooleanQuery(){
   return "------------------------------------------------------\n"+"BooleanQuery Logs\n"+"------------------------------------------------------\n"+this.resultBooleanQuery;
   }
}