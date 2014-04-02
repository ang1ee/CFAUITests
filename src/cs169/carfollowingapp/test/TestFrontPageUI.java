package cs169.carfollowingapp.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class TestFrontPageUI extends UiAutomatorTestCase {

	protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testBroadcasting() throws UiObjectNotFoundException {   
        
        // Simulate a short press on the HOME button.
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
        UiObject appsTab = new UiObject(new UiSelector()
           .text("Apps"));
        
        // Simulate a click to enter the Apps tab.
        appsTab.click();

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon.  Since the container view 
        // is scrollable, we can use a UiScrollable object.
        UiScrollable appViews = new UiScrollable(new UiSelector()
           .scrollable(true));
        
//        // Set the swiping mode to horizontal (the default is vertical)
//        appViews.setAsHorizontalList();
        
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
        
        
        UiObject usernameInput = new UiObject(new UiSelector().description("Username"));
        UiObject passwordInput = new UiObject(new UiSelector().description("Password"));
        UiObject loginButton = new UiObject(new UiSelector().text("Login"));
        
        usernameInput.setText("test");
        passwordInput.setText("test");
        loginButton.clickAndWaitForNewWindow();
                
        UiSelector activityLabel = new UiSelector().text("FrontPageActivity");
        assertNotNull("Wrong activity opened for FrontPageActivity when login", activityLabel);
        
        UiObject broadcastButton = new UiObject(new UiSelector().text("Broadcast"));
        broadcastButton.clickAndWaitForNewWindow();
        activityLabel = new UiSelector().text("BroadcastActivity");
        assertNotNull("Wrong activity opened for BroadcastActivity when broadcast", activityLabel);
        
        UiObject stopBroadcastingButton = new UiObject(new UiSelector().text("Stop Broadcasting"));
        stopBroadcastingButton.clickAndWaitForNewWindow();
        activityLabel = new UiSelector().text("FrontPageActivity");
        assertNotNull("Wrong activity opened for FrontPageActivity when stop broadcast", activityLabel);
        
        UiObject logoutButton = new UiObject(new UiSelector().text("Logout"));
        logoutButton.clickAndWaitForNewWindow();
        
        activityLabel = new UiSelector().text("LoginActivity");
        assertNotNull("Wrong activity opened", activityLabel);
        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
