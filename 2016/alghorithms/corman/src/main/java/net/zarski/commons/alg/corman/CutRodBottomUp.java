package net.zarski.commons.alg.corman;

public class CutRodBottomUp implements CutRod {
	private final int[] prices;
	private int[] cache;
	
	public CutRodBottomUp(int[] prices){
		this.prices = prices;
	}

	public int solve(int rodLength){
    	if(rodLength > prices.length-1){
			int a = prices.length-1;
			int r = rodLength - (prices.length-1);
			return cutRod(prices,a) + solve(r);
		}else

    	return cutRod(prices, rodLength);
    }
	
	public int cutRod(int[] prices, int rodLength){
		cache = new int[rodLength+1];
		
		cache[0] = 0;
		int maxValue = Integer.MIN_VALUE;;
		
		for(int j=1; j<=rodLength; j++){
			maxValue = Integer.MIN_VALUE;
			
			for(int i= 1; i<=j; i++){
				maxValue = max(maxValue, prices[i] + cache[j-i]);
			}
			cache[j] = maxValue;
		}
		
		return cache[rodLength];
    }

	//Own implementation only according to fact, that SW-Test exam is forbidding using math.
    private int max(int i1, int i2){
    	return i1 > i2 ?  i1 : i2;
    }

}
