import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {
	

	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		for(int r=0; r<board.size(); r++){
			for(int c=0; c<board.size(); c++){
				// define index
				int index = 0;
//				List<BoardCell> matchedSoFar = new ArrayList<BoardCell>();
				if (helper(board, r, c, list, word, index)){
					// do stuff
					return list; 
				}
			}
		}
		
		
		
		return new ArrayList<BoardCell>();
	}
	
	
	public boolean helper(BoggleBoard board, int r, int c, 
			List<BoardCell> list, String word, int index){
		
		if(index>word.length()-1){ return true; }
		if(r < 0 || c < 0 || r > board.size() || c > board.size() ){
			return false; // discontinue search 
		}
		BoardCell cell = new BoardCell(r, c);
		if(list.contains(cell)){ return false; } // no duplicating cells
		
		String current = board.getFace(r, c);
		int newIndex;
		if(current.equals("Qu")){newIndex = index+2; }
		else{ newIndex = index+1; }
		String compare = word.substring(index, newIndex);
		
		System.out.println(current);
		System.out.println(compare);
		
		if(current.equals(compare)){
			
			list.add(cell);
			int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
			int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
			for(int k=0; k < rdelta.length; k++){
			  if (helper(board, r+rdelta[k], c+cdelta[k], 
			    		list, word, newIndex)){ return true; }
			}
			
			// if you reach this point the word hasn't been found in neighbors
			list.remove(cell);
		}
		
		
		return false;
	}

}

