package com.tutorialspoint.lucene;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term ;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
public class LuceneTester_Sort {
	String indexDir ;
	String dataDir ;
	Indexer indexer;
	Searcher searcher;
        String sortingResultRelevance = "";
        String sortingResultIndex = "";
        /*
	public static void main(String[] args) {
		LuceneTester_Sort testerS;
		try {
			testerS = new LuceneTester_Sort();
			testerS.sortUsingRelevance("Mohan");
			testerS.sortUsingIndex("Mohan");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
*/
	public void sortUsingRelevance(String searchQuery)throws IOException, ParseException{
		//System.out.println("Sort by Relevance");
                this.sortingResultRelevance +="Sort by Relevance\n";
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		//create a term to search file nam e
		Term term = new Term (LuceneConstants.CONTENTS, searchQuery);
		//create the term query object
		Query query = new FuzzyQuery(term );
		searcher.setDefaultFieldSortScoring(true, false);
		//do the search
		TopDocs hits = searcher.search(query,Sort.RELEVANCE);
		long endTime = System.currentTimeMillis();
		//System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "m s");
                this.sortingResultRelevance += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "m s\n";
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			//System.out.print("Score: "+ scoreDoc.score + " ");
                        
                        this.sortingResultRelevance += "Score: "+ scoreDoc.score + " \n";
                        
			//System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
                        
                        this.sortingResultRelevance += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
		}
		searcher.close();
	}
	public void sortUsingIndex(String searchQuery)throws IOException, ParseException{
		//System.out.println("Sort by Index");
                this.sortingResultIndex += "Sort by Index\n";
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		//create a term to search file nam e
		Term term = new Term (LuceneConstants.CONTENTS, searchQuery);
		//create the term query object
		Query query = new FuzzyQuery(term );
		searcher.setDefaultFieldSortScoring(true, false);
		//do the search
		TopDocs hits = searcher.search(query,Sort.INDEXORDER);
		long endTime = System.currentTimeMillis();
		//System.out.println(hits.totalHits +" documents found. Time :" + (endTime - startTime) + "m s");
                this.sortingResultIndex += hits.totalHits +" documents found. Time :" + (endTime - startTime) + "m s\n";
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			//System.out.print("Score: "+ scoreDoc.score + " ");
                        
                         this.sortingResultIndex += "Score: "+ scoreDoc.score + " \n";
                        
			//System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
                        
                        this.sortingResultIndex += "File: "+ doc.get(LuceneConstants.FILE_PATH)+"\n";
		}
		searcher.close();
	}
        
        
        
        public String getResultRelevance(){
        
        
        return "------------------------------------------------------\n"+"Sorting Logs (sortUsingRelevance)\n"+"------------------------------------------------------\n"+this.sortingResultRelevance; 
        
        
        }
        
        public String getResultIndex(){
        
        return "------------------------------------------------------\n"+"Sorting Logs (sortUsingIndex)\n"+"------------------------------------------------------\n"+this.sortingResultIndex; 
        
        
        }
}