package net.alexandroid.shprefhelper;

import android.app.Activity;
import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.widget.TextView;

import net.alexandroid.shpref.ShPref;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private  UiDevice mDevice;

    @Rule
    public MainActivityTestRule<MainActivity> mMainActivityActivityTestRule = new MainActivityTestRule<>(MainActivity.class);

    @Before
    public void beforeEachTestClearShPref() {
        ShPref.clear();
        mDevice = UiDevice.getInstance(getInstrumentation());
        System.out.println("=== ShPref is cleared ===");
    }

    @Test
    @Ignore
    public void testUI() {
        Activity activity = mMainActivityActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.textView));
        TextView textView = (TextView) activity.findViewById(R.id.textView);
        assertTrue(textView.isShown());
        assertEquals(textView.getText(), activity.getString(R.string.sample_app));
    }

    @Test
    @Ignore
    public void testPressBackButton() throws RemoteException {
        UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
        if (mDevice.isScreenOn()) {
            mDevice.setOrientationLeft();
            mDevice.openNotification();
        }
        mDevice.pressBack();
    }



    @Test
    public void testSaveAndLoad() {
        UiObject loadBtn = getUiObjectByText(R.string.load);
        UiObject saveBtn = getUiObjectByText(R.string.save);
        waitSeconds(1);
        clickOnWidget(loadBtn);
        clickOnWidget(saveBtn);
        clickOnWidget(loadBtn);
    }


    @Test
    public void testContainsRemove() {
        UiObject saveBtn = getUiObjectByText(R.string.save);
        UiObject containsBtn = getUiObjectByText(R.string.contains);
        UiObject removeBtn = getUiObjectByText(R.string.remove);
        waitSeconds(1);
        clickOnWidget(containsBtn);
        clickOnWidget(saveBtn);
        clickOnWidget(containsBtn);
        clickOnWidget(removeBtn);
        clickOnWidget(containsBtn);
    }

    /*

    @Test
    public void testSaveAndLoadWithEditor() {
    }

    @Test
    public void testLogger() {
    }

    @Test
    public void testSaveAndLoadList() {
    }*/

    private UiObject getUiObjectByText(int stringRes) {
        Activity activity = mMainActivityActivityTestRule.getActivity();
        UiSelector selector = new UiSelector().text(activity.getString(stringRes));
        return mDevice.findObject(selector);
    }

    private void clickOnWidget(UiObject aWidget) {
        try {
            aWidget.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        waitSeconds(4);
    }

    private void waitSeconds(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
