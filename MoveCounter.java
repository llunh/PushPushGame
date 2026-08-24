package term_;

public class MoveCounter {
	    private int count;

	    public MoveCounter() {
	        count = 0;
	    }

	    public void increment() {
	        count++;
	    }

	    public int getCount() {
	        return count;
	    }

	    public void reset() {
	        count = 0;
	    }
}
