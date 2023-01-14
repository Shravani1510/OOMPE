package assignmentOne;

import org.mariuszgromada.math.mxparser.Function;

public class IntegrationThreePointThree{
 
	// first graph integration    

	public static double integrateGraphOne(Function f, double u, double v, long gridPoints) {
        
       double sum=0, p=0, k;
               
        if (u > v) {
              return integrateGraphOne(f, u, v, gridPoints);
            }
              
        else {
              for (k = u; k <= v; k+= p) {
                     p = (v-u)/gridPoints;
                     sum = sum + p * f.calculate(k);
             }
          }
           
        return (double)(Math.round(sum * 1000) / 1000);
         }
 
	// second graph integration 

	public static double integrateGraphTwo(Function f, double u, double v, long gridPoints) {
    
       double sum=0, p=0, k;
               
       if (u > v) {
              return integrateGraphTwo(f, u, v, gridPoints);
            }
              
       else {
              for (k = u; k <= v; k+=p) {
            	  p = (v-u)/gridPoints;
            	  sum = sum + (p/2) *( f.calculate(k) + f.calculate(k + p));
            	               
            }
          }
           
        return (double)(Math.round(sum * 1000) / 1000);
         }    
 
	// third graph integration 

	public static double integrateGraphThree(Function f, double u, double v, long gridPoints) {
    
double sum=0, p=0, k;
               
 if (u > v) {
              return integrateGraphThree(f, u, v, gridPoints);
            }
              
       else {
              for (k = u; k <= v; k+=p ) {
            	  p = (v-u)/gridPoints;
            	 double q1 = k+p*0.5; 
            	 double q2 = k+p+p*0.5;
            	                       	              			             		 
                     double  r1 = (f.calculate(k)*((k+p)-q1) + f.calculate(k+p)*(q1-k))/((k+p)-k);
                     double  r2 = (f.calculate(k)*((k+p)-q2) + f.calculate(k+p)*(q2-k))/((k+p)-k);
                    
                     sum = sum + (p/2) *( r1 + r2);
                     
                 }
              }
                 
        return (double)(Math.round(sum * 1000) / 1000);
         }
	
public static void main(String[] args) {
	
	
		Function powerFunction = new Function("f(t)= (124567/40000)*t + sin(2*pi*2*t)");
		
	    double AreaOne = IntegrationThreePointThree.integrateGraphOne(powerFunction, 0, 20, 200);	
	    double AreaTwo = IntegrationThreePointThree.integrateGraphTwo(powerFunction, 0, 20, 200);
	    double AreaThree = IntegrationThreePointThree.integrateGraphThree(powerFunction, 0, 20, 200);
	    
	    double exactArea = 628.520;
	    
	    double absoluteErrorOne = (Math.abs(exactArea - AreaOne));
	    double absoluteErrorTwo = (Math.abs(exactArea - AreaTwo));
	    double absoluteErrorThree = (Math.abs(exactArea - AreaThree));
	    
	    double relativeErrorOne = (absoluteErrorOne*100/exactArea);
	    double relativeErrorTwo = (absoluteErrorTwo*100/exactArea);
	    double relativeErrorThree = (absoluteErrorThree*100/exactArea);
	    
	    System.out.println("Calculated Area One: " + AreaOne +  "\n Absolute Error One: " +  absoluteErrorOne  + "\n Relative Error One: " + relativeErrorOne);
	    System.out.println("\n Calculated Area Two: " + AreaTwo +  "\n Absolute Error Two: " +  absoluteErrorTwo  + "\n Relative Error Two: " + relativeErrorTwo);
	    System.out.println("\n Calculated Area Three: " + AreaThree +  "\n Absolute Error Three: " +  absoluteErrorThree  + "\n Relative Error Three: " + relativeErrorThree);
	}
		
	
	
       }