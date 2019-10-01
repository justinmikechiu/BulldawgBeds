package boundary;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logiclayer.ApartmentLogicImpl;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Configuration cfg = null;

	private String templateDir = "/WEB-INF/templates";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
	}

	public void init() {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Specify the source where the template files come from.
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

		// Sets how errors will appear.
		// This handler outputs the stack trace information to the client, formatting it so 
		// that it will be usually well readable in the browser, and then re-throws the exception.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	/*
	 * This method is called by the doGet method. This method will create and process the template
	 * used to display the search results or an error page if the user input is invalid.
	 */
	public void runTemplate(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		Template template = null;
		String templateName = null; //template to be generated

		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		// The following Strings are used to check for a null value.
		String signIn = request.getParameter("sign in"); // home page "Sign in" button
		String signUp = request.getParameter("sign up"); // home page "Sign up" button
		String register = request.getParameter("register"); // signUp.ftl "Register" button
		String login = request.getParameter("login"); // signIn.ftl "Login" button
		String contact = request.getParameter("contact"); // home page "Contact Us" button
		String about = request.getParameter("about"); // home page "About Us" button
		String leaseMyApartment = request.getParameter("leaseMyApartment"); // home page "Lease your apartment" button
		String checkMessages = request.getParameter("checkMessages"); // home page "Inbox" button
//		String search = request.getParameter("search"); // search button
		String leaseIt = request.getParameter("leaseIt"); // submit their lease button
		String loginName = null;


		//begin checks to see what the input is
		if(signUp != null){ // check to see if user clicked the sign up button on the home page
			templateName = "signUp.ftl";
			
		} else if (register!= null){ // check to see if user clicked the register button on signUp.ftl
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			boolean validEmail = ApartmentLogicImpl.validEmail(email); // boolean check for valid inputs
			if (validEmail == false){
				templateName = "signUp.ftl";
				root.put("notValidEmail","yes");
			}
			
			boolean duplicateEmail = false; // first assume there is no duplicate email in the database
			
			if (validEmail){ // enter here if the user enters a valid @uga.edu email
				try {
					if(ApartmentLogicImpl.duplicateEmail(request, response, email) == true){
						duplicateEmail = true;
						templateName = "signUp.ftl";
						root.put("duplicateEmail","yes");
					}
				} catch (NumberFormatException e){
					}
			}

			int r = 0;
			if (duplicateEmail == false && validEmail == true){ // enter here if there is no duplicate email in the database
				try{
					r = ApartmentLogicImpl.newUser(request, response, name, email, password); // newUser method is called to add a new user into the database
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			
			if (r == 0){
				templateName = "signUp.ftl"; //error inserting the new user into the database. 
			} else{
				root.put("name", name);
				root.put("registerSuccessful","yes");
				templateName = "signIn.ftl";
			}
			
		} else if (signIn != null){ // check to see if user clicked the sign in button on the home page
			templateName = "signIn.ftl";
			
		} else if (login != null){ // check to see if user clicked the login button on signIn.ftl
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			root.put("email", email);
			
			boolean loggedIn = false;
			loggedIn = ApartmentLogicImpl.verifyLogin(request, response, email, password); // check for correct email and password 
			
			if (loggedIn) { // enter here if successfully login
				loginName = ApartmentLogicImpl.getLoginName(request, response, email); // retrieve the login name from the database
				templateName = "welcome.ftl";
				root.put("loginName", loginName);
			} else {
				root.put("failedLogin","yes");
				templateName = "signIn.ftl";
			}
			
		} else if (contact != null){ // check to see if user clicked the contact us button on the home page
			templateName = "contact.ftl";
			
		} else if (about != null){ // check to see if user clicked the contact us button on the home page
			templateName = "about.ftl";
			
		} else if (leaseMyApartment != null){ // check to see if user clicked the lease my apartment button on the home page
			templateName = "lease.ftl";
			
		} else if (checkMessages != null){ // check to see if user clicked the inbox button on the home page
			
//		} else if (search != null){ // check to see if user clicked the search button on the home page
//			templateName = "searchPage.ftl";
//			
		} else if (leaseIt != null){

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String location = request.getParameter("location");
			String picture = request.getParameter("pic");
			String semester = request.getParameter("semester");
			String priceX = request.getParameter("price");
			String bedsX = request.getParameter("beds");
			String image = null;
			int price = 0;
			int beds = 0;
			
			int r = 0;
			try{
				price = Integer.parseInt(priceX);
				beds = Integer.parseInt(bedsX);
			} catch (NumberFormatException e){
			}
			
				
				r = ApartmentLogicImpl.addLease(email, address, location, price, beds, name, semester, image);
				ApartmentLogicImpl.addPicture(request, response, name, picture);
				
				loginName = ApartmentLogicImpl.getLoginName(request, response, email); // retrieve the login name from the database
				root.put("loginName", loginName);

			
			templateName = "index.html";
		}
	
	
		
		try {
			template = cfg.getTemplate(templateName);
			response.setContentType("text/html");
			Writer out = response.getWriter();
			template.process(root, out); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				runTemplate(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
