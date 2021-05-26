// author: Alexandra Strompová
// GUID: 2483859S
package detectors;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

//prints out the collection of breakpoints
//calls the visitor methods
//stores results into breakpoints collection for useless control flow and recursions

public class Driver {
	
	public static void main(String args []) {
		try {
			JavaParser.getStaticConfiguration().setAttributeComments(false);
			CompilationUnit cu = JavaParser.parse(new FileInputStream(args[0]));
			List<Breakpoints> breakpoints= new ArrayList<Breakpoints>();
			VoidVisitor<List<Breakpoints>> methodVisitor= new UselessControlFlowDetector();
			methodVisitor.visit(cu,breakpoints);
			
			List<Breakpoints> breakpoints_rec= new ArrayList<Breakpoints>();
			VoidVisitor<List<Breakpoints>> recurVisitor= new RecursionDetector();
			recurVisitor.visit(cu,breakpoints_rec);
			

			System.out.println("Useless Control Flows:");
			for(Breakpoints b:breakpoints) {
				System.out.println(b.toString());
			}			
			
			System.out.println("Recursions:");
			for(Breakpoints b:breakpoints_rec) {
				System.out.println(b.toString());
			}	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}