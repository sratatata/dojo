package net.zarski.commons.alg.corman;

public class CutRodDirect implements CutRod {

	private final int[] prices;
	
	public CutRodDirect(int[] prices){
		this.prices = prices;
	}
	
	public int solve(int rodLength) {
		if(rodLength > prices.length-1){
			int a = prices.length-1;
			int r = rodLength - (prices.length-1);
			return cutRod(prices,a) + solve(r);
		}else

		return cutRod(prices, rodLength);
	}
	
	// r[n] = max(p[1] + r[n-i]) for 1 <= i <= n
	private int cutRod(int[] prices, int rodLength){
		if(rodLength == 0){
			return 0;
		}
		
		int maxValue = Integer.MIN_VALUE;
		
		for(int i=1; i<=rodLength; i++){
			maxValue = max(maxValue, prices[i]+cutRod(prices, rodLength-i));
		}
		
		return maxValue;
	}

	private int max(int v1, int v2) {
		return v1 > v2 ? v1 : v2;
	}

}
