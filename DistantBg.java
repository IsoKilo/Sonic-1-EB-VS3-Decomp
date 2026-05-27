import javax.microedition.lcdui.Graphics;

public final class DistantBg {
  static final int ANCHOR = 20;
  
  static final int DISP_W = 240;
  
  static final int DISP_H = 168;
  
  static final int DISP_OY = 36;
  
  static final int DISP_WP = 496;
  
  static Graphics g;
  
  static int stageNo;
  
  static int zoneNo = -1;
  
  static int workX;
  
  static int workY;
  
  public static final int[] box = new int[] { 
      3440, 3584, 784, 1084, 2528, 3440, 944, 1096, 2528, 4256, 
      1096, 2048, 4192, 4496, 988, 2048, 2960, 4496, 784, 1072, 
      760, 1664, 800, 1088, 568, 3848, 1068, 2048, 760, 1283, 
      832, 1088, 2122, 3898, 800, 1072, 4443, 4715, 800, 1316, 
      4091, 5544, 1028, 2048, 5346, 6034, 757, 1125, 1550, 1949, 
      808, 1076, 0, 6138, 1040, 2048, 0, 1168, 720, 960, 
      3574, 4600, 752, 1040, 4600, 4999, 776, 1076 };
  
  public static final void setStage(int paramInt1, int paramInt2) {
    zoneNo = paramInt1;
    stageNo = paramInt2;
    g = MainCanvas.gg;
  }
  
  public static final void paint(int paramInt1, int paramInt2) {
    byte b;
    if (zoneNo < 0)
      return; 
    int i = paramInt2;
    g.setClip(0, 36, 240, 168);
    switch (zoneNo) {
      case 0:
        paramInt2 >>= 5;
        if (paramInt2 > 32)
          paramInt2 = 32; 
        drawParts(DistantBgTbl1.data0, paramInt1 / 5, paramInt2);
        drawParts(DistantBgTbl1.data1, paramInt1 / 3, paramInt2);
        drawParts(DistantBgTbl1.data2, paramInt1 >> 1, paramInt2);
        drawParts(DistantBgTbl1.data3, paramInt1, paramInt2);
        return;
      case 1:
        paintS2(paramInt1, i);
        return;
      case 2:
        b = 0;
        paramInt2 = (paramInt2 >> 4) - 16;
        if (paramInt2 > 16)
          paramInt2 = 16; 
        switch (stageNo) {
          case 0:
            if (chkBox(paramInt1, i, 4) || chkBox(paramInt1, i, 8) || chkBox(paramInt1, i, 12))
              break; 
            if (chkBox(paramInt1, i, 16)) {
              b = -17;
              break;
            } 
            b = 1;
            break;
          case 1:
            if (chkBox(paramInt1, i, 24) || chkBox(paramInt1, i, 40))
              break; 
            if (chkBox(paramInt1, i, 20)) {
              b = -21;
              break;
            } 
            if (chkBox(paramInt1, i, 28)) {
              b = -29;
              break;
            } 
            if (chkBox(paramInt1, i, 32)) {
              b = -33;
              break;
            } 
            if (chkBox(paramInt1, i, 36)) {
              b = -37;
              break;
            } 
            if (chkBox(paramInt1, i, 44)) {
              b = -45;
              break;
            } 
            b = 1;
            break;
          default:
            if (chkBox(paramInt1, i, 52) || chkBox(paramInt1, i, 56) || chkBox(paramInt1, i, 60))
              break; 
            if (chkBox(paramInt1, i, 48)) {
              b = -49;
              break;
            } 
            if (chkBox(paramInt1, i, 64)) {
              b = -65;
              break;
            } 
            b = 1;
            break;
        } 
        if (b == 0) {
          paintS1(paramInt1, i);
        } else if (b > 0) {
          paintS0(paramInt1, paramInt2);
        } else if (b < 0) {
          paintS0(paramInt1, paramInt2);
          if (setClip(paramInt1, i, -b - 1))
            paintS1(paramInt1, i); 
          g.setClip(0, 36, 240, 168);
        } 
        return;
      case 3:
        paramInt2 = 32 - paramInt2 * 512 / 1712;
        if (paramInt2 < -480)
          paramInt2 = -480; 
        drawParts(DistantBgTbl2.data16, paramInt1 / 5, paramInt2);
        drawParts(DistantBgTbl2.data17, paramInt1 >> 2, paramInt2);
        drawParts(DistantBgTbl2.data18, paramInt1 / 3, paramInt2);
        drawParts(DistantBgTbl2.data19, paramInt1 >> 1, paramInt2);
        return;
      case 4:
        paramInt2 = 32 - (paramInt2 >> 3);
        if (paramInt2 < -112)
          paramInt2 = -112; 
        drawParts(DistantBgTbl1.data8, paramInt1 / 5, paramInt2);
        drawParts(DistantBgTbl1.data9, paramInt1 / 3, paramInt2);
        drawParts(DistantBgTbl1.data10, paramInt1 >> 1, paramInt2);
        drawParts(DistantBgTbl1.data11, paramInt1, paramInt2);
        return;
    } 
    switch (stageNo) {
      case 0:
        paramInt2 = 32 - (paramInt2 >> 3);
        if (paramInt2 < -144)
          paramInt2 = -144; 
        drawParts(DistantBgTbl2.data12, paramInt1 / 5, paramInt2);
        drawParts(DistantBgTbl2.data13, paramInt1 / 3, paramInt2);
        return;
    } 
    paintS3(paramInt1, i);
  }
  
  private static final void paintS0(int paramInt1, int paramInt2) {
    drawParts(DistantBgTbl1.data4, paramInt1 / 5, paramInt2);
    drawParts(DistantBgTbl1.data5, paramInt1 / 3, paramInt2);
    drawParts(DistantBgTbl1.data6, paramInt1 >> 1, paramInt2);
  }
  
  private static final void paintS1(int paramInt1, int paramInt2) {
    paramInt1 >>= 1;
    paramInt2 = 256 - (paramInt2 & 0xFF);
    drawParts(DistantBgTbl1.data7, paramInt1, paramInt2);
    drawParts(DistantBgTbl1.data7, paramInt1, paramInt2 - 256);
  }
  
  private static final void paintS2(int paramInt1, int paramInt2) {
    paramInt1 >>= 1;
    paramInt2 = 256 - (paramInt2 >> 2 & 0xFF);
    drawParts(DistantBgTbl2.data15, paramInt1, paramInt2);
    drawParts(DistantBgTbl2.data15, paramInt1, paramInt2 - 256);
  }
  
  private static final void paintS3(int paramInt1, int paramInt2) {
    paramInt1 >>= 1;
    paramInt2 = 256 - (paramInt2 >> 3 & 0xFF);
    drawParts(DistantBgTbl2.data14, paramInt1, paramInt2);
    drawParts(DistantBgTbl2.data14, paramInt1, paramInt2 - 256);
  }
  
  private static final void drawParts(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    workY = paramInt2;
    workX = paramInt1;
    drawPartsSub(paramArrayOfint, 0);
    drawPartsSub(paramArrayOfint, 256);
  }
  
  public static final void drawPartsSub(int[] paramArrayOfint, int paramInt) {
    int k = workY;
    if (paramInt == 0) {
      int n = paramArrayOfint[0];
      if (n > 0) {
        g.setColor(paramArrayOfint[2]);
        g.fillRect(0, paramArrayOfint[1] + k, 240, n);
      } 
    } 
    int m = 0;
    int j = paramInt - workX;
    int i = paramArrayOfint[3];
    byte b = 4;
    if (k < 20)
      while (i > 0) {
        j = j + paramArrayOfint[b + 0] & 0x1FF;
        int n = paramArrayOfint[b + 1];
        int i1 = paramArrayOfint[b + 2];
        if (i1 == -2) {
          k += paramArrayOfint[b + 1];
          m = paramArrayOfint[b + 3];
          if (k >= 20) {
            b += 4;
            i--;
            break;
          } 
        } 
        b += 4;
        i--;
      }  
    while (i > 0) {
      j = j + paramArrayOfint[b + 0] & 0x1FF;
      int n = paramArrayOfint[b + 1];
      int i1 = paramArrayOfint[b + 2];
      if (i1 == -2 || (j < 496 && (j >= 256 || j + n >= 256)))
        if (i1 >= 0) {
          g.drawRegion(MainCanvas.m_imgMimg, i1, paramArrayOfint[b + 3], n, 16, m, j - 256, k, 20);
        } else {
          int i2;
          switch (i1) {
            case -1:
              g.setColor(paramArrayOfint[b + 3]);
              g.fillRect(j - 256, k, n, 16);
              break;
            case -2:
              k += paramArrayOfint[b + 1];
              if (k >= 204)
                break; 
              m = paramArrayOfint[b + 3];
              break;
            default:
              i1 = -(i1 + 16);
              for (i2 = j - 256; n > 0; i2 += 16) {
                g.drawRegion(MainCanvas.m_imgMimg, i1, paramArrayOfint[b + 3], 16, 16, m, i2, k, 20);
                n -= 16;
              } 
              break;
          } 
        }  
      b += 4;
      i--;
    } 
  }
  
  private static final boolean setClip(int paramInt1, int paramInt2, int paramInt3) {
    int i = box[paramInt3] - paramInt1;
    int j = box[paramInt3 + 2] - paramInt2;
    int k = box[paramInt3 + 1] - paramInt1;
    int m = box[paramInt3 + 3] - paramInt2;
    if (i < 0)
      i = 0; 
    if (j < 36)
      j = 36; 
    if (k - i > 240)
      k = i + 240; 
    if (m - j > 168)
      m = j + 168; 
    if (i >= k)
      return false; 
    if (j >= m)
      return false; 
    if (j >= 204)
      return false; 
    if (m <= 36)
      return false; 
    g.clipRect(i, j, k - i, m - j);
    return true;
  }
  
  private static final boolean chkBox(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt1 + 240 < box[paramInt3]) ? false : ((paramInt1 >= box[paramInt3 + 1]) ? false : ((paramInt2 + 168 + 36 < box[paramInt3 + 2]) ? false : (!(paramInt2 >= box[paramInt3 + 3]))));
  }
}