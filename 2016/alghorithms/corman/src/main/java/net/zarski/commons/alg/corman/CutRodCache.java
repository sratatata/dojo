package net.zarski.commons.alg.corman;

public class CutRodCache implements CutRod
{
	private final int[] prices;
	private int[] cache;
	
	public CutRodCache(int[] prices){
		this.prices = prices;
	}
	
    public int solve(int rodLength){
    	cache = initCache(Integer.MIN_VALUE, rodLength);
    	
    	if(rodLength > prices.length-1){
			int a = prices.length-1;
			int r = rodLength - (prices.length-1);
			return cutRod(prices,a, cache) + solve(r);
		}else

    	return cutRod(prices, rodLength, cache);
    }
    
    private int[] initCache(final int constant, final int size){
    	int[] r = new int[size+1];
    	for(int i=0; i<= size; i++){
    		r[i] = constant;
    	}
    	
    	return r;
    }
    
    public int cutRod(int[] prices, int rodLength, int[] cache){
    	//return value if cached
    	if(cache[rodLength] >= 0){
    		return cache[rodLength];
    	}
    	
    	//return 0 value for no rod
    	if(rodLength == 0){
			return 0;
		}
    	
		int maxValue = Integer.MIN_VALUE;
		
		for(int i=1; i<=rodLength; i++){
			maxValue = max(maxValue, prices[i]+cutRod(prices, rodLength-i, cache));
		}
		
		cache[rodLength] = maxValue;
		return maxValue;
    }
    
    
    
    //Own implementation only according to fact, that SW-Test exam is forbidding using math.
    private int max(int i1, int i2){
    	return i1 > i2 ?  i1 : i2;
    }
}
