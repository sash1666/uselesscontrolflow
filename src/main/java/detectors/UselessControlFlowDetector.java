// author: Alexandra Strompová
// GUID: 2483859S

package detectors;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UselessControlFlowDetector extends VoidVisitorAdapter <List<Breakpoints>>{
	private String methodName;
	private String className;
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, List<Breakpoints> arg) {
		this.className=n.getName().asString();
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodDeclaration n, List<Breakpoints> arg) {
		this.methodName= n.getName().asString();		
		
		//find all statements present
		List<Statement>statements=n.getChildNodesByType(Statement.class);
		
		//flag all empty statements
		for(Statement s:statements) {
			if(!(s.getParentNode().get() instanceof MethodDeclaration)) {
				if(!s.isSwitchStmt() && (s.isEmptyStmt() || (s.isBlockStmt() && s.getChildNodes().isEmpty()))) {
					Breakpoints e= new Breakpoints(this.className, this.methodName, s.getRange().get().begin.line, s.getRange().get().end.line);
					arg.add(e);
				}
			}
		super.visit(n, arg);
		}
	}
}

