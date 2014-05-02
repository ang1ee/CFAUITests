package cs169.carfollowingapp.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestRegistrationUI extends UiAutomatorTestCase {

	protected void setUp() throws Exception {
        super.setUp();

        getUiDevice().pressHome();
        
        // We’re now in the home screen. Next, we want to simulate 
        // a user bringing up the All Apps screen.
        // If you use the uiautomatorviewer tool to capture a snapshot 
        // of the Home screen, notice that the All Apps button’s 
        // content-description property has the value “Apps”.  We can 
        // use this property to create a UiSelector to find the button. 
        UiObject allAppsButton = new UiObject(new UiSelector()
           .description("Apps"));
        
        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow();
        
        // In the All Apps screen, the Settings app is located in 
        // the Apps tab. To simulate the user bringing up the Apps tab,
        // we create a UiSelector to find a tab with the text 
        // label “Apps”.
        //UiObject appsTab = new UiObject(new UiSelector()
        //   .text("Apps"));
        
        // Simulate a click to enter the Apps tab.
        //appsTab.click();

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon.  Since the container view 
        // is scrollable, we can use a UiScrollable object.
        UiScrollable appViews = new UiScrollable(new UiSelector()
           .scrollable(true));
        //appViews = appViews.setAsHorizontalList();
        
        // Create a UiSelector to find the Settings app and simulate      
        // a user click to launch the app. 
        UiObject cfaApp = appViews.getChildByText(new UiSelector()
           .className(android.widget.TextView.class.getName()), 
           "LoginActivity");
        cfaApp.clickAndWaitForNewWindow();
        
        // Validate that the package name is the expected one
        UiObject cfaValidation = new UiObject(new UiSelector()
           .packageName("cs169.carfollowingapp"));
        assertTrue("Unable to detect app", 
           cfaValidation.exists());   
                    
    }
	public void testRegistration() throws UiObjectNotFoundException {   
 
        UiObject registerButton = new UiObject(new UiSelector().text("Sign Up for Caravan"));
        

        registerButton.clickAndWaitForNewWindow();
                
        UiObject label = new UiObject(new UiSelector().text("RegisterActivity"));
        assertNotNull("RegisterActivity", label.getText());
        
        UiObject usernameInput = new UiObject(new UiSelector().description("Username"));
        UiObject passwordInput = new UiObject(new UiSelector().description("Password"));
        UiObject emailInput = new UiObject(new UiSelector().description("email"));
        
        usernameInput.setText("new");
        passwordInput.setText("new");
        emailInput.setText("new@gmail.com");
        
        registerButton = new UiObject(new UiSelector().text("Register"));
        registerButton.clickAndWaitForNewWindow();
        
        label = new UiObject(new UiSelector().text("LoginActivity"));
        assertNotNull("LoginActivity", label.getText());



    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
