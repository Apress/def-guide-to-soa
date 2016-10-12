import javax.servlet.http.HttpSession;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

/**
 * A controller class that contains logic, exception handlers, and state 
 * for the current web directory path. When a request is received for the 
 * page flow (/Controller.jpf), an action (/begin.do), or a page (/index.jsp), 
 * an instance of this class becomes the <em>current page flow</em>. By 
 * default, it is stored in the session while its actions and pages are being
 * accessed, and is removed when another page flow is requested.
 *
 * Properties in the current page flow may be accessed through the 
 * <code>pageFlow</code> databinding context in pages and in expression-aware
 * annotations. For example, if this class contains a 
 * <code>getSomeProperty</code> method, it can be accessed through the 
 * expression <code>${pageFlow.someProperty}</code>.
 *
 * There may be only one page flow in any package.
 */
@Jpf.Controller(simpleActions = { @Jpf.SimpleAction(name = "begin", path = "index.jsp") })
public class Controller extends PageFlowController {
	private static final long serialVersionUID = 1L;

	/**
	 * Callback that is invoked when this controller instance is created.
	 */
	@Override
	protected void onCreate() {
	}

	/**
	 * Callback that is invoked when this controller instance is destroyed.
	 */
	@Override
	protected void onDestroy(HttpSession session) {
	}
}