import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.jfree.ui.RefineryUtilities;

public class Main {

	public static void main(String[] args) {
		
		//lambda expressions
		myEquation fx = (double x) -> { return Math.pow(x, 2) * Math.sin(x); };
		myEquation fPrime = (double x) -> { return Math.pow(x, 2) * Math.cos(x) + 2.0 * x * Math.sin(x); };
		myEquation fxQx = (double x) -> { return Math.cos(Math.pow(x, 2) * Math.sin(x) + Math.acos(x)); };
		myEquation gx = (double x) -> { return (Math.pow(x, 2) * Math.sin(x)) - x; };
		myEquation gPrime = (double x) -> { return (Math.pow(x, 2) * Math.cos(x) + 2.0 * x * Math.sin(x)) - 1.0; };
		myEquation gxQx = (double x) -> { return Math.pow(x, 2) * Math.sin(x); };
		myEquation hx = (double x) -> { return Math.cbrt(x); };
		myEquation hPrime = (double x) -> { return (1.0/3.0) * Math.pow(Math.cbrt(x), -2); };
		
		double root = 0.0;
		
		//newtons method
		ArrayList<Double> newtonEq1ErrorValues = new ArrayList<Double>();
		ArrayList<Double> newtonEq1Iterations = new ArrayList<Double>();
		ArrayList<Double> newtonEq2ErrorValues = new ArrayList<Double>();
		ArrayList<Double> newtonEq2Iterations = new ArrayList<Double>();
		ArrayList<Double> newtonEq3ErrorValues = new ArrayList<Double>();
		ArrayList<Double> newtonEq3Iterations = new ArrayList<Double>();
		//equation 1
		double pzero = 0.5;
		newtonsMethod(pzero, root, fx, fPrime, newtonEq1ErrorValues, newtonEq1Iterations);
		//equation 2
		pzero = 0.5;
		newtonsMethod(pzero, root, gx, gPrime, newtonEq2ErrorValues, newtonEq2Iterations);
		//equation 3
		pzero = 0.5;
		newtonsMethod(pzero, root, hx, hPrime, newtonEq3ErrorValues, newtonEq3Iterations);
		System.out.println(newtonEq3ErrorValues);

		//bisection method
		ArrayList<Double> bisectionEq1ErrorValues = new ArrayList<Double>();
		ArrayList<Double> bisectionEq1Iterations  = new ArrayList<Double>();
		ArrayList<Double> bisectionEq2ErrorValues = new ArrayList<Double>();
		ArrayList<Double> bisectionEq2Iterations  = new ArrayList<Double>();
		ArrayList<Double> bisectionEq3ErrorValues = new ArrayList<Double>();
		ArrayList<Double> bisectionEq3Iterations  = new ArrayList<Double>();
		double a = -0.9, b = 1.0;
		//bisection equation1
		bisectionMethod(a, b, root, fx, bisectionEq1ErrorValues, bisectionEq1Iterations);
		//bisection equation2
		bisectionMethod(a, b, root, gx, bisectionEq2ErrorValues, bisectionEq2Iterations);
		//bisection equation3
		bisectionMethod(a, b, root, hx, bisectionEq3ErrorValues, bisectionEq3Iterations);

		//fixed point method
		ArrayList<Double> fixedPointEq1ErrorValues = new ArrayList<Double>();
		ArrayList<Double> fixedPointEq1Iterations = new ArrayList<Double>();
		ArrayList<Double> fixedPointEq2ErrorValues = new ArrayList<Double>();
		ArrayList<Double> fixedPointEq2Iterations = new ArrayList<Double>();
		double p1 = 0.5;
		//fixed point equation 1
		fixedPointMethod(p1, root, fxQx, fixedPointEq1ErrorValues, fixedPointEq1Iterations);
		//fixed point second equation
		p1 = 0.5;
		fixedPointMethod(p1, root, gxQx, fixedPointEq2ErrorValues, fixedPointEq2Iterations);
		
		//graph equation 1
		final Graph demo = new Graph("f(x)", newtonEq1Iterations,newtonEq1ErrorValues, bisectionEq1Iterations, bisectionEq1ErrorValues, fixedPointEq1Iterations, fixedPointEq1ErrorValues);
		demo.pack();
	    RefineryUtilities.positionFrameOnScreen(demo, 0.5, 0.5);
	    demo.setVisible(true);
	    
	    //graph equation 2
	    final Graph demo2 = new Graph("g(x)", newtonEq2Iterations,newtonEq2ErrorValues, bisectionEq2Iterations, bisectionEq2ErrorValues, fixedPointEq2Iterations, fixedPointEq2ErrorValues);
		demo2.pack();
	    RefineryUtilities.positionFrameOnScreen(demo, 0.5, 0.5);
	    demo2.setVisible(true);
	    
	    //graph equation 3
	    final Graph demo3 = new Graph("h(x)", newtonEq3Iterations,newtonEq3ErrorValues, bisectionEq3Iterations, bisectionEq3ErrorValues);
	    demo3.pack();
	    RefineryUtilities.positionFrameOnScreen(demo, 0.5, 0.5);
	    demo3.setVisible(true);
	    
	    //graph3 bisection only
	    final Graph demo4 = new Graph("h(x) bisection method",bisectionEq3Iterations, bisectionEq3ErrorValues);
	    demo4.pack();
	    RefineryUtilities.positionFrameOnScreen(demo, 0.5, 0.5);
	    demo4.setVisible(true);
	}//end of main

	public static void newtonsMethod(double pn, double root, myEquation fx, myEquation fprime, ArrayList<Double> errorValues, ArrayList<Double> iterations) {
		AtomicInteger counter = new AtomicInteger(0);
		newtonsMethodAlgorithm(pn, root, fx, fprime, errorValues, counter);
		initializeCounterList(counter, iterations);
	}
	
	public static double newtonsMethodAlgorithm(double pn, double root, myEquation fx, myEquation fprime, ArrayList<Double> errorValues, AtomicInteger counter) {
		//when f(x) < 10^(-4) error is small enough to return pn(root value).
		if(Math.abs(pn - root) < Math.pow(10, -4) || counter.get() >= 25) {
			errorValues.add(pn - root);
			counter.incrementAndGet();
			return pn;
		}	
		errorValues.add(pn - root);
		double newP = pn - (fx.apply(pn)/fprime.apply(pn));
		//recursive call
		counter.incrementAndGet();
		pn = newtonsMethodAlgorithm(newP, root, fx, fprime, errorValues, counter);
		return pn;
	}
	
	public static void bisectionMethod(double a, double b, double root, myEquation fx, ArrayList<Double> errorValues, ArrayList<Double> iterations) {
		AtomicInteger counter = new AtomicInteger(0);
		bisectionMethodAlgorithm(a, b, root, fx, errorValues, counter);
		initializeCounterList(counter, iterations);
	}
	
	//bisection Method with lambdas
	public static double bisectionMethodAlgorithm(double a, double b, double root, myEquation fx, ArrayList<Double> errorValues, AtomicInteger counter) {
		double p = (a+b)/2;
		errorValues.add(p - root);
		double fp = fx.apply(p); 
		double fa = fx.apply(a);  
		counter.incrementAndGet();	
		//once error is less than 10^(-4) return x.
		if(Math.abs(p - root) < Math.pow(10, -4) || counter.get() >= 25) {
			errorValues.add(p - root);
			counter.incrementAndGet();
			return p;
		}
		// if which ever f(a) of f(b) is opposite sign of f(p) will be kept.
		// new points to insert are a or b and p.
		if(oppositeSigns(fp,fa)) 
			p = bisectionMethodAlgorithm(a, p, root, fx, errorValues, counter);
		else 
			p = bisectionMethodAlgorithm(p, b, root, fx, errorValues, counter);
		
		return p;	
	}
	
	public static void fixedPointMethod(double p, double root, myEquation gx,  ArrayList<Double> errorValues, ArrayList<Double> iterations) {
		AtomicInteger counter = new AtomicInteger(0);
		fixedPointMethodAlgorithm(p, root, gx, errorValues, counter);
		initializeCounterList(counter, iterations);
	}
	
	public static double fixedPointMethodAlgorithm(double p, double root, myEquation qx, ArrayList<Double> errorValues, AtomicInteger counter) {
		if(counter.get() > 100)	
			return p;
		
		if(Math.abs(p - root) < Math.pow(10, -4) || counter.get() >= 25) {
			errorValues.add(p-root);
			counter.incrementAndGet();
			return p;
		}
		errorValues.add(p-root);
		counter.incrementAndGet();	
		p = qx.apply(p);	
		p = fixedPointMethodAlgorithm(p, root, qx, errorValues, counter);	
		return p;
	}
	
	
	//check if two double are opposite signs. Utilized in bisection method.
	public static boolean oppositeSigns(double x, double y) 
    { 
        if(x < 0 && y > 0)
        	return true;
        else if(y < 0 &&  x > 0)
        	return true;
        else 
        	return false;
    } 
	
	public static void initializeCounterList(AtomicInteger counter, ArrayList<Double> iterations) {
		for(Double i = 0.0; i < counter.get() ; i++) 
			iterations.add(i);
	}	
}

	
	
	

