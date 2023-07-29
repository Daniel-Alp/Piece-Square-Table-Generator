import java.util.Arrays;

public class Main {
    static long[] midgamePawn = {
            0,   0,   0,   0,   0,   0,  0,   0,
            98, 134,  61,  95,  68, 126, 34, -11,
            -6,   7,  26,  31,  65,  56, 25, -20,
            -14,  13,   6,  21,  23,  12, 17, -23,
            -27,  -2,  -5,  12,  17,   6, 10, -25,
            -26,  -4,  -4, -10,   3,   3, 33, -12,
            -35,  -1, -20, -23, -15,  24, 38, -22,
            0,   0,   0,   0,   0,   0,  0,   0,
    };
    static  long[] midgameKnight = {
            -167, -89, -34, -49,  61, -97, -15, -107,
            -73, -41,  72,  36,  23,  62,   7,  -17,
            -47,  60,  37,  65,  84, 129,  73,   44,
            -9,  17,  19,  53,  37,  69,  18,   22,
            -13,   4,  16,  13,  28,  19,  21,   -8,
            -23,  -9,  12,  10,  19,  17,  25,  -16,
            -29, -53, -12,  -3,  -1,  18, -14,  -19,
            -105, -21, -58, -33, -17, -28, -19,  -23,
    };
    static long[] midgameBishop = {
            -29,   4, -82, -37, -25, -42,   7,  -8,
            -26,  16, -18, -13,  30,  59,  18, -47,
            -16,  37,  43,  40,  35,  50,  37,  -2,
            -4,   5,  19,  50,  37,  37,   7,  -2,
            -6,  13,  13,  26,  34,  12,  10,   4,
            0,  15,  15,  15,  14,  27,  18,  10,
            4,  15,  16,   0,   7,  21,  33,   1,
            -33,  -3, -14, -21, -13, -12, -39, -21,
    };
    static long[] midgameRook = {
            32,  42,  32,  51, 63,  9,  31,  43,
            27,  32,  58,  62, 80, 67,  26,  44,
            -5,  19,  26,  36, 17, 45,  61,  16,
            -24, -11,   7,  26, 24, 35,  -8, -20,
            -36, -26, -12,  -1,  9, -7,   6, -23,
            -45, -25, -16, -17,  3,  0,  -5, -33,
            -44, -16, -20,  -9, -1, 11,  -6, -71,
            -19, -13,   1,  17, 16,  7, -37, -26,
    };
    static long[] midgameQueen = {
            -28,   0,  29,  12,  59,  44,  43,  45,
            -24, -39,  -5,   1, -16,  57,  28,  54,
            -13, -17,   7,   8,  29,  56,  47,  57,
            -27, -27, -16, -16,  -1,  17,  -2,   1,
            -9, -26,  -9, -10,  -2,  -4,   3,  -3,
            -14,   2, -11,  -2,  -5,   2,  14,   5,
            -35,  -8,  11,   2,   8,  15,  -3,   1,
            -1, -18,  -9,  10, -15, -25, -31, -50,
    };
    static long[] midgameKing = {
            -65,  23,  16, -15, -56, -34,   2,  13,
            29,  -1, -20,  -7,  -8,  -4, -38, -29,
            -9,  24,   2, -16, -20,   6,  22, -22,
            -17, -20, -12, -27, -30, -25, -14, -36,
            -49,  -1, -27, -39, -46, -44, -33, -51,
            -14, -14, -22, -46, -44, -30, -15, -27,
            1,   7,  -8, -64, -43, -16,   9,   8,
            -15,  36,  12, -54,   8, -28,  24,  14,
    };
    static long[] compressedTable = new long[64];
    static long cell = 0;

    public static void main(String[] args) {
        //All scores from perspective of white player
        //A1 is index 0 for reference
        for (int i = 0; i < 64; i++) {
            encodeCell(i);
        }
        System.out.println(Arrays.toString(compressedTable));
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                int i = r * 8 + c;
                System.out.println("Row: " + r + " Col: " + c);
                decodeCell(compressedTable[i]);
                System.out.println();
            }
        }
    }

    public static void encodeCell(int i) {
        cell = 0;
        encodeCellPieceWeight(midgameKing[i]);
        encodeCellPieceWeight(midgameQueen[i]);
        encodeCellPieceWeight(midgameRook[i]);
        encodeCellPieceWeight(midgameBishop[i]);
        encodeCellPieceWeight(midgameKnight[i]);
        encodeCellPieceWeight(midgamePawn[i]);
        compressedTable[i] = cell;
    }

    public static void encodeCellPieceWeight(long val) {
        val += 167;
        cell = cell << 10;
        cell += Math.abs(val);
    }

    //Piece
    //Pawn:   0
    //Knight: 1
    //Bishop: 2
    //Rook:   3
    //Queen:  4
    //King    5
    public static void decodeCell(long cell) {
        for (int j = 0; j < 6; j++) {
            long val = (cell >> (10 * j)) % 512 - 167;
            System.out.println("Piece type: " + j + " cell weight: " + val);
        }
    }
}