package com.securonix.customprocessor;

import com.securonix.application.common.Processor;
import com.securonix.application.exception.common.ProcessorException;
import com.securonix.snyper.common.EnrichedEventObject;
import com.securonix.snyper.policy.beans.violations.ProcessorViolation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Custom test function executed by IEE 
 * 
 * @author Sudhanshu Umalkar 
 */
public class TestFunction implements Processor {

    /**
     * Logger for the class
     */
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Map<String, Object> process(Map<String, Object> parameters) throws ProcessorException {

        LOGGER.debug("Processor invoked- {}", parameters);

        final Map<String, Object> output = new HashMap<>();
        
        // retrieve the enriched event object from the input map
        final EnrichedEventObject eeo = (EnrichedEventObject) parameters.get("eeo");

        // apply the required validation
        if (eeo.getAccountname().equalsIgnoreCase("sumalkar@securonix.com")) {
            
            // create violation info that would be displayed on the UI for this violation
            ProcessorViolation pv = new ProcessorViolation();
            pv.addInfo("Account", eeo.getAccountname());
            pv.addInfo("2nd value", "This is test value");
            pv.addInfo("Time", "" + new Date());
            output.put("violation", pv);
        }

        return output;
    }

}
