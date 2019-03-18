package com.apptinus.sagan.eval;

import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Evaluation;
import com.apptinus.sagan.util.BoardUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class EvalTest {

  @Test
  public void test() {
    for (String epd : getPos()) {
      evalPos(epd);
    }
  }

  private static void evalPos(String epd) {
    Pattern p =
        Pattern.compile("^([^ ]+ [^ ]+ [^ ]+ [^ ]+).*cem ([^;]+); cee ([^;]+); ce ([^;]+);.*$");
    Matcher m = p.matcher(epd);

    if (!m.matches()) {
      throw new IllegalArgumentException("Couldn't parse line in epd: " + epd);
    }

    Board board = BoardUtil.createBoard(m.group(1));
    int evaluate = Evaluation.evaluate(board);

    System.out.println(String.format("eval %s real %s fen %s", evaluate, (int)(Double.parseDouble(m.group(4))*100), m.group(1)));
  }

  private List<String> getPos() {
    List<String> pos = new ArrayList<>();
    pos.add(
        "3r1k2/4npp1/1ppr3p/p6P/P2PPPP1/1NR5/5K2/2R5 w - - bm d5; id \"BK.02\"; cem -0.15; cee 1.06; ce 0.86;");
    pos.add(
        "1k1r4/pp1b1R2/3q2pp/4p3/2B5/4Q3/PPP2B2/2K5 b - - bm Qd1+; id \"BK.01\"; cem 7.97; cee 3.67; ce 5.85;");
    pos.add(
        "2q1rr1k/3bbnnp/p2p1pp1/2pPp3/PpP1P1P1/1P2BNNP/2BQ1PRK/7R b - - bm f5; id \"BK.03\"; cem 0.68; cee 0.27; ce 0.59;");
    pos.add(
        "rnbqkb1r/p3pppp/1p6/2ppP3/3N4/2P5/PPP1QPPP/R1B1KB1R w KQkq - bm e6; id \"BK.04\"; cem 3.02; cee 1.49; ce 3.10;");
    pos.add(
        "r1b2rk1/2q1b1pp/p2ppn2/1p6/3QP3/1BN1B3/PPP3PP/R4RK1 w - - bm Nd5 a4; id \"BK.05\"; cem 0.30; cee 0.33; ce 0.40;");
    pos.add(
        "2r3k1/pppR1pp1/4p3/4P1P1/5P2/1P4K1/P1P5/8 w - - bm g6; id \"BK.06\"; cem 0.15; cee 1.71; ce 1.81;");
    pos.add(
        "1nk1r1r1/pp2n1pp/4p3/q2pPp1N/b1pP1P2/B1P2R2/2P1B1PP/R2Q2K1 w - - bm Nf6; id \"BK.07\"; cem 0.19; cee -1.70; ce 0.24;");
    pos.add(
        "4b3/p3kp2/6p1/3pP2p/2pP1P2/4K1P1/P3N2P/8 w - - bm f5; id \"BK.08\"; cem -0.29; cee 0.51; ce 0.61;");
    pos.add(
        "2kr1bnr/pbpq4/2n1pp2/3p3p/3P1P1B/2N2N1Q/PPP3PP/2KR1B1R w - - bm f5; id \"BK.09\"; cem 1.96; cee 1.00; ce 2.06;");
    pos.add(
        "3rr1k1/pp3pp1/1qn2np1/8/3p4/PP1R1P2/2P1NQPP/R1B3K1 b - - bm Ne5; id \"BK.10\"; cem -0.96; cee -1.03; ce -1.06;");
    pos.add(
        "2r1nrk1/p2q1ppp/bp1p4/n1pPp3/P1P1P3/2PBB1N1/4QPPP/R4RK1 w - - bm f4; id \"BK.11\"; cem 0.81; cee 0.33; ce 0.89;");
    pos.add(
        "r3r1k1/ppqb1ppp/8/4p1NQ/8/2P5/PP3PPP/R3R1K1 b - - bm Bf5; id \"BK.12\"; cem 2.20; cee 0.00; ce 1.43;");
    pos.add(
        "r2q1rk1/4bppp/p2p4/2pP4/3pP3/3Q4/PP1B1PPP/R3R1K1 w - - bm b4; id \"BK.13\"; cem 0.03; cee 0.73; ce 0.34;");
    pos.add(
        "rnb2r1k/pp2p2p/2pp2p1/q2P1p2/8/1Pb2NP1/PB2PPBP/R2Q1RK1 w - - bm Qd2 Qe1; id \"BK.14\"; cem 0.54; cee -0.13; ce 0.62;");
    pos.add(
        "2r3k1/1p2q1pp/2b1pr2/p1pp4/6Q1/1P1PP1R1/P1PN2PP/5RK1 w - - bm Qxg7+; id \"BK.15\"; cem 0.49; cee 0.06; ce 0.45;");
    pos.add(
        "r1bqkb1r/4npp1/p1p4p/1p1pP1B1/8/1B6/PPPN1PPP/R2Q1RK1 w kq - bm Ne4; id \"BK.16\"; cem -0.01; cee -0.78; ce 0.07;");
    pos.add(
        "r2q1rk1/1ppnbppp/p2p1nb1/3Pp3/2P1P1P1/2N2N1P/PPB1QP2/R1B2RK1 b - - bm h5; id \"BK.17\"; cem 0.47; cee -0.16; ce 0.38;");
    pos.add(
        "r1bq1rk1/pp2ppbp/2np2p1/2n5/P3PP2/N1P2N2/1PB3PP/R1B1QRK1 b - - bm Nb3; id \"BK.18\"; cem 0.24; cee 0.50; ce 0.14;");
    pos.add(
        "3rr3/2pq2pk/p2p1pnp/8/2QBPP2/1P6/P5PP/4RRK1 b - - bm Rxe4; id \"BK.19\"; cem 1.02; cee 0.48; ce 0.76;");
    pos.add(
        "r4k2/pb2bp1r/1p1qp2p/3pNp2/3P1P2/2N3P1/PPP1Q2P/2KRR3 w - - bm g4; id \"BK.20\"; cem 1.49; cee 1.87; ce 1.64;");
    pos.add(
        "3rn2k/ppb2rpp/2ppqp2/5N2/2P1P3/1P5Q/PB3PPP/3RR1K1 w - - bm Nh6; id \"BK.21\"; cem 3.98; cee 2.16; ce 3.77;");
    pos.add(
        "2r2rk1/1bqnbpp1/1p1ppn1p/pP6/N1P1P3/P2B1N1P/1B2QPP1/R2R2K1 b - - bm Bxe4; id \"BK.22\"; cem 0.22; cee -0.05; ce 0.12;");
    pos.add(
        "r1bqk2r/pp2bppp/2p5/3pP3/P2Q1P2/2N1B3/1PP3PP/R4RK1 b kq - bm f6; id \"BK.23\"; cem 1.41; cee 1.65; ce 1.35;");
    pos.add(
        "r2qnrnk/p2b2b1/1p1p2pp/2pPpp2/1PP1P3/PRNBB3/3QNPPP/5RK1 w - - bm f4; id \"BK.24\"; cem 1.64; cee 1.31; ce 1.74;");
    pos.add(
        "rn1qkb1r/pp2pppp/5n2/3p1b2/3P4/2N1P3/PP3PPP/R1BQKBNR w KQkq - id \"CCR01\"; bm Qb3; cem 0.14; cee 0.14; ce 0.24;");
    pos.add(
        "rn1qkb1r/pp2pppp/5n2/3p1b2/3P4/1QN1P3/PP3PPP/R1B1KBNR b KQkq - id \"CCR02\";bm Bc8; cem 0.52; cee 0.45; ce 0.43;");
    pos.add(
        "r1bqk2r/ppp2ppp/2n5/4P3/2Bp2n1/5N1P/PP1N1PP1/R2Q1RK1 b kq - id \"CCR03\"; bm Nh6; am Ne5; cem 1.31; cee -0.25; ce 1.17;");
    pos.add(
        "r1bqrnk1/pp2bp1p/2p2np1/3p2B1/3P4/2NBPN2/PPQ2PPP/1R3RK1 w - - id \"CCR04\"; bm b4; cem 1.53; cee 1.08; ce 1.63;");
    pos.add(
        "rnbqkb1r/ppp1pppp/5n2/8/3PP3/2N5/PP3PPP/R1BQKBNR b KQkq - id \"CCR05\"; bm e5; cem 1.60; cee 1.12; ce 1.50;");
    pos.add(
        "rnbq1rk1/pppp1ppp/4pn2/8/1bPP4/P1N5/1PQ1PPPP/R1B1KBNR b KQ - id \"CCR06\"; bm Bcx3+; cem 0.38; cee 1.15; ce 0.29;");
    pos.add(
        "r4rk1/3nppbp/bq1p1np1/2pP4/8/2N2NPP/PP2PPB1/R1BQR1K1 b - - id \"CCR07\"; bm Rfb8; cem 0.84; cee 0.33; ce 0.75;");
    pos.add(
        "rn1qkb1r/pb1p1ppp/1p2pn2/2p5/2PP4/5NP1/PP2PPBP/RNBQK2R w KQkq c6 id \"CCR08\"; bm d5; cem -0.26; cee -0.05; ce -0.16;");
    pos.add(
        "r1bq1rk1/1pp2pbp/p1np1np1/3Pp3/2P1P3/2N1BP2/PP4PP/R1NQKB1R b KQ - id \"CCR09\"; bm Nd4; cem 0.59; cee 1.46; ce 0.49;");
    pos.add(
        "rnbqr1k1/1p3pbp/p2p1np1/2pP4/4P3/2N5/PP1NBPPP/R1BQ1RK1 w - - id \"CCR10\"; bm a4; cem 0.57; cee 0.67; ce 0.66;");
    pos.add(
        "rnbqkb1r/pppp1ppp/5n2/4p3/4PP2/2N5/PPPP2PP/R1BQKBNR b KQkq f3 id \"CCR11\"; bm d5; cem 0.45; cee 0.21; ce 0.35;");
    pos.add(
        "r1bqk1nr/pppnbppp/3p4/8/2BNP3/8/PPP2PPP/RNBQK2R w KQkq - id \"CCR12\"; bm Bxf7+; cem 1.18; cee 0.62; ce 1.27;");
    pos.add(
        "rnbq1b1r/ppp2kpp/3p1n2/8/3PP3/8/PPP2PPP/RNBQKB1R b KQ d3 id \"CCR13\"; am Ne4; cem -1.24; cee -2.65; ce -1.33;");
    pos.add(
        "rnbqkb1r/pppp1ppp/3n4/8/2BQ4/5N2/PPP2PPP/RNB2RK1 b kq - id \"CCR14\"; am Nxc4; cem 5.22; cee -0.24; ce 5.12;");
    pos.add(
        "r2q1rk1/2p1bppp/p2p1n2/1p2P3/4P1b1/1nP1BN2/PP3PPP/RN1QR1K1 w - - id \"CCR15\"; bm exf6; cem -3.16; cee -4.43; ce -3.06;");
    pos.add(
        "r1bqkb1r/2pp1ppp/p1n5/1p2p3/3Pn3/1B3N2/PPP2PPP/RNBQ1RK1 b kq - id \"CCR16\"; bm d5; cem 0.08; cee -1.62; ce -0.02;");
    pos.add(
        "r2qkbnr/2p2pp1/p1pp4/4p2p/4P1b1/5N1P/PPPP1PP1/RNBQ1RK1 w kq - id \"CCR17\"; am hxg4; cem 0.09; cee -0.88; ce 0.16;");
    pos.add(
        "r1bqkb1r/pp3ppp/2np1n2/4p1B1/3NP3/2N5/PPP2PPP/R2QKB1R w KQkq e6 id \"CCR18\"; bm Bxf6+; cem -0.11; cee 0.10; ce -0.01;");
    pos.add(
        "rn1qk2r/1b2bppp/p2ppn2/1p6/3NP3/1BN5/PPP2PPP/R1BQR1K1 w kq - id \"CCR19\"; am Bxe6; cem 0.75; cee -0.64; ce 0.85;");
    pos.add(
        "r1b1kb1r/1pqpnppp/p1n1p3/8/3NP3/2N1B3/PPP1BPPP/R2QK2R w KQkq - id \"CCR20\"; am Ndb5; cem 0.47; cee 0.37; ce 0.57;");
    pos.add(
        "r1bqnr2/pp1ppkbp/4N1p1/n3P3/8/2N1B3/PPP2PPP/R2QK2R b KQ - id \"CCR21\"; am Kxe6; cem 3.54; cee -1.51; ce 3.44;");
    pos.add(
        "r3kb1r/pp1n1ppp/1q2p3/n2p4/3P1Bb1/2PB1N2/PPQ2PPP/RN2K2R w KQkq - id \"CCR22\"; bm a4; cem 0.25; cee 0.37; ce 0.35;");
    pos.add(
        "r1bq1rk1/pppnnppp/4p3/3pP3/1b1P4/2NB3N/PPP2PPP/R1BQK2R w KQ - id \"CCR23\"; bm Bxh7+; cem -0.34; cee 0.37; ce -0.24;");
    pos.add(
        "r2qkbnr/ppp1pp1p/3p2p1/3Pn3/4P1b1/2N2N2/PPP2PPP/R1BQKB1R w KQkq - id \"CCR24\"; bm Nxe5; cem 1.67; cee 0.47; ce 1.76;");
    pos.add(
        "rn2kb1r/pp2pppp/1qP2n2/8/6b1/1Q6/PP1PPPBP/RNB1K1NR b KQkq - id \"CCR25\"; am Qxb3; cem 0.84; cee 1.26; ce 0.74;");
    pos.add(
        "1rbq1rk1/p1b1nppp/1p2p3/8/1B1pN3/P2B4/1P3PPP/2RQ1R1K w - - bm Nf6+; id \"position 01\"; cem -1.55; cee -1.18; ce -1.44;");
    pos.add(
        "3r2k1/p2r1p1p/1p2p1p1/q4n2/3P4/PQ5P/1P1RNPP1/3R2K1 b - - bm Nxd4 id \"position 02\"; cem -0.31; cee -0.21; ce -0.37;");
    pos.add(
        "3r2k1/1p3ppp/2pq4/p1n5/P6P/1P6/1PB2QP1/1K2R3 w - - am Rd1; id \"position 03\"; cem -0.89; cee -1.77; ce -1.26;");
    pos.add(
        "r1b1r1k1/1ppn1p1p/3pnqp1/8/p1P1P3/5P2/PbNQNBPP/1R2RB1K w - - bm Rxb2; id \"position 04\"; cem -0.80; cee -1.03; ce -0.71;");
    pos.add(
        "2r4k/pB4bp/1p4p1/6q1/1P1n4/2N5/P4PPP/2R1Q1K1 b - - bm Qxc1; id \"position 05\"; cem 0.99; cee 1.02; ce 0.81;");
    pos.add(
        "r5k1/3n1ppp/1p6/3p1p2/3P1B2/r3P2P/PR3PP1/2R3K1 b - - am Rxa2; id \"position 06\"; cem 1.07; cee -0.10; ce 0.10;");
    pos.add(
        "2r2rk1/1bqnbpp1/1p1ppn1p/pP6/N1P1P3/P2B1N1P/1B2QPP1/R2R2K1 b - - bm Bxe4 id \"position 07\"; cem 0.22; cee -0.05; ce 0.12;");
    pos.add(
        "5r1k/6pp/1n2Q3/4p3/8/7P/PP4PK/R1B1q3 b - - bm h6; id \"position 08\"; cem -1.15; cee 0.43; ce -0.40;");
    pos.add(
        "r3k2r/pbn2ppp/8/1P1pP3/P1qP4/5B2/3Q1PPP/R3K2R w KQkq - bm Be2; id \"position 09\"; cem -0.62; cee -0.94; ce -0.59;");
    pos.add(
        "3r2k1/ppq2pp1/4p2p/3n3P/3N2P1/2P5/PP2QP2/K2R4 b - - bm Nxc3; id \"position 10\"; cem -0.73; cee 0.35; ce -0.25;");
    pos.add(
        "q3rn1k/2QR4/pp2pp2/8/P1P5/1P4N1/6n1/6K1 w - - bm Nf5; id \"position 11\"; cem -3.35; cee -4.00; ce -3.55;");
    pos.add(
        "6k1/p3q2p/1nr3pB/8/3Q1P2/6P1/PP5P/3R2K1 b - - bm Rd6; id \"position 12\"; cem 4.98; cee 2.58; ce 3.61;");
    pos.add(
        "1r4k1/7p/5np1/3p3n/8/2NB4/7P/3N1RK1 w - - bm Nxd5; id \"position 13\"; cem 1.77; cee 1.91; ce 1.58;");
    pos.add(
        "1r2r1k1/p4p1p/6pB/q7/8/3Q2P1/PbP2PKP/1R3R2 w - - bm Rxb2; id \"position 14\"; cem 0.25; cee 0.74; ce 0.49;");
    pos.add(
        "r2q1r1k/pb3p1p/2n1p2Q/5p2/8/3B2N1/PP3PPP/R3R1K1 w - - bm Bxf5; id \"position 15\"; cem 1.31; cee 0.65; ce 1.29;");
    pos.add(
        "8/4p3/p2p4/2pP4/2P1P3/1P4k1/1P1K4/8 w - - bm b4; id \"position 16\"; cem 1.45; cee 1.30; ce 1.39;");
    pos.add(
        "1r1q1rk1/p1p2pbp/2pp1np1/6B1/4P3/2NQ4/PPP2PPP/3R1RK1 w - - bm e5; id \"position 17\"; cem 0.69; cee 0.58; ce 0.76;");
    pos.add(
        "q4rk1/1n1Qbppp/2p5/1p2p3/1P2P3/2P4P/6P1/2B1NRK1 b - - bm Qc8; id \"position 18\"; cem -0.77; cee -0.43; ce -0.73;");
    pos.add(
        "r2q1r1k/1b1nN2p/pp3pp1/8/Q7/PP5P/1BP2RPN/7K w - - bm Qxd7; id \"position 19\"; cem -1.74; cee -2.55; ce -1.70;");
    pos.add(
        "8/5p2/pk2p3/4P2p/2b1pP1P/P3P2B/8/7K w - - bm Bg4; id \"position 20\"; cem 0.23; cee -0.78; ce -0.69;");
    pos.add(
        "8/2k5/4p3/1nb2p2/2K5/8/6B1/8 w - - bm Kxb5; id \"position 21\"; cem -5.75; cee -5.56; ce -3.72;");
    pos.add(
        "1B1b4/7K/1p6/1k6/8/8/8/8 w - - bm Ba7; id \"position 22\"; cem -1.11; cee -1.86; ce -1.26;");
    pos.add(
        "rn1q1rk1/1b2bppp/1pn1p3/p2pP3/3P4/P2BBN1P/1P1N1PP1/R2Q1RK1 b - - bm Ba6; id \"position 23\"; cem 1.85; cee 1.15; ce 1.75;");
    pos.add(
        "8/p1ppk1p1/2n2p2/8/4B3/2P1KPP1/1P5P/8 w - - bm Bxc6; id \"position 24\"; cem 0.77; cee 0.97; ce 1.07;");
    pos.add(
        "8/3nk3/3pp3/1B6/8/3PPP2/4K3/8 w - - bm Bxd7; id \"position 25\"; cem 1.76; cee 1.00; ce 1.05;");
    pos.add(
        "r3kb1r/3n1pp1/p6p/2pPp2q/Pp2N3/3B2PP/1PQ2P2/R3K2R w KQkq - bm d6; id \"LCTII.POS.01\"; c0 \"Chernin - Miles, Tunis 1985\"; cem -0.19; cee 0.10; ce -0.04;");
    pos.add(
        "1k1r3r/pp2qpp1/3b1n1p/3pNQ2/2pP1P2/2N1P3/PP4PP/1K1RR3 b - - bm Bb4; id \"LCTII.POS.02\"; c0 \"Lilienthal - Botvinnik, Moskau 1945\"; cem -0.91; cee 0.00; ce -0.85;");
    pos.add(
        "r6k/pp4p1/2p1b3/3pP3/7q/P2B3r/1PP2Q1P/2K1R1R1 w - - bm Qc5; id \"LCTII.POS.03\"; c0 \"Boissel - Boulard, corr. 1994\"; cem 0.90; cee 0.43; ce 0.85;");
    pos.add(
        "1nr5/2rbkppp/p3p3/Np6/2PRPP2/8/PKP1B1PP/3R4 b - - bm e5; id \"LCTII.POS.04\"; c0 \"Kaplan - Kopec, USA 1975\"; cem 2.94; cee 1.83; ce 2.16;");
    pos.add(
        "2r2rk1/1p1bq3/p3p2p/3pPpp1/1P1Q4/P7/2P2PPP/2R1RBK1 b - - bm Bb5; id \"LCTII.POS.05\"; c0 \"Estrin - Pytel, Albena 1973\"; cem -0.28; cee -0.25; ce -0.36;");
    pos.add(
        "3r1bk1/p4ppp/Qp2p3/8/1P1B4/Pq2P1P1/2r2P1P/R3R1K1 b - - bm e5; id \"LCTII.POS.06\"; c0 \"Nimzowitsch - Capablanca, New York 1927\"; cem -0.59; cee -0.37; ce -0.62;");
    pos.add(
        "r1b2r1k/pp2q1pp/2p2p2/2p1n2N/4P3/1PNP2QP/1PP2RP1/5RK1 w - - bm Nd1; id \"LCTII.POS.07\"; c0 \"Tartakower - Rubinstein, Moskau 1925\"; cem 1.31; cee 1.47; ce 1.43;");
    pos.add(
        "r2qrnk1/pp3ppb/3b1n1p/1Pp1p3/2P1P2N/P5P1/1B1NQPBP/R4RK1 w - - bm Bh3; id \"LCTII.POS.08\"; c0 \"Polugaevsky - Unzicker, Kislovodsk 1972\"; cem -0.06; cee 0.00; ce 0.04;");
    pos.add(
        "5nk1/Q4bpp/5p2/8/P1n1PN2/q4P2/6PP/1R4K1 w - - bm Qd4; id \"LCTII.POS.09\"; c0 \"Boissel - Del Gobbo, corr. 1994\"; cem -0.06; cee 0.71; ce 0.43;");
    pos.add(
        "r3k2r/3bbp1p/p1nppp2/5P2/1p1NP3/5NP1/PPPK3P/3R1B1R b kq - bm Bf8; id \"LCTII.POS.10\"; c0 \"Cucka - Jansa, Brno 1960\"; cem 0.00; cee 0.72; ce 0.24;");
    pos.add(
        "bn6/1q4n1/1p1p1kp1/2pPp1pp/1PP1P1P1/3N1P1P/4B1K1/2Q2N2 w - - bm h4; id \"LCTII.POS.11\"; c0 \"Landau - Schmidt, Noordwijk 1938\"; cem 2.96; cee 1.38; ce 2.29;");
    pos.add(
        "3r2k1/pp2npp1/2rqp2p/8/3PQ3/1BR3P1/PP3P1P/3R2K1 b - - bm Rb6; id \"LCTII.POS.12\"; c0 \"Korchnoi - Karpov, Meran 1981\"; cem -0.11; cee 0.00; ce -0.17;");
    pos.add(
        "1r2r1k1/4ppbp/B5p1/3P4/pp1qPB2/2n2Q1P/P4PP1/4RRK1 b - - bm Nxa2; id \"LCTII.POS.13\"; c0 \"Barbero - Kouatly, Budapest 1987\"; cem 0.15; cee -0.97; ce -0.12;");
    pos.add(
        "r2qkb1r/1b3ppp/p3pn2/1p6/1n1P4/1BN2N2/PP2QPPP/R1BR2K1 w kq - bm d5; id \"LCTII.POS.14\"; c0 \"Spasski - Aftonomov, Leningrad 1949\"; cem 0.82; cee -0.29; ce 0.92;");
    pos.add(
        "1r4k1/1q2bp2/3p2p1/2pP4/p1N4R/2P2QP1/1P3PK1/8 w - - bm Nxd6; id \"LCTII.CMB.01\"; c0 \"Romanishin - Gdansky, Polonica Zdroj 1992\"; cem 1.77; cee 0.03; ce 0.94;");
    pos.add(
        "rn3rk1/pbppq1pp/1p2pb2/4N2Q/3PN3/3B4/PPP2PPP/R3K2R w KQ - bm Qxh7+; id \"LCTII.CMB.02\"; c0 \"Lasker,Ed - Thomas, London 1911\"; cem -0.23; cee 0.74; ce -0.11;");
    pos.add(
        "4r1k1/3b1p2/5qp1/1BPpn2p/7n/r3P1N1/2Q1RPPP/1R3NK1 b - - bm Qf3; id \"LCTII.CMB.03\"; c0 \"Andruet - Spassky, BL 1988\"; cem -0.75; cee 0.43; ce -0.81;");
    pos.add(
        "2k2b1r/1pq3p1/2p1pp2/p1n1PnNp/2P2B2/2N4P/PP2QPP1/3R2K1 w - - bm exf6; id \"LCTII.CMB.04\"; c0 \"Vanka - Jansa, Prag 1957\"; cem 1.12; cee 0.42; ce 1.04;");
    pos.add(
        "2r2r2/3qbpkp/p3n1p1/2ppP3/6Q1/1P1B3R/PBP3PP/5R1K w - - bm Rxh7+; id \"LCTII.CMB.05\"; c0 \"Boros - Szabo, Budapest 1937\"; cem 5.36; cee -0.23; ce 4.58;");
    pos.add(
        "2r1k2r/2pn1pp1/1p3n1p/p3PP2/4q2B/P1P5/2Q1N1PP/R4RK1 w q - bm exf6; id \"LCTII.CMB.06\"; c0 \"Lilienthal - Capablanca, Hastings 1934\"; cem 0.08; cee -1.00; ce -0.00;");
    pos.add(
        "2rr2k1/1b3ppp/pb2p3/1p2P3/1P2BPnq/P1N3P1/1B2Q2P/R4R1K b - - bm Rxc3; id \"LCTII.CMB.07\"; c0 \"Rotlewi - Rubinstein, Lodz 1907\"; cem -2.51; cee -0.48; ce -2.58;");
    pos.add(
        "2b1r1k1/r4ppp/p7/2pNP3/4Q3/q6P/2P2PP1/3RR1K1 w - - bm Nf6+; id \"LCTII.CMB.08\"; c0 \"Zarkov - Mephisto, Albuquerque 1991\"; cem 2.37; cee 1.65; ce 2.25;");
    pos.add(
        "6k1/5p2/3P2p1/7n/3QPP2/7q/r2N3P/6RK b - - bm Rxd2; id \"LCTII.CMB.09\"; c0 \"Portisch - Kasparov, Moskau 1981\"; cem 1.76; cee 2.64; ce 2.13;");
    pos.add(
        "rq2rbk1/6p1/p2p2Pp/1p1Rn3/4PB2/6Q1/PPP1B3/2K3R1 w - - bm Bxh6; id \"LCTII.CMB.10\"; c0 \"Tchoudinovskikh - Merchiev, UdSSR 1987\"; cem 2.07; cee 1.72; ce 2.11;");
    pos.add(
        "rnbq2k1/p1r2p1p/1p1p1Pp1/1BpPn1N1/P7/2P5/6PP/R1B1QRK1 w - - bm Nxh7; id \"LCTII.CMB.11\"; c0 \"Vaisser - Genius 2, Aubervilliers, 1994\"; cem 1.57; cee 0.07; ce 1.62;");
    pos.add(
        "r2qrb1k/1p1b2p1/p2ppn1p/8/3NP3/1BN5/PPP3QP/1K3RR1 w - - bm e5; id \"LCTII.CMB.12\"; c0 \"Spassky - Petrosian, Moskau 1969\"; cem 0.57; cee -0.37; ce 0.64;");
    pos.add(
        "8/1p3pp1/7p/5P1P/2k3P1/8/2K2P2/8 w - - bm f6; id \"LCTII.FIN.01\"; c0 \"NN - Lasker,Ed\"; cem 0.64; cee -0.20; ce -0.10;");
    pos.add(
        "8/pp2r1k1/2p1p3/3pP2p/1P1P1P1P/P5KR/8/8 w - - bm f5; id \"LCTII.FIN.02\"; c0 \"Capablanca - Eliskases, Moskau 1936\"; cem -1.05; cee -0.11; ce -0.01;");
    pos.add(
        "8/3p4/p1bk3p/Pp6/1Kp1PpPp/2P2P1P/2P5/5B2 b - - bm Bxe4; id \"LCTII.FIN.03\"; c0 \"Studie 1994\"; cem -0.11; cee -1.35; ce -1.44;");
    pos.add(
        "5k2/7R/4P2p/5K2/p1r2P1p/8/8/8 b - - bm h3; am h5; id \"LCTII.FIN.04\"; c0 \"Karpov - Deep Thought, Analyse 1990\"; cem -0.70; cee -0.47; ce -0.54;");
    pos.add(
        "6k1/6p1/7p/P1N5/1r3p2/7P/1b3PP1/3bR1K1 w - - bm a6; id \"LCTII.FIN.05\"; c0 \"Karpov - Kasparov, Moskau 1985 [Analyse]\"; cem -1.77; cee -1.67; ce -1.51;");
    pos.add(
        "8/3b4/5k2/2pPnp2/1pP4N/pP1B2P1/P3K3/8 b - - bm f4; id \"LCTII.FIN.06\"; c0 \"Minev - Portisch, Halle 1967\"; cem 0.46; cee 0.48; ce 0.38;");
    pos.add(
        "6k1/4pp1p/3p2p1/P1pPb3/R7/1r2P1PP/3B1P2/6K1 w - - bm Bb4; id \"LCTII.FIN.07\"; c0 \"Lengyel - Kaufman, Los Angeles 1974\"; cem 0.13; cee 0.47; ce 0.56;");
    pos.add(
        "2k5/p7/Pp1p1b2/1P1P1p2/2P2P1p/3K3P/5B2/8 w - - bm c5; id \"LCTII.FIN.08\"; c0 \"Spassky - Byrne, 1974\"; cem 0.38; cee 2.48; ce 2.57;");
    pos.add(
        "8/5Bp1/4P3/6pP/1b1k1P2/5K2/8/8 w - - bm Kg4; id \"LCTII.FIN.09\"; c0 \"Klimenok - Kabanov, UdSSR 1969\"; cem 1.63; cee 1.91; ce 1.05;");
    pos.add(
        "8/8/p1p5/1p5p/1P5p/8/PPP2K1p/4R1rk w - - 0 1 bm Rf1; id \"zugzwang.001\"; cem -1.64; cee -3.51; ce -3.41;");
    pos.add(
        "1q1k4/2Rr4/8/2Q3K1/8/8/8/8 w - - 0 1 bm Kh6;  id \"zugzwang.002\"; cem 4.02; cee 0.32; ce 1.43;");
    pos.add(
        "7k/5K2/5P1p/3p4/6P1/3p4/8/8 w - - 0 1 bm g5; id \"zugzwang.003\"; cem -1.98; cee -1.92; ce -1.74;");
    pos.add(
        "8/6B1/p5p1/Pp4kp/1P5r/5P1Q/4q1PK/8 w - - 0 32 bm Qxh4; id \"zugzwang.004\"; cem -0.40; cee -3.43; ce -2.48;");
    pos.add(
        "8/8/1p1r1k2/p1pPN1p1/P3KnP1/1P6/8/3R4 b - - 0 1 bm Nxd5; id \"zugzwang.005\"; cem -0.36; cee 0.08; ce -0.02;");
    pos.add(
        "1qr3k1/p2nbppp/bp2p3/3p4/3P4/1P2PNP1/P2Q1PBP/1N2R1K1 b - - bm Qc7; id \"sbd.001\"; cem -0.99; cee -0.37; ce -0.93;");
    pos.add(
        "1r2r1k1/3bnppp/p2q4/2RPp3/4P3/6P1/2Q1NPBP/2R3K1 w - - bm Rc7; id \"sbd.002\"; cem 1.24; cee 0.49; ce 1.21;");
    pos.add(
        "2b1k2r/2p2ppp/1qp4n/7B/1p2P3/5Q2/PPPr2PP/R2N1R1K b k - bm O-O; id \"sbd.003\"; cem -0.87; cee -1.28; ce -1.03;");
    pos.add(
        "2b5/1p4k1/p2R2P1/4Np2/1P3Pp1/1r6/5K2/8 w - - bm Rd8; id \"sbd.004\"; cem 0.34; cee -0.71; ce -0.59;");
    pos.add(
        "2brr1k1/ppq2ppp/2pb1n2/8/3NP3/2P2P2/P1Q2BPP/1R1R1BK1 w - - bm g3; id \"sbd.005\"; cem -0.06; cee 0.16; ce 0.04;");
    pos.add(
        "2kr2nr/1pp3pp/p1pb4/4p2b/4P1P1/5N1P/PPPN1P2/R1B1R1K1 b - - bm Bf7; id \"sbd.006\"; cem 0.73; cee 1.42; ce 0.95;");
    pos.add(
        "2r1k2r/1p1qbppp/p3pn2/3pBb2/3P4/1QN1P3/PP2BPPP/2R2RK1 b k - bm O-O; id \"sbd.007\"; cem 2.03; cee 0.93; ce 1.92;");
    pos.add(
        "2r1r1k1/pbpp1npp/1p1b3q/3P4/4RN1P/1P4P1/PB1Q1PB1/2R3K1 w - - bm Rce1; id \"sbd.008\"; cem 1.27; cee 0.80; ce 1.36;");
    pos.add(
        "2r2k2/r4p2/2b1p1p1/1p1p2Pp/3R1P1P/P1P5/1PB5/2K1R3 w - - bm Kd2; id \"sbd.009\"; cem -0.12; cee 0.28; ce 0.28;");
    pos.add(
        "2r3k1/5pp1/1p2p1np/p1q5/P1P4P/1P1Q1NP1/5PK1/R7 w - - bm Rd1; id \"sbd.010\"; cem -0.48; cee 0.58; ce 0.19;");
    pos.add(
        "2r3qk/p5p1/1n3p1p/4PQ2/8/3B4/5P1P/3R2K1 w - - bm e6; id \"sbd.011\"; cem 0.64; cee 0.15; ce 0.47;");
    pos.add(
        "3b4/3k1pp1/p1pP2q1/1p2B2p/1P2P1P1/P2Q3P/4K3/8 w - - bm Qf3; id \"sbd.012\"; cem 1.68; cee 0.17; ce 0.63;");
    pos.add(
        "3n1r1k/2p1p1bp/Rn4p1/6N1/3P3P/2N1B3/2r2PP1/5RK1 w - - bm Na4 Nce4; id \"sbd.013\"; cem 0.50; cee -0.06; ce 0.34;");
    pos.add(
        "3q1rk1/3rbppp/ppbppn2/1N6/2P1P3/BP6/P1B1QPPP/R3R1K1 w - - bm Nd4; id \"sbd.014\"; cem -0.92; cee -0.20; ce -0.81;");
    pos.add(
        "3r1rk1/p1q4p/1pP1ppp1/2n1b1B1/2P5/6P1/P1Q2PBP/1R3RK1 w - - bm Bh6; id \"sbd.015\"; cem -0.07; cee -0.04; ce 0.03;");
    pos.add(
        "3r2k1/2q2p1p/5bp1/p1pP4/PpQ5/1P3NP1/5PKP/3R4 b - - bm Qd6; id \"sbd.016\"; cem -0.42; cee 0.79; ce 0.12;");
    pos.add(
        "3r2k1/p1q1npp1/3r1n1p/2p1p3/4P2B/P1P2Q1P/B4PP1/1R2R1K1 w - - bm Bc4; id \"sbd.017\"; cem -0.05; cee -0.59; ce -0.04;");
    pos.add(
        "3r4/2k5/p3N3/4p3/1p1p4/4r3/PPP3P1/1K1R4 b - - bm Kd7; id \"sbd.018\"; cem 0.16; cee -2.59; ce -2.51;");
    pos.add(
        "3r4/2R1np1p/1p1rpk2/p2b1p2/8/PP2P3/4BPPP/2R1NK2 w - - bm b4; id \"sbd.019\"; cem 0.86; cee 0.25; ce 0.58;");
    pos.add(
        "3rk2r/1b2bppp/p1qppn2/1p6/4P3/PBN2PQ1/1PP3PP/R1B1R1K1 b k - bm O-O; id \"sbd.020\"; cem 0.77; cee -0.26; ce 0.65;");
    pos.add(
        "3rk2r/1bq2pp1/2pb1n1p/p3pP2/P1B1P3/8/1P2QBPP/2RN1R1K b k - bm Be7 O-O; id \"sbd.021\"; cem 0.63; cee -0.24; ce 0.52;");
    pos.add(
        "3rkb1r/pppb1pp1/4n2p/2p5/3NN3/1P5P/PBP2PP1/3RR1K1 w - - bm Nf5; id \"sbd.022\"; cem 0.32; cee -1.57; ce -0.47;");
    pos.add(
        "3rr1k1/1pq2ppp/p1n5/3p4/6b1/2P2N2/PP1QBPPP/3R1RK1 w - - bm Rfe1; id \"sbd.023\"; cem -0.88; cee -0.21; ce -0.67;");
    pos.add(
        "4r1k1/1q1n1ppp/3pp3/rp6/p2PP3/N5P1/PPQ2P1P/3RR1K1 w - - bm Rc1; id \"sbd.024\"; cem -0.48; cee -0.16; ce -0.28;");
    pos.add(
        "4rb1k/1bqn1pp1/p3rn1p/1p2pN2/1PP1p1N1/P1P2Q1P/1BB2PP1/3RR1K1 w - - bm Qe2; id \"sbd.025\"; cem -1.18; cee -0.35; ce -1.09;");
    pos.add(
        "4rr2/2p5/1p1p1kp1/p6p/P1P4P/6P1/1P3PK1/3R1R2 w - - bm Rfe1; id \"sbd.026\"; cem 0.68; cee -0.51; ce -0.28;");
    pos.add(
        "5r2/pp1b1kpp/8/2pPp3/2P1p2P/4P1r1/PPRKB1P1/6R1 b - - bm Ke7; id \"sbd.027\"; cem -0.67; cee 0.25; ce -0.08;");
    pos.add(
        "6k1/1R5p/r2p2p1/2pN2B1/2bb4/P7/1P1K2PP/8 w - - bm Nf6+; id \"sbd.028\"; cem 3.30; cee 0.99; ce 1.46;");
    pos.add(
        "6k1/pp1q1pp1/2nBp1bp/P2pP3/3P4/8/1P2BPPP/2Q3K1 w - - bm Qc5; id \"sbd.029\"; cem -0.44; cee -0.32; ce -0.27;");
    pos.add(
        "6k1/pp2rp1p/2p2bp1/1n1n4/1PN3P1/P2rP2P/R3NPK1/2B2R2 w - - bm Rd2; id \"sbd.030\"; cem -1.70; cee -1.19; ce -1.36;");
    pos.add(
        "8/2p2kpp/p6r/4Pp2/1P2pPb1/2P3P1/P2B1K1P/4R3 w - - bm h4; id \"sbd.031\"; cem -0.08; cee 0.88; ce 0.82;");
    pos.add(
        "Q5k1/5pp1/5n1p/2b2P2/8/5N1P/5PP1/2q1B1K1 b - - bm Kh7; id \"sbd.032\"; cem 0.16; cee 0.00; ce -0.03;");
    pos.add(
        "r1b1k1nr/1p3ppp/p1np4/4p1q1/2P1P3/N1NB4/PP3PPP/2RQK2R w Kkq - bm O-O; id \"sbd.033\"; cem -0.22; cee 0.40; ce -0.10;");
    pos.add(
        "r1b1k2r/p1pp1ppp/1np1q3/4P3/1bP5/1P6/PB1NQPPP/R3KB1R b KQkq - bm O-O; id \"sbd.034\"; cem 1.02; cee 0.39; ce 0.91;");
    pos.add(
        "r1b1k2r/ppppqppp/8/2bP4/3p4/6P1/PPQPPPBP/R1B2RK1 b kq - bm O-O; id \"sbd.035\"; cem 1.00; cee -0.38; ce 0.68;");
    pos.add(
        "r1b1k2r/ppq1bppp/2n5/2N1p3/8/2P1B1P1/P3PPBP/R2Q1RK1 b kq - bm O-O; id \"sbd.036\"; cem 0.68; cee -0.32; ce 0.56;");
    pos.add(
        "r1b1kb1r/pp2qppp/2pp4/8/4nP2/2N2N2/PPPP2PP/R1BQK2R w KQkq - bm O-O; id \"sbd.037\"; cem -1.10; cee -0.74; ce -0.99;");
    pos.add(
        "r1b1qrk1/pp4b1/2pRn1pp/5p2/2n2B2/2N2NPP/PPQ1PPB1/5RK1 w - - bm Rd3; id \"sbd.038\"; cem -0.89; cee 0.00; ce -0.79;");
    pos.add(
        "r1b2rk1/1pqn1pp1/p2bpn1p/8/3P4/2NB1N2/PPQB1PPP/3R1RK1 w - - bm Rc1; id \"sbd.039\"; cem 0.50; cee 0.49; ce 0.60;");
    pos.add(
        "r1b2rk1/2qnbp1p/p1npp1p1/1p4PQ/4PP2/1NNBB3/PPP4P/R4RK1 w - - bm Qh6; id \"sbd.040\"; cem -0.56; cee 0.10; ce -0.46;");
    pos.add(
        "r1b2rk1/pp2ppbp/2n2np1/2q5/5B2/1BN1PN2/PP3PPP/2RQK2R w K - bm O-O; id \"sbd.041\"; cem 0.12; cee 0.62; ce 0.22;");
    pos.add(
        "r1b2rk1/pp4pp/1q1Nppn1/2n4B/1P3P2/2B2RP1/P6P/R2Q3K b - - bm Na6; id \"sbd.042\"; cem 2.29; cee 1.30; ce 2.17;");
    pos.add(
        "r1b2rk1/ppp1qppp/1b1n4/8/B2n4/3NN3/PPPP1PPP/R1BQK2R w KQ - bm O-O; id \"sbd.043\"; cem -1.54; cee -0.10; ce -1.45;");
    pos.add(
        "r1b2rk1/ppq1bppp/2p1pn2/8/2NP4/2N1P3/PP2BPPP/2RQK2R w K - bm O-O; id \"sbd.044\"; cem -0.57; cee 0.82; ce -0.44;");
    pos.add(
        "r1bq1rk1/1p1n1pp1/p4n1p/2bp4/8/2NBPN2/PPQB1PPP/R3K2R w KQ - bm O-O; id \"sbd.045\"; cem -0.40; cee 0.90; ce -0.30;");
    pos.add(
        "r1bq1rk1/1p2ppbp/p2p1np1/6B1/2P1P3/2N5/PP1QBPPP/R3K2R w KQ - bm O-O; id \"sbd.046\"; cem -0.17; cee 1.10; ce -0.05;");
    pos.add(
        "r1bq1rk1/1p3ppp/p1np4/3Np1b1/2B1P3/P7/1PP2PPP/RN1QK2R w KQ - bm O-O; id \"sbd.047\"; cem -2.55; cee -0.14; ce -2.39;");
    pos.add(
        "r1bq1rk1/4bppp/ppnppn2/8/2P1P3/2N5/PPN1BPPP/R1BQK2R w KQ - bm O-O; id \"sbd.048\"; cem -0.75; cee 0.70; ce -0.65;");
    pos.add(
        "r1bq1rk1/pp1n1pbp/2n1p1p1/2ppP3/8/2PP1NP1/PP1N1PBP/R1BQ1RK1 w - - bm d4; id \"sbd.049\"; cem -0.10; cee -0.38; ce 0.00;");
    pos.add(
        "r1bq1rk1/pp1pppbp/2n2np1/8/4P3/1NN5/PPP1BPPP/R1BQK2R w KQ - bm O-O; id \"sbd.050\"; cem -0.10; cee 0.96; ce 0.00;");
    pos.add(
        "r1bq1rk1/pp2ppbp/2n2np1/2p3B1/4P3/2P2N2/PP1NBPPP/R2QK2R w KQ - bm O-O; id \"sbd.051\"; cem -0.11; cee 1.10; ce -0.01;");
    pos.add(
        "r1bq1rk1/pp2ppbp/2n3p1/2p5/2BPP3/2P1B3/P3NPPP/R2QK2R w KQ - bm O-O; id \"sbd.052\"; cem 0.70; cee 1.83; ce 0.81;");
    pos.add(
        "r1bq1rk1/pp3ppp/2n1pn2/2p5/1bBP4/2N1PN2/PP3PPP/R1BQ1RK1 w - - bm a3; id \"sbd.053\"; cem 0.71; cee 0.66; ce 0.81;");
    pos.add(
        "r1bq1rk1/pp3ppp/2n2n2/3p4/8/P1NB4/1PP2PPP/R1BQK2R w KQ - bm O-O; id \"sbd.054\"; cem -0.63; cee 0.67; ce -0.50;");
    pos.add(
        "r1bq1rk1/ppp1npb1/3p2pp/3Pp2n/1PP1P3/2N5/P2NBPPP/R1BQR1K1 b - - bm Nf4; id \"sbd.055\"; cem 2.08; cee 1.58; ce 1.99;");
    pos.add(
        "r1bq1rk1/ppp2ppp/2n1pn2/3p4/1bPP4/2NBPN2/PP3PPP/R1BQK2R w KQ - bm O-O; id \"sbd.056\"; cem -0.62; cee 0.67; ce -0.52;");
    pos.add(
        "r1bq1rk1/pppp1pbp/2n2np1/4p3/2P5/P1N2NP1/1P1PPPBP/R1BQK2R w KQ - bm O-O; id \"sbd.057\"; cem -0.75; cee 0.77; ce -0.65;");
    pos.add(
        "r1bqk2r/2ppbppp/p1n2n2/1p2p3/4P3/1B3N2/PPPPQPPP/RNB2RK1 b kq - bm O-O; id \"sbd.058\"; cem 0.23; cee -1.15; ce 0.13;");
    pos.add(
        "r1bqk2r/5ppp/p1np4/1p1Np1b1/4P3/2P5/PPN2PPP/R2QKB1R b KQkq - bm O-O; id \"sbd.059\"; cem 0.36; cee 0.30; ce 0.25;");
    pos.add(
        "r1bqk2r/bp3ppp/p1n1pn2/3p4/1PP5/P1N1PN2/1B3PPP/R2QKB1R b KQkq - bm O-O; id \"sbd.060\"; cem 0.81; cee 0.56; ce 0.71;");
    pos.add(
        "r1bqk2r/p2pppbp/2p3pn/2p5/4P3/2P2N2/PP1P1PPP/RNBQR1K1 b kq - bm O-O; id \"sbd.061\"; cem 0.42; cee -0.73; ce 0.29;");
    pos.add(
        "r1bqk2r/pp2bppp/2n1p3/1B1n4/3P4/2N2N2/PP3PPP/R1BQ1RK1 b kq - bm O-O; id \"sbd.062\"; cem 0.88; cee -0.67; ce 0.78;");
    pos.add(
        "r1bqk2r/pp2bppp/2n1p3/3n4/3P4/2NB1N2/PP3PPP/R1BQ1RK1 b kq - bm O-O; id \"sbd.063\"; cem 0.40; cee -0.92; ce 0.31;");
    pos.add(
        "r1bqk2r/pp2ppbp/2np1np1/2p5/4P3/1B1P1N1P/PPP2PP1/RNBQK2R w KQkq - bm O-O; id \"sbd.064\"; cem 0.12; cee -0.03; ce 0.22;");
    pos.add(
        "r1bqk2r/ppn1bppp/2n5/2p1p3/8/2NP1NP1/PP1BPPBP/R2Q1RK1 b kq - bm O-O; id \"sbd.065\"; cem 1.13; cee 0.28; ce 1.04;");
    pos.add(
        "r1bqk2r/ppp1bppp/2n5/3p4/3P4/2PB1N2/P1P2PPP/R1BQ1RK1 b kq - bm O-O; id \"sbd.066\"; cem 0.96; cee -0.47; ce 0.84;");
    pos.add(
        "r1bqk2r/ppp2ppp/2nb4/3np3/8/PP2P3/1BQP1PPP/RN2KBNR b KQkq - bm O-O; id \"sbd.067\"; cem -1.00; cee -0.93; ce -1.10;");
    pos.add(
        "r1bqk2r/ppp2ppp/3b4/4p3/8/1PPP1N2/2PB1PPP/R2Q1RK1 b kq - bm O-O; id \"sbd.068\"; cem 1.05; cee 0.46; ce 0.86;");
    pos.add(
        "r1bqk2r/pppp1ppp/5n2/4p3/Bb2P3/5Q2/PPPPNPPP/R1B1K2R b KQkq - bm O-O; id \"sbd.069\"; cem 0.43; cee 0.40; ce 0.33;");
    pos.add(
        "r1bqkb1r/pp3ppp/2n5/2pp4/3Pn3/2N2N2/PPP1BPPP/R1BQK2R w KQkq - bm O-O; id \"sbd.070\"; cem -0.66; cee -0.63; ce -0.56;");
    pos.add(
        "r1bqkb1r/pp3ppp/2npp3/3nP3/2BP4/5N2/PP3PPP/RNBQK2R w KQkq - bm O-O; id \"sbd.071\"; cem -0.31; cee -0.45; ce -0.22;");
    pos.add(
        "r1bqkbnr/3p1ppp/p1p1p3/8/4P3/3B4/PPP2PPP/RNBQK2R w KQkq - bm O-O; id \"sbd.072\"; cem 0.42; cee 0.32; ce 0.51;");
    pos.add(
        "r1bqkbnr/ppp2ppp/2n5/8/2BpP3/5N2/PP3PPP/RNBQK2R w KQkq - bm O-O; id \"sbd.073\"; cem -0.90; cee -0.74; ce -0.80;");
    pos.add(
        "r1bqrbk1/1pp3pp/2n2p2/p2np3/8/PP1PPN2/1BQNBPPP/R3K2R w KQ - bm O-O; id \"sbd.074\"; cem -0.71; cee 0.50; ce -0.61;");
    pos.add(
        "r1br2k1/1p2qppp/pN2pn2/P7/2pn4/4N1P1/1P2PPBP/R3QRK1 b - - bm Rb8; id \"sbd.075\"; cem 0.20; cee 0.27; ce 0.11;");
    pos.add(
        "r1q1k2r/1b1nbppp/pp1ppn2/8/2PQP3/1PN2NP1/PB3PBP/R2R2K1 b kq - bm O-O; id \"sbd.076\"; cem 1.36; cee 0.49; ce 1.26;");
    pos.add(
        "r1q1k2r/pb1nbppp/1p2pn2/8/P1PNP3/2B3P1/2QN1PBP/R4RK1 b kq - bm O-O; id \"sbd.077\"; cem 0.62; cee 0.24; ce 0.52;");
    pos.add(
        "r1r3k1/1bq2pbp/pp1pp1p1/2n5/P3PP2/R2B4/1PPBQ1PP/3N1R1K w - - bm Bc3; id \"sbd.078\"; cem -1.39; cee -0.78; ce -1.29;");
    pos.add(
        "r1rn2k1/pp1qppbp/6p1/3pP3/3P4/1P3N1P/PB1Q1PP1/R3R1K1 w - - bm Rac1; id \"sbd.079\"; cem 1.43; cee 0.42; ce 1.36;");
    pos.add(
        "r2q1rk1/1b1nbpp1/pp2pn1p/8/2BN3B/2N1P3/PP2QPPP/2R2RK1 w - - bm Rfd1; id \"sbd.080\"; cem -0.46; cee -0.34; ce -0.36;");
    pos.add(
        "r2q1rk1/1b3ppp/4pn2/1pP5/1b6/2NBPN2/1PQ2PPP/R3K2R w KQ - bm O-O; id \"sbd.081\"; cem -3.99; cee 0.00; ce -3.80;");
    pos.add(
        "r2q1rk1/pb1nppbp/6p1/1p6/3PP3/3QBN1P/P3BPP1/R3K2R w KQ - bm O-O; id \"sbd.082\"; cem 0.24; cee 0.69; ce 0.34;");
    pos.add(
        "r2q1rk1/pb2bppp/npp1pn2/3pN3/2PP4/1PB3P1/P2NPPBP/R2Q1RK1 w - - bm e4; id \"sbd.083\"; cem 0.96; cee 1.10; ce 1.06;");
    pos.add(
        "r2q1rk1/pppb1pbp/2np1np1/4p3/2P5/P1NPPNP1/1P3PBP/R1BQK2R w KQ - bm O-O; id \"sbd.084\"; cem -1.01; cee -0.33; ce -0.91;");
    pos.add(
        "r2qk2r/1b1n1ppp/4pn2/p7/1pPP4/3BPN2/1B3PPP/R2QK2R w KQkq - bm O-O; id \"sbd.085\"; cem 0.25; cee -0.73; ce 0.33;");
    pos.add(
        "r2qk2r/1b2bppp/p1n1pn2/1p6/1P6/P2BPN2/1B2QPPP/RN3RK1 b kq - bm O-O; id \"sbd.086\"; cem 0.75; cee -0.37; ce 0.65;");
    pos.add(
        "r2qk2r/2p2ppp/p1n1b3/1pbpP3/4n3/1BP2N2/PP1N1PPP/R1BQ1RK1 b kq - bm O-O; id \"sbd.087\"; cem -0.56; cee -2.33; ce -0.66;");
    pos.add(
        "r2qk2r/3n1ppp/p3p3/3nP3/3R4/5N2/1P1N1PPP/3QR1K1 b kq - bm O-O; id \"sbd.088\"; cem 1.52; cee 0.45; ce 1.24;");
    pos.add(
        "r2qk2r/p1pn1ppp/b3pn2/3p4/Nb1P4/1P3NP1/P3PPBP/1RBQ1RK1 b kq - bm O-O Qe7; id \"sbd.089\"; cem 1.33; cee -0.43; ce 1.23;");
    pos.add(
        "r2qk2r/ppp1bppp/2n2n2/8/2BP2b1/2N2N2/PP3PPP/R1BQR1K1 b kq - bm O-O; id \"sbd.090\"; cem 2.71; cee 0.93; ce 2.61;");
    pos.add(
        "r2qkb1r/pb1n1p2/2p1p2p/4P1pn/PppP4/2N2NB1/1P2BPPP/R2Q1RK1 w kq - bm Ne4; id \"sbd.091\"; cem -0.72; cee -2.06; ce -0.62;");
    pos.add(
        "r2qkb1r/pp2nppp/1np1p3/4Pb2/3P4/PB3N2/1P3PPP/RNBQ1RK1 b kq - bm Ned5; id \"sbd.092\"; cem 0.35; cee -1.64; ce 0.25;");
    pos.add(
        "r2qkb1r/pp3ppp/2bppn2/8/2PQP3/2N2N2/PP3PPP/R1B1K2R w KQkq - bm O-O; id \"sbd.093\"; cem -0.35; cee -0.08; ce -0.25;");
    pos.add(
        "r2qr1k1/p3bppp/1p2n3/3Q1N2/5P2/4B1P1/PP3R1P/R5K1 w - - bm Rd1; id \"sbd.094\"; cem -0.32; cee 0.00; ce -0.17;");
    pos.add(
        "r2r2k1/p1pnqpp1/1p2p2p/3b4/3P4/3BPN2/PP3PPP/2RQR1K1 b - - bm c5; id \"sbd.095\"; cem 0.64; cee 0.13; ce 0.46;");
    pos.add(
        "r2r2k1/pp1b1ppp/8/3p2P1/3N4/P3P3/1P3P1P/3RK2R b K - bm Rac8; id \"sbd.096\"; cem -0.63; cee 0.43; ce 0.07;");
    pos.add(
        "r3k2r/1b1nb1p1/p1q1pn1p/1pp3N1/4PP2/2N5/PPB3PP/R1BQ1RK1 w kq - bm Nf3; id \"sbd.097\"; cem 0.38; cee -1.35; ce 0.48;");
    pos.add(
        "r3k2r/1pqnnppp/p5b1/1PPp1p2/3P4/2N5/P2NB1PP/2RQ1RK1 b kq - bm O-O; id \"sbd.098\"; cem 1.28; cee 0.67; ce 1.17;");
    pos.add(
        "r3k2r/p1q1nppp/1pn5/2P1p3/4P1Q1/P1P2P2/4N1PP/R1B2K1R b kq - bm O-O; id \"sbd.099\"; cem 0.38; cee 0.21; ce 0.25;");
    pos.add(
        "r3k2r/pp2pp1p/6p1/2nP4/1R2PB2/4PK2/P5PP/5bNR w kq - bm Ne2; id \"sbd.100\"; cem -1.44; cee -0.25; ce -0.59;");
    pos.add(
        "r3k2r/ppp1bppp/2n5/3n4/3PB3/8/PP3PPP/RNB1R1K1 b kq - bm O-O-O; id \"sbd.101\"; cem 1.05; cee -0.64; ce 0.16;");
    pos.add(
        "r3kb1r/pp3ppp/4bn2/3p4/P7/4N1P1/1P2PPBP/R1B1K2R w KQkq - bm O-O; id \"sbd.102\"; cem -0.23; cee 0.00; ce -0.02;");
    pos.add(
        "r3kbnr/1pp3pp/p1p2p2/8/3qP3/5Q1P/PP3PP1/RNB2RK1 w kq - bm Rd1; id \"sbd.103\"; cem 1.15; cee -1.31; ce 0.84;");
    pos.add(
        "r3kr2/pppb1p2/2n3p1/3Bp2p/4P2N/2P5/PP3PPP/2KR3R b q - bm O-O-O; id \"sbd.104\"; cem 0.78; cee -0.03; ce 0.19;");
    pos.add(
        "r3nrk1/pp2qpb1/3p1npp/2pPp3/2P1P2N/2N3Pb/PP1BBP1P/R2Q1RK1 w - - bm Re1; id \"sbd.105\"; cem 0.33; cee 0.19; ce 0.42;");
    pos.add(
        "r3r1k1/1pqn1pbp/p2p2p1/2nP2B1/P1P1P3/2NB3P/5PP1/R2QR1K1 w - - bm Rc1; id \"sbd.106\"; cem 0.27; cee -0.32; ce 0.35;");
    pos.add(
        "r3r1k1/pp1q1ppp/2p5/P2n1p2/1b1P4/1B2PP2/1PQ3PP/R1B2RK1 w - - bm e4; id \"sbd.107\"; cem -0.79; cee -1.20; ce -0.75;");
    pos.add(
        "r3r1k1/pp3ppp/2ppqn2/5R2/2P5/2PQP1P1/P2P2BP/5RK1 w - - bm Qd4; id \"sbd.108\"; cem -0.16; cee -0.32; ce -0.12;");
    pos.add(
        "r3rbk1/p2b1p2/5p1p/1q1p4/N7/6P1/PP1BPPBP/3Q1RK1 w - - bm Nc3; id \"sbd.109\"; cem -0.93; cee -1.32; ce -0.91;");
    pos.add(
        "r4r1k/pp1bq1b1/n2p2p1/2pPp1Np/2P4P/P1N1BP2/1P1Q2P1/2KR3R w - - bm Ne6; id \"sbd.110\"; cem 0.89; cee 1.56; ce 1.00;");
    pos.add(
        "r4rk1/1bqp1ppp/pp2pn2/4b3/P1P1P3/2N2BP1/1PQB1P1P/2R2RK1 w - - bm b3; id \"sbd.111\"; cem -1.18; cee -0.28; ce -1.07;");
    pos.add(
        "r4rk1/1q2bppp/p1bppn2/8/3BPP2/3B2Q1/1PP1N1PP/4RR1K w - - bm e5; id \"sbd.112\"; cem 0.71; cee 0.64; ce 0.80;");
    pos.add(
        "r4rk1/pp2qpp1/2pRb2p/4P3/2p5/2Q1PN2/PP3PPP/4K2R w K - bm O-O; id \"sbd.113\"; cem -0.89; cee 0.28; ce -0.44;");
    pos.add(
        "r7/3rq1kp/2p1bpp1/p1Pnp3/2B4P/PP4P1/1B1RQP2/2R3K1 b - - bm Rad8; id \"sbd.114\"; cem 0.20; cee -0.66; ce -0.03;");
    pos.add(
        "r7/pp1bpp2/1n1p2pk/1B3P2/4P1P1/2N5/PPP5/1K5R b - - bm Kg5; id \"sbd.115\"; cem 2.59; cee 0.55; ce 0.78;");
    pos.add(
        "rn1q1rk1/p4pbp/bp1p1np1/2pP4/8/P1N2NP1/1PQ1PPBP/R1B1K2R w KQ - bm O-O; id \"sbd.116\"; cem 0.03; cee 1.19; ce 0.12;");
    pos.add(
        "rn1q1rk1/pb3p2/1p5p/3n2P1/3p4/P4P2/1P1Q1BP1/R3KBNR b KQ - bm Re8+; id \"sbd.117\"; cem -2.79; cee 1.64; ce -2.78;");
    pos.add(
        "rn1q1rk1/pp2bppp/1n2p1b1/8/2pPP3/1BN1BP2/PP2N1PP/R2Q1RK1 w - - bm Bc2; id \"sbd.118\"; cem 0.91; cee 1.75; ce 1.01;");
    pos.add(
        "rn1q1rk1/pp3ppp/4bn2/2bp4/5B2/2NBP1N1/PP3PPP/R2QK2R w KQ - bm O-O; id \"sbd.119\"; cem 0.13; cee 1.42; ce 0.23;");
    pos.add(
        "rn1qkbnr/pp1b1ppp/8/1Bpp4/3P4/8/PPPNQPPP/R1B1K1NR b KQkq - bm Qe7; id \"sbd.120\"; cem 0.80; cee 0.43; ce 0.71;");
    pos.add(
        "rn1qr1k1/pb3p2/1p5p/3n2P1/3p4/P4P2/1P1QNBP1/R3KB1R b KQ - bm d3; id \"sbd.121\"; cem -0.52; cee 1.36; ce -0.57;");
    pos.add(
        "rn2kb1r/pp2nppp/1q2p3/3pP3/3P4/5N2/PP2NPPP/R1BQK2R w KQkq - bm O-O; id \"sbd.122\"; cem 1.58; cee 0.43; ce 1.64;");
    pos.add(
        "rn3rk1/1bqp1ppp/p3pn2/8/Nb1NP3/4B3/PP2BPPP/R2Q1RK1 w - - bm Rc1; id \"sbd.123\"; cem 1.01; cee 1.07; ce 1.11;");
    pos.add(
        "rn3rk1/pbp1qppp/1p1ppn2/8/2PP4/P1Q2NP1/1P2PPBP/R1B1K2R w KQ - bm O-O; id \"sbd.124\"; cem -0.23; cee 0.79; ce -0.11;");
    pos.add(
        "rnb1k2r/1pq2ppp/p2ppn2/2b5/3NPP2/2P2B2/PP4PP/RNBQ1R1K b kq - bm O-O; id \"sbd.125\"; cem 2.12; cee 0.35; ce 2.02;");
    pos.add(
        "rnb2rk1/ppq1ppbp/6p1/2p5/3PP3/2P2N2/P3BPPP/1RBQK2R w K - bm O-O; id \"sbd.126\"; cem 0.50; cee 1.63; ce 0.62;");
    pos.add(
        "rnbq1rk1/5ppp/p3pn2/1p6/2BP4/P1P2N2/5PPP/R1BQ1RK1 w - - bm Bd3; id \"sbd.127\"; cem 0.11; cee 0.07; ce 0.20;");
    pos.add(
        "rnbq1rk1/pp2ppbp/2pp1np1/8/P2PP3/2N2N2/1PP1BPPP/R1BQK2R w KQ - bm O-O; id \"sbd.128\"; cem 0.06; cee 1.32; ce 0.15;");
    pos.add(
        "rnbq1rk1/ppp1ppbp/6p1/8/8/2P2NP1/P2PPPBP/R1BQK2R w KQ - bm O-O; id \"sbd.129\"; cem -0.79; cee 0.43; ce -0.67;");
    pos.add(
        "rnbqk1nr/pp3pbp/2ppp1p1/8/2BPP3/2N2Q2/PPP2PPP/R1B1K1NR w KQkq - bm Nge2; id \"sbd.130\"; cem 0.84; cee 0.03; ce 0.94;");
    pos.add(
        "rnbqk2r/ppp2ppp/1b1p1n2/4p3/2B1P3/2PP1N2/PP1N1PPP/R1BQK2R b KQkq - bm O-O; id \"sbd.131\"; cem 2.93; cee 0.98; ce 2.84;");
    pos.add(
        "rnbqk2r/pppp2pp/4pn2/5p2/1b1P4/2P2NP1/PP2PPBP/RNBQK2R b KQkq - bm Be7; id \"sbd.132\"; cem 2.21; cee 1.48; ce 2.12;");
    pos.add(
        "rnbqr1k1/pp1p1ppp/5n2/3Pb3/1P6/P1N3P1/4NPBP/R1BQK2R w KQ - bm O-O; id \"sbd.133\"; cem 0.50; cee 2.09; ce 0.59;");
    pos.add(
        "rnq1nrk1/pp3pbp/6p1/3p4/3P4/5N2/PP2BPPP/R1BQK2R w KQ - bm O-O; id \"sbd.134\"; cem 0.42; cee 1.06; ce 0.53;");
    pos.add(
        "1kr5/3n4/q3p2p/p2n2p1/PppB1P2/5BP1/1P2Q2P/3R2K1 w - - bm f5; id \"Undermine.001\"; c0 \"f5=10, Be5+=2, Bf2=3, Bg4=2\"; cem 0.41; cee -0.53; ce 0.13;");
    pos.add(
        "1n5k/3q3p/pp1p2pB/5r2/1PP1Qp2/P6P/6P1/2R3K1 w - - bm c5; id \"Undermine.002\"; c0 \"c5=10, Qd4+=4, b5=4, g4=3\"; cem 1.94; cee 0.44; ce 1.24;");
    pos.add(
        "1n6/4bk1r/1p2rp2/pP2pN1p/K1P1N2P/8/P5R1/3R4 w - - bm c5; id \"Undermine.003\"; c0 \"c5=10, Rd3=7, Rdd2=7, Rg3=7 Rd5=9\"; cem 3.56; cee 1.82; ce 2.58;");
    pos.add(
        "1nr5/1k5r/p3pqp1/3p4/1P1P1PP1/R4N2/3Q1PK1/R7 w - - bm b5; id \"Undermine.004\"; c0 \"b5=10, Kg3=4, Ng5=4, Qe3=4\"; cem 1.94; cee 2.63; ce 2.25;");
    pos.add(
        "1q2r1k1/1b2bpp1/p2ppn1p/2p5/P3PP1B/2PB1RP1/2P1Q2P/2KR4 b - - bm c4; id \"Undermine.005\"; c0 \"c4=10, Bc6=7, Qa8=7, Qc8=7\"; cem -0.06; cee 2.45; ce 0.35;");
    pos.add(
        "1q4k1/5p1p/p1rprnp1/3R4/N1P1P3/1P6/P5PP/3Q1R1K w - - bm e5; id \"Undermine.006\"; c0 \"e5=10, Nc3=3, Qd3=1, Qf3=2\"; cem 0.64; cee 0.80; ce 0.79;");
    pos.add(
        "1qr1k2r/1p2bp2/pBn1p3/P2pPbpp/5P2/2P1QBPP/1P1N3R/R4K2 b k - bm h4; id \"Undermine.007\"; c0 \"h4=10, Bd8=1, Bf8=1, Rh7=1\"; cem 1.09; cee 0.79; ce 0.99;");
    pos.add(
        "1r1b2k1/2r2ppp/p1qp4/3R1NPP/1pn1PQB1/8/PPP3R1/1K6 w - - bm g6; id \"Undermine.008\"; c0 \"g6=10, Ka1=2, Nd4=2, Rd3=2\"; cem 2.18; cee 1.64; ce 2.13;");
    pos.add(
        "1r1qk1nr/p3ppbp/3p2p1/1pp5/2bPP3/4B1P1/2PQNPBP/R2R2K1 w k - bm e5; id \"Undermine.009\"; c0 \"e5=10, Bf3=5, Nc1=5, Rxa7=4\"; cem 0.13; cee -0.69; ce 0.22;");
    pos.add(
        "1r1r2k1/p3n2p/b1nqpbp1/2pp4/1p3PP1/2PP1N2/PPN3BP/R1BRQ2K w - - bm d4; id \"Undermine.010\"; c0 \"d4=10, Ng5=6, a4=6, h3=6\"; cem -0.31; cee -1.22; ce -0.21;");
    pos.add(
        "1r2n1rk/pP2q2p/P2p4/4pQ2/2P2p2/5B1P/3R1P1K/3R4 w - - bm c5; id \"Undermine.011\"; c0 \"c5=10, Bc6=6, Qc2=5, Rg1=5\"; cem 2.79; cee 4.35; ce 3.36;");
    pos.add(
        "1r3bk1/7p/pp1q2p1/P1pPp3/2P3b1/4B3/1P1Q2BP/R6K w - - bm b4; id \"Undermine.012\"; c0 \"b4=10, Bg1=3, axb6=3, h3=1\"; cem -0.31; cee -0.47; ce -0.27;");
    pos.add(
        "1r3rk1/3n1pbp/1q1pp1p1/p1p5/2PnPP2/PPB1N1PP/6B1/1R1Q1RK1 b - - bm a4; id \"Undermine.013\"; c0 \"a4=10, Ne2+=2, Rfd8=2, h5=2\"; cem 0.77; cee 0.09; ce 0.65;");
    pos.add(
        "1r3rk1/p5bp/6p1/q1pPppn1/7P/1B1PQ1P1/PB3P2/R4RK1 b - - bm f4; id \"Undermine.014\"; c0 \"f4=10, Nf7=4, c4=5, e4=5\"; cem -0.12; cee 0.52; ce -0.12;");
    pos.add(
        "1r4k1/1rq2pp1/3b1nn1/pBpPp3/P1N4p/2PP1Q1P/6PB/2R2RK1 w - - bm d4; id \"Undermine.015\"; c0 \"d4=10, Bc6=4, Qf5=6, Rce1=4\"; cem 1.55; cee 0.88; ce 1.63;");
    pos.add(
        "1r4k1/p1rqbp1p/b1p1p1p1/NpP1P3/3PB3/3Q2P1/P4P1P/3RR1K1 w - - bm a4; id \"Undermine.016\"; c0 \"a4=10, Kg2=4, Qf3=4, h4=5\"; cem 1.09; cee 1.22; ce 1.21;");
    pos.add(
        "2r3k1/p2q1pp1/Pbrp3p/6n1/1BP1PpP1/R4P2/2QN2KP/1R6 b - - bm h5; id \"Undermine.017\"; c0 \"h5=10, Be3=4, Ne6=4, Qd8=4\"; cem -2.38; cee 0.62; ce -1.99;");
    pos.add(
        "1r6/2q2pk1/2n1p1pp/p1Pr4/P1RP4/1p1RQ2P/1N3PP1/7K b - - bm e5; id \"Undermine.018\"; c0 \"e5=10, Kh7=4, Qb7=5, Rbd8=5\"; cem -1.05; cee -2.79; ce -1.69;");
    pos.add(
        "1r6/R1nk1p2/1p4pp/pP1p1P2/P2P3P/5PN1/5K2/8 w - - bm h5; id \"Undermine.019\"; c0 \"h5=10, Ne2=4, Nf1=7, f4=7\"; cem 1.07; cee 0.62; ce 0.72;");
    pos.add(
        "1rb3k1/2pn2pp/p2p4/4p3/1pP4q/1P1PBP1P/1PQ2P2/R3R1K1 w - - bm c5; id \"Undermine.020\"; c0 \"c5=10, Kf1=5, Kg2=5, Ra5=5\"; cem 0.85; cee 1.55; ce 1.10;");
    pos.add(
        "1rbqnrk1/6bp/pp3np1/2pPp3/P1P1N3/2N1B3/1P2Q1BP/R4R1K w - - bm a5; id \"Undermine.021\"; c0 \"a5=10, Bg5=6, Kg1=6, Nxf6+=6\"; cem 1.81; cee 1.43; ce 1.90;");
    pos.add(
        "1rr3k1/1q3pp1/pnbQp2p/1p2P3/3B1P2/2PB4/P1P2RPP/R5K1 w - - bm f5; id \"Undermine.022\"; c0 \"f5=10, Qa3=3, Rd1=2, h3=2\"; cem 0.18; cee 0.04; ce 0.25;");
    pos.add(
        "2kr2r1/1bpnqp2/1p1ppn2/p5pp/P1PP4/4PP2/1P1NBBPP/R2Q1RK1 w - - bm b4; id \"Undermine.023\"; c0 \"b4=10, Qb1=3, Qc2=4, Re1=4\"; cem 0.81; cee -0.30; ce 0.88;");
    pos.add(
        "2b1k2r/5p2/pq1pNp1b/1p6/2r1PPBp/3Q4/PPP3PP/1K1RR3 w k - bm e5; id \"Undermine.024\"; c0 \"e5=10, Qd5=1, Qh3=7, f5=1\"; cem 5.19; cee 1.98; ce 4.78;");
    pos.add(
        "2b1r1k1/1p6/pQ1p1q1p/P2P3P/2P1pPpN/6P1/4R1K1/8 w - - bm c5; id \"Undermine.025\"; c0 \"c5=10, Kg1=6, Kh2=5, Re3=5\"; cem 0.79; cee 1.59; ce 1.31;");
    pos.add(
        "2b2rk1/2qn1p2/p2p2pp/2pPP3/8/4NN1P/P1Q2PP1/bB2R1K1 w - - bm e6; id \"Undermine.026\"; c0 \"e6=10, Nc4=1, Ng4=1, exd6=2\"; cem 1.00; cee 0.39; ce 0.95;");
    pos.add(
        "2bq2k1/1pr3bp/1Qpr2p1/P2pNp2/3P1P1P/6P1/5PB1/1RR3K1 w - - bm a6; id \"Undermine.027\"; c0 \"a6=10, Qc5=2, Rc5=3, h5=2\"; cem 0.08; cee 1.50; ce 0.39;");
    pos.add(
        "rr6/8/2pbkp2/ppp1p1p1/P3P3/1P1P1PB1/R1P2PK1/R7 b - - bm c4; id \"Undermine.28\"; c0 \"c4=10, Bc7=4, Rb6=1, b4=1\"; cem 0.33; cee -1.05; ce -0.80;");
    pos.add(
        "2r2rk1/pb2q2p/1pn1p2p/5p1Q/3P4/P1NB4/1P3PPP/R4RK1 w - - bm d5; id \"Undermine.029\"; c0 \"d5=10, Qxh6=5, Rac1=5, Rfd1=5\"; cem 0.28; cee 0.00; ce 0.33;");
    pos.add(
        "2kr4/ppqnbp1r/2n1p1p1/P2pP3/3P2P1/3BBN2/1P1Q1PP1/R4RK1 w - - bm a6; id \"Undermine.030\"; c0 \"a6=10, Qc2=3, Rfc1=4, g3=4\"; cem 1.21; cee 0.79; ce 1.29;");
    pos.add(
        "2q5/1pb2r1k/p1b3pB/P1Pp3p/3P4/3B1pPP/1R3P1K/2Q5 b - - bm h4; id \"Undermine.031\"; c0 \"h4=10, Bb5=5, Bd7=1, Qd8=5\"; cem 0.02; cee -0.25; ce -0.18;");
    pos.add(
        "2r1kb1r/1bqn1pp1/p3p3/1p2P1P1/3Np3/P1N1B3/1PP1Q2P/R4RK1 w k - bm g6; id \"Undermine.032\"; c0 \"g6=10, Bf4=6, Rae1=6, Rf4=6\"; cem -0.44; cee -0.41; ce -0.34;");
    pos.add(
        "2r1rb2/1bq2p1k/3p1np1/p1p5/1pP1P1P1/PP2BPN1/2Q3P1/R2R1BK1 b - - bm d5; id \"Undermine.033\"; c0 \"d5=10, Bg7=7, Kg8=7, Nd7=7\"; cem 1.70; cee 1.98; ce 1.60;");
    pos.add(
        "2r2bk1/pq3r1p/6p1/2ppP1P1/P7/BP1Q4/2R3P1/3R3K b - - bm d4; id \"Undermine.034\"; c0 \"d4=10, Qe7=1, Rcc7=1, c4=1\"; cem -0.35; cee 0.18; ce -0.28;");
    pos.add(
        "2r2rk1/1bb2ppp/p2ppn2/1p4q1/1PnNP3/P1N4P/2P1QPPB/3RRBK1 w - - bm a4; id \"Undermine.035\"; c0 \"a4=10, Nf3=4, Rb1=5, Rd3=5\"; cem -0.42; cee -0.15; ce -0.32;");
    pos.add(
        "2r2rk1/3q3p/p3pbp1/1p1pp3/4P3/2P5/PPN1QPPP/3R1RK1 b - - bm d4; id \"Undermine.036\"; c0 \"d4=10, Bg7=7, Qb7=6, Qd6=7\"; cem 0.24; cee -0.62; ce -0.12;");
    pos.add(
        "2r4k/pp3q1b/5PpQ/3p4/3Bp3/1P6/P5RP/6K1 w - - bm h4; id \"Undermine.037\"; c0 \"h4=10, Rg3=2, Rg4=2, Rg5=2\"; cem 4.55; cee 0.72; ce 2.51;");
    pos.add(
        "2r3k1/1b2b2p/r2p1pp1/pN1Pn3/1pPB2P1/1P5P/P3R1B1/5RK1 w - - bm g5; id \"Undermine.038\"; c0 \"g5=10, Rd1=4, Rff2=4, h4=5\"; cem 1.71; cee 0.92; ce 1.44;");
    pos.add(
        "2r3k1/5pp1/1pq4p/p7/P1nR4/2P2P2/Q5PP/4B1K1 b - - bm b5; id \"Undermine.039\"; c0 \"b5=10, Nd6=1, Ne3=1, Ne5=1\"; cem 0.21; cee 0.00; ce 0.00;");
    pos.add(
        "6k1/6pp/4r3/p1qpp3/Pp6/1n1P1B1P/1B2Q1P1/3R1K2 w - - bm d4; id \"Undermine.040\"; c0 \"d4=10, Bxe5=3, Qf2=2, Re1=3\"; cem 1.02; cee 1.52; ce 1.35;");
    pos.add(
        "r2qkb1r/1b1n1ppp/p3pn2/1pp5/3PP3/2NB1N2/PP3PPP/R1BQ1RK1 w kq - bm d5; id \"Undermine.041\"; c0 \"d5=10, Be3=3, a3=2, e5=3\"; cem 0.75; cee -0.85; ce 0.85;");
    pos.add(
        "r3r1k1/pn1bnpp1/1p2p2p/1q1pPP2/1BpP3N/2P2BP1/2P3QP/R4RK1 w - - bm f6; id \"Undermine.42\"; c0 \"f6=10, Bxe7=6, Rab1=5, g4=5\"; cem -0.81; cee -1.86; ce -0.74;");
    pos.add(
        "2r5/p3kpp1/1pn1p2p/8/1PP2P2/PB1R1KP1/7P/8 b - - bm a5; id \"Undermine.043\"; c0 \"a5=10, a6=1, e5=1, f5=1\"; cem 0.81; cee 0.92; ce 0.82;");
    pos.add(
        "2rq1rk1/1b2bppp/p2p1n2/1p1Pp3/1Pn1P3/5N1P/P1B2PP1/RNBQR1K1 w - - bm a4; id \"Undermine.044\"; c0 \"a4=10, Bb3=1, Nbd2=1, Nc3=1\"; cem -0.81; cee -0.94; ce -0.72;");
    pos.add(
        "2rqr1k1/1b2bp1p/ppn1p1pB/3n4/3P3P/P1NQ1N2/1PB2PP1/3RR1K1 w - - bm h5; id \"Undermine.045\"; c0 \"h5=10, Bc1=4, Nxd5=1, Rb1=1\"; cem 0.37; cee 0.00; ce 0.46;");
    pos.add(
        "3Rb3/5ppk/2r1r3/p5Pp/1pN2P1P/1P5q/P4Q2/K2R4 b - - bm a4; id \"Undermine.046\"; c0 \"a4=10, Rc7=7, Re7=6, Rxc4=7\"; cem 1.06; cee 1.29; ce 1.03;");
    pos.add(
        "3Rbrk1/4Q2p/6q1/pp3p2/4p2P/1P4P1/8/5R1K w - - bm g4; id \"Undermine.047\"; c0 \"g4=10, Kh2=5, Rc8=2, Rf2=3\"; cem 0.06; cee 0.12; ce 0.18;");
    pos.add(
        "3bn3/3r1p1k/3Pp1p1/1q6/Np2BP1P/3R2PK/8/3Q4 w - - bm h5; id \"Undermine.048\"; c0 \"h5=10, Bf3=6, Rd4=6, g4=6\"; cem 0.31; cee 0.25; ce 0.36;");
    pos.add(
        "3k1r1r/p2n1p1p/q2p2pQ/1p2P3/2pP4/P4N2/5PPP/2R1R1K1 w - - bm a4; id \"Undermine.049\"; c0 \"a4=10, Ng5=5, Qh4+=8, exd6=6\"; cem 2.38; cee -1.00; ce 1.42;");
    pos.add(
        "3r1bk1/1p2qp1p/p5p1/P1pPp3/2QnP3/3BB3/1P3PPP/2R3K1 w - - bm f4; id \"Undermine.050\"; c0 \"f4=10, Rb1=4, Rf1=4, h3=4\"; cem 0.82; cee 1.09; ce 1.02;");
    pos.add(
        "3r1bkr/2q3pp/1p1Npp2/pPn1P3/5B2/1P6/2P2PPP/R2QR1K1 w - - bm b4; id \"Undermine.051\"; c0 \"b4=10, Bd2=5, Qf3=4, exf6=3\"; cem 2.97; cee 2.54; ce 2.99;");
    pos.add(
        "3r2k1/p2q1pp1/1p2n1p1/2p1P2n/P4P2/2B1Q1P1/7P/1R3BK1 w - - bm a5; id \"Undermine.052\"; c0 \"a5=10, Bb5=3, Qe4=1, Ra1=3\"; cem -0.43; cee -0.92; ce -0.52;");
    pos.add(
        "3r4/8/pq3kr1/3Bp3/7p/1P3P2/P5PP/3RQ2K b - - bm h3; id \"Undermine.053\"; c0 \"h3=10, Kg7=5, Rd7=4, Rh6=5\"; cem 4.15; cee -0.58; ce 1.74;");
    pos.add(
        "3r4/pk1p3p/1p2pp2/1N6/2P1KP2/6P1/3R3P/8 w - - bm f5; id \"Undermine.054\"; c0 \"f5=10, Kd4=5, Ke3=5, Nc3=5\"; cem 1.70; cee 2.84; ce 2.94;");
    pos.add(
        "4k2r/1b2b3/p3pp1p/1p1p4/3BnpP1/P1P4R/1KP4P/5BR1 w k - bm g5; id \"Undermine.055\"; c0 \"g5=10, Be2=5, a4=6, c4=5\"; cem -0.89; cee -1.85; ce -1.42;");
    pos.add(
        "4k3/r2bbprp/3p1p1N/2qBpP2/ppP1P1P1/1P1R3P/P7/1KR1Q3 w - - bm a3; id \"Undermine.056\"; c0 \"a3=10, Qd2=3, Rc2=3, h4=3\"; cem 2.68; cee 0.65; ce 2.46;");
    pos.add(
        "4q1k1/pb5p/Nbp1p1r1/3r1p2/PP1Pp1pP/4P1P1/1BR1QP2/2R3K1 w - - bm b5; id \"Undermine.057\"; c0 \"b5=10, Ba1=3, Kg2=3, a5=3\"; cem 0.86; cee 0.72; ce 0.93;");
    pos.add(
        "4r1k1/1pb3qp/p1b1r1p1/P1Pp4/3P1p2/2BB4/1R1Q1PPP/1R4K1 b - - bm f3; id \"Undermine.058\"; c0 \"f3=10, Qd7=7, Qe7=7, Qf6=7\"; cem -0.22; cee 0.00; ce -0.27;");
    pos.add(
        "4r1k1/5p1p/p2q2p1/3p4/3Qn3/2P1RN2/Pr3PPP/R5K1 w - - bm c4; id \"Undermine.059\"; c0 \"c4=10, Ree1=5, a3=5, g3=5\"; cem -2.50; cee -2.08; ce -2.26;");
    pos.add(
        "4rr1k/pp1n2bp/7n/1Pp1pp1q/2Pp3N/1N1P1PP1/P5QP/2B1RR1K b - - bm f4; id \"Undermine.060\"; c0 \"f4=10, Nf7=3, Rf7=4, b6=1\"; cem -0.26; cee -0.34; ce -0.36;");
    pos.add(
        "4rrk1/p6p/2q2pp1/1p6/2pP1BQP/5N2/P4PP1/2R3K1 w - - bm h5; id \"Undermine.061\"; c0 \"h5=10, Bd2=1, Qg3=1, a4=1\"; cem 1.60; cee 0.00; ce 1.02;");
    pos.add(
        "5nk1/1bp1rnp1/pp1p4/4p1P1/2PPP3/NBP5/P2B4/4R1K1 w - - bm c5; id \"Undermine.062\"; c0 \"c5=10, Kf1=7, Kf2=7, d5=7\"; cem -0.14; cee -0.43; ce -0.24;");
    pos.add(
        "5r2/1p1k4/2bp4/r3pp1p/PRP4P/2P2PP1/2B2K2/7R b - - bm f4; id \"Undermine.063\"; c0 \"f4=10, Kc7=4, Raa8=4, Rf7=4\"; cem 1.02; cee 0.88; ce 0.82;");
    pos.add(
        "5r2/5p1Q/4pkp1/p7/1pb2q1P/5P2/P4RP1/3R2K1 w - - bm h5; id \"Undermine.064\"; c0 \"h5=10, Rb2=1, Rd7=1, Rdd2=1\"; cem 0.86; cee -0.20; ce 0.43;");
    pos.add(
        "5rk1/1Q3pp1/p2p3p/4p1b1/N3PqP1/1N1K4/PP6/3R4 b - - bm d5; id \"Undermine.065\"; c0 \"d5=10, Qf3+=1, Qxg4=1, h5=1\"; cem -3.40; cee 2.34; ce -0.85;");
    pos.add(
        "7r/3nkpp1/4p3/p1pbP3/1r3P1p/1P2B2P/P2RBKP1/7R b - - bm a4; id \"Undermine.066\"; c0 \"a4=10, Rc8=6, Rd8=6, Rhb8=6\"; cem 1.19; cee 0.13; ce 0.44;");
    pos.add(
        "8/1r1rq2k/2p3p1/3b1p1p/4p2P/1N1nP1P1/2Q2PK1/RR3B2 b - - bm f4; id \"Undermine.067\"; c0 \"f4=10, Rb4=3, c5=3, g5=3\"; cem -2.91; cee -1.91; ce -2.84;");
    pos.add(
        "8/1r2k3/4p2p/R3K2P/1p1P1P2/1P6/8/8 w - - bm f5; id \"Undermine.068\"; c0 \"f5=10, Ke4=4, Rc5=4, d5=3\"; cem 0.88; cee 1.65; ce 1.75;");
    pos.add(
        "8/3r1pp1/p7/2k2PpP/rp1pB3/2pK1P2/P1R5/1R6 w - - bm f6; id \"Undermine.069\"; c0 \"f6=10, Rg1=1, Rh1=1, f4=2\"; cem -0.07; cee 0.45; ce 0.45;");
    pos.add(
        "8/6k1/3P1bp1/2B1p3/1P6/1Q3P1q/7r/1K2R3 b - - bm e4; id \"Undermine.070\"; c0 \"e4=10, Qc8=1, Qf5+=1, g5=1\"; cem 0.02; cee 1.99; ce 0.92;");
    pos.add(
        "b2rrbk1/2q2p1p/pn1p2p1/1p4P1/2nNPB1P/P1N3Q1/1PP3B1/1K1RR3 w - - bm h5; id \"Undermine.071\"; c0 \"h5=10, Bc1=6, Na2=6, Qh3=6\"; cem 1.67; cee 1.39; ce 1.76;");
    pos.add(
        "b7/2pr1kp1/1p3p2/p2p3p/P1nP1N2/4P1P1/P1R2P1P/2R3K1 w - - bm e4; id \"Undermine.072\"; c0 \"e4=10, Rc3=5, Re2=6, f3=5\"; cem 2.95; cee 2.70; ce 2.85;");
    pos.add(
        "k1qbr1n1/1p4p1/p1p1p1Np/2P2p1P/3P4/R7/PP2Q1P1/1K1R4 w - - bm d5; id \"Undermine.073\"; c0 \"d5=10, Ra4=3, Rdd3=4, g4=3\"; cem 2.26; cee 3.21; ce 2.69;");
    pos.add(
        "r1b1rnk1/pp3pq1/2p3p1/6P1/2B2P1R/2P5/PP1Q2P1/2K4R w - - bm f5; id \"Undermine.074\"; c0 \"f5=10, Bd3=3, Rh8+=1, g4=4\"; cem 2.52; cee -0.85; ce 1.83;");
    pos.add(
        "r1bq1rk1/pp3pbp/3Pp1p1/2p5/4PP2/2P5/P2QB1PP/1RB1K2R b K - bm e5; id \"Undermine.075\"; c0 \"e5=10, Bd7=7, Qh4+=7, b6=6\"; cem 0.66; cee 2.38; ce 0.83;");
    pos.add(
        "r1bqr2k/pppn2bp/4n3/2P1p1p1/1P2Pp2/5NPB/PBQN1P1P/R4RK1 w - - bm c6; id \"Undermine.076\"; c0 \"c6=10, Nb3=5, Nc4=5, Rac1=5\"; cem 1.58; cee 1.62; ce 1.68;");
    pos.add(
        "r1br1k2/1pq2pb1/1np1p1pp/2N1N3/p2P1P1P/P3P1R1/1PQ3P1/1BR3K1 w - - bm h5; id \"Undermine.077\"; c0 \"h5=10, Ba2=4, Re1=4, Rf1=5\"; cem 3.46; cee 0.87; ce 3.50;");
    pos.add(
        "r1n2k1r/5pp1/2R5/pB2pPq1/P2pP3/6Pp/1P2Q2P/5RK1 w - - bm f6; id \"Undermine.078\"; c0 \"f6=10, Qd3=2, Rc5=2, Rg6=2\"; cem 2.02; cee 0.73; ce 1.72;");
    pos.add(
        "r1r2bk1/pp1n1p1p/2pqb1p1/3p4/1P1P4/1QN1PN2/P3BPPP/2RR2K1 w - - bm b5; id \"Undermine.079\"; c0 \"b5=10, Qc2=5, Rb1=4, a3=4\"; cem -0.24; cee -0.15; ce -0.14;");
    pos.add(
        "r2q1r2/pp1b2kp/2n1p1p1/3p4/3P1P1P/2PB1N2/6P1/R3QRK1 w - - bm h5; id \"Undermine.080\"; c0 \"h5=10, Qe3=3, Rb1=4, g3=4\"; cem -0.20; cee -1.00; ce -0.23;");
    pos.add(
        "r2q1rk1/pp2b1pp/1np1b3/4pp2/1P6/P1NP1BP1/2Q1PP1P/1RB2RK1 w - - bm b5; id \"Undermine.081\"; c0 \"b5=10, Be3=1, Bg2=1, Re1=1\"; cem -0.78; cee -0.91; ce -0.69;");
    pos.add(
        "r2q4/6k1/r1p3p1/np1p1p2/3P4/4P1P1/R2QBPK1/7R w - - bm e4; id \"Undermine.082\"; c0 \"e4=10, Qb2=5, Qc3=5, Rc2=2\"; cem 1.34; cee 0.00; ce 1.02;");
    pos.add(
        "r2qr1k1/pp3pbp/5np1/2p2b2/8/2PP1Q2/PPB3PP/RNB2RK1 b - - bm c4; id \"Undermine.083\"; c0 \"c4=10, Bg4=3, Ng4=2, Qd7=3\"; cem -1.25; cee -1.42; ce -1.34;");
    pos.add(
        "r3k2r/1bq1bpp1/p4n2/2p1pP2/2NpP2p/3B4/PPP3PP/R1B1QR1K b k - bm h3; id \"Undermine.084\"; c0 \"h3=10, Bc6=3, Bd8=1, Kf8=1\"; cem 0.03; cee -1.15; ce -0.09;");
    pos.add(
        "r3k2r/2q2p2/p2bpPpp/1b1p4/1p1B1PPP/8/PPPQ4/1K1R1B1R w kq - bm f5; id \"Undermine.085\"; c0 \"f5=10, Be3=6, Bxb5+=4, h5=4\"; cem 1.53; cee 0.26; ce 1.43;");
    pos.add(
        "r3k2r/ppq2p1p/2n1p1p1/3pP3/5PP1/2P1Q3/PP2N2P/3R1RK1 b k - bm h5; id \"Undermine.086\"; c0 \"h5=10, O-O=3, Qb6=1, Rc8=1, Rc8=3\"; cem 1.62; cee 1.35; ce 1.43;");
    pos.add(
        "r3r1k1/1pp1np1p/1b1p1p2/pP2p3/2PP2b1/P3PN2/1B3PPP/R3KB1R w KQ - bm c5; id \"Undermine.087\"; c0 \"c5=10, Be2=3, Rd1=3, dxe5=3\"; cem -0.81; cee -0.54; ce -0.59;");
    pos.add(
        "r3r1k1/1pq2pbp/p1ppbnp1/4n3/2P1PB2/1NN2P2/PP1Q2PP/R3RBK1 w - - bm c5; id \"Undermine.088\"; c0 \"c5=10, Bxe5=7, Nd1=7, Red1=7\"; cem -1.26; cee -0.88; ce -1.16;");
    pos.add(
        "r3r1k1/bpp1np1p/3p1p2/pPP1p3/3P2b1/P3PN2/1B3PPP/R3KB1R w KQ - bm b6; id \"Undermine.089\"; c0 \"b6=10, Rc1=3, Rd1=2, h3=5\"; cem -0.26; cee -0.19; ce -0.13;");
    pos.add(
        "r3r1k1/pp2q3/2b1pp2/6pN/Pn1P4/6R1/1P3PP1/3QRBK1 w - - bm f4; id \"Undermine.090\"; c0 \"f4=10, Qd2=4, b3=5, f3=3\"; cem 1.36; cee 0.02; ce 1.24;");
    pos.add(
        "r4r2/1p2pbk1/1np1qppp/p7/3PP2P/P1Q2NP1/1P3PB1/2R1R1K1 w - - bm h5; id \"Undermine.091\"; c0 \"h5=10, Qc5=5, Qe3=3, b4=2\"; cem 1.41; cee 0.17; ce 1.30;");
    pos.add(
        "r4r2/2p2kb1/1p1p2p1/qPnPp2n/2B1PP2/pP6/P1Q1N2R/1KB4R w - - bm f5; id \"Undermine.092\"; c0 \"f5=10, Bd2=5, Rg1=3, Rg2=4\"; cem 1.59; cee -0.56; ce 1.63;");
    pos.add(
        "r4rk1/2p5/p2p1n2/1p1P3p/2P1p1pP/1P4B1/1P3PP1/3RR1K1 w - - bm c5; id \"Undermine.093\"; c0 \"c5=10, Rc1=5, Rd2=4, Re2=6\"; cem 1.24; cee 0.03; ce 0.43;");
    pos.add(
        "r4rk1/2qnb1pp/4p3/ppPb1p2/3Pp3/1PB3P1/R1QNPPBP/R5K1 b - - bm a4; id \"Undermine.094\"; c0 \"a4=10, Bf6=6, Qc6=6, e5=6\"; cem -0.84; cee -0.53; ce -0.93;");
    pos.add(
        "r4rk1/p5pp/1p2b3/2Pn1p2/P2Pp2P/4P1Pq/2Q1BP2/R1BR2K1 w - - bm a5; id \"Undermine.095\"; c0 \"a5=10, Bc4=5, Bf1=5, Rb1=6\"; cem 0.87; cee 1.53; ce 1.07;");
    pos.add(
        "r4rk1/pbq2p2/2p2np1/1p2b2p/4P3/2N1BPP1/PPQ1B2P/R2R2K1 b - - bm h4; id \"Undermine.096\"; c0 \"h4=10, Rfd8=2, a5=3, a6=2\"; cem 0.17; cee 0.44; ce 0.08;");
    pos.add(
        "r4rk1/pp1b2b1/n2p1nq1/2pP1p1p/2P1pP2/PP4PP/1BQ1N1B1/R3RNK1 b - - bm h4; id \"Undermine.097\"; c0 \"h4=10, Rab8=1, Rae8=1, Rf7=1\"; cem 0.62; cee 0.64; ce 0.52;");
    pos.add(
        "rn3rk1/p1p1qp2/1pbppn1p/6p1/P1PP4/2PBP1B1/3N1P1P/R2QK1R1 w Q - bm h4; id \"Undermine.098\"; c0 \"h4=10, Qe2=1, a5=1, f4=1\"; cem -0.73; cee -0.43; ce -0.62;");
    pos.add(
        "rnbq1rk1/2p1p1bp/p3pnp1/1p6/3P4/1QN1BN2/PP3PPP/R3KB1R w KQ - bm a4; id \"Undermine.099\"; c0 \"a4=10, Rc1=6, g3=6, h3=6\"; cem -0.19; cee 0.47; ce -0.09;");
    pos.add(
        "rr3n1k/q3bpn1/2p1p1p1/2PpP2p/pP1P1N1P/2BB1NP1/P2Q1P2/6RK w - - bm g4; id \"Undermine.100\"; c0 \"g4=10, Kh2=4, Qc1=2, a3=3\"; cem 0.88; cee -0.57; ce 0.87;");
    pos.add(
        "1b1r4/3rkp2/p3p2p/4q3/P5P1/2RBP3/P1Q4P/1R3K2 b - - bm Ba7; c0 \"Ba7=10, Qf6+=3, a5=3, h5=5\"; id \"STS(v2.2) Open Files and Diagonals.001\"; cem -1.87; cee 0.00; ce -1.39;");
    pos.add(
        "1bq3rk/R6p/5n1P/1N1p4/1PnP2p1/6P1/5B2/2Q2BK1 w - - bm Re7; c0 \"Re7=10, Ra2=5, Rg7=2\"; id \"STS(v2.2) Open Files and Diagonals.002\"; cem 2.44; cee 1.32; ce 2.25;");
    pos.add(
        "1k1r3r/1p1b1Q1p/p7/q3p3/4p3/2P1N3/P4PPP/R4RK1 w - - bm Rad1; c0 \"Rad1=10, Qf6=7, Rfd1=7, a4=2\"; id \"STS(v2.2) Open Files and Diagonals.003\"; cem 0.10; cee 0.61; ce 0.35;");
    pos.add(
        "1Q6/1b4pk/2q2b1p/1p1ppP2/1Pp5/2P2P1P/2BB2P1/6K1 b - - bm Qa6; c0 \"Qa6=10, Bc8=5, Qd7=7\"; id \"STS(v2.2) Open Files and Diagonals.004\"; cem 1.73; cee -0.90; ce 0.02;");
    pos.add(
        "1qrr3k/1p2bp1p/1n2p1pP/p2pP3/P4B2/1PPB2P1/2R1QP2/3R2K1 w - - bm Bb5; c0 \"Bb5=10, Qe1=2, Qe3=2\"; id \"STS(v2.2) Open Files and Diagonals.005\"; cem 1.49; cee 0.66; ce 1.45;");
    pos.add(
        "1r1n1rk1/3qp2p/P2p2p1/1p6/5pP1/1p3P1P/5PB1/R1QR2K1 w - - bm Bf1; c0 \"Bf1=10, Qb2=7, Qc3=7, Qd2=6\"; id \"STS(v2.2) Open Files and Diagonals.006\"; cem -1.17; cee -2.55; ce -1.50;");
    pos.add(
        "1r1n2k1/5r1p/P2qp1p1/3p4/1p3pP1/1Q3P1P/R4P2/2R2BK1 w - - bm Rac2; c0 \"Rac2=10, Kg2=5, Qa4=4\"; id \"STS(v2.2) Open Files and Diagonals.007\"; cem 0.15; cee -0.59; ce 0.02;");
    pos.add(
        "1r1q1nk1/p3bpp1/b1p1p2p/4P3/1P2NB2/P4N2/5PPP/2Q1R1K1 w - - bm Be3; c0 \"Be3=10, Nd6=5, Rd1=7, h3=6\"; id \"STS(v2.2) Open Files and Diagonals.008\"; cem 0.57; cee 0.00; ce 0.52;");
    pos.add(
        "1r1q4/5p2/2p4k/1n2p1pP/4P3/P4BR1/3Q1PKP/8 w - - bm Qc1; c0 \"Qc1=10, Qa2=5, Qe3=6\"; id \"STS(v2.2) Open Files and Diagonals.009\"; cem 3.54; cee 0.31; ce 1.92;");
    pos.add(
        "1r1qbr1k/4bp1p/p3p2Q/3pP3/2pP4/P1N1PN2/1PR2RP1/6K1 b - - bm Rg8; c0 \"Rg8=10, Bc6=1, Bd7=4\"; id \"STS(v2.2) Open Files and Diagonals.010\"; cem 1.18; cee 0.89; ce 1.03;");
    pos.add(
        "1r3k2/8/R7/4pPP1/P1p5/1nP5/R5P1/3rB1K1 w - - bm Rh6; c0 \"Rh6=10, Kf1=6, Rf6+=6\"; id \"STS(v2.2) Open Files and Diagonals.011\"; cem 4.49; cee 4.38; ce 4.50;");
    pos.add(
        "1r3rk1/2b3pp/p4p2/2p5/P1Np4/1P1R2P1/1P3P1P/2R3K1 b - - bm Rfe8; c0 \"Rfe8=10, Rfc8=7, Rfd8=8\"; id \"STS(v2.2) Open Files and Diagonals.012\"; cem -0.31; cee -0.45; ce -0.51;");
    pos.add(
        "1r5k/p7/3pQ1p1/1Np5/2P3P1/7P/1PK5/7q b - - bm Qf1; c0 \"Qf1=10, Qg2+=2, Qh2+=2\"; id \"STS(v2.2) Open Files and Diagonals.013\"; cem -1.80; cee -0.89; ce -1.24;");
    pos.add(
        "1r6/4q3/2p2p1k/4p1pP/P2nP2P/5BR1/5PK1/2Q5 w - - bm Bg4; c0 \"Bg4=10, Bd1=6, a5=8\"; id \"STS(v2.2) Open Files and Diagonals.014\"; cem 2.01; cee -0.83; ce 0.60;");
    pos.add(
        "1r6/7k/1p4r1/1P2p3/2P1p2P/2RbB3/6P1/2R4K w - - bm Ra1; c0 \"Ra1=10, Kg1=7, Kh2=8\"; id \"STS(v2.2) Open Files and Diagonals.015\"; cem 1.90; cee 1.86; ce 1.62;");
    pos.add(
        "1r6/8/1r5p/p2pk3/4p3/2P4P/RP2B1n1/1RK5 b - - bm Rf6; c0 \"Rf6=10, Rf8=8, Rg6=6\"; id \"STS(v2.2) Open Files and Diagonals.016\"; cem -1.06; cee -2.76; ce -2.43;");
    pos.add(
        "1rbr2k1/pp3pp1/q6p/4P3/3p1P2/6P1/P2QP1BP/R1R3K1 w - - bm Be4; c0 \"Be4=10, Bf3=4, Qd3=5\"; id \"STS(v2.2) Open Files and Diagonals.017\"; cem 0.60; cee 0.96; ce 0.80;");
    pos.add(
        "2b1r1k1/2r5/p3pbp1/5p2/PN5P/1Pp2BP1/4PP2/2R2RK1 w - - bm Rfd1; c0 \"Rfd1=10\"; id \"STS(v2.2) Open Files and Diagonals.018\"; cem -0.25; cee -0.01; ce -0.00;");
    pos.add(
        "2r1k3/p5pp/1p2p3/7P/P2B2Q1/1bP5/R4PPK/4q3 w - - bm Re2; c0 \"Re2=10, Qxg7=4, Rb2=4\"; id \"STS(v2.2) Open Files and Diagonals.019\"; cem -0.25; cee -1.27; ce -0.55;");
    pos.add(
        "2r1q1k1/2p2rbp/p2p2p1/Q2P4/b3P3/3p2P1/3B1PBP/2R1R1K1 w - - bm Bh3; c0 \"Bh3=10, Bf1=7, e5=8\"; id \"STS(v2.2) Open Files and Diagonals.020\"; cem 0.35; cee -0.51; ce 0.31;");
    pos.add(
        "2r1q2k/7p/p1np1P1P/8/1pP2R2/8/PP1Q4/R1KN2r1 b - - bm Qg6; c0 \"Qg6=10, Ne5=4, Qe5=2\"; id \"STS(v2.2) Open Files and Diagonals.021\"; cem 1.05; cee 1.24; ce 1.01;");
    pos.add(
        "2r1q3/p1p1nk1p/4p2p/2R1Pp2/1P1Q4/P3N1P1/5P2/6K1 w - - bm Qh4; c0 \"Qh4=10, Rc1=6, Rc2=9, Rc4=6\"; id \"STS(v2.2) Open Files and Diagonals.022\"; cem 0.55; cee 0.03; ce 0.37;");
    pos.add(
        "2r2k2/R4bpp/2p2p2/1pN5/1n3PP1/1P2P2P/8/5BK1 w - - bm Bg2; c0 \"Bg2=10, e4=6\"; id \"STS(v2.2) Open Files and Diagonals.023\"; cem 1.76; cee 0.68; ce 0.95;");
    pos.add(
        "2r2rk1/1p1nbppp/p2p4/B2Pp1PP/5P2/1P6/P1P5/1K1R1B1R w - - bm Bh3; c0 \"Bh3=10, Bd2=5, Re1=6\"; id \"STS(v2.2) Open Files and Diagonals.024\"; cem 0.25; cee -0.74; ce -0.25;");
    pos.add(
        "2r2rk1/4q1pp/p7/1pb1Pp2/5P2/1P1QB3/b3N1PP/2R1K2R b K - bm Rfd8; c0 \"Rfd8=10, Bxe3=5, Kh8=3\"; id \"STS(v2.2) Open Files and Diagonals.025\"; cem -2.13; cee 0.25; ce -1.86;");
    pos.add(
        "2r3k1/1b1n1npp/1pq2p2/8/1P1QP3/6P1/1B1NBP1P/3R2K1 w - - bm Rc1; c0 \"Rc1=10, Bg4=7, f3=7, f4=5\"; id \"STS(v2.2) Open Files and Diagonals.026\"; cem 1.84; cee 1.62; ce 1.88;");
    pos.add(
        "2r3n1/p1p3kp/1q2p2p/4Pp2/1P4N1/P5P1/1Q3P2/2R3K1 w - - bm Qd2; c0 \"Qd2=10, Ne3=9, Nf6=5\"; id \"STS(v2.2) Open Files and Diagonals.027\"; cem 0.64; cee 0.00; ce 0.39;");
    pos.add(
        "2rq2k1/1p3pb1/1n4pp/pP2p3/P1b1P3/2N4P/2B1NPP1/R1Q3K1 b - - bm Bf8; c0 \"Bg7f8=10, Bg7f6=8, Qd8d6=7\"; id \"STS(v2.2) Open Files and Diagonals.028\"; cem -0.63; cee -0.64; ce -0.73;");
    pos.add(
        "2rq2k1/4rpp1/p3p3/P2n2pP/2pPR3/2P3B1/2Q2PP1/R5K1 b - - bm Rb7; c0 \"Rb7=10, Ra7=4, Rd7=3\"; id \"STS(v2.2) Open Files and Diagonals.029\"; cem 0.09; cee -0.38; ce -0.14;");
    pos.add(
        "2rr1b2/3q3k/p4n1p/1p1p2p1/2nNp3/P1N2PQ1/1PP3PP/R1BR2K1 b - - bm Bd6; c0 \"Bd6=10, Bc5=7, Bg7=5\"; id \"STS(v2.2) Open Files and Diagonals.030\"; cem -0.61; cee -1.70; ce -0.73;");
    pos.add(
        "3b2k1/1p1b4/4p2B/1n1p1p2/pN6/P2P4/1PR3PP/7K w - - bm Be3; c0 \"Be3=10, Bf4=7, Re2=7\"; id \"STS(v2.2) Open Files and Diagonals.031\"; cem 2.10; cee 1.66; ce 1.81;");
    pos.add(
        "3q2k1/5rpp/r1pPp3/1bQn4/p2B4/4P1P1/1P1R2BP/R5K1 w - - bm Bh3; c0 \"Bh3=10, Rc1=8, Rc2=7\"; id \"STS(v2.2) Open Files and Diagonals.032\"; cem 1.77; cee 1.88; ce 1.88;");
    pos.add(
        "3Q4/5pk1/4p1p1/3pb2p/P3q2P/1r4P1/2R2P1K/2R5 b - - bm Bf4; c0 \"Bf4=10, Ra3=3, Rd3=4\"; id \"STS(v2.2) Open Files and Diagonals.033\"; cem -0.98; cee 0.47; ce -0.36;");
    pos.add(
        "3q4/7k/3ppp2/p3n2B/P1r1P2P/8/1PPQ4/1K3R2 w - - bm Rg1; c0 \"Rg1=10, Qe3=7, Qg2=5\"; id \"STS(v2.2) Open Files and Diagonals.034\"; cem 2.35; cee 0.63; ce 1.53;");
    pos.add(
        "3r1k2/R4bpp/2p2p2/1pN5/1n3PP1/1P2P2P/6B1/6K1 w - - bm Be4; c0 \"Be4=10, Bf3=2, g5=3\"; id \"STS(v2.2) Open Files and Diagonals.035\"; cem 1.18; cee 0.50; ce 0.71;");
    pos.add(
        "3r1r2/p5k1/1pnpP2p/6p1/2P3P1/4P3/P1B1K1P1/3R3R w - - bm Ba4; c0 \"Ba4=10, Rdf1=6, Rhf1=7\"; id \"STS(v2.2) Open Files and Diagonals.036\"; cem 1.16; cee 2.46; ce 2.23;");
    pos.add(
        "3r2k1/4q1pp/p7/1p2Pp2/5P2/1P2Q3/b5PP/2N1K2R b K - bm Bb1; c0 \"Bb1=10\"; id \"STS(v2.2) Open Files and Diagonals.037\"; cem -2.00; cee 0.17; ce -0.94;");
    pos.add(
        "3r2k1/pp1q1ppp/4rnn1/3p4/P1pP4/2P1P2P/1BQN1PP1/RR4K1 w - - bm Ba3; c0 \"Ba3=10, Qd1=4, Qf5=4\"; id \"STS(v2.2) Open Files and Diagonals.038\"; cem -0.24; cee -0.50; ce -0.18;");
    pos.add(
        "3r2k1/R7/1p3np1/1P1b4/1p6/5PqP/2Q3P1/3R2K1 b - - bm Re8; c0 \"Re8=10, Rf8=4\"; id \"STS(v2.2) Open Files and Diagonals.039\"; cem -1.32; cee -1.19; ce -1.34;");
    pos.add(
        "3r3k/1p2R2p/pP4p1/8/2rp1p2/5P2/2P3PP/4R1K1 w - - bm Rc7; c0 \"Rc7=10\"; id \"STS(v2.2) Open Files and Diagonals.040\"; cem 0.33; cee -0.12; ce 0.03;");
    pos.add(
        "3r4/3pkpp1/4p3/2p3q1/6r1/1P6/P5B1/2R1RQK1 b - - bm Rh8; c0 \"Rh8=10, Ra8=1, f5=1\"; id \"STS(v2.2) Open Files and Diagonals.041\"; cem 0.93; cee 0.18; ce 0.54;");
    pos.add(
        "3r4/3q2bk/3rp1p1/1p5p/4QP2/R1Pp2P1/P2B1R1P/6K1 b - - bm Rc8; c0 \"Rc8=10, Rc6=6, Re8=6\"; id \"STS(v2.2) Open Files and Diagonals.042\"; cem -0.59; cee -1.20; ce -0.87;");
    pos.add(
        "3r4/p2rppk1/2R3p1/4q3/3b4/PP4P1/2QRNP2/5K2 b - - bm Qh5; c0 \"Qh5=10, Bb6=2, Qd5=2\"; id \"STS(v2.2) Open Files and Diagonals.043\"; cem 0.86; cee 0.00; ce 0.50;");
    pos.add(
        "3rr1k1/1p2bppp/p1p5/P2P3q/2B1PPb1/1P1R4/3B1QP1/4R1K1 w - - bm Bc3; c0 \"Bc3=10, b4=8, f5=4\"; id \"STS(v2.2) Open Files and Diagonals.044\"; cem -1.09; cee 0.31; ce -0.77;");
    pos.add(
        "3rr1k1/pp2q1bp/n1p1P1p1/5p2/P2N1Pb1/1BP5/6PP/R1B1QRK1 w - - bm Ba3; c0 \"Ba3=10, Ra2=5, h3=5\"; id \"STS(v2.2) Open Files and Diagonals.045\"; cem -0.33; cee -0.24; ce -0.23;");
    pos.add(
        "4b2k/1q2bprp/pr6/3pP3/2pP4/P4N2/1P3RPQ/3N1RK1 b - - bm Bd7; c0 \"Bd7=10, Bd8=8, Rg8=1\"; id \"STS(v2.2) Open Files and Diagonals.046\"; cem -0.82; cee -0.74; ce -0.90;");
    pos.add(
        "4k2r/p2n3p/4q1pQ/1p2b2P/8/P1P2R2/1P4P1/K4R2 w - - bm Rd1; c0 \"Rd1=10, Re1=9, Re3=5\"; id \"STS(v2.2) Open Files and Diagonals.047\"; cem 1.22; cee -0.97; ce 0.39;");
    pos.add(
        "4qrk1/pb4p1/2p1p2p/PpP1Pr2/1P2QP1R/2B3P1/6R1/6K1 w - - bm Rd2; c0 \"Rd2=10, Be1=9, Ra2=9\"; id \"STS(v2.2) Open Files and Diagonals.048\"; cem -0.01; cee 0.28; ce 0.15;");
    pos.add(
        "4r1k1/3q1ppb/2p5/2Pp1P1p/p2P3P/P3P1PB/5Q1K/4R3 w - - bm Rb1; c0 \"Rb1=10, Qf4=5, Re2=2, Re2=5\"; id \"STS(v2.2) Open Files and Diagonals.049\"; cem 1.58; cee 1.82; ce 1.80;");
    pos.add(
        "4r1k1/pbq2rpp/1p2p3/4P2P/P1p4Q/1nP1B3/R1B2PP1/1R4K1 w - - bm Bg6; c0 \"Bg6=10, Qg5=1, Rd1=3, a5=4\"; id \"STS(v2.2) Open Files and Diagonals.050\"; cem 1.39; cee 0.39; ce 1.33;");
    pos.add(
        "4r1k1/R5p1/4b2p/1p2P3/7P/P1nP4/6PK/4R3 w - - bm Rc1; c0 \"Rc1=10, Ra6=7, d4=6\"; id \"STS(v2.2) Open Files and Diagonals.051\"; cem -1.74; cee 0.54; ce 0.33;");
    pos.add(
        "4r2k/1pp1n1pp/pb3r2/6Nq/P2P4/2PQ2P1/1R3RKP/2B5 w - - bm Rbe2; c0 \"Rbe2=10, Rf3=4, Rxf6=3, a5=3, h3=4, h4=3\"; id \"STS(v2.2) Open Files and Diagonals.052\"; cem -0.48; cee 0.23; ce -0.26;");
    pos.add(
        "4rrk1/1b3pp1/1q3b1p/p2p1P2/3N4/2P3N1/1P3QPP/1R3R1K b - - bm Ba6; c0 \"Ba6=10\"; id \"STS(v2.2) Open Files and Diagonals.053\"; cem -0.50; cee -0.21; ce -0.55;");
    pos.add(
        "5b2/1p1q3n/pB1p2k1/P4N1p/6pP/4R3/6P1/5RK1 w - - bm Bd4; c0 \"Bd4=10\"; id \"STS(v2.2) Open Files and Diagonals.054\"; cem 0.30; cee -1.00; ce -0.40;");
    pos.add(
        "5k2/ppQbbp1p/8/5p2/P7/1P6/4KPPP/q4B1R b - - bm Bb4; c0 \"Bb4=10, Qa2+=2, Qb2+=2\"; id \"STS(v2.2) Open Files and Diagonals.055\"; cem -1.88; cee 1.42; ce -0.10;");
    pos.add(
        "5r1k/6p1/p1n1pr1p/2P1p2q/P5RN/4Q2P/5PP1/2R4K w - - bm Rb1; c0 \"Rb1=10, Qd3=6, Rd1=7\"; id \"STS(v2.2) Open Files and Diagonals.056\"; cem 0.30; cee 0.51; ce 0.46;");
    pos.add(
        "5rk1/p3qpb1/1p5p/5Q2/P7/1B2P3/1p3PPP/5RK1 b - - bm Rd8; c0 \"Rd8=10, Qb4=6, Qe5=5\"; id \"STS(v2.2) Open Files and Diagonals.057\"; cem -0.61; cee -2.20; ce -1.29;");
    pos.add(
        "5rk1/p7/1n1q1p2/1Prp1pNp/8/5NPP/P2Q1PB1/5RK1 w - - bm Re1; c0 \"Re1=10, Qe3=4, a4=3\"; id \"STS(v2.2) Open Files and Diagonals.058\"; cem 2.14; cee 1.57; ce 2.07;");
    pos.add(
        "5rk1/pp4p1/5rnp/3p1b2/2p2P2/P1P3P1/2P1B2P/R1B1R1K1 w - - bm Bf3; c0 \"Bf3=10, Bd1=2, Be3=7\"; id \"STS(v2.2) Open Files and Diagonals.059\"; cem -0.77; cee -1.18; ce -0.92;");
    pos.add(
        "5rrk/pR4p1/3p2q1/P6p/2Q1pn2/7P/1PP2PP1/R3N1K1 w - - bm Ra3; c0 \"Ra3=10, Kh2=6, Rb3=4\"; id \"STS(v2.2) Open Files and Diagonals.060\"; cem 0.39; cee 1.40; ce 0.80;");
    pos.add(
        "6k1/1b3p2/p2q2p1/P2Pp2p/1p2Pb1P/1Br5/Q4PP1/3RN1K1 b - - bm Bc8; c0 \"Bc8=10\"; id \"STS(v2.2) Open Files and Diagonals.061\"; cem -0.82; cee -0.97; ce -0.97;");
    pos.add(
        "6k1/1q3rpp/5n2/Rp2r3/4p3/1B5P/3BQPP1/6K1 w - - bm Be3; c0 \"Be3=10, Bc3=5, g3=5\"; id \"STS(v2.2) Open Files and Diagonals.062\"; cem -0.09; cee -0.61; ce -0.17;");
    pos.add(
        "6k1/1q5p/5np1/pb1pNp2/3P1B1P/1Q6/1P2PP2/6K1 w - - bm Qa3; c0 \"Qa3=10, Qa2=6, Qc3=7\"; id \"STS(v2.2) Open Files and Diagonals.063\"; cem 0.08; cee 0.00; ce 0.12;");
    pos.add(
        "6k1/2q4p/r3b1p1/2P1p3/r7/4QP2/p1RN2PP/R5K1 b - - bm Rb4; c0 \"Rb4=10, R6a5=8, Ra8=8\"; id \"STS(v2.2) Open Files and Diagonals.064\"; cem -0.56; cee -1.92; ce -1.07;");
    pos.add(
        "6k1/pb4p1/1p1b2q1/1P1p3p/3Pn3/P1r2N1P/4QPB1/2B1R1K1 b - - bm Bc8; c0 \"Bc8=10, Bb8=5, Rc4=7\"; id \"STS(v2.2) Open Files and Diagonals.065\"; cem -5.04; cee -2.59; ce -4.54;");
    pos.add(
        "6k1/q4p2/2b1p2p/4Pp2/pP3N2/Q4PP1/6KP/8 b - - bm Qd4; c0 \"Qd4=10, Bb5=9, Qb8=6\"; id \"STS(v2.2) Open Files and Diagonals.066\"; cem -1.58; cee 0.07; ce -0.42;");
    pos.add(
        "6r1/1R6/1bp1knp1/pp1n3p/4pP1P/1PN3B1/1P3PB1/5K2 w - - bm Bh3+; c0 \"Bh3+=10, Nxd5=6, Nxe4=6\"; id \"STS(v2.2) Open Files and Diagonals.067\"; cem 0.10; cee -2.10; ce -1.33;");
    pos.add(
        "6r1/4bbk1/p3p1p1/Pp1pPp2/3P1P2/2P2B2/3B2K1/1R6 b - - bm Rh8; c0 \"Rh8=10, Bd8=7, Be8=7, g5=7\"; id \"STS(v2.2) Open Files and Diagonals.068\"; cem -0.61; cee -0.72; ce -0.79;");
    pos.add(
        "6rk/1n4bp/p3R2r/1p1P1Pp1/1P6/P1pB2P1/5P2/2R3K1 b - - bm Rc8; c0 \"Rc8=10\"; id \"STS(v2.2) Open Files and Diagonals.069\"; cem -1.04; cee -1.68; ce -1.32;");
    pos.add(
        "8/2b1rk2/2b1p1p1/5n2/p1pP2Q1/2p3P1/P4P2/2B1RK2 w - - bm Bg5; c0 \"Bg5=10, Ba3=5, Bf4=4\"; id \"STS(v2.2) Open Files and Diagonals.070\"; cem 2.23; cee 1.76; ce 2.03;");
    pos.add(
        "8/3q1pk1/3p4/2pB2p1/P2n4/1P4BP/6P1/4R1K1 b - - bm Qf5; c0 \"Qf5=10, Kh6=3, g4=5\"; id \"STS(v2.2) Open Files and Diagonals.071\"; cem -0.96; cee -1.22; ce -1.26;");
    pos.add(
        "8/3r1k2/2p3p1/1pPb1p2/p7/P1B2P2/4BK2/3R4 w - - bm Rh1; c0 \"Rh1=10, Bd3=5, Rd4=6\"; id \"STS(v2.2) Open Files and Diagonals.072\"; cem 2.45; cee 2.44; ce 2.43;");
    pos.add(
        "8/5r1k/p3b1pp/4p1q1/P3Pn2/2B1N2P/3R1PPK/3Q4 w - - bm Qb1; c0 \"Qb1=10, Ba1=5, Bb2=5\"; id \"STS(v2.2) Open Files and Diagonals.073\"; cem 1.49; cee 1.30; ce 1.39;");
    pos.add(
        "8/5r2/1pp2p1R/p3n3/4Pp1p/2P2P1k/4K2P/R7 b - - bm Rg7; c0 \"Rg7=10, b5=3\"; id \"STS(v2.2) Open Files and Diagonals.074\"; cem 0.24; cee -0.40; ce -0.46;");
    pos.add(
        "8/p1r3kp/r3p1p1/PRp1n1P1/7R/8/4K2P/7B b - - bm Rd6; c0 \"Rd6=10, Nd7=5, Nf7=7\"; id \"STS(v2.2) Open Files and Diagonals.075\"; cem -2.45; cee -1.24; ce -1.63;");
    pos.add(
        "8/p1rb3k/1p1b1p1p/3q1p2/1P1P4/P1n1PPP1/3N3P/R1R2Q1K b - - bm Bb5; c0 \"Bb5=10, Kg6=5, Kh8=3, h5=3\"; id \"STS(v2.2) Open Files and Diagonals.076\"; cem -1.53; cee 0.10; ce -1.17;");
    pos.add(
        "b1r3k1/3n1pb1/p2q2p1/P2Pp2p/1p2P2P/1Rr1NN2/Q1B2PP1/3R2K1 b - - bm Bh6; c0 \"Bh6=10\"; id \"STS(v2.2) Open Files and Diagonals.077\"; cem 0.02; cee -1.29; ce -0.11;");
    pos.add(
        "b2r4/3qk3/1p1pp3/pN4b1/P3Pp1p/2P5/4BQPP/5R1K w - - bm Bg4; c0 \"Bg4=10, Rd1=1\"; id \"STS(v2.2) Open Files and Diagonals.078\"; cem 0.15; cee -1.67; ce -0.47;");
    pos.add(
        "q1b2k2/5ppp/2n5/P1N1n3/8/2PpB1P1/3N1P1P/R4RK1 w - - bm Rfb1; c0 \"Rfb1=10, Rfe1=7, a6=7\"; id \"STS(v2.2) Open Files and Diagonals.079\"; cem 0.78; cee 2.05; ce 1.27;");
    pos.add(
        "r1b2r1k/1p2qpb1/1np3pp/p7/3P4/PBN2N2/1PQ2PPP/3R1RK1 w - - bm Rfe1; c0 \"Rfe1=10, Qc1=7, Qd2=6, h3=7\"; id \"STS(v2.2) Open Files and Diagonals.080\"; cem -0.32; cee 0.07; ce -0.22;");
    pos.add(
        "r1b2rk1/4bpp1/7p/1B1pP3/3B4/8/1PP3PP/2KR3R b - - bm Bg4; c0 \"Bg4=10, Bf5=9, Ra5=8\"; id \"STS(v2.2) Open Files and Diagonals.081\"; cem 0.41; cee 1.83; ce 1.17;");
    pos.add(
        "r1b2rk1/pp3pb1/1npN1qpp/2P5/1PBp4/P4NPP/5P2/2RQ1RK1 w - - bm Re1; c0 \"Re1=10, Nxc8=7, Qd3=7\"; id \"STS(v2.2) Open Files and Diagonals.082\"; cem -0.25; cee 0.60; ce -0.13;");
    pos.add(
        "r1b3k1/4brp1/p6p/1p1p4/3B4/8/PPP3PP/2KR1B1R w - - bm Bd3; c0 \"Bd3=10, Be3=4, Kb1=2\"; id \"STS(v2.2) Open Files and Diagonals.083\"; cem -0.25; cee 0.05; ce 0.03;");
    pos.add(
        "r1b3k1/pp2qrpp/1n6/1Bb1Pp2/3p1P2/7Q/PP1BN1PP/R3K2R b KQ - bm Be6; c0 \"Be6=10, a6=2\"; id \"STS(v2.2) Open Files and Diagonals.084\"; cem -0.43; cee 1.29; ce -0.50;");
    pos.add(
        "r1b3kr/p3qpp1/1pn1p2p/3pP2P/7n/3B3Q/2P2PPN/1RB1R1K1 w - - bm Ba3; c0 \"Ba3=10, Qg3=4\"; id \"STS(v2.2) Open Files and Diagonals.085\"; cem -0.72; cee -1.28; ce -0.63;");
    pos.add(
        "r1br2k1/p4p2/1qp4p/1p2P1p1/6n1/5NP1/PP2QN1P/R1B2BK1 b - - bm Be6; c0 \"Be6=10, Bf5=3, b4=4\"; id \"STS(v2.2) Open Files and Diagonals.086\"; cem 0.72; cee 0.27; ce 0.56;");
    pos.add(
        "r3r1k1/1b3ppp/2p2n2/1p2b1B1/4P1B1/P6P/1PQ1NPP1/6K1 w - - bm Bf5; c0 \"Bf5=10, Bf3=3, Bxf6=3, f3=2\"; id \"STS(v2.2) Open Files and Diagonals.087\"; cem 0.86; cee -0.07; ce 0.52;");
    pos.add(
        "r3r1k1/2q2pp1/1pp2np1/4p3/nP2P1P1/P3Q2P/3R1PB1/B1R3K1 w - - bm Bf1; c0 \"Bf1=10, Bf3=5, Rdc2=6\"; id \"STS(v2.2) Open Files and Diagonals.088\"; cem 0.77; cee 0.74; ce 0.86;");
    pos.add(
        "r3r1k1/ppn2ppp/2p5/5P2/P7/2N4P/1PP5/R1B2RK1 w - - bm Bf4; c0 \"Bf4=10, Kg2=7, Rd1=7, f6=6\"; id \"STS(v2.2) Open Files and Diagonals.089\"; cem 2.53; cee 2.69; ce 2.73;");
    pos.add(
        "r3r2k/2R4p/3B2b1/p7/1p2p3/6PP/PPP4K/4R3 w - - bm Bc5; c0 \"Bc5=10, c3=7, c4=7\"; id \"STS(v2.2) Open Files and Diagonals.090\"; cem 1.12; cee 1.20; ce 1.08;");
    pos.add(
        "r4qk1/1p3rbp/3p1np1/2pPp3/b1P5/P1NBBPP1/3Q1R1P/4R1K1 w - - bm Rb1; c0 \"Rb1=10, Kg2=3, Qc1=3, Ree2=3\"; id \"STS(v2.0) Open Files and Diagonals.091\"; cem 1.46; cee 1.24; ce 1.55;");
    pos.add(
        "r4rk1/1p2pp1p/3p1np1/q2P1P2/2P3BQ/pP5R/P1R3PP/7K w - - bm Re2; c0 \"Re2=10, Be2=2, fxg6=4\"; id \"STS(v2.2) Open Files and Diagonals.092\"; cem -0.57; cee -1.36; ce -0.71;");
    pos.add(
        "r4rk1/1p2ppbp/1q1p2p1/p1nP1P2/2P3B1/5R2/PPRB2PP/1Q5K w - - bm Qe1; c0 \"Qe1=10, Bg5=4, Rh3=5\"; id \"STS(v2.2) Open Files and Diagonals.093\"; cem 0.09; cee -1.30; ce -0.03;");
    pos.add(
        "r4rk1/5pb1/2p3pp/pp2n3/1n3B2/1PN1PP1P/1P2BPK1/2R3R1 w - - bm Rgd1; c0 \"Rgd1=10\"; id \"STS(v2.2) Open Files and Diagonals.094\"; cem -0.55; cee -0.23; ce -0.30;");
    pos.add(
        "r4rk1/6b1/2p1N1p1/8/p2p3R/8/R4P1P/5K2 b - - bm Rfb8; c0 \"Rfb8=10, Rf6=6, c5=8\"; id \"STS(v2.2) Open Files and Diagonals.095\"; cem -1.88; cee -2.34; ce -2.32;");
    pos.add(
        "r4rk1/ppp1qp1p/1n4p1/4P3/6Q1/1N6/PP3PPP/2R1R1K1 b - - bm Rad8; c0 \"Rad8=10, Nd5=5, Rfd8=6\"; id \"STS(v2.2) Open Files and Diagonals.096\"; cem 0.90; cee 0.69; ce 0.74;");
    pos.add(
        "r5k1/p1q2pbp/Ppp1bnp1/4p1B1/Q1P1P3/2N4P/1P2BPP1/5RK1 w - - bm Rd1; c0 \"Rd1=10, Be3=1, Rc1=8, b4=1\"; id \"STS(v2.2) Open Files and Diagonals.097\"; cem 0.79; cee 0.48; ce 0.81;");
    pos.add(
        "r5k1/p4nbp/2qN2p1/2P2p2/3p1B2/6P1/P2Q1P1P/3R2K1 w - - bm Qe2; c0 \"Qe2=10, Nxf7=8, Qd3=7\"; id \"STS(v2.2) Open Files and Diagonals.098\"; cem -0.27; cee 0.00; ce -0.07;");
    pos.add(
        "r7/3b1pk1/6p1/3Pp2p/2P4P/p4B2/5PP1/2R3K1 b - - bm Bf5; c0 \"Bf5=10, Kf6=6, Kf8=5\"; id \"STS(v2.2) Open Files and Diagonals.099\"; cem -0.11; cee -1.73; ce -1.79;");
    pos.add(
        "rbbrqnk1/pp3pp1/2p2n1p/5N2/3P1P2/1BN4P/PPQB2P1/R4RK1 w - - bm Rae1; c0 \"Rae1=10, Rfe1=5\"; id \"STS(v2.2) Open Files and Diagonals.100\"; cem 0.81; cee 0.67; ce 0.90;");
    pos.add(
        "1k2r2r/1bq2p2/pn4p1/3pP3/pbpN1P1p/4QN1B/1P4PP/2RR3K b - - bm Nd7; c0 \"Nd7=10, Bc5=8, Bc6=2, Be7=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.001\"; cem -0.74; cee -2.39; ce -0.87;");
    pos.add(
        "1q2bn2/6pk/2p1pr1p/2Q2p1P/1PP5/5N2/5PP1/4RBK1 w - - bm Ne5; c0 \"Ne5=10, Nd4=8, Ra1=6, b5=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.002\"; cem 0.94; cee 0.94; ce 1.04;");
    pos.add(
        "1r1q1rk1/1b1n1p1p/p2b1np1/3pN3/3P1P2/P1N5/3BB1PP/1R1Q1RK1 b - - bm Ne4; c0 \"Ne4=10, Bxa3=6, Nb6=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.003\"; cem 0.43; cee 0.72; ce 0.33;");
    pos.add(
        "1r1r1bk1/1bq2p1p/pn2p1p1/2p1P3/5P2/P1NBB3/1P3QPP/R2R2K1 b - - bm Nd5; c0 \"Nd5=10, Ba8=8, Kg7=8, a5=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.004\"; cem -0.21; cee 0.26; ce -0.29;");
    pos.add(
        "1r1r2k1/5pp1/p2p4/1p2pnqp/1BP1Q3/PP1R2P1/5P1P/3R2K1 b - - bm Nd4; c0 \"Nd4=10, Qf6=5, bxc4=3, h4=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.005\"; cem -0.38; cee -0.09; ce -0.38;");
    pos.add(
        "1r1r4/R3pk2/4n1p1/2p2p2/8/4B3/Pn2BPPP/5RK1 b - - bm Nd4; c0 \"Nd4=10, Nd3=1, c4=4, f4=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.006\"; cem 0.57; cee -0.12; ce 0.05;");
    pos.add(
        "1r2k2r/pp2ppb1/2n2np1/7p/4P3/P3BB1P/1P1N1PP1/R2R2K1 b k - bm Nd7; c0 \"Nd7=10, Bh6=6, a6=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.007\"; cem 1.69; cee 0.80; ce 1.17;");
    pos.add(
        "1r2qrk1/3bn3/pp1p3p/n1p1p1p1/P1P5/B1PP1NPP/2Q2PB1/1R2R1K1 w - - bm Nd2; c0 \"Nd2=10, Bc1=6, Qe2=9, Rb2=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.008\"; cem -0.06; cee -1.20; ce 0.01;");
    pos.add(
        "1r2r2k/2b2q1p/p4p2/3Pn2P/3N1N2/1P2R3/4Q3/1K1R4 w - - bm Nfe6; c0 \"Nfe6=10, Ka1=4, Nf5=6, Qxa6=2, Rc3=3, h6=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.009\"; cem 0.84; cee 0.67; ce 0.90;");
    pos.add(
        "1r3rk1/8/3p3p/p1qP2p1/R1b1P3/2Np1P2/1P1Q1RP1/6K1 w - - bm Nd1; c0 \"Nd1=10, Na2=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.010\"; cem 0.14; cee -1.46; ce -0.25;");
    pos.add(
        "1r6/1q2b1k1/pn1pb3/B1p1p1pp/2P1Pp2/NP3P1P/1R2Q1PN/6K1 b - - bm Nc8; c0 \"Nc8=10, Bg8=8, Kg8=8, Nd7=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.011\"; cem -1.83; cee -2.11; ce -2.00;");
    pos.add(
        "1r6/2qnrpk1/2pp1np1/pp2P3/4P3/PBN2Q2/1PPR1PP1/3R2K1 b - - bm Nxe5; c0 \"Nxe5=10, Nxe4=8, Rxe5=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.012\"; cem 0.68; cee -0.53; ce 0.38;");
    pos.add(
        "1rr2qk1/3p1pp1/1pb2n1p/4p3/p1P1P2P/P1NQ1BP1/1P3PK1/2RR4 w - - bm Nb5; c0 \"Nb5=10, Kh2=5, Rc2=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.013\"; cem 0.58; cee 1.81; ce 0.88;");
    pos.add(
        "2b2rk1/1r1nbppp/4p3/1p2P3/p4P2/P1N1B3/BPP4P/R2R2K1 w - - bm Ne4; c0 \"Ne4=10, Rac1=1, Rd2=3, b4=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.014\"; cem -0.36; cee 0.31; ce 0.05;");
    pos.add(
        "2b3n1/6kp/p1nB1pp1/8/1p2P1P1/4NP2/PP3K2/3B4 w - - bm Nd5; c0 \"Nd5=10, Ba4=8, Kg3=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.015\"; cem 1.52; cee 2.00; ce 2.06;");
    pos.add(
        "2b5/2p1r2k/1pP2q1p/p2Pp3/4R3/1PN1Q2P/P2KP3/8 w - - bm Nb5; c0 \"Nb5=10, Kc1=7, Rc4=8, h4=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.016\"; cem 0.86; cee 2.78; ce 1.98;");
    pos.add(
        "2k3r1/1b2bp2/2p2n2/ppn1p1Bp/2p1P2P/P4B2/1P1RN1P1/4K2R b K - bm Nd3+; c0 \"Nd3+=10, Kc7=9, Ne6=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.017\"; cem -0.51; cee -0.18; ce -0.43;");
    pos.add(
        "2r2r1k/p3ppbp/qpnp2pn/5P2/2P1PP2/P1N1BB2/1PQ3RP/3R2K1 w - - bm Nb5; c0 \"Nb5=10, Be2=8, Nd5=9, fxg6=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.018\"; cem 1.00; cee 1.03; ce 1.10;");
    pos.add(
        "2r2rk1/4bpp1/p2pbn1p/Pp2p3/1Pq1P2N/2P4P/1BB2PP1/R2QR1K1 w - - bm Nf5; c0 \"Nf5=10, Bb1=5, Qe2=5, Qf3=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.019\"; cem -0.55; cee -0.92; ce -0.46;");
    pos.add(
        "2r3k1/3q1pp1/ppr1p1np/4P3/P1nPQ3/5N1P/5PPK/RRB5 b - - bm Ne7; c0 \"Ne7=10, Qc7=9, Qd8=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.020\"; cem -0.73; cee -0.50; ce -0.78;");
    pos.add(
        "2r3k1/p1r1qpb1/1p2p1p1/nR2P3/P2B4/2P5/3NQPP1/R5K1 w - - bm Ne4; c0 \"Ne4=10, Nb3=8, f4=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.021\"; cem -0.91; cee -0.40; ce -0.73;");
    pos.add(
        "2rq1rk1/1p2b1p1/pn2p3/2p1Pn2/2pP3p/5N1P/PPQ2BP1/1BRR2K1 b - - bm Nd5; c0 \"Nd5=10, Bg5=6, Qc7=6, Qe8=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.022\"; cem 0.29; cee -0.88; ce 0.16;");
    pos.add(
        "r7/p1r2nk1/1pNq1np1/1P1p1p2/P2Qp3/4P1P1/2R1P1BP/2R3K1 b - - bm Ng5; id \"STS(v3.1) Knight Outposts/Centralization/Repositioning.023\"; c0 \"Ng5=10, Rac8=4, Rb7=3, Re8=3\"; cem 1.16; cee 0.19; ce 0.90;");
    pos.add(
        "2rq2k1/3nbppp/pprp1nb1/4p3/P1P1P3/1PN1BN1P/2Q1BPP1/R2R2K1 w - - bm Nh4; c0 \"Nh4=10, Ra2=5, Rab1=6, g3=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.024\"; cem 0.70; cee 1.03; ce 0.80;");
    pos.add(
        "2rqr1k1/1p2bppp/p2p1n2/3P1P2/2Pp4/1P1B4/P3Q1PP/R1B2RK1 b - - bm Nd7; c0 \"Nd7=10, Qa5=7, h6=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.025\"; cem 0.37; cee 0.10; ce 0.23;");
    pos.add(
        "2rr4/Bp3k1p/5pp1/8/2n3b1/P1N5/1PP2PPP/R1R3K1 w - - bm Ne4; c0 \"Ne4=10, Na4=9, f3=9, h3=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.026\"; cem 1.84; cee 0.64; ce 1.13;");
    pos.add(
        "3R4/5pk1/2p4r/1p2p1p1/p3P1P1/P1P2P2/1P2B1K1/n7 b - - bm Nb3; c0 \"Nb3=10, Rf6=9, Rg6=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.027\"; cem 1.03; cee 0.11; ce 0.03;");
    pos.add(
        "3q3r/2p2pk1/6p1/2p1p1Pn/1pBnP3/1P2BP1R/P5Q1/7K b - - bm Nf4; c0 \"Nf4=10, Qd7=5, c6=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.028\"; cem -1.20; cee -1.51; ce -1.42;");
    pos.add(
        "3r1bk1/1rq2p2/2npb1p1/p3p2p/2P1P2P/1PN3P1/2N1QPBK/R2R4 w - - bm Nb5; c0 \"Nb5=10, Rdb1=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.029\"; cem -0.84; cee 0.18; ce -0.72;");
    pos.add(
        "3r1rk1/1p4bp/2qPp1p1/p3n3/P2BN3/1PN4P/2PR2P1/4Q1K1 w - - bm Nb5; c0 \"Nb5=10, Qe3=5, Qh4=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.030\"; cem 0.75; cee 0.00; ce 0.69;");
    pos.add(
        "3r2k1/2q2ppp/p1p1bn2/1p2b1B1/4P3/1PN2B1P/P1Q2PP1/2R4K w - - bm Nd5; c0 \"Nd5=10\"; id \"STS: Knight Outposts/Repositioning/Centralization.031\"; cem -1.46; cee -1.61; ce -1.39;");
    pos.add(
        "3r2k1/4qpn1/R2p3p/1Pp1p1p1/1rP1P1P1/6P1/3Q1P2/4RBK1 b - - bm Ne6; c0 \"Ne6=10, Kf8=5, Kh7=4, Rd7=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.032\"; cem 0.81; cee 1.06; ce 0.79;");
    pos.add(
        "3r4/2p2pk1/2q1n1p1/2p1p1Pn/1pB1P3/1P2BP2/P6R/4Q1K1 b - - bm Nd4; c0 \"Nd4=10, Kg8=8, Nhf4=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.033\"; cem -0.67; cee -1.23; ce -0.99;");
    pos.add(
        "3r4/bp1r2pk/p3npqp/P2Np3/1PR1P2B/5Q1P/2P3P1/5R1K b - - bm Nd4; c0 \"Nd4=10, Bb8=4, Kh8=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.034\"; cem 0.13; cee 0.25; ce 0.05;");
    pos.add(
        "3r4/r1pb3p/1p4kB/2p3P1/4pP2/1P2NnKP/PR3R2/8 b - - bm Nd4; c0 \"Nd4=10, Bc8=2, Bf5=2, c4=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.035\"; cem 0.78; cee 0.58; ce 0.48;");
    pos.add(
        "3rb1k1/4qpbp/1p2p1p1/1P3n2/Q1P2p2/2N2B1P/6PK/1NR2R2 b - - bm Ne3; c0 \"Ne3=10, Bd4=3, Rb8=7, Rc8=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.036\"; cem 2.11; cee 3.70; ce 2.22;");
    pos.add(
        "3rr1k1/1p3ppp/p1q2b2/P4P2/2P1p3/1P6/2N1Q1PP/4RR1K w - - bm Nb4; c0 \"Nb4=10, Ne3=9, Qh5=9, b4=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.037\"; cem -1.40; cee -0.68; ce -1.08;");
    pos.add(
        "3rr1k1/pb1n1pp1/2q2b1p/2p5/2P1p2N/1P2B1P1/P1QR1PBP/3R2K1 b - - bm Ne5; c0 \"Ne5=10, Bxh4=7, Nf8=7, g6=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.038\"; cem 0.11; cee 0.42; ce 0.01;");
    pos.add(
        "3rr3/p3b1kp/2p2pp1/1q1np3/4Q1PB/1NP5/PP3P1P/R2R2K1 b - - bm Nf4; c0 \"Nf4=10, Kf7=3, Qa6=2, a5=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.039\"; cem -0.49; cee -0.76; ce -0.62;");
    pos.add(
        "4b1nk/p1r1p1rp/Bpq5/n3Ppp1/8/5N1P/2P2BPQ/R3R1K1 w - - bm Nd4; c0 \"Nd4=10, Bd3=5, Rad1=3, e6=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.040\"; cem -0.66; cee -1.66; ce -0.58;");
    pos.add(
        "4k2r/1r2np2/2q1p1p1/p2pP3/n1pP1PP1/1pP1NNK1/1P2Q3/R4R2 w k - bm Ng5; c0 \"Ng5=10, Qd2=3, Qg2=3, Rf2=4, Rh1=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.041\"; cem -0.03; cee -0.16; ce 0.04;");
    pos.add(
        "4n3/1p1b1pk1/2n5/rN4p1/1p1Np3/1B2P1P1/PP3PK1/2R5 b - - bm Ne5; c0 \"Ne5=10\"; id \"STS: Knight Outposts/Repositioning/Centralization.042\"; cem 1.59; cee 0.10; ce 0.45;");
    pos.add(
        "4n3/1p1b1pk1/8/rNR1n1p1/1p1Np3/1B2P1P1/PP3PK1/8 b - - bm Nf3; c0 \"Nf3=10, Bxb5=3, f6=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.043\"; cem 1.22; cee 0.00; ce 0.26;");
    pos.add(
        "4nk2/p4rr1/1pRp3b/1P1Pp2p/1P5P/2NBpP2/4R1P1/5K2 w - - bm Ne4; c0 \"Ne4=10, Kg1=1, Nd1=1, Rc8=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.044\"; cem -0.12; cee 0.50; ce 0.29;");
    pos.add(
        "4r1k1/2pbqp1p/1r3p2/pP1p4/8/P1QBPN2/3P1PPP/4K2R w K - bm Nd4; c0 \"Nd4=10, Qxc7=6, a4=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.045\"; cem -0.11; cee -0.40; ce -0.11;");
    pos.add(
        "4r1k1/3q1pp1/3p1n2/rp2nP1p/3B1R2/2N5/2PQ2PP/4R1K1 w - - bm Ne4; c0 \"Ne4=10, Kh1=8, Rb1=6, h3=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.046\"; cem -1.10; cee -1.08; ce -1.00;");
    pos.add(
        "4r1k1/3r2p1/b1q2n1p/p7/Pp2P2Q/1N2BP2/1PP4P/2R1R2K w - - bm Nd4; c0 \"Nd4=10, Nc5=6, Nxa5=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.047\"; cem 1.50; cee 1.95; ce 1.61;");
    pos.add(
        "4r3/1p3pk1/2p2n1p/2n1qP2/5bPQ/P3pB2/2R5/1R3N1K b - - bm Nfe4; c0 \"Nfe4=10, Nd3=9, Nh7=9, Qd4=2, Rd8=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.048\"; cem -2.58; cee -2.36; ce -2.51;");
    pos.add(
        "4rrk1/p4pp1/1b1p3p/2pP4/6q1/5N2/PP3PPP/2RQ1R1K w - - bm Nd2; c0 \"Nd2=10, Kg1=7, Qd3=2, h3=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.049\"; cem -0.72; cee 0.00; ce -0.40;");
    pos.add(
        "5k2/6p1/Bnp1p2p/5p2/3P1n2/2q2P2/7P/5RQK b - - bm Nbd5; c0 \"Nbd5=10, Kf7=7, Kg8=4, g6=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.050\"; cem -1.09; cee -0.54; ce -0.87;");
    pos.add(
        "5r1k/2p3q1/1p1npr2/pPn1N1pp/P1PN4/R4PPP/4Q1K1/3R4 w - - bm Ndc6; c0 \"Ndc6=10, Kh1=1, Rb1=8, Rc1=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.051\"; cem 0.87; cee 0.91; ce 0.97;");
    pos.add(
        "5r2/2r2kp1/3n1p2/1p1Pp2p/p3P2P/PnP4R/1BB2KP1/4R3 b - - bm Nc4; c0 \"Nc4=10, Ke7=9, Rb8=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.052\"; cem 0.10; cee -0.16; ce -0.15;");
    pos.add(
        "5rk1/3nbp1p/2p1p3/2PpP1pN/3P2B1/q3PQ1P/6P1/5RK1 w - - bm Nf6+; c0 \"Nf6+=10, Qf2=1, Qg3=4, h4=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.053\"; cem 2.07; cee 0.30; ce 1.45;");
    pos.add(
        "5rk1/6pp/pn1qp1r1/1p2R3/2pP1P2/P1P2Q1P/5P2/2B1R2K b - - bm Nd5; c0 \"Nd5=10, Nd7=9, Qd7=9, Rgf6=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.054\"; cem 0.07; cee -0.41; ce -0.17;");
    pos.add(
        "5rk1/pb1qbppp/1p6/n1rpP3/7P/2P2NP1/P3QPB1/R1BR2K1 w - - bm Nd4; c0 \"Nd4=10, Bb2=2, Bd2=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.055\"; cem 0.05; cee -0.34; ce 0.13;");
    pos.add(
        "6k1/3pbpp1/p3p2n/r3P2p/2rBNP2/2P3PP/P3R3/3R3K b - - bm Nf5; c0 \"Nf5=10, Rd5=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.056\"; cem 0.73; cee 0.12; ce 0.26;");
    pos.add(
        "6k1/p2b3n/1p2pn1q/3r1p2/5P1B/P5NR/1Q2B1P1/4K3 b - - bm Ng4; c0 \"Ng4=10, Qf8=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.057\"; cem -0.43; cee 0.00; ce -0.42;");
    pos.add(
        "7k/Rb4r1/3pPp1q/1pp2P2/3n2BP/4N1K1/1P3Q2/8 b - - bm Nc6; c0 \"Nc6=10, Kg8=8, Kh7=6, Qxe3+=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.058\"; cem -4.34; cee 1.75; ce -2.05;");
    pos.add(
        "8/1b5p/1p1rrkp1/p2p1p2/P2P3P/1R1B1N2/nP3PPK/3R4 w - - bm Ne5; c0 \"Ne5=10, Bb5=4, h5=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.059\"; cem 1.70; cee 0.74; ce 1.21;");
    pos.add(
        "8/1p2kp2/p3pn2/4n1p1/1P2P3/P1r1NB1P/5PPK/R7 b - - bm Ne8; c0 \"Ne8=10, Nxf3+=1, b5=3, b6=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.060\"; cem 0.77; cee -0.39; ce -0.30;");
    pos.add(
        "8/1q6/3pn1k1/2p1p1p1/2P1P1Pp/1rBP1p1P/2Q2P2/R5K1 b - - bm Nf4; c0 \"Nf4=10, Kf7=4, Kg7=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.061\"; cem -0.21; cee -1.49; ce -0.99;");
    pos.add(
        "8/2k2p2/pr1pbpn1/2p5/2P1P1P1/2P1KP1p/7P/R2N1B2 b - - bm Ne5; c0 \"Ne5=10, Kd7=5, Rb3=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.062\"; cem -0.15; cee 0.48; ce 0.27;");
    pos.add(
        "8/pB1b2k1/1p2pn2/5p2/5P1B/P7/3K1NPn/8 b - - bm Ne8; c0 \"Ne8=10, Bb5=8, Kg6=7, Nf1+=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.063\"; cem -0.48; cee -0.13; ce -0.25;");
    pos.add(
        "b1r2r1k/p2qp1bp/1p1pn1p1/8/1PP2P2/R1NBB3/P2Q2PP/5RK1 w - - bm Nb5; c0 \"Nb5=10, Be2=7, Kh1=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.064\"; cem -0.16; cee 0.02; ce -0.06;");
    pos.add(
        "b4q2/1r5k/3p4/1p1Pn2B/1PpQP3/2N4P/6P1/B5K1 w - - bm Nd1; c0 \"Nd1=10, Ne2=6, Qe3=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.065\"; cem 0.48; cee -0.15; ce 0.30;");
    pos.add(
        "q3n3/2rp1ppk/2b4p/4p2P/p3P3/Pr2NBP1/1P1R1PK1/2R1Q3 w - - bm Nd5; c0 \"Nd5=10, Kg1=3, Qe2=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.066\"; cem 0.58; cee 0.75; ce 0.70;");
    pos.add(
        "r1b1k1nr/pp2p1bp/1q1p2p1/2pP4/2Pnp3/2NB1N1P/PP1B1PP1/R2QK2R w KQkq - bm Nxe4; c0 \"Nxe4=10, Bxe4=3, Na4=7, Nxd4=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.067\"; cem 0.88; cee 0.56; ce 0.98;");
    pos.add(
        "r1b2r1k/pp3pbp/3n4/4p3/2N4R/2N5/PP3PPP/R1B3K1 b - - bm Nf5; c0 \"Nf5=10, Bf6=2, Nxc4=3, Rd8=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.068\"; cem 0.53; cee 0.00; ce 0.18;");
    pos.add(
        "r1b2rk1/1pq1bppp/p2p4/P3p3/2N1n3/4B3/1PP1BPPP/R2QK2R w KQ - bm Nb6; c0 \"Nb6=10, O-O=9, f3=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.069\"; cem -1.73; cee 0.25; ce -1.60;");
    pos.add(
        "r1b2rk1/p2q2b1/1nppp1pp/5pN1/P2P1B2/Q5P1/1P2PPBP/R1R3K1 b - - bm Nd5; c0 \"Nd5=10, Bb7=3, hxg5=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.070\"; cem 0.72; cee 1.60; ce 0.63;");
    pos.add(
        "r1b2rk1/pp3pp1/2np2qp/b1p1p3/2P1P3/2NPB1P1/PP3PBP/R2Q1RK1 w - - bm Nd5; c0 \"Nd5=10, Qb3=1, Qc2=1, f4=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.071\"; cem -0.51; cee 0.57; ce -0.40;");
    pos.add(
        "r1bq1rk1/ppp3bp/n2p1pp1/3P4/2P1Pp2/2N2N1P/PP2BPP1/R2QR1K1 w - - bm Nd4; c0 \"Nd4=10, Bf1=9, Qd2=4, Rc1=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.072\"; cem 1.91; cee 1.51; ce 2.00;");
    pos.add(
        "r1r3k1/pb3p1p/1pqBp1p1/4P3/3b4/2P2P2/PR1N2PP/2RQ3K w - - bm Ne4; c0 \"Ne4=10\"; id \"STS: Knight Outposts/Repositioning/Centralization.073\"; cem -0.21; cee -0.50; ce -0.16;");
    pos.add(
        "r2b2k1/2pr4/1pn1b1qp/3Np1p1/p1P1p3/1P2B1P1/PQ2PP1P/R2R2NK b - - bm Nd4; c0 \"Nd4=10, Bf5=4, a3=5, h5=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.074\"; cem -0.55; cee -0.23; ce -0.63;");
    pos.add(
        "r2br3/p2b1q1k/n2P2pp/1p1N1p2/4pP2/BP2Q1PN/P1R4P/3R2K1 w - - bm Nc7; c0 \"Nc7=10, Nf2=3, Qd4=2, Qe2=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.075\"; cem 0.14; cee 1.09; ce 0.25;");
    pos.add(
        "r2q1bk1/1p1brpp1/p1np1n1p/4p3/PN2P3/1QPP1N1P/B2B1PP1/R3R1K1 w - - bm Nd5; c0 \"Nd5=10, Be3=4, Qb2=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.076\"; cem 0.29; cee 0.58; ce 0.38;");
    pos.add(
        "r2q1k2/2p2pb1/p2n2rp/1p1RB1p1/8/2PQRN1P/P4PP1/6K1 w - - bm Nd4; c0 \"Nd4=10, Bxg7+=2, Re2=3, h4=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.077\"; cem 0.80; cee -0.99; ce 0.60;");
    pos.add(
        "r2q1rk1/3nbpp1/p2p3p/8/1p1BP3/3B2Q1/PPP4P/2KR3R b - - bm Ne5; c0 \"Ne5=10, Bf6=1, Bg5+=4, g6=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.078\"; cem 1.49; cee 0.55; ce 1.24;");
    pos.add(
        "r2q1rk1/pp1bp1bp/5np1/2pP1p2/8/2N2NP1/PP2PPBP/2RQ1RK1 w - - bm Ne5; c0 \"Ne5=10, Ng5=5, Qb3=7, e3=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.079\"; cem -0.06; cee -0.32; ce 0.03;");
    pos.add(
        "r2q2kr/p3n3/1p1Bp1bp/3pP1pN/5PPn/3B3Q/2P2K2/1R5R w - - bm Nf6+; c0 \"Nf6+=10, Bxg6=3, Rbg1=2\"; id \"STS: Knight Outposts/Repositioning/Centralization.080\"; cem 4.06; cee 0.86; ce 4.08;");
    pos.add(
        "r2qk2r/1bpnnpbp/p2pp1p1/4P3/Pp1P1P2/2NBBN2/1PP3PP/R2Q1RK1 w kq - bm Ne4; c0 \"Ne4=10, Na2=2, Ne2=2, exd6=8\"; id \"STS: Knight Outposts/Repositioning/Centralization.081\"; cem 0.88; cee -0.17; ce 0.98;");
    pos.add(
        "r2qr1k1/pp3ppp/2n2nb1/1P4B1/3p4/P2B1P2/2P1N1PP/R2Q1RK1 b - - bm Ne5; c0 \"Ne5=10, Na5=9, Ne7=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.082\"; cem 0.91; cee 0.91; ce 0.82;");
    pos.add(
        "r2r2k1/1bqn1pp1/1p1p1b1p/1B2n3/2P1P3/P1N1B3/1P1NQ1PP/4RR1K w - - bm Nd5; c0 \"Nd5=10, Bd4=9, Nb3=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.083\"; cem 0.83; cee 1.18; ce 0.92;");
    pos.add(
        "r2r2k1/1p1n2q1/2ppbp2/6p1/2PBP2p/p1N2P2/Pb4PP/1R1RQBK1 b - - bm Ne5; c0 \"Ne5=10, Qf7=2, Qh7=5, h3=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.084\"; cem -0.83; cee -2.15; ce -0.95;");
    pos.add(
        "r2rq3/pp1b3k/n2P1bpp/4pp2/8/BPN1Q1PN/P4P1P/2RR2K1 w - - bm Nd5; c0 \"Nd5=10, Bb2=5, Qd3=5, Qf3=5, f3=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.085\"; cem -0.21; cee 0.23; ce -0.10;");
    pos.add(
        "r3r1k1/1b1nq2p/p2pNppQ/1ppP3n/P3P3/7P/1PB2PP1/R3RNK1 b - - bm Nf8; c0 \"Nf8=10, Ne5=4, Rec8=6\"; id \"STS: Knight Outposts/Repositioning/Centralization.086\"; cem 2.35; cee 1.07; ce 2.22;");
    pos.add(
        "r3r1k1/1p1q2bp/1n1p2p1/1PpPpp1n/p3P3/R1NQBN1P/1PP2PP1/4R1K1 w - - bm Ng5; c0 \"Ng5=10, Qf1=7, Rd1=8, exf5=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.087\"; cem 0.44; cee 0.59; ce 0.53;");
    pos.add(
        "r3r1k1/3nbppp/p1q5/1pp1P2b/5B2/1P2QN2/1P1N1PPP/3RR1K1 w - - bm Ne4; c0 \"Ne4=10, Bg3=7, Rc1=7\"; id \"STS: Knight Outposts/Repositioning/Centralization.088\"; cem -1.04; cee -1.17; ce -0.95;");
    pos.add(
        "r3r1k1/4bppp/pnq5/1pp1P2b/4NB2/1P2QN2/1P3PPP/3RR1K1 w - - bm Nd6; c0 \"Nd6=10, Bg3=4, Qc1=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.089\"; cem -0.26; cee -0.36; ce -0.17;");
    pos.add(
        "r3r2k/pp3pp1/1np4p/3p2q1/1P1P2b1/P1NBP3/2Q2PPP/R4RK1 w - - bm Ne2; c0 \"Ne2=10, Kh1=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.090\"; cem 0.06; cee 0.83; ce 0.28;");
    pos.add(
        "r3r3/2P4k/3Bbbqp/ppQ2pp1/4pPP1/1P6/P1R2N1P/3R2K1 w - - bm Nxe4; c0 \"Nxe4=10, Qe3=4, Rcd2=5, Re2=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.091\"; cem 0.99; cee 3.02; ce 1.40;");
    pos.add(
        "r3rnk1/1bpq1pp1/p2p3p/1p1Pp1b1/P3P1P1/1BP1N1P1/1P3P2/R1BQR1K1 w - - bm Nf5; c0 \"Nf5=10, Bc2=3, Kg2=4, Kg2=9, Qe2=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.092\"; cem 0.03; cee -0.34; ce 0.12;");
    pos.add(
        "r4rk1/1b1q1ppp/pb1p1nn1/1pp1p3/1PP1P3/P2PNN1P/B2B1PP1/2RQR1K1 w - - bm Nf5; c0 \"Nf5=10, Bb1=7, Qb3=4\"; id \"STS: Knight Outposts/Repositioning/Centralization.093\"; cem 0.29; cee 0.54; ce 0.38;");
    pos.add(
        "r4rk1/1pq1bppp/1n2p3/p1n1P3/2PR4/2N1BN2/P3QPPP/1R4K1 w - - bm Nb5; c0 \"Nb5=10, Qd2=5, h4=9\"; id \"STS: Knight Outposts/Repositioning/Centralization.094\"; cem 1.04; cee 0.52; ce 1.12;");
    pos.add(
        "r4rk1/5p1p/p2qpnp1/1p2b3/3p4/3B1R2/PPQ3PP/R1BN3K b - - bm Nd7; c0 \"Nd7=10, Nd5=7, Nh5=5, Rac8=1, Rfc8=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.095\"; cem 0.32; cee -0.16; ce 0.18;");
    pos.add(
        "r4rk1/ppp3b1/3p1q1p/3Ppn2/P1P3n1/2NQ1N2/1P1B1PP1/R3R1K1 w - - bm Ne4; c0 \"Ne4=10, Nb5=4, Nh2=1, Ra3=1\"; id \"STS: Knight Outposts/Repositioning/Centralization.096\"; cem 0.66; cee 0.82; ce 0.75;");
    pos.add(
        "r5r1/1pp2k1p/2bn4/2p3B1/p3pPP1/1P2N2P/P1P1R3/R5K1 b - - bm Nb5; c0 \"Nb5=10\"; id \"STS: Knight Outposts/Repositioning/Centralization.097\"; cem 1.86; cee 0.27; ce 0.76;");
    pos.add(
        "r6k/pp3pp1/1n6/1pQp1q2/3PrN1p/P3P2P/5PP1/2R2RK1 w - - bm Nd3; c0 \"Nd3=10, Ne2=2, Qxb5=2, Rce1=3\"; id \"STS: Knight Outposts/Repositioning/Centralization.098\"; cem 0.46; cee -0.15; ce 0.36;");
    pos.add(
        "r6r/1q1bbkp1/p1p1pn2/5p1p/N2Bp3/2Q3P1/PPP2PBP/R2R2K1 b - - bm Nd5; c0 \"Nd5=10, Rad8=5, h4=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.099\"; cem -1.32; cee -2.92; ce -1.44;");
    pos.add(
        "rqr3k1/1p2bppp/3pn3/p3p1Pn/P3P3/1PNBBP2/1P1Q3P/2KR3R b - - bm Nd4; c0 \"Nd4=10, Nef4=6, Nhf4=5\"; id \"STS: Knight Outposts/Repositioning/Centralization.100\"; cem -0.71; cee 0.40; ce -0.78;");
    pos.add(
        "6k1/p2pp2p/bp4n1/q1r4R/1RP1P3/2P2B2/P2Q2P1/4K3 w - - bm Rd5; c0 \"Rd5=10, Rf5=6, g4=7\"; id \"STS(v4.0) Square Vacancy.001\"; cem 2.30; cee 2.50; ce 2.47;");
    pos.add(
        "r2r2k1/pp3ppp/2p1qn2/5N1b/1n2PP2/4Q2P/PPP3B1/R1B2RK1 w - - bm Qc5; c0 \"Qc5=10, Qg3=3\"; id \"STS(v4.0) Square Vacancy.002\"; cem -0.09; cee -0.29; ce 0.00;");
    pos.add(
        "3r4/p4pk1/P1pr3p/3nb3/1p6/5B1P/1P3PP1/R1BR2K1 w - - bm Ra5; c0 \"Ra5=10, Kf1=2, g3=5\"; id \"STS(v4.0) Square Vacancy.003\"; cem 0.56; cee -0.63; ce -0.07;");
    pos.add(
        "1b1r3r/3pkpp1/3np1q1/2p5/2P1PPP1/5Q2/PP4B1/R1BR2K1 b - - bm Rh4; c0 \"Rh4=10, Nxc4=6, Rc8=7\"; id \"STS(v4.0) Square Vacancy.004\"; cem 0.53; cee -0.14; ce 0.33;");
    pos.add(
        "7k/1p6/1n1rrq1p/1R1p1p1p/3P1P2/QR2P2P/6PK/5B2 w - - bm Qa7; c0 \"Qa7=10, Kg1=7, R5b4=7\"; id \"STS(v4.0) Square Vacancy.005\"; cem 1.47; cee 1.32; ce 1.52;");
    pos.add(
        "5r1k/5rp1/p1n1p1qp/2P1p3/P7/4QN1P/5PP1/2R1R2K w - - bm Rc4; c0 \"Rc4=10, Qe2=5, Rc3=7\"; id \"STS(v4.0) Square Vacancy.006\"; cem 0.12; cee 1.06; ce 0.51;");
    pos.add(
        "6r1/1p2Q1pk/2p2p1p/3p1P1P/p1nP4/PqP1P3/1P2RN2/2K5 b - - bm Qa2; c0 \"Qa2=10, Qb5=5, b6=3\"; id \"STS(v4.0) Square Vacancy.007\"; cem -1.89; cee -0.65; ce -1.31;");
    pos.add(
        "6r1/1p2Q1pk/2p2p1p/n2p1P1P/p2P4/P1P1P3/1P1KR3/q2N4 b - - bm Qb1; c0 \"Qb1=10, Kh8=3, Nb3+=4\"; id \"STS(v4.0) Square Vacancy.008\"; cem -3.47; cee 0.83; ce -1.25;");
    pos.add(
        "5r1k/1b3pp1/p3pb2/4N2q/3R1B2/4P3/1PB2PPP/1K4R1 b - - bm Qe2; c0 \"Qe2=10, Bxe5=7, Kg8=4\"; id \"STS(v4.0) Square Vacancy.009\"; cem -1.58; cee -0.68; ce -1.29;");
    pos.add(
        "8/1b2r2p/1p1r1kp1/p2p1p2/Pn1P3P/1R1B1N2/1P3PPK/2R5 w - - bm Bb5; c0 \"Bb5=10, Bf1=2, Ne5=6\"; id \"STS(v4.0) Square Vacancy.010\"; cem 0.66; cee -0.33; ce 0.15;");
    pos.add(
        "1q1k2r1/p2bn3/1r3n2/2QPp1Rp/2P4P/2PB1P2/2K1N3/R7 w - - bm Qa5; c0 \"Qa5=10, Qa3=4, Rxg8+=5\"; id \"STS(v4.0) Square Vacancy.011\"; cem -0.81; cee -0.09; ce -0.64;");
    pos.add(
        "8/p3q1kp/1p1p1rp1/3Bn3/P1PQ1p2/1P6/6PP/4R2K b - - bm Rf5; c0 \"Rf5=10, Kh6=6, Rf8=3\"; id \"STS(v4.0) Square Vacancy.012\"; cem -0.21; cee -0.82; ce -0.62;");
    pos.add(
        "4r1rk/pp6/5q2/3pNp1P/2pPnQ2/2P1P2P/P6K/R5R1 w - - bm Rg6; c0 \"Rg6=10, Rab1=4, Rac1=4\"; id \"STS(v4.0) Square Vacancy.013\"; cem 2.69; cee 1.46; ce 2.40;");
    pos.add(
        "5r2/p1qn3k/bp1p1pp1/3P4/P2BPP2/2Pp4/3N2P1/R2Q2K1 w - - bm Qg4; c0 \"Qg4=10, Qe1=4, a5=5\"; id \"STS(v4.0) Square Vacancy.014\"; cem 0.60; cee -0.18; ce 0.40;");
    pos.add(
        "1r3q2/1n4pk/R4p1p/4p2P/2B1P1P1/2P1QPK1/8/8 w - - bm Be6; c0 \"Be6=10, Bd5=7, Rb6=8\"; id \"STS(v4.0) Square Vacancy.015\"; cem 2.38; cee 2.74; ce 2.66;");
    pos.add(
        "4r1k1/1pb3p1/p1p3q1/3n4/1P1P4/P3pRP1/4Q1K1/2B2R2 b - - bm Qe4; c0 \"Qe4=10, Bb6=5, Bd8=5\"; id \"STS(v4.0) Square Vacancy.016\"; cem -1.51; cee 0.26; ce -1.00;");
    pos.add(
        "3R4/pkn5/1p2qp2/1p5p/1P2P1pR/P5P1/1N3PP1/5K2 b - - bm Qb3; c0 \"Qb3=10, Qa2=5\"; id \"STS(v4.0) Square Vacancy.017\"; cem -0.06; cee 0.47; ce 0.25;");
    pos.add(
        "4r1k1/p5pp/4q3/P2R4/2p2P2/2N3P1/2nB1K1P/1R6 b - - bm Qh3; c0 \"Qh3=10, a6=5, h6=6\"; id \"STS(v4.0) Square Vacancy.018\"; cem -3.48; cee -0.87; ce -2.09;");
    pos.add(
        "1Q6/1p2p2k/1r4pp/p1p2P2/q2nr1P1/7R/1P1R4/5NK1 w - - bm Qf8; c0 \"Qf8=10, Kh2=7, Rg2=7\"; id \"STS(v4.0) Square Vacancy.019\"; cem -3.13; cee -3.95; ce -3.29;");
    pos.add(
        "4R3/p2q2k1/2p2r1p/6p1/8/P5P1/4QP1P/3rB1K1 b - - bm Qd4; c0 \"Qd4=10, Kf7=6, Kg6=6\"; id \"STS(v4.0) Square Vacancy.020\"; cem 0.41; cee -2.70; ce -1.22;");
    pos.add(
        "3q1r1k/5ppp/1pR1pn2/p7/B1PP4/P3QP1P/5PK1/8 w - - bm Qe5; id \"STS(v4.0) Square Vacancy.021\"; c0 \"Qe5=10, f4=1, Kf1=1, Kh2=1\"; cem -0.11; cee 0.91; ce 0.53;");
    pos.add(
        "8/Qpnbk2q/4pp2/1P1p4/P1r5/2P3P1/1K2N3/1R3B2 b - - bm Qd3; c0 \"Qd3=10, Qh2=2, e5=2\"; id \"STS(v4.0) Square Vacancy.022\"; cem -0.76; cee -0.93; ce -0.92;");
    pos.add(
        "2r2rk1/pb2qp1p/n3p1p1/2pp4/N1P1n3/1P2P1P1/PN3PBP/2R1QRK1 w - - bm Qa5; c0 \"Qa5=10, Qe2=6, Rd1=4\"; id \"STS(v4.0) Square Vacancy.023\"; cem 0.39; cee -0.29; ce 0.47;");
    pos.add(
        "r5k1/1p5p/p1p1qrnP/2B1pb2/R1P5/2P2B2/P2Q2P1/5RK1 w - - bm Qg5; c0 \"Qg5=10, Qb2=1, Rb4=6\"; id \"STS(4.0) Square Vacancy.024\"; cem 1.10; cee 0.00; ce 1.02;");
    pos.add(
        "Q7/2pq3k/1rp2b1p/2R5/8/1P1NP3/P2K1Pr1/5R2 w - - bm Qf8; c0 \"Qf8=10, Qa4=5, Rc2=4\"; id \"STS(v4.0) Square Vacancy.025\"; cem -1.65; cee 1.28; ce -0.66;");
    pos.add(
        "q5k1/p2p2bp/1p1p2r1/2p1np2/6p1/1PP2PP1/P2PQ1KP/4R1NR b - - bm Qd5; c0 \"Qd5=10, Re6=4, a5=1, c4=2\"; id \"STS(v4.0) Square Vacancy.026\"; cem -1.75; cee 0.47; ce -1.06;");
    pos.add(
        "4r1k1/1p5p/1nq1p1p1/4B3/6R1/P6P/3Q1PPK/8 w - - bm Qh6; c0 \"Qh6=10, Ba1=4, Qd3=4\"; id \"STS(v4.0) Square Vacancy.027\"; cem 3.62; cee 1.02; ce 2.34;");
    pos.add(
        "r5nk/p4p1r/qp1Rb2p/2p1Pp2/5P1Q/P4N2/2B3PP/5RK1 w - - bm Qh5; c0 \"Qh5=10, a4=2\"; id \"STS(v4.0) Square Vacancy.028\"; cem 1.60; cee 0.45; ce 1.50;");
    pos.add(
        "r2r2k1/1q2bpp1/4p2p/p6Q/N4P2/1P2R1P1/2P4P/2KR4 b - - bm Qb4; c0 \"Qb4=10, Qg2=5, Rdc8=5\"; id \"STS(v4.0) Square Vacancy.029\"; cem -2.12; cee -0.61; ce -1.75;");
    pos.add(
        "r4rk1/1b2bpp1/1P1p4/p1q1p2p/R3PPn1/3B3Q/2P1N1PP/1R1N3K b - - bm Qf2; c0 \"Qf2=10, d5=1\"; id \"STS(v4.0) Square Vacancy.030\"; cem -0.38; cee 0.80; ce -0.44;");
    pos.add(
        "1r5r/1p6/p2p2p1/P2P1pkq/1R1Qp3/2P1P1bP/1P2R3/5BK1 b - - bm Qf3; c0 \"Qf3=10, Be5=8, Rh6=5\"; id \"STS(v4.0) Square Vacancy.031\"; cem -2.38; cee -2.15; ce -2.28;");
    pos.add(
        "2r1r2k/q6p/6p1/3R1p2/1p1P1B2/2bQPBP1/5P1P/6K1 w - - bm Qb5; c0 \"Qb5=10, Kg2=5, Rb5=5\"; id \"STS(v4.0) Square Vacancy.032\"; cem -0.45; cee -1.71; ce -0.79;");
    pos.add(
        "1r3q1k/6p1/r6p/3P3b/2PQ1R2/pp4P1/5P2/R1B3K1 b - - bm Qb4; c0 \"Qb4=10, Qd6=1, Qe7=1\"; id \"STS(v4.0) Square Vacancy.033\"; cem -2.25; cee -3.36; ce -2.42;");
    pos.add(
        "5k2/pb3pp1/qp3n1p/2p5/2P2B2/8/P1PN1PPP/R3R1K1 b - - bm Qa4; c0 \"Qa4=10, Bc6=1\"; id \"STS(v4.0) Square Vacancy.034\"; cem -0.29; cee 0.13; ce -0.13;");
    pos.add(
        "2r1qr1k/1p4bp/p1n1bpp1/4p3/B3P3/4QN1P/PP1B1PP1/R1R3K1 w - - bm Qb6; c0 \"Qb6=10, Rc2=5, Rc3=5, a3=4\"; id \"STS(v4.0) Square Vacancy.035\"; cem 0.57; cee 0.00; ce 0.66;");
    pos.add(
        "6rk/5q1p/p2p4/P1nP1p2/2B2N1Q/1Pb1pPR1/7P/7K w - - bm Qh6; c0 \"Qh6=10, Rg5=3, Rxg8+=5\"; id \"STS(v4.0) Square Vacancy.036\"; cem 0.40; cee -1.13; ce -0.00;");
    pos.add(
        "3r2k1/p4rp1/1pRp3q/1P1Ppb2/7P/Q4P2/P5BP/2R4K b - - bm Qd2; c0 \"Qd2=10, Kh7=5, Qf4=4\"; id \"STS(v4.0) Square Vacancy.037\"; cem 0.15; cee 1.00; ce 0.31;");
    pos.add(
        "rq4k1/3bn1bp/3p2p1/3Pp1N1/4P1p1/2N1B1P1/1P2QP1P/4R1K1 b - - bm Qb3; c0 \"Qb3=10, Kh8=4, h5=4\"; id \"STS(v4.0) Square Vacancy.038\"; cem 1.25; cee 0.55; ce 0.97;");
    pos.add(
        "3rk3/2p1r1p1/7q/p1p1P1R1/2B4P/4P3/PPQ5/6K1 w - - bm Qe4; c0 \"Qe4=10, Kg2=4, b3=3\"; id \"STS(v4.0) Square Vacancy.039\"; cem 3.81; cee 0.12; ce 2.09;");
    pos.add(
        "2r2r1k/3q2np/p2P1pp1/1pP1p3/7N/5QP1/P4P1P/2R1R1K1 w - - bm Qd5; c0 \"Qd5=10, Qd1=7, Qd3=7\"; id \"STS(v4.0) Square Vacancy.040\"; cem 1.12; cee 1.90; ce 1.46;");
    pos.add(
        "r2r2k1/5p1p/6nQ/ppq5/2p1P3/P4P2/1P4PP/R1N2R1K b - - bm Qd4; c0 \"Qd4=10, Qd6=5, Qf2=1\"; id \"STS(v4.0) Square Vacancy.041\"; cem -0.11; cee -0.85; ce -0.43;");
    pos.add(
        "r3r1k1/ppq3p1/2p1n3/3pN1p1/Pb1P2P1/3QB2P/1P3P2/R1R3K1 w - - bm Qf5; c0 \"Qf5=10, Kg2=4, Ng6=3, Qg6=4, Rc2=4\"; id \"STS(v4.0) Square Vacancy.042\"; cem 1.03; cee 0.77; ce 1.08;");
    pos.add(
        "2bq1rk1/1r3p2/3p2pp/p1p1p3/PnP1P3/2QPP1N1/2B3PP/1R3RK1 b - - bm Qg5; c0 \"Qg5=10, Bd7=3, Qe8=3, h5=4\"; id \"STS(v4.0) Square Vacancy.043\"; cem -0.82; cee -0.24; ce -0.82;");
    pos.add(
        "2q5/5kp1/pP2p1r1/3b1p1Q/3P1P2/6P1/5K2/4RB2 b - - bm Qc3; c0 \"Qc3=10, Bb7=2, Qc2+=3, Qc6=1\"; id \"STS(v4.0) Square Vacancy.044\"; cem 1.11; cee 1.90; ce 1.43;");
    pos.add(
        "2q2r1k/7p/p2p1p2/1p1Np3/nP6/4Q2P/P4PP1/4R1K1 w - - bm Qh6; c0 \"Qh6=10, Qd2=2, Rc1=6, Re2=1\"; id \"STS(v4.0) Square Vacancy.045\"; cem 1.32; cee 0.40; ce 0.92;");
    pos.add(
        "8/3k4/2p5/3r4/1PQBp1pq/4P3/P4PpP/6K1 w - - bm Qa6; c0 \"Qa6=10, Kxg2=4, a4=6, b5=5\"; id \"STS(v4.0) Square Vacancy.046\"; cem -1.94; cee -1.52; ce -1.54;");
    pos.add(
        "8/7p/p2p1kqP/2r1b3/P1B1p1r1/1P6/4QP2/3R1K1R b - - bm Qf5; c0 \"Qf5=10, Qg5=1, Rf4=3, a5=3\"; id \"STS(v4.0) Square Vacancy.047\"; cem -0.83; cee -0.44; ce -0.77;");
    pos.add(
        "2b1rnk1/1p3pp1/r3p2p/1q2P3/p2PQ3/P1P2PB1/6PP/1BKR3R b - - bm Qb3; c0 \"Qb3=10, Bd7=5, Qa5=4, Rc6=4\"; id \"STS(v4.0) Square Vacancy.048\"; cem 1.66; cee 2.46; ce 1.68;");
    pos.add(
        "2r5/p4kp1/1pn2n2/3q1b2/3p4/P2P2Q1/2RB1PPP/4R1K1 b - - bm Qb3; c0 \"Qb3=10, Nd7=5, Nh5=3, Qb5=3\"; id \"STS(v4.0) Square Vacancy.049\"; cem -0.05; cee -1.89; ce -0.53;");
    pos.add(
        "r6k/1p2rR1P/2p3p1/4p1q1/8/pP6/P1P5/1K2Q2R w - - bm Qa5; c0 \"Qa5=10, Rf2=6, Rf3=6, Rff1=6\"; id \"STS(v4.0) Square Vacancy.050\"; cem 1.58; cee -0.75; ce 0.64;");
    pos.add(
        "r3r1k1/2q3p1/p2p1p2/3P2p1/n2B2P1/8/P1PQ3P/K2R1R2 b - - bm Qc4; c0 \"Qc4=10, Qc8=2, Rac8=6, Re4=6\"; id \"STS(v4.0) Square Vacancy.051\"; cem 0.09; cee 0.46; ce 0.11;");
    pos.add(
        "3r2k1/2p2pp1/p6p/1p5q/1PbB4/P1Q3P1/5P1P/4R1K1 b - - bm Qg4; c0 \"Qg4=10, Rd6=2, a5=2, f6=2\"; id \"STS(v4.0) Square Vacancy.052\"; cem -1.77; cee -0.93; ce -1.33;");
    pos.add(
        "r5k1/1q4b1/6n1/3pp1Nn/1N3p2/3P2Pp/2QBP2P/1R4K1 w - - bm Qc5; c0 \"Qc5=10, Nxh3=4\"; id \"STS(v4.0) Square Vacancy.053\"; cem -0.91; cee 0.12; ce -0.55;");
    pos.add(
        "6r1/4pp1k/3p3p/2qP1P2/r3P1PK/1R6/4Q3/1R6 b - - bm Qd4; c0 \"Qd4=10, Ra3=1, Rg5=1, Rga8=2, d5d4=1\"; id \"STS(v4.0) Square Vacancy.054\"; cem -0.18; cee 0.00; ce -0.19;");
    pos.add(
        "8/p3b1k1/2p5/3qp2p/P2p3P/1P1Q1PP1/3NP1K1/8 w - - bm Qf5; c0 \"Qf5=10, Nc4=7, Qa6=6, Qb1=5, Qc2=5\"; id \"STS(v4.0) Square Vacancy.055\"; cem 1.41; cee 1.82; ce 1.82;");
    pos.add(
        "1rb2rk1/2p3pp/p1p1p3/2N5/8/1PQ2PPq/P3P3/R2R2K1 w - - bm Qe5; c0 \"Qe5=10, Kf2=4, Ne4=6\"; id \"STS(v4.0) Square Vacancy.056\"; cem -4.08; cee 0.31; ce -2.64;");
    pos.add(
        "2rqrbk1/1b1n1p2/p2p1npp/1p6/4P2B/1B3NNP/PP1Q1PP1/3RR1K1 w - - bm Qf4; c0 \"Qf4=10, Re2=3, a3=1, a4=2\"; id \"STS(v4.0) Square Vacancy.057\"; cem 1.29; cee 0.20; ce 1.39;");
    pos.add(
        "r1k5/4qp2/P1p1b1p1/6Pp/n3P2P/6Q1/2N5/1K1R2R1 b - - bm Qc5; c0 \"Qc5=10, Rxa6=2\"; id \"STS(v4.0) Square Vacancy.058\"; cem -4.62; cee 2.44; ce -2.23;");
    pos.add(
        "6k1/3qbp1p/6p1/3Pp1P1/3n3P/pP1Q4/P7/1K2B2B b - - bm Qg4; c0 \"Qg4=10, Bd6=5, Kf8=3, h6=3\"; id \"STS(v4.0) Square Vacancy.059\"; cem -1.43; cee -0.46; ce -0.92;");
    pos.add(
        "3n4/p2rk3/3pq1p1/pR3p1p/2PQ1P2/1PB4P/6PK/8 w - - bm Qh8; c0 \"Qh8=10, Bb2=5, Kh1=5, Rb8=6, Rd5=5\"; id \"STS(v4.0) Square Vacancy.060\"; cem 4.30; cee 0.56; ce 2.41;");
    pos.add(
        "5b1k/r7/Pq5p/2p5/3p3N/R2Q2P1/5PK1/8 w - - bm Qf5; c0 \"Qf5=10, Ng6+=2, Qf3=8, Qg6=2\"; id \"STS(v4.0) Square Vacancy.061\"; cem 2.53; cee 1.86; ce 2.22;");
    pos.add(
        "5rk1/3n4/3Rp1p1/2P2q1p/1p1P2n1/6P1/1B1NQ2P/6K1 b - - bm Qc2; id \"STS(v4.0) Square Vacancy.062\"; c0 \"Qc2=10, Ndf6=4, Nxc5=4\"; cem 0.23; cee 0.97; ce 0.42;");
    pos.add(
        "2r3k1/5pbp/2pB2p1/p3P3/2R2P2/2Q3P1/5K1P/1q6 b - - bm Qh1; c0 \"Qh1=10, Qa2+=2, Qb6+=3, c5=2\"; id \"STS(v4.0) Square Vacancy.063\"; cem -2.39; cee 0.00; ce -1.22;");
    pos.add(
        "5r2/3q2pk/5n1p/3p1p1P/1p1P4/4PP2/1P2R1PQ/2rNR1K1 b - - bm Qb5; c0 \"Qb5=10, Re8=4\"; id \"STS(v4.0) Square Vacancy.064\"; cem -0.33; cee -0.56; ce -0.50;");
    pos.add(
        "5r1k/2pb1q2/1p1p4/pP1Pp1Rp/2P1P3/1KN4r/P7/4Q2R b - - bm Qf3; c0 \"Qf3=10, Qh7=4, Rg8=2, Rxh1=3\"; id \"STS(v4.0) Square Vacancy.065\"; cem -0.42; cee -0.97; ce -0.68;");
    pos.add(
        "7k/p4r2/b3p2q/Pp1p2pp/3P2p1/1P2P1P1/2Q2PBP/2R3K1 w - - bm Qc6; c0 \"Qc6=10, Bf1=5, Qe2=4, e4=6\"; id \"STS(v4.0) Square Vacancy.066\"; cem 1.78; cee 1.04; ce 1.48;");
    pos.add(
        "3r1k2/2q5/1p6/5P1p/pP2Q3/P2p4/3R4/1K6 b - - bm Qg3; c0 \"Qg3=10, Qc3=5, Qe7=7, Re8=4\"; id \"STS(v4.0) Square Vacancy.067\"; cem 0.00; cee -1.61; ce -1.19;");
    pos.add(
        "6k1/4bp2/4q3/3pP3/p1pr3p/P1NbQR2/1P4PR/3K4 b - - bm Qg4; c0 \"Qg4=10, Bc2+=4, Rg4=6\"; id \"STS(v4.0) Square Vacancy.068\"; cem -2.30; cee 0.62; ce -1.39;");
    pos.add(
        "5bk1/3r1p1p/3P1Pp1/1q4Pn/2pN3P/1P2B3/1K3Q2/R7 b - - bm Qe5; c0 \"Qe5=10, Qb8=7, Qc5=4, Qd5=8\"; id \"STS(v4.0) Square Vacancy.069\"; cem 1.90; cee 4.50; ce 2.82;");
    pos.add(
        "1r1n3k/1pq3pp/p2Nrn2/2P1p3/3p4/Q2P2P1/4PPB1/1RR3K1 w - - bm Rb6; c0 \"Rb6=10, Qa2=4, Qa4=4, Qb3=4, Rb3=5\"; id \"STS(v4.0) Square Vacancy.070\"; cem 1.84; cee 1.11; ce 1.81;");
    pos.add(
        "3r1q1k/6pp/4R3/8/n2p1PB1/3PpQP1/4P1K1/8 w - - bm Qc6; c0 \"Qc6=10, Bh3=3, Kh2=3, Re5=3, f5=3\"; id \"STS(v4.0) Square Vacancy.071\"; cem 0.23; cee 0.38; ce 0.40;");
    pos.add(
        "8/1p3q1k/p1p2b1P/2B1nP2/8/1Pn2PN1/2Q3P1/6K1 b - - bm Qd5; c0 \"Qd5=10, Bh4=3, Nd5=3, Nd7=5\"; id \"STS(v4.0) Square Vacancy.072\"; cem -2.42; cee -1.61; ce -2.03;");
    pos.add(
        "2r4k/7p/2q2nr1/p3R3/Ppp1P3/7P/P1B3P1/3R1Q1K w - - bm Qf5; c0 \"Qf5=10, Qf3=4, Rd2=4, Re7=2\"; id \"STS(v4.0) Square Vacancy.073\"; cem -0.38; cee -1.08; ce -0.49;");
    pos.add(
        "bq6/4bk1p/1B1p2p1/3Ppp2/2P5/7P/1Q1N1PP1/6K1 w - - bm Qb5; c0 \"Qb5=10, Qb1=2, Qb3=4, Qb4=2\"; id \"STS(v4.0) Square Vacancy.074\"; cem 1.18; cee 0.00; ce 0.54;");
    pos.add(
        "b3qbk1/B6p/1QNp2p1/3Pp3/2P2p2/5P1P/6P1/6K1 w - - bm Qc7; c0 \"Qc7=10, Kh2=2, Nd8=4, Qb5=2\"; id \"STS(v4.0) Square Vacancy.075\"; cem 0.50; cee 0.07; ce 0.33;");
    pos.add(
        "r3r1k1/1b3p1p/3b2p1/p2Pp1P1/1pq1B3/4B3/PPP3QP/1K1R1R2 w - - bm Rf6; c0 \"Rf6=10, Bd3=2, b3=1, h4=3\"; id \"STS(v4.0) Square Vacancy.076\"; cem 1.44; cee 0.83; ce 1.44;");
    pos.add(
        "r4q1k/1p2b2r/7P/p1pPp3/8/P2PP1R1/1P4Q1/1K5R w - - bm Qe4; c0 \"Qe4=10, Rf1=5, Rg4=5, Rg7=5\"; id \"STS(v4.0) Square Vacancy.077\"; cem 1.70; cee -1.04; ce 0.76;");
    pos.add(
        "2b1q3/p4rbk/1p1R2pp/1P1N4/P2pPp2/B4P1P/4Q1P1/6K1 w - - bm Qc4; c0 \"Qc4=10, Nb4=5, Qd2=2, Qd3=2\"; id \"STS(v4.0) Square Vacancy.078\"; cem 1.57; cee 1.81; ce 1.76;");
    pos.add(
        "r1b2rk1/p6p/1p1P1pp1/q1p5/2P1PQ2/P7/1R2B1PP/5R1K b - - bm Qc3; c0 \"Qc3=10, Bb7=3, Bd7=1, Be6=2\"; id \"STS(v4.0) Square Vacancy.079\"; cem 0.75; cee 1.55; ce 0.90;");
    pos.add(
        "2b2kn1/5pp1/r2q3p/p2pN3/2pP1PP1/5P2/P1Q4P/1B2R2K w - - bm Qh7; c0 \"Qh7=10, Kg1=6, Qc3=6, g5=6\"; id \"STS(v4.0) Square Vacancy.080\"; cem 1.04; cee -0.82; ce 0.41;");
    pos.add(
        "2r1r3/1Rpp2pk/2n4p/p1PNqp2/P3p3/2P1P1P1/4QP1P/3R2K1 w - - bm Qh5; c0 \"Qh5=10, Nf4=5, Qa6=2, Rb5=2\"; id \"STS(v4.0) Square Vacancy.081\"; cem 0.69; cee 0.72; ce 0.79;");
    pos.add(
        "r1b1k2r/2q1pp2/1p5p/p1pPp1p1/2P5/3B3P/PP2QPP1/4RRK1 w kq - bm Qh5; c0 \"Qh5=10, Bc2=4, Qxe5=4, a3=4\"; id \"STS(v4.0) Square Vacancy.082\"; cem 1.49; cee -0.48; ce 0.98;");
    pos.add(
        "1rr3k1/pp3p1p/6p1/1PPB4/4P2q/2Q2P2/P4P1P/2R2K2 w - - bm Qe5; c0 \"Qe5=10, Kg1=3, Kg2=3, Rd1=3\"; id \"STS(v4.0) Square Vacancy.083\"; cem 0.51; cee 1.15; ce 0.92;");
    pos.add(
        "r2bn1k1/1p3pp1/pqb1p2p/4B3/2B2Q1P/2N2P2/PPP3P1/1K1R4 b - - bm Qf2; c0 \"Qf2=10, Bf6=1, Qc5=2, Rc8=1\"; id \"STS(v4.0) Square Vacancy.084\"; cem 1.15; cee 0.82; ce 0.97;");
    pos.add(
        "7r/q3kp2/2rp1p2/3RpPb1/1p2P1P1/1P1N4/P4R1P/1K2Q3 b - - bm Qa3; c0 \"Qa3=10, Kd7=2\"; id \"STS(v4.0) Square Vacancy.085\"; cem -1.20; cee 1.23; ce -0.55;");
    pos.add(
        "1n2r1k1/1q4pp/4pp2/p1R5/Pp1Q4/4B2P/1P3PP1/6K1 w - - bm Qd6; c0 \"Qd6=10, Qc4=5, Qd3=5, Qg4=4\"; id \"STS(v4.0) Square Vacancy.086\"; cem 0.61; cee 0.00; ce 0.38;");
    pos.add(
        "2r5/p4p1k/1p5p/2qPnN2/P2R4/1P4Pp/7P/3Q2K1 b - - bm Qc3; c0 \"Qc3=10, Qc1=2\"; id \"STS(v4.0) Square Vacancy.087\"; cem -4.12; cee -1.62; ce -2.87;");
    pos.add(
        "4r1k1/1rp2ppp/p3b3/1n1p4/1q1P1B2/1P3P1P/P1Q3P1/3RRNK1 w - - bm Qc6; c0 \"Qc6=10, Ne3=4, Qd2=5, Qd3=5, Qf2=3\"; id \"STS(v4.0) Square Vacancy.088\"; cem 0.63; cee 0.66; ce 0.71;");
    pos.add(
        "r1b1n2r/1p1pqpkp/p1n1p3/4P3/4NP2/3B3P/PPP3P1/R2Q1R1K w - - bm Qh5; c0 \"Qh5=10, Qg4+=7, c4=7, f5=7\"; id \"STS(v4.0) Square Vacancy.089\"; cem 1.45; cee -1.35; ce 1.28;");
    pos.add(
        "6rk/5Qp1/7p/4p2P/1p1rq3/6R1/PP6/K5R1 b - - bm Qc2; c0 \"Qc2=10, Qd5=3, Qe2=5, Qf4=5\"; id \"STS(v4.0) Square Vacancy.090\"; cem 0.24; cee -0.63; ce -0.25;");
    pos.add(
        "3r2k1/5pb1/5qpp/1Np5/8/1P3P1P/1PQ3P1/1K5R b - - bm Qg5; c0 \"Qg5=10, Qa6=3, Qh4=3, Rb8=4\"; id \"STS(v4.0) Square Vacancy.091\"; cem -1.75; cee -0.26; ce -1.05;");
    pos.add(
        "5k2/q7/2p5/4pppn/1pP2nN1/1P2RPPP/3r1B1K/4Q3 b - - bm Qd4; c0 \"Qd4=10, Nxg3=2, Rxf2+=4\"; id \"STS(v4.0) Square Vacancy.092\"; cem -2.94; cee -1.17; ce -2.33;");
    pos.add(
        "2q2rk1/p1P1bppp/Pn3n2/3p1b2/3P1B2/1P3N1P/4BPP1/2Q2RK1 w - - bm Qc6; c0 \"Qc6=10, Nh4=7, Qe3=7, g4=6\"; id \"STS(v4.0) Square Vacancy.093\"; cem -0.42; cee 0.65; ce -0.13;");
    pos.add(
        "5rk1/1Q3pp1/p2ppq1p/6b1/N2NP1P1/8/PP2K1P1/3R4 b - - bm Qf4; c0 \"Qf4=10, Qe5=5, a5=6, d5=6\"; id \"STS(v4.0) Square Vacancy.094\"; cem 0.53; cee 3.76; ce 1.92;");
    pos.add(
        "2k3r1/1b1n1p2/5n2/1Rp1q2p/1pPpP1p1/1N3P2/4B1PP/Q4RK1 w - - bm Qa7; c0 \"Qa7=10, Qa5=1, Rf2=2, fxg4=3\"; id \"STS(v4.0) Square Vacancy.095\"; cem 1.78; cee -0.41; ce 1.41;");
    pos.add(
        "r3r1k1/5pp1/p2p3p/2nPP3/p5q1/2P1RNP1/2Q2P1P/2R3K1 b - - bm Qc4; c0 \"Qc4=10, Rac8=3, Rad8=3\"; id \"STS(v4.0) Square Vacancy.096\"; cem 0.09; cee -0.69; ce -0.25;");
    pos.add(
        "7k/ppr1qpb1/1n2pN1p/4P1p1/8/1B3QP1/PP3PKP/4R3 w - - bm Qh5; c0 \"Qh5=10, Re2=3, Re3=1, h3=2\"; id \"STS(v4.0) Square Vacancy.097\"; cem 1.87; cee 1.34; ce 1.65;");
    pos.add(
        "3r2k1/R5p1/2b2npp/1pP5/4pP1P/6Pq/1Q2BP2/4B1K1 w - - bm Qe5; c0 \"Qe5=10, Bf1=2, Ra6=3, Rc7=6\"; id \"STS(v4.0) Square Vacancy.098\"; cem 2.48; cee 1.11; ce 2.04;");
    pos.add(
        "3r4/k1pr4/npR1p3/p3Pp1p/P1QP2pN/q5P1/6PP/1R5K b - - bm Qe3; c0 \"Qe3=10, Kb7=4, Qe7=5, f4=4\"; id \"STS(v4.0) Square Vacancy.099\"; cem 0.17; cee -1.22; ce -0.36;");
    pos.add(
        "R7/2q3k1/p1r2p1n/2p1pPp1/P3P3/2PP4/7Q/5BK1 w - - bm Qh5; c0 \"Qh5=10, Be2=1, Qb2=1, Qe2=1\"; id \"STS(v4.0) Square Vacancy.100\"; cem 3.86; cee 0.00; ce 1.90;");
    pos.add(
        "1b3rk1/5ppp/2p2rq1/1p1n4/3P2P1/1BPbBP2/1P1N2QP/R3R1K1 w - - bm Bxd5; c0 \"Bxd5=10, Ne4=6\"; id \"STS(v5.0) Bishop vs Knight.001\"; cem 0.09; cee 1.83; ce 0.21;");
    pos.add(
        "1k1r2r1/1p2bp2/4q2p/p1ppP3/6b1/2PQ2B1/PP2NPP1/R1R3K1 b - - bm Bxe2; c0 \"Bxe2=10, Bf5=3, Bg5=3, c4=4\"; id \"STS(v5.0) Bishop vs Knight.002\"; cem -1.87; cee -1.79; ce -1.95;");
    pos.add(
        "1q2n1k1/r4pb1/6p1/1bpPN1Pp/p1N1PP2/3B2KP/R3Q3/8 b - - bm Bxe5; c0 \"Bxe5=10\"; id \"STS(v5.0) Bishop vs Knight.003\"; cem 0.69; cee 1.22; ce 0.73;");
    pos.add(
        "1r1qr1k1/1b1nbpp1/ppnp3p/8/2P1PBB1/2N5/PPNQ2PP/2R2RK1 w - - bm Bxd7; c0 \"Bxd7=10, Be2=2, Ne3=3, Rcd1=3\"; id \"STS(v5.0) Bishop vs Knight.004\"; cem 0.35; cee 0.85; ce 0.45;");
    pos.add(
        "1r1r2k1/2qp1ppp/2pbpn2/8/3BP3/3B2P1/P1P2P1P/R2Q1RK1 w - - bm Bxf6; c0 \"Bxf6=10, Qd2=7, Qe1=6, a4=5\"; id \"STS(v5.0) Bishop vs Knight.005\"; cem 0.05; cee 0.09; ce 0.15;");
    pos.add(
        "1r1r2n1/1pb3pk/p1p3bp/4Np2/3P2P1/2N1RB1P/PP3PK1/4R3 w - - bm Nxg6; c0 \"Nxg6=10, Ne2=3, Rd1=2, d5=3\"; id \"STS(v5.0) Bishop vs Knight.006\"; cem -0.14; cee 0.07; ce 0.05;");
    pos.add(
        "1r2r1k1/2q3p1/3bp2p/p2nNp2/1ppP1P2/2P3Q1/PP1RN1PP/R5K1 b - - bm Bxe5; c0 \"Bxe5=10, Rb5=7, Rb7=6, a4=6\"; id \"STS(v5.0) Bishop vs Knight.007\"; cem -0.46; cee -0.44; ce -0.55;");
    pos.add(
        "1r2rbk1/1bqn1pp1/pp1p2np/3N1p2/2P1P3/PP2B1PB/4NQKP/2RR4 b - - bm Bxd5; c0 \"Bxd5=10, Qc6=6, Qc8=6, Qd8=3\"; id \"STS(v5.0) Bishop vs Knight.008\"; cem 0.36; cee 1.70; ce 0.26;");
    pos.add(
        "1r3bk1/1q2r1p1/pp1p3p/2nP2p1/3BP3/PP3QP1/4N1KP/2RR4 w - - bm Bxc5; c0 \"Bxc5=10, Bg1=5, Rc2=5, Re1=5\"; id \"STS(v5.0) Bishop vs Knight.009\"; cem 1.40; cee 1.85; ce 1.57;");
    pos.add(
        "1r4k1/2qnbpp1/r3p2p/3pP2b/1p1N4/3P1NP1/nBPQ1PBP/R3R1K1 b - - bm Bxf3; c0 \"Bxf3=10, Raa8=5, Rba8=6, Rbb6=5\"; id \"STS(v5.0) Bishop vs Knight.010\"; cem 0.35; cee -0.31; ce 0.25;");
    pos.add(
        "1r4k1/6pp/2b3n1/Nnq1p3/4Pp2/3B2PP/4QP1K/2B1R3 w - - bm Nxc6; c0 \"Nxc6=10, Bb2=2, Bc4+=3, Qa2+=6\"; id \"STS(v5.0) Bishop vs Knight.011\"; cem -0.04; cee 0.00; ce 0.07;");
    pos.add(
        "1rr4k/5p1p/4b1p1/pp1Nb3/4P3/PP1N1P2/K5RP/3R4 b - - bm Bxd5; c0 \"Bxd5=10, Bc3=4, Bd4=4, Bg7=6\"; id \"STS(v5.0) Bishop vs Knight.012\"; cem -0.24; cee -0.28; ce -0.36;");
    pos.add(
        "2b1rr2/4q1pk/p2ppb1p/1p2n3/4PNB1/P1N4Q/1PP3PP/R4R1K b - - bm Nxg4; c0 \"Nxg4=10, Bd7=4, Bg5=4, Qd7=3\"; id \"STS(v5.0) Bishop vs Knight.013\"; cem -1.45; cee -0.75; ce -1.53;");
    pos.add(
        "2b1rr2/4q1pk/p2ppb1p/1p5P/4PNQ1/P1N5/1PP3P1/R4R1K b - - bm Bxc3; c0 \"Bxc3=10, Bd4=4, Qc7=1, Rf7=1\"; id \"STS(v5.0) Bishop vs Knight.014\"; cem 0.92; cee 0.12; ce 0.69;");
    pos.add(
        "2b3k1/p3qpbp/2p3p1/1pn1p3/4P3/PBP1NN1P/1PQ2PP1/6K1 b - - bm Nxb3; c0 \"Nxb3=10, Qd6=6, a5=7, h6=6\"; id \"STS(v5.0) Bishop vs Knight.015\"; cem -0.67; cee -0.60; ce -0.73;");
    pos.add(
        "2k5/1p1nbqpp/pPprp1b1/P1p1p3/2P1P2N/4BP1P/1Q1N2P1/3R2K1 w - - bm Nxg6; c0 \"Nxg6=10, Bf2=7, Rb1=7, g3=7\"; id \"STS(v5.0) Bishop vs Knight.016\"; cem -0.06; cee 0.42; ce 0.15;");
    pos.add(
        "2k5/p7/1pp3r1/6b1/3P1p1p/P4NrP/1PP2R2/1K1R4 w - - bm Nxg5; c0 \"Nxg5=10, Rdf1=4, Re1=6, Rh1=6\"; id \"STS(v5.0) Bishop vs Knight.017\"; cem 0.98; cee -0.46; ce -0.00;");
    pos.add(
        "2kr1b1r/p1qn3p/Ppp3p1/4np2/8/2NBBP2/1PP3PP/RQ2R1K1 b - - bm Nxd3; c0 \"Nxd3=10, Bc5=4, Bd6=2, h5=2\"; id \"STS(v5.0) Bishop vs Knight.018\"; cem 1.12; cee 0.77; ce 1.01;");
    pos.add(
        "2kr3b/1pr2p1p/p1n1p1p1/2NpPn2/1P1P4/3RBP1B/1P2KP1P/6R1 w - - bm Bxf5; c0 \"Bxf5=10, Ra1=6, Rgd1=6, f4=5\"; id \"STS(v5.0) Bishop vs Knight.019\"; cem -1.15; cee -0.55; ce -0.77;");
    pos.add(
        "2krr3/pp1qbp2/4b3/1P2P2p/P1pPB1pN/4B1Q1/5PPP/3R2K1 b - - bm Bxh4; c0 \"Bxh4=10, c3=4\"; id \"STS(v5.0) Bishop vs Knight.020\"; cem 0.42; cee -2.59; ce -0.28;");
    pos.add(
        "2n2rk1/p2b2bp/3p2p1/q1pPp2n/P1P1P3/1QN5/3BBNPP/1R4K1 w - - bm Bxh5; c0 \"Bxh5=10, Qa2=5, Qb8=7, Qd1=5\"; id \"STS(v5.0) Bishop vs Knight.021\"; cem 1.62; cee 1.06; ce 1.65;");
    pos.add(
        "2q3k1/1b4r1/p2np2p/QnBpN3/3P1pPp/1P1BP3/8/R5K1 w - - bm Bxb5; c0 \"Bxb5=10, Qb6=1, Rf1=3, exf4=2\"; id \"STS(v5.0) Bishop vs Knight.022\"; cem -1.00; cee -0.94; ce -0.89;");
    pos.add(
        "2r1r1k1/1q1bp1bp/3p2p1/1pnP4/3NB3/1P2BP2/3Q2PP/1RR3K1 b - - bm Nxe4; c0 \"Nxe4=10, Rc7=1\"; id \"STS(v5.0) Bishop vs Knight.023\"; cem 0.23; cee 0.27; ce 0.13;");
    pos.add(
        "2r1r1k1/pp1q1pbp/2npbnp1/4p1N1/1P2P3/2P2N1P/P4PP1/R1BQRBK1 w - - bm Nxe6; c0 \"Nxe6=10, Bb2=4, Bd2=3, a3=4\"; id \"STS(v5.0) Bishop vs Knight.024\"; cem 0.37; cee -0.59; ce 0.46;");
    pos.add(
        "2r2nk1/1b3ppp/p2q1n2/1p1p4/3B1P1Q/1P1NP1P1/P5BP/2R3K1 w - - bm Bxf6; c0 \"Bxf6=10, Bc5=2, Rxc8=7, f5=4\"; id \"STS(v5.0) Bishop vs Knight.025\"; cem 2.48; cee 1.57; ce 2.34;");
    pos.add(
        "2r2rk1/4bp2/p6p/1p1R4/3nn1p1/4P1B1/PP1NBPPP/4R1K1 b - - bm Nxe2+; c0 \"Nxe2+=10, Nc2=4, Rc1=6\"; id \"STS(v5.0) Bishop vs Knight.026\"; cem 3.25; cee 1.69; ce 2.42;");
    pos.add(
        "2r2rk1/p2qbppp/5n2/3p1N2/1p2n3/1NP1BQ2/PP3PPP/R4RK1 w - - bm Nxe7+; c0 \"Nxe7+=10, Bd4=2, Rfe1=2, cxb4=4\"; id \"STS(v5.0) Bishop vs Knight.027\"; cem 0.93; cee 0.00; ce 1.00;");
    pos.add(
        "2r5/2q2pk1/3n1b1p/3Pp1p1/b1B1P1N1/P4N2/5PPP/2Q2RK1 w - - bm Nxf6; c0 \"Nxf6=10\"; id \"STS(v5.0) Bishop vs Knight.028\"; cem 1.07; cee 0.58; ce 1.04;");
    pos.add(
        "2r5/3n2k1/5p2/Pp2nB1r/1B1pP2P/P1pP2P1/6K1/3R2R1 w - - bm Bxd7; c0 \"Bxd7=10, Be6=5, Bh3=5, Rdf1=3\"; id \"STS(v5.0) Bishop vs Knight.029\"; cem 1.77; cee 1.70; ce 1.82;");
    pos.add(
        "2rq1rk1/1b2bppp/p3p3/n7/3P4/1BBQ1N1P/P4PP1/R3R1K1 b - - bm Nxb3; c0 \"Nxb3=10, Bf6=3, Bxf3=2, Qc7=2\"; id \"STS(v5.0) Bishop vs Knight.030\"; cem -0.85; cee -0.03; ce -0.93;");
    pos.add(
        "2rq1rk1/1p1npp1p/p2pb1p1/3N4/3QP3/1BP2P2/PP4PP/R4R1K b - - bm Bxd5; c0 \"Bxd5=10, Nc5=6, a5=5, b5=5\"; id \"STS(v5.0) Bishop vs Knight.031\"; cem 1.55; cee 1.32; ce 1.42;");
    pos.add(
        "2rq1rk1/1p1nppbp/p2pn1p1/6B1/2P1P3/1PN1Q1PP/P4PB1/2R2RK1 b - - bm Nxg5; c0 \"Nxg5=10, Bd4=1, Bxc3=1, Re8=4\"; id \"STS(v5.0) Bishop vs Knight.032\"; cem -0.69; cee -0.33; ce -0.77;");
    pos.add(
        "2rq1rk1/3bppbp/p4np1/n3Q3/Np6/1B3N1P/PPP2PP1/R1BR2K1 b - - bm Nxb3; c0 \"Nxb3=10, Nc4=7, Qc7=7, e6=7\"; id \"STS(v5.0) Bishop vs Knight.033\"; cem -0.42; cee -0.89; ce -0.52;");
    pos.add(
        "2rq1rk1/4bppp/p1p1pn2/1p1b4/3P4/2NB1NQ1/PPP2PPP/1K1RR3 b - - bm Bxf3; c0 \"Bxf3=10, Bd6=3, Nh5=3, a5=2\"; id \"STS(v5.0) Bishop vs Knight.034\"; cem -0.40; cee -0.41; ce -0.50;");
    pos.add(
        "2rq1rk1/pp1bppbp/3p1np1/8/2nNP3/1BN1BP2/PPP2QPP/R4RK1 w - - bm Bxc4; c0 \"Bxc4=10, Bc1=5, Nd1=7, Nde2=7\"; id \"STS(v5.0) Bishop vs Knight.035\"; cem -0.79; cee -0.52; ce -0.69;");
    pos.add(
        "2rqr1k1/1b3p1p/pn1p1bpB/2p5/3P4/1P3N1P/P2QBPP1/2RR2K1 b - - bm Bxf3; c0 \"Bxf3=10, Nd7=5, Qc7=6, cxd4=6\"; id \"STS(v5.0) Bishop vs Knight.036\"; cem -0.40; cee 0.00; ce -0.49;");
    pos.add(
        "2rqr1k1/1b3ppp/3b2n1/p2pN3/1p1P1B2/3BPP1P/P2Q2P1/1R1R2K1 b - - bm Nxf4; c0 \"Nxf4=10, Bxe5=4, Nxe5=2, Rc3=2\"; id \"STS(v5.0) Bishop vs Knight.037\"; cem -0.15; cee -0.30; ce -0.25;");
    pos.add(
        "2rr2k1/ppq1pp1p/2npbnpB/8/2PNP3/1QP2P2/P3B1PP/R4RK1 w - - bm Nxe6; c0 \"Nxe6=10, Qa4=4, Rab1=3, Rfb1=4\"; id \"STS(v5.0) Bishop vs Knight.038\"; cem 0.56; cee 0.13; ce 0.64;");
    pos.add(
        "2rr4/p3q1pk/bn2p2p/2b1Pp2/1pN4P/1P4Q1/P2BBPP1/2RR2K1 b - - bm Bxc4; c0 \"Bxc4=10, Bd4=4, Rc7=3, Rd4=6\"; id \"STS(v5.0) Bishop vs Knight.039\"; cem 1.34; cee 0.66; ce 1.23;");
    pos.add(
        "3b1k2/1b3p1p/pP4p1/3p4/1p1PnBP1/1K3B2/PP2N2P/8 w - - bm Bxe4; c0 \"Bxe4=10, Be3=2, h3=2\"; id \"STS(v5.0) Bishop vs Knight.040\"; cem -0.09; cee 1.92; ce 1.86;");
    pos.add(
        "3qr2k/p5r1/1pRp1n1b/1P1Pp2p/1P3p1P/2N1nP1Q/1R3BP1/5BK1 w - - bm Bxe3; c0 \"Bxe3=10\"; id \"STS(v5.0) Bishop vs Knight.041\"; cem -1.35; cee -0.86; ce -1.24;");
    pos.add(
        "3r1rk1/1b2p1bp/pp6/4Pp1n/B2n4/2NNBP1p/PP3KPP/3RR3 w - - bm Bxd4; c0 \"Bxd4=10, Bb3+=4, Ne2=6, gxh3=3\"; id \"STS(v5.0) Bishop vs Knight.042\"; cem 0.19; cee 1.01; ce 0.55;");
    pos.add(
        "3r2k1/1b3ppp/1p2pq2/p7/2P5/1Pn1QNP1/4PPBP/4R1K1 b - - bm Bxf3; c0 \"Bxf3=10, Rd6=6, a4=7, b5=6\"; id \"STS(v5.0) Bishop vs Knight.043\"; cem -0.83; cee -0.21; ce -0.68;");
    pos.add(
        "3r2k1/5p1p/p2qn1p1/P3N1r1/1P1pb1B1/2nN3P/5PPQ/2R1R2K b - - bm Bxd3; c0 \"Bxd3=10\"; id \"STS(v5.0) Bishop vs Knight.044\"; cem 0.86; cee 0.20; ce 0.74;");
    pos.add(
        "3r2k1/pr2bpp1/6bp/3nN3/2R3P1/4B2P/P3BP2/3R2K1 b - - bm Nxe3; c0 \"Nxe3=10, Bf6=7, Kh7=7, Nb6=8\"; id \"STS(v5.0) Bishop vs Knight.045\"; cem -0.53; cee 0.07; ce -0.35;");
    pos.add(
        "3rk2r/1p2bp2/p1n1q2p/3pPbp1/3B4/2P3Q1/BN3PPP/R3R1K1 b k - bm Nxd4; c0 \"Nxd4=10, O-O=3, Rd7=3, h5=2\"; id \"STS(v5.0) Bishop vs Knight.046\"; cem -0.47; cee -2.67; ce -0.60;");
    pos.add(
        "3rr1k1/p1p1p1bp/2p2pp1/3bP3/5P2/BPP2N2/P5PP/4RRK1 b - - bm Bxf3; c0 \"Bxf3=10\"; id \"STS(v5.0) Bishop vs Knight.047\"; cem -0.01; cee 0.49; ce 0.20;");
    pos.add(
        "3rr3/p3b1kp/2p2pp1/1q1npb2/4N1PB/1NP2Q2/PP3P1P/R2R2K1 b - - bm Bxe4; c0 \"Bxe4=10, Bc8=6, Bd7=4, Bxg4=6\"; id \"STS(v5.0) Bishop vs Knight.048\"; cem -0.63; cee -1.18; ce -0.74;");
    pos.add(
        "4qr1k/2bn1p1p/2N1p3/p2nP3/NpRQ1B2/1P4PP/P6K/8 b - - bm Nxf4; c0 \"Nxf4=10, Qc8=2, Rg8=2, h5=1\"; id \"STS(v5.0) Bishop vs Knight.049\"; cem -0.25; cee 0.00; ce -0.28;");
    pos.add(
        "4r1k1/p4bp1/1p1p1p2/1P1N2n1/4P1P1/4qP1P/P4RQ1/5BK1 b - - bm Bxd5; c0 \"Bxd5=10, Qc5=2, Qd4=6, Qe1=5\"; id \"STS(v5.0) Bishop vs Knight.050\"; cem -1.99; cee 0.00; ce -1.30;");
    pos.add(
        "4r1k1/p4p1p/1p2q1pB/1Q2Pn2/P3B2P/5P2/bR3P2/6K1 w - - bm Bxf5; c0 \"Bxf5=10, Bf4=7, Rxa2=7, a5=7\"; id \"STS(v5.0) Bishop vs Knight.051\"; cem -0.34; cee -0.05; ce -0.12;");
    pos.add(
        "4r1k1/pb6/2pbpp1B/2N2p2/3P4/1P6/P4KPP/R7 b - - bm Bxc5; c0 \"Bxc5=10, Ba8=2, Re7=5\"; id \"STS(v5.0) Bishop vs Knight.052\"; cem -0.29; cee 0.84; ce 0.56;");
    pos.add(
        "4r3/1p2rpk1/p1p1bqp1/2QnN1bp/1B1PB3/P6P/1P2RPP1/4R1K1 b - - bm Nxb4; c0 \"Nxb4=10, Bf4=4, Bh4=4, h4=5\"; id \"STS(v5.0) Bishop vs Knight.053\"; cem 0.57; cee 0.23; ce 0.46;");
    pos.add(
        "4r3/5pkp/2b1n1p1/3p3n/p1pP4/P1N1BP1B/1P3KPP/2R5 w - - bm Bxe6; c0 \"Bxe6=10, Ne2=7, g3=8, g4=5\"; id \"STS(v5.0) Bishop vs Knight.054\"; cem 0.46; cee 1.19; ce 1.06;");
    pos.add(
        "4rrk1/2q3pp/pp1p1bn1/2nR4/2P2B2/N4PP1/PP3Q1P/3R1BK1 b - - bm Nxf4; c0 \"Nxf4=10, Be7=6, Nb7=6\"; id \"STS(v5.0) Bishop vs Knight.055\"; cem 1.07; cee 1.05; ce 0.97;");
    pos.add(
        "5k2/p2b3r/1ppb1p2/1q1p1P2/1P1PrNpP/4P1P1/1RQ2BK1/2R5 b - - bm Bxf4; c0 \"Bxf4=10, Bb8=2\"; id \"STS(v5.0) Bishop vs Knight.056\"; cem 1.55; cee -0.83; ce 1.08;");
    pos.add(
        "5k2/p2q2p1/bp1p1p1p/3PnP1Q/1P1BP3/2P5/3N2P1/6K1 w - - bm Bxe5; c0 \"Bxe5=10, Kh2=7, Qd1=8, Qh3=7\"; id \"STS(v5.0) Bishop vs Knight.057\"; cem 0.50; cee -0.39; ce 0.10;");
    pos.add(
        "6k1/r2rbpp1/p1q2n2/1pp2PB1/5Q2/1PN1R2P/1P3P2/4R1K1 w - - bm Bxf6; c0 \"Bxf6=10, R3e2=2\"; id \"STS(v5.0) Bishop vs Knight.058\"; cem -0.04; cee -0.48; ce -0.01;");
    pos.add(
        "6k1/r6p/2pb1ppn/p1Np4/1P1PrPP1/P6P/2R5/3K1RB1 b - - bm Bxc5; c0 \"Bxc5=10, Re8=1, Ree7=5\"; id \"STS(v5.0) Bishop vs Knight.059\"; cem -0.74; cee -0.32; ce -0.58;");
    pos.add(
        "6n1/p6k/2R5/2p1rp2/p1P1n3/P3PB1P/5PP1/6K1 w - - bm Bxe4; c0 \"Bxe4=10, Bd1=1, Be2=4, Ra6=1\"; id \"STS(v5.0) Bishop vs Knight.060\"; cem 0.07; cee -1.66; ce -1.42;");
    pos.add(
        "6r1/r2Nbnk1/2R3pp/p4p2/PpB2PP1/1P6/6K1/3R4 w - - bm Bxf7; c0 \"Bxf7=10, Be6=1, Re6=4, gxf5=3\"; id \"STS(v5.0) Bishop vs Knight.061\"; cem 0.26; cee 0.00; ce 0.20;");
    pos.add(
        "8/1R3pp1/p3rnk1/3pBb1p/3P3P/1B1P1PP1/5K2/8 w - - bm Bxf6; c0 \"Bxf6=10, Ba4=3, Ke2=4, Ke3=4\"; id \"STS(v5.0) Bishop vs Knight.062\"; cem 1.19; cee 0.91; ce 1.05;");
    pos.add(
        "8/5p1k/2p3pp/2p1p3/2P2nPP/2Q2P2/1P1B1K2/q7 w - - bm Bxf4; c0 \"Bxf4=10, Qa3=7, b3=7, h5=7\"; id \"STS(v5.0) Bishop vs Knight.063\"; cem -6.99; cee -0.76; ce -2.17;");
    pos.add(
        "8/6k1/4ppp1/r2Pn2p/bpq1PB1P/6P1/R4PK1/1QN5 w - - bm Bxe5; c0 \"Bxe5=10, Nb3=1, Rb2=1, dxe6=5\"; id \"STS(v5.0) Bishop vs Knight.064\"; cem 0.46; cee -0.37; ce 0.26;");
    pos.add(
        "8/r2b1nk1/Np1qp2p/pP1pNrpP/Pp1P4/3Q1PP1/2R3K1/7R w - - bm Nxd7; c0 \"Nxd7=10\"; id \"STS(v5.0) Bishop vs Knight.065\"; cem 1.02; cee 0.14; ce 0.97;");
    pos.add(
        "q1r1r1k1/p2nppbp/1pnpb1p1/6N1/1PP1P3/P1N1Q2P/4BPPB/1R1R2K1 w - - bm Nxe6; c0 \"Nxe6=10\"; id \"STS(v5.0) Bishop vs Knight.066\"; cem 1.06; cee 1.13; ce 1.15;");
    pos.add(
        "q5k1/1pbb2pn/2ppNr1p/p3rP2/P1P1pNB1/1P5P/5PP1/1RQR2K1 b - - bm Bxe6; c0 \"Bxe6=10, Qb8=6, Qc8=5, Rfxe6=6\"; id \"STS(v5.0) Bishop vs Knight.067\"; cem 2.31; cee 1.32; ce 2.19;");
    pos.add(
        "r1b1r1k1/p1qn1ppp/2pb1n2/4pN2/Np2P3/7P/PPQ1BPP1/R1B2RK1 w - - bm Nxd6; c0 \"Nxd6=10, Be3=1, Rd1=1\"; id \"STS(v5.0) Bishop vs Knight.068\"; cem -0.03; cee -1.22; ce 0.07;");
    pos.add(
        "r1b1r1k1/ppq2ppp/2n5/3p4/P1pPn3/B1P1PN2/2B2PPP/R2Q1RK1 w - - bm Bxe4; c0 \"Bxe4=10, Bb2=7, Qb1=6, Qe1=4\"; id \"STS(v5.0) Bishop vs Knight.069\"; cem -0.49; cee -0.62; ce -0.39;");
    pos.add(
        "r1b2rk1/p3qppp/1pn5/2b5/3pN3/5NP1/PP2PPBP/R2Q1RK1 w - - bm Nxc5; c0 \"Nxc5=10, Ne1=1, Nfd2=1, Qd3=1\"; id \"STS(v5.0) Bishop vs Knight.070\"; cem -0.15; cee 0.08; ce -0.04;");
    pos.add(
        "r1bq2k1/pp3rbp/2n1p1p1/1BBpPn2/8/2N2N1P/PP3PP1/R2Q1RK1 w - - bm Bxc6; c0 \"Bxc6=10, Qe1=3, Qe2=3, Re1=1\"; id \"STS(v5.0) Bishop vs Knight.071\"; cem 0.03; cee -0.41; ce 0.13;");
    pos.add(
        "r1br4/pp3pk1/2p2npp/2n1N3/P2q4/2NB2QP/1PP2PP1/R3R1K1 b - - bm Nxd3; c0 \"Nxd3=10, Nh5=1, g5=5, h5=7\"; id \"STS(v5.0) Bishop vs Knight.072\"; cem 1.65; cee 0.51; ce 1.53;");
    pos.add(
        "r1r3k1/1nqb1ppp/3p4/pp1Pb3/1P2Pp2/R1PB1N1P/Q4PP1/1N2R1K1 w - - bm Nxe5; c0 \"Nxe5=10, Be2=2, Bf1=2, Rd1=3\"; id \"STS(v5.0) Bishop vs Knight.073\"; cem 0.55; cee -0.52; ce 0.62;");
    pos.add(
        "r1r3k1/1p1b1p2/1q1p3p/1NpPb1pB/PpP1Pp2/1P6/R4PPP/1Q1R3K b - - bm Bxb5; c0 \"Bxb5=10\"; id \"STS(v5.0) Bishop vs Knight.074\"; cem -0.75; cee -1.49; ce -0.96;");
    pos.add(
        "r1r4k/pb1nq1pp/1p1bp3/1N1p1p2/3Pn3/1P1NB1P1/PQ2PPBP/R4RK1 w - - bm Nxd6; c0 \"Nxd6=10, Rac1=7, Rfc1=7, a4=7\"; id \"STS(v5.0) Bishop vs Knight.075\"; cem 0.53; cee 0.00; ce 0.63;");
    pos.add(
        "r2q1rk1/2pb2b1/1p1p2pp/p1nP1p1n/2P5/2N1B1P1/PPQ1B2P/R3NRK1 w - - bm Bxh5; c0 \"Bxh5=10\"; id \"STS(v5.0) Bishop vs Knight.076\"; cem -1.97; cee -1.16; ce -1.88;");
    pos.add(
        "r2q1rk1/pb3pbp/1pn1p1p1/2pnN3/2N2B2/3P2P1/PPP2PBP/R2QR1K1 b - - bm Nxf4; c0 \"Nxf4=10, Nce7=2, Nd4=2, Rc8=2\"; id \"STS(v5.0) Bishop vs Knight.077\"; cem -0.12; cee 0.54; ce -0.22;");
    pos.add(
        "r2q1rk1/pppb1ppp/1b1p2n1/3Pp3/2N1P1n1/2PB1N2/PP3PPP/R1BQR1K1 w - - bm Nxb6; c0 \"Nxb6=10, Be3=4, Re2=1, Rf1=6\"; id \"STS(v5.0) Bishop vs Knight.078\"; cem -0.72; cee -0.71; ce -0.62;");
    pos.add(
        "r2r2k1/1p2qppp/2n1bn2/p2Bp1N1/2P5/R5P1/1P1NPP1P/1Q1R2K1 w - - bm Nxe6; c0 \"Nxe6=10, Bxc6=2, Bxe6=3\"; id \"STS(v5.0) Bishop vs Knight.079\"; cem -0.33; cee -0.15; ce -0.23;");
    pos.add(
        "r2r2k1/1pq2pp1/p1b1p2p/3n4/8/1QB3P1/PP2PPBP/R3R1K1 b - - bm Nxc3; c0 \"Nxc3=10, Qd7=7, Rac8=8, a5=7\"; id \"STS(v5.0) Bishop vs Knight.080\"; cem -0.23; cee 0.00; ce -0.28;");
    pos.add(
        "r2r2k1/ppp2pb1/2n1p1pp/1B2P3/3PNn1q/2Q1BP2/PP3P1P/2R1K2R w K - bm Bxf4; c0 \"Bxf4=10, Bc4=7, Bxc6=6, Rd1=6\"; id \"STS(v5.0) Bishop vs Knight.081\"; cem -1.85; cee -0.74; ce -1.73;");
    pos.add(
        "r3k2r/ppqbnpb1/n1p1p2p/P2pP1p1/3P4/1P1BBN1P/1NPQ1PP1/R3K2R w KQkq - bm Bxa6; c0 \"Bxa6=10\"; id \"STS(v5.0) Bishop vs Knight.082\"; cem 1.20; cee 0.58; ce 1.29;");
    pos.add(
        "r3kb1B/1p1b3p/pqnpp1p1/7n/8/2NRp3/PPP1B3/1K1RQ3 w q - bm Bxh5; c0 \"Bxh5=10, Ne4=2\"; id \"STS(v5.0) Bishop vs Knight.083\"; cem -1.22; cee -3.06; ce -1.23;");
    pos.add(
        "r3qrk1/1pp2bp1/p1n1p2p/2Pp1pbP/1P1P4/P1NBPN2/2Q2PP1/2R1K2R w K - bm Nxg5; c0 \"Nxg5=10, Ne2=7, Rd1=7, b5=2\"; id \"STS(v5.0) Bishop vs Knight.084\"; cem 0.80; cee 1.82; ce 0.92;");
    pos.add(
        "r3r1k1/p1q3bp/2p1bpp1/2nnp3/2N1N3/2PB2B1/PPQ2PPP/R3R1K1 b - - bm Nxd3; c0 \"Nxd3=10, Bf8=1, Nb7=1, Nxe4=2\"; id \"STS(v5.0) Bishop vs Knight.085\"; cem 0.51; cee 0.05; ce 0.42;");
    pos.add(
        "r3r1k1/pp3pbp/2p3p1/P3p3/1n2P1n1/2N1B3/1PPR1PPP/R3N1K1 b - - bm Nxe3; c0 \"Nxe3=10, Bf8=4, Na6=4, f6=3\"; id \"STS(v5.0) Bishop vs Knight.086\"; cem -0.28; cee 0.25; ce -0.13;");
    pos.add(
        "r3r1k1/ppq2pb1/2p1b1p1/P3n2p/2PN1B2/7P/1P1QBPP1/R2R2K1 w - - bm Nxe6; c0 \"Nxe6=10, Ra3=8, Rac1=7, a6=7\"; id \"STS(v5.0) Bishop vs Knight.087\"; cem 0.85; cee 0.47; ce 0.94;");
    pos.add(
        "r3r2k/6p1/q3pp1p/1b1pn2P/3Q2B1/1P6/1BP2PP1/1K1RR3 b - - bm Nxg4; c0 \"Nxg4=10, Nc6=1, Nf7=1, Qa2+=5\"; id \"STS(v5.0) Bishop vs Knight.088\"; cem -3.75; cee 0.65; ce -3.15;");
    pos.add(
        "r3rbk1/1p3pp1/1qp1b2p/p1Nn4/2Rp4/P2P1BP1/1P1BPP1P/2Q1R1K1 b - - bm Bxc5; c0 \"Bxc5=10, Bd6=4, Be7=5, Nc7=3\"; id \"STS(v5.0) Bishop vs Knight.089\"; cem 0.26; cee -0.14; ce 0.15;");
    pos.add(
        "r4r1k/1p2b1pp/pBq3n1/2PNp3/2N1Ppn1/1Q4P1/PP1R3P/5RK1 w - - bm Nxe7; c0 \"Nxe7=10, Nd6=3, Qa3=3, Rc2=4\"; id \"STS(v5.0) Bishop vs Knight.090\"; cem 0.61; cee 0.64; ce 0.71;");
    pos.add(
        "r4r2/p1n1q1bk/6pp/1pp2b2/2P1p3/P3N1P1/R2QPPBP/2BR2K1 w - - bm Nxf5; c0 \"Nxf5=10, Qa5=6, Qc2=2, Qd6=4\"; id \"STS(v5.0) Bishop vs Knight.091\"; cem 0.78; cee -0.46; ce 0.86;");
    pos.add(
        "r4r2/p3pp1k/1nR1b2p/5Np1/4P3/5NP1/Pb3PBP/5RK1 b - - bm Bxf5; c0 \"Bxf5=10, Rfc8=1\"; id \"STS(v5.0) Bishop vs Knight.092\"; cem -0.32; cee -0.50; ce -0.50;");
    pos.add(
        "r4rk1/1bq2pp1/1p1bpn1p/p2nN3/P1BP4/5NP1/1P1BQPKP/R3R3 b - - bm Bxe5; c0 \"Bxe5=10, Nb4=2, Ne7=3, Qe7=2\"; id \"STS(v5.0) Bishop vs Knight.093\"; cem -0.33; cee 1.01; ce -0.42;");
    pos.add(
        "r4rk1/1p2bppp/p2p1nn1/3P4/2PN1B2/8/PP2B1PP/R4R1K b - - bm Nxf4; c0 \"Nxf4=10, Ne4=1, Rae8=1\"; id \"STS(v5.0) Bishop vs Knight.094\"; cem 0.42; cee 0.91; ce 0.55;");
    pos.add(
        "r4rk1/1ppbq1bp/1n4p1/p1NPpp2/1n6/1PN1P1PP/PB3PB1/R2Q1RK1 w - - bm Nxd7; c0 \"Nxd7=10, N5a4=5, Nxb7=5, d6=4\"; id \"STS(v5.0) Bishop vs Knight.095\"; cem 1.23; cee -0.11; ce 1.33;");
    pos.add(
        "r4rk1/ppq1ppbp/1np1P1p1/2Nb4/Pn6/3B1N1P/1PP1QPP1/R1B1R1K1 b - - bm Nxd3; c0 \"Nxd3=10, Bxf3=6, Qd6=6, f5=6\"; id \"STS(v5.0) Bishop vs Knight.096\"; cem 0.67; cee 0.70; ce 0.57;");
    pos.add(
        "r5k1/pp1n1rpp/2pb1q2/2N5/1P3B2/P2P2P1/3Q1PKP/1R2R3 b - - bm Bxc5; c0 \"Bxc5=10, Bxf4=4, Nxc5=5, Qd4=7\"; id \"STS(v5.0) Bishop vs Knight.097\"; cem 2.52; cee 2.44; ce 2.41;");
    pos.add(
        "r5k1/pp2q1bp/n2p2p1/2pPpr2/2P1Nn2/PQ3N1P/1P1B1PP1/1R2R1K1 w - - bm Bxf4; c0 \"Bxf4=10, Kh2=7, Rbd1=6, g4=6\"; id \"STS(v5.0) Bishop vs Knight.098\"; cem 2.39; cee 1.74; ce 2.47;");
    pos.add(
        "r6r/4bkpp/2p1np2/p1p5/2P1NB2/6P1/PP2P2P/3R1R1K b - - bm Nxf4; c0 \"Nxf4=10\"; id \"STS(v5.0) Bishop vs Knight.099\"; cem 0.06; cee -0.70; ce -0.50;");
    pos.add(
        "rnq1k2r/p3ppbp/1p2bnp1/2p3N1/4P3/3BB2P/P1QN1PP1/1R3RK1 w kq - bm Nxe6; c0 \"Nxe6=10, Ndf3=4, Rfd1=4, f4=4\"; id \"STS(v5.0) Bishop vs Knight.100\"; cem 2.63; cee -0.48; ce 2.73;");
    pos.add(
        "1k1r1r2/p1p5/Bpnbb3/3p2pp/3P4/P1N1NPP1/1PP4P/2KR1R2 w - - bm Ncxd5; id \"STS(v6.0) Recapturing.001\"; c0 \"Ncxd5=10, Nb5=4, Ne2=3, Nexd5=6\"; cem 0.30; cee 0.89; ce 0.67;");
    pos.add(
        "1k1r4/4rp2/1p1qbnp1/p2p3p/P2P4/1Nn2P2/1P1QBRPP/2R3K1 w - - bm Rxc3; id \"STS(v6.0) Recapturing.002\"; c0 \"Rxc3=10, Qxc3=5, Re1=8, bxc3=7\"; cem -3.62; cee -4.50; ce -3.61;");
    pos.add(
        "1k1rr3/pp2qpp1/1b2p3/4N2p/2R1n2P/P2R2P1/1PP1QP2/1K6 w - - bm Rxe4; id \"STS(v6.0) Recapturing.003\"; c0 \"Rxe4=10, Ng6=7, Rxd8+=3\"; cem -1.95; cee -3.29; ce -2.17;");
    pos.add(
        "1k3rn1/3r1p2/p2p1np1/Pp1P3p/1Pp1PP2/2P1R1bP/2B2K1B/6R1 w - - bm Bxg3; id \"STS(v6.0) Recapturing.004\"; c0 \"Bxg3=10, Kxg3=5, Rexg3=5, Rgxg3=3\"; cem -4.01; cee -3.59; ce -3.69;");
    pos.add(
        "1q2rnk1/5rb1/bp1p1np1/pNpP2Bp/P1P1Pp1P/3B2P1/3QNRR1/7K w - - bm gxf4; id \"STS(v6.0) Recapturing.005\"; c0 \"gxf4=10, Bxf4=8, Bxf6=7, Rxf4=7\"; cem -1.03; cee -0.91; ce -0.94;");
    pos.add(
        "1q4k1/1r3pp1/3p1n1p/2pPpP2/b1PnP3/rPB1R1NP/3Q2PK/1R3B2 b - - bm Bxb3; id \"STS(v6.0) Recapturing.006\"; c0 \"Bxb3=10, Raxb3=2, Rbxb3=3\"; cem 2.08; cee 1.07; ce 1.96;");
    pos.add(
        "1qr2rk1/4ppbp/6p1/pp1b4/2NP4/4B3/PPB2PPP/2RQR2K b - - bm bxc4; id \"STS(v6.0) Recapturing.007\"; c0 \"bxc4=10, Bxc4=3, Rfd8=6, Rxc4=5\"; cem 1.86; cee 2.73; ce 1.84;");
    pos.add(
        "1qrr3k/6p1/1p1pp2p/pNn5/Pn1bP1PP/5Q2/1PP1N3/1K1R2R1 w - - bm Nexd4; id \"STS(v6.0) Recapturing.008\"; c0 \"Nexd4=10, Nbxd4=4, Rg2=5\"; cem -4.11; cee -4.30; ce -4.03;");
    pos.add(
        "1r1q2k1/5pp1/2bp2rp/p1pNp2n/2PBP3/1P1B1P2/P2Q2PP/1R3RK1 b - - bm cxd4; id \"STS(v6.0) Recapturing.009\"; c0 \"cxd4=10, Bxd5=8, Ng3=8, exd4=7\"; cem 4.33; cee 5.19; ce 4.31;");
    pos.add(
        "1r1r2k1/5pb1/6p1/p1pb2Pp/PpB1PR2/7P/1PP5/2KR4 w - - bm exd5; id \"STS(v6.0) Recapturing.010\"; c0 \"exd5=10, Ba6=6, Bxd5=2\"; cem -5.05; cee -4.63; ce -4.67;");
    pos.add(
        "1r2r1k1/pb1n1pp1/1p1p2np/2P1p1q1/P1P1P3/2P2PP1/2QNBB1P/1R1R3K b - - bm Nxc5; id \"STS(v6.0) Recapturing.011\"; c0 \"Nxc5=10, Bc6=8, bxc5=7, dxc5=7\"; cem 0.27; cee 0.24; ce 0.17;");
    pos.add(
        "1r2r2k/1p3pbp/b5p1/p2np3/P1P1B1PP/1PN2P2/6K1/3R1R2 w - - bm Nxd5; id \"STS(v6.0) Recapturing.012\"; c0 \"Nxd5=10, Bxd5=4, Rxd5=5, cxd5=4\"; cem -2.76; cee -2.82; ce -2.69;");
    pos.add(
        "1r2rbk1/1b1q1p2/3pn1p1/1p5p/1p2P2P/P1P1BPP1/4QNBK/2R1R3 w - - bm axb4; id \"STS(v6.0) Recapturing.013\"; c0 \"axb4=10, Qa2=1, Qb2=2, cxb4=6\"; cem 0.45; cee 1.27; ce 0.55;");
    pos.add(
        "1r3nk1/3r1qb1/bp1p1np1/pNpP2Bp/P1P1Pp1P/7N/1P1QBR2/6RK w - - bm Rxf4; id \"STS(v6.0) Recapturing.014\"; c0 \"Rxf4=10, Bf3=1, Nc3=1, Qxf4=2\"; cem -0.95; cee -0.59; ce -0.85;");
    pos.add(
        "1r3r2/1b4bk/p1n2ppp/qp1p4/4PN2/1B2B1P1/P3QP2/1R1R2K1 w - - bm exd5; id \"STS(v6.0) Recapturing.015\"; c0 \"exd5=10, Bc5=3, Ne6=6, Nxg6=3\"; cem -0.76; cee -1.42; ce -0.67;");
    pos.add(
        "1r3rk1/p1qnppbp/p5p1/2pPP3/2P5/4R2P/PP2Q1P1/1RBN3K b - - bm Bxe5; id \"STS(v6.0) Recapturing.016\"; c0 \"Bxe5=10, Nxe5=8, Rb7=8, Rbe8=8\"; cem 0.44; cee -0.35; ce 0.21;");
    pos.add(
        "1r4k1/1rq2ppp/3p1n2/2pPp3/p1PnP3/PPB4P/3Q1PP1/1R2RBK1 b - - bm axb3; id \"STS(v6.0) Recapturing.017\"; c0 \"axb3=10, Nd7=4, Nxb3=3, Rxb3=8\"; cem 1.99; cee 0.43; ce 1.63;");
    pos.add(
        "1r4k1/pr3ppp/q2p1n2/2pPp3/b1PnP3/PPB3NP/3Q1PP1/1R2RBK1 b - - bm Nxb3; id \"STS(v6.0) Recapturing.018\"; c0 \"Nxb3=10, Bxb3=7, Nd7=7, Rxb3=6\"; cem 3.50; cee 1.98; ce 3.37;");
    pos.add(
        "1rq2rk1/4bp2/2np2p1/p1p1p3/P1PNP1P1/1PB2P2/1Q3K1P/R2R1N2 b - - bm exd4; id \"STS(v6.0) Recapturing.019\"; c0 \"exd4=10, Bh4+=7, Nxd4=5, cxd4=6\"; cem 3.38; cee 6.43; ce 3.56;");
    pos.add(
        "1rq2rk1/4bp2/2np2p1/p1p1p3/P1PNP1P1/1PB2P2/1Q4P1/R2R1NK1 b - - bm cxd4; id \"STS(v6.0) Recapturing.020\"; c0 \"cxd4=10, Nxd4=1, Rb7=7, exd4=2\"; cem 4.62; cee 6.11; ce 4.66;");
    pos.add(
        "1rq2rk1/4bp2/2np2p1/p1p1p3/P1PNP1P1/1PB2P2/1Q4PK/R2R1N2 b - - bm cxd4; id \"STS(v6.0) Recapturing.021\"; c0 \"cxd4=10, Nb4=7, Nxd4=2, exd4=4\"; cem 4.33; cee 6.10; ce 4.40;");
    pos.add(
        "1rq2rk1/4bp2/3pn1p1/p1p1p3/P1PNP1P1/1PB2P2/1Q5P/R2R1NK1 b - - bm Nxd4; id \"STS(v6.0) Recapturing.022\"; c0 \"Nxd4=10, Ng5=6, cxd4=6, exd4=1\"; cem 4.07; cee 5.71; ce 4.12;");
    pos.add(
        "2kr3r/p1p4p/1np2p2/3P4/1b2N3/1P4P1/PB3PP1/R2K1R2 b - - bm Nxd5; id \"STS(v6.0) Recapturing.023\"; c0 \"Nxd5=10, Rhe8=1, Rhf8=3, Rxd5+=3\"; cem 0.83; cee 1.78; ce 1.31;");
    pos.add(
        "2qr2r1/1p3pk1/p1np1np1/7p/2P1Pp2/1PN5/3QN1PP/4RR1K w - - bm Nxf4; id \"STS(v6.0) Recapturing.024\"; c0 \"Nxf4=10, Nd5=4, Qxf4=5, Rxf4=4\"; cem -2.08; cee -2.42; ce -2.04;");
    pos.add(
        "2r1rbk1/1p3p2/1qn3p1/p1Pp1b1p/5P2/2P1RNP1/PP1Q2BP/RN5K b - - bm Bxc5; id \"STS(v6.0) Recapturing.025\"; c0 \"Bxc5=10, Qa7=3, Qb5=2, Qxc5=6\"; cem -0.27; cee -0.88; ce -0.38;");
    pos.add(
        "2r2r1k/1q1nbpp1/p3p2p/2P1P3/1p1N1B1P/6Q1/PP3PP1/R2R2K1 b - - bm Nxc5; id \"STS(v6.0) Recapturing.026\"; c0 \"Nxc5=10, Nb8=6, Rxc5=7\"; cem 2.09; cee 1.52; ce 1.89;");
    pos.add(
        "2r2rk1/1p1q1pb1/p2p1np1/7p/2P1Pp1Q/1PN5/P2B2PP/4RR1K w - - bm Rxf4; id \"STS(v6.0) Recapturing.027\"; c0 \"Rxf4=10, Bxf4=1\"; cem -0.83; cee -0.80; ce -0.73;");
    pos.add(
        "2r2rk1/3bqppp/1p6/p2pb2Q/Pn1P1P2/1P2PN2/6PP/R1B2RK1 w - - bm fxe5; id \"STS(v6.0) Recapturing.028\"; c0 \"fxe5=10, Nxe5=1, Qxe5=2, dxe5=4\"; cem -3.61; cee -4.18; ce -3.57;");
    pos.add(
        "2r2rk1/3bqppp/2p5/p2pb2Q/Pn1P1P2/1P2PN2/6PP/R1B2RK1 w - - bm Qxe5; id \"STS(v6.0) Recapturing.029\"; c0 \"Qxe5=10, Nxe5=6, dxe5=3, fxe5=7\"; cem -3.36; cee -3.92; ce -3.31;");
    pos.add(
        "2r3k1/1bqnbpp1/1p2p2p/2P5/P7/4PN1B/1B2QPPP/R5K1 b - - bm Nxc5; id \"STS(v6.0) Recapturing.030\"; c0 \"Nxc5=10, Bxc5=4, Qxc5=2, bxc5=4\"; cem -0.21; cee 0.25; ce -0.19;");
    pos.add(
        "2r3k1/1p1nqp1p/1P4p1/Q2Pp3/1RbnP3/5P1P/3N2P1/5B1K w - - bm Rxc4; id \"STS(v6.0) Recapturing.031\"; c0 \"Rxc4=10, Bxc4=2, Nxc4=5, d6=1\"; cem -3.99; cee -3.79; ce -3.82;");
    pos.add(
        "2r5/4bkp1/p3q3/1b2p2n/N3PBp1/1p2Q3/1P3PPP/RB1R2K1 b - - bm Nxf4; id \"STS(v6.0) Recapturing.032\"; c0 \"Nxf4=10, Bxa4=7, Rc4=7\"; cem 4.01; cee 4.09; ce 3.92;");
    pos.add(
        "2rq1r1k/p2nb1p1/1p3P1p/3p4/8/1P1p3Q/P2B1PPP/1R1N1RK1 b - - bm Bxf6; id \"STS(v6.0) Recapturing.033\"; c0 \"Bxf6=10, Nxf6=3, gxf6=6\"; cem 1.46; cee -0.84; ce 0.99;");
    pos.add(
        "2rq1rk1/4ppbp/p5p1/1p1b4/1PNP4/4B3/P1B2PPP/2RQR1K1 b - - bm Rxc4; id \"STS(v6.0) Recapturing.034\"; c0 \"Rxc4=10, Bxc4=7, Bxg2=6, bxc4=6\"; cem 2.42; cee 2.81; ce 2.35;");
    pos.add(
        "2rq1rk1/4ppbp/p5p1/1p1b4/2NP4/P3B2P/1PB2PP1/2RQR1K1 b - - bm Rxc4; id \"STS(v6.0) Recapturing.035\"; c0 \"Rxc4=10, Bxc4=4, Bxg2=6, bxc4=6\"; cem 2.49; cee 2.54; ce 2.39;");
    pos.add(
        "2rq1rk1/4ppbp/p5p1/1p1b4/2NP4/P3B3/1P3PPP/1BRQR2K b - - bm Rxc4; id \"STS(v6.0) Recapturing.036\"; c0 \"Rxc4=10, Bxc4=5, Bxg2+=6, bxc4=5\"; cem 2.23; cee 2.45; ce 2.15;");
    pos.add(
        "2rqr1k1/1b2bp1n/1pnpp1pp/p7/P2pP2P/1NPB1NP1/1P2QPK1/1R2B1R1 w - - bm Nbxd4; id \"STS(v6.0) Recapturing.037\"; c0 \"Nbxd4=10, Bd2=6, Nfxd4=6, cxd4=6\"; cem -1.78; cee -0.57; ce -1.68;");
    pos.add(
        "2rr4/1pqb1ppk/p1Nppn1p/4n3/2P1P3/2N3P1/PP1QB1PP/R3BRK1 b - - bm Bxc6; id \"STS(v6.0) Recapturing.038\"; c0 \"Bxc6=10, Nxc6=4, Qxc6=4\"; cem 1.10; cee 2.84; ce 1.00;");
    pos.add(
        "3b2nk/p2Nqrpp/1pB1p3/3pPnp1/1P1P1P1P/P7/2R1QB1K/8 w - - bm fxg5; id \"STS(v6.0) Recapturing.039\"; c0 \"fxg5=10, Qf3=6, Qg4=6, hxg5=7\"; cem -0.99; cee -0.81; ce -0.85;");
    pos.add(
        "3q4/p2b2kp/1R1P1p2/b1p5/2B1rPp1/8/P5P1/B1Q2RK1 b - - bm Bxb6; id \"STS(v6.0) Recapturing.040\"; c0 \"Bxb6=10, Qxb6=7, Rxc4=7, axb6=8\"; cem 4.33; cee 4.32; ce 4.23;");
    pos.add(
        "3r1r2/pkp4p/1np1p3/3P4/1b2N3/1P3P2/1B4PP/2R2RK1 b - - bm exd5; id \"STS(v6.0) Recapturing.041\"; c0 \"exd5=10, Nxd5=3, Rxd5=3, cxd5=4\"; cem 1.55; cee 0.76; ce 0.98;");
    pos.add(
        "3r1rk1/ppp2ppp/1nnN4/7q/8/1B5P/PP1BRPP1/R3Q1K1 b - - bm cxd6; id \"STS(v6.0) Recapturing.042\"; c0 \"cxd6=10, Nd4=8, Nd7=8, Rxd6=8\"; cem 4.30; cee 3.79; ce 4.15;");
    pos.add(
        "3r4/pkp2r2/1np5/3P3p/1b1BN1p1/1P3PP1/6P1/2R2R1K b - - bm cxd5; id \"STS(v6.0) Recapturing.043\"; c0 \"cxd5=10, Nxd5=6, Rxd5=7\"; cem 0.53; cee 0.62; ce 0.49;");
    pos.add(
        "3r4/pkp2r2/1np5/3P3p/1b1BN1pP/1P3P2/6P1/2R2R1K b - - bm Rxd5; id \"STS(v6.0) Recapturing.044\"; c0 \"Rxd5=10, cxd5=7, gxf3=2\"; cem 0.94; cee 0.71; ce 0.70;");
    pos.add(
        "3rkb1r/p2npp1p/1p4p1/1Q1p4/P2p1B2/2q1PN2/2P2PPP/R4RK1 w k - bm exd4; id \"STS(v6.0) Recapturing.045\"; c0 \"exd4=10, Be5=7, Nxd4=6, a5=5\"; cem -0.05; cee -1.16; ce -0.13;");
    pos.add(
        "3rr1k1/p4bbp/1p1p4/3p1BPp/P2p1N1P/2P1P1K1/R1P5/3R4 w - - bm exd4; id \"STS(v6.0) Recapturing.046\"; c0 \"exd4=10, Kf2=7, a5=7, cxd4=8\"; cem -0.91; cee 0.69; ce 0.16;");
    pos.add(
        "3rr1k1/pR1n1p1p/5b2/3N4/6p1/P2bp1P1/3N1PBP/2R3K1 w - - bm Nxe3; id \"STS(v6.0) Recapturing.047\"; c0 \"Nxe3=10, Ne4=8, Nxf6+=8, fxe3=8\"; cem -1.99; cee -1.84; ce -1.82;");
    pos.add(
        "4q1k1/p2n1pp1/4p2p/3n4/1P1P4/P4P2/1B1QN1PP/2r2K2 w - - bm Bxc1; id \"STS(v6.0) Recapturing.048\"; c0 \"Bxc1=10, Kf2=7, Qxc1=4\"; cem -7.18; cee -5.97; ce -6.47;");
    pos.add(
        "4r1k1/2p2rp1/1pnp4/p2N3p/2P2p2/PP1RP1P1/6KP/5R2 w - - bm gxf4; id \"STS(v6.0) Recapturing.049\"; c0 \"gxf4=10, Nxf4=5, Rxf4=1, exf4=8\"; cem -1.69; cee -0.82; ce -0.93;");
    pos.add(
        "4rnk1/3r1qb1/bp1p1np1/pNpP2Bp/P1P1Pp1P/3B4/1P1QNR2/6RK w - - bm Nxf4; id \"STS(v6.0) Recapturing.050\"; c0 \"Nxf4=10, Qxf4=2, Rxf4=3\"; cem -0.86; cee -0.53; ce -0.76;");
    pos.add(
        "5r2/4k3/2qpp2b/pN2p2n/P3PR1p/2P5/6PP/QB2R2K b - - bm Bxf4; id \"STS(v6.0) Recapturing.051\"; c0 \"Bxf4=10, Nxf4=6, Rxf4=2, exf4=2\"; cem 5.96; cee 4.91; ce 5.26;");
    pos.add(
        "6k1/pp2npp1/2r1bq1p/3p4/PP3N1P/5BP1/4QP2/3R3K w - - bm Bxd5; id \"STS(v6.0) Recapturing.052\"; c0 \"Bxd5=10, Kg1=8, Nxd5=8, b5=8\"; cem -0.26; cee -0.37; ce -0.20;");
    pos.add(
        "6n1/1k1qn1r1/1p1p4/p1pPpPp1/2P3N1/2B2PP1/PP4Q1/KR6 b - - bm Nxf5; id \"STS(v6.0) Recapturing.053\"; c0 \"Nxf5=10, Qxf5=3, Rf7=7, Rh7=8\"; cem 3.13; cee 2.35; ce 2.73;");
    pos.add(
        "8/1p4k1/3p4/p1pbp1r1/P1P1P2p/1PK2B2/5R2/8 w - - bm exd5; id \"STS(v6.0) Recapturing.054\"; c0 \"exd5=10, Kd3=6, Rd2=6, cxd5=8\"; cem -1.13; cee -2.54; ce -2.41;");
    pos.add(
        "8/p1p2p1k/1p1pqn1p/7r/P1PPP1rP/2P1RQ2/3N1K2/6R1 b - - bm Rhxh4; id \"STS(v6.0) Recapturing.055\"; c0 \"Rhxh4=10, Kg6=5, Rgxh4=9, Rxg1=5\"; cem -1.18; cee -0.23; ce -0.98;");
    pos.add(
        "b2r1r2/6bk/p1n2ppp/q2p4/1p2PN2/1B2B1P1/P3QP2/1R1R2K1 w - - bm Rxd5; id \"STS(v6.0) Recapturing.056\"; c0 \"Rxd5=10, Bxd5=3, Ne6=4, exd5=4\"; cem -0.38; cee -1.12; ce -0.29;");
    pos.add(
        "b2r1r2/6bk/p1n2ppp/qp1p4/4PN2/1B2B1PP/P3Q3/1R1R2K1 w - - bm Nxd5; id \"STS(v6.0) Recapturing.057\"; c0 \"Nxd5=10, exd5=3\"; cem -1.14; cee -1.49; ce -1.05;");
    pos.add(
        "b4r1r/3kq1p1/p1p1p1p1/NpPpPP2/1P1P2P1/4Q2p/1K5P/4R2R w - - bm fxe6+; id \"STS(v6.0) Recapturing.058\"; c0 \"fxe6+=10, Ref1=1, Rhf1=1\"; cem 2.34; cee 1.51; ce 2.18;");
    pos.add(
        "br1r2k1/p2n1pp1/np1p4/2P1p1qp/2P1P3/P1P2PP1/2QNBB1P/2R2R1K b - - bm Naxc5; id \"STS(v6.0) Recapturing.059\"; c0 \"Naxc5=10, Ndxc5=7, bxc5=1, dxc5=1\"; cem 0.81; cee 0.40; ce 0.70;");
    pos.add(
        "q1rb2rk/1b3ppp/2n1p2n/p1BpP3/B2P1PP1/1p2RN1P/2PN1Q2/1R4K1 w - - bm Rexb3; id \"STS(v6.0) Recapturing.060\"; c0 \"Rexb3=10, Bxb3=7, Nxb3=6, c3=6\"; cem 0.67; cee -0.35; ce 0.77;");
    pos.add(
        "q1rb2rk/1b3ppp/2n1p2n/p2pP3/B2P1PP1/Bp2RN1P/1RPNQ3/6K1 w - - bm Nxb3; id \"STS(v6.0) Recapturing.061\"; c0 \"Nxb3=10, Rbxb3=3, Rexb3=6, c3=4\"; cem 0.12; cee -1.25; ce 0.22;");
    pos.add(
        "q2r2k1/1br1bpp1/pp3n2/2nN4/P1P4P/1P3P1B/1BQ4P/1R1RN1K1 b - - bm Nxd5; id \"STS(v6.0) Recapturing.062\"; c0 \"Nxd5=10, Bxd5=1, Qb8=7, Rxd5=5\"; cem 4.08; cee 4.08; ce 3.98;");
    pos.add(
        "r1b2rk1/4qppp/pp6/3pb2Q/Pn1P1P2/1P2PN2/6PP/R1B2RK1 w - - bm dxe5; id \"STS(v6.0) Recapturing.063\"; c0 \"dxe5=10, Nxe5=1, Qxe5=2, fxe5=6\"; cem -2.88; cee -3.12; ce -2.80;");
    pos.add(
        "r1b2rk1/p3qppp/Pp6/3pb2Q/1n1P1P2/1P2PN2/1B4PP/R4RK1 w - - bm dxe5; id \"STS(v6.0) Recapturing.064\"; c0 \"dxe5=10, Qxe5=1, fxe5=2\"; cem -2.61; cee -2.40; ce -2.49;");
    pos.add(
        "r1b3k1/5r2/p4n1p/3q2pP/2p1p3/P1PBB3/3N1PP1/R2QK2R b KQ - bm exd3; id \"STS(v6.0) Recapturing.065\"; c0 \"exd3=10, Bg4=6, Qxd3=2, cxd3=3\"; cem 3.88; cee 5.25; ce 3.90;");
    pos.add(
        "r1bq1rk1/1p1nbppp/p3pn2/P1p5/4p3/2NP2N1/BPPB1PPP/R2Q1RK1 w - - bm dxe4; id \"STS(v6.0) Recapturing.066\"; c0 \"dxe4=10, Bf4=3, Ncxe4=5, Ngxe4=4\"; cem -1.49; cee -0.62; ce -1.39;");
    pos.add(
        "r1q1k2r/1p1nbppp/p7/3bpP2/2P1P1P1/1PN1BQ2/7P/2KR3R w kq - bm Nxd5; id \"STS(v6.0) Recapturing.067\"; c0 \"Nxd5=10, Kb2=5, Rxd5=3, exd5=5\"; cem -1.44; cee -2.70; ce -1.46;");
    pos.add(
        "r1q1k2r/1p1nbppp/p7/Q2bpP2/2B1P1P1/1P2N3/PK5P/3R3R w kq - bm Qxd5; id \"STS(v6.0) Recapturing.068\"; c0 \"Qxd5=10, Bxd5=3, Nxd5=4, Rxd5=1\"; cem -3.66; cee -4.26; ce -3.62;");
    pos.add(
        "r1qr2k1/1b1nbpp1/1P6/p1B1p2p/4P3/3B3Q/2P1N1PP/RR1N3K b - - bm Nxc5; id \"STS(v6.0) Recapturing.069\"; c0 \"Nxc5=10, Bg5=6\"; cem 3.69; cee 5.49; ce 3.59;");
    pos.add(
        "r1qr2k1/4bp1p/2p1p1p1/2pb4/PP1P1P2/3NQ3/5BPP/R2R2K1 w - - bm Nxc5; id \"STS(v6.0) Recapturing.070\"; c0 \"Nxc5=10, Rdb1=4, bxc5=1, dxc5=1\"; cem -0.32; cee 0.89; ce -0.03;");
    pos.add(
        "r1qr2k1/4bp2/p1p1p1p1/2pb4/PP1P1P2/3NQ3/5BPP/R2R2K1 w - - bm bxc5; id \"STS(v6.0) Recapturing.071\"; c0 \"bxc5=10, Nxc5=7, dxc5=9, f5=2\"; cem 0.00; cee 0.57; ce 0.19;");
    pos.add(
        "r1qr2k1/p3bp1p/P1p1p1p1/2pb4/1P1P1P2/3NQ3/5BPP/R2R2K1 w - - bm dxc5; id \"STS(v6.0) Recapturing.072\"; c0 \"dxc5=10, Nxc5=8, Qe1=7, bxc5=8\"; cem -1.49; cee -0.55; ce -1.24;");
    pos.add(
        "r1r2nk1/5pp1/1pnpbq2/2B5/p1P1PN1p/8/PPNR1QP1/3R1BK1 b - - bm dxc5; id \"STS(v6.0) Recapturing.073\"; c0 \"dxc5=10, Bg4=6, Ne5=6, bxc5=6\"; cem 4.60; cee 3.87; ce 4.50;");
    pos.add(
        "r1r3k1/1p2q1pp/n2pp3/pN6/2nbP1P1/P1N2Q2/1PP4P/1K1R1R2 w - - bm Rxd4; id \"STS(v6.0) Recapturing.074\"; c0 \"Rxd4=10, Na4=6, Nxd4=3, g5=6\"; cem -2.48; cee -4.08; ce -2.54;");
    pos.add(
        "r2q1rk1/1b1pbppp/1pn1pn2/p7/P2pP3/2PB1N2/1P1NQPPP/R1B2RK1 w - - bm Nxd4; id \"STS(v6.0) Recapturing.075\"; c0 \"Nxd4=10, Rb1=7, Re1=7, cxd4=7\"; cem -1.04; cee -0.72; ce -0.95;");
    pos.add(
        "r2q1rk1/2pnbppp/p3p3/3b4/3P2P1/3QnN1P/PP1BPP2/RNR3K1 w - - bm Qxe3; id \"STS(v6.0) Recapturing.076\"; c0 \"Qxe3=10, Ne1=7\"; cem -3.96; cee -3.91; ce -3.86;");
    pos.add(
        "r2q1rk1/p1p1n1pp/1p1p4/P2Ppb2/1PP5/4b1P1/3QNPBP/R4RK1 w - - bm fxe3; id \"STS(v6.0) Recapturing.077\"; c0 \"fxe3=10, Qb2=7, Qd1=7, Qxe3=8\"; cem -3.74; cee -4.48; ce -3.71;");
    pos.add(
        "r2qk1r1/1b2bp2/3ppp1p/p1p5/2B1PP2/1nN5/PPPQ2PP/1K1R3R w q - bm axb3; id \"STS(v6.0) Recapturing.078\"; c0 \"axb3=10, Bb5+=5, Bxb3=5, cxb3=6\"; cem -1.14; cee -3.76; ce -1.29;");
    pos.add(
        "r2qk1r1/1b3pb1/2pppp1p/8/2B1PP2/1nN5/PPPQ2PP/1K1R3R w q - bm Bxb3; id \"STS(v6.0) Recapturing.079\"; c0 \"Bxb3=10, Qe3=4, axb3=5, cxb3=8\"; cem -0.84; cee -2.49; ce -0.90;");
    pos.add(
        "r2qk1r1/3bbp2/3ppp2/p7/2B1PP1p/1nN5/PPPQ2PP/1K1R3R w q - bm cxb3; id \"STS(v6.0) Recapturing.080\"; c0 \"cxb3=10, Bxb3=3, Qf2=4, axb3=8\"; cem -0.49; cee -2.91; ce -0.62;");
    pos.add(
        "r2r1k2/2b1npp1/p1np3p/1p6/3P1B2/P1N4P/1PBR1PP1/3qR1K1 w - - bm Bxd1; id \"STS(v6.0) Recapturing.081\"; c0 \"Bxd1=10, Nxd1=6, Rdxd1=5, Rexd1=6\"; cem 0.00; cee 0.00; ce -12.27;");
    pos.add(
        "r2r1nk1/1q2bp2/p3pnpp/2p1B3/2p5/1P2N1P1/P1QNPP1P/2R2RK1 w - - bm Ndxc4; id \"STS(v6.0) Recapturing.082\"; c0 \"Ndxc4=10, Bxf6=4, Nexc4=7, bxc4=3\"; cem -0.18; cee -0.44; ce -0.09;");
    pos.add(
        "r3b3/4kpp1/3p1n1p/5P2/1r2P1P1/1p2RN1P/2PN4/1R4K1 w - - bm Rbxb3; id \"STS(v6.0) Recapturing.083\"; c0 \"Rbxb3=10, Rexb3=5, cxb3=7, e5=6\"; cem -0.56; cee -2.66; ce -1.76;");
    pos.add(
        "r3r1k1/1p3pb1/6p1/p2b2Pp/PpB1PR2/1P6/2P4P/2KR4 w - - bm Rxd5; id \"STS(v6.0) Recapturing.084\"; c0 \"Rxd5=10, Bd3=7, Bxd5=5, exd5=4\"; cem -4.24; cee -4.82; ce -4.53;");
    pos.add(
        "r4r1k/p2nb1p1/1p2pPqp/3p4/P1pPn3/2P1BN1P/1QP1BPP1/R5RK b - - bm Qxf6; id \"STS(v6.0) Recapturing.085\"; c0 \"Qxf6=10, Bxf6=4, Ndxf6=5, Rxf6=8\"; cem 2.49; cee 0.37; ce 2.34;");
    pos.add(
        "r4r2/5pk1/1bR1n1p1/Pp1Np2p/1P1pP3/8/5PPP/2R3K1 w - - bm Nxb6; id \"STS(v6.0) Recapturing.086\"; c0 \"Nxb6=10, Kf1=5, axb6=3\"; cem -1.89; cee -3.37; ce -2.79;");
    pos.add(
        "r4rk1/1b1pq1pp/1p6/p1n1pp2/2PBN3/P3P3/1PQ1BPPP/3R1RK1 b - - bm Bxe4; id \"STS(v6.0) Recapturing.087\"; c0 \"Bxe4=10, Nxe4=6, exd4=6, fxe4=5\"; cem 2.75; cee 3.44; ce 2.71;");
    pos.add(
        "r4rk1/1p1bqpp1/2p4p/3pb2Q/Pn1P1P2/1P2PN2/6PP/R1B2RK1 w - - bm Nxe5; id \"STS(v6.0) Recapturing.088\"; c0 \"Nxe5=10, Qxe5=7, fxe5=5\"; cem -3.89; cee -4.15; ce -3.82;");
    pos.add(
        "r4rk1/1p5p/2p2p2/2P2Pp1/p5B1/PbR2n2/1P2PPKP/2R5 w - - bm Bxf3; id \"STS(v6.0) Recapturing.089\"; c0 \"Bxf3=10, Kxf3=3, Rxf3=5\"; cem -1.80; cee -1.54; ce -1.53;");
    pos.add(
        "r4rk1/3p1b2/2p2p2/1pP1pPp1/4P1B1/P1R2n2/1P3PKP/2R5 w - - bm Bxf3; id \"STS(v6.0) Recapturing.090\"; c0 \"Bxf3=10, Kxf3=6, Re3=6, Rxf3=7\"; cem -2.73; cee -1.96; ce -2.11;");
    pos.add(
        "r4rk1/pp1qppbp/2p1b1p1/n7/P2PpB1N/2P3P1/2P1QPBP/1R2R1K1 w - - bm Bxe4; id \"STS(v6.0) Recapturing.091\"; c0 \"Bxe4=10, Qd2=8, Qe3=8, Qxe4=8\"; cem -1.34; cee -0.78; ce -1.24;");
    pos.add(
        "r4rk1/ppp2ppp/1nnb4/8/1P1P3q/PBN1B2P/4bPP1/R2QR1K1 w - - bm Qxe2; id \"STS(v6.0) Recapturing.092\"; c0 \"Qxe2=10, Bxf7+=6, Nxe2=3, Rxe2=6\"; cem -2.72; cee -3.53; ce -2.62;");
    pos.add(
        "r4rn1/2q2p1k/p2p2np/4pb1Q/1pP1P3/4B2P/2P3PN/3R1R1K w - - bm Rxf5; id \"STS(v6.0) Recapturing.093\"; c0 \"Rxf5=10, Bxh6=6, Qxf5=3, exf5=4\"; cem -4.29; cee -5.50; ce -4.21;");
    pos.add(
        "r5b1/3rn1pk/p1n4p/1p1p1p1P/3P1B2/P1N3P1/1P1R1P2/3qRBK1 w - - bm Nxd1; id \"STS(v6.0) Recapturing.094\"; c0 \"Nxd1=10, Rdxd1=6, Rexd1=5, Rxe7=7\"; cem 0.00; cee 0.00; ce -12.12;");
    pos.add(
        "r5k1/5pbp/6P1/p2R4/1p5r/P5R1/1PP5/2KB4 b - - bm hxg6; id \"STS(v6.0) Recapturing.095\"; c0 \"hxg6=10, Bh6+=6, bxa3=2, fxg6=7\"; cem 0.66; cee 0.17; ce 0.16;");
    pos.add(
        "r6r/ppk1npp1/1b2p2p/2pnP2P/4N3/2p2N2/PPPB1PP1/1K1RR3 w - - bm Bxc3; id \"STS(v6.0) Recapturing.096\"; c0 \"Bxc3=10, Bc1=8, Nxc3=8, bxc3=6\"; cem -1.19; cee -1.97; ce -1.45;");
    pos.add(
        "rn1q1rk1/1b2bppp/1p1pp1n1/pN6/P2pP3/2PB1NP1/1P2QP1P/R1B2RK1 w - - bm cxd4; id \"STS(v6.0) Recapturing.097\"; c0 \"cxd4=10, Nbxd4=2, Nfxd4=5, Rd1=3\"; cem -0.08; cee -0.14; ce 0.01;");
    pos.add(
        "rq1r2k1/pb2npbp/4pnp1/3P2N1/Q6P/1B4N1/PP3PP1/R1BR2K1 b - - bm Bxd5; id \"STS(v6.0) Recapturing.098\"; c0 \"Bxd5=10, Nexd5=6, exd5=3\"; cem 0.51; cee 0.81; ce 0.42;");
    pos.add(
        "rqr3k1/3bb1p1/p1n2n1p/3p1p2/1P1NpP2/2N1B2P/1PPQB1P1/1K1R3R b - - bm Bxb4; id \"STS(v6.0) Recapturing.099\"; c0 \"Bxb4=10, Nxb4=4, Qb7=3, Qxb4=3\"; cem -0.57; cee 0.98; ce -0.67;");
    pos.add(
        "rqr3k1/4bpp1/pBbp1n1p/Pp2p3/3PPP2/2N1QNP1/1P4P1/R4R1K w - - bm fxe5; id \"STS(v6.0) Recapturing.100\"; c0 \"fxe5=10, Rae1=2, d5=1, dxe5=4\"; cem -0.81; cee -0.01; ce -0.70;");
    pos.add(
        "1R3b2/r4pk1/2qpn1p1/P1p1p2p/2P1P2P/5PP1/6K1/1Q1BB3 w - - bm Qb6; id \"STS(v7.0) Simplification.001\"; c0 \"Qb6=10, Bc3=7, Kf1=7, Qb5=9\"; cem -0.03; cee 0.90; ce 0.43;");
    pos.add(
        "1b4k1/6p1/pr2p2p/5p2/P2N4/P1Q1P1qP/6P1/5RK1 b - - bm Be5; id \"STS(v7.0) Simplification.002\"; c0 \"Be5=10, Bd6=3, Kh7=4, Qh2+=7\"; cem -6.64; cee 0.00; ce -3.21;");
    pos.add(
        "1k1r1b1r/ppqbnp2/4p1pp/n2pP3/2pP4/P1P3B1/1P1NBPPP/1R1QRNK1 b - - bm Nf5; id \"STS(v7.0) Simplification.003\"; c0 \"Nf5=10\"; cem 0.84; cee 0.76; ce 0.75;");
    pos.add(
        "1nb2rk1/2p1pqb1/1r2p2p/R6P/1PBP2p1/B4NN1/5PK1/3QR3 w - - bm Rf5; id \"STS(v7.0) Simplification.004\"; c0 \"Rf5=10\"; cem 4.29; cee 4.86; ce 4.39;");
    pos.add(
        "1nr3k1/1pr2pp1/p3bn2/P3p2p/1PP4P/3BNPP1/3RN1K1/R7 w - - bm Bf5; id \"STS(v7.0) Simplification.005\"; c0 \"Bf5=10, Nc3=1, Rdd1=2, b5=1\"; cem 0.96; cee 1.87; ce 1.48;");
    pos.add(
        "1q1rb1k1/5p1p/p3pnpb/P1r5/2B1P3/1P3P2/2R1N1PP/R2NQ2K b - - bm Bb5; id \"STS(v7.0) Simplification.006\"; c0 \"Bb5=10\"; cem -0.22; cee 0.87; ce -0.29;");
    pos.add(
        "1r1r2k1/2p1qpp1/pnP1b2p/1p2P3/3N4/P3P2P/1QB2PP1/3RR1K1 w - - bm Qb4; id \"STS(v7.0) Simplification.007\"; c0 \"Qb4=10, Bd3=7, Qc3=8, f4=9\"; cem 2.07; cee 1.42; ce 2.06;");
    pos.add(
        "1r2kbQ1/1r1q1p2/p2p2n1/6P1/N1pBP3/P1N5/1PPR4/K7 w - - bm Bg7; id \"STS(v7.0) Simplification.008\"; c0 \"Bg7=10, Bc5=7, Bf6=5, Rf2=5\"; cem 1.07; cee -1.75; ce 0.57;");
    pos.add(
        "1r2r1k1/5pp1/R2p3n/3P1P1p/2p2PP1/1qb2B1P/Q7/2BR1K2 b - - bm Bd2; id \"STS(v7.0) Simplification.009\"; c0 \"Bd2=10, Bb2=5, Bb4=5, hxg4=3\"; cem -0.12; cee 0.02; ce -0.19;");
    pos.add(
        "1r3qk1/rpb2pp1/2p2n1p/p1P1p3/P1bPP2Q/2N1PN1P/1RB3P1/1R5K w - - bm Bb3; id \"STS(v7.0) Simplification.010\"; c0 \"Bb3=10, Qe1=6, Qf2=6, g4=6\"; cem 0.19; cee 1.14; ce 0.30;");
    pos.add(
        "1r3rk1/1nqb1p1p/p3p1p1/1ppPb3/2P1N3/1P1Q2PP/P2B2BK/1RR5 w - - bm Bc3; id \"STS(v7.0) Simplification.011\"; c0 \"Bc3=10, Bf4=6, Qf3=5, Rf1=5\"; cem 0.95; cee 1.24; ce 1.04;");
    pos.add(
        "1r4k1/3b1pbp/R2Np1p1/4n3/1P1NP3/3BP3/4K1PP/8 w - - bm Nf3; id \"STS(v7.0) Simplification.012\"; c0 \"Nf3=10, Nc4=3, Ra7=1, b5=1\"; cem -2.13; cee 0.77; ce -0.01;");
    pos.add(
        "1r4k1/4qpp1/r1b4p/pp1pPB2/n1pP3P/2P2N2/R1P3P1/R3Q1K1 b - - bm Bd7; id \"STS(v7.0) Simplification.013\"; c0 \"Bd7=10, Be8=8, Rab6=8, Re8=7\"; cem -1.31; cee -1.75; ce -1.48;");
    pos.add(
        "1r4k1/5bpp/3b2p1/3PpqPn/1pB5/rP1NBP2/P4Q2/1K1R3R w - - bm Bc5; id \"STS(v7.0) Simplification.014\"; c0 \"Bc5=10, Rd2=1, Rh2=5, Rh4=1\"; cem -0.77; cee -1.03; ce -0.68;");
    pos.add(
        "1r4k1/pp1r1p1p/2np1np1/2q5/P3PB2/1QP2B2/5PPP/R2R2K1 w - - bm Qb5; id \"STS(v7.0) Simplification.015\"; c0 \"Qb5=10, Ra2=2, a5=3, h3=2\"; cem 1.62; cee 1.10; ce 1.63;");
    pos.add(
        "1rb1r1k1/2q1bp1p/3p1np1/p1nPp3/4P3/1PRB1N1P/3BQPP1/1R3NK1 w - - bm Be3; id \"STS(v7.0) Simplification.016\"; c0 \"Be3=10, Bc1=3, Rbc1=4, Rcc1=4\"; cem 0.51; cee 0.00; ce 0.61;");
    pos.add(
        "2b1r1k1/p1B1ppbp/1p4p1/8/4n3/7P/P1RRBPP1/6K1 w - - bm Rd8; id \"STS(v7.0) Simplification.017\"; c0 \"Rd8=10\"; cem -0.14; cee 0.00; ce 0.05;");
    pos.add(
        "2bq2k1/4pp2/p2p1n2/n1pP2p1/2P3Pp/P1Q1N2P/3NPPB1/6K1 w - - bm Be4; id \"STS(v7.0) Simplification.018\"; c0 \"Be4=10, Nc2=8, Nd1=7, Nef1=9\"; cem 0.72; cee 0.37; ce 0.64;");
    pos.add(
        "2br3k/2pqb2r/p1n1n3/P3PQ2/1pB5/5N2/1P1N1PPP/R3R1K1 b - - bm Ncd4; id \"STS(v7.0) Simplification.019\"; c0 \"Ncd4=10, Nc5=4, Ned4=5, Rf8=4\"; cem -0.21; cee -0.19; ce -0.31;");
    pos.add(
        "2q1r1k1/1p2rpp1/p1p3pb/2Pp2n1/3P1R2/1Q2P1P1/PP3NKP/2B2R2 b - - bm Ne4; id \"STS(v7.0) Simplification.020\"; c0 \"Ne4=10, Nh7=8, Qd7=7, Qd8=7\"; cem -1.58; cee -1.06; ce -1.59;");
    pos.add(
        "2r1r1k1/1bq1bpp1/pp1pp1n1/2n3P1/2P1PP1p/BPN1QB1P/P1R5/3RN1K1 w - - bm Bh5; id \"STS(v7.0) Simplification.021\"; c0 \"Bh5=10, Bg4=3, Ng2=9\"; cem 0.38; cee 0.29; ce 0.47;");
    pos.add(
        "2r1r1k1/1p1bn1p1/p2Bpb1p/5p1q/P2P4/5NQP/1P2BPP1/R2R2K1 w - - bm Ne5; id \"STS(v7.0) Simplification.022\"; c0 \"Ne5=10\"; cem 0.48; cee 0.29; ce 0.57;");
    pos.add(
        "2r1r1k1/pN2b1p1/2p4p/1p1nBp1P/3P2n1/1NP5/PP2KPP1/3R3R b - - bm Bf6; id \"STS(v7.0) Simplification.023\"; c0 \"Bf6=10\"; cem -0.37; cee 2.00; ce 0.64;");
    pos.add(
        "2r1r1k1/pb2bp1p/8/2p1Rp1q/P1N5/6P1/2Q2P1P/3R1BK1 w - - bm Bg2; id \"STS(v7.0) Simplification.024\"; c0 \"Bg2=10, Be2=5, Ne3=6, Rxf5=9\"; cem -0.70; cee 0.00; ce -0.49;");
    pos.add(
        "2r1rk2/1p2bpp1/p4nbp/4R3/P2R1P1B/2N5/1P2B1PP/5K2 b - - bm Red8; id \"STS(v7.0) Simplification.025\"; c0 \"Red8=10\"; cem 0.13; cee 0.26; ce 0.09;");
    pos.add(
        "2r2n2/6k1/1pNBbb2/3p1p2/3P1P2/P4KP1/2R5/4N3 w - - bm Ne5; id \"STS(v7.0) Simplification.026\"; c0 \"Ne5=10, Ke2=8, Ke3=8, Kf2=7\"; cem -0.02; cee 0.39; ce 0.36;");
    pos.add(
        "2r2rk1/p3qp1p/2b1pp1Q/2b5/1n6/2N2NP1/1P2PPBP/R2R2K1 w - - bm Nh4; id \"STS(v7.0) Simplification.027\"; c0 \"Nh4=10, Nd2=4, Ne1=4, Ra5=5\"; cem 0.33; cee 0.08; ce 0.41;");
    pos.add(
        "2r3k1/1p1b1pb1/1qn3p1/pN1p2Pp/P3r1nP/1NP3B1/1P2BPR1/R2Q2K1 b - - bm Be5; id \"STS(v7.0) Simplification.028\"; c0 \"Be5=10, Bf8=3, Na7=4, Re6=4\"; cem -0.63; cee -0.15; ce -0.73;");
    pos.add(
        "2r3k1/2q1bpp1/p1P2n1p/4pb2/8/2N1BB2/1rPR1QPP/R5K1 w - - bm Nd5; id \"STS(v7.0) Simplification.029\"; c0 \"Nd5=10, Na4=8, Nd1=8, Rxa6=8\"; cem -0.87; cee -1.24; ce -0.78;");
    pos.add(
        "2r3k1/p1q1bp1p/2p1p1p1/4P3/1p6/1P1QP1P1/PB3P1P/3R2K1 b - - bm Rd8; id \"STS(v7.0) Simplification.030\"; c0 \"Rd8=10, Qa5=3, a5=1, c5=1\"; cem 0.63; cee 0.11; ce 0.25;");
    pos.add(
        "2r3k1/p2q1pp1/2p4p/1p1p1b1n/2PP4/P1QP1PP1/3B3P/4RBK1 b - - bm Bh3; id \"STS(v7.0) Simplification.031\"; c0 \"Bh3=10\"; cem 1.03; cee 0.58; ce 0.75;");
    pos.add(
        "2r5/4k1p1/4p2p/1pnbPp2/3B4/2P5/1PB3PP/5R1K b - - bm Be4; id \"STS(v7.0) Simplification.032\"; c0 \"Be4=10, Kf7=4, Nb3=5, Rc7=4\"; cem 1.80; cee -0.52; ce -0.23;");
    pos.add(
        "2r5/p4k2/4bp2/Ppp3p1/4p3/B1P4r/2P2P1P/R3KNR1 w - - bm Rg3; id \"STS(v7.0) Simplification.033\"; c0 \"Rg3=10, Bb2=7, Bc1=8, Rg2=8\"; cem 1.10; cee 0.57; ce 0.75;");
    pos.add(
        "2rn4/2p1rpk1/p3n1p1/P3N1P1/BpN1b2P/1P4K1/2PR4/3R4 w - - bm Rd7; id \"STS(v7.0) Simplification.034\"; c0 \"Rd7=10\"; cem 1.54; cee 1.74; ce 1.73;");
    pos.add(
        "2rq1rk1/pb3p1p/1p1p2p1/4p3/1PPnP3/2N2P2/P2QB1PP/2RR2K1 w - - bm Nb5; id \"STS(v7.0) Simplification.035\"; c0 \"Nb5=10, Bd3=1, Kh1=1\"; cem -0.09; cee 0.23; ce 0.06;");
    pos.add(
        "2rr2k1/1p3pp1/p3b2p/3pP2Q/3B4/bqP5/1P1RB1PP/1K3R2 b - - bm Bc5; id \"STS(v7.0) Simplification.036\"; c0 \"Bc5=10\"; cem -1.57; cee -0.90; ce -1.56;");
    pos.add(
        "3Q4/2p2k1p/p3b1p1/2p3P1/4Pq2/1P2NP2/P5K1/8 w - - bm Nc4; id \"STS(v7.0) Simplification.037\"; c0 \"Nc4=10, Kf2=3, Nd1=2\"; cem 0.22; cee 0.86; ce 0.79;");
    pos.add(
        "3br1k1/r4pp1/p1qpbn1p/n1p1pN2/P1B1P3/2PPN2P/3BQPP1/R4RK1 w - - bm Bd5; id \"STS(v7.0) Simplification.038\"; c0 \"Bd5=10, Bxe6=7, Bxe6=8, Ng3=7, f3=7\"; cem 0.94; cee 1.40; ce 1.03;");
    pos.add(
        "3qr3/2n1rpbk/3p2np/R1pP2p1/P3P3/2N2P1P/2QB2P1/1R3B1K w - - bm Nb5; id \"STS(v7.0) Simplification.039\"; c0 \"Nb5=10, Nd1=7, Qa2=7, Ra7=6\"; cem 1.74; cee 1.92; ce 1.83;");
    pos.add(
        "3r1r2/1p2k1pp/p2ppn2/4n3/P3P3/1BN4P/1PP2RP1/5R1K b - - bm Nfd7; id \"STS(v7.0) Simplification.040\"; c0 \"Nfd7=10, Rb8=6, Rf7=6, g5=6\"; cem 0.34; cee -1.81; ce -1.08;");
    pos.add(
        "3r1rk1/4q1pp/1pb1p3/p1p1P3/P3pP2/1P2P3/4Q1PP/N2R1RK1 b - - bm Rd3; id \"STS(v7.0) Simplification.041\"; c0 \"Rd3=10, Bd5=1, Qb7=2, Rxd1=1\"; cem -1.15; cee -0.44; ce -1.02;");
    pos.add(
        "3r2k1/1R3ppp/8/p1p1r3/4n3/4P2P/PP2BP2/1K5R w - - bm Rd1; id \"STS(v7.0) Simplification.042\"; c0 \"Rd1=10\"; cem -0.49; cee 0.70; ce 0.50;");
    pos.add(
        "3r2k1/1b3qp1/pp2p1p1/4P1P1/2PN1P1P/b1NR2K1/P3Q3/8 w - - bm Nc2; id \"STS(v7.0) Simplification.043\"; c0 \"Nc2=10, Nb3=6, Ne4=3, Nf3=4\"; cem -0.38; cee 1.53; ce 0.46;");
    pos.add(
        "3r2k1/1p3ppp/p3nn2/Qbp4r/2N5/1B3PP1/PP3RKP/8 w - - bm Rd2; id \"STS(v7.0) Simplification.044\"; c0 \"Rd2=10\"; cem 0.56; cee 0.54; ce 0.64;");
    pos.add(
        "3r2k1/2rq2pp/4bp2/p1p1pn2/1n2N3/B2PP1P1/1R1Q1P1P/1R3BK1 b - - bm Nd6; id \"STS(v7.0) Simplification.045\"; c0 \"Nd6=10\"; cem -0.97; cee -1.78; ce -1.08;");
    pos.add(
        "3r3k/p3bppp/4q3/2p1P1Bb/3p4/P6P/1P1Q1PP1/1B2R1K1 w - - bm Ba2; id \"STS(v7.0) Simplification.046\"; c0 \"Ba2=10, Bd3=3, Bf5=4, g4=3\"; cem -0.41; cee -0.68; ce -0.41;");
    pos.add(
        "3r4/1R4bk/7p/p3p2p/3rP3/3p3Q/P1qN1PP1/1R4K1 b - - bm Rb4; id \"STS(v7.0) Simplification.047\"; c0 \"Rb4=10, Qc6=7, Qc8=7, Qxd2=8\"; cem 3.82; cee 0.14; ce 2.60;");
    pos.add(
        "3rr3/1k3p1p/p2P1np1/8/pn6/1bN1NP2/1P1RBKPP/7R w - - bm Bd1; id \"STS(v7.0) Simplification.048\"; c0 \"Bd1=10, Ra1=2, Rc1=1, g4=4\"; cem 0.91; cee 1.13; ce 1.11;");
    pos.add(
        "4qnk1/1p1rbppb/4p2p/1P2P2P/3N1P2/2Q2BP1/5BK1/2R5 w - - bm Qc8; id \"STS(v7.0) Simplification.049\"; c0 \"Qc8=10\"; cem 0.78; cee 1.88; ce 1.14;");
    pos.add(
        "4r1k1/1p1n1p1b/2n2q2/2P4p/1PBp1PpN/6P1/3Q2P1/3R1NK1 w - - bm Bd3; id \"STS(v7.0) Simplification.050\"; c0 \"Bd3=10\"; cem -0.47; cee -0.38; ce -0.35;");
    pos.add(
        "4r1k1/2R3p1/p4qnp/8/2P2p2/1Pn2Q2/5BPP/5N1K b - - bm Ne4; id \"STS(v7.0) Simplification.051\"; c0 \"Ne4=10, Rd8=4\"; cem -1.16; cee 0.00; ce -0.79;");
    pos.add(
        "4r1k1/4bp1p/p4np1/1r1p4/2NP1B1P/P7/1P3PP1/3RR1K1 w - - bm Bg5; id \"STS(v7.0) Simplification.052\"; c0 \"Bg5=10\"; cem 1.07; cee 0.85; ce 1.03;");
    pos.add(
        "4r1k1/p2n3p/1qp3p1/3rp3/6P1/2B1R3/PP2Q2P/4R2K b - - bm Qb5; id \"STS(v7.0) Simplification.053\"; c0 \"Qb5=10\"; cem -0.47; cee -0.59; ce -0.60;");
    pos.add(
        "4r1k1/p6p/2pB2pb/3pP1q1/8/1P5b/P1P2Q1P/3BR2K b - - bm Qd2; id \"STS(v7.0) Simplification.054\"; c0 \"Qd2=10, Bf5=3, Qd8=3, Qf5=7\"; cem -0.42; cee -0.30; ce -0.47;");
    pos.add(
        "4r1k1/pp3p2/2n1r1p1/2q4p/n3p3/4B1Q1/P1PRBPPP/3R2K1 b - - bm Qe5; id \"STS(v7.0) Simplification.055\"; c0 \"Qe5=10, Qb4=5, Qc3=6\"; cem 1.25; cee 0.35; ce 1.00;");
    pos.add(
        "4r3/q4pkp/2p1bnp1/p3p3/PbP1P2P/4NNP1/1RQ2P2/5BK1 b - - bm Ng4; id \"STS(v7.0) Simplification.056\"; c0 \"Ng4=10\"; cem -0.23; cee -0.38; ce -0.36;");
    pos.add(
        "4rb1k/3q1p2/2R4p/1P1npN2/4Q3/7P/1P3PP1/6K1 b - - bm Re6; id \"STS(v7.0) Simplification.057\"; c0 \"Re6=10, Nb4=4, f6=5, h5=3\"; cem -1.20; cee -0.37; ce -0.90;");
    pos.add(
        "4rk2/1b2rqb1/p4p2/1p1P1Pp1/2pRp1N1/P1N1R1PQ/1P4P1/6K1 w - - bm Nh6; id \"STS(v7.0) Simplification.058\"; c0 \"Nh6=10, Kf2=3, Qh2=3, Qh7=3\"; cem 0.72; cee 0.38; ce 0.75;");
    pos.add(
        "5k2/1p1n1pp1/p5bp/P2p4/1P1N4/2P2PP1/4QK1P/2q2B2 w - - bm Qe3; id \"STS(v7.0) Simplification.059\"; c0 \"Qe3=10\"; cem 0.33; cee 1.05; ce 0.87;");
    pos.add(
        "5k2/3q1pp1/pB6/4b2p/4N3/2N3P1/BP3P1P/6K1 b - - bm Bd4; id \"STS(v7.0) Simplification.060\"; c0 \"Bd4=10, Ke8=3, f5=4, h4=2\"; cem 0.28; cee -0.33; ce -0.28;");
    pos.add(
        "5k2/4rpp1/pp6/4b2p/4N1q1/2NRB1P1/BP3P1P/6K1 b - - bm Rd7; id \"STS(v7.0) Simplification.061\"; c0 \"Rd7=10, Qf5=2\"; cem -0.53; cee -0.64; ce -0.68;");
    pos.add(
        "5r1k/2q2rpp/2BbRn2/p1pP1P2/1p1p2PB/1P1P1Q1P/6K1/R7 w - - bm Bg3; id \"STS(v7.0) Simplification.062\"; c0 \"Bg3=10, Qf2=5, Ra2=6, Rae1=7\"; cem 1.12; cee 1.48; ce 1.27;");
    pos.add(
        "5r2/6pk/5qnp/4p3/2QpP3/P2R1NP1/1r3P1P/2R3K1 b - - bm Nf4; id \"STS(v7.0) Simplification.063\"; c0 \"Nf4=10, Nh4=9\"; cem -0.21; cee 0.00; ce -0.24;");
    pos.add(
        "5rk1/2p1q2p/1r2p2Q/3nNp2/8/6P1/1P3P1P/2RR2K1 w - - bm Rc6; id \"STS(v7.0) Simplification.064\"; c0 \"Rc6=10, Nc4=8, Rc2=9, Rd2=8\"; cem 0.53; cee 0.00; ce 0.46;");
    pos.add(
        "5rk1/p3qp1p/2r1p2Q/2b2p2/1n5N/2N3P1/1P2PP1P/R2R2K1 w - - bm e4; id \"STS(v7.0) Simplification.065\"; c0 \"e4=10, Kg2=4, Nf3=5, Rac1=4\"; cem 0.50; cee 0.00; ce 0.51;");
    pos.add(
        "5rk1/pb2qppp/1p2p3/7B/2P5/1P4QP/P4PP1/3R2K1 w - - bm Qd6; id \"STS(v7.0) Simplification.066\"; c0 \"Qd6=10, Be2=4, Qd3=4, Qe5=3\"; cem 0.54; cee 0.19; ce 0.45;");
    pos.add(
        "6bk/6p1/1b1q2n1/p6p/3N4/1P1Q4/P4PPP/3R2K1 w - - bm Nf3; id \"STS(v7.0) Simplification.067\"; c0 \"Nf3=10, Nc2=5, Ne2=4, Nf5=5\"; cem 0.14; cee 0.10; ce 0.22;");
    pos.add(
        "6k1/p4ppp/4p3/3n4/8/1Pn1PP1P/P3b1P1/R1B3K1 w - - bm Bd2; id \"STS(v7.0) Simplification.068\"; c0 \"Bd2=10, Bb2=9, Kf2=4, a4=5\"; cem -2.22; cee -1.25; ce -0.94;");
    pos.add(
        "6k1/pp3p1p/1q2r1p1/3p4/6n1/6P1/PPPQ1P1P/3R1BK1 w - - bm Qd4; id \"STS(v7.0) Simplification.069\"; c0 \"Qd4=10, Bh3=9, b3=5, c3=7\"; cem -0.25; cee 0.00; ce -0.02;");
    pos.add(
        "6rk/2R4p/Pp1pq3/1P2pp2/1P2b3/8/6PP/2Q2BK1 w - - bm Qc4; id \"STS(v7.0) Simplification.070\"; c0 \"Qc4=10, a7=5, g3=5, h4=4\"; cem 2.75; cee 2.15; ce 2.53;");
    pos.add(
        "7k/4r1p1/1p1b3p/p4q2/8/P5PP/1P1NQPK1/4R3 w - - bm Qf3; id \"STS(v7.0) Simplification.071\"; c0 \"Qf3=10, Ne4=7, Qd1=6, Qf1=1\"; cem -1.95; cee 0.70; ce -0.44;");
    pos.add(
        "7r/2q2pkp/2b1n1pN/3pP3/2pP4/4Q2P/2B2PP1/R5K1 w - - bm Bf5; id \"STS(v7.0) Simplification.072\"; c0 \"Bf5=10, Bd1=1\"; cem 2.14; cee -0.94; ce 1.03;");
    pos.add(
        "7r/p1P2kb1/1nB1b3/6pp/4p3/2P3BP/PP3PP1/R5K1 b - - bm Nd5; id \"STS(v7.0) Simplification.073\"; c0 \"Nd5=10, Nc4=7, e3=8, h4=7\"; cem 1.63; cee -0.15; ce 0.18;");
    pos.add(
        "8/p4k2/1p3r2/2pPnb2/2Pr3P/PPK3P1/4B1Q1/8 b - - bm Bg4; id \"STS(v7.0) Simplification.074\"; c0 \"Bg4=10, Bd3=7, Be4=7, Ng4=4\"; cem -3.07; cee -1.60; ce -2.11;");
    pos.add(
        "b5k1/q4p1p/3p1bp1/4p3/1NB1P3/3P3P/1Q3PP1/6K1 w - - bm Bd5; id \"STS(v7.0) Simplification.075\"; c0 \"Bd5=10, g3=4\"; cem 0.26; cee 0.03; ce 0.21;");
    pos.add(
        "b7/3r2bk/p2Np1p1/2B1p1Pp/qP2P2P/P2Q1RK1/8/8 w - - bm Rf7; id \"STS(v7.0) Simplification.076\"; c0 \"Rf7=10, Kh2=7, Qb1=7, Qe2=7\"; cem 0.94; cee 2.26; ce 1.55;");
    pos.add(
        "q1rr2k1/pbnn1pbp/1p4p1/4p1P1/2P1P3/1NN2B2/PP2QB1P/3RR1K1 w - - bm Bg4; id \"STS(v7.0) Simplification.077\"; c0 \"Bg4=10, Be3=6, Nc1=3, Nd5=4\"; cem 0.37; cee 1.82; ce 0.46;");
    pos.add(
        "r1b2kr1/pp1nbp2/4pn1p/2q3p1/B1P5/P1B2N1P/1P2QPP1/3R1KNR w - - bm Ne5; id \"STS(v7.0) Simplification.078\"; c0 \"Ne5=10, Nd2=6, Nh2=7, b4=5\"; cem 1.74; cee 0.80; ce 1.83;");
    pos.add(
        "r1b2rk1/1p2Bpbp/1np3p1/4P3/pn6/1N3N1P/1P1RBPP1/R5K1 b - - bm Nc2; id \"STS(v7.0) Simplification.079\"; c0 \"Nc2=10, N4d5=1\"; cem -0.34; cee -0.00; ce -0.33;");
    pos.add(
        "r1b2rk1/p1qn1ppp/2pb1n2/8/Np1NP3/7P/PPQ1BPP1/R1B2RK1 w - - bm Nf5; id \"STS(v7.0) Simplification.080\"; c0 \"Nf5=10, Nxc6=6, Rd1=7, f4=7\"; cem 0.26; cee -0.24; ce 0.36;");
    pos.add(
        "r1b2rk1/pp1p1ppp/4pn2/6q1/2PP4/P1N5/1PQ2PPP/R3KB1R w KQ - bm Qd2; id \"STS(v7.0) Simplification.081\"; c0 \"Qd2=10, Qc1=3, g3=6, h4=6\"; cem 0.35; cee 1.39; ce 0.62;");
    pos.add(
        "r1bR4/1p3pkp/1np3p1/4P3/pB6/1n3N1P/1P2BPP1/6K1 b - - bm Be6; id \"STS(v7.0) Simplification.082\"; c0 \"Be6=10, Bd7=6, Bf5=7, c5=8\"; cem 1.56; cee 0.06; ce 0.42;");
    pos.add(
        "r1bq1rk1/pp2ppbp/1n1n2p1/2p5/5B2/1PN2NP1/P1Q1PPBP/3R1RK1 w - - bm Nb5; id \"STS(v7.0) Simplification.083\"; c0 \"Nb5=10, Bxd6=2, Ng5=9, e4=4\"; cem -0.44; cee -0.37; ce -0.34;");
    pos.add(
        "r1bq2k1/ppbnr1p1/2p1ppBp/2Pp4/P2P3N/2Q5/1P1B1PPP/4RRK1 b - - bm Nf8; id \"STS(v7.0) Simplification.084\"; c0 \"Nf8=10, Rb8=4, a5=9, a6=3\"; cem 2.72; cee 0.82; ce 2.59;");
    pos.add(
        "r1q2r1k/1bBnbpp1/4p2p/pN1p3P/8/5BQ1/PPP2PP1/1K1R3R b - - bm Bc6; id \"STS(v7.0) Simplification.085\"; c0 \"Bc6=10\"; cem 0.27; cee 0.94; ce 0.19;");
    pos.add(
        "r2q1r1k/ppp3bp/3pb3/6p1/2Pn4/1PN3BP/P2QBPP1/3RR1K1 w - - bm Bg4; id \"STS(v7.0) Simplification.086\"; c0 \"Bg4=10, Bh5=6, Nb5=7, Nd5=6\"; cem 0.61; cee 0.14; ce 0.70;");
    pos.add(
        "r2q1rk1/1p3pp1/6p1/3Bn3/Ppp1P1P1/1n3P2/1P5P/1RBQ1RK1 w - - bm Bf4; id \"STS(v7.0) Simplification.087\"; c0 \"Bf4=10, Be3=8, Kh1=8, f4=7\"; cem -3.02; cee -1.71; ce -2.71;");
    pos.add(
        "r2r2k1/1q3pbp/p3b3/2pPN3/2p1RB2/7P/PP3PP1/R2Q2K1 w - - bm Nc6; id \"STS(v7.0) Simplification.088\"; c0 \"Nc6=10, Qe2=6, Qh5=3, Re2=3\"; cem 1.83; cee 1.71; ce 1.90;");
    pos.add(
        "r2r2k1/p2bppbp/1p4p1/2pP4/2q1P3/2P1B3/P1QN1PPP/2R2RK1 b - - bm Qa4; id \"STS(v7.0) Simplification.089\"; c0 \"Qa4=10, Qa6=2, Qb5=2, Qe2=6\"; cem 0.54; cee 0.25; ce 0.39;");
    pos.add(
        "r2r4/5kpp/p2q1n2/1p4B1/3b4/2Np1Q2/PP3PPP/3R1RK1 w - - bm Ne4; id \"STS(v7.0) Simplification.090\"; c0 \"Ne4=10, Bf4=1, Bxf6=2\"; cem 2.41; cee -1.22; ce 1.91;");
    pos.add(
        "r3k1r1/2q1bp2/2p1p1np/p1Pp2P1/Pp1Pn2P/1P2P3/1B2NNP1/R3QRK1 b q - bm Ng3; id \"STS(v7.0) Simplification.091\"; c0 \"Ng3=10, Nf6=1, Nxf2=3\"; cem 1.47; cee 0.76; ce 1.36;");
    pos.add(
        "r3kb1r/3n1ppp/p3p3/3bP3/Pp6/4B3/1P1NNPPP/R4RK1 w kq - bm Nf4; id \"STS(v7.0) Simplification.092\"; c0 \"Nf4=10, Bd4=8, Rac1=8, Rfc1=8\"; cem 0.08; cee -0.41; ce -0.05;");
    pos.add(
        "r3nbk1/1b3ppp/pr6/1pp1PBq1/P1P5/7P/1B1NQPP1/R3R1K1 w - - bm Be4; id \"STS(v7.0) Simplification.093\"; c0 \"Be4=10, Bg4=7, Qg4=4\"; cem -0.05; cee 0.34; ce 0.05;");
    pos.add(
        "r3r1k1/2q2np1/3p1pNp/p2P4/R4PP1/2P3K1/1P1Q4/7R w - - bm Re1; id \"STS(v7.0) Simplification.094\"; c0 \"Re1=10, Raa1=6\"; cem 0.40; cee 1.03; ce 0.69;");
    pos.add(
        "r3r1k1/5pp1/p1n2q1p/P1bp1P2/2p2P2/5N2/2BB3P/R2QRK2 b - - bm Nd4; id \"STS(v7.0) Simplification.095\"; c0 \"Nd4=10, Rad8=3, c3=6, d4=4\"; cem -0.88; cee 0.58; ce -0.83;");
    pos.add(
        "r4bk1/1p1nqp1p/p1p1b1p1/P3p3/2P1P3/1QN2PP1/1P5P/R4BBK b - - bm Qb4; id \"STS(v7.0) Simplification.096\"; c0 \"Qb4=10, Nc5=4, Qd6=1, Rb8=1\"; cem -0.54; cee -0.08; ce -0.52;");
    pos.add(
        "r4rk1/2pqbppp/p1n5/1p1pP3/3P4/1B2Bb1P/PP1Q1PP1/R1R3K1 w - - bm Qc3; id \"STS(v7.0) Simplification.097\"; c0 \"Qc3=10, Qc2=6, Rxc6=7, gxf3=1\"; cem -2.90; cee -4.05; ce -2.90;");
    pos.add(
        "r7/4pp1k/6pb/p1rPp2p/2P1P2q/7P/1RQ1BPP1/1R4K1 w - - bm Rb5; id \"STS(v7.0) Simplification.098\"; c0 \"Rb5=10, Bf1=6, Rb3=5, Rb8=6\"; cem 1.55; cee 0.88; ce 1.39;");
    pos.add(
        "rn3rk1/pp1q2bp/3ppnp1/2p3N1/2P1P3/8/PP2NPPP/R1BQ1RK1 w - - bm e5; id \"STS(v7.0) Simplification.099\"; c0 \"e5=10, Qd3=1\"; cem 0.44; cee -0.11; ce 0.52;");
    pos.add(
        "rq4k1/1b1nbpp1/4p2p/r1PnP3/Np6/1P1B1N2/1Q1B1PPP/R2R2K1 b - - bm Bc6; id \"STS(v7.0) Simplification.100\"; c0 \"Bc6=10, Bf8=4, Nxc5=6, Qa7=6\"; cem 1.11; cee 0.82; ce 1.01;");
    pos.add(
        "1qr2k1r/pb3pp1/1b2p2p/3nP3/1p6/3B2QN/PP3PPP/R1BR2K1 b - - bm g5; id \"STS(v8.0) AKPC.001\"; c0 \"g5=10, Bd4=4, Kg8=4, Rd8=3\"; cem -0.07; cee -0.84; ce -0.17;");
    pos.add(
        "1r1qr1k1/p5b1/2p2ppp/3p4/1Pp5/P1Nn1Q2/3BN1PP/R4RK1 b - - bm f5; id \"STS(v8.0) AKPC.002\"; c0 \"f5=10, a5=6, d4=7, Rb7=5\"; cem -0.44; cee 0.78; ce -0.42;");
    pos.add(
        "1r1rq1k1/1p4p1/pNb1pp1p/1pP5/3P4/1PQ5/5PPP/R3R1K1 w - - bm f3; id \"STS(v8.0) AKPC.003\"; c0 \"f3=10, Qd3=4, Rad1=4, Red1=4\"; cem -0.30; cee -0.79; ce -0.35;");
    pos.add(
        "1r3r2/1p3q1k/1Q1p4/2pNbpp1/2P5/7P/PP2R1P1/3R3K b - - bm g4; id \"STS(v8.0) AKPC.004\"; c0 \"g4=10, f4=7, Ra8=3, Rbe8=6\"; cem -0.18; cee -0.34; ce -0.32;");
    pos.add(
        "1r4k1/2p2p1p/4n1p1/2qpP3/2nN4/1BPQ4/Pr3PPP/3RR1K1 w - - bm h4; id \"STS(v8.0) AKPC.005\"; c0 \"h4=10, g3=7, h3=8, Qh3=9\"; cem 0.34; cee -0.18; ce 0.34;");
    pos.add(
        "1r4k1/3q2pp/2np4/2p1pp2/2P1P3/R1BP1Q1P/5PP1/6K1 b - - bm f4; id \"STS(v8.0) AKPC.006\"; c0 \"f4=10, fxe4=8, Ne7=6, Rb1+=5\"; cem 0.09; cee -0.27; ce -0.20;");
    pos.add(
        "1r4k1/ppq2ppp/r4nn1/P2p4/2pP4/B1P1P1PP/1Q1N1PK1/RR6 b - - bm h5; id \"STS(v8.0) AKPC.007\"; c0 \"h5=10, h6=1, Ne7=1, Nh5=1\"; cem -0.44; cee 0.44; ce -0.38;");
    pos.add(
        "1rb1r1k1/4qpb1/p1np1npp/1pp5/2P1PN2/1P3PP1/PB4BP/1QRR1N1K b - - bm g5; id \"STS(v8.0) AKPC.008\"; c0 \"g5=10, Bb7=5, bxc4=5, Ne5=6\"; cem -0.11; cee -0.10; ce -0.20;");
    pos.add(
        "1rq1n3/2pr3k/2n1bppp/p1PNp3/Pp2P3/1P2Q1PP/3R1PBK/3RN3 w - - bm f4; id \"STS(v8.0) AKPC.009\"; c0 \"f4=10, Bf1=6, Nc2=5, Nd3=6\"; cem 0.62; cee 0.57; ce 0.71;");
    pos.add(
        "2b2kn1/5ppQ/1r5p/p2pN3/1qpP1PP1/5P2/P6P/1B4RK w - - bm g5; id \"STS(v8.0) AKPC.010\"; c0 \"g5=10, Bg6=8, Qc2=8, Rd1=7\"; cem 1.01; cee -1.96; ce -0.05;");
    pos.add(
        "2qn4/p5k1/1p1Qbpp1/1B1p3p/3P3P/2P5/P4PP1/4N1K1 w - - bm g3; id \"STS(v8.0) AKPC.011\"; c0 \"g3=10, Kf1=3, Nc2=3, Nd3=2\"; cem 1.30; cee -0.30; ce 0.41;");
    pos.add(
        "2r1r1k1/p2b2bp/1pn1p1pq/4Pp2/2BP4/P4N2/1P2NQPP/2R2RK1 w - - bm h4; id \"STS(v8.0) AKPC.012\"; c0 \"h4=10, Ba2=8, g3=7, h3=6\"; cem 0.29; cee 0.45; ce 0.39;");
    pos.add(
        "2r2bk1/p1q2p2/4p1p1/3nP1B1/1p2Q2P/1P3N2/Pr3PP1/R4RK1 w - - bm h5; id \"STS(v8.0) AKPC.013\"; c0 \"h5=10, Bc1=4, Qd4=2, Qg4=6\"; cem 0.49; cee 0.00; ce 0.50;");
    pos.add(
        "2r2k1r/1q1nbpp1/p2p4/1p1Pp3/8/P1B2BP1/1PP1QP2/3RR1K1 b - - bm f5; id \"STS(v8.0) AKPC.014\"; c0 \"f5=10, g6=8, Kg8=5\"; cem 0.01; cee -0.47; ce -0.16;");
    pos.add(
        "2r2rk1/1b1qb1pp/p2p4/1p1PpP2/4Q3/1P2BN1P/P4PP1/R3R1K1 w - - bm g4; id \"STS(v8.0) AKPC.015\"; c0 \"g4=10, a4=2\"; cem 1.00; cee 0.95; ce 1.09;");
    pos.add(
        "2r2rk1/2qbnppp/1p2p3/p2pPn2/P5QP/2PB1N2/2PB1PP1/1R2R1K1 w - - bm h5; id \"STS(v8.0) AKPC.016\"; c0 \"h5=10, g3=5, Kf1=5, Rb3=6\"; cem 0.17; cee -0.28; ce 0.25;");
    pos.add(
        "2r2rk1/p3ppbp/1pnp1np1/4q3/2P1P3/PPN1B2P/2Q1BPP1/R2R2K1 w - - bm f4; id \"STS(v8.0) AKPC.017\"; c0 \"f4=10, b4=6, Qd3=7, Rab1=6, Rac1=8\"; cem 1.35; cee 0.85; ce 1.43;");
    pos.add(
        "2r2rk1/p4qb1/1p1ppp2/2n1n1pp/2PN4/1PN2PPP/P1QBP1K1/2RR4 w - - bm f4; id \"STS(v8.0) AKPC.018\"; c0 \"f4=10, Be3=9, Kg1=5, Ndb5=6\"; cem 0.86; cee 1.36; ce 0.97;");
    pos.add(
        "2r2rk1/pq2ppbp/1pnp1np1/8/2P1P3/2N1BP2/PPQ1BPKP/3R1R2 w - - bm f4; id \"STS(v8.0) AKPC.019\"; c0 \"f4=10, b3=4, Rfe1=4, Rg1=6\"; cem -0.01; cee 1.08; ce 0.11;");
    pos.add(
        "2r3k1/p3np1p/1p1pp1p1/4b3/1PP5/P3B2P/2R2PP1/3R3K w - - bm g4; id \"STS(v8.0) AKPC.020\"; c0 \"g4=10, Bd4=5, g3=5, Kg1=5\"; cem 1.11; cee 1.54; ce 1.54;");
    pos.add(
        "2r3k1/pp2p1b1/q2pP1p1/3pnrPp/P7/1P5P/2PB1PB1/2RQR1K1 w - - bm f4; id \"STS(v8.0) AKPC.021\"; c0 \"f4=10, a5=5, Be3=2\"; cem 0.09; cee 1.12; ce 0.34;");
    pos.add(
        "2r5/1bq3kp/4pNp1/3pP1Q1/2pP4/7P/5PP1/R5K1 w - - bm h4; id \"STS(v8.0) AKPC.022\"; c0 \"h4=10, Nh5+=6, Ra3=5, Rc1=5\"; cem 3.50; cee -0.02; ce 1.72;");
    pos.add(
        "2r5/5k2/p1n3p1/P1Pqpp1p/3n4/4BPQN/6PP/3R3K w - - bm f4; id \"STS(v8.0) AKPC.023\"; c0 \"f4=10, Ng5+=6\"; cem 0.72; cee -1.97; ce -0.25;");
    pos.add(
        "2rr1bk1/p4pp1/1p2q2p/1PpN4/2Q1P3/P4PP1/3R3P/3R2K1 w - - bm f4; id \"STS(v8.0) AKPC.024\"; c0 \"f4=10, Kg2=8, Qc3=6, Rf1=5\"; cem 0.40; cee 1.19; ce 0.74;");
    pos.add(
        "2rr2k1/5ppp/p3p3/1p2P3/1Pn5/2B1PqP1/P1Q2P1P/3RR1K1 b - - bm h5; id \"STS(v8.0) AKPC.025\"; c0 \"h5=10, g5=6, h6=7, Rxd1=7\"; cem -0.70; cee 0.00; ce -0.58;");
    pos.add(
        "3bn1k1/1bq1npp1/3p3p/1p1Pp3/1Pp1P3/2P1B1NP/2BN1PPK/Q7 b - - bm f5; id \"STS(v8.0) AKPC.026\"; c0 \"f5=10, Bc8=3, Qc8=2\"; cem 1.10; cee 0.56; ce 0.82;");
    pos.add(
        "3q1r2/2r2ppk/5b1p/2pBpR2/P3P2P/1P4PK/5Q2/5R2 w - - bm g4; id \"STS(v8.0) AKPC.027\"; c0 \"g4=10, a5=6, Bc4=5, Qg1=5\"; cem 0.00; cee 1.83; ce 0.53;");
    pos.add(
        "3q2k1/rb1r3p/6p1/pQRp1p2/3N3P/1P2PBP1/P4P2/6K1 w - - bm h5; id \"STS(v8.0) AKPC.028\"; c0 \"h5=10, a3=7, Bg2=7, Qa4=8\"; cem 0.82; cee 0.64; ce 0.85;");
    pos.add(
        "3q3k/3r1p1p/4p3/p4p2/PbP2Q1P/1p3N2/1P2KPP1/1R6 b - - bm f6; id \"STS(v8.0) AKPC.029\"; c0 \"f6=10\"; cem -1.87; cee 1.17; ce -0.35;");
    pos.add(
        "3q3k/5p1p/3rp3/p4p1P/PbP2Q2/1p3N2/1P2KPP1/1R6 b - - bm f6; id \"STS(v8.0) AKPC.030\"; c0 \"f6=10, Kg8=9, Qd7=2, Qf6=2\"; cem -1.34; cee 1.37; ce 0.00;");
    pos.add(
        "3qr1k1/5r1p/p1p1n1p1/Ppp1Q3/4Pp2/1P1P1N1P/2P2RPK/R7 b - - bm g5; id \"STS(v8.0) AKPC.031\"; c0 \"g5=10, h6=1, Rff8=1, Rg7=1\"; cem -0.35; cee 0.51; ce -0.17;");
    pos.add(
        "3qrr1k/pp1n1ppp/2n3b1/2p5/3Pp1P1/P1P1P2P/BB2QPN1/2RR2K1 b - - bm f5; id \"STS(v8.0) AKPC.032\"; c0 \"f5=10, f6=5, h6=6, Qe7=6\"; cem 0.50; cee 1.06; ce 0.41;");
    pos.add(
        "3r1r1k/1p2q2b/p1p1p2p/2P1P3/PPQP1pPP/5B2/7K/4BR2 b - - bm h5; id \"STS(v8.0) AKPC.033\"; c0 \"h5=10, Kg7=4, Rd7=3, Rde8=5, Rf7=6\"; cem -0.62; cee -0.43; ce -0.65;");
    pos.add(
        "3r1rk1/pb1pb2p/1p2p1q1/1N2npp1/B1P5/4PPN1/PP2Q1PP/3R1RK1 b - - bm h5; id \"STS(v8.0) AKPC.034\"; c0 \"h5=10, Bc5=3, Bc6=3, Qf6=1\"; cem -0.59; cee -0.63; ce -0.68;");
    pos.add(
        "3r1rk1/pb2bppp/1pp1p3/2n1P2Q/2P1P3/1Pp2NP1/P4PBP/2R3K1 b - - bm g6; id \"STS(v8.0) AKPC.035\"; c0 \"g6=10, h6=2, Nxe4=3, Rd7=4\"; cem 0.73; cee 0.13; ce 0.38;");
    pos.add(
        "3r2k1/2q1rp2/4p1p1/2p1P2p/1p3Q2/1P1bP1PP/P4RBK/2R5 w - - bm g4; id \"STS(v8.0) AKPC.036\"; c0 \"g4=10, Qf6=7\"; cem 0.58; cee 0.89; ce 0.77;");
    pos.add(
        "3r2k1/3n1p1p/p1r3p1/q1P3Q1/P5B1/2R1B2P/5PP1/6K1 b - - bm h6; id \"STS(v8.0) AKPC.037\"; c0 \"h6=10, h5=6, Nf6=7, Nf8=4\"; cem -0.11; cee -0.83; ce -0.45;");
    pos.add(
        "3r2k1/p1r3pp/Q1p1pb2/2Pn1p1q/PpBPp3/1P2P1PP/1B2RPK1/4R3 b - - bm g5; id \"STS(v8.0) AKPC.038\"; c0 \"g5=10, Qe8=2, Qf7=2, Rb8=2\"; cem -2.46; cee -1.16; ce -2.35;");
    pos.add(
        "3r2r1/2qb2pk/1p2p2p/p1n1Pp2/P1PNpP2/1P2Q3/3RB1PP/3R2K1 b - - bm g5; id \"STS(v8.0) AKPC.039\"; c0 \"g5=10, Be8=8, g6=8, Rb8=8, Rc8=8\"; cem 0.31; cee -0.16; ce 0.13;");
    pos.add(
        "3r4/1pq3kp/2pp1pp1/p1n1r3/P1PNP3/1P2BPPB/2Q1N1K1/8 w - - bm f4; id \"STS(v8.0) AKPC.040\"; c0 \"f4=10, Bc1=2, Bd2=2, Nf4=1\"; cem 1.01; cee -0.88; ce 0.50;");
    pos.add(
        "3rb1k1/2q1b1p1/pp2p3/5p1p/1PP1p3/P3Q1PP/4P1RK/3N1R2 b - - bm h4; id \"STS(v8.0) AKPC.041\"; c0 \"h4=10, a5=6, Bf6=5, Kh7=4\"; cem -1.83; cee -0.61; ce -1.50;");
    pos.add(
        "3rb1k1/2q1b1pp/pp2p3/5p2/1PP1p3/P3Q1PP/4PR1K/3N1R2 b - - bm h5; id \"STS(v8.0) AKPC.042\"; c0 \"h5=10, a5=8, g6=6, Rc8=8\"; cem -1.86; cee -0.36; ce -1.43;");
    pos.add(
        "3rrk2/p2n1p1B/1pq4p/2ppB1p1/2bP4/PnN1PP2/1PQ3PP/3RR1K1 w - - bm f4; id \"STS(v8.0) AKPC.043\"; c0 \"f4=10, Bd3=6, Bg3=1, Bh8=5\"; cem 2.60; cee 0.21; ce 2.63;");
    pos.add(
        "4b1k1/pp1qbpp1/4p3/4Q2p/8/2P1N1P1/PP3PBP/6K1 b - - bm h4; id \"STS(v8.0) AKPC.044\"; c0 \"h4=10, b6=1, g6=3, Qd2=1\"; cem 0.08; cee 0.22; ce 0.07;");
    pos.add(
        "4qr1k/p4n1p/1p1p2p1/2pQ1n2/2P1NB2/P1P2P2/5KPP/3R4 w - - bm g4; id \"STS(v8.0) AKPC.045\"; c0 \"g4=10, g3=6, h3=7, Rd3=6, Re1=6\"; cem 0.89; cee 1.35; ce 1.17;");
    pos.add(
        "4r1k1/p4bpp/1p6/1P6/2PQ4/4rPq1/PR2PRB1/6K1 b - - bm h5; id \"STS(v8.0) AKPC.046\"; c0 \"h5=10, Qc7=1, R3e5=4\"; cem -2.92; cee -1.15; ce -2.48;");
    pos.add(
        "4r2k/6p1/2b2p2/pq5p/r2P2P1/2P1N1PP/B2Q1K2/4R3 b - - bm h4; id \"STS(v8.0) AKPC.047\"; c0 \"h4=10, Be4=3, Qb8=4, Rb8=2\"; cem -1.74; cee -0.36; ce -1.36;");
    pos.add(
        "4r3/2pq1pk1/1r4p1/p2pP1Np/1pnP4/2PQ3P/P3RPP1/4R1K1 w - - bm f4; id \"STS(v8.0) AKPC.048\"; c0 \"f4=10, cxb4=8, e6=8, Kh2=5\"; cem 0.28; cee -1.04; ce -0.03;");
    pos.add(
        "4r3/p4rk1/1p4p1/3Rp1q1/4P2p/2Q2P2/P5PP/3R1K2 w - - bm h3; id \"STS(v8.0) AKPC.049\"; c0 \"h3=10, Qd2=3, R1d3=5, Rd7=8\"; cem 0.64; cee 0.45; ce 0.65;");
    pos.add(
        "4rr1k/1pq3bp/p1b2np1/P1Ppp3/1P1N2P1/1B3PB1/Q6P/3RR1K1 b - - bm h5; id \"STS(v8.0) AKPC.050\"; c0 \"h5=10, h6=7, Qb8=7, Re7=6\"; cem 1.03; cee 0.75; ce 0.93;");
    pos.add(
        "4rr1k/2q5/1pnn1b1p/p1p2b2/N1PppPp1/PP1P4/2QBPPBP/3R1RNK b - - bm h5; id \"STS(v8.0) AKPC.051\"; c0 \"h5=10, Re6=4, Rf7=4, Rg8=8\"; cem -1.55; cee -1.05; ce -1.64;");
    pos.add(
        "5k1r/1b2rp2/pR4pp/1p3n2/2p5/2N5/PP3PPP/3Q2K1 w - - bm f3; id \"STS(v8.0) AKPC.052\"; c0 \"f3=10, g4=7, h4=3, Rxb7=5\"; cem 3.22; cee 1.50; ce 2.33;");
    pos.add(
        "5k2/q7/2p2pp1/4p1pn/1pP1PnN1/1P2RPPP/3r1B1K/5Q2 b - - bm f5; id \"STS(v8.0) AKPC.053\"; c0 \"f5=10, Ne6=1, Nxg3=4, Qa2=1\"; cem -1.59; cee -0.94; ce -1.42;");
    pos.add(
        "5r1k/2qb1ppp/6nb/3B4/pp1BQ3/5NP1/P1P2P1P/4R1K1 w - - bm h4; id \"STS(v8.0) AKPC.054\"; c0 \"h4=10, Ba1=5, Ne5=7, Rb1=7\"; cem 0.43; cee 0.50; ce 0.54;");
    pos.add(
        "5r1k/2qb1ppp/6nb/3B4/ppPBQ3/5NP1/P4P1P/4R1K1 w - - bm h4; id \"STS(v8.0) AKPC.055\"; c0 \"h4=10, Be5=5, c5=7, Ne5=5\"; cem 0.57; cee 0.62; ce 0.67;");
    pos.add(
        "5r2/3br1kp/3q2p1/1pNp4/1P1Pnp2/5B2/2P1R1PP/2Q2RK1 b - - bm g5; id \"STS(v8.0) AKPC.056\"; c0 \"g5=10, Bc8=6, Bf5=1, Rfe8=1\"; cem -0.16; cee -0.52; ce -0.31;");
    pos.add(
        "5rk1/2r1bpp1/p2p1n1p/1p2pP2/1P3q2/1BP4P/PB3PP1/R2QR1K1 w - - bm g4; id \"STS(v8.0) AKPC.057\"; c0 \"g4=10, a4=7, Qc2=6, Qe2=7\"; cem -0.33; cee -0.50; ce -0.25;");
    pos.add(
        "5rk1/5p2/pnB1p1pp/q3P3/2nP1P2/1R1N3P/6PK/2Q5 w - - bm f5; id \"STS(v8.0) AKPC.058\"; c0 \"f5=10, Bb7=3, Nc5=3, Rc3=3\"; cem 1.29; cee 0.78; ce 1.18;");
    pos.add(
        "5rk1/p1r3pp/1p2q3/1P1p4/1QnB2P1/2R1PP1P/4R1K1/8 b - - bm h5; id \"STS(v8.0) AKPC.059\"; c0 \"h5=10, Qf7=8, Re7=9, Rf4=7\"; cem -0.95; cee -0.23; ce -0.82;");
    pos.add(
        "6k1/2qn3p/pp1pprpB/5r2/1PP5/P4PbP/3QB1P1/2R2RK1 w - - bm f4; id \"STS(v8.0) AKPC.060\"; c0 \"f4=10, Be3=3, Kh1=3, Rc3=4\"; cem 0.92; cee 0.82; ce 1.00;");
    pos.add(
        "6k1/5p1p/1p1n2pP/1n6/q1rP4/2pQP3/r1N2PP1/BRR3K1 b - - bm f5; id \"STS(v8.0) AKPC.061\"; c0 \"f5=10, f6=3\"; cem -0.87; cee -1.80; ce -1.12;");
    pos.add(
        "6k1/p2nqpp1/Qp2p2p/8/3PNb2/P4N2/1Pr2PPP/4R1K1 w - - bm g3; id \"STS(v8.0) AKPC.062\"; c0 \"g3=10, b4=1, Qxa7=1, Rb1=3\"; cem -0.88; cee -0.80; ce -0.75;");
    pos.add(
        "8/3qpk2/1p3r1p/p1pPRp2/2P2P2/P4KPP/4Q3/8 w - - bm g4; id \"STS(v8.0) AKPC.063\"; c0 \"g4=10, Qc2=6, Qd1=6, Qd3=6\"; cem 2.08; cee 2.05; ce 2.15;");
    pos.add(
        "8/3r1pk1/p1pqp1p1/8/P1Pb4/5RPP/2Q5/4R2K b - - bm f5; id \"STS(v8.0) AKPC.064\"; c0 \"f5=10, a5=7, c5=1, e5=6, Re7=1\"; cem 0.51; cee 0.50; ce 0.41;");
    pos.add(
        "8/6pk/p5rp/2NpPq2/2bPp2b/P3P1P1/6Q1/1R2B1K1 b - - bm h5; id \"STS(v8.0) AKPC.065\"; c0 \"h5=10, a5=4, Be2=7, Qg4=4\"; cem -3.09; cee -0.20; ce -2.05;");
    pos.add(
        "b4rk1/2pq1pp1/3p3p/B1p5/2PpP1n1/3P2P1/PR1Q1PBP/6K1 b - - bm f5; id \"STS(v8.0) AKPC.066\"; c0 \"f5=10, Ne5=8\"; cem 0.20; cee 0.36; ce 0.16;");
    pos.add(
        "b4rk1/p2q1pp1/3p3p/B1p5/2PpP1n1/P2P2P1/1R1Q1PBP/6K1 b - - bm f5; id \"STS(v8.0) AKPC.067\"; c0 \"f5=10, Re8=1\"; cem 0.44; cee 0.37; ce 0.31;");
    pos.add(
        "b5k1/6bp/3qp1p1/3n2P1/5P1K/2rBB3/6NP/3R1Q2 b - - bm h6; id \"STS(v8.0) AKPC.068\"; c0 \"h6=10, Bb7=2, Ne7=5, Rb3=2\"; cem -2.56; cee 0.00; ce -2.03;");
    pos.add(
        "n1rr2k1/3qppbp/p1nP2p1/6P1/5P1Q/1P2B3/P4PBP/2RR2K1 w - - bm f5; id \"STS(v8.0) AKPC.069\"; c0 \"f5=10, Bc5=4, Qh3=6, Rc4=2\"; cem 1.25; cee 0.46; ce 1.27;");
    pos.add(
        "nr4k1/1p1qrpb1/p6p/P2p2p1/1P1N4/4B1P1/2RQ1PKP/2R5 w - - bm h4; id \"STS(v8.0) AKPC.070\"; c0 \"h4=10, h3=4, Qd3=7, Rc5=3\"; cem 0.98; cee 0.63; ce 1.02;");
    pos.add(
        "q5k1/4bpp1/r3p2p/2p5/1pP5/1P3NPP/1RQ1PP2/6K1 b - - bm f5; id \"STS(v8.0) AKPC.071\"; c0 \"f5=10, Bf6=5, g6=5, Ra3=5\"; cem -1.84; cee -0.44; ce -1.19;");
    pos.add(
        "r1b1r1k1/1p1nqpbp/2ppn1p1/1P6/2P1P3/2N1BPP1/2Q1N1BP/1R1R2K1 w - - bm f4; id \"STS(v8.0) AKPC.072\"; c0 \"f4=10, bxc6=5, h3=5, Kh1=5\"; cem -0.46; cee 0.40; ce -0.36;");
    pos.add(
        "r1b2rk1/1pp1q1b1/2nppn1p/pN3pp1/2PP4/1Q4PN/PP2PPBP/R1BR2K1 w - - bm f4; id \"STS(v8.0) AKPC.073\"; c0 \"f4=10, d5=3\"; cem -0.13; cee -0.41; ce -0.04;");
    pos.add(
        "r1b2rk1/p4pp1/1q2p2p/1p1n4/3b2N1/3B2Q1/PPP2PPP/R1B2RK1 b - - bm f5; id \"STS(v8.0) AKPC.074\"; c0 \"f5=10, h5=5, Kh8=6, Rd8=7\"; cem 0.38; cee -0.12; ce 0.28;");
    pos.add(
        "r1b2rk1/pp2qppp/n2p4/2pPp3/P1N1P3/3P4/1P2BPPP/R1Q2RK1 w - - bm f4; id \"STS(v8.0) AKPC.075\"; c0 \"f4=10, b3=1\"; cem 1.13; cee 1.50; ce 1.29;");
    pos.add(
        "r1b2rk1/pp3pp1/1q2p2p/3n4/3b2N1/3B2Q1/PPP2PPP/R1B2RK1 b - - bm h5; id \"STS(v8.0) AKPC.076\"; c0 \"h5=10, f5=5, Kh8=6, Rd8=5\"; cem 0.88; cee 0.00; ce 0.77;");
    pos.add(
        "r1b4r/pp3k1p/3R1p2/B1p5/2P4Q/5q2/PP2NP1P/2K5 b - - bm h5; id \"STS(v8.0) AKPC.077\"; c0 \"h5=10, Be6=6, Rg8=3\"; cem -0.89; cee -2.00; ce -1.22;");
    pos.add(
        "r1q1k2r/3n3p/p5p1/1pbpP3/2p2P2/8/PPB1QP1P/R1B2RK1 w kq - bm f5; id \"STS(v8.0) AKPC.078\"; c0 \"f5=10, a4=5, Bb3=4, Rd1=3\"; cem 1.68; cee -0.62; ce 1.41;");
    pos.add(
        "r1q2rk1/p3npb1/1pnBb1pp/2p1P3/8/2N2NPP/PP2QPB1/R4RK1 w - - bm g4; id \"STS(v8.0) AKPC.079\"; c0 \"g4=10, h4=1, Qe4=2, Rfd1=4\"; cem -0.02; cee -0.36; ce 0.08;");
    pos.add(
        "r1r3k1/1bqn1pbp/pp1pp1p1/4n3/2P2N1P/1PN1PP2/P2BB1P1/2R1QRK1 w - - bm h5; id \"STS(v8.0) AKPC.080\"; c0 \"h5=10, Qf2=6, Qg3=5, Rd1=6\"; cem -0.74; cee -0.44; ce -0.64;");
    pos.add(
        "r2q1rk1/1p2n1bp/p1npp1p1/2p1p3/4P3/N1PPB1PP/1P3PB1/R2QR1K1 w - - bm h4; id \"STS(v8.0) AKPC.081\"; c0 \"h4=10, Nc2=2, Qb3=4, Qg4=5, Rf1=4\"; cem -0.68; cee -1.04; ce -0.59;");
    pos.add(
        "r2q1rk1/1p2p3/2p2ppp/p2n1b2/P2P4/1B3N1P/1PP2PP1/R2QR1K1 b - - bm g5; id \"STS(v8.0) AKPC.082\"; c0 \"g5=10, Bc8=6, Bd7=6, Kh7=6\"; cem 1.48; cee 0.89; ce 1.28;");
    pos.add(
        "r2r2k1/1p3p1p/1qbppQp1/p7/4P3/P1NR4/1P3PPP/1R4K1 w - - bm h4; id \"STS(v8.0) AKPC.083\"; c0 \"h4=10, b4=5, Rbd1=4, Rf3=6\"; cem 0.39; cee -0.68; ce 0.16;");
    pos.add(
        "r2r2k1/2pq1pp1/p1p2b1p/5p2/P2P4/2N3P1/1PQ1PP1P/R2R2K1 b - - bm f4; id \"STS(v8.0) AKPC.084\"; c0 \"f4=10, g5=9, Qe6=8, Rab8=7\"; cem -0.09; cee 0.74; ce 0.06;");
    pos.add(
        "r2r2k1/pp1qppbp/2p1bnp1/4B3/3P2P1/4QN1P/PP2PPB1/R1R3K1 b - - bm h5; id \"STS(v8.0) AKPC.085\"; c0 \"h5=10, a5=5, Bd5=2, h6=2\"; cem 0.59; cee 0.57; ce 0.49;");
    pos.add(
        "r3k3/1p1n1p2/2p1r1p1/3bP3/3P3Q/8/p1B2PPP/R5K1 w q - bm f4; id \"STS(v8.0) AKPC.086\"; c0 \"f4=10, Be4=8, f3=7, Qh6=5\"; cem 3.75; cee -0.62; ce 1.36;");
    pos.add(
        "r3qrk1/1p4bp/1n1p1p1n/pPpPp1p1/P3P3/1P1N3P/3B1PP1/2QBRRK1 w - - bm h4; id \"STS(v8.0) AKPC.087\"; c0 \"h4=10, Bc2=6, Bf3=7, Kh2=6, Nb2=7\"; cem 2.32; cee 1.21; ce 2.38;");
    pos.add(
        "r3qrk1/2p3pp/np6/3pN3/p2P4/P5P1/1PQ1PP2/3R1RK1 w - - bm f4; id \"STS(v8.0) AKPC.088\"; c0 \"f4=10, e3=6, e4=5, Rd2=6\"; cem 1.80; cee 0.63; ce 1.53;");
    pos.add(
        "r3r1k1/1pq4p/p2b1pp1/3pnb2/3N3Q/2P5/PP1B1PPP/R3RBK1 b - - bm g5; id \"STS(v8.0) AKPC.089\"; c0 \"g5=10, Bd7=3, Be4=3, Qd7=6\"; cem 1.62; cee 0.19; ce 1.50;");
    pos.add(
        "r3r1k1/4qpp1/2p5/p1ppP2p/6P1/1PQ2P1P/P1P5/3RR1K1 w - - bm f4; id \"STS(v8.0) AKPC.090\"; c0 \"f4=10, a4=1, Kg2=2, Re2=1\"; cem -0.13; cee -0.04; ce 0.00;");
    pos.add(
        "r3r1k1/pn1bnpp1/1p2p2p/1q1pP3/2pP1P1N/B1P2BP1/2P3QP/R4RK1 w - - bm f5; id \"STS(v8.0) AKPC.091\"; c0 \"f5=10, Bxe7=6, g4=5, g4=6, Rfb1=4\"; cem -0.63; cee -1.78; ce -0.56;");
    pos.add(
        "r3rb2/1p3k2/p1p2p2/2q2bpp/2PR4/1PQ4P/P4PPB/3R1BK1 b - - bm h4; id \"STS(v8.0) AKPC.092\"; c0 \"h4=10, Be7=6, g4=5, Kg7=6\"; cem 1.50; cee -0.56; ce 1.08;");
    pos.add(
        "r4k2/1b1qbp2/2pnp1r1/pp2B2p/2pPP1pP/P1N2P2/1P2B1PQ/2RR2K1 b - - bm f5; id \"STS(v8.0) AKPC.093\"; c0 \"f5=10, f6=2, gxf3=4, Kg8=2\"; cem 0.41; cee -1.65; ce 0.28;");
    pos.add(
        "r4rk1/1b3pbp/1qp1p1p1/p2n2B1/3P3P/P2B1N2/1PQ2PP1/R3R1K1 w - - bm h5; id \"STS(v8.0) AKPC.094\"; c0 \"h5=10, Bd2=1, Rac1=7, Re2=1\"; cem -0.16; cee -0.24; ce -0.06;");
    pos.add(
        "r4rk1/1p1qn1bp/p1npp1p1/2p1p3/4P2P/N1PPB1P1/1P3PB1/R2QR1K1 w - - bm h5; id \"STS(v8.0) AKPC.095\"; c0 \"h5=10, Bh3=8, Nc4=8, Rf1=4\"; cem -0.63; cee -1.08; ce -0.55;");
    pos.add(
        "r4rk1/1p2qppp/1np5/p2pNb2/P2Pn3/2NBP3/1PQ2PPP/2R2RK1 b - - bm f6; id \"STS(v8.0) AKPC.096\"; c0 \"f6=10, g6=6, h6=7, Nxc3=7\"; cem 0.22; cee 0.12; ce 0.12;");
    pos.add(
        "r4rk1/1pq1p3/2p2ppp/p2n1b2/P2P4/1B3N1P/1PP2PP1/R2QR1K1 b - - bm g5; id \"STS(v8.0) AKPC.097\"; c0 \"g5=10, Bc8=7, Kh7=7, Rf7=7\"; cem 1.37; cee 0.83; ce 1.18;");
    pos.add(
        "r4rk1/3n1pbp/3Pq1p1/4p3/p7/2Q1B1N1/P2R1PPP/2R3K1 b - - bm f5; id \"STS(v8.0) AKPC.098\"; c0 \"f5=10, h6=4, Nf6=5, Rfd8=5\"; cem 0.08; cee 0.25; ce 0.00;");
    pos.add(
        "r4rk1/ppp1qp1p/2p3p1/4n3/4P2P/2N1QP2/PPP2P2/2KR3R w - - bm h5; id \"STS(v8.0) AKPC.099\"; c0 \"h5=10, b3=1, Nb1=5, Ne2=4\"; cem -0.16; cee 0.70; ce 0.20;");
    pos.add(
        "r5k1/p2b1r2/1p1p1q2/2pPbp1p/P1P1p3/1P4P1/2RQNPBP/4R1K1 b - - bm h4; id \"STS(v8.0) AKPC.100\"; c0 \"h4=10, Kh7=8, Qg7=8, Rg7=8\"; cem 0.12; cee 0.33; ce 0.05;");
    pos.add(
        "1b2r1k1/1bqn1pp1/p1p4p/Pp2p3/1P2B3/2B1PN1P/5PP1/1Q1R2K1 b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.001\"; c0 \"c5=10, Kf8=1, Nf8=2, g5=2\"; cem 1.51; cee 0.69; ce 1.22;");
    pos.add(
        "1k4nr/pppr1q2/3p2p1/3Nn1Qp/2P1PN2/1P6/P1P3PR/2KR4 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.002\"; c0 \"c5=10, Nd3=5, Rf1=2, Rhh1=3\"; cem 0.12; cee 0.82; ce 0.34;");
    pos.add(
        "1n4k1/2pb2q1/1p1p3p/3P1p2/1PP1pP2/3rN3/4N1PP/1RQ3K1 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.003\"; c0 \"b5=10\"; cem 0.04; cee 0.47; ce 0.30;");
    pos.add(
        "1nr5/p2p1qpk/1pb1p2p/5p1P/1PP2N2/P3PPQ1/6P1/3RKB2 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.004\"; c0 \"b5=10, Kf2=5, Ng6=6, a4=5\"; cem -0.03; cee 1.35; ce 0.60;");
    pos.add(
        "1q2r1k1/1b2bpp1/p2ppn1p/2p5/P3PP1B/2PB1RP1/2P1Q2P/2KR4 b - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.005\"; c0 \"c4=10, Bc6=5, Qa7=4, Qa8=5, Qc8=5, b8a8=5\"; cem -0.06; cee 2.45; ce 0.35;");
    pos.add(
        "1q2rb2/3b1r1k/p1p4p/B3p1p1/1PPpN3/3P1P1P/3QR1P1/4R1K1 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.006\"; c0 \"c5=10, Kh1=7, Qb2=6, Ra1=7\"; cem 1.46; cee 0.82; ce 1.46;");
    pos.add(
        "1r1qr1k1/1p3pp1/p1n1bn1p/2NN4/4P3/6P1/PP3PB1/2RQR1K1 w - - bm b3; id \"STS(v9.0) Advancement of a/b/c pawns.007\"; c0 \"b3=10, Qd2=5, Rf1=7, a4=5\"; cem 0.57; cee 0.75; ce 0.67;");
    pos.add(
        "1r1r1bk1/1bq2ppp/pnp1p3/4P3/5P2/P1NBB3/1P4PP/R1QR2K1 b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.008\"; c0 \"c5=10, Nd5=5, Rbc8=5, h6=2\"; cem 1.00; cee 1.15; ce 0.90;");
    pos.add(
        "1r1r2k1/p1p1p3/4n1p1/5p2/8/R3B3/Pn2BPPP/4K2R b K - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.009\"; c0 \"c5=10, Kf7=5, Nd4=5, Rb4=3\"; cem -0.86; cee -0.17; ce -0.53;");
    pos.add(
        "1r1r2k1/pb3ppp/1p1ppn2/8/PPPP1P2/2NB2P1/3K3P/RR6 w - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.010\"; c0 \"a5=10, Ke2=6, Nb5=8, Ra3=6\"; cem -1.57; cee 1.63; ce 0.48;");
    pos.add(
        "1r1r3k/p4pp1/3nb2p/2p1q3/8/P1PBPP2/2Q3PP/R1B2RK1 b - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.011\"; c0 \"c4=10, Bb3=3, Nc8=4, Rb3=4\"; cem -0.53; cee -0.10; ce -0.55;");
    pos.add(
        "1r2r1k1/2qp1ppp/2p1pn2/8/8/2PBQ1P1/PP3P1P/R4RK1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.012\"; c0 \"b4=10, Qd2=2, b3=4\"; cem -1.09; cee -0.23; ce -0.73;");
    pos.add(
        "1r2r1k1/2qp1ppp/2p2n2/4p3/1P6/2PBQ1P1/P4P1P/R4RK1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.013\"; c0 \"a4=10, Bc2=1\"; cem -1.33; cee -0.24; ce -0.90;");
    pos.add(
        "1r2rn2/1p3pk1/p4npp/P2p4/1P1N2P1/1P3P1P/4BK2/2R1R3 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.014\"; c0 \"b5=10, Bf1=6, Rc2=6, Rc7=7\"; cem 0.21; cee 0.97; ce 0.77;");
    pos.add(
        "1r3rk1/2qnbppp/2p1p3/pbPpP3/8/PP2QNP1/3B1PBP/2R1R1K1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.015\"; c0 \"b4=10, Bh1=4, Qd4=3, h4=2\"; cem 0.45; cee 0.36; ce 0.54;");
    pos.add(
        "1r4k1/p3pr1p/1qbp1pp1/2p5/2P5/1PN1Q2P/P2R1PP1/3R3K b - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.016\"; c0 \"a5=10, Kg7=7, Qb7=8, a6=6\"; cem -0.06; cee -0.48; ce -0.28;");
    pos.add(
        "1rbr2k1/p1q2p1p/2n1p1p1/1p4Q1/2pPP3/2P3P1/P1B1N1PP/2R2RK1 b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.017\"; c0 \"b4=10, Qe7=8, Rb6=6, a5=7\"; cem 1.07; cee 0.86; ce 0.93;");
    pos.add(
        "1rq2r2/Q3pp1k/1B1p2pb/4n2p/2PN4/PP5P/3nBPP1/R2R2K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.018\"; c0 \"a4=10, Ra2=2, c5=2\"; cem 1.07; cee 1.31; ce 1.17;");
    pos.add(
        "1rr3k1/p2qbpnp/Ppp3p1/3pP3/3P4/1Q1B1P2/1P1B1P1P/2R3RK b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.019\"; c0 \"c5=10, Ne6=6, Nf5=4, Qe6=5\"; cem 0.91; cee 0.49; ce 0.75;");
    pos.add(
        "1rrqb3/1p3kp1/p3p2p/P1Rp1p1P/3P1P2/6P1/1P1QBPK1/2R5 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.020\"; c0 \"b4=10, Bd3=5, Qe1=5, b3=5\"; cem 2.05; cee 2.49; ce 2.28;");
    pos.add(
        "2r1n1k1/1p1R1r1p/2q1pp1B/p5p1/6P1/Q1P4P/PP6/1K1R4 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.021\"; c0 \"b5=10, b6=7\"; cem 0.82; cee -0.74; ce 0.25;");
    pos.add(
        "2r1r1k1/1b1n1pp1/1q2p2p/3p3n/P2N4/BP1NPP1P/2B3P1/2R1Q1K1 w - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.022\"; c0 \"a5=10\"; cem 0.74; cee 0.43; ce 0.81;");
    pos.add(
        "2r1r1k1/1b1q1pp1/4p2p/1Nnp3n/1N6/PP2PP1P/1BB2QP1/3R2K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.023\"; c0 \"a4=10, Na7=1\"; cem 0.45; cee -0.49; ce 0.48;");
    pos.add(
        "2r1r1k1/1p1npp1p/p2pb1p1/q7/2PnP3/1PNN2PP/P2Q1PB1/2RR2K1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.024\"; c0 \"b4=10, Nf4=5, Qb2=6, Qe3=3\"; cem 0.14; cee 0.36; ce 0.25;");
    pos.add(
        "2r2bk1/R4pp1/3pqnnp/R3p3/1P2P3/3Q1NNP/2rB1PPK/8 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.025\"; c0 \"b5=10, R5a6=3, R7a6=3, Ra8=1\"; cem -0.01; cee 1.25; ce 0.11;");
    pos.add(
        "2r2r1k/1bqnbppp/p3p3/1p1pP3/3B1P2/P1NB4/1PP2QPP/R4R1K w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.026\"; c0 \"b4=10, Qe3=4, Rad1=4, Rae1=5\"; cem 0.34; cee 0.34; ce 0.43;");
    pos.add(
        "2r2rk1/1p2bpp1/p3p3/4P1N1/PqB3P1/1Pn2NQ1/2b2P1P/R3R1K1 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.027\"; c0 \"b5=10, Bg6=4, Rfd8=3, Rxc4=4\"; cem -1.26; cee 0.00; ce -1.32;");
    pos.add(
        "2r2rk1/pp3ppp/2nb1q2/P3pb2/2P5/1NB3P1/1Q2PPBP/R4RK1 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.028\"; c0 \"c5=10, Nd2=3, Rfd1=4, f4=4\"; cem 0.53; cee 0.21; ce 0.62;");
    pos.add(
        "2r3k1/1pq1b1p1/p1b1pr1p/8/PP2pP2/2P1B3/4B1PP/RQR4K w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.029\"; c0 \"b5=10, Rd1=3, Rf1=3, g3=5\"; cem -0.10; cee 0.39; ce 0.08;");
    pos.add(
        "2r3k1/5p2/pr3p1p/8/3N2q1/P3P1P1/1P1Q1PP1/5RK1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.030\"; c0 \"b4=10\"; cem 1.13; cee 0.39; ce 0.86;");
    pos.add(
        "2r5/1r2nkp1/p3p2p/1p1b1p1P/8/PP3P2/1KPRB1P1/R5B1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.031\"; c0 \"a4=10, Bd4=7, Bh2=8, g4=7\"; cem 0.71; cee -0.23; ce 0.23;");
    pos.add(
        "2r5/3npk1p/1p1p1r2/pP1P2p1/3N1pP1/1P3P2/2P1R2P/R5K1 w - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.032\"; c0 \"c4=10, Kf2=6, Kg2=5, Nc6=3\"; cem 1.30; cee 0.22; ce 0.58;");
    pos.add(
        "2rqr1k1/5ppp/n4b2/pbP5/3p4/P4NP1/1N2PPBP/2RQR1K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.033\"; c0 \"a4=10, Na4=2, Nd3=4, c6=2\"; cem 0.65; cee 0.80; ce 0.75;");
    pos.add(
        "2rr2k1/1b3ppp/pp2pn2/2n5/2BNP3/4KPNP/PP4P1/2R4R w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.034\"; c0 \"b4=10\"; cem -2.84; cee 0.71; ce -1.08;");
    pos.add(
        "2rr2k1/1q3pp1/pp1pp2p/4n3/2PNP3/1P4P1/P1R1QPKP/3R4 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.035\"; c0 \"b5=10, Nd7=7, Qc7=7, Rc5=7\"; cem -0.29; cee 0.46; ce -0.15;");
    pos.add(
        "3b2rk/5pp1/2q2n1p/1p2BP2/7P/2NQ4/1PP5/1K4R1 b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.036\"; c0 \"b4=10, Bb6=1, Nd7=4, Re8=1\"; cem 0.30; cee 1.01; ce 0.48;");
    pos.add(
        "3q2k1/1p1bprbp/3pn1p1/3N4/1PP5/r3B3/3QBPPP/2R1R1K1 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.037\"; c0 \"c5=10, Bd1=4, Bg4=4, Rb1=5\"; cem 0.94; cee 0.73; ce 1.03;");
    pos.add(
        "3r1nk1/pp2q2p/2p3p1/2NnPp2/5P2/1Q6/PP1rNRPP/2R3K1 b - - bm b6; id \"STS(v9.0) Advancement of a/b/c pawns.038\"; c0 \"b6=10, Nd7=3, Rb8=2, b5=1\"; cem 0.06; cee -0.38; ce -0.11;");
    pos.add(
        "3r1rk1/2pq2pp/p4pb1/1p6/4P1P1/2PnQN1P/PP1N1P2/RR4K1 b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.039\"; c0 \"c5=10, Qd6=3, Rfe8=1\"; cem -0.42; cee 0.41; ce -0.37;");
    pos.add(
        "3r1rk1/3n1pbp/1p1p2p1/pN1Ppn1q/2P3b1/PPN3P1/1B1Q1P1P/R3R1KB w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.040\"; c0 \"b4=10, Na4=5, Ne4=6, Qc2=7\"; cem -0.19; cee 0.05; ce -0.10;");
    pos.add(
        "3r2k1/1p2bpp1/pNrppnqp/8/1PPBP3/P4Q1P/5PP1/3RR1K1 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.041\"; c0 \"b5=10, Qd3=3, Rd3=2, a4=8\"; cem 0.55; cee -0.02; ce 0.55;");
    pos.add(
        "3r2k1/5p2/p1prb2p/1p2q3/4Pp2/1P1B1P2/P3Q1PP/2RR1K2 b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.042\"; c0 \"c5=10, Kh7=6, Qh5=4, R8d7=5\"; cem -0.80; cee -0.43; ce -0.78;");
    pos.add(
        "3rn1k1/1pqn1pp1/p3br1p/2P1p3/P3P3/3BBN1P/2P3PQ/RR5K w - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.043\"; c0 \"a5=10, Ra3=3, Rb4=6\"; cem 0.22; cee 0.88; ce 0.33;");
    pos.add(
        "3rr1k1/1bqn1ppp/p1p5/1p2b3/4P3/P3BB1P/1PQ1NPP1/1R1R3K b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.044\"; c0 \"c5=10, h6=3\"; cem -0.21; cee -0.32; ce -0.30;");
    pos.add(
        "3rr1k1/1p5p/p2B1bp1/5b2/1RP2P2/1PN5/1P1R1K1P/8 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.045\"; c0 \"c5=10, Kf3=6, Kg2=6, Rd5=1\"; cem -2.52; cee -0.84; ce -1.40;");
    pos.add(
        "3rr1k1/1q2bp1p/2b3pB/3npP2/1p6/5B2/NPP3PP/3RQR1K w - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.046\"; c0 \"c4=10, Qe2=5, c3=3, fxg6=4\"; cem 0.21; cee -0.50; ce 0.29;");
    pos.add(
        "4b3/ppr1bpk1/2n1p1p1/1B1rP2p/3P3P/P1R1BNP1/5PK1/2R5 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.047\"; c0 \"a4=10, Rb1=5\"; cem 0.06; cee -0.11; ce 0.08;");
    pos.add(
        "4q1k1/p5p1/1rp1p1p1/2R1Pp2/1PQ5/P5P1/5PK1/8 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.048\"; c0 \"a4=10, Qc3=3\"; cem -0.19; cee 0.43; ce 0.32;");
    pos.add(
        "4r1k1/3bpr1p/1qpp1ppQ/p7/2P1N3/1P5P/P2R1PP1/3R3K b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.049\"; c0 \"c5=10, Bf5=6, Qa7=7, Qc7=5\"; cem -0.21; cee -0.96; ce -0.53;");
    pos.add(
        "4r1k1/p2bpr1p/1qpp2pQ/5p2/2P4P/1P4N1/P2R1PP1/3R3K b - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.050\"; c0 \"a5=10, Qc5=5, Ref8=7, Rg7=7\"; cem -0.59; cee -1.18; ce -0.87;");
    pos.add(
        "4rk2/2p1rpp1/pbn3bp/1p2P3/PP6/1BP2NNP/5PP1/2R1R1K1 w - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.051\"; c0 \"a5=10, Nh4=3, axb5=5, e6=5\"; cem 1.62; cee 0.77; ce 1.31;");
    pos.add(
        "4rrk1/1q4pp/4p3/2bp4/5P2/3Q4/1PP3PP/R1B2R1K w - - bm c3; id \"STS(v9.0) Advancement of a/b/c pawns.052\"; c0 \"c3=10, Ra4=6, Ra5=6, b3=8\"; cem 0.31; cee 0.67; ce 0.51;");
    pos.add(
        "4rrk1/p2n2pp/qp2pb2/2p5/2P2P2/P1BP1Q2/4N1PP/1R3RK1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.053\"; c0 \"a4=10, Bxf6=6, Qe4=2, h3=6\"; cem 0.20; cee 0.30; ce 0.31;");
    pos.add(
        "5nk1/1bq2pp1/rp1r1b1p/1R1p1B2/2pP4/2N1PN2/PQ3PPP/1R4K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.054\"; c0 \"a4=10, Ne5=5, Rb4=6, h4=2\"; cem -0.11; cee 0.51; ce 0.00;");
    pos.add(
        "5r1k/1p2q1pp/p1p2n2/5r2/3Pp3/4P2R/PPB2PQP/6RK b - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.055\"; c0 \"c5=10\"; cem 0.23; cee -0.20; ce 0.00;");
    pos.add(
        "5r2/5r1k/p2pN1p1/1p1Pn2p/2q1P2b/P4pR1/1PPQ1B1P/1K2R3 w - - bm b3; id \"STS(v9.0) Advancement of a/b/c pawns.056\"; c0 \"b3=10, Nxf8+=2\"; cem 1.97; cee -1.57; ce 1.49;");
    pos.add(
        "5rk1/1prn1qp1/p2p3p/3Ppp1P/7R/1NPQ1PP1/PP6/1K1R4 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.057\"; c0 \"b5=10, Nc5=5, Re8=6, Rfc8=3\"; cem -1.06; cee 0.32; ce -0.72;");
    pos.add(
        "5rk1/2nq2pp/3p1p2/3P4/3B1Q2/PrN3P1/4PP1P/R5K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.058\"; c0 \"a4=10, Qe4=4, e3=2, h4=4\"; cem -0.73; cee -1.27; ce -0.82;");
    pos.add(
        "5rk1/p2qp2p/1p1p1pp1/2nN1P1r/P1P1P3/1P2R1P1/1Q4KP/4R3 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.059\"; c0 \"b4=10, Qd2=6, Ra1=5, h4=5\"; cem 0.74; cee 1.56; ce 1.09;");
    pos.add(
        "6k1/1b2bp1p/p3p3/1p2P1p1/2r5/2N1BP2/PP4PP/3R2K1 b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.060\"; c0 \"b4=10, Bc6=4, Kf8=4\"; cem -0.28; cee 0.12; ce -0.04;");
    pos.add(
        "6k1/1p1n1rbp/r2n1pp1/4p3/4P3/4B1P1/PP2N1BP/R4RK1 w - - bm b3; id \"STS(v9.0) Advancement of a/b/c pawns.061\"; c0 \"b3=10, Bf2=5, Rad1=2, Rfc1=6\"; cem 0.28; cee 0.00; ce 0.24;");
    pos.add(
        "6k1/1pr2rp1/p4q1p/2p1p3/2Q1P3/P6P/2P2PP1/3R1RK1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.062\"; c0 \"a4=10, Qc3=5, Rd3=5, Rd5=5\"; cem 0.63; cee 1.14; ce 0.95;");
    pos.add(
        "6k1/2rqbrpp/2n2n2/pBpppN2/Pp4P1/2PP3P/1P2QP2/R1B1R1K1 w - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.063\"; c0 \"c4=10, Bg5=5, Bxc6=4, cxb4=4\"; cem 0.90; cee 0.77; ce 1.00;");
    pos.add(
        "6k1/4brpp/3p4/p1q1p3/1pN1Pr2/1P1R1P2/P1P3QP/1K4R1 b - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.064\"; c0 \"a4=10, Bf8=5, Kh8=7, R4f6=3\"; cem 0.69; cee 0.68; ce 0.59;");
    pos.add(
        "6k1/5r2/p4pp1/Br1pp3/2b5/6NP/1P3RP1/2R4K w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.065\"; c0 \"b4=10, Bc3=3, Bd8=5, Be1=6\"; cem 1.18; cee 0.28; ce 0.61;");
    pos.add(
        "8/5p2/bk2p3/1pr1Pp2/3p4/bP3BPP/P1nBN3/1R1K4 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.066\"; c0 \"b4=10, Bg5=7, Nf4=7, h4=7\"; cem -2.35; cee -0.31; ce -0.85;");
    pos.add(
        "8/r2b2pk/2p1pr1p/3nQp2/p7/qP3N2/P1PRBPPP/4R1K1 w - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.067\"; c0 \"c4=10, Bd3=5, Red1=5, h3=5\"; cem 0.21; cee 0.50; ce 0.36;");
    pos.add(
        "b2qr2k/2p3pp/8/1p3p2/2p2B2/2Pp1PPP/5QB1/R6K b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.068\"; c0 \"b4=10, Bc6=2, Bd5=2, h6=2\"; cem 0.15; cee -0.19; ce -0.10;");
    pos.add(
        "r1b2k2/ppq2p1p/1np3p1/4p3/2n5/3N2P1/P1Q1PPBP/R2R2K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.069\"; c0 \"a4=10, Qb3=1, Qc3=3\"; cem 0.22; cee 0.47; ce 0.37;");
    pos.add(
        "r1bq1r2/1pp3bk/3p1npp/1N1Ppn2/PBP1N3/1Q1B4/5PPP/R4RK1 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.070\"; c0 \"c5=10, Nxf6+=6, Rae1=6, Rfe1=5, a5=6\"; cem 0.27; cee 0.47; ce 0.37;");
    pos.add(
        "r1r3k1/3nqpp1/1pp1pn1p/p2pN3/2PP1Q2/1P4P1/P2BPPKP/2RR4 b - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.071\"; c0 \"a4=10, Nxe5=6, Qe8=6, Ra7=7\"; cem 0.08; cee 0.32; ce 0.02;");
    pos.add(
        "r1r3k1/p3pp1p/3p2pb/R1nP4/4PP2/2Pp4/1P1N2PP/2BR2K1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.072\"; c0 \"b4=10, Nc4=5, Rf1=2, g3=6\"; cem -0.60; cee -0.99; ce -0.74;");
    pos.add(
        "r1r4k/1q1nbpp1/2bp1n1p/pp2pP2/4P3/P1N2BQ1/1PPN1BPP/2R1R1K1 b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.073\"; c0 \"b4=10, Nb6=7, Rc7=7, Rd8=7\"; cem -0.38; cee -0.85; ce -0.48;");
    pos.add(
        "r2q1nk1/1p2rppp/p1p5/3p3b/PP1P4/2NBP3/2Q2PPP/2R2RK1 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.074\"; c0 \"b5=10, Ne2=7, Qb3=6, h3=6\"; cem 0.81; cee 1.16; ce 0.96;");
    pos.add(
        "r2rn1k1/3nqppp/1pp1p3/3p4/pPPP4/P2QN1P1/1B2PP1P/2RR2K1 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.075\"; c0 \"b5=10, Re1=2, cxd5=3, f3=2\"; cem 0.44; cee -0.32; ce 0.40;");
    pos.add(
        "r3kr2/pp1b1pp1/2p1p2p/4q3/7Q/2PBPR2/PP4PP/2KR4 b q - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.076\"; c0 \"c5=10, Qg5=4, a5=2, g5=1\"; cem 1.07; cee -0.09; ce 0.62;");
    pos.add(
        "r3r1k1/1p3pp1/1qp1b2p/p1Rn4/3p4/P2P1BP1/1P1BPP1P/2Q1R1K1 b - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.077\"; c0 \"a4=10, Kh7=1, Nf6=1, Red8=1\"; cem 0.26; cee -0.28; ce 0.08;");
    pos.add(
        "r3r1k1/1pb1qpp1/2p1b2p/p3P3/4Q3/2B1PN1P/PP3PP1/3RR1K1 w - - bm a3; id \"STS(v9.0) Advancement of a/b/c pawns.078\"; c0 \"a3=10, Qb1=5, a4=8, b3=4\"; cem 0.10; cee -0.05; ce 0.17;");
    pos.add(
        "r3r1k1/pn1qnppp/1p2p3/3pP3/b1pP4/B1P2N2/R1PQBPPP/R5K1 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.079\"; c0 \"b5=10, Bc6=6, Nc6=2, Nc8=2\"; cem -0.69; cee -1.84; ce -0.81;");
    pos.add(
        "r3rbk1/1npq1pp1/p2p3p/1p6/1P1P4/P1R1BN1P/5PP1/3QR1K1 b - - bm c6; id \"STS(v9.0) Advancement of a/b/c pawns.080\"; c0 \"c6=10, Nd8=5, Rac8=6\"; cem 0.08; cee 0.00; ce -0.03;");
    pos.add(
        "r3rbk1/1qpn1pp1/p4n1p/1p2pN2/2P1P3/5N2/PPQB1PPP/R3R1K1 b - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.081\"; c0 \"b4=10, Nc5=5, Re6=2, bxc4=4\"; cem 0.16; cee -0.27; ce 0.05;");
    pos.add(
        "r3rn1k/1q2b1p1/pp1p1n1p/4pQ2/P3P3/1NN1BR2/1PP3PP/5R1K b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.082\"; c0 \"b5=10, N8h7=4, Rab8=5, Rac8=5\"; cem 1.29; cee 0.25; ce 1.16;");
    pos.add(
        "r4rk1/1p4bp/2qp1np1/p1n1p3/2P5/P1B1N3/1PB2PPP/R2Q1RK1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.083\"; c0 \"b4=10, Qe1=5, Qe2=6, f3=3\"; cem 0.45; cee -0.19; ce 0.52;");
    pos.add(
        "r4rk1/2Q2ppp/4p3/p3P3/q1P5/P5P1/5P1P/2R1R1K1 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.084\"; c0 \"c5=10, Qb7=6, Rc3=5, Re3=6\"; cem 0.90; cee 1.42; ce 1.23;");
    pos.add(
        "r4rk1/2qb1ppp/p2pp3/1pn3P1/3RPP2/P1N2Q2/1PP3BP/2KR4 b - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.085\"; c0 \"a5=10, Bc6=7, Rac8=7, Rfc8=6\"; cem 0.55; cee 1.42; ce 0.60;");
    pos.add(
        "r4rk1/4bppp/b3pn2/1p1q2B1/3p4/3B1N2/PP2QPPP/R2R2K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.086\"; c0 \"a4=10, Bf4=4, Bxf6=1, a3=2\"; cem -0.53; cee -0.66; ce -0.44;");
    pos.add(
        "r4rkb/p2bp2p/2nq2pP/1p6/2pP4/2P1B1N1/P1B2PP1/2RQR1K1 w - - bm a4; id \"STS(v9.0) Advancement of a/b/c pawns.087\"; c0 \"a4=10, Be4=6, Qe2=5, Rb1=6\"; cem 1.39; cee 0.05; ce 1.46;");
    pos.add(
        "r5k1/1pp1r1pp/3pbpq1/pB2n3/P1P5/1P2BP2/2PQ2PP/2KRR3 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.088\"; c0 \"c5=10, Bf4=3, Bg1=5, f4=4\"; cem 0.01; cee 0.82; ce 0.24;");
    pos.add(
        "r5k1/1rpn1pbp/p2pp3/1p4Np/2PP4/1PN1P1P1/P4PK1/2RR4 w - - bm c5; id \"STS(v9.0) Advancement of a/b/c pawns.089\"; c0 \"c5=10, Nb1=3, Ne2=1, d5=3\"; cem -0.55; cee -0.40; ce -0.36;");
    pos.add(
        "r5k1/2p1qp2/1p1p2bp/p1nPr1p1/2P5/PP2P1N1/1Q1B2PP/3R1RK1 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.090\"; c0 \"b5=10, Rb8=3, Re8=6, Rf8=3\"; cem -0.07; cee -0.71; ce -0.25;");
    pos.add(
        "r5k1/5q1p/2ppNr1b/2pPp1pP/p1P3P1/P7/1PQ1RPK1/1R6 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.091\"; c0 \"b4=10, Rd1=2, Rd2=2, b3=4\"; cem 1.49; cee 1.52; ce 1.59;");
    pos.add(
        "rr2q1k1/3bnpb1/ppp3pp/3p4/1P1P4/PQ1BP1NP/1NR2PP1/3R2K1 b - - bm a5; id \"STS(v9.0) Advancement of a/b/c pawns.092\"; c0 \"a5=10, Bf8=3, Ra7=4, h5=2\"; cem 0.21; cee 0.02; ce 0.11;");
    pos.add(
        "1r1rb1k1/pp3pbp/4p1p1/1P2P3/2PBQ3/q7/P3B1PP/2R2R1K b - - bm a6; id \"STS(v9.0) Advancement of a/b/c pawns.093\"; c0 \"a6=10, b6=3, h5=2, Rd7=4\"; cem 0.75; cee 1.21; ce 0.72;");
    pos.add(
        "2r1k2r/1n2ppb1/p2p2p1/1p1P3p/8/1P2BP1P/P1P1BP2/2KR2R1 w k - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.094\"; c0 \"b4=10, Bd4=2, Kb1=2\"; cem -0.38; cee -0.51; ce -0.37;");
    pos.add(
        "2r2rk1/1pqn3p/p2p1npb/N2Pp3/2P2p2/Q4PP1/PP2BBKP/3RR3 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.095\"; c0 \"b4=10\"; cem -0.18; cee 0.18; ce -0.08;");
    pos.add(
        "2r3k1/p1q1bpp1/2p2n2/2Np3r/1P1B3p/P1Q4P/5PP1/2R1R1K1 w - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.096\"; c0 \"b5=10, Qb2=3, Qd3=2, Qe3=3\"; cem 1.58; cee 0.61; ce 1.51;");
    pos.add(
        "3r2k1/4rppp/2ppbn2/Nq2p3/4P3/1P2Q1PP/P1PR1PBK/4R3 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.097\"; c0 \"b4=10, Nc4=1, Nxc6=4, Qc3=4\"; cem 0.45; cee 1.44; ce 0.70;");
    pos.add(
        "r6k/p3Npb1/qp4pp/4p3/1B6/1PP3P1/P2R1P1P/3R2K1 b - - bm b5; id \"STS(v9.0) Advancement of a/b/c pawns.098\"; c0 \"b5=10, Kh7=6, Qb7=7, Re8=7\"; cem -1.29; cee -1.02; ce -1.23;");
    pos.add(
        "r6r/4p1k1/3q1p2/p1pPn1p1/1p2P2p/4Q3/PP2BPPP/1R2R1K1 b - - bm c4; id \"STS(v9.0) Advancement of a/b/c pawns.099\"; c0 \"c4=10, Rac8=5, Rhc8=2\"; cem -0.08; cee -1.63; ce -0.65;");
    pos.add(
        "rq4kb/5p1p/3p1Pp1/p2Pp1P1/3pP1bP/1P6/P1Q2R2/4BBK1 w - - bm b4; id \"STS(v9.0) Advancement of a/b/c pawns.100\"; c0 \"b4=10, Qc1=3, Qc4=3, Qc6=4\"; cem -0.06; cee 0.82; ce 0.38;");
    pos.add(
        "1b1qrr2/1p4pk/1np4p/p3Np1B/Pn1P4/R1N3B1/1Pb2PPP/2Q1R1K1 b - - bm Bxe5; id \"STS(v10.0) Simplification.001\"; c0 \"Bxe5=10, f4=3, Nc4=2\"; cem 1.01; cee -0.04; ce 0.91;");
    pos.add(
        "1k1r2r1/1b4p1/p4n1p/1pq1pPn1/2p1P3/P1N2N2/1PB1Q1PP/3R1R1K b - - bm Nxf3; id \"STS(v10.0) Simplification.002\"; c0 \"Nxf3=10, Rge8=7, Rgf8=6, Rh8=7\"; cem 0.97; cee 0.56; ce 0.86;");
    pos.add(
        "1k1r3r/pb1q2p1/B4p2/2p4p/Pp1bPPn1/7P/1P2Q1P1/R1BN1R1K b - - bm Bxa6; id \"STS(v10.0) Simplification.003\"; c0 \"Bxa6=10, Qc6=3, Qe6=3, Rde8=5\"; cem -1.24; cee -1.52; ce -1.34;");
    pos.add(
        "1k1r4/1br2p2/3p1p2/pp2pPb1/2q1P2p/P1PQNB1P/1P4P1/1K1RR3 b - - bm Qxd3+; id \"STS(v10.0) Simplification.004\"; c0 \"Qxd3+=10, Qa4=6, Qb3=5, Qc5=6\"; cem -0.09; cee 0.73; ce -0.06;");
    pos.add(
        "1k1r4/1br2p2/3p1p2/pp2pPb1/4P2p/P1PRNB1P/1P4P1/1K2R3 b - - bm Bxe3; id \"STS(v10.0) Simplification.005\"; c0 \"Bxe3=10, b4=7, Ka7=6, Rc5=6\"; cem 0.21; cee 0.32; ce 0.18;");
    pos.add(
        "1k1r4/5qp1/p1b2n1p/1p2p3/2p1P2P/P1N1P1P1/1PB1Q3/3R2K1 b - - bm Rxd1+; id \"STS(v10.0) Simplification.006\"; c0 \"Rxd1+=10, Qc7=4, Qe7=4, Qf8=4\"; cem -0.25; cee -0.79; ce -0.56;");
    pos.add(
        "1k1rr3/p1p4p/Bpnb4/3p1qp1/N2P4/P2Q1PPb/1PP3NP/2KR2R1 w - - bm Qxf5; id \"STS(v10.0) Simplification.007\"; c0 \"Qxf5=10, Bb5=3, Nc3=5, Qd2=1\"; cem 0.31; cee 0.41; ce 0.40;");
    pos.add(
        "1kr5/3n2p1/q2rpn1p/p7/Ppp2P2/5BP1/1PQ2B1P/2RR2K1 w - - bm Rxd6; id \"STS(v10.0) Simplification.008\"; c0 \"Rxd6=10, Kh1=4, Qe2=7, Rd4=4\"; cem 0.04; cee -0.77; ce 0.00;");
    pos.add(
        "1nr2qk1/pb1r1p1p/1b4pP/1p1Bp1B1/4P1Q1/2NR4/P1R2PP1/6K1 w - - bm Bxb7; id \"STS(v10.0) Simplification.009\"; c0 \"Bxb7=10, Rdd2=1\"; cem 1.30; cee 0.00; ce 1.38;");
    pos.add(
        "1qr3k1/p3bppp/4pn2/1B2n3/N7/P3P2P/1P2QPP1/2R1B2K w - - bm Rxc8+; id \"STS(v10.0) Simplification.010\"; c0 \"Rxc8+=10, Nc3=6, Rc3=6, Rd1=6\"; cem 1.00; cee 1.09; ce 1.12;");
    pos.add(
        "1r1q1n2/2p2ppk/p2p3p/P1b1p3/2P1P3/3P1N1P/1R1B1PP1/1Q4K1 b - - bm Rxb2; id \"STS(v10.0) Simplification.011\"; c0 \"Rxb2=10, Nd7=3, Ra8=2, Rc8=2\"; cem -0.04; cee -0.44; ce -0.29;");
    pos.add(
        "1r1qr1k1/2Q1bp1p/2n3p1/2PN4/4B3/2N3P1/5P1P/5RK1 b - - bm Qxc7; id \"STS(v10.0) Simplification.012\"; c0 \"Qxc7=10, Nb4=2, Rc8=1\"; cem -0.67; cee -0.70; ce -0.73;");
    pos.add(
        "1r1r2k1/6pp/1b2p2q/3b1p2/1BB1n3/P3PN1P/2Q2PP1/2R2RK1 w - - bm Bxd5; id \"STS(v10.0) Simplification.013\"; c0 \"Bxd5=10, Nd4=3, Qb3=3, Rfe1=2\"; cem 0.27; cee 0.36; ce 0.37;");
    pos.add(
        "1r1r3k/1qpn1pbp/p1n3pB/P2bp3/BpN3Q1/3P3P/1PPN1PP1/R3R1K1 w - - bm Bxg7+; id \"STS(v10.0) Simplification.014\"; c0 \"Bxg7+=10, Qg5=1, Qh4=4\"; cem 0.01; cee -0.16; ce 0.11;");
    pos.add(
        "1r2k2r/pp1npp2/2n3p1/7p/3bP3/P3BB1P/1P3PP1/1R1R1NK1 b k - bm Bxe3; id \"STS(v10.0) Simplification.015\"; c0 \"Bxe3=10, Bb6=6, Bc5=6, e5=6\"; cem 2.23; cee 0.30; ce 1.23;");
    pos.add(
        "1r3r2/3q2kp/p2p1pp1/2pPp3/4Pn2/P2P1N1P/2Q2PP1/1R2R1K1 w - - bm Rxb8; id \"STS(v10.0) Simplification.016\"; c0 \"Rxb8=10, Kh1=5, Kh2=5, Nd2=5\"; cem 0.23; cee -0.44; ce 0.11;");
    pos.add(
        "1r3rk1/pp3pp1/1np4p/2Nn2bq/3P3N/P2Q2PP/1P1BRP2/4R1K1 w - - bm Bxg5; id \"STS(v10.0) Simplification.017\"; c0 \"Bxg5=10, Qf3=2, Re4=2, Re5=2\"; cem 1.00; cee 1.25; ce 1.10;");
    pos.add(
        "1r4k1/1q2r2p/1n4pQ/2pp1pN1/7P/P5P1/1P3P2/3RR1K1 w - - bm Rxe7; id \"STS(v10.0) Simplification.018\"; c0 \"Rxe7=10, h5=4, Nf3=4, Nxh7=3\"; cem 0.52; cee 0.00; ce 0.45;");
    pos.add(
        "1r4k1/5ppp/1p3n2/5n2/NP1r4/1B3P2/1P4PP/3RR2K w - - bm Rxd4; id \"STS(v10.0) Simplification.019\"; c0 \"Rxd4=10, b5=1, Bc2=2, Nc3=2\"; cem 0.68; cee 0.60; ce 0.73;");
    pos.add(
        "1r4k1/p1rn1ppp/2p1p1b1/N1PpP3/3P1P2/1R4P1/P6P/R4BK1 w - - bm Rxb8+; id \"STS(v10.0) Simplification.020\"; c0 \"Rxb8+=10, Ba6=3, Rc1=3, Re1=3\"; cem -0.13; cee 1.00; ce 0.65;");
    pos.add(
        "1r4r1/4kp1p/3p1p1b/2q1pP2/Bn2P3/2N5/1PPNQ1PP/1K1R4 b - - bm Bxd2; id \"STS(v10.0) Simplification.021\"; c0 \"Bxd2=10, Rgd8=1\"; cem 0.45; cee -1.78; ce -0.04;");
    pos.add(
        "1r4rk/2q1b2p/pN6/P1p1Pp2/3n1p2/5N2/5QPP/R4RK1 b - - bm Nxf3+; id \"STS(v10.0) Simplification.022\"; c0 \"Nxf3+=10, Qb7=5, Rbd8=7, Rgd8=5\"; cem -1.36; cee -1.03; ce -1.39;");
    pos.add(
        "1r6/1R2bk2/P3p1pp/4Pp2/1r1P3P/1N2B1P1/5PK1/8 w - - bm Rxb4; id \"STS(v10.0) Simplification.023\"; c0 \"Rxb4=10, Rxb8=6\"; cem 3.55; cee 2.53; ce 2.84;");
    pos.add(
        "1r6/3n4/p2b3p/2nN1kpP/1BP1p3/3rP3/3NKP2/1R1R4 w - - bm Bxc5; id \"STS(v10.0) Simplification.024\"; c0 \"Bxc5=10, Bc3=3, Nxe4=2, Rf1=2\"; cem 1.65; cee 0.39; ce 1.15;");
    pos.add(
        "1r6/8/p2b3p/2nN1kpP/2P1p3/3rP3/3NKP2/1R1R4 w - - bm Rxb8; id \"STS(v10.0) Simplification.025\"; c0 \"Rxb8=10, Ne7+=6\"; cem 1.26; cee 0.47; ce 0.87;");
    pos.add(
        "1rb2nk1/8/1p1PNbrp/3Pp1pq/p3Pn1P/P2N1PB1/4B3/R2Q1R1K w - - bm Nxf8; id \"STS(v10.0) Simplification.026\"; c0 \"Nxf8=10, Nc7=4, Rc1=1\"; cem 3.45; cee 2.86; ce 3.54;");
    pos.add(
        "2br1rk1/2q3p1/1pp1p2p/p1n1b3/2BNN1Q1/4P3/PP3PP1/2RR3K w - - bm Nxc5; id \"STS(v10.0) Simplification.027\"; c0 \"Nxc5=10, Kg1=2, Nf3=2, Nxe6=3\"; cem -0.75; cee -0.32; ce -0.63;");
    pos.add(
        "2br2k1/r5p1/4p3/5p2/8/pP3B2/1q2RPPP/Q3R1K1 b - - bm Qxa1; id \"STS(v10.0) Simplification.028\"; c0 \"Qxa1=10, Qd4=1, Qxb3=1, Rd2=3\"; cem -0.32; cee -1.64; ce -0.82;");
    pos.add(
        "2brr1k1/2q1bpp1/pNnpp2p/P1p5/1P2PP2/2P1B1P1/4Q1BP/R2R2K1 w - - bm Nxc8; id \"STS(v10.0) Simplification.029\"; c0 \"Nxc8=10, b5=3, bxc5=4, Rab1=3\"; cem 1.80; cee 2.45; ce 1.91;");
    pos.add(
        "2r1n1k1/5ppp/1prp4/p3p1q1/2b1P3/P1B1QB1P/1PPR1PPK/R7 b - - bm Qxe3; id \"STS(v10.0) Simplification.030\"; c0 \"Qxe3=10, h6=2, Qe7=2, Qh4=2\"; cem 0.65; cee -0.19; ce 0.42;");
    pos.add(
        "2r1r1k1/1p1q1bbp/pn3pp1/n2p4/3P1B1P/2NB1N2/PPQ1RPP1/4R1K1 w - - bm Rxe8+; id \"STS(v10.0) Simplification.031\"; c0 \"Rxe8+=10, h5=8, Qb1=5\"; cem 1.34; cee 0.88; ce 1.44;");
    pos.add(
        "2r1r1k1/2qbppbp/pp1p2p1/n2P4/P1P4P/1P3N2/1BQ1BPP1/R2R2K1 w - - bm Bxg7; id \"STS(v10.0) Simplification.032\"; c0 \"Bxg7=10, Bd4=3, h5=3, Ng5=2\"; cem 1.09; cee 0.77; ce 1.18;");
    pos.add(
        "2r2nk1/pp3p2/5qpb/4p1Np/P3P2P/1P2B1P1/2r2P2/2RRQ1K1 w - - bm Rxc2; id \"STS(v10.0) Simplification.033\"; c0 \"Rxc2=10, Kg2=2\"; cem 0.36; cee -0.05; ce 0.39;");
    pos.add(
        "2r2r1k/1p1q2b1/pQn1b1pp/4pp2/B3P3/1P3N1P/P1RB1PP1/R5K1 w - - bm Bxc6; id \"STS(v10.0) Simplification.034\"; c0 \"Bxc6=10, Bb4=4, Be3=2, Rac1=1\"; cem -0.22; cee -0.53; ce -0.13;");
    pos.add(
        "2r3k1/4qpp1/4p2p/1P1bP3/4pPP1/4Q3/4B2P/2R3K1 b - - bm Rxc1+; id \"STS(v10.0) Simplification.035\"; c0 \"Rxc1+=10\"; cem 0.50; cee 1.32; ce 0.84;");
    pos.add(
        "2r3k1/pp3pp1/4q2p/4p3/bB2Pn2/P4P2/1P1Q2PP/2R2BK1 w - - bm Rxc8+; id \"STS(v10.0) Simplification.036\"; c0 \"Rxc8+=10, Bc5=4, Qe3=2, Rc3=4\"; cem -1.25; cee 0.00; ce -0.67;");
    pos.add(
        "2r5/4q1kp/1nB2pp1/p3p3/Pb2Q3/4P3/1B1r1PPP/1R1R2K1 b - - bm Rxd1+; id \"STS(v10.0) Simplification.037\"; c0 \"Rxd1+=10, Rcd8=2\"; cem -0.26; cee -1.85; ce -0.60;");
    pos.add(
        "2rk1br1/4np1p/p2pb3/1p1Npp2/P3P3/2PB2Pq/1PN2P1P/R2Q1R1K w - - bm Nxe7; id \"STS(v10.0) Simplification.038\"; c0 \"Nxe7=10, Nb6=4, Ncb4=6, Ndb4=3\"; cem 0.96; cee 0.71; ce 1.05;");
    pos.add(
        "2rnq2k/pb3pp1/1p5p/3B4/1P3Q2/P4N1P/2R2PP1/6K1 w - - bm Rxc8; id \"STS(v10.0) Simplification.039\"; c0 \"Rxc8=10, Bb3=3, Rc4=5, Rd2=1\"; cem 0.57; cee 0.67; ce 0.70;");
    pos.add(
        "2rq1r1k/3n2pp/1p2p3/1P1b2bn/p1BP4/P4NP1/1B1NQP2/2R1R1K1 w - - bm Bxd5; id \"STS(v10.0) Simplification.040\"; c0 \"Bxd5=10, Bd3=1, Nxg5=2, Rc3=2\"; cem -0.69; cee -0.08; ce -0.59;");
    pos.add(
        "2rq1rk1/1p2bpp1/4p3/pP1pP1np/P2P4/R3BP2/3Q2PP/1NR3K1 w - - bm Rxc8; id \"STS(v10.0) Simplification.041\"; c0 \"Rxc8=10, Ra2=1, Rac3=3, Rb3=1\"; cem -0.14; cee -0.03; ce -0.02;");
    pos.add(
        "2rr1bk1/3qnppp/pp6/3p4/2PP2Q1/PP5P/1B1N2P1/2R2R1K b - - bm Qxg4; id \"STS(v10.0) Simplification.042\"; c0 \"Qxg4=10, dxc4=2, f6=1, g6=2\"; cem 0.72; cee 0.36; ce 0.56;");
    pos.add(
        "2rr2k1/p4pp1/1p2pn1p/3b1P2/1qNP4/1P1B4/P2Q2PP/2RR2K1 b - - bm Qxd2; id \"STS(v10.0) Simplification.043\"; c0 \"Qxd2=10, a5=3, Qe7=2, Qf8=2\"; cem -0.68; cee -0.37; ce -0.72;");
    pos.add(
        "3b4/2q2pkp/p2p1np1/1b1Pp1B1/1pN1P3/1P5P/P1Q2PP1/5NK1 b - - bm Bxc4; id \"STS(v10.0) Simplification.044\"; c0 \"Bxc4=10, a5=1, Be7=1, h6=2\"; cem 1.21; cee 0.50; ce 0.77;");
    pos.add(
        "3n1r2/2r4p/p2bp2k/1p1p2pP/1P1P4/P1R1B1Pq/4QP2/1B2R1K1 w - - bm Rxc7; id \"STS(v10.0) Simplification.045\"; c0 \"Rxc7=10, Rec1=2\"; cem 0.94; cee -0.46; ce 0.81;");
    pos.add(
        "3q4/pp2b1k1/2r5/4p2p/P1RpN3/1P3PP1/1Q2P1K1/8 w - - bm Rxc6; id \"STS(v10.0) Simplification.046\"; c0 \"Rxc6=10, Nd2=3, Qb1=2, Qc2=4\"; cem 0.24; cee -0.11; ce 0.15;");
    pos.add(
        "3r1k2/p4ppp/1r2p3/n1p1P3/5P1P/P1PBK1P1/8/1R3R2 w - - bm Rxb6; id \"STS(v10.0) Simplification.047\"; c0 \"Rxb6=10, a4=5, h5=8, Rfd1=4\"; cem -1.38; cee 1.31; ce 0.73;");
    pos.add(
        "3r1q1k/Q2R1bpp/3b1r2/2p2p2/2N2P2/1Pp3P1/5PBP/4R1K1 w - - bm Rxd8; id \"STS(v10.0) Simplification.048\"; c0 \"Rxd8=10, h4=5, Nxd6=5, Rc1=6\"; cem 0.52; cee 0.48; ce 0.61;");
    pos.add(
        "3r1rk1/4b1p1/p3p3/4pbPp/2R4P/1PN5/PB3P2/3R2K1 w - - bm Rxd8; id \"STS(v10.0) Simplification.049\"; c0 \"Rxd8=10, Rc7=2\"; cem -0.22; cee -0.12; ce -0.06;");
    pos.add(
        "3r2k1/1b1rnp1p/p2qpbp1/1pR5/1P1PB2P/P3BN2/4QPP1/3R2K1 w - - bm Bxb7; id \"STS(v10.0) Simplification.050\"; c0 \"Bxb7=10, Bg5=7\"; cem 0.38; cee 0.09; ce 0.47;");
    pos.add(
        "3r4/5pk1/4rqp1/p2p4/Pb1Q3p/1Pp1P2P/2R1BPP1/3R2K1 b - - bm Qxd4; id \"STS(v10.0) Simplification.051\"; c0 \"Qxd4=10, g5=6, Re4=5, Red6=5\"; cem 0.39; cee -0.28; ce 0.11;");
    pos.add(
        "3rkb2/1p4rp/p4p2/2p2B2/2N1Pp2/3R2P1/PPP2P1P/6K1 b - - bm Rxd3; id \"STS(v10.0) Simplification.052\"; c0 \"Rxd3=10, Be7=2, fxg3=2, Rd4=3\"; cem 2.32; cee 0.19; ce 0.52;");
    pos.add(
        "3rr1k1/6p1/pb1p3p/1p1RnBqP/4PQN1/2P5/PP6/1K3R2 w - - bm Qxg5; id \"STS(v10.0) Simplification.053\"; c0 \"Qxg5=10, a3=3, Kc2=3, Nxe5=4\"; cem 0.47; cee 0.24; ce 0.51;");
    pos.add(
        "4qbk1/r1p1nbpp/1p3p2/1Q6/P2P4/2BN1BP1/5P1P/2R3K1 w - - bm Qxe8; id \"STS(v10.0) Simplification.054\"; c0 \"Qxe8=10, Bb2=5, h3=5, Nf4=5, Nf4=6\"; cem 0.38; cee 0.11; ce 0.41;");
    pos.add(
        "4r1k1/1p2qp1p/2p1n1pB/p3p3/P3P1B1/2N2Q1P/2P2PP1/6K1 w - - bm Bxe6; id \"STS(v10.0) Simplification.055\"; c0 \"Bxe6=10, Qd1=2, Qd3=1, Qe3=2\"; cem 1.85; cee 0.00; ce 1.00;");
    pos.add(
        "4r1k1/1p5p/1r4p1/2pq1p2/P4P2/3P2Q1/1bP3RP/4R1BK b - - bm Rxe1; id \"STS(v10.0) Simplification.056\"; c0 \"Rxe1=10, Ra8=2, Rbe6=3, Rc8=3\"; cem 0.65; cee -0.82; ce 0.10;");
    pos.add(
        "4r1k1/2r1bp2/1p4p1/3BP2p/nP5P/5K2/1B1R1P2/2R5 b - - bm Rxc1; id \"STS(v10.0) Simplification.057\"; c0 \"Rxc1=10, Ra7=1, Rcc8=3\"; cem -0.89; cee 1.15; ce 0.25;");
    pos.add(
        "4r1k1/2r2ppp/2nq1n2/1BbppP2/Q7/2P1BN1P/5PP1/RR4K1 w - - bm Bxc5; id \"STS(v10.0) Simplification.058\"; c0 \"Bxc5=10, Nd2=6, Qb3=6, Re1=6\"; cem 0.63; cee 0.30; ce 0.72;");
    pos.add(
        "4r2k/p1r1b1pp/1p1pqn2/4p3/1PP4B/P4B1P/4QPP1/2RR2K1 w - - bm Bxf6; id \"STS(v10.0) Simplification.059\"; c0 \"Bxf6=10, Qd2=6, Qe3=6, Re1=6\"; cem 2.06; cee 0.93; ce 1.98;");
    pos.add(
        "4r2k/p2n1pp1/4p2p/1q1r3P/2pR1BP1/P1P1R3/1P2QP2/2K5 b - - bm Rxd4; id \"STS(v10.0) Simplification.060\"; c0 \"Rxd4=10, Nc5=4, Rc8=2\"; cem -1.39; cee -0.35; ce -1.17;");
    pos.add(
        "4r3/1pn3k1/4p1b1/p1Pp3r/3P2NR/1P3B2/3K2P1/4R3 w - - bm Rxh5; id \"STS(v10.0) Simplification.061\"; c0 \"Rxh5=10, g3=3, Reh1=3\"; cem 0.14; cee 0.19; ce 0.26;");
    pos.add(
        "4rrk1/2pb3p/p7/1ppPq3/2P1P1pP/2N5/PP2Q2P/4RR1K w - - bm Rxf8+; id \"STS(v10.0) Simplification.062\"; c0 \"Rxf8+=10, a3=3, Kg2=4, Rf2=2\"; cem 0.09; cee 0.64; ce 0.36;");
    pos.add(
        "5B1k/p6p/1p2q1r1/2p1P1p1/3b1n2/PQ3R2/1P4P1/K2R4 w - - bm Qxe6; id \"STS(v10.0) Simplification.063\"; c0 \"Qxe6=10, g3=3, Qc2=1\"; cem 0.69; cee 0.24; ce 0.63;");
    pos.add(
        "5k2/4pp2/1N2n1pp/r3P3/P5PP/2rR1K2/P7/3R4 b - - bm Rxd3+; id \"STS(v10.0) Simplification.064\"; c0 \"Rxd3+=10, Rc6=4\"; cem -0.35; cee 0.74; ce 0.38;");
    pos.add(
        "5qk1/1p1rbrpp/pP3p2/P1pbp3/1n6/BN1P2P1/1Q2PPBP/R1R3K1 w - - bm Bxd5; id \"STS(v10.0) Simplification.065\"; c0 \"Bxd5=10, Bxb4=7, Rab1=6, Rc3=4\"; cem 0.08; cee 1.52; ce 0.19;");
    pos.add(
        "5rk1/8/3p3p/p1qP2p1/Rrb1P3/3p1P2/1P1Q1RP1/3N2K1 w - - bm Rxb4; id \"STS(v10.0) Simplification.066\"; c0 \"Rxb4=10, b3=3, Ra3=2\"; cem -1.77; cee -2.62; ce -1.93;");
    pos.add(
        "6k1/1r3pp1/4b2p/p2pP2P/1r1R1Q2/1Pq1N3/2P3P1/3R3K b - - bm Rxd4; id \"STS(v10.0) Simplification.067\"; c0 \"Rxd4=10, R7b5=5, R7b6=4, Rb8=5\"; cem -0.96; cee 0.19; ce -0.70;");
    pos.add(
        "6k1/3q2b1/p1rrnpp1/P3p3/4P3/1pBR3Q/1P4PP/1B1R3K b - - bm Rxd3; id \"STS(v10.0) Simplification.068\"; c0 \"Rxd3=10, Bf8=6, Nd4=5, Qc7=5\"; cem 0.38; cee -0.38; ce 0.16;");
    pos.add(
        "6k1/3qbppp/5n2/PQpPp3/4P3/5PP1/1P4KP/2B5 w - - bm Qxd7; id \"STS(v10.0) Simplification.069\"; c0 \"Qxd7=10\"; cem -1.67; cee -0.44; ce -0.73;");
    pos.add(
        "6k1/5p2/1q1bp1p1/1Pp1B2p/2Pn3P/6P1/1Q2BP2/6K1 w - - bm Bxd6; id \"STS(v10.0) Simplification.070\"; c0 \"Bxd6=10, Bf6=2, Bxd4=1, f4=2\"; cem 0.52; cee 0.50; ce 0.60;");
    pos.add(
        "6r1/4pp1k/3p3p/2qP1Pb1/r3P1PB/1R5K/4Q3/1R6 b - - bm Bxh4; id \"STS(v10.0) Simplification.071\"; c0 \"Bxh4=10, h5=5, Qd4=5, Rc8=2\"; cem -0.28; cee 0.00; ce -0.29;");
    pos.add(
        "7r/2r1kp2/3pp2p/p3q1p1/4B3/P5P1/1Q1B1P1P/4R1K1 w - - bm Qxe5; id \"STS(v10.0) Simplification.072\"; c0 \"Qxe5=10, Qb1=5, Qb3=3, Qb6=3\"; cem 2.49; cee -0.13; ce 1.50;");
    pos.add(
        "8/1p1bkq2/p2pp1n1/P1b1p1P1/4P1QN/1KNB4/1PP5/8 b - - bm Nxh4; id \"STS(v10.0) Simplification.073\"; c0 \"Nxh4=10, Bc6=5, Be8=4, Bf2=5\"; cem -2.08; cee -0.75; ce -1.54;");
    pos.add(
        "8/3n1nk1/q2b4/3p2p1/2pPp3/2P1P3/1Q4PN/3BB1K1 b - - bm Bxh2+; id \"STS(v10.0) Simplification.074\"; c0 \"Bxh2+=10, Bc7=4, Nf6=4, Nh6=4\"; cem -1.01; cee -1.35; ce -1.27;");
    pos.add(
        "8/p1r4p/1p1qnkpP/3Ppp2/8/P2Q2P1/BP3P2/2R3K1 w - - bm Rxc7; id \"STS(v10.0) Simplification.075\"; c0 \"Rxc7=10, Rc3=6, Rc4=6, Re1=6\"; cem 0.82; cee -0.96; ce -0.03;");
    pos.add(
        "8/r4p2/P1ppk3/2p4p/1rPb1BqP/RR1PpQP1/4P1K1/8 b - - bm Qxf3+; id \"STS(v10.0) Simplification.076\"; c0 \"Qxf3+=10, f5=2, Ra8=1, Rxb3=1\"; cem 4.01; cee 0.79; ce 2.93;");
    pos.add(
        "br2r1k1/5p1p/p7/nq4Pp/p3P3/P1PQ4/1P2R1B1/K1BR4 b - - bm Qxd3; id \"STS(v10.0) Simplification.077\"; c0 \"Qxd3=10, Nb3+=5, Rbc8=4, Rbd8=4\"; cem -0.46; cee 0.35; ce -0.43;");
    pos.add(
        "br3rk1/p2q1pp1/3p1n1p/2p1p3/2PnP3/P1BP1NP1/1R1Q1PBP/1R4K1 b - - bm Rxb2; id \"STS(v10.0) Simplification.078\"; c0 \"Rxb2=10, Nxf3+=4, Qc7=4, Rb7=5\"; cem -0.26; cee -0.31; ce -0.36;");
    pos.add(
        "q3b3/4Bpkp/6p1/3Pp3/r3r3/1Q5P/R4PP1/R5K1 w - - bm Rxa4; id \"STS(v10.0) Simplification.079\"; c0 \"Rxa4=10, Bd6=4, Kh1=5, Ra3=5\"; cem 2.31; cee 1.36; ce 2.01;");
    pos.add(
        "qr3nk1/r4p1p/2p1p1pP/p1PpPbP1/3P1P2/QR2R3/P2NB2K/8 w - - bm Rxb8; id \"STS(v10.0) Simplification.080\"; c0 \"Rxb8=10, Kg3=2, Qb2=4, Rb6=3\"; cem 1.27; cee 2.88; ce 1.63;");
    pos.add(
        "qr4k1/3b1pp1/4p2p/n2pP3/n1pP4/Q1P2NP1/3N1P1P/1R3BK1 b - - bm Rxb1; id \"STS(v10.0) Simplification.081\"; c0 \"Rxb1=10, Bc6=2, Be8=3, Rb7=2\"; cem -0.17; cee 0.57; ce -0.09;");
    pos.add(
        "R1b2b2/2kp1p1p/B2npp2/2p5/4P1r1/6P1/3N1P1P/3K3R w - - bm Bxc8; id \"STS(v10.0) Simplification.082\"; c0 \"Bxc8=10, f3=3, h3=3, Kc2=3\"; cem -0.09; cee 0.20; ce 0.19;");
    pos.add(
        "r1b2rk1/1p2bpp1/5n2/1Q2pq2/2P1N3/p3B1Pp/P2RPP1P/3R2KB w - - bm Nxf6+; id \"STS(v10.0) Simplification.083\"; c0 \"Nxf6+=10, Nc3=4, Ng5=2, Qb1=1\"; cem 0.58; cee 0.22; ce 0.66;");
    pos.add(
        "r1bq4/3n1pkp/2pRr1p1/8/nPP1PP2/6PP/2Q1N1B1/1R4K1 w - - bm Rxe6; id \"STS(v10.0) Simplification.084\"; c0 \"Rxe6=10, Rdd1=3\"; cem -0.87; cee -0.61; ce -0.75;");
    pos.add(
        "r1r1bnk1/5pp1/p2pp3/2q1n1P1/3QP2P/1PN4B/2P1NR1K/3R4 b - - bm Qxd4; id \"STS(v10.0) Simplification.085\"; c0 \"Qxd4=10, a5=5, Bb5=4, Rab8=3\"; cem -0.32; cee 0.40; ce -0.40;");
    pos.add(
        "r2qr1k1/2p2pp1/3p1n2/3P1n1p/1Q3P2/1B5P/PP4PB/2R1R2K w - - bm Rxe8+; id \"STS(v10.0) Simplification.086\"; c0 \"Rxe8+=10, Qc4=5, Ra1=4, Rb1=4\"; cem 0.12; cee 0.09; ce 0.21;");
    pos.add(
        "r2r2k1/1b1nqpp1/pp5p/2pNP3/P3Q3/5N2/1P3PPP/2RR2K1 b - - bm Bxd5; id \"STS(v10.0) Simplification.087\"; c0 \"Bxd5=10, Kh8=6, Qe6=6, Qe8=6\"; cem 2.30; cee 1.43; ce 2.05;");
    pos.add(
        "r2r2k1/4qp2/p5p1/2p4p/5P2/3RP1K1/PPQ4P/3R4 b - - bm Rxd3; id \"STS(v10.0) Simplification.088\"; c0 \"Rxd3=10, h4+=2, Rdb8=2, Re8=4\"; cem -0.63; cee 0.79; ce -0.10;");
    pos.add(
        "r3r1k1/2qn3p/1p2ppp1/3b4/p1PP1P2/4QN2/R5PP/1R3BK1 b - - bm Bxf3; id \"STS(v10.0) Simplification.089\"; c0 \"Bxf3=10, Bb7=4, Bc6=5, Bxc4=3\"; cem -0.25; cee -1.57; ce -0.56;");
    pos.add(
        "r3r1k1/5pbp/4bpp1/pp2q3/4P1PP/P1NR2Q1/1PP1B3/2KR4 b - - bm Qxg3; id \"STS(v10.0) Simplification.090\"; c0 \"Qxg3=10, Qb8=1, Qc5=2\"; cem -0.19; cee 0.82; ce -0.12;");
    pos.add(
        "r3r1k1/6p1/p2p1qp1/1p1P3p/4P1bP/P2Q3B/1PP5/1K2R2R b - - bm Bxh3; id \"STS(v10.0) Simplification.091\"; c0 \"Bxh3=10, Bc8=5, Qxh4=5, Rf8=5\"; cem 0.36; cee 0.29; ce 0.24;");
    pos.add(
        "r3r3/p5bk/2pq1ppp/1p1b4/3P4/P2N1N2/1P3PPP/2RQR1K1 b - - bm Rxe1+; id \"STS(v10.0) Simplification.092\"; c0 \"Rxe1+=10, a5=6, h5=7, Rac8=5\"; cem -0.52; cee -0.68; ce -0.64;");
    pos.add(
        "r3rbk1/p1qn1p1p/2p1n1p1/Pp2p1B1/1P2P3/2N1Q3/2PN1PPP/1R2R1K1 b - - bm Nxg5; id \"STS(v10.0) Simplification.093\"; c0 \"Nxg5=10, a6=3, Nb8=3, Rac8=4\"; cem -0.62; cee -0.38; ce -0.71;");
    pos.add(
        "r4r1k/1bq1bppp/p2p4/1p1Nn3/4PR2/PN1B3Q/1PP3PP/R5K1 b - - bm Bxd5; id \"STS(v10.0) Simplification.094\"; c0 \"Bxd5=10, Bc8=5, Qc8=5, Qd8=1\"; cem 0.00; cee 0.10; ce -0.09;");
    pos.add(
        "r4rk1/1p2bppp/p3bn2/8/Pq1BP3/1BN1Q3/1PP3PP/R3K2R w KQ - bm Bxe6; id \"STS(v10.0) Simplification.095\"; c0 \"Bxe6=10, e5=2, O-O-O=2, Rd1=1\"; cem 0.35; cee 1.00; ce 0.46;");
    pos.add(
        "r4rk1/pp1qppbp/2n3p1/3b4/3PB2B/2P5/3N1PPP/1R1QR1K1 b - - bm Bxe4; id \"STS(v10.0) Simplification.096\"; c0 \"Bxe4=10, e6=5, Rac8=3, Rfe8=4\"; cem -0.58; cee -0.75; ce -0.67;");
    pos.add(
        "r4rk1/pp5p/2pp2pb/2n1nq2/2P2P2/2N1B3/PP2B1PP/2RQR1K1 w - - bm Bxc5; id \"STS(v10.0) Simplification.097\"; c0 \"Bxc5=10, g4=6, Kh1=6, Rf1=6\"; cem 2.02; cee 0.23; ce 2.08;");
    pos.add(
        "r4rk1/pppb3p/8/2pPqp2/2P1P1nP/1QN4B/PP5P/4RRK1 w - - bm Bxg4; id \"STS(v10.0) Simplification.098\"; c0 \"Bxg4=10, Qc2=3, Rf4=5\"; cem 0.46; cee 1.35; ce 0.70;");
    pos.add(
        "r5k1/5p2/7p/5qP1/1P1QR3/5P2/r4RK1/8 b - - bm Rxf2+; id \"STS(v10.0) Simplification.099\"; c0 \"Rxf2+=10, h5=6, hxg5=6, R2a6=6\"; cem -0.43; cee 1.49; ce 0.30;");
    pos.add(
        "rr4k1/p2nBpp1/q1p1pn1p/7P/2pPN3/P1P5/1PQ2PP1/2KR3R b - - bm Nxe4; id \"STS(v10.0) Simplification.100\"; c0 \"Nxe4=10, Rb3=4, Rb5=4\"; cem -0.97; cee 0.33; ce -0.84;");
    pos.add(
        "1k2r3/1p1bP3/2p2p1Q/Ppb5/4Rp1P/2q2N1P/5PB1/6K1 b - - bm Kc7; id \"STS(v11.0) King Activity.001\"; c0 \"Kc7=10, Kc8=4, Qa1+=4\"; cem -3.61; cee 0.42; ce -2.13;");
    pos.add(
        "1k2r3/p7/Pp1pP1p1/4p2p/2r4P/5P1B/4RB1K/8 w - - bm Kg3; id \"STS(v11.0) King Activity.002\"; c0 \"Kg3=10, Kg2=5, Re1=5, Re3=3\"; cem 1.25; cee 0.54; ce 0.73;");
    pos.add(
        "1k5r/6p1/p2b4/7p/2r1p2P/R1B1P3/6P1/2R3K1 b - - bm Ka7; id \"STS(v11.0) King Activity.003\"; c0 \"Ka7=10, Bxa3=3, Kb7=4, Rc6=2\"; cem 0.26; cee -1.17; ce -0.91;");
    pos.add(
        "1n5k/3r2p1/2p1qp1p/3p1N1P/1P1P1rP1/p1R5/P7/1KRQ4 w - - bm Ka1; id \"STS(v11.0) King Activity.004\"; c0 \"Ka1=10, Re3=2, Rg3=3\"; cem -0.43; cee 0.50; ce -0.04;");
    pos.add(
        "1r2r3/1p1b3k/2p2n2/p1Pp4/P2N1PpP/1R2p3/1P2P1BP/3R2K1 b - - bm Kg6; id \"STS(v11.0) King Activity.005\"; c0 \"Kg6=10, Kh6=8, Ne4=8, Nh5=8\"; cem 0.71; cee 1.42; ce 1.04;");
    pos.add(
        "1r2rqk1/8/bn1p3p/B1p2p2/p1PnpP2/P3R2Q/1P3NPP/2R2BK1 b - - bm Kh7; id \"STS(v11.0) King Activity.006\"; c0 \"Kh7=10, Re6=3\"; cem 3.64; cee 0.11; ce 3.46;");
    pos.add(
        "1R3bk1/7p/6p1/8/1pN3P1/8/r4P1P/6K1 b - - bm Kf7; id \"STS(v11.0) King Activity.007\"; c0 \"Kf7=10, Kg7=1\"; cem 0.74; cee 0.00; ce -0.08;");
    pos.add(
        "1r3k2/2N2pp1/1pR2n1p/4p3/8/1P1K1P2/P5PP/8 w - - bm Kc4; id \"STS(v11.0) King Activity.008\"; c0 \"Kc4=10, a4=6, Ke3=2, Nb5=2\"; cem -1.30; cee 0.59; ce 0.65;");
    pos.add(
        "1r3k2/3n1p2/6pN/2p1PP2/p2q4/Pr1p2P1/1P1R1R1P/3Q2K1 b - - bm Ke7; id \"STS(v11.0) King Activity.009\"; c0 \"Ke7=10, Ke8=2, Kg7=2\"; cem 0.97; cee -2.20; ce -0.12;");
    pos.add(
        "2b4r/1p1k4/1pnbppp1/r2p3p/2pP1P1P/2P2NPB/PPN2P2/R3R1K1 b - - bm Ke7; id \"STS(v11.0) King Activity.010\"; c0 \"Ke7=10, Nd8=3, Re8=2\"; cem 2.48; cee 0.73; ce 1.56;");
    pos.add(
        "2k2r1r/1b4p1/1nq1p3/1p5p/5P2/1Pp1B1KP/2B1QRP1/R7 w - - bm Kh2; id \"STS(v11.0) King Activity.011\"; c0 \"Kh2=10, Bd3=7\"; cem -0.75; cee -1.02; ce -0.70;");
    pos.add(
        "2k3r1/1b2bp2/2p2n2/pp2p1Bp/2p1P2P/P2n1B2/1P1RN1P1/5K1R b - - bm Kc7; id \"STS(v11.0) King Activity.012\"; c0 \"Kc7=10, a4=3, Ba6=1, c5=2, Kb8=3\"; cem -1.28; cee -0.91; ce -1.19;");
    pos.add(
        "2kr1b1r/1bq2pp1/p3pn2/7p/1ppPN2P/4PQ2/PPBB1PP1/R1R3K1 b - - bm Kb8; id \"STS(v11.0) King Activity.013\"; c0 \"Kb8=10, a5=2, Bd5=2, e5=2, Rh6=3\"; cem 0.10; cee -0.90; ce -0.01;");
    pos.add(
        "2kr3r/p3p3/1pn2pp1/1R5p/4P2P/2P1BPP1/P3K3/7R b - - bm Kb7; id \"STS(v11.0) King Activity.014\"; c0 \"Kb7=10, Rd6=7, Rd7=7\"; cem -0.50; cee 0.37; ce 0.05;");
    pos.add(
        "2q2r2/3bbpkp/r2p4/p1pPp1P1/PpP1P2P/1P3QK1/4RN2/2B3R1 w - - bm Kh2; id \"STS(v11.0) King Activity.015\"; c0 \"Kh2=10, Ng4=6, Qh5=5\"; cem 0.60; cee 1.80; ce 0.88;");
    pos.add(
        "2r1b3/p3kpp1/7p/3P4/7P/2p1KPP1/P7/1BR5 w - - bm Kd4; id \"STS(v11.0) King Activity.016\"; c0 \"Kd4=10, Bc2=7, Bf5=7\"; cem -1.09; cee -0.46; ce -0.38;");
    pos.add(
        "2r1k2r/1q1bbp2/p4p2/1p2pP1p/8/1N1B2Q1/PPP3PP/1K1RR3 b k - bm Kf8; id \"STS(v11.0) King Activity.017\"; c0 \"Kf8=10, Kd8=6, Rf8=6\"; cem 1.35; cee -0.65; ce 0.94;");
    pos.add(
        "2r1r3/1k1b1p2/1p4p1/p3n3/2PRP2p/PP1NK2P/2R3B1/8 b - - bm Kc7; id \"STS(v11.0) King Activity.018\"; c0 \"Kc7=10, Bc6=9, Kc6=9, Rc7=9\"; cem -1.43; cee -0.62; ce -1.03;");
    pos.add(
        "2r2bk1/1Q3ppp/p6r/P2BP2q/5Pb1/1P1RR1P1/3B4/6K1 w - - bm Kf1; id \"STS(v11.0) King Activity.019\"; c0 \"Kf1=10, Bc3=8, Bg2=8, Bxf7+=8\"; cem -3.88; cee 1.35; ce -2.96;");
    pos.add(
        "2r2k2/4pp2/pp6/2pPn3/4PN1p/1P5P/P4P2/2R2K2 w - - bm Ke2; id \"STS(v11.0) King Activity.020\"; c0 \"Ke2=10, Ng2=3, Rc3=3\"; cem 0.21; cee 0.00; ce 0.10;");
    pos.add(
        "2r2k2/p4pp1/b3n2p/8/1pp1P2P/4NPP1/PPB3K1/3R4 w - - bm Kf2; id \"STS(v11.0) King Activity.021\"; c0 \"Kf2=10, Nd5=2, Rd2=1\"; cem 0.46; cee 0.06; ce 0.22;");
    pos.add(
        "2r3k1/1q6/4p3/p2p2bp/P2P1n2/2P1NP2/1PB4B/1R2R1K1 b - - bm Kf7; id \"STS(v11.0) King Activity.022\"; c0 \"Kf7=10, Kf8=3, Rf8=6\"; cem -2.99; cee -1.38; ce -2.39;");
    pos.add(
        "2r3k1/2r1b3/p3p2p/3n2p1/2N1KP2/P2N2PP/2R5/2R5 b - - bm Kg7; id \"STS(v11.0) King Activity.023\"; c0 \"Kg7=10, Kh7=1\"; cem -2.87; cee 0.00; ce -1.19;");
    pos.add(
        "2rb2k1/1p1q3p/1P2b1p1/1N3p2/3B4/4PP1P/1Q4P1/R5K1 w - - bm Kh2; id \"STS(v11.0) King Activity.024\"; c0 \"Kh2=10, Kh1=4, Ra7=3\"; cem 1.41; cee 0.50; ce 1.15;");
    pos.add(
        "2rk4/1Rp5/1bBp1r2/4p2p/8/6P1/P1P2P1P/5RK1 w - - bm Kg2; id \"STS(v11.0) King Activity.025\"; c0 \"Kg2=10, c4=4, h4=6\"; cem 2.07; cee 0.84; ce 1.10;");
    pos.add(
        "3b4/3k1p2/4p1p1/p1rpP1Pp/R4P2/1P3K2/P2B1P2/8 b - - bm Kc6; id \"STS(v11.0) King Activity.026\"; c0 \"Kc6=10, Bb6=4, Kc8=5\"; cem -0.24; cee -0.70; ce -0.78;");
    pos.add(
        "3b4/5p2/2k1p1p1/p1rpP1Pp/R4P2/1P6/P2BKP2/8 b - - bm Kb5; id \"STS(v11.0) King Activity.027\"; c0 \"Kb5=10, Bb6=6, Kb7=6\"; cem 0.32; cee -0.63; ce -0.71;");
    pos.add(
        "3k1b1r/pp3p1p/5P2/3Bn3/8/2N5/PP5P/2K4R b - - bm Kc7; id \"STS(v11.0) King Activity.028\"; c0 \"Kc7=10, b6=4, Kc8=4\"; cem 1.00; cee 0.45; ce 0.35;");
    pos.add(
        "3q1rk1/5p2/1pQ1p1p1/p2p4/P2P2P1/1P2P3/6RP/6K1 b - - bm Kg7; id \"STS(v11.0) King Activity.029\"; c0 \"Kg7=10, Kh7=3, Qe7=4, Qf6=4, Qh4=4\"; cem -0.90; cee 0.00; ce -0.38;");
    pos.add(
        "3q4/p2r1k2/1p1b2nP/1Pp5/2P1Q3/1P2B3/3R1K2/8 w - - bm Kg1; id \"STS(v11.0) King Activity.030\"; c0 \"Kg1=10, Kf1=8\"; cem -0.13; cee -0.92; ce -0.38;");
    pos.add(
        "3r1k2/4qp1p/6p1/p3p1P1/P2nQ2P/1B1R4/5PK1/8 b - - bm Kg7; id \"STS(v11.0) King Activity.031\"; c0 \"Kg7=10, Rc8=4, Rd7=4, Re8=4\"; cem -1.70; cee -0.92; ce -1.38;");
    pos.add(
        "3r1k2/p4pp1/2n1p2p/1NPr4/P7/6P1/5P1P/2R1RK2 b - - bm Ke7; id \"STS(v11.0) King Activity.032\"; c0 \"Ke7=10, a6=3, g5=4, g6=4\"; cem -1.44; cee -0.48; ce -0.81;");
    pos.add(
        "3r1r2/p3bpp1/1p2p1k1/4P2p/2P2P2/5K1P/PP3B2/3R1R2 b - - bm Kf5; id \"STS(v11.0) King Activity.033\"; c0 \"Kf5=10, f5=5, f6=1, Rfe8=5\"; cem -0.54; cee -0.44; ce -0.56;");
    pos.add(
        "3r1r2/p3bppk/1p2p2p/4P3/2P2P2/8/PP3BKP/3R1R2 b - - bm Kg6; id \"STS(v11.0) King Activity.034\"; c0 \"Kg6=10\"; cem -0.79; cee -0.20; ce -0.44;");
    pos.add(
        "3r2k1/1p4p1/p2P3p/1pPN4/1K4b1/8/2R4P/8 w - - bm Ka5; id \"STS(v11.0) King Activity.035\"; c0 \"Ka5=10, Rd2=5, Rf2=6, Rg2=5\"; cem -2.18; cee -0.68; ce -0.62;");
    pos.add(
        "3r2k1/1rq2p2/2bp2pP/p3p2n/4P3/1BN2P2/1PP5/R2RQK2 b - - bm Kh7; id \"STS(v11.0) King Activity.036\"; c0 \"Kh7=10, Nf4=5, Ra7=2\"; cem 0.27; cee 0.58; ce 0.23;");
    pos.add(
        "3r2k1/2p2ppp/8/P1b1N3/8/1Bn1nP2/5B1P/4K2R b - - bm Kf8; id \"STS(v11.0) King Activity.037\"; c0 \"Kf8=10, g6=3, Ncd5=1\"; cem -7.20; cee -0.41; ce -2.57;");
    pos.add(
        "3r2k1/4pp1p/2q3p1/8/1P6/r1P2P2/P3Q1PP/RKR5 w - - bm Kb2; id \"STS(v11.0) King Activity.038\"; c0 \"Kb2=10, h3=6, Qe1=6, Qe4=5\"; cem -0.78; cee 1.56; ce 0.35;");
    pos.add(
        "3r3r/ppk2pb1/4bn1p/3p2p1/3N4/2NR1BP1/PPP1P2P/5RK1 b - - bm Kb8; id \"STS(v11.0) King Activity.039\"; c0 \"Kb8=10, h5=2, Rhe8=1\"; cem 0.57; cee -0.77; ce -0.15;");
    pos.add(
        "3r4/6p1/5kBp/7P/6P1/4BP2/1p2K3/8 b - - bm Ke5; id \"STS(v11.0) King Activity.040\"; c0 \"Ke5=10, Ke6=6, Rd5=7, Rd7=7\"; cem 2.49; cee 0.97; ce 0.82;");
    pos.add(
        "3R4/6pk/p4p1p/1r1p3P/2pP4/2P1PN2/4bPP1/6K1 w - - bm Kh2; id \"STS(v11.0) King Activity.041\"; c0 \"Kh2=10, Kh1=2\"; cem 0.50; cee 0.62; ce 0.71;");
    pos.add(
        "3r4/p3k3/1p2bpp1/2p5/2P2q2/2Q2N2/PP4PP/4R2K w - - bm Kg1; id \"STS(v11.0) King Activity.042\"; c0 \"Kg1=10, b3=9, h3=9, Qc2=9\"; cem 1.78; cee -0.10; ce 0.88;");
    pos.add(
        "3rbk2/4pp1p/6p1/3PP3/p1PnN1P1/Nn5P/5PBK/1R6 w - - bm Kg3; id \"STS(v11.0) King Activity.043\"; c0 \"Kg3=10, Nc3=4, Re1=4\"; cem 1.90; cee 1.67; ce 1.83;");
    pos.add(
        "3rbk2/5p1p/1p2p3/pP1nr1p1/P2RN1P1/3BP3/3K3P/3R4 b - - bm Ke7; id \"STS(v11.0) King Activity.044\"; c0 \"Ke7=10, Bd7=4, h6=6, Rd7=8\"; cem -0.94; cee -0.06; ce -0.50;");
    pos.add(
        "3rr1k1/q4pp1/p1ppbR1p/2P4R/3PP3/2N1Q2P/6P1/6K1 b - - bm Kf8; id \"STS(v11.0) King Activity.045\"; c0 \"Kf8=10, dxc5=3\"; cem -1.93; cee -0.70; ce -1.65;");
    pos.add(
        "3rr3/p1k4p/1p3p1b/2p2p1n/5P2/2P1BR2/PP1N3P/1K2R3 b - - bm Kc6; id \"STS(v11.0) King Activity.046\"; c0 \"Kc6=10, Kb7=5, Kc8=5\"; cem -0.60; cee -1.61; ce -1.30;");
    pos.add(
        "4k3/1p4pp/p1b2p2/P4P2/1PNp2qN/2nP2P1/3Q1K1P/8 b - - bm Kd8; id \"STS(v11.0) King Activity.047\"; c0 \"Kd8=10, Kf8=2, Qd1=3\"; cem -0.87; cee -0.05; ce -0.45;");
    pos.add(
        "4k3/3p3p/p2Pn1p1/Pr2Pp2/5P1P/1NR3P1/5K2/8 w - - bm Ke3; id \"STS(v11.0) King Activity.048\"; c0 \"Ke3=10, h5=7, Ke1=5, Rc8+=4, Rd3=3\"; cem 3.03; cee 1.61; ce 1.72;");
    pos.add(
        "4r1k1/1p1q2pp/p4pn1/3p4/3N4/1PPK2RP/P2Q1PP1/8 w - - bm Kc2; id \"STS(v11.0) King Activity.049\"; c0 \"Kc2=10, Qc1=5, Qd1=5\"; cem -3.93; cee 0.19; ce -1.61;");
    pos.add(
        "4r1k1/1Q3ppp/2PB4/pb6/8/P1K5/6P1/1r6 w - - bm Kc2; id \"STS(v11.0) King Activity.050\"; c0 \"Kc2=10, c7=7, Kd4=1\"; cem -4.12; cee -0.29; ce -1.09;");
    pos.add(
        "4r1k1/2q1p3/2p2pp1/1p1b4/p7/P1Q3R1/1P3PP1/4R1K1 b - - bm Kg7; id \"STS(v11.0) King Activity.051\"; c0 \"Kg7=10, Bf7=8, g5=8, Kf7=8\"; cem 2.28; cee 0.42; ce 1.26;");
    pos.add(
        "4r1k1/3b1q2/2pn1p1b/1p3P2/p2PN1PP/P4Q2/1P4R1/4R1K1 b - - bm Kf8; id \"STS(v11.0) King Activity.052\"; c0 \"Kf8=10\"; cem 0.65; cee 0.75; ce 0.58;");
    pos.add(
        "4r1k1/5r2/p5p1/6p1/1PbB4/2P2B2/5PP1/R5K1 w - - bm Kh2; id \"STS(v11.0) King Activity.053\"; c0 \"Kh2=10, Be3=8, Ra5=6, Rd1=4\"; cem 0.15; cee -0.12; ce 0.04;");
    pos.add(
        "4r1k1/p4p1p/1bpp2p1/3p4/8/1P1Q2P1/P1P1rPKP/5R2 w - - bm Kf3; id \"STS(v11.0) King Activity.054\"; c0 \"Kf3=10, Kg1=2, Qc3=2\"; cem -0.41; cee -0.51; ce -0.38;");
    pos.add(
        "4r2r/1p6/2p2n2/p1Pp3k/P2NbPp1/4R3/1P2P2P/2R2BK1 b - - bm Kg6; id \"STS(v11.0) King Activity.055\"; c0 \"Kg6=10, Kh4=4, Ref8=3, Rh7=3\"; cem 0.47; cee 0.39; ce 0.33;");
    pos.add(
        "4r3/2R1pk2/3p2pp/2bP1p2/8/1R4P1/5PK1/8 b - - bm Kf6; id \"STS(v11.0) King Activity.056\"; c0 \"Kf6=10, Bd4=8, g5=9, h5=8, Ra8=8\"; cem 0.64; cee 0.44; ce 0.33;");
    pos.add(
        "4r3/5k2/2PB4/p4p2/8/1P1R2KP/2P5/5r2 w - - bm Kg2; id \"STS(v11.0) King Activity.057\"; c0 \"Kg2=10, Rd2=1\"; cem 0.51; cee 1.06; ce 1.12;");
    pos.add(
        "4r3/5k2/3p1npp/1p1P1p2/p4P2/P3N2P/1P2KP2/2R5 w - - bm Kf3; id \"STS(v11.0) King Activity.058\"; c0 \"Kf3=10, h4=8, Kf1=8, Rc7+=8\"; cem -1.63; cee -1.13; ce -1.04;");
    pos.add(
        "4r3/5k2/p2B2r1/2P2p2/1P5p/3R3P/1P3KP1/8 w - - bm Kf3; id \"STS(v11.0) King Activity.059\"; c0 \"Kf3=10\"; cem 0.30; cee 0.19; ce 0.29;");
    pos.add(
        "4R3/5pkp/b3p1p1/2Q5/1P6/P2qP3/K7/8 w - - bm Kb2; id \"STS(v11.0) King Activity.060\"; c0 \"Kb2=10, Qc1=4\"; cem -2.89; cee 2.11; ce 0.73;");
    pos.add(
        "4rbk1/p2R1p2/2p2p1p/5p2/8/PR2p3/2P3PP/5K2 w - - bm Ke2; id \"STS(v11.0) King Activity.061\"; c0 \"Ke2=10, g3=3, Rc3=3\"; cem -2.15; cee 0.24; ce 0.18;");
    pos.add(
        "4rnk1/1p2rp1p/2p3p1/3p1B1P/N4R2/pP2P1R1/P5P1/6K1 w - - bm Kf2; id \"STS(v11.0) King Activity.062\"; c0 \"Kf2=10, e4=5, hxg6=7, Rff3=6\"; cem 0.54; cee 0.37; ce 0.52;");
    pos.add(
        "4rrk1/7p/pn1p1npb/N2P4/1P6/5BP1/PB6/3R1RK1 w - - bm Kg2; id \"STS(v11.0) King Activity.063\"; c0 \"Kg2=10, Bxf6=2\"; cem 0.67; cee 0.00; ce 0.45;");
    pos.add(
        "5bk1/1r3p2/6p1/2pRP3/8/6P1/3N1PK1/8 b - - bm Kg7; id \"STS(v11.0) King Activity.064\"; c0 \"Kg7=10\"; cem -0.17; cee 0.00; ce -0.10;");
    pos.add(
        "5k2/p3np2/2b1p2p/2P2p1P/8/2N1PP2/2K1B1P1/8 b - - bm Ke8; id \"STS(v11.0) King Activity.065\"; c0 \"Ke8=10, Bd7=7, Nd5=8, Ng8=6\"; cem -0.36; cee 0.64; ce 0.54;");
    pos.add(
        "5n2/1p2r1kp/1Qp1r1p1/p3Pp2/P6P/4R1P1/1P6/5K2 w - - bm Kg2; id \"STS(v11.0) King Activity.066\"; c0 \"Kg2=10, b3=6, Kg1=7, Re1=9, Re2=8\"; cem 0.76; cee 0.87; ce 0.93;");
    pos.add(
        "5r2/1N6/2p2Pk1/p1P1P1p1/3b3p/KP1N2n1/P6R/8 b - - bm Kf5; id \"STS(v11.0) King Activity.067\"; c0 \"Kf5=10, Ne4=1, Nf1=1\"; cem 2.30; cee 0.77; ce 0.91;");
    pos.add(
        "5rk1/1p5p/3p2p1/qP1N1n2/2P2P2/8/P4Q1P/4R1K1 w - - bm Kg2; id \"STS(v11.0) King Activity.068\"; c0 \"Kg2=10, Kf1=3, Kh1=5, Rc1=2, Re4=5\"; cem 1.33; cee 1.38; ce 1.45;");
    pos.add(
        "5rrk/6qp/2R2b2/1P1pp2Q/5p2/7R/P2B1P2/5K2 w - - bm Ke2; id \"STS(v11.0) King Activity.069\"; c0 \"Ke2=10, Be1=9, Rc1=9, Rh1=9\"; cem -8.48; cee -0.45; ce -5.93;");
    pos.add(
        "6k1/5b1p/6p1/r1P5/3pNK2/5P1P/p5P1/R7 b - - bm Kf8; id \"STS(v11.0) King Activity.070\"; c0 \"Kf8=10, h6=6, Kg7=4, Rb5=6\"; cem -1.67; cee -0.95; ce -1.06;");
    pos.add(
        "6k1/8/p2Br1r1/2P2p1p/1P6/7P/1P1R2PK/8 w - - bm Kg1; id \"STS(v11.0) King Activity.071\"; c0 \"Kg1=10, Rc2=4, Rf2=2\"; cem 1.58; cee 1.36; ce 1.47;");
    pos.add(
        "6r1/1b1k4/2p2p2/ppB1p3/2p1P2P/P2n4/1P1R2P1/5K1R b - - bm Ke6; id \"STS(v11.0) King Activity.072\"; c0 \"Ke6=10, Kc7=4, Ke8=2\"; cem 0.98; cee 1.29; ce 0.90;");
    pos.add(
        "6r1/pb3pk1/1p2p3/1Bp1P3/2Pb4/P7/4RPPR/6K1 b - - bm Kf8; id \"STS(v11.0) King Activity.073\"; c0 \"Kf8=10, a6=8, Ba8=8, Rb8=7\"; cem 1.31; cee 1.32; ce 1.22;");
    pos.add(
        "6rk/q2p3p/4pp2/1P1n3P/2R5/p4NP1/P4P2/1Q4K1 w - - bm Kg2; id \"STS(v11.0) King Activity.074\"; c0 \"Kg2=10, Nd4=4, Rd4=4\"; cem 0.54; cee 0.96; ce 0.86;");
    pos.add(
        "7k/1b1n1pp1/p2Ppq1p/1p5Q/1P1N3P/P3P3/B5P1/6K1 b - - bm Kg8; id \"STS(v11.0) King Activity.075\"; c0 \"Kg8=10, Kh7=6, Nf8=1\"; cem -1.75; cee 0.45; ce -0.49;");
    pos.add(
        "7k/1q5p/5prP/1p1pr3/1PbR1QPK/2N3P1/2P5/3R4 w - - bm Kh3; id \"STS(v11.0) King Activity.076\"; c0 \"Kh3=10, R1d2=8, Ra1=9, Rb1=8\"; cem -0.95; cee 1.99; ce 0.04;");
    pos.add(
        "7k/3N1p2/p7/3p1rp1/8/1P2P2p/P4n2/2R2NK1 w - - bm Kh2; id \"STS(v11.0) King Activity.077\"; c0 \"Kh2=10, Ng3=6, Rc2=5, Rc8+=6\"; cem -0.04; cee 1.91; ce 1.76;");
    pos.add(
        "7k/3r1p2/p1r1p2p/1q1n3P/1PNRQP2/P7/K7/2R5 w - - bm Kb2; id \"STS(v11.0) King Activity.078\"; c0 \"Kb2=10\"; cem -0.47; cee 0.40; ce -0.10;");
    pos.add(
        "7r/5p2/3k4/1p1p4/rPnP3P/4P3/4RPN1/1R5K w - - bm Kh2; id \"STS(v11.0) King Activity.079\"; c0 \"Kh2=10, Kg1=7, Nf4=5, Rb3=4\"; cem 2.14; cee 0.30; ce 0.84;");
    pos.add(
        "7r/r1p2k1p/1p5B/1bp5/4pPP1/1P2Nn1P/PR3R2/7K b - - bm Kg6; id \"STS(v11.0) King Activity.080\"; c0 \"Kg6=10, Ne1=2, Ra3=2, Rd8=1, Rha8=2\"; cem 1.17; cee 0.13; ce 0.42;");
    pos.add(
        "8/1k1r3p/pp3p1R/2pn4/4r3/N1P5/PP5P/2KR4 b - - bm Kc7; id \"STS(v11.0) King Activity.081\"; c0 \"Kc7=10, b5=4, Ree7=4\"; cem -1.52; cee -0.53; ce -0.87;");
    pos.add(
        "8/1p2bk2/r7/p2P4/2P5/4B1R1/PP4Pn/1K6 w - - bm Kc2; id \"STS(v11.0) King Activity.082\"; c0 \"Kc2=10, Bd2=2, Bd4=7, Bg1=8\"; cem 0.94; cee -0.55; ce -0.23;");
    pos.add(
        "8/2kr3p/5p2/pppn4/4r3/1PP4R/P1N4P/2KR4 b - - bm Kc6; id \"STS(v11.0) King Activity.083\"; c0 \"Kc6=10, c4=4, Re2=5\"; cem -0.85; cee -1.08; ce -1.12;");
    pos.add(
        "8/3k3p/2p2p2/2B5/8/1r1b1P1P/3R1KP1/8 b - - bm Ke6; id \"STS(v11.0) King Activity.084\"; c0 \"Ke6=10, Kc7=7, Ke8=7\"; cem 2.17; cee 0.67; ce 0.42;");
    pos.add(
        "8/4q1k1/5pp1/pp2b3/2p1P3/P1P1Q1Pp/7P/3R2K1 w - - bm Kf1; id \"STS(v11.0) King Activity.085\"; c0 \"Kf1=10, Qd2=3, Rd5=4\"; cem 0.07; cee 0.23; ce 0.27;");
    pos.add(
        "8/4r1n1/1k5p/1p1prp1P/1PpN2p1/p1K1P1R1/P1B3PR/8 w - - bm Kd2; id \"STS(v11.0) King Activity.086\"; c0 \"Kd2=10, Bb1=9, Rh1=9, Rh4=9\"; cem 1.10; cee 1.72; ce 1.62;");
    pos.add(
        "8/4r2p/2k2p2/1p1nr3/1Pp5/2P2R2/2NR3P/1K6 b - - bm Kb6; id \"STS(v11.0) King Activity.087\"; c0 \"Kb6=10, f5=6, Rg7=5, Rh5=6\"; cem -0.91; cee -2.18; ce -1.97;");
    pos.add(
        "8/p1r1rk1p/2R1pb2/P4p2/1N4p1/1Pp1P1P1/5P1P/2R3K1 w - - bm Kf1; id \"STS(v11.0) King Activity.088\"; c0 \"Kf1=10, a6=7, Kg2=7, Rd6=7, Rxc7=6\"; cem 0.06; cee -0.97; ce -0.62;");
    pos.add(
        "8/p3p1k1/1p1p1pp1/4n3/3R3P/1Pr5/P5P1/3R2K1 b - - bm Kh6; id \"STS(v11.0) King Activity.089\"; c0 \"Kh6=10, a5=8, f5=8, Rc2=6\"; cem -0.41; cee -0.26; ce -0.37;");
    pos.add(
        "8/pQ3qk1/P2pp2p/2p2n2/2P2PB1/3P3P/8/7K b - - bm Kf6; id \"STS(v11.0) King Activity.090\"; c0 \"Kf6=10, h5=7, Kf8=7, Ng3+=6\"; cem 1.04; cee -0.28; ce -0.06;");
    pos.add(
        "8/pr3pbk/6p1/7p/R7/2p1P1P1/2R2P1P/6K1 w - - bm Kf1; id \"STS(v11.0) King Activity.091\"; c0 \"Kf1=10, Kg2=4, Ra5=4\"; cem 0.53; cee 0.24; ce 0.35;");
    pos.add(
        "8/R2n4/pr1k2p1/4pp1p/5P2/1N2P1PP/Pn2B1K1/8 w - - bm Kf1; id \"STS(v11.0) King Activity.092\"; c0 \"Kf1=10, Bxa6=3, Kf2=4, Kh2=3\"; cem 2.17; cee 0.51; ce 0.87;");
    pos.add(
        "b2q2r1/5p2/2prpkp1/1pN5/p2P1PP1/P3PQ1p/1PB4P/2R3K1 b - - bm Kg7; id \"STS(v11.0) King Activity.093\"; c0 \"Kg7=10, Ke7=4, Qa5=6, Re8=5, Rh8=4\"; cem 7.57; cee -0.50; ce 4.70;");
    pos.add(
        "br3rk1/2q1bpp1/pnnpp2p/1p3P2/4P3/PNN1BBQ1/1PPR2PP/1R4K1 b - - bm Kh7; id \"STS(v11.0) King Activity.094\"; c0 \"Kh7=10, Bf6=4, Kh8=8\"; cem 1.36; cee 0.73; ce 1.26;");
    pos.add(
        "r1b3qr/3kppb1/p2p4/2pPn1B1/1pP1P3/1N5P/1P2BQP1/1R3RK1 b - - bm Ke8; id \"STS(v11.0) King Activity.095\"; c0 \"Ke8=10\"; cem 4.38; cee 0.61; ce 4.22;");
    pos.add(
        "r2r2k1/p4pp1/b6p/8/1pp1Pn2/4NP2/PPB3PP/3RR1K1 w - - bm Kf2; id \"STS(v11.0) King Activity.096\"; c0 \"Kf2=10, g3=4, Nd5=2\"; cem 0.17; cee 0.27; ce 0.33;");
    pos.add(
        "r2r4/5qbk/2p3pp/pp2p3/Pn1P1p1Q/4P2N/1P1N1PPP/2RR2K1 b - - bm Kg8; id \"STS(v11.0) King Activity.097\"; c0 \"Kg8=10, Bf6=7, Qe8=2, Qf6=6\"; cem -1.81; cee -1.46; ce -1.84;");
    pos.add(
        "r4b1r/pp1k1pp1/4bn1p/3p4/4p3/1NN3P1/PPP1PPBP/3R1RK1 b - - bm Kc7; id \"STS(v11.0) King Activity.098\"; c0 \"Kc7=10, Kc6=7, Ke8=7\"; cem 1.41; cee -0.43; ce 0.45;");
    pos.add(
        "r5k1/4pp1p/6p1/3q4/1P6/r1P2P2/PKR1Q1PP/R7 w - - bm Kc1; id \"STS(v11.0) King Activity.099\"; c0 \"Kc1=10, Kb1=3, Kb1=9\"; cem -0.70; cee 2.87; ce 0.98;");
    pos.add(
        "R7/2k5/2p2p2/4p3/1pp1P1b1/n7/6P1/2R2K2 b - - bm Kb6; id \"STS(v11.0) King Activity.100\"; c0 \"Kb6=10\"; cem 0.01; cee 0.47; ce 0.29;");
    pos.add(
        "1k1r4/4bp2/p1q1pnr1/6B1/NppP3P/6P1/1P3P2/2RQR1K1 w - - bm Re5; id \"STS(v12.0) Center Control.001\"; cem -0.51; cee -0.96; ce -0.49;");
    pos.add(
        "1k5r/1pq1b2r/p2p1p2/4n1p1/R3P1p1/1BP3B1/PP1Q3P/1K1R4 w - - bm Bd5; id \"STS(v12.0) Center Control.002\"; cem -0.70; cee -1.28; ce -0.69;");
    pos.add(
        "1kb4r/1p3pr1/3b1p1p/q2B1p2/p7/P1P3P1/1P1Q2NP/K2RR3 b - - bm Be5; id \"STS(v12.0) Center Control.003\"; cem 1.11; cee 0.99; ce 0.99;");
    pos.add(
        "1kr5/1b3ppp/p4n2/3p4/2qN1P2/2r2B2/PQ4PP/R2R3K b - - bm Ne4; id \"STS(v12.0) Center Control.004\"; cem 1.52; cee -0.66; ce 1.07;");
    pos.add(
        "1n1r4/p1q2pk1/b2bp2p/4N1p1/3P1P2/1QN1P3/5PBP/1R5K w - - bm Ne4; id \"STS(v12.0) Center Control.005\"; cem 1.22; cee 0.42; ce 1.11;");
    pos.add(
        "1n1rr1k1/1pq2pp1/3b2p1/2p3N1/P1P5/P3B2P/2Q2PP1/R2R2K1 w - - bm Ne4; id \"STS(v12.0) Center Control.006\"; cem 0.96; cee 0.50; ce 0.98;");
    pos.add(
        "1n1rr1k1/5pp1/1qp4p/3p3P/3P4/pP1Q1N2/P1R2PP1/1KR5 w - - bm Ne5; id \"STS(v12.0) Center Control.007\"; cem -0.13; cee 1.19; ce 0.37;");
    pos.add(
        "1r1r1bk1/1bq2p1p/pn2p1p1/2p1P3/5P2/P1NBB3/1P3QPP/R2R2K1 b - - bm Nd5; id \"STS(v12.0) Center Control.008\"; cem -0.21; cee 0.26; ce -0.29;");
    pos.add(
        "1r2qrk1/2p3pp/2Qb1p2/2p1pP2/8/BP6/3P1PPP/R4RK1 w - - bm Qe4; id \"STS(v12.0) Center Control.009\"; cem -0.69; cee -0.16; ce -0.43;");
    pos.add(
        "1r2qrk1/4n3/ppbp3p/n1p1p1p1/2P5/B1PP2PP/Q2N1PB1/1R2R1K1 w - - bm Ne4; id \"STS(v12.0) Center Control.010\"; cem -1.06; cee -1.57; ce -0.97;");
    pos.add(
        "1r3b2/3R2pk/6q1/3Q1p2/p6P/4B2P/1P3P1K/8 w - - bm Bd4; id \"STS(v12.0) Center Control.011\"; cem 2.59; cee 1.24; ce 1.97;");
    pos.add(
        "1r3r1k/6pp/p2p4/q3pb2/2P4P/1P2Q1P1/P4PB1/R4K1R w - - bm Bd5; id \"STS(v12.0) Center Control.012\"; cem 0.66; cee 1.18; ce 0.91;");
    pos.add(
        "1r3r1k/7p/p1p1q3/1pP1P3/3P4/Pb6/3Q2BP/2R1R1K1 b - - bm Bd5; id \"STS(v12.0) Center Control.013\"; cem 1.78; cee 2.02; ce 1.75;");
    pos.add(
        "1r4k1/p1rnpp2/p2p1bp1/2qP4/4PQ2/1PN4P/P2B2P1/1R3R1K b - - bm Qd4; id \"STS(v12.0) Center Control.014\"; cem 0.11; cee 0.00; ce -0.01;");
    pos.add(
        "1rb2rnk/2qn4/pp1p3p/2pP1p2/PPPbp2B/1QN5/R2NBPPP/1R4K1 b - - bm Ne5; id \"STS(v12.0) Center Control.015\"; cem 0.65; cee 0.92; ce 0.56;");
    pos.add(
        "2b2q1k/2p4p/1rNn1p2/1p1P4/p3p3/P1P5/1P1Q2PP/1B1R2K1 w - - bm Qd4; id \"STS(v12.0) Center Control.016\"; cem 1.00; cee 0.29; ce 0.82;");
    pos.add(
        "2b3k1/1q3pp1/p2R3p/8/Q7/2P1r3/6PP/R5K1 w - - bm Qd4; id \"STS(v12.0) Center Control.017\"; cem 0.16; cee 1.25; ce 0.76;");
    pos.add(
        "2br4/2q2k1p/p2bp1p1/1r6/4BP2/2p1B3/P3Q1PP/2R2R1K w - - bm Bd4; id \"STS(v12.0) Center Control.018\"; cem -0.56; cee -2.54; ce -0.77;");
    pos.add(
        "2q1rbk1/1b3p2/p4Pp1/1p1p3p/3B4/1P1Q1N1P/1P3PP1/R5K1 w - - bm Ne5; id \"STS(v12.0) Center Control.019\"; cem 0.90; cee 0.49; ce 0.84;");
    pos.add(
        "2r1r1k1/p1qn1pp1/1p3n1p/3p4/3P3B/P1NQ4/1P3PPP/2RR2K1 b - - bm Ne4; id \"STS(v12.0) Center Control.020\"; cem 0.32; cee 0.00; ce 0.16;");
    pos.add(
        "2r1rbk1/pp2p2p/6p1/n4p2/1NbP4/4PBBP/P4PP1/2RR2K1 w - - bm Nd5; id \"STS(v12.0) Center Control.021\"; cem 1.27; cee 0.34; ce 0.94;");
    pos.add(
        "2r2k2/p1q1bpp1/2p2n2/2Np3r/1P5p/P1Q1B2P/5PP1/2R1R1K1 w - - bm Bd4; id \"STS(v12.0) Center Control.022\"; cem 0.55; cee -0.43; ce 0.48;");
    pos.add(
        "2r2rk1/1p3ppp/p2q1n2/3pn3/N7/4P2P/PP2BPP1/2RQ1RK1 w - - bm Qd4; id \"STS(v12.0) Center Control.023\"; cem -0.12; cee -0.33; ce -0.05;");
    pos.add(
        "2r2rk1/1pp2qpb/1n2p2p/pP1p1P2/P2PPBP1/7P/3NQ3/R1R3K1 w - - bm Be5; c0 \"Be5=10, Bg3=4, Ra3=4, Rf1=4\"; id \"STS(v12.0) Center Control.024\"; cem 2.03; cee 2.13; ce 2.09;");
    pos.add(
        "2r3k1/1b2q1pp/1pr1p3/4B3/2P1p3/4P3/4QPPP/1R1R2K1 w - - bm Rd4; c0 \"Rd4=10, h3=2, h4=2, Qg4=2\"; id \"STS(v12.0) Center Control.025\"; cem 1.43; cee 0.88; ce 1.29;");
    pos.add(
        "2r3k1/p2npp2/p2p1bp1/3P4/1qN1PQ1P/1P2B3/r5P1/1R3R1K b - - bm Ne5; c0 \"Ne5=10, a5=7, Bd4=10Qb8=6, Rc7=6\"; id \"STS(v12.0) Center Control.026\"; cem -0.14; cee -0.76; ce -0.34;");
    pos.add(
        "2r3k1/ppr2p2/4b2p/4b1p1/P7/2N1P3/1PB2PPP/2RRK3 w - - bm Be4; c0 \"Be4=10, Bd3=6, h3=6, Rd3=6\"; id \"STS(v12.0) Center Control.027\"; cem -0.94; cee 0.07; ce -0.23;");
    pos.add(
        "2r4k/5p1p/1prp1q1P/2n1p1p1/p3P1P1/P3BP2/1PP5/1KRR3Q w - - bm Rd5; c0 \"Rd5=10, Qg2=3, Qh3=7, Qh5=5, Re1=6, Rf1=7\"; id \"STS(v12.0) Center Control.028\"; cem 2.28; cee 1.14; ce 2.02;");
    pos.add(
        "2r5/1b2kp1p/3qp1p1/1B6/3pn2N/8/PP2QPPP/4R1K1 b - - bm Qd5; c0 \"Qd5=10, Nf6=4, Qf4=5, Rc5=4\"; id \"STS(v12.0) Center Control.029\"; cem -0.12; cee -1.66; ce -0.82;");
    pos.add(
        "2r5/1bq2k2/p2pp2r/5p2/2PN1Pp1/1PB1Q3/P5PP/4R1K1 b - - bm Be4; c0 \"Be4=10, g3=1, Qc5=1, Qd7=2\"; id \"STS(v12.0) Center Control.030\"; cem -0.43; cee -1.58; ce -0.80;");
    pos.add(
        "2rq1k1r/pp3p2/7p/1BPp1bp1/1P6/P1N1P3/3Q1PPP/2R1K2n w - - bm Qd4; c0 \"Qd4=10, Ke2=4, Kf1=5, Nxd5=5\"; id \"STS(v12.0) Center Control.031\"; cem -2.75; cee -3.81; ce -2.95;");
    pos.add(
        "2rq1rk1/p3b1pp/2p1p3/8/P2Pp3/1P1bP1P1/3B3P/2RQRBK1 b - - bm Qd5; c0 \"Qd5=10, Bxf1=1, e5=1\"; id \"STS(v12.0) Center Control.032\"; cem -1.69; cee -0.34; ce -1.57;");
    pos.add(
        "2rq1rk1/pb2bppp/1p2pn2/4N3/3P1B2/2PB4/P4PPP/2RQR1K1 b - - bm Qd5; c0 \"Qd5=10, Ba3=7, Nd5=7, Rc7=6\"; id \"STS(v12.0) Center Control.033\"; cem 1.60; cee 1.02; ce 1.50;");
    pos.add(
        "2rq1rk1/pb3ppp/1p1p1b2/2n1p3/P1B1P3/1P3N2/2P1RPPP/R1BQ2K1 w - - bm Bd5; c0 \"Bd5=10, Bb2=6, Nd2=3, Rb1=5\"; id \"STS(v12.0) Center Control.034\"; cem -0.06; cee 0.12; ce 0.04;");
    pos.add(
        "2rr4/p1N1pk1p/1p4pb/n4p2/2bP4/4PB1P/P1R2PPB/3R2K1 w - - bm d5; c0 \"d5=10, Be5=4, Rdc1=5\"; id \"STS(v12.0) Center Control.035\"; cem 1.31; cee -1.11; ce 0.29;");
    pos.add(
        "3r1bk1/3q1p2/4bP1p/pp4pP/2pP4/2P3B1/P1Q3P1/4RRK1 w - - bm Re5; c0 \"Re5=10, a3=1, Re3=4, Rf3=3\"; id \"STS(v12.0) Center Control.036\"; cem 2.50; cee 2.64; ce 2.64;");
    pos.add(
        "3r1qk1/6pp/1p1np3/2n4Q/p7/P3N1P1/1B2PP1P/3R2K1 w - - bm Rd4; c0 \"Rd4=10, Bd4=6, f3=3, Nc4=5, Qg4=4, Qh4=3\"; id \"STS(v12.0) Center Control.037\"; cem 0.47; cee -0.02; ce 0.37;");
    pos.add(
        "3r1r2/2n3kp/1pp1p1p1/p2nP3/P7/2NR2PB/1P3P1P/3R2K1 w - - bm Ne4; c0 \"Ne4=10, f4=4, Rd4=3\"; id \"STS(v12.0) Center Control.038\"; cem 0.31; cee -0.32; ce 0.02;");
    pos.add(
        "3r1r2/2p3k1/1p3b1p/p7/2P1qP1P/1P1R2P1/P4P2/1Q1R2K1 b - - bm Bd4; c0 \"Bd4=10, Rde8=2, Rf7=2, Rxd3=2\"; id \"STS(v12.0) Center Control.039\"; cem -1.36; cee -1.98; ce -1.69;");
    pos.add(
        "3r2k1/1p1b1pp1/p1q4p/8/2nNRB2/2P3P1/P4P1P/4Q1K1 b - - bm Qd5; c0 \"Qd5=10, Qb6=2, Qc5=3, Qc8=2\"; id \"STS(v12.0) Center Control.040\"; cem -0.56; cee -0.20; ce -0.50;");
    pos.add(
        "3r2k1/3r1pp1/2QP4/pP5p/1qN5/4R1P1/4PK1P/8 w - - bm Re4; c0 \"Re4=10, Rd3=1\"; id \"STS(v12.0) Center Control.041\"; cem -0.77; cee 0.75; ce 0.08;");
    pos.add(
        "3r2k1/3r2p1/2q1p2p/1p1n1p2/p7/P3RQ1P/BPP2PP1/3R2K1 w - - bm Re5; c0 \"Re5=10, h4=3, Rd2=3, Rde1=2, Ree1=1\"; id \"STS(v12.0) Center Control.042\"; cem -0.99; cee -1.57; ce -1.07;");
    pos.add(
        "3r2r1/BpqP1pkp/6p1/3Qb3/8/2p3P1/PP1R1PBP/5K2 w - - bm Bd4; c0 \"Bd4=10, Bb6=3, bxc3=7, Rd1=5\"; id \"STS(v12.0) Center Control.043\"; cem -1.74; cee -1.00; ce -1.38;");
    pos.add(
        "3r4/1p4kp/p1b3p1/2q1p3/P1P5/1B3P1P/4Q1P1/1R5K b - - bm Rd4; c0 \"Rd4=10, a5=3, b6=4, Kf6=4, Kf7=4\"; id \"STS(v12.0) Center Control.044\"; cem -0.31; cee -1.23; ce -0.89;");
    pos.add(
        "3rbk2/4np1p/1p2p3/pP2r1p1/P1R1N1P1/3BP3/3K3P/3R4 b - - bm Nd5; c0 \"Nd5=10, Bd7=3, h6=4, Kg7=4\"; id \"STS(v12.0) Center Control.045\"; cem -1.85; cee -0.92; ce -1.38;");
    pos.add(
        "4k3/p1p3p1/1pbp2qp/n3p3/2P3PB/2PPQ2P/P3P1B1/6K1 w - - bm Bd5; c0 \"Bd5=10, Bf1=5, Bf2=5, Bxc6+=7\"; id \"STS(v12.0) Center Control.046\"; cem 2.50; cee 0.11; ce 1.12;");
    pos.add(
        "4r1k1/1b3pp1/p2pqb2/1p4r1/4PR2/PNN4P/1PP2QP1/5R1K w - - bm Nd4; c0 \"Nd4=10, Nd5=1, Qb6=3\"; id \"STS(v12.0) Center Control.047\"; cem 0.22; cee 0.47; ce 0.35;");
    pos.add(
        "4r1k1/1pb1qp2/p1b3p1/3p1rN1/P4P1p/1PPRB1P1/3Q3P/3R2K1 w - - bm Bd4; c0 \"Bd4=10, Bf2=4, Nf3=3, Re1=4, Rf1=4\"; id \"STS(v12.0) Center Control.048\"; cem -0.02; cee 0.05; ce 0.09;");
    pos.add(
        "4r1k1/3r1p1p/3p2p1/3P2Q1/4PR2/2n2B1P/5qPK/R7 b - - bm Qd4; c0 \"Qd4=10, Qd2=1, Qe3=1, Ra8=6, Rb7=1\"; id \"STS(v12.0) Center Control.049\"; cem 0.50; cee 0.00; ce 0.25;");
    pos.add(
        "4r1k1/4r1pp/p7/3n4/2p1P3/2P1BPN1/P1P4P/1K2R3 w - - bm Bd4; c0 \"Bd4=10, Bd2=7, Kc1=7, Nf5=7\"; id \"STS(v12.0) Center Control.050\"; cem -1.64; cee -1.05; ce -1.07;");
    pos.add(
        "4r1k1/p5b1/P2p1pp1/q1pP3p/2Pn1BbP/2NP2P1/3Q2BK/1R6 w - - bm Ne4; c0 \"Ne4=10, Be5=7, Kh1=6, Nb5=10Rb7=7\"; id \"STS(v12.0) Center Control.051\"; cem -0.93; cee -0.03; ce -0.62;");
    pos.add(
        "4r1k1/pb2qp1p/4n2P/3p1pP1/1P1N1Q2/2N5/P1P5/5R1K w - - bm Qe5; c0 \"Qe5=10, Qd2=1\"; id \"STS(v12.0) Center Control.052\"; cem 0.04; cee 0.75; ce 0.41;");
    pos.add(
        "4r1k1/R3rpp1/1p3n1p/8/2Q5/4B2P/1q3PP1/3R2K1 w - - bm Bd4; c0 \"Bd4=10, Raa1=3, Rxe7=2\"; id \"STS(v12.0) Center Control.053\"; cem -0.05; cee 0.00; ce 0.06;");
    pos.add(
        "4r2k/1q4p1/p2Bp2p/P3P3/1PpN2P1/2Pb2b1/Q7/K2R4 b - - bm Qe4; c0 \"Qe4=10, Qa8=2, Qd7=1, Qf7=1\"; id \"STS(v12.0) Center Control.054\"; cem -0.08; cee 0.24; ce -0.05;");
    pos.add(
        "4r3/p4pk1/3p1n1p/1P4p1/4q3/1P4P1/1N1QP2P/4R1K1 b - - bm Qe5; c0 \"Qe5=10, d5=4, Qf5=4, Rb8=4\"; id \"STS(v12.0) Center Control.055\"; cem -1.45; cee -1.55; ce -1.60;");
    pos.add(
        "5n2/kbr3p1/5n1p/Pp2pP2/4P1PP/2p5/P1Br4/2RNRNK1 b - - bm Rd4; c0 \"Rd4=10, Ka6=5, Rd8=2, Rdd7=2\"; id \"STS(v12.0) Center Control.056\"; cem 0.12; cee -1.57; ce -0.76;");
    pos.add(
        "5r1k/p1r3np/1p3q2/2p4P/2Q2P2/8/PPB5/1K1R1R2 w - - bm Rd5; c0 \"Rd5=10, f5=2, Rfe1=2, Rg1=2\"; id \"STS(v12.0) Center Control.057\"; cem 1.98; cee 0.93; ce 1.75;");
    pos.add(
        "5rk1/p3pp1p/2Q2bp1/q1P5/3p4/5BP1/Pr2PP1P/2RR2K1 w - - bm Qe4; c0 \"Qe4=10, a4=6, h4=6, Kg2=3\"; id \"STS(v12.0) Center Control.058\"; cem -0.35; cee -0.26; ce -0.21;");
    pos.add(
        "5rk1/pb3ppp/1p3q2/2n5/2B1p3/2P5/P3QPPP/R2R2K1 w - - bm Rd4; c0 \"Rd4=10, a4=4, Bd5=5, Qe1=4, Qe3=5, Rac1=4\"; id \"STS(v12.0) Center Control.059\"; cem -0.26; cee 0.90; ce 0.23;");
    pos.add(
        "5rk1/qbrpnppp/p3p3/P2nP3/Np6/1N1B4/1PPQ1PPP/3RR1K1 w - - bm Re4; c0 \"Re4=10, c3=2, c4=2, g3=2\"; id \"STS(v12.0) Center Control.060\"; cem 0.94; cee 1.19; ce 1.04;");
    pos.add(
        "5rqk/3b2pp/1pnNp3/3r4/6P1/PQ2B1RP/5PK1/3R4 b - - bm Ne5; c0 \"Ne5=10, Na5=7, Ne7=7, Rxd1=6\"; id \"STS(v12.0) Center Control.061\"; cem -0.18; cee 0.67; ce -0.16;");
    pos.add(
        "6k1/1p2p3/2n1P2p/p3b3/2P2p2/1P4qP/P7/1B3QBK w - - bm Be4; c0 \"Be4=10, Bf2=4, Qg2=2\"; id \"STS(v12.0) Center Control.062\"; cem -0.39; cee -0.18; ce -0.16;");
    pos.add(
        "6k1/1p3pp1/p3q2p/3p3P/1Pn2Q2/P3PP2/4NKP1/8 w - - bm Qd4; c0 \"Qd4=10, g4=2\"; id \"STS(v12.0) Center Control.063\"; cem -1.37; cee 0.12; ce -0.13;");
    pos.add(
        "6k1/4qp2/p6p/1p6/2n2p2/P4Q2/1P4PP/6BK b - - bm Qe5; c0 \"Qe5=10, Qd6=3, Qf6=4, Qg5=1\"; id \"STS(v12.0) Center Control.064\"; cem -0.86; cee -0.96; ce -1.02;");
    pos.add(
        "6k1/5p2/p3b2p/1pp4q/4Pp2/1P3P2/P1Q3PP/3B1K2 b - - bm Qe5; c0 \"Qe5=10, c4=2, f6=1, Kh8=3\"; id \"STS(v12.0) Center Control.065\"; cem -0.54; cee 0.19; ce -0.09;");
    pos.add(
        "7k/1qr2p2/p3p2p/P1b1P1p1/2Q1n3/1p2BN1P/1P3PP1/2R3K1 w - - bm Nd4; c0 \"Nd4=10, Bd4=1, Kf1=1, Kh2=1\"; id \"STS(v12.0) Center Control.066\"; cem 0.67; cee 0.57; ce 0.72;");
    pos.add(
        "8/2p1r1pk/2pq2bp/2p5/2P3Q1/pP1P2NP/P3PRK1/8 b - - bm Qe5; c0 \"Qe5=10, Qd8=7, Re5=7, Re6=7\"; id \"STS(v12.0) Center Control.067\"; cem 0.24; cee 0.88; ce 0.48;");
    pos.add(
        "8/3n1kpp/1pr1p3/2n5/pR6/P3NPP1/1B2PK1P/8 w - - bm Bd4; c0 \"Bd4=10, f4=4, Nc4=4, Ng4=4\"; id \"STS(v12.0) Center Control.068\"; cem 0.50; cee 0.47; ce 0.57;");
    pos.add(
        "8/6k1/1b1p1pp1/1b1Pp3/2q1P3/6P1/2B2PKN/3Q4 b - - bm Qd4; c0 \"Qd4=10, Bc5=4, Bd4=4, Qa2=4\"; id \"STS(v12.0) Center Control.069\"; cem -2.29; cee -1.17; ce -1.70;");
    pos.add(
        "8/pp2r1kp/2prP3/5R2/2P5/3n2P1/P5BP/1R5K w - - bm Be4; c0 \"Be4=10, Bd5=6, Bf1=7, Rb3=7, Rf3=7\"; id \"STS(v12.0) Center Control.070\"; cem 2.50; cee 1.81; ce 2.08;");
    pos.add(
        "b4rk1/8/4pr1p/2q5/P4p2/2PB4/6PP/R3QR1K w - - bm Be4; c0 \"Be4=10, Be2=2, Qe4=5, Rb1=4, Rd1=3, Rg1=2\"; id \"STS(v12.0) Center Control.071\"; cem 0.75; cee 1.36; ce 1.02;");
    pos.add(
        "brr1n1k1/4bpp1/q2p4/Np2n3/1P1RP2p/2N4P/2P3PB/3RQB1K w - - bm Nd5; c0 \"Nd5=10, Be2=1, Qd2=1, Ra1=1\"; id \"STS(v12.0) Center Control.072\"; cem 0.65; cee -0.41; ce 0.75;");
    pos.add(
        "k2r4/2q3p1/p1Pr1p2/P1R1p2P/5pP1/8/1PQp1P2/1K1R4 b - - bm Rd4; c0 \"Rd4=10, Ka7=6, Kb8=6, Rd3=6\"; id \"STS(v12.0) Center Control.073\"; cem 0.61; cee -0.70; ce -0.07;");
    pos.add(
        "nr2q1k1/1p2rpb1/p2p2pp/P7/1PRN4/4BBPb/3Q1P1P/2R3K1 w - - bm Bd5; c0 \"Bd5=10, Kh1=5, Ne2=6, R4c2=5\"; id \"STS(v12.0) Center Control.074\"; cem 0.92; cee 0.85; ce 1.01;");
    pos.add(
        "r1q1r1k1/1p2b1pp/2p1P1b1/3p1p2/p2N1B2/P1Q1PP1P/1P4P1/2RR2K1 w - - bm Be5; c0 \"Be5=10, Kf2=1, Kh2=2, Rd2=2\"; id \"STS(v12.0) Center Control.075\"; cem 1.03; cee 1.22; ce 1.15;");
    pos.add(
        "r1q3k1/p2rnpb1/1pnBb1pp/2p1P3/6P1/2N2N1P/PP2QPB1/R2R2K1 w - - bm Qe4; c0 \"Qe4=10, Qe3=4, Rac1=5, Rd2=5\"; id \"STS(v12.0) Center Control.076\"; cem -0.01; cee 0.12; ce 0.08;");
    pos.add(
        "r1r3k1/pb3p1p/1pqBp1p1/4P3/3b4/2P2P2/PR1N2PP/2RQ3K w - - bm Ne4; c0 \"Ne4=10, Qe2=3, Rbb1=1, Rbc2=1\"; id \"STS(v12.0) Center Control.077\"; cem -0.21; cee -0.50; ce -0.16;");
    pos.add(
        "r2q1rk1/pp3pbp/3P2p1/4nb2/2p2N2/4B1P1/PP3PBP/R2Q1RK1 w - - bm Qd5; c0 \"Qd5=10, b3=2, Rc1=2, Re1=1\"; id \"STS(v12.0) Center Control.078\"; cem 0.73; cee 1.18; ce 0.83;");
    pos.add(
        "r2q3r/1p3pk1/2b1p1p1/2R1P2p/p2P1P1R/P1Q1N1P1/1P3K2/8 b - - bm Be4; c0 \"Be4=10, Rc8=4\"; id \"STS(v12.0) Center Control.079\"; cem -1.09; cee -0.54; ce -1.01;");
    pos.add(
        "r2qk2r/3nbpp1/2bBp2p/p3P3/1p6/8/PPPQBPPP/1NKR3R w kq - bm Qd4; c0 \"Qd4=10, f4=5, Qe3=7, Rhg1=5\"; id \"STS(v12.0) Center Control.080\"; cem -0.21; cee -0.19; ce -0.11;");
    pos.add(
        "r2r2k1/p2n1p2/4q2p/3p2p1/1PpB4/P1NnPP2/2Q3PP/R2R2K1 b - - bm N7e5; c0 \"N7e5=10, a5=6, a6=6, Nb8=5\"; id \"STS(v12.0) Center Control.081\"; cem 0.63; cee 0.22; ce 0.47;");
    pos.add(
        "r2r2k1/pN3p1p/2n1pp2/4q3/2P1P3/1Q6/P4PPP/R4RK1 b - - bm Rd4; c0 \"Rd4=10, Rd2=6, Rd7=7, Rdb8=6\"; id \"STS(v12.0) Center Control.082\"; cem -0.03; cee 0.48; ce 0.03;");
    pos.add(
        "r3k2r/2qb1ppp/p3p3/2PpP3/2p2P2/P1P5/4B1PP/1R1Q1RK1 w kq - bm Qd4; c0 \"Qd4=10, c6=7, Rb2=7, Rb6=7\"; id \"STS(v12.0) Center Control.083\"; cem 0.96; cee 0.79; ce 1.00;");
    pos.add(
        "r3kb1r/3n1ppp/p1bPp3/1q6/Npp1BB2/8/PP3PPP/2RQR1K1 w kq - bm Qd4; c0 \"Qd4=10, b3=2, Bxc6=4, Qc2=3, Qf3=7\"; id \"STS(v12.0) Center Control.084\"; cem -1.07; cee -2.58; ce -0.99;");
    pos.add(
        "r3q1k1/p3r1p1/3R3p/2p5/1p2P3/1P4P1/P1Q4P/4R1K1 b - - bm Re5; c0 \"Re5=10, Qb5=5, Qh5=5, Rc8=4\"; id \"STS(v12.0) Center Control.085\"; cem 1.92; cee 0.91; ce 1.37;");
    pos.add(
        "r3qrk1/4bpp1/Rp2pn1p/2p1N3/3P4/2P1P1B1/4QPPP/3R2K1 b - - bm Ne4; c0 \"Ne4=10, cxd4=2, Nd5=1, Rxa6=1\"; id \"STS(v12.0) Center Control.086\"; cem 1.00; cee 1.17; ce 0.93;");
    pos.add(
        "r3r1k1/1b2qpp1/p3pn1p/1pPp4/PP1Q3P/1B2PPB1/6P1/R3K2R w KQ - bm Be5; c0 \"Be5=10, axb5=3, Bd6=3, Qb2=3\"; id \"STS(v12.0) Center Control.087\"; cem 0.80; cee 2.38; ce 1.14;");
    pos.add(
        "r3r1k1/pp2qpb1/1n2b1pp/8/5B2/1P3NP1/P4PBP/R2QR1K1 w - - bm Be5; c0 \"Be5=10, Bd6=6, Nd2=6, Rc1=2\"; id \"STS(v12.0) Center Control.088\"; cem -0.03; cee -0.38; ce 0.06;");
    pos.add(
        "r3r3/2q1bp1k/2N3pp/p1p5/Pp2Q3/4P3/1P3PPP/2RR2K1 w - - bm Qd5; c0 \"Qd5=10, Nxe7=6, Qf3=5\"; id \"STS(v12.0) Center Control.089\"; cem 0.49; cee -0.05; ce 0.41;");
    pos.add(
        "r4r1k/p5p1/1qp1bp1p/8/3N4/P1Q1P3/1P4PP/2R2RK1 b - - bm Bd5; c0 \"Bd5=10, Bd7=4, Bf7=5, Bg8=5\"; id \"STS(v12.0) Center Control.090\"; cem 1.27; cee 1.07; ce 1.11;");
    pos.add(
        "r4rk1/1pp2qpb/1n2p2p/pP1p1P2/P2PPBP1/7P/3NQ3/R3R1K1 w - - bm Be5; c0 \"Be5=10, Bg3=3, Ra3=3, Rac1=2, Rec1=2, Rf1=1\"; id \"STS(v12.0) Center Control.091\"; cem 1.65; cee 1.60; ce 1.70;");
    pos.add(
        "r4rk1/2pb2b1/np1p3p/3P1p2/1PP1pP1q/4B3/2QNN1PP/1R3RK1 w - - bm Bd4; c0 \"Bd4=10, Nd4=1, Rf2=3\"; id \"STS(v12.0) Center Control.092\"; cem 0.30; cee 1.04; ce 0.41;");
    pos.add(
        "r4rk1/pb1p2pp/1q1Ppn2/2p5/2P2P2/3BB3/PP2Q1PP/R3K2R b KQ - bm Be4; c0 \"Be4=10, Ne4=6, Qxd6=6, Rab8=6\"; id \"STS(v12.0) Center Control.093\"; cem -0.38; cee 1.12; ce -0.24;");
    pos.add(
        "r5k1/3pppbp/6p1/4n3/1q2P3/4B3/P4PPP/2RQ1K1R w - - bm Qd5; c0 \"Qd5=10, h4=1, Qc2=4\"; id \"STS(v12.0) Center Control.094\"; cem -1.30; cee 1.08; ce -0.38;");
    pos.add(
        "r5k1/8/r6p/P1q1p3/5p2/2P5/6PP/R3QR1K w - - bm Qe4; c0 \"Qe4=10, Qb1=2, Rb1=2, Rd1=1\"; id \"STS(v12.0) Center Control.095\"; cem 0.20; cee 0.34; ce 0.36;");
    pos.add(
        "r5k1/p2b1pbp/6p1/2p5/1nn5/2N3P1/PP1N1P1P/R1B2RK1 b - - bm Ne5; c0 \"Ne5=10, Nb6=2, Nc2=2\"; id \"STS(v12.0) Center Control.096\"; cem 0.84; cee 1.27; ce 0.96;");
    pos.add(
        "r5r1/1pp4p/2bn1k1B/p1p1p3/5PP1/1P5P/P1P1R3/R3N1K1 b - - bm e4; c0 \"e4=10, exf4=3, Kg6=2, Nf7=2, Rae8=3\"; id \"STS(v12.0) Center Control.097\"; cem 0.95; cee -1.44; ce -0.44;");
    pos.add(
        "r6k/pp1q1pp1/1n6/1pQp4/3Pr2p/P2NP2P/5PP1/2R2RK1 w - - bm Ne5; c0 \"Ne5=10, Qb4=3, Qc7=4, Rb1=4\"; id \"STS(v12.0) Center Control.098\"; cem 0.60; cee -0.02; ce 0.50;");
    pos.add(
        "r6r/4kp2/1qbp1p1b/p3pP1N/1pB1P2p/8/PPP1Q1PP/1K2R2R b - - bm Qd4; c0 \"Qd4=10, Bg5=3, Qc5=3, Rac8=3\"; id \"STS(v12.0) Center Control.099\"; cem 1.57; cee -0.88; ce 1.09;");
    pos.add(
        "r7/4kp2/3Rp2b/2p1P3/bp3P1P/rB2N3/P1P5/1K1R4 w - - bm Bd5; c0 \"Bd5=10, Nc4=2\"; id \"STS(v12.0) Center Control.100\"; cem 2.33; cee 1.05; ce 1.64;");
    pos.add(
        "1k1r4/1p3p2/p1bq1p1p/4p3/3r1P1Q/P5P1/1PP1B2P/K2RR3 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.001\"; c0 \"e4=10, Qc5=7, Qe6=7, Qe7=7, Re8=7\"; cem -0.11; cee -0.84; ce -0.43;");
    pos.add(
        "1k2r1r1/ppp1q1b1/nn1pp3/1N3p1p/1PP2P2/PQ2B1PB/4PK1P/3R2R1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.002\"; c0 \"e5=10, Rgf8=3\"; cem 0.71; cee 1.71; ce 0.63;");
    pos.add(
        "1kr3r1/1p1nqp2/p2p4/3PpBp1/1PP5/5R1P/P3QP2/R5K1 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.003\"; c0 \"e4=10\"; cem 0.03; cee -0.72; ce -0.29;");
    pos.add(
        "1q1r2k1/3n1bp1/1p2pp2/pNn5/P3PP2/1P2QB2/6NP/3R2K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.004\"; c0 \"e5=10, Be2=5, Nd4=6, Nd6=3, Rb1=7\"; cem 0.28; cee 0.14; ce 0.34;");
    pos.add(
        "1q2rrk1/p5bp/2p1p1p1/3p4/5P2/4QBP1/PPP2R1P/1R4K1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.005\"; c0 \"e5=10, a6=1, Qc7=2, Rf7=1\"; cem -0.31; cee -0.09; ce -0.33;");
    pos.add(
        "1qr1r1k1/5pp1/1p2p2p/1Qbn3b/2R5/3P1NPP/3NPPB1/1R4K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.006\"; c0 \"e4=10, d4=2, Ne4=2, Rbc1=1\"; cem -0.09; cee 0.47; ce 0.02;");
    pos.add(
        "1qr1r3/5ppk/1p2p2p/1Qbn3b/2R5/3P1NPP/3NPPB1/1R5K w - - bm d4; id \"STS(v13.0) Pawn Play in the Center.007\"; c0 \"d4=10, e3=3, Kg1=3, Ne4=4\"; cem -0.38; cee 0.00; ce -0.27;");
    pos.add(
        "1r1r2k1/1p3pp1/pNn1b2p/3p1q2/5B2/P7/1P1Q1PP1/2R1R1K1 b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.008\"; c0 \"d4=10, Kh7=1\"; cem -1.73; cee -1.66; ce -1.76;");
    pos.add(
        "1r1r2k1/p1R2ppp/1p6/2n1p1q1/3P4/Q4PP1/PP2B2P/3R2K1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.009\"; c0 \"d5=10\"; cem 1.23; cee 1.63; ce 1.45;");
    pos.add(
        "1r1rb1k1/2q1bp1p/3ppnp1/8/pn1NPP2/2N2R1P/1PP1B1P1/2QR2BK b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.010\"; c0 \"d5=10, e5=2, Nc6=2\"; cem 1.34; cee 0.46; ce 1.24;");
    pos.add(
        "1r2r1k1/2qn1pb1/3p2pp/p1pP4/PpQ1PP2/1P4PP/1B4B1/3RR1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.011\"; c0 \"e5=10, Ba1=8, Bc1=8, Bxg7=9\"; cem 0.49; cee -0.16; ce 0.48;");
    pos.add(
        "1r2r1k1/p2bp1b1/2p1nnpp/qpP2p2/3P3P/P1N1PBPN/1BQ2P2/1R2R1K1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.012\"; c0 \"d5=10, Na2=4, Rec1=4, Red1=4\"; cem 0.53; cee 0.31; ce 0.62;");
    pos.add(
        "1r2r2k/5pp1/1n2qn1p/3p4/p1pR4/Q3PBNP/PP3PP1/3R2K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.013\"; c0 \"e4=10, Ne2=5, Nh5=4, Qc3=6\"; cem 0.58; cee 0.18; ce 0.61;");
    pos.add(
        "1r3k1r/1q2bpp1/4pn2/1p4p1/p1pPP1P1/P3P2P/1PQ4R/K1R1BB2 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.014\"; c0 \"e5=10, Bg2=8, Bg3=8, d5=6, Qe2=8\"; cem -0.51; cee -2.19; ce -0.68;");
    pos.add(
        "1r3rk1/1p1qbbp1/2n1pn1p/1Np5/2P5/2BPP1PB/2N1Q2P/1R3RK1 w - - bm d4; id \"STS(v13.0) Pawn Play in the Center.015\"; c0 \"d4=10, Bg2=1, Ne1=2\"; cem 1.03; cee 0.24; ce 1.13;");
    pos.add(
        "1r3rk1/1p3ppp/pN2bn2/P1p5/4P3/2P5/1P2B1PP/R4RK1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.016\"; c0 \"e5=10, Bf3=2, Rad1=1, Rfe1=1\"; cem 0.20; cee 0.32; ce 0.37;");
    pos.add(
        "1r4k1/1br3p1/1ppbp3/p2p1q1p/P1PP1Pp1/1P2P1P1/1Q3NBP/2RR2K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.017\"; c0 \"e4=10, cxd5=3, Nd3=6, Qa2=5, Qe2=9\"; cem -0.15; cee -0.73; ce -0.14;");
    pos.add(
        "1r6/pp1rqpbk/6p1/P2Bpb2/2P4p/1QN1P1P1/P2R1PKP/3R4 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.018\"; c0 \"e4=10, Bh6=7, h3+=7, hxg3=6, Rbd8=7, Rdd8=7\"; cem 0.27; cee 1.30; ce 0.33;");
    pos.add(
        "1rb1r1k1/2n1ppbp/pq1p2p1/1ppP4/2P1PP2/NP3B1P/P2B1QP1/2R1R1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.019\"; c0 \"e5=10, Kh1=1\"; cem 0.62; cee 0.65; ce 0.71;");
    pos.add(
        "1rb2r1k/2q1bppp/p1pppn2/P7/4P3/1PNQ4/2P1BPPP/R1B3RK b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.020\"; c0 \"d5=10\"; cem 0.49; cee 1.09; ce 0.39;");
    pos.add(
        "1rb2rk1/2q2pb1/3p1np1/1pnP2Bp/4P3/1pN2PN1/3QB1PP/1RR3K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.021\"; c0 \"e5=10, Be3=7, Kh1=7, Nxb5=7\"; cem -0.28; cee -0.88; ce -0.18;");
    pos.add(
        "1rnr2k1/2Rb2bp/3p2p1/2pPpp2/1p5P/1P1PP1P1/3B1PK1/R4BN1 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.022\"; c0 \"e4=10, Bb5=9, Be8=9, h6=8\"; cem -0.21; cee 0.75; ce 0.13;");
    pos.add(
        "1rnrbk2/2R3bp/3p2p1/1ppPpp2/7P/3PP1P1/1P1B1PK1/R2N1B2 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.023\"; c0 \"e4=10, Kf3=1\"; cem 0.87; cee 1.20; ce 1.11;");
    pos.add(
        "1rr5/p2bppkp/3p1np1/p1p5/Pq1PP3/1P2QN1P/2P1NPP1/2R1R1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.024\"; c0 \"e5=10, dxc5=4, Rcd1=1, Red1=3\"; cem 0.95; cee 0.21; ce 0.92;");
    pos.add(
        "2b1k1r1/3nppb1/2pp1npp/qp6/3PP2B/1RNB1N2/P1PQ1PPP/7K w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.025\"; c0 \"e5=10, h3=2\"; cem 0.78; cee -1.45; ce 0.63;");
    pos.add(
        "2b1r1k1/pp2rppp/2p3q1/3p4/1P1P4/P1N1PP2/5QPP/4RRK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.026\"; c0 \"e4=10, Nd1=8, Qd2=8, Qe2=7, Rc1=8\"; cem -0.74; cee 0.26; ce -0.33;");
    pos.add(
        "2br1r1k/4b1pp/pq2pn2/2p5/Pp2PN2/1B2BP2/1PQ2P1P/R5RK b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.027\"; c0 \"e5=10, a5=7, Qc6=7, Qd6=7\"; cem 1.10; cee 0.58; ce 0.99;");
    pos.add(
        "2kr4/1p3p2/p1bqpp1p/8/3r1P1Q/3B2P1/PPP4P/K2RR3 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.028\"; c0 \"e5=10, Bf3=9, f5=7, Qe7=7\"; cem 0.75; cee 0.19; ce 0.49;");
    pos.add(
        "2r1r1k1/1pqn1pp1/p2b1n1p/P2Ppb2/R7/2NNB3/1PPQBPPP/3R2K1 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.029\"; c0 \"e4=10, Bh7=6, Nf8=6, Qb8=7, Qd8=7\"; cem 0.49; cee 0.84; ce 0.39;");
    pos.add(
        "2r1r1k1/3n2pp/b1p1pb2/1pPp1p1q/p2PnB1P/P4NP1/1PQNPPB1/R2R1K2 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.030\"; c0 \"e5=10, Bb7=1, h6=1, Kh8=1\"; cem 0.20; cee 0.40; ce 0.11;");
    pos.add(
        "2r1r1k1/5npp/3q4/1QpP1p2/1p6/4PP2/1B2R1PP/2R3K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.031\"; c0 \"e4=10, h3=6, Qd3=8, Rec2=8, Ree1=7\"; cem -0.91; cee -1.54; ce -1.00;");
    pos.add(
        "2r1r1k1/qp2ppb1/p1np1npp/5b2/2P5/PPN3PP/1BN1PPBK/1RQR4 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.032\"; c0 \"e4=10, e3=2, f3=5, Kg1=2, Rd2=3, Rf1=4\"; cem -0.05; cee 0.58; ce 0.04;");
    pos.add(
        "2r2nk1/2q1pp1p/3p2p1/pr6/2P1PP2/1PR3PP/3Q2B1/3R2K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.033\"; c0 \"e5=10, cxb5=6, Qe2=7, Qe3=7, Re1=7\"; cem 2.18; cee 1.07; ce 1.94;");
    pos.add(
        "2r2rk1/pp2qpp1/2nbp2p/2Np1b2/1P1Pn3/P3PN2/3BBPPP/R1RQ2K1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.034\"; c0 \"e5=10, a6=2, Nxd2=1, Rfd8=1, Rfe8=2\"; cem -2.32; cee -0.96; ce -2.42;");
    pos.add(
        "2r3k1/4n1b1/1p1pq1p1/4p3/2r2P1P/P2NP3/1B1Q3P/4RRK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.035\"; c0 \"e4=10, fxe5=9, Nb4=9, Qg2=9\"; cem -1.68; cee -0.44; ce -1.38;");
    pos.add(
        "2r3k1/4Qp1p/3p2p1/3q4/b1p5/P1b3P1/R3PP1P/1R3BK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.036\"; c0 \"e4=10\"; cem 0.68; cee 1.34; ce 1.00;");
    pos.add(
        "2r4r/2qb1nk1/3ppp2/p1p5/P3P3/1PR3P1/3NRPK1/3Q1B2 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.037\"; c0 \"d5=10, Rh6=1, Rh7=1\"; cem -1.77; cee -1.37; ce -1.80;");
    pos.add(
        "2rbr1k1/1p1nqp1p/4bn1P/pP2p1p1/N7/P1NRPP2/1Q2BBP1/1K5R w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.038\"; c0 \"e4=10, Bg3=2, Ka1=2\"; cem -0.63; cee -0.12; ce -0.54;");
    pos.add(
        "2rq1rk1/6bp/p1npb3/1p1Npp1Q/8/2PBN3/PP3PPP/R3K2R b KQ - bm e4; id \"STS(v13.0) Pawn Play in the Center.039\"; c0 \"e4=10, b4=3, Bxd5=1, Kh8=2, Qd7=1\"; cem -1.75; cee -1.02; ce -1.83;");
    pos.add(
        "2rqr2k/pp3pp1/1nb2n2/7p/3pPP2/1P1B2NP/P5P1/RQB2R1K w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.040\"; c0 \"e5=10, Ba3=3, Bb2=1, Re1=3\"; cem -0.02; cee -0.55; ce 0.06;");
    pos.add(
        "2rrb1k1/1p3pp1/p1p4p/P5q1/2BP4/1P1RPQP1/5P2/3R1K2 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.041\"; c0 \"d5=10, Ra1=6, Rc3=6\"; cem -0.38; cee 0.50; ce -0.01;");
    pos.add(
        "3qr1k1/1p2npb1/2ppb1pp/4p3/1PP5/R1NP2P1/2NQPPBP/6K1 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.042\"; c0 \"d5=10, f5=8, h5=2, Kh7=6, Qc7=9, Qd7=2, Rf8=2\"; cem -0.09; cee 0.00; ce -0.16;");
    pos.add(
        "3r1k2/5p2/1p2q1p1/p2p4/P7/1P1Q3P/4nPP1/B2R3K b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.043\"; c0 \"d4=10, f5=8, Nf4=8, Rd7=8\"; cem 0.79; cee -0.26; ce 0.13;");
    pos.add(
        "3r1r2/1b2qpkp/p2p1np1/1pp5/4P3/P1N1QB2/1PP2PPP/3R1RK1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.044\"; c0 \"e5=10, h3=7, Qc1=7, Rfe1=7\"; cem 0.02; cee -0.30; ce 0.07;");
    pos.add(
        "3r1rk1/ppqnppb1/n1p3pp/8/N2P4/3QP1P1/PP1B1PBP/R4RK1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.045\"; c0 \"e5=10, Ndb8=8, Nf6=8, Rfe8=8\"; cem 0.54; cee 0.21; ce 0.43;");
    pos.add(
        "3r2k1/1pp1q2p/p5pb/2n1p2r/5PQ1/P1PP3P/1P5K/1BBR1R2 w - - bm d4; id \"STS(v13.0) Pawn Play in the Center.046\"; c0 \"d4=10, Ba2+=1\"; cem -0.38; cee -0.90; ce -0.37;");
    pos.add(
        "3r2rk/1q2b2p/pNb2p2/P4Qn1/1p1p4/4PP2/1P1BB2P/2R2R1K w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.047\"; c0 \"e4=10, b3=7, Bxb4=9, exd4=7, Rc4=7\"; cem -0.19; cee 0.05; ce -0.09;");
    pos.add(
        "3rr1k1/1p3p1p/2b1pqp1/p3N3/2PP4/4R1P1/P2Q1P1P/4R1K1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.048\"; c0 \"d5=10, a4=4, Rd1=5, Rd3=5\"; cem 0.79; cee 1.18; ce 1.00;");
    pos.add(
        "3rr1k1/pp4bp/2p3p1/P4p2/2qPP3/B1P4P/2P3P1/R3QRK1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.049\"; c0 \"e5=10, Bc5=1, Qg3=1, Rb1=1\"; cem -0.11; cee 0.57; ce 0.19;");
    pos.add(
        "4b3/3k1n2/2pB1p1p/1p3Pp1/1P2P1P1/8/r3BK2/3R4 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.050\"; c0 \"e5=10, Bc5+=9, Bf8+=9\"; cem -0.37; cee -1.27; ce -1.02;");
    pos.add(
        "4r1k1/1p1r1ppb/1qpp1n1p/p7/P1PRPN2/2Q2P2/1P4PP/1B1R2K1 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.051\"; c0 \"d5=10, Qa7=6, Rdd8=6, Red8=6\"; cem -0.29; cee -0.78; ce -0.47;");
    pos.add(
        "4r1k1/1r2ppnp/1q4p1/ppp5/3nP3/1P2QP2/P2R1BPP/2R2B1K b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.052\"; c0 \"e5=10, Rbb8=3, Rc7=3, Rd8=5\"; cem 0.13; cee -0.43; ce -0.06;");
    pos.add(
        "4r1k1/2qn1pb1/rn1p2pp/2p5/p1b1PPP1/R1N1B2P/1PP1NQB1/R6K b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.053\"; c0 \"d5=10, Qb8=4, Ra5=3, Ra7=3\"; cem 0.07; cee 0.63; ce -0.03;");
    pos.add(
        "4r3/2qnr1k1/4ppp1/p1p4p/p1P2P1P/3PQNP1/1P2R3/4R1K1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.054\"; c0 \"e5=10, Qb7=7, Qc6=9, Rb8=9, Rd8=4\"; cem -0.12; cee -0.94; ce -0.48;");
    pos.add(
        "4rrk1/p4p1p/1qb3p1/2pp4/8/1P3B2/P1PQ1PPP/R3R1K1 b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.055\"; c0 \"d4=10, Qb7=9, Rd8=9, Rxe1+=9\"; cem 0.22; cee 0.00; ce 0.05;");
    pos.add(
        "5k2/4pp1p/r2p2p1/8/6P1/5P2/1R3B1P/2r2BK1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.056\"; c0 \"e5=10, f6=9, Ra8=4, Rac6=9\"; cem 0.41; cee -0.51; ce -0.48;");
    pos.add(
        "5rk1/1ppqnr2/3p3p/1P4p1/3PPp2/5P2/P2QN2P/2R3RK w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.057\"; c0 \"d5=10\"; cem -0.53; cee 0.57; ce -0.09;");
    pos.add(
        "5rk1/1r2bpp1/pp2qn1p/2p5/P7/2N1PPP1/1PQB2KP/2RR4 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.058\"; c0 \"e4=10, b3=1, Ne2=2\"; cem -0.38; cee 0.56; ce -0.13;");
    pos.add(
        "5rk1/6p1/2pp4/4q1p1/1PP1PnP1/4Q2P/R7/5BK1 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.059\"; c0 \"d5=10, Kh8=5\"; cem 0.27; cee 0.71; ce 0.41;");
    pos.add(
        "5rk1/rbqnbppp/1p2pn2/2p5/4P3/P1P2NPP/1B1N1P2/R2QRBK1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.060\"; c0 \"e5=10, Bb5=2, c4=1, Qc2=2\"; cem -0.65; cee -0.09; ce -0.56;");
    pos.add(
        "r1b1kb1r/1p1ppppp/p2q2n1/2pN3Q/8/8/PPPP1PPP/R1B1RBK1 w kq - bm d4; id \"STS(v13.0) Pawn Play in the Center.061\"; c0 \"d4=10, b3=2, c4=3, g3=2, Ne3=1\"; cem 0.82; cee -0.39; ce 0.89;");
    pos.add(
        "r1b1r1k1/4q1pp/1bp1pp2/np6/3PN3/3NP1PB/R1Q2P1P/3R2K1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.062\"; c0 \"e5=10, Kh8=2, Ra7=2, Rd8=2\"; cem -0.33; cee -0.35; ce -0.43;");
    pos.add(
        "r1b1r1k1/ppq2pp1/2n1pn1p/8/2P5/PN4P1/1BP2PBP/R2Q1RK1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.063\"; c0 \"e5=10, a5=2, Rb8=2\"; cem 0.66; cee 0.97; ce 0.57;");
    pos.add(
        "r1b1r3/pp1nq1kp/2pp2p1/5p2/1PP2P2/1Q2P1PP/P2N2B1/3R1RK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.064\"; c0 \"e4=10, Qb2+=2, Qc3+=3, Rfe1=4\"; cem 0.44; cee 0.12; ce 0.49;");
    pos.add(
        "r1b2rk1/1p1n1p1p/p5p1/q2p2B1/1bpP4/2N1PN1P/PPQ2PP1/2R2RK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.065\"; c0 \"e4=10, Bf4=1, Rfe1=2\"; cem 1.27; cee 0.95; ce 1.36;");
    pos.add(
        "r1b2rk1/1p3pb1/pq1Pp1p1/5n1p/2B2P2/1NP2Q2/PP1P2PP/R1B1K2R b KQ - bm e5; id \"STS(v13.0) Pawn Play in the Center.066\"; c0 \"e5=10, a5=7, Bd7=7, Nxd6=7, Qxd6=7\"; cem 0.71; cee 1.86; ce 0.62;");
    pos.add(
        "r1b2rk1/p1B1bpp1/q3pn1p/3p4/8/2N3PP/PPQ1PPB1/R3R1K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.067\"; c0 \"e4=10, Be5=2, Rac1=2, Rad1=2\"; cem -0.03; cee 0.37; ce 0.07;");
    pos.add(
        "r1b3k1/1p1n1qpp/2p1p3/3p1r2/2PP4/1PR5/2QNPPBP/5RK1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.068\"; c0 \"e4=10, b4=1, Rf3=1\"; cem 0.04; cee 0.17; ce 0.15;");
    pos.add(
        "r1br2k1/1p3ppp/p1p5/P5q1/1bBP4/1P2P3/1BQ2PP1/R2R2K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.069\"; c0 \"e4=10, Bc3=7, Be2=7, Qe2=7\"; cem 0.24; cee 0.58; ce 0.38;");
    pos.add(
        "r1q1nrk1/pp2ppbp/2n3p1/8/Q3P3/N5P1/PB3PBP/3RR1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.070\"; c0 \"e5=10, Bc1=8, Qb3=8, Qc2=8\"; cem -0.66; cee 0.00; ce -0.55;");
    pos.add(
        "r1r1q1k1/1n1b2bp/p4pp1/1p6/2pNP2B/Q1P5/P1B2PPP/3RR1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.071\"; c0 \"e5=10, Bg3=5, h3=3, Qc1=4, Re2=2\"; cem 0.87; cee -0.06; ce 0.95;");
    pos.add(
        "r1r3k1/1pp2ppp/2np1n2/1N6/p1PP4/Q3RNPq/PP3P1P/2R3K1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.072\"; c0 \"d5=10, Rce1=4, Re2=3, Ree1=2\"; cem 0.14; cee 0.14; ce 0.24;");
    pos.add(
        "r2q1kr1/1b2np2/p2p1p1p/4p3/2N1P3/3B4/PP3PPP/1R1Q1RK1 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.073\"; c0 \"d5=10\"; cem 1.46; cee 0.36; ce 1.18;");
    pos.add(
        "r2q1rk1/1p1n1ppp/6b1/n2B4/Ppp2NP1/4PP2/1P4KP/R1BQ1R2 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.074\"; c0 \"e4=10, Bd2=4, h4=4, Nxg6=3, Qe2=2\"; cem -0.87; cee -0.11; ce -0.75;");
    pos.add(
        "r2q1rk1/4nbbp/p2p4/1p3p1Q/4pN2/1BP1N3/PP3PPP/R3K2R b KQ - bm d5; id \"STS(v13.0) Pawn Play in the Center.075\"; c0 \"d5=10, b4=9, Be5=7, Qe8=7\"; cem 0.77; cee 0.88; ce 0.67;");
    pos.add(
        "r2qr1k1/1b3pb1/p2p2p1/8/2BNn1P1/2p1B3/P1P5/1NKRQR2 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.076\"; c0 \"d5=10\"; cem 1.87; cee 1.95; ce 1.77;");
    pos.add(
        "r2r1k2/4q1p1/2b2p1p/ppP1pQ2/8/1P2PB2/5PPP/R1R3K1 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.077\"; c0 \"e4=10\"; cem 0.84; cee -0.44; ce 0.35;");
    pos.add(
        "r2r2k1/1b2qppp/p3pn2/R7/2N5/1P1BP3/2Q2PPP/R5K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.078\"; c0 \"e4=10, f3=8, Ne5=2, Rd1=2\"; cem 0.12; cee 0.02; ce 0.20;");
    pos.add(
        "r2r2k1/1bq2p1p/p3nnpQ/P2pp3/8/2PNN3/1P3PPP/R2R1BK1 b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.079\"; c0 \"d4=10, Ne8=1, Nh5=9, Rab8=6, Rac8=9, Rd7=1\"; cem -0.51; cee -0.23; ce -0.60;");
    pos.add(
        "r2r2k1/6bp/1P2q1p1/4pp2/p1N5/2Q5/PPR3PP/4R2K b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.080\"; c0 \"e4=10, Qc6=6, Rab8=2, Rd7=4\"; cem 0.86; cee 1.39; ce 0.92;");
    pos.add(
        "r2r2k1/p1q3pp/1p1npp2/2p5/P1PP4/B3PP2/6PP/2RQ1RK1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.081\"; c0 \"d5=10, dxc5=8, Qb3=8, Re1=7\"; cem -0.43; cee -0.88; ce -0.47;");
    pos.add(
        "r2r2k1/pbq2ppp/1p3n2/4p3/2PP4/PB1Q4/1B3PPP/R4RK1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.082\"; c0 \"d5=10, f3=1, Rfd1=1\"; cem -0.82; cee -0.22; ce -0.63;");
    pos.add(
        "r2r2k1/pp2ppbp/2q3p1/2pnP3/2Q5/1P4P1/PB2PPNP/R2R2K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.083\"; c0 \"e4=10, a4=3, f4=2, Ne1=2, Rac1=8\"; cem 0.46; cee 0.16; ce 0.50;");
    pos.add(
        "r2r2k1/ppq1p2p/1np1p1p1/2B5/P7/3P3P/1P2QPP1/R3R1K1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.084\"; c0 \"e5=10, Qc8=7, Qd7=7, Rf8=7\"; cem 1.16; cee 0.41; ce 0.84;");
    pos.add(
        "r2r2k1/ppq2pp1/2p1pnbp/8/3P3P/1QP2P2/P3P2P/R1B2BRK w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.085\"; c0 \"e4=10, Bd2=4, Be3=4, Rg2=4\"; cem -1.79; cee -1.22; ce -1.61;");
    pos.add(
        "r2r2k1/ppq2pp1/5nnp/1B3b2/2PPp3/P6P/1B1NQPP1/2R2RK1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.086\"; c0 \"d5=10, Ba1=1, Ba4=2, Qe3=5\"; cem 0.52; cee 0.48; ce 0.62;");
    pos.add(
        "r2r3k/p2qppbp/1pn2np1/2p5/2P2P2/B1N1PN2/P2PQ1PP/3R1R1K w - - bm d4; id \"STS(v13.0) Pawn Play in the Center.087\"; c0 \"d4=10, d3=1\"; cem -0.68; cee -0.81; ce -0.58;");
    pos.add(
        "r2r4/2q1bp1k/1nb1p1pp/p1ppP3/P1P2P2/1P3BPP/1N2QB2/2RR2K1 b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.088\"; c0 \"d4=10, dxc4=2, Rab8=1\"; cem 0.66; cee -0.33; ce 0.55;");
    pos.add(
        "r3kb1r/1q1n2p1/2bpp1Pp/pN3p2/1p1BP2P/P2B1P2/1PP5/2KR1Q1R b kq - bm e5; id \"STS(v13.0) Pawn Play in the Center.089\"; c0 \"e5=10\"; cem 1.43; cee 0.91; ce 1.33;");
    pos.add(
        "r3r1k1/1p2bppp/nqp2n2/p5B1/2bPP3/P1N3P1/1P2N1BP/R1Q2RK1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.090\"; c0 \"e5=10, Bf3=7, Kh1=7, Rf5=7\"; cem 0.86; cee 0.53; ce 0.95;");
    pos.add(
        "r3r1k1/1p2qpp1/1bp2n1p/2n1pP2/p5P1/B6P/PPPNQPB1/R2R2K1 b - - bm e4; id \"STS(v13.0) Pawn Play in the Center.091\"; c0 \"e4=10, Ba7=3, Bc7=6, Nd5=5, Qc7=5\"; cem 0.40; cee 0.20; ce 0.30;");
    pos.add(
        "r3r1k1/4npbp/bqnpp1p1/2p3P1/1p2PP2/3PN3/1PPQNBBP/1R3RK1 b - - bm d5; id \"STS(v13.0) Pawn Play in the Center.092\"; c0 \"d5=10\"; cem -0.34; cee 0.28; ce -0.43;");
    pos.add(
        "r3r1k1/5pp1/1qp3bp/p2pP3/8/P1Q3N1/1P3PPP/2R1R1K1 b - - bm d4; id \"STS(v13.0) Pawn Play in the Center.093\"; c0 \"d4=10\"; cem -0.53; cee -0.43; ce -0.60;");
    pos.add(
        "r3r1k1/pp4pp/2pnqpn1/3p1b2/3P4/P1NBPN2/1PQ2PPP/2R1R1K1 w - - bm e4; id \"STS(v13.0) Pawn Play in the Center.094\"; c0 \"e4=10\"; cem -0.14; cee 0.01; ce -0.04;");
    pos.add(
        "r4r1k/1bq3p1/pp1bpn1p/2pp4/3P3Q/1NP1B3/PP2BPPP/R4RK1 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.095\"; c0 \"e5=10, a5=5, Bc8=3, Kg8=3, Rac8=3, Rf7=2\"; cem -0.73; cee -0.84; ce -0.82;");
    pos.add(
        "r4rk1/1p1qppbp/1n1p2p1/1PpP3n/p3P3/2NQ1N1P/1PPB1PP1/R3R1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.096\"; c0 \"e5=10, Bg5=3, Ra2=3, Ra3=2\"; cem 0.90; cee 1.23; ce 1.00;");
    pos.add(
        "r4rk1/2p1bppp/p2p1n2/1p6/3PP3/1QN1BP1q/PP3P1P/2R1R1K1 w - - bm e5; id \"STS(v13.0) Pawn Play in the Center.097\"; c0 \"e5=10, Bf4=8, Nd5=7, Qd1=7\"; cem -0.40; cee -0.10; ce -0.25;");
    pos.add(
        "r4rk1/3qbppp/p1np1n2/1pp4b/3PP3/PB2BN1P/1P1N1PP1/RQ2R1K1 w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.098\"; c0 \"d5=10, Bc2=1\"; cem 0.88; cee 0.37; ce 0.98;");
    pos.add(
        "r4rk1/pbp5/1p1p1n1p/5Bp1/P1PP4/4q3/1P1N2PP/2RQ1R1K w - - bm d5; id \"STS(v13.0) Pawn Play in the Center.099\"; c0 \"d5=10, Bb1=4, Nf3=1\"; cem 1.02; cee 0.13; ce 0.97;");
    pos.add(
        "rq3k2/1p3bp1/2p1pp1p/2P5/1P2PPP1/4Q2P/6BK/3R4 b - - bm e5; id \"STS(v13.0) Pawn Play in the Center.100\"; c0 \"e5=10, Kg8=8, Qa7=8, Qc7=8\"; cem 0.38; cee 0.77; ce 0.49;");
    pos.add(
        "2rr3k/pp3pp1/1nnqbN1p/3pN3/2pP4/2P3Q1/PPB4P/R4RK1 w - - bm Qg6; id \"WAC.001\"; cem 2.83; cee -1.38; ce 2.82;");
    pos.add(
        "8/7p/5k2/5p2/p1p2P2/Pr1pPK2/1P1R3P/8 b - - bm Rxb2; id \"WAC.002\"; cem -0.96; cee -1.93; ce -2.02;");
    pos.add(
        "5rk1/1ppb3p/p1pb4/6q1/3P1p1r/2P1R2P/PP1BQ1P1/5RKN w - - bm Rg3; id \"WAC.003\"; cem -1.89; cee -1.40; ce -1.72;");
    pos.add(
        "r1bq2rk/pp3pbp/2p1p1pQ/7P/3P4/2PB1N2/PP3PPR/2KR4 w - - bm Qxh7+; id \"WAC.004\"; cem -0.36; cee 0.59; ce -0.12;");
    pos.add(
        "5k2/6pp/p1qN4/1p1p4/3P4/2PKP2Q/PP3r2/3R4 b - - bm Qc4+; id \"WAC.005\"; cem 2.60; cee 4.15; ce 3.45;");
    pos.add(
        "7k/p7/1R5K/6r1/6p1/6P1/8/8 w - - bm Rb7; id \"WAC.006\"; cem -1.80; cee -0.04; ce 0.06;");
    pos.add(
        "rnbqkb1r/pppp1ppp/8/4P3/6n1/7P/PPPNPPP1/R1BQKBNR b KQkq - bm Ne3; id \"WAC.007\"; cem 2.09; cee 1.71; ce 1.99;");
    pos.add(
        "r4q1k/p2bR1rp/2p2Q1N/5p2/5p2/2P5/PP3PPP/R5K1 w - - bm Rf7; id \"WAC.008\"; cem 9.47; cee 4.64; ce 8.09;");
    pos.add(
        "3q1rk1/p4pp1/2pb3p/3p4/6Pr/1PNQ4/P1PB1PP1/4RRK1 b - - bm Bh2+; id \"WAC.009\"; cem 2.43; cee 4.64; ce 2.85;");
    pos.add(
        "2br2k1/2q3rn/p2NppQ1/2p1P3/Pp5R/4P3/1P3PPP/3R2K1 w - - bm Rxh7; id \"WAC.010\"; cem 6.03; cee -0.07; ce 4.70;");
    pos.add(
        "r1b1kb1r/3q1ppp/pBp1pn2/8/Np3P2/5B2/PPP3PP/R2Q1RK1 w kq - bm Bxc6; id \"WAC.011\"; cem 1.08; cee -0.21; ce 1.15;");
    pos.add(
        "4k1r1/2p3r1/1pR1p3/3pP2p/3P2qP/P4N2/1PQ4P/5R1K b - - bm Qxf3+; id \"WAC.012\"; cem 4.57; cee 5.49; ce 4.82;");
    pos.add(
        "5rk1/pp4p1/2n1p2p/2Npq3/2p5/6P1/P3P1BP/R4Q1K w - - bm Qxf8+; id \"WAC.013\"; cem -1.19; cee -1.32; ce -1.15;");
    pos.add(
        "r2rb1k1/pp1q1p1p/2n1p1p1/2bp4/5P2/PP1BPR1Q/1BPN2PP/R5K1 w - - bm Qxh7+; id \"WAC.014\"; cem 3.30; cee 0.30; ce 3.35;");
    pos.add(
        "1R6/1brk2p1/4p2p/p1P1Pp2/P7/6P1/1P4P1/2R3K1 w - - bm Rxb7; id \"WAC.015\"; cem 4.48; cee 3.76; ce 3.90;");
    pos.add(
        "r4rk1/ppp2ppp/2n5/2bqp3/8/P2PB3/1PP1NPPP/R2Q1RK1 w - - bm Nc3; id \"WAC.016\"; cem 0.68; cee 0.39; ce 0.73;");
    pos.add(
        "1k5r/pppbn1pp/4q1r1/1P3p2/2NPp3/1QP5/P4PPP/R1B1R1K1 w - - bm Ne5; id \"WAC.017\"; cem -0.18; cee -1.37; ce -0.25;");
    pos.add("R7/P4k2/8/8/8/8/r7/6K1 w - - bm Rh8; id \"WAC.018\"; cem 1.58; cee 1.23; ce 1.00;");
    pos.add(
        "r1b2rk1/ppbn1ppp/4p3/1QP4q/3P4/N4N2/5PPP/R1B2RK1 w - - bm c6; id \"WAC.019\"; cem -1.36; cee -1.60; ce -1.27;");
    pos.add(
        "r2qkb1r/1ppb1ppp/p7/4p3/P1Q1P3/2P5/5PPP/R1B2KNR b kq - bm Bb5; id \"WAC.020\"; cem -2.55; cee -1.93; ce -2.54;");
    pos.add(
        "5rk1/1b3p1p/pp3p2/3n1N2/1P6/P1qB1PP1/3Q3P/4R1K1 w - - bm Qh6; id \"WAC.021\"; cem 3.49; cee 1.79; ce 2.92;");
    pos.add(
        "r1bqk2r/ppp1nppp/4p3/n5N1/2BPp3/P1P5/2P2PPP/R1BQK2R w KQkq - bm Ba2 Nxf7; id \"WAC.022\"; cem -0.11; cee -0.80; ce -0.02;");
    pos.add(
        "r3nrk1/2p2p1p/p1p1b1p1/2NpPq2/3R4/P1N1Q3/1PP2PPP/4R1K1 w - - bm g4; id \"WAC.023\"; cem 3.06; cee 3.16; ce 3.17;");
    pos.add(
        "6k1/1b1nqpbp/pp4p1/5P2/1PN5/4Q3/P5PP/1B2B1K1 b - - bm Bd4; id \"WAC.024\"; cem -0.73; cee -0.12; ce -0.54;");
    pos.add(
        "3R1rk1/8/5Qpp/2p5/2P1p1q1/P3P3/1P2PK2/8 b - - bm Qh4+; id \"WAC.025\"; cem 0.94; cee 1.25; ce 1.06;");
    pos.add(
        "3r2k1/1p1b1pp1/pq5p/8/3NR3/2PQ3P/PP3PP1/6K1 b - - bm Bf5; id \"WAC.026\"; cem 1.06; cee 0.95; ce 0.90;");
    pos.add(
        "7k/pp4np/2p3p1/3pN1q1/3P4/Q7/1r3rPP/2R2RK1 w - - bm Qf8+; id \"WAC.027\"; cem -10.04; cee -3.90; ce -8.02;");
    pos.add(
        "1r1r2k1/4pp1p/2p1b1p1/p3R3/RqBP4/4P3/1PQ2PPP/6K1 b - - bm Qe1+; id \"WAC.028\"; cem -0.60; cee -0.82; ce -0.76;");
    pos.add(
        "r2q2k1/pp1rbppp/4pn2/2P5/1P3B2/6P1/P3QPBP/1R3RK1 w - - bm c6; id \"WAC.029\"; cem 0.94; cee 1.24; ce 1.08;");
    pos.add(
        "1r3r2/4q1kp/b1pp2p1/5p2/pPn1N3/6P1/P3PPBP/2QRR1K1 w - - bm Nxd6; id \"WAC.030\"; cem 0.48; cee -0.91; ce 0.35;");
    pos.add(
        "rb3qk1/pQ3ppp/4p3/3P4/8/1P3N2/1P3PPP/3R2K1 w - - bm Qxa8 d6 dxe6 g3; id \"WAC.031\"; cem 1.27; cee 2.03; ce 1.77;");
    pos.add(
        "6k1/p4p1p/1p3np1/2q5/4p3/4P1N1/PP3PPP/3Q2K1 w - - bm Qd8+; id \"WAC.032\"; cem 0.21; cee 0.00; ce 0.14;");
    pos.add(
        "8/p1q2pkp/2Pr2p1/8/P3Q3/6P1/5P1P/2R3K1 w - - bm Qe5+ Qf4; id \"WAC.033\"; cem 2.15; cee 1.72; ce 1.95;");
    pos.add(
        "7k/1b1r2p1/p6p/1p2qN2/3bP3/3Q4/P5PP/1B1R3K b - - bm Bg1; id \"WAC.034\"; cem -2.08; cee -1.24; ce -1.85;");
    pos.add(
        "r3r2k/2R3pp/pp1q1p2/8/3P3R/7P/PP3PP1/3Q2K1 w - - bm Rxh7+; id \"WAC.035\"; cem 2.15; cee 1.63; ce 2.01;");
    pos.add(
        "3r4/2p1rk2/1pQq1pp1/7p/1P1P4/P4P2/6PP/R1R3K1 b - - bm Re1+; id \"WAC.036\"; cem 0.06; cee -0.08; ce -0.10;");
    pos.add(
        "2r5/2rk2pp/1pn1pb2/pN1p4/P2P4/1N2B3/nPR1KPPP/3R4 b - - bm Nxd4+; id \"WAC.037\"; cem 0.43; cee 0.52; ce 0.38;");
    pos.add(
        "4k3/p4prp/1p6/2b5/8/2Q3P1/P2R1PKP/4q3 w - - bm Qd3 Rd8+; id \"WAC.038\"; cem -7.29; cee -4.17; ce -5.32;");
    pos.add(
        "r1br2k1/pp2bppp/2nppn2/8/2P1PB2/2N2P2/PqN1B1PP/R2Q1R1K w - - bm Na4; id \"WAC.039\"; cem -1.00; cee -0.50; ce -0.91;");
    pos.add(
        "3r1r1k/1p4pp/p4p2/8/1PQR4/6Pq/P3PP2/2R3K1 b - - bm Rc8; id \"WAC.040\"; cem -0.94; cee 0.22; ce -0.52;");
    pos.add(
        "1k6/5RP1/1P6/1K6/6r1/8/8/8 w - - bm Ka5 Kc5 b7; id \"WAC.041\"; cem 3.88; cee 5.18; ce 4.46;");
    pos.add(
        "r1b1r1k1/pp1n1pbp/1qp3p1/3p4/1B1P4/Q3PN2/PP2BPPP/R4RK1 w - - bm Ba5; id \"WAC.042\"; cem 0.32; cee 0.28; ce 0.41;");
    pos.add(
        "r2q3k/p2P3p/1p3p2/3QP1r1/8/B7/P5PP/2R3K1 w - - bm Be7 Qxa8; id \"WAC.043\"; cem 2.64; cee 2.91; ce 2.87;");
    pos.add(
        "3rb1k1/pq3pbp/4n1p1/3p4/2N5/2P2QB1/PP3PPP/1B1R2K1 b - - bm dxc4; id \"WAC.044\"; cem 0.73; cee 0.07; ce 0.47;");
    pos.add(
        "7k/2p1b1pp/8/1p2P3/1P3r2/2P3Q1/1P5P/R4qBK b - - bm Qxa1; id \"WAC.045\"; cem 0.55; cee 0.73; ce 0.55;");
    pos.add(
        "r1bqr1k1/pp1nb1p1/4p2p/3p1p2/3P4/P1N1PNP1/1PQ2PP1/3RKB1R w K - bm Nb5; id \"WAC.046\"; cem 0.06; cee 1.51; ce 0.19;");
    pos.add(
        "r1b2rk1/pp2bppp/2n1pn2/q5B1/2BP4/2N2N2/PP2QPPP/2R2RK1 b - - bm Nxd4; id \"WAC.047\"; cem 0.36; cee 0.29; ce 0.26;");
    pos.add(
        "1rbq1rk1/p1p1bppp/2p2n2/8/Q1BP4/2N5/PP3PPP/R1B2RK1 b - - bm Rb4; id \"WAC.048\"; cem 0.39; cee 0.30; ce 0.29;");
    pos.add(
        "2b3k1/4rrpp/p2p4/2pP2RQ/1pP1Pp1N/1P3P1P/1q6/6RK w - - bm Qxh7+; id \"WAC.049\"; cem -1.50; cee -1.97; ce -1.54;");
    pos.add(
        "k4r2/1R4pb/1pQp1n1p/3P4/5p1P/3P2P1/r1q1R2K/8 w - - bm Rxb6+; id \"WAC.050\"; cem 0.00; cee 0.00; ce -9.42;");
    pos.add(
        "r1bq1r2/pp4k1/4p2p/3pPp1Q/3N1R1P/2PB4/6P1/6K1 w - - bm Rg4+; id \"WAC.051\"; cem -1.00; cee -2.93; ce -1.57;");
    pos.add(
        "r1k5/1p3q2/1Qpb4/3N1p2/5Pp1/3P2Pp/PPPK3P/4R3 w - - bm Re7 c4; id \"WAC.052\"; cem 1.67; cee 1.55; ce 1.70;");
    pos.add(
        "6k1/6p1/p7/3Pn3/5p2/4rBqP/P4RP1/5QK1 b - - bm Re1; id \"WAC.053\"; cem -1.38; cee 0.21; ce -0.63;");
    pos.add(
        "r3kr2/1pp4p/1p1p4/7q/4P1n1/2PP2Q1/PP4P1/R1BB2K1 b q - bm Qh1+; id \"WAC.054\"; cem -5.78; cee -2.00; ce -4.58;");
    pos.add(
        "r3r1k1/pp1q1pp1/4b1p1/3p2B1/3Q1R2/8/PPP3PP/4R1K1 w - - bm Qxg7+; id \"WAC.055\"; cem 0.36; cee -0.30; ce 0.27;");
    pos.add(
        "r1bqk2r/pppp1ppp/5n2/2b1n3/4P3/1BP3Q1/PP3PPP/RNB1K1NR b KQkq - bm Bxf2+; id \"WAC.056\"; cem 0.80; cee -0.44; ce 0.70;");
    pos.add(
        "r3q1kr/ppp5/3p2pQ/8/3PP1b1/5R2/PPP3P1/5RK1 w - - bm Rf8+; id \"WAC.057\"; cem -0.24; cee -3.16; ce -1.24;");
    pos.add(
        "8/8/2R5/1p2qp1k/1P2r3/2PQ2P1/5K2/8 w - - bm Qd1+; id \"WAC.058\"; cem 4.48; cee 1.46; ce 2.47;");
    pos.add(
        "r1b2rk1/2p1qnbp/p1pp2p1/5p2/2PQP3/1PN2N1P/PB3PP1/3R1RK1 w - - bm Nd5; id \"WAC.059\"; cem -0.60; cee -0.45; ce -0.50;");
    pos.add(
        "rn1qr1k1/1p2np2/2p3p1/8/1pPb4/7Q/PB1P1PP1/2KR1B1R w - - bm Qh8+; id \"WAC.060\"; cem -2.04; cee -4.84; ce -2.21;");
    pos.add(
        "3qrbk1/ppp1r2n/3pP2p/3P4/2P4P/1P3Q2/PB6/R4R1K w - - bm Qf7+; id \"WAC.061\"; cem 10.08; cee 0.38; ce 7.90;");
    pos.add(
        "6r1/3Pn1qk/p1p1P1rp/2Q2p2/2P5/1P4P1/P3R2P/5RK1 b - - bm Rxg3+; id \"WAC.062\"; cem 1.86; cee 3.67; ce 2.45;");
    pos.add(
        "r1brnbk1/ppq2pp1/4p2p/4N3/3P4/P1PB1Q2/3B1PPP/R3R1K1 w - - bm Nxf7; id \"WAC.063\"; cem 4.18; cee 2.22; ce 4.24;");
    pos.add(
        "8/6pp/3q1p2/3n1k2/1P6/3NQ2P/5PP1/6K1 w - - bm g4+; id \"WAC.064\"; cem 6.99; cee 0.44; ce 2.07;");
    pos.add(
        "1r1r1qk1/p2n1p1p/bp1Pn1pQ/2pNp3/2P2P1N/1P5B/P6P/3R1RK1 w - - bm Ne7+; id \"WAC.065\"; cem 3.28; cee -0.33; ce 3.29;");
    pos.add(
        "1k1r2r1/ppq5/1bp4p/3pQ3/8/2P2N2/PP4P1/R4R1K b - - bm Qxe5; id \"WAC.066\"; cem -3.42; cee -1.73; ce -3.00;");
    pos.add(
        "3r2k1/p2q4/1p4p1/3rRp1p/5P1P/6PK/P3R3/3Q4 w - - bm Rxd5; id \"WAC.067\"; cem -1.89; cee -1.07; ce -1.43;");
    pos.add(
        "6k1/5ppp/1q6/2b5/8/2R1pPP1/1P2Q2P/7K w - - bm Qxe3; id \"WAC.068\"; cem 0.99; cee 1.70; ce 1.60;");
    pos.add(
        "2k5/pppr4/4R3/4Q3/2pp2q1/8/PPP2PPP/6K1 w - - bm f3 h3; id \"WAC.069\"; cem 3.44; cee 1.78; ce 2.41;");
    pos.add(
        "2kr3r/pppq1ppp/3p1n2/bQ2p3/1n1PP3/1PN1BN1P/1PP2PP1/2KR3R b - - bm Na2+; id \"WAC.070\"; cem 0.93; cee 0.80; ce 0.83;");
    pos.add(
        "2kr3r/pp1q1ppp/5n2/1Nb5/2Pp1B2/7Q/P4PPP/1R3RK1 w - - bm Nxa7+; id \"WAC.071\"; cem 2.89; cee -1.09; ce 2.33;");
    pos.add(
        "r3r1k1/pp1n1ppp/2p5/4Pb2/2B2P2/B1P5/P5PP/R2R2K1 w - - bm e6; id \"WAC.072\"; cem 0.31; cee 0.17; ce 0.32;");
    pos.add(
        "r1q3rk/1ppbb1p1/4Np1p/p3pP2/P3P3/2N4R/1PP1Q1PP/3R2K1 w - - bm Qd2; id \"WAC.073\"; cem 1.62; cee 1.55; ce 1.71;");
    pos.add(
        "5r1k/pp4pp/2p5/2b1P3/4Pq2/1PB1p3/P3Q1PP/3N2K1 b - - bm Qf1+; id \"WAC.074\"; cem -4.54; cee -2.98; ce -3.75;");
    pos.add(
        "r3r1k1/pppq1ppp/8/8/1Q4n1/7P/PPP2PP1/RNB1R1K1 b - - bm Qd6; id \"WAC.075\"; cem 5.07; cee 3.80; ce 4.67;");
    pos.add(
        "r1b1qrk1/2p2ppp/pb1pnn2/1p2pNB1/3PP3/1BP5/PP2QPPP/RN1R2K1 w - - bm Bxf6; id \"WAC.076\"; cem 0.12; cee -0.37; ce 0.21;");
    pos.add(
        "3r2k1/ppp2ppp/6q1/b4n2/3nQB2/2p5/P4PPP/RN3RK1 b - - bm Ng3; id \"WAC.077\"; cem -5.06; cee -3.92; ce -4.91;");
    pos.add(
        "r2q3r/ppp2k2/4nbp1/5Q1p/2P1NB2/8/PP3P1P/3RR1K1 w - - bm Ng5+; id \"WAC.078\"; cem 3.48; cee -0.14; ce 2.98;");
    pos.add(
        "r3k2r/pbp2pp1/3b1n2/1p6/3P3p/1B2N1Pq/PP1PQP1P/R1B2RK1 b kq - bm Qxh2+; id \"WAC.079\"; cem -0.17; cee 0.22; ce -0.26;");
    pos.add(
        "r4rk1/p1B1bpp1/1p2pn1p/8/2PP4/3B1P2/qP2QP1P/3R1RK1 w - - bm Ra1; id \"WAC.080\"; cem 0.82; cee 0.81; ce 0.91;");
    pos.add(
        "r4rk1/1bR1bppp/4pn2/1p2N3/1P6/P3P3/4BPPP/3R2K1 b - - bm Bd6; id \"WAC.081\"; cem -1.60; cee -1.17; ce -1.46;");
    pos.add(
        "3rr1k1/pp3pp1/4b3/8/2P1B2R/6QP/P3q1P1/5R1K w - - bm Bh7+; id \"WAC.082\"; cem 4.20; cee -0.18; ce 2.96;");
    pos.add(
        "3rr1k1/ppqbRppp/2p5/8/3Q1n2/2P3N1/PPB2PPP/3R2K1 w - - bm Qxd7; id \"WAC.083\"; cem 2.44; cee 1.41; ce 2.36;");
    pos.add(
        "r2q1r1k/2p1b1pp/p1n5/1p1Q1bN1/4n3/1BP1B3/PP3PPP/R4RK1 w - - bm Qg8+; id \"WAC.084\"; cem -1.60; cee -2.72; ce -1.50;");
    pos.add(
        "kr2R3/p4r2/2pq4/2N2p1p/3P2p1/Q5P1/5P1P/5BK1 w - - bm Na6; id \"WAC.085\"; cem 6.42; cee 1.71; ce 4.53;");
    pos.add(
        "8/p7/1ppk1n2/5ppp/P1PP4/2P1K1P1/5N1P/8 b - - bm Ng4+; id \"WAC.086\"; cem -0.74; cee -0.79; ce -0.88;");
    pos.add(
        "8/p3k1p1/4r3/2ppNpp1/PP1P4/2P3KP/5P2/8 b - - bm Rxe5; id \"WAC.087\"; cem -1.46; cee -2.56; ce -2.66;");
    pos.add(
        "r6k/p1Q4p/2p1b1rq/4p3/B3P3/4P3/PPP3P1/4RRK1 b - - bm Rxg2+; id \"WAC.088\"; cem 0.68; cee 1.25; ce 0.75;");
    pos.add(
        "1r3b1k/p4rpp/4pp2/3q4/2ppbPPQ/6RK/PP5P/2B1NR2 b - - bm g5; id \"WAC.089\"; cem -6.60; cee -5.33; ce -6.50;");
    pos.add(
        "3qrrk1/1pp2pp1/1p2bn1p/5N2/2P5/P1P3B1/1P4PP/2Q1RRK1 w - - bm Nxg7; id \"WAC.090\"; cem -0.27; cee -0.42; ce -0.19;");
    pos.add(
        "2qr2k1/4b1p1/2p2p1p/1pP1p3/p2nP3/PbQNB1PP/1P3PK1/4RB2 b - - bm Be6; id \"WAC.091\"; cem -1.91; cee -0.66; ce -1.70;");
    pos.add(
        "r4rk1/1p2ppbp/p2pbnp1/q7/3BPPP1/2N2B2/PPP4P/R2Q1RK1 b - - bm Bxg4; id \"WAC.092\"; cem 0.67; cee 0.55; ce 0.57;");
    pos.add(
        "r1b1k1nr/pp3pQp/4pq2/3pn3/8/P1P5/2P2PPP/R1B1KBNR w KQkq - bm Bh6; id \"WAC.093\"; cem 1.22; cee 0.49; ce 1.30;");
    pos.add(
        "8/k7/p7/3Qp2P/n1P5/3KP3/1q6/8 b - - bm e4+; id \"WAC.094\"; cem -7.94; cee -1.54; ce -2.48;");
    pos.add(
        "2r5/1r6/4pNpk/3pP1qp/8/2P1QP2/5PK1/R7 w - - bm Ng4+; id \"WAC.095\"; cem 0.94; cee -2.30; ce -0.56;");
    pos.add(
        "r1b4k/ppp2Bb1/6Pp/3pP3/1qnP1p1Q/8/PPP3P1/1K1R3R w - - bm Qd8+ b3; id \"WAC.096\"; cem 7.61; cee 2.24; ce 6.24;");
    pos.add(
        "6k1/5p2/p5np/4B3/3P4/1PP1q3/P3r1QP/6RK w - - bm Qa8+; id \"WAC.097\"; cem 6.43; cee 3.23; ce 4.82;");
    pos.add(
        "1r3rk1/5pb1/p2p2p1/Q1n1q2p/1NP1P3/3p1P1B/PP1R3P/1K2R3 b - - bm Nxe4; id \"WAC.098\"; cem -2.40; cee -2.24; ce -2.40;");
    pos.add(
        "r1bq1r1k/1pp1Np1p/p2p2pQ/4R3/n7/8/PPPP1PPP/R1B3K1 w - - bm Rh5; id \"WAC.099\"; cem 1.15; cee 0.06; ce 1.07;");
    pos.add(
        "8/k1b5/P4p2/1Pp2p1p/K1P2P1P/8/3B4/8 w - - bm Be3 b6+; id \"WAC.100\"; cem 0.97; cee 3.03; ce 3.12;");
    pos.add(
        "5rk1/p5pp/8/8/2Pbp3/1P4P1/7P/4RN1K b - - bm Bc3; id \"WAC.101\"; cem -1.67; cee -0.92; ce -1.03;");
    pos.add(
        "2Q2n2/2R4p/1p1qpp1k/8/3P3P/3B2P1/5PK1/r7 w - - bm Qxf8+; id \"WAC.102\"; cem 1.24; cee 0.96; ce 1.18;");
    pos.add(
        "6k1/2pb1r1p/3p1PpQ/p1nPp3/1q2P3/2N2P2/PrB5/2K3RR w - - bm Qxg6+; id \"WAC.103\"; cem -2.90; cee -2.01; ce -2.66;");
    pos.add(
        "b4r1k/pq2rp2/1p1bpn1p/3PN2n/2P2P2/P2B3K/1B2Q2N/3R2R1 w - - bm Qxh5; id \"WAC.104\"; cem -0.61; cee 0.81; ce -0.51;");
    pos.add(
        "r2r2k1/pb3ppp/1p1bp3/7q/3n2nP/PP1B2P1/1B1N1P2/RQ2NRK1 b - - bm Bxg3 Qxh4; id \"WAC.105\"; cem -7.71; cee -2.28; ce -7.81;");
    pos.add(
        "4rrk1/pppb4/7p/3P2pq/3Qn3/P5P1/1PP4P/R3RNNK b - - bm Nf2+; id \"WAC.106\"; cem -5.64; cee -0.11; ce -4.79;");
    pos.add(
        "5n2/pRrk2p1/P4p1p/4p3/3N4/5P2/6PP/6K1 w - - bm Nb5; id \"WAC.107\"; cem 1.34; cee 0.23; ce 0.34;");
    pos.add(
        "r5k1/1q4pp/2p5/p1Q5/2P5/5R2/4RKPP/r7 w - - bm Qe5; id \"WAC.108\"; cem -0.61; cee -0.31; ce -0.38;");
    pos.add(
        "rn2k1nr/pbp2ppp/3q4/1p2N3/2p5/QP6/PB1PPPPP/R3KB1R b KQkq - bm c3; id \"WAC.109\"; cem 0.13; cee -0.03; ce 0.03;");
    pos.add(
        "2kr4/bp3p2/p2p2b1/P7/2q5/1N4B1/1PPQ2P1/2KR4 b - - bm Be3; id \"WAC.110\"; cem -0.19; cee 0.00; ce -0.21;");
    pos.add(
        "6k1/p5p1/5p2/2P2Q2/3pN2p/3PbK1P/7P/6q1 b - - bm Qf1+; id \"WAC.111\"; cem -5.20; cee 0.21; ce -1.20;");
    pos.add(
        "r4kr1/ppp5/4bq1b/7B/2PR1Q1p/2N3P1/PP3P1P/2K1R3 w - - bm Rxe6; id \"WAC.112\"; cem 6.75; cee 3.40; ce 6.32;");
    pos.add(
        "rnbqkb1r/1p3ppp/5N2/1p2p1B1/2P5/8/PP2PPPP/R2QKB1R b KQkq - bm Qxf6; id \"WAC.113\"; cem -0.57; cee 0.34; ce -0.65;");
    pos.add(
        "r1b1rnk1/1p4pp/p1p2p2/3pN2n/3P1PPq/2NBPR1P/PPQ5/2R3K1 w - - bm Bxh7+; id \"WAC.114\"; cem 2.11; cee 2.04; ce 2.20;");
    pos.add(
        "4N2k/5rpp/1Q6/p3q3/8/P5P1/1P3P1P/5K2 w - - bm Nd6; id \"WAC.115\"; cem -2.55; cee -0.71; ce -1.11;");
    pos.add(
        "r2r2k1/2p2ppp/p7/1p2P1n1/P6q/5P2/1PB1QP1P/R5RK b - - bm Rd2; id \"WAC.116\"; cem -1.30; cee -0.20; ce -1.06;");
    pos.add(
        "3r1rk1/q4ppp/p1Rnp3/8/1p6/1N3P2/PP3QPP/3R2K1 b - - bm Ne4; id \"WAC.117\"; cem -0.20; cee 0.00; ce -0.23;");
    pos.add(
        "r5k1/pb2rpp1/1p6/2p4q/5R2/2PB2Q1/P1P3PP/5R1K w - - bm Rh4; id \"WAC.118\"; cem 1.82; cee 0.02; ce 1.37;");
    pos.add(
        "r2qr1k1/p1p2ppp/2p5/2b5/4nPQ1/3B4/PPP3PP/R1B2R1K b - - bm Qxd3; id \"WAC.119\"; cem -0.25; cee 0.05; ce -0.30;");
    pos.add(
        "r4rk1/1bn2qnp/3p1B1Q/p2P1pP1/1pp5/5N1P/PPB2P2/2KR3R w - - bm Rhg1 g6; id \"WAC.120\"; cem 1.30; cee 0.77; ce 1.38;");
    pos.add(
        "6k1/5p1p/2bP2pb/4p3/2P5/1p1pNPPP/1P1Q1BK1/1q6 b - - bm Bxf3+; id \"WAC.121\"; cem -1.89; cee 0.37; ce -0.59;");
    pos.add(
        "1k6/ppp4p/1n2pq2/1N2Rb2/2P2Q2/8/P4KPP/3r1B2 b - - bm Rxf1+; id \"WAC.122\"; cem -2.49; cee -0.85; ce -1.94;");
    pos.add(
        "6k1/1b2rp2/1p4p1/3P4/PQ4P1/2N2q2/5P2/3R2K1 b - - bm Bxd5 Rc7 Re6; id \"WAC.123\"; cem -0.95; cee 1.18; ce 0.08;");
    pos.add(
        "6k1/3r4/2R5/P5P1/1P4p1/8/4rB2/6K1 b - - bm g3; id \"WAC.124\"; cem 0.51; cee 0.69; ce 0.55;");
    pos.add(
        "r1bqr1k1/pp3ppp/1bp5/3n4/3B4/2N2P1P/PPP1B1P1/R2Q1RK1 b - - bm Bxd4+; id \"WAC.125\"; cem -1.60; cee -0.45; ce -1.68;");
    pos.add(
        "r5r1/pQ5p/1qp2R2/2k1p3/4P3/2PP4/P1P3PP/6K1 w - - bm Rxc6+; id \"WAC.126\"; cem 1.94; cee -4.15; ce -1.38;");
    pos.add(
        "2k4r/1pr1n3/p1p1q2p/5pp1/3P1P2/P1P1P3/1R2Q1PP/1RB3K1 w - - bm Rxb7; id \"WAC.127\"; cem 2.12; cee 1.23; ce 1.94;");
    pos.add(
        "6rk/1pp2Qrp/3p1B2/1pb1p2R/3n1q2/3P4/PPP3PP/R6K w - - bm Qg6; id \"WAC.128\"; cem -0.75; cee -2.72; ce -1.11;");
    pos.add(
        "3r1r1k/1b2b1p1/1p5p/2p1Pp2/q1B2P2/4P2P/1BR1Q2K/6R1 b - - bm Bf3; id \"WAC.129\"; cem 0.43; cee -0.09; ce 0.25;");
    pos.add(
        "6k1/1pp3q1/5r2/1PPp4/3P1pP1/3Qn2P/3B4/4R1K1 b - - bm Qh6 Qh8; id \"WAC.130\"; cem -0.20; cee 1.45; ce 0.58;");
    pos.add(
        "2rq1bk1/p4p1p/1p4p1/3b4/3B1Q2/8/P4PpP/3RR1K1 w - - bm Re8; id \"WAC.131\"; cem -3.35; cee -1.46; ce -2.60;");
    pos.add(
        "4r1k1/5bpp/2p5/3pr3/8/1B3pPq/PPR2P2/2R2QK1 b - - bm Re1; id \"WAC.132\"; cem -3.73; cee -2.15; ce -3.34;");
    pos.add(
        "r1b1k2r/1pp1q2p/p1n3p1/3QPp2/8/1BP3B1/P5PP/3R1RK1 w kq - bm Bh4; id \"WAC.133\"; cem 5.39; cee 1.22; ce 4.84;");
    pos.add(
        "3r2k1/p6p/2Q3p1/4q3/2P1p3/P3Pb2/1P3P1P/2K2BR1 b - - bm Rd1+; id \"WAC.134\"; cem -5.52; cee 1.14; ce -2.07;");
    pos.add(
        "3r1r1k/N2qn1pp/1p2np2/2p5/2Q1P2N/3P4/PP4PP/3R1RK1 b - - bm Nd4; id \"WAC.135\"; cem -0.83; cee -0.90; ce -0.93;");
    pos.add(
        "6kr/1q2r1p1/1p2N1Q1/5p2/1P1p4/6R1/7P/2R3K1 w - - bm Rc8+; id \"WAC.136\"; cem 5.33; cee 2.43; ce 4.17;");
    pos.add(
        "3b1rk1/1bq3pp/5pn1/1p2rN2/2p1p3/2P1B2Q/1PB2PPP/R2R2K1 w - - bm Rd7; id \"WAC.137\"; cem 1.12; cee 0.00; ce 1.20;");
    pos.add(
        "r1bq3r/ppppR1p1/5n1k/3P4/6pP/3Q4/PP1N1PP1/5K1R w - - bm h5; id \"WAC.138\"; cem 3.97; cee -2.23; ce 2.61;");
    pos.add(
        "rnb3kr/ppp2ppp/1b6/3q4/3pN3/Q4N2/PPP2KPP/R1B1R3 w - - bm Nf6+; id \"WAC.139\"; cem -1.59; cee -0.67; ce -1.47;");
    pos.add(
        "r2b1rk1/pq4p1/4ppQP/3pB1p1/3P4/2R5/PP3PP1/5RK1 w - - bm Bc7 Rc7; id \"WAC.140\"; cem 2.60; cee 1.28; ce 2.29;");
    pos.add(
        "4r1k1/p1qr1p2/2pb1Bp1/1p5p/3P1n1R/1B3P2/PP3PK1/2Q4R w - - bm Qxf4; id \"WAC.141\"; cem -0.53; cee -1.31; ce -0.56;");
    pos.add(
        "r2q3n/ppp2pk1/3p4/5Pr1/2NP1Qp1/2P2pP1/PP3K2/4R2R w - - bm Re8 f6+; id \"WAC.142\"; cem 1.44; cee 0.58; ce 1.27;");
    pos.add(
        "5b2/pp2r1pk/2pp1pRp/4rP1N/2P1P3/1P4QP/P3q1P1/5R1K w - - bm Rxh6+; id \"WAC.143\"; cem 0.19; cee 0.39; ce 0.35;");
    pos.add(
        "r2q1rk1/pp3ppp/2p2b2/8/B2pPPb1/7P/PPP1N1P1/R2Q1RK1 b - - bm d3; id \"WAC.144\"; cem -0.56; cee 0.28; ce -0.52;");
    pos.add(
        "r1bq4/1p4kp/3p1n2/p4pB1/2pQ4/8/1P4PP/4RRK1 w - - bm Re8; id \"WAC.145\"; cem 4.45; cee 1.38; ce 3.36;");
    pos.add(
        "8/8/2Kp4/3P1B2/2P2k2/5p2/8/8 w - - bm Bc8 Bd3 Bh3; id \"WAC.146\"; cem 2.90; cee 2.21; ce 1.96;");
    pos.add(
        "r2r2k1/ppqbppbp/2n2np1/2pp4/6P1/1P1PPNNP/PBP2PB1/R2QK2R b KQ - bm Nxg4; id \"WAC.147\"; cem -0.66; cee 0.18; ce -0.75;");
    pos.add(
        "2r1k3/6pr/p1nBP3/1p3p1p/2q5/2P5/P1R4P/K2Q2R1 w - - bm Rxg7; id \"WAC.148\"; cem 3.82; cee -0.69; ce 2.54;");
    pos.add(
        "6k1/6p1/2p4p/4Pp2/4b1qP/2Br4/1P2RQPK/8 b - - bm Bxg2; id \"WAC.149\"; cem -1.28; cee 0.00; ce -0.70;");
    pos.add(
        "r3r1k1/5p2/pQ1b2pB/1p6/4p3/6P1/Pq2BP1P/2R3K1 b - - bm Ba3 Be5 Bf8 e3; c0 \"All win but e3 is best.\"; id \"WAC.150\"; cem -2.71; cee -3.59; ce -3.11;");
    pos.add(
        "8/3b2kp/4p1p1/pr1n4/N1N4P/1P4P1/1K3P2/3R4 w - - bm Nc3; id \"WAC.151\"; cem -0.37; cee 0.36; ce 0.34;");
    pos.add(
        "1br2rk1/1pqb1ppp/p3pn2/8/1P6/P1N1PN1P/1B3PP1/1QRR2K1 w - - bm Ne4; id \"WAC.152\"; cem -0.26; cee -0.43; ce -0.17;");
    pos.add(
        "2r3k1/q4ppp/p3p3/pnNp4/2rP4/2P2P2/4R1PP/2R1Q1K1 b - - bm Nxd4; id \"WAC.153\"; cem -1.12; cee -2.11; ce -1.52;");
    pos.add(
        "r1b2rk1/2p2ppp/p7/1p6/3P3q/1BP3bP/PP3QP1/RNB1R1K1 w - - bm Qxf7+; id \"WAC.154\"; cem 1.55; cee 2.87; ce 1.75;");
    pos.add(
        "5bk1/1rQ4p/5pp1/2pP4/3n1PP1/7P/1q3BB1/4R1K1 w - - bm d6; id \"WAC.155\"; cem -1.07; cee -0.12; ce -0.60;");
    pos.add(
        "r1b1qN1k/1pp3p1/p2p3n/4p1B1/8/1BP4Q/PP3KPP/8 w - - bm Qxh6+; id \"WAC.156\"; cem 2.82; cee -2.22; ce 0.75;");
    pos.add(
        "5rk1/p4ppp/2p1b3/3Nq3/4P1n1/1p1B2QP/1PPr2P1/1K2R2R w - - bm Ne7+; id \"WAC.157\"; cem -1.35; cee -1.93; ce -1.35;");
    pos.add(
        "5rk1/n1p1R1bp/p2p4/1qpP1QB1/7P/2P3P1/PP3P2/6K1 w - - bm Rxg7+; id \"WAC.158\"; cem 3.63; cee -0.72; ce 1.72;");
    pos.add(
        "r1b2r2/5P1p/ppn3pk/2p1p1Nq/1bP1PQ2/3P4/PB4BP/1R3RK1 w - - bm Ne6+; id \"WAC.159\"; cem 3.19; cee 1.60; ce 3.25;");
    pos.add(
        "qn1kr2r/1pRbb3/pP5p/P2pP1pP/3N1pQ1/3B4/3B1PP1/R5K1 w - - bm Qxd7+; id \"WAC.160\"; cem 7.58; cee 3.47; ce 7.61;");
    pos.add(
        "3r3k/3r1P1p/pp1Nn3/2pp4/7Q/6R1/Pq4PP/5RK1 w - - bm Qxd8+; id \"WAC.161\"; cem 6.81; cee 0.53; ce 4.94;");
    pos.add(
        "r3kbnr/p4ppp/2p1p3/8/Q1B3b1/2N1B3/PP3PqP/R3K2R w KQkq - bm Bd5; id \"WAC.162\"; cem -4.06; cee -1.18; ce -3.91;");
    pos.add(
        "5rk1/2p4p/2p4r/3P4/4p1b1/1Q2NqPp/PP3P1K/R4R2 b - - bm Qg2+; id \"WAC.163\"; cem -2.24; cee 0.97; ce -1.35;");
    pos.add(
        "8/6pp/4p3/1p1n4/1NbkN1P1/P4P1P/1PR3K1/r7 w - - bm Rxc4+; id \"WAC.164\"; cem 6.85; cee 0.91; ce 1.93;");
    pos.add(
        "1r5k/p1p3pp/8/8/4p3/P1P1R3/1P1Q1qr1/2KR4 w - - bm Re2; id \"WAC.165\"; cem -3.50; cee -2.90; ce -3.13;");
    pos.add(
        "r3r1k1/5pp1/p1p4p/2Pp4/8/q1NQP1BP/5PP1/4K2R b K - bm d4; id \"WAC.166\"; cem 0.31; cee 1.01; ce 0.51;");
    pos.add(
        "7Q/ppp2q2/3p2k1/P2Ppr1N/1PP5/7R/5rP1/6K1 b - - bm Rxg2+; id \"WAC.167\"; cem -1.75; cee -4.15; ce -3.02;");
    pos.add(
        "r3k2r/pb1q1p2/8/2p1pP2/4p1p1/B1P1Q1P1/P1P3K1/R4R2 b kq - bm Qd2+; id \"WAC.168\"; cem -1.55; cee -1.08; ce -1.44;");
    pos.add(
        "5rk1/1pp3bp/3p2p1/2PPp3/1P2P3/2Q1B3/4q1PP/R5K1 b - - bm Bh6; id \"WAC.169\"; cem -0.90; cee 0.00; ce -0.52;");
    pos.add(
        "5r1k/6Rp/1p2p3/p2pBp2/1qnP4/4P3/Q4PPP/6K1 w - - bm Qxc4; id \"WAC.170\"; cem 1.20; cee -1.98; ce -0.39;");
    pos.add(
        "2rq4/1b2b1kp/p3p1p1/1p1nNp2/7P/1B2B1Q1/PP3PP1/3R2K1 w - - bm Bh6+; id \"WAC.171\"; cem 1.01; cee -1.38; ce 0.52;");
    pos.add(
        "5r1k/p5pp/8/1P1pq3/P1p2nR1/Q7/5BPP/6K1 b - - bm Qe1+; id \"WAC.172\"; cem -0.88; cee -1.57; ce -1.34;");
    pos.add(
        "2r1b3/1pp1qrk1/p1n1P1p1/7R/2B1p3/4Q1P1/PP3PP1/3R2K1 w - - bm Qh6+; id \"WAC.173\"; cem 4.67; cee -0.68; ce 3.51;");
    pos.add(
        "2r2rk1/6p1/p3pq1p/1p1b1p2/3P1n2/PP3N2/3N1PPP/1Q2RR1K b - - bm Nxg2; id \"WAC.174\"; cem -1.79; cee -1.12; ce -1.77;");
    pos.add(
        "r5k1/pppb3p/2np1n2/8/3PqNpP/3Q2P1/PPP5/R4RK1 w - - bm Nh5; id \"WAC.175\"; cem -1.04; cee -1.83; ce -1.16;");
    pos.add(
        "r1bq3r/ppp2pk1/3p1pp1/8/2BbPQ2/2NP2P1/PPP4P/R4R1K b - - bm Rxh2+; id \"WAC.176\"; cem -1.66; cee -0.56; ce -1.59;");
    pos.add(
        "r1b3r1/4qk2/1nn1p1p1/3pPp1P/p4P2/1p3BQN/PKPBN3/3R3R b - - bm Qa3+; id \"WAC.177\"; cem 0.72; cee 3.57; ce 0.62;");
    pos.add(
        "3r2k1/p1rn1p1p/1p2pp2/6q1/3PQNP1/5P2/P1P4R/R5K1 w - - bm Nxe6; id \"WAC.178\"; cem 3.25; cee 0.54; ce 2.50;");
    pos.add(
        "r1b2r1k/pp4pp/3p4/3B4/8/1QN3Pn/PP3q1P/R3R2K b - - bm Qg1+; id \"WAC.179\"; cem -3.64; cee 0.00; ce -3.13;");
    pos.add(
        "r1q2rk1/p3bppb/3p1n1p/2nPp3/1p2P1P1/6NP/PP2QPB1/R1BNK2R b KQ - bm Nxd5; id \"WAC.180\"; cem -2.17; cee -0.40; ce -2.27;");
    pos.add(
        "r3k2r/2p2p2/p2p1n2/1p2p3/4P2p/1PPPPp1q/1P5P/R1N2QRK b kq - bm Ng4; id \"WAC.181\"; cem -3.93; cee -3.02; ce -3.74;");
    pos.add(
        "r1b2rk1/ppqn1p1p/2n1p1p1/2b3N1/2N5/PP1BP3/1B3PPP/R2QK2R w KQ - bm Qh5; id \"WAC.182\"; cem 2.75; cee 1.23; ce 2.85;");
    pos.add(
        "1r2k1r1/5p2/b3p3/1p2b1B1/3p3P/3B4/PP2KP2/2R3R1 w - - bm Bf6; id \"WAC.183\"; cem 0.67; cee 1.73; ce 1.40;");
    pos.add(
        "4kn2/r4p1r/p3bQ2/q1nNP1Np/1p5P/8/PPP3P1/2KR3R w - - bm Qe7+; id \"WAC.184\"; cem 3.29; cee -0.36; ce 3.01;");
    pos.add(
        "1r1rb1k1/2p3pp/p2q1p2/3PpP1Q/Pp1bP2N/1B5R/1P4PP/2B4K w - - bm Qxh7+; id \"WAC.185\"; cem -2.15; cee -4.74; ce -2.58;");
    pos.add(
        "r5r1/p1q2p1k/1p1R2pB/3pP3/6bQ/2p5/P1P1NPPP/6K1 w - - bm Bf8+; id \"WAC.186\"; cem 0.50; cee -2.28; ce -0.22;");
    pos.add(
        "6k1/5p2/p3p3/1p3qp1/2p1Qn2/2P1R3/PP1r1PPP/4R1K1 b - - bm Nh3+; id \"WAC.187\"; cem 0.60; cee 0.71; ce 0.55;");
    pos.add(
        "3RNbk1/pp3p2/4rQpp/8/1qr5/7P/P4P2/3R2K1 w - - bm Qg7+; id \"WAC.188\"; cem -3.94; cee -3.14; ce -3.60;");
    pos.add(
        "3r1k2/1ppPR1n1/p2p1rP1/3P3p/4Rp1N/5K2/P1P2P2/8 w - - bm Re8+; id \"WAC.189\"; cem 5.88; cee 4.64; ce 5.03;");
    pos.add(
        "8/p2b2kp/1q1p2p1/1P1Pp3/4P3/3B2P1/P2Q3P/2Nn3K b - - bm Bh3; id \"WAC.190\"; cem -3.15; cee 0.00; ce -1.30;");
    pos.add(
        "2r1Rn1k/1p1q2pp/p7/5p2/3P4/1B4P1/P1P1QP1P/6K1 w - - bm Qc4; id \"WAC.191\"; cem 6.41; cee 3.23; ce 4.81;");
    pos.add(
        "r3k3/ppp2Npp/4Bn2/2b5/1n1pp3/N4P2/PPP3qP/R2QKR2 b Qq - bm Nd3+; id \"WAC.192\"; cem -0.77; cee 1.72; ce -0.57;");
    pos.add(
        "5bk1/p4ppp/Qp6/4B3/1P6/Pq2P1P1/2rr1P1P/R4RK1 b - - bm Qxe3; id \"WAC.193\"; cem -0.14; cee -0.05; ce -0.21;");
    pos.add(
        "5rk1/ppq2ppp/2p5/4bN2/4P3/6Q1/PPP2PPP/3R2K1 w - - bm Nh6+; id \"WAC.194\"; cem 1.08; cee 0.99; ce 1.12;");
    pos.add(
        "3r1rk1/1p3p2/p3pnnp/2p3p1/2P2q2/1P5P/PB2QPPN/3RR1K1 w - - bm g3; id \"WAC.195\"; cem 0.81; cee -0.11; ce 0.75;");
    pos.add(
        "rr4k1/p1pq2pp/Q1n1pn2/2bpp3/4P3/2PP1NN1/PP3PPP/R1B1K2R b KQ - bm Nb4; id \"WAC.196\"; cem -0.24; cee 0.73; ce -0.31;");
    pos.add(
        "7k/1p4p1/7p/3P1n2/4Q3/2P2P2/PP3qRP/7K b - - bm Qf1+; id \"WAC.197\"; cem 4.00; cee 6.72; ce 5.86;");
    pos.add(
        "2br2k1/ppp2p1p/4p1p1/4P2q/2P1Bn2/2Q5/PP3P1P/4R1RK b - - bm Rd3; id \"WAC.198\"; cem -0.12; cee 1.82; ce 0.45;");
    pos.add(
        "r1br2k1/pp2nppp/2n5/1B1q4/Q7/4BN2/PP3PPP/2R2RK1 w - - bm Bxc6 Rcd1 Rfd1; id \"WAC.199\"; cem 1.27; cee 0.61; ce 1.35;");
    pos.add(
        "2rqrn1k/pb4pp/1p2pp2/n2P4/2P3N1/P2B2Q1/1B3PPP/2R1R1K1 w - - bm Bxf6; id \"WAC.200\"; cem 2.52; cee 1.79; ce 2.60;");
    pos.add(
        "2b2r1k/4q2p/3p2pQ/2pBp3/8/6P1/1PP2P1P/R5K1 w - - bm Ra7; id \"WAC.201\"; cem 0.85; cee 0.33; ce 0.67;");
    pos.add(
        "QR2rq1k/2p3p1/3p1pPp/8/4P3/8/P1r3PP/1R4K1 b - - bm Rxa2; id \"WAC.202\"; cem -0.42; cee -0.11; ce -0.38;");
    pos.add(
        "r4rk1/5ppp/p3q1n1/2p2NQ1/4n3/P3P3/1B3PPP/1R3RK1 w - - bm Qh6; id \"WAC.203\"; cem 0.75; cee -0.78; ce 0.58;");
    pos.add(
        "r1b1qrk1/1p3ppp/p1p5/3Nb3/5N2/P7/1P4PQ/K1R1R3 w - - bm Rxe5; id \"WAC.204\"; cem -3.06; cee -3.54; ce -3.04;");
    pos.add(
        "r3rnk1/1pq2bb1/p4p2/3p1Pp1/3B2P1/1NP4R/P1PQB3/2K4R w - - bm Qxg5; id \"WAC.205\"; cem 2.61; cee 0.91; ce 2.67;");
    pos.add(
        "1Qq5/2P1p1kp/3r1pp1/8/8/7P/p4PP1/2R3K1 b - - bm Rc6; id \"WAC.206\"; cem -0.08; cee -1.61; ce -1.21;");
    pos.add(
        "r1bq2kr/p1pp1ppp/1pn1p3/4P3/2Pb2Q1/BR6/P4PPP/3K1BNR w - - bm Qxg7+; id \"WAC.207\"; cem -3.24; cee -2.28; ce -3.12;");
    pos.add(
        "3r1bk1/ppq3pp/2p5/2P2Q1B/8/1P4P1/P6P/5RK1 w - - bm Bf7+; id \"WAC.208\"; cem 5.08; cee 0.63; ce 2.74;");
    pos.add(
        "4kb1r/2q2p2/r2p4/pppBn1B1/P6P/6Q1/1PP5/2KRR3 w k - bm Rxe5+; id \"WAC.209\"; cem 2.89; cee 0.95; ce 2.68;");
    pos.add(
        "3r1rk1/pp1q1ppp/3pn3/2pN4/5PP1/P5PQ/1PP1B3/1K1R4 w - - bm Rh1; id \"WAC.210\"; cem -0.61; cee -3.11; ce -1.38;");
    pos.add(
        "r1bqrk2/pp1n1n1p/3p1p2/P1pP1P1Q/2PpP1NP/6R1/2PB4/4RBK1 w - - bm Qxf7+; id \"WAC.211\"; cem 3.17; cee 0.80; ce 3.21;");
    pos.add(
        "rn1qr2Q/pbppk1p1/1p2pb2/4N3/3P4/2N5/PPP3PP/R4RK1 w - - bm Qxg7+; id \"WAC.212\"; cem -1.17; cee -4.53; ce -1.38;");
    pos.add(
        "3r1r1k/1b4pp/ppn1p3/4Pp1R/Pn5P/3P4/4QP2/1qB1NKR1 w - - bm Rxh7+; id \"WAC.213\"; cem -6.71; cee -5.95; ce -6.44;");
    pos.add(
        "r2r2k1/1p2qpp1/1np1p1p1/p3N3/2PPN3/bP5R/4QPPP/4R1K1 w - - bm Ng5; id \"WAC.214\"; cem 2.08; cee -0.62; ce 1.71;");
    pos.add(
        "3r2k1/pb1q1pp1/1p2pb1p/8/3N4/P2QB3/1P3PPP/1Br1R1K1 w - - bm Qh7+; id \"WAC.215\"; cem -2.45; cee -4.19; ce -2.71;");
    pos.add(
        "r2qr1k1/1b1nbppp/p3pn2/1p1pN3/3P1B2/2PB1N2/PP2QPPP/R4RK1 w - - bm Nxf7 a4; id \"WAC.216\"; cem 2.38; cee 1.38; ce 2.48;");
    pos.add(
        "r3kb1r/1pp3p1/p3bp1p/5q2/3QN3/1P6/PBP3P1/3RR1K1 w kq - bm Qd7+; id \"WAC.217\"; cem 3.40; cee -0.97; ce 2.81;");
    pos.add(
        "6k1/pp5p/2p3q1/6BP/2nPr1Q1/8/PP3R1K/8 w - - bm Bh6; id \"WAC.218\"; cem 5.37; cee 0.39; ce 2.82;");
    pos.add(
        "7k/p4q1p/1pb5/2p5/4B2Q/2P1B3/P6P/7K b - - bm Qf1+; id \"WAC.219\"; cem 4.84; cee 3.38; ce 3.63;");
    pos.add(
        "3rr1k1/ppp2ppp/8/5Q2/4n3/1B5R/PPP1qPP1/5RK1 b - - bm Qxf1+; id \"WAC.220\"; cem 4.43; cee -0.67; ce 2.78;");
    pos.add(
        "r3k3/P5bp/2N1bp2/4p3/2p5/6NP/1PP2PP1/3R2K1 w q - bm Rd8+; id \"WAC.221\"; cem 4.85; cee 4.06; ce 4.28;");
    pos.add(
        "2r1r2k/1q3ppp/p2Rp3/2p1P3/6QB/p3P3/bP3PPP/3R2K1 w - - bm Bf6; id \"WAC.222\"; cem 0.13; cee -1.26; ce -0.13;");
    pos.add(
        "r1bqk2r/pp3ppp/5n2/8/1b1npB2/2N5/PP1Q2PP/1K2RBNR w kq - bm Nxe4; id \"WAC.223\"; cem -2.79; cee -2.79; ce -2.69;");
    pos.add(
        "5rk1/p1q3pp/1p1r4/2p1pp1Q/1PPn1P2/3B3P/P2R2P1/3R2K1 b - - bm Rh6 e4; id \"WAC.224\"; cem -2.13; cee -2.20; ce -2.25;");
    pos.add(
        "4R3/4q1kp/6p1/1Q3b2/1P1b1P2/6KP/8/8 b - - bm Qh4+; id \"WAC.225\"; cem -2.30; cee -1.00; ce -1.46;");
    pos.add(
        "2b2rk1/p1p4p/2p1p1p1/br2N1Q1/1p2q3/8/PB3PPP/3R1RK1 w - - bm Nf7; id \"WAC.226\"; cem -1.09; cee -2.15; ce -1.15;");
    pos.add(
        "2k1rb1r/ppp3pp/2np1q2/5b2/2B2P2/2P1BQ2/PP1N1P1P/2KR3R b - - bm d5; id \"WAC.227\"; cem -0.49; cee 0.03; ce -0.58;");
    pos.add(
        "r4rk1/1bq1bp1p/4p1p1/p2p4/3BnP2/1N1B3R/PPP3PP/R2Q2K1 w - - bm Bxe4; id \"WAC.228\"; cem 0.94; cee 0.14; ce 1.02;");
    pos.add(
        "8/8/8/1p5r/p1p1k1pN/P2pBpP1/1P1K1P2/8 b - - bm Rxh4 b4; id \"WAC.229\"; cem -1.62; cee -2.79; ce -2.88;");
    pos.add(
        "2b5/1r6/2kBp1p1/p2pP1P1/2pP4/1pP3K1/1R3P2/8 b - - bm Rb4; id \"WAC.230\"; cem -1.58; cee -4.01; ce -3.32;");
    pos.add(
        "r4rk1/1b1nqp1p/p5p1/1p2PQ2/2p5/5N2/PP3PPP/R1BR2K1 w - - bm Bg5; id \"WAC.231\"; cem -0.16; cee -0.43; ce -0.10;");
    pos.add(
        "1R2rq1k/2p3p1/Q2p1pPp/8/4P3/8/P1r3PP/1R4K1 w - - bm Qb5 Rxe8; id \"WAC.232\"; cem -0.94; cee -0.19; ce -0.51;");
    pos.add(
        "5rk1/p1p2r1p/2pp2p1/4p3/PPPnP3/3Pq1P1/1Q1R1R1P/4NK2 b - - bm Nb3; id \"WAC.233\"; cem -4.47; cee -1.13; ce -3.52;");
    pos.add(
        "2kr1r2/p6p/5Pp1/2p5/1qp2Q1P/7R/PP6/1KR5 w - - bm Rb3; id \"WAC.234\"; cem 1.96; cee 0.79; ce 1.53;");
    pos.add(
        "5r2/1p1RRrk1/4Qq1p/1PP3p1/8/4B3/1b3P1P/6K1 w - - bm Qe4 Qxf7+ Rxf7+; id \"WAC.235\"; cem 3.37; cee 2.41; ce 3.17;");
    pos.add(
        "1R6/p5pk/4p2p/4P3/8/2r3qP/P3R1b1/4Q1K1 b - - bm Rc1; id \"WAC.236\"; cem -1.63; cee 0.75; ce -0.57;");
    pos.add(
        "r5k1/pQp2qpp/8/4pbN1/3P4/6P1/PPr4P/1K1R3R b - - bm Rc1+; id \"WAC.237\"; cem -1.05; cee -0.20; ce -0.89;");
    pos.add(
        "1k1r4/pp1r1pp1/4n1p1/2R5/2Pp1qP1/3P2QP/P4PB1/1R4K1 w - - bm Bxb7; id \"WAC.238\"; cem 1.48; cee -0.52; ce 0.96;");
    pos.add(
        "8/6k1/5pp1/Q6p/5P2/6PK/P4q1P/8 b - - bm Qf1+; id \"WAC.239\"; cem -3.96; cee 0.02; ce -0.44;");
    pos.add(
        "2b4k/p1b2p2/2p2q2/3p1PNp/3P2R1/3B4/P1Q2PKP/4r3 w - - bm Qxc6; id \"WAC.240\"; cem -3.16; cee -0.47; ce -2.01;");
    pos.add(
        "2rq1rk1/pp3ppp/2n2b2/4NR2/3P4/PB5Q/1P4PP/3R2K1 w - - bm Qxh7+; id \"WAC.241\"; cem 2.58; cee 0.95; ce 2.38;");
    pos.add(
        "r1b1r1k1/pp1nqp2/2p1p1pp/8/4N3/P1Q1P3/1P3PPP/1BRR2K1 w - - bm Rxd7; id \"WAC.242\"; cem 1.33; cee 0.31; ce 1.25;");
    pos.add(
        "1r3r1k/3p4/1p1Nn1R1/4Pp1q/pP3P1p/P7/5Q1P/6RK w - - bm Qe2; id \"WAC.243\"; cem 6.00; cee 1.02; ce 4.54;");
    pos.add(
        "r6r/pp3ppp/3k1b2/2pb4/B4Pq1/2P1Q3/P5PP/1RBR2K1 w - - bm Qxc5+; id \"WAC.244\"; cem 5.26; cee -0.29; ce 4.49;");
    pos.add(
        "4rrn1/ppq3bk/3pPnpp/2p5/2PB4/2NQ1RPB/PP5P/5R1K w - - bm Qxg6+; id \"WAC.245\"; cem 3.69; cee 2.23; ce 3.75;");
    pos.add(
        "6R1/4qp1p/ppr1n1pk/8/1P2P1QP/6N1/P4PP1/6K1 w - - bm Qh5+; id \"WAC.246\"; cem 2.81; cee 0.51; ce 1.66;");
    pos.add(
        "2k1r3/1p2Bq2/p2Qp3/Pb1p1p1P/2pP1P2/2P5/2P2KP1/1R6 w - - bm Rxb5; id \"WAC.247\"; cem 5.62; cee 3.35; ce 4.23;");
    pos.add(
        "5r1k/1p4pp/3q4/3Pp1R1/8/8/PP4PP/4Q1K1 b - - bm Qc5+; id \"WAC.248\"; cem -0.81; cee 0.96; ce 0.30;");
    pos.add(
        "r4rk1/pbq2pp1/1ppbpn1p/8/2PP4/1P1Q1N2/PBB2PPP/R3R1K1 w - - bm c5 d5; id \"WAC.249\"; cem 0.91; cee 0.55; ce 1.00;");
    pos.add(
        "1b5k/7P/p1p2np1/2P2p2/PP3P2/4RQ1R/q2r3P/6K1 w - - bm Re8+; id \"WAC.250\"; cem -2.99; cee 0.00; ce -1.63;");
    pos.add(
        "k7/p4p2/P1q1b1p1/3p3p/3Q4/7P/5PP1/1R4K1 w - - bm Qe5 Qf4; id \"WAC.251\"; cem 6.52; cee 2.78; ce 3.93;");
    pos.add(
        "1rb1r1k1/p1p2ppp/5n2/2pP4/5P2/2QB4/qNP3PP/2KRB2R b - - bm Bg4 Re2; c0 \"Bg4 wins, but Re2 is far better.\"; id \"WAC.252\"; cem -0.68; cee 2.52; ce -0.47;");
    pos.add(
        "k5r1/p4b2/2P5/5p2/3P1P2/4QBrq/P5P1/4R1K1 w - - bm Qe8+; id \"WAC.253\"; cem -2.88; cee -2.32; ce -2.40;");
    pos.add(
        "r6k/pp3p1p/2p1bp1q/b3p3/4Pnr1/2PP2NP/PP1Q1PPN/R2B2RK b - - bm Nxh3; id \"WAC.254\"; cem 2.38; cee 2.08; ce 2.27;");
    pos.add(
        "3r3r/p4pk1/5Rp1/3q4/1p1P2RQ/5N2/P1P4P/2b4K w - - bm Rfxg6+; id \"WAC.255\"; cem -3.22; cee -3.62; ce -3.24;");
    pos.add(
        "3r1rk1/1pb1qp1p/2p3p1/p7/P2Np2R/1P5P/1BP2PP1/3Q1BK1 w - - bm Nf5; id \"WAC.256\"; cem 0.70; cee 0.00; ce 0.60;");
    pos.add(
        "4r1k1/pq3p1p/2p1r1p1/2Q1p3/3nN1P1/1P6/P1P2P1P/3RR1K1 w - - bm Rxd4; id \"WAC.257\"; cem -0.52; cee 0.44; ce -0.12;");
    pos.add(
        "r3brkn/1p5p/2p2Ppq/2Pp3B/3Pp2Q/4P1R1/6PP/5R1K w - - bm Bxg6; id \"WAC.258\"; cem -0.73; cee -3.19; ce -1.21;");
    pos.add(
        "r1bq1rk1/ppp2ppp/2np4/2bN1PN1/2B1P3/3p4/PPP2nPP/R1BQ1K1R w - - bm Qh5; id \"WAC.259\"; cem -2.46; cee -1.64; ce -2.36;");
    pos.add(
        "2r2b1r/p1Nk2pp/3p1p2/N2Qn3/4P3/q6P/P4PP1/1R3K1R w - - bm Qe6+; id \"WAC.260\"; cem 6.19; cee 0.38; ce 5.28;");
    pos.add(
        "r5k1/1bp3pp/p2p4/1p6/5p2/1PBP1nqP/1PP3Q1/R4R1K b - - bm Nd4; id \"WAC.261\"; cem -4.32; cee -0.13; ce -2.97;");
    pos.add(
        "6k1/p1B1b2p/2b3r1/2p5/4p3/1PP1N1Pq/P2R1P2/3Q2K1 b - - bm Rh6; id \"WAC.262\"; cem -0.53; cee 0.85; ce -0.09;");
    pos.add(
        "rnbqr2k/pppp1Qpp/8/b2NN3/2B1n3/8/PPPP1PPP/R1B1K2R w KQ - bm Qg8+; id \"WAC.263\"; cem 1.79; cee 2.31; ce 1.89;");
    pos.add(
        "r2r2k1/1R2qp2/p5pp/2P5/b1PN1b2/P7/1Q3PPP/1B1R2K1 b - - bm Qe5 Rab8; id \"WAC.264\"; cem 1.78; cee 1.37; ce 1.62;");
    pos.add(
        "2r1k2r/2pn1pp1/1p3n1p/p3PP2/4q2B/P1P5/2Q1N1PP/R4RK1 w k - bm exf6; id \"WAC.265\"; cem -0.26; cee -1.02; ce -0.30;");
    pos.add(
        "r3q2r/2p1k1p1/p5p1/1p2Nb2/1P2nB2/P7/2PNQbPP/R2R3K b - - bm Rxh2+; id \"WAC.266\"; cem -1.65; cee -0.86; ce -1.73;");
    pos.add(
        "2r1kb1r/pp3ppp/2n1b3/1q1N2B1/1P2Q3/8/P4PPP/3RK1NR w Kk - bm Nc7+; id \"WAC.267\"; cem 1.13; cee 0.00; ce 1.20;");
    pos.add(
        "2r3kr/ppp2n1p/7B/5q1N/1bp5/2Pp4/PP2RPPP/R2Q2K1 w - - bm Re8+; id \"WAC.268\"; cem 2.40; cee -0.68; ce 1.99;");
    pos.add(
        "2kr2nr/pp1n1ppp/2p1p3/q7/1b1P1B2/P1N2Q1P/1PP1BPP1/R3K2R w KQ - bm axb4; id \"WAC.269\"; cem 1.26; cee 0.89; ce 1.35;");
    pos.add(
        "2r1r1k1/pp1q1ppp/3p1b2/3P4/3Q4/5N2/PP2RPPP/4R1K1 w - - bm Qg4; id \"WAC.270\"; cem -0.45; cee -0.06; ce -0.24;");
    pos.add(
        "2kr4/ppp3Pp/4RP1B/2r5/5P2/1P6/P2p4/3K4 w - - bm Rd6; id \"WAC.271\"; cem -3.07; cee 1.45; ce 1.26;");
    pos.add(
        "nrq4r/2k1p3/1p1pPnp1/pRpP1p2/P1P2P2/2P1BB2/1R2Q1P1/6K1 w - - bm Bxc5; id \"WAC.272\"; cem 2.14; cee -0.40; ce 1.82;");
    pos.add(
        "2k4B/bpp1qp2/p1b5/7p/1PN1n1p1/2Pr4/P5PP/R3QR1K b - - bm Ng3+ g3; id \"WAC.273\"; cem -3.31; cee -1.55; ce -3.04;");
    pos.add(
        "8/1p6/p5R1/k7/Prpp4/K7/1NP5/8 w - - am Rd6; bm Rb6 Rg5+; id \"WAC.274\"; cem 2.66; cee 1.21; ce 1.12;");
    pos.add(
        "r1b2rk1/1p1n1ppp/p1p2q2/4p3/P1B1Pn2/1QN2N2/1P3PPP/3R1RK1 b - - bm Nc5 Nxg2 b5; id \"WAC.275\"; cem -0.14; cee 0.29; ce -0.22;");
    pos.add(
        "r5k1/pp1RR1pp/1b6/6r1/2p5/B6P/P4qPK/3Q4 w - - bm Qd5+; id \"WAC.276\"; cem -14.25; cee -1.48; ce -10.26;");
    pos.add(
        "1r4r1/p2kb2p/bq2p3/3p1p2/5P2/2BB3Q/PP4PP/3RKR2 b - - bm Rg3 Rxg2; id \"WAC.277\"; cem -0.87; cee -1.77; ce -1.10;");
    pos.add(
        "r2qkb1r/pppb2pp/2np1n2/5pN1/2BQP3/2N5/PPP2PPP/R1B1K2R w KQkq - bm Bf7+; id \"WAC.278\"; cem 1.67; cee -0.03; ce 1.76;");
    pos.add(
        "r7/4b3/2p1r1k1/1p1pPp1q/1P1P1P1p/PR2NRpP/2Q3K1/8 w - - bm Nxf5 Rc3; id \"WAC.279\"; cem -0.94; cee -0.39; ce -0.67;");
    pos.add(
        "r1r2bk1/5p1p/pn4p1/N2b4/3Pp3/B3P3/2q1BPPP/RQ3RK1 b - - bm Bxa3; id \"WAC.280\"; cem -0.06; cee -0.65; ce -0.16;");
    pos.add(
        "2R5/2R4p/5p1k/6n1/8/1P2QPPq/r7/6K1 w - - bm Rxh7+; id \"WAC.281\"; cem -4.41; cee 4.28; ce -0.13;");
    pos.add(
        "6k1/2p3p1/1p1p1nN1/1B1P4/4PK2/8/2r3b1/7R w - - bm Rh8+; id \"WAC.282\"; cem -0.94; cee -2.31; ce -1.99;");
    pos.add(
        "3q1rk1/4bp1p/1n2P2Q/3p1p2/6r1/Pp2R2N/1B4PP/7K w - - bm Ng5; id \"WAC.283\"; cem 0.00; cee 0.00; ce -7.41;");
    pos.add(
        "3r3k/pp4pp/8/1P6/3N4/Pn2P1qb/1B1Q2B1/2R3K1 w - - bm Nf5; id \"WAC.284\"; cem 0.76; cee 2.21; ce 1.28;");
    pos.add(
        "2rr3k/1b2bppP/p2p1n2/R7/3P4/1qB2P2/1P4Q1/1K5R w - - bm Qxg7+; id \"WAC.285\"; cem 0.00; cee 0.00; ce -9.16;");
    pos.add(
        "3r1k2/1p6/p4P2/2pP2Qb/8/1P1KB3/P6r/8 b - - bm Rxd5+; id \"WAC.286\"; cem 9.28; cee 1.74; ce 3.20;");
    pos.add(
        "rn3k1r/pp2bBpp/2p2n2/q5N1/3P4/1P6/P1P3PP/R1BQ1RK1 w - - bm Qg4 Qh5; id \"WAC.287\"; cem 8.73; cee 2.20; ce 8.67;");
    pos.add(
        "r1b2rk1/p4ppp/1p1Qp3/4P2N/1P6/8/P3qPPP/3R1RK1 w - - bm Nf6+; id \"WAC.288\"; cem 0.63; cee 0.97; ce 0.83;");
    pos.add(
        "2r3k1/5p1p/p3q1p1/2n3P1/1p1QP2P/1P4N1/PK6/2R5 b - - bm Qe5; id \"WAC.289\"; cem 0.49; cee 1.06; ce 0.70;");
    pos.add(
        "2k2r2/2p5/1pq5/p1p1n3/P1P2n1B/1R4Pp/2QR4/6K1 b - - bm Ne2+; id \"WAC.290\"; cem -4.32; cee -0.95; ce -3.23;");
    pos.add(
        "5r1k/3b2p1/p6p/1pRpR3/1P1P2q1/P4pP1/5QnP/1B4K1 w - - bm h3; id \"WAC.291\"; cem -0.84; cee 2.32; ce 0.34;");
    pos.add(
        "4r3/1Q1qk2p/p4pp1/3Pb3/P7/6PP/5P2/4R1K1 w - - bm d6+; id \"WAC.292\"; cem 2.60; cee -2.56; ce -0.41;");
    pos.add(
        "1nbq1r1k/3rbp1p/p1p1pp1Q/1p6/P1pPN3/5NP1/1P2PPBP/R4RK1 w - - bm Nfg5; id \"WAC.293\"; cem 1.48; cee 1.01; ce 1.56;");
    pos.add(
        "3r3k/1r3p1p/p1pB1p2/8/p1qNP1Q1/P6P/1P4P1/3R3K w - - bm Bf8 Nf5 Qf4; id \"WAC.294\"; cem 1.58; cee 1.05; ce 1.45;");
    pos.add(
        "4r3/p4r1p/R1p2pp1/1p1bk3/4pNPP/2P1K3/2P2P2/3R4 w - - bm Rxd5+; id \"WAC.295\"; cem -1.49; cee -2.09; ce -1.84;");
    pos.add(
        "3r4/1p2k2p/p1b1p1p1/4Q1Pn/2B3KP/4pP2/PP2R1N1/6q1 b - - bm Rd4+ Rf8; id \"WAC.296\"; cem -3.49; cee -1.80; ce -2.92;");
    pos.add(
        "3r1rk1/p3qp1p/2bb2p1/2p5/3P4/1P6/PBQN1PPP/2R2RK1 b - - bm Bxg2 Bxh2+; id \"WAC.297\"; cem -0.65; cee 0.04; ce -0.64;");
    pos.add(
        "3Q4/p3b1k1/2p2rPp/2q5/4B3/P2P4/7P/6RK w - - bm Qh8+; id \"WAC.298\"; cem 2.75; cee -0.91; ce 1.03;");
    pos.add(
        "1n2rr2/1pk3pp/pNn2p2/2N1p3/8/6P1/PP2PPKP/2RR4 w - - bm Nca4; id \"WAC.299\"; cem 5.33; cee 0.71; ce 2.57;");
    pos.add(
        "b2b1r1k/3R1ppp/4qP2/4p1PQ/4P3/5B2/4N1K1/8 w - - bm g6; id \"WAC.300\"; cem 0.70; cee 0.59; ce 0.74;");
    return pos;
  }
}
