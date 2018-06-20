package classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Calendar implements Serializable{
	
	public static boolean isBefore(LocalDate deadline, LocalDate startingDate){
	    int cmp = (deadline.getYear() - startingDate.getYear());
	    if(cmp<0) return true;
	    else if(cmp>0) return false;
	    else{
	    	cmp = (deadline.getMonthValue() - startingDate.getMonthValue());
	    	if(cmp<0) return true;
	    	else if(cmp>0) return false;
	    	else{
	    		cmp = (deadline.getDayOfMonth() - startingDate.getDayOfMonth());
	    		if(cmp<=0) return true;
	    		else return false;
	    	}
	    }
	}
}
