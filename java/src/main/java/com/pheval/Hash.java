package com.pheval;

public class Hash {
  public static int quinary(byte[] q, int len, int k) {
    int sum = 0;
    for (int i=0; i<len; i++)
    {
      sum += com.pheval.tables.DP.value[q[i]][len-i-1][k];
      k -= q[i];
      if (k <= 0)
        break;
    }
    return sum;
  }
}
