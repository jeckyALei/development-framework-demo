package com.mwh.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.distexec.mapreduce.Collector;
import org.infinispan.distexec.mapreduce.MapReduceTask;
import org.infinispan.distexec.mapreduce.Mapper;
import org.infinispan.distexec.mapreduce.Reducer;
import org.infinispan.manager.DefaultCacheManager;

public class WordCountExample {

   /**
    * In this example replace c1 and c2 with
    * real Cache references
    *
    * @param args
    */
   public static void main(String[] args) {
	   Configuration c = new ConfigurationBuilder().clustering().cacheMode(CacheMode.LOCAL).build();
	  DefaultCacheManager defaultCacheManager=new DefaultCacheManager();
	  defaultCacheManager.defineConfiguration("c1", c);
	  defaultCacheManager.defineConfiguration("c2", c);
      Cache c1 = defaultCacheManager.getCache("c1");
      Cache c2 = defaultCacheManager.getCache("c2");

      c1.put("1", "Hello world here I am");
      c2.put("2", "Infinispan rules the world");
      c1.put("3", "JUDCon is in Boston");
      c2.put("4", "JBoss World is in Boston as well");
      c1.put("12","JBoss Application Server");
      c2.put("15", "Hello world");
      c1.put("14", "Infinispan community");
      c2.put("15", "Hello world");

      c1.put("111", "Infinispan open source");
      c2.put("112", "Boston is close to Toronto");
      c1.put("113", "Toronto is a capital of Ontario");
      c2.put("114", "JUDCon is cool");
      c1.put("211", "JBoss World is awesome");
      c2.put("212", "JBoss rules");
      c1.put("213", "JBoss division of RedHat ");
      c2.put("214", "RedHat community");

      MapReduceTask<String, String, String, Integer> t =
         new MapReduceTask<String, String, String, Integer>(c1);
      t.mappedWith(new WordCountMapper())
         .reducedWith(new WordCountReducer());
      Map<String, Integer> map=t.execute();
      Set<String> set= map.keySet();
      
      for(String key:set){
    	  System.out.println(key+"---"+map.get(key));
      }
//      String wordCountMap = t.execute(
//    	      new Collator<String,Integer,String>() {
//    	          @Override
//    	          public String collate(Map<String, Integer> reducedResults) {
//    	             String mostFrequent = "";
//    	             int maxCount = 0;
//    	             for (Entry<String, Integer> e : reducedResults.entrySet()) {
//    	                Integer count = e.getValue();
//    	                if(count > maxCount) {
//    	                   maxCount = count;
//    	                   mostFrequent = e.getKey();
//    	                }
//    	             }
//    	          return mostFrequent;
//    	          }
//    	       });
//      System.out.println(wordCountMap);
   }

   static class WordCountMapper implements Mapper<String,String,String,Integer> {
      /** The serialVersionUID */
      private static final long serialVersionUID = -5943370243108735560L;

      @Override
      public void map(String key, String value, Collector<String, Integer> c) {
         StringTokenizer tokens = new StringTokenizer(value);
         while (tokens.hasMoreElements()) {
            String s = (String) tokens.nextElement();
            c.emit(s, 1);
         }
      }
   }

   static class WordCountReducer implements Reducer<String, Integer> {
      /** The serialVersionUID */
      private static final long serialVersionUID = 1901016598354633256L;

      @Override
      public Integer reduce(String key, Iterator<Integer> iter) {
         int sum = 0;
         while (iter.hasNext()) {
            Integer i = (Integer) iter.next();
            sum += i;
         }
         return sum;
      }
   }
}