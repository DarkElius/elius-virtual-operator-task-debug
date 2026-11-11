/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package elius.virtualoperator.task.debug;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import elius.virtualoperator.task.Task;
import elius.virtualoperator.task.TaskProcess;
import elius.virtualoperator.task.TaskStatus;

/**
 * Elius Virtual Operator - Debug Process Task.
 * <p>
 * Genera un risultato casuale dopo un tempo di attesa configurato.
 * Viene utilizzata per simulare l'esecuzione di un processo durante le fasi di test.
 */
public class DebugProcess implements TaskProcess {
	
	// Get logger
	private static Logger logger = LogManager.getLogger(DebugProcess.class);
	
	// Task Status
	private TaskStatus tStatus;

	// Random number generator
	private final Random rand = new Random();


	@Override
    public String getName() {
        return "EVO_DEBUG";
    }

	@Override
    public String getDescription() {
        return "Debug Process for Elius Virtual Operator";
    }


    @Override
    public String getVersion() {
        return "0.0.2";
    }


	@Override
	public void execute(Task task) {

		logger.traceEntry("task={}", task);
				
		// Generate a status from ENDED, WARNING, ERROR
		tStatus =  TaskStatus.getById(5 + rand.nextInt(3));
		
		try { 
			// Do nothing
		    Thread.sleep(rand.nextInt(4000));
		    
		} catch (InterruptedException e) {
			logger.error("Error processing task", e);
			// Cleanup before interrupting
			Thread.currentThread().interrupt();
		}
		
		logger.traceExit("status={}", tStatus);
	}

	@Override
	public String getProcessResult() {
		return "This is a random result";
	}

	@Override
	public TaskStatus getTaskStatusResult() {		
		return tStatus;
	}

}