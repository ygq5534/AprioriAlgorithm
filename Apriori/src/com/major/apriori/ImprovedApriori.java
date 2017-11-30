package com.major.apriori;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.major.tuple.Record;

public class ImprovedApriori 
{
	 Set<Record> C;
	 Set<Record> L;
	 int min_support;
	 int dataset[][];
	 public static int step=1;
	    
	 public ImprovedApriori()
	 {
	    C=new HashSet<Record>();
	    L=new HashSet<Record>();
	    min_support=2;     
	 }
	public void readDataset_csv()throws IOException
    {
    	String csv="/home/anubhav55182/eclipse-workspace/Apriori/dataset.csv";
    	BufferedReader br=null;
    	String line="";
    	Map<Integer,List<Integer>> map;       // <Transaction_id,List of Itemsets>
    	List<Integer> temp;
    	try
    	{
    		br=new BufferedReader(new FileReader(csv));
    		map=new HashMap<Integer,List<Integer>>();
    		//line=br.readLine();
    		String transaction[];             // consists of purchased items id in a particular transaction
    		int i=1;
    		while((line=br.readLine())!=null)
    		{
    			transaction=line.split(",");
    			Integer id;                    // items id
    			temp=new LinkedList<Integer>();
    			for(String s:transaction)
    			{
    				id=Integer.parseInt(s);
    				temp.add(id);
    			}
    			map.put(i,temp);
    			++i;
    			
    		}
    		
    		Set<Integer> keyset=map.keySet();  // get the keys in map
    		dataset=new int[keyset.size()][];
    		Iterator<Integer> it=keyset.iterator();
    		int count=0;
    		while(it.hasNext())
    		{
    			temp=map.get(it.next());       // getting itemsets of each transaction
    			Integer items[]=temp.toArray(new Integer[0]);
    			dataset[count]=new int[items.length];
    			for(int j=0;j<items.length;j++)
    			{
    				dataset[count][j]=items[j].intValue();
    				//System.out.print(dataset[count][j]+" ");
    			}
    			//System.out.println();
    			++count;
    		}
    		
    		
    		
    		
    	}
    	catch(FileNotFoundException fnfe)
    	{
    		fnfe.printStackTrace();
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	finally
    	{
    		if(br!=null)
    		{
    			try
    			{
    				br.close();
    			}
    			catch(IOException e)
    			{
    				e.printStackTrace();
    			}
    		}
    	}
    }
	public void init()
    {
    	try
    	{
    		readDataset_csv();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	Set<Integer> candidate_set=new HashSet<Integer>();
    	for(int i=0;i<dataset.length;i++) 
    	{
    		for(int j=0;j<dataset[i].length;j++)
    		{
    			candidate_set.add(dataset[i][j]);
    		}
    	}
    	Iterator<Integer> it=candidate_set.iterator();
    	int item;
    	while(it.hasNext())
    	{
    		item=(int) it.next();
    		Set<Integer> s = new HashSet<>();
			s.add(item);
    		//C.add(new Record(s,count(s),-1,));
    	}
    
    	//prune();
    	//generateFrequentItemsets();
    }

	public static void main(String[] args) 
	{
		ImprovedApriori apriori=new ImprovedApriori();
	    long start=System.currentTimeMillis();
	    apriori.init();
	    long end=System.currentTimeMillis();
	    System.out.println("\nTime taken to run the Improved Apriori Algorithm= "+(end-start)+"ms");

	}

}