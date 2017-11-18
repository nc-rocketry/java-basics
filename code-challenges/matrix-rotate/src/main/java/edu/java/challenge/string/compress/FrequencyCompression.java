package edu.java.challenge.string.compress;

public class FrequencyCompression {

    public static void main(String[] args) {
        
        StringBuilder compressed= new StringBuilder();
        String z= "";

        String str= "axabbbbbbbcccicxxxxxxyyz";
        
        for (int i= 1; i < str.length(); i++) {
            char c0= str.charAt(i-1);
            char c1= str.charAt(i);
            compressed.append(c0);

            if (c0 != c1) {
                // character changed, reset internal buffer
                compressed.append(z);
                z= "";        // reset 
                if (i+1 == str.length()) { compressed.append(c1); }
                continue;    // next
            }
            
            int counter= 1;
            while (c0 == c1 && i+1 < str.length()) {
                c1= str.charAt(++i);
                counter++;
                z= ""+ counter;
            }
            
            compressed.append(z);
            z= "";
            
            if (i+1 == str.length()) {
                // last char of string
                compressed.append(c1);
            }
            
        }
        
        System.out.println("original  : "+ str);
        System.out.println("compressed: "+ compressed);
        System.out.println("decompress: "+ decompress(compressed.toString()));
        System.out.println("original  : "+ "axab7c3icx6y3");
        System.out.println("decompress: "+ decompress("axab7c3icx6y3"));
    }

    
    
    public static String decompress(String compressed) {
    	
    	boolean lastChar= true;
    	StringBuilder sb= new StringBuilder();
    	
    	for (int i= 1; i < compressed.length(); i++) {
    		char c= compressed.charAt(i-1);

    		sb.append(c); // write character

    		char digit= compressed.charAt(i);

    		if ( ! (digit >= '0' && digit <= '9')) {
    			continue;
    		}

    		String frequency= "";
    		while (digit >= '0' && digit <= '9') {
    			i++; // advance pointer
    			frequency+= digit;
    			if (i >= compressed.length()) { 
    				// deal with the final character
    				lastChar= false;
    				break;
    			}
    			digit= compressed.charAt(i);
    		}

    		for (int j= 0; j < Integer.parseInt(frequency) - 1; j++) {
    		    sb.append(c);
    		}
    		
    	}

    	if (lastChar) {
    	    sb.append(compressed.charAt(compressed.length() - 1));
    	}
    	
    	return sb.toString();

    }
}
