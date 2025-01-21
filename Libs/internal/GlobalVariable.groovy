package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object authToken
     
    /**
     * <p></p>
     */
    public static Object consid
     
    /**
     * <p></p>
     */
    public static Object roomkey
     
    /**
     * <p></p>
     */
    public static Object base_url
     
    /**
     * <p></p>
     */
    public static Object chronos_url
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters(), selectedVariables)
    
            authToken = selectedVariables['authToken']
            consid = selectedVariables['consid']
            roomkey = selectedVariables['roomkey']
            base_url = selectedVariables['base_url']
            chronos_url = selectedVariables['chronos_url']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
