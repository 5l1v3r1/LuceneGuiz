package com.tutorialspoint.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester_PhraseQuery {
	
   String indexDir ;
   String dataDir ;
   Searcher searcher;
   String resultPhraseQuery = "";
   boolean queryOptions;
/*
   public static void main(String[] args) {
      LuceneTester_PhraseQuery testerP;
      try {
         testerP = new LuceneTester_PhraseQuery();
         String[] phrases = new String[]{"record1.txt"};
         testerP.searchUsingPhraseQuery(phrases);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
*/
   public void searchUsingPhraseQuery(String[] phrases)
      throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();

      PhraseQuery query = new PhraseQuery();
      query.setSlop(0);

      for(String word:phrases){
          if(queryOptions){
              query.add(new Term(LuceneConstants.FILE_NAME,word));
          }else{
              query.add(new Term(LuceneConstants.CONTENTS,word));
          }
         
      }

      //do the search
      TopDocs hits = searcher.search(query);
      long endTime = System.currentTimeMillis();

     // System.out.println(hits.totalHits +
     //    " documents found. Time :" + (endTime - startTime) + "ms");
     this.resultPhraseQuery += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "ms\n";
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
        // System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
        this.resultPhraseQuery += "File: "+ doc.get(LuceneConstants.FILE_PATH);
      }
      searcher.close();
   }
   
   public String getResultPhraseQuery(){
   
       return "------------------------------------------------------\n"+"PhraseQuery Logs\n"+"------------------------------------------------------\n"+this.resultPhraseQuery;
   
   }
}