package cpsc2150.extendedConnectX.models;
// Alex Jorgensen
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    private IGameBoard makeAGameBoard(int r, int c, int num) {
        return new GameBoard(r, c, num);
    }

    private String makeAString(char gb[][], int r, int c){
        // intialize expected, starting with a "|"
        String expected = "| ";
        // filing top row wit column numbers
        for (int i = 0; i < c; ++i){
            expected += i;
            if (i >= 9) {
                expected += "|";
            }
            else{
                expected += "| ";
            }
        }

        expected += "\n";

        // filling each index of the board with what is in each position
        for (int i = r - 1; i >= 0; i--){
            for (int j = 0; j < c; ++j){
                BoardPosition pos = new BoardPosition(i,j);
                expected += "|" + gb[i][j] + " ";
            }
            expected += "|\n";
        }
        return expected;
    }

    /**
     * TEST CONSTRUCTOR
     */
    @Test
    public void testGameBoard_smallest_board() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertEquals(3, gb.getNumToWin());
    }

    @Test
    public void testGameBoard_largest_board() {
        char[][] boardArray = new char[100][100];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(100, 100, 25);

        assertEquals(gb.toString(), makeAString(boardArray, 100, 100));
        assertEquals(25, gb.getNumToWin());
    }

    @Test
    public void testGameBoard_arbitrary_uneven() {
        char[][] boardArray = new char[15][20];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 20; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(15, 20, 10);

        assertEquals(gb.toString(), makeAString(boardArray, 15, 20));
        assertEquals(10, gb.getNumToWin());
    }

    /**
     * TEST CHECKIFFREE
     */
    @Test
    public void testCheckIfFree_full() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        for(int i = 0; i < 3; ++i){
            gb.placeToken('X', 0);
        }

        assertFalse(gb.checkIfFree(0));
    }

    @Test
    public void testCheckIfFree_empty() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        assertTrue(gb.checkIfFree(0));
    }

    @Test
    public void testCheckIfFree_partial() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        gb.placeToken('X', 0);

        assertTrue(gb.checkIfFree(0));
    }

    /**
     * TEST PLACETOKEN
     */
    @Test
    public void testPlaceToken_empty_board() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
    }

    @Test
    public void testPlaceToken_full_board() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = 'X';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                gb.placeToken('X',j);
            }
        }

        gb.placeToken('O',0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
    }

    @Test
    public void testPlaceToken_non_empty_column() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
    }

    @Test
    public void testPlaceToken_almost_full_column() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[2][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
    }

    @Test
    public void testPlaceToken_three_players() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'A';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('A', 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
    }

    /**
     * TEST CHECKHORIZWIN
     */
    @Test
    public void testHorizWin_no_win() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'X';
        boardArray[0][2] = 'O';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 2);
        gb.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0,1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testHorizWin_win_last_token_end() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'X';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0,2);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testHorizWin_win_last_token_middle() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'X';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 2);
        gb.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0,1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testHorizWin_no_win_chain_broken() {
        char[][] boardArray = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'X';
        boardArray[0][2] = 'O';
        boardArray[0][3] = 'X';

        IGameBoard gb = makeAGameBoard(4, 4, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        BoardPosition pos = new BoardPosition(0, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 4, 4));
        assertFalse(gb.checkHorizWin(pos, 'X'));
    }
    /**
     * TEST CHECKVERTWIN
     */
    @Test
    public void testVertWin_no_win_basic() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[2][0] = 'O';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(2, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void testVertWin_basic() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[2][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(2, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void testVertWin_no_win_vert_but_win_horiz() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[0][1] = 'X';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(1, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void testVertWin_no_win_chain_broken() {
        char[][] boardArray = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[2][0] = 'O';
        boardArray[3][0] = 'X';

        IGameBoard gb = makeAGameBoard(4, 4, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(3, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 4, 4));
        assertFalse(gb.checkDiagWin(pos, 'X'));
    }
    /**
     * TEST CHECKDIAGWIN
     */
    @Test
    public void testDiagWin_no_win() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'X';
        boardArray[0][1] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        BoardPosition pos = new BoardPosition(0, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkHorizWin(pos, 'X'));
    }
    @Test
    public void testDiagWin_win_down_left() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'O';
        boardArray[1][1] = 'X';
        boardArray[1][2] = 'O';
        boardArray[2][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(2, 2);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_down_right() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'O';
        boardArray[1][0] = 'O';
        boardArray[2][0] = 'X';
        boardArray[1][1] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(2, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_up_left() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'O';
        boardArray[1][0] = 'O';
        boardArray[2][0] = 'X';
        boardArray[1][1] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_up_right() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'O';
        boardArray[1][1] = 'X';
        boardArray[1][2] = 'O';
        boardArray[2][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_last_token_middle_up_right_down_left() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'O';
        boardArray[1][1] = 'X';
        boardArray[1][2] = 'O';
        boardArray[2][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(1, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_last_token_middle_up_left_down_right() {
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'O';
        boardArray[1][0] = 'O';
        boardArray[2][0] = 'X';
        boardArray[1][1] = 'X';
        boardArray[0][1] = 'O';
        boardArray[0][2] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(1, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    /**
     * TEST CHECKTIE
     */
    @Test
    public void testCheckTie_empty() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_full_board() {
        char[][] boardArray = new char[3][3];
        char token = 'A';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = token;
                ++token;
            }
        }


        IGameBoard gb = makeAGameBoard(3, 3, 3);
        token = 'A';
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                gb.placeToken(token, j);
                ++token;
            }
        }

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.checkTie());
    }
    @Test
    public void testCheckTie_one_column_open() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                boardArray[i][j] = 'X';
            }
        }
        boardArray[0][2] = ' ';
        boardArray[1][2] = ' ';
        boardArray[2][2] = ' ';

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 2; ++j){
                gb.placeToken('X', j);
            }
        }

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_one_space_open() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = 'X';
            }
        }
        boardArray[2][2] = ' ';

        IGameBoard gb = makeAGameBoard(3, 3, 3);

        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 2; ++j){
                gb.placeToken('X', j);
            }
        }
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.checkTie());
    }
    /**
     * TEST ISPLAYERATPOS
     */
    @Test
    public void testIsPlayerAtPos_empty() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }
    @Test
    public void testIsPlayerAtPos_X() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testIsPlayerAtPos_O() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'O';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.isPlayerAtPos(pos, 'O'));
    }

    @Test
    public void testIsPlayerAtPos_wrong_player() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertFalse(gb.isPlayerAtPos(pos, 'O'));
    }

    @Test
    public void testIsPlayerAtPos_X_surrounded() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = 'O';
            }
        }
        boardArray[1][1] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        BoardPosition pos = new BoardPosition(1, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }
    /**
     * TEST WHATSATPOS
     */
    @Test
    public void testWhatsAtPos_empty() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertEquals(gb.whatsAtPos(pos), ' ');
    }

    @Test
    public void testWhatsAtPos_X() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void testIsPlayerAtPos_different_player() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }
        boardArray[0][0] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertNotEquals(gb.whatsAtPos(pos), ' ');
    }

    @Test
    public void testIsPlayerAtPos_many_players() {
        char[][] boardArray = new char[3][3];
        char token = 'A';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = token;
                ++token;
            }
        }

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        token = 'A';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gb.placeToken(token, j);
                ++token;
            }
        }

        BoardPosition pos = new BoardPosition(1, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertEquals(gb.whatsAtPos(pos), 'E');
    }

    @Test
    public void testWhatsAtPos_X_surrounded() {
        char[][] boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = 'O';
            }
        }
        boardArray[1][1] = 'X';

        IGameBoard gb = makeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        BoardPosition pos = new BoardPosition(1, 1);

        assertEquals(gb.toString(), makeAString(boardArray, 3, 3));
        assertEquals(gb.whatsAtPos(pos), 'X');
    }
}



