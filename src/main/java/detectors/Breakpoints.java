// author: Alexandra Strompová
// GUID: 2483859S
package detectors;

//creates a string representing a breakpoint
public class Breakpoints {
	private String className;
	private String methodName;
	private int startline;
	private int endline; 
	
	public Breakpoints(String className, String methodName,
			int startline, int endline) {
		this.className=className;
		this.methodName=methodName;
		this.startline=startline;
		this.endline=endline;
	}
	
	@Override
	public String toString() {
		return "className="+this.className+", methodName="+this.methodName
				+", startline="+Integer.toString(this.startline)
				+", endline="+Integer.toString(this.endline);
	}
	
	

}
