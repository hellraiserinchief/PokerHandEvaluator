package com.pheval;

import java.util.stream.Stream;

import com.pheval.tables.ByID;
import com.pheval.tables.Flush;
import com.pheval.tables.NoFlush5;
import com.pheval.tables.Suits;

public class Evaluator5 {
  public static int evaluate( int a, int b, int c, int d, int e ) {
  int suit_hash = 0;
  int suit_binary[] = new int[4];
  byte[] quinary = new byte[13];
  int hash;

  suit_hash += ByID.suitbit[a];
  suit_hash += ByID.suitbit[b];
  suit_hash += ByID.suitbit[c];
  suit_hash += ByID.suitbit[d];
  suit_hash += ByID.suitbit[e];

  if (Suits.value[suit_hash] != 0)
  {
    suit_binary[a & 0x3] |= ByID.binaries[a];
    suit_binary[b & 0x3] |= ByID.binaries[b];
    suit_binary[c & 0x3] |= ByID.binaries[c];
    suit_binary[d & 0x3] |= ByID.binaries[d];
    suit_binary[e & 0x3] |= ByID.binaries[e];

    return Flush.value[suit_binary[Suits.value[suit_hash]-1]];
  }

  quinary[(a >> 2)]++;
  quinary[(b >> 2)]++;
  quinary[(c >> 2)]++;
  quinary[(d >> 2)]++;
  quinary[(e >> 2)]++;

  hash = Hash.quinary(quinary, 13, 5);

  return NoFlush5.value[hash];
  }

  public static void main(String[] args) throws Exception {
    if( args.length == 5 ) {
      int[] cardIds = Stream.of(args).mapToInt((String s) -> Integer.parseInt(s)).toArray();
      int rank = Evaluator5.evaluate(cardIds[0], cardIds[1] , cardIds[2], cardIds[3], cardIds[4]);
      System.out.println("Rank is " + rank);
    } else {
      System.out.println("Kindly pass in 5 card-ids");
      throw new Exception("Invalid Input");
    }
  }
}
