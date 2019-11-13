package pack1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class TestRateCsv1 { 

	public static void main(String[] args) {
        String fileName = "f:\\freelancer\\java-rates\\Rate.csv";
		File csvFile = new File(fileName);
		CsvMapper mapper = new CsvMapper();
		List<Item1> items = new ArrayList<Item1>();
		CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
		int i=0;
		try{
		MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
		   .with(schema)
		   .readValues(csvFile);
		while (it.hasNext()) {
			i++;
			if(i > 10000){
				break;
			}
		  Map<String,String> rowAsMap = it.next();
		  // access by column name, as defined in the header row...
		  
		  
		   //  System.out.println(rowAsMap.get("StateCode") +" " + rowAsMap.get("PlanId") + " " + rowAsMap.get("IndividualRate"));
		     Item1 it1 = new Item1();
		  
		     it1.setStatePlan(rowAsMap.get("StateCode") +" " + rowAsMap.get("PlanId"));
		  
		     it1.setIndividualRate(Double.parseDouble(rowAsMap.get("IndividualRate")));
		   
		     items.add(it1);
		    
	    	}
		
		 
		}
		catch(Exception ex){
		   System.out.println(ex.getMessage());
		}
		 // Group BY 
		 Map<String, Double> sum = items.stream().collect(
	                Collectors.groupingBy(Item1::getStatePlan, Collectors.averagingDouble(Item1::getIndividualRate)));

	        System.out.println(sum);
     

         
    }

}

