// author: Alexandra Strompová
// GUID: 2483859S
package detectors;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class RecursionDetector extends VoidVisitorAdapter <List<Breakpoints>>{
	
	private String methodName;
	private String className;
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, List<Breakpoints> arg) {
		this.className=n.getName().asString();
		super.visit(n, arg);
	}
	
	public void visit(MethodDeclaration n, List<Breakpoints> arg) {
		this.methodName= n.getName().asString();		
		super.visit(n, arg);
		}
	
	@Override
	public void visit(MethodCallExpr n, List<Breakpoints> arg) {
		String name=n.getNameAsString();
		if(methodName.equals(name)) {
			Breakpoints e= new Breakpoints(this.className, this.methodName, n.getRange().get().begin.line, n.getRange().get().end.line);
			arg.add(e);
		}
		super.visit(n, arg);
	}

}
