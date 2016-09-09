package net.zarski.commons.alg.corman;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CutRodDirectTimeoutTest extends BaseTest {
	private static final int MIN_TIMEOUT = 100;
	
	@Test(expected = TimeoutException.class)
	public void bigData(){
		CutRod cutRod2 = new CutRodDirect(bigPrices());
		System.out.println(cutRod2.solve(40));
		
	}
	
	@Rule
    public Timeout timeout = new Timeout(MIN_TIMEOUT) {
        public Statement apply(Statement base, Description description) {
            return new FailOnTimeout(base, MIN_TIMEOUT) {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        super.evaluate();
                        throw new TimeoutException();
                    } catch (Exception e) {}
                }
            };
        }
    };
}
