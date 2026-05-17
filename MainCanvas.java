import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.rms.RecordStore;

public class MainCanvas extends GameCanvas implements CommandListener, PlayerListener, Runnable {
  public static boolean moveRsm;
  
  public static boolean drawRsm;
  
  public static boolean loopMode;
  
  public static boolean noloop;
  
  public static boolean loopOut1;
  
  public static boolean loopOut2;
  
  public static final boolean EmuOn = false;
  
  private static final boolean DebugPutOn = false;
  
  private static final boolean DebugCommandOn = false;
  
  private static DataOutputStream out;
  
  private static InputStream in;
  
  private static DataInputStream indata;
  
  public Random rnd = new Random();
  
  private static sonic h;
  
  private static RecordStore record = null;
  
  private static Font f = Font.getDefaultFont();
  
  public static Graphics gg;
  
  private static int[][] ObjectList = new int[256][25];
  
  private static int ObjectListNum;
  
  private static boolean[] ObjectAct;
  
  private static boolean[] ObjectDead;
  
  private Image[] m_imgCmd = new Image[20];
  
  public static Image m_imgMimg;
  
  private Image[] m_imgObj = new Image[150];
  
  private static boolean keyBreak;
  
  private static boolean DrawFlag;
  
  private static boolean[] KeyEvent = new boolean[5];
  
  private static boolean[] KeyPress = new boolean[10];
  
  private static boolean debugFlag = false;
  
  private static int TIME_WAIT = 66;
  
  private static int XNUM = 100;
  
  private static int MODE_SELECT_DATAFOLDER = -6;
  
  private static int MODE_DEBUGPRINT2 = -5;
  
  private static int MODE_CONNINIT = -4;
  
  private static int MODE_DEBUGPRINT = -3;
  
  private static int MODE_CONNECT_FAILED = -2;
  
  private static int MODE_INIT = -1;
  
  private static int MODE_TITLE = 1;
  
  private static int MODE_FIELD = 2;
  
  private static int MODE_STAGESELECT = 3;
  
  private static int MODE_STARTSTAGE = 4;
  
  private static int MODE_CLEARSTAGE = 5;
  
  private static int MODE_CONTINUE = 6;
  
  private static int MODE_OPTION = 10;
  
  public int zoneNumber = 0;
  
  public int stageNumber = 0;
  
  public int selectZoneNumber = 0;
  
  public int selectStageNumber = 0;
  
  public int animeTimer = 1;
  
  public int cpuTimer = 1;
  
  private static int plmaxspd = 1536;
  
  private static int pladdspd = 12;
  
  private static int plretspd = 128;
  
  private static int plstaspd = 128;
  
  private static int gravity = 56;
  
  private static int pljump = 1664;
  
  private static int pljump_w = 896;
  
  private static int[] plspeed = new int[2];
  
  private static int[] ploldpos = new int[2];
  
  private static int falltimer;
  
  private static int nocoltimer;
  
  private static int kokyutimer;
  
  private static int noloopchecktimer;
  
  private static int olddir;
  
  private static int olddir2;
  
  private static int mutekicount;
  
  private static int muteki2count;
  
  private static int speedupcount;
  
  private static int bariacount;
  
  private static int oldringcount;
  
  private static int ringcount;
  
  private static int scorecount;
  
  private static int timecount;
  
  private static int timecount2;
  
  private static int diecount;
  
  private static int playercount;
  
  private static int plsaveX;
  
  private static int plsaveY;
  
  private static int plsaveTime;
  
  private static int plsaveTime2;
  
  private static int[] objectData = new int[25];
  
  private static int OBJA_MAX = 30;
  
  private static int[][] objAwaData = new int[OBJA_MAX][20];
  
  private static int[][] objData = new int[OBJA_MAX][10];
  
  private static boolean initDisplay = false;
  
  private static boolean readStageObjectFlag = false;
  
  private static boolean raidOn;
  
  private static boolean[] switchflag = new boolean[256];
  
  private static int raidObjectNum;
  
  private static int raidObjectNumSub;
  
  private static int PlayerH = 32;
  
  private static int SONIC_N = 1;
  
  private static int SONIC_S = 2;
  
  private static int LOGO = 3;
  
  private static int LOGOLINE = 4;
  
  private static int SYSTXT = 5;
  
  private static int WINDOW_RING = 6;
  
  private static int WINDOW_TIME = 7;
  
  private static int WINDOW_ZANKI = 8;
  
  private static int WINDOU_SUUJI = 9;
  
  private static int SYSSCORE = 10;
  
  private static int SYSTXT2 = 11;
  
  private static int T_CUR1 = 12;
  
  private static int T_CUR2 = 13;
  
  private static int GAMEOVER = 14;
  
  private static int TIMEOVER = 15;
  
  private static int RING = 0;
  
  private static int RING1 = 1;
  
  private static int SJUMP = 2;
  
  private static int BURANKO = 3;
  
  private static int HASHI = 4;
  
  private static int TOGE_HASHI = 5;
  
  private static int BREAK = 6;
  
  private static int YUKA = 7;
  
  private static int TURI = 8;
  
  private static int TOGE = 9;
  
  private static int BOX = 10;
  
  private static int FBLOCK = 11;
  
  private static int DAI = 12;
  
  private static int YOGAN = 14;
  
  private static int SWITCH2 = 15;
  
  private static int SHIMA = 16;
  
  private static int DAI2 = 17;
  
  private static int BRKABE = 18;
  
  private static int PEDAL = 19;
  
  private static int BREAK2 = 20;
  
  private static int STEP = 21;
  
  private static int FUN = 22;
  
  private static int SISOO = 23;
  
  private static int BELT = 24;
  
  private static int PATA = 25;
  
  private static int FIRE6 = 26;
  
  private static int SWITCH2_ = 27;
  
  private static int MAWARU = 28;
  
  private static int YUKAI = 29;
  
  private static int DOOR = 30;
  
  private static int YUKAE = 31;
  
  private static int DAI4 = 32;
  
  private static int ELE = 33;
  
  private static int BELTC = 34;
  
  private static int NOKO = 35;
  
  private static final int RING_SFLAG_RING_18_00 = 0;
  
  private static final int RING_SFLAG_RING_00_18 = 1;
  
  private static final int SJUMP_NFLAG = 2;
  
  private static final int BURANKO_NFLAG = 3;
  
  private static final int THASHI_NFLAG = 4;
  
  private static final int HASHI_NFLAG = 5;
  
  private static final int BREAK_SFLAG = 6;
  
  private static final int YUKA_NFLAG = 7;
  
  private static final int TURI_NFLAG = 8;
  
  private static final int TOGE_NFLAG = 9;
  
  private static final int BOX_SFLAG = 10;
  
  private static final int FBLOCK_NFLAG = 11;
  
  private static final int DAI_NFLAG = 12;
  
  private static final int YOGAN2_SFLAG = 13;
  
  private static final int MYOGAN_NFLAG = 14;
  
  private static final int SWITCH2_NFLAG = 15;
  
  private static final int SHIMA_NFLAG = 16;
  
  private static final int DAI2_NFLAG = 17;
  
  private static final int BRKABE_SFLAG = 18;
  
  private static final int PEDAL_NFLAG = 19;
  
  private static final int BREAK2_NFLAG = 20;
  
  private static final int STEP_NFLAG = 21;
  
  private static final int FUN_NFLAG = 22;
  
  private static final int SISOO_NFLAG = 23;
  
  private static final int BELT_NFLAG = 24;
  
  private static final int PATA_NFLAG = 25;
  
  private static final int FIRE6_NFLAG = 26;
  
  private static final int BRYUKA_NFLAG = 27;
  
  private static final int MAWARU_NFLAG = 28;
  
  private static final int YUKAI_NFLAG = 29;
  
  private static final int DOOR_NFLAG = 30;
  
  private static final int YUKAE_NFLAG = 31;
  
  private static final int DAI4_NFLAG = 32;
  
  private static final int ELE_NFLAG = 33;
  
  private static final int BELTC_NFLAG = 34;
  
  private static final int NOKO_NFLAG = 35;
  
  private static final int SAVE_SFLAG = 36;
  
  private static final int KAGEB_NFLAG = 37;
  
  private static final int BGSPR_NFLAG = 38;
  
  private static final int KAMERE_SFLAG = 39;
  
  private static final int HACHI_SFLAG = 40;
  
  private static final int MUSI_SFLAG = 41;
  
  private static final int ITEM_NFLAG = 42;
  
  private static final int ITEM_SFLAG = 43;
  
  private static final int GOLE_NFLAG = 44;
  
  private static final int BTEN_NFLAG = 45;
  
  private static final int BTEN_SFLAG = 46;
  
  private static final int BIGRING_NFLAG = 47;
  
  private static final int SCOLI_NFLAG = 48;
  
  private static final int IMO_SFLAG = 49;
  
  private static final int BROBO_SFLAG = 50;
  
  private static final int BUTA_SFLAG = 51;
  
  private static final int HAGURUMA_NFLAG = 52;
  
  private static final int SHOOTER_NFLAG = 53;
  
  private static final int DAINFLA = 54;
  
  private static final int MASIN_NFLAG = 55;
  
  private static final int BOBIN_SFLAG = 56;
  
  private static final int KANI_SFLAG = 57;
  
  private static final int JYAMA_NFLAG = 58;
  
  private static final int FETAMA_NFLAG = 59;
  
  private static final int TEKYU_NFLAG = 60;
  
  private static final int SIGNAL_NFLAG = 61;
  
  private static final int DAI2_SFLAG = 62;
  
  private static final int RING_SFLAG_RING_M10_10 = 63;
  
  private static final int RING_SFLAG_RING_10_10 = 64;
  
  private static final int RING_SFLAG_RING_20_20 = 65;
  
  private static final int RING_SFLAG_RING_10_00 = 66;
  
  private static final int RING_SFLAG_RING_20_00 = 67;
  
  private static final int RING_SFLAG_RING_00_10 = 68;
  
  private static final int RING_SFLAG_RING_00_20 = 69;
  
  private static final int ARUMA_SFLAG = 70;
  
  private static final int YADO_SFLAG = 71;
  
  private static final int ELEV_NFLAG_80 = 72;
  
  private static final int ELEV_NFLAG = 73;
  
  private static final int UNI_SFLAG = 74;
  
  private static final int MFIRE_NFLAG = 75;
  
  private static final int HASHIRA_NFLAG = 76;
  
  private static final int YOGANC_NFLAG = 77;
  
  private static final int BAT_SFLAG = 78;
  
  private static final int OCHI_NFLAG = 79;
  
  private static final int YARI_SFLAG = 80;
  
  private static final int MOGURA_SFLAG = 81;
  
  private static final int KAZARI_SFLAG = 82;
  
  private static final int DAI3_NFLAG = 83;
  
  private static final int MIZU_NFLAG = 84;
  
  private static final int AWA_NFLAG = 85;
  
  private static final int FISH_SFLAG = 86;
  
  private static final int FISH2_SFLAG = 87;
  
  private static final int KASSYA_NFLAG = 88;
  
  private static final int TAKI_NFLAG = 89;
  
  private static final int SHIMA2_NFLAG = 90;
  
  private static final int BOU_NFLAG = 91;
  
  private static final int BEN_NFLAG = 92;
  
  private static final int BEN_SFLAG = 93;
  
  private static final int TURI2 = 94;
  
  private static final int TURI3 = 95;
  
  private static final int TAMA = 96;
  
  private static final int BAKUHATU = 97;
  
  private static final int MYOGAN2 = 98;
  
  private static final int YOGAN2 = 99;
  
  private static final int ANIMAL = 100;
  
  private static final int _FIRE = 101;
  
  private static final int BLOCK = 102;
  
  private static final int OBJAWA = 104;
  
  private static final int DAI3_0x27 = 105;
  
  private static final int DAI3_0x13 = 106;
  
  private static final int DAI2_0xE0 = 107;
  
  private static final int DAI2_0xF0 = 108;
  
  private static final int EFFECT = 109;
  
  private static final int MIZU_0x09 = 110;
  
  private static final int WATER2 = 111;
  
  private static final int BOSS1 = 120;
  
  private static final int BOSS2 = 125;
  
  private static final int BOSS3 = 130;
  
  private static final int BOSS4 = 135;
  
  private static final int BOSS5 = 140;
  
  private static final int BOSS6 = 145;
  
  private static final int BOSS5BLOCK = 150;
  
  private static int[] objectDrawList = new int[200];
  
  private static int objectDrawCount = 0;
  
  private static int MapW;
  
  private static int MapH;
  
  private static int mode;
  
  private static int BossFirst = -1;
  
  private static int Target;
  
  private static int connCount;
  
  private static int connPos;
  
  private static int comSel;
  
  private static int Window = 1;
  
  private static int[] mapOxy = new int[2];
  
  private static int[] oldMapOxy = new int[2];
  
  private static int[] mapView = new int[2];
  
  private static int[] mapViewTarget = new int[2];
  
  private static int[] mapOfs = new int[2];
  
  private static int[] mapOfsTarget = new int[2];
  
  private static byte[] mapData = new byte[42496];
  
  private static byte[] mapFrontData = new byte[600];
  
  private static byte[] blockLinkTable = new byte[600];
  
  private static byte[] blockColTable = new byte[8192];
  
  private static byte[][] zoneActTable = new byte[4][];
  
  private static int[] zoneActTable2;
  
  private static byte[][] tempWorldMapData;
  
  private static final byte[][][][] worldMapData = new byte[][][][] { { { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 56, 1, 1, 1, 36, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 56, 36, 
            0, 0, 33, 38, 17, 17, 31, 15, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 45, 49, 36, 20, 56, 4, 35, 37, 
            45, 53, 38, 17, 31, 30, 32, 15, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            45, 45, 3, 49, 36, 16, 2, 7, 4, 5, 
            43, 14, 30, 17, 37, 26, 38, 17, 8, 9, 
            10, 23, 30, 30, 32, 17, 31, 15, 0, 16, 
            5, 43, 22, 2, 3, 55, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 30, 30, 30, 32, 37, 7, 34, 
            12, 13, 21, 25, 17, 37, 45, 45, 45 } }, { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0 }, { 
            14, 43, 22, 28, 5, 43, 22, 2, 55, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 33, 3, 0, 0, 0, 0, 33, 49, 36, 
            0, 0, 0 }, { 
            12, 13, 6, 12, 30, 13, 21, 17, 37, 50, 
            43, 11, 45, 53, 45, 7, 36, 18, 56, 36, 
            45, 38, 8, 5, 43, 11, 33, 38, 31, 15, 
            0, 0, 0 }, { 
            30, 30, 30, 30, 10, 23, 30, 30, 12, 12, 
            13, 25, 17, 8, 23, 30, 30, 30, 30, 30, 
            30, 30, 30, 24, 13, 8, 35, 17, 32, 37, 
            45, 45, 45 }, { 
            30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 
            30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 
            0, 30, 30, 10, 23, 30, 17, 8, 29, 9, 
            30, 30, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 30, 30, 30, 30, 30, 30, 30, 30, 
            30, 30, 0 } }, { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 45, 55, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 19, 56, 24, 15, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 0, 19, 56, 36, 20, 0, 19, 56, 
            39, 2, 26, 38, 31, 15, 0, 0, 20, 33, 
            45, 0, 0, 0, 19, 51, 51, 16, 36, 44, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 }, { 
            45, 55, 22, 27, 35, 37, 26, 3, 6, 38, 
            31, 30, 30, 30, 32, 37, 53, 49, 27, 35, 
            17, 51, 51, 2, 26, 52, 52, 25, 37, 6, 
            49, 39, 0, 22, 2, 7, 2, 55, 45, 45, 
            45, 60, 60, 45, 45 }, { 
            30, 15, 21, 30, 30, 30, 30, 30, 30, 30, 
            32, 31, 30, 30, 30, 30, 30, 30, 12, 17, 
            9, 52, 52, 17, 30, 30, 30, 30, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 
            30, 32, 17, 8, 9, 10, 29, 9, 30, 30, 
            30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 } }, { { 
            40, 41, 42, 45, 49, 39, 2, 49, 34, 37, 
            3, 49, 2, 49, 39 } } }, { { { 
            25, 42, 39, 41, 26, 23, 14, 33, 23, 0, 
            0, 0, 39, 29, 36, 13, 14, 14, 14, 14, 
            14, 14, 33, 23, 23, 23, 23, 0, 0, 0, 
            0, 0 }, { 
            14, 14, 14, 14, 14, 37, 12, 34, 14, 20, 
            0, 0, 14, 14, 14, 2, 10, 9, 10, 9, 
            21, 12, 34, 31, 1, 14, 14, 14, 0, 0, 
            0, 0 }, { 
            0, 0, 0, 0, 14, 14, 14, 14, 14, 12, 
            45, 14, 14, 14, 14, 11, 58, 14, 60, 25, 
            29, 29, 29, 30, 14, 0, 0, 0, 0, 0, 
            0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 14, 14, 
            24, 9, 45, 14, 17, 15, 59, 14, 61, 17, 
            27, 14, 14, 14, 14, 0, 0, 0, 0, 0, 
            0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 
            14, 14, 46, 12, 15, 17, 15, 14, 62, 15, 
            16, 14, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 14, 14, 14, 14, 25, 26, 12, 11, 11, 
            15, 14, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 14, 14, 14, 14, 14, 14, 
            14, 14, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0 }, { 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14 } }, { { 
            23, 11, 23, 13, 14, 14, 14, 14, 14, 14, 
            14, 14, 0, 0, 0, 0, 0, 0, 0 }, { 
            1, 1, 1, 2, 3, 11, 14, 17, 0, 11, 
            23, 14, 0, 0, 0, 0, 0, 0, 0 }, { 
            14, 14, 14, 11, 76, 5, 20, 46, 45, 14, 
            19, 14, 14, 0, 0, 0, 0, 0, 0 }, { 
            33, 23, 12, 46, 14, 14, 14, 45, 46, 14, 
            15, 14, 14, 63, 28, 0, 0, 0, 0 }, { 
            47, 21, 12, 23, 34, 9, 10, 46, 15, 14, 
            19, 14, 14, 64, 14, 1, 14, 14, 14 }, { 
            14, 14, 14, 14, 14, 14, 14, 14, 17, 11, 
            15, 11, 12, 65, 14, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 14, 21, 12, 
            11, 15, 14, 14, 14, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 14, 14, 14, 
            14, 14, 14, 0, 0, 0, 0, 0, 0 } }, { { 
            0, 0, 0, 0, 0, 14, 14, 33, 77, 14, 
            14, 14, 14, 14, 0, 0, 0, 14, 14, 14, 
            14, 14, 14, 14, 14, 14, 14, 16, 14, 14, 
            0, 0, 0, 0, 0 }, { 
            14, 14, 14, 14, 14, 14, 32, 75, 14, 17, 
            11, 11, 11, 14, 14, 14, 0, 14, 17, 33, 
            0, 13, 14, 14, 14, 14, 23, 15, 14, 14, 
            49, 1, 14, 14, 14 }, { 
            11, 11, 13, 14, 12, 17, 75, 14, 17, 0, 
            46, 39, 66, 68, 39, 14, 0, 14, 70, 71, 
            14, 2, 3, 18, 14, 14, 16, 14, 14, 14, 
            49, 14, 0, 0, 0 }, { 
            14, 14, 2, 3, 18, 8, 20, 35, 36, 11, 
            23, 40, 67, 69, 34, 14, 14, 14, 20, 45, 
            23, 12, 23, 4, 18, 33, 15, 14, 0, 14, 
            49, 14, 0, 0, 0 }, { 
            0, 14, 14, 14, 4, 74, 12, 37, 14, 17, 
            22, 14, 36, 12, 34, 9, 10, 36, 12, 45, 
            45, 11, 22, 14, 72, 0, 14, 14, 14, 14, 
            49, 14, 0, 0, 0 }, { 
            0, 0, 0, 14, 14, 4, 18, 36, 23, 15, 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 43, 
            43, 44, 14, 14, 16, 14, 14, 14, 23, 23, 
            15, 14, 0, 0, 0 }, { 
            0, 0, 0, 0, 14, 14, 4, 18, 43, 34, 
            38, 10, 9, 10, 9, 41, 29, 41, 42, 29, 
            41, 42, 0, 11, 37, 14, 35, 14, 16, 14, 
            14, 14, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 14, 14, 4, 18, 14, 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14, 46, 15, 14, 14, 15, 33, 15, 14, 
            14, 14, 0, 0, 0 } }, { { 
            14, 14, 14, 14, 14, 17, 39, 41, 26, 17, 
            6, 73, 14, 14, 48, 14, 14, 14, 14, 14, 
            14, 14, 14 }, { 
            14, 33, 11, 18, 14, 22, 14, 11, 6, 5, 
            11, 17, 23, 18, 16, 14, 14, 14, 14, 14, 
            14, 14, 14 }, { 
            14, 31, 14, 47, 27, 35, 14, 20, 11, 12, 
            15, 1, 35, 43, 15, 14, 14, 14, 14, 14, 
            14, 14, 14 }, { 
            14, 43, 11, 14, 46, 15, 14, 14, 1, 43, 
            18, 14, 37, 14, 19, 14, 11, 11, 14, 14, 
            14, 14, 14 }, { 
            14, 45, 0, 17, 44, 19, 14, 12, 35, 17, 
            43, 14, 43, 34, 25, 18, 37, 24, 38, 41, 
            42, 23, 14 }, { 
            33, 45, 0, 46, 12, 46, 14, 12, 38, 31, 
            23, 12, 11, 14, 14, 47, 30, 14, 14, 14, 
            14, 16, 14 }, { 
            14, 45, 12, 34, 14, 43, 11, 11, 11, 23, 
            44, 14, 47, 10, 9, 10, 9, 10, 9, 27, 
            12, 15, 14 }, { 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 
            14, 14, 14 } } }, { { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 6, 11, 8, 9, 0, 
            0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 
            0, 0, 0, 0, 0, 0 }, { 
            1, 52, 2, 2, 3, 5, 18, 18, 10, 8, 
            11, 7, 7, 7, 9, 12, 32, 12, 11, 8, 
            3, 8, 11, 16, 16, 16 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 
            15, 15, 14, 14, 73, 13, 24, 22, 32, 32, 
            18, 18, 18, 18, 18, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 
            39, 40, 32, 32, 31, 49, 25, 32, 18, 18, 
            18, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 
            17, 33, 20, 20, 20, 69, 22, 32, 0, 0, 
            0, 0, 0, 0, 0, 0 } }, { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 32, 32, 0, 0, 0, 
            0, 6, 8, 53, 16, 9, 0, 0, 32, 32, 
            32, 32, 32, 32, 0, 0, 0 }, { 
            52, 8, 53, 7, 9, 32, 32, 16, 9, 11, 
            2, 5, 18, 18, 32, 10, 8, 9, 32, 32, 
            32, 32, 32, 12, 53, 53, 53 }, { 
            18, 51, 32, 43, 73, 17, 21, 18, 46, 30, 
            29, 42, 42, 42, 42, 32, 18, 46, 40, 32, 
            32, 42, 25, 22, 32, 32, 0 }, { 
            0, 18, 26, 44, 20, 23, 22, 42, 42, 31, 
            49, 48, 47, 47, 45, 32, 68, 30, 22, 32, 
            25, 22, 32, 32, 32, 0, 0 }, { 
            0, 18, 41, 27, 27, 27, 30, 17, 38, 36, 
            36, 36, 23, 17, 22, 32, 44, 20, 20, 23, 
            22, 32, 32, 0, 0, 0, 0 } }, { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0 }, { 
            1, 8, 53, 16, 53, 9, 0, 0, 32, 32, 
            32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 
            0, 0, 0, 0, 0, 0, 0, 0 }, { 
            32, 32, 32, 32, 70, 73, 8, 12, 32, 32, 
            32, 32, 32, 32, 32, 32, 32, 32, 32, 12, 
            11, 3, 71, 11, 72, 74, 53, 53 }, { 
            26, 33, 25, 17, 22, 32, 26, 22, 32, 32, 
            32, 32, 32, 32, 26, 69, 25, 25, 0, 22, 
            32, 32, 18, 32, 0, 0, 0, 0 }, { 
            21, 14, 15, 30, 36, 36, 22, 18, 32, 32, 
            32, 32, 32, 32, 39, 42, 0, 0, 0, 0, 
            42, 32, 32, 32, 0, 0, 0, 0 }, { 
            28, 34, 69, 22, 32, 37, 29, 14, 30, 37, 
            49, 31, 49, 49, 49, 22, 47, 47, 47, 22, 
            39, 42, 32, 0, 0, 0, 0, 0 }, { 
            32, 35, 30, 20, 20, 36, 13, 32, 26, 22, 
            32, 32, 32, 42, 42, 14, 14, 40, 43, 40, 
            29, 25, 32, 0, 0, 0, 0, 0 }, { 
            32, 32, 32, 32, 32, 32, 32, 32, 39, 30, 
            38, 20, 20, 23, 22, 32, 32, 33, 44, 44, 
            17, 22, 32, 0, 0, 0, 0, 0 } } }, { { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 1, 8, 10, 0, 0, 0, 0, 
            1, 24, 5, 1, 8, 0, 0, 0, 0, 51, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 1, 10, 51, 51, 51, 51, 0, 
            0, 0, 15, 51, 6, 7, 24, 0, 0, 0, 
            0, 0, 0, 0, 6, 7, 42, 15, 24, 51, 
            0, 0, 0, 0 }, { 
            1, 1, 38, 51, 12, 5, 8, 63, 47, 5, 
            24, 3, 12, 4, 1, 51, 51, 1, 1, 14, 
            24, 4, 18, 5, 8, 51, 47, 3, 12, 18, 
            4, 18, 18, 18 }, { 
            51, 51, 29, 51, 0, 0, 6, 7, 46, 51, 
            51, 4, 3, 1, 1, 51, 51, 51, 35, 20, 
            51, 35, 20, 32, 6, 7, 46, 51, 32, 24, 
            0, 0, 0, 0 }, { 
            0, 51, 51, 51, 0, 0, 51, 51, 11, 51, 
            51, 0, 51, 51, 0, 1, 40, 3, 12, 19, 
            1, 24, 19, 31, 3, 12, 18, 24, 31, 18, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 51, 41, 8, 
            51, 0, 3, 12, 3, 12, 5, 40, 5, 0, 
            51, 0, 4, 0, 0, 0, 51, 51, 0, 4, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 51, 51, 6, 
            7, 1, 42, 40, 0, 3, 0, 12, 5, 24, 
            3, 0, 0, 0, 4, 3, 4, 5, 24, 0, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 
            51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0 } }, { { 
            0, 0, 20, 1, 8, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 1, 40, 5, 40, 5, 40, 
            5, 40, 4, 51, 51, 0, 51, 0, 1, 51, 
            0, 0, 0, 0 }, { 
            21, 21, 19, 51, 6, 7, 52, 0, 0, 0, 
            20, 1, 38, 3, 51, 20, 21, 5, 42, 42, 
            1, 24, 5, 15, 51, 0, 3, 0, 51, 51, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 51, 51, 51, 41, 42, 40, 36, 
            37, 8, 21, 3, 12, 19, 0, 1, 40, 3, 
            0, 1, 4, 3, 12, 0, 51, 0, 5, 32, 
            1, 1, 1, 1 }, { 
            0, 0, 0, 0, 0, 51, 51, 51, 0, 0, 
            0, 6, 7, 52, 51, 0, 0, 51, 0, 51, 
            0, 51, 0, 51, 0, 0, 15, 0, 15, 39, 
            24, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 51, 51, 11, 0, 18, 40, 21, 5, 52, 
            51, 51, 20, 51, 0, 4, 29, 15, 29, 0, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 51, 51, 41, 24, 51, 51, 51, 51, 41, 
            8, 24, 19, 0, 4, 0, 15, 29, 29, 0, 
            0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 51, 51, 51, 51, 0, 0, 0, 51, 
            6, 7, 42, 24, 21, 0, 29, 29, 29, 0, 
            0, 0, 0, 0 } }, { { 
            0, 0, 0, 0, 0, 1, 24, 5, 9, 5, 
            1, 24, 5, 20, 52, 51, 51, 0, 0, 14, 
            8, 0, 51, 29, 14, 24, 5, 8, 0, 51, 
            0, 0, 0, 0, 0 }, { 
            21, 21, 25, 23, 0, 18, 0, 0, 6, 7, 
            52, 51, 47, 19, 41, 52, 51, 35, 18, 10, 
            6, 7, 52, 29, 14, 10, 51, 6, 7, 52, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 51, 26, 22, 0, 4, 14, 32, 51, 
            41, 1, 46, 51, 51, 41, 42, 40, 21, 10, 
            51, 51, 41, 24, 10, 4, 5, 10, 51, 41, 
            20, 1, 1, 1, 1 }, { 
            0, 0, 51, 2, 27, 0, 0, 0, 31, 5, 
            20, 51, 41, 40, 13, 22, 0, 0, 3, 12, 
            5, 14, 24, 5, 20, 38, 3, 12, 35, 18, 
            19, 51, 0, 0, 0 }, { 
            0, 0, 0, 51, 28, 0, 20, 14, 24, 18, 
            19, 0, 0, 0, 2, 27, 0, 0, 51, 0, 
            14, 0, 3, 12, 19, 3, 12, 18, 24, 3, 
            12, 51, 0, 0, 0 }, { 
            0, 0, 0, 51, 30, 21, 19, 10, 51, 51, 
            14, 0, 0, 15, 63, 28, 20, 33, 51, 1, 
            10, 21, 51, 1, 0, 0, 0, 1, 20, 51, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 0, 51, 0, 0, 4, 3, 12, 3, 
            10, 0, 0, 29, 10, 30, 19, 10, 0, 20, 
            14, 0, 5, 24, 3, 12, 4, 4, 19, 51, 
            0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 
            10, 0, 0, 29, 21, 4, 3, 12, 4, 19, 
            10, 0, 0, 51, 51, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0 } } }, { { { 
            36, 47, 36, 66, 12, 36, 56, 71, 1, 36, 
            36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 
            36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 
            2, 36, 2, 71, 1, 36, 0 }, { 
            36, 36, 36, 66, 10, 73, 1, 71, 1, 1, 
            43, 36, 36, 4, 14, 19, 30, 55, 25, 28, 
            6, 36, 2, 4, 36, 36, 28, 73, 36, 36, 
            1, 36, 1, 71, 1, 36, 0 }, { 
            36, 36, 36, 8, 9, 45, 1, 14, 15, 17, 
            1, 36, 36, 1, 1, 20, 22, 23, 23, 29, 
            1, 36, 1, 1, 71, 74, 29, 43, 43, 2, 
            1, 2, 11, 2, 2, 2, 2 }, { 
            6, 2, 7, 1, 1, 1, 1, 1, 16, 18, 
            5, 33, 2, 15, 17, 1, 1, 1, 1, 1, 
            1, 2, 34, 37, 70, 57, 67, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 0 }, { 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 16, 18, 21, 27, 21, 24, 21, 
            27, 21, 35, 38, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 0 } }, { { 
            36, 36, 38, 36, 36, 40, 47, 47, 36, 36, 
            56, 36, 36, 36, 36, 36, 36, 36, 36, 36, 
            36, 36, 36, 36, 36, 36, 36, 1, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0 }, { 
            2, 2, 44, 36, 36, 36, 36, 36, 36, 36, 
            1, 71, 1, 36, 49, 3, 50, 48, 36, 36, 
            36, 36, 36, 2, 6, 36, 36, 1, 36, 36, 
            36, 36, 36, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0 }, { 
            1, 1, 1, 36, 36, 36, 28, 73, 36, 56, 
            1, 71, 1, 56, 40, 1, 52, 51, 50, 48, 
            36, 36, 28, 15, 17, 4, 13, 4, 36, 36, 
            25, 36, 36, 36, 36, 38, 36, 36, 1, 36, 
            0, 0, 0 }, { 
            1, 1, 1, 36, 2, 26, 29, 43, 43, 1, 
            1, 71, 1, 1, 1, 1, 1, 1, 52, 31, 
            36, 4, 29, 16, 18, 21, 41, 5, 33, 26, 
            23, 14, 6, 73, 32, 44, 36, 36, 1, 36, 
            36, 36, 0 }, { 
            1, 1, 1, 36, 46, 17, 1, 1, 1, 1, 
            1, 71, 1, 1, 1, 1, 1, 1, 1, 1, 
            56, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 40, 45, 1, 1, 36, 36, 58, 32, 
            33, 2, 2 }, { 
            1, 1, 1, 45, 16, 18, 21, 27, 21, 24, 
            5, 70, 57, 67, 57, 68, 68, 68, 68, 68, 
            69, 1, 1, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 1, 1, 1, 53, 2, 54, 32, 
            33, 2, 2 } }, { { 
            56, 36, 36, 36, 36, 36, 36, 0, 0, 0, 
            0, 1, 36, 36, 25, 36, 2, 36, 36, 36, 
            36, 6, 6, 44, 36, 1, 1, 1, 1, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            1, 49, 3, 50, 48, 36, 36, 36, 36, 36, 
            36, 1, 36, 36, 23, 4, 1, 36, 36, 36, 
            4, 71, 68, 1, 36, 1, 1, 1, 1, 36, 
            36, 36, 36, 36, 36, 71, 1, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            0, 1, 1, 52, 51, 50, 48, 36, 36, 2, 
            6, 1, 36, 6, 42, 21, 5, 33, 73, 36, 
            54, 71, 1, 1, 36, 1, 1, 1, 1, 36, 
            36, 56, 36, 36, 36, 71, 1, 1, 36, 36, 
            47, 47, 40, 0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 1, 1, 52, 31, 36, 2, 15, 
            17, 1, 4, 71, 1, 1, 1, 1, 43, 43, 
            1, 70, 40, 1, 36, 40, 1, 1, 1, 36, 
            36, 1, 43, 43, 43, 71, 1, 12, 36, 36, 
            36, 36, 11, 36, 36, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 1, 1, 36, 1, 16, 
            18, 27, 5, 71, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 36, 46, 17, 1, 1, 4, 
            13, 1, 1, 1, 1, 71, 1, 10, 36, 56, 
            36, 36, 36, 36, 36, 36, 36, 36, 0 }, { 
            0, 0, 0, 0, 0, 0, 1, 4, 1, 1, 
            1, 1, 1, 4, 57, 67, 11, 1, 1, 1, 
            1, 1, 1, 1, 36, 16, 18, 24, 39, 39, 
            41, 24, 21, 27, 5, 72, 8, 9, 36, 1, 
            36, 36, 36, 4, 36, 64, 4, 4, 4 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 45, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 
            36, 36, 36, 1, 36, 66, 1, 1, 0 } } }, { { { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            6, 0, 0, 22, 1, 14, 16, 16, 0, 0, 
            0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 9, 0, 34, 
            2, 34, 0, 17, 16, 12, 3, 16, 16, 16, 
            0, 0, 16, 16, 16, 0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 
            34, 0, 0, 0, 0, 46, 45, 16, 0, 16, 
            44, 16, 13, 3, 16, 16, 5, 16, 16, 16, 
            30, 15, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 6, 0, 18, 19, 17, 
            16, 0, 0, 9, 45, 29, 0, 15, 0, 16, 
            12, 7, 8, 5, 16, 16, 4, 75, 47, 26, 
            42, 43, 24, 20, 18, 18 }, { 
            1, 9, 22, 22, 34, 2, 1, 7, 3, 16, 
            16, 0, 0, 15, 0, 0, 0, 22, 22, 1, 
            14, 16, 16, 4, 75, 31, 47, 33, 33, 26, 
            42, 43, 16, 16, 16, 0 }, { 
            16, 16, 17, 17, 16, 12, 3, 16, 5, 16, 
            16, 27, 24, 20, 18, 45, 0, 17, 17, 16, 
            12, 7, 9, 33, 33, 28, 16, 0, 46, 0, 
            32, 43, 16, 16, 16, 0 }, { 
            16, 16, 17, 17, 16, 16, 5, 16, 4, 75, 
            47, 28, 16, 16, 16, 17, 16, 16, 30, 33, 
            1, 21, 21, 9, 34, 18, 18, 45, 29, 0, 
            0, 0, 0, 0, 0, 0 }, { 
            0, 0, 0, 0, 0, 16, 4, 75, 7, 21, 
            21, 21, 9, 75, 47, 75, 47, 34, 25, 17, 
            16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 
            0, 0, 0, 0, 0, 0 } }, { { 
            16, 16, 16, 16, 30, 30, 30, 44, 11, 1, 
            47, 33, 33, 33, 33, 33, 75, 50, 36, 50, 
            9, 10, 16, 30, 16, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            11, 1, 36, 47, 34, 0, 0, 10, 11, 75, 
            31, 47, 34, 49, 48, 48, 15, 30, 16, 0, 
            33, 26, 42, 43, 16, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            44, 16, 16, 16, 16, 16, 16, 13, 8, 16, 
            28, 30, 16, 9, 1, 23, 24, 20, 18, 19, 
            17, 26, 42, 43, 16, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            11, 1, 36, 36, 47, 33, 33, 10, 16, 30, 
            15, 41, 75, 36, 50, 36, 47, 10, 16, 16, 
            16, 26, 42, 43, 16, 16, 16, 16, 16, 16, 
            16, 16, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            16, 16, 16, 15, 15, 33, 33, 34, 18, 19, 
            40, 40, 16, 16, 16, 13, 7, 8, 11, 1, 
            47, 26, 42, 43, 15, 15, 15, 15, 30, 30, 
            30, 16, 16, 0, 0, 0, 0, 0, 0, 0 }, { 
            16, 15, 0, 41, 41, 17, 17, 16, 11, 75, 
            47, 75, 36, 36, 9, 10, 16, 16, 44, 16, 
            11, 34, 32, 43, 24, 20, 37, 38, 1, 9, 
            1, 68, 69, 70, 68, 68, 71, 72, 73, 74 }, { 
            16, 0, 0, 40, 22, 1, 9, 10, 11, 1, 
            9, 0, 0, 1, 36, 50, 36, 47, 10, 16, 
            44, 16, 16, 16, 16, 16, 16, 16, 16, 16, 
            16, 16, 0, 0, 0, 0, 0, 0, 0, 0 }, { 
            18, 19, 39, 40, 17, 16, 16, 44, 16, 16, 
            16, 0, 0, 16, 16, 15, 15, 15, 16, 16, 
            12, 14, 16, 16, 16, 16, 16, 16, 16, 16, 
            16, 16, 16, 0, 0, 0, 0, 0, 0, 0 } }, { {} }, { { 69, 70, 68, 68, 71, 72, 73, 74 } } } };
  
  private static byte[] scddirtbl = new byte[512];
  
  private static boolean pauseGame = false;
  
  private static int[] PlayerParam = new int[26];
  
  private static boolean PlayerSJump = false;
  
  private static boolean PlayerDamage = false;
  
  private static boolean PlayerWater = false;
  
  private static boolean PlayerSWater = false;
  
  private static boolean PlayerBou = false;
  
  private static boolean PlayerJump = false;
  
  private static boolean PlayerAir = false;
  
  private static boolean PlayerBall = false;
  
  private static boolean PlayerDie = false;
  
  private static boolean PlayerCrouch = false;
  
  private static boolean PlayerLookUp = false;
  
  private static boolean PlayerNoCol = false;
  
  private static boolean PlayerNoCtrl = false;
  
  private static boolean TimerClear = false;
  
  private static boolean TimerStop = false;
  
  private static boolean PlayerDush;
  
  private boolean[] crushing = new boolean[4];
  
  private static final int[] sinData = new int[] { 
      0, 175, 349, 523, 698, 872, 1045, 1219, 1392, 1564, 
      1736, 1908, 2079, 2249, 2419, 2588, 2756, 2924, 3090, 3256, 
      3420, 3584, 3746, 3907, 4067, 4226, 4384, 4540, 4695, 4848, 
      5000, 5150, 5299, 5446, 5592, 5736, 5878, 6018, 6156, 6293, 
      6428, 6560, 6691, 6820, 6946, 7071, 7193, 7313, 7431, 7547, 
      7660, 7771, 7880, 7986, 8090, 8191, 8290, 8387, 8480, 8572, 
      8660, 8746, 8829, 8910, 8988, 9063, 9135, 9205, 9272, 9336, 
      9397, 9455, 9510, 9563, 9613, 9659, 9703, 9744, 9781, 9816, 
      9848, 9877, 9903, 9925, 9945, 9962, 9976, 9986, 9994, 9998, 
      10000 };
  
  private static byte[] scdtblwk = new byte[8192];
  
  private static int FontPos;
  
  private static long getTime2;
  
  private static int MapEndCounter = 0;
  
  private static boolean bossModeOn = false;
  
  private static boolean bossBreakOn = false;
  
  private static short[][] objectSizeTbl = new short[][] { 
      { 48, 48 }, { 48, 48 }, { 48, 48 }, { 160, 160 }, { 192, 48 }, { 192, 48 }, { 480, 480 }, { 128, 96 }, { 48, 48 }, { 48, 48 }, 
      { 32, 32 }, { 32, 32 }, { 240, 24 }, { 240, 48 }, { 64, 240 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, 
      { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 240, 48 }, { 64, 24 }, { 48, 48 }, { 48, 48 }, { 94, 94 }, { 192, 48 }, 
      { 16, 32 }, { 48, 48 }, { 480, 480 }, { 48, 48 }, { 480, 480 }, { 240, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 40, 32 }, 
      { 32, 32 }, { 32, 24 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 64, 64 }, { 48, 48 }, { 16, 16 }, 
      { 24, 24 }, { 24, 40 }, { 48, 48 }, { -1, -1 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 40, 24 }, { 48, 48 }, { 48, 48 }, 
      { 48, 48 }, { 48, 48 }, { -1, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, 
      { 32, 32 }, { 28, 20 }, { 48, 48 }, { 48, 48 }, { 16, 16 }, { 48, 48 }, { 48, 48 }, { 240, 240 }, { 32, 24 }, { 48, 48 }, 
      { 48, 48 }, { 24, 40 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 24, 32 }, { 40, 32 }, { 48, 480 }, { 48, 48 }, 
      { 48, 48 }, { 48, 48 }, { 48, 48 }, { 48, 48 }, { 128, 128 }, { 128, 128 }, { 32, 32 }, { 32, 32 }, { 128, 128 }, { 128, 128 }, 
      { 40, 40 }, { 32, 40 } };
  
  private static int TRANS_NONE = 0;
  
  private static int TRANS_ROT90 = 1;
  
  private static int TRANS_ROT180 = 2;
  
  private static int TRANS_ROT270 = 3;
  
  private static int TRANS_MIRROR = 4;
  
  private static int TRANS_MIRROR_ROT90 = 5;
  
  private static int TRANS_MIRROR_ROT180 = 6;
  
  private static int TRANS_MIRROR_ROT270 = 7;
  
  public static final int[] rotNumTable = new int[] { 0, 5, 3, 6, 2, 7, 1, 4 };
  
  static final int[][] encZoneNumber = new int[][] { { 0, 0, 0 }, { 2, 2, 2 }, { 4, 4, 4 }, { 1, 1, 1 }, { 3, 3, 3 }, { 5, 5, 1 }, { 5, 0 } };
  
  static final int[][] encStageNumber = new int[][] { { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 3 }, { 3, 3 } };
  
  private static int cutDrawVLine = 0;
  
  int displayOffsetY;
  
  int displayOffsetY2;
  
  private byte resumeStage;
  
  private byte resumeZanki;
  
  private int resumeScore;
  
  private byte clearStageData;
  
  public int MODE_FIELD_PAUSE = 10;
  
  public int pauseTimer = 0;
  
  public int pauseSelect = 0;
  
  byte[] oldm_nConfigValue = new byte[4];
  
  public static String[] softKeys = new String[33];
  
  boolean SetSoftFlag;
  
  int SetSoftCount;
  
  int drawRsmCount;
  
  int wipeCount;
  
  boolean outWipe;
  
  boolean putWipe;
  
  boolean playerDraw;
  
  private byte[] imageOffset = new byte[21248];
  
  private byte[] rot = new byte[21248];
  
  private byte[] hitChk = new byte[21248];
  
  private byte[] hitChk2 = new byte[21248];
  
  private int[][][] drawMapData = new int[20][20][4];
  
  boolean drawRingFlag;
  
  boolean drawTimeFlag;
  
  boolean drawZankiFlag;
  
  int oldRingCount = 0;
  
  int oldScoreCount = 0;
  
  int oldTimeCount = 0;
  
  int oldZankiCount = 0;
  
  int[][] kyuryuTable = new int[][] { { 2688, 784, 3088, 912 }, { 3968, 272, 5136, 400 }, { 1120, 1040, 1808, 1168 }, { 2592, 1552, 5648, 1776 }, { 3200, 1568, 5072, 1680 } };
  
  private boolean goleFlag = false;
  
  private int golecount;
  
  private int scoreGetcount;
  
  private int scoreGetcountMax;
  
  private int SysStringMax = 10;
  
  private int[][] SysString = new int[this.SysStringMax][15];
  
  private int SysCenter;
  
  private int SysCount;
  
  private int GREEN_HILL = 0;
  
  private int FINAL = 1;
  
  private int MARBLE = 2;
  
  private int ZONE = 3;
  
  private int ACT1 = 4;
  
  private int ACT2 = 5;
  
  private int ACT3 = 6;
  
  private int SPRING_YARD = 7;
  
  private int ACT = 8;
  
  private int SCRAP_BRAIN = 9;
  
  private int DAEN_B = 10;
  
  private int STAR_LIGHT = 11;
  
  private int LABYRINTH = 12;
  
  private int DAEN_Y = 13;
  
  private int SONIC_HAS = 14;
  
  private int PASSED = 15;
  
  private int SPECIAL_STAGE = 16;
  
  private int CHAOS_EMERALDS = 17;
  
  int[][] SystxtTable = new int[][] { 
      { 0, 0, 112, 16 }, { 112, 0, 56, 16 }, { 0, 16, 80, 16 }, { 80, 16, 48, 16 }, { 128, 16, 8, 16 }, { 136, 16, 16, 16 }, { 152, 16, 16, 16 }, { 0, 32, 128, 16 }, { 128, 32, 24, 8 }, { 0, 48, 128, 16 }, 
      { 128, 40, 40, 40 }, { 0, 64, 120, 16 }, { 0, 80, 104, 16 }, { 104, 80, 40, 40 }, { 0, 96, 104, 16 }, { 0, 112, 72, 16 }, { 0, 128, 152, 16 }, { 0, 144, 176, 16 } };
  
  int[] zonetable = new int[] { this.GREEN_HILL, this.MARBLE, this.SPRING_YARD, this.LABYRINTH, this.STAR_LIGHT, this.SCRAP_BRAIN, this.FINAL };
  
  public boolean scoreMoveFlag = false;
  
  public boolean limitBreak = false;
  
  public int resultRing = 0;
  
  public int resultTime = 0;
  
  int blockColCount;
  
  int enemyBlock;
  
  public int pushCount = 0;
  
  public int bressCount = 2100;
  
  public int CrouchCount = 0;
  
  public int LookUpCount = 0;
  
  boolean rhit;
  
  boolean lhit;
  
  int PlayerW = 10;
  
  boolean playdamageYogan;
  
  int offSetPos = 0;
  
  int[][][] limitTable = new int[][][] { { { 4, 0, 9407, 0, 768, 96 }, { 4, 0, 7871, 0, 768, 96 }, { 4, 0, 10592, 0, 768, 96 }, { 4, 0, 10943, 0, 768, 96 } }, { { 4, 0, 6591, 0, 1328, 96 }, { 4, 0, 4271, 0, 1824, 96 }, { 4, 0, 8239, 65280, 2048, 96 }, { 4, 0, 8383, 0, 1824, 96 } }, { { 4, 0, 6079, 0, 464, 96 }, { 4, 0, 6079, 0, 1312, 96 }, { 4, 0, 6144, 0, 1824, 96 }, { 4, 0, 5823, 0, 1824, 96 } }, { { 4, 0, 8127, 0, 1600, 96 }, { 4, 0, 8127, 0, 1600, 96 }, { 4, 0, 8192, 0, 1728, 96 }, { 4, 0, 16064, 0, 1824, 96 } }, { { 4, 0, 8896, 0, 1056, 96 }, { 4, 0, 10432, 0, 1312, 96 }, { 4, 0, 11264, 0, 1568, 96 }, { 4, 0, 11968, 0, 1568, 96 } }, { { 4, 0, 8640, 0, 1824, 96 }, { 4, 0, 7744, 65280, 2048, 96 }, { 4, 8320, 9312, 1296, 1296, 96 }, { 4, 0, 16064, 0, 1824, 96 } } };
  
  public byte m_bScrollLock;
  
  public short[][] m_aaScrollLockPos = new short[][] { { 9312, 7776 }, { 6736, 4176, 8208 }, { 6224, 6224 }, { 8288, 8032 }, { 8800, 10336 }, { 8544, 7648 } };
  
  int[] poslimit = new int[4];
  
  int nofcolTimer = 0;
  
  public boolean damageNow = false;
  
  public int damageMoveTimer = 0;
  
  public boolean PlayerSub;
  
  boolean bressDie;
  
  boolean timeUpDie;
  
  boolean noTimeScore;
  
  private static boolean OttotoOn;
  
  private static int OttotoSide;
  
  private static int raidObjectW = 0;
  
  private static int raidObjectX = 0;
  
  public int playerStandCount = 0;
  
  private boolean bressMusic = false;
  
  int objChkPoint = 0;
  
  int objChkNum = 0;
  
  int m_objMaxObject;
  
  boolean ChkVecR = false;
  
  boolean ChkVecL = false;
  
  int LSize = -240;
  
  int RSize = 640;
  
  private static int[] m_aAddObjectData = new int[25];
  
  int noDataPointer = 0;
  
  int listSub = 0;
  
  int[][] objTempData = new int[30][25];
  
  int objCount;
  
  boolean[] setDrawFlag = new boolean[150];
  
  private int waterH = 0;
  
  private int waterH2 = 0;
  
  private int waterH3 = 0;
  
  private byte water_flag = 0;
  
  private byte water_flag2 = 0;
  
  private byte water_flag3 = 0;
  
  private byte water_flag4 = 0;
  
  int noLeverTimer = 0;
  
  int[] awasintlb = new int[] { 
      0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 
      1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 
      3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
      3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
      3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 
      2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 
      0, 0, 0, 0, 0, 255, 255, 255, 255, 255, 
      254, 254, 254, 254, 254, 253, 253, 253, 253, 253, 
      253, 253, 252, 252, 252, 252, 252, 252, 252, 252, 
      252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 
      252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 
      252, 253, 253, 253, 253, 253, 253, 253, 254, 254, 
      254, 254, 254, 255, 255, 255, 255, 255 };
  
  int[] awaSize = new int[] { 8, 8, 16, 16, 24, 32, 32, 32 };
  
  int[] awaPos = new int[] { 0, 8, 16, 32, 48, 72, 104, 136 };
  
  public boolean endingModeOn = false;
  
  private static byte LANGUAGE_MAX = 2;
  
  private static byte TITLE_MODE_LICENSE_SEGA = 0;
  
  private static byte TITLE_MODE_LICENSE_SONICTEAM = 1;
  
  private static byte TITLE_MODE_FIRST_SETUP = 2;
  
  private static byte TITLE_MODE_TITLE = 3;
  
  private static byte TITLE_MODE_TITLE_MENU = 4;
  
  private static byte TITLE_MODE_TITLE_RANCKING = 5;
  
  private static byte TITLE_MODE_TITLE_RANCKING_MENU = 6;
  
  private static byte TITLE_MODE_TITLE_RANCKING_DEL = 7;
  
  private static byte TITLE_MODE_TITLE_CONFIG_MENU = 8;
  
  private static byte TITLE_MODE_TITLE_CONTINUE_MENU = 9;
  
  private static byte TITLE_MODE_TITLE_HOWTO = 10;
  
  private static byte m_nTitleMode;
  
  private static byte m_nPattern;
  
  private static byte m_nRingPattern;
  
  private static byte m_nSel;
  
  private static byte m_bDraw;
  
  private static byte m_bFirstSetUp = 0;
  
  private static byte m_nTimer;
  
  private static byte[] m_nConfigValue = new byte[4];
  
  private static byte[] m_HowToPicIndexTbl = new byte[] { 
      -1, 0, -1, -1, -1, -1, -1, 1, 1, 2, 
      3, 4, 5, 6, 7, -1, 8, 9, -1, -1, 
      -1, 10, 11, 12, -1, -1 };
  
  private static byte[][] m_aConfigTextOffset = new byte[][] { { 25, 26, 27 }, { 29, 30, 31, 32 }, { 29, 28 }, { 33, 34, 35, 36, 37 } };
  
  private static Command[] cmd = new Command[2];
  
  private static int m_nMarqueePos;
  
  private static int m_nOnKey;
  
  private static int m_nPushedKey;
  
  private static int m_nLastKey;
  
  private static int[] m_nHiScore = new int[] { 10000, 8000, 6000, 4000, 2000 };
  
  private static int[] m_nDifficulty = new int[] { 0, 1, 2, 1, 0 };
  
  private static boolean[] m_OnKeyFlag = new boolean[10];
  
  private static Image[] m_imgImage = new Image[10];
  
  private static Font m_Font = Font.getFont(0, 0, 8);
  
  private static short[][] m_HowToPicTbl = new short[][] { 
      { 0, 0, 32, 32 }, { 32, 0, 32, 40 }, { 64, 0, 32, 32 }, { 96, 0, 32, 32 }, { 128, 0, 32, 32 }, { 160, 0, 32, 32 }, { 192, 0, 32, 32 }, { 224, 0, 32, 32 }, { 0, 40, 40, 32 }, { 40, 40, 40, 48 }, 
      { 80, 40, 40, 48 }, { 120, 32, 32, 48 }, { 160, 32, 16, 64 }, { 176, 32, 40, 20 }, { 176, 52, 20, 20 }, { 196, 52, 20, 20 } };
  
  private static String[] m_strText = new String[51];
  
  private static String[] m_strHowToText = new String[182];
  
  private static String[] m_strMusicComposed = new String[] { "MUSIC COMPOSED", "BY MASATO", "NAKAMURA" };
  
  private static String m_strMarquee;
  
  private int MarqOfs;
  
  private static final int LocalType = 1;
  
  private static int comboScore = 0;
  
  private boolean isObj2Debug = false;
  
  private static int[][] obj2Data;
  
  private static final int OBJ2_MAX = 50;
  
  private static final int OBJ2_BAKUHATU = 1;
  
  private static final int OBJ2_KEMURI = 2;
  
  private static final int OBJ2_BAKUDAN = 3;
  
  private static final int OBJ2_RING = 4;
  
  private static final int OBJ2_KIRA = 5;
  
  private static final int OBJ2_SCORE = 6;
  
  private static final int OBJ2_TAMA = 7;
  
  private static final int OBJ2_HACHI_TAMA = 8;
  
  private static final int OBJ2_KANI_TAMA = 9;
  
  private static final int OBJ2_BUTA_TAMA = 10;
  
  private static final int OBJ2_UNI_TAMA = 11;
  
  private static final int OBJ2_UNI2_TAMA = 12;
  
  private static final int OBJ2_BROBO_TAMA = 13;
  
  private static final int OBJ2_IMO_TAMA = 14;
  
  private static final int OBJ2_MUSI_KEMURI = 15;
  
  private static final int OBJ2_FIREBALL = 16;
  
  private static final int OBJ2_FIREBALL2 = 17;
  
  private static final int OBJ2_FIREBALL3 = 18;
  
  private static final int OBJ2_FIREBALL4 = 19;
  
  private static final int OBJ2_FIREBALL5 = 20;
  
  private static final int OBJ2_KAZARIFIRE = 21;
  
  private static final int OBJ2_DBLOCK = 22;
  
  private static final int OBJ2_DBLOCK2 = 23;
  
  private static final int OBJ2_DBLOCK3 = 24;
  
  private static final int OBJ2_DBLOCK4 = 25;
  
  private static final int OBJ2_BRKABE_G = 26;
  
  private static final int OBJ2_BOSS6_TAMA = 27;
  
  private static final int OBJ2_FRIC = 28;
  
  private static final int OBJ2_AZARASI = 29;
  
  private static final int OBJ2_NIWATORI = 30;
  
  private static final int OBJ2_USAGI = 31;
  
  private static final int OBJ2_PENGUIN = 32;
  
  private static final int OBJ2_RISU = 33;
  
  private static final int OBJ2_BUTA = 34;
  
  private static final int OBJ2_DEBUG = 35;
  
  private boolean putNowLoading = false;
  
  private Display display;
  
  private int mapViewType = 0;
  
  private int mapViewTypeTemp = 0;
  
  private int mapViewCount = 0;
  
  private int mapViewPri = 0;
  
  private volatile InputStream is1 = null;
  
  private volatile Player player1 = null;
  
  private static volatile boolean bPauseMusic = false;
  
  private static volatile boolean bGoalMusic = false;
  
  private static volatile int musicCount = 0;
  
  private static volatile int musicRetry = 0;
  
  private static volatile int musicRequest = -1;
  
  private static volatile int musicNum = -1;
  
  private volatile boolean bDoPlay = false;
  
  private static final int MusicRetryInterval = 50;
  
  private static final byte[][] friendTbl = new byte[][] { { 28, 31 }, { 32, 29 }, { 33, 29 }, { 28, 34 }, { 34, 30 }, { 31, 30 }, { 33, 33 }, { 31, 31 } };
  
  private static final byte[] sisootbl = new byte[] { 
      36, 36, 38, 40, 42, 44, 42, 40, 38, 36, 
      35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 
      25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 
      15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 
      5, 4, 3, 2, 2, 2, 2, 2 };
  
  private static final int KaniAttackCount = 360;
  
  private static final int ArumaRunCount = 94;
  
  private static final int ArumaSpeedX = 500;
  
  private static final int ArumaSpeedY = 400;
  
  private static final int ArumaStartOffsetX = 160;
  
  private static final byte[] batAnimTbl = new byte[] { 1, 2, 3, 2 };
  
  private static final short[][] RectTblKamere = new short[][] { { 0, 0, 56, 40, 0 }, { 0, 40, 56, 40, 0 }, { 0, 80, 56, 40, 0 }, { 0, 120, 56, 24, 0 }, { 0, 144, 56, 16, 0 }, { 0, 160, 56, 16, 0 } };
  
  private static final short[][] RectTblHachi = new short[][] { { 0, 0, 48, 32, 0 }, { 0, 32, 48, 24, 3 }, { 0, 56, 48, 32, 0 }, { 0, 88, 48, 24, 3 }, { 0, 112, 48, 40, 4 }, { 0, 152, 48, 32, 7 } };
  
  private static final short[][] RectTblMusi = new short[][] { { 0, 0, 40, 32, 0 }, { 0, 32, 40, 32, 0 }, { 0, 64, 40, 32, 0 } };
  
  private static final short[][] RectTblImo = new short[][] { { 0, 0, 16, 24, -4 }, { 0, 24, 16, 24, -4 }, { 0, 48, 16, 16, 0 } };
  
  private static final short[][] RectTblBrobo = new short[][] { { 0, 0, 24, 40, 0 }, { 0, 40, 24, 40, 0 }, { 0, 80, 24, 40, 0 }, { 0, 120, 24, 40, 0 }, { 0, 160, 24, 40, 0 } };
  
  private static final short[][] RectTblButa = new short[][] { { 0, 0, 24, 40, 0 }, { 0, 40, 24, 40, 0 }, { 0, 80, 24, 40, 0 }, { 0, 120, 24, 40, 0 } };
  
  private static final short[][] RectTblKani = new short[][] { { 0, 0, 48, 32, 0 }, { 0, 32, 48, 32, 0 }, { 0, 64, 48, 32, 0 }, { 0, 96, 48, 32, 0 } };
  
  private static final short[][] RectTblAruma = new short[][] { { 0, 0, 32, 32, 0 }, { 0, 32, 32, 32, 0 }, { 0, 64, 32, 40, -5 }, { 0, 104, 32, 48, -8 } };
  
  private static final short[][] RectTblYado = new short[][] { { 0, 0, 40, 40, 0 }, { 0, 40, 40, 40, 0 }, { 0, 80, 40, 40, 0 } };
  
  private static final short[][] RectTblUni = new short[][] { { 0, 0, 24, 24, 0 }, { 0, 24, 24, 24, 0 }, { 0, 48, 24, 24, 0 }, { 0, 72, 24, 24, 0 } };
  
  private static final short[][] RectTblBat = new short[][] { { 0, 0, 40, 24, 0 }, { 0, 24, 40, 32, 0 }, { 0, 56, 40, 32, 0 }, { 0, 88, 40, 32, 0 } };
  
  private static final short[][] RectTblMogura = new short[][] { { 0, 0, 32, 48, 0 }, { 0, 48, 32, 48, 0 }, { 0, 96, 32, 48, 0 }, { 0, 144, 32, 40, 0 }, { 0, 184, 32, 40, 0 } };
  
  private static final short[][] RectTblFish = new short[][] { { 0, 0, 32, 32, 0 }, { 0, 32, 32, 32, 0 } };
  
  private static final short[][] RectTblFish2 = new short[][] { { 0, 0, 48, 24, 0 }, { 0, 24, 48, 24, 0 }, { 0, 48, 48, 24, 0 }, { 0, 72, 48, 24, 0 } };
  
  private static final byte[] Boss6TamaAnmTbl = new byte[] { 1, -1, -1 };
  
  private static final byte[] Boss6TamaAnmTbl2 = new byte[] { 
      1, -1, 0, -1, 2, -1, 3, -1, 4, -1, 
      1, -1, 0, -1, 2, -1, 3, -1, 4, -1 };
  
  private static final byte[] Boss6TamaAnmTbl3 = new byte[] { 0, 4, 1, 4 };
  
  private static final short[][] RectTblBakuhatu = new short[][] { { 8, 0, 24, 16 }, { 0, 16, 40, 32 }, { 0, 48, 40, 32 }, { 0, 80, 40, 40 }, { 0, 120, 40, 40 } };
  
  private static final short[][] RectTblKemuri = new short[][] { { 8, 0, 24, 16 }, { 0, 160, 40, 32 }, { 0, 192, 40, 32 }, { 0, 80, 40, 40 }, { 0, 120, 40, 40 } };
  
  private static final short[][] RectTblTama = new short[][] { 
      { 0, 0, 16, 16 }, { 0, 16, 16, 16 }, { 0, 32, 16, 16 }, { 0, 48, 16, 16 }, { 0, 64, 16, 16 }, { 0, 80, 16, 16 }, { 0, 96, 16, 16 }, { 0, 112, 16, 16 }, { 0, 128, 16, 16 }, { 0, 144, 16, 16 }, 
      { 8, 160, 8, 8 }, { 8, 168, 8, 8 }, { 0, 160, 8, 8 }, { 0, 168, 8, 8 } };
  
  private static final short[][] RectTblDBlock = new short[][] { { 0, 0 }, { 16, 0 }, { 0, 16 }, { 16, 16 } };
  
  private static final short[][] RectTblBoss6Tama = new short[][] { { 88, 56, 16, 16 }, { 64, 56, 24, 24 }, { 88, 72, 16, 16 }, { 88, 88, 16, 16 }, { 64, 80, 24, 24 } };
  
  private short[][] MoveAnimalTbl = new short[][] { { -250, -360 }, { -80, -100 }, { -160, -250 }, { -200, -300 }, { -120, -225 }, { -150, -250 }, { -100, -165 } };
  
  private short[][] RectAnimalTbl = new short[][] { 
      { 0, 0, 16, 24, 0 }, { 16, 0, 16, 16, 8 }, { 32, 0, 16, 16, 8 }, { 0, 24, 16, 24, 0 }, { 16, 16, 16, 16, 8 }, { 32, 16, 16, 16, 8 }, { 0, 48, 16, 24, 0 }, { 16, 32, 16, 16, 8 }, { 32, 32, 16, 16, 8 }, { 0, 72, 16, 24, 0 }, 
      { 16, 48, 16, 24, 0 }, { 32, 48, 16, 24, 0 }, { 0, 96, 16, 24, 0 }, { 16, 72, 16, 24, 0 }, { 32, 72, 16, 24, 0 }, { 16, 96, 16, 24, 0 }, { 0, 120, 24, 16, 8 }, { 0, 136, 24, 16, 8 }, { 32, 96, 16, 24, 0 }, { 24, 120, 24, 16, 8 }, 
      { 24, 136, 24, 16, 8 } };
  
  private int bossType;
  
  private int bossStep;
  
  private int bossAnim;
  
  private int bossDir;
  
  private int bossAngle;
  
  private int bossAngle2;
  
  private int bossParam1;
  
  private int bossParam2;
  
  private int bossPosX;
  
  private int bossPosY;
  
  private int bossOfsX;
  
  private int bossOfsY;
  
  private int bossOriginX;
  
  private int bossOriginY;
  
  private int bossCount;
  
  private int bossFrame;
  
  private int bossFlash;
  
  private int bossStopCount;
  
  private int bossFace;
  
  private int bossFaceCount;
  
  private int bossHP;
  
  private static final int[] BossDeadLimitY = new int[] { 912, 224, 656, 672, 1360, -16 };
  
  private static final int Boss1MoveWidth = 3200;
  
  private static final int Boss1Speed = 100;
  
  private static final int Boss1FurikoSpeed = 100;
  
  private static int boss1BallPosX;
  
  private static int boss1BallPosY;
  
  private static boolean boss1BallOn;
  
  private static final int[][] boss2MoveTbl = new int[][] { { 769600, 147200 }, { 775200, 128000 }, { 779200, 121600 }, { 779200, 25600 }, { 801200, 19200 } };
  
  private static final int boss3AttackWidth = 104;
  
  private static final int boss3SpeedX = 150;
  
  private static final int boss3DownSpeed = 50;
  
  private static final int boss3FloatSpeed = 12;
  
  private static final int boss3AttackWait = 48;
  
  private static int boss3FireCount;
  
  private static short[][] boss4Sisoo = new short[3][4];
  
  private static final int Boss4SisooOfs = 3500;
  
  private static final int Boss4ShootWait = 50;
  
  private static final int Boss4Speed = 80;
  
  private static final int Boss4HighPos = -400;
  
  private static final int Boss4LowPos = 4800;
  
  private static final int Boss4BakuhatuCount = 240;
  
  private static short[][] boss5Block = new short[10][4];
  
  static final int Boss5BlockLine = 160;
  
  private static final int boss5AttackWidth = 120;
  
  private static final int boss5Speed = 70;
  
  private static int boss5AttackCount = 0;
  
  private static int[] boss6Piston = new int[4];
  
  private static int[][] boss6PistonXY = new int[4][4];
  
  private static int boss6RideNum;
  
  private static int boss6PistonNum;
  
  private static int[] boss6TamaY = new int[4];
  
  private static int boss6Lamp;
  
  private static int boss6Destroy;
  
  private static final short[][] boss6PistonPos = new short[][] { { -104, -159 }, { 24, -159 }, { -40, 144 }, { 88, 144 } };
  
  private static int nakaStep;
  
  private static int nakaLevel;
  
  private static int nakaCount;
  
  private static int endingEggStep = 0;
  
  private static int endingEggAnim = 0;
  
  private static int endingEggCount = 0;
  
  private static final short[][] RectTblEndingB = new short[][] { { 0, 0, 64, 96 }, { 64, 0, 64, 96 }, { 128, 0, 64, 96 } };
  
  private static int wipeCol = 0;
  
  private static int wipeLevel = 0;
  
  private static boolean wipeDir = false;
  
  private int endingStep;
  
  private int endingCount;
  
  private int endingAnim;
  
  private int endingType;
  
  private int endingLogoPosX;
  
  private int endingStringFadeLevel;
  
  private short[][] endingRectTbl = new short[][] { { 0, 0, 32, 40 }, { 0, 40, 48, 72 }, { 48, 0, 176, 136 } };
  
  private short[][] RectBoss6LampTbll = new short[][] { { 0, 0, 48, 56 }, { 0, 56, 48, 56 } };
  
  private short[][] RectEggmanTbl = new short[][] { { 0, 0, 48, 56 }, { 0, 56, 48, 56 }, { 0, 112, 48, 56 }, { 0, 168, 48, 56 }, { 48, 0, 64, 56 }, { 48, 56, 64, 56 }, { 48, 112, 64, 56 }, { 48, 168, 64, 56 } };
  
  private short[][] RectBossTbl = new short[][] { 
      { 0, 0, 56, 24, 0, -36 }, { 0, 24, 56, 24, 0, -36 }, { 0, 48, 56, 24, 0, -36 }, { 0, 72, 56, 24, 0, -36 }, { 0, 96, 56, 24, 0, -36 }, { 0, 120, 56, 24, 0, -36 }, { 0, 144, 56, 24, 0, -36 }, { 0, 168, 56, 24, 0, -36 }, { 56, 48, 64, 32, 4, -8 }, { 56, 80, 64, 32, 4, -8 }, 
      { 56, 64, 64, 48, 4, -8 }, { 56, 112, 16, 16, 42, -10 }, { 56, 128, 16, 16, 42, -10 }, { 56, 144, 24, 16, 46, -10 }, { 56, 160, 32, 32, 52, -10 }, { 88, 112, 16, 16, 0, 16 }, { 88, 128, 16, 16, 0, 16 }, { 88, 144, 16, 16, 0, 0 }, { 104, 112, 16, 8, 18, -42 }, { 104, 128, 16, 16, 0, 10 }, 
      { 104, 144, 16, 40, 0, 10 }, { 56, 0, 56, 24, 0, -36 }, { 56, 24, 56, 24, 0, -36 } };
  
  private short[][] RectBoss2Tbl = new short[][] { { 0, 0, 64, 48, 4, 0 }, { 0, 48, 64, 40, 4, -4 }, { 0, 88, 64, 32, 4, -8 }, { 0, 120, 64, 32, 4, -8 } };
  
  private short[] RectBossBallTbl = new short[] { 0, 0, 48, 48, 0, 0 };
  
  private static int continueStep = 0;
  
  private static int continueSonicPosX;
  
  private static int continueSonicPosY;
  
  private static int continueSonicAnim;
  
  private static int continueSonicAnim2;
  
  private static int continueCount;
  
  private static int continueResult = 0;
  
  private static final int ContinueSonicCenterX = 120;
  
  private static final int ContinueSonicBottomY = 167;
  
  private static final short[][] ContinueSonicTbl = new short[][] { { 0, 120, 0 }, { 48, 120, 0 }, { 96, 120, 0 }, { 96, 120, 1 }, { 48, 120, 1 } };
  
  private static final short[][] ContinueSonicTbl2 = new short[][] { { 48, 0 }, { 0, 32 }, { 48, 32 }, { 0, 32 } };
  
  int[] break_sflag_ike_yuka = new int[] { 
      32, 32, 32, 32, 32, 32, 32, 32, 33, 33, 
      34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 
      39, 39, 40, 40, 41, 41, 42, 42, 43, 43, 
      44, 44, 45, 45, 46, 46, 47, 47, 48, 48, 
      48, 48, 48, 48, 48, 48, 48, 48 };
  
  int[][] yuka_nflag_ike_yuka = new int[][] { { 
        44, 44, 44, 44, 44, 44, 44, 44, 45, 46, 
        47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
        57, 58, 59, 60, 60, 60, 60, 60, 60, 60, 
        60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 
        60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 
        50, 49, 48, 47, 46, 45, 44, 44, 44, 44, 
        44, 44, 44, 44 }, { 
        52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 
        52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 
        52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 
        52, 52 }, { 
        37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 
        47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
        57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 
        67, 68, 68, 68, 68, 68, 68, 68, 68, 68, 
        68, 68, 68, 68, 68, 68, 68, 68, 68, 67, 
        66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 
        56, 55, 54, 53 } };
  
  int[] yuka_nflag_yuka_w = new int[] { 64, 32, 64 };
  
  int[] yuka_nflag_yuka_h = new int[] { 40, 48, 48 };
  
  int[] box_sflag_ike_def_X = new int[] { 1296, 3632, 752, 4880 };
  
  int[] box_sflag_ike_def_Y = new int[] { 1168, 1104, 1680, 1360 };
  
  int[] box_sflag_ike_col_X = new int[] { 1263, 3598, 785, 4845 };
  
  int[] box_sflag_ike_col_Y = new int[] { 1216, 1136, 1728, 1392 };
  
  int[] box_sflag_ike_box_V = new int[] { -1, -1, 1, -1 };
  
  private static boolean[] switchflag2 = new boolean[256];
  
  int[] break2_nflag_ike_brockTable = new int[] { 4, 7, 5, 6, 0, 3, 2, 1 };
  
  int[] break2_nflag_ike_brockTimeTable = new int[] { 0, 5, 8, 17, 20, 28, 32, 34 };
  
  int[] fire6_nflag_ike_posTable = new int[] { 16, 16, 8, 8, 8 };
  
  int[] fire6_nflag_ike_sizeTable = new int[] { 32, 32, 24, 24, 16 };
  
  int[] fire6_nflag_ike_sizeTable2 = new int[] { 32, 32, 16, 24, 16 };
  
  int[] mawaru_nflag_ike_posx = new int[] { -24, -47, -47, -46, -23, 1, 9, 1 };
  
  int[] mawaru_nflag_ike_posy = new int[] { -47, -46, -24, 1, 8, 1, -23, -46 };
  
  int[][] ele_nflag_ike_anime = new int[][] { { 1, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 } };
  
  int[][] beltc_nflag_ike_objectPos = new int[][] { { 3604, 880, 3823, 770, 3823, 832, 3604, 942 }, { 3860, 736, 4079, 626, 4079, 688, 3860, 798 }, { 4116, 624, 4335, 514, 4335, 576, 4116, 686 }, { 3860, 1392, 4079, 1282, 4079, 1344, 3860, 1454 }, { 6932, 1648, 7151, 1538, 7151, 1600, 6932, 1710 }, { 7188, 1504, 7407, 1394, 7407, 1456, 7188, 1566 } };
  
  int[] beltc_nflag_ike_defx = new int[] { 3712, 3968, 3968, 4224, 7040, 7296 };
  
  int[] beltc_nflag_ike_defy = new int[] { 896, 768, 1280, 640, 1536, 1408 };
  
  int[] beltc_nflag_ike_startPos = new int[2];
  
  int[] beltc_nflag_ike_endPos = new int[2];
  
  boolean gole_on = false;
  
  int[] bten_nflag_ike_score = new int[] { 0, 10000, 1000, 100 };
  
  int[][] shooter_nflag_ike_objectPos = new int[][] { { 1940, 396 }, { 148, 920 }, { 
        1940, 756, 1956, 716, 2000, 696, 2136, 696, 2180, 676, 
        2196, 636, 2196, 412 }, { 2196, 1692 }, { 
        4500, 1148, 4484, 1188, 4440, 1208, 4048, 1208, 4004, 1228, 
        3988, 1268, 3988, 1436 }, { 4756, 1180 }, { 
        5524, 2040, 5508, 2000, 5472, 1980, 5328, 1980, 5284, 1960, 
        5268, 1920, 5268, 1440 }, { 2196, 144 } };
  
  int[] shooter_nflag_ike_pos = new int[] { 
      2, 4, 6, 8, 10, 12, 12, 9, 7, 4, 
      1, -2 };
  
  int[] masin_nflag_ike_x = new int[] { -8, -16, -12, 12, 16, 8 };
  
  int[] masin_nflag_ike_y = new int[] { 22, 25, 13, 22, 25, 13 };
  
  int[] yari_sflag_ike_PosTable = new int[] { 48, 32, 16, 32 };
  
  private static int[][] kassya_x = new int[6][20];
  
  private static int[][] kassya_y = new int[6][20];
  
  int[][] kassya_nflag_ike_objectPos = new int[][] { { 
        4216, 538, 4286, 608, 4286, 915, 4236, 965, 4130, 912, 
        4130, 580 }, { 4734, 640, 4814, 720, 4814, 1134, 4658, 1056, 4658, 716 }, { 3362, 1154, 3362, 1502, 3502, 1502, 3502, 1154 }, { 3426, 930, 3566, 930, 3566, 1246, 3426, 1246 }, { 3244, 578, 3550, 578, 3550, 990, 3154, 990, 3154, 668 }, { 4690, 522, 5086, 522, 5086, 702, 4690, 702 } };
  
  int[][] kassya_nflag_ike_defX = new int[][] { { 4208, 4736 }, { 3432, 3488 }, { 4208, 4736 } };
  
  int[][] kassya_nflag_ike_defY = new int[][] { { 640, 768 }, { 1152, 896 }, { 640, 768 } };
  
  int[] myogan_nflag_ike_ani = new int[] { 
      2, 3, 2, 3, 2, 3, 2, 3, 2, 2, 
      3, 0, 1, 0, 1 };
  
  int[][] step_nflag_ike_gura = new int[][] { { -1, 1 }, { 1, -1 } };
  
  int[] fire6_nflag_ike_animeTable = new int[] { 40, 40, 16, 16, 0 };
  
  int[][] fire6_nflag_ike_rotTable = new int[][] { { TRANS_NONE, TRANS_MIRROR }, { TRANS_MIRROR, TRANS_NONE }, { TRANS_ROT180, TRANS_MIRROR_ROT180 }, { 0, 0 } };
  
  int[][] ele_nflag_ike_rotTable = new int[][] { { TRANS_ROT180, TRANS_MIRROR_ROT180 }, { TRANS_ROT180, TRANS_MIRROR_ROT180 }, { TRANS_MIRROR, TRANS_NONE }, { TRANS_ROT180, TRANS_MIRROR_ROT180 } };
  
  int[] item_nflag_ike_itemTable = new int[] { 8, 0, 4, 3, 2, 1, 0 };
  
  int[] gole_nflag_ike_rotTable = new int[] { rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR] };
  
  int[] gole_nflag_ike_kiraTableX = new int[] { 8, 0, 40, 24, 10, 40, 30, 24, 41, 6 };
  
  int[] gole_nflag_ike_kiraTableY = new int[] { 8, 16, 8, 30, 23, 24, 18, 16, 18, 8 };
  
  int[] yoganc_nflag_ike_posY = new int[] { 0, 32, 64, 96, 128, 168, 208 };
  
  int[] yari_sflag_ike_drawPosTable = new int[] { 0, 6, 6, 4, 10, 2, 6, 4 };
  
  private void crushingDeathChk() {
    if (debugFlag)
      return; 
    if (this.crushing[0] && this.crushing[3]) {
      playerDie();
    } else if ((!PlayerJump || raidOn) && this.crushing[3]) {
      playerDie();
    } else if (this.zoneNumber != 2 && this.zoneNumber != 4) {
      if (raidOn && (blockColChk2(PlayerPosX() - 8, PlayerPosY() - 24) || blockColChk2(PlayerPosX() + 8, PlayerPosY() - 24)))
        playerDie(); 
    } else if (raidOn && blockColChk2(PlayerPosX() - 8, PlayerPosY() - 24) && blockColChk2(PlayerPosX() + 8, PlayerPosY() - 24)) {
      playerDie();
    } 
    this.crushing[0] = false;
    this.crushing[3] = false;
  }
  
  MainCanvas(sonic paramsonic) {
    super(false);
    this;
    h = paramsonic;
  }
  
  public void keyPressed(int paramInt) {
    if (PlayerNoCtrl && mode == MODE_FIELD)
      return; 
    if (this.endingModeOn)
      return; 
    int i = getGameAction(paramInt);
    if (i == 8 || paramInt == 53) {
      KeyPress[0] = true;
    } else if (i == 6 || paramInt == 56) {
      KeyPress[1] = true;
    } else if (i == 1 || paramInt == 50) {
      KeyPress[2] = true;
    } else if (i == 2 || paramInt == 52) {
      KeyPress[3] = true;
    } else if (i == 5 || paramInt == 54) {
      KeyPress[4] = true;
    } else if (paramInt == 48) {
      KeyPress[7] = true;
    } else if (paramInt == 42) {
      KeyPress[8] = true;
    } else if (paramInt == 35) {
      KeyPress[9] = true;
    } 
  }
  
  public void keyReleased(int paramInt) {
    int i = getGameAction(paramInt);
    if (i == 8 || paramInt == 53) {
      KeyPress[0] = false;
    } else if (i == 6 || paramInt == 56) {
      KeyPress[1] = false;
    } else if (i == 1 || paramInt == 50) {
      KeyPress[2] = false;
    } else if (i == 2 || paramInt == 52) {
      KeyPress[3] = false;
    } else if (i == 5 || paramInt == 54) {
      KeyPress[4] = false;
    } else if (paramInt == 48) {
      KeyPress[7] = false;
    } else if (paramInt == 42) {
      KeyPress[8] = false;
    } else if (paramInt == 35) {
      KeyPress[9] = false;
    } 
  }
  
  public void clearKey() {
    KeyPress[0] = false;
    KeyPress[1] = false;
    KeyPress[2] = false;
    KeyPress[3] = false;
    KeyPress[4] = false;
    KeyPress[5] = false;
    KeyPress[6] = false;
    KeyPress[8] = false;
    KeyPress[9] = false;
  }
  
  public void initAll() {
    try {
      try {
        gg = getGraphics();
      } catch (Throwable throwable) {}
      mode = MODE_INIT;
      this.displayOffsetY = getHeight() - 240 >> 1;
      this.displayOffsetY2 = this.displayOffsetY + 36;
      gg.translate(0, this.displayOffsetY);
      DG();
      load_conf();
      TK_LoadTextset();
      DG();
      this.m_imgCmd[LOGO] = createImage("/logo.png");
      this.m_imgCmd[LOGOLINE] = createImage("/logoline.png");
      this.m_imgCmd[SYSTXT] = createImage("/Systxt.png");
      this.m_imgCmd[SYSTXT2] = createImage("/Systxt2.png");
      this.m_imgCmd[WINDOW_RING] = createImage("/windou_ring.png");
      this.m_imgCmd[WINDOW_TIME] = createImage("/windou_time.png");
      this.m_imgCmd[WINDOW_ZANKI] = createImage("/windou_zanki.png");
      this.m_imgCmd[WINDOU_SUUJI] = createImage("/windou_suuji.png");
      this.m_imgCmd[SYSSCORE] = createImage("/score.png");
      this.m_imgCmd[GAMEOVER] = createImage("/gameover.png");
      this.m_imgCmd[TIMEOVER] = createImage("/timeover.png");
      load_resu();
      load_hisc();
      FontPos = (20 - f.getHeight()) / 2;
      gg.setFont(f);
      this.m_imgCmd[T_CUR1] = createImage("/t_cur1.png");
      this.m_imgCmd[T_CUR2] = createImage("/t_cur2.png");
      initDisplay = true;
      DG();
      indata = new DataInputStream(getClass().getResourceAsStream("/scddirtbl.blt"));
      indata.read(scddirtbl);
      indata.close();
      mode = MODE_TITLE;
      this.SetSoftFlag = true;
      this.SetSoftCount = 10;
      InitObj2();
      DrawFlag = true;
      InitSound();
      TK_TitleInit(true);
      setCommandListener(this);
      ObjectListNum = 0;
    } catch (Throwable throwable) {}
  }
  
  public void GameMain() {
    byte b1 = 0;
    byte b2 = 0;
    long l2 = 0L;
    long l1 = System.currentTimeMillis();
    while (true) {
      long l = System.currentTimeMillis();
      if (l2 > l) {
        l2 = l;
        l1 = 0L;
      } else {
        l2 = l;
      } 
      getTime2 = l2 - l1 - TIME_WAIT + 10L;
      if (l1 > l2) {
        try {
          Thread.sleep(l1 - l2);
        } catch (Exception exception) {}
        l2 = System.currentTimeMillis();
        b1 = 0;
      } else if (++b1 > 1) {
        b1 = 0;
      } else if (b2) {
        b1 = 0;
      } 
      ProcessMain();
      if (b1 <= 0) {
        DG();
        l1 = l2 + (TIME_WAIT - 10);
      } else if (mode != MODE_FIELD || this.endingModeOn) {
        DG();
        l1 = l2 + (TIME_WAIT - 10);
      } else {
        if (muteki2count > 0) {
          muteki2count--;
          if (muteki2count % 2 == 0) {
            this.playerDraw = true;
          } else {
            this.playerDraw = false;
          } 
        } else {
          this.playerDraw = false;
        } 
        this.animeTimer++;
      } 
      if (++b2 > 7)
        b2 = 0; 
    } 
  }
  
  public void save_conf() {
    SaveRecordStore(m_nConfigValue, "conf");
  }
  
  public void load_conf() {
    try {
      m_nConfigValue = LoadRecordStore("conf");
      if (m_nConfigValue[0] == 0);
    } catch (Throwable throwable) {
      m_bFirstSetUp = 1;
      m_nConfigValue = new byte[4];
      m_nConfigValue[0] = 1;
      m_nConfigValue[1] = 3;
      m_nConfigValue[2] = 1;
      save_conf();
    } 
  }
  
  public void save_resu() {
    byte[] arrayOfByte = new byte[7];
    arrayOfByte[0] = this.resumeStage;
    arrayOfByte[1] = this.resumeZanki;
    for (byte b = 0; b < 4; b++)
      arrayOfByte[2 + b] = (byte)(this.resumeScore >> b * 8 % 256); 
    arrayOfByte[6] = this.clearStageData;
    SaveRecordStore(arrayOfByte, "resu");
  }
  
  public void load_resu() {
    try {
      byte[] arrayOfByte = LoadRecordStore("resu");
      this.resumeStage = arrayOfByte[0];
      this.resumeZanki = arrayOfByte[1];
      int i = 0;
      for (byte b = 0; b < 4; b++)
        i |= (arrayOfByte[2 + b] & 0xFF) << b * 8; 
      this.resumeScore = i;
      this.clearStageData = arrayOfByte[6];
    } catch (Throwable throwable) {
      save_resu();
    } 
  }
  
  public void save_hisc() {
    byte[] arrayOfByte = new byte[25];
    for (byte b = 0; b < 5; b++) {
      arrayOfByte[0 + b * 5] = (byte)m_nDifficulty[b];
      for (byte b1 = 0; b1 < 4; b1++)
        arrayOfByte[1 + b1 + b * 5] = (byte)(m_nHiScore[b] >> b1 * 8 % 256); 
    } 
    SaveRecordStore(arrayOfByte, "hisc");
  }
  
  public void load_hisc() {
    try {
      byte[] arrayOfByte = LoadRecordStore("hisc");
      for (byte b = 0; b < 5; b++) {
        m_nDifficulty[b] = arrayOfByte[0 + b * 5];
        int i = 0;
        for (byte b1 = 0; b1 < 4; b1++)
          i |= (arrayOfByte[1 + b1 + b * 5] & 0xFF) << b1 * 8; 
        m_nHiScore[b] = i;
      } 
    } catch (Throwable throwable) {
      save_hisc();
    } 
  }
  
  public void SetSoftLabel() {
    if (mode == MODE_TITLE) {
      if (m_nTitleMode == TITLE_MODE_TITLE || m_nTitleMode == TITLE_MODE_TITLE_MENU)
        if (m_nConfigValue[1] != 0) {
          SetSoftLabel(0, softKeys[14]);
        } else {
          SetSoftLabel(0, softKeys[13]);
        }  
    } else {
      if (mode == MODE_CONTINUE || mode == MODE_STARTSTAGE || mode == MODE_FIELD || mode == MODE_STAGESELECT) {
        SetSoftLabel(0, "");
      } else if (m_nConfigValue[1] != 0) {
        SetSoftLabel(0, softKeys[14]);
      } else {
        SetSoftLabel(0, softKeys[13]);
      } 
      if (mode == MODE_FIELD) {
        if ((playercount <= 0 && PlayerDie) || this.gole_on || this.endingModeOn) {
          SetSoftLabel(1, "");
        } else {
          SetSoftLabel(1, softKeys[5]);
        } 
      } else if (mode == this.MODE_FIELD_PAUSE) {
        SetSoftLabel(1, softKeys[6]);
      } else if (mode == MODE_CONTINUE) {
        SetSoftLabel(1, softKeys[11]);
      } else if (mode == MODE_STAGESELECT) {
        SetSoftLabel(1, softKeys[4]);
      } else if (mode == MODE_STARTSTAGE) {
        SetSoftLabel(1, "");
      } 
    } 
  }
  
  public boolean softKeyChk() {
    try {
      if (moveRsm && CheckSoftLabel(1, softKeys[5])) {
        mode = this.MODE_FIELD_PAUSE;
        this.SetSoftFlag = true;
        this.SetSoftCount = 10;
        this.pauseTimer = 0;
        this.pauseSelect = 0;
        moveRsm = false;
        PauseMusic();
        return true;
      } 
      moveRsm = false;
      byte b = -1;
      if (KeyPress[5]) {
        KeyPress[5] = false;
        b = 0;
      } else if (KeyPress[6]) {
        if (mode == MODE_TITLE)
          return false; 
        KeyPress[6] = false;
        b = 1;
      } 
      if (b != -1) {
        if (CheckSoftLabel(b, softKeys[4])) {
          if (mode == MODE_STAGESELECT) {
            mode = MODE_TITLE;
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            ObjImageClear();
            TK_TitleInit(false);
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            m_nSel = 1;
            TK_SetMarquee(7 + m_nSel);
            SetSoftKey(2);
          } else {
            mode = MODE_TITLE;
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            ObjImageClear();
            TK_TitleInit(false);
          } 
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[5])) {
          mode = this.MODE_FIELD_PAUSE;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          this.pauseTimer = 0;
          this.pauseSelect = 0;
          PauseMusic();
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[6])) {
          save_conf();
          mode = MODE_FIELD;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          RestartMusic();
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[11])) {
          mode = MODE_TITLE;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          ObjImageClear();
          StopMusic();
          TK_TitleInit(true);
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[13])) {
          m_nConfigValue[1] = this.oldm_nConfigValue[1];
          if (m_nConfigValue[1] == 0)
            m_nConfigValue[1] = 3; 
          save_conf();
          MuteMusic(false);
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[14])) {
          this.oldm_nConfigValue[1] = m_nConfigValue[1];
          if (this.oldm_nConfigValue[1] == 0)
            this.oldm_nConfigValue[1] = 0; 
          m_nConfigValue[1] = 0;
          save_conf();
          MuteMusic(true);
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          return true;
        } 
        if (CheckSoftLabel(b, softKeys[2])) {
          h.doExit();
          return true;
        } 
      } 
    } catch (Throwable throwable) {
      moveRsm = false;
    } 
    return false;
  }
  
  public void ProcessMain() {
    try {
      if (softKeyChk()) {
        clearKey();
        return;
      } 
      if (mode == MODE_TITLE) {
        TK_TitleFactor();
      } else if (mode == this.MODE_FIELD_PAUSE) {
        this.playerStandCount = 0;
        if (KeyPress[0]) {
          save_conf();
          if (this.pauseSelect == 0) {
            clearKey();
            mode = MODE_FIELD;
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            RestartMusic();
          } else {
            mode = MODE_TITLE;
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            ObjImageClear();
            TK_TitleInit(true);
          } 
          clearKey();
        } else if (KeyPress[1]) {
          clearKey();
          this.pauseSelect = this.pauseSelect + 1 & 0x1;
        } else if (KeyPress[2]) {
          clearKey();
          this.pauseSelect = this.pauseSelect + 1 & 0x1;
        } 
      } else if (mode == MODE_STAGESELECT) {
        if (KeyPress[0]) {
          this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
          this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
          playercount = 3;
          scorecount = 0;
          readStageObjectFlag = true;
          initStageStart();
        } else if (KeyPress[4]) {
          clearKey();
          byte b = 18;
          this.selectStageNumber = (this.selectStageNumber + 1) % 3;
          if (this.selectStageNumber == 0)
            this.selectZoneNumber++; 
          if (this.selectZoneNumber == b / 3) {
            if (this.selectStageNumber > b % 3) {
              this.selectZoneNumber = 0;
              this.selectStageNumber = 0;
            } 
          } else if (this.selectZoneNumber > b / 3) {
            this.selectZoneNumber = 0;
            this.selectStageNumber = 0;
          } 
        } else if (KeyPress[3]) {
          clearKey();
          byte b = 18;
          this.selectStageNumber = (this.selectStageNumber + 2) % 3;
          if (this.selectStageNumber == 2) {
            this.selectZoneNumber--;
            if (this.selectZoneNumber < 0) {
              this.selectZoneNumber = b / 3;
              this.selectStageNumber = b % 3;
            } 
          } 
        } 
        if (this.selectZoneNumber == 6)
          this.selectStageNumber = 0; 
      } else if (mode == MODE_FIELD) {
        oldMapOxy[0] = mapOxy[0];
        oldMapOxy[1] = mapOxy[1];
        for (byte b = 0; b < 4; b++) {
          oldringcount = ringcount;
          if (this.endingModeOn) {
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            clearKey();
            moveEnding();
          } 
          if (this.gole_on) {
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            TimerStop = true;
          } 
          ploldpos[0] = PlayerPosX();
          ploldpos[1] = PlayerPosY() - 1;
          raidObjectW = 0;
          raidObjectX = 0;
          OttotoOn = false;
          this.damageNow = false;
          this.playdamageYogan = false;
          try {
            playerAction();
            if (!PlayerDie) {
              addObjectChk();
              objectAction();
            } 
            if (this.zoneNumber == 1)
              objAwaData_move(); 
            if (!PlayerDie)
              DriveObj2(); 
            if (this.playdamageYogan)
              playdamageset(); 
          } catch (Throwable throwable) {}
          moveObjData();
          crushingDeathChk();
          mutekicount--;
          speedupcount--;
          if (mutekicount == 0)
            PlayZoneBGML(); 
          if (speedupcount <= 0) {
            plmaxspd = 1536;
            pladdspd = 12;
          } 
          limitchk(true);
          ViewControl();
          this.cpuTimer++;
          if (this.cpuTimer % 60 == 0 && !TimerStop) {
            timecount = (timecount + 1) % 60;
            if (timecount == 0)
              if (timecount2 == 9) {
                timecount = 59;
                playerDie();
              } else {
                timecount2++;
              }  
          } 
          if (TimerClear) {
            timecount = 0;
            timecount2 = 0;
          } 
          if (!PlayerDamage && ringcount >= 100 && oldringcount < 100) {
            PlayMusic(13);
            playercount++;
          } 
          if (!PlayerDamage && ringcount >= 200 && oldringcount < 200) {
            PlayMusic(13);
            playercount++;
          } 
          if (!PlayerDamage && ringcount >= 300 && oldringcount < 300) {
            PlayMusic(13);
            playercount++;
          } 
          if (PlayerPosY() < 32 && PlayerSJump && this.stageNumber == 3 && this.zoneNumber == 1) {
            this.limitBreak = false;
            this.scoreMoveFlag = false;
            this.gole_on = this.goleFlag = false;
            plsaveX = 0;
            plsaveY = 0;
            plsaveTime = 0;
            plsaveTime2 = 0;
            this.noTimeScore = false;
            this.selectStageNumber = (this.selectStageNumber + 1) % 3;
            if (this.selectStageNumber == 0)
              this.selectZoneNumber++; 
            this.resumeStage = (byte)(this.selectStageNumber + this.selectZoneNumber * 3);
            this.resumeZanki = (byte)playercount;
            this.resumeScore = scorecount;
            if (this.clearStageData < this.resumeStage)
              this.clearStageData = this.resumeStage; 
            save_resu();
            this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
            this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
            countClear();
            readStageObjectFlag = true;
            initStageStart();
          } 
          if (mode != MODE_FIELD)
            break; 
        } 
      } else if (mode == MODE_CONTINUE) {
        this.cpuTimer++;
        moveContinue();
      } 
      moveSysString();
    } catch (Throwable throwable) {}
  }
  
  public void DG() {
    try {
      Draw();
      if (this.SetSoftFlag) {
        SetSoftLabel();
        if (this.SetSoftCount > 0) {
          this.SetSoftCount--;
        } else {
          this.SetSoftFlag = false;
        } 
      } 
      if (drawRsm) {
        if (this.drawRsmCount == 0)
          this.drawRsmCount = 10; 
        if (this.drawRsmCount > 1) {
          this.drawRsmCount--;
        } else {
          this.drawRsmCount = 0;
          drawRsm = false;
        } 
      } 
    } catch (Throwable throwable) {}
  }
  
  public void paint(Graphics paramGraphics) {}
  
  public void Draw() {
    try {
      if (mode == MODE_INIT) {
        gg.setClip(0, 0, 240, 240);
        gg.setColor(0);
        gg.fillRect(0, 0, 240, 320);
        TK_DrawStringC(m_strText[44], 120, 180, 16777215, 658170);
      } else if (mode == this.MODE_FIELD_PAUSE) {
        gg.setClip(0, 0, 240, 240);
        gg.setFont(m_Font);
        TK_DrawBelt(true, false);
        TK_DrawStringC(softKeys[5], 120, 6, 16777215, 0);
        drawField();
        gg.setColor(0);
        for (byte b = 0; b < 84; b++)
          gg.fillRect(0, b * 2 + 36, 240, 1); 
        int[] arrayOfInt1 = { 0, 0, 0, 2 };
        int[] arrayOfInt2 = { 0, 16, 32, 16 };
        gg.drawRegion(this.m_imgObj[0], 0, arrayOfInt2[this.pauseTimer % 4], 16, 16, arrayOfInt1[this.pauseTimer % 4], 120 - m_Font.stringWidth(softKeys[15 + this.pauseSelect]) / 2 - 16 - 14, 94 + 30 * this.pauseSelect, 20);
        gg.drawRegion(this.m_imgObj[0], 0, arrayOfInt2[this.pauseTimer % 4], 16, 16, arrayOfInt1[this.pauseTimer % 4], 120 + m_Font.stringWidth(softKeys[15 + this.pauseSelect]) / 2 + 14, 94 + 30 * this.pauseSelect, 20);
        gg.setColor(16777215);
        gg.drawString(softKeys[15], 120 - m_Font.stringWidth(softKeys[15]) / 2, 93, 20);
        gg.drawString(softKeys[16], 120 - m_Font.stringWidth(softKeys[16]) / 2, 123, 20);
        this.pauseTimer++;
        initDisplay = true;
      } else if (mode == MODE_TITLE) {
        TK_TitleDraw();
      } else if (mode == MODE_STAGESELECT) {
        if (initDisplay) {
          gg.setClip(0, 0, 240, 240);
          gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
          gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
          gg.drawImage(this.m_imgCmd[LOGO], 153, 209, 20);
          initDisplay = false;
        } 
        gg.setColor(0);
        gg.fillRect(0, 36, 240, 168);
        this.SysCenter = 120 + this.SystxtTable[this.zonetable[this.selectZoneNumber]][2] / 2;
        gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.DAEN_B][0], this.SystxtTable[this.DAEN_B][1], this.SystxtTable[this.DAEN_B][2], this.SystxtTable[this.DAEN_B][3], rotNumTable[TRANS_NONE], this.SysCenter - this.SystxtTable[this.DAEN_B][2] - 1, 82, 20);
        if (this.selectZoneNumber == 6) {
          gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.ZONE][0], this.SystxtTable[this.ZONE][1], this.SystxtTable[this.ZONE][2], this.SystxtTable[this.ZONE][3], rotNumTable[TRANS_NONE], this.SysCenter - 48, 100, 20);
        } else {
          gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.ACT1 + this.selectStageNumber][0], this.SystxtTable[this.ACT1 + this.selectStageNumber][1], this.SystxtTable[this.ACT1 + this.selectStageNumber][2], this.SystxtTable[this.ACT1 + this.selectStageNumber][3], rotNumTable[TRANS_NONE], this.SysCenter - this.SystxtTable[this.DAEN_B][2] + 25, 108, 20);
          gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.ACT][0], this.SystxtTable[this.ACT][1], this.SystxtTable[this.ACT][2], this.SystxtTable[this.ACT][3], rotNumTable[TRANS_NONE], this.SysCenter - this.SystxtTable[this.DAEN_B][2] + 1, 116, 20);
          gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.ZONE][0], this.SystxtTable[this.ZONE][1], this.SystxtTable[this.ZONE][2], this.SystxtTable[this.ZONE][3], rotNumTable[TRANS_NONE], this.SysCenter - 65, 100, 20);
        } 
        gg.drawRegion(this.m_imgCmd[SYSTXT], this.SystxtTable[this.zonetable[this.selectZoneNumber]][0], this.SystxtTable[this.zonetable[this.selectZoneNumber]][1], this.SystxtTable[this.zonetable[this.selectZoneNumber]][2], this.SystxtTable[this.zonetable[this.selectZoneNumber]][3], rotNumTable[TRANS_NONE], 120 - this.SystxtTable[this.zonetable[this.selectZoneNumber]][2] / 2, 84, 20);
        gg.drawImage(this.m_imgCmd[T_CUR1], 213, 96, 20);
        gg.drawImage(this.m_imgCmd[T_CUR2], 22, 96, 20);
      } else if (mode == MODE_CONTINUE) {
        gg.setClip(0, 0, 240, 240);
        this.animeTimer++;
        drawContinue();
      } else if (mode == MODE_STARTSTAGE) {
        gg.setClip(0, 0, 240, 240);
        gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
        gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
        gg.drawImage(this.m_imgCmd[LOGO], 153, 209, 20);
        initDisplay = false;
        gg.setColor(0);
        gg.fillRect(0, 36, 240, 168);
      } else if (mode == MODE_FIELD) {
        if (initDisplay) {
          gg.setClip(0, 0, 240, 240);
          gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
          gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
          gg.drawImage(this.m_imgCmd[LOGO], 153, 209, 20);
          this.drawZankiFlag = this.drawRingFlag = true;
          drawSystemData();
          initDisplay = false;
        } else {
          drawSystemData();
        } 
        if (muteki2count > 0) {
          muteki2count--;
          if (muteki2count % 2 == 0) {
            this.playerDraw = true;
          } else {
            this.playerDraw = false;
          } 
        } else {
          this.playerDraw = false;
        } 
        drawField();
        this.animeTimer++;
      } 
    } catch (Throwable throwable) {}
    if (mode == MODE_STARTSTAGE || mode == MODE_FIELD) {
      if (this.outWipe) {
        gg.setColor(0);
        for (byte b = 0; b < 10; b++) {
          int i = 24 - (this.wipeCount - b) * 4;
          if (i <= 0) {
            i = 0;
          } else {
            if (i > 24)
              i = 24; 
            gg.fillRect(b * 24 + 24 - i, 0, i, 240);
          } 
        } 
        if (22 < this.wipeCount) {
          this.wipeCount = 0;
          this.outWipe = false;
        } 
        this.wipeCount++;
      } 
      drawSysString();
      if (this.putWipe) {
        gg.setColor(0);
        for (byte b = 0; b < 10; b++) {
          if (this.wipeCount - b > 0) {
            int i = this.wipeCount - b << 2;
            if (i > 24)
              i = 24; 
            gg.fillRect(b * 24, 0, i, 240);
          } 
        } 
        if (22 < this.wipeCount) {
          this.wipeCount = 0;
          this.putWipe = false;
        } 
        this.wipeCount++;
      } 
      if (this.putNowLoading)
        TK_DrawStringC(m_strText[44], 120, 180, 16777215, 658170); 
    } 
    if (drawRsm || this.drawZankiFlag || this.drawTimeFlag || this.drawRingFlag || mode != MODE_FIELD) {
      this.drawZankiFlag = this.drawTimeFlag = this.drawRingFlag = false;
      flushGraphics(0, this.displayOffsetY, 240, 240);
    } else {
      flushGraphics(0, this.displayOffsetY2, 240, 168);
    } 
    serviceRepaints();
  }
  
  public void drawField() {
    gg.setClip(0, 36, 240, 168);
    if (!pauseGame) {
      DistantBg.paint(mapView[0], mapView[1]);
      if (this.zoneNumber == 1 || this.zoneNumber == 5) {
        DrawMap(gg);
      } else {
        DrawMap3(gg);
      } 
      CallObjectDraw();
      DrawObj2();
      if (!PlayerDie)
        drawPlayerImage(gg); 
      drawObjData();
      if (this.zoneNumber == 1 || this.zoneNumber == 5) {
        DrawMap2(gg);
      } else {
        DrawMap4(gg);
      } 
      CallObjectDrawFront();
      if (this.zoneNumber == 1)
        objAwaData_draw(); 
      if (PlayerDie)
        drawPlayerImage(gg); 
      if (this.zoneNumber == 1) {
        int i = (168 - this.waterH2 - mapView[1]) / 16 + 1;
        int j = (this.waterH2 - mapView[1]) % 2;
        if (j < 0)
          j = 0; 
        for (byte b = 0; b < i; b++) {
          if (b == 0)
            for (byte b1 = 0; b1 < 8; b1++)
              drawRegion(gg, this.m_imgObj[111], 0, this.animeTimer % 3 * 16, 32, 16, rotNumTable[0], b1 * 32, this.waterH2 - mapView[1] - 8, 20);  
          if (b == 0) {
            drawRegion(gg, this.m_imgObj[5], 0, j, 240, 16 - j, rotNumTable[0], 0, this.waterH2 - mapView[1] + b * 16, 20);
          } else {
            drawRegion(gg, this.m_imgObj[5], 0, 0, 240, 16, rotNumTable[0], 0, this.waterH2 - mapView[1] + b * 16 - j, 20);
          } 
        } 
        if (this.bressCount < 600 && this.bressCount / 60 % 2 == 1) {
          if (this.bressMusic) {
            PlayMusic(24);
            this.bressMusic = false;
          } 
          drawNumber(116, 80, this.bressCount / 60 / 2, 1);
        } 
        if (this.bressCount >= 600) {
          if (!this.bressMusic)
            PlayZoneBGML(); 
          this.bressMusic = true;
        } 
      } 
      if (this.endingModeOn)
        drawEnding(); 
      if ((playercount <= 0 && PlayerDie) || (this.timeUpDie && timecount == 59 && timecount2 == 9)) {
        int i = (660 - diecount) * 6;
        if (120 - i < 8)
          i = 112; 
        if (timecount == 59 && timecount2 == 9) {
          gg.drawRegion(this.m_imgCmd[TIMEOVER], 0, 0, 64, 16, rotNumTable[TRANS_NONE], i - 64, 108, 20);
          gg.drawRegion(this.m_imgCmd[TIMEOVER], 0, 16, 64, 16, rotNumTable[TRANS_NONE], 240 - i, 108, 20);
        } else {
          gg.drawRegion(this.m_imgCmd[GAMEOVER], 0, 0, 64, 16, rotNumTable[TRANS_NONE], i - 64, 108, 20);
          gg.drawRegion(this.m_imgCmd[GAMEOVER], 0, 16, 64, 16, rotNumTable[TRANS_NONE], 240 - i, 108, 20);
        } 
      } 
    } 
  }
  
  public void ObjImageClear() {
    this.m_imgObj = null;
    try {
      System.gc();
      Thread.sleep(200L);
      this.m_imgObj = new Image[150];
      System.gc();
      Thread.sleep(200L);
    } catch (Exception exception) {}
  }
  
  public void StageDataTableClear() {
    zoneActTable[0] = null;
    zoneActTable[1] = null;
    zoneActTable[2] = null;
    zoneActTable[3] = null;
    try {
      System.gc();
      Thread.sleep(200L);
    } catch (Exception exception) {}
  }
  
  public void scroll(Graphics paramGraphics, int paramInt1, int paramInt2) {}
  
  public boolean CheckSoftLabel(int paramInt, String paramString) {
    String[] arrayOfString = new String[2];
    arrayOfString[paramInt] = cmd[paramInt].getLabel();
    return arrayOfString[paramInt].equals(paramString);
  }
  
  public void SetSoftLabel(int paramInt, String paramString) {
    String[] arrayOfString = new String[2];
    try {
      arrayOfString[0] = cmd[0].getLabel();
      arrayOfString[1] = cmd[1].getLabel();
      if (arrayOfString[paramInt].equals(paramString))
        return; 
      removeCommand(cmd[1]);
      removeCommand(cmd[0]);
      arrayOfString[paramInt] = paramString;
      cmd[0] = new Command(arrayOfString[0], 1, 1);
      cmd[1] = new Command(arrayOfString[1], 1, 1);
      addCommand(cmd[0]);
      addCommand(cmd[1]);
    } catch (Throwable throwable) {}
  }
  
  public String ZeroSup(int paramInt1, int paramInt2) {
    String str = paramInt1 + "";
    for (int i = str.length(); i < paramInt2; i++)
      str = Character.MIN_VALUE + str; 
    return str;
  }
  
  public String ZeroSup(String paramString, int paramInt) {
    String str = paramString;
    for (int i = str.length(); i < paramInt; i++)
      str = Character.MIN_VALUE + str; 
    return str;
  }
  
  public boolean DrawWaterMap(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.zoneNumber == 1) {
      if (paramInt1 > 60 && paramInt1 < 100) {
        paramInt1 -= 60;
        drawRegion(gg, this.m_imgObj[84], paramInt1 % 10 << 4, (paramInt1 / 10 << 4) + (this.animeTimer % 4 << 4 << 2), 16, 16, rotNumTable[paramInt2], paramInt3, paramInt4, 20);
        return false;
      } 
      if (paramInt1 >= 186) {
        paramInt1 -= 186;
        drawRegion(gg, this.m_imgObj[84], paramInt1 % 10 << 4, (paramInt1 / 10 << 4) + (this.animeTimer % 4 << 4) + 256, 16, 16, rotNumTable[paramInt2], paramInt3, paramInt4, 20);
        return false;
      } 
    } else if (this.zoneNumber == 5 && this.animeTimer % 3 != 0) {
      if (paramInt1 >= 250 && paramInt1 < 290) {
        paramInt1 -= 250;
        drawRegion(gg, this.m_imgObj[84], paramInt1 % 10 << 4, (paramInt1 / 10 << 4) + (this.animeTimer % 3 - 1 << 4 << 2), 16, 16, rotNumTable[paramInt2], paramInt3, paramInt4, 20);
        return false;
      } 
      if (paramInt1 >= 560 && paramInt1 < 570) {
        paramInt1 -= 560;
        drawRegion(gg, this.m_imgObj[84], paramInt1 % 10 << 4, (paramInt1 / 10 << 4) + (this.animeTimer % 3 - 1 << 4) + 128, 16, 16, rotNumTable[paramInt2], paramInt3, paramInt4, 20);
        return false;
      } 
      if (paramInt1 >= 30 && paramInt1 < 90) {
        paramInt1 -= 30;
        drawRegion(gg, this.m_imgObj[84], paramInt1 % 10 << 4, (paramInt1 / 10 << 4) + (this.animeTimer % 3 - 1) * 96 + 160, 16, 16, rotNumTable[paramInt2], paramInt3, paramInt4, 20);
        return false;
      } 
    } 
    return true;
  }
  
  public void setMapData() {
    for (byte b = 0; b < mapData.length >> 1; b++) {
      int i = mapData[b << 1] & 0xFF;
      this.hitChk[b] = 0;
      this.hitChk2[b] = 0;
      byte b1 = (byte)(i << 6);
      b1 = (byte)Math.abs(b1 >> 6);
      if (i >> 5 <= 1)
        this.hitChk2[b] = 1; 
      if ((i >> 5) % 2 == 0)
        this.hitChk[b] = 1; 
      this.rot[b] = (byte)(i >> 3 & 0x3);
      this.imageOffset[b] = 0;
      if ((i & 0x1) == 1) {
        this.imageOffset[b] = 1;
      } else if ((i & 0x3) == 2) {
        this.imageOffset[b] = 2;
      } 
    } 
  }
  
  public void DrawMapRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    for (i = paramInt1; i < paramInt1 + paramInt3; i++) {
      if (MapW << 4 > (mapView[0] >> 4) + i)
        for (j = paramInt2; j < paramInt2 + paramInt4; j++) {
          int n = (mapView[0] >> 4) + i >> 4;
          int i1 = (mapView[1] >> 4) + j >> 4;
          i1 %= MapH;
          try {
            m = (tempWorldMapData[i1][n] << 9) + (((mapView[0] >> 4) + i & 0xF) + (((mapView[1] >> 4) + j & 0xF) << 4) << 1) + 1;
            k = mapData[m] & 0xFF;
            k += this.imageOffset[m >> 1] << 8;
            if (k != 0) {
              if (this.zoneNumber == 2) {
                if (k == 367 || k == 366)
                  k = (k + this.animeTimer / 5 & 0x1) + 366; 
                if (k == 365 || k == 364)
                  k = (k + this.animeTimer / 5 & 0x1) + 364; 
                if (k == 363 || k == 362)
                  k = (k + this.animeTimer / 5 & 0x1) + 362; 
              } 
              int i2 = TRANS_NONE;
              if (this.rot[m >> 1] != 0)
                if (this.rot[m >> 1] == 1) {
                  i2 = TRANS_MIRROR;
                } else if (this.rot[m >> 1] == 2) {
                  i2 = TRANS_MIRROR_ROT180;
                } else if (this.rot[m >> 1] == 3) {
                  i2 = TRANS_ROT180;
                }  
              drawRegion(gg, m_imgMimg, k % 10 << 4, k / 10 << 4, 16, 16, rotNumTable[i2], (i << 4) - (mapView[0] & 0xF), (j << 4) - (mapView[1] & 0xF), 20);
            } 
          } catch (Throwable throwable) {}
        }  
    } 
  }
  
  public void DrawMap(Graphics paramGraphics) {
    byte b2 = 0;
    int i = 0;
    int j = 0;
    for (byte b1 = 0; b1 < 16; b1++) {
      if (MapW << 4 <= (mapView[0] >> 4) + b1) {
        this.drawMapData[b1][b2][2] = 0;
      } else {
        for (b2 = 0; b2 < 12; b2++) {
          int k = (mapView[0] >> 4) + b1 >> 4;
          int m = (mapView[1] >> 4) + b2 >> 4;
          m %= MapH;
          try {
            j = (tempWorldMapData[m][k] << 9) + (((mapView[0] >> 4) + b1 & 0xF) + (((mapView[1] >> 4) + b2 & 0xF) << 4) << 1) + 1;
            i = mapData[j] & 0xFF;
            i += this.imageOffset[j >> 1] << 8;
            this.drawMapData[b1][b2][0] = i;
            if (i != 0) {
              int n = TRANS_NONE;
              if (this.rot[j >> 1] != 0)
                if (this.rot[j >> 1] == 1) {
                  n = TRANS_MIRROR;
                } else if (this.rot[j >> 1] == 2) {
                  n = TRANS_MIRROR_ROT180;
                } else if (this.rot[j >> 1] == 3) {
                  n = TRANS_ROT180;
                }  
              this.drawMapData[b1][b2][1] = n;
              this.drawMapData[b1][b2][2] = 0;
              if (mapFrontData[i] != 0) {
                this.drawMapData[b1][b2][2] = 1;
                if (mapFrontData[i] == 2) {
                  gg.setColor(8738);
                  gg.fillRect((b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF) + 36, 16, 16);
                } 
              } else if (DrawWaterMap(i, n, (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF))) {
                drawRegion(paramGraphics, m_imgMimg, i % 10 << 4, i / 10 << 4, 16, 16, rotNumTable[n], (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF), 20);
              } 
            } 
          } catch (Throwable throwable) {}
        } 
      } 
    } 
  }
  
  public void DrawMap2(Graphics paramGraphics) {
    byte b2 = 0;
    for (byte b1 = 0; b1 < 16; b1++) {
      if (MapW << 4 > (mapView[0] >> 4) + b1)
        for (b2 = 0; b2 < 12; b2++) {
          try {
            if (this.drawMapData[b1][b2][2] == 1 && DrawWaterMap(this.drawMapData[b1][b2][0], this.drawMapData[b1][b2][1], (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF)))
              drawRegion(paramGraphics, m_imgMimg, this.drawMapData[b1][b2][0] % 10 << 4, this.drawMapData[b1][b2][0] / 10 << 4, 16, 16, rotNumTable[this.drawMapData[b1][b2][1]], (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF), 20); 
          } catch (Throwable throwable) {}
        }  
    } 
  }
  
  public void DrawMap3(Graphics paramGraphics) {
    byte b2 = 0;
    int i = 0;
    int j = 0;
    for (byte b1 = 0; b1 < 16; b1++) {
      if (MapW << 4 <= (mapView[0] >> 4) + b1) {
        this.drawMapData[b1][b2][2] = 0;
      } else {
        for (b2 = 0; b2 < 12; b2++) {
          int k = (mapView[0] >> 4) + b1 >> 4;
          int m = (mapView[1] >> 4) + b2 >> 4;
          m %= MapH;
          try {
            j = (tempWorldMapData[m][k] << 9) + (((mapView[0] >> 4) + b1 & 0xF) + (((mapView[1] >> 4) + b2 & 0xF) << 4) << 1) + 1;
            i = mapData[j] & 0xFF;
            i += this.imageOffset[j >> 1] << 8;
            this.drawMapData[b1][b2][0] = i;
            if (i != 0) {
              if (this.zoneNumber == 2) {
                if (i == 367 || i == 366)
                  i = (i + this.animeTimer / 5 & 0x1) + 366; 
                if (i == 365 || i == 364)
                  i = (i + this.animeTimer / 5 & 0x1) + 364; 
                if (i == 363 || i == 362)
                  i = (i + this.animeTimer / 5 & 0x1) + 362; 
              } 
              int n = TRANS_NONE;
              if (this.rot[j >> 1] != 0)
                if (this.rot[j >> 1] == 1) {
                  n = TRANS_MIRROR;
                } else if (this.rot[j >> 1] == 2) {
                  n = TRANS_MIRROR_ROT180;
                } else if (this.rot[j >> 1] == 3) {
                  n = TRANS_ROT180;
                }  
              this.drawMapData[b1][b2][1] = n;
              this.drawMapData[b1][b2][2] = 0;
              if (mapFrontData[i] != 0) {
                this.drawMapData[b1][b2][2] = 1;
              } else {
                drawRegion(paramGraphics, m_imgMimg, i % 10 << 4, i / 10 << 4, 16, 16, rotNumTable[n], (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF), 20);
              } 
            } 
          } catch (Throwable throwable) {}
        } 
      } 
    } 
  }
  
  public void DrawMap4(Graphics paramGraphics) {
    byte b2 = 0;
    for (byte b1 = 0; b1 < 16; b1++) {
      if (MapW << 4 > (mapView[0] >> 4) + b1)
        for (b2 = 0; b2 < 12; b2++) {
          try {
            if (this.drawMapData[b1][b2][2] == 1)
              drawRegion(paramGraphics, m_imgMimg, this.drawMapData[b1][b2][0] % 10 << 4, this.drawMapData[b1][b2][0] / 10 << 4, 16, 16, rotNumTable[this.drawMapData[b1][b2][1]], (b1 << 4) - (mapView[0] & 0xF), (b2 << 4) - (mapView[1] & 0xF), 20); 
          } catch (Throwable throwable) {}
        }  
    } 
  }
  
  public void drawHitMap() {}
  
  public void drawChipPut(int paramInt1, int paramInt2, int paramInt3) {
    int i = (blockLinkTable[paramInt3] & 0xFF) * 16;
    gg.setColor(16777215);
    for (byte b = 0; b < 16; b++) {
      gg.fillRect(paramInt1 + b, paramInt2 + 36 + 16 - Math.abs(scdtblwk[i + b]), 1, Math.abs(scdtblwk[i + b]));
      if (Math.abs(scdtblwk[i + b + 4096]) > 16);
    } 
    gg.setColor(0);
    drawStringCenter(gg, f, paramInt3 + "", paramInt1, paramInt2, true);
  }
  
  public void drawStringCenter(Graphics paramGraphics, Font paramFont, String paramString, int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramBoolean)
      drawString(paramGraphics, paramString, paramInt1 - paramFont.stringWidth(paramString) / 2, paramInt2); 
  }
  
  public void drawString(Graphics paramGraphics, String paramString, int paramInt1, int paramInt2) {
    paramInt2 += FontPos;
    paramGraphics.drawString(paramString, paramInt1 - 1, paramInt2, 20);
    paramGraphics.drawString(paramString, paramInt1 + 1, paramInt2, 20);
    paramGraphics.drawString(paramString, paramInt1, paramInt2 + 1, 20);
    paramGraphics.drawString(paramString, paramInt1, paramInt2 - 1, 20);
    paramGraphics.setColor(16777215);
    paramGraphics.drawString(paramString, paramInt1, paramInt2, 20);
  }
  
  public int dSin(int paramInt) {
    int i = paramInt % 360;
    return (i >= 0 && i <= 90) ? (sinData[i] / 100) : ((i > 90 && i <= 180) ? (sinData[90 - i - 90] / 100) : ((i > 180 && i <= 270) ? (-1 * sinData[i - 180] / 100) : ((i > 270 && i <= 359) ? (-1 * sinData[90 - i - 270] / 100) : 0)));
  }
  
  public int dCos(int paramInt) {
    int i = paramInt % 360;
    return (i >= 0 && i < 90) ? (-1 * sinData[89 - i] / 100) : ((i >= 90 && i < 180) ? (sinData[i - 90] / 100) : ((i >= 180 && i < 270) ? (sinData[89 - i - 180] / 100) : ((i >= 270 && i <= 359) ? (-1 * sinData[i - 270] / 100) : 0)));
  }
  
  private void addScoreCount(int paramInt) {
    if (99950000 > scorecount && scorecount % 50000 > (scorecount + paramInt) % 50000) {
      playercount++;
      PlayMusic(13);
    } 
    scorecount += paramInt;
    if (scorecount > 99999999)
      scorecount = 99999999; 
  }
  
  private void addScoreCount(int paramInt1, int paramInt2) {
    if (99950000 > scorecount && scorecount % 50000 > (scorecount + paramInt1) % 50000) {
      playercount++;
      PlayMusic(30);
    } 
    scorecount += paramInt1;
    if (scorecount > 99999999)
      scorecount = 99999999; 
  }
  
  public void drawSystemData() {
    gg.setClip(0, 0, 240, 240);
    if (this.oldRingCount != ringcount || this.oldScoreCount != scorecount) {
      this.drawRingFlag = true;
    } else if (ringcount == 0 && (this.animeTimer & 0x1) == 0) {
      this.drawRingFlag = true;
    } 
    this.oldRingCount = ringcount;
    this.oldScoreCount = scorecount;
    if (this.drawRingFlag) {
      gg.drawRegion(this.m_imgCmd[LOGOLINE], 0, 0, 100, 32, rotNumTable[TRANS_NONE], 0, 0, 20);
      gg.drawRegion(this.m_imgCmd[WINDOW_RING], 0, 0, 55, 26, rotNumTable[TRANS_NONE], 4, 5, 20);
      if (ringcount != 0 || (this.animeTimer >> 1 & 0x1) == 0)
        drawNumber(31, 3, ringcount, 3); 
      drawNumber(31, 18, scorecount, 8);
    } 
    if (timecount == 0 || this.oldTimeCount != timecount2 * 100 + timecount)
      this.drawTimeFlag = true; 
    this.oldTimeCount = timecount2 * 100 + timecount;
    if (this.drawTimeFlag) {
      gg.drawRegion(this.m_imgCmd[LOGOLINE], 192, 0, 48, 32, rotNumTable[TRANS_NONE], 192, 0, 20);
      gg.drawRegion(this.m_imgCmd[WINDOW_TIME], 0, 0, 44, 13, rotNumTable[TRANS_NONE], 192, 18, 20);
      drawNumber(200, 16, timecount2, 1);
      drawNumber(216, 16, timecount, 2);
    } 
    if (playercount > 99)
      playercount = 99; 
    if (this.oldZankiCount != playercount)
      this.drawZankiFlag = true; 
    this.oldZankiCount = playercount;
    if (this.drawZankiFlag) {
      gg.drawRegion(this.m_imgCmd[LOGOLINE], 0, 0, 50, 32, rotNumTable[TRANS_NONE], 0, 204, 20);
      gg.drawRegion(this.m_imgCmd[WINDOW_ZANKI], 0, 0, 22, 20, rotNumTable[TRANS_NONE], 5, 213, 20);
      if (playercount > 9) {
        drawNumber(31, 219, playercount, 2);
      } else {
        drawNumber(31, 219, playercount, 1);
      } 
    } 
  }
  
  public void drawNumber(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt3 % 10;
    gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], 7 * i, 0, 7, 13, rotNumTable[TRANS_NONE], paramInt1 + (paramInt4 << 3) - 8, paramInt2, 20);
    for (byte b = 1; b < paramInt4; b++) {
      int j = 1;
      for (byte b1 = 0; b1 < b; b1++)
        j *= 10; 
      i = paramInt3 / j % 10;
      gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], 7 * i, 0, 7, 13, rotNumTable[TRANS_NONE], paramInt1 + (paramInt4 << 3) - (b << 3) - 8, paramInt2, 20);
    } 
  }
  
  private boolean kyuryuchk() {
    if (this.zoneNumber == 1)
      if (this.stageNumber == 0) {
        try {
          int[][] arrayOfInt = searchObject(62, 227);
          if (arrayOfInt.length > 0 && arrayOfInt[0][5] != 0 && this.kyuryuTable[0][0] <= PlayerPosX() && this.kyuryuTable[0][2] > PlayerPosX() && this.kyuryuTable[0][1] <= PlayerPosY() - 16 && this.kyuryuTable[0][3] > PlayerPosY() - 16)
            return true; 
        } catch (Throwable throwable) {}
        if (this.kyuryuTable[1][0] <= PlayerPosX() && this.kyuryuTable[1][2] > PlayerPosX() && this.kyuryuTable[1][1] <= PlayerPosY() && this.kyuryuTable[1][3] > PlayerPosY())
          return true; 
      } else if (this.stageNumber == 1) {
        try {
          if (this.animeTimer % 60 < 30 && this.kyuryuTable[2][0] <= PlayerPosX() && this.kyuryuTable[2][2] > PlayerPosX() && this.kyuryuTable[2][1] <= PlayerPosY() && this.kyuryuTable[2][3] > PlayerPosY())
            return true; 
          int[][] arrayOfInt = searchObject(93, -1);
          if (arrayOfInt.length > 0) {
            if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[2][0] <= PlayerPosX() && this.kyuryuTable[2][2] > PlayerPosX() && this.kyuryuTable[2][1] <= PlayerPosY() && this.kyuryuTable[2][3] > PlayerPosY())
              return true; 
          } else {
            arrayOfInt = searchObject(92, -1);
            if (arrayOfInt.length > 0) {
              if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[2][0] <= PlayerPosX() && this.kyuryuTable[2][2] > PlayerPosX() && this.kyuryuTable[2][1] <= PlayerPosY() && this.kyuryuTable[2][3] > PlayerPosY())
                return true; 
            } else if (this.kyuryuTable[2][0] <= PlayerPosX() && this.kyuryuTable[2][2] > PlayerPosX() && this.kyuryuTable[2][1] <= PlayerPosY() && this.kyuryuTable[2][3] > PlayerPosY()) {
              return true;
            } 
          } 
        } catch (Throwable throwable) {}
      } else if (this.stageNumber == 2) {
        try {
          if (this.animeTimer % 60 < 30 && this.kyuryuTable[3][0] <= PlayerPosX() && this.kyuryuTable[3][2] > PlayerPosX() && this.kyuryuTable[3][1] <= PlayerPosY() && this.kyuryuTable[3][3] > PlayerPosY())
            return true; 
          int[][] arrayOfInt = searchObject(93, -1);
          if (arrayOfInt.length > 0) {
            if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[3][0] <= PlayerPosX() && this.kyuryuTable[3][2] > PlayerPosX() && this.kyuryuTable[3][1] <= PlayerPosY() && this.kyuryuTable[3][3] > PlayerPosY())
              return true; 
          } else {
            arrayOfInt = searchObject(92, -1);
            if (arrayOfInt.length > 0) {
              if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[3][0] <= PlayerPosX() && this.kyuryuTable[3][2] > PlayerPosX() && this.kyuryuTable[3][1] <= PlayerPosY() && this.kyuryuTable[3][3] > PlayerPosY())
                return true; 
            } else if (this.kyuryuTable[3][0] <= PlayerPosX() && this.kyuryuTable[3][2] > PlayerPosX() && this.kyuryuTable[3][1] <= PlayerPosY() && this.kyuryuTable[3][3] > PlayerPosY()) {
              return true;
            } 
          } 
        } catch (Throwable throwable) {}
      } else if (this.stageNumber == 3) {
        try {
          if (this.animeTimer % 60 < 30 && this.kyuryuTable[4][0] <= PlayerPosX() && this.kyuryuTable[4][2] > PlayerPosX() && this.kyuryuTable[4][1] <= PlayerPosY() && this.kyuryuTable[4][3] > PlayerPosY())
            return true; 
          int[][] arrayOfInt = searchObject(93, -1);
          if (arrayOfInt.length > 0) {
            if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[4][0] <= PlayerPosX() && this.kyuryuTable[4][2] > PlayerPosX() && this.kyuryuTable[4][1] <= PlayerPosY() && this.kyuryuTable[4][3] > PlayerPosY())
              return true; 
          } else {
            arrayOfInt = searchObject(92, -1);
            if (arrayOfInt.length > 0) {
              if (arrayOfInt[0][2] < PlayerPosX() && this.kyuryuTable[4][0] <= PlayerPosX() && this.kyuryuTable[4][2] > PlayerPosX() && this.kyuryuTable[4][1] <= PlayerPosY() && this.kyuryuTable[4][3] > PlayerPosY())
                return true; 
            } else if (this.kyuryuTable[4][0] <= PlayerPosX() && this.kyuryuTable[4][2] > PlayerPosX() && this.kyuryuTable[4][1] <= PlayerPosY() && this.kyuryuTable[4][3] > PlayerPosY()) {
              return true;
            } 
          } 
        } catch (Throwable throwable) {}
      }  
    return false;
  }
  
  public void initGoleStart() {
    this.SysString = new int[this.SysStringMax][15];
    PlayMusic(20);
    byte b1 = 0;
    this.SysCount = 0;
    this.golecount = 30;
    this.goleFlag = true;
    this.SysString[b1][0] = 1;
    this.SysString[b1][1] = this.DAEN_B;
    this.SysString[b1][2] = 240;
    this.SysString[b1][3] = 82;
    this.SysString[b1][8] = 1 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b1][9] = 0;
    this.SysString[++b1][0] = 1;
    this.SysString[b1][1] = this.ACT1 + this.selectStageNumber;
    this.SysString[b1][2] = 240;
    this.SysString[b1][3] = 108;
    this.SysString[b1][8] = 25 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b1][9] = 3;
    this.SysString[++b1][0] = 1;
    this.SysString[b1][1] = this.ACT;
    this.SysString[b1][2] = 240;
    this.SysString[b1][3] = 116;
    this.SysString[b1][8] = -1 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b1][9] = 2;
    this.SysString[++b1][0] = 1;
    this.SysString[b1][1] = this.SONIC_HAS;
    this.SysString[b1][2] = 0 - this.SystxtTable[this.SysString[b1][1]][2];
    this.SysString[b1][3] = 84;
    this.SysString[b1][8] = -this.SystxtTable[this.SysString[b1][1]][2];
    this.SysString[b1][9] = 0;
    this.SysCenter = 120 + this.SystxtTable[this.SysString[b1][1]][2] / 2;
    this.SysString[++b1][0] = 1;
    this.SysString[b1][1] = this.PASSED;
    this.SysString[b1][2] = 0 - this.SystxtTable[this.SysString[b1][1]][2];
    this.SysString[b1][3] = 100;
    this.SysString[b1][8] = -this.SystxtTable[this.SysString[b1 - 1][1]][2] / 2 - this.SystxtTable[this.SysString[b1][1]][2] / 2;
    this.SysString[b1][9] = 1;
    b1++;
    for (byte b2 = 0; b2 < this.SysString.length; b2++) {
      if (this.SysString[b2][0] == 1) {
        this.SysString[b2][4] = this.SystxtTable[this.SysString[b2][1]][0];
        this.SysString[b2][5] = this.SystxtTable[this.SysString[b2][1]][1];
        this.SysString[b2][6] = this.SystxtTable[this.SysString[b2][1]][2];
        this.SysString[b2][7] = this.SystxtTable[this.SysString[b2][1]][3];
      } 
    } 
  }
  
  public void initStageStart() {
    try {
      this.water_flag = 0;
      this.water_flag2 = 0;
      this.water_flag3 = 0;
      this.water_flag4 = 0;
      bossModeOn = false;
      bossBreakOn = false;
      this.gole_on = false;
      this.ChkVecR = true;
      this.ChkVecL = true;
      MapEndCounter = 0;
      this.bressCount = 2100;
      this.bressMusic = true;
      indata = new DataInputStream(getClass().getResourceAsStream("/zone" + (this.zoneNumber + 1) + ".bmd"));
      MapW = (worldMapData[this.zoneNumber][this.stageNumber][0]).length;
      MapH = (worldMapData[this.zoneNumber][this.stageNumber]).length;
      tempWorldMapData = new byte[MapH][MapW];
      for (byte b = 0; b < MapH; b++) {
        for (byte b3 = 0; b3 < MapW; b3++)
          tempWorldMapData[b][b3] = worldMapData[this.zoneNumber][this.stageNumber][b][b3]; 
      } 
      indata.read(mapData);
      indata.close();
      setMapData();
    } catch (Throwable throwable) {}
    InitObj2();
    this.SysString = new int[this.SysStringMax][15];
    this.cpuTimer = 0;
    this.animeTimer = 0;
    mutekicount = 0;
    muteki2count = 0;
    this.objChkNum = 0;
    this.objChkPoint = 0;
    mode = MODE_STARTSTAGE;
    this.SetSoftFlag = true;
    this.SetSoftCount = 10;
    this.m_bScrollLock = 0;
    this.limitBreak = false;
    ResetSound();
    PlayZoneBGM();
    byte b2 = 0;
    this.SysCount = 0;
    this.SysString[b2][0] = 1;
    this.SysString[b2][1] = this.DAEN_B;
    this.SysString[b2][2] = 240;
    this.SysString[b2][3] = 82;
    this.SysString[b2][8] = 1 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b2][9] = 0;
    this.SysString[++b2][0] = 1;
    this.SysString[b2][1] = this.ACT1 + this.selectStageNumber;
    this.SysString[b2][2] = 240;
    this.SysString[b2][3] = 108;
    this.SysString[b2][8] = 25 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b2][9] = 3;
    this.SysString[++b2][0] = 1;
    this.SysString[b2][1] = this.ACT;
    this.SysString[b2][2] = 240;
    this.SysString[b2][3] = 116;
    this.SysString[b2][8] = -1 - this.SystxtTable[this.SysString[0][1]][2];
    this.SysString[b2][9] = 2;
    this.SysString[++b2][0] = 1;
    this.SysString[b2][1] = this.zonetable[this.selectZoneNumber];
    this.SysString[b2][2] = 0 - this.SystxtTable[this.SysString[b2][1]][2];
    this.SysString[b2][3] = 84;
    this.SysString[b2][8] = -this.SystxtTable[this.SysString[b2][1]][2];
    this.SysString[b2][9] = 0;
    this.SysCenter = 120 + this.SystxtTable[this.SysString[b2][1]][2] / 2;
    b2++;
    if (this.selectZoneNumber == 6) {
      this.SysString[b2][0] = 1;
      this.SysString[b2][1] = this.ZONE;
      this.SysString[b2][2] = 0 - this.SystxtTable[this.SysString[b2][1]][2];
      this.SysString[b2][3] = 100;
      this.SysString[b2][8] = -48;
      this.SysString[b2][9] = 1;
    } else {
      this.SysString[b2][0] = 1;
      this.SysString[b2][1] = this.ZONE;
      this.SysString[b2][2] = 0 - this.SystxtTable[this.SysString[b2][1]][2];
      this.SysString[b2][3] = 100;
      this.SysString[b2][8] = -65;
      this.SysString[b2][9] = 1;
    } 
    b2++;
    for (byte b1 = 0; b1 < this.SysString.length; b1++) {
      if (this.SysString[b1][0] == 1) {
        this.SysString[b1][4] = this.SystxtTable[this.SysString[b1][1]][0];
        this.SysString[b1][5] = this.SystxtTable[this.SysString[b1][1]][1];
        this.SysString[b1][6] = this.SystxtTable[this.SysString[b1][1]][2];
        this.SysString[b1][7] = this.SystxtTable[this.SysString[b1][1]][3];
      } 
    } 
  }
  
  public void drawNumber2(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt3 % 10;
    for (byte b = 1;; b++) {
      int j = 1;
      byte b1;
      for (b1 = 0; b1 < b; b1++)
        j *= 10; 
      if (paramInt3 / j == 0) {
        j = b;
        drawRegion(gg, this.m_imgCmd[SYSTXT2], 8 * i, 48, 8, 16, rotNumTable[TRANS_NONE], paramInt1 - 8, paramInt2, 20);
        for (b = 1; b < j; b++) {
          int k = 1;
          for (b1 = 0; b1 < b; b1++)
            k *= 10; 
          i = paramInt3 / k % 10;
          drawRegion(gg, this.m_imgCmd[SYSTXT2], 8 * i, 48, 8, 16, rotNumTable[TRANS_NONE], paramInt1 - b * 8 - 8, paramInt2, 20);
        } 
        return;
      } 
    } 
  }
  
  public void readStageObject() {
    try {
      ObjImageClear();
      StageDataTableClear();
      initStage(this.zoneNumber + 1);
      objectInit(this.stageNumber);
    } catch (Throwable throwable) {}
  }
  
  public void countClear() {
    ringcount = 0;
    timecount = 0;
    timecount2 = 0;
    diecount = 0;
    bariacount = 0;
    speedupcount = 0;
    mutekicount = 0;
    muteki2count = 0;
    this.damageMoveTimer = 0;
    this.LookUpCount = 0;
    this.CrouchCount = 0;
    for (byte b1 = 0; b1 < objData.length; b1++)
      objData[b1][0] = 0; 
    PlayerSJump = false;
    PlayerDamage = false;
    PlayerDush = false;
    PlayerWater = false;
    PlayerSWater = false;
    PlayerBou = false;
    PlayerJump = false;
    PlayerBall = false;
    PlayerDie = false;
    this.bressDie = false;
    this.timeUpDie = false;
    PlayerCrouch = false;
    PlayerLookUp = false;
    PlayerNoCol = false;
    PlayerNoCtrl = false;
    TimerStop = false;
    TimerClear = false;
    comboScore = 0;
    byte b2;
    for (b2 = 0; b2 < 'Ā'; b2++) {
      switchflag[b2] = false;
      switchflag2[b2] = false;
    } 
    ObjectListNum = 0;
    this.noDataPointer = 0;
    for (b2 = 0; b2 < ObjectList.length; b2++)
      ObjectList[b2][24] = 0; 
  }
  
  public void endStageStart() {
    clearKey();
    this.drawZankiFlag = this.drawRingFlag = true;
    countClear();
    if (this.stageNumber == 2 && this.zoneNumber == 4)
      AddObjectData(150, 11424, 1394, 0, 0); 
    for (byte b = 0; b < PlayerParam.length; b++)
      PlayerParam[b] = 0; 
    olddir = 0;
    plspeed[0] = 0;
    plspeed[1] = 0;
    int[][] arrayOfInt = { { 80, 944, 80, 252, 80, 944, 128, 168 }, { 96, 108, 80, 236, 80, 748, 2944, 0 }, { 48, 614, 48, 614, 48, 358, 128, 168 }, { 64, 716, 64, 332, 64, 332, 128, 168 }, { 48, 957, 48, 445, 48, 236, 128, 168 }, { 48, 1164, 48, 1868, 8512, 1452, 128, 168, 304, 168 }, { 1568, 363, 3808, 364, 128, 168, 128, 168 } };
    mapOxy[0] = 0;
    mapOxy[1] = 0;
    if (this.zoneNumber == 5 && this.stageNumber == 3) {
      PlayerParam[0] = arrayOfInt[this.zoneNumber][8] << 8;
      PlayerParam[1] = (arrayOfInt[this.zoneNumber][9] << 8) + 5120;
    } else {
      PlayerParam[0] = arrayOfInt[this.zoneNumber][this.stageNumber * 2 + 0] << 8;
      PlayerParam[1] = (arrayOfInt[this.zoneNumber][this.stageNumber * 2 + 1] << 8) + 5120;
    } 
    if (plsaveX != 0 || plsaveY != 0) {
      PlayerParam[0] = plsaveX << 8;
      PlayerParam[1] = plsaveY << 8;
      timecount = plsaveTime;
      timecount2 = plsaveTime2;
      this.water_flag = 0;
      if (this.zoneNumber == 1 && this.stageNumber == 2) {
        this.water_flag = 1;
        if (plsaveX > 4864)
          this.water_flag = 2; 
      } 
    } 
    limitchk(false);
    InitViewControl();
    waterMove();
    this.waterH3 = this.waterH;
    waterMove();
    PlayerParam[8] = -1;
    PlayerParam[9] = 1;
    mode = MODE_FIELD;
    addObjectChk();
    this.SetSoftFlag = true;
    this.SetSoftCount = 10;
  }
  
  public void initStage(int paramInt) {
    LoadImages(paramInt);
    try {
      this.water_flag = 0;
      this.water_flag2 = 0;
      this.water_flag3 = 0;
      this.water_flag4 = 0;
      bossModeOn = false;
      this.ChkVecR = true;
      this.ChkVecL = true;
      if (this.zoneNumber != 1) {
        this.waterH = this.waterH2 = this.waterH3 = 16777215;
      } else {
        int[] arrayOfInt = { 184, 808, 2304, 552 };
        this.waterH = this.waterH2 = this.waterH3 = arrayOfInt[this.stageNumber];
      } 
      mapOxy[0] = 0;
      oldMapOxy[0] = 0;
      mapOxy[1] = 0;
      oldMapOxy[1] = 0;
      indata = new DataInputStream(getClass().getResourceAsStream("/zone" + paramInt + ".blt"));
      indata.read(blockLinkTable);
      indata.close();
      indata = new DataInputStream(getClass().getResourceAsStream("/MapLzone" + paramInt + ".blt"));
      indata.read(mapFrontData);
      indata.close();
      indata = new DataInputStream(getClass().getResourceAsStream("/ZONE" + paramInt + "ACT.act"));
      zoneActTable[0] = new byte[indata.readShort()];
      zoneActTable[1] = new byte[indata.readShort()];
      zoneActTable[2] = new byte[indata.readShort()];
      zoneActTable[3] = new byte[indata.readShort()];
      indata.read(zoneActTable[0]);
      indata.read(zoneActTable[1]);
      indata.read(zoneActTable[2]);
      indata.read(zoneActTable[3]);
      indata.close();
      indata = new DataInputStream(getClass().getResourceAsStream("/scdtblwk.scd"));
      indata.read(scdtblwk);
      indata.close();
      indata = new DataInputStream(getClass().getResourceAsStream("/blkcol.bct"));
      indata.read(blockColTable);
      indata.close();
      this.m_imgObj[0] = createImage("/ring.png");
      this.m_imgObj[36] = createImage("/save.png");
      this.m_imgObj[42] = createImage("/item.png");
      this.m_imgObj[109] = createImage("/effect.png");
      this.m_imgObj[9] = createImage("/toge.png");
      if (this.zoneNumber == 0)
        this.m_imgObj[3] = createImage("/buranko.png"); 
      if (this.zoneNumber != 5)
        this.m_imgObj[55] = createImage("/masin.png"); 
      this.m_imgObj[SJUMP] = createImage("/sjump.png");
      this.m_imgObj[1] = createImage("/sjump2.png");
      if (this.zoneNumber == 4) {
        this.m_imgObj[16] = createImage("/shima5.png");
      } else if (this.zoneNumber == 0) {
        this.m_imgObj[16] = createImage("/shima.png");
      } 
      if (this.zoneNumber == 0) {
        this.m_imgObj[5] = createImage("/hashi.png");
        this.m_imgObj[58] = createImage("/jyama.png");
        this.m_imgObj[6] = createImage("/break.png");
        this.m_imgObj[37] = createImage("/kageb.png");
      } 
      if (this.zoneNumber == 1)
        if (this.stageNumber < 3) {
          this.m_imgObj[83] = createImage("/dai3.png");
          this.m_imgObj[107] = createImage("/dai2_0xE0.png");
          this.m_imgObj[108] = createImage("/dai2_0xF0.png");
          this.m_imgObj[82] = createImage("/kazari.png");
          this.m_imgObj[88] = createImage("/kassya.png");
          this.m_imgObj[84] = createImage("/mizu.png");
          this.m_imgObj[110] = createImage("/mizu_0x09.png");
          this.m_imgObj[80] = createImage("/yari.png");
          this.m_imgObj[85] = createImage("/awa.png");
          this.m_imgObj[104] = createImage("/objawa.png");
          this.m_imgObj[105] = createImage("/dai3_0x27.png");
          this.m_imgObj[106] = createImage("/dai3_0x13.png");
          this.m_imgObj[91] = createImage("/bou.png");
          this.m_imgObj[92] = createImage("/ben.png");
          this.m_imgObj[5] = createImage("/water.png");
          this.m_imgObj[111] = createImage("/water2.png");
        } else {
          this.m_imgObj[83] = createImage("/z_dai3.png");
          this.m_imgObj[105] = createImage("/z_dai3_0x27.png");
          this.m_imgObj[106] = createImage("/z_dai3_0x13.png");
          this.m_imgObj[107] = createImage("/z_dai2_0xE0.png");
          this.m_imgObj[108] = createImage("/z_dai2_0xF0.png");
          this.m_imgObj[82] = createImage("/z_kazari.png");
          this.m_imgObj[88] = createImage("/kassya.png");
          this.m_imgObj[84] = createImage("/z_mizu.png");
          this.m_imgObj[110] = createImage("/mizu_0x09.png");
          this.m_imgObj[80] = createImage("/yari.png");
          this.m_imgObj[85] = createImage("/awa.png");
          this.m_imgObj[104] = createImage("/objawa.png");
          this.m_imgObj[91] = createImage("/bou.png");
          this.m_imgObj[92] = createImage("/z_ben.png");
          this.m_imgObj[5] = createImage("/water.png");
          this.m_imgObj[111] = createImage("/water2.png");
        }  
      if (this.zoneNumber == 2) {
        this.m_imgObj[79] = createImage("/ochi.png");
        this.m_imgObj[54] = createImage("/dai.png");
        this.m_imgObj[8] = createImage("/turi.png");
        this.m_imgObj[94] = createImage("/turi2.png");
        this.m_imgObj[95] = createImage("/turi3.png");
        this.m_imgObj[13] = createImage("/yogan2.png");
        this.m_imgObj[99] = createImage("/yogan22.png");
        this.m_imgObj[11] = createImage("/fblock.png");
        this.m_imgObj[77] = createImage("/yoganc.png");
        this.m_imgObj[14] = createImage("/myogan.png");
        this.m_imgObj[98] = createImage("/myogan2.png");
        this.m_imgObj[7] = createImage("/yuka.png");
        this.m_imgObj[27] = createImage("/bryuka.png");
      } 
      this.m_imgObj[15] = createImage("/switch.png");
      if (this.zoneNumber == 3) {
        this.m_imgObj[22] = createImage("/fun.png");
        this.m_imgObj[BRKABE] = createImage("/brkabe.png");
        this.m_imgObj[PEDAL] = createImage("/pedal.png");
        this.m_imgObj[STEP] = createImage("/step.png");
        this.m_imgObj[73] = createImage("/elev.png");
        this.m_imgObj[23] = createImage("/sisoo.png");
      } 
      if (this.zoneNumber == 4) {
        this.m_imgObj[DAI2] = createImage("/dai2.png");
        this.m_imgObj[61] = createImage("/signal.png");
        this.m_imgObj[56] = createImage("/bobin.png");
      } 
      if (this.zoneNumber == 5 && this.stageNumber != 3) {
        this.m_imgObj[PATA] = createImage("/paka2.png");
        this.m_imgObj[33] = createImage("/ele.png");
        this.m_imgObj[28] = createImage("/mawaru.png");
        this.m_imgObj[29] = createImage("/yukai.png");
        this.m_imgObj[32] = createImage("/dai4.png");
        this.m_imgObj[12] = createImage("/dai_.png");
      } 
      this.m_imgObj[26] = createImage("/fire6.png");
      if (this.zoneNumber == 4)
        this.m_imgObj[83] = createImage("/dai4_.png"); 
      this.m_imgObj[96] = createImage("/tama.png");
      this.m_imgObj[97] = createImage("/bakuhatu.png");
      IkeshitaLoadStageImage(paramInt);
      AraiLoadStageImage(paramInt);
      DistantBg.setStage(this.zoneNumber, this.stageNumber);
    } catch (Throwable throwable) {}
  }
  
  public void LoadImages(int paramInt) {
    try {
      m_imgMimg = null;
      System.gc();
      Thread.sleep(200L);
      if (paramInt == 2 && this.stageNumber == 3) {
        m_imgMimg = createImage("/z_zone2.png");
      } else {
        m_imgMimg = createImage("/zone" + paramInt + ".png");
      } 
      if (this.m_imgCmd[SONIC_N] == null)
        this.m_imgCmd[SONIC_N] = createImage("/sonic.png"); 
      if (this.m_imgCmd[SONIC_S] == null)
        this.m_imgCmd[SONIC_S] = createImage("/sonic_s.png"); 
    } catch (Throwable throwable) {}
  }
  
  public void playerAction() {
    waterMove();
    if (debugFlag) {
      if (KeyPress[2])
        if (KeyPress[0]) {
          PlayerParam[1] = PlayerParam[1] - 128;
        } else {
          PlayerParam[1] = PlayerParam[1] - 2048;
        }  
      if (KeyPress[1]) {
        if (KeyPress[0]) {
          PlayerParam[1] = PlayerParam[1] + 128;
        } else {
          PlayerParam[1] = PlayerParam[1] + 2048;
        } 
      } else if (KeyPress[3]) {
        if (KeyPress[0]) {
          PlayerParam[0] = PlayerParam[0] - 128;
        } else {
          PlayerParam[0] = PlayerParam[0] - 2048;
        } 
      } else if (KeyPress[4]) {
        if (KeyPress[0]) {
          PlayerParam[0] = PlayerParam[0] + 128;
        } else {
          PlayerParam[0] = PlayerParam[0] + 2048;
        } 
      } 
    } else if (PlayerNoCol || PlayerNoCtrl) {
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
    } else if (PlayerDie) {
      PlayerParam[1] = PlayerParam[1] + PlayerParam[5];
      PlayerParam[5] = PlayerParam[5] + gravity;
    } else if (kyuryuchk()) {
      if (this.damageMoveTimer > 0) {
        this.damageMoveTimer--;
        PlayerParam[1] = PlayerParam[1] - PlayerParam[3] * 2;
        PlayerParam[0] = PlayerParam[0] + PlayerParam[5];
        PlayerParam[5] = PlayerParam[5] + gravity;
      } else {
        for (byte b = 0; b < 4; b++) {
          PlayerParam[3] = 512;
          if ((PlayerPosY() - 24) % 128 < 64) {
            if (blockColChk(PlayerPosX() + 24, PlayerPosY() - 24)) {
              PlayerParam[1] = PlayerParam[1] + 256;
              byte b1 = 0;
              while (blockColChk(PlayerPosX() + 24, PlayerPosY() - 24) && b1 <= 24) {
                b1++;
                PlayerParam[0] = PlayerParam[0] - 256;
              } 
            } else if (blockColChk(PlayerPosX() + 24, PlayerPosY())) {
              PlayerParam[1] = PlayerParam[1] - 256;
              byte b1 = 0;
              while (blockColChk(PlayerPosX() + 24, PlayerPosY()) && b1 <= 24) {
                b1++;
                PlayerParam[0] = PlayerParam[0] - 256;
              } 
            } else {
              PlayerParam[0] = PlayerParam[0] + PlayerParam[3];
            } 
          } else if (blockColChk(PlayerPosX() + 24, PlayerPosY())) {
            PlayerParam[1] = PlayerParam[1] - 256;
            byte b1 = 0;
            while (blockColChk(PlayerPosX() + 24, PlayerPosY()) && b1 <= 24) {
              b1++;
              PlayerParam[0] = PlayerParam[0] - 256;
            } 
          } else if (blockColChk(PlayerPosX() + 24, PlayerPosY() - 24)) {
            PlayerParam[1] = PlayerParam[1] + 256;
            byte b1 = 0;
            while (blockColChk(PlayerPosX() + 24, PlayerPosY() - 24) && b1 <= 24) {
              b1++;
              PlayerParam[0] = PlayerParam[0] - 256;
            } 
          } else {
            PlayerParam[0] = PlayerParam[0] + PlayerParam[3];
          } 
          if (KeyPress[2] && b == 0) {
            PlayerParam[1] = PlayerParam[1] - 256;
            if (blockColChk_Enemy(PlayerPosX() + 24, PlayerPosY() - 24))
              PlayerParam[1] = PlayerParam[1] + 256; 
          } 
          if (KeyPress[1] && b == 0)
            PlayerParam[1] = PlayerParam[1] + 256; 
        } 
        PlayerJump = true;
        PlayerDamage = false;
        PlayerSWater = true;
        PlayerParam[3] = 2048;
      } 
    } else if (ballchk()) {
      if (ball00walk())
        ball00jump(); 
    } else if (play00walk()) {
      play00jump();
    } 
  }
  
  public int rnd(int paramInt) {
    return Math.abs(this.rnd.nextInt()) % paramInt;
  }
  
  public boolean blockColChk(int paramInt1, int paramInt2) {
    try {
      if (paramInt1 < 0)
        paramInt1 = 0; 
      if (paramInt2 < 0)
        paramInt2 = 0; 
      this.blockColCount++;
      int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
      if (this.hitChk[i >> 1] == 1)
        return false; 
      int j = (blockLinkTable[(mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8)] & 0xFF) << 5;
      if (this.rot[i >> 1] == 1) {
        if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (this.rot[i >> 1] == 2) {
        if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (this.rot[i >> 1] == 3) {
        if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1)) {
        return false;
      } 
      j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
      PlayerParam[8] = j;
      if (this.zoneNumber == 1)
        if (j > 70 && j < 84) {
          PlayerWater = true;
          PlayerParam[12] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
          PlayerParam[10] = 4096;
          if (this.rot[i >> 1] == 0) {
            PlayerParam[12] = 1;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[10] = -4096;
          } 
        } else {
          paramInt2 -= 16;
          if (paramInt1 < 0)
            paramInt1 = 0; 
          if (paramInt2 < 0)
            paramInt2 = 0; 
          i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
          j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
          if (j == 83 || j == 186) {
            PlayerWater = true;
            PlayerParam[12] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[10] = 4096;
            if (this.rot[i >> 1] == 0) {
              PlayerParam[12] = 1;
              PlayerParam[13] = 0;
              PlayerParam[14] = 0;
              PlayerParam[10] = -4096;
            } 
          } else {
            paramInt1 -= 16;
            paramInt2 += 16;
            if (paramInt1 < 0)
              paramInt1 = 0; 
            if (paramInt2 < 0)
              paramInt2 = 0; 
            i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
            j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
            if (j == 71 || j == 72) {
              PlayerWater = true;
            } else {
              PlayerWater = false;
            } 
          } 
        }  
    } catch (Throwable throwable) {}
    return true;
  }
  
  public boolean blockColChk2(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      paramInt1 = 0; 
    if (paramInt2 < 0)
      paramInt2 = 0; 
    int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
    if (this.hitChk2[i >> 1] == 1)
      return false; 
    int j = (blockLinkTable[(mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8)] & 0xFF) << 5;
    if (this.rot[i >> 1] == 1) {
      if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1))
        return false; 
    } else if (this.rot[i >> 1] == 2) {
      if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
        return false; 
    } else if (this.rot[i >> 1] == 3) {
      if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
        return false; 
    } else if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1)) {
      return false;
    } 
    j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
    PlayerParam[8] = j;
    return true;
  }
  
  public boolean blockColChk_easy(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      paramInt1 = 0; 
    if (paramInt2 < 0)
      paramInt2 = 0; 
    int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
    return !(this.hitChk[i >> 1] == 1);
  }
  
  public boolean blockColChk_Enemy(int paramInt1, int paramInt2) {
    try {
      if (paramInt1 < 0)
        paramInt1 = 0; 
      if (paramInt2 < 0)
        paramInt2 = 0; 
      int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
      if (this.hitChk[i >> 1] == 1 && this.hitChk2[i >> 1] == 1)
        return false; 
      int j = (blockLinkTable[(mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8)] & 0xFF) << 5;
      if (this.rot[i >> 1] == 1) {
        if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (this.rot[i >> 1] == 2) {
        if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (this.rot[i >> 1] == 3) {
        if (0 == (blockColTable[j + (15 - (paramInt1 & 0xF) << 1) + (15 - (paramInt2 & 0xF) >> 3)] >> (paramInt2 & 0x7) & 0x1))
          return false; 
      } else if (0 == (blockColTable[j + ((paramInt1 & 0xF) << 1) + ((paramInt2 & 0xF) >> 3)] >> 7 - (paramInt2 & 0x7) & 0x1)) {
        return false;
      } 
      j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
      this.enemyBlock = j;
    } catch (Throwable throwable) {
      return true;
    } 
    return true;
  }
  
  public int blockdirChk(int paramInt) {
    return scddirtbl[blockLinkTable[paramInt] & 0xFF] & 0xFF;
  }
  
  public boolean rcol2() {
    if (blockColChk2(PlayerPosX() + 12, PlayerPosY() - 12)) {
      byte b2 = 14;
      for (byte b1 = 0; b1 < b2; b1++) {
        PlayerParam[0] = PlayerParam[0] - 256;
        if (!blockColChk2(PlayerPosX() + 12, PlayerPosY() - 12))
          break; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean lcol2() {
    if (blockColChk2(PlayerPosX() - 12, PlayerPosY() - 12)) {
      byte b2 = 14;
      for (byte b1 = 0; b1 < b2; b1++) {
        PlayerParam[0] = PlayerParam[0] + 256;
        if (!blockColChk2(PlayerPosX() - 12, PlayerPosY() - 12))
          break; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean rcol3() {
    if (blockColChk2(PlayerPosX() + 12, PlayerPosY() - 24)) {
      byte b2 = 14;
      for (byte b1 = 0; b1 < b2; b1++) {
        PlayerParam[0] = PlayerParam[0] - 256;
        if (!blockColChk2(PlayerPosX() + 12, PlayerPosY() - 24))
          break; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean lcol3() {
    if (blockColChk2(PlayerPosX() - 12, PlayerPosY() - 24)) {
      byte b2 = 14;
      for (byte b1 = 0; b1 < b2; b1++) {
        PlayerParam[0] = PlayerParam[0] + 256;
        if (!blockColChk2(PlayerPosX() - 12, PlayerPosY() - 24))
          break; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean rcol() {
    int i;
    int j;
    if (olddir <= 22 || olddir >= 338) {
      i = PlayerPosX() + 12;
      j = PlayerPosY() - 12;
    } else if (this.zoneNumber == 1 && olddir == 316) {
      i = PlayerPosX() + 12;
      j = PlayerPosY() - 36;
    } else {
      return false;
    } 
    if ((this.zoneNumber == 4 || this.zoneNumber == 3) && olddir != 0)
      return false; 
    if (blockColChk2(i, j)) {
      for (byte b = 0; b < 14; b++) {
        PlayerParam[0] = PlayerParam[0] - 256;
        if (!blockColChk(--i, j))
          break; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean lcol() {
    if (olddir <= 22 || olddir >= 338) {
      if ((this.zoneNumber == 4 || this.zoneNumber == 3) && olddir != 0)
        return false; 
      if (this.zoneNumber == 2 && olddir == 22)
        return false; 
      int i = PlayerPosX() - 12;
      int j = PlayerPosY() - 12;
      if (blockColChk2(i, j)) {
        for (byte b = 0; b < 14; b++) {
          PlayerParam[0] = PlayerParam[0] + 256;
          if (!blockColChk(++i, j))
            break; 
        } 
        return true;
      } 
      return false;
    } 
    return false;
  }
  
  public boolean hcol() {
    try {
      byte b = 32;
      if (PlayerSJump)
        b = 32; 
      if (blockColChk2(PlayerPosX(), PlayerPosY() - b)) {
        byte b2 = 14;
        for (byte b1 = 0; b1 < b2; b1++) {
          PlayerParam[1] = PlayerParam[1] + 256;
          if (!blockColChk2(PlayerPosX(), PlayerPosY() - b)) {
            PlayerParam[1] = PlayerParam[1] - 256;
            break;
          } 
        } 
        return true;
      } 
      b = 16;
      if (blockColChk2(PlayerPosX(), PlayerPosY() - b)) {
        byte b2 = 14;
        for (byte b1 = 0; b1 < b2; b1++) {
          PlayerParam[1] = PlayerParam[1] + 256;
          if (!blockColChk2(PlayerPosX(), PlayerPosY() - b)) {
            PlayerParam[1] = PlayerParam[1] - 256;
            break;
          } 
        } 
        return true;
      } 
    } catch (Throwable throwable) {}
    return false;
  }
  
  public boolean fcol(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual PlayerPosX : ()I
    //   5: aload_0
    //   6: invokevirtual PlayerPosY : ()I
    //   9: invokevirtual blockColChk : (II)Z
    //   12: ifeq -> 235
    //   15: bipush #16
    //   17: istore_3
    //   18: getstatic MainCanvas.olddir : I
    //   21: istore #4
    //   23: iload_1
    //   24: iconst_m1
    //   25: if_icmpne -> 28
    //   28: aload_0
    //   29: getfield zoneNumber : I
    //   32: iconst_3
    //   33: if_icmpne -> 70
    //   36: getstatic MainCanvas.olddir : I
    //   39: bipush #90
    //   41: if_icmpne -> 70
    //   44: getstatic MainCanvas.PlayerParam : [I
    //   47: bipush #8
    //   49: iaload
    //   50: sipush #370
    //   53: if_icmpeq -> 68
    //   56: getstatic MainCanvas.PlayerParam : [I
    //   59: bipush #8
    //   61: iaload
    //   62: sipush #371
    //   65: if_icmpne -> 70
    //   68: iconst_1
    //   69: ireturn
    //   70: iload #4
    //   72: ifge -> 78
    //   75: iconst_0
    //   76: istore #4
    //   78: iconst_0
    //   79: istore_2
    //   80: iload_2
    //   81: iload_3
    //   82: if_icmpge -> 233
    //   85: getstatic MainCanvas.PlayerJump : Z
    //   88: ifne -> 115
    //   91: getstatic MainCanvas.PlayerParam : [I
    //   94: iconst_0
    //   95: dup2
    //   96: iaload
    //   97: aload_0
    //   98: iload #4
    //   100: sipush #180
    //   103: iadd
    //   104: invokevirtual dSin : (I)I
    //   107: bipush #8
    //   109: ishl
    //   110: bipush #100
    //   112: idiv
    //   113: isub
    //   114: iastore
    //   115: getstatic MainCanvas.PlayerParam : [I
    //   118: iconst_1
    //   119: dup2
    //   120: iaload
    //   121: aload_0
    //   122: iload #4
    //   124: sipush #180
    //   127: iadd
    //   128: invokevirtual dCos : (I)I
    //   131: sipush #256
    //   134: imul
    //   135: bipush #100
    //   137: idiv
    //   138: isub
    //   139: iastore
    //   140: aload_0
    //   141: aload_0
    //   142: invokevirtual PlayerPosX : ()I
    //   145: aload_0
    //   146: invokevirtual PlayerPosY : ()I
    //   149: invokevirtual blockColChk : (II)Z
    //   152: ifne -> 227
    //   155: getstatic MainCanvas.PlayerJump : Z
    //   158: ifne -> 185
    //   161: getstatic MainCanvas.PlayerParam : [I
    //   164: iconst_0
    //   165: dup2
    //   166: iaload
    //   167: aload_0
    //   168: iload #4
    //   170: sipush #180
    //   173: iadd
    //   174: invokevirtual dSin : (I)I
    //   177: bipush #8
    //   179: ishl
    //   180: bipush #100
    //   182: idiv
    //   183: iadd
    //   184: iastore
    //   185: getstatic MainCanvas.PlayerParam : [I
    //   188: iconst_1
    //   189: dup2
    //   190: iaload
    //   191: aload_0
    //   192: iload #4
    //   194: sipush #180
    //   197: iadd
    //   198: invokevirtual dCos : (I)I
    //   201: bipush #8
    //   203: ishl
    //   204: bipush #100
    //   206: idiv
    //   207: iadd
    //   208: iastore
    //   209: aload_0
    //   210: aload_0
    //   211: invokevirtual PlayerPosX : ()I
    //   214: aload_0
    //   215: invokevirtual PlayerPosY : ()I
    //   218: invokevirtual getPlayerArg : (II)I
    //   221: putstatic MainCanvas.olddir : I
    //   224: goto -> 233
    //   227: iinc #2, 1
    //   230: goto -> 80
    //   233: iconst_1
    //   234: ireturn
    //   235: goto -> 239
    //   238: astore_2
    //   239: iconst_0
    //   240: ireturn
    // Exception table:
    //   from	to	target	type
    //   0	69	238	java/lang/Throwable
    //   70	234	238	java/lang/Throwable
  }
  
  public void playerPushSet() {
    if (!PlayerJump && PlayerBall)
      PlayerBall = false; 
    this.pushCount = 2;
  }
  
  public void playerBressChk() {
    if (this.zoneNumber == 1 && this.waterH2 < PlayerPosY() - 12) {
      this.bressCount--;
      if (this.bressCount < 0) {
        this.bressDie = true;
        playerDie();
        PlayMusic(29);
        PlayerParam[5] = 256;
      } 
    } else {
      this.bressCount = 2100;
    } 
  }
  
  public boolean jumpchk(int paramInt) {
    if (0 == paramInt) {
      if (PlayerJump)
        return true; 
      if (this.limitBreak)
        return false; 
      if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 31 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 32)) {
        KeyPress[2] = false;
        return false;
      } 
      if (KeyPress[2]) {
        if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
          PlayerJump = true;
          PlayerDamage = false;
          PlayerAir = false;
          raidOn = false;
          PlayerBall = true;
          int i3 = getPlayerArg(PlayerPosX(), PlayerPosY());
          if (i3 < 0)
            i3 = 0; 
          PlayerParam[3] = dSin(i3) * (pljump + PlayerParam[10]) / 100 + PlayerParam[10];
          PlayerParam[5] = dCos(i3) * (pljump + PlayerParam[10]) / 100;
          return true;
        } 
        if (raidOn) {
          PlayerJump = true;
          PlayerDamage = false;
          PlayerAir = false;
          raidOn = false;
          PlayerBall = true;
          PlayerParam[3] = dSin(0) * pljump / 100 + PlayerParam[10];
          PlayerParam[5] = dCos(0) * pljump / 100;
          return true;
        } 
        PlayerJump = true;
        PlayerDamage = false;
        PlayerAir = false;
        raidOn = false;
        PlayerBall = true;
        int i = olddir;
        int j = PlayerPosX() / 16 / 16;
        int k = PlayerPosY() / 16 / 16;
        k %= MapH;
        int m = tempWorldMapData[k][j] * 512 + (PlayerPosX() / 16 % 16 + PlayerPosY() / 16 % 16 * 16) * 2 + 1;
        int n = 0;
        int i1 = mapData[m - 1] & 0xFF;
        byte b1 = (byte)(i1 << 6);
        b1 = (byte)Math.abs(b1 >> 6);
        if (b1 % 4 != 0)
          n = 256 * b1 % 4; 
        int i2 = (mapData[m] & 0xFF) + n;
        byte b2 = (byte)blockdirChk(i2);
        if (olddir == 270) {
          nocoltimer = 5;
          PlayerParam[3] = -pljump;
          PlayerParam[5] = -Math.abs(pljump);
        } else if (olddir == 90) {
          nocoltimer = 5;
          PlayerParam[3] = pljump;
          PlayerParam[5] = -Math.abs(pljump);
        } else if (b2 == 0 || olddir > 290 || olddir < 70) {
          PlayerParam[3] = dSin(i) * pljump / 100 + PlayerParam[10];
          PlayerParam[5] = dCos(i) * pljump / 100;
          if (PlayerParam[5] > 0)
            this.nofcolTimer = 1; 
          if (Math.abs(PlayerParam[10]) > 2560 && this.zoneNumber == 4 && this.stageNumber == 1 && PlayerParam[0] > 9216) {
            PlayerParam[5] = PlayerParam[5] - 768;
            PlayerParam[3] = dSin(i) * pljump / 100 + 2560;
          } 
        } else {
          PlayerParam[3] = dSin(i) * pljump / 100;
          PlayerParam[5] = dCos(i) * pljump / 100;
        } 
        return true;
      } 
      return false;
    } 
    return false;
  }
  
  public boolean ballchk() {
    if (this.CrouchCount > -1)
      this.CrouchCount--; 
    if (this.LookUpCount > -1)
      this.LookUpCount--; 
    if (PlayerBall)
      return true; 
    if (!PlayerJump && !this.limitBreak)
      if (KeyPress[1]) {
        if (Math.abs(PlayerParam[10]) > plretspd) {
          PlayerBall = true;
          return true;
        } 
        if (!KeyPress[3] && !KeyPress[4]) {
          PlayerCrouch = true;
          this.CrouchCount += 2;
          if (this.CrouchCount > 32)
            this.CrouchCount = 32; 
          return false;
        } 
      } else if (KeyPress[0] && Math.abs(PlayerParam[10]) <= plretspd && !KeyPress[3] && !KeyPress[4]) {
        PlayerLookUp = true;
        this.LookUpCount += 2;
        if (this.LookUpCount > 24)
          this.LookUpCount = 24; 
        return false;
      }  
    return false;
  }
  
  public boolean setPlayerPos() {
    this.rhit = false;
    this.lhit = false;
    int i = olddir;
    char c1 = ' ';
    char c2 = 'Ā';
    if (olddir != 22 && olddir != 338)
      c1 = 'Ā'; 
    if (olddir >= 270 && olddir < 300)
      c2 = ' '; 
    if (olddir >= 60 && olddir < 90)
      c2 = ' '; 
    if (this.zoneNumber == 0 && (tempWorldMapData[PlayerPosY() >> 8][PlayerPosX() >> 8] == 31 || tempWorldMapData[PlayerPosY() >> 8][PlayerPosX() >> 8] == 32))
      c2 = 'Ā'; 
    int j = olddir;
    if (fcol()) {
      raidOn = false;
      if (this.zoneNumber == 4 && ((j == 79 && olddir == 90) || (j == 281 && olddir == 270))) {
        if (olddir == 90) {
          PlayerParam[10] = 640;
        } else {
          PlayerParam[10] = -640;
        } 
        olddir = j;
        this.noLeverTimer = 0;
        this.nofcolTimer = 3;
        return false;
      } 
      return true;
    } 
    for (byte b = 0; b < 14; b++) {
      PlayerParam[0] = PlayerParam[0] + dSin(i + 180) * c1 / 100;
      PlayerParam[1] = PlayerParam[1] + dCos(i + 180) * c2 / 100;
      if (fcol()) {
        raidOn = false;
        if (this.zoneNumber == 4 && ((j == 79 && olddir == 90) || (j == 281 && olddir == 270))) {
          if (olddir == 90) {
            PlayerParam[10] = 640;
          } else {
            PlayerParam[10] = -640;
          } 
          olddir = j;
          this.noLeverTimer = 0;
          this.nofcolTimer = 3;
          return false;
        } 
        return true;
      } 
    } 
    return false;
  }
  
  private boolean fcol() {
    if (this.zoneNumber == 5 && this.stageNumber != 2) {
      if (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])
        return fcol(0); 
      if (plspeed[0] >= 0) {
        this.rhit = fcol_r();
        if (!this.rhit)
          this.lhit = fcol_l(); 
      } 
      if (plspeed[0] < 0) {
        this.lhit = fcol_l();
        if (!this.lhit)
          this.rhit = fcol_r(); 
      } 
      return (this.rhit || this.lhit);
    } 
    if (this.zoneNumber == 0) {
      if (tempWorldMapData[PlayerPosY() >> 8][PlayerPosX() >> 8] == 31 || tempWorldMapData[PlayerPosY() >> 8][PlayerPosX() >> 8] == 32)
        return fcol(0); 
      if (plspeed[0] >= 0) {
        this.rhit = fcol_r();
        if (!this.rhit)
          this.lhit = fcol_l(); 
      } 
      if (plspeed[0] < 0) {
        this.lhit = fcol_l();
        if (!this.lhit)
          this.rhit = fcol_r(); 
      } 
      return (this.rhit || this.lhit);
    } 
    if (this.zoneNumber == 3) {
      if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 42 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 43 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53)
        return fcol(0); 
      if (plspeed[0] >= 0) {
        this.rhit = fcol_r();
        if (!this.rhit)
          this.lhit = fcol_l(); 
      } 
      if (plspeed[0] < 0) {
        this.lhit = fcol_l();
        if (!this.lhit)
          this.rhit = fcol_r(); 
      } 
      return (this.rhit || this.lhit);
    } 
    if (plspeed[0] >= 0) {
      this.rhit = fcol_r();
      if (!this.rhit)
        this.lhit = fcol_l(); 
    } 
    if (plspeed[0] < 0) {
      this.lhit = fcol_l();
      if (!this.lhit)
        this.rhit = fcol_r(); 
    } 
    return (this.rhit || this.lhit);
  }
  
  private boolean fcol_r() {
    try {
      int i = olddir;
      if (i < 0)
        i = 0; 
      int j = this.PlayerW;
      int k = PlayerPosX() + dSin(i + 90) * j / 100;
      int m = PlayerPosY() + dCos(i + 90) * j / 100;
      if (blockColChk(k, m)) {
        int n = getPlayerArg(k, m);
        if (this.zoneNumber == 3 && (PlayerParam[8] == 1 || PlayerParam[8] == 17 || PlayerParam[8] == 319) && ((olddir >= 270 && olddir < 300) || (olddir > 60 && olddir <= 90))) {
          if (olddir >= 270 && olddir < 300) {
            olddir = 270;
          } else {
            olddir = 90;
          } 
          return true;
        } 
        byte b2 = 16;
        for (byte b1 = 0; b1 < b2; b1++) {
          n = getPlayerArg(k, m);
          if (!PlayerJump)
            PlayerParam[0] = PlayerParam[0] - (dSin(i + 180) << 8) / 100; 
          PlayerParam[1] = PlayerParam[1] - (dCos(i + 180) << 8) / 100;
          k = PlayerPosX() + dSin(i + 90) * j / 100;
          m = PlayerPosY() + dCos(i + 90) * j / 100;
          if (!blockColChk(k, m)) {
            if (!PlayerJump)
              PlayerParam[0] = PlayerParam[0] + (dSin(i + 180) << 8) / 100; 
            PlayerParam[1] = PlayerParam[1] + (dCos(i + 180) << 8) / 100;
            if (this.zoneNumber == 3 && n == 350)
              n = 0; 
            olddir = n;
            break;
          } 
        } 
        return true;
      } 
    } catch (Throwable throwable) {}
    return false;
  }
  
  private boolean fcol_l() {
    try {
      int i = olddir;
      if (i < 0)
        i = 0; 
      int j = this.PlayerW;
      int k = PlayerPosX() + dSin(i + 270) * j / 100;
      int m = PlayerPosY() + dCos(i + 270) * j / 100;
      if (blockColChk(k, m)) {
        int n = getPlayerArg(k, m);
        if (this.zoneNumber == 3 && (PlayerParam[8] == 1 || PlayerParam[8] == 17 || PlayerParam[8] == 319) && ((olddir >= 270 && olddir < 300) || (olddir > 60 && olddir <= 90))) {
          if (olddir >= 270 && olddir < 300) {
            olddir = 270;
          } else {
            olddir = 90;
          } 
          return true;
        } 
        byte b2 = 16;
        for (byte b1 = 0; b1 < b2; b1++) {
          n = getPlayerArg(k, m);
          if (!PlayerJump)
            PlayerParam[0] = PlayerParam[0] - (dSin(i + 180) << 8) / 100; 
          PlayerParam[1] = PlayerParam[1] - (dCos(i + 180) << 8) / 100;
          k = PlayerPosX() + dSin(i + 270) * j / 100;
          m = PlayerPosY() + dCos(i + 270) * j / 100;
          if (!blockColChk(k, m)) {
            if (!PlayerJump)
              PlayerParam[0] = PlayerParam[0] + (dSin(i + 180) << 8) / 100; 
            PlayerParam[1] = PlayerParam[1] + (dCos(i + 180) << 8) / 100;
            if (this.zoneNumber == 3 && n == 350)
              n = 0; 
            olddir = n;
            break;
          } 
        } 
        return true;
      } 
    } catch (Throwable throwable) {}
    return false;
  }
  
  public boolean play00walk() {
    plspeed[0] = 0;
    plspeed[1] = 0;
    if (jumpchk(0))
      return true; 
    int i = Math.abs(PlayerParam[10]);
    keispd(0);
    levermove();
    if (i <= plmaxspd && Math.abs(PlayerParam[10]) > plmaxspd && i <= plmaxspd)
      if (PlayerParam[10] < 0) {
        PlayerParam[10] = -plmaxspd;
      } else {
        PlayerParam[10] = plmaxspd;
      }  
    if ((olddir <= 22 || olddir >= 338) && (i > plmaxspd || (!KeyPress[3] && !KeyPress[4] && PlayerParam[10] != 0)) && PlayerParam[10] != 0)
      if (PlayerParam[10] < 0) {
        PlayerParam[10] = PlayerParam[10] + pladdspd;
        if (PlayerParam[10] > 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } 
      } else {
        PlayerParam[10] = PlayerParam[10] - pladdspd;
        if (PlayerParam[10] < 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } 
      }  
    if (Math.abs(PlayerParam[10]) > 4096)
      if (PlayerParam[10] < 0) {
        PlayerParam[10] = -4096;
      } else {
        PlayerParam[10] = 4096;
      }  
    if (ballchk())
      return false; 
    speedset(0);
    if (this.zoneNumber == 0 || this.zoneNumber == 3)
      loopchange(); 
    if (!PlayerNoCol) {
      boolean bool1 = false;
      if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 31 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 32)) {
        bool1 = true;
        PlayerBall = true;
        PlayerParam[10] = PlayerParam[10] + 256;
        if (PlayerParam[10] > 4096)
          PlayerParam[10] = 4096; 
      } 
      if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 54))
        bool1 = true; 
      if (this.zoneNumber == 3 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 42 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 43 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53))
        bool1 = true; 
      if (!bool1)
        if (raidOn) {
          if (blockColChk2(PlayerPosX() - 12, PlayerPosY() - 24) && blockColChk2(PlayerPosX() + 12, PlayerPosY() - 24)) {
            if (rcol2()) {
              PlayerParam[10] = 0;
              PlayerParam[13] = 0;
              PlayerParam[14] = 0;
              if (KeyPress[4])
                playerPushSet(); 
            } 
            if (lcol2()) {
              PlayerParam[10] = 0;
              PlayerParam[13] = 0;
              PlayerParam[14] = 0;
              if (KeyPress[3])
                playerPushSet(); 
            } 
          } else {
            if (rcol3() || rcol2()) {
              PlayerParam[10] = 0;
              PlayerParam[13] = 0;
              PlayerParam[14] = 0;
              if (KeyPress[4])
                playerPushSet(); 
            } 
            if (lcol3() || lcol2()) {
              PlayerParam[10] = 0;
              PlayerParam[13] = 0;
              PlayerParam[14] = 0;
              if (KeyPress[3])
                playerPushSet(); 
            } 
          } 
        } else {
          if (rcol()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            if (KeyPress[4])
              playerPushSet(); 
          } 
          if (lcol()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            if (KeyPress[3])
              playerPushSet(); 
          } 
        }  
      int j = PlayerParam[0];
      int k = PlayerParam[1];
      boolean bool2 = true;
      if (setPlayerPos()) {
        this.crushing[0] = true;
        bool2 = false;
      } 
      if (this.noLeverTimer > 0) {
        if (bool2) {
          PlayerParam[0] = j;
          PlayerParam[1] = k;
        } 
      } else if (!raidOn && bool2) {
        PlayerParam[0] = j;
        PlayerParam[1] = k;
        if (olddir == 22 && this.zoneNumber == 1 && this.stageNumber == 1) {
          PlayerParam[3] = dSin(90) * PlayerParam[10] / 100;
          PlayerParam[5] = dCos(90) * PlayerParam[10] / 100;
        } else {
          PlayerParam[3] = dSin(olddir + 90) * PlayerParam[10] / 100;
          PlayerParam[5] = dCos(olddir + 90) * PlayerParam[10] / 100;
        } 
        if ((olddir - 90) % 180 == 0)
          if (olddir == 90) {
            PlayerParam[0] = PlayerParam[0] + 256;
            if (this.zoneNumber == 4 && this.stageNumber != 0)
              PlayerParam[5] = 0; 
          } else {
            PlayerParam[0] = PlayerParam[0] - 256;
            if (PlayerParam[10] > 2816)
              this.noLeverTimer = 15; 
          }  
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerAir = true;
        PlayerJump = true;
        PlayerDamage = false;
        raidOn = false;
        if (hcol())
          PlayerParam[5] = 0; 
      } else {
        bool1 = false;
        if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 31 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 32))
          bool1 = true; 
        if (this.zoneNumber == 2 && (PlayerParam[8] == 365 || PlayerParam[8] == 364 || PlayerParam[8] == 363 || PlayerParam[8] == 362))
          playdamageset(); 
        if (!bool1)
          fallchk(); 
      } 
    } 
    return false;
  }
  
  public void play00jump() {
    if (this.noLeverTimer > 0) {
      this.noLeverTimer--;
    } else if (KeyPress[3]) {
      PlayerParam[12] = 1;
      if (PlayerParam[3] == 0) {
        PlayerParam[3] = PlayerParam[3] - (plretspd << 1);
      } else {
        PlayerParam[3] = PlayerParam[3] - pladdspd;
        if (PlayerParam[3] > 0)
          PlayerParam[3] = PlayerParam[3] - pladdspd; 
      } 
    } else if (KeyPress[4]) {
      PlayerParam[12] = 0;
      if (PlayerParam[3] == 0) {
        PlayerParam[3] = PlayerParam[3] + (plretspd << 1);
      } else {
        PlayerParam[3] = PlayerParam[3] + pladdspd;
        if (PlayerParam[3] < 0)
          PlayerParam[3] = PlayerParam[3] + pladdspd; 
      } 
    } 
    if (Math.abs(PlayerParam[3]) > plmaxspd)
      if (PlayerParam[3] < 0) {
        PlayerParam[3] = -plmaxspd;
      } else {
        PlayerParam[3] = plmaxspd;
      }  
    plspeed[0] = plspeed[0] + PlayerParam[3];
    plspeed[1] = plspeed[1] + PlayerParam[5];
    if (PlayerParam[5] > 0 && this.nofcolTimer <= 0) {
      if (olddir == 270) {
        PlayerParam[0] = PlayerParam[0] - 3072;
      } else if (olddir == 90) {
        PlayerParam[0] = PlayerParam[0] + 3072;
      } 
      olddir = 0;
    } 
    jumpmove();
    speedset(1);
    if (this.zoneNumber == 0 || this.zoneNumber == 3)
      loopchange(); 
    if (PlayerJump && nocoltimer <= 0) {
      if (rcol2()) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerParam[3] = 0;
      } 
      if (lcol2()) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerParam[3] = 0;
      } 
    } 
    jumpcolchk();
  }
  
  public boolean ball00walk() {
    // Byte code:
    //   0: getstatic MainCanvas.plspeed : [I
    //   3: iconst_0
    //   4: iconst_0
    //   5: iastore
    //   6: getstatic MainCanvas.plspeed : [I
    //   9: iconst_1
    //   10: iconst_0
    //   11: iastore
    //   12: aload_0
    //   13: iconst_0
    //   14: invokevirtual jumpchk : (I)Z
    //   17: ifeq -> 22
    //   20: iconst_1
    //   21: ireturn
    //   22: getstatic MainCanvas.PlayerParam : [I
    //   25: bipush #10
    //   27: iaload
    //   28: invokestatic abs : (I)I
    //   31: istore_1
    //   32: aload_0
    //   33: iconst_1
    //   34: invokevirtual keispd : (I)V
    //   37: aload_0
    //   38: invokevirtual blevermove : ()V
    //   41: aload_0
    //   42: iconst_0
    //   43: invokevirtual speedset : (I)V
    //   46: getstatic MainCanvas.PlayerParam : [I
    //   49: bipush #10
    //   51: iaload
    //   52: invokestatic abs : (I)I
    //   55: sipush #4096
    //   58: if_icmple -> 91
    //   61: getstatic MainCanvas.PlayerParam : [I
    //   64: bipush #10
    //   66: iaload
    //   67: ifge -> 82
    //   70: getstatic MainCanvas.PlayerParam : [I
    //   73: bipush #10
    //   75: sipush #-4096
    //   78: iastore
    //   79: goto -> 91
    //   82: getstatic MainCanvas.PlayerParam : [I
    //   85: bipush #10
    //   87: sipush #4096
    //   90: iastore
    //   91: getstatic MainCanvas.PlayerParam : [I
    //   94: bipush #10
    //   96: iaload
    //   97: invokestatic abs : (I)I
    //   100: sipush #3072
    //   103: if_icmple -> 106
    //   106: aload_0
    //   107: getfield zoneNumber : I
    //   110: ifeq -> 121
    //   113: aload_0
    //   114: getfield zoneNumber : I
    //   117: iconst_3
    //   118: if_icmpne -> 125
    //   121: aload_0
    //   122: invokevirtual loopchange : ()V
    //   125: getstatic MainCanvas.PlayerNoCol : Z
    //   128: ifne -> 1205
    //   131: iconst_0
    //   132: istore_3
    //   133: aload_0
    //   134: getfield zoneNumber : I
    //   137: ifne -> 230
    //   140: getstatic MainCanvas.tempWorldMapData : [[B
    //   143: aload_0
    //   144: invokevirtual PlayerPosY : ()I
    //   147: bipush #8
    //   149: ishr
    //   150: getstatic MainCanvas.MapH : I
    //   153: irem
    //   154: aaload
    //   155: aload_0
    //   156: invokevirtual PlayerPosX : ()I
    //   159: bipush #8
    //   161: ishr
    //   162: baload
    //   163: bipush #31
    //   165: if_icmpeq -> 196
    //   168: getstatic MainCanvas.tempWorldMapData : [[B
    //   171: aload_0
    //   172: invokevirtual PlayerPosY : ()I
    //   175: bipush #8
    //   177: ishr
    //   178: getstatic MainCanvas.MapH : I
    //   181: irem
    //   182: aaload
    //   183: aload_0
    //   184: invokevirtual PlayerPosX : ()I
    //   187: bipush #8
    //   189: ishr
    //   190: baload
    //   191: bipush #32
    //   193: if_icmpne -> 230
    //   196: iconst_1
    //   197: istore_3
    //   198: getstatic MainCanvas.PlayerParam : [I
    //   201: bipush #10
    //   203: dup2
    //   204: iaload
    //   205: bipush #12
    //   207: iadd
    //   208: iastore
    //   209: getstatic MainCanvas.PlayerParam : [I
    //   212: bipush #10
    //   214: iaload
    //   215: sipush #4096
    //   218: if_icmple -> 230
    //   221: getstatic MainCanvas.PlayerParam : [I
    //   224: bipush #10
    //   226: sipush #4096
    //   229: iastore
    //   230: aload_0
    //   231: getfield zoneNumber : I
    //   234: ifne -> 295
    //   237: getstatic MainCanvas.tempWorldMapData : [[B
    //   240: aload_0
    //   241: invokevirtual PlayerPosY : ()I
    //   244: bipush #8
    //   246: ishr
    //   247: getstatic MainCanvas.MapH : I
    //   250: irem
    //   251: aaload
    //   252: aload_0
    //   253: invokevirtual PlayerPosX : ()I
    //   256: bipush #8
    //   258: ishr
    //   259: baload
    //   260: bipush #53
    //   262: if_icmpeq -> 293
    //   265: getstatic MainCanvas.tempWorldMapData : [[B
    //   268: aload_0
    //   269: invokevirtual PlayerPosY : ()I
    //   272: bipush #8
    //   274: ishr
    //   275: getstatic MainCanvas.MapH : I
    //   278: irem
    //   279: aaload
    //   280: aload_0
    //   281: invokevirtual PlayerPosX : ()I
    //   284: bipush #8
    //   286: ishr
    //   287: baload
    //   288: bipush #54
    //   290: if_icmpne -> 295
    //   293: iconst_1
    //   294: istore_3
    //   295: aload_0
    //   296: getfield zoneNumber : I
    //   299: iconst_3
    //   300: if_icmpne -> 417
    //   303: getstatic MainCanvas.tempWorldMapData : [[B
    //   306: aload_0
    //   307: invokevirtual PlayerPosY : ()I
    //   310: bipush #8
    //   312: ishr
    //   313: getstatic MainCanvas.MapH : I
    //   316: irem
    //   317: aaload
    //   318: aload_0
    //   319: invokevirtual PlayerPosX : ()I
    //   322: bipush #8
    //   324: ishr
    //   325: baload
    //   326: bipush #42
    //   328: if_icmpeq -> 415
    //   331: getstatic MainCanvas.tempWorldMapData : [[B
    //   334: aload_0
    //   335: invokevirtual PlayerPosY : ()I
    //   338: bipush #8
    //   340: ishr
    //   341: getstatic MainCanvas.MapH : I
    //   344: irem
    //   345: aaload
    //   346: aload_0
    //   347: invokevirtual PlayerPosX : ()I
    //   350: bipush #8
    //   352: ishr
    //   353: baload
    //   354: bipush #43
    //   356: if_icmpeq -> 415
    //   359: getstatic MainCanvas.tempWorldMapData : [[B
    //   362: aload_0
    //   363: invokevirtual PlayerPosY : ()I
    //   366: bipush #8
    //   368: ishr
    //   369: getstatic MainCanvas.MapH : I
    //   372: irem
    //   373: aaload
    //   374: aload_0
    //   375: invokevirtual PlayerPosX : ()I
    //   378: bipush #8
    //   380: ishr
    //   381: baload
    //   382: bipush #52
    //   384: if_icmpeq -> 415
    //   387: getstatic MainCanvas.tempWorldMapData : [[B
    //   390: aload_0
    //   391: invokevirtual PlayerPosY : ()I
    //   394: bipush #8
    //   396: ishr
    //   397: getstatic MainCanvas.MapH : I
    //   400: irem
    //   401: aaload
    //   402: aload_0
    //   403: invokevirtual PlayerPosX : ()I
    //   406: bipush #8
    //   408: ishr
    //   409: baload
    //   410: bipush #53
    //   412: if_icmpne -> 417
    //   415: iconst_1
    //   416: istore_3
    //   417: iload_3
    //   418: ifne -> 729
    //   421: getstatic MainCanvas.raidOn : Z
    //   424: ifeq -> 649
    //   427: aload_0
    //   428: aload_0
    //   429: invokevirtual PlayerPosX : ()I
    //   432: bipush #12
    //   434: isub
    //   435: aload_0
    //   436: invokevirtual PlayerPosY : ()I
    //   439: bipush #24
    //   441: isub
    //   442: invokevirtual blockColChk2 : (II)Z
    //   445: ifeq -> 552
    //   448: aload_0
    //   449: aload_0
    //   450: invokevirtual PlayerPosX : ()I
    //   453: bipush #12
    //   455: iadd
    //   456: aload_0
    //   457: invokevirtual PlayerPosY : ()I
    //   460: bipush #24
    //   462: isub
    //   463: invokevirtual blockColChk2 : (II)Z
    //   466: ifeq -> 552
    //   469: aload_0
    //   470: invokevirtual rcol2 : ()Z
    //   473: ifeq -> 509
    //   476: getstatic MainCanvas.PlayerParam : [I
    //   479: bipush #10
    //   481: iconst_0
    //   482: iastore
    //   483: getstatic MainCanvas.PlayerParam : [I
    //   486: bipush #13
    //   488: iconst_0
    //   489: iastore
    //   490: getstatic MainCanvas.PlayerParam : [I
    //   493: bipush #14
    //   495: iconst_0
    //   496: iastore
    //   497: getstatic MainCanvas.KeyPress : [Z
    //   500: iconst_4
    //   501: baload
    //   502: ifeq -> 509
    //   505: aload_0
    //   506: invokevirtual playerPushSet : ()V
    //   509: aload_0
    //   510: invokevirtual lcol2 : ()Z
    //   513: ifeq -> 729
    //   516: getstatic MainCanvas.PlayerParam : [I
    //   519: bipush #10
    //   521: iconst_0
    //   522: iastore
    //   523: getstatic MainCanvas.PlayerParam : [I
    //   526: bipush #13
    //   528: iconst_0
    //   529: iastore
    //   530: getstatic MainCanvas.PlayerParam : [I
    //   533: bipush #14
    //   535: iconst_0
    //   536: iastore
    //   537: getstatic MainCanvas.KeyPress : [Z
    //   540: iconst_3
    //   541: baload
    //   542: ifeq -> 729
    //   545: aload_0
    //   546: invokevirtual playerPushSet : ()V
    //   549: goto -> 729
    //   552: aload_0
    //   553: invokevirtual rcol3 : ()Z
    //   556: ifne -> 566
    //   559: aload_0
    //   560: invokevirtual rcol2 : ()Z
    //   563: ifeq -> 599
    //   566: getstatic MainCanvas.PlayerParam : [I
    //   569: bipush #10
    //   571: iconst_0
    //   572: iastore
    //   573: getstatic MainCanvas.PlayerParam : [I
    //   576: bipush #13
    //   578: iconst_0
    //   579: iastore
    //   580: getstatic MainCanvas.PlayerParam : [I
    //   583: bipush #14
    //   585: iconst_0
    //   586: iastore
    //   587: getstatic MainCanvas.KeyPress : [Z
    //   590: iconst_4
    //   591: baload
    //   592: ifeq -> 599
    //   595: aload_0
    //   596: invokevirtual playerPushSet : ()V
    //   599: aload_0
    //   600: invokevirtual lcol3 : ()Z
    //   603: ifne -> 613
    //   606: aload_0
    //   607: invokevirtual lcol2 : ()Z
    //   610: ifeq -> 729
    //   613: getstatic MainCanvas.PlayerParam : [I
    //   616: bipush #10
    //   618: iconst_0
    //   619: iastore
    //   620: getstatic MainCanvas.PlayerParam : [I
    //   623: bipush #13
    //   625: iconst_0
    //   626: iastore
    //   627: getstatic MainCanvas.PlayerParam : [I
    //   630: bipush #14
    //   632: iconst_0
    //   633: iastore
    //   634: getstatic MainCanvas.KeyPress : [Z
    //   637: iconst_3
    //   638: baload
    //   639: ifeq -> 729
    //   642: aload_0
    //   643: invokevirtual playerPushSet : ()V
    //   646: goto -> 729
    //   649: aload_0
    //   650: invokevirtual rcol : ()Z
    //   653: ifeq -> 689
    //   656: getstatic MainCanvas.PlayerParam : [I
    //   659: bipush #10
    //   661: iconst_0
    //   662: iastore
    //   663: getstatic MainCanvas.PlayerParam : [I
    //   666: bipush #13
    //   668: iconst_0
    //   669: iastore
    //   670: getstatic MainCanvas.PlayerParam : [I
    //   673: bipush #14
    //   675: iconst_0
    //   676: iastore
    //   677: getstatic MainCanvas.KeyPress : [Z
    //   680: iconst_4
    //   681: baload
    //   682: ifeq -> 689
    //   685: aload_0
    //   686: invokevirtual playerPushSet : ()V
    //   689: aload_0
    //   690: invokevirtual lcol : ()Z
    //   693: ifeq -> 729
    //   696: getstatic MainCanvas.PlayerParam : [I
    //   699: bipush #10
    //   701: iconst_0
    //   702: iastore
    //   703: getstatic MainCanvas.PlayerParam : [I
    //   706: bipush #13
    //   708: iconst_0
    //   709: iastore
    //   710: getstatic MainCanvas.PlayerParam : [I
    //   713: bipush #14
    //   715: iconst_0
    //   716: iastore
    //   717: getstatic MainCanvas.KeyPress : [Z
    //   720: iconst_3
    //   721: baload
    //   722: ifeq -> 729
    //   725: aload_0
    //   726: invokevirtual playerPushSet : ()V
    //   729: getstatic MainCanvas.PlayerParam : [I
    //   732: iconst_0
    //   733: iaload
    //   734: istore #4
    //   736: getstatic MainCanvas.PlayerParam : [I
    //   739: iconst_1
    //   740: iaload
    //   741: istore #5
    //   743: iconst_1
    //   744: istore #6
    //   746: aload_0
    //   747: invokevirtual setPlayerPos : ()Z
    //   750: ifeq -> 763
    //   753: aload_0
    //   754: getfield crushing : [Z
    //   757: iconst_0
    //   758: iconst_1
    //   759: bastore
    //   760: iconst_0
    //   761: istore #6
    //   763: aload_0
    //   764: getfield noLeverTimer : I
    //   767: ifle -> 792
    //   770: iload #6
    //   772: ifeq -> 1205
    //   775: getstatic MainCanvas.PlayerParam : [I
    //   778: iconst_0
    //   779: iload #4
    //   781: iastore
    //   782: getstatic MainCanvas.PlayerParam : [I
    //   785: iconst_1
    //   786: iload #5
    //   788: iastore
    //   789: goto -> 1205
    //   792: getstatic MainCanvas.raidOn : Z
    //   795: ifne -> 1069
    //   798: iload #6
    //   800: ifeq -> 1069
    //   803: iload_3
    //   804: ifne -> 1069
    //   807: getstatic MainCanvas.PlayerParam : [I
    //   810: iconst_0
    //   811: iload #4
    //   813: iastore
    //   814: getstatic MainCanvas.PlayerParam : [I
    //   817: iconst_1
    //   818: iload #5
    //   820: iastore
    //   821: aload_0
    //   822: getfield zoneNumber : I
    //   825: ifne -> 873
    //   828: getstatic MainCanvas.PlayerParam : [I
    //   831: bipush #10
    //   833: iaload
    //   834: invokestatic abs : (I)I
    //   837: sipush #2560
    //   840: if_icmple -> 873
    //   843: getstatic MainCanvas.PlayerParam : [I
    //   846: bipush #10
    //   848: iaload
    //   849: ifge -> 864
    //   852: getstatic MainCanvas.PlayerParam : [I
    //   855: bipush #10
    //   857: sipush #-4224
    //   860: iastore
    //   861: goto -> 873
    //   864: getstatic MainCanvas.PlayerParam : [I
    //   867: bipush #10
    //   869: sipush #4224
    //   872: iastore
    //   873: getstatic MainCanvas.PlayerParam : [I
    //   876: iconst_3
    //   877: aload_0
    //   878: getstatic MainCanvas.olddir : I
    //   881: bipush #90
    //   883: iadd
    //   884: invokevirtual dSin : (I)I
    //   887: getstatic MainCanvas.PlayerParam : [I
    //   890: bipush #10
    //   892: iaload
    //   893: imul
    //   894: bipush #100
    //   896: idiv
    //   897: iastore
    //   898: getstatic MainCanvas.PlayerParam : [I
    //   901: iconst_5
    //   902: aload_0
    //   903: getstatic MainCanvas.olddir : I
    //   906: bipush #90
    //   908: iadd
    //   909: invokevirtual dCos : (I)I
    //   912: getstatic MainCanvas.PlayerParam : [I
    //   915: bipush #10
    //   917: iaload
    //   918: imul
    //   919: bipush #100
    //   921: idiv
    //   922: iastore
    //   923: getstatic MainCanvas.olddir : I
    //   926: bipush #90
    //   928: isub
    //   929: sipush #180
    //   932: irem
    //   933: ifne -> 1016
    //   936: getstatic MainCanvas.olddir : I
    //   939: bipush #90
    //   941: if_icmpne -> 979
    //   944: getstatic MainCanvas.PlayerParam : [I
    //   947: iconst_0
    //   948: dup2
    //   949: iaload
    //   950: sipush #256
    //   953: iadd
    //   954: iastore
    //   955: aload_0
    //   956: getfield zoneNumber : I
    //   959: iconst_4
    //   960: if_icmpne -> 1016
    //   963: aload_0
    //   964: getfield stageNumber : I
    //   967: ifeq -> 1016
    //   970: getstatic MainCanvas.PlayerParam : [I
    //   973: iconst_5
    //   974: iconst_0
    //   975: iastore
    //   976: goto -> 1016
    //   979: getstatic MainCanvas.PlayerParam : [I
    //   982: iconst_0
    //   983: dup2
    //   984: iaload
    //   985: sipush #256
    //   988: isub
    //   989: iastore
    //   990: getstatic MainCanvas.PlayerParam : [I
    //   993: bipush #10
    //   995: iaload
    //   996: sipush #3072
    //   999: if_icmple -> 1016
    //   1002: aload_0
    //   1003: getfield zoneNumber : I
    //   1006: iconst_4
    //   1007: if_icmpne -> 1016
    //   1010: aload_0
    //   1011: bipush #15
    //   1013: putfield noLeverTimer : I
    //   1016: getstatic MainCanvas.PlayerParam : [I
    //   1019: bipush #10
    //   1021: iconst_0
    //   1022: iastore
    //   1023: getstatic MainCanvas.PlayerParam : [I
    //   1026: bipush #13
    //   1028: iconst_0
    //   1029: iastore
    //   1030: getstatic MainCanvas.PlayerParam : [I
    //   1033: bipush #14
    //   1035: iconst_0
    //   1036: iastore
    //   1037: iconst_1
    //   1038: putstatic MainCanvas.PlayerAir : Z
    //   1041: iconst_1
    //   1042: putstatic MainCanvas.PlayerJump : Z
    //   1045: iconst_0
    //   1046: putstatic MainCanvas.PlayerDamage : Z
    //   1049: iconst_0
    //   1050: putstatic MainCanvas.raidOn : Z
    //   1053: aload_0
    //   1054: invokevirtual hcol : ()Z
    //   1057: ifeq -> 1205
    //   1060: getstatic MainCanvas.PlayerParam : [I
    //   1063: iconst_5
    //   1064: iconst_0
    //   1065: iastore
    //   1066: goto -> 1205
    //   1069: iconst_0
    //   1070: istore_3
    //   1071: aload_0
    //   1072: getfield zoneNumber : I
    //   1075: ifne -> 1136
    //   1078: getstatic MainCanvas.tempWorldMapData : [[B
    //   1081: aload_0
    //   1082: invokevirtual PlayerPosY : ()I
    //   1085: bipush #8
    //   1087: ishr
    //   1088: getstatic MainCanvas.MapH : I
    //   1091: irem
    //   1092: aaload
    //   1093: aload_0
    //   1094: invokevirtual PlayerPosX : ()I
    //   1097: bipush #8
    //   1099: ishr
    //   1100: baload
    //   1101: bipush #31
    //   1103: if_icmpeq -> 1134
    //   1106: getstatic MainCanvas.tempWorldMapData : [[B
    //   1109: aload_0
    //   1110: invokevirtual PlayerPosY : ()I
    //   1113: bipush #8
    //   1115: ishr
    //   1116: getstatic MainCanvas.MapH : I
    //   1119: irem
    //   1120: aaload
    //   1121: aload_0
    //   1122: invokevirtual PlayerPosX : ()I
    //   1125: bipush #8
    //   1127: ishr
    //   1128: baload
    //   1129: bipush #32
    //   1131: if_icmpne -> 1136
    //   1134: iconst_1
    //   1135: istore_3
    //   1136: aload_0
    //   1137: getfield zoneNumber : I
    //   1140: iconst_2
    //   1141: if_icmpne -> 1196
    //   1144: getstatic MainCanvas.PlayerParam : [I
    //   1147: bipush #8
    //   1149: iaload
    //   1150: sipush #365
    //   1153: if_icmpeq -> 1192
    //   1156: getstatic MainCanvas.PlayerParam : [I
    //   1159: bipush #8
    //   1161: iaload
    //   1162: sipush #364
    //   1165: if_icmpeq -> 1192
    //   1168: getstatic MainCanvas.PlayerParam : [I
    //   1171: bipush #8
    //   1173: iaload
    //   1174: sipush #363
    //   1177: if_icmpeq -> 1192
    //   1180: getstatic MainCanvas.PlayerParam : [I
    //   1183: bipush #8
    //   1185: iaload
    //   1186: sipush #362
    //   1189: if_icmpne -> 1196
    //   1192: aload_0
    //   1193: invokevirtual playdamageset : ()V
    //   1196: iload_3
    //   1197: ifne -> 1205
    //   1200: aload_0
    //   1201: invokevirtual fallchk : ()Z
    //   1204: pop
    //   1205: iconst_0
    //   1206: ireturn
  }
  
  public void ball00jump() {
    int i = Math.abs(PlayerParam[3]);
    if (this.noLeverTimer > 0) {
      this.noLeverTimer--;
    } else if (!this.limitBreak) {
      if (KeyPress[3]) {
        PlayerParam[12] = 1;
        if (PlayerParam[3] == 0) {
          PlayerParam[3] = PlayerParam[3] - (plretspd << 1);
        } else {
          PlayerParam[3] = PlayerParam[3] - pladdspd;
          if (PlayerParam[3] > 0)
            PlayerParam[3] = PlayerParam[3] - pladdspd; 
        } 
      } else if (KeyPress[4]) {
        PlayerParam[12] = 0;
        if (PlayerParam[3] == 0) {
          PlayerParam[3] = PlayerParam[3] + (plretspd << 1);
        } else {
          PlayerParam[3] = PlayerParam[3] + pladdspd;
          if (PlayerParam[3] < 0)
            PlayerParam[3] = PlayerParam[3] + pladdspd; 
        } 
      } 
    } 
    if (Math.abs(PlayerParam[3]) > 4096)
      if (PlayerParam[3] < 0) {
        PlayerParam[3] = -4096;
      } else {
        PlayerParam[3] = 4096;
      }  
    if (this.zoneNumber == 3 && Math.abs(PlayerParam[3]) > plmaxspd && i <= plmaxspd)
      if (PlayerParam[3] < 0) {
        PlayerParam[3] = -plmaxspd;
      } else {
        PlayerParam[3] = plmaxspd;
      }  
    plspeed[0] = plspeed[0] + PlayerParam[3];
    plspeed[1] = plspeed[1] + PlayerParam[5];
    if (PlayerParam[5] > 0 && this.nofcolTimer <= 0) {
      if (olddir == 270) {
        PlayerParam[0] = PlayerParam[0] - 3072;
      } else if (olddir == 90) {
        PlayerParam[0] = PlayerParam[0] + 3072;
      } 
      olddir = 0;
    } 
    jumpmove();
    speedset(1);
    if (this.zoneNumber == 0 || this.zoneNumber == 3)
      loopchange(); 
    nocoltimer--;
    if (PlayerJump && nocoltimer <= 0) {
      if (rcol2()) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerParam[3] = 0;
      } 
      if (lcol2()) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerParam[3] = 0;
      } 
    } 
    jumpcolchk();
  }
  
  public void jumpmove() {}
  
  public void playerTyakuchi(int paramInt) {
    if (paramInt == 0) {
      PlayerBall = false;
      comboScore = 0;
      PlayerJump = false;
      PlayerSJump = false;
      PlayerDamage = false;
    } 
    if (paramInt == 0) {
      PlayerParam[10] = PlayerParam[3];
      int i = getPlayerArg(PlayerPosX(), PlayerPosY());
      if (i < 0)
        for (byte b = 1; b < this.PlayerW + 1; b++) {
          i = getPlayerArg(PlayerPosX() - b, PlayerPosY());
          if (i >= 0)
            break; 
          i = getPlayerArg(PlayerPosX() + b, PlayerPosY());
          if (i >= 0)
            break; 
        }  
      olddir = i;
      if ((i <= 67 && i >= 44) || (i >= 293 && i <= 316)) {
        if (PlayerParam[5] > 3072)
          PlayerParam[5] = 4096; 
        if (dCos(i + 90) > 0) {
          PlayerParam[10] = PlayerParam[5];
        } else {
          PlayerParam[10] = -PlayerParam[5];
        } 
      } else if (i < 338 && i > 22) {
        PlayerParam[10] = PlayerParam[10] + dCos(i + 90) * PlayerParam[5] / 100;
      } 
      PlayerParam[5] = 0;
      PlayerParam[3] = 0;
      if (this.zoneNumber == 2 && (PlayerParam[8] == 365 || PlayerParam[8] == 364 || PlayerParam[8] == 363 || PlayerParam[8] == 362))
        this.playdamageYogan = true; 
      if (PlayerParam[10] < 0)
        PlayerParam[13] = 1; 
      if (PlayerParam[10] > 0)
        PlayerParam[13] = 2; 
      PlayerParam[14] = 0;
    } else if (PlayerSJump) {
      int i = getPlayerArg(PlayerPosX(), PlayerPosY() - 32);
      if (i < 0)
        i = olddir; 
      if (i % 90 == 0) {
        PlayerParam[5] = 0;
      } else {
        PlayerParam[3] = PlayerParam[3] + -(dCos(i + 90) * PlayerParam[5]) / 100;
      } 
    } else {
      PlayerParam[5] = 0;
    } 
  }
  
  public void jumpcolchk() {
    if (this.nofcolTimer > 0) {
      this.nofcolTimer--;
      return;
    } 
    if (-pljump_w <= PlayerParam[5]) {
      KeyPress[2] = false;
    } else if (!KeyPress[2] && !PlayerSJump && !PlayerAir) {
      PlayerParam[5] = -pljump_w;
    } 
    if (PlayerParam[5] > 0) {
      PlayerSJump = false;
      if (fcol())
        if (olddir >= 270 || olddir <= 90) {
          playerTyakuchi(0);
        } else {
          olddir = 0;
        }  
    } else if (hcol()) {
      playerTyakuchi(1);
    } 
  }
  
  public void levermove() {
    if (this.limitBreak) {
      PlayerParam[12] = 0;
      if (PlayerParam[13] == 0) {
        PlayerParam[13] = 2;
        PlayerParam[14] = 2;
      } 
      plwalk(1);
    } else if (!PlayerWater) {
      if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
        PlayerParam[10] = PlayerParam[10] + 256;
        if (1024 < PlayerParam[10])
          PlayerParam[10] = 1024; 
        PlayerParam[12] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
      } else if (this.noLeverTimer > 0) {
        this.noLeverTimer--;
      } else if (KeyPress[3]) {
        PlayerParam[12] = 1;
        if (PlayerParam[13] == 0)
          PlayerParam[13] = 1; 
        if (PlayerParam[14] == 0)
          PlayerParam[14] = 1; 
        plwalk(0);
      } else if (KeyPress[4]) {
        PlayerParam[12] = 0;
        if (PlayerParam[13] == 0)
          PlayerParam[13] = 2; 
        if (PlayerParam[14] == 0)
          PlayerParam[14] = 2; 
        plwalk(1);
      } 
    } 
    plspeed[0] = plspeed[0] + dSin(olddir + 90) * PlayerParam[10] / 100;
    plspeed[1] = plspeed[1] + dCos(olddir + 90) * PlayerParam[10] / 100;
  }
  
  public void plwalk(int paramInt) {
    boolean bool = false;
    int i = Math.abs(PlayerParam[10]);
    if (paramInt == 1) {
      if (PlayerParam[10] < 0 && PlayerParam[13] == 1) {
        PlayerParam[10] = PlayerParam[10] + plretspd;
        if (PlayerParam[10] > 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } 
      } 
      if (PlayerParam[10] == 0)
        PlayerParam[10] = PlayerParam[10] + pladdspd; 
      PlayerParam[10] = PlayerParam[10] + pladdspd;
      if (PlayerParam[10] > 0 && PlayerParam[13] == 1) {
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
      } 
    } else {
      if (PlayerParam[10] > 0 && PlayerParam[13] == 2) {
        PlayerParam[10] = PlayerParam[10] - plretspd;
        if (PlayerParam[10] < 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } 
      } 
      if (PlayerParam[10] == 0)
        PlayerParam[10] = PlayerParam[10] - pladdspd; 
      PlayerParam[10] = PlayerParam[10] - pladdspd;
      if (PlayerParam[10] < 0 && PlayerParam[13] == 2) {
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
      } 
    } 
  }
  
  public void blevermove() {
    if (this.limitBreak) {
      PlayerParam[12] = 0;
      if (PlayerParam[13] == 0) {
        PlayerParam[13] = 2;
        PlayerParam[14] = 2;
      } 
      plwalk(1);
    } else if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
      PlayerParam[10] = PlayerParam[10] + 256;
      if (2560 < PlayerParam[10])
        PlayerParam[10] = 2560; 
    } else if (this.noLeverTimer > 0) {
      this.noLeverTimer--;
    } else if (KeyPress[3]) {
      if (PlayerParam[13] == 0)
        PlayerParam[13] = 1; 
      if (PlayerParam[14] == 0)
        PlayerParam[14] = 1; 
      PlayerParam[12] = 1;
      plballwalk(0);
    } else if (KeyPress[4]) {
      if (PlayerParam[13] == 0)
        PlayerParam[13] = 2; 
      if (PlayerParam[14] == 0)
        PlayerParam[14] = 2; 
      PlayerParam[12] = 0;
      plballwalk(1);
    } 
    plspeed[0] = plspeed[0] + dSin(olddir + 90) * PlayerParam[10] / 100;
    plspeed[1] = plspeed[1] + dCos(olddir + 90) * PlayerParam[10] / 100;
    if (PlayerParam[10] != 0 && PlayerParam[10] != 0)
      if (PlayerParam[10] < 0) {
        PlayerParam[10] = PlayerParam[10] + pladdspd;
        if (PlayerParam[10] >= 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
          PlayerBall = false;
        } 
      } else {
        PlayerParam[10] = PlayerParam[10] - pladdspd;
        if (PlayerParam[10] <= 0) {
          PlayerParam[10] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
          PlayerBall = false;
        } 
      }  
    if (PlayerParam[10] == 0) {
      PlayerBall = false;
      comboScore = 0;
    } 
  }
  
  public void plballwalk(int paramInt) {
    boolean bool = false;
    if (paramInt == 1) {
      PlayerParam[10] = PlayerParam[10] + (pladdspd >> 1);
      if (Math.abs(PlayerParam[10]) > 4096)
        if (PlayerParam[10] < 0) {
          PlayerParam[10] = -4096;
        } else {
          PlayerParam[10] = 4096;
        }  
    } else {
      PlayerParam[10] = PlayerParam[10] - (pladdspd >> 1);
      if (Math.abs(PlayerParam[10]) > 4096)
        if (PlayerParam[10] < 0) {
          PlayerParam[10] = -4096;
        } else {
          PlayerParam[10] = 4096;
        }  
    } 
  }
  
  public void keispd(int paramInt) {
    if (raidOn)
      return; 
    if (0 == paramInt) {
      int i = olddir;
      if (i < 0) {
        i = 0;
        if (olddir != 0 && !raidOn && (olddir <= 22 || olddir >= 338))
          if (PlayerParam[10] > 0) {
            i = 90;
          } else if (PlayerParam[10] < 0) {
            i = 270;
          } else {
            i = olddir;
          }  
      } 
      if (olddir > 22 && olddir < 338)
        PlayerParam[10] = PlayerParam[10] + dCos(i + 90) * 32 / 100; 
    } else {
      int i = PlayerParam[10];
      int j = olddir;
      if (j < 0) {
        j = 0;
        if (olddir != 0 && !raidOn && (olddir <= 22 || olddir >= 338))
          if (PlayerParam[10] > 0) {
            j = 90;
          } else if (PlayerParam[10] < 0) {
            j = 270;
          } else {
            j = olddir;
          }  
      } 
      int k = (j + 135) % 360;
      if (j < 338 && j > 22) {
        if (dCos(j + 90) * 80 / 100 < 0 && PlayerParam[10] < 0)
          PlayerParam[10] = PlayerParam[10] + dCos(j + 90) * 80 / 100; 
        if (dCos(j + 90) * 80 / 100 > 0 && PlayerParam[10] > 0)
          PlayerParam[10] = PlayerParam[10] + dCos(j + 90) * 80 / 100; 
      } else if (olddir == 338 || olddir == 22) {
        if ((dCos(j + 90) << 5) / 100 < 0 && PlayerParam[10] < 0)
          PlayerParam[10] = PlayerParam[10] + dCos(j + 90) * 80 / 100; 
        if ((dCos(j + 90) << 5) / 100 > 0 && PlayerParam[10] > 0)
          PlayerParam[10] = PlayerParam[10] + dCos(j + 90) * 80 / 100; 
      } else {
        PlayerParam[10] = PlayerParam[10] + dCos(j + 90) * 80 / 100;
      } 
      if (PlayerParam[10] >= 0 && i <= 0) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerBall = false;
      } 
      if (PlayerParam[10] <= 0 && i >= 0) {
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        PlayerBall = false;
      } 
    } 
  }
  
  public void limitchk(boolean paramBoolean) {
    // Byte code:
    //   0: bipush #112
    //   2: istore_3
    //   3: aload_0
    //   4: getfield LookUpCount : I
    //   7: ifle -> 19
    //   10: iload_3
    //   11: aload_0
    //   12: getfield LookUpCount : I
    //   15: iconst_1
    //   16: ishl
    //   17: iadd
    //   18: istore_3
    //   19: aload_0
    //   20: getfield CrouchCount : I
    //   23: ifle -> 35
    //   26: iload_3
    //   27: aload_0
    //   28: getfield CrouchCount : I
    //   31: iconst_1
    //   32: ishl
    //   33: isub
    //   34: istore_3
    //   35: aload_0
    //   36: getfield zoneNumber : I
    //   39: ifne -> 103
    //   42: aload_0
    //   43: getfield stageNumber : I
    //   46: iconst_3
    //   47: if_icmpne -> 103
    //   50: aload_0
    //   51: getfield poslimit : [I
    //   54: iconst_0
    //   55: iconst_0
    //   56: iastore
    //   57: aload_0
    //   58: getfield poslimit : [I
    //   61: iconst_2
    //   62: iconst_0
    //   63: iastore
    //   64: aload_0
    //   65: getfield poslimit : [I
    //   68: iconst_1
    //   69: sipush #3840
    //   72: iastore
    //   73: aload_0
    //   74: getfield poslimit : [I
    //   77: iconst_3
    //   78: sipush #256
    //   81: iastore
    //   82: getstatic MainCanvas.bossBreakOn : Z
    //   85: ifeq -> 920
    //   88: aload_0
    //   89: getfield poslimit : [I
    //   92: iconst_1
    //   93: dup2
    //   94: iaload
    //   95: sipush #768
    //   98: iadd
    //   99: iastore
    //   100: goto -> 920
    //   103: aload_0
    //   104: getfield zoneNumber : I
    //   107: iconst_5
    //   108: if_icmpne -> 288
    //   111: aload_0
    //   112: getfield stageNumber : I
    //   115: iconst_3
    //   116: if_icmpne -> 288
    //   119: aload_0
    //   120: getfield poslimit : [I
    //   123: iconst_0
    //   124: iconst_0
    //   125: iastore
    //   126: aload_0
    //   127: getfield poslimit : [I
    //   130: iconst_2
    //   131: iconst_0
    //   132: iastore
    //   133: aload_0
    //   134: getfield poslimit : [I
    //   137: iconst_1
    //   138: sipush #1440
    //   141: iastore
    //   142: aload_0
    //   143: getfield poslimit : [I
    //   146: iconst_3
    //   147: bipush #64
    //   149: iload_3
    //   150: iadd
    //   151: iastore
    //   152: getstatic MainCanvas.bossBreakOn : Z
    //   155: ifeq -> 170
    //   158: aload_0
    //   159: getfield poslimit : [I
    //   162: iconst_1
    //   163: dup2
    //   164: iaload
    //   165: sipush #768
    //   168: iadd
    //   169: iastore
    //   170: getstatic MainCanvas.mapOxy : [I
    //   173: iconst_0
    //   174: iaload
    //   175: sipush #1200
    //   178: if_icmplt -> 920
    //   181: getstatic MainCanvas.bossModeOn : Z
    //   184: ifeq -> 223
    //   187: aload_0
    //   188: getfield poslimit : [I
    //   191: iconst_0
    //   192: sipush #1200
    //   195: iastore
    //   196: aload_0
    //   197: getfield poslimit : [I
    //   200: iconst_1
    //   201: sipush #1440
    //   204: iastore
    //   205: aload_0
    //   206: getfield poslimit : [I
    //   209: iconst_2
    //   210: bipush #32
    //   212: iastore
    //   213: aload_0
    //   214: getfield poslimit : [I
    //   217: iconst_3
    //   218: bipush #32
    //   220: iload_3
    //   221: iadd
    //   222: iastore
    //   223: getstatic MainCanvas.bossBreakOn : Z
    //   226: ifeq -> 238
    //   229: aload_0
    //   230: getfield poslimit : [I
    //   233: iconst_0
    //   234: sipush #1200
    //   237: iastore
    //   238: getstatic MainCanvas.mapOxy : [I
    //   241: iconst_0
    //   242: iaload
    //   243: sipush #1808
    //   246: if_icmplt -> 920
    //   249: aload_0
    //   250: getfield poslimit : [I
    //   253: iconst_0
    //   254: sipush #1808
    //   257: iastore
    //   258: aload_0
    //   259: getfield poslimit : [I
    //   262: iconst_1
    //   263: sipush #2048
    //   266: iastore
    //   267: aload_0
    //   268: getfield poslimit : [I
    //   271: iconst_2
    //   272: bipush #32
    //   274: iastore
    //   275: aload_0
    //   276: getfield poslimit : [I
    //   279: iconst_3
    //   280: bipush #64
    //   282: iload_3
    //   283: iadd
    //   284: iastore
    //   285: goto -> 920
    //   288: aload_0
    //   289: getfield poslimit : [I
    //   292: iconst_0
    //   293: iconst_0
    //   294: iastore
    //   295: aload_0
    //   296: getfield poslimit : [I
    //   299: iconst_2
    //   300: iconst_0
    //   301: iastore
    //   302: aload_0
    //   303: getfield poslimit : [I
    //   306: iconst_1
    //   307: aload_0
    //   308: getfield limitTable : [[[I
    //   311: aload_0
    //   312: getfield zoneNumber : I
    //   315: aaload
    //   316: aload_0
    //   317: getfield stageNumber : I
    //   320: aaload
    //   321: iconst_2
    //   322: iaload
    //   323: sipush #320
    //   326: iadd
    //   327: iastore
    //   328: aload_0
    //   329: getfield poslimit : [I
    //   332: iconst_3
    //   333: aload_0
    //   334: invokevirtual hlimitget : ()I
    //   337: sipush #240
    //   340: sipush #184
    //   343: iload_3
    //   344: isub
    //   345: isub
    //   346: iadd
    //   347: iastore
    //   348: aload_0
    //   349: getfield m_bScrollLock : B
    //   352: iconst_2
    //   353: if_icmpne -> 361
    //   356: aload_0
    //   357: iconst_1
    //   358: putfield limitBreak : Z
    //   361: aload_0
    //   362: getfield stageNumber : I
    //   365: iconst_2
    //   366: if_icmpne -> 438
    //   369: aload_0
    //   370: getfield zoneNumber : I
    //   373: iconst_1
    //   374: if_icmpne -> 438
    //   377: aload_0
    //   378: invokevirtual PlayerPosX : ()I
    //   381: aload_0
    //   382: getfield m_aaScrollLockPos : [[S
    //   385: aload_0
    //   386: getfield zoneNumber : I
    //   389: aaload
    //   390: aload_0
    //   391: getfield stageNumber : I
    //   394: saload
    //   395: if_icmpge -> 406
    //   398: aload_0
    //   399: getfield m_bScrollLock : B
    //   402: iconst_1
    //   403: if_icmpne -> 553
    //   406: aload_0
    //   407: getfield poslimit : [I
    //   410: iconst_0
    //   411: aload_0
    //   412: getfield m_aaScrollLockPos : [[S
    //   415: aload_0
    //   416: getfield zoneNumber : I
    //   419: aaload
    //   420: aload_0
    //   421: getfield stageNumber : I
    //   424: saload
    //   425: sipush #144
    //   428: isub
    //   429: iastore
    //   430: aload_0
    //   431: iconst_1
    //   432: putfield m_bScrollLock : B
    //   435: goto -> 553
    //   438: aload_0
    //   439: getfield stageNumber : I
    //   442: iconst_2
    //   443: if_icmpge -> 507
    //   446: aload_0
    //   447: invokevirtual PlayerPosX : ()I
    //   450: aload_0
    //   451: getfield m_aaScrollLockPos : [[S
    //   454: aload_0
    //   455: getfield zoneNumber : I
    //   458: aaload
    //   459: aload_0
    //   460: getfield stageNumber : I
    //   463: saload
    //   464: if_icmpge -> 475
    //   467: aload_0
    //   468: getfield m_bScrollLock : B
    //   471: iconst_1
    //   472: if_icmpne -> 553
    //   475: aload_0
    //   476: getfield poslimit : [I
    //   479: iconst_0
    //   480: aload_0
    //   481: getfield m_aaScrollLockPos : [[S
    //   484: aload_0
    //   485: getfield zoneNumber : I
    //   488: aaload
    //   489: aload_0
    //   490: getfield stageNumber : I
    //   493: saload
    //   494: sipush #144
    //   497: isub
    //   498: iastore
    //   499: aload_0
    //   500: iconst_1
    //   501: putfield m_bScrollLock : B
    //   504: goto -> 553
    //   507: aload_0
    //   508: getfield m_bScrollLock : B
    //   511: iconst_2
    //   512: if_icmpne -> 553
    //   515: aload_0
    //   516: getfield zoneNumber : I
    //   519: iconst_1
    //   520: if_icmpne -> 526
    //   523: goto -> 553
    //   526: aload_0
    //   527: getfield poslimit : [I
    //   530: iconst_0
    //   531: aload_0
    //   532: getfield limitTable : [[[I
    //   535: aload_0
    //   536: getfield zoneNumber : I
    //   539: aaload
    //   540: aload_0
    //   541: getfield stageNumber : I
    //   544: aaload
    //   545: iconst_2
    //   546: iaload
    //   547: sipush #320
    //   550: iadd
    //   551: i2s
    //   552: iastore
    //   553: aload_0
    //   554: getfield zoneNumber : I
    //   557: iconst_1
    //   558: if_icmpne -> 580
    //   561: aload_0
    //   562: getfield stageNumber : I
    //   565: iconst_2
    //   566: if_icmpne -> 580
    //   569: aload_0
    //   570: getfield poslimit : [I
    //   573: iconst_3
    //   574: dup2
    //   575: iaload
    //   576: bipush #56
    //   578: iadd
    //   579: iastore
    //   580: aload_0
    //   581: getfield zoneNumber : I
    //   584: iconst_5
    //   585: if_icmpne -> 608
    //   588: aload_0
    //   589: getfield stageNumber : I
    //   592: iconst_1
    //   593: if_icmpne -> 608
    //   596: aload_0
    //   597: getfield poslimit : [I
    //   600: iconst_3
    //   601: dup2
    //   602: iaload
    //   603: sipush #168
    //   606: isub
    //   607: iastore
    //   608: aload_0
    //   609: getfield stageNumber : I
    //   612: iconst_2
    //   613: if_icmpne -> 642
    //   616: aload_0
    //   617: getfield zoneNumber : I
    //   620: iconst_1
    //   621: if_icmpeq -> 642
    //   624: getstatic MainCanvas.bossBreakOn : Z
    //   627: ifeq -> 642
    //   630: aload_0
    //   631: getfield poslimit : [I
    //   634: iconst_1
    //   635: dup2
    //   636: iaload
    //   637: sipush #256
    //   640: iadd
    //   641: iastore
    //   642: getstatic MainCanvas.bossModeOn : Z
    //   645: ifeq -> 838
    //   648: aload_0
    //   649: getfield zoneNumber : I
    //   652: ifne -> 694
    //   655: aload_0
    //   656: getfield poslimit : [I
    //   659: iconst_0
    //   660: sipush #10632
    //   663: iastore
    //   664: aload_0
    //   665: getfield poslimit : [I
    //   668: iconst_1
    //   669: sipush #10872
    //   672: iastore
    //   673: aload_0
    //   674: getfield poslimit : [I
    //   677: iconst_2
    //   678: iconst_0
    //   679: iastore
    //   680: aload_0
    //   681: getfield poslimit : [I
    //   684: iconst_3
    //   685: sipush #800
    //   688: iload_3
    //   689: iadd
    //   690: iastore
    //   691: goto -> 838
    //   694: aload_0
    //   695: getfield zoneNumber : I
    //   698: iconst_2
    //   699: if_icmpne -> 743
    //   702: aload_0
    //   703: getfield poslimit : [I
    //   706: iconst_0
    //   707: sipush #6168
    //   710: iastore
    //   711: aload_0
    //   712: getfield poslimit : [I
    //   715: iconst_1
    //   716: sipush #6440
    //   719: iastore
    //   720: aload_0
    //   721: getfield poslimit : [I
    //   724: iconst_2
    //   725: sipush #560
    //   728: iastore
    //   729: aload_0
    //   730: getfield poslimit : [I
    //   733: iconst_3
    //   734: sipush #568
    //   737: iload_3
    //   738: iadd
    //   739: iastore
    //   740: goto -> 838
    //   743: aload_0
    //   744: getfield zoneNumber : I
    //   747: iconst_4
    //   748: if_icmpne -> 792
    //   751: aload_0
    //   752: getfield poslimit : [I
    //   755: iconst_0
    //   756: sipush #11304
    //   759: iastore
    //   760: aload_0
    //   761: getfield poslimit : [I
    //   764: iconst_1
    //   765: sipush #11544
    //   768: iastore
    //   769: aload_0
    //   770: getfield poslimit : [I
    //   773: iconst_2
    //   774: sipush #1232
    //   777: iastore
    //   778: aload_0
    //   779: getfield poslimit : [I
    //   782: iconst_3
    //   783: sipush #1248
    //   786: iload_3
    //   787: iadd
    //   788: iastore
    //   789: goto -> 838
    //   792: aload_0
    //   793: getfield zoneNumber : I
    //   796: iconst_3
    //   797: if_icmpne -> 838
    //   800: aload_0
    //   801: getfield poslimit : [I
    //   804: iconst_0
    //   805: sipush #8280
    //   808: iastore
    //   809: aload_0
    //   810: getfield poslimit : [I
    //   813: iconst_1
    //   814: sipush #8520
    //   817: iastore
    //   818: aload_0
    //   819: getfield poslimit : [I
    //   822: iconst_2
    //   823: sipush #528
    //   826: iastore
    //   827: aload_0
    //   828: getfield poslimit : [I
    //   831: iconst_3
    //   832: sipush #576
    //   835: iload_3
    //   836: iadd
    //   837: iastore
    //   838: getstatic MainCanvas.bossBreakOn : Z
    //   841: ifeq -> 920
    //   844: aload_0
    //   845: getfield zoneNumber : I
    //   848: ifne -> 863
    //   851: aload_0
    //   852: getfield poslimit : [I
    //   855: iconst_0
    //   856: sipush #10632
    //   859: iastore
    //   860: goto -> 920
    //   863: aload_0
    //   864: getfield zoneNumber : I
    //   867: iconst_2
    //   868: if_icmpne -> 883
    //   871: aload_0
    //   872: getfield poslimit : [I
    //   875: iconst_0
    //   876: sipush #6168
    //   879: iastore
    //   880: goto -> 920
    //   883: aload_0
    //   884: getfield zoneNumber : I
    //   887: iconst_4
    //   888: if_icmpne -> 903
    //   891: aload_0
    //   892: getfield poslimit : [I
    //   895: iconst_0
    //   896: sipush #11304
    //   899: iastore
    //   900: goto -> 920
    //   903: aload_0
    //   904: getfield zoneNumber : I
    //   907: iconst_3
    //   908: if_icmpne -> 920
    //   911: aload_0
    //   912: getfield poslimit : [I
    //   915: iconst_0
    //   916: sipush #8280
    //   919: iastore
    //   920: getstatic MainCanvas.PlayerDie : Z
    //   923: ifeq -> 931
    //   926: aload_0
    //   927: invokevirtual checkDieCount : ()V
    //   930: return
    //   931: iconst_0
    //   932: istore_2
    //   933: iload_2
    //   934: iconst_2
    //   935: if_icmpge -> 2484
    //   938: iconst_0
    //   939: istore #4
    //   941: iload_2
    //   942: ifne -> 952
    //   945: bipush #120
    //   947: istore #4
    //   949: goto -> 955
    //   952: iload_3
    //   953: istore #4
    //   955: iconst_0
    //   956: istore #5
    //   958: iload_2
    //   959: ifne -> 971
    //   962: aload_0
    //   963: invokevirtual PlayerPosX : ()I
    //   966: istore #5
    //   968: goto -> 977
    //   971: aload_0
    //   972: invokevirtual PlayerPosY : ()I
    //   975: istore #5
    //   977: iload_2
    //   978: ifne -> 1124
    //   981: getstatic MainCanvas.bossModeOn : Z
    //   984: ifne -> 1016
    //   987: getstatic MainCanvas.MapEndCounter : I
    //   990: ifne -> 1016
    //   993: aload_0
    //   994: getfield zoneNumber : I
    //   997: iconst_5
    //   998: if_icmpne -> 1016
    //   1001: aload_0
    //   1002: getfield stageNumber : I
    //   1005: iconst_3
    //   1006: if_icmpne -> 1016
    //   1009: aload_0
    //   1010: invokespecial startBossMode : ()V
    //   1013: goto -> 1068
    //   1016: getstatic MainCanvas.bossModeOn : Z
    //   1019: ifne -> 1068
    //   1022: getstatic MainCanvas.MapEndCounter : I
    //   1025: ifne -> 1068
    //   1028: aload_0
    //   1029: getfield zoneNumber : I
    //   1032: iconst_1
    //   1033: if_icmpne -> 1068
    //   1036: aload_0
    //   1037: getfield stageNumber : I
    //   1040: iconst_2
    //   1041: if_icmpne -> 1068
    //   1044: aload_0
    //   1045: invokevirtual PlayerPosX : ()I
    //   1048: sipush #7488
    //   1051: if_icmplt -> 1068
    //   1054: aload_0
    //   1055: invokevirtual PlayerPosY : ()I
    //   1058: sipush #1536
    //   1061: if_icmplt -> 1068
    //   1064: aload_0
    //   1065: invokespecial startBossMode : ()V
    //   1068: getstatic MainCanvas.bossModeOn : Z
    //   1071: ifeq -> 1082
    //   1074: aload_0
    //   1075: getfield zoneNumber : I
    //   1078: iconst_1
    //   1079: if_icmpeq -> 1082
    //   1082: getstatic MainCanvas.mapOxy : [I
    //   1085: iload_2
    //   1086: iaload
    //   1087: aload_0
    //   1088: getfield poslimit : [I
    //   1091: iconst_1
    //   1092: iaload
    //   1093: sipush #240
    //   1096: isub
    //   1097: if_icmplt -> 1124
    //   1100: getstatic MainCanvas.bossModeOn : Z
    //   1103: ifne -> 1124
    //   1106: getstatic MainCanvas.MapEndCounter : I
    //   1109: ifne -> 1124
    //   1112: aload_0
    //   1113: getfield stageNumber : I
    //   1116: iconst_2
    //   1117: if_icmpne -> 1124
    //   1120: aload_0
    //   1121: invokespecial startBossMode : ()V
    //   1124: iload_2
    //   1125: ifne -> 1655
    //   1128: iload #5
    //   1130: getstatic MainCanvas.mapOxy : [I
    //   1133: iload_2
    //   1134: iaload
    //   1135: isub
    //   1136: iload #4
    //   1138: if_icmpeq -> 2464
    //   1141: iload #5
    //   1143: getstatic MainCanvas.mapOxy : [I
    //   1146: iload_2
    //   1147: iaload
    //   1148: isub
    //   1149: iload #4
    //   1151: if_icmpge -> 1402
    //   1154: aload_0
    //   1155: getfield gole_on : Z
    //   1158: ifeq -> 1245
    //   1161: getstatic MainCanvas.mapOxy : [I
    //   1164: iload_2
    //   1165: iaload
    //   1166: bipush #16
    //   1168: iadd
    //   1169: bipush #8
    //   1171: ishl
    //   1172: getstatic MainCanvas.PlayerParam : [I
    //   1175: iconst_0
    //   1176: iaload
    //   1177: if_icmple -> 2464
    //   1180: getstatic MainCanvas.PlayerParam : [I
    //   1183: iconst_0
    //   1184: getstatic MainCanvas.mapOxy : [I
    //   1187: iload_2
    //   1188: iaload
    //   1189: bipush #16
    //   1191: iadd
    //   1192: bipush #8
    //   1194: ishl
    //   1195: iastore
    //   1196: getstatic MainCanvas.PlayerParam : [I
    //   1199: bipush #10
    //   1201: iaload
    //   1202: ifge -> 1226
    //   1205: getstatic MainCanvas.PlayerParam : [I
    //   1208: bipush #10
    //   1210: iconst_0
    //   1211: iastore
    //   1212: getstatic MainCanvas.PlayerParam : [I
    //   1215: bipush #13
    //   1217: iconst_0
    //   1218: iastore
    //   1219: getstatic MainCanvas.PlayerParam : [I
    //   1222: bipush #14
    //   1224: iconst_0
    //   1225: iastore
    //   1226: getstatic MainCanvas.PlayerJump : Z
    //   1229: ifne -> 2464
    //   1232: getstatic MainCanvas.PlayerBall : Z
    //   1235: ifeq -> 2464
    //   1238: iconst_0
    //   1239: putstatic MainCanvas.PlayerBall : Z
    //   1242: goto -> 2464
    //   1245: aload_0
    //   1246: iconst_1
    //   1247: putfield ChkVecL : Z
    //   1250: getstatic MainCanvas.mapOxy : [I
    //   1253: iload_2
    //   1254: dup2
    //   1255: iaload
    //   1256: iload #4
    //   1258: iload #5
    //   1260: getstatic MainCanvas.mapOxy : [I
    //   1263: iload_2
    //   1264: iaload
    //   1265: isub
    //   1266: isub
    //   1267: isub
    //   1268: iastore
    //   1269: getstatic MainCanvas.mapOxy : [I
    //   1272: iload_2
    //   1273: iaload
    //   1274: aload_0
    //   1275: getfield poslimit : [I
    //   1278: iload_2
    //   1279: iconst_1
    //   1280: ishl
    //   1281: iaload
    //   1282: if_icmpge -> 1298
    //   1285: getstatic MainCanvas.mapOxy : [I
    //   1288: iload_2
    //   1289: aload_0
    //   1290: getfield poslimit : [I
    //   1293: iload_2
    //   1294: iconst_1
    //   1295: ishl
    //   1296: iaload
    //   1297: iastore
    //   1298: getstatic MainCanvas.mapOxy : [I
    //   1301: iload_2
    //   1302: iaload
    //   1303: bipush #16
    //   1305: iadd
    //   1306: bipush #8
    //   1308: ishl
    //   1309: getstatic MainCanvas.PlayerParam : [I
    //   1312: iconst_0
    //   1313: iaload
    //   1314: if_icmple -> 2464
    //   1317: getstatic MainCanvas.PlayerParam : [I
    //   1320: iconst_0
    //   1321: getstatic MainCanvas.mapOxy : [I
    //   1324: iload_2
    //   1325: iaload
    //   1326: bipush #16
    //   1328: iadd
    //   1329: bipush #8
    //   1331: ishl
    //   1332: iastore
    //   1333: getstatic MainCanvas.PlayerParam : [I
    //   1336: bipush #10
    //   1338: iaload
    //   1339: ifge -> 1363
    //   1342: getstatic MainCanvas.PlayerParam : [I
    //   1345: bipush #10
    //   1347: iconst_0
    //   1348: iastore
    //   1349: getstatic MainCanvas.PlayerParam : [I
    //   1352: bipush #13
    //   1354: iconst_0
    //   1355: iastore
    //   1356: getstatic MainCanvas.PlayerParam : [I
    //   1359: bipush #14
    //   1361: iconst_0
    //   1362: iastore
    //   1363: getstatic MainCanvas.PlayerJump : Z
    //   1366: ifne -> 1379
    //   1369: getstatic MainCanvas.PlayerBall : Z
    //   1372: ifeq -> 1379
    //   1375: iconst_0
    //   1376: putstatic MainCanvas.PlayerBall : Z
    //   1379: getstatic MainCanvas.PlayerJump : Z
    //   1382: ifeq -> 2464
    //   1385: getstatic MainCanvas.PlayerParam : [I
    //   1388: iconst_3
    //   1389: iaload
    //   1390: ifge -> 2464
    //   1393: getstatic MainCanvas.PlayerParam : [I
    //   1396: iconst_3
    //   1397: iconst_0
    //   1398: iastore
    //   1399: goto -> 2464
    //   1402: iload #5
    //   1404: getstatic MainCanvas.mapOxy : [I
    //   1407: iload_2
    //   1408: iaload
    //   1409: isub
    //   1410: iload #4
    //   1412: if_icmple -> 2464
    //   1415: aload_0
    //   1416: iconst_1
    //   1417: putfield ChkVecR : Z
    //   1420: getstatic MainCanvas.mapOxy : [I
    //   1423: iload_2
    //   1424: dup2
    //   1425: iaload
    //   1426: iload #4
    //   1428: iload #5
    //   1430: getstatic MainCanvas.mapOxy : [I
    //   1433: iload_2
    //   1434: iaload
    //   1435: isub
    //   1436: isub
    //   1437: isub
    //   1438: iastore
    //   1439: getstatic MainCanvas.mapOxy : [I
    //   1442: iload_2
    //   1443: iaload
    //   1444: iload #4
    //   1446: iconst_2
    //   1447: imul
    //   1448: iadd
    //   1449: aload_0
    //   1450: getfield poslimit : [I
    //   1453: iload_2
    //   1454: iconst_1
    //   1455: ishl
    //   1456: iconst_1
    //   1457: iadd
    //   1458: iaload
    //   1459: if_icmple -> 1482
    //   1462: getstatic MainCanvas.mapOxy : [I
    //   1465: iload_2
    //   1466: aload_0
    //   1467: getfield poslimit : [I
    //   1470: iload_2
    //   1471: iconst_1
    //   1472: ishl
    //   1473: iconst_1
    //   1474: iadd
    //   1475: iaload
    //   1476: iload #4
    //   1478: iconst_1
    //   1479: ishl
    //   1480: isub
    //   1481: iastore
    //   1482: aload_0
    //   1483: getfield limitBreak : Z
    //   1486: ifeq -> 1572
    //   1489: getstatic MainCanvas.mapOxy : [I
    //   1492: iload_2
    //   1493: iaload
    //   1494: bipush #96
    //   1496: iadd
    //   1497: sipush #240
    //   1500: iadd
    //   1501: bipush #8
    //   1503: ishl
    //   1504: getstatic MainCanvas.PlayerParam : [I
    //   1507: iconst_0
    //   1508: iaload
    //   1509: if_icmpge -> 2464
    //   1512: getstatic MainCanvas.PlayerParam : [I
    //   1515: iconst_0
    //   1516: getstatic MainCanvas.mapOxy : [I
    //   1519: iload_2
    //   1520: iaload
    //   1521: bipush #96
    //   1523: iadd
    //   1524: sipush #240
    //   1527: iadd
    //   1528: bipush #8
    //   1530: ishl
    //   1531: iastore
    //   1532: getstatic MainCanvas.PlayerParam : [I
    //   1535: bipush #10
    //   1537: iconst_0
    //   1538: iastore
    //   1539: getstatic MainCanvas.PlayerParam : [I
    //   1542: bipush #13
    //   1544: iconst_0
    //   1545: iastore
    //   1546: getstatic MainCanvas.PlayerParam : [I
    //   1549: bipush #14
    //   1551: iconst_0
    //   1552: iastore
    //   1553: getstatic MainCanvas.PlayerJump : Z
    //   1556: ifne -> 2464
    //   1559: getstatic MainCanvas.PlayerBall : Z
    //   1562: ifeq -> 2464
    //   1565: iconst_0
    //   1566: putstatic MainCanvas.PlayerBall : Z
    //   1569: goto -> 2464
    //   1572: getstatic MainCanvas.mapOxy : [I
    //   1575: iload_2
    //   1576: iaload
    //   1577: bipush #16
    //   1579: isub
    //   1580: sipush #240
    //   1583: iadd
    //   1584: bipush #8
    //   1586: ishl
    //   1587: getstatic MainCanvas.PlayerParam : [I
    //   1590: iconst_0
    //   1591: iaload
    //   1592: if_icmpge -> 2464
    //   1595: getstatic MainCanvas.PlayerParam : [I
    //   1598: iconst_0
    //   1599: getstatic MainCanvas.mapOxy : [I
    //   1602: iload_2
    //   1603: iaload
    //   1604: bipush #16
    //   1606: isub
    //   1607: sipush #240
    //   1610: iadd
    //   1611: bipush #8
    //   1613: ishl
    //   1614: iastore
    //   1615: getstatic MainCanvas.PlayerParam : [I
    //   1618: bipush #10
    //   1620: iconst_0
    //   1621: iastore
    //   1622: getstatic MainCanvas.PlayerParam : [I
    //   1625: bipush #13
    //   1627: iconst_0
    //   1628: iastore
    //   1629: getstatic MainCanvas.PlayerParam : [I
    //   1632: bipush #14
    //   1634: iconst_0
    //   1635: iastore
    //   1636: getstatic MainCanvas.PlayerJump : Z
    //   1639: ifne -> 2464
    //   1642: getstatic MainCanvas.PlayerBall : Z
    //   1645: ifeq -> 2464
    //   1648: iconst_0
    //   1649: putstatic MainCanvas.PlayerBall : Z
    //   1652: goto -> 2464
    //   1655: getstatic MainCanvas.bossModeOn : Z
    //   1658: ifeq -> 1717
    //   1661: aload_0
    //   1662: getfield zoneNumber : I
    //   1665: iconst_1
    //   1666: if_icmpne -> 1680
    //   1669: aload_0
    //   1670: getfield stageNumber : I
    //   1673: iconst_2
    //   1674: if_icmpne -> 1680
    //   1677: goto -> 1717
    //   1680: getstatic MainCanvas.mapOxy : [I
    //   1683: iload_2
    //   1684: iaload
    //   1685: iload_3
    //   1686: iadd
    //   1687: aload_0
    //   1688: getfield poslimit : [I
    //   1691: iload_2
    //   1692: iconst_2
    //   1693: imul
    //   1694: iconst_1
    //   1695: iadd
    //   1696: iaload
    //   1697: if_icmple -> 1717
    //   1700: getstatic MainCanvas.mapOxy : [I
    //   1703: iload_2
    //   1704: aload_0
    //   1705: getfield poslimit : [I
    //   1708: iload_2
    //   1709: iconst_2
    //   1710: imul
    //   1711: iconst_1
    //   1712: iadd
    //   1713: iaload
    //   1714: iload_3
    //   1715: isub
    //   1716: iastore
    //   1717: iload #5
    //   1719: getstatic MainCanvas.mapOxy : [I
    //   1722: iload_2
    //   1723: iaload
    //   1724: isub
    //   1725: iload #4
    //   1727: if_icmpeq -> 2464
    //   1730: iconst_0
    //   1731: istore #6
    //   1733: iload #5
    //   1735: getstatic MainCanvas.mapOxy : [I
    //   1738: iload_2
    //   1739: iaload
    //   1740: isub
    //   1741: iload #4
    //   1743: iload #6
    //   1745: isub
    //   1746: if_icmpge -> 2016
    //   1749: getstatic MainCanvas.mapOxy : [I
    //   1752: iload_2
    //   1753: dup2
    //   1754: iaload
    //   1755: iload #4
    //   1757: iload #6
    //   1759: isub
    //   1760: iload #5
    //   1762: getstatic MainCanvas.mapOxy : [I
    //   1765: iload_2
    //   1766: iaload
    //   1767: isub
    //   1768: isub
    //   1769: isub
    //   1770: iastore
    //   1771: aload_0
    //   1772: getfield zoneNumber : I
    //   1775: iconst_1
    //   1776: if_icmpne -> 1798
    //   1779: aload_0
    //   1780: getfield stageNumber : I
    //   1783: iconst_2
    //   1784: if_icmpne -> 1798
    //   1787: getstatic MainCanvas.mapOxy : [I
    //   1790: iconst_0
    //   1791: iaload
    //   1792: sipush #7936
    //   1795: if_icmplt -> 1814
    //   1798: aload_0
    //   1799: getfield zoneNumber : I
    //   1802: iconst_5
    //   1803: if_icmpne -> 1895
    //   1806: aload_0
    //   1807: getfield stageNumber : I
    //   1810: iconst_1
    //   1811: if_icmpne -> 1895
    //   1814: aload_0
    //   1815: getfield LookUpCount : I
    //   1818: ifgt -> 1895
    //   1821: aload_0
    //   1822: getfield CrouchCount : I
    //   1825: ifgt -> 1895
    //   1828: getstatic MainCanvas.mapOxy : [I
    //   1831: iload_2
    //   1832: iaload
    //   1833: aload_0
    //   1834: getfield poslimit : [I
    //   1837: iload_2
    //   1838: iconst_2
    //   1839: imul
    //   1840: iaload
    //   1841: if_icmpge -> 1924
    //   1844: getstatic MainCanvas.PlayerParam : [I
    //   1847: iconst_1
    //   1848: aload_0
    //   1849: getfield poslimit : [I
    //   1852: iload_2
    //   1853: iconst_2
    //   1854: imul
    //   1855: iconst_1
    //   1856: iadd
    //   1857: iaload
    //   1858: getstatic MainCanvas.mapOxy : [I
    //   1861: iload_2
    //   1862: iaload
    //   1863: iadd
    //   1864: iload #4
    //   1866: iadd
    //   1867: bipush #8
    //   1869: ishl
    //   1870: iastore
    //   1871: getstatic MainCanvas.mapOxy : [I
    //   1874: iload_2
    //   1875: aload_0
    //   1876: getfield poslimit : [I
    //   1879: iload_2
    //   1880: iconst_2
    //   1881: imul
    //   1882: iconst_1
    //   1883: iadd
    //   1884: iaload
    //   1885: getstatic MainCanvas.mapOxy : [I
    //   1888: iload_2
    //   1889: iaload
    //   1890: iadd
    //   1891: iastore
    //   1892: goto -> 1924
    //   1895: getstatic MainCanvas.mapOxy : [I
    //   1898: iload_2
    //   1899: iaload
    //   1900: aload_0
    //   1901: getfield poslimit : [I
    //   1904: iload_2
    //   1905: iconst_2
    //   1906: imul
    //   1907: iaload
    //   1908: if_icmpge -> 1924
    //   1911: getstatic MainCanvas.mapOxy : [I
    //   1914: iload_2
    //   1915: aload_0
    //   1916: getfield poslimit : [I
    //   1919: iload_2
    //   1920: iconst_2
    //   1921: imul
    //   1922: iaload
    //   1923: iastore
    //   1924: aload_0
    //   1925: getfield zoneNumber : I
    //   1928: iconst_1
    //   1929: if_icmpne -> 1972
    //   1932: aload_0
    //   1933: getfield stageNumber : I
    //   1936: ifne -> 1972
    //   1939: getstatic MainCanvas.PlayerParam : [I
    //   1942: iconst_1
    //   1943: iaload
    //   1944: sipush #8192
    //   1947: if_icmpge -> 1972
    //   1950: getstatic MainCanvas.PlayerParam : [I
    //   1953: iconst_1
    //   1954: sipush #8192
    //   1957: iastore
    //   1958: getstatic MainCanvas.PlayerParam : [I
    //   1961: iconst_5
    //   1962: iaload
    //   1963: ifge -> 1972
    //   1966: getstatic MainCanvas.PlayerParam : [I
    //   1969: iconst_5
    //   1970: iconst_0
    //   1971: iastore
    //   1972: aload_0
    //   1973: getfield zoneNumber : I
    //   1976: iconst_4
    //   1977: if_icmpne -> 2464
    //   1980: getstatic MainCanvas.PlayerParam : [I
    //   1983: iconst_1
    //   1984: iaload
    //   1985: sipush #8192
    //   1988: if_icmpge -> 2464
    //   1991: getstatic MainCanvas.PlayerParam : [I
    //   1994: iconst_1
    //   1995: sipush #8192
    //   1998: iastore
    //   1999: getstatic MainCanvas.PlayerParam : [I
    //   2002: iconst_5
    //   2003: iaload
    //   2004: ifge -> 2464
    //   2007: getstatic MainCanvas.PlayerParam : [I
    //   2010: iconst_5
    //   2011: iconst_0
    //   2012: iastore
    //   2013: goto -> 2464
    //   2016: iload #5
    //   2018: getstatic MainCanvas.mapOxy : [I
    //   2021: iload_2
    //   2022: iaload
    //   2023: isub
    //   2024: iload #4
    //   2026: if_icmple -> 2464
    //   2029: getstatic MainCanvas.mapOxy : [I
    //   2032: iload_2
    //   2033: dup2
    //   2034: iaload
    //   2035: iload #4
    //   2037: iload #5
    //   2039: getstatic MainCanvas.mapOxy : [I
    //   2042: iload_2
    //   2043: iaload
    //   2044: isub
    //   2045: isub
    //   2046: isub
    //   2047: iastore
    //   2048: aload_0
    //   2049: getfield zoneNumber : I
    //   2052: iconst_1
    //   2053: if_icmpne -> 2075
    //   2056: aload_0
    //   2057: getfield stageNumber : I
    //   2060: iconst_2
    //   2061: if_icmpne -> 2075
    //   2064: getstatic MainCanvas.mapOxy : [I
    //   2067: iconst_0
    //   2068: iaload
    //   2069: sipush #7936
    //   2072: if_icmplt -> 2091
    //   2075: aload_0
    //   2076: getfield zoneNumber : I
    //   2079: iconst_5
    //   2080: if_icmpne -> 2171
    //   2083: aload_0
    //   2084: getfield stageNumber : I
    //   2087: iconst_1
    //   2088: if_icmpne -> 2171
    //   2091: aload_0
    //   2092: getfield LookUpCount : I
    //   2095: ifgt -> 2171
    //   2098: aload_0
    //   2099: getfield CrouchCount : I
    //   2102: ifgt -> 2171
    //   2105: getstatic MainCanvas.mapOxy : [I
    //   2108: iload_2
    //   2109: iaload
    //   2110: aload_0
    //   2111: getfield poslimit : [I
    //   2114: iload_2
    //   2115: iconst_2
    //   2116: imul
    //   2117: iconst_1
    //   2118: iadd
    //   2119: iaload
    //   2120: if_icmple -> 2297
    //   2123: getstatic MainCanvas.mapOxy : [I
    //   2126: iload_2
    //   2127: getstatic MainCanvas.mapOxy : [I
    //   2130: iload_2
    //   2131: iaload
    //   2132: aload_0
    //   2133: getfield poslimit : [I
    //   2136: iload_2
    //   2137: iconst_2
    //   2138: imul
    //   2139: iconst_1
    //   2140: iadd
    //   2141: iaload
    //   2142: irem
    //   2143: iastore
    //   2144: getstatic MainCanvas.PlayerParam : [I
    //   2147: iconst_1
    //   2148: getstatic MainCanvas.PlayerParam : [I
    //   2151: iconst_1
    //   2152: iaload
    //   2153: aload_0
    //   2154: getfield poslimit : [I
    //   2157: iload_2
    //   2158: iconst_2
    //   2159: imul
    //   2160: iconst_1
    //   2161: iadd
    //   2162: iaload
    //   2163: bipush #8
    //   2165: ishl
    //   2166: irem
    //   2167: iastore
    //   2168: goto -> 2297
    //   2171: aload_0
    //   2172: getfield zoneNumber : I
    //   2175: iconst_1
    //   2176: if_icmpne -> 2198
    //   2179: aload_0
    //   2180: getfield stageNumber : I
    //   2183: iconst_2
    //   2184: if_icmpne -> 2198
    //   2187: getstatic MainCanvas.mapOxy : [I
    //   2190: iconst_0
    //   2191: iaload
    //   2192: sipush #7936
    //   2195: if_icmplt -> 2214
    //   2198: aload_0
    //   2199: getfield zoneNumber : I
    //   2202: iconst_5
    //   2203: if_icmpne -> 2260
    //   2206: aload_0
    //   2207: getfield stageNumber : I
    //   2210: iconst_1
    //   2211: if_icmpne -> 2260
    //   2214: aload_0
    //   2215: getfield CrouchCount : I
    //   2218: ifle -> 2224
    //   2221: goto -> 2297
    //   2224: getstatic MainCanvas.mapOxy : [I
    //   2227: iload_2
    //   2228: iaload
    //   2229: aload_0
    //   2230: getfield poslimit : [I
    //   2233: iload_2
    //   2234: iconst_2
    //   2235: imul
    //   2236: iconst_1
    //   2237: iadd
    //   2238: iaload
    //   2239: if_icmple -> 2297
    //   2242: getstatic MainCanvas.mapOxy : [I
    //   2245: iload_2
    //   2246: aload_0
    //   2247: getfield poslimit : [I
    //   2250: iload_2
    //   2251: iconst_2
    //   2252: imul
    //   2253: iconst_1
    //   2254: iadd
    //   2255: iaload
    //   2256: iastore
    //   2257: goto -> 2297
    //   2260: getstatic MainCanvas.mapOxy : [I
    //   2263: iload_2
    //   2264: iaload
    //   2265: iload_3
    //   2266: iadd
    //   2267: aload_0
    //   2268: getfield poslimit : [I
    //   2271: iload_2
    //   2272: iconst_2
    //   2273: imul
    //   2274: iconst_1
    //   2275: iadd
    //   2276: iaload
    //   2277: if_icmple -> 2297
    //   2280: getstatic MainCanvas.mapOxy : [I
    //   2283: iload_2
    //   2284: aload_0
    //   2285: getfield poslimit : [I
    //   2288: iload_2
    //   2289: iconst_2
    //   2290: imul
    //   2291: iconst_1
    //   2292: iadd
    //   2293: iaload
    //   2294: iload_3
    //   2295: isub
    //   2296: iastore
    //   2297: aload_0
    //   2298: getfield zoneNumber : I
    //   2301: iconst_1
    //   2302: if_icmpne -> 2313
    //   2305: aload_0
    //   2306: getfield stageNumber : I
    //   2309: iconst_2
    //   2310: if_icmpeq -> 2464
    //   2313: aload_0
    //   2314: getfield zoneNumber : I
    //   2317: iconst_5
    //   2318: if_icmpne -> 2332
    //   2321: aload_0
    //   2322: getfield stageNumber : I
    //   2325: iconst_1
    //   2326: if_icmpne -> 2332
    //   2329: goto -> 2464
    //   2332: aload_0
    //   2333: getfield zoneNumber : I
    //   2336: iconst_3
    //   2337: if_icmpne -> 2402
    //   2340: getstatic MainCanvas.mapOxy : [I
    //   2343: iload_2
    //   2344: iaload
    //   2345: sipush #168
    //   2348: iadd
    //   2349: bipush #8
    //   2351: ishl
    //   2352: getstatic MainCanvas.PlayerParam : [I
    //   2355: iload_2
    //   2356: iaload
    //   2357: if_icmpge -> 2402
    //   2360: getstatic MainCanvas.debugFlag : Z
    //   2363: ifeq -> 2369
    //   2366: goto -> 2464
    //   2369: getstatic MainCanvas.PlayerDie : Z
    //   2372: ifne -> 2464
    //   2375: getstatic MainCanvas.PlayerParam : [I
    //   2378: iload_2
    //   2379: getstatic MainCanvas.mapOxy : [I
    //   2382: iload_2
    //   2383: iaload
    //   2384: bipush #16
    //   2386: isub
    //   2387: sipush #240
    //   2390: iadd
    //   2391: bipush #8
    //   2393: ishl
    //   2394: iastore
    //   2395: aload_0
    //   2396: invokevirtual playerDie : ()V
    //   2399: goto -> 2464
    //   2402: getstatic MainCanvas.mapOxy : [I
    //   2405: iload_2
    //   2406: iaload
    //   2407: bipush #16
    //   2409: isub
    //   2410: sipush #240
    //   2413: iadd
    //   2414: bipush #8
    //   2416: ishl
    //   2417: getstatic MainCanvas.PlayerParam : [I
    //   2420: iload_2
    //   2421: iaload
    //   2422: if_icmpge -> 2464
    //   2425: getstatic MainCanvas.debugFlag : Z
    //   2428: ifeq -> 2434
    //   2431: goto -> 2464
    //   2434: getstatic MainCanvas.PlayerDie : Z
    //   2437: ifne -> 2464
    //   2440: getstatic MainCanvas.PlayerParam : [I
    //   2443: iload_2
    //   2444: getstatic MainCanvas.mapOxy : [I
    //   2447: iload_2
    //   2448: iaload
    //   2449: bipush #16
    //   2451: isub
    //   2452: sipush #240
    //   2455: iadd
    //   2456: bipush #8
    //   2458: ishl
    //   2459: iastore
    //   2460: aload_0
    //   2461: invokevirtual playerDie : ()V
    //   2464: getstatic MainCanvas.mapOxy : [I
    //   2467: iload_2
    //   2468: iaload
    //   2469: ifge -> 2478
    //   2472: getstatic MainCanvas.mapOxy : [I
    //   2475: iload_2
    //   2476: iconst_0
    //   2477: iastore
    //   2478: iinc #2, 1
    //   2481: goto -> 933
    //   2484: goto -> 2488
    //   2487: astore_2
    //   2488: return
    // Exception table:
    //   from	to	target	type
    //   0	930	2487	java/lang/Throwable
    //   931	2484	2487	java/lang/Throwable
  }
  
  public int hlimitget() {
    int i = MapH * 256 - 232;
    switch (this.zoneNumber) {
      case 0:
        i = zone1((byte)this.stageNumber);
        break;
      case 1:
        i = 1824;
        break;
      case 2:
        i = zone3((byte)this.stageNumber);
        break;
      case 3:
        i = zone4((byte)this.stageNumber);
        break;
      case 4:
        i = zone5((byte)this.stageNumber);
        break;
      case 5:
        i = zone6((byte)this.stageNumber);
        break;
    } 
    return i;
  }
  
  public int zone1(byte paramByte) {
    switch (paramByte) {
      case 0:
        c = 'Ѐ';
        if (6016 > mapOxy[0])
          c = '̀'; 
        return c;
      case 1:
        c = '̀';
        if (3792 > mapOxy[0]) {
          c = '̀';
        } else if (5632 > mapOxy[0]) {
          c = 'Ȁ';
        } else if (7520 > mapOxy[0]) {
          c = 'Ѐ';
        } 
        return c;
    } 
    char c = 'Ӏ';
    if (896 > mapOxy[0]) {
      c = '̀';
    } else if (2400 > mapOxy[0]) {
      c = '̐';
    } else if (640 > mapOxy[1]) {
      c = '̀';
    } else if (4992 <= mapOxy[0]) {
      c = 'Ѐ';
      if (5888 <= mapOxy[0])
        if (mapOxy[1] > 880) {
          if (6144 <= mapOxy[0]) {
            this.poslimit[1] = 6384;
            MapEndCounter = 1;
          } 
        } else {
          c = '̀';
        }  
    } 
    return c;
  }
  
  public int zone3(byte paramByte) {
    switch (paramByte) {
      case 0:
        if ((plsaveX != 0 || plsaveY != 0) && mapOxy[0] == 0 && mapOxy[1] == 0) {
          c = 'Ԁ';
        } else {
          c = 'Ѐ';
          if (PlayerPosX() > 3584 && PlayerPosX() < 4096 && PlayerPosY() > 1024 && PlayerPosY() < 1248) {
            c = 'Ԁ';
          } else if (3696 <= mapOxy[0]) {
            c = 'Ȑ';
            if (5168 > mapOxy[0])
              c = 'Ԁ'; 
          } else if (1792 > mapOxy[0]) {
            c = 'ǐ';
          } else if (2400 <= mapOxy[0] && 2752 > mapOxy[0]) {
            c = 'Ԁ';
            if (728 > mapOxy[1])
              c = 'Ƞ'; 
          } else {
            c = 'Ԁ';
            if (728 > mapOxy[1]) {
              c = '̀';
              if (3328 > mapOxy[0])
                c = 'Ƞ'; 
            } else if (1176 > mapOxy[1] && 3792 > mapOxy[0]) {
              c = '̀';
            } 
          } 
        } 
        return c;
      case 1:
        c = 'Ȁ';
        if (5888 > mapOxy[0])
          c = 'Ԡ'; 
        return c;
    } 
    char c = 'ܠ';
    if (5472 < mapOxy[0] && PlayerPosY() < 1280)
      c = 'Ȑ'; 
    return c;
  }
  
  public int zone4(byte paramByte) {
    byte b;
    null = 'ܠ';
    switch (paramByte) {
      case 0:
        return 1824;
      case 1:
        b = 112;
        return (MapH << 8) - 72 - 240 - 184 - b;
    } 
    char c = 'Ȑ';
    if (7936 > mapOxy[0])
      c = '܀'; 
    return c;
  }
  
  public int zone5(byte paramByte) {
    char c = 'ܠ';
    switch (paramByte) {
      case 0:
        return c;
      case 1:
        c = 'Ԡ';
        if (9632 <= mapOxy[0] && 1248 > PlayerPosY())
          c = 'Р'; 
    } 
    c = 'ӌ';
    if (11264 > mapOxy[0])
      c = 'ܠ'; 
  }
  
  public int zone6(byte paramByte) {
    switch (paramByte) {
      case 0:
        null = 'ʠ';
        if (6272 > mapOxy[0]) {
          null = 'ܠ';
        } else if (8192 > mapOxy[0]) {
          null = 'ؠ';
        } 
        return null;
      case 1:
        null = 'ὠ';
        if (6144 > mapOxy[0]) {
          null = 'ࠀ';
        } else if (7680 > mapOxy[0]) {
          null = 'ָ';
        } 
        return null;
    } 
    return 1824;
  }
  
  public void speedset(int paramInt) {
    playerBressChk();
    if (this.zoneNumber == 1 && this.waterH2 < PlayerPosY() - 12) {
      PlayerParam[0] = PlayerParam[0] + (plspeed[0] >> 1);
      PlayerParam[1] = PlayerParam[1] + (plspeed[1] >> 1);
    } else {
      if (plspeed[0] > 4096)
        plspeed[0] = 4096; 
      if (plspeed[0] < -4096)
        plspeed[0] = -4096; 
      if (plspeed[1] > 4096)
        plspeed[1] = 4096; 
      if (plspeed[1] < -4096)
        plspeed[1] = -4096; 
      PlayerParam[0] = PlayerParam[0] + plspeed[0];
      PlayerParam[1] = PlayerParam[1] + plspeed[1];
    } 
    if (paramInt == 1)
      if (falltimer <= 0) {
        if (this.zoneNumber == 1 && this.waterH2 < PlayerPosY() - 12) {
          PlayerParam[5] = PlayerParam[5] + gravity / 2;
        } else {
          PlayerParam[5] = PlayerParam[5] + gravity;
        } 
      } else {
        falltimer--;
      }  
    if (paramInt == 1 && PlayerBall) {
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
    } else if (Math.abs(plspeed[0]) + Math.abs(plspeed[1]) > plmaxspd) {
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
    } else {
      PlayerParam[11] = PlayerParam[11] + Math.abs(plspeed[0]) + Math.abs(plspeed[1]);
    } 
  }
  
  public boolean fallchk() {
    if (olddir <= 290 && olddir >= 70)
      if (olddir < 90 || olddir > 270) {
        if (Math.abs(PlayerParam[10]) < 640) {
          if (PlayerParam[10] < 0) {
            PlayerParam[12] = 1;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
          } else {
            PlayerParam[12] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
          } 
          int i = Math.abs(PlayerParam[10]);
          PlayerParam[5] = 280;
          PlayerParam[3] = -(dSin(olddir + 90) * i) / 100;
          PlayerJump = true;
          PlayerDamage = false;
          raidOn = false;
          this.noLeverTimer = 30;
          hcol();
        } 
      } else if (Math.abs(PlayerParam[10]) < 640) {
        if (PlayerParam[10] < 0) {
          PlayerParam[12] = 1;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } else {
          PlayerParam[12] = 0;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
        } 
        int i = PlayerParam[10];
        PlayerParam[5] = dCos(olddir + 90) * i / 100;
        if (olddir > 90 && olddir < 270)
          PlayerParam[5] = 0; 
        PlayerParam[3] = dSin(olddir + 90) * i / 100;
        PlayerJump = true;
        PlayerDamage = false;
        raidOn = false;
        this.nofcolTimer = 15;
        if (olddir == 90 || olddir == 270)
          this.nofcolTimer = 0; 
        hcol();
      }  
    return false;
  }
  
  public int PlayerPosX() {
    return PlayerParam[0] >> 8;
  }
  
  public int PlayerPosY() {
    return PlayerParam[1] >> 8;
  }
  
  public void playdamageset() {
    if (!debugFlag && !this.damageNow && mutekicount <= 0)
      if (bariacount > 0) {
        bariacount = 0;
        muteki2count = 60;
        PlayerDamage = true;
        PlayerJump = true;
        if (PlayerParam[12] == 1) {
          PlayerParam[3] = 512;
        } else {
          PlayerParam[3] = -512;
        } 
        PlayerParam[5] = -1024;
        PlayerParam[12] = (PlayerParam[12] + 1) % 2;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        this.damageNow = true;
      } else if (muteki2count <= 0) {
        if (ringcount <= 0) {
          playerDie();
        } else {
          Vibrate(1000);
          muteki2count = 60;
          PlayerDamage = true;
          PlayerJump = true;
          if (kyuryuchk()) {
            this.damageMoveTimer = 10;
            PlayerParam[3] = -512;
            PlayerParam[5] = -1024;
          } else {
            if (PlayerParam[12] == 1) {
              PlayerParam[3] = 512;
            } else {
              PlayerParam[3] = -512;
            } 
            PlayerParam[5] = -1024;
          } 
          PlayerParam[12] = (PlayerParam[12] + 1) % 2;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
          ShotRing(PlayerPosX(), PlayerPosY() - 12, ringcount);
          ringcount = 0;
          this.damageNow = true;
        } 
      }  
  }
  
  public void playdamageset2() {
    if (!debugFlag && !this.damageNow && mutekicount <= 0)
      if (bariacount > 0) {
        bariacount = 0;
        muteki2count = 60;
        PlayerDamage = true;
        PlayerJump = true;
        if (PlayerParam[12] == 1) {
          PlayerParam[3] = 512;
        } else {
          PlayerParam[3] = -512;
        } 
        PlayerParam[5] = -1024;
        PlayerParam[12] = (PlayerParam[12] + 1) % 2;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        this.damageNow = true;
      } else if (muteki2count <= 0) {
        if (ringcount <= 0) {
          playerDie();
        } else {
          Vibrate(1000);
          muteki2count = 60;
          PlayerDamage = true;
          PlayerJump = true;
          if (kyuryuchk()) {
            this.damageMoveTimer = 10;
            PlayerParam[3] = 512;
            PlayerParam[5] = 0;
          } else {
            if (PlayerParam[12] == 1) {
              PlayerParam[3] = 512;
            } else {
              PlayerParam[3] = -512;
            } 
            PlayerParam[5] = -1024;
          } 
          PlayerParam[12] = (PlayerParam[12] + 1) % 2;
          PlayerParam[13] = 0;
          PlayerParam[14] = 0;
          ShotRing(PlayerPosX(), PlayerPosY() - 12, ringcount);
          ringcount = 0;
          this.damageNow = true;
        } 
      }  
  }
  
  public void playerDie() {
    boolean bool = false;
    if (PlayerDie) {
      this.SetSoftFlag = true;
      this.SetSoftCount = 10;
      return;
    } 
    Vibrate(1000);
    TimerStop = true;
    this.bressCount = 2100;
    this.bressMusic = true;
    PlayerJump = true;
    PlayerDamage = false;
    PlayerBall = false;
    PlayerDie = true;
    this.PlayerSub = true;
    raidOn = false;
    PlayerParam[10] = 0;
    PlayerParam[13] = 0;
    PlayerParam[14] = 0;
    falltimer = 5;
    PlayerParam[3] = 0;
    PlayerParam[5] = dCos(bool) * pljump / 100;
    diecount = 120;
  }
  
  public void checkDieCount() {
    if (PlayerDie) {
      PlayerParam[3] = 0;
      diecount--;
      if (mapOxy[1] - 16 + 240 << 8 < PlayerParam[1] && this.PlayerSub) {
        this.PlayerSub = false;
        playercount--;
        if (playercount <= 0) {
          PlayMusic(21);
          diecount = 660;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          for (byte b = 0; b < m_nHiScore.length; b++) {
            if (m_nHiScore[b] < scorecount) {
              for (int i = m_nHiScore.length - 1; i > b; i--) {
                m_nHiScore[i] = m_nHiScore[i - 1];
                m_nDifficulty[i] = m_nDifficulty[i - 1];
              } 
              m_nHiScore[b] = scorecount;
              m_nDifficulty[b] = m_nConfigValue[0];
              save_hisc();
              break;
            } 
          } 
          scorecount = 0;
        } else if (timecount == 59 && timecount2 == 9) {
          diecount = 240;
          this.timeUpDie = true;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          plsaveTime = 0;
          plsaveTime2 = 0;
          this.noTimeScore = true;
        } 
      } 
      if (diecount < 0)
        if (playercount <= 0) {
          startContinue();
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
        } else {
          initStageStart();
        }  
    } 
  }
  
  public void playerRaidOn(int paramInt) {
    this.crushing[0] = true;
    if (this.damageNow || PlayerParam[5] < 0)
      return; 
    if (PlayerJump && PlayerBall && !PlayerAir)
      PlayerBall = false; 
    int i = raidObjectW - 8;
    if (i < 0) {
      i = 0;
    } else if (Math.abs(PlayerPosX() - raidObjectX) > i) {
      OttotoOn = true;
      OttotoSide = 0;
      if (PlayerPosX() - raidObjectX > 0)
        OttotoSide = 1; 
    } 
    this.playdamageYogan = false;
    PlayerParam[8] = 0;
    PlayerParam[5] = 0;
    if (PlayerJump) {
      PlayerParam[10] = PlayerParam[3];
      if (PlayerParam[10] < 0)
        PlayerParam[13] = 1; 
      if (PlayerParam[10] > 0)
        PlayerParam[13] = 2; 
      PlayerParam[14] = 0;
    } 
    PlayerParam[3] = 0;
    raidOn = true;
    raidObjectNum = paramInt;
    olddir = 0;
    PlayerJump = false;
    PlayerSJump = false;
    PlayerWater = false;
    comboScore = 0;
  }
  
  public byte getPlayerArg2(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      paramInt1 = 0; 
    if (paramInt2 < 0)
      paramInt2 = 0; 
    int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
    if (this.hitChk[i >> 1] == 1)
      return -1; 
    int j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
    return (byte)blockdirChk(j);
  }
  
  public int getPlayerArg(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      paramInt1 = 0; 
    if (paramInt2 < 0)
      paramInt2 = 0; 
    int i = (tempWorldMapData[(paramInt2 >> 4 >> 4) % MapH][paramInt1 >> 4 >> 4] << 9) + ((paramInt1 >> 4 & 0xF) + ((paramInt2 >> 4 & 0xF) << 4) << 1) + 1;
    if (this.hitChk[i >> 1] == 1)
      return -1; 
    int j = (mapData[i] & 0xFF) + (this.imageOffset[i >> 1] << 8);
    int k = blockdirChk(j) * 360 / 255;
    if (this.rot[i >> 1] == 1) {
      k = 360 - k;
    } else if (this.rot[i >> 1] == 2) {
      k = 540 - k;
    } else if (this.rot[i >> 1] == 3) {
      k = 540 - k;
      k %= 360;
      k = 360 - k;
    } 
    if (k % 90 == 0) {
      if (olddir == 62)
        return 90; 
      k = Math.abs((olddir - 22 + 45) / 90) * 90;
      if (olddir == 44)
        k = 0; 
    } 
    return k % 360;
  }
  
  public void drawPlayerImage(Graphics paramGraphics) {
    if (this.playerDraw)
      return; 
    int i = rotNumTable[TRANS_NONE];
    int j = (540 - olddir) % 360;
    if (PlayerParam[12] == 1)
      i = rotNumTable[TRANS_MIRROR]; 
    PlayerParam[11] = PlayerParam[11] % 92160;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    if (PlayerSJump) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 240, 80, 40, 45, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
    } else if (PlayerDamage && PlayerJump) {
      if (PlayerParam[12] == 1) {
        i = rotNumTable[TRANS_MIRROR - 4];
      } else {
        i = rotNumTable[TRANS_MIRROR];
      } 
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 160, 80, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1] - 5, 0x1 | 0x20);
    } else if (kokyutimer > 0) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 352, 0, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      kokyutimer--;
    } else if (PlayerBou) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 144 + 48 * (this.animeTimer >> 1) % 2, 128, 49, 32, rotNumTable[TRANS_NONE + 4], PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      PlayerBou = false;
    } else if (PlayerSWater) {
      if ((this.animeTimer >> 1) % 5 < 3) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 48 * (this.animeTimer >> 1) % 5, 120, 45, 24, rotNumTable[TRANS_NONE], PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 240 - 48 * (this.animeTimer >> 1) % 5, 120, 45, 24, rotNumTable[TRANS_NONE + 4], PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } 
      PlayerSWater = false;
    } else if (PlayerBall && PlayerJump) {
      k = 0;
      if (PlayerJump && olddir == 270)
        k -= true; 
      n = -24 + dSin(j) * 24 / 100;
      i1 = -24 - dCos(j) * 24 / 100;
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 192 + PlayerParam[11] / plmaxspd / 4 % 5 * 32, 0, 32, 40, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1], 0x1 | 0x20);
    } else if (PlayerWater) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 120 + 40 * this.animeTimer / 2 % 2, 80, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1] - 5, 0x1 | 0x20);
    } else if (PlayerDie) {
      if (this.bressDie) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 280, 80, 40, 44, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 200, 80, 40, 44, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } 
    } else if (PlayerBall && !PlayerJump) {
      k = -16 + dSin(j) * 16 / 100;
      m = -15 - dCos(j) * 15 / 100;
      n = -24 + dSin(j) * 24 / 100;
      i1 = -24 - dCos(j) * 24 / 100;
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 192 + PlayerParam[11] / plmaxspd / 4 % 5 * 32, 10, 32, 30, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1] + m, 20);
    } else if (PlayerCrouch) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 0, 80, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      PlayerCrouch = false;
    } else if (PlayerLookUp) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 160, 0, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      PlayerLookUp = false;
    } else if (this.pushCount > 0) {
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 288 + this.animeTimer / 4 % 4 * 32, 120, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
    } else if (PlayerParam[10] == 0 && !PlayerJump) {
      if (OttotoOn) {
        if (OttotoSide == 1) {
          drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 40 + this.playerStandCount / 8 % 2 * 40, 80, 40, 40, rotNumTable[TRANS_NONE], PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
        } else {
          drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 40 + this.playerStandCount / 8 % 2 * 40, 80, 40, 40, rotNumTable[TRANS_MIRROR], PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
        } 
      } else if (!raidOn && (olddir <= 22 || olddir >= 338) && ((!blockColChk_easy(PlayerPosX() + 12, PlayerPosY()) && !blockColChk_easy(PlayerPosX() + 12, PlayerPosY() + 16)) || (!blockColChk_easy(PlayerPosX() - 12, PlayerPosY()) && !blockColChk_easy(PlayerPosX() - 12, PlayerPosY() + 16)))) {
        if (!blockColChk_easy(PlayerPosX() + 12, PlayerPosY()) && !blockColChk_easy(PlayerPosX() + 12, PlayerPosY() + 16)) {
          i = rotNumTable[TRANS_NONE];
        } else {
          i = rotNumTable[TRANS_MIRROR];
        } 
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 40 + this.playerStandCount / 8 % 2 * 40, 80, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else if (this.playerStandCount < 75) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 0, 0, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else if (this.playerStandCount < 90) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 32, 0, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else if (this.playerStandCount < 105) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 64, 0, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } else {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 96 + this.playerStandCount / 8 % 2 * 32, 0, 32, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
      } 
    } else if (!PlayerJump && ((PlayerParam[14] == 2 && PlayerParam[10] > 0 && PlayerParam[12] == 1) || (PlayerParam[14] == 1 && PlayerParam[10] < 0 && PlayerParam[12] == 0))) {
      i = rotNumTable[TRANS_NONE];
      if (PlayerParam[12] == 0)
        i = rotNumTable[TRANS_MIRROR]; 
      drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 360 + PlayerParam[11] / plmaxspd / 4 % 2 * 40, 80, 40, 40, i, PlayerPosX() - mapView[0], PlayerPosY() - mapView[1], 0x1 | 0x20);
    } else if (Math.abs(PlayerParam[10]) >= plmaxspd || PlayerDush) {
      int i2 = 0;
      k = -20 + dSin(j) * 20 / 100;
      m = -20 - dCos(j) * 20 / 100;
      n = -24 + dSin(j) * 24 / 100;
      i1 = -24 - dCos(j) * 24 / 100;
      if (PlayerJump && olddir != 0)
        k += dCos(olddir) * 20 / 100; 
      if (PlayerParam[12] == 1) {
        i = rotNumTable[TRANS_MIRROR];
        int[] arrayOfInt = { rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT180], rotNumTable[TRANS_MIRROR_ROT180] };
        byte b;
        for (b = 0; b < 7 && (b * 45 + 23 >= j || (b + 1) * 45 + 23 < j); b++);
        i = arrayOfInt[b];
        i2 = b % 2;
      } else {
        int[] arrayOfInt = { rotNumTable[TRANS_ROT180], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_NONE], rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT180] };
        byte b;
        for (b = 0; b < 7 && (b * 45 + 23 >= j || (b + 1) * 45 + 23 < j); b++);
        i = arrayOfInt[b];
        i2 = b % 2;
      } 
      if (i2 == 1) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], 240 + PlayerParam[11] / plmaxspd / 4 % 4 * 40, 40, 40, 40, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1] + m, 20);
      } else {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_S], 240 + PlayerParam[11] / plmaxspd / 4 % 4 * 40, 0, 40, 42, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1] + m, 20);
      } 
    } else {
      int i2 = 0;
      k = -20 + dSin(j) * 20 / 100;
      m = -20 - dCos(j) * 20 / 100;
      n = -24 + dSin(j) * 24 / 100;
      i1 = -24 - dCos(j) * 24 / 100;
      if (PlayerJump && olddir != 0)
        k += dCos(olddir) * 20 / 100; 
      if (PlayerParam[12] == 1) {
        i = rotNumTable[TRANS_MIRROR];
        int[] arrayOfInt = { rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT180], rotNumTable[TRANS_MIRROR_ROT180] };
        byte b;
        for (b = 0; b < 7 && (b * 45 + 23 >= j || (b + 1) * 45 + 23 < j); b++);
        i = arrayOfInt[b];
        i2 = b % 2;
      } else {
        int[] arrayOfInt = { rotNumTable[TRANS_ROT180], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_NONE], rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT180] };
        byte b;
        for (b = 0; b < 7 && (b * 45 + 23 >= j || (b + 1) * 45 + 23 < j); b++);
        i = arrayOfInt[b];
        i2 = b % 2;
      } 
      if (i2 == 1) {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_N], PlayerParam[11] / plmaxspd / 4 % 6 * 40, 40, 40, 40, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1] + m, 20);
      } else {
        drawRegion(paramGraphics, this.m_imgCmd[SONIC_S], PlayerParam[11] / plmaxspd / 4 % 6 * 40, 0, 40, 42, i, PlayerPosX() - mapView[0] + k, PlayerPosY() - mapView[1] + m, 20);
      } 
    } 
    PlayerDush = false;
    if (this.pushCount > 0)
      this.pushCount--; 
    if (PlayerParam[10] == 0 && !PlayerJump) {
      this.playerStandCount++;
    } else {
      this.playerStandCount = 0;
    } 
    if (mutekicount > 0) {
      if (this.animeTimer % 2 == 0)
        if (k != 0 || m != 0) {
          setObjData(PlayerPosX() + n + 24, PlayerPosY() + i1 + 24, 0);
        } else {
          setObjData(PlayerPosX(), PlayerPosY() - 18, 0);
        }  
    } else if (bariacount > 0) {
      if (k != 0 || m != 0) {
        if (this.animeTimer % 3 == 2) {
          drawRegion(gg, this.m_imgObj[109], 48, 48, 48, 48, rotNumTable[TRANS_NONE + 4], PlayerPosX() - mapView[0] + n, PlayerPosY() - mapView[1] + i1, 20);
        } else {
          drawRegion(gg, this.m_imgObj[109], 48, 48 * this.animeTimer % 3, 48, 48, rotNumTable[TRANS_NONE], PlayerPosX() - mapView[0] + n, PlayerPosY() - mapView[1] + i1, 20);
        } 
      } else if (this.animeTimer % 3 == 2) {
        drawRegion(gg, this.m_imgObj[109], 48, 48, 48, 48, rotNumTable[TRANS_NONE + 4], PlayerPosX() - mapView[0], PlayerPosY() - 18 - mapView[1], 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[109], 48, 48 * this.animeTimer % 3, 48, 48, rotNumTable[TRANS_NONE], PlayerPosX() - mapView[0], PlayerPosY() - 18 - mapView[1], 0x1 | 0x2);
      } 
    } 
  }
  
  private void setObjData(int paramInt1, int paramInt2, int paramInt3) {
    for (byte b = 0; b < objData.length; b++) {
      if (objData[b][0] == 0) {
        objData[b][0] = 1;
        objData[b][1] = paramInt3;
        objData[b][2] = paramInt1;
        objData[b][3] = paramInt2;
        objData[b][5] = 0;
        break;
      } 
    } 
  }
  
  private void moveObjData() {
    for (byte b = 0; b < objData.length; b++) {
      if (objData[b][0] == 1 && objData[b][1] == 0) {
        objData[b][5] = objData[b][5] + 1;
        if (objData[b][5] > 60) {
          objData[b][5] = 0;
          objData[b][0] = 0;
        } 
      } 
    } 
  }
  
  private void drawObjData() {
    for (byte b = 0; b < objData.length; b++) {
      if (objData[b][0] == 1 && objData[b][1] == 0)
        drawRegion(gg, this.m_imgObj[109], 0, 48 * objData[b][5] / 4 % 2, 48, 48, rotNumTable[TRANS_NONE], objData[b][2] - mapView[0], objData[b][3] - mapView[1], 0x1 | 0x2); 
    } 
  }
  
  public void objectInit(int paramInt) {
    int i = 0;
    if (paramInt > 3)
      paramInt = 3; 
    try {
      byte b2 = 0;
      byte b1;
      for (b1 = 0; b1 < (zoneActTable[paramInt]).length / 7; b1++) {
        if (0 == zoneActTable[paramInt][b1 * 7 + 5] || 1 == zoneActTable[paramInt][b1 * 7 + 5] || 63 == zoneActTable[paramInt][b1 * 7 + 5] || 64 == zoneActTable[paramInt][b1 * 7 + 5] || 65 == zoneActTable[paramInt][b1 * 7 + 5] || 66 == zoneActTable[paramInt][b1 * 7 + 5] || 67 == zoneActTable[paramInt][b1 * 7 + 5] || 68 == zoneActTable[paramInt][b1 * 7 + 5] || 69 == zoneActTable[paramInt][b1 * 7 + 5]) {
          i = ++i + zoneActTable[paramInt][b1 * 7 + 6];
        } else {
          i++;
        } 
      } 
      zoneActTable2 = new int[i];
      ObjectAct = new boolean[i + 20];
      ObjectDead = new boolean[i + 20];
      i = 0;
      for (b1 = 0; b1 < (zoneActTable[paramInt]).length / 7; b1++) {
        zoneActTable2[b1] = i;
        if (0 == zoneActTable[paramInt][b1 * 7 + 5] || 1 == zoneActTable[paramInt][b1 * 7 + 5] || 63 == zoneActTable[paramInt][b1 * 7 + 5] || 64 == zoneActTable[paramInt][b1 * 7 + 5] || 65 == zoneActTable[paramInt][b1 * 7 + 5] || 66 == zoneActTable[paramInt][b1 * 7 + 5] || 67 == zoneActTable[paramInt][b1 * 7 + 5] || 68 == zoneActTable[paramInt][b1 * 7 + 5] || 69 == zoneActTable[paramInt][b1 * 7 + 5]) {
          for (b2 = 0; b2 < zoneActTable[paramInt][b1 * 7 + 6] + 1; b2++)
            i++; 
        } else {
          i++;
        } 
      } 
    } catch (Throwable throwable) {}
  }
  
  public void addObjectChk() {
    if (this.endingModeOn)
      return; 
    if (this.zoneNumber == 5 && this.stageNumber == 3)
      return; 
    try {
      if (this.ChkVecR) {
        if (this.objChkPoint < this.objChkNum) {
          this.objChkNum = this.objChkPoint;
        } else {
          this.objChkPoint = this.objChkNum;
        } 
        while (true) {
          int i = (zoneActTable[this.stageNumber][this.objChkNum * 7 + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][this.objChkNum * 7 + 1] & 0xFF;
          if (i - mapOxy[0] > this.RSize) {
            this.ChkVecR = false;
            break;
          } 
          if (!ObjectAct[zoneActTable2[this.objChkNum]])
            addObjectSet(this.objChkNum); 
          this.objChkNum++;
          if (this.objChkNum >= (zoneActTable[this.stageNumber]).length / 7) {
            this.objChkNum = (zoneActTable[this.stageNumber]).length / 7 - 1;
            this.ChkVecR = false;
            break;
          } 
        } 
        while (true) {
          int i = (zoneActTable[this.stageNumber][this.objChkPoint * 7 + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][this.objChkPoint * 7 + 1] & 0xFF;
          if (i - mapOxy[0] < this.LSize) {
            this.objChkPoint++;
            if (this.objChkPoint >= (zoneActTable[this.stageNumber]).length / 7) {
              this.objChkPoint = (zoneActTable[this.stageNumber]).length / 7 - 1;
              break;
            } 
            continue;
          } 
          break;
        } 
      } else if (this.ChkVecL) {
        if (this.objChkPoint > this.objChkNum) {
          this.objChkNum = this.objChkPoint;
        } else {
          this.objChkPoint = this.objChkNum;
        } 
        while (true) {
          int i = (zoneActTable[this.stageNumber][this.objChkNum * 7 + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][this.objChkNum * 7 + 1] & 0xFF;
          if (i - mapOxy[0] < this.LSize) {
            this.ChkVecL = false;
            break;
          } 
          if (!ObjectAct[zoneActTable2[this.objChkNum]])
            addObjectSet(this.objChkNum); 
          this.objChkNum--;
          if (this.objChkNum < 0) {
            this.objChkNum = 0;
            this.ChkVecL = false;
            break;
          } 
        } 
        while (true) {
          int i = (zoneActTable[this.stageNumber][this.objChkPoint * 7 + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][this.objChkPoint * 7 + 1] & 0xFF;
          if (i - mapOxy[0] > this.RSize) {
            this.objChkPoint--;
            if (this.objChkPoint < 0) {
              this.objChkPoint = 0;
              break;
            } 
            continue;
          } 
          break;
        } 
      } 
    } catch (Throwable throwable) {
      this.ChkVecR = false;
    } 
  }
  
  public void addObjectSet(int paramInt) {
    int i = 1;
    int j = paramInt * 7;
    int k = zoneActTable[this.stageNumber][paramInt * 7 + 5] & 0xFF;
    if (0 == k || 1 == k || 63 == k || 64 == k || 65 == k || 66 == k || 67 == k || 68 == k || 69 == k) {
      i += zoneActTable[this.stageNumber][j + 6];
      int m = (zoneActTable[this.stageNumber][j + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 1] & 0xFF;
      int n = (zoneActTable[this.stageNumber][j + 2] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 3] & 0xFF;
      if (i != 1) {
        switch (zoneActTable[this.stageNumber][j + 5]) {
          case 0:
            m += (i - 1) * 24;
            break;
          case 1:
            n += (i - 1) * 24;
            break;
          case 63:
            m -= (i - 1) * 16;
            n += (i - 1) * 16;
            break;
          case 64:
            m += (i - 1) * 16;
            n += (i - 1) * 16;
            break;
          case 65:
            m += (i - 1) * 32;
            n += (i - 1) * 32;
            break;
          case 66:
            m += (i - 1) * 16;
            break;
          case 67:
            m += (i - 1) * 32;
            break;
          case 68:
            n += (i - 1) * 16;
            break;
          case 69:
            n += (i - 1) * 32;
            break;
        } 
        if (m - mapOxy[0] < this.LSize)
          return; 
        if (m - mapOxy[0] > this.RSize)
          return; 
      } 
    } else if (k == 41 || k == 86 || k == 87 || k == 81 || k == 57 || k == 78 || k == 40 || k == 70 || k == 39 || k == 74 || k == 49 || k == 50 || k == 71 || k == 51 || k == 10 || k == 35) {
      int m = (zoneActTable[this.stageNumber][j + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 1] & 0xFF;
      if (m - mapOxy[0] > -48 && m - mapOxy[0] < 288)
        return; 
    } 
    for (byte b = 0; b < i; b++) {
      int[] arrayOfInt = new int[25];
      if (!ObjectDead[zoneActTable2[paramInt] + b] && !ObjectAct[zoneActTable2[paramInt] + b]) {
        arrayOfInt[0] = 1;
        arrayOfInt[1] = zoneActTable[this.stageNumber][j + 5] & 0xFF;
        arrayOfInt[2] = (zoneActTable[this.stageNumber][j + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 1] & 0xFF;
        arrayOfInt[3] = (zoneActTable[this.stageNumber][j + 2] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 3] & 0xFF;
        arrayOfInt[8] = (zoneActTable[this.stageNumber][j + 0] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 1] & 0xFF;
        arrayOfInt[9] = (zoneActTable[this.stageNumber][j + 2] & 0xFF) << 8 | zoneActTable[this.stageNumber][j + 3] & 0xFF;
        if (i != 1)
          switch (zoneActTable[this.stageNumber][j + 5]) {
            case 0:
              arrayOfInt[2] = arrayOfInt[2] + b * 24;
              arrayOfInt[8] = arrayOfInt[8] + b * 24;
              break;
            case 1:
              arrayOfInt[3] = arrayOfInt[3] + b * 24;
              arrayOfInt[9] = arrayOfInt[9] + b * 24;
              break;
            case 63:
              arrayOfInt[2] = arrayOfInt[2] - b * 16;
              arrayOfInt[8] = arrayOfInt[8] - b * 16;
              arrayOfInt[3] = arrayOfInt[3] + b * 16;
              arrayOfInt[9] = arrayOfInt[9] + b * 16;
              break;
            case 64:
              arrayOfInt[2] = arrayOfInt[2] + b * 16;
              arrayOfInt[8] = arrayOfInt[8] + b * 16;
              arrayOfInt[3] = arrayOfInt[3] + b * 16;
              arrayOfInt[9] = arrayOfInt[9] + b * 16;
              break;
            case 65:
              arrayOfInt[2] = arrayOfInt[2] + b * 32;
              arrayOfInt[8] = arrayOfInt[8] + b * 32;
              arrayOfInt[3] = arrayOfInt[3] + b * 32;
              arrayOfInt[9] = arrayOfInt[9] + b * 32;
              break;
            case 66:
              arrayOfInt[2] = arrayOfInt[2] + b * 16;
              arrayOfInt[8] = arrayOfInt[8] + b * 16;
              break;
            case 67:
              arrayOfInt[2] = arrayOfInt[2] + b * 32;
              arrayOfInt[8] = arrayOfInt[8] + b * 32;
              break;
            case 68:
              arrayOfInt[3] = arrayOfInt[3] + b * 16;
              arrayOfInt[9] = arrayOfInt[9] + b * 16;
              break;
            case 69:
              arrayOfInt[3] = arrayOfInt[3] + b * 32;
              arrayOfInt[9] = arrayOfInt[9] + b * 32;
              break;
          }  
        arrayOfInt[4] = zoneActTable[this.stageNumber][j + 6] & 0xFF;
        arrayOfInt[19] = zoneActTable[this.stageNumber][j + 4] & 0xFF;
        arrayOfInt[20] = zoneActTable2[paramInt] + b;
        arrayOfInt[22] = zoneActTable2[paramInt];
        if (m_nConfigValue[0] == 0) {
          if (arrayOfInt[1] == 41 || arrayOfInt[1] == 86 || arrayOfInt[1] == 87 || arrayOfInt[1] == 81 || arrayOfInt[1] == 57 || arrayOfInt[1] == 78 || arrayOfInt[1] == 40 || arrayOfInt[1] == 70 || arrayOfInt[1] == 39 || arrayOfInt[1] == 74 || arrayOfInt[1] == 49 || arrayOfInt[1] == 50 || arrayOfInt[1] == 71 || arrayOfInt[1] == 51)
            return; 
        } else if (m_nConfigValue[0] == 1 && (arrayOfInt[1] == 40 || arrayOfInt[1] == 70 || arrayOfInt[1] == 39 || (arrayOfInt[1] == 74 && arrayOfInt[4] == 0) || arrayOfInt[1] == 49 || arrayOfInt[1] == 50 || arrayOfInt[1] == 71)) {
          return;
        } 
        ObjectAct[zoneActTable2[paramInt] + b] = true;
        if (arrayOfInt[1] == 0 && this.zoneNumber == 5 && this.stageNumber == 1 && 6144 > arrayOfInt[2] && arrayOfInt[3] < 256) {
          arrayOfInt[11] = 1;
          arrayOfInt[12] = arrayOfInt[3] + 2048;
        } 
        addObject(arrayOfInt);
      } 
    } 
  }
  
  public void addObject(int[] paramArrayOfint) {
    try {
      if (paramArrayOfint[1] == 9 || paramArrayOfint[1] == 15 || paramArrayOfint[1] == 48 || paramArrayOfint[1] == 11 || paramArrayOfint[1] == 2 || paramArrayOfint[1] == 79 || 0 == paramArrayOfint[1] || 1 == paramArrayOfint[1] || 63 == paramArrayOfint[1] || 64 == paramArrayOfint[1] || 65 == paramArrayOfint[1] || 66 == paramArrayOfint[1] || 67 == paramArrayOfint[1] || 68 == paramArrayOfint[1] || 69 == paramArrayOfint[1]) {
        for (int i = ObjectList.length - 1; i > 0; i--) {
          if (ObjectList[i][24] == 0) {
            ObjectList[i] = paramArrayOfint;
            ObjectList[i][24] = 1;
            if (this.noDataPointer != i) {
              ObjectListNum++;
              break;
            } 
            ObjectListNum++;
            for (i = 0; i < ObjectList.length; i++) {
              if (ObjectList[i][24] == 0) {
                this.noDataPointer = i;
                break;
              } 
            } 
            break;
          } 
        } 
      } else if (ObjectList[this.noDataPointer][24] == 0) {
        ObjectList[this.noDataPointer] = paramArrayOfint;
        ObjectList[this.noDataPointer][24] = 1;
        ObjectListNum++;
        for (byte b = 0; b < ObjectList.length; b++) {
          if (ObjectList[b][24] == 0) {
            this.noDataPointer = b;
            break;
          } 
        } 
      } else {
        byte b;
        for (b = 0; b < ObjectList.length; b++) {
          if (ObjectList[b][24] == 0) {
            this.noDataPointer = b;
            break;
          } 
        } 
        ObjectList[this.noDataPointer] = paramArrayOfint;
        ObjectList[this.noDataPointer][24] = 1;
        ObjectListNum++;
        for (b = 0; b < ObjectList.length; b++) {
          if (ObjectList[b][24] == 0) {
            this.noDataPointer = b;
            break;
          } 
        } 
      } 
    } catch (Throwable throwable) {}
  }
  
  public boolean removeObjectChk(int[] paramArrayOfint) {
    return (paramArrayOfint[2] - mapOxy[0] < this.LSize) ? true : ((paramArrayOfint[2] - mapOxy[0] > this.RSize));
  }
  
  public boolean removeObjectChkDead(int[] paramArrayOfint) {
    return (paramArrayOfint[0] <= 0);
  }
  
  public void removeObject(int paramInt) {
    this.noDataPointer = paramInt;
    ObjectList[paramInt][24] = 0;
    this.listSub--;
  }
  
  public void InsertObject(int[] paramArrayOfint, int paramInt) {
    ObjectList[paramInt] = paramArrayOfint;
  }
  
  public int[][] searchObject(int paramInt1, int paramInt2) {
    this.objCount = 0;
    byte b1 = 0;
    for (byte b2 = 0; b2 < ObjectList.length && b1 < ObjectListNum; b2++) {
      if (ObjectList[b2][24] == 1) {
        b1++;
        if (ObjectList[b2][1] == paramInt1 && (paramInt2 < 0 || paramInt2 == ObjectList[b2][4])) {
          this.objTempData[this.objCount] = ObjectList[b2];
          this.objTempData[this.objCount][23] = b2;
          this.objCount++;
        } 
      } 
    } 
    int[][] arrayOfInt = new int[this.objCount][25];
    System.arraycopy(this.objTempData, 0, arrayOfInt, 0, arrayOfInt.length);
    return arrayOfInt;
  }
  
  public void objectAction() {
    this.listSub = 0;
    byte b1 = 0;
    for (byte b2 = 0; b2 < ObjectList.length && b1 < ObjectListNum; b2++) {
      if (ObjectList[b2][24] == 1) {
        b1++;
        objectData = ObjectList[b2];
        CallObjectMove(0);
        ObjectList[b2] = objectData;
        if (objectData[1] != 17 || objectData[4] != 55)
          if (objectData[0] <= 0 && objectData[21] == 0) {
            ObjectDead[objectData[20]] = true;
            ObjectAct[objectData[20]] = false;
            removeObject(b2);
          } else if (!ObjectAct[objectData[20]] && objectData[1] < 120 && objectData[1] != 42 && objectData[1] != 43) {
            removeObject(b2);
          } else if (removeObjectChk(objectData) && objectData[21] == 0 && objectData[1] < 120 && objectData[1] != 42 && objectData[1] != 43) {
            ObjectAct[objectData[20]] = false;
            ObjectAct[objectData[22]] = false;
            removeObject(b2);
          }  
      } 
    } 
    ObjectListNum += this.listSub;
  }
  
  public void CallObjectDraw() {
    try {
      byte b = 0;
      for (int i = ObjectList.length - 1; i >= 0 && b < ObjectListNum; i--) {
        if (ObjectList[i][24] == 1) {
          b++;
          if (ObjectList[i][1] != 45 && ObjectList[i][1] != 45 && ObjectList[i][1] != 26 && ObjectList[i][1] != 53 && ObjectList[i][1] != 10 && (ObjectList[i][1] != 88 || ObjectList[i][4] != 127)) {
            objectData = ObjectList[i];
            CallObjectDraw(0);
          } 
        } 
      } 
    } catch (Throwable throwable) {}
  }
  
  public void CallObjectDrawFront() {
    try {
      byte b = 0;
      for (int i = ObjectList.length - 1; i >= 0 && b < ObjectListNum; i--) {
        if (ObjectList[i][24] == 1) {
          b++;
          if (ObjectList[i][1] == 45 || ObjectList[i][1] == 45 || ObjectList[i][1] == 26 || ObjectList[i][1] == 53 || ObjectList[i][1] == 10 || (ObjectList[i][1] == 88 && ObjectList[i][4] == 127)) {
            objectData = ObjectList[i];
            CallObjectDraw(0);
          } 
        } 
      } 
    } catch (Throwable throwable) {}
  }
  
  public void loopchange() {
    if (noloopchecktimer-- > 0)
      return; 
    boolean bool = false;
    if (ploldpos[0] >> 8 < PlayerPosX() >> 8) {
      if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 54))
        tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 53; 
      if (this.zoneNumber == 3) {
        if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 42 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 43)
          tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 42; 
        if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53)
          tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 52; 
      } 
    } 
    if (ploldpos[0] >> 8 > PlayerPosX() >> 8) {
      if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 54))
        tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 54; 
      if (this.zoneNumber == 3) {
        if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 42 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 43)
          tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 43; 
        if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53)
          tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 53; 
      } 
    } 
    if (this.zoneNumber == 3 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53) && PlayerJump)
      tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = 52; 
    int i = tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] & 0x1;
    if (this.zoneNumber == 0 && (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 54))
      bool = true; 
    if (this.zoneNumber == 3) {
      if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 42 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 43) {
        bool = true;
        i++;
      } 
      if (tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 52 || tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] == 53) {
        bool = true;
        i++;
      } 
    } 
    if (!bool)
      return; 
    if (blockColChk(PlayerPosX(), PlayerPosY()))
      if (i % 2 == 1 && PlayerPosX() / 16 % 16 < 9 && PlayerPosY() / 16 % 16 < 3) {
        tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = (byte)(tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] + 1);
        noloopchecktimer = 60;
      } else if (i % 2 == 0 && PlayerPosX() / 16 % 16 >= 7 && PlayerPosY() / 16 % 16 < 3) {
        tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] = (byte)(tempWorldMapData[(PlayerPosY() >> 8) % MapH][PlayerPosX() >> 8] - 1);
        noloopchecktimer = 60;
      }  
  }
  
  public void waterMove() {
    int i;
    char c;
    byte b;
    if (this.waterH3 == 0)
      this.waterH3 = this.waterH; 
    if (this.waterH3 < this.waterH) {
      this.waterH3++;
      if (this.waterH3 > this.waterH)
        this.waterH3 = this.waterH; 
    } else if (this.waterH3 > this.waterH) {
      this.waterH3--;
      if (this.waterH3 < this.waterH)
        this.waterH3 = this.waterH; 
    } 
    this.waterH2 = this.waterH3 + dSin(this.cpuTimer) * 8 / 100 + 8;
    if (this.zoneNumber != 1) {
      this.waterH = 16777215;
      return;
    } 
    switch (this.stageNumber) {
      case 0:
        if (this.water_flag != 0) {
          if (this.water_flag - 1 != 0 || 736 <= PlayerPosY())
            break; 
          this.waterH = 936;
          if (4864 > mapOxy[0])
            break; 
          this.waterH = 264;
          this.water_flag = 2;
          this.waterH = 264;
          break;
        } 
        this.waterH = 184;
        if (1536 > mapOxy[0])
          break; 
        this.waterH = 264;
        if (512 > PlayerPosY()) {
          if (3200 > mapOxy[0])
            break; 
          this.waterH = 232;
          if (5376 > mapOxy[0])
            break; 
          this.waterH = 264;
          break;
        } 
        if (3072 > mapOxy[0])
          break; 
        this.waterH = 792;
        if (4224 > mapOxy[0])
          break; 
        this.waterH = 1480;
        if (4992 > mapOxy[0])
          break; 
        this.waterH = 936;
        if (this.waterH != this.waterH3)
          break; 
        this.water_flag = 1;
        break;
      case 1:
        this.waterH = 808;
        if (1280 > mapOxy[0])
          break; 
        this.waterH = 968;
        if (2816 > mapOxy[0])
          break; 
        this.waterH = 1064;
        break;
      case 2:
        i = mapOxy[0];
        b = this.water_flag;
        if (this.water_flag != 0) {
          if (--b != 0) {
            if (--b != 0) {
              if (--b != 0) {
                if (7680 > i)
                  break; 
                this.waterH = 296;
                break;
              } 
              char c3 = 'ƈ';
              if ((6896 > i && mapOxy[1] < 2001) || 6260 > i) {
                if (6868 <= i && mapOxy[1] > 1280)
                  c3 = 'ऀ'; 
                this.waterH = c3;
                this.waterH3 = c3;
                break;
              } 
              c3 = 'ऀ';
              if (7147 > i) {
                this.waterH = c3;
                this.waterH3 = c3;
                break;
              } 
              this.water_flag = 4;
              this.waterH = 1544;
              this.waterH3 = 1920;
              break;
            } 
            char c2 = 'Ԉ';
            if (6240 > i) {
              this.waterH = c2;
              break;
            } 
            c2 = 'ƈ';
            if (6896 <= i) {
              this.water_flag = 3;
              this.waterH = c2;
              break;
            } 
            if (this.waterH3 != c2) {
              this.waterH = c2;
              break;
            } 
            this.water_flag = 3;
            this.waterH = c2;
            break;
          } 
          char c1 = 'ӈ';
          if (1936 > i) {
            this.waterH = c1;
            break;
          } 
          c1 = '̈';
          if (5120 > i) {
            this.waterH = c1;
            break;
          } 
          if (this.waterH == 1288) {
            c1 = 'Ԉ';
            this.waterH3 = c1;
            if (6000 > i) {
              this.waterH = c1;
              break;
            } 
            this.water_flag = 2;
            this.waterH = c1;
            break;
          } 
          if (1536 <= PlayerPosY()) {
            c1 = 'Ԉ';
            this.waterH3 = c1;
            if (6000 > i) {
              this.waterH = c1;
              break;
            } 
            this.water_flag = 2;
            this.waterH = c1;
            break;
          } 
          if (640 <= PlayerPosY()) {
            this.waterH = c1;
            break;
          } 
          c1 = 'Ԉ';
          this.waterH3 = c1;
          if (6000 > i) {
            this.waterH = c1;
            break;
          } 
          this.water_flag = 2;
          this.waterH = c1;
          break;
        } 
        c = 'ऀ';
        if (1696 > i) {
          this.waterH = c;
          this.waterH3 = c;
          break;
        } 
        if (992 > PlayerPosY()) {
          this.waterH = c;
          this.waterH3 = c;
          break;
        } 
        if (1536 <= PlayerPosY()) {
          this.waterH = c;
          this.waterH3 = c;
          break;
        } 
        c = 'ӈ';
        this.water_flag = 1;
        this.waterH = c;
        this.waterH3 = c;
        break;
      case 3:
        c = 'Ȩ';
        if (3840 > mapOxy[0]) {
          this.waterH = c;
          break;
        } 
        c = 'ӈ';
        this.waterH = c;
        break;
    } 
  }
  
  public void pata_draw(int paramInt) {
    if (objectData[4] == 1 || objectData[4] == 2)
      if (this.cpuTimer / 30 / 4 % 2 == 0) {
        drawImage(gg, this.m_imgObj[PATA], objectData[2] - mapView[0] - this.m_imgObj[PATA].getWidth(), objectData[3] - mapView[1] - this.m_imgObj[PATA].getHeight() / 2, 20);
        drawImage(gg, this.m_imgObj[PATA], objectData[2] - mapView[0], objectData[3] - mapView[1] - this.m_imgObj[PATA].getHeight() / 2, 20);
      } else {
        drawRegion(gg, this.m_imgObj[PATA], 0, 0, 64, 24, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0] - this.m_imgObj[PATA].getWidth() - 12, objectData[3] - mapView[1], 20);
        drawRegion(gg, this.m_imgObj[PATA], 0, 0, 64, 24, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0] + this.m_imgObj[PATA].getWidth() - 12, objectData[3] - mapView[1], 20);
      }  
  }
  
  public void drawRegion(Graphics paramGraphics, Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    try {
      paramInt7 += 36;
      paramGraphics.drawRegion(paramImage, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
    } catch (Throwable throwable) {}
  }
  
  public void drawImage(Graphics paramGraphics, Image paramImage, int paramInt1, int paramInt2, int paramInt3) {
    try {
      paramInt2 += 36;
      paramGraphics.drawImage(paramImage, paramInt1, paramInt2, paramInt3);
    } catch (Throwable throwable) {}
  }
  
  public Image createImage(String paramString) {
    try {
      System.gc();
      if (drawRsm)
        DG(); 
      return Image.createImage(paramString);
    } catch (Exception exception) {
      return createImage(paramString);
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  public void getItem(int paramInt) {
    if (paramInt == 2) {
      playercount++;
      PlayMusic(13);
    } else if (paramInt == 3) {
      speedupcount = 1200;
      plmaxspd = 3072;
      pladdspd = 24;
    } else if (paramInt == 4) {
      bariacount = 1;
    } else if (paramInt == 5) {
      mutekicount = 1200;
      PlayMusic(12);
    } else if (paramInt == 6) {
      ringcount += 10;
    } 
  }
  
  public void CallObjectMove(int paramInt) {
    try {
      switch (objectData[1]) {
        case 0:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_18_00_move_ikeshita(paramInt); 
          break;
        case 1:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_00_18_move_ikeshita(paramInt); 
          break;
        case 2:
          if (ObjectMoveChk(objectData[2], objectData[3], 16, 16))
            sjump_nflag_move_sakaki(paramInt); 
          break;
        case 3:
          buranko_nflag_move_ikeshita(paramInt);
          break;
        case 5:
          hashi_nflag_move_ikeshita(paramInt);
          break;
        case 4:
          thashi_nflag_move_ikeshita(paramInt);
          break;
        case 6:
          break_sflag_move_ikeshita(paramInt);
          break;
        case 7:
          yuka_nflag_move_ikeshita(paramInt);
          break;
        case 8:
          turi_nflag_move_ikeshita(paramInt);
          break;
        case 9:
          toge_nflag_move_ikeshita(paramInt);
          break;
        case 10:
          box_sflag_move_ikeshita(paramInt);
          break;
        case 11:
          fblock_nflag_move_ikeshita(paramInt);
          break;
        case 54:
          dainfla_move_ikeshita(paramInt);
          break;
        case 13:
          yogan2_sflag_move_ikeshita(paramInt);
          break;
        case 14:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, -1))
            myogan_nflag_move_ikeshita(paramInt); 
          break;
        case 15:
          switch2_nflag_move_ikeshita(paramInt);
          break;
        case 16:
          shima_nflag_move_ikeshita(paramInt);
          break;
        case 17:
          dai2_nflag_move_ikeshita(paramInt);
          break;
        case 18:
          if (ObjectMoveChk(objectData[2], objectData[3], 16, 32))
            brkabe_sflag_move_ikeshita(paramInt); 
          break;
        case 19:
          pedal_nflag_move_ikeshita(paramInt);
          break;
        case 20:
          if (ObjectMoveChk(objectData[2], objectData[3], 16, -1))
            break2_nflag_move_ikeshita(paramInt); 
          break;
        case 21:
          step_nflag_move_ikeshita(paramInt);
          break;
        case 22:
          if (ObjectMoveChk(objectData[2], objectData[3], 96, 64))
            fun_nflag_move_ikeshita(paramInt); 
          break;
        case 23:
          sisoo_nflag_move_arai(paramInt);
          break;
        case 24:
          if (ObjectMoveChk(objectData[2], objectData[3], 128, 16))
            belt_nflag_move_ikeshita(paramInt); 
          break;
        case 25:
          pata_nflag_move_ikeshita(paramInt);
          break;
        case 26:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 100))
            fire6_nflag_move_ikeshita(paramInt); 
          break;
        case 27:
          bryuka_nflag_move_ikeshita(paramInt);
          break;
        case 28:
          mawaru_nflag_move_ikeshita(paramInt);
          break;
        case 29:
          yukai_nflag_move_ikeshita(paramInt);
          break;
        case 30:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 64))
            door_nflag_move_ikeshita(paramInt); 
          break;
        case 31:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 32))
            yukae_nflag_move_ikeshita(paramInt); 
          break;
        case 32:
          dai4_nflag_move_ikeshita(paramInt);
          break;
        case 33:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 16))
            ele_nflag_move_ikeshita(paramInt); 
          break;
        case 34:
          beltc_nflag_move_ikeshita(paramInt);
          break;
        case 35:
          noko_nflag_move_ikeshita(paramInt);
          break;
        case 36:
          save_sflag_move_ikeshita(paramInt);
          break;
        case 37:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, 32))
            kageb_nflag_move_ikeshita(paramInt); 
          break;
        case 39:
          kamere_sflag_move_arai(paramInt);
          break;
        case 40:
          hachi_sflag_move_arai(paramInt);
          break;
        case 41:
          musi_sflag_move_arai(paramInt);
          break;
        case 42:
          if (ObjectMoveChk(objectData[2], objectData[3], 16, 16))
            item_nflag_move_ikeshita(paramInt); 
          break;
        case 43:
          if (ObjectMoveChk(objectData[2], objectData[3], 16, 16))
            item_sflag_move_ikeshita(paramInt); 
          break;
        case 44:
          gole_nflag_move_ikeshita(paramInt);
          break;
        case 45:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 32))
            bten_nflag_move_ikeshita(paramInt); 
          break;
        case 46:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 32))
            bten_sflag_move_ikeshita(paramInt); 
          break;
        case 48:
          scoli_nflag_move_ikeshita(paramInt);
          break;
        case 49:
          imo_sflag_move_arai(paramInt);
          break;
        case 50:
          brobo_sflag_move_arai(paramInt);
          break;
        case 51:
          buta_sflag_move_arai(paramInt);
          break;
        case 53:
          shooter_nflag_move_ikeshita(paramInt);
          break;
        case 55:
          masin_nflag_move_ikeshita(paramInt);
          break;
        case 56:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 32))
            bobin_sflag_move_ikeshita(paramInt); 
          break;
        case 57:
          kani_sflag_move_arai(paramInt);
          break;
        case 58:
          if (ObjectMoveChk(objectData[2], objectData[3], 24, 24))
            jyama_nflag_move_ikeshita(paramInt); 
          break;
        case 59:
          fetama_nflag_move_ikeshita(paramInt);
          break;
        case 60:
          tekyu_nflag_move_ikeshita(paramInt);
          break;
        case 62:
          dai2_sflag_move_ikeshita(paramInt);
          break;
        case 63:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_m10_10_move_ikeshita(paramInt); 
          break;
        case 64:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_10_10_move_ikeshita(paramInt); 
          break;
        case 65:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_20_20_move_ikeshita(paramInt); 
          break;
        case 66:
          ring_sflag_ring_10_00_move_ikeshita(paramInt);
          break;
        case 67:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_20_00_move_ikeshita(paramInt); 
          break;
        case 68:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_00_10_move_ikeshita(paramInt); 
          break;
        case 69:
          if (ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0)
            ring_sflag_ring_00_20_move_ikeshita(paramInt); 
          break;
        case 70:
          aruma_sflag_move_arai(paramInt);
          break;
        case 71:
          yado_sflag_move_arai(paramInt);
          break;
        case 72:
          elev_nflag_80_move_ikeshita(paramInt);
          break;
        case 73:
          elev_nflag_move_ikeshita(paramInt);
          break;
        case 74:
          uni_sflag_move_arai(paramInt);
          break;
        case 75:
          mfire_nflag_move_ikeshita(paramInt);
          break;
        case 77:
          yoganc_nflag_move_ikeshita(paramInt);
          break;
        case 78:
          bat_sflag_move_arai(paramInt);
          break;
        case 79:
          ochi_nflag_move_ikeshita(paramInt);
          break;
        case 80:
          if (ObjectMoveChk(objectData[2], objectData[3], 24, 24))
            yari_sflag_move_ikeshita(paramInt); 
          break;
        case 81:
          mogura_sflag_move_arai(paramInt);
          break;
        case 82:
          kazari_sflag_move_ikeshita(paramInt);
          break;
        case 83:
          dai3_nflag_move_ikeshita(paramInt);
          break;
        case 84:
          mizu_nflag_move_sakaki(paramInt);
          break;
        case 85:
          awa_nflag_move_sakaki(paramInt);
          break;
        case 86:
          fish_sflag_move_arai(paramInt);
          break;
        case 87:
          fish2_sflag_move_arai(paramInt);
          break;
        case 88:
          kassya_nflag_move_ikeshita(paramInt);
          break;
        case 90:
          shima2_nflag_move_ikeshita(paramInt);
          break;
        case 91:
          if (ObjectMoveChk(objectData[2], objectData[3], 32, 42))
            bou_nflag_move_ikeshita(paramInt); 
          break;
        case 92:
          ben_nflag_move_sakaki(paramInt);
          break;
        case 93:
          ben_sflag_move_sakaki(paramInt);
          break;
        case 120:
          boss1_move_arai(paramInt);
          break;
        case 125:
          boss2_move_arai(paramInt);
          break;
        case 130:
          boss3_move_arai(paramInt);
          break;
        case 135:
          boss4_move_arai(paramInt);
          break;
        case 140:
          boss5_move_arai(paramInt);
          break;
        case 145:
          boss6_move_arai(paramInt);
          break;
        case 150:
          MoveBoss5Block(paramInt);
          break;
      } 
    } catch (Throwable throwable) {}
  }
  
  public void CallObjectDraw(int paramInt) {
    try {
      switch (objectData[1]) {
        case 0:
          ring_sflag_ring_18_00_draw_ikeshita(paramInt);
          break;
        case 1:
          ring_sflag_ring_00_18_draw_ikeshita(paramInt);
          break;
        case 2:
          sjump_nflag_draw_sakaki(paramInt);
          break;
        case 3:
          buranko_nflag_draw_ikeshita(paramInt);
          break;
        case 5:
          hashi_nflag_draw_ikeshita(paramInt);
          break;
        case 4:
          thashi_nflag_draw_ikeshita(paramInt);
          break;
        case 6:
          break_sflag_draw_ikeshita(paramInt);
          break;
        case 7:
          yuka_nflag_draw_ikeshita(paramInt);
          break;
        case 8:
          turi_nflag_draw_ikeshita(paramInt);
          break;
        case 9:
          toge_nflag_draw_ikeshita(paramInt);
          break;
        case 10:
          box_sflag_draw_ikeshita(paramInt);
          break;
        case 11:
          fblock_nflag_draw_ikeshita(paramInt);
          break;
        case 54:
          dainfla_draw_ikeshita(paramInt);
          break;
        case 13:
          yogan2_sflag_draw_ikeshita(paramInt);
          break;
        case 14:
          myogan_nflag_draw_ikeshita(paramInt);
          break;
        case 15:
          switch2_nflag_draw_ikeshita(paramInt);
          break;
        case 16:
          shima_nflag_draw_ikeshita(paramInt);
          break;
        case 17:
          dai2_nflag_draw_ikeshita(paramInt);
          break;
        case 18:
          brkabe_sflag_draw_ikeshita(paramInt);
          break;
        case 19:
          pedal_nflag_draw_ikeshita(paramInt);
          break;
        case 20:
          break2_nflag_draw_ikeshita(paramInt);
          break;
        case 21:
          step_nflag_draw_ikeshita(paramInt);
          break;
        case 22:
          fun_nflag_draw_ikeshita(paramInt);
          break;
        case 23:
          sisoo_nflag_draw_arai(paramInt);
          break;
        case 25:
          pata_nflag_draw_ikeshita(paramInt);
          break;
        case 26:
          fire6_nflag_draw_ikeshita(paramInt);
          break;
        case 27:
          bryuka_nflag_draw_ikeshita(paramInt);
          break;
        case 28:
          mawaru_nflag_draw_ikeshita(paramInt);
          break;
        case 29:
          yukai_nflag_draw_ikeshita(paramInt);
          break;
        case 30:
          door_nflag_draw_ikeshita(paramInt);
          break;
        case 31:
          yukae_nflag_draw_ikeshita(paramInt);
          break;
        case 32:
          dai4_nflag_draw_ikeshita(paramInt);
          break;
        case 33:
          ele_nflag_draw_ikeshita(paramInt);
          break;
        case 34:
          beltc_nflag_draw_ikeshita(paramInt);
          break;
        case 35:
          noko_nflag_draw_ikeshita(paramInt);
          break;
        case 36:
          save_sflag_draw_ikeshita(paramInt);
          break;
        case 37:
          kageb_nflag_draw_ikeshita(paramInt);
          break;
        case 38:
          bgspr_nflag_draw_sakaki(paramInt);
          break;
        case 39:
          kamere_sflag_draw_arai(paramInt);
          break;
        case 40:
          hachi_sflag_draw_arai(paramInt);
          break;
        case 41:
          musi_sflag_draw_arai(paramInt);
          break;
        case 42:
          item_nflag_draw_ikeshita(paramInt);
          break;
        case 43:
          item_sflag_draw_ikeshita(paramInt);
          break;
        case 44:
          gole_nflag_draw_ikeshita(paramInt);
          break;
        case 45:
          bten_nflag_draw_ikeshita(paramInt);
          break;
        case 46:
          bten_sflag_draw_ikeshita(paramInt);
          break;
        case 49:
          imo_sflag_draw_arai(paramInt);
          break;
        case 50:
          brobo_sflag_draw_arai(paramInt);
          break;
        case 51:
          buta_sflag_draw_arai(paramInt);
          break;
        case 55:
          masin_nflag_draw_ikeshita(paramInt);
          break;
        case 56:
          bobin_sflag_draw_ikeshita(paramInt);
          break;
        case 57:
          kani_sflag_draw_arai(paramInt);
          break;
        case 58:
          jyama_nflag_draw_ikeshita(paramInt);
          break;
        case 59:
          fetama_nflag_draw_ikeshita(paramInt);
          break;
        case 60:
          tekyu_nflag_draw_ikeshita(paramInt);
          break;
        case 61:
          signal_nflag_draw_sakaki(paramInt);
          break;
        case 62:
          dai2_sflag_draw_ikeshita(paramInt);
          break;
        case 63:
          ring_sflag_ring_m10_10_draw_ikeshita(paramInt);
          break;
        case 64:
          ring_sflag_ring_10_10_draw_ikeshita(paramInt);
          break;
        case 65:
          ring_sflag_ring_20_20_draw_ikeshita(paramInt);
          break;
        case 66:
          ring_sflag_ring_10_00_draw_ikeshita(paramInt);
          break;
        case 67:
          ring_sflag_ring_20_00_draw_ikeshita(paramInt);
          break;
        case 68:
          ring_sflag_ring_00_10_draw_ikeshita(paramInt);
          break;
        case 69:
          ring_sflag_ring_00_20_draw_ikeshita(paramInt);
          break;
        case 70:
          aruma_sflag_draw_arai(paramInt);
          break;
        case 71:
          yado_sflag_draw_arai(paramInt);
          break;
        case 72:
          elev_nflag_80_draw_ikeshita(paramInt);
          break;
        case 73:
          elev_nflag_draw_ikeshita(paramInt);
          break;
        case 74:
          uni_sflag_draw_arai(paramInt);
          break;
        case 75:
          mfire_nflag_draw_ikeshita(paramInt);
          break;
        case 77:
          yoganc_nflag_draw_ikeshita(paramInt);
          break;
        case 78:
          bat_sflag_draw_arai(paramInt);
          break;
        case 79:
          ochi_nflag_draw_ikeshita(paramInt);
          break;
        case 80:
          yari_sflag_draw_ikeshita(paramInt);
          break;
        case 81:
          mogura_sflag_draw_arai(paramInt);
          break;
        case 82:
          kazari_sflag_draw_ikeshita(paramInt);
          break;
        case 83:
          dai3_nflag_draw_ikeshita(paramInt);
          break;
        case 84:
          mizu_nflag_draw_sakaki(paramInt);
          break;
        case 85:
          awa_nflag_draw_sakaki(paramInt);
          break;
        case 86:
          fish_sflag_draw_arai(paramInt);
          break;
        case 87:
          fish2_sflag_draw_arai(paramInt);
          break;
        case 88:
          kassya_nflag_draw_ikeshita(paramInt);
          break;
        case 90:
          shima2_nflag_draw_ikeshita(paramInt);
          break;
        case 91:
          bou_nflag_draw_ikeshita(paramInt);
          break;
        case 92:
          ben_nflag_draw_sakaki(paramInt);
          break;
        case 93:
          ben_sflag_draw_sakaki(paramInt);
          break;
        case 120:
          boss1_draw_arai(paramInt);
          break;
        case 125:
          boss2_draw_arai(paramInt);
          break;
        case 130:
          boss3_draw_arai(paramInt);
          break;
        case 135:
          boss4_draw_arai(paramInt);
          break;
        case 140:
          boss5_draw_arai(paramInt);
          break;
        case 145:
          boss6_draw_arai(paramInt);
          break;
        case 150:
          DrawBoss5Block(false);
          break;
      } 
    } catch (Throwable throwable) {}
  }
  
  public int ObjectColChk(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12) {
    return (PlayerDie || PlayerNoCol || debugFlag) ? -1 : ((paramInt4 + paramInt6 <= paramInt10 - paramInt12 && paramInt2 + paramInt6 >= paramInt8 - paramInt12 && paramInt1 + paramInt5 >= paramInt7 - paramInt11 && paramInt1 - paramInt5 <= paramInt7 + paramInt11) ? ((paramInt1 + paramInt5 > paramInt7 - paramInt11 && paramInt1 - paramInt5 < paramInt7 + paramInt11) ? 0 : -1) : ((paramInt3 + paramInt5 <= paramInt9 - paramInt11 && paramInt1 + paramInt5 >= paramInt7 - paramInt11 && paramInt2 + paramInt6 >= paramInt8 - paramInt12 && paramInt2 - paramInt6 <= paramInt8 + paramInt12) ? 1 : ((paramInt3 - paramInt5 >= paramInt9 + paramInt11 && paramInt1 - paramInt5 <= paramInt7 + paramInt11 && paramInt2 + paramInt6 >= paramInt8 - paramInt12 && paramInt2 - paramInt6 <= paramInt8 + paramInt12) ? 2 : ((paramInt4 - paramInt6 >= paramInt10 + paramInt12 && paramInt2 - paramInt6 <= paramInt8 + paramInt12 && paramInt1 + paramInt5 >= paramInt7 - paramInt11 && paramInt1 - paramInt5 <= paramInt7 + paramInt11) ? 3 : ((paramInt1 + paramInt5 >= paramInt7 - paramInt11 && paramInt1 - paramInt5 <= paramInt7 + paramInt11 && paramInt2 + paramInt6 >= paramInt8 - paramInt12 && paramInt2 - paramInt6 <= paramInt8 + paramInt12) ? 4 : -1)))));
  }
  
  private void setRaidOnSize(int paramInt1, int paramInt2) {
    raidObjectW = paramInt2;
    raidObjectX = paramInt1;
  }
  
  private void setHeadHit() {
    this.crushing[3] = true;
    if (PlayerParam[5] < 0)
      PlayerParam[5] = 0; 
  }
  
  public int getPlayerH() {
    byte b = 18;
    if (PlayerBall)
      b = 12; 
    return b;
  }
  
  public int ObjectColChk2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    if (PlayerDie || PlayerNoCol || debugFlag)
      return -1; 
    int i = (540 - olddir) % 360;
    if (0 > i)
      i = 0; 
    byte b1 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b1 = 18; 
    int j = dSin(i) * b1 / 100;
    int k = -dCos(i) * b1 / 100;
    int m = PlayerPosX() + j;
    int n = PlayerPosY() + k;
    int i1 = ploldpos[0] + j;
    int i2 = ploldpos[1] + k;
    byte b2 = 12;
    byte b3 = b1;
    return (i2 + b3 <= paramInt4 - paramInt6 && n + b3 >= paramInt2 - paramInt6 && m + b2 >= paramInt1 - paramInt5 && m - b2 <= paramInt1 + paramInt5) ? 0 : ((i1 + b2 <= paramInt3 - paramInt5 && m + b2 >= paramInt1 - paramInt5 && n + b3 >= paramInt2 - paramInt6 && n - b3 <= paramInt2 + paramInt6) ? 1 : ((i1 - b2 >= paramInt3 + paramInt5 && m - b2 <= paramInt1 + paramInt5 && n + b3 >= paramInt2 - paramInt6 && n - b3 <= paramInt2 + paramInt6) ? 2 : ((i2 - b3 >= paramInt4 + paramInt6 && n - b3 <= paramInt2 + paramInt6 && m + b2 >= paramInt1 - paramInt5 && m - b2 <= paramInt1 + paramInt5) ? 3 : ((m + b2 >= paramInt1 - paramInt5 && m - b2 <= paramInt1 + paramInt5 && n + b3 >= paramInt2 - paramInt6 && n - b3 <= paramInt2 + paramInt6) ? 4 : -1))));
  }
  
  public int ObjectColChkPl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    if (PlayerDie || PlayerNoCol || debugFlag)
      return -1; 
    byte b1 = 12;
    if (!PlayerBall)
      b1 = 16; 
    byte b2 = 0;
    byte b3 = -b1;
    int i = PlayerPosX() + b2;
    int j = PlayerPosY() + b3;
    int k = ploldpos[0] + b2;
    int m = ploldpos[1] + b3;
    byte b4 = 12;
    byte b5 = b1;
    return (m + b5 <= paramInt4 - paramInt6 && j + b5 >= paramInt2 - paramInt6 && i + b4 >= paramInt1 - paramInt5 && i - b4 <= paramInt1 + paramInt5) ? 0 : ((k + b4 <= paramInt3 - paramInt5 && i + b4 >= paramInt1 - paramInt5 && j + b5 >= paramInt2 - paramInt6 && j - b5 <= paramInt2 + paramInt6) ? 1 : ((k - b4 >= paramInt3 + paramInt5 && i - b4 <= paramInt1 + paramInt5 && j + b5 >= paramInt2 - paramInt6 && j - b5 <= paramInt2 + paramInt6) ? 2 : ((m - b5 >= paramInt4 + paramInt6 && j - b5 <= paramInt2 + paramInt6 && i + b4 >= paramInt1 - paramInt5 && i - b4 <= paramInt1 + paramInt5) ? 3 : ((i + b4 >= paramInt1 - paramInt5 && i - b4 <= paramInt1 + paramInt5 && j + b5 >= paramInt2 - paramInt6 && j - b5 <= paramInt2 + paramInt6) ? 4 : -1))));
  }
  
  public void sjump_nflag_move_sakaki(int paramInt) {
    byte b1 = 16;
    byte b2 = 8;
    if (objectData[4] == 16 || objectData[4] == 18) {
      b1 = 8;
      b2 = 16;
    } 
    boolean bool = false;
    if (objectData[5] > 0) {
      objectData[5] = objectData[5] + 1;
      if (objectData[5] > 12)
        objectData[5] = 0; 
    } 
    int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0) {
      char c = 'က';
      int j = objectData[4] / 16;
      if (objectData[4] % 16 == 2)
        c = '਀'; 
      if (j == 1) {
        b1 = 7;
      } else {
        b2 = 7;
      } 
      if (i == 0 || i == 4) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        if (j == 0) {
          if (objectData[5] == 4) {
            PlayerParam[1] = objectData[3] - 8 << 8;
            PlayerJump = true;
            PlayerDamage = false;
            PlayerSJump = true;
            PlayerBall = false;
            comboScore = 0;
            boolean bool1 = false;
            PlayerParam[5] = dCos(bool1) * c / 100;
          } 
          objectData[5] = objectData[5] + 1;
        } else {
          playerRaidOn(objectData[22]);
        } 
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 - 1 << 8;
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        if (j == 1 && objectData[19] == 1) {
          this.noLeverTimer = 15;
          PlayerParam[10] = -c;
          PlayerParam[12] = 1;
          PlayerParam[13] = 1;
          PlayerParam[14] = 1;
          objectData[5] = objectData[5] + 1;
        } 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
        if (j == 1 && objectData[19] == 0) {
          this.noLeverTimer = 15;
          PlayerParam[10] = c;
          PlayerParam[12] = 0;
          PlayerParam[13] = 2;
          PlayerParam[14] = 2;
          objectData[5] = objectData[5] + 1;
        } 
      } else if (i == 3) {
        if (j == 2) {
          if (objectData[19] == 2) {
            PlayerParam[5] = c;
            PlayerJump = true;
            this.noLeverTimer = 30;
          } 
          objectData[5] = objectData[5] + 1;
        } 
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  public void mizu_nflag_move_sakaki(int paramInt) {
    if (objectData[4] == 8) {
      byte b1 = 24;
      byte b2 = 8;
      int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
      if (i >= 0) {
        PlayerWater = true;
        PlayerParam[12] = 0;
        PlayerParam[10] = 4096;
        if (objectData[19] == 0) {
          PlayerParam[12] = 1;
          PlayerParam[10] = -4096;
        } 
      } 
    } 
    if (objectData[4] <= 6 && objectData[4] != 0) {
      byte b1 = 8;
      byte b2 = 8;
      int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
      if (i >= 0)
        PlayerWater = true; 
    } 
  }
  
  public void awa_nflag_move_sakaki(int paramInt) {
    if (objectData[5] % 256 == 0) {
      objectData[10] = rnd(4) + rnd(2) + rnd(2);
      objectData[11] = 1;
      objectData[5] = objectData[5] + 1;
    } 
    if (objectData[10] == 0) {
      objectData[5] = objectData[5] + 1;
    } else if (this.cpuTimer % 16 == 0 && rnd(3) != 0) {
      objectData[10] = objectData[10] - 1;
      if (objectData[10] > 0) {
        int i = 0;
        if (objectData[11] != 0) {
          i = rnd(3);
        } else {
          i = rnd(2);
        } 
        objAwaData_set(i, objectData[8], objectData[9]);
        if (i == 2)
          objectData[11] = objectData[11] - 1; 
      } else if (objectData[11] != 0) {
        objectData[11] = objectData[11] - 1;
        objAwaData_set(2, objectData[8], objectData[9]);
      } else {
        int i = 0;
        i = rnd(2);
        objAwaData_set(i, objectData[8], objectData[9]);
      } 
    } 
  }
  
  public void objAwaData_set(int paramInt1, int paramInt2, int paramInt3) {
    for (byte b = 0; b < objAwaData.length; b++) {
      if (objAwaData[b][0] == 0) {
        for (byte b1 = 0; b1 < (objAwaData[b]).length; b1++)
          objAwaData[b][b1] = 0; 
        objAwaData[b][0] = 1;
        objAwaData[b][1] = paramInt1;
        objAwaData[b][8] = paramInt2 + rnd(16) - 8;
        objAwaData[b][2] = paramInt2 + rnd(16) - 8;
        objAwaData[b][9] = paramInt3;
        objAwaData[b][3] = paramInt3;
        objAwaData[b][6] = rnd(2) * 16 * 4;
        break;
      } 
    } 
  }
  
  public void objAwaData_move() {
    for (byte b = 0; b < objAwaData.length; b++) {
      if (objAwaData[b][0] > 0)
        if (objAwaData[b][3] - mapView[1] > -48 && objAwaData[b][3] - mapView[1] < 288 && this.waterH3 < objAwaData[b][3]) {
          int i = this.awasintlb[objAwaData[b][6] % this.awasintlb.length];
          if (i > 128)
            i -= 255; 
          objAwaData[b][10] = objAwaData[b][2];
          objAwaData[b][11] = objAwaData[b][3];
          objAwaData[b][2] = objAwaData[b][8] + i;
          objAwaData[b][3] = objAwaData[b][9] - objAwaData[b][5] / 2;
          objAwaData[b][5] = objAwaData[b][5] + 1;
          objAwaData[b][6] = objAwaData[b][6] + 1;
          objAwaData[b][7] = objAwaData[b][7] + 1;
          if (objAwaData[b][1] * 32 + 16 < objAwaData[b][7]) {
            objAwaData[b][7] = objAwaData[b][1] * 32 + 16;
            if (objAwaData[b][1] == 2) {
              byte b1 = 16;
              byte b2 = 16;
              byte b3 = -1;
              if (Math.abs(PlayerPosX() - objAwaData[b][2]) < 12 + b1 && Math.abs(PlayerPosY() - 12 - objAwaData[b][3]) < 12 + b2)
                b3 = 0; 
              if (b3 >= 0) {
                objAwaData[b][1] = 3;
                kokyutimer = 8;
                this.bressCount = 2100;
                this.bressMusic = true;
                PlayerBall = false;
                PlayMusic(27);
                if (PlayerJump && -pljump_w >> 1 > PlayerParam[5])
                  PlayerParam[5] = -pljump_w >> 1; 
              } 
            } else if (objAwaData[b][1] == 3) {
              objAwaData[b][0] = 0;
            } 
          } 
        } else {
          objAwaData[b][0] = 0;
        }  
    } 
  }
  
  public void objAwaData_draw() {
    for (byte b = 0; b < objAwaData.length; b++) {
      if (objAwaData[b][0] > 0) {
        int i = objAwaData[b][7] / 16;
        drawRegion(gg, this.m_imgObj[104], 0, this.awaPos[i], 32, this.awaSize[i], rotNumTable[TRANS_NONE], objAwaData[b][2] - mapView[0], objAwaData[b][3] - mapView[1], 0x1 | 0x2);
      } 
    } 
  }
  
  public void ben_nflag_move_sakaki(int paramInt) {
    if (this.animeTimer % 60 < 30) {
      if (raidOn && raidObjectNum == objectData[20])
        raidOn = false; 
      return;
    } 
    byte b1 = 8;
    byte b2 = 32;
    int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (objectData[5] == 1)
      i = -1; 
    if (i >= 0 && i == 1)
      PlayerParam[0] = objectData[2] - b1 - 12 << 8; 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  public void ben_sflag_move_sakaki(int paramInt) {
    ben_nflag_move_sakaki(paramInt);
  }
  
  public void sjump_nflag_draw_sakaki(int paramInt) {
    int i = SJUMP;
    int j = objectData[4] / 16;
    if (objectData[4] % 16 == 2)
      i = 1; 
    int[] arrayOfInt = { 
        0, 0, 32, 16, 0, 0, 32, 8, 0, 24, 
        32, 32 };
    byte b = 0;
    if (objectData[5] > 4) {
      b = 8;
    } else if (objectData[5] > 0) {
      b = 4;
    } 
    if (j == 0) {
      if (b == 8) {
        drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 8, 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } 
    } else if (j == 1) {
      if (objectData[19] == 0) {
        if (b == 8) {
          drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_ROT90], objectData[2] - mapView[0] + 8, objectData[3] - mapView[1], 0x1 | 0x2);
        } else {
          drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_ROT90], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
        } 
      } else if (b == 8) {
        drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_MIRROR_ROT270], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1], 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_MIRROR_ROT270], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } 
    } else if (b == 8) {
      drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_MIRROR_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1] + 8, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[i], arrayOfInt[b + 0], arrayOfInt[b + 1], arrayOfInt[b + 2], arrayOfInt[b + 3], rotNumTable[TRANS_MIRROR_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  public void bgspr_nflag_draw_sakaki(int paramInt) {
    if (this.zoneNumber != 0)
      return; 
    int i = rotNumTable[TRANS_NONE];
    if (objectData[19] == 0)
      i = rotNumTable[TRANS_MIRROR]; 
    drawRegion(gg, this.m_imgObj[5], 0, 0, 32, 16, i, objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  public void jyama_nflag_draw_sakaki(int paramInt) {
    drawImage(gg, this.m_imgObj[58], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  public void signal_nflag_draw_sakaki(int paramInt) {
    drawRegion(gg, this.m_imgObj[61], 0, 16 * (this.animeTimer >> 1) % 6, 32, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  public void mizu_nflag_draw_sakaki(int paramInt) {
    if (objectData[4] == 0) {
      DrawWaterMap(71, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 16);
      DrawWaterMap(72, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1]);
    } else if (objectData[4] == 7) {
      DrawWaterMap(95, TRANS_NONE, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 16);
      DrawWaterMap(73, TRANS_NONE + 0, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1]);
    } else if (objectData[4] == 8) {
      DrawWaterMap(74, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] + 8, objectData[3] - mapView[1] - 8);
      DrawWaterMap(75, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8);
      DrawWaterMap(76, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 24, objectData[3] - mapView[1] - 8);
    } else if (objectData[4] == 73) {
      drawRegion(gg, this.m_imgObj[110], 0, this.animeTimer % 5 * 32, 40, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], this.waterH2 - 20 - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 9 || objectData[4] == 169) {
      drawRegion(gg, this.m_imgObj[110], 0, this.animeTimer % 5 * 32, 40, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] <= 6) {
      DrawWaterMap(187, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8);
    } 
  }
  
  public void awa_nflag_draw_sakaki(int paramInt) {
    if (this.waterH3 < objectData[3])
      drawRegion(gg, this.m_imgObj[85], 0, 16 * this.cpuTimer / 16 % 3, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2); 
  }
  
  public void ben_nflag_draw_sakaki(int paramInt) {
    if (this.animeTimer % 60 < 30) {
      drawRegion(gg, this.m_imgObj[92], 0, 0, 32, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + 8, objectData[3] - mapView[1] - 32, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[92], 0, 0, 32, 16, rotNumTable[TRANS_NONE + 6], objectData[2] - mapView[0] + 8, objectData[3] - mapView[1] + 32, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[92], 0, 0, 32, 16, rotNumTable[TRANS_NONE + 1], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[92], 0, 0, 32, 16, rotNumTable[TRANS_NONE + 5], objectData[2] - mapView[0], objectData[3] - mapView[1] + 16, 0x1 | 0x2);
    } 
  }
  
  public void ben_sflag_draw_sakaki(int paramInt) {
    ben_nflag_draw_sakaki(paramInt);
  }
  
  public void resultContinue(boolean paramBoolean) {
    if (paramBoolean) {
      playercount = 3;
      scorecount = 0;
      diecount = 0;
      initStageStart();
      plsaveX = 0;
      plsaveY = 0;
      plsaveTime = 0;
      plsaveTime2 = 0;
      this.noTimeScore = false;
    } else {
      mode = MODE_TITLE;
      this.SetSoftFlag = true;
      this.SetSoftCount = 10;
      ObjImageClear();
      TK_TitleInit(true);
    } 
  }
  
  public void setEnding() {
    this.selectZoneNumber = 6;
    this.selectStageNumber = 1;
    PlayerParam[0] = 978688;
    PlayerParam[1] = 32768;
    this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
    this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
    try {
      indata = new DataInputStream(getClass().getResourceAsStream("/zone" + (this.zoneNumber + 1) + ".bmd"));
      MapW = (worldMapData[this.zoneNumber][this.stageNumber][0]).length;
      MapH = (worldMapData[this.zoneNumber][this.stageNumber]).length;
      tempWorldMapData = new byte[MapH][MapW];
      for (byte b1 = 0; b1 < MapH; b1++) {
        for (byte b2 = 0; b2 < MapW; b2++)
          tempWorldMapData[b1][b2] = worldMapData[this.zoneNumber][this.stageNumber][b1][b2]; 
      } 
      indata.read(mapData);
      indata.close();
      setMapData();
    } catch (Throwable throwable) {}
    byte b;
    for (b = 0; b < m_nHiScore.length; b++) {
      if (m_nHiScore[b] < scorecount) {
        for (int i = m_nHiScore.length - 1; i > b; i--) {
          m_nHiScore[i] = m_nHiScore[i - 1];
          m_nDifficulty[i] = m_nDifficulty[i - 1];
        } 
        m_nHiScore[b] = scorecount;
        m_nDifficulty[b] = m_nConfigValue[0];
        save_hisc();
        break;
      } 
    } 
    readStageObject();
    objectInit(this.stageNumber);
    ObjectListNum = 0;
    this.noDataPointer = 0;
    for (b = 0; b < ObjectList.length; b++)
      ObjectList[b][24] = 0; 
    this.endingStep = 0;
    this.endingModeOn = true;
  }
  
  public String[] readStrings(String paramString) {
    try {
      InputStream inputStream = null;
      inputStream = getClass().getResourceAsStream("/" + paramString);
      int i = inputStream.read();
      i = inputStream.read();
      String[] arrayOfString = new String[i];
      for (byte b = 0; b < i; b++) {
        int j = inputStream.read() & 0xFF;
        byte[] arrayOfByte = new byte[j];
        inputStream.read(arrayOfByte);
        arrayOfString[b] = new String(arrayOfByte);
      } 
      inputStream.close();
      return arrayOfString;
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  private void TK_TitleFactor() {
    byte b;
    for (b = 0; b < 10; b++) {
      if (m_OnKeyFlag[b]) {
        if (KeyPress[b]) {
          KeyPress[b] = false;
        } else {
          m_OnKeyFlag[b] = false;
        } 
      } else {
        m_OnKeyFlag[b] = KeyPress[b];
      } 
    } 
    if (m_bDraw > 0)
      m_bDraw = (byte)(m_bDraw - 1); 
    m_nRingPattern = (byte)((m_nRingPattern + 1) % 6);
    if (m_nTitleMode == TITLE_MODE_LICENSE_SEGA) {
      if (m_nTimer == 17)
        PlayMusic(26); 
      m_nTimer = (byte)(m_nTimer + 1);
      if (m_nTimer > 75) {
        m_nTimer = 0;
        m_nTitleMode = TITLE_MODE_LICENSE_SONICTEAM;
      } 
    } else if (m_nTitleMode == TITLE_MODE_LICENSE_SONICTEAM) {
      m_nTimer = (byte)(m_nTimer + 1);
      if (m_nTimer > 10) {
        m_nTimer = 0;
        TK_TitleInit(false);
      } 
    } else if (m_nTitleMode == TITLE_MODE_FIRST_SETUP) {
      if (KeyPress[4]) {
        m_nConfigValue[3] = (byte)((m_nConfigValue[3] + 1) % LANGUAGE_MAX);
        TK_LoadTextset();
      } else if (KeyPress[3]) {
        m_nConfigValue[3] = (byte)(m_nConfigValue[3] - 1);
        if (m_nConfigValue[3] < 0)
          m_nConfigValue[3] = (byte)(LANGUAGE_MAX - 1); 
        TK_LoadTextset();
      } else if (KeyPress[0]) {
        save_conf();
        TK_TitleInit(false);
        SetSoftKey(0);
      } 
    } else if (m_nTitleMode == TITLE_MODE_TITLE) {
      if (m_nPattern < 6) {
        m_nTimer = (byte)(m_nTimer + 1);
        if (m_nTimer > 5)
          m_nPattern = (byte)(m_nPattern + 1); 
      } else {
        m_nTimer = (byte)((m_nTimer + 1) % 5);
      } 
      if (KeyPress[0]) {
        m_nTitleMode = TITLE_MODE_TITLE_MENU;
        m_nSel = 0;
        TK_SetMarquee(7 + m_nSel);
        SetSoftKey(2);
      } 
      if (KeyPress[6])
        h.doExit(); 
    } else if (m_nTitleMode == TITLE_MODE_TITLE_MENU) {
      m_nTimer = (byte)((m_nTimer + 1) % 5);
      if (KeyPress[4]) {
        m_nSel = (byte)((m_nSel + 1) % 5);
        TK_SetMarquee(7 + m_nSel);
      } 
      if (KeyPress[3]) {
        m_nSel = (byte)(m_nSel - 1);
        if (m_nSel < 0)
          m_nSel = 4; 
        TK_SetMarquee(7 + m_nSel);
      } 
      if (KeyPress[0]) {
        if (m_nSel == 0) {
          m_nSel = 0;
          load_resu();
          if (this.resumeStage != 0) {
            SetSoftKey(1);
            m_nSel = 1;
            TK_SetMarquee(45 + m_nSel);
            m_nTitleMode = TITLE_MODE_TITLE_CONTINUE_MENU;
          } else {
            clearKey();
            initDisplay = true;
            for (b = 0; b < m_imgImage.length; b++)
              m_imgImage[b] = null; 
            playercount = 3;
            scorecount = 0;
            this.resumeStage = 0;
            this.selectZoneNumber = this.zoneNumber = 0;
            this.selectStageNumber = this.stageNumber = 0;
            readStageObjectFlag = true;
            initStageStart();
          } 
          return;
        } 
        if (m_nSel == 1) {
          m_nSel = 1;
          clearKey();
          this.selectZoneNumber = this.zoneNumber = 0;
          this.selectStageNumber = this.stageNumber = 0;
          initDisplay = true;
          mode = MODE_STAGESELECT;
          this.SetSoftFlag = true;
          this.SetSoftCount = 10;
          for (b = 0; b < m_imgImage.length; b++)
            m_imgImage[b] = null; 
          return;
        } 
        if (m_nSel == 2) {
          m_nSel = 0;
          m_nTitleMode = TITLE_MODE_TITLE_HOWTO;
          m_bDraw = 1;
          SetSoftKey(1);
        } else if (m_nSel == 3) {
          m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
          SetSoftKey(1);
        } else {
          m_nSel = 0;
          m_nTitleMode = TITLE_MODE_TITLE_CONFIG_MENU;
          TK_SetMarquee(47 + m_nSel);
          SetSoftKey(1);
          m_bDraw = 1;
        } 
      } 
      if (KeyPress[6]) {
        SetSoftKey(0);
        m_nTitleMode = TITLE_MODE_TITLE;
        return;
      } 
      TK_MoveMarquee();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING) {
      if (KeyPress[6]) {
        m_nSel = 3;
        m_nTitleMode = TITLE_MODE_TITLE_MENU;
        SetSoftKey(2);
        TK_SetMarquee(7 + m_nSel);
      } else if (KeyPress[9]) {
        m_nSel = 1;
        m_nTitleMode = TITLE_MODE_TITLE_RANCKING_MENU;
      } 
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_MENU) {
      if (KeyPress[6]) {
        m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
      } else if (KeyPress[2] || KeyPress[1]) {
        m_nSel = (byte)((m_nSel + 1) % 2);
      } else if (KeyPress[0]) {
        m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
        if (m_nSel == 0) {
          m_nHiScore = new int[5];
          m_nDifficulty = new int[5];
          save_hisc();
          m_nTitleMode = TITLE_MODE_TITLE_RANCKING_DEL;
        } 
      } 
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_DEL) {
      if (KeyPress[6] || KeyPress[0])
        m_nTitleMode = TITLE_MODE_TITLE_RANCKING; 
    } else if (m_nTitleMode == TITLE_MODE_TITLE_CONFIG_MENU) {
      if (KeyPress[6] || KeyPress[0]) {
        m_nTitleMode = TITLE_MODE_TITLE_MENU;
        SetSoftKey(2);
        m_nSel = 4;
        TK_SetMarquee(7 + m_nSel);
        save_conf();
      } else if (KeyPress[2]) {
        m_nSel = (byte)(m_nSel - 1);
        if (m_nSel < 0)
          m_nSel = 3; 
        TK_SetMarquee(47 + m_nSel);
        m_bDraw = 1;
      } else if (KeyPress[1]) {
        m_nSel = (byte)((m_nSel + 1) % 4);
        TK_SetMarquee(47 + m_nSel);
        m_bDraw = 1;
      } else if (KeyPress[4]) {
        if (m_nSel == 0) {
          m_nConfigValue[0] = (byte)((m_nConfigValue[0] + 1) % 3);
        } else if (m_nSel == 1) {
          m_nConfigValue[1] = (byte)((m_nConfigValue[1] + 1) % 4);
        } else if (m_nSel == 2) {
          m_nConfigValue[2] = (byte)((m_nConfigValue[2] + 1) % 2);
        } else {
          m_nConfigValue[3] = (byte)((m_nConfigValue[3] + 1) % LANGUAGE_MAX);
          TK_LoadTextset();
          TK_SetMarquee(47 + m_nSel);
          SetSoftLabel(1, softKeys[4]);
        } 
        m_bDraw = 1;
      } else if (KeyPress[3]) {
        m_nConfigValue[m_nSel] = (byte)(m_nConfigValue[m_nSel] - 1);
        if (m_nConfigValue[m_nSel] < 0)
          if (m_nSel == 0) {
            m_nConfigValue[0] = 2;
          } else if (m_nSel == 1) {
            m_nConfigValue[1] = 3;
          } else if (m_nSel == 2) {
            m_nConfigValue[2] = 1;
          } else {
            m_nConfigValue[3] = (byte)(LANGUAGE_MAX - 1);
          }  
        if (m_nSel == 3) {
          TK_LoadTextset();
          TK_SetMarquee(47 + m_nSel);
          SetSoftLabel(1, softKeys[4]);
        } 
        m_bDraw = 1;
      } 
      TK_MoveMarquee();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_CONTINUE_MENU) {
      if (KeyPress[6]) {
        m_nTitleMode = TITLE_MODE_TITLE_MENU;
        m_nSel = 0;
        TK_SetMarquee(7 + m_nSel);
        SetSoftKey(2);
      } else if (KeyPress[2] || KeyPress[1]) {
        m_nSel = (byte)((m_nSel + 1) % 2);
        TK_SetMarquee(45 + m_nSel);
      } else if (KeyPress[0]) {
        if (m_nSel == 0) {
          clearKey();
          initDisplay = true;
          for (b = 0; b < m_imgImage.length; b++)
            m_imgImage[b] = null; 
          playercount = 3;
          scorecount = 0;
          this.resumeStage = 0;
          this.selectZoneNumber = this.zoneNumber = 0;
          this.selectStageNumber = this.stageNumber = 0;
          readStageObjectFlag = true;
          initStageStart();
        } else if (m_nSel == 1) {
          clearKey();
          initDisplay = true;
          for (b = 0; b < m_imgImage.length; b++)
            m_imgImage[b] = null; 
          playercount = this.resumeZanki;
          scorecount = this.resumeScore;
          this.selectZoneNumber = this.zoneNumber = this.resumeStage / 3;
          this.selectStageNumber = this.stageNumber = this.resumeStage % 3;
          this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
          this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
          readStageObjectFlag = true;
          initStageStart();
        } 
      } 
      TK_MoveMarquee();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_HOWTO) {
      if (KeyPress[4] && m_nSel < 25) {
        m_nSel = (byte)(m_nSel + 1);
        m_bDraw = 1;
      } 
      if (KeyPress[3] && m_nSel > 0) {
        m_nSel = (byte)(m_nSel - 1);
        m_bDraw = 1;
      } 
      if (KeyPress[6]) {
        m_nSel = 2;
        m_nTitleMode = TITLE_MODE_TITLE_MENU;
        SetSoftKey(2);
      } 
    } 
    KeyPress[5] = false;
    KeyPress[6] = false;
  }
  
  private void TK_TitleDraw() {
    gg.setFont(m_Font);
    gg.setClip(0, 0, 240, 240);
    if (m_nTitleMode == TITLE_MODE_LICENSE_SEGA) {
      gg.setColor(16777215);
      gg.fillRect(0, 0, 240, 240);
      gg.drawImage(m_imgImage[0], 120, 120, 3);
      if (m_nTimer < 15) {
        int i = 240 * m_nTimer / 15;
        gg.fillRect(i - 240 - 10, 0, 240, 240);
        gg.fillRect(i + 10, 0, 240, 240);
        int j = i - 11;
        gg.drawLine(j + 2, 0, j + 2, 240);
        gg.drawLine(j + 3, 0, j + 3, 240);
        gg.drawLine(j + 5, 0, j + 5, 240);
        gg.drawLine(j + 8, 0, j + 8, 240);
        j = i + 10;
        gg.drawLine(j - 2, 0, j - 2, 240);
        gg.drawLine(j - 3, 0, j - 3, 240);
        gg.drawLine(j - 5, 0, j - 5, 240);
        gg.drawLine(j - 8, 0, j - 8, 240);
      } 
      if (m_nTimer >= 60) {
        gg.setColor(0);
        int i = (m_nTimer - 60) * 120 / 15;
        for (byte b = 0; b < i; b++) {
          gg.drawLine(2 * b, 0, 2 * b, 240);
          gg.drawLine(239 - 2 * b, 0, 239 - 2 * b, 240);
        } 
      } 
    } else if (m_nTitleMode == TITLE_MODE_LICENSE_SONICTEAM) {
      gg.setColor(0);
      gg.fillRect(0, 0, 240, 240);
      gg.drawImage(m_imgImage[1], 120, 120, 3);
    } else if (m_nTitleMode == TITLE_MODE_FIRST_SETUP) {
      gg.setColor(0);
      gg.fillRect(0, 0, 240, 240);
      TK_DrawBelt(true, true);
      gg.setColor(16777215);
      byte b = 3;
      gg.drawString(m_strText[21 + b], 11, 48 + b * 30, 20);
      gg.drawString(m_strText[m_aConfigTextOffset[b][m_nConfigValue[b]]], 212, 48 + b * 30, 24);
      gg.drawImage(m_imgImage[1], 217, 48 + b * 30 + 8, 20);
      int i = m_Font.stringWidth(m_strText[m_aConfigTextOffset[b][m_nConfigValue[b]]]);
      gg.drawImage(m_imgImage[2], 212 - i - 10, 48 + b * 30 + 8, 20);
      TK_DrawStringC(m_strText[21 + b], 120, 8, 16777215, 0);
    } else if (m_nTitleMode == TITLE_MODE_TITLE) {
      TK_DrawBg(false);
      gg.drawRegion(m_imgImage[0], 0, 0, 190, 109, 0, 25, 56, 20);
      if (m_nPattern == 0) {
        gg.drawRegion(m_imgImage[0], 0, 159, 49, 57, 0, 97, 56 + (5 - m_nTimer) * 3, 20);
      } else if (m_nPattern == 1) {
        gg.drawRegion(m_imgImage[0], 0, 109, 56, 50, 0, 93, 61, 20);
      } else if (m_nPattern == 2) {
        gg.drawRegion(m_imgImage[0], 0, 109, 56, 50, 0, 93, 61, 20);
        gg.drawRegion(m_imgImage[0], 107, 188, 53, 36, 0, 94, 75, 20);
      } else if (m_nPattern == 3) {
        gg.drawRegion(m_imgImage[0], 62, 109, 60, 55, 0, 97, 56, 20);
      } else if (m_nPattern == 4) {
        gg.drawRegion(m_imgImage[0], 50, 164, 55, 57, 0, 97, 55, 20);
      } else if (m_nPattern == 5) {
        gg.drawRegion(m_imgImage[0], 127, 110, 35, 57, 0, 94, 54, 20);
        gg.drawRegion(m_imgImage[0], 163, 109, 27, 58, 0, 129, 53, 20);
      } else {
        gg.drawRegion(m_imgImage[0], 127, 110, 35, 57, 0, 94, 54, 20);
        gg.drawRegion(m_imgImage[0], 135, 167, 26, 21, 0, 98, 67, 20);
        if (m_nTimer / 2 % 2 == 0) {
          gg.drawRegion(m_imgImage[0], 163, 167, 27, 58, 0, 129, 53, 20);
        } else {
          gg.drawRegion(m_imgImage[0], 163, 109, 27, 58, 0, 129, 53, 20);
        } 
      } 
      gg.drawRegion(m_imgImage[0], 0, 224, 190, 56, 0, 25, 109, 20);
      TK_DrawStringC(m_strText[0], 120, 178, 16777215, 0);
      TK_DrawStringC(m_strText[1], 120, 204, 16777215, 0);
    } else if (m_nTitleMode == TITLE_MODE_TITLE_MENU) {
      gg.setColor(16777215);
      gg.fillRect(0, 0, 240, 240);
      TK_DrawBg(false);
      gg.drawRegion(m_imgImage[0], 0, 0, 190, 109, 0, 25, 56, 20);
      gg.drawRegion(m_imgImage[0], 127, 110, 35, 57, 0, 94, 54, 20);
      gg.drawRegion(m_imgImage[0], 135, 167, 26, 21, 0, 98, 67, 20);
      if (m_nTimer / 2 % 2 == 0) {
        gg.drawRegion(m_imgImage[0], 163, 167, 27, 58, 0, 129, 53, 20);
      } else {
        gg.drawRegion(m_imgImage[0], 163, 109, 27, 58, 0, 129, 53, 20);
      } 
      gg.drawRegion(m_imgImage[0], 0, 224, 190, 56, 0, 25, 109, 20);
      TK_DrawBelt(true, true);
      gg.drawImage(m_imgImage[1], 233, 218, 20);
      gg.drawImage(m_imgImage[2], 2, 218, 20);
      TK_DrawStringC(m_strText[2 + m_nSel], 120, 212, 16777215, 16386570);
      TK_DrawMarqueeTop();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING) {
      TK_DrawBg(true);
      TK_DrawBelt(true, true);
      gg.setColor(16777215);
      for (byte b = 0; b < 5; b++) {
        gg.drawString("" + (b + 1), 32, 48 + 30 * b, 24);
        gg.drawString("" + m_nHiScore[b], 130, 48 + 30 * b, 24);
        gg.drawString(m_strText[25 + m_nDifficulty[b]], 170, 48 + 30 * b, 17);
      } 
      TK_DrawStringC(m_strText[5], 120, 8, 16777215, 0);
      TK_DrawStringC(m_strText[12], 120, 212, 16777215, 0);
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_MENU) {
      TK_DrawBg(true);
      TK_DrawBelt(true, true);
      gg.setColor(16777215);
      gg.drawString(m_strText[13], 120, 43, 17);
      gg.drawString(m_strText[14], 120, 69, 17);
      gg.drawString(m_strText[15], 120, 95, 17);
      gg.drawString(m_strText[16], 120, 121, 17);
      gg.drawString(m_strText[17], 120, 147, 17);
      gg.drawString(m_strText[18], 120, 173, 17);
      int i = m_Font.stringWidth(m_strText[17 + m_nSel]);
      TK_DrawRing(120 - i / 2 - 18, 147 + m_nSel * 26);
      TK_DrawRing(120 + i / 2 + 2, 147 + m_nSel * 26);
    } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_DEL) {
      TK_DrawBg(true);
      TK_DrawBelt(true, true);
      gg.setColor(16777215);
      gg.drawString(m_strText[19], 120, 95, 17);
      gg.drawString(m_strText[20], 120, 122, 17);
    } else if (m_nTitleMode == TITLE_MODE_TITLE_CONFIG_MENU) {
      if (m_bDraw != 0) {
        TK_DrawBg(true);
        gg.setColor(16777215);
        int i;
        for (i = 0; i < 4; i++) {
          gg.drawString(m_strText[21 + i], 11, 48 + i * 30, 20);
          gg.drawString(m_strText[m_aConfigTextOffset[i][m_nConfigValue[i]]], 212, 48 + i * 30, 24);
        } 
        gg.drawImage(m_imgImage[1], 217, 48 + m_nSel * 30 + 8, 20);
        i = m_Font.stringWidth(m_strText[m_aConfigTextOffset[m_nSel][m_nConfigValue[m_nSel]]]);
        gg.drawImage(m_imgImage[2], 212 - i - 10, 48 + m_nSel * 30 + 8, 20);
        gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
        gg.setColor(34, 115, 251);
        gg.fillRect(11, 5, 218, 26);
        gg.setColor(6, 66, 148);
        gg.drawLine(10, 30, 10, 4);
        gg.drawLine(10, 4, 229, 4);
        gg.setColor(129, 205, 242);
        gg.drawLine(10, 31, 229, 31);
        gg.drawLine(229, 31, 229, 5);
        TK_DrawStringC(m_strText[6], 120, 8, 16777215, 0);
      } 
      gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
      gg.setColor(34, 115, 251);
      gg.fillRect(9, 207, 222, 30);
      gg.setColor(6, 66, 148);
      gg.drawLine(8, 236, 8, 206);
      gg.drawLine(8, 206, 231, 206);
      gg.setColor(129, 205, 242);
      gg.drawLine(8, 237, 231, 237);
      gg.drawLine(231, 237, 231, 207);
      TK_DrawMarqueeBottom();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_CONTINUE_MENU) {
      TK_DrawBg(true);
      TK_DrawBelt(true, true);
      gg.setColor(16777215);
      gg.drawString(m_strText[42], 120, 95, 17);
      gg.drawString(m_strText[43], 120, 121, 17);
      int i = m_Font.stringWidth(m_strText[42 + m_nSel]);
      TK_DrawRing(120 - i / 2 - 18, 95 + m_nSel * 26);
      TK_DrawRing(120 + i / 2 + 2, 95 + m_nSel * 26);
      TK_DrawMarqueeBottom();
    } else if (m_nTitleMode == TITLE_MODE_TITLE_HOWTO && m_bDraw != 0) {
      TK_DrawBg(true);
      TK_DrawBelt(true, false);
      if (m_HowToPicIndexTbl[m_nSel] >= 0)
        gg.drawRegion(m_imgImage[6], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][0], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][1], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][2], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][3], 0, 233, 196, 40); 
      int i = m_nSel * 7;
      if (m_nSel > 0) {
        gg.setColor(16777215);
        for (byte b = 0; b < 6; b++)
          gg.drawString(m_strHowToText[1 + i + b], 120, 43 + 26 * b, 17); 
        TK_DrawStringC(m_strHowToText[i], 120, 8, 16777215, 0);
      } else {
        gg.drawRegion(m_imgImage[6], m_HowToPicTbl[13][0], m_HowToPicTbl[13][1], m_HowToPicTbl[13][2], m_HowToPicTbl[13][3], 0, 15, 43, 20);
        gg.drawRegion(m_imgImage[6], m_HowToPicTbl[15][0], m_HowToPicTbl[15][1], m_HowToPicTbl[15][2], m_HowToPicTbl[15][3], 0, 25, 69, 20);
        gg.drawRegion(m_imgImage[6], m_HowToPicTbl[14][0], m_HowToPicTbl[14][1], m_HowToPicTbl[14][2], m_HowToPicTbl[14][3], 0, 25, 95, 20);
        gg.setColor(16777215);
        gg.drawString(m_strHowToText[1 + i + 0], 65, 43, 20);
        gg.drawString(m_strHowToText[1 + i + 1], 65, 69, 20);
        gg.drawString(m_strHowToText[1 + i + 2], 65, 95, 20);
        gg.drawString(m_strHowToText[1 + i + 3], 15, 121, 20);
        gg.drawString(m_strHowToText[1 + i + 4], 15, 147, 20);
        for (byte b = 5; b < 6; b++)
          gg.drawString(m_strHowToText[1 + i + b], 120, 43 + 26 * b, 17); 
      } 
      TK_DrawStringC(m_strHowToText[i], 120, 8, 16777215, 0);
      gg.setColor(34, 115, 251);
      gg.fillRect(15, 230, 210, 7);
      gg.setColor(6, 66, 148);
      gg.drawLine(14, 235, 14, 229);
      gg.drawLine(14, 229, 224, 229);
      gg.setColor(129, 205, 242);
      gg.drawLine(14, 236, 224, 236);
      gg.drawLine(224, 236, 224, 229);
      gg.fillRect(15 + m_nSel * 8, 230, 8, 5);
      gg.drawImage(m_imgImage[1], 227, 229, 20);
      gg.drawImage(m_imgImage[2], 7, 229, 20);
    } 
  }
  
  private void TK_DrawStringC(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    gg.setColor(paramInt4);
    gg.drawString(paramString, paramInt1 - 1, paramInt2, 17);
    gg.drawString(paramString, paramInt1 + 1, paramInt2, 17);
    gg.drawString(paramString, paramInt1, paramInt2 + 1, 17);
    gg.drawString(paramString, paramInt1, paramInt2 - 1, 17);
    gg.setColor(paramInt3);
    gg.drawString(paramString, paramInt1, paramInt2, 17);
  }
  
  private void TK_DrawBelt(boolean paramBoolean1, boolean paramBoolean2) {
    gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
    gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
    if (paramBoolean1) {
      gg.setColor(34, 115, 251);
      gg.fillRect(11, 5, 218, 26);
      gg.setColor(6, 66, 148);
      gg.drawLine(10, 30, 10, 4);
      gg.drawLine(10, 4, 229, 4);
      gg.setColor(129, 205, 242);
      gg.drawLine(10, 31, 229, 31);
      gg.drawLine(229, 31, 229, 5);
    } 
    if (paramBoolean2) {
      gg.setColor(34, 115, 251);
      gg.fillRect(9, 207, 222, 30);
      gg.setColor(6, 66, 148);
      gg.drawLine(8, 236, 8, 206);
      gg.drawLine(8, 206, 231, 206);
      gg.setColor(129, 205, 242);
      gg.drawLine(8, 237, 231, 237);
      gg.drawLine(231, 237, 231, 207);
    } 
  }
  
  private void TK_DrawRing(int paramInt1, int paramInt2) {
    gg.drawRegion(m_imgImage[4], 0, m_nRingPattern / 2 * 16, 16, 16, 0, paramInt1, paramInt2, 20);
  }
  
  private void TK_SetMarquee(int paramInt) {
    m_strMarquee = m_strText[paramInt];
    m_nMarqueePos = 0;
  }
  
  private void TK_SetMarquee(String paramString) {
    m_strMarquee = paramString;
    m_nMarqueePos = 0;
  }
  
  private void TK_MoveMarquee() {
    int i = (m_Font.stringWidth(m_strMarquee) + 218) / 6;
    m_nMarqueePos = (m_nMarqueePos + 1) % i;
  }
  
  private void TK_DrawMarqueeTop() {
    gg.setClip(12, 6, 216, 24);
    gg.setColor(255, 200, 200);
    this.MarqOfs = (m_nConfigValue[3] == 1) ? -2 : 0;
    gg.drawString(m_strMarquee, 216 - m_nMarqueePos * 6, 8 + this.MarqOfs, 20);
    gg.setClip(0, 0, 240, 240);
  }
  
  private void TK_DrawMarqueeBottom() {
    gg.setClip(10, 208, 220, 28);
    gg.setColor(255, 200, 200);
    this.MarqOfs = (m_nConfigValue[3] == 1) ? -2 : 0;
    gg.drawString(m_strMarquee, 216 - m_nMarqueePos * 6, 210 + this.MarqOfs, 20);
    gg.setClip(0, 0, 240, 240);
  }
  
  private void TK_DrawBg(boolean paramBoolean) {
    gg.drawImage(m_imgImage[5], 0, 0, 20);
    if (paramBoolean) {
      gg.setColor(0);
      for (byte b = 0; b < 120; b++)
        gg.drawLine(0, b * 2, 240, b * 2); 
    } 
  }
  
  private void TK_TitleInit(boolean paramBoolean) {
    ObjImageClear();
    SetSoftKey(-1);
    TK_LoadTextset();
    if (paramBoolean) {
      m_imgImage[0] = createImage("/t_license1.png");
      m_imgImage[1] = createImage("/t_license2.png");
      m_nTitleMode = TITLE_MODE_LICENSE_SEGA;
    } else {
      m_imgImage[0] = createImage("/t_title.png");
      m_imgImage[1] = createImage("/t_cur1.png");
      m_imgImage[2] = createImage("/t_cur2.png");
      m_imgImage[4] = createImage("/ring.png");
      m_imgImage[5] = createImage("/title_bg.png");
      m_imgImage[6] = createImage("/t_matome.png");
      if (m_bFirstSetUp != 0) {
        m_bFirstSetUp = 0;
        m_nConfigValue[3] = 1;
        TK_LoadTextset();
        m_nTitleMode = TITLE_MODE_FIRST_SETUP;
      } else {
        m_nTitleMode = TITLE_MODE_TITLE;
        SetSoftKey(0);
        PlayMusic(15);
      } 
    } 
    m_nTimer = 0;
    for (byte b = 0; b < 10; b++)
      m_OnKeyFlag[b] = false; 
    m_nRingPattern = 0;
  }
  
  private void TK_LoadTextset() {
    try {
      if (m_nConfigValue[3] == 0) {
        m_Font = Font.getFont(0, 0, 8);
      } else {
        m_Font = Font.getFont(0, 0, 16);
      } 
      in = getClass().getResourceAsStream("/lang_" + m_nConfigValue[3] + ".txt");
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(300);
      byte[] arrayOfByte = new byte[1];
      byte b = 0;
      int i;
      while ((i = in.read(arrayOfByte)) > 0) {
        if (arrayOfByte[0] == 13) {
          in.read(arrayOfByte);
          if (b < 51) {
            m_strText[b] = new String(byteArrayOutputStream.toByteArray(), "utf-8");
          } else {
            softKeys[b - 51] = new String(byteArrayOutputStream.toByteArray(), "utf-8");
          } 
          byteArrayOutputStream.reset();
          b++;
          continue;
        } 
        byteArrayOutputStream.write(arrayOfByte, 0, i);
      } 
      in.close();
      byteArrayOutputStream.close();
      byteArrayOutputStream = new ByteArrayOutputStream(300);
      in = getClass().getResourceAsStream("/manual_" + m_nConfigValue[3] + ".txt");
      b = 0;
      while ((i = in.read(arrayOfByte)) > 0) {
        if (arrayOfByte[0] == 13) {
          in.read(arrayOfByte);
          m_strHowToText[b] = new String(byteArrayOutputStream.toByteArray(), "utf-8");
          byteArrayOutputStream.reset();
          b++;
          continue;
        } 
        byteArrayOutputStream.write(arrayOfByte, 0, i);
      } 
      in.close();
      byteArrayOutputStream.close();
    } catch (Throwable throwable) {}
  }
  
  public void SetSoftKey(int paramInt) {
    removeCommand(cmd[1]);
    removeCommand(cmd[0]);
    cmd[1] = null;
    cmd[0] = null;
    if (paramInt == 0) {
      if (m_nConfigValue[1] == 0) {
        cmd[0] = new Command(m_strText[40], 1, 1);
      } else {
        cmd[0] = new Command(m_strText[41], 1, 1);
      } 
      cmd[1] = new Command(m_strText[39], 1, 1);
      addCommand(cmd[0]);
      addCommand(cmd[1]);
    } 
    if (paramInt == 1) {
      cmd[0] = new Command("", 1, 1);
      cmd[1] = new Command(m_strText[38], 1, 1);
      addCommand(cmd[0]);
      addCommand(cmd[1]);
    } 
    if (paramInt == 2) {
      if (m_nConfigValue[1] == 0) {
        cmd[0] = new Command(m_strText[40], 1, 1);
      } else {
        cmd[0] = new Command(m_strText[41], 1, 1);
      } 
      cmd[1] = new Command(m_strText[38], 1, 1);
      addCommand(cmd[0]);
      addCommand(cmd[1]);
    } 
  }
  
  public void commandAction(Command paramCommand, Displayable paramDisplayable) {
    if (paramCommand == cmd[1]) {
      clearKey();
      KeyPress[6] = true;
    } 
    if (paramCommand == cmd[0]) {
      clearKey();
      KeyPress[5] = true;
    } 
  }
  
  private void Vibrate(int paramInt) {
    if (paramInt == 0)
      paramInt = 100; 
    if (m_nConfigValue[2] == 1) {
      h.vibrate(paramInt);
    } else {
      h.vibrate(0);
    } 
  }
  
  private void StopVibrate(int paramInt) {
    h.vibrate(0);
  }
  
  private void InitViewControl() {
    this.mapViewType = 0;
    this.mapViewTypeTemp = -1;
    this.mapViewCount = 0;
    this.mapViewPri = 0;
    for (byte b = 0; b < 2; b++) {
      mapView[b] = mapOxy[b];
      mapViewTarget[b] = mapOxy[b];
      mapOfs[b] = 0;
      mapOfsTarget[b] = 0;
    } 
  }
  
  private void ForceViewControl(int paramInt) {
    this.mapViewCount = 20;
    this.mapViewTypeTemp = paramInt;
  }
  
  private void view_yuka(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 1 && paramInt3 != 21)
      return; 
    if (this.mapViewPri > 1)
      return; 
    if (this.mapViewCount > 1)
      return; 
    if (Math.abs(PlayerPosX() - paramInt1) < 300 && Math.abs(PlayerPosY() + 40 - paramInt2) < 120) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 10;
      this.mapViewPri = 1;
    } 
  }
  
  private void view_turi(int paramInt1, int paramInt2, int paramInt3) {
    if (this.mapViewPri > 2)
      return; 
    if (this.mapViewPri == 2 && this.mapViewCount > 1)
      return; 
    byte b = (objectData[4] == 35) ? 48 : 136;
    if (Math.abs(PlayerPosX() - paramInt1) < b && PlayerPosY() + 40 > paramInt2 && PlayerPosY() - 320 < paramInt2) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 8;
      this.mapViewPri = 2;
    } 
  }
  
  private void view_fblock(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 2 && paramInt3 != 10)
      return; 
    if (this.mapViewPri > 3)
      return; 
    if (this.mapViewPri == 3 && this.mapViewCount > 1)
      return; 
    if (this.mapViewCount < 2 && Math.abs(PlayerPosX() - paramInt1) < 128 && Math.abs(PlayerPosY() - paramInt2) < 96) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 7;
      this.mapViewPri = 3;
    } 
  }
  
  private void view_dai_ride(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 2)
      return; 
    if (this.mapViewPri > 4)
      return; 
    if (this.mapViewPri == 4 && this.mapViewCount > 1)
      return; 
    if (Math.abs(PlayerPosX() - paramInt1) < 96 && Math.abs(PlayerPosY() - paramInt2) < 96) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 5;
      this.mapViewPri = 4;
    } 
  }
  
  private void view_box_ride(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 1 && paramInt3 != 2)
      return; 
    if (this.mapViewPri > 4)
      return; 
    if (this.mapViewPri == 4 && this.mapViewCount > 1)
      return; 
    if (Math.abs(PlayerPosX() - paramInt1) < 96 && Math.abs(PlayerPosY() - paramInt2) < 96) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 5;
      this.mapViewPri = 4;
    } 
  }
  
  private void view_dai(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 2)
      return; 
    if (this.mapViewPri > 5)
      return; 
    if (this.mapViewPri == 5 && this.mapViewCount > 1)
      return; 
    if (Math.abs(PlayerPosX() - paramInt1) < 96 && Math.abs(PlayerPosY() - paramInt2) < 96) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 2;
      this.mapViewPri = 5;
    } 
  }
  
  private void view_box(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 != 1 && paramInt3 != 2)
      return; 
    if (this.mapViewPri > 5)
      return; 
    if (this.mapViewPri == 5 && this.mapViewCount > 1)
      return; 
    if (Math.abs(PlayerPosX() - paramInt1) < 96 && Math.abs(PlayerPosY() - paramInt2) < 96) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = 2;
      this.mapViewPri = 5;
    } 
  }
  
  private void ViewControl() {
    int i = PlayerPosX();
    int j = PlayerPosY();
    if (this.mapViewCount > 0) {
      this.mapViewCount--;
      if (this.mapViewCount == 0) {
        this.mapViewTypeTemp = -1;
        this.mapViewPri = 0;
      } 
    } 
    if (this.mapViewTypeTemp >= 0) {
      this.mapViewType = this.mapViewTypeTemp;
    } else {
      this.mapViewPri = 0;
      if (bossModeOn || bossBreakOn) {
        if (this.zoneNumber == 5 && mapOxy[0] < 1024) {
          this.mapViewType = 1;
        } else {
          this.mapViewType = 6;
        } 
      } else {
        switch (this.zoneNumber) {
          case 0:
            this.mapViewType = 1;
            break;
          case 1:
            this.mapViewType = 0;
            break;
          case 2:
            this.mapViewType = 1;
            switch (this.stageNumber) {
              case 0:
                if (Math.abs(4272 - i) < 120 && Math.abs(1024 - j) < 320) {
                  this.mapViewType = 8;
                  break;
                } 
                if (Math.abs(4672 - i) < 80 && Math.abs(688 - j) < 96)
                  this.mapViewType = 9; 
                break;
              case 1:
                if (Math.abs(2944 - i) < 640 && Math.abs(384 - j + 40) < 384)
                  this.mapViewType = 9; 
                break;
              case 2:
                if (Math.abs(1552 - i) < 160 && Math.abs(1120 - j + 40) < 96) {
                  this.mapViewType = 3;
                  break;
                } 
                if (Math.abs(5632 - i) < 512 && Math.abs(688 - j + 40) < 96)
                  this.mapViewType = 9; 
                break;
            } 
            break;
          case 3:
            this.mapViewType = 1;
            break;
          case 4:
            this.mapViewType = 1;
            if (this.stageNumber == 1) {
              if (Math.abs(i - 7296) < 240 && Math.abs(j - 640) < 80) {
                this.mapViewType = 2;
                break;
              } 
              if (Math.abs(i - 8464) < 240 && Math.abs(j - 640) < 80)
                this.mapViewType = 2; 
            } 
            break;
          case 5:
            this.mapViewType = 0;
            break;
        } 
      } 
    } 
    switch (this.mapViewType) {
      default:
        mapView[0] = mapOxy[0];
        mapView[1] = mapOxy[1];
        return;
      case 1:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 0;
        break;
      case 2:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 32;
        break;
      case 3:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 40;
        break;
      case 4:
        if (PlayerParam[5] > 0) {
          mapOfsTarget[0] = 0;
          mapOfsTarget[1] = 48;
          break;
        } 
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 0;
        break;
      case 5:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = -16;
        break;
      case 6:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 0;
        break;
      case 7:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = 16;
        break;
      case 8:
        mapOfsTarget[0] = 0;
        mapOfsTarget[1] = -32;
        break;
      case 9:
        mapOfsTarget[0] = 36;
        mapOfsTarget[1] = 16;
        break;
      case 10:
        mapOfsTarget[0] = 32;
        mapOfsTarget[1] = 40;
        break;
    } 
    int k = Math.abs(mapOxy[0] - mapViewTarget[0]);
    int m = Math.abs(mapOxy[1] - mapViewTarget[1]);
    if (this.mapViewType == 6) {
      if (k < 4 || k > 256) {
        mapViewTarget[0] = mapOxy[0];
      } else {
        mapViewTarget[0] = mapViewTarget[0] + ((mapOxy[0] > mapViewTarget[0]) ? 4 : -4);
      } 
      if (m < 4 || m > 256) {
        mapViewTarget[1] = mapOxy[1];
      } else {
        mapViewTarget[1] = mapViewTarget[1] + ((mapOxy[1] > mapViewTarget[1]) ? 4 : -4);
      } 
    } else {
      if (k < 32 || k > 256) {
        mapViewTarget[0] = mapOxy[0];
      } else {
        mapViewTarget[0] = mapViewTarget[0] + ((mapOxy[0] > mapViewTarget[0]) ? 32 : -32);
      } 
      if (m < 16 || m > 256) {
        mapViewTarget[1] = mapOxy[1];
      } else if (m > 32) {
        mapViewTarget[1] = (mapOxy[1] > mapViewTarget[1]) ? (mapViewTarget[1] + 32) : (mapViewTarget[1] - 32);
      } else {
        mapViewTarget[1] = mapViewTarget[1] + ((mapOxy[1] > mapViewTarget[1]) ? 16 : -16);
      } 
    } 
    if (mapOfs[0] != mapOfsTarget[0])
      mapOfs[0] = mapOfs[0] + ((mapOfs[0] < mapOfsTarget[0]) ? 1 : -1); 
    mapView[0] = mapViewTarget[0] + mapOfs[0];
    if (mapOfs[1] != mapOfsTarget[1])
      mapOfs[1] = mapOfs[1] + ((mapOfs[1] < mapOfsTarget[1]) ? 1 : -1); 
    mapView[1] = mapViewTarget[1] + mapOfs[1];
    int n = 112;
    if (this.LookUpCount > 0)
      n += this.LookUpCount << 1; 
    if (this.CrouchCount > 0)
      n -= this.CrouchCount << 1; 
    int i1 = this.poslimit[3] - n;
    if (mapView[1] > i1)
      mapView[1] = i1; 
  }
  
  private void DoGc() {
    try {
      System.gc();
      Thread.sleep(200L);
    } catch (Throwable throwable) {}
  }
  
  private void moveSysString() {
    if (this.goleFlag && this.golecount > 0) {
      this.golecount--;
      return;
    } 
    if (this.scoreMoveFlag)
      if (this.scoreGetcount < 0) {
        this.limitBreak = false;
        this.scoreMoveFlag = false;
        this.goleFlag = false;
        plsaveX = 0;
        plsaveY = 0;
        plsaveTime = 0;
        plsaveTime2 = 0;
        this.noTimeScore = false;
        this.selectStageNumber = (this.selectStageNumber + 1) % 3;
        if (this.selectStageNumber == 0)
          this.selectZoneNumber++; 
        this.resumeStage = (byte)(this.selectStageNumber + this.selectZoneNumber * 3);
        this.resumeZanki = (byte)playercount;
        this.resumeScore = scorecount;
        if (this.clearStageData < this.resumeStage)
          this.clearStageData = this.resumeStage; 
        save_resu();
        this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
        this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
        readStageObject();
        readStageObjectFlag = false;
        countClear();
        objectInit(this.stageNumber);
        initStageStart();
      } else {
        this.scoreGetcount--;
        if (this.scoreGetcount == 10)
          PlayMusic(28); 
        if (this.scoreGetcountMax - this.scoreGetcount > 15)
          for (byte b1 = 0; b1 < 40; b1++) {
            if (this.resultTime > 0) {
              this.resultTime -= 10;
              addScoreCount(10, 0);
            } 
            if (this.resultRing > 0) {
              this.resultRing -= 10;
              addScoreCount(10, 0);
            } 
          }  
      }  
    if (this.goleFlag) {
      this.limitBreak = true;
      PlayerParam[12] = 0;
      PlayerParam[10] = PlayerParam[10] + 128;
    } 
    for (byte b = 0; b < this.SysString.length; b++) {
      if (this.SysString[b][0] == 1 && this.SysCount >= this.SysString[b][9])
        for (byte b1 = 0; b1 < 24; b1++) {
          if (this.SysCenter + this.SysString[b][8] > this.SysString[b][2]) {
            this.SysString[b][2] = this.SysString[b][2] + 1;
          } else if (this.SysCenter + this.SysString[b][8] < this.SysString[b][2]) {
            this.SysString[b][2] = this.SysString[b][2] - 1;
          } else {
            this.SysCount++;
            this.SysString[b][0] = 2;
            if (this.goleFlag) {
              if (this.SysCount >= 5) {
                DG();
                this.scoreMoveFlag = true;
                mutekicount = -1;
                this.resultRing = ringcount * 100;
                this.resultTime = 0;
                if (timecount < 30 && timecount2 == 0 && !this.noTimeScore) {
                  this.resultTime = 50000;
                } else if (timecount < 45 && timecount2 == 0) {
                  this.resultTime = 10000;
                } else if (timecount2 < 1) {
                  this.resultTime = 5000;
                } else if (timecount < 30 && timecount2 == 1) {
                  this.resultTime = 4000;
                } else if (timecount2 < 2) {
                  this.resultTime = 3000;
                } else if (timecount < 30 && timecount2 == 2) {
                  this.resultTime = 2000;
                } else {
                  this.resultTime = 1000;
                } 
                if (this.resultTime > this.resultRing) {
                  this.scoreGetcountMax = this.scoreGetcount = this.resultTime / 240 + 30;
                  break;
                } 
                this.scoreGetcountMax = this.scoreGetcount = this.resultRing / 240 + 30;
              } 
              break;
            } 
            if (this.SysCount >= 5) {
              this.putNowLoading = true;
              DG();
              if (readStageObjectFlag) {
                readStageObject();
                readStageObjectFlag = false;
                plsaveX = 0;
                plsaveY = 0;
                plsaveTime = 0;
                plsaveTime2 = 0;
                this.noTimeScore = false;
                endStageStart();
              } else {
                objectInit(this.stageNumber);
                endStageStart();
              } 
              this.putNowLoading = false;
            } 
            break;
          } 
        }  
    } 
    if (mode == MODE_FIELD && !this.goleFlag) {
      this.SysCount++;
      if (this.SysCount >= 20)
        if (this.SysCount < 30) {
          this.SysString[0][2] = this.SysString[0][2] + 48;
          this.SysString[1][2] = this.SysString[1][2] + 48;
          this.SysString[2][2] = this.SysString[2][2] + 48;
          this.SysString[3][2] = this.SysString[3][2] - 48;
          this.SysString[4][2] = this.SysString[4][2] - 48;
        } else {
          this.SysString[0][0] = 0;
          this.SysString[1][0] = 0;
          this.SysString[2][0] = 0;
          this.SysString[3][0] = 0;
          this.SysString[4][0] = 0;
        }  
    } 
  }
  
  private void drawSysString() {
    byte b;
    for (b = 0; b < this.SysString.length; b++) {
      if (this.SysString[b][0] >= 1 && (this.zoneNumber != 5 || this.stageNumber != 3 || (this.SysString[b][1] != this.ACT && this.SysString[b][1] != this.ACT1)))
        gg.drawRegion(this.m_imgCmd[SYSTXT], this.SysString[b][4], this.SysString[b][5], this.SysString[b][6], this.SysString[b][7], rotNumTable[TRANS_NONE], this.SysString[b][2], this.SysString[b][3], 20); 
    } 
    if (this.scoreMoveFlag) {
      int[] arrayOfInt = { scorecount, this.resultTime, this.resultRing };
      for (b = 0; b < 3; b++) {
        int i = 240 - (this.scoreGetcountMax - this.scoreGetcount) * 24 + b * 12;
        if (i < 44)
          i = 44; 
        gg.drawRegion(this.m_imgCmd[SYSTXT2], 0, b * 16, 84, 16, rotNumTable[TRANS_NONE], i, 124 + 16 * b, 20);
        drawNumber2(i + 152, 124 + 16 * b - 36, arrayOfInt[b]);
      } 
    } 
  }
  
  public void run() {
    long l = System.currentTimeMillis();
    while (true) {
      try {
        if (bPauseMusic) {
          if (this.player1 != null && this.player1.getState() == 400)
            try {
              this.player1.stop();
            } catch (Throwable throwable) {} 
        } else if (musicRetry > 0) {
          int i = musicRetry;
          PlayMusic(musicRequest);
          musicRetry = i;
          musicRetry--;
        } else if (this.bDoPlay) {
          if (!this.scoreMoveFlag)
            switch (musicNum) {
              case 1:
                PlayMusic(2);
                break;
              case 3:
                PlayMusic(4);
                break;
              case 5:
                PlayMusic(6);
                break;
              case 7:
                PlayMusic(8);
                break;
              case 9:
                PlayMusic(10);
                break;
              case 18:
                PlayMusic(19);
                break;
              case 12:
              case 13:
              case 24:
              case 25:
              case 27:
                PlayZoneBGML();
                break;
            }  
          this.bDoPlay = false;
        } 
        l = System.currentTimeMillis();
        Thread.sleep(50L);
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  private void ResetSound() {
    musicNum = -1;
    musicCount = 0;
    musicRetry = 0;
    musicRequest = -1;
    this.scoreMoveFlag = false;
    bPauseMusic = false;
    bGoalMusic = false;
    if (this.player1 != null)
      try {
        this.player1.close();
      } catch (Throwable throwable) {} 
    this.player1 = null;
    if (this.is1 != null)
      try {
        this.is1.close();
      } catch (Throwable throwable) {} 
    this.is1 = null;
  }
  
  private void InitSound() {
    ResetSound();
    Thread thread = new Thread(this);
    thread.start();
    thread.setPriority(10);
  }
  
  public void playerUpdate(Player paramPlayer, String paramString, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface getState : ()I
    //   6: lookupswitch default -> 75, 0 -> 68, 100 -> 56, 200 -> 59, 300 -> 62, 400 -> 65
    //   56: goto -> 75
    //   59: goto -> 75
    //   62: goto -> 75
    //   65: goto -> 75
    //   68: iconst_m1
    //   69: putstatic MainCanvas.musicNum : I
    //   72: goto -> 75
    //   75: aload_2
    //   76: ldc_w 'endOfMedia'
    //   79: if_acmpne -> 180
    //   82: getstatic MainCanvas.musicNum : I
    //   85: tableswitch default -> 172, 1 -> 175, 2 -> 172, 3 -> 175, 4 -> 172, 5 -> 175, 6 -> 172, 7 -> 175, 8 -> 172, 9 -> 175, 10 -> 172, 11 -> 172, 12 -> 172, 13 -> 172, 14 -> 172, 15 -> 172, 16 -> 172, 17 -> 172, 18 -> 175
    //   172: goto -> 180
    //   175: aload_0
    //   176: iconst_1
    //   177: putfield bDoPlay : Z
    //   180: aload_2
    //   181: ldc_w 'endOfMedia'
    //   184: if_acmpne -> 207
    //   187: aload_0
    //   188: getfield player1 : Ljavax/microedition/media/Player;
    //   191: invokeinterface getState : ()I
    //   196: sipush #300
    //   199: if_icmpne -> 207
    //   202: aload_0
    //   203: iconst_1
    //   204: putfield bDoPlay : Z
    //   207: aload_2
    //   208: ldc_w 'stopped'
    //   211: if_acmpeq -> 228
    //   214: aload_2
    //   215: ldc_w 'closed'
    //   218: if_acmpeq -> 228
    //   221: aload_2
    //   222: ldc_w 'endOfMedia'
    //   225: if_acmpne -> 228
    //   228: aload_2
    //   229: ldc_w 'volumeChanged'
    //   232: if_acmpne -> 235
    //   235: return
  }
  
  private boolean _playMusic(String paramString, int paramInt) {
    boolean bool = true;
    bPauseMusic = false;
    System.gc();
    try {
      if (this.is1 != null) {
        this.is1.close();
        this.is1 = null;
      } 
      this.is1 = getClass().getResourceAsStream("/bgm/" + paramString + ".mmf");
      if (this.player1 != null) {
        this.player1.close();
        this.player1 = null;
      } 
      this.player1 = Manager.createPlayer(this.is1, "application/x-smaf");
      this.player1.realize();
      this.player1.addPlayerListener(this);
      this.player1.setLoopCount(paramInt);
      VolumeMusic();
      this.player1.prefetch();
      this.player1.start();
      musicNum = musicRequest;
      musicRequest = -1;
      musicRetry = 0;
    } catch (MediaException mediaException) {
      if (this.player1 != null)
        this.player1.close(); 
      this.player1 = null;
      DoGc();
      bool = false;
    } catch (IOException iOException) {
      if (this.player1 != null)
        this.player1.close(); 
      this.player1 = null;
      DoGc();
      bool = false;
    } catch (Throwable throwable) {
      if (this.player1 != null)
        this.player1.close(); 
      this.player1 = null;
      DoGc();
      bool = false;
    } 
    if (this.is1 != null)
      try {
        this.is1.close();
        this.is1 = null;
      } catch (Throwable throwable) {} 
    return bool;
  }
  
  private void PlayMusic(int paramInt) {
    boolean bool = false;
    if (paramInt == 20)
      bGoalMusic = true; 
    if (bGoalMusic && paramInt < 12)
      return; 
    switch (paramInt) {
      default:
        StopMusic();
        return;
      case 1:
        bool = _playMusic("81_1", 1);
        break;
      case 2:
        bool = _playMusic("81_2", -1);
        break;
      case 3:
        bool = _playMusic("82_1", 1);
        break;
      case 4:
        bool = _playMusic("82_2", -1);
        break;
      case 5:
        bool = _playMusic("83_1", 1);
        break;
      case 6:
        bool = _playMusic("83_2", -1);
        break;
      case 7:
        bool = _playMusic("84_1", 1);
        break;
      case 8:
        bool = _playMusic("84_2", -1);
        break;
      case 9:
        bool = _playMusic("85_1", 1);
        break;
      case 10:
        bool = _playMusic("85_2", -1);
        break;
      case 11:
        bool = _playMusic("86", -1);
        break;
      case 12:
        bool = _playMusic("87", -1);
        break;
      case 13:
        bool = _playMusic("88", 1);
        break;
      case 14:
        bool = _playMusic("89", -1);
        break;
      case 15:
        bool = _playMusic("8a", 1);
        break;
      case 16:
        bool = _playMusic("8b", 1);
        break;
      case 17:
        bool = _playMusic("8c", -1);
        break;
      case 18:
        bool = _playMusic("8d_1", 1);
        break;
      case 19:
        bool = _playMusic("8d_2", -1);
        break;
      case 20:
        bool = _playMusic("8e", 1);
        break;
      case 21:
        bool = _playMusic("8f", 1);
        break;
      case 22:
        bool = _playMusic("90", 1);
        break;
      case 23:
        bool = _playMusic("91", 1);
        break;
      case 24:
        bool = _playMusic("92", -1);
        break;
      case 25:
        bool = _playMusic("93", 1);
        break;
      case 26:
        bool = _playMusic("SEGA", 1);
        break;
      case 27:
        bool = _playMusic("ad", 1);
        break;
      case 28:
        bool = _playMusic("c5", 1);
        break;
      case 29:
        bool = _playMusic("b2", 1);
        break;
      case 30:
        bool = _playMusic("88", 1);
        break;
    } 
    if (bool) {
      musicNum = paramInt;
      musicRequest = -1;
      musicRetry = 0;
    } else {
      musicRequest = paramInt;
      musicRetry = 30;
    } 
  }
  
  private void PauseMusic() {
    try {
      this.player1.stop();
    } catch (MediaException mediaException) {
      mediaException.printStackTrace();
    } 
    bPauseMusic = true;
  }
  
  private void RestartMusic() {
    bPauseMusic = false;
    if (this.player1 != null)
      try {
        VolumeMusic();
        this.player1.start();
      } catch (MediaException mediaException) {
        mediaException.printStackTrace();
      }  
  }
  
  private void StopMusic() {
    if (this.player1 != null)
      try {
        this.player1.close();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }  
    this.player1 = null;
    musicNum = -1;
    musicCount = 0;
    musicRetry = 0;
    musicRequest = -1;
    bPauseMusic = false;
    this.bDoPlay = false;
  }
  
  private void _setMusicVol(int paramInt) {
    if (this.player1 != null && this.player1.getState() != 0)
      try {
        VolumeControl volumeControl = (VolumeControl)this.player1.getControl("VolumeControl");
        if (volumeControl != null)
          volumeControl.setLevel(paramInt); 
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }  
  }
  
  private void VolumeMusic() {
    switch (m_nConfigValue[1]) {
      case 0:
        _setMusicVol(0);
        break;
      case 1:
        _setMusicVol(20);
        break;
      case 2:
        _setMusicVol(36);
        break;
      case 3:
        _setMusicVol(100);
        break;
    } 
  }
  
  private void MuteMusic(boolean paramBoolean) {
    if (this.player1 != null && this.player1.getState() != 0)
      try {
        VolumeControl volumeControl = (VolumeControl)this.player1.getControl("VolumeControl");
        if (volumeControl != null)
          volumeControl.setMute(paramBoolean); 
        if (!paramBoolean)
          VolumeMusic(); 
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }  
  }
  
  private int GetZoneBGMNum(boolean paramBoolean) {
    if (this.bressCount < 600)
      return 24; 
    if (mutekicount > 0)
      return 12; 
    if (bossModeOn)
      return (this.zoneNumber == 5 && this.stageNumber == 3) ? 18 : 17; 
    if (paramBoolean) {
      switch (this.zoneNumber) {
        default:
          return 2;
        case 0:
          return 2;
        case 1:
          return (this.stageNumber == 3) ? 11 : 4;
        case 2:
          return 6;
        case 3:
          return 8;
        case 4:
          return 10;
        case 5:
          return (this.stageNumber == 3) ? 18 : 11;
        case 6:
          break;
      } 
      return 14;
    } 
    switch (this.zoneNumber) {
      default:
        return 2;
      case 0:
        return 1;
      case 1:
        return (this.stageNumber == 3) ? 11 : 3;
      case 2:
        return 5;
      case 3:
        return 7;
      case 4:
        return 9;
      case 5:
        return (this.stageNumber == 3) ? 19 : 11;
      case 6:
        break;
    } 
    return 14;
  }
  
  private void PlayZoneBGM() {
    PlayMusic(GetZoneBGMNum(false));
  }
  
  private void PlayZoneBGML() {
    PlayMusic(GetZoneBGMNum(true));
  }
  
  private void AraiLoadStageImage(int paramInt) {
    try {
      this.m_imgObj[100] = createImage("/animal.png");
      switch (paramInt) {
        default:
          this.m_imgObj[41] = createImage("/musi.png");
          this.m_imgObj[40] = createImage("/hachi.png");
          this.m_imgObj[86] = createImage("/fish.png");
          this.m_imgObj[39] = createImage("/kamere.png");
          this.m_imgObj[57] = createImage("/kani.png");
          break;
        case 2:
          this.m_imgObj[101] = createImage("/fire.png");
          this.m_imgObj[81] = createImage("/mogura.png");
          this.m_imgObj[87] = createImage("/fish2.png");
          this.m_imgObj[74] = createImage("/uni.png");
          break;
        case 3:
          this.m_imgObj[101] = createImage("/fire.png");
          this.m_imgObj[40] = createImage("/hachi.png");
          this.m_imgObj[49] = createImage("/imo.png");
          this.m_imgObj[78] = createImage("/bat.png");
          break;
        case 4:
          this.m_imgObj[101] = createImage("/fire.png");
          this.m_imgObj[50] = createImage("/brobo.png");
          this.m_imgObj[74] = createImage("/uni.png");
          break;
        case 5:
          this.m_imgObj[101] = createImage("/fire.png");
          this.m_imgObj[40] = createImage("/hachi.png");
          this.m_imgObj[57] = createImage("/kani.png");
          this.m_imgObj[71] = createImage("/yado.png");
          this.m_imgObj[70] = createImage("/aruma.png");
          this.m_imgObj[102] = createImage("/block.png");
          break;
        case 6:
          if (this.stageNumber != 3) {
            this.m_imgObj[101] = createImage("/fire.png");
            this.m_imgObj[49] = createImage("/imo.png");
            this.m_imgObj[50] = createImage("/brobo.png");
            this.m_imgObj[51] = createImage("/buta.png");
          } 
          break;
        case 7:
          break;
      } 
    } catch (Throwable throwable) {}
  }
  
  private boolean SaveRecordStore(byte[] paramArrayOfbyte, String paramString) {
    RecordStore recordStore = null;
    try {
      deleteRecordStore(paramString);
      recordStore = RecordStore.openRecordStore(paramString, true);
      recordStore.addRecord(paramArrayOfbyte, 0, paramArrayOfbyte.length);
      recordStore.closeRecordStore();
    } catch (Throwable throwable) {
      try {
        recordStore.closeRecordStore();
      } catch (Throwable throwable1) {}
      return false;
    } 
    return true;
  }
  
  private void deleteRecordStore(String paramString) {
    RecordStore recordStore = null;
    try {
      try {
        recordStore = RecordStore.openRecordStore(paramString, false);
        recordStore.closeRecordStore();
        RecordStore.deleteRecordStore(paramString);
      } catch (Throwable throwable) {
        try {
          recordStore.closeRecordStore();
        } catch (Throwable throwable1) {}
      } 
      recordStore.closeRecordStore();
    } catch (Throwable throwable) {
      try {
        recordStore.closeRecordStore();
      } catch (Throwable throwable1) {}
    } 
  }
  
  private byte[] LoadRecordStore(String paramString) {
    RecordStore recordStore = null;
    byte[] arrayOfByte = null;
    try {
      recordStore = RecordStore.openRecordStore(paramString, false);
      arrayOfByte = new byte[recordStore.getRecordSize(1)];
      recordStore.getRecord(1, arrayOfByte, 0);
      recordStore.closeRecordStore();
    } catch (Throwable throwable) {
      try {
        recordStore.closeRecordStore();
      } catch (Throwable throwable1) {}
    } 
    return arrayOfByte;
  }
  
  private int GetDrawRot(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 1:
        return 5;
      case 2:
        return 3;
      case 3:
        break;
    } 
    return 6;
  }
  
  private boolean _CharaDefault(int[] paramArrayOfint) {
    if (paramArrayOfint[14] == 0) {
      paramArrayOfint[5] = 0;
      paramArrayOfint[6] = 0;
      paramArrayOfint[7] = 0;
      paramArrayOfint[10] = 0;
      paramArrayOfint[11] = 0;
      paramArrayOfint[12] = paramArrayOfint[2] * 100;
      paramArrayOfint[13] = paramArrayOfint[3] * 100;
      paramArrayOfint[15] = -1;
      paramArrayOfint[16] = paramArrayOfint[2];
      paramArrayOfint[17] = paramArrayOfint[3];
      paramArrayOfint[14] = paramArrayOfint[14] + 1;
    } 
    if (paramArrayOfint[5] > 0)
      paramArrayOfint[5] = paramArrayOfint[5] - 1; 
    paramArrayOfint[6] = paramArrayOfint[6] + 1;
    if (paramArrayOfint[7] > 0)
      paramArrayOfint[7] = paramArrayOfint[7] - 1; 
    return false;
  }
  
  private void AraiMoveStand(int[] paramArrayOfint) {
    AraiMoveStand(paramArrayOfint, (objectSizeTbl[paramArrayOfint[1]][1] >> 1) - 2);
  }
  
  private void AraiMoveStand(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint[3];
    int j = i + paramInt;
    if (i < 99999)
      i++; 
    if (blockColChk_Enemy(paramArrayOfint[2], j)) {
      i -= 2;
      j = i + paramInt;
    } 
    if (i < 0)
      i = 0; 
    paramArrayOfint[3] = i;
  }
  
  private void AraiDirChangeX(int[] paramArrayOfint) {
    if (paramArrayOfint[19] == 0) {
      paramArrayOfint[19] = 1;
    } else {
      paramArrayOfint[19] = 0;
    } 
  }
  
  private int GetEnemyFloorY(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2 + paramInt3;
    if (blockColChk_Enemy(paramInt1, i)) {
      for (byte b = 0; b < 8 && blockColChk_Enemy(paramInt1, --i); b++);
    } else {
      for (byte b = 0; b < 8 && !blockColChk_Enemy(paramInt1, i + 1); b++)
        i++; 
    } 
    i -= paramInt3;
    if (i < 0)
      i = 0; 
    return i;
  }
  
  private boolean CheckSlide(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt5 == 0) {
      if (paramInt1 - paramInt3 - 1 < 0)
        return true; 
      if (blockColChk_Enemy(paramInt1 - paramInt3 - 1, paramInt2 - 12))
        return true; 
      if (!blockColChk_Enemy(paramInt1 - paramInt3 - 1, paramInt2 + paramInt4 + 10))
        return true; 
    } else {
      if (blockColChk_Enemy(paramInt1 + paramInt3 + 1, paramInt2 - 12))
        return true; 
      if (!blockColChk_Enemy(paramInt1 + paramInt3 + 1, paramInt2 + paramInt4 + 10))
        return true; 
    } 
    return false;
  }
  
  private boolean CheckSlideInverse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt5 == 0) {
      if (paramInt1 - paramInt3 - 1 < 0)
        return true; 
      if (blockColChk_Enemy(paramInt1 - paramInt3 - 1, paramInt2 - 8))
        return true; 
      if (!blockColChk_Enemy(paramInt1 - paramInt3 - 1, paramInt2 - paramInt4 - 12))
        return true; 
    } else {
      if (blockColChk_Enemy(paramInt1 + paramInt3 + 1, paramInt2 - 8))
        return true; 
      if (!blockColChk_Enemy(paramInt1 + paramInt3 + 1, paramInt2 - paramInt4 - 12))
        return true; 
    } 
    return false;
  }
  
  boolean AraiCheckSlide(int[] paramArrayOfint) {
    int i = objectSizeTbl[paramArrayOfint[1]][0] >> 1;
    int j = objectSizeTbl[paramArrayOfint[1]][1] >> 1;
    return CheckSlide(paramArrayOfint[2], paramArrayOfint[3], i, j, paramArrayOfint[19] & 0x1);
  }
  
  boolean AraiCheckInside(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int i = PlayerPosX();
    int j = PlayerPosY() - (PlayerBall ? 16 : 20);
    if (paramArrayOfint[3] - paramInt2 > j || j > paramArrayOfint[3] + paramInt2)
      return false; 
    switch (paramArrayOfint[19]) {
      case 0:
        if (paramArrayOfint[2] - paramInt1 < i && i < paramArrayOfint[2])
          return true; 
        break;
      case 1:
        if (paramArrayOfint[2] < i && i < paramArrayOfint[2] + paramInt1)
          return true; 
        break;
    } 
    return false;
  }
  
  private boolean IsFarDistance(int paramInt1, int paramInt2) {
    return (Math.abs(paramInt1 - PlayerPosX()) > 240 || Math.abs(paramInt2 - PlayerPosY()) > 240);
  }
  
  private boolean IsDistance(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt1 - PlayerPosX();
    int j = paramInt2 - PlayerPosY() - (PlayerBall ? 16 : 20);
    return (i * i + j * j < paramInt3 * paramInt3);
  }
  
  private boolean DebugNearCheck(int paramInt1, int paramInt2) {
    return (Math.abs(paramInt1 - PlayerPosX()) < 160 && Math.abs(paramInt2 - PlayerPosY()) < 120);
  }
  
  private int IsHitSonic(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    if (PlayerJump == true && PlayerDamage == true)
      return 0; 
    byte b1 = 12;
    byte b2 = (PlayerBall || PlayerCrouch) ? 12 : 16;
    int i = PlayerPosX();
    int j = PlayerPosY() - b2;
    int k = (paramInt3 >> 1) + b1;
    int m = (paramInt4 >> 1) + b2;
    if (PlayerBall == true) {
      if (i - k < paramInt1 && paramInt1 < i + k && j - m < paramInt2 && paramInt2 < j + m)
        return (mutekicount > 0) ? 1 : (paramBoolean ? 1 : 2); 
    } else if (i - k < paramInt1 && paramInt1 < i + k && j - m < paramInt2 && paramInt2 < j + m) {
      return (mutekicount > 0) ? 1 : 2;
    } 
    return 0;
  }
  
  private boolean AraiCharaHitCheck(int[] paramArrayOfint) {
    if (debugFlag)
      return false; 
    short s1 = objectSizeTbl[paramArrayOfint[1]][0];
    short s2 = objectSizeTbl[paramArrayOfint[1]][1];
    int i = IsHitSonic(paramArrayOfint[2], paramArrayOfint[3], s1, s2, true);
    if (paramArrayOfint[1] == 71 && i == 1) {
      if (paramArrayOfint[3] - 4 > PlayerPosY() - 16)
        i = 2; 
    } else if (paramArrayOfint[1] == 50 && i == 1) {
      i = 2;
    } 
    if (i == 1) {
      if (PlayerParam[5] > 0)
        PlayerParam[5] = (PlayerParam[5] > 2560) ? -2560 : -PlayerParam[5]; 
      if (comboScore == 0) {
        comboScore = 100;
      } else if (comboScore == 100) {
        comboScore = 200;
      } else if (comboScore == 200) {
        comboScore = 500;
      } else if (comboScore == 500) {
        comboScore = 1000;
      } 
      addScoreCount(comboScore);
      ShotScore(paramArrayOfint[2], paramArrayOfint[3], comboScore);
      SetObj2(2, paramArrayOfint[2], paramArrayOfint[3], 0, 0, 0, 0);
      SetObj2(friendTbl[this.zoneNumber][rnd(2)], paramArrayOfint[2], paramArrayOfint[3], 0, -300, 0, 0);
      paramArrayOfint[0] = 0;
      return true;
    } 
    if (i == 2) {
      playdamageset();
      return false;
    } 
    return false;
  }
  
  private void AraiMoveTest(int[] paramArrayOfint) {
    byte b = (paramArrayOfint[19] == 0) ? -1 : 1;
    if (_CharaDefault(paramArrayOfint))
      return; 
    paramArrayOfint[15] = this.animeTimer;
    paramArrayOfint[2] = paramArrayOfint[2] + b;
    AraiMoveStand(paramArrayOfint);
    if (AraiCheckSlide(paramArrayOfint) || paramArrayOfint[6] > 180) {
      AraiDirChangeX(paramArrayOfint);
      paramArrayOfint[6] = 0;
    } 
  }
  
  private void sisoo_shot_tama(int paramInt1, int paramInt2) {
    if (objectData[12] == 1) {
      if (paramInt2 == 0) {
        if ((objectData[6] >> 8) - objectData[2] >= 0) {
          objectData[10] = -276;
        } else {
          objectData[10] = 276;
        } 
        objectData[11] = -2072;
      } else if (paramInt2 == 1) {
        if ((objectData[6] >> 8) - objectData[2] >= 0) {
          objectData[10] = -204;
        } else {
          objectData[10] = 204;
        } 
        objectData[11] = -2800;
      } else if (paramInt2 == 2) {
        if ((objectData[6] >> 8) - objectData[2] >= 0) {
          objectData[10] = -160;
        } else {
          objectData[10] = 160;
        } 
        objectData[11] = -3584;
      } 
      objectData[12] = 0;
      objectData[7] = objectData[7] - 9216;
      objectData[18] = 1;
    } 
  }
  
  private void sleep(int paramInt) {}
  
  private void sisoo_nflag_move_arai(int paramInt) {
    byte b1 = 40;
    byte b2 = 21;
    boolean bool = false;
    if (objectData[6] == 0 && objectData[7] == 0) {
      objectData[6] = objectData[8] + 32 << 8;
      objectData[7] = objectData[3] - b2 - 4 + 20 << 8;
      if (objectData[4] != 255)
        objectData[12] = 1; 
    } 
    int[] arrayOfInt1 = new int[2];
    arrayOfInt1[0] = objectData[6] >> 8;
    arrayOfInt1[1] = objectData[7] >> 8;
    if (objectData[4] == 255 && objectData[13] == 1)
      if (objectData[14] > 0) {
        objectData[14] = objectData[14] - 1;
      } else if (objectData[12] != 0) {
        if (objectData[18] == 0) {
          ShotBomb(objectData[6] >> 8, objectData[7] >> 8);
        } else {
          SetObj2(3, objectData[6] >> 8, objectData[7] >> 8, 0, 0, 0, 0);
        } 
        objectData[13] = 0;
        objectData[12] = 0;
        objectData[18] = 0;
      }  
    int i = 47 - (Math.abs(PlayerPosX() - objectData[2]) >> 1);
    if (i < 0)
      i = 0; 
    int j = objectData[3] + sisootbl[i] - 16;
    if (objectData[5] == 1) {
      int k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 0, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1 + 12, b2);
      if (k >= 0)
        if (Math.abs(PlayerPosX() - objectData[2]) < 8) {
          PlayerParam[1] = objectData[3] - b2 << 8;
          sisoo_shot_tama(paramInt, 0);
          playerRaidOn(objectData[20]);
        } else if (PlayerPosX() > objectData[2]) {
          PlayerParam[1] = objectData[3] + sisootbl[i] - 16 << 8;
          objectData[5] = 0;
          sisoo_shot_tama(paramInt, 0);
          playerRaidOn(objectData[20]);
        } else {
          PlayerParam[1] = objectData[3] + sisootbl[i] - 16 << 8;
          objectData[5] = 2;
          sisoo_shot_tama(paramInt, 0);
          playerRaidOn(objectData[20]);
        }  
      if (raidOn && raidObjectNum == objectData[20] && k != 0)
        raidOn = false; 
    } else if (raidOn && raidObjectNum == objectData[20]) {
      if (objectData[2] - b1 - 10 < PlayerPosX() && objectData[2] + b1 + 10 > PlayerPosX()) {
        if (Math.abs(PlayerPosX() - objectData[2]) < 8) {
          PlayerParam[1] = objectData[3] - b2 << 8;
          if (objectData[5] != 1)
            sisoo_shot_tama(paramInt, 0); 
          objectData[5] = 1;
          playerRaidOn(objectData[20]);
        } else if (objectData[2] < PlayerPosX()) {
          PlayerParam[1] = objectData[3] - 16 + sisootbl[i] << 8;
          if (objectData[5] != 0)
            if (PlayerParam[5] >= 2560) {
              sisoo_shot_tama(paramInt, 2);
            } else {
              sisoo_shot_tama(paramInt, 1);
            }  
          objectData[5] = 0;
          playerRaidOn(objectData[20]);
        } else {
          PlayerParam[1] = objectData[3] - 16 + sisootbl[i] << 8;
          if (objectData[5] != 2)
            if (PlayerParam[5] >= 2560) {
              sisoo_shot_tama(paramInt, 2);
            } else {
              sisoo_shot_tama(paramInt, 1);
            }  
          objectData[5] = 2;
          playerRaidOn(objectData[20]);
        } 
      } else {
        raidOn = false;
      } 
    } else if (objectData[2] - b1 - 10 < PlayerPosX() && objectData[2] + b1 + 10 > PlayerPosX()) {
      if (Math.abs(PlayerPosX() - objectData[2]) < 8) {
        j = objectData[3] - b2;
        if (j > ploldpos[1] && j <= PlayerPosY()) {
          PlayerParam[1] = (j << 8) - 1;
          sisoo_shot_tama(paramInt, 0);
          objectData[5] = 1;
          playerRaidOn(objectData[20]);
        } 
      } else if (objectData[2] < PlayerPosX()) {
        if (objectData[5] == 0) {
          j = objectData[3] - 16 + sisootbl[i];
        } else if (objectData[5] == 2) {
          j = objectData[3] - 16 - sisootbl[i];
        } else {
          j = objectData[3] - b2;
        } 
        if (j >= ploldpos[1] && j <= PlayerPosY()) {
          PlayerParam[1] = objectData[3] - 16 + sisootbl[i] << 8;
          if (objectData[5] != 0)
            if (PlayerParam[5] >= 2560) {
              sisoo_shot_tama(paramInt, 2);
            } else {
              sisoo_shot_tama(paramInt, 1);
            }  
          objectData[5] = 0;
          playerRaidOn(objectData[20]);
        } 
      } else {
        if (objectData[5] == 0) {
          j = objectData[3] - 16 - sisootbl[i];
        } else if (objectData[5] == 2) {
          j = objectData[3] - 16 + sisootbl[i];
        } else {
          j = objectData[3] - b2;
        } 
        if (j > ploldpos[1] && j <= PlayerPosY()) {
          PlayerParam[1] = objectData[3] - 16 + sisootbl[i] << 8;
          if (objectData[5] != 2) {
            if (PlayerParam[5] >= 2560) {
              sisoo_shot_tama(paramInt, 2);
            } else {
              sisoo_shot_tama(paramInt, 1);
            } 
            objectData[5] = 2;
          } 
          playerRaidOn(objectData[20]);
        } 
      } 
    } 
    if (objectData[4] == 255 && objectData[13] == 0)
      return; 
    int[] arrayOfInt2 = new int[2];
    if (objectData[12] == 0) {
      objectData[11] = objectData[11] + gravity;
      if (objectData[2] - b1 << 8 >= objectData[6]) {
        objectData[6] = objectData[2] - b1 + 1 << 8;
        objectData[10] = 0;
      } else if (objectData[2] + b1 << 8 <= objectData[6]) {
        objectData[6] = objectData[2] + b1 - 1 << 8;
        objectData[10] = 0;
      } 
      objectData[6] = objectData[6] + objectData[10];
      objectData[7] = objectData[7] + objectData[11];
    } 
    arrayOfInt2[0] = objectData[6] >> 8;
    arrayOfInt2[1] = objectData[7] >> 8;
    i = 47 - (Math.abs(arrayOfInt2[0] - objectData[2]) >> 1);
    if (i < 0)
      i = 0; 
    j = objectData[3] - sisootbl[i] - 16;
    if (objectData[12] == 0 && objectData[11] > 0)
      if (objectData[5] == 1) {
        if (objectData[2] - b1 < arrayOfInt2[0] && objectData[2] + b1 > arrayOfInt2[0] && objectData[3] - 16 <= arrayOfInt2[1]) {
          if (arrayOfInt2[0] >= objectData[2]) {
            objectData[5] = 0;
          } else {
            objectData[5] = 2;
          } 
          objectData[7] = objectData[3] - b2 - 4 + 20 << 8;
          objectData[12] = 1;
          if (raidOn && raidObjectNum == objectData[20]) {
            PlayerParam[3] = 0;
            PlayerParam[5] = -objectData[11];
            PlayerJump = true;
            PlayerAir = true;
            PlayerDamage = false;
            if (objectData[4] == 255) {
              PlayerBall = true;
              PlayerSJump = false;
            } else {
              PlayerBall = false;
              PlayerSJump = true;
            } 
            raidOn = false;
          } 
          objectData[10] = 0;
          objectData[11] = 0;
        } 
      } else if (objectData[2] - b1 < arrayOfInt2[0] && objectData[2] + b1 > arrayOfInt2[0]) {
        if (objectData[2] < arrayOfInt2[0]) {
          if (objectData[5] == 2) {
            j = objectData[3] - 16 - sisootbl[i];
          } else {
            j = objectData[3] - 16 + sisootbl[i];
          } 
          if (j <= arrayOfInt2[1]) {
            objectData[7] = objectData[3] - b2 - 4 + 20 << 8;
            objectData[12] = 1;
            if (objectData[5] != 0 && raidOn && raidObjectNum == objectData[20]) {
              PlayerParam[3] = 0;
              PlayerParam[5] = -objectData[11];
              PlayerJump = true;
              if (objectData[4] == 255) {
                PlayerBall = true;
                PlayerSJump = false;
              } else {
                PlayerBall = false;
                PlayerSJump = true;
              } 
              PlayerDamage = false;
              PlayerAir = true;
              raidOn = false;
            } 
            objectData[5] = 0;
            objectData[10] = 0;
            objectData[11] = 0;
          } 
        } else {
          if (objectData[5] == 0) {
            j = objectData[3] - 16 - sisootbl[i];
          } else {
            j = objectData[3] - 16 + sisootbl[i];
          } 
          if (j <= arrayOfInt2[1]) {
            objectData[7] = objectData[3] - b2 - 4 + 20 << 8;
            objectData[12] = 1;
            if (objectData[5] != 2 && raidOn && raidObjectNum == objectData[20]) {
              PlayerParam[5] = -objectData[11];
              PlayerParam[3] = 0;
              PlayerJump = true;
              if (objectData[4] == 255) {
                PlayerBall = true;
                PlayerSJump = false;
              } else {
                PlayerBall = false;
                PlayerSJump = true;
              } 
              PlayerDamage = false;
              PlayerAir = true;
              raidOn = false;
            } 
            objectData[5] = 2;
            objectData[10] = 0;
            objectData[11] = 0;
          } 
        } 
      } else {
        objectData[12] = 0;
      }  
    if (IsHitSonic(objectData[6] >> 8, objectData[7] >> 8, 12, 12, false) != 0) {
      if (raidOn && raidObjectNum == objectData[20])
        raidOn = false; 
      playdamageset();
    } 
  }
  
  private void kamere_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    int k = arrayOfInt[19];
    byte b = (k == 0) ? -2 : 2;
    if (_CharaDefault(arrayOfInt))
      return; 
    int i = arrayOfInt[2];
    int j = arrayOfInt[3];
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[15] = -1;
        arrayOfInt[14] = 1;
        break;
      case 1:
        if (Math.abs(PlayerPosX() - arrayOfInt[2]) < 50) {
          arrayOfInt[5] = 30;
          arrayOfInt[15] = 0;
          arrayOfInt[14] = arrayOfInt[14] + 1;
        } 
        break;
      case 2:
        if (PlayerPosX() > arrayOfInt[2]) {
          arrayOfInt[19] = 1;
        } else {
          arrayOfInt[19] = 0;
        } 
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 48;
        arrayOfInt[15] = 1;
        if (arrayOfInt[4] == 1) {
          arrayOfInt[14] = 6;
          break;
        } 
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 3:
        AraiMoveStand(arrayOfInt);
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 8;
        arrayOfInt[15] = 2;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 4:
        AraiMoveStand(arrayOfInt);
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 8;
        arrayOfInt[15] = 3;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 5:
        arrayOfInt[2] = arrayOfInt[2] + b;
        AraiMoveStand(arrayOfInt);
        arrayOfInt[15] = 4 + (this.animeTimer & 0x1);
        break;
      case 6:
        if (arrayOfInt[5] > 0)
          break; 
        ShotObj2(7, arrayOfInt[2], arrayOfInt[3], (k == 0) ? 270 : 90, 150, 0);
        arrayOfInt[5] = 60;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 7:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 0;
        arrayOfInt[5] = 10;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 8:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 9:
        arrayOfInt[15] = -1;
        break;
    } 
    if (arrayOfInt[14] >= 3 && arrayOfInt[14] <= 7)
      AraiCharaHitCheck(arrayOfInt); 
  }
  
  private void hachi_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    int k = arrayOfInt[19];
    byte b = (k == 0) ? -2 : 2;
    if (_CharaDefault(arrayOfInt))
      return; 
    int i = arrayOfInt[2];
    int j = arrayOfInt[3];
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
        break;
      case 1:
        arrayOfInt[18] = 0;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        arrayOfInt[18] = 0;
        if (arrayOfInt[6] > 440) {
          if (arrayOfInt[19] == 0) {
            arrayOfInt[19] = 1;
          } else {
            arrayOfInt[19] = 0;
          } 
          arrayOfInt[6] = 0;
        } 
        if (AraiCheckInside(arrayOfInt, 160, 100)) {
          arrayOfInt[5] = 20;
          arrayOfInt[14] = 3;
        } 
        arrayOfInt[2] = arrayOfInt[2] + b;
        arrayOfInt[15] = this.animeTimer & 0x1;
        break;
      case 3:
        arrayOfInt[6] = arrayOfInt[6] - 1;
        arrayOfInt[15] = 2 + (this.animeTimer & 0x1);
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 90;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 4:
        arrayOfInt[6] = arrayOfInt[6] - 1;
        arrayOfInt[15] = 4 + (this.animeTimer & 0x1);
        if (arrayOfInt[5] == 16) {
          arrayOfInt[18] = 1;
        } else if (arrayOfInt[5] == 8) {
          arrayOfInt[18] = 2;
        } 
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[18] = 0;
        ShotObj2(7, arrayOfInt[2] + ((k == 0) ? -18 : 18), arrayOfInt[3] + 24, (k == 0) ? 210 : 150, 150, 0);
        arrayOfInt[5] = 30;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 5:
        arrayOfInt[6] = arrayOfInt[6] - 1;
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 6:
        arrayOfInt[2] = arrayOfInt[2] + b;
        arrayOfInt[15] = this.animeTimer & 0x1;
        if (arrayOfInt[6] > 480)
          arrayOfInt[14] = 2; 
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void musi_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    int i = arrayOfInt[19];
    byte b = (i == 0) ? -1 : 1;
    boolean bool = false;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 168)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        arrayOfInt[15] = (this.animeTimer >> 1) % 3;
        arrayOfInt[2] = arrayOfInt[2] + b;
        AraiMoveStand(arrayOfInt);
        if (AraiCheckSlide(arrayOfInt)) {
          arrayOfInt[5] = 60;
          arrayOfInt[14] = arrayOfInt[14] + 1;
        } 
        if ((this.animeTimer & 0x7) == 0)
          SetObj2(15, arrayOfInt[2] - b * 20, arrayOfInt[3] - 2, 0, 0, 0, 0); 
        break;
      case 3:
        if (arrayOfInt[5] > 0)
          break; 
        AraiDirChangeX(arrayOfInt);
        arrayOfInt[6] = 0;
        arrayOfInt[14] = arrayOfInt[14] - 1;
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void imo_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 168)
      return; 
    arrayOfInt[5] = arrayOfInt[5] + 1;
    if (IsFarDistance(arrayOfInt[2], arrayOfInt[3])) {
      arrayOfInt[18] = 0;
      return;
    } 
    if (arrayOfInt[18] == 3 || arrayOfInt[18] == 4) {
      if (CheckSlide(arrayOfInt[6] >> 8, arrayOfInt[7] >> 8, 8, 8, (arrayOfInt[8] == -1) ? 0 : 1))
        arrayOfInt[8] = (arrayOfInt[8] == 1) ? -1 : 1; 
      if (CheckSlide(arrayOfInt[9] >> 8, arrayOfInt[10] >> 8, 8, 8, (arrayOfInt[11] == -1) ? 0 : 1))
        arrayOfInt[11] = (arrayOfInt[11] == 1) ? -1 : 1; 
      if (CheckSlide(arrayOfInt[12] >> 8, arrayOfInt[13] >> 8, 8, 8, (arrayOfInt[14] == -1) ? 0 : 1))
        arrayOfInt[14] = (arrayOfInt[14] == 1) ? -1 : 1; 
      if (CheckSlide(arrayOfInt[15] >> 8, arrayOfInt[16] >> 8, 8, 8, (arrayOfInt[17] == -1) ? 0 : 1))
        arrayOfInt[17] = (arrayOfInt[17] == 1) ? -1 : 1; 
    } 
    switch (arrayOfInt[18]) {
      default:
        arrayOfInt[18] = 1;
      case 1:
        arrayOfInt[5] = 0;
        arrayOfInt[6] = arrayOfInt[2] << 8;
        arrayOfInt[7] = arrayOfInt[3] << 8;
        arrayOfInt[8] = b;
        arrayOfInt[9] = arrayOfInt[6] + (-b * 12 << 8);
        arrayOfInt[10] = arrayOfInt[7];
        arrayOfInt[11] = b;
        arrayOfInt[12] = arrayOfInt[9] + (-b * 12 << 8);
        arrayOfInt[13] = arrayOfInt[10];
        arrayOfInt[14] = b;
        arrayOfInt[15] = arrayOfInt[12] + (-b * 12 << 8);
        arrayOfInt[16] = arrayOfInt[13];
        arrayOfInt[17] = b;
        arrayOfInt[18] = arrayOfInt[18] + 1;
      case 2:
        arrayOfInt[7] = GetEnemyFloorY(arrayOfInt[6] >> 8, arrayOfInt[7] >> 8, 8) << 8;
        arrayOfInt[10] = GetEnemyFloorY(arrayOfInt[9] >> 8, arrayOfInt[10] >> 8, 8) << 8;
        arrayOfInt[13] = GetEnemyFloorY(arrayOfInt[12] >> 8, arrayOfInt[13] >> 8, 8) << 8;
        arrayOfInt[16] = GetEnemyFloorY(arrayOfInt[15] >> 8, arrayOfInt[16] >> 8, 8) << 8;
        if (arrayOfInt[5] < 8)
          break; 
        arrayOfInt[5] = 0;
        arrayOfInt[18] = arrayOfInt[18] + 1;
        break;
      case 3:
        arrayOfInt[6] = arrayOfInt[6] + 0 * arrayOfInt[8];
        arrayOfInt[9] = arrayOfInt[9] + 32 * arrayOfInt[11];
        arrayOfInt[12] = arrayOfInt[12] + 64 * arrayOfInt[14];
        arrayOfInt[15] = arrayOfInt[15] + 96 * arrayOfInt[17];
        if (arrayOfInt[5] < 32)
          break; 
        arrayOfInt[18] = arrayOfInt[18] + 1;
        break;
      case 4:
        arrayOfInt[6] = arrayOfInt[6] + 96 * arrayOfInt[8];
        arrayOfInt[9] = arrayOfInt[9] + 64 * arrayOfInt[11];
        arrayOfInt[12] = arrayOfInt[12] + 32 * arrayOfInt[14];
        arrayOfInt[15] = arrayOfInt[15] + 0 * arrayOfInt[17];
        if (arrayOfInt[5] < 64)
          break; 
        arrayOfInt[18] = arrayOfInt[18] + 1;
        break;
      case 5:
        if (arrayOfInt[8] == arrayOfInt[11] && arrayOfInt[11] == arrayOfInt[14] && arrayOfInt[14] == arrayOfInt[17]) {
          arrayOfInt[9] = arrayOfInt[6] + (-arrayOfInt[8] * 12 << 8);
          arrayOfInt[12] = arrayOfInt[9] + (-arrayOfInt[8] * 12 << 8);
          arrayOfInt[15] = arrayOfInt[12] + (-arrayOfInt[8] * 12 << 8);
        } 
        arrayOfInt[5] = 0;
        arrayOfInt[18] = 3;
        break;
    } 
    arrayOfInt[7] = GetEnemyFloorY(arrayOfInt[6] >> 8, arrayOfInt[7] >> 8, 8) << 8;
    arrayOfInt[10] = GetEnemyFloorY(arrayOfInt[9] >> 8, arrayOfInt[10] >> 8, 8) << 8;
    arrayOfInt[13] = GetEnemyFloorY(arrayOfInt[12] >> 8, arrayOfInt[13] >> 8, 8) << 8;
    arrayOfInt[16] = GetEnemyFloorY(arrayOfInt[15] >> 8, arrayOfInt[16] >> 8, 8) << 8;
    if (arrayOfInt[7] + 4096 < arrayOfInt[10])
      arrayOfInt[10] = arrayOfInt[7]; 
    if (Math.abs(arrayOfInt[8] - arrayOfInt[11]) > 3072)
      arrayOfInt[11] = arrayOfInt[11] + (arrayOfInt[8] << 8); 
    if (arrayOfInt[7] + 4096 < arrayOfInt[13])
      arrayOfInt[13] = arrayOfInt[7]; 
    if (Math.abs(arrayOfInt[8] - arrayOfInt[14]) > 3072)
      arrayOfInt[14] = arrayOfInt[14] + (arrayOfInt[8] << 8); 
    if (arrayOfInt[7] + 4096 < arrayOfInt[16])
      arrayOfInt[16] = arrayOfInt[7]; 
    if (Math.abs(arrayOfInt[8] - arrayOfInt[17]) > 3072)
      arrayOfInt[17] = arrayOfInt[17] + (arrayOfInt[8] << 8); 
    arrayOfInt[2] = arrayOfInt[6] >> 8;
    arrayOfInt[3] = arrayOfInt[7] >> 8;
    if (!AraiCharaHitCheck(arrayOfInt)) {
      boolean bool = false;
      bool = (bool || IsHitSonic(arrayOfInt[12] >> 8, arrayOfInt[13] >> 8, 22, 10, false) != 0) ? true : false;
      if (bool) {
        ShotObj2(14, arrayOfInt[2], arrayOfInt[3], 30 * b + 360, 300, 0);
        ShotObj2(14, arrayOfInt[9] >> 8, arrayOfInt[10] >> 8, 15 * b + 360, 300, 1);
        ShotObj2(14, arrayOfInt[12] >> 8, arrayOfInt[13] >> 8, -15 * b + 360, 300, 1);
        ShotObj2(14, arrayOfInt[15] >> 8, arrayOfInt[16] >> 8, -30 * b + 360, 300, 1);
        arrayOfInt[0] = 0;
        playdamageset();
      } 
    } 
  }
  
  private void brobo_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 360 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 360)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[5] = 121;
        arrayOfInt[6] = 0;
        arrayOfInt[18] = (arrayOfInt[19] == 2) ? 1 : 0;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        arrayOfInt[5] = 121;
        arrayOfInt[15] = this.animeTimer >> 3;
        if (arrayOfInt[18] == 0) {
          AraiMoveStand(arrayOfInt, 18);
          if ((this.cpuTimer & 0xF) == 0)
            arrayOfInt[2] = arrayOfInt[2] + b; 
          if (AraiCheckSlide(arrayOfInt) || arrayOfInt[6] > 600) {
            AraiDirChangeX(arrayOfInt);
            arrayOfInt[6] = 0;
          } 
        } else {
          if (blockColChk_Enemy(arrayOfInt[2], arrayOfInt[3] - 18))
            arrayOfInt[3] = arrayOfInt[3] + 1; 
          if (CheckSlideInverse(arrayOfInt[2], arrayOfInt[3], 12, 18, arrayOfInt[19]) || arrayOfInt[6] > 600) {
            if (arrayOfInt[19] == 0) {
              arrayOfInt[19] = 1;
            } else {
              arrayOfInt[19] = 0;
            } 
            arrayOfInt[6] = 0;
          } 
          if ((this.cpuTimer & 0xF) == 0)
            arrayOfInt[2] = arrayOfInt[2] + b; 
        } 
        if (Math.abs(arrayOfInt[2] - PlayerPosX()) < 100 && Math.abs(arrayOfInt[3] - PlayerPosY() - 12) < 100)
          arrayOfInt[14] = arrayOfInt[14] + 1; 
        break;
      case 3:
        if (arrayOfInt[5] > 0)
          break; 
        ShotObj2(13, arrayOfInt[2], arrayOfInt[3], 30, 300, 0);
        ShotObj2(13, arrayOfInt[2], arrayOfInt[3], 30, 200, 0);
        ShotObj2(13, arrayOfInt[2], arrayOfInt[3], 330, 300, 0);
        ShotObj2(13, arrayOfInt[2], arrayOfInt[3], 330, 200, 0);
        SetObj2(1, arrayOfInt[2], arrayOfInt[3], 0, 0, 0, 0);
        arrayOfInt[0] = 0;
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void buta_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    boolean bool = (arrayOfInt[19] == 0) ? true : true;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 168)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[8] = 0;
        arrayOfInt[5] = 30;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        arrayOfInt[15] = 0;
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 30;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 3:
        arrayOfInt[15] = 1;
        arrayOfInt[3] = arrayOfInt[17] - 8;
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 30;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 4:
        arrayOfInt[15] = 0;
        arrayOfInt[3] = arrayOfInt[17];
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 30;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 5:
        arrayOfInt[15] = 2;
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 30;
        arrayOfInt[8] = arrayOfInt[8] + 1;
        if (arrayOfInt[8] + 1 > 3) {
          arrayOfInt[14] = arrayOfInt[14] + 1;
          break;
        } 
        arrayOfInt[14] = 2;
        break;
      case 6:
        arrayOfInt[15] = 3;
        if (arrayOfInt[5] == 10)
          if (arrayOfInt[19] == 0) {
            ShotObj2(10, arrayOfInt[2], arrayOfInt[3], 345, 300, 0);
          } else {
            ShotObj2(10, arrayOfInt[2], arrayOfInt[3], 15, 300, 0);
          }  
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[8] = 0;
        arrayOfInt[14] = 1;
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void kani_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 168)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[10] = 0;
        arrayOfInt[11] = 0;
        arrayOfInt[5] = 360;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        arrayOfInt[15] = (this.animeTimer >> 2) % 3;
        arrayOfInt[10] = b * 20;
        arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
        arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
        AraiStand100x(arrayOfInt, 16);
        arrayOfInt[2] = arrayOfInt[12] / 100;
        arrayOfInt[3] = arrayOfInt[13] / 100;
        if (AraiCheckSlide(arrayOfInt) || arrayOfInt[6] > 180) {
          AraiDirChangeX(arrayOfInt);
          arrayOfInt[6] = 0;
        } 
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 1;
        arrayOfInt[5] = 60;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 3:
        AraiStand100x(arrayOfInt, 16);
        if (arrayOfInt[5] < 12)
          arrayOfInt[15] = 3; 
        if (arrayOfInt[5] > 0)
          break; 
        ShotObj2(9, arrayOfInt[2] + 16, arrayOfInt[3] - 16, 15, 240, 0);
        ShotObj2(9, arrayOfInt[2] - 16, arrayOfInt[3] - 16, 345, 240, 0);
        arrayOfInt[5] = 50;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 4:
        AraiStand100x(arrayOfInt, 16);
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[14] = 1;
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void aruma_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[15] = -1;
        arrayOfInt[8] = 0;
        arrayOfInt[9] = 0;
        arrayOfInt[10] = 0;
        arrayOfInt[11] = 400;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 2:
        if (PlayerPosX() - 160 < arrayOfInt[2])
          break; 
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 3:
        arrayOfInt[10] = 500;
        arrayOfInt[11] = 400;
        arrayOfInt[15] = 0;
        arrayOfInt[5] = 94;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 4:
        arrayOfInt[15] = this.animeTimer & 0x1;
        arrayOfInt[10] = 400;
        if (arrayOfInt[9] == 0 && CheckSlide(arrayOfInt[2], arrayOfInt[3], 20, 20, 1)) {
          arrayOfInt[9] = 1;
          arrayOfInt[11] = -800;
          arrayOfInt[5] = 94;
        } 
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 94;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 5:
        arrayOfInt[10] = 0;
        arrayOfInt[11] = 0;
        arrayOfInt[15] = 1;
        arrayOfInt[5] = 20;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 6:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 2;
        arrayOfInt[5] = 20;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 7:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 3;
        arrayOfInt[5] = 60;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 8:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 2;
        arrayOfInt[5] = 20;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 9:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[15] = 1;
        arrayOfInt[5] = 20;
        arrayOfInt[14] = arrayOfInt[14] + 1;
      case 10:
        if (arrayOfInt[5] > 0)
          break; 
        arrayOfInt[5] = 94;
        arrayOfInt[14] = 3;
        break;
    } 
    arrayOfInt[11] = arrayOfInt[11] + 20;
    if (arrayOfInt[11] > 400)
      arrayOfInt[11] = 400; 
    arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
    arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
    if (arrayOfInt[11] > 0 && blockColChk_Enemy(arrayOfInt[12] / 100, arrayOfInt[13] / 100 + 12)) {
      arrayOfInt[9] = 0;
      for (byte b = 0; b < 4; b++) {
        if (blockColChk_Enemy(arrayOfInt[12] / 100, arrayOfInt[13] / 100 + 12));
        arrayOfInt[13] = arrayOfInt[13] - 100;
      } 
      if (arrayOfInt[13] < 0)
        arrayOfInt[13] = 0; 
    } 
    arrayOfInt[2] = arrayOfInt[12] / 100;
    arrayOfInt[3] = arrayOfInt[13] / 100;
    if (arrayOfInt[15] >= 0)
      AraiCharaHitCheck(arrayOfInt); 
  }
  
  private void yado_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 168)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[10] = 0;
        arrayOfInt[11] = 0;
        arrayOfInt[5] = 120;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 2:
        break;
    } 
    arrayOfInt[15] = (this.animeTimer >> 2) % 3;
    arrayOfInt[10] = b * 25;
    arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
    arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
    arrayOfInt[2] = arrayOfInt[12] / 100;
    arrayOfInt[3] = arrayOfInt[13] / 100;
    AraiMoveStand(arrayOfInt, 24);
    if (AraiCheckSlide(arrayOfInt) || arrayOfInt[6] > 240) {
      AraiDirChangeX(arrayOfInt);
      arrayOfInt[6] = 0;
    } 
    if (arrayOfInt[5] <= 0) {
      arrayOfInt[15] = 1;
      arrayOfInt[5] = 40;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void uni_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    boolean bool = (arrayOfInt[4] == 2) ? false : true;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 240)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[5] = 1800;
        arrayOfInt[9] = 0;
        arrayOfInt[10] = !bool ? (b * 20) : 0;
        arrayOfInt[18] = 359;
        arrayOfInt[11] = 0;
        arrayOfInt[15] = !bool ? 0 : 1;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 2:
        break;
    } 
    if (bool == true)
      if ((arrayOfInt[9] & 0xF) == 15) {
        arrayOfInt[10] = b * 25;
      } else {
        arrayOfInt[10] = 0;
      }  
    if (arrayOfInt[5] <= 0)
      arrayOfInt[5] = 1800; 
    arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
    arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
    arrayOfInt[2] = arrayOfInt[12] / 100;
    arrayOfInt[3] = arrayOfInt[13] / 100;
    if (arrayOfInt[19] == 0) {
      arrayOfInt[18] = arrayOfInt[18] + 1;
      if (arrayOfInt[18] + 1 > 359)
        arrayOfInt[18] = arrayOfInt[18] - 360; 
    } else {
      arrayOfInt[18] = arrayOfInt[18] - 1;
      if (arrayOfInt[18] - 1 < 0)
        arrayOfInt[18] = arrayOfInt[18] + 360; 
    } 
    if (AraiCheckInside(arrayOfInt, 100, 50) && bool == true)
      arrayOfInt[15] = 3; 
    if ((arrayOfInt[9] & 0x1) == 0) {
      int k = arrayOfInt[18] % 360;
      int i = arrayOfInt[2] + dSin(k) * 16 / 100;
      int j = arrayOfInt[3] + dCos(k) * 16 / 100;
      if (IsHitSonic(i, j, 16, 16, false) != 0)
        playdamageset(); 
      if (bool == true && Math.abs(180 - k) < 4 && AraiCheckInside(arrayOfInt, 100, 50)) {
        arrayOfInt[9] = arrayOfInt[9] | 0x1;
        ShotObj2(12, i, j, (arrayOfInt[19] == 0) ? 270 : 90, 80, 0);
      } 
    } 
    if ((arrayOfInt[9] & 0x2) == 0) {
      int k = (arrayOfInt[18] + 90) % 360;
      int i = arrayOfInt[2] + dSin(k) * 16 / 100;
      int j = arrayOfInt[3] + dCos(k) * 16 / 100;
      if (IsHitSonic(i, j, 16, 16, false) != 0)
        playdamageset(); 
      if (bool == true && Math.abs(180 - k) < 4 && AraiCheckInside(arrayOfInt, 100, 50)) {
        arrayOfInt[9] = arrayOfInt[9] | 0x2;
        ShotObj2(12, i, j, (arrayOfInt[19] == 0) ? 270 : 90, 80, 0);
      } 
    } 
    if ((arrayOfInt[9] & 0x4) == 0) {
      int k = (arrayOfInt[18] + 180) % 360;
      int i = arrayOfInt[2] + dSin(k) * 16 / 100;
      int j = arrayOfInt[3] + dCos(k) * 16 / 100;
      if (IsHitSonic(i, j, 16, 16, false) != 0)
        playdamageset(); 
      if (bool == true && Math.abs(180 - k) < 4 && AraiCheckInside(arrayOfInt, 100, 50)) {
        arrayOfInt[9] = arrayOfInt[9] | 0x4;
        ShotObj2(12, i, j, (arrayOfInt[19] == 0) ? 270 : 90, 80, 0);
      } 
    } 
    if ((arrayOfInt[9] & 0x8) == 0) {
      int k = (arrayOfInt[18] + 270) % 360;
      int i = arrayOfInt[2] + dSin(k) * 16 / 100;
      int j = arrayOfInt[3] + dCos(k) * 16 / 100;
      if (IsHitSonic(i, j, 16, 16, false) != 0)
        playdamageset(); 
      if (bool == true && Math.abs(180 - k) < 4 && AraiCheckInside(arrayOfInt, 100, 50)) {
        arrayOfInt[9] = arrayOfInt[9] | 0x8;
        ShotObj2(12, i, j, (arrayOfInt[19] == 0) ? 270 : 90, 80, 0);
      } 
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void bat_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[15] = 0;
        if (PlayerPosX() > arrayOfInt[2]) {
          arrayOfInt[19] = 1;
        } else {
          arrayOfInt[19] = 0;
        } 
        if (arrayOfInt[3] + 40 <= PlayerPosY() && arrayOfInt[3] + 100 >= PlayerPosY() && Math.abs(arrayOfInt[2] - PlayerPosX()) < 100) {
          arrayOfInt[9] = PlayerPosY() - 16;
          arrayOfInt[14] = arrayOfInt[14] + 1;
        } 
        break;
      case 2:
        arrayOfInt[15] = 1;
        arrayOfInt[3] = arrayOfInt[3] + 2;
        if (arrayOfInt[3] < arrayOfInt[9])
          break; 
        arrayOfInt[3] = arrayOfInt[9];
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 3:
        arrayOfInt[15] = batAnimTbl[this.animeTimer & 0x3];
        arrayOfInt[2] = arrayOfInt[2] + b;
        if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 80)
          arrayOfInt[14] = arrayOfInt[14] + 1; 
        break;
      case 4:
        arrayOfInt[15] = batAnimTbl[this.animeTimer & 0x3];
        arrayOfInt[2] = arrayOfInt[2] + b;
        arrayOfInt[3] = arrayOfInt[3] - 2;
        if (!blockColChk_Enemy(arrayOfInt[2], arrayOfInt[3] - 16))
          break; 
        arrayOfInt[14] = 1;
        break;
    } 
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private boolean AraiStand100x(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint[13];
    int j = i / 100;
    int k = j + paramInt;
    int m = (paramArrayOfint[17] - 240) * 100;
    int n = (paramArrayOfint[17] + 240) * 100;
    boolean bool = false;
    if (i < 9999900)
      i += 100; 
    if (blockColChk_Enemy(paramArrayOfint[12] / 100, k)) {
      i -= 200;
      paramArrayOfint[11] = 50;
      bool = true;
    } 
    if (i < 0)
      i = 0; 
    paramArrayOfint[13] = i;
    return bool;
  }
  
  private void mogura_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -8 : 8;
    if (Math.abs(arrayOfInt[2] - PlayerPosX()) > 240 && Math.abs(arrayOfInt[3] - PlayerPosY()) > 240)
      return; 
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[10] = 0;
        arrayOfInt[11] = 0;
        arrayOfInt[15] = 0;
        arrayOfInt[14] = 1;
        break;
      case 1:
        if (PlayerPosX() > arrayOfInt[2]) {
          arrayOfInt[19] = 1;
        } else {
          arrayOfInt[19] = 0;
        } 
        arrayOfInt[15] = 0;
        if (Math.abs(PlayerPosX() - arrayOfInt[2]) < 100) {
          arrayOfInt[10] = (arrayOfInt[19] == 0) ? -50 : 50;
          arrayOfInt[11] = -300;
          arrayOfInt[14] = arrayOfInt[14] + 1;
        } 
        break;
      case 2:
        arrayOfInt[11] = arrayOfInt[11] + 5;
        if (arrayOfInt[11] > 0) {
          arrayOfInt[5] = 10;
          arrayOfInt[14] = arrayOfInt[14] + 1;
        } 
        break;
      case 3:
        if (arrayOfInt[5] > 16) {
          arrayOfInt[15] = 1;
        } else if (arrayOfInt[5] > 8 || blockColChk_Enemy(arrayOfInt[2] + b, arrayOfInt[3])) {
          arrayOfInt[15] = 2;
        } else {
          arrayOfInt[15] = 3 + (this.animeTimer >> 1 & 0x1);
        } 
        if (arrayOfInt[6] > 360)
          if (PlayerPosX() > arrayOfInt[2]) {
            arrayOfInt[19] = 1;
          } else {
            arrayOfInt[19] = 0;
          }  
        AraiStand100x(arrayOfInt, 16);
        arrayOfInt[11] = arrayOfInt[11] + 5;
        break;
    } 
    arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
    arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
    arrayOfInt[2] = arrayOfInt[12] / 100;
    arrayOfInt[3] = arrayOfInt[13] / 100;
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void fish_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    if (_CharaDefault(arrayOfInt))
      return; 
    arrayOfInt[15] = this.animeTimer & 0x1;
    arrayOfInt[3] = arrayOfInt[17] - Math.abs(dCos(arrayOfInt[6] % 180)) * 268 / 100;
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void fish2_sflag_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    if (_CharaDefault(arrayOfInt))
      return; 
    switch (arrayOfInt[14]) {
      default:
        arrayOfInt[14] = 1;
      case 1:
        arrayOfInt[5] = 480;
        arrayOfInt[10] = b * 26;
        arrayOfInt[11] = 0;
        arrayOfInt[14] = arrayOfInt[14] + 1;
        break;
      case 2:
        break;
    } 
    arrayOfInt[10] = b * 26;
    arrayOfInt[15] = this.animeTimer >> 1 & 0x3;
    if (arrayOfInt[5] <= 0) {
      arrayOfInt[19] = (arrayOfInt[19] == 0) ? 1 : 0;
      arrayOfInt[5] = 480;
    } 
    arrayOfInt[12] = arrayOfInt[12] + arrayOfInt[10];
    arrayOfInt[13] = arrayOfInt[13] + arrayOfInt[11];
    arrayOfInt[2] = arrayOfInt[12] / 100;
    arrayOfInt[3] = arrayOfInt[13] / 100;
    AraiCharaHitCheck(arrayOfInt);
  }
  
  private void AraiDrawChara(int paramInt1, short[][] paramArrayOfshort, int paramInt2) {
    byte b;
    if (paramInt2 < 0)
      return; 
    short s1 = paramArrayOfshort[paramInt2][0];
    short s2 = paramArrayOfshort[paramInt2][1];
    short s3 = paramArrayOfshort[paramInt2][2];
    short s4 = paramArrayOfshort[paramInt2][3];
    short s5 = paramArrayOfshort[paramInt2][4];
    switch (objectData[19]) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        break;
      case 2:
        b = 1;
        break;
      case 3:
        b = 3;
        break;
    } 
    drawRegion(gg, this.m_imgObj[objectData[1]], s1, s2, s3, s4, b, objectData[2] - mapView[0], objectData[3] - mapView[1] + s5, 0x1 | 0x2);
  }
  
  private void AraiDrawChara100x(int paramInt1, short[][] paramArrayOfshort, int paramInt2) {
    byte b;
    if (paramInt2 < 0)
      return; 
    short s1 = paramArrayOfshort[paramInt2][0];
    short s2 = paramArrayOfshort[paramInt2][1];
    short s3 = paramArrayOfshort[paramInt2][2];
    short s4 = paramArrayOfshort[paramInt2][3];
    short s5 = paramArrayOfshort[paramInt2][4];
    switch (objectData[19]) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        break;
      case 2:
        b = 1;
        break;
      case 3:
        b = 3;
        break;
    } 
    drawRegion(gg, this.m_imgObj[objectData[1]], s1, s2, s3, s4, b, objectData[2] / 100 - mapView[0], objectData[3] / 100 - mapView[1] + s5, 0x1 | 0x2);
  }
  
  private void sisoo_nflag_draw_arai(int paramInt) {
    byte b = 0;
    if (objectData[4] == 255)
      if (objectData[14] < 60) {
        if ((this.animeTimer & 0x1) == 0)
          b = 24; 
      } else if (objectData[14] < 200 && (this.animeTimer >> 1 & 0x1) == 0) {
        b = 24;
      }  
    if (objectData[4] != 255 || objectData[13] == 1)
      drawRegion(gg, this.m_imgObj[23], b, 80, 24, 24, 0, (objectData[6] >> 8) - mapView[0], (objectData[7] >> 8) - mapView[1], 0x1 | 0x2); 
    if (objectData[5] == 1) {
      drawRegion(gg, this.m_imgObj[23], 0, 56, 96, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[23], 0, 0, 96, 56, rotNumTable[TRANS_NONE + (objectData[5] >> 1) * 4], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
    } 
    drawStringCenter(gg, f, "height:" + ((objectData[7] >> 8) - objectData[3]), objectData[2] - mapView[0] + 40, objectData[3] - mapView[1] + FontPos - 40, false);
  }
  
  private void kamere_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 6;
    AraiDrawChara(paramInt, RectTblKamere, i);
  }
  
  private void hachi_sflag_draw_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    byte b = (arrayOfInt[19] == 0) ? -1 : 1;
    int i = arrayOfInt[15] % 6;
    AraiDrawChara(paramInt, RectTblHachi, i);
    if (arrayOfInt[18] == 1) {
      drawRegion(gg, this.m_imgObj[40], 0, 184, 16, 16, (arrayOfInt[19] == 0) ? 0 : 2, arrayOfInt[2] - mapView[0] + b * 18, arrayOfInt[3] - mapView[1] + 28, 0x1 | 0x2);
    } else if (arrayOfInt[18] == 2) {
      drawRegion(gg, this.m_imgObj[40], 16, 184, 16, 16, (arrayOfInt[19] == 0) ? 0 : 2, arrayOfInt[2] - mapView[0] + b * 18, arrayOfInt[3] - mapView[1] + 28, 0x1 | 0x2);
    } 
  }
  
  private void musi_sflag_draw_arai(int paramInt) {
    int i = 0;
    i = objectData[15] % 3;
    AraiDrawChara(paramInt, RectTblMusi, i);
  }
  
  private void imo_sflag_draw_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    if (arrayOfInt[18] < 1)
      return; 
    byte b = 2;
    short s1 = RectTblImo[b][0];
    short s2 = RectTblImo[b][1];
    short s3 = RectTblImo[b][2];
    short s4 = RectTblImo[b][3];
    int i = (arrayOfInt[18] == 3) ? (arrayOfInt[5] >> 2) : (8 - (arrayOfInt[5] - 30 >> 2));
    drawRegion(gg, this.m_imgObj[49], s1, s2, s3, s4, (arrayOfInt[17] == 1) ? 2 : 0, (arrayOfInt[15] >> 8) - mapView[0], (arrayOfInt[16] >> 8) - mapView[1], 0x1 | 0x2);
    drawRegion(gg, this.m_imgObj[49], s1, s2, s3, s4, (arrayOfInt[14] == 1) ? 2 : 0, (arrayOfInt[12] >> 8) - mapView[0], (arrayOfInt[13] >> 8) - mapView[1] - i, 0x1 | 0x2);
    drawRegion(gg, this.m_imgObj[49], s1, s2, s3, s4, (arrayOfInt[11] == 1) ? 2 : 0, (arrayOfInt[9] >> 8) - mapView[0], (arrayOfInt[10] >> 8) - mapView[1], 0x1 | 0x2);
    b = (arrayOfInt[18] > 3) ? 1 : 0;
    s1 = RectTblImo[b][0];
    s2 = RectTblImo[b][1];
    s3 = RectTblImo[b][2];
    s4 = RectTblImo[b][3];
    short s5 = RectTblImo[b][4];
    drawRegion(gg, this.m_imgObj[49], s1, s2, s3, s4, (arrayOfInt[8] == 1) ? 2 : 0, (arrayOfInt[6] >> 8) - mapView[0], (arrayOfInt[7] >> 8) - mapView[1] + s5 - i, 0x1 | 0x2);
  }
  
  private void brobo_sflag_draw_arai(int paramInt) {
    boolean bool;
    int[] arrayOfInt = objectData;
    int i = arrayOfInt[15] % 5;
    short s1 = RectTblBrobo[i][0];
    short s2 = RectTblBrobo[i][1];
    short s3 = RectTblBrobo[i][2];
    short s4 = RectTblBrobo[i][3];
    if (arrayOfInt[18] == 0) {
      bool = (arrayOfInt[19] == 0) ? false : true;
    } else {
      bool = (arrayOfInt[19] == 0) ? true : true;
    } 
    drawRegion(gg, this.m_imgObj[arrayOfInt[1]], s1, s2, s3, s4, bool, arrayOfInt[2] - mapView[0], arrayOfInt[3] - mapView[1], 0x1 | 0x2);
    if (arrayOfInt[5] < 119) {
      i = this.animeTimer & 0x1;
      s1 = RectTblTama[10 + i][0];
      s2 = RectTblTama[10 + i][1];
      s3 = RectTblTama[10 + i][2];
      s4 = RectTblTama[10 + i][3];
      int j = (arrayOfInt[18] == 0) ? (-6 - arrayOfInt[5] / 10) : (6 + arrayOfInt[5] / 10);
      drawRegion(gg, this.m_imgObj[96], s1, s2, s3, s4, (arrayOfInt[18] == 0) ? 0 : 3, arrayOfInt[2] - mapView[0], arrayOfInt[3] - mapView[1] + j, 0x1 | 0x2);
    } 
  }
  
  private void buta_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 4;
    AraiDrawChara(paramInt, RectTblButa, i);
  }
  
  private void kani_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 4;
    AraiDrawChara(paramInt, RectTblKani, i);
  }
  
  private void aruma_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 4;
    AraiDrawChara(paramInt, RectTblAruma, i);
  }
  
  private void yado_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 3;
    AraiDrawChara(paramInt, RectTblYado, i);
  }
  
  private void drawUniToge(int paramInt1, int paramInt2, int paramInt3) {
    byte b = (paramInt3 == 0) ? 6 : 7;
    short s1 = RectTblTama[b][0];
    short s2 = RectTblTama[b][1];
    short s3 = RectTblTama[b][2];
    short s4 = RectTblTama[b][3];
    drawRegion(gg, this.m_imgObj[96], s1, s2, s3, s4, 0, paramInt1 - mapView[0], paramInt2 - mapView[1], 0x1 | 0x2);
  }
  
  private void uni_sflag_draw_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    boolean bool = (arrayOfInt[4] == 2) ? false : true;
    if ((arrayOfInt[9] & 0x1) == 0) {
      int j = arrayOfInt[18] % 360;
      drawUniToge(arrayOfInt[2] + dSin(j) * 16 / 100, arrayOfInt[3] + dCos(j) * 16 / 100, bool);
    } 
    if ((arrayOfInt[9] & 0x2) == 0) {
      int j = arrayOfInt[18] + 90;
      drawUniToge(arrayOfInt[2] + dSin(j) * 16 / 100, arrayOfInt[3] + dCos(j) * 16 / 100, bool);
    } 
    if ((arrayOfInt[9] & 0x4) == 0) {
      int j = arrayOfInt[18] + 180;
      drawUniToge(arrayOfInt[2] + dSin(j) * 16 / 100, arrayOfInt[3] + dCos(j) * 16 / 100, bool);
    } 
    if ((arrayOfInt[9] & 0x8) == 0) {
      int j = arrayOfInt[18] + 270;
      drawUniToge(arrayOfInt[2] + dSin(j) * 16 / 100, arrayOfInt[3] + dCos(j) * 16 / 100, bool);
    } 
    int i = arrayOfInt[15] % 4;
    AraiDrawChara(paramInt, RectTblUni, i);
  }
  
  private void bat_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 4;
    AraiDrawChara(paramInt, RectTblBat, i);
  }
  
  private void ochi_nflag_draw_arai(int paramInt) {}
  
  private void yari_sflag_draw_arai(int paramInt) {}
  
  private void mogura_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 5;
    AraiDrawChara(paramInt, RectTblMogura, i);
  }
  
  private void fish_sflag_draw_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    int i = arrayOfInt[15] % 2;
    AraiDrawChara(paramInt, RectTblFish, i);
  }
  
  private void fish2_sflag_draw_arai(int paramInt) {
    int i = objectData[15] % 4;
    AraiDrawChara(paramInt, RectTblFish2, i);
  }
  
  private void AddObjectData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int[] arrayOfInt = new int[25];
    if (paramInt1 < 1)
      return; 
    arrayOfInt[0] = 1;
    arrayOfInt[1] = paramInt1;
    arrayOfInt[2] = paramInt2;
    arrayOfInt[3] = paramInt3;
    arrayOfInt[4] = paramInt4;
    arrayOfInt[19] = paramInt5;
    arrayOfInt[21] = 1;
    addObject(arrayOfInt);
  }
  
  private void InitObj2() {
    obj2Data = new int[50][20];
    for (byte b = 0; b < 50; b++)
      obj2Data[b][0] = 0; 
  }
  
  private void ClearObj2() {
    for (byte b = 0; b < 50; b++)
      obj2Data[b][0] = 0; 
  }
  
  private void SetObj2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
    if (paramInt1 < 1)
      return; 
    byte b = 0;
    while (b < 50) {
      int[] arrayOfInt = obj2Data[b];
      if (arrayOfInt[0] > 0) {
        b++;
        continue;
      } 
      for (byte b1 = 4; b1 < 20; b1++)
        arrayOfInt[b1] = 0; 
      arrayOfInt[0] = 1;
      arrayOfInt[1] = paramInt1;
      arrayOfInt[2] = paramInt2 * 100;
      arrayOfInt[3] = paramInt3 * 100;
      arrayOfInt[8] = paramInt7;
      arrayOfInt[10] = paramInt4;
      arrayOfInt[11] = paramInt5;
      arrayOfInt[19] = paramInt6;
      return;
    } 
  }
  
  private void SetObj2Ex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9) {
    if (paramInt1 < 1)
      return; 
    byte b = 0;
    while (b < 50) {
      int[] arrayOfInt = obj2Data[b];
      if (arrayOfInt[0] > 0) {
        b++;
        continue;
      } 
      for (byte b1 = 4; b1 < 20; b1++)
        arrayOfInt[b1] = 0; 
      arrayOfInt[0] = 1;
      arrayOfInt[1] = paramInt1;
      arrayOfInt[2] = paramInt2 * 100;
      arrayOfInt[3] = paramInt3 * 100;
      arrayOfInt[5] = paramInt9;
      arrayOfInt[8] = paramInt7;
      arrayOfInt[9] = paramInt8;
      arrayOfInt[10] = paramInt4;
      arrayOfInt[11] = paramInt5;
      arrayOfInt[19] = paramInt6;
      return;
    } 
  }
  
  private void ShotObj2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    if (paramInt1 < 1)
      return; 
    byte b = 0;
    while (b < 50) {
      int[] arrayOfInt = obj2Data[b];
      if (arrayOfInt[0] > 0) {
        b++;
        continue;
      } 
      for (byte b1 = 4; b1 < 20; b1++)
        arrayOfInt[b1] = 0; 
      arrayOfInt[0] = 1;
      arrayOfInt[1] = paramInt1;
      arrayOfInt[2] = paramInt2 * 100;
      arrayOfInt[3] = paramInt3 * 100;
      arrayOfInt[8] = paramInt6;
      arrayOfInt[10] = dSin(paramInt4) * paramInt5 / 100;
      arrayOfInt[11] = dCos(paramInt4) * paramInt5 / 100;
      return;
    } 
  }
  
  private void ShotRing(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 <= 0)
      return; 
    if (paramInt3 > 32)
      paramInt3 = 32; 
    byte b = 0;
    while (true) {
      if (b < ((paramInt3 > 16) ? 16 : paramInt3)) {
        int i = (b >> 1) * 2250 + 1125;
        if ((b & 0x1) == 1)
          i = -i + 36000; 
        ShotObj2(4, paramInt1, paramInt2, i / 100, 256, 0);
        b++;
        continue;
      } 
      if (paramInt3 > 16) {
        paramInt3 -= 16;
        for (b = 0; b < ((paramInt3 > 16) ? 16 : paramInt3); b++) {
          int i = (b >> 1) * 2250 + 1125;
          if ((b & 0x1) == 1)
            i = -i + 36000; 
          ShotObj2(4, paramInt1, paramInt2, i / 100, 128, 0);
        } 
      } 
      return;
    } 
  }
  
  private void ShotAnimal(int paramInt1, int paramInt2, int paramInt3) {
    int[] arrayOfInt = new int[24];
    if (paramInt3 < 0 || paramInt3 > 5)
      paramInt3 = 0; 
    byte b = 0;
    int i = 60;
    while (b < 24) {
      i += 2 + rnd(18);
      arrayOfInt[b] = i;
      b++;
    } 
    for (b = 0; b < 24; b++)
      SetObj2(friendTbl[paramInt3][rnd(2)], paramInt1 + b % 8 * 8 - 32, paramInt2, 0, -350 + rnd(60), rnd(2), i - arrayOfInt[b]); 
  }
  
  private void ShotScore(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 < 10) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 0);
    } else if (paramInt3 < 50) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 1);
    } else if (paramInt3 < 100) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 2);
    } else if (paramInt3 < 200) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 3);
    } else if (paramInt3 < 500) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 4);
    } else if (paramInt3 < 1000) {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 5);
    } else {
      SetObj2(6, paramInt1, paramInt2, 0, 0, 0, 6);
    } 
  }
  
  private void ShotBomb(int paramInt1, int paramInt2) {
    ShotObj2(13, paramInt1, paramInt2, 30, 200, 0);
    ShotObj2(13, paramInt1, paramInt2, 30, 300, 0);
    ShotObj2(13, paramInt1, paramInt2, 330, 200, 0);
    ShotObj2(13, paramInt1, paramInt2, 330, 300, 0);
    SetObj2(1, paramInt1, paramInt2, 0, 0, 0, 0);
  }
  
  private void DebugRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  private void DriveObj2() {
    for (byte b = 0; b < 50; b++) {
      int[] arrayOfInt = obj2Data[b];
      if (arrayOfInt[0] > 0)
        if (Math.abs(arrayOfInt[2] / 100 - PlayerPosX()) > 320 || Math.abs(arrayOfInt[3] / 100 - PlayerPosY()) > 260 || arrayOfInt[6] > 600) {
          arrayOfInt[0] = 0;
        } else {
          CallObj2(arrayOfInt);
          if (arrayOfInt[5] > 0)
            arrayOfInt[5] = arrayOfInt[5] - 1; 
          arrayOfInt[6] = arrayOfInt[6] + 1;
        }  
    } 
  }
  
  private void CallObj2(int[] paramArrayOfint) {
    switch (paramArrayOfint[1]) {
      case 1:
        MoveBakuhatu(paramArrayOfint);
        break;
      case 2:
        MoveBakuhatu(paramArrayOfint);
        break;
      case 3:
        MoveBakudan(paramArrayOfint);
        break;
      case 4:
        MoveRing(paramArrayOfint);
        break;
      case 5:
        MoveKira(paramArrayOfint);
        break;
      case 6:
        MoveScore(paramArrayOfint);
        break;
      case 7:
        MoveNormalTama(paramArrayOfint);
        break;
      case 8:
        MoveHachiTama(paramArrayOfint);
        break;
      case 9:
        MoveKaniTama(paramArrayOfint);
        break;
      case 10:
        MoveButaTama(paramArrayOfint);
        break;
      case 13:
        MoveBroboTama(paramArrayOfint);
        break;
      case 11:
        MoveUniTama(paramArrayOfint);
        break;
      case 12:
        MoveUni2Tama(paramArrayOfint);
        break;
      case 14:
        MoveImoTama(paramArrayOfint);
        break;
      case 15:
        MoveMusiKemuri(paramArrayOfint);
        break;
      case 16:
        MoveFireball(paramArrayOfint);
        break;
      case 17:
        MoveFireball2(paramArrayOfint);
        break;
      case 18:
        MoveFireball3(paramArrayOfint);
        break;
      case 19:
        MoveFireball4(paramArrayOfint);
        break;
      case 20:
        MoveFireball5(paramArrayOfint);
        break;
      case 21:
        MoveKazarifire(paramArrayOfint);
        break;
      case 22:
        MoveDBlock(paramArrayOfint);
        break;
      case 23:
        MoveDBlock(paramArrayOfint);
        break;
      case 24:
        MoveDBlock(paramArrayOfint);
        break;
      case 25:
        MoveDBlock(paramArrayOfint);
        break;
      case 26:
        MoveDBlock(paramArrayOfint);
        break;
      case 27:
        MoveBoss6Tama(paramArrayOfint);
        break;
      case 28:
        MoveAnimal(paramArrayOfint);
        break;
      case 29:
        MoveAnimal(paramArrayOfint);
        break;
      case 30:
        MoveAnimal(paramArrayOfint);
        break;
      case 31:
        MoveAnimal(paramArrayOfint);
        break;
      case 32:
        MoveAnimal(paramArrayOfint);
        break;
      case 33:
        MoveAnimal(paramArrayOfint);
        break;
      case 34:
        MoveAnimal(paramArrayOfint);
        break;
      case 35:
        MoveObj2Debug(paramArrayOfint);
        break;
    } 
  }
  
  private void DrawObj2() {
    byte b1 = 0;
    for (byte b2 = 0; b2 < 50; b2++) {
      int[] arrayOfInt = obj2Data[b2];
      if (arrayOfInt[0] > 0) {
        b1++;
        switch (arrayOfInt[1]) {
          case 1:
            DrawBakuhatu(arrayOfInt);
            break;
          case 2:
            DrawBakuhatu(arrayOfInt);
            break;
          case 3:
            DrawBakuhatu(arrayOfInt);
            break;
          case 4:
            DrawRing(arrayOfInt);
            break;
          case 5:
            DrawKira(arrayOfInt);
            break;
          case 6:
            DrawScore(arrayOfInt);
            break;
          case 7:
            DrawTama(arrayOfInt);
            break;
          case 8:
            DrawTama(arrayOfInt);
            break;
          case 9:
            DrawTama(arrayOfInt);
            break;
          case 10:
            DrawTama(arrayOfInt);
            break;
          case 13:
            DrawTama(arrayOfInt);
            break;
          case 11:
            DrawTama(arrayOfInt);
            break;
          case 12:
            DrawTama(arrayOfInt);
            break;
          case 14:
            DrawImoTama(arrayOfInt);
            break;
          case 15:
            DrawMusiKemuri(arrayOfInt);
            break;
          case 16:
            DrawFireball(arrayOfInt);
            break;
          case 17:
            DrawFireball(arrayOfInt);
            break;
          case 18:
            DrawFireball(arrayOfInt);
            break;
          case 19:
            DrawFireball(arrayOfInt);
            break;
          case 20:
            DrawFireball(arrayOfInt);
            break;
          case 21:
            DrawKazarifire(arrayOfInt);
            break;
          case 22:
            DrawDBlock(arrayOfInt);
            break;
          case 23:
            DrawDBlock(arrayOfInt);
            break;
          case 24:
            DrawDBlock(arrayOfInt);
            break;
          case 25:
            DrawDBlock(arrayOfInt);
            break;
          case 26:
            DrawBrkabe(arrayOfInt);
            break;
          case 27:
            DrawBoss6Tama(arrayOfInt);
            break;
          case 28:
            DrawAnimal(arrayOfInt);
            break;
          case 29:
            DrawAnimal(arrayOfInt);
            break;
          case 30:
            DrawAnimal(arrayOfInt);
            break;
          case 31:
            DrawAnimal(arrayOfInt);
            break;
          case 32:
            DrawAnimal(arrayOfInt);
            break;
          case 33:
            DrawAnimal(arrayOfInt);
            break;
          case 34:
            DrawAnimal(arrayOfInt);
            break;
          case 35:
            DrawObj2Debug(arrayOfInt);
            break;
        } 
      } 
    } 
    if (b1 > 42);
  }
  
  private void MoveBakudan(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 4 && paramArrayOfint[6] < 20 && IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
      playdamageset(); 
    if (paramArrayOfint[6] > 24)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveBakuhatu(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 24)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveRing(int[] paramArrayOfint) {
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    paramArrayOfint[15] = this.animeTimer >> 1;
    int i = paramArrayOfint[2];
    int j = paramArrayOfint[3];
    int k = paramArrayOfint[11];
    int m = paramArrayOfint[2] / 100;
    int n = paramArrayOfint[3] / 100;
    if (IsHitSonic(m, n, 12, 12, false) != 0) {
      SetObj2(5, m, n, 0, 0, 0, 0);
      ringcount++;
      paramArrayOfint[0] = 0;
      return;
    } 
    if (k > 0) {
      if (blockColChk_Enemy(m, n + 4)) {
        int i1 = (blockLinkTable[this.enemyBlock] & 0xFF) << 4;
        n = (n & 0xFFFFFFF0) + 16 - Math.abs(scdtblwk[i1 + (m & 0xF)]);
        k = -(k >> 1);
      } else {
        k += 6;
        if (k > 500)
          k = 500; 
      } 
    } else if (blockColChk_Enemy(m, n - 4)) {
      for (byte b = 0; b < 3; b++) {
        if (!blockColChk_Enemy(m, ++n - 4))
          break; 
      } 
      k = -(k >> 1);
    } else {
      k += 6;
      if (k > 500)
        k = 500; 
    } 
    if (n < 0) {
      n = 0;
    } else if (n > 99999) {
      paramArrayOfint[0] = 0;
    } 
    paramArrayOfint[3] = n * 100;
    paramArrayOfint[11] = k;
    if (paramArrayOfint[6] > 300)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveKira(int[] paramArrayOfint) {
    paramArrayOfint[15] = paramArrayOfint[6] / 8;
    if (paramArrayOfint[15] > 3)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveScore(int[] paramArrayOfint) {
    paramArrayOfint[3] = paramArrayOfint[3] - 100;
    if (paramArrayOfint[6] > 50)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveNormalTama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 8, 8, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    paramArrayOfint[15] = 2 + (this.animeTimer & 0x1);
  }
  
  private void MoveHachiTama(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 10) {
      if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 10, 10, false) != 0)
        playdamageset(); 
      paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
      paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    } 
    paramArrayOfint[15] = 2 + (this.animeTimer & 0x1);
  }
  
  private void MoveKaniTama(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 5) {
      if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 10, 10, false) != 0)
        playdamageset(); 
      paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
      paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
      paramArrayOfint[11] = paramArrayOfint[11] + 10;
      if (paramArrayOfint[11] > 600)
        paramArrayOfint[11] = 600; 
    } 
    paramArrayOfint[15] = 4 + (this.animeTimer & 0x1);
  }
  
  private void MoveButaTama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
      playdamageset(); 
    int i = paramArrayOfint[2];
    int j = paramArrayOfint[3];
    int k = paramArrayOfint[10];
    int m = paramArrayOfint[11];
    m += 10;
    if (m > 300)
      m = 300; 
    i += k;
    j += m;
    if (m > 0 && blockColChk_Enemy(i / 100, j / 100 + 5))
      while (true) {
        int n = getPlayerArg(i / 100, j / 100 + 5);
        j -= 100;
        if (!blockColChk_Enemy(i / 100, j / 100 + 5)) {
          if (n > 270) {
            k = -Math.abs(k);
          } else if (n > 15) {
            k = Math.abs(k);
          } 
          m = -m;
          break;
        } 
      }  
    if (j < 0) {
      j = 0;
    } else if (j > 999900) {
      paramArrayOfint[0] = 0;
    } 
    paramArrayOfint[15] = 8 + (this.animeTimer & 0x1);
    paramArrayOfint[2] = i;
    paramArrayOfint[3] = j;
    paramArrayOfint[10] = k;
    paramArrayOfint[11] = m;
    if (paramArrayOfint[6] > 300) {
      SetObj2(1, paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 0, 0, 0, 0);
      paramArrayOfint[0] = 0;
    } 
  }
  
  private void MoveBroboTama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 8, 8, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    paramArrayOfint[11] = paramArrayOfint[11] + 10;
    if (paramArrayOfint[11] > 800)
      paramArrayOfint[11] = 800; 
    paramArrayOfint[15] = 12 + (this.animeTimer & 0x1);
  }
  
  private void MoveUniTama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
      playdamageset(); 
    paramArrayOfint[15] = 6;
  }
  
  private void MoveUni2Tama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    paramArrayOfint[15] = 7;
  }
  
  private void MoveImoTama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
      playdamageset(); 
    paramArrayOfint[15] = 11;
    int i = paramArrayOfint[2];
    int j = paramArrayOfint[3];
    int k = paramArrayOfint[10];
    int m = paramArrayOfint[11];
    m += 10;
    if (m > 400)
      m = 400; 
    i += k;
    j += m;
    if (m > 0 && blockColChk_Enemy(i / 100, j / 100 + 4))
      while (true) {
        j -= 100;
        if (!blockColChk_Enemy(i / 100, j / 100 + 4)) {
          m = -m;
          break;
        } 
      }  
    if (j < 0) {
      j = 0;
    } else if (j > 999900) {
      paramArrayOfint[0] = 0;
    } 
    paramArrayOfint[2] = i;
    paramArrayOfint[3] = j;
    paramArrayOfint[10] = k;
    paramArrayOfint[11] = m;
    if (paramArrayOfint[6] > 180)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveMusiKemuri(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 30)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveFireball(int[] paramArrayOfint) {
    switch (paramArrayOfint[14]) {
      case 2:
        paramArrayOfint[15] = 4;
        paramArrayOfint[5] = 8;
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 3:
        if (paramArrayOfint[5] > 0)
          return; 
        paramArrayOfint[0] = 0;
        return;
    } 
    if (paramArrayOfint[6] > 4) {
      int i = paramArrayOfint[2] / 100;
      int j = paramArrayOfint[3] / 100;
      if (paramArrayOfint[10] > 0) {
        i += 5;
      } else if (paramArrayOfint[10] < 0) {
        i -= 5;
      } 
      if (paramArrayOfint[11] > 0) {
        j += 5;
      } else if (paramArrayOfint[11] < 0) {
        j -= 5;
      } 
      if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0)
        playdamageset(); 
      paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
      paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
      if (paramArrayOfint[8] == 1) {
        paramArrayOfint[11] = paramArrayOfint[11] + 6;
        if (paramArrayOfint[11] > 1000)
          paramArrayOfint[11] = 1000; 
      } 
    } 
    if (paramArrayOfint[10] > 0) {
      paramArrayOfint[18] = 3;
      if (blockColChk_Enemy(paramArrayOfint[2] / 100 + 15, paramArrayOfint[3] / 100))
        paramArrayOfint[14] = 2; 
    } else if (paramArrayOfint[10] < 0) {
      paramArrayOfint[18] = 1;
      if (blockColChk_Enemy(paramArrayOfint[2] / 100 - 15, paramArrayOfint[3] / 100))
        paramArrayOfint[14] = 2; 
    } else if (paramArrayOfint[11] < 0) {
      paramArrayOfint[18] = 2;
    } else if (paramArrayOfint[11] > 0) {
      paramArrayOfint[18] = 0;
      if (blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 16))
        paramArrayOfint[14] = 2; 
    } 
  }
  
  private void MoveFireball2(int[] paramArrayOfint) {
    int i = paramArrayOfint[2] / 100;
    int j = paramArrayOfint[3] / 100 + 6;
    if (IsHitSonic(i, j, 10, 10, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[10] = 0;
        paramArrayOfint[11] = 20;
        paramArrayOfint[18] = 0;
        paramArrayOfint[19] = 0;
        paramArrayOfint[14] = 1;
      case 1:
        paramArrayOfint[11] = paramArrayOfint[11] + 4;
        if (paramArrayOfint[11] > 300)
          paramArrayOfint[11] = 300; 
        if (blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 14)) {
          SetObj2(18, paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 0, 0, 0, 0);
          SetObj2(18, paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 0, 0, 1, 0);
          paramArrayOfint[11] = 0;
          paramArrayOfint[5] = 13;
          paramArrayOfint[14] = paramArrayOfint[14] + 1;
          break;
        } 
        return;
      case 2:
        break;
    } 
    paramArrayOfint[15] = 4;
    if (paramArrayOfint[5] <= 0)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveFireball3(int[] paramArrayOfint) {
    int i = paramArrayOfint[2] / 100;
    int j = paramArrayOfint[3] / 100 + 6;
    if (IsHitSonic(i, j, 12, 12, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[10] = (paramArrayOfint[19] == 1) ? 64 : -64;
        paramArrayOfint[11] = 100;
        paramArrayOfint[5] = 100;
        switch (paramArrayOfint[8]) {
          default:
            paramArrayOfint[5] = 100;
            paramArrayOfint[9] = 5;
            break;
          case 1:
            paramArrayOfint[5] = 140;
            paramArrayOfint[9] = 8;
            break;
        } 
        paramArrayOfint[18] = 0;
        paramArrayOfint[15] = 0;
        paramArrayOfint[14] = 1;
      case 1:
        if (blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 14))
          paramArrayOfint[3] = paramArrayOfint[3] - 200; 
        if (paramArrayOfint[6] % 24 == 0 && paramArrayOfint[9] > 0) {
          paramArrayOfint[9] = paramArrayOfint[9] - 1;
          if (paramArrayOfint[8] == 0) {
            SetObj2(19, paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 0, 0, 0, 0);
          } else if (paramArrayOfint[8] == 1) {
            SetObj2(19, paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 0, 0, 0, 999);
          } 
        } 
        if (paramArrayOfint[5] <= 0) {
          paramArrayOfint[5] = 12;
          paramArrayOfint[14] = paramArrayOfint[14] + 1;
          break;
        } 
        return;
      case 2:
        break;
    } 
    paramArrayOfint[15] = 4;
    if (paramArrayOfint[5] <= 0)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveFireball4(int[] paramArrayOfint) {
    int i = paramArrayOfint[2] / 100;
    int j = paramArrayOfint[3] / 100 + 6;
    if (IsHitSonic(i, j, 12, 12, false) != 0)
      playdamageset(); 
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[14] = 1;
      case 1:
        if (paramArrayOfint[8] == 0)
          paramArrayOfint[8] = 100; 
        paramArrayOfint[5] = 0;
        paramArrayOfint[18] = 0;
        paramArrayOfint[15] = 0;
        paramArrayOfint[14] = 1;
      case 2:
        if (paramArrayOfint[8] <= 998 && paramArrayOfint[6] >= paramArrayOfint[8]) {
          paramArrayOfint[15] = 4;
          paramArrayOfint[5] = 8;
          paramArrayOfint[14] = paramArrayOfint[14] + 1;
          break;
        } 
        return;
      case 3:
        break;
    } 
    if (paramArrayOfint[5] <= 0)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveFireball5(int[] paramArrayOfint) {
    int j = paramArrayOfint[2] / 100;
    int k = paramArrayOfint[3] / 100 + ((paramArrayOfint[18] == 0) ? 6 : -6);
    if (IsHitSonic(j, k, 10, 12, false) != 0)
      playdamageset(); 
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[14] = 1;
      case 1:
        if (paramArrayOfint[8] == 0)
          paramArrayOfint[8] = 64; 
        paramArrayOfint[5] = 0;
        paramArrayOfint[9] = 0;
        paramArrayOfint[18] = 0;
        paramArrayOfint[15] = 0;
        paramArrayOfint[17] = paramArrayOfint[3];
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
        break;
      case 2:
        break;
    } 
    paramArrayOfint[9] = paramArrayOfint[9] + 140;
    paramArrayOfint[15] = this.animeTimer & 0x1;
    int i = paramArrayOfint[9] / 100 % 180;
    paramArrayOfint[3] = paramArrayOfint[17] - Math.abs(dSin(i)) * paramArrayOfint[8];
    if (i < 90) {
      paramArrayOfint[18] = 2;
    } else {
      paramArrayOfint[18] = 0;
    } 
    if (paramArrayOfint[9] >= 18000)
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveKazarifire(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 2, 10, 10, false) != 0)
      playdamageset(); 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    if (blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100))
      paramArrayOfint[0] = 0; 
  }
  
  private void MoveDBlock(int[] paramArrayOfint) {
    paramArrayOfint[15] = paramArrayOfint[8];
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    paramArrayOfint[11] = paramArrayOfint[11] + 20;
    if (paramArrayOfint[11] > 800)
      paramArrayOfint[11] = 800; 
  }
  
  private void MoveBoss6Tama(int[] paramArrayOfint) {
    if (IsHitSonic(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100, 12, 12, false) != 0 && paramArrayOfint[14] == 4)
      playdamageset(); 
    if (paramArrayOfint[14] <= 2) {
      paramArrayOfint[15] = Boss6TamaAnmTbl[this.animeTimer & 0x3];
    } else if (paramArrayOfint[14] <= 3) {
      paramArrayOfint[15] = Boss6TamaAnmTbl2[this.animeTimer % 20];
    } else {
      paramArrayOfint[15] = Boss6TamaAnmTbl3[this.animeTimer & 0x3];
    } 
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[14] = 1;
      case 1:
        paramArrayOfint[10] = (paramArrayOfint[8] - paramArrayOfint[2]) / 20;
        paramArrayOfint[11] = 0;
        paramArrayOfint[15] = 1;
        paramArrayOfint[5] = 30;
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 2:
        if (paramArrayOfint[2] > paramArrayOfint[8]) {
          paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
          paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
        } 
        if (paramArrayOfint[5] > 0)
          return; 
        paramArrayOfint[2] = paramArrayOfint[8];
        paramArrayOfint[5] = 90;
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 3:
        if (paramArrayOfint[5] <= 0) {
          int i = PlayerPosX() * 100;
          char c = '餠';
          paramArrayOfint[10] = (i - paramArrayOfint[2]) / 256;
          paramArrayOfint[11] = (c - paramArrayOfint[3]) / 256;
          paramArrayOfint[5] = 150;
          paramArrayOfint[14] = paramArrayOfint[14] + 1;
          break;
        } 
        return;
      case 4:
        break;
    } 
    paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
    paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
    if (paramArrayOfint[5] <= 0)
      paramArrayOfint[0] = 0; 
  }
  
  private void DrawBakuhatu(int[] paramArrayOfint) {
    short s1;
    short s2;
    short s3;
    short s4;
    int i = paramArrayOfint[6] / 4 % 5;
    if (paramArrayOfint[1] == 2) {
      s1 = RectTblKemuri[i][0];
      s2 = RectTblKemuri[i][1];
      s3 = RectTblKemuri[i][2];
      s4 = RectTblKemuri[i][3];
    } else {
      s1 = RectTblBakuhatu[i][0];
      s2 = RectTblBakuhatu[i][1];
      s3 = RectTblBakuhatu[i][2];
      s4 = RectTblBakuhatu[i][3];
    } 
    drawRegion(gg, this.m_imgObj[97], s1, s2, s3, s4, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawRing(int[] paramArrayOfint) {
    int[] arrayOfInt1 = { 0, 0, 0, 2 };
    int[] arrayOfInt2 = { 0, 16, 32, 16 };
    drawRegion(gg, this.m_imgObj[0], 0, arrayOfInt2[this.animeTimer & 0x3], 16, 16, arrayOfInt1[this.animeTimer & 0x3], paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawKira(int[] paramArrayOfint) {
    if (paramArrayOfint[15] < 0 || paramArrayOfint[15] > 3)
      return; 
    drawRegion(gg, this.m_imgObj[0], 16, paramArrayOfint[15] * 16, 16, 16, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawScore(int[] paramArrayOfint) {
    byte b1;
    byte b2;
    byte b3;
    int i = paramArrayOfint[8];
    if (i < 0)
      return; 
    if (i > 6)
      i = 6; 
    switch (i) {
      default:
        b1 = 8;
        b2 = 0;
        b3 = 5;
        break;
      case 1:
        b1 = 3;
        b2 = 0;
        b3 = 10;
        break;
      case 2:
        b1 = 3;
        b2 = 16;
        b3 = 10;
        break;
      case 3:
        b1 = 3;
        b2 = 0;
        b3 = 15;
        break;
      case 4:
        b1 = 3;
        b2 = 8;
        b3 = 15;
        break;
      case 5:
        b1 = 3;
        b2 = 16;
        b3 = 15;
        break;
      case 6:
        b1 = 3;
        b2 = 0;
        b3 = 20;
        break;
    } 
    byte b4 = 8;
    drawRegion(gg, this.m_imgCmd[SYSSCORE], b1, b2, b3, b4, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawTama(int[] paramArrayOfint) {
    int i = paramArrayOfint[15] % 14;
    short s1 = RectTblTama[i][0];
    short s2 = RectTblTama[i][1];
    short s3 = RectTblTama[i][2];
    short s4 = RectTblTama[i][3];
    drawRegion(gg, this.m_imgObj[96], s1, s2, s3, s4, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawImoTama(int[] paramArrayOfint) {
    boolean bool = (paramArrayOfint[8] == 0) ? false : true;
    short s1 = RectTblImo[bool][0];
    short s2 = RectTblImo[bool][1];
    short s3 = RectTblImo[bool][2];
    short s4 = RectTblImo[bool][3];
    drawRegion(gg, this.m_imgObj[49], s1, s2, s3, s4, (paramArrayOfint[19] == 0) ? 0 : 2, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawMusiKemuri(int[] paramArrayOfint) {
    byte b;
    if (paramArrayOfint[6] < 5) {
      b = 0;
    } else if (paramArrayOfint[6] < 10) {
      b = 8;
    } else if (paramArrayOfint[6] < 15) {
      b = 16;
    } else {
      return;
    } 
    if ((this.animeTimer & 0x1) == 0)
      drawRegion(gg, this.m_imgObj[41], b, 96, 8, 8, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2); 
  }
  
  private void DrawFireball(int[] paramArrayOfint) {
    int i = paramArrayOfint[15] % 5;
    if (i == 0)
      i += this.animeTimer & 0x3; 
    int k = paramArrayOfint[18];
    boolean bool = false;
    int j = i * 32;
    byte b1 = 24;
    byte b2 = 32;
    drawRegion(gg, this.m_imgObj[101], bool, j, b1, b2, GetDrawRot(k), paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawKazarifire(int[] paramArrayOfint) {
    int i = paramArrayOfint[15] % 1;
    if (i == 0)
      i += this.animeTimer >> 1 & 0x1; 
    boolean bool2 = (paramArrayOfint[10] < 0) ? false : true;
    boolean bool1 = false;
    char c = (i == 0) ? ' ' : '¨';
    byte b1 = 16;
    byte b2 = 8;
    drawRegion(gg, this.m_imgObj[101], bool1, c, b1, b2, bool2, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawDBlock(int[] paramArrayOfint) {
    byte b3;
    int i = paramArrayOfint[15] & 0x3;
    if (paramArrayOfint[1] == 23) {
      b3 = 102;
    } else if (paramArrayOfint[1] == 24) {
      b3 = BRKABE;
    } else {
      b3 = 27;
    } 
    short s1 = RectTblDBlock[i][0];
    short s2 = RectTblDBlock[i][1];
    byte b1 = 16;
    byte b2 = 16;
    drawRegion(gg, this.m_imgObj[b3], s1, s2, b1, b2, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawBrkabe(int[] paramArrayOfint) {
    byte b1;
    switch (paramArrayOfint[8]) {
      default:
        b1 = 0;
        break;
      case 1:
        b1 = 16;
        break;
      case 2:
        b1 = 32;
        break;
    } 
    boolean bool = false;
    byte b2 = 16;
    byte b3 = 16;
    drawRegion(gg, this.m_imgObj[18], b1, bool, b2, b3, 0, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void DrawBoss6Tama(int[] paramArrayOfint) {
    byte b;
    if (paramArrayOfint[15] < 0)
      return; 
    int i = paramArrayOfint[15] % 5;
    short s1 = RectTblBoss6Tama[i][0];
    short s2 = RectTblBoss6Tama[i][1];
    short s3 = RectTblBoss6Tama[i][2];
    short s4 = RectTblBoss6Tama[i][3];
    switch (this.cpuTimer & 0x3) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        break;
      case 2:
        b = 1;
        break;
      case 3:
        b = 3;
        break;
    } 
    drawRegion(gg, this.m_imgObj[145], s1, s2, s3, s4, b, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1], 0x1 | 0x2);
  }
  
  private void MoveAnimal(int[] paramArrayOfint) {
    int i = (paramArrayOfint[1] - 28) % 7;
    if (paramArrayOfint[14] > 2) {
      paramArrayOfint[2] = paramArrayOfint[2] + paramArrayOfint[10];
      paramArrayOfint[3] = paramArrayOfint[3] + paramArrayOfint[11];
      paramArrayOfint[11] = paramArrayOfint[11] + 10;
      if (paramArrayOfint[11] > 800)
        paramArrayOfint[11] = 800; 
    } 
    switch (paramArrayOfint[14]) {
      default:
        paramArrayOfint[14] = 1;
      case 1:
        paramArrayOfint[10] = 0;
        paramArrayOfint[15] = 0;
        paramArrayOfint[5] = paramArrayOfint[8];
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 2:
        if (paramArrayOfint[5] > 0)
          break; 
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 3:
        paramArrayOfint[15] = 0;
        if (!blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 8))
          break; 
        paramArrayOfint[10] = this.MoveAnimalTbl[i][0];
        paramArrayOfint[11] = this.MoveAnimalTbl[i][1];
        if (paramArrayOfint[19] == 1)
          paramArrayOfint[10] = -paramArrayOfint[10]; 
        paramArrayOfint[6] = 0;
        paramArrayOfint[14] = paramArrayOfint[14] + 1;
      case 4:
        paramArrayOfint[15] = 1;
        if (blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 8)) {
          for (byte b = 0; b < 3; b++) {
            paramArrayOfint[3] = paramArrayOfint[3] - 200;
            if (!blockColChk_Enemy(paramArrayOfint[2] / 100, paramArrayOfint[3] / 100 + 8))
              break; 
          } 
          paramArrayOfint[11] = this.MoveAnimalTbl[i][1];
        } 
        break;
    } 
    if (paramArrayOfint[3] < 0) {
      paramArrayOfint[3] = 0;
    } else if (paramArrayOfint[3] > 999900) {
      paramArrayOfint[0] = 0;
    } 
  }
  
  private void DrawAnimal(int[] paramArrayOfint) {
    byte b;
    int i = paramArrayOfint[15] % 3;
    if (i < 0)
      return; 
    if (i == 1 && paramArrayOfint[11] > 0)
      i++; 
    int j = (paramArrayOfint[1] - 28) % 7;
    if (j < 0)
      j = 0; 
    i += j * 3;
    short s1 = this.RectAnimalTbl[i][0];
    short s2 = this.RectAnimalTbl[i][1];
    short s3 = this.RectAnimalTbl[i][2];
    short s4 = this.RectAnimalTbl[i][3];
    short s5 = this.RectAnimalTbl[i][4];
    switch (paramArrayOfint[19]) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        break;
      case 2:
        b = 1;
        break;
      case 3:
        b = 3;
        break;
    } 
    drawRegion(gg, this.m_imgObj[100], s1, s2, s3, s4, b, paramArrayOfint[2] / 100 - mapView[0], paramArrayOfint[3] / 100 - mapView[1] + s5, 0x1 | 0x2);
  }
  
  private void MoveObj2Debug(int[] paramArrayOfint) {
    if (paramArrayOfint[6] > 0)
      paramArrayOfint[0] = 0; 
  }
  
  private void DrawObj2Debug(int[] paramArrayOfint) {}
  
  private void InitBoss() {
    this.bossType = this.zoneNumber;
    this.bossStep = 0;
    this.bossAnim = 0;
    this.bossDir = 0;
    this.bossAngle = 0;
    this.bossAngle2 = 0;
    this.bossParam1 = 0;
    this.bossParam2 = 0;
    this.bossPosX = this.bossPosY = 0;
    this.bossOfsX = this.bossOfsY = 0;
    this.bossCount = 0;
    this.bossFrame = 0;
    this.bossFlash = 0;
    this.bossStopCount = 0;
    this.bossFace = 0;
    this.bossFaceCount = 0;
    if (this.zoneNumber == 3 || this.zoneNumber == 4) {
      switch (m_nConfigValue[0]) {
        case 2:
          this.bossHP = 6;
          break;
        case 1:
          this.bossHP = 5;
          break;
        default:
          this.bossHP = 4;
          break;
      } 
    } else {
      switch (m_nConfigValue[0]) {
        case 2:
          this.bossHP = 8;
          break;
        case 1:
          this.bossHP = 6;
          break;
        default:
          this.bossHP = 4;
          break;
      } 
    } 
    if (this.zoneNumber < 5)
      PlayMusic(17); 
  }
  
  private void initBossData(int paramInt) {
    try {
      int[] arrayOfInt = new int[25];
      byte b;
      for (b = 0; b < arrayOfInt.length; b++)
        arrayOfInt[b] = 0; 
      InitBoss();
      if (paramInt == 0) {
        this.m_imgObj[120] = createImage("boss.png");
        this.m_imgObj[121] = createImage("bossball.png");
        arrayOfInt[1] = 120;
        this.bossOriginX = 10752;
        this.bossOriginY = 808;
      } else if (paramInt == 1) {
        this.m_imgObj[120] = createImage("boss.png");
        arrayOfInt[1] = 125;
        this.bossOriginX = 0;
        this.bossOriginY = 0;
        this.bossPosX = 769600;
        this.bossPosY = 147200;
      } else if (paramInt == 2) {
        this.m_imgObj[120] = createImage("boss.png");
        this.m_imgObj[131] = createImage("fire.png");
        arrayOfInt[1] = 130;
        this.bossOriginX = 6304;
        this.bossOriginY = 608;
        this.bossPosX = (6640 - this.bossOriginX) * 100;
        this.bossPosY = (544 - this.bossOriginY) * 100;
      } else if (paramInt == 3) {
        this.m_imgObj[120] = createImage("boss.png");
        arrayOfInt[1] = 135;
        this.bossOriginX = 8352;
        this.bossOriginY = 576;
        this.bossPosX = 0;
        this.bossPosY = 0;
        for (b = 0; b < 3; b++)
          boss4Sisoo[b][0] = 0; 
        boolean bool = false;
        int[][] arrayOfInt1 = searchObject(23, 255);
        for (b = 0; b < arrayOfInt1.length; b++) {
          boolean bool1;
          ObjectAct[arrayOfInt1[b][20]] = true;
          ObjectDead[arrayOfInt1[b][20]] = false;
          if (arrayOfInt1[b][2] == 8352) {
            bool1 = true;
          } else if (arrayOfInt1[b][2] == 8452) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          boss4Sisoo[bool1][0] = (short)arrayOfInt1[b][23];
          boss4Sisoo[bool1][1] = (short)(arrayOfInt1[b][2] - this.bossOriginX);
          boss4Sisoo[bool1][2] = (short)((bool1 == 2) ? 20 : -20);
          boss4Sisoo[bool1][3] = 0;
        } 
      } else if (paramInt == 4) {
        PreInitBoss5();
        this.m_imgObj[120] = createImage("boss.png");
        arrayOfInt[1] = 140;
        this.bossPosX = 0;
        this.bossPosY = 0;
      } else if (paramInt == 5) {
        for (b = 0; b < ''; b++)
          this.m_imgObj[b] = null; 
        DoGc();
        this.m_imgObj[0] = createImage("/ring.png");
        this.m_imgObj[97] = createImage("/bakuhatu.png");
        this.m_imgObj[84] = createImage("/beltcon.png");
        this.m_imgObj[120] = createImage("boss.png");
        this.m_imgObj[145] = createImage("boss6.png");
        this.m_imgObj[146] = createImage("eggman.png");
        this.m_imgObj[147] = createImage("boss2.png");
        arrayOfInt[1] = 145;
        this.bossOriginX = 1272;
        this.bossOriginY = 120;
        this.bossPosX = 0;
        this.bossPosY = 0;
      } 
      arrayOfInt[0] = 1;
      arrayOfInt[16] = mapView[0] + 256 + 46;
      arrayOfInt[2] = mapView[0] + 256 + 46;
      arrayOfInt[17] = mapView[1] + 46;
      arrayOfInt[3] = mapView[1] + 46;
      arrayOfInt[4] = 0;
      arrayOfInt[14] = 0;
      arrayOfInt[20] = ObjectAct.length - 1;
      ObjectAct[arrayOfInt[20]] = true;
      ObjectDead[arrayOfInt[20]] = false;
      addObject(arrayOfInt);
    } catch (Exception exception) {}
  }
  
  private void startBossMode() {
    bossModeOn = true;
    initBossData(this.zoneNumber);
  }
  
  private void endBossMode() {
    bossBreakOn = true;
    bossModeOn = false;
    MapEndCounter = 1;
    if (this.zoneNumber != 5)
      PlayZoneBGM(); 
  }
  
  private boolean _BossDefault(int[] paramArrayOfint) {
    paramArrayOfint[2] = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
    paramArrayOfint[3] = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY;
    if (this.bossFlash > 0)
      this.bossFlash--; 
    if (this.bossFaceCount > 0 && --this.bossFaceCount == 0)
      this.bossFace = 0; 
    if (this.bossStopCount > 0) {
      this.bossStopCount--;
      return true;
    } 
    if (this.bossCount > 0)
      this.bossCount--; 
    this.bossFrame++;
    return false;
  }
  
  private boolean _BossDead() {
    int i = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100;
    switch (this.bossStep) {
      case 100:
        this.bossAnim = 3;
        this.bossCount = 120;
        this.bossStep++;
      case 101:
        if (this.bossParam1 > 0)
          this.bossParam1--; 
        if (this.bossCount > 0)
          break; 
        this.bossAnim = 4;
        this.bossCount = 80;
        this.bossStep++;
      case 102:
        this.bossAnim = 4;
        this.bossPosY += 200;
        if (i < BossDeadLimitY[this.zoneNumber])
          break; 
        this.bossCount = 40;
        this.bossStep++;
      case 103:
        if (this.bossCount > 0)
          break; 
        endBossMode();
        this.bossCount = 24;
        this.bossStep++;
      case 104:
        this.bossPosY -= 80;
        if (this.bossCount > 0)
          break; 
        this.bossCount = 40;
        this.bossStep++;
      case 105:
        if (this.bossCount > 0)
          break; 
        this.bossStep++;
      case 106:
        this.bossAnim = 5;
        this.bossDir = 1;
        this.bossPosX += 100;
        this.bossPosY -= 50;
        if (this.bossCount > 0)
          break; 
        this.bossCount = 120;
        this.bossStep++;
      case 107:
        this.bossAnim = 6;
        this.bossDir = 1;
        this.bossPosX += 500;
        this.bossPosY -= 50;
        if (this.bossCount > 0)
          break; 
        this.bossStep++;
      case 108:
        this.bossAnim = -1;
        return true;
    } 
    return false;
  }
  
  private void UpdateBossPos(int[] paramArrayOfint) {
    paramArrayOfint[2] = this.bossOriginX + (this.bossPosX + this.bossOfsX) / 100;
    paramArrayOfint[3] = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100;
  }
  
  private void ColliRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, paramInt1, paramInt2, paramInt1, paramInt2, paramInt3, paramInt4);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = paramInt2 - paramInt4 << 8;
        playerRaidOn(-1);
      } else if (i == 1) {
        PlayerParam[0] = paramInt1 - paramInt3 - 12 << 8;
        PlayerParam[10] = 0;
      } else if (i == 2) {
        PlayerParam[0] = paramInt1 + paramInt3 + 12 + 1 << 8;
        PlayerParam[10] = 0;
      } else if (i == 3) {
        PlayerParam[1] = paramInt2 + paramInt4 + 12 + 12 + 1 << 8;
      }  
    if (raidOn && i != 0)
      raidOn = false; 
  }
  
  private boolean isHitBoss() {
    if (this.bossStep >= 50)
      return false; 
    if (PlayerJump == true && PlayerDamage == true)
      return false; 
    if (comboScore > 0)
      return false; 
    int i = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
    int j = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
    int k = i - PlayerPosX();
    int m = j - PlayerPosY() - 16;
    int n = k * k + m * m;
    if (n < 1024) {
      if (PlayerBall) {
        comboScore = 1;
        Vibrate(100);
        return true;
      } 
      if (mutekicount == 0)
        playdamageset(); 
      comboScore = 1;
      return false;
    } 
    return false;
  }
  
  private boolean isHitBoss6() {
    byte b = 6;
    byte b1 = -8;
    if (!PlayerBall || this.bossFlash > 0 || this.bossStep >= 100)
      return false; 
    int i = PlayerPosX();
    int j = PlayerPosY() - 16;
    int k = this.bossPosX / 100 + this.bossOriginX;
    int m = this.bossPosY / 100 + this.bossOriginY;
    if (k - 32 - 8 - b <= i && i <= k + 32 + 8 + b && m - 32 - b1 <= j && j <= m + 32 + b1) {
      Vibrate(100);
      return true;
    } 
    return false;
  }
  
  private void boundBossHit(int paramInt1, int paramInt2, int paramInt3) {
    char c;
    int i = PlayerPosX();
    int j = PlayerPosY() - 16;
    if (paramInt2 >= j) {
      if (paramInt1 <= i) {
        c = '-';
      } else {
        c = 'Ļ';
      } 
    } else if (paramInt1 <= i) {
      c = '';
    } else {
      c = 'á';
    } 
    PlayerParam[3] = dSin(c) * 8;
    PlayerParam[5] = dCos(c) * 8;
  }
  
  private void boss1_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    this.bossOfsX = 0;
    this.bossOfsY = dSin(this.bossAngle2 / 100) * 8;
    _BossDefault(arrayOfInt);
    if (isHitBoss()) {
      boundBossHit(arrayOfInt[2], arrayOfInt[3], 12);
      if (this.bossFlash == 0 && this.bossStep < 100) {
        if (--this.bossHP <= 0) {
          this.bossStep = 100;
          return;
        } 
        this.bossFace = 3;
        this.bossFlash = this.bossFaceCount = 60;
      } 
    } 
    if (this.bossStep < 100) {
      int k;
      for (k = this.bossAngle / 100; k < 0; k += 360);
      int m = 180 + dSin(k) * 90 / 100;
      int i = (this.bossPosX + this.bossOfsX) / 100 + dSin(m) * this.bossParam1 / 100 + this.RectBossTbl[15][4];
      int j = (this.bossPosY + this.bossOfsY) / 100 + dCos(m) * this.bossParam1 / 100 + this.RectBossTbl[15][5];
      if (IsDistance(this.bossOriginX + i, this.bossOriginY + j, 28)) {
        playdamageset();
        this.bossFace = 1;
        this.bossFaceCount = 60;
      } 
    } 
    switch (this.bossStep) {
      default:
        this.bossStep = 1;
      case 1:
        this.bossPosX = 10000;
        this.bossPosY = -12000;
        this.bossParam1 = -32;
        this.bossParam2 = -100;
        this.bossAngle = 0;
        this.bossAngle2 = 0;
        this.bossCount = 80;
        this.bossStep++;
      case 2:
        this.bossAnim = 0;
        this.bossPosY += 80;
        if (this.bossPosY < 0)
          return; 
        this.bossPosY = 0;
        this.bossCount = 100;
        this.bossStep++;
      case 3:
        this.bossAnim = 2;
        this.bossPosX -= 100;
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 4:
        this.bossAnim = 1;
        if (++this.bossParam1 < 96)
          return; 
        this.bossCount = 32;
        this.bossStep++;
      case 5:
        this.bossAnim = 2;
        this.bossPosX -= 50;
        this.bossAngle += this.bossParam2;
        this.bossAngle2 += 100;
        if (this.bossPosX <= -3200) {
          this.bossPosX = -3200;
          this.bossStep = 6;
        } 
        return;
      case 6:
        this.bossAnim = 2;
        this.bossPosX -= 100;
        this.bossAngle += this.bossParam2;
        this.bossAngle2 += 100;
        if (this.bossPosX > -3200)
          return; 
        this.bossPosX = -3200;
        this.bossStep++;
      case 7:
        this.bossAnim = 0;
        this.bossDir = 1;
        this.bossAngle2 += 100;
        if (this.bossParam2 < 0) {
          this.bossAngle += this.bossParam2;
          if (this.bossAngle < -9000) {
            this.bossParam2 = -this.bossParam2;
            this.bossAngle = -9000;
          } 
        } else {
          this.bossAngle += this.bossParam2;
          if (this.bossAngle < 9000)
            return; 
          this.bossAngle = 9000;
          this.bossParam2 = -this.bossParam2;
          this.bossCount = 64;
          this.bossStep++;
        } 
        return;
      case 8:
        this.bossAnim = 2;
        this.bossPosX += 100;
        this.bossAngle += this.bossParam2;
        this.bossAngle2 += 100;
      case 9:
        this.bossAnim = 0;
        this.bossDir = 0;
        this.bossAngle2 += 100;
        if (this.bossParam2 > 0) {
          this.bossAngle += this.bossParam2;
          if (this.bossAngle > 9000) {
            this.bossParam2 = -this.bossParam2;
            this.bossAngle = 9000;
          } 
        } else {
          this.bossAngle += this.bossParam2;
          if (this.bossAngle <= -9000) {
            this.bossAngle = -9000;
            this.bossParam2 = -this.bossParam2;
            this.bossCount = 64;
            this.bossStep = 6;
          } 
        } 
        return;
      case 100:
        this.bossAnim = 3;
        this.bossCount = 40;
        this.bossStep++;
      case 101:
        if (this.bossCount > 0)
          return; 
        this.bossCount = 180;
        this.bossStep++;
      case 102:
        if (this.bossParam1 > 0)
          this.bossParam1--; 
        if (this.bossCount > 0)
          return; 
        this.bossAnim = 4;
        this.bossCount = 80;
        this.bossStep++;
      case 103:
        this.bossAnim = 4;
        this.bossPosY += 200;
        UpdateBossPos(arrayOfInt);
        if (arrayOfInt[3] < BossDeadLimitY[this.zoneNumber])
          return; 
        this.bossCount = 40;
        this.bossStep++;
      case 104:
        if (this.bossCount > 0)
          return; 
        endBossMode();
        this.bossCount = 16;
        this.bossStep++;
      case 105:
        this.bossAnim = 5;
        this.bossDir = 1;
        this.bossPosX += 200;
        this.bossPosY -= 50;
        if (this.bossCount > 0)
          return; 
        this.bossCount = 120;
        this.bossStep++;
      case 106:
        this.bossAnim = 6;
        this.bossDir = 1;
        this.bossPosX += 600;
        this.bossPosY -= 50;
        if (this.bossCount <= 0) {
          this.bossStep++;
          break;
        } 
        return;
      case 107:
        break;
    } 
    this.bossAnim = -1;
    objectData[0] = 0;
  }
  
  private boolean moveBoss2Point(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (Math.abs(paramInt1 - this.bossPosX) > paramInt3) {
      if (paramInt1 > this.bossPosX) {
        this.bossPosX += paramInt3;
      } else {
        this.bossPosX -= paramInt3;
      } 
    } else {
      this.bossPosX = paramInt1;
    } 
    if (Math.abs(paramInt2 - this.bossPosY) > paramInt4) {
      if (paramInt2 > this.bossPosY) {
        this.bossPosY += paramInt4;
      } else {
        this.bossPosY -= paramInt4;
      } 
    } else {
      this.bossPosY = paramInt2;
    } 
    return (Math.abs(this.bossPosX - paramInt1) < 200 && Math.abs(this.bossPosY - paramInt2) < 200);
  }
  
  private void boss2_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    _BossDefault(arrayOfInt);
    this.bossAngle2 += 200;
    this.bossOfsX = 0;
    this.bossOfsY = dSin(this.bossAngle2 / 100) * 8;
    UpdateBossPos(arrayOfInt);
    int i = arrayOfInt[2] - PlayerPosX();
    int j = arrayOfInt[3] - PlayerPosY() - 16;
    int k = i * i + j * j;
    if (isHitBoss()) {
      boundBossHit(arrayOfInt[2], arrayOfInt[3], 8);
      if (this.bossFlash == 0 && this.bossStep < 100) {
        this.bossFace = 3;
        this.bossFlash = this.bossFaceCount = 60;
      } 
    } 
    switch (this.bossStep) {
      default:
        this.bossStep = 1;
      case 1:
        this.bossFace = 0;
        this.bossAnim = 2;
        this.bossPosX = boss2MoveTbl[0][0];
        this.bossPosY = boss2MoveTbl[0][1];
        this.bossAngle = 0;
        this.bossAngle2 = 0;
        this.bossStep++;
        if (k > 4096)
          return; 
        this.bossStep++;
      case 3:
        if (moveBoss2Point(boss2MoveTbl[1][0], boss2MoveTbl[1][1], 50, 100))
          this.bossStep++; 
        return;
      case 4:
        if (k > 4096 && arrayOfInt[3] < PlayerPosY() - 16)
          return; 
        this.bossStep++;
      case 5:
        if (moveBoss2Point(boss2MoveTbl[2][0], boss2MoveTbl[2][1], 50, 80))
          this.bossStep++; 
        return;
      case 6:
        if (k > 6400 && arrayOfInt[3] < PlayerPosY() - 16)
          return; 
        this.bossStep++;
      case 7:
        if (moveBoss2Point(boss2MoveTbl[3][0], boss2MoveTbl[3][1], 50, 80))
          this.bossStep++; 
        return;
      case 8:
        if (k > 6400 && arrayOfInt[3] < PlayerPosY() - 16)
          return; 
        this.bossStep++;
      case 9:
        if (moveBoss2Point(boss2MoveTbl[4][0], boss2MoveTbl[4][1], 80, 80))
          this.bossStep++; 
        return;
      case 10:
        if (k > 6400)
          return; 
        this.bossCount = 40;
        this.bossStep++;
      case 11:
        if (this.bossCount <= 0)
          this.bossStep = 100; 
        return;
      case 100:
        if (this.bossCount > 0)
          return; 
        endBossMode();
        this.bossCount = 16;
        this.bossStep++;
      case 101:
        this.bossAnim = 5;
        this.bossDir = 1;
        this.bossPosX += 100;
        this.bossPosY -= 30;
        if (this.bossCount > 0)
          return; 
        this.bossCount = 180;
        this.bossStep++;
      case 102:
        this.bossAnim = 6;
        this.bossDir = 1;
        this.bossPosX += 300;
        this.bossPosY -= 50;
        if (this.bossCount <= 0) {
          this.bossStep++;
          break;
        } 
        return;
      case 103:
        break;
    } 
    this.bossAnim = -1;
    objectData[0] = 0;
  }
  
  private void boss3_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    if (_BossDefault(objectData))
      return; 
    this.bossAngle2 += 100;
    this.bossOfsX = 0;
    this.bossOfsY = dSin(this.bossAngle2 / 100) * 10;
    if (this.bossStep > 99) {
      if (_BossDead())
        arrayOfInt[0] = 0; 
      return;
    } 
    if (isHitBoss() && this.bossStep > 3) {
      boundBossHit(arrayOfInt[2], arrayOfInt[3], 12);
      if (this.bossFlash == 0 && this.bossStep < 100) {
        if (--this.bossHP <= 0) {
          boss3FireCount = 0;
          this.bossStep = 100;
          return;
        } 
        this.bossFace = 3;
        this.bossFlash = this.bossFaceCount = 60;
        this.bossStopCount = 20;
      } 
    } 
    if (boss3FireCount > 0 && --boss3FireCount == 0) {
      boss3FireCount = 150 + rnd(150);
      SetObj2(16, this.bossOriginX + rnd(40) - 20, this.bossOriginY + 130, 0, -300, 0, 1);
    } 
    switch (this.bossStep) {
      default:
        this.bossStep = 1;
      case 1:
        this.bossFace = 0;
        this.bossAnim = 2;
        this.bossPosX = 31200;
        this.bossPosY = 4800;
        this.bossAngle = 0;
        this.bossAngle2 = 0;
        boss3FireCount = 0;
        this.bossCount = 30;
        this.bossStep++;
      case 2:
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 3:
        this.bossAnim = 2;
        this.bossPosX -= 150;
        this.bossPosY -= 20;
        if (this.bossPosX <= 10400 && this.bossCount <= 0) {
          this.bossPosX = 10400;
          this.bossAnim = 0;
          this.bossFace = 1;
          this.bossFaceCount = 60;
          this.bossCount = 60;
          boss3FireCount = 120;
          this.bossStep++;
        } 
        return;
      case 4:
        this.bossAnim = 2;
        this.bossDir = 0;
        this.bossStep++;
      case 5:
        this.bossAnim = 2;
        this.bossDir = 0;
        this.bossPosX -= 150;
        this.bossPosY -= 12;
        if (this.bossPosX > -10400)
          return; 
        this.bossPosX = -10400;
        this.bossStep++;
      case 6:
        this.bossPosY += 50;
        if (this.bossPosY <= 1200)
          return; 
        this.bossPosY = 1200;
        this.bossStep++;
      case 7:
        this.bossFace = 1;
        this.bossFaceCount = 60;
        this.bossCount = 48;
        this.bossStep++;
      case 8:
        if (this.bossCount == 8)
          SetObj2(17, arrayOfInt[2], arrayOfInt[3] + 16, 0, 0, 0, 0); 
        if (this.bossCount <= 0) {
          UpdateBossPos(arrayOfInt);
          this.bossStep++;
        } 
        return;
      case 9:
        this.bossAnim = 2;
        this.bossDir = 1;
        this.bossStep++;
      case 10:
        this.bossAnim = 2;
        this.bossDir = 1;
        this.bossPosX += 150;
        this.bossPosY -= 12;
        if (this.bossPosX < 10400)
          return; 
        this.bossPosX = 10400;
        this.bossStep++;
      case 11:
        this.bossPosY += 50;
        if (this.bossPosY <= 1200)
          return; 
        this.bossPosY = 1200;
        this.bossStep++;
      case 12:
        this.bossFace = 1;
        this.bossFaceCount = 60;
        this.bossCount = 48;
        this.bossStep++;
      case 13:
        if (this.bossCount == 8)
          SetObj2(17, arrayOfInt[2], arrayOfInt[3] + 16, 0, 0, 0, 0); 
        if (this.bossCount <= 0) {
          UpdateBossPos(arrayOfInt);
          this.bossStep++;
          break;
        } 
        return;
      case 14:
        break;
    } 
    this.bossPosY -= 100;
    if (this.bossPosY <= 0) {
      this.bossPosY = 0;
      this.bossStep = 4;
    } 
  }
  
  private void Boss4ShotTekkyu(int paramInt) {
    short s = boss4Sisoo[paramInt % 3][0];
    int[] arrayOfInt = new int[25];
    for (byte b = 0; b < 25; b++)
      arrayOfInt[b] = ObjectList[s][b]; 
    if (arrayOfInt[13] == 0) {
      int i = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
      int j = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
      arrayOfInt[6] = i << 8;
      arrayOfInt[7] = j + 32 << 8;
      arrayOfInt[10] = 0;
      arrayOfInt[11] = 70;
      arrayOfInt[12] = 0;
      arrayOfInt[13] = 1;
      arrayOfInt[14] = 240;
      InsertObject(arrayOfInt, s);
    } 
  }
  
  private boolean Boss4HitTekkyu(int paramInt) {
    if (this.bossFlash > 0 || this.bossStep >= 100)
      return false; 
    short s = boss4Sisoo[paramInt % 3][0];
    int[] arrayOfInt = new int[25];
    for (byte b = 0; b < 25; b++)
      arrayOfInt[b] = ObjectList[s][b]; 
    if (arrayOfInt[13] == 1 && arrayOfInt[14] < 210) {
      int i = arrayOfInt[6] >> 8;
      int j = arrayOfInt[7] >> 8;
      int k = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
      int m = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
      if (k - 24 <= i && i <= k + 24 && m - 24 <= j && j <= m + 24) {
        arrayOfInt[13] = 0;
        arrayOfInt[14] = 0;
        InsertObject(arrayOfInt, s);
        SetObj2(1, i, j, 0, 0, 0, 0);
        return true;
      } 
    } 
    return false;
  }
  
  private void boss4_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    _BossDefault(arrayOfInt);
    if (this.bossStep > 99) {
      if (_BossDead())
        arrayOfInt[0] = 0; 
      return;
    } 
    this.bossAngle2 += 400;
    this.bossOfsX = 0;
    this.bossOfsY = dSin(this.bossAngle2 / 100) * 8;
    int i = boss4Sisoo[0][1] * 100;
    int j = boss4Sisoo[1][1] * 100;
    int k = boss4Sisoo[2][1] * 100;
    UpdateBossPos(arrayOfInt);
    if (this.bossStep > 3 && this.bossPosY > -400)
      this.bossPosY -= 240; 
    boolean bool = false;
    bool = (bool || Boss4HitTekkyu(1)) ? true : false;
    bool = (bool || Boss4HitTekkyu(2)) ? true : false;
    if ((isHitBoss() || bool) && this.bossStep > 3) {
      boundBossHit(arrayOfInt[2], arrayOfInt[3], 12);
      if (this.bossFlash == 0 && this.bossStep < 100) {
        if (--this.bossHP <= 0) {
          this.bossStep = 100;
          return;
        } 
        this.bossFace = 3;
        this.bossFlash = this.bossFaceCount = 60;
      } 
    } 
    switch (this.bossStep) {
      default:
        this.bossStep = 1;
      case 1:
        this.bossFace = 1;
        this.bossAnim = 2;
        this.bossPosX = 21600;
        this.bossPosY = 9000;
        this.bossAngle = 0;
        this.bossAngle2 = 0;
        this.bossCount = 60;
        this.bossStep++;
      case 2:
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 3:
        this.bossPosX -= 80;
        if (this.bossPosX > k - 3500 + 10)
          return; 
        this.bossStep++;
      case 4:
        this.bossDir = 0;
        this.bossAnim = 2;
        this.bossPosX -= 80;
        if (this.bossPosX > k - 3500)
          return; 
        this.bossPosX = k - 3500;
        this.bossAnim = 0;
        this.bossFace = 1;
        this.bossFaceCount = 50;
        this.bossCount = 50;
        this.bossStep++;
      case 5:
        if (this.bossCount == 10)
          Boss4ShotTekkyu(2); 
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 6:
        this.bossAnim = 2;
        this.bossDir = 0;
        this.bossPosX -= 80;
        if (this.bossPosX <= j - 3500) {
          this.bossPosX = j - 3500;
          this.bossAnim = 0;
          this.bossFace = 1;
          this.bossFaceCount = 50;
          this.bossCount = 50;
          this.bossStep++;
        } 
        return;
      case 7:
        if (this.bossCount == 10)
          Boss4ShotTekkyu(1); 
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 8:
        this.bossDir = 1;
        this.bossAnim = 2;
        this.bossStep++;
      case 9:
        this.bossDir = 1;
        this.bossAnim = 2;
        this.bossPosX += 80;
        if (this.bossPosX < j + 3500)
          return; 
        this.bossPosX = j + 3500;
        this.bossAnim = 0;
        this.bossFace = 1;
        this.bossFaceCount = 50;
        this.bossCount = 50;
        this.bossStep++;
      case 10:
        if (this.bossCount == 10)
          Boss4ShotTekkyu(1); 
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 11:
        this.bossDir = 1;
        this.bossAnim = 2;
        this.bossPosX += 80;
        if (this.bossPosX < k + 3500)
          return; 
        this.bossPosX = k + 3500;
        this.bossAnim = 0;
        this.bossFace = 1;
        this.bossFaceCount = 50;
        this.bossCount = 50;
        this.bossStep++;
      case 12:
        if (this.bossCount == 10)
          Boss4ShotTekkyu(2); 
        if (this.bossCount > 0)
          return; 
        this.bossStep++;
      case 13:
        this.bossDir = 1;
        this.bossAnim = 2;
        this.bossPosX += 80;
        if (this.bossPosX >= 16000) {
          this.bossPosX = 16000;
          this.bossAnim = 0;
          this.bossFace = 1;
          this.bossFaceCount = 50;
          this.bossCount = 50;
          this.bossStep++;
          break;
        } 
        return;
      case 14:
        break;
    } 
    this.bossDir = 0;
    this.bossStep = 4;
  }
  
  private void PreInitBoss5() {
    this.bossOriginX = 11424;
    this.bossOriginY = 1232;
    for (byte b = 0; b < 10; b++) {
      boss5Block[b][0] = (short)(-160 + 32 * b + 16);
      boss5Block[b][1] = 176;
      boss5Block[b][2] = 0;
      boss5Block[b][3] = 0;
    } 
  }
  
  private void DestroyBoss5Block() {
    for (byte b = 0; b < 10; b++) {
      if (boss5Block[b][3] != 0) {
        ShotObj2(23, boss5Block[b][0], boss5Block[b][1], 330, 300, 0);
        ShotObj2(23, boss5Block[b][0], boss5Block[b][1], 30, 300, 1);
        ShotObj2(23, boss5Block[b][0], boss5Block[b][1], 300, 300, 2);
        ShotObj2(23, boss5Block[b][0], boss5Block[b][1], 60, 300, 3);
        boss5Block[b][2] = 1;
      } 
    } 
  }
  
  private void MoveBoss5Block(int paramInt) {
    if (objectData[14] == 0) {
      PreInitBoss5();
      objectData[14] = objectData[14] + 1;
    } 
    int i = PlayerPosX();
    int j = PlayerPosY();
    for (byte b = 0; b < 10; b++) {
      if (boss5Block[b][2] == 0) {
        int k;
        int m;
        if (boss5Block[b][3] == 0) {
          k = this.bossOriginX + boss5Block[b][0];
          m = this.bossOriginY + boss5Block[b][1];
        } else {
          k = this.bossOriginX + (this.bossPosX + this.bossOfsX) / 100;
          m = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100 + 24;
        } 
        ColliRect(k, m, 16, 16);
      } 
    } 
  }
  
  private void DrawBoss5Block(boolean paramBoolean) {
    for (byte b = 0; b < 10; b++) {
      int i;
      int j;
      if (boss5Block[b][2] != 0)
        continue; 
      if (boss5Block[b][3] == 0) {
        i = boss5Block[b][0];
        j = boss5Block[b][1];
      } else {
        i = (this.bossPosX + this.bossOfsX) / 100;
        j = (this.bossPosY + this.bossOfsY) / 100 + 24;
      } 
      if (paramBoolean) {
        short s = boss5Block[b][0];
        int k = (this.bossPosX + this.bossOfsX) / 100;
        if (Math.abs(s - k) > 32)
          continue; 
      } 
      drawRegion(gg, this.m_imgObj[102], 0, 0, 32, 32, 0, i + this.bossOriginX - mapView[0], j + this.bossOriginY - mapView[1], 0x1 | 0x2);
      continue;
    } 
  }
  
  private void boss5_move_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    int j = 0;
    boolean bool = false;
    _BossDefault(arrayOfInt);
    if (this.bossStep > 99) {
      if (_BossDead())
        arrayOfInt[0] = 0; 
      return;
    } 
    this.bossAngle2 += 200;
    this.bossOfsX = 0;
    this.bossOfsY = dSin(this.bossAngle2 / 100) * 12;
    int k = (PlayerPosX() - this.bossOriginX - 160) / 32;
    int i = (this.bossPosX + 16000) / 3200;
    j = this.bossOriginX - 160 + i * 32 + 16;
    int m = Math.abs(this.bossPosX / 100 + 160 - i * 32 + 16);
    if (k == i && m < 3 && boss5AttackCount == 0)
      bool = true; 
    if (boss5AttackCount > 0)
      boss5AttackCount--; 
    if (this.bossParam1 > 16 && IsHitSonic(arrayOfInt[2], arrayOfInt[3] + 8 - 32 + this.bossParam1, 14, 32, false) != 0)
      playdamageset(); 
    if (isHitBoss() && this.bossStep > 3 && this.bossFlash == 0) {
      boundBossHit(arrayOfInt[2], arrayOfInt[3], 10);
      if (this.bossFlash == 0 && this.bossStep < 100) {
        if (--this.bossHP <= 0) {
          DestroyBoss5Block();
          this.bossStep = 100;
          return;
        } 
        this.bossFace = 3;
        this.bossFlash = this.bossFaceCount = 60;
      } 
    } 
    switch (this.bossStep) {
      default:
        this.bossStep = 1;
      case 1:
        this.bossFace = 0;
        this.bossAnim = 2;
        this.bossPosX = 19200;
        this.bossPosY = 10000;
        this.bossAngle = 0;
        this.bossAngle2 = 0;
        this.bossCount = 50;
        boss5AttackCount = 90;
        bool = false;
        this.bossStep++;
      case 2:
        if (this.bossCount > 0)
          break; 
        this.bossStep++;
      case 3:
        this.bossAnim = 2;
        this.bossPosY -= 20;
        this.bossPosX -= 100;
        if (this.bossPosX > 6400)
          break; 
        this.bossPosX = 6400;
        this.bossAnim = 0;
        this.bossFace = 1;
        this.bossFaceCount = 60;
        this.bossCount = 60;
        this.bossStep++;
      case 4:
        if (bool) {
          this.bossStep = 10;
          break;
        } 
        this.bossAnim = 2;
        this.bossDir = 0;
        this.bossPosX -= 70;
        if (this.bossPosX > -12000)
          break; 
        this.bossPosX = -12000;
        this.bossStep++;
      case 5:
        if (bool) {
          this.bossStep = 10;
          break;
        } 
        this.bossAnim = 2;
        this.bossDir = 1;
        this.bossPosX += 70;
        if (this.bossPosX < 12000)
          break; 
        this.bossPosX = 12000;
        this.bossStep++;
      case 6:
        this.bossStep = 4;
        break;
      case 10:
        this.bossAnim = 0;
        this.bossFace = 1;
        this.bossStep++;
      case 11:
        this.bossPosY += 120;
        if (this.bossParam1 < 32)
          this.bossParam1++; 
        if (this.bossPosY < 15400)
          break; 
        this.bossPosY = 15400;
        this.bossCount = 48;
        boss5Block[i][3] = 1;
        this.bossStep++;
      case 12:
        this.bossOfsX = 0;
        this.bossOfsY = rnd(400) - 200;
        if (this.bossCount > 0)
          break; 
        this.bossOfsX = 0;
        this.bossOfsY = 0;
        this.bossStep++;
      case 13:
        this.bossPosY -= 300;
        if (this.bossPosY > 2400)
          break; 
        this.bossPosY = 2400;
        this.bossCount = 50;
        this.bossStep++;
      case 14:
        this.bossOfsX = 0;
        this.bossOfsY = rnd(400) - 200;
        if (this.bossParam1 > 0)
          this.bossParam1--; 
        if (this.bossCount > 0)
          break; 
        if (boss5Block[i][2] == 0) {
          this.bossOfsX = 0;
          this.bossOfsY = 0;
          ShotObj2(23, arrayOfInt[2], arrayOfInt[3] + 24, 330, 300, 0);
          ShotObj2(23, arrayOfInt[2], arrayOfInt[3] + 24, 30, 300, 1);
          ShotObj2(23, arrayOfInt[2], arrayOfInt[3] + 24, 300, 300, 2);
          ShotObj2(23, arrayOfInt[2], arrayOfInt[3] + 24, 60, 300, 3);
          boss5Block[i][2] = 1;
        } 
        this.bossCount = 30;
        this.bossStep++;
      case 15:
        if (this.bossCount > 0)
          break; 
        boss5AttackCount = 120;
        this.bossStep = (this.bossDir == 0) ? 4 : 5;
        break;
    } 
    UpdateBossPos(arrayOfInt);
    if (this.bossStep == 4 || this.bossStep == 5)
      if (Math.abs(arrayOfInt[2] - PlayerPosX()) < 64) {
        if (this.bossPosY > 1000)
          this.bossPosY -= 160; 
      } else if (this.bossPosY < 8000) {
        this.bossPosY += 140;
      }  
  }
  
  private void ColliRect2(int paramInt1, int[] paramArrayOfint, int paramInt2, int paramInt3) {
    int i = paramArrayOfint[0];
    int j = paramArrayOfint[1];
    int k = (paramArrayOfint[2] > -99999) ? paramArrayOfint[2] : paramArrayOfint[0];
    int m = (paramArrayOfint[3] > -99999) ? paramArrayOfint[3] : paramArrayOfint[1];
    int n = paramInt2 >> 1;
    int i1 = paramInt3 >> 1;
    int i2 = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, i, j, k, m, n, i1);
    if (i2 >= 0)
      if (i2 == 0) {
        PlayerParam[1] = j - i1 << 8;
        setRaidOnSize(i, n);
        playerRaidOn(paramInt1);
      } else if (i2 == 1) {
        PlayerParam[0] = i - n - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i2 == 2) {
        PlayerParam[0] = i + n + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i2 == 3) {
        PlayerParam[1] = j + i1 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == paramInt1 && i2 != 0)
      raidOn = false; 
    paramArrayOfint[2] = paramArrayOfint[0];
    paramArrayOfint[3] = paramArrayOfint[1];
  }
  
  private void boss6_move_arai(int paramInt) {
    // Byte code:
    //   0: getstatic MainCanvas.objectData : [I
    //   3: astore #4
    //   5: aload_0
    //   6: getfield bossStep : I
    //   9: sipush #200
    //   12: if_icmple -> 16
    //   15: return
    //   16: getstatic MainCanvas.PlayerParam : [I
    //   19: iconst_1
    //   20: iaload
    //   21: bipush #8
    //   23: ishr
    //   24: sipush #192
    //   27: if_icmple -> 38
    //   30: getstatic MainCanvas.PlayerParam : [I
    //   33: iconst_1
    //   34: ldc_w 49152
    //   37: iastore
    //   38: getstatic MainCanvas.PlayerParam : [I
    //   41: iconst_0
    //   42: iaload
    //   43: bipush #8
    //   45: ishr
    //   46: sipush #1952
    //   49: if_icmplt -> 73
    //   52: aload_0
    //   53: getfield bossAnim : I
    //   56: bipush #11
    //   58: if_icmpeq -> 65
    //   61: iconst_1
    //   62: putstatic MainCanvas.PlayerLookUp : Z
    //   65: getstatic MainCanvas.PlayerParam : [I
    //   68: iconst_0
    //   69: ldc_w 499968
    //   72: iastore
    //   73: aload_0
    //   74: aload #4
    //   76: invokespecial _BossDefault : ([I)Z
    //   79: pop
    //   80: aload_0
    //   81: getfield bossStep : I
    //   84: lookupswitch default -> 256, 1 -> 261, 2 -> 369, 3 -> 395, 4 -> 448, 5 -> 523, 6 -> 603, 7 -> 772, 50 -> 825, 51 -> 864, 52 -> 956, 100 -> 975, 101 -> 1053, 102 -> 1177, 103 -> 1308, 104 -> 1382, 105 -> 1450, 106 -> 1637, 107 -> 1703, 200 -> 1759, 201 -> 1797
    //   256: aload_0
    //   257: iconst_1
    //   258: putfield bossStep : I
    //   261: aload_0
    //   262: iconst_0
    //   263: putfield bossFace : I
    //   266: aload_0
    //   267: iconst_0
    //   268: putfield bossAnim : I
    //   271: aload_0
    //   272: iconst_0
    //   273: putfield bossPosX : I
    //   276: aload_0
    //   277: iconst_0
    //   278: putfield bossPosY : I
    //   281: aload_0
    //   282: iconst_0
    //   283: putfield bossOfsX : I
    //   286: aload_0
    //   287: iconst_0
    //   288: putfield bossOfsY : I
    //   291: iconst_0
    //   292: istore_2
    //   293: iload_2
    //   294: iconst_4
    //   295: if_icmpge -> 336
    //   298: getstatic MainCanvas.boss6Piston : [I
    //   301: iload_2
    //   302: iconst_0
    //   303: iastore
    //   304: getstatic MainCanvas.boss6PistonXY : [[I
    //   307: iload_2
    //   308: aaload
    //   309: iconst_2
    //   310: ldc_w -99999
    //   313: iastore
    //   314: getstatic MainCanvas.boss6PistonXY : [[I
    //   317: iload_2
    //   318: aaload
    //   319: iconst_3
    //   320: ldc_w -99999
    //   323: iastore
    //   324: getstatic MainCanvas.boss6TamaY : [I
    //   327: iload_2
    //   328: iconst_0
    //   329: iastore
    //   330: iinc #2, 1
    //   333: goto -> 293
    //   336: iconst_2
    //   337: putstatic MainCanvas.boss6PistonNum : I
    //   340: iconst_3
    //   341: putstatic MainCanvas.boss6RideNum : I
    //   344: iconst_1
    //   345: putstatic MainCanvas.boss6Lamp : I
    //   348: iconst_0
    //   349: putstatic MainCanvas.boss6Destroy : I
    //   352: aload_0
    //   353: iconst_0
    //   354: iconst_1
    //   355: iconst_0
    //   356: invokevirtual setWipe : (IZI)V
    //   359: aload_0
    //   360: dup
    //   361: getfield bossStep : I
    //   364: iconst_1
    //   365: iadd
    //   366: putfield bossStep : I
    //   369: aload_0
    //   370: invokevirtual PlayerPosX : ()I
    //   373: sipush #1328
    //   376: if_icmpge -> 382
    //   379: goto -> 1810
    //   382: aload_0
    //   383: dup
    //   384: getfield bossStep : I
    //   387: iconst_1
    //   388: iadd
    //   389: putfield bossStep : I
    //   392: goto -> 1810
    //   395: getstatic MainCanvas.boss6Piston : [I
    //   398: getstatic MainCanvas.boss6RideNum : I
    //   401: dup2
    //   402: iaload
    //   403: bipush #50
    //   405: iadd
    //   406: iastore
    //   407: getstatic MainCanvas.boss6Piston : [I
    //   410: getstatic MainCanvas.boss6PistonNum : I
    //   413: dup2
    //   414: iaload
    //   415: bipush #50
    //   417: iadd
    //   418: iastore
    //   419: getstatic MainCanvas.boss6Piston : [I
    //   422: getstatic MainCanvas.boss6RideNum : I
    //   425: iaload
    //   426: sipush #2000
    //   429: if_icmpge -> 435
    //   432: goto -> 1810
    //   435: aload_0
    //   436: dup
    //   437: getfield bossStep : I
    //   440: iconst_1
    //   441: iadd
    //   442: putfield bossStep : I
    //   445: goto -> 1810
    //   448: getstatic MainCanvas.boss6Piston : [I
    //   451: getstatic MainCanvas.boss6RideNum : I
    //   454: dup2
    //   455: iaload
    //   456: sipush #180
    //   459: iadd
    //   460: iastore
    //   461: getstatic MainCanvas.boss6Piston : [I
    //   464: getstatic MainCanvas.boss6PistonNum : I
    //   467: dup2
    //   468: iaload
    //   469: sipush #180
    //   472: iadd
    //   473: iastore
    //   474: getstatic MainCanvas.boss6Piston : [I
    //   477: getstatic MainCanvas.boss6RideNum : I
    //   480: iaload
    //   481: sipush #16000
    //   484: if_icmpge -> 490
    //   487: goto -> 1810
    //   490: getstatic MainCanvas.boss6Piston : [I
    //   493: getstatic MainCanvas.boss6RideNum : I
    //   496: sipush #16000
    //   499: iastore
    //   500: getstatic MainCanvas.boss6Piston : [I
    //   503: getstatic MainCanvas.boss6PistonNum : I
    //   506: sipush #16000
    //   509: iastore
    //   510: aload_0
    //   511: dup
    //   512: getfield bossStep : I
    //   515: iconst_1
    //   516: iadd
    //   517: putfield bossStep : I
    //   520: goto -> 1810
    //   523: getstatic MainCanvas.boss6Piston : [I
    //   526: getstatic MainCanvas.boss6RideNum : I
    //   529: dup2
    //   530: iaload
    //   531: sipush #180
    //   534: isub
    //   535: iastore
    //   536: getstatic MainCanvas.boss6Piston : [I
    //   539: getstatic MainCanvas.boss6PistonNum : I
    //   542: dup2
    //   543: iaload
    //   544: sipush #180
    //   547: isub
    //   548: iastore
    //   549: getstatic MainCanvas.boss6Piston : [I
    //   552: getstatic MainCanvas.boss6RideNum : I
    //   555: iaload
    //   556: ifle -> 562
    //   559: goto -> 1810
    //   562: getstatic MainCanvas.boss6Piston : [I
    //   565: getstatic MainCanvas.boss6RideNum : I
    //   568: iconst_0
    //   569: iastore
    //   570: getstatic MainCanvas.boss6Piston : [I
    //   573: getstatic MainCanvas.boss6PistonNum : I
    //   576: iconst_0
    //   577: iastore
    //   578: aload_0
    //   579: aload_0
    //   580: aload_0
    //   581: iconst_0
    //   582: dup_x1
    //   583: putfield bossFaceCount : I
    //   586: dup_x1
    //   587: putfield bossFace : I
    //   590: putfield bossFlash : I
    //   593: aload_0
    //   594: dup
    //   595: getfield bossStep : I
    //   598: iconst_1
    //   599: iadd
    //   600: putfield bossStep : I
    //   603: iconst_2
    //   604: putstatic MainCanvas.boss6Lamp : I
    //   607: aload_0
    //   608: bipush #27
    //   610: sipush #1415
    //   613: bipush #60
    //   615: iconst_0
    //   616: iconst_0
    //   617: iconst_0
    //   618: aload_0
    //   619: getfield bossOriginX : I
    //   622: bipush #64
    //   624: iadd
    //   625: bipush #32
    //   627: iadd
    //   628: aload_0
    //   629: bipush #8
    //   631: invokevirtual rnd : (I)I
    //   634: isub
    //   635: bipush #42
    //   637: iadd
    //   638: bipush #100
    //   640: imul
    //   641: invokespecial SetObj2 : (IIIIIII)V
    //   644: aload_0
    //   645: bipush #27
    //   647: sipush #1415
    //   650: bipush #60
    //   652: iconst_0
    //   653: iconst_0
    //   654: iconst_0
    //   655: aload_0
    //   656: getfield bossOriginX : I
    //   659: bipush #32
    //   661: iadd
    //   662: aload_0
    //   663: bipush #46
    //   665: invokevirtual rnd : (I)I
    //   668: iadd
    //   669: bipush #23
    //   671: isub
    //   672: bipush #42
    //   674: iadd
    //   675: bipush #100
    //   677: imul
    //   678: invokespecial SetObj2 : (IIIIIII)V
    //   681: aload_0
    //   682: bipush #27
    //   684: sipush #1415
    //   687: bipush #60
    //   689: iconst_0
    //   690: iconst_0
    //   691: iconst_0
    //   692: aload_0
    //   693: getfield bossOriginX : I
    //   696: bipush #32
    //   698: isub
    //   699: aload_0
    //   700: bipush #46
    //   702: invokevirtual rnd : (I)I
    //   705: iadd
    //   706: bipush #23
    //   708: isub
    //   709: bipush #42
    //   711: iadd
    //   712: bipush #100
    //   714: imul
    //   715: invokespecial SetObj2 : (IIIIIII)V
    //   718: aload_0
    //   719: bipush #27
    //   721: sipush #1415
    //   724: bipush #60
    //   726: iconst_0
    //   727: iconst_0
    //   728: iconst_0
    //   729: aload_0
    //   730: getfield bossOriginX : I
    //   733: bipush #64
    //   735: isub
    //   736: bipush #32
    //   738: isub
    //   739: aload_0
    //   740: bipush #24
    //   742: invokevirtual rnd : (I)I
    //   745: iadd
    //   746: bipush #42
    //   748: iadd
    //   749: bipush #100
    //   751: imul
    //   752: invokespecial SetObj2 : (IIIIIII)V
    //   755: aload_0
    //   756: sipush #300
    //   759: putfield bossCount : I
    //   762: aload_0
    //   763: dup
    //   764: getfield bossStep : I
    //   767: iconst_1
    //   768: iadd
    //   769: putfield bossStep : I
    //   772: aload_0
    //   773: getfield bossCount : I
    //   776: ifle -> 782
    //   779: goto -> 1810
    //   782: iconst_1
    //   783: aload_0
    //   784: iconst_3
    //   785: invokevirtual rnd : (I)I
    //   788: iadd
    //   789: putstatic MainCanvas.boss6RideNum : I
    //   792: getstatic MainCanvas.boss6RideNum : I
    //   795: iconst_1
    //   796: iadd
    //   797: aload_0
    //   798: iconst_3
    //   799: invokevirtual rnd : (I)I
    //   802: iadd
    //   803: iconst_4
    //   804: irem
    //   805: putstatic MainCanvas.boss6PistonNum : I
    //   808: iconst_1
    //   809: putstatic MainCanvas.boss6Lamp : I
    //   812: aload_0
    //   813: iconst_3
    //   814: putfield bossStep : I
    //   817: aload_0
    //   818: iconst_m1
    //   819: putfield bossAnim : I
    //   822: goto -> 1810
    //   825: iconst_2
    //   826: putstatic MainCanvas.boss6Lamp : I
    //   829: iconst_1
    //   830: putstatic MainCanvas.boss6Destroy : I
    //   833: aload_0
    //   834: iconst_2
    //   835: putfield bossFace : I
    //   838: aload_0
    //   839: aload_0
    //   840: sipush #999
    //   843: dup_x1
    //   844: putfield bossFaceCount : I
    //   847: putfield bossFlash : I
    //   850: iconst_1
    //   851: putstatic MainCanvas.TimerStop : Z
    //   854: aload_0
    //   855: dup
    //   856: getfield bossStep : I
    //   859: iconst_1
    //   860: iadd
    //   861: putfield bossStep : I
    //   864: getstatic MainCanvas.boss6Piston : [I
    //   867: getstatic MainCanvas.boss6RideNum : I
    //   870: dup2
    //   871: iaload
    //   872: bipush #50
    //   874: isub
    //   875: iastore
    //   876: getstatic MainCanvas.boss6Piston : [I
    //   879: getstatic MainCanvas.boss6PistonNum : I
    //   882: dup2
    //   883: iaload
    //   884: bipush #50
    //   886: isub
    //   887: iastore
    //   888: getstatic MainCanvas.boss6Piston : [I
    //   891: getstatic MainCanvas.boss6RideNum : I
    //   894: iaload
    //   895: ifle -> 901
    //   898: goto -> 1810
    //   901: getstatic MainCanvas.boss6Piston : [I
    //   904: getstatic MainCanvas.boss6RideNum : I
    //   907: iconst_0
    //   908: iastore
    //   909: getstatic MainCanvas.boss6Piston : [I
    //   912: getstatic MainCanvas.boss6PistonNum : I
    //   915: iconst_0
    //   916: iastore
    //   917: aload_0
    //   918: aload_0
    //   919: aload_0
    //   920: iconst_0
    //   921: dup_x1
    //   922: putfield bossFaceCount : I
    //   925: dup_x1
    //   926: putfield bossFace : I
    //   929: putfield bossFlash : I
    //   932: aload_0
    //   933: bipush #60
    //   935: putfield bossCount : I
    //   938: iconst_2
    //   939: putstatic MainCanvas.boss6Destroy : I
    //   942: iconst_3
    //   943: putstatic MainCanvas.boss6Lamp : I
    //   946: aload_0
    //   947: dup
    //   948: getfield bossStep : I
    //   951: iconst_1
    //   952: iadd
    //   953: putfield bossStep : I
    //   956: aload_0
    //   957: getfield bossCount : I
    //   960: ifle -> 966
    //   963: goto -> 1810
    //   966: aload_0
    //   967: bipush #100
    //   969: putfield bossStep : I
    //   972: goto -> 1810
    //   975: iconst_0
    //   976: putstatic MainCanvas.boss6Lamp : I
    //   979: iconst_0
    //   980: putstatic MainCanvas.boss6Destroy : I
    //   983: aload_0
    //   984: iconst_0
    //   985: putfield bossOfsX : I
    //   988: aload_0
    //   989: iconst_0
    //   990: putfield bossOfsY : I
    //   993: aload_0
    //   994: sipush #1472
    //   997: aload_0
    //   998: getfield bossOriginX : I
    //   1001: isub
    //   1002: bipush #100
    //   1004: imul
    //   1005: putfield bossPosX : I
    //   1008: aload_0
    //   1009: sipush #168
    //   1012: aload_0
    //   1013: getfield bossOriginY : I
    //   1016: isub
    //   1017: bipush #100
    //   1019: imul
    //   1020: putfield bossPosY : I
    //   1023: aload_0
    //   1024: aload #4
    //   1026: invokespecial UpdateBossPos : ([I)V
    //   1029: aload_0
    //   1030: bipush #7
    //   1032: putfield bossAnim : I
    //   1035: iconst_1
    //   1036: putstatic MainCanvas.TimerStop : Z
    //   1039: aload_0
    //   1040: invokespecial endBossMode : ()V
    //   1043: aload_0
    //   1044: dup
    //   1045: getfield bossStep : I
    //   1048: iconst_1
    //   1049: iadd
    //   1050: putfield bossStep : I
    //   1053: aload #4
    //   1055: iconst_2
    //   1056: iaload
    //   1057: aload_0
    //   1058: invokevirtual PlayerPosX : ()I
    //   1061: isub
    //   1062: sipush #150
    //   1065: if_icmpge -> 1079
    //   1068: aload_0
    //   1069: dup
    //   1070: getfield bossPosX : I
    //   1073: bipush #50
    //   1075: iadd
    //   1076: putfield bossPosX : I
    //   1079: aload #4
    //   1081: iconst_2
    //   1082: iaload
    //   1083: aload_0
    //   1084: invokevirtual PlayerPosX : ()I
    //   1087: isub
    //   1088: bipush #110
    //   1090: if_icmpge -> 1105
    //   1093: aload_0
    //   1094: dup
    //   1095: getfield bossPosX : I
    //   1098: sipush #400
    //   1101: iadd
    //   1102: putfield bossPosX : I
    //   1105: aload #4
    //   1107: iconst_2
    //   1108: iaload
    //   1109: aload_0
    //   1110: invokevirtual PlayerPosX : ()I
    //   1113: isub
    //   1114: bipush #70
    //   1116: if_icmpge -> 1131
    //   1119: aload_0
    //   1120: dup
    //   1121: getfield bossPosX : I
    //   1124: sipush #500
    //   1127: iadd
    //   1128: putfield bossPosX : I
    //   1131: aload #4
    //   1133: iconst_2
    //   1134: iaload
    //   1135: sipush #1696
    //   1138: if_icmpge -> 1144
    //   1141: goto -> 1810
    //   1144: aload #4
    //   1146: iconst_2
    //   1147: sipush #1696
    //   1150: iastore
    //   1151: aload #4
    //   1153: bipush #10
    //   1155: sipush #200
    //   1158: iastore
    //   1159: aload #4
    //   1161: bipush #11
    //   1163: sipush #-300
    //   1166: iastore
    //   1167: aload_0
    //   1168: dup
    //   1169: getfield bossStep : I
    //   1172: iconst_1
    //   1173: iadd
    //   1174: putfield bossStep : I
    //   1177: aload_0
    //   1178: dup
    //   1179: getfield bossPosX : I
    //   1182: aload #4
    //   1184: bipush #10
    //   1186: iaload
    //   1187: iadd
    //   1188: putfield bossPosX : I
    //   1191: aload_0
    //   1192: dup
    //   1193: getfield bossPosY : I
    //   1196: aload #4
    //   1198: bipush #11
    //   1200: iaload
    //   1201: iadd
    //   1202: putfield bossPosY : I
    //   1205: aload #4
    //   1207: bipush #11
    //   1209: dup2
    //   1210: iaload
    //   1211: bipush #12
    //   1213: iadd
    //   1214: dup_x2
    //   1215: iastore
    //   1216: sipush #600
    //   1219: if_icmple -> 1230
    //   1222: aload #4
    //   1224: bipush #11
    //   1226: sipush #600
    //   1229: iastore
    //   1230: aload #4
    //   1232: iconst_2
    //   1233: iaload
    //   1234: sipush #1760
    //   1237: if_icmpge -> 1243
    //   1240: goto -> 1810
    //   1243: aload_0
    //   1244: sipush #1760
    //   1247: aload_0
    //   1248: getfield bossOriginX : I
    //   1251: isub
    //   1252: bipush #100
    //   1254: imul
    //   1255: putfield bossPosX : I
    //   1258: aload_0
    //   1259: sipush #168
    //   1262: aload_0
    //   1263: getfield bossOriginY : I
    //   1266: isub
    //   1267: bipush #100
    //   1269: imul
    //   1270: putfield bossPosY : I
    //   1273: aload #4
    //   1275: bipush #10
    //   1277: iconst_0
    //   1278: iastore
    //   1279: aload #4
    //   1281: bipush #11
    //   1283: bipush #-50
    //   1285: iastore
    //   1286: aload_0
    //   1287: bipush #8
    //   1289: putfield bossAnim : I
    //   1292: aload_0
    //   1293: bipush #60
    //   1295: putfield bossCount : I
    //   1298: aload_0
    //   1299: dup
    //   1300: getfield bossStep : I
    //   1303: iconst_1
    //   1304: iadd
    //   1305: putfield bossStep : I
    //   1308: aload_0
    //   1309: dup
    //   1310: getfield bossPosX : I
    //   1313: aload #4
    //   1315: bipush #10
    //   1317: iaload
    //   1318: iadd
    //   1319: putfield bossPosX : I
    //   1322: aload_0
    //   1323: dup
    //   1324: getfield bossPosY : I
    //   1327: aload #4
    //   1329: bipush #11
    //   1331: iaload
    //   1332: iadd
    //   1333: putfield bossPosY : I
    //   1336: aload_0
    //   1337: getfield bossCount : I
    //   1340: ifle -> 1346
    //   1343: goto -> 1810
    //   1346: aload #4
    //   1348: bipush #10
    //   1350: bipush #25
    //   1352: iastore
    //   1353: aload #4
    //   1355: bipush #11
    //   1357: bipush #-100
    //   1359: iastore
    //   1360: aload_0
    //   1361: bipush #9
    //   1363: putfield bossAnim : I
    //   1366: aload_0
    //   1367: bipush #30
    //   1369: putfield bossCount : I
    //   1372: aload_0
    //   1373: dup
    //   1374: getfield bossStep : I
    //   1377: iconst_1
    //   1378: iadd
    //   1379: putfield bossStep : I
    //   1382: aload_0
    //   1383: dup
    //   1384: getfield bossPosX : I
    //   1387: aload #4
    //   1389: bipush #10
    //   1391: iaload
    //   1392: iadd
    //   1393: putfield bossPosX : I
    //   1396: aload_0
    //   1397: dup
    //   1398: getfield bossPosY : I
    //   1401: aload #4
    //   1403: bipush #11
    //   1405: iaload
    //   1406: iadd
    //   1407: putfield bossPosY : I
    //   1410: aload_0
    //   1411: getfield bossCount : I
    //   1414: ifle -> 1420
    //   1417: goto -> 1810
    //   1420: aload #4
    //   1422: bipush #10
    //   1424: sipush #200
    //   1427: iastore
    //   1428: aload #4
    //   1430: bipush #11
    //   1432: iconst_0
    //   1433: iastore
    //   1434: aload_0
    //   1435: bipush #10
    //   1437: putfield bossAnim : I
    //   1440: aload_0
    //   1441: dup
    //   1442: getfield bossStep : I
    //   1445: iconst_1
    //   1446: iadd
    //   1447: putfield bossStep : I
    //   1450: aload_0
    //   1451: dup
    //   1452: getfield bossPosX : I
    //   1455: aload #4
    //   1457: bipush #10
    //   1459: iaload
    //   1460: iadd
    //   1461: putfield bossPosX : I
    //   1464: aload_0
    //   1465: dup
    //   1466: getfield bossPosY : I
    //   1469: aload #4
    //   1471: bipush #11
    //   1473: iaload
    //   1474: iadd
    //   1475: putfield bossPosY : I
    //   1478: aload_0
    //   1479: aload #4
    //   1481: invokespecial UpdateBossPos : ([I)V
    //   1484: aload #4
    //   1486: iconst_2
    //   1487: iaload
    //   1488: sipush #2168
    //   1491: if_icmple -> 1509
    //   1494: aload_0
    //   1495: bipush #120
    //   1497: putfield bossCount : I
    //   1500: aload_0
    //   1501: bipush #107
    //   1503: putfield bossStep : I
    //   1506: goto -> 1810
    //   1509: aload_0
    //   1510: getfield bossPosX : I
    //   1513: aload_0
    //   1514: getfield bossOfsX : I
    //   1517: iadd
    //   1518: bipush #100
    //   1520: idiv
    //   1521: aload_0
    //   1522: getfield bossOriginX : I
    //   1525: iadd
    //   1526: aload_0
    //   1527: invokevirtual PlayerPosX : ()I
    //   1530: isub
    //   1531: istore #9
    //   1533: aload_0
    //   1534: getfield bossPosY : I
    //   1537: aload_0
    //   1538: getfield bossOfsY : I
    //   1541: iadd
    //   1542: bipush #100
    //   1544: idiv
    //   1545: aload_0
    //   1546: getfield bossOriginY : I
    //   1549: iadd
    //   1550: aload_0
    //   1551: invokevirtual PlayerPosY : ()I
    //   1554: bipush #16
    //   1556: isub
    //   1557: isub
    //   1558: istore #10
    //   1560: iload #9
    //   1562: iload #9
    //   1564: imul
    //   1565: iload #10
    //   1567: iload #10
    //   1569: imul
    //   1570: iadd
    //   1571: istore #11
    //   1573: iload #11
    //   1575: sipush #1444
    //   1578: if_icmpge -> 1810
    //   1581: getstatic MainCanvas.PlayerBall : Z
    //   1584: ifne -> 1590
    //   1587: goto -> 1810
    //   1590: aload_0
    //   1591: aload #4
    //   1593: iconst_2
    //   1594: iaload
    //   1595: aload #4
    //   1597: iconst_3
    //   1598: iaload
    //   1599: bipush #6
    //   1601: invokespecial boundBossHit : (III)V
    //   1604: aload #4
    //   1606: bipush #10
    //   1608: bipush #100
    //   1610: iastore
    //   1611: aload #4
    //   1613: bipush #11
    //   1615: bipush #40
    //   1617: iastore
    //   1618: aload_0
    //   1619: bipush #11
    //   1621: putfield bossAnim : I
    //   1624: aload_0
    //   1625: dup
    //   1626: getfield bossStep : I
    //   1629: iconst_1
    //   1630: iadd
    //   1631: putfield bossStep : I
    //   1634: goto -> 1810
    //   1637: aload_0
    //   1638: dup
    //   1639: getfield bossPosX : I
    //   1642: aload #4
    //   1644: bipush #10
    //   1646: iaload
    //   1647: iadd
    //   1648: putfield bossPosX : I
    //   1651: aload_0
    //   1652: dup
    //   1653: getfield bossPosY : I
    //   1656: aload #4
    //   1658: bipush #11
    //   1660: iaload
    //   1661: iadd
    //   1662: putfield bossPosY : I
    //   1665: aload_0
    //   1666: aload #4
    //   1668: invokespecial UpdateBossPos : ([I)V
    //   1671: aload #4
    //   1673: iconst_2
    //   1674: iaload
    //   1675: sipush #2168
    //   1678: if_icmpge -> 1684
    //   1681: goto -> 1810
    //   1684: aload_0
    //   1685: bipush #120
    //   1687: putfield bossCount : I
    //   1690: aload_0
    //   1691: dup
    //   1692: getfield bossStep : I
    //   1695: iconst_1
    //   1696: iadd
    //   1697: putfield bossStep : I
    //   1700: goto -> 1810
    //   1703: aload_0
    //   1704: bipush #120
    //   1706: putfield playerStandCount : I
    //   1709: aload_0
    //   1710: iconst_1
    //   1711: invokevirtual doWipe : (Z)Z
    //   1714: ifeq -> 1717
    //   1717: aload_0
    //   1718: getfield bossCount : I
    //   1721: bipush #10
    //   1723: if_icmpne -> 1731
    //   1726: aload_0
    //   1727: iconst_1
    //   1728: putfield putNowLoading : Z
    //   1731: aload_0
    //   1732: getfield bossCount : I
    //   1735: ifle -> 1741
    //   1738: goto -> 1810
    //   1741: aload_0
    //   1742: iconst_0
    //   1743: iconst_1
    //   1744: bipush #8
    //   1746: invokevirtual setWipe : (IZI)V
    //   1749: aload_0
    //   1750: sipush #200
    //   1753: putfield bossStep : I
    //   1756: goto -> 1810
    //   1759: aload_0
    //   1760: iconst_0
    //   1761: putfield playerStandCount : I
    //   1764: aload_0
    //   1765: invokevirtual setEnding : ()V
    //   1768: aload_0
    //   1769: iconst_0
    //   1770: iconst_1
    //   1771: bipush #8
    //   1773: invokevirtual setWipe : (IZI)V
    //   1776: aload_0
    //   1777: iconst_1
    //   1778: putfield putNowLoading : Z
    //   1781: aload #4
    //   1783: iconst_0
    //   1784: iconst_0
    //   1785: iastore
    //   1786: aload_0
    //   1787: dup
    //   1788: getfield bossStep : I
    //   1791: iconst_1
    //   1792: iadd
    //   1793: putfield bossStep : I
    //   1796: return
    //   1797: aload_0
    //   1798: iconst_1
    //   1799: putfield putNowLoading : Z
    //   1802: aload_0
    //   1803: iconst_0
    //   1804: iconst_1
    //   1805: bipush #8
    //   1807: invokevirtual setWipe : (IZI)V
    //   1810: aload_0
    //   1811: bipush #48
    //   1813: sipush #1216
    //   1816: bipush #16
    //   1818: iconst_0
    //   1819: iconst_0
    //   1820: invokespecial AddObjectData : (IIIII)V
    //   1823: aload_0
    //   1824: bipush #48
    //   1826: sipush #1248
    //   1829: bipush #16
    //   1831: iconst_0
    //   1832: iconst_0
    //   1833: invokespecial AddObjectData : (IIIII)V
    //   1836: aload_0
    //   1837: bipush #48
    //   1839: sipush #1344
    //   1842: bipush #16
    //   1844: iconst_0
    //   1845: iconst_0
    //   1846: invokespecial AddObjectData : (IIIII)V
    //   1849: aload_0
    //   1850: bipush #48
    //   1852: sipush #1376
    //   1855: bipush #16
    //   1857: iconst_0
    //   1858: iconst_0
    //   1859: invokespecial AddObjectData : (IIIII)V
    //   1862: aload_0
    //   1863: getfield bossStep : I
    //   1866: bipush #100
    //   1868: if_icmpge -> 2078
    //   1871: getstatic MainCanvas.boss6RideNum : I
    //   1874: tableswitch default -> 1904, 0 -> 1917, 1 -> 1958, 2 -> 1999, 3 -> 2040
    //   1904: aload_0
    //   1905: aload_0
    //   1906: iconst_0
    //   1907: dup_x1
    //   1908: putfield bossPosY : I
    //   1911: putfield bossPosX : I
    //   1914: goto -> 2078
    //   1917: aload_0
    //   1918: getstatic MainCanvas.boss6PistonPos : [[S
    //   1921: iconst_0
    //   1922: aaload
    //   1923: iconst_0
    //   1924: saload
    //   1925: bipush #100
    //   1927: imul
    //   1928: putfield bossPosX : I
    //   1931: aload_0
    //   1932: getstatic MainCanvas.boss6PistonPos : [[S
    //   1935: iconst_0
    //   1936: aaload
    //   1937: iconst_1
    //   1938: saload
    //   1939: bipush #100
    //   1941: imul
    //   1942: getstatic MainCanvas.boss6Piston : [I
    //   1945: iconst_0
    //   1946: iaload
    //   1947: iadd
    //   1948: sipush #-800
    //   1951: iadd
    //   1952: putfield bossPosY : I
    //   1955: goto -> 2078
    //   1958: aload_0
    //   1959: getstatic MainCanvas.boss6PistonPos : [[S
    //   1962: iconst_1
    //   1963: aaload
    //   1964: iconst_0
    //   1965: saload
    //   1966: bipush #100
    //   1968: imul
    //   1969: putfield bossPosX : I
    //   1972: aload_0
    //   1973: getstatic MainCanvas.boss6PistonPos : [[S
    //   1976: iconst_1
    //   1977: aaload
    //   1978: iconst_1
    //   1979: saload
    //   1980: bipush #100
    //   1982: imul
    //   1983: getstatic MainCanvas.boss6Piston : [I
    //   1986: iconst_1
    //   1987: iaload
    //   1988: iadd
    //   1989: sipush #-800
    //   1992: iadd
    //   1993: putfield bossPosY : I
    //   1996: goto -> 2078
    //   1999: aload_0
    //   2000: getstatic MainCanvas.boss6PistonPos : [[S
    //   2003: iconst_2
    //   2004: aaload
    //   2005: iconst_0
    //   2006: saload
    //   2007: bipush #100
    //   2009: imul
    //   2010: putfield bossPosX : I
    //   2013: aload_0
    //   2014: getstatic MainCanvas.boss6PistonPos : [[S
    //   2017: iconst_2
    //   2018: aaload
    //   2019: iconst_1
    //   2020: saload
    //   2021: bipush #100
    //   2023: imul
    //   2024: getstatic MainCanvas.boss6Piston : [I
    //   2027: iconst_2
    //   2028: iaload
    //   2029: isub
    //   2030: sipush #1600
    //   2033: iadd
    //   2034: putfield bossPosY : I
    //   2037: goto -> 2078
    //   2040: aload_0
    //   2041: getstatic MainCanvas.boss6PistonPos : [[S
    //   2044: iconst_3
    //   2045: aaload
    //   2046: iconst_0
    //   2047: saload
    //   2048: bipush #100
    //   2050: imul
    //   2051: putfield bossPosX : I
    //   2054: aload_0
    //   2055: getstatic MainCanvas.boss6PistonPos : [[S
    //   2058: iconst_3
    //   2059: aaload
    //   2060: iconst_1
    //   2061: saload
    //   2062: bipush #100
    //   2064: imul
    //   2065: getstatic MainCanvas.boss6Piston : [I
    //   2068: iconst_3
    //   2069: iaload
    //   2070: isub
    //   2071: sipush #1600
    //   2074: iadd
    //   2075: putfield bossPosY : I
    //   2078: getstatic MainCanvas.boss6PistonXY : [[I
    //   2081: iconst_0
    //   2082: aaload
    //   2083: iconst_0
    //   2084: getstatic MainCanvas.boss6PistonPos : [[S
    //   2087: iconst_0
    //   2088: aaload
    //   2089: iconst_0
    //   2090: saload
    //   2091: aload_0
    //   2092: getfield bossOriginX : I
    //   2095: iadd
    //   2096: iastore
    //   2097: getstatic MainCanvas.boss6PistonXY : [[I
    //   2100: iconst_0
    //   2101: aaload
    //   2102: iconst_1
    //   2103: getstatic MainCanvas.boss6PistonPos : [[S
    //   2106: iconst_0
    //   2107: aaload
    //   2108: iconst_1
    //   2109: saload
    //   2110: aload_0
    //   2111: getfield bossOriginY : I
    //   2114: iadd
    //   2115: bipush #8
    //   2117: isub
    //   2118: getstatic MainCanvas.boss6Piston : [I
    //   2121: iconst_0
    //   2122: iaload
    //   2123: bipush #100
    //   2125: idiv
    //   2126: iadd
    //   2127: iastore
    //   2128: aload_0
    //   2129: ldc_w 46592
    //   2132: getstatic MainCanvas.boss6PistonXY : [[I
    //   2135: iconst_0
    //   2136: aaload
    //   2137: bipush #64
    //   2139: sipush #160
    //   2142: invokespecial ColliRect2 : (I[III)V
    //   2145: getstatic MainCanvas.boss6PistonXY : [[I
    //   2148: iconst_1
    //   2149: aaload
    //   2150: iconst_0
    //   2151: getstatic MainCanvas.boss6PistonPos : [[S
    //   2154: iconst_1
    //   2155: aaload
    //   2156: iconst_0
    //   2157: saload
    //   2158: aload_0
    //   2159: getfield bossOriginX : I
    //   2162: iadd
    //   2163: iastore
    //   2164: getstatic MainCanvas.boss6PistonXY : [[I
    //   2167: iconst_1
    //   2168: aaload
    //   2169: iconst_1
    //   2170: getstatic MainCanvas.boss6PistonPos : [[S
    //   2173: iconst_1
    //   2174: aaload
    //   2175: iconst_1
    //   2176: saload
    //   2177: aload_0
    //   2178: getfield bossOriginY : I
    //   2181: iadd
    //   2182: bipush #8
    //   2184: isub
    //   2185: getstatic MainCanvas.boss6Piston : [I
    //   2188: iconst_1
    //   2189: iaload
    //   2190: bipush #100
    //   2192: idiv
    //   2193: iadd
    //   2194: iastore
    //   2195: aload_0
    //   2196: ldc_w 46593
    //   2199: getstatic MainCanvas.boss6PistonXY : [[I
    //   2202: iconst_1
    //   2203: aaload
    //   2204: bipush #64
    //   2206: sipush #160
    //   2209: invokespecial ColliRect2 : (I[III)V
    //   2212: getstatic MainCanvas.boss6PistonXY : [[I
    //   2215: iconst_2
    //   2216: aaload
    //   2217: iconst_0
    //   2218: getstatic MainCanvas.boss6PistonPos : [[S
    //   2221: iconst_2
    //   2222: aaload
    //   2223: iconst_0
    //   2224: saload
    //   2225: aload_0
    //   2226: getfield bossOriginX : I
    //   2229: iadd
    //   2230: iastore
    //   2231: getstatic MainCanvas.boss6PistonXY : [[I
    //   2234: iconst_2
    //   2235: aaload
    //   2236: iconst_1
    //   2237: getstatic MainCanvas.boss6PistonPos : [[S
    //   2240: iconst_2
    //   2241: aaload
    //   2242: iconst_1
    //   2243: saload
    //   2244: aload_0
    //   2245: getfield bossOriginY : I
    //   2248: iadd
    //   2249: bipush #8
    //   2251: iadd
    //   2252: getstatic MainCanvas.boss6Piston : [I
    //   2255: iconst_2
    //   2256: iaload
    //   2257: bipush #100
    //   2259: idiv
    //   2260: isub
    //   2261: iastore
    //   2262: aload_0
    //   2263: ldc_w 46594
    //   2266: getstatic MainCanvas.boss6PistonXY : [[I
    //   2269: iconst_2
    //   2270: aaload
    //   2271: bipush #64
    //   2273: sipush #160
    //   2276: invokespecial ColliRect2 : (I[III)V
    //   2279: getstatic MainCanvas.boss6PistonXY : [[I
    //   2282: iconst_3
    //   2283: aaload
    //   2284: iconst_0
    //   2285: getstatic MainCanvas.boss6PistonPos : [[S
    //   2288: iconst_3
    //   2289: aaload
    //   2290: iconst_0
    //   2291: saload
    //   2292: aload_0
    //   2293: getfield bossOriginX : I
    //   2296: iadd
    //   2297: iastore
    //   2298: getstatic MainCanvas.boss6PistonXY : [[I
    //   2301: iconst_3
    //   2302: aaload
    //   2303: iconst_1
    //   2304: getstatic MainCanvas.boss6PistonPos : [[S
    //   2307: iconst_3
    //   2308: aaload
    //   2309: iconst_1
    //   2310: saload
    //   2311: aload_0
    //   2312: getfield bossOriginY : I
    //   2315: iadd
    //   2316: bipush #8
    //   2318: iadd
    //   2319: getstatic MainCanvas.boss6Piston : [I
    //   2322: iconst_3
    //   2323: iaload
    //   2324: bipush #100
    //   2326: idiv
    //   2327: isub
    //   2328: iastore
    //   2329: aload_0
    //   2330: ldc_w 46595
    //   2333: getstatic MainCanvas.boss6PistonXY : [[I
    //   2336: iconst_3
    //   2337: aaload
    //   2338: bipush #64
    //   2340: sipush #160
    //   2343: invokespecial ColliRect2 : (I[III)V
    //   2346: getstatic MainCanvas.boss6Destroy : I
    //   2349: iconst_1
    //   2350: if_icmpne -> 2572
    //   2353: aload_0
    //   2354: getfield animeTimer : I
    //   2357: iconst_3
    //   2358: iand
    //   2359: ifne -> 2459
    //   2362: getstatic MainCanvas.boss6PistonPos : [[S
    //   2365: getstatic MainCanvas.boss6RideNum : I
    //   2368: aaload
    //   2369: iconst_0
    //   2370: saload
    //   2371: aload_0
    //   2372: getfield bossOriginX : I
    //   2375: iadd
    //   2376: istore #9
    //   2378: getstatic MainCanvas.boss6PistonPos : [[S
    //   2381: getstatic MainCanvas.boss6RideNum : I
    //   2384: aaload
    //   2385: iconst_1
    //   2386: saload
    //   2387: aload_0
    //   2388: getfield bossOriginY : I
    //   2391: iadd
    //   2392: istore #10
    //   2394: getstatic MainCanvas.boss6Piston : [I
    //   2397: getstatic MainCanvas.boss6RideNum : I
    //   2400: iaload
    //   2401: bipush #100
    //   2403: idiv
    //   2404: istore #11
    //   2406: getstatic MainCanvas.boss6RideNum : I
    //   2409: iconst_2
    //   2410: iand
    //   2411: ifeq -> 2419
    //   2414: iload #11
    //   2416: ineg
    //   2417: istore #11
    //   2419: aload_0
    //   2420: iconst_1
    //   2421: iload #9
    //   2423: aload_0
    //   2424: bipush #64
    //   2426: invokevirtual rnd : (I)I
    //   2429: iadd
    //   2430: bipush #32
    //   2432: isub
    //   2433: iload #10
    //   2435: aload_0
    //   2436: sipush #128
    //   2439: invokevirtual rnd : (I)I
    //   2442: iadd
    //   2443: bipush #64
    //   2445: isub
    //   2446: iload #11
    //   2448: iadd
    //   2449: iconst_0
    //   2450: iconst_0
    //   2451: iconst_0
    //   2452: iconst_0
    //   2453: invokespecial SetObj2 : (IIIIIII)V
    //   2456: goto -> 2623
    //   2459: aload_0
    //   2460: getfield animeTimer : I
    //   2463: iconst_3
    //   2464: iand
    //   2465: iconst_2
    //   2466: if_icmpne -> 2623
    //   2469: getstatic MainCanvas.boss6PistonNum : I
    //   2472: ifeq -> 2623
    //   2475: getstatic MainCanvas.boss6PistonPos : [[S
    //   2478: getstatic MainCanvas.boss6PistonNum : I
    //   2481: aaload
    //   2482: iconst_0
    //   2483: saload
    //   2484: aload_0
    //   2485: getfield bossOriginX : I
    //   2488: iadd
    //   2489: istore #9
    //   2491: getstatic MainCanvas.boss6PistonPos : [[S
    //   2494: getstatic MainCanvas.boss6PistonNum : I
    //   2497: aaload
    //   2498: iconst_1
    //   2499: saload
    //   2500: aload_0
    //   2501: getfield bossOriginY : I
    //   2504: iadd
    //   2505: istore #10
    //   2507: getstatic MainCanvas.boss6Piston : [I
    //   2510: getstatic MainCanvas.boss6PistonNum : I
    //   2513: iaload
    //   2514: bipush #100
    //   2516: idiv
    //   2517: istore #11
    //   2519: getstatic MainCanvas.boss6PistonNum : I
    //   2522: iconst_2
    //   2523: iand
    //   2524: ifeq -> 2532
    //   2527: iload #11
    //   2529: ineg
    //   2530: istore #11
    //   2532: aload_0
    //   2533: iconst_1
    //   2534: iload #9
    //   2536: aload_0
    //   2537: bipush #64
    //   2539: invokevirtual rnd : (I)I
    //   2542: iadd
    //   2543: bipush #32
    //   2545: isub
    //   2546: iload #10
    //   2548: aload_0
    //   2549: sipush #128
    //   2552: invokevirtual rnd : (I)I
    //   2555: iadd
    //   2556: bipush #64
    //   2558: isub
    //   2559: iload #11
    //   2561: iadd
    //   2562: iconst_0
    //   2563: iconst_0
    //   2564: iconst_0
    //   2565: iconst_0
    //   2566: invokespecial SetObj2 : (IIIIIII)V
    //   2569: goto -> 2623
    //   2572: getstatic MainCanvas.boss6Destroy : I
    //   2575: iconst_2
    //   2576: if_icmpne -> 2623
    //   2579: aload_0
    //   2580: getfield animeTimer : I
    //   2583: bipush #7
    //   2585: iand
    //   2586: ifne -> 2623
    //   2589: aload_0
    //   2590: iconst_1
    //   2591: sipush #1415
    //   2594: aload_0
    //   2595: bipush #16
    //   2597: invokevirtual rnd : (I)I
    //   2600: iadd
    //   2601: bipush #8
    //   2603: isub
    //   2604: bipush #60
    //   2606: aload_0
    //   2607: bipush #16
    //   2609: invokevirtual rnd : (I)I
    //   2612: iadd
    //   2613: bipush #8
    //   2615: isub
    //   2616: iconst_0
    //   2617: iconst_0
    //   2618: iconst_0
    //   2619: iconst_0
    //   2620: invokespecial SetObj2 : (IIIIIII)V
    //   2623: aload_0
    //   2624: invokespecial isHitBoss6 : ()Z
    //   2627: ifeq -> 2714
    //   2630: aload_0
    //   2631: getfield bossStep : I
    //   2634: bipush #50
    //   2636: if_icmpge -> 2714
    //   2639: getstatic MainCanvas.PlayerParam : [I
    //   2642: iconst_3
    //   2643: aload #4
    //   2645: iconst_2
    //   2646: iaload
    //   2647: aload_0
    //   2648: invokevirtual PlayerPosX : ()I
    //   2651: if_icmpge -> 2660
    //   2654: sipush #768
    //   2657: goto -> 2663
    //   2660: sipush #-768
    //   2663: iastore
    //   2664: getstatic MainCanvas.PlayerParam : [I
    //   2667: iconst_5
    //   2668: sipush #-1536
    //   2671: iastore
    //   2672: aload_0
    //   2673: dup
    //   2674: getfield bossHP : I
    //   2677: iconst_1
    //   2678: isub
    //   2679: dup_x1
    //   2680: putfield bossHP : I
    //   2683: ifgt -> 2693
    //   2686: aload_0
    //   2687: bipush #50
    //   2689: putfield bossStep : I
    //   2692: return
    //   2693: aload_0
    //   2694: iconst_1
    //   2695: putfield bossFace : I
    //   2698: aload_0
    //   2699: aload_0
    //   2700: bipush #120
    //   2702: dup_x1
    //   2703: putfield bossFaceCount : I
    //   2706: putfield bossFlash : I
    //   2709: aload_0
    //   2710: iconst_0
    //   2711: putfield bossStopCount : I
    //   2714: return
  }
  
  private boolean moveNaka() {
    if (nakaCount > 0)
      nakaCount--; 
    switch (nakaStep) {
      default:
        nakaStep = 1;
      case 1:
        nakaLevel = 0;
        nakaStep++;
        return false;
      case 2:
        if ((nakaLevel += 4) >= 255) {
          nakaLevel = 255;
          nakaCount = 180;
          nakaStep++;
        } 
        return false;
      case 3:
        if (nakaCount <= 0)
          nakaStep++; 
        return false;
      case 4:
        if ((nakaLevel -= 4) <= 0) {
          nakaLevel = 0;
          nakaStep++;
        } 
        return false;
      case 5:
        break;
    } 
    return true;
  }
  
  private void drawNaka() {
    int i = nakaLevel;
    Font font = Font.getFont(0, 0, 8);
    i &= 0xFF;
    gg.setColor(0);
    gg.fillRect(0, 0, 240, 240);
    gg.setFont(font);
    int j = i << 16 | i << 8 | i;
    int k = i;
    byte b1 = 116;
    byte b2 = 22;
    drawRegion(gg, this.m_imgObj[124], 0, 0, 54, 15, 0, 206, 152, 0x1 | 0x2);
    TK_DrawStringC(softKeys[25], 120, 96, j, k);
    TK_DrawStringC(softKeys[26], 120, 121, j, k);
  }
  
  private void drawEndingEggmanB() {
    int i = endingEggAnim % 3;
    gg.setColor(0);
    gg.fillRect(0, 0, 240, 240);
    short s1 = RectTblEndingB[i][0];
    short s2 = RectTblEndingB[i][1];
    short s3 = RectTblEndingB[i][2];
    short s4 = RectTblEndingB[i][3];
    drawRegion(gg, this.m_imgObj[123], s1, s2, s3, s4, 0, 120, 90, 0x1 | 0x2);
  }
  
  private boolean moveEndingEggmanB() {
    if (endingEggCount > 0)
      endingEggCount--; 
    endingEggAnim = (this.animeTimer >> 1) % 3;
    switch (endingEggStep) {
      default:
        endingEggStep = 1;
      case 1:
        setWipe(0, true, 0);
        endingEggCount = 300;
        endingEggStep++;
      case 2:
        if (endingEggCount <= 0)
          endingEggStep++; 
        return false;
      case 3:
        break;
    } 
    return true;
  }
  
  void setWipe(int paramInt1, boolean paramBoolean, int paramInt2) {
    wipeCol = paramInt1;
    wipeDir = paramBoolean;
    wipeLevel = paramInt2;
  }
  
  boolean doWipe(boolean paramBoolean) {
    if (paramBoolean) {
      if ((this.animeTimer & 0x3) == 0 && wipeLevel < 8)
        wipeLevel++; 
      return (wipeLevel >= 8);
    } 
    if ((this.animeTimer & 0x3) == 0 && wipeLevel > 0)
      wipeLevel--; 
    return (wipeLevel == 0);
  }
  
  void drawWipe() {
    if (wipeLevel > 0) {
      gg.setColor(0);
      if (wipeDir) {
        for (byte b = 0; b < 'ð'; b += 8)
          gg.fillRect(0, b, 240, wipeLevel); 
      } else {
        for (byte b = 0; b < 'ð'; b += 8)
          gg.fillRect(0, b + 7 - wipeLevel, 240, wipeLevel); 
      } 
    } 
  }
  
  private void moveEnding() {
    ringcount = 0;
    timecount = 0;
    timecount2 = 0;
    if (this.endingCount > 0)
      this.endingCount--; 
    if (this.endingStep >= 4 && rnd(15) == 0)
      if (rnd(2) == 0) {
        SetObj2(28 + rnd(7), -20, PlayerPosY() - 30 + rnd(120), 0, 300, 1, 0);
      } else {
        SetObj2(28 + rnd(7), 260, PlayerPosY() - 30 + rnd(120), 0, 300, 0, 0);
      }  
    switch (this.endingStep) {
      default:
        this.endingStep = 1;
      case 1:
        try {
          for (byte b = 0; b < ''; b++)
            this.m_imgObj[b] = null; 
          DoGc();
          this.m_imgObj[100] = createImage("/animal.png");
          this.m_imgObj[121] = createImage("/ED_00.png");
          this.m_imgObj[122] = createImage("/ED3.png");
          this.m_imgObj[123] = createImage("/endegg_b.png");
          this.m_imgObj[124] = createImage("/t_license3.png");
        } catch (Throwable throwable) {}
        countClear();
        this.endingCount = 180;
        this.endingLogoPosX = -102;
        this.endingAnim = -1;
        this.endingType = 0;
        this.endingStringFadeLevel = 0;
        setWipe(0, true, 8);
        TimerClear = true;
        TimerStop = true;
        this.putNowLoading = false;
        bossModeOn = false;
        bossBreakOn = false;
        PlayMusic(16);
        this.endingStep++;
        return;
      case 2:
        doWipe(false);
        PlayerParam[10] = -2048;
        PlayerParam[12] = 1;
        playerRaidOn(-1);
        if (rnd(12) == 0)
          switch (rnd(3)) {
            default:
              SetObj2(28, PlayerPosX() - 300, PlayerPosY() - 30 + rnd(120), 0, 300, 0, 0);
              break;
            case 1:
              SetObj2(31, PlayerPosX() - 300, PlayerPosY() - 30 + rnd(120), 0, 300, 0, 0);
              break;
            case 2:
              SetObj2(33, PlayerPosX() - 300, PlayerPosY() - 30 + rnd(120), 0, 300, 0, 0);
              break;
          }  
        if (PlayerPosX() <= 64)
          this.endingStep++; 
        return;
      case 3:
        if (PlayerParam[10] < 768) {
          PlayerParam[10] = PlayerParam[10] + 80;
        } else {
          PlayerParam[10] = 768;
        } 
        PlayerParam[12] = 0;
        if (PlayerPosX() >= 160) {
          PlayerParam[0] = 40960;
          PlayerParam[10] = 0;
          this.endingCount = 265;
          this.endingStep++;
        } 
        return;
      case 4:
        this.playerStandCount = 120;
        if (this.endingCount > 0)
          return; 
        this.endingStep++;
      case 5:
        this.endingAnim = 0;
        this.endingLogoPosX += 8;
        if (this.endingLogoPosX >= 46) {
          this.endingCount = 48;
          this.endingStep++;
        } 
        return;
      case 6:
        muteki2count = 1;
        this.endingAnim = 1;
        if (this.endingCount > 0)
          return; 
        this.endingCount = 10;
        this.endingStep++;
      case 7:
        muteki2count = 1;
        this.endingAnim = 2;
        if (this.endingCount > 0)
          return; 
        this.endingCount = 10;
        this.endingStep++;
      case 8:
        muteki2count = 1;
        this.endingAnim = 3;
        if (this.endingCount > 0)
          return; 
        this.endingCount = 300;
        this.endingStep++;
      case 9:
        muteki2count = 1;
        if (this.endingCount > 0)
          return; 
        this.endingCount = 34;
        this.endingStep++;
      case 10:
        muteki2count = 1;
        doWipe(true);
        if (this.endingCount <= 0) {
          this.endingType = 1;
          endingEggStep = 0;
          this.endingStep++;
        } 
        return;
      case 11:
        muteki2count = 1;
        if (!moveEndingEggmanB())
          return; 
        this.endingType = 2;
        nakaStep = 0;
        this.endingStep++;
      case 12:
        muteki2count = 1;
        if (moveNaka())
          this.endingStep = 100; 
        return;
      case 100:
        break;
    } 
    muteki2count = 1;
    this.endingModeOn = false;
    mode = MODE_TITLE;
    ObjImageClear();
    TK_TitleInit(false);
  }
  
  private void drawEnding() {
    _drawEnding();
    drawWipe();
  }
  
  private void _drawEnding() {
    if (this.endingStep < 2)
      return; 
    if (this.endingType == 1) {
      drawEndingEggmanB();
      return;
    } 
    if (this.endingType == 2) {
      drawNaka();
      return;
    } 
    if (this.endingAnim < 0)
      return; 
    drawRegion(gg, this.m_imgObj[121], 0, 0, 96, 32, 0, this.endingLogoPosX, 110, 0x1 | 0x2);
    switch (this.endingAnim) {
      default:
        return;
      case 1:
        drawRegion(gg, this.m_imgObj[122], 0, 0, 32, 40, 0, 120, 96, 0x1 | 0x2);
      case 2:
        drawRegion(gg, this.m_imgObj[122], 0, 40, 48, 72, 0, 120, 96, 0x1 | 0x2);
      case 3:
        break;
    } 
    drawRegion(gg, this.m_imgObj[122], 48, 0, 176, 136, 0, 162, 96, 0x1 | 0x2);
  }
  
  private void boss1_draw_arai(int paramInt) {
    AraiDrawBoss(objectData);
  }
  
  private void boss2_draw_arai(int paramInt) {
    AraiDrawBoss(objectData);
  }
  
  private void boss3_draw_arai(int paramInt) {
    AraiDrawBoss(objectData);
  }
  
  private void boss4_draw_arai(int paramInt) {
    AraiDrawBoss(objectData);
  }
  
  private void boss5_draw_arai(int paramInt) {
    AraiDrawBoss(objectData);
    DrawBoss5Block(true);
  }
  
  private void drawBoss6Piston(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i;
    byte b1;
    byte b2;
    byte b;
    boolean bool;
    int j = this.bossOriginX + paramInt1 - mapView[0];
    int k = this.bossOriginY + paramInt2 - mapView[1];
    if (paramInt3 == 0) {
      i = paramInt4;
      b1 = -84;
      b2 = -24;
      b = 24;
      bool = true;
    } else {
      i = -paramInt4;
      b1 = 84;
      b2 = 24;
      b = -24;
      bool = false;
    } 
    drawRegion(gg, this.m_imgObj[145], 0, 0, 64, 144, bool, j, k + i, 0x1 | 0x2);
    switch ((this.animeTimer >> 1) % 3) {
      case 1:
        drawRegion(gg, this.m_imgObj[145], 64, 0, 16, 16, bool, j, k + i + b, 0x1 | 0x2);
        break;
      case 2:
        drawRegion(gg, this.m_imgObj[145], 64, 16, 16, 16, bool, j, k + i + b, 0x1 | 0x2);
        break;
    } 
    if (paramInt4 > 0) {
      drawRegion(gg, this.m_imgObj[145], 16, 120, 32, 24, bool, j, k + i + b1, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[145], 16, 120, 32, 24, bool, j, k + i + b1 + b2, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[145], 16, 120, 32, 24, bool, j, k + i + b1 + b2 + b2, 0x1 | 0x2);
    } 
  }
  
  private void drawBoss6Lamp(int paramInt) {
    int i = 1415 - mapView[0];
    int j = 60 - mapView[1];
    switch (paramInt) {
      case 0:
        return;
      case 2:
        drawRegion(gg, this.m_imgObj[145], 80, 0, 16, 16, 0, i, j, 0x1 | 0x2);
        break;
      case 3:
        drawRegion(gg, this.m_imgObj[145], 80, 16, 16, 16, 0, i, j, 0x1 | 0x2);
        break;
      case 4:
        drawRegion(gg, this.m_imgObj[145], 80, 32, 16, 16, 0, i, j, 0x1 | 0x2);
        break;
    } 
  }
  
  private void drawEggman(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b;
    int i = 0;
    switch (paramInt1) {
      default:
        i = 0 + (this.animeTimer >> 1 & 0x1);
        break;
      case 1:
        i = 2 + (this.animeTimer & 0x1);
        break;
      case 2:
        i = 4;
        break;
      case 3:
        i = 5 + (this.animeTimer >> 1) % 3;
        break;
    } 
    short s1 = this.RectEggmanTbl[i][0];
    short s2 = this.RectEggmanTbl[i][1];
    short s3 = this.RectEggmanTbl[i][2];
    short s4 = this.RectEggmanTbl[i][3];
    switch (paramInt4) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        break;
      case 2:
        b = 1;
        break;
      case 3:
        b = 3;
        break;
    } 
    drawRegion(gg, this.m_imgObj[146], s1, s2, s3, s4, b, paramInt2 - mapView[0], paramInt3 - mapView[1], 0x1 | 0x2);
  }
  
  private void boss6_draw_arai(int paramInt) {
    int[] arrayOfInt = objectData;
    if (this.bossStep > 200)
      return; 
    UpdateBossPos(arrayOfInt);
    if (this.bossStep < 100) {
      drawEggman(this.bossFace, arrayOfInt[2], arrayOfInt[3], 0);
    } else {
      AraiDrawBoss(arrayOfInt);
    } 
    drawBoss6Piston(boss6PistonPos[0][0], boss6PistonPos[0][1], 0, boss6Piston[0] / 100);
    drawBoss6Piston(boss6PistonPos[1][0], boss6PistonPos[1][1], 0, boss6Piston[1] / 100);
    drawBoss6Piston(boss6PistonPos[2][0], boss6PistonPos[2][1], 1, boss6Piston[2] / 100);
    drawBoss6Piston(boss6PistonPos[3][0], boss6PistonPos[3][1], 1, boss6Piston[3] / 100);
    switch (boss6Lamp) {
      default:
        drawBoss6Lamp(2);
        break;
      case 0:
        drawBoss6Lamp(1);
        break;
      case 2:
      case 3:
        drawBoss6Lamp(3 + (this.animeTimer & 0x1));
        break;
    } 
    drawWipe();
  }
  
  private void AraiOfsDraw(Image paramImage, int paramInt1, int paramInt2, int paramInt3, short[] paramArrayOfshort) {
    byte b;
    short s1 = paramArrayOfshort[0];
    short s2 = paramArrayOfshort[1];
    short s3 = paramArrayOfshort[2];
    short s4 = paramArrayOfshort[3];
    short s5 = paramArrayOfshort[4];
    short s6 = paramArrayOfshort[5];
    switch (paramInt3) {
      default:
        b = 0;
        break;
      case 1:
        b = 2;
        s5 = -s5;
        break;
      case 2:
        b = 1;
        s6 = -s6;
        break;
      case 3:
        b = 3;
        s5 = -s5;
        s6 = -s6;
        break;
    } 
    drawRegion(gg, paramImage, s1, s2, s3, s4, b, paramInt1 - mapView[0] + s5, paramInt2 - mapView[1] + s6, 0x1 | 0x2);
  }
  
  private void DrawBossFace(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    switch (paramInt1) {
      case 0:
        if (paramInt5 == 0) {
          AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[1]);
          break;
        } 
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[2]);
        break;
      case 1:
        if (paramInt5 == 0) {
          AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[3]);
          break;
        } 
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[4]);
        break;
      case 2:
        if (paramInt5 == 0) {
          AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[1]);
          break;
        } 
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[6]);
        break;
      case 3:
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[6]);
        break;
      case 4:
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[7]);
        break;
      case 5:
        if (paramInt5 == 0) {
          AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[21]);
          break;
        } 
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[22]);
        break;
      case 6:
        AraiOfsDraw(this.m_imgObj[120], paramInt2, paramInt3, paramInt4, this.RectBossTbl[0]);
        break;
    } 
  }
  
  private void DrawBossPartsStage1(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    short s1 = this.RectBossTbl[15][4];
    short s2 = this.RectBossTbl[15][5];
    int k = this.bossParam1;
    int m = this.bossAngle / 100;
    if (paramInt3 == 1)
      s1 = -s1; 
    while (m < 0)
      m += 360; 
    int n = 180 + dSin(m) * 90 / 100;
    if (k > 16)
      AraiOfsDraw(this.m_imgObj[120], paramInt1, paramInt2, paramInt3, this.RectBossTbl[15 + (this.animeTimer >> 2 & 0x1)]); 
    int i1;
    for (i1 = 0; i1 < 4; i1++) {
      int i4 = (i1 + 1) * 16;
      int i2 = paramInt1 + dSin(n) * i4 / 100 + s1;
      int i3 = paramInt2 + dCos(n) * i4 / 100 + s2;
      if (k > 16 + i4)
        AraiOfsDraw(this.m_imgObj[120], i2, i3, 0, this.RectBossTbl[17]); 
      if (paramInt4 == 2 && Math.abs(k - 16 + i4) < 4)
        SetObj2(1, i2, i3, 0, 0, 0, 0); 
    } 
    int i = paramInt1 + s1 + dSin(n) * k / 100;
    int j = paramInt2 + s2 + dCos(n) * k / 100;
    if (paramInt4 == 2) {
      i1 = paramInt1 + s1 + dSin(n) * 96 / 100;
      int i2 = paramInt2 + s2 + dCos(n) * 96 / 100;
      AraiOfsDraw(this.m_imgObj[121], i1, i2, 0, this.RectBossBallTbl);
      if ((this.animeTimer & 0x7) == 4)
        SetObj2(1, i1 + rnd(48) - 24, i2 + rnd(48) - 24, 0, 0, 0, 0); 
    } else {
      AraiOfsDraw(this.m_imgObj[121], i, j, 0, this.RectBossBallTbl);
    } 
  }
  
  private void DrawBossPartsStage2(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  private void DrawBossPartsStage3(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    AraiOfsDraw(this.m_imgObj[120], paramInt1, paramInt2, paramInt3, this.RectBossTbl[19]);
  }
  
  private void DrawBossPartsStage4(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    AraiOfsDraw(this.m_imgObj[120], paramInt1, paramInt2, paramInt3, this.RectBossTbl[19]);
  }
  
  private void DrawBossPartsStage5(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    AraiOfsDraw(this.m_imgObj[120], paramInt1, paramInt2 + 8 - 32 + this.bossParam1, paramInt3, this.RectBossTbl[20]);
  }
  
  private void DrawBossPartsStage6(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  private void DrawBossParts(int paramInt1, int paramInt2, int paramInt3) {
    switch (this.bossType) {
      case 0:
        DrawBossPartsStage1(paramInt1, paramInt2, paramInt3, 0);
        break;
      case 1:
        DrawBossPartsStage2(paramInt1, paramInt2, paramInt3, 0);
        break;
      case 2:
        DrawBossPartsStage3(paramInt1, paramInt2, paramInt3, 0);
        break;
      case 3:
        DrawBossPartsStage4(paramInt1, paramInt2, paramInt3, 0);
        break;
      case 4:
        DrawBossPartsStage5(paramInt1, paramInt2, paramInt3, 0);
        break;
      case 5:
        DrawBossPartsStage6(paramInt1, paramInt2, paramInt3, 0);
        break;
    } 
  }
  
  private void DrawBossEnd(int paramInt1, int paramInt2, int paramInt3) {
    switch (this.bossType) {
      case 0:
        DrawBossPartsStage1(paramInt1, paramInt2, paramInt3, 2);
        break;
      case 1:
        DrawBossPartsStage2(paramInt1, paramInt2, paramInt3, 2);
        break;
      case 2:
        DrawBossPartsStage3(paramInt1, paramInt2, paramInt3, 2);
        break;
      case 3:
        DrawBossPartsStage4(paramInt1, paramInt2, paramInt3, 2);
        break;
      case 4:
        DrawBossPartsStage5(paramInt1, paramInt2, paramInt3, 2);
        break;
      case 5:
        DrawBossPartsStage6(paramInt1, paramInt2, paramInt3, 2);
        break;
    } 
  }
  
  private void AraiDrawBoss(int[] paramArrayOfint) {
    int i1 = this.animeTimer >> 3 & 0x1;
    int i2 = this.animeTimer >> 2 & 0x1;
    int i3 = this.animeTimer >> 1 & 0x1;
    int i4 = this.animeTimer & 0x1;
    if (this.bossStep < 2)
      return; 
    int k = paramArrayOfint[1];
    UpdateBossPos(paramArrayOfint);
    int i = paramArrayOfint[2];
    int j = paramArrayOfint[3];
    int m = this.bossAnim;
    int n = this.bossDir;
    byte b = ((this.animeTimer & 0x1) == 0 && this.bossFlash > 0) ? 1 : 0;
    switch (m) {
      case 0:
        DrawBossParts(i, j, n);
        DrawBossFace(this.bossFace, i, j, n, i2);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8 + b]);
        break;
      case 1:
        DrawBossParts(i, j, n);
        DrawBossFace(1, i, j, n, i2);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8]);
        break;
      case 2:
        DrawBossParts(i, j, n);
        DrawBossFace(this.bossFace, i, j, n, i2);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[11 + i4]);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8 + b]);
        break;
      case 3:
        DrawBossEnd(i, j, n);
        DrawBossFace(2, i, j, n, i1);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8]);
        if (i2 != 0)
          SetObj2(1, i + rnd(32) - 16, j + rnd(32) - 16 - 8, 0, 0, 0, 0); 
        break;
      case 4:
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[7]);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8]);
        break;
      case 5:
        DrawBossFace(0, i, j, n, i2);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8]);
        if (i3 == 1)
          AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[18]); 
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[13 + i2]);
        break;
      case 6:
        DrawBossFace(0, i, j, n, i2);
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[8]);
        if (i3 != 0)
          AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[18]); 
        AraiOfsDraw(this.m_imgObj[120], i, j, n, this.RectBossTbl[11 + i4]);
        break;
      case 7:
        DrawBossFace(6, 1760, 168, 1, 0);
        AraiOfsDraw(this.m_imgObj[147], 1760, 168, n, this.RectBoss2Tbl[0]);
        drawEggman(3, i, j, 1);
        break;
      case 8:
        DrawBossFace(0, i, j, 1, i2);
        AraiOfsDraw(this.m_imgObj[147], i, j, 1, this.RectBoss2Tbl[0]);
        break;
      case 9:
        DrawBossFace(0, i, j, 1, i2);
        AraiOfsDraw(this.m_imgObj[147], i, j, 1, this.RectBoss2Tbl[1]);
        break;
      case 10:
        DrawBossFace(0, i, j, 1, i2);
        AraiOfsDraw(this.m_imgObj[147], i, j, 1, this.RectBoss2Tbl[2]);
        break;
      case 11:
        DrawBossFace(5, i, j, 1, i2);
        AraiOfsDraw(this.m_imgObj[147], i, j, 1, this.RectBoss2Tbl[3]);
        if (i2 != 0)
          SetObj2(1, i + rnd(32) - 16, j + rnd(32) - 16 - 8, 0, 0, 0, 0); 
        break;
    } 
  }
  
  private void startContinue() {
    continueStep = 0;
    mode = MODE_CONTINUE;
  }
  
  private void moveContinue() {
    if (continueCount > 0 && continueStep < 4)
      continueCount--; 
    switch (continueStep) {
      default:
        continueStep = 1;
      case 1:
        continueSonicPosX = 120;
        continueSonicPosY = -20;
        continueCount = 164;
        continueResult = 0;
        continueSonicAnim = -1;
        continueSonicAnim2 = -1;
        try {
          for (byte b = 120; b < ''; b++)
            this.m_imgObj[b] = null; 
          DoGc();
          this.m_imgObj[121] = createImage("/continue.png");
        } catch (Throwable throwable) {}
        PlayMusic(22);
        continueStep++;
      case 2:
        continueSonicAnim = 0;
        continueSonicPosY += 15;
        if (continueSonicPosY < 167)
          break; 
        continueSonicPosY = 167;
        continueSonicAnim = 1;
        continueStep++;
      case 3:
        if (continueCount == 0) {
          resultContinue(false);
          continueStep = 999;
          break;
        } 
        if (KeyPress[0]) {
          continueSonicAnim = 2;
          continueSonicAnim2 = 0;
          clearKey();
          continueStep++;
        } 
        break;
      case 4:
        continueSonicAnim2++;
        if (continueSonicAnim2 > 8)
          continueSonicPosX++; 
        if (continueSonicAnim2 > 12)
          continueSonicPosX += 20; 
        if (continueSonicAnim2 < 45)
          break; 
        continueStep++;
        break;
      case 5:
        StopMusic();
        resultContinue(true);
        continueStep = 999;
        break;
      case 999:
        break;
    } 
  }
  
  private void drawContinue() {
    int i = continueSonicPosX;
    int j = continueSonicPosY;
    byte b = 120;
    gg.setColor(0);
    gg.fillRect(0, 0, 240, 240);
    if (continueStep < 2)
      return; 
    if (continueSonicAnim == 0) {
      gg.drawRegion(this.m_imgObj[121], 0, 0, 48, 32, 0, b, 167, 0x1 | 0x2);
      int n = this.animeTimer % 5;
      short s1 = ContinueSonicTbl[n][0];
      short s2 = ContinueSonicTbl[n][1];
      byte b1 = 48;
      byte b2 = 24;
      boolean bool = (ContinueSonicTbl[n][2] == 0) ? false : true;
      gg.drawRegion(this.m_imgCmd[SONIC_N], s1, s2, b1, b2, bool, i, j, 0x1 | 0x2);
    } else if (continueSonicAnim == 1) {
      int n = this.animeTimer >> 2 & 0x3;
      short s1 = ContinueSonicTbl2[n][0];
      short s2 = ContinueSonicTbl2[n][1];
      byte b1 = 48;
      byte b2 = 32;
      gg.drawRegion(this.m_imgObj[121], s1, s2, b1, b2, 0, b, 167, 0x1 | 0x2);
    } else if (continueSonicAnim == 2) {
      int i1;
      byte b1;
      byte b2;
      byte b3;
      byte b4;
      int n = continueSonicAnim2;
      if (continueSonicAnim2 < 0)
        n = 0; 
      if (continueSonicAnim2 < 1) {
        i1 = 48;
        b1 = 120;
        b2 = 48;
        b3 = 24;
        b4 = -16;
      } else {
        if (continueSonicAnim2 < 12) {
          n = (continueSonicAnim2 >> 1) % 6;
        } else {
          n = 6 + (continueSonicAnim2 - 12) % 4;
        } 
        i1 = n * 40;
        b1 = 40;
        b2 = 40;
        b3 = 40;
        b4 = -4;
      } 
      gg.drawRegion(this.m_imgObj[121], 0, 0, 48, 32, 0, b, 167, 0x1 | 0x2);
      gg.drawRegion(this.m_imgCmd[SONIC_N], i1, b1, b2, b3, 0, i, j + b4, 0x1 | 0x2);
    } 
    gg.drawRegion(this.m_imgObj[121], 96, 0, 16, 16, 0, b - 16, 128, 0x1 | 0x2);
    gg.drawRegion(this.m_imgObj[121], 96, 0, 16, 16, 0, b + 16, 128, 0x1 | 0x2);
    int k = continueCount / 15 % 10;
    int m = continueCount / 15 / 10 % 10;
    gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], m * 7, 0, 7, 13, 0, b - 4, 128, 0x1 | 0x2);
    gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], k * 7, 0, 7, 13, 0, b + 4, 128, 0x1 | 0x2);
    gg.drawRegion(this.m_imgObj[121], 0, 64, 120, 16, 0, b, 80, 0x1 | 0x2);
  }
  
  private void IkeshitaLoadStageImage(int paramInt) {
    try {
      if (this.zoneNumber == 5) {
        this.m_imgObj[BURANKO] = createImage("/buranko_.png");
        this.m_imgObj[35] = createImage("/noko.png");
        this.m_imgObj[DOOR] = createImage("/door.png");
        this.m_imgObj[BELTC] = createImage("/beltc.png");
        this.m_imgObj[31] = createImage("/yukae.png");
        this.m_imgObj[29] = createImage("/yukai.png");
        this.m_imgObj[84] = createImage("/beltcon.png");
        this.m_imgObj[27] = createImage("/bryuka_sc.png");
      } 
      if (this.zoneNumber == 0) {
        this.m_imgObj[4] = createImage("/thashi.png");
        this.m_imgObj[18] = createImage("/brkabe_g.png");
      } 
      if (this.zoneNumber == 2)
        this.m_imgObj[BURANKO] = createImage("/buranko_m.png"); 
      if (this.zoneNumber == 3) {
        this.m_imgObj[75] = createImage("/mfire.png");
        this.m_imgObj[BURANKO] = createImage("/buranko_s.png");
      } 
      if (this.zoneNumber == 1 && this.stageNumber != 3)
        this.m_imgObj[12] = createImage("/dai_la.png"); 
      if (this.zoneNumber != 2)
        this.m_imgObj[15] = createImage("/switch2.png"); 
      if (this.zoneNumber == 1 && this.stageNumber == 3) {
        this.m_imgObj[32] = createImage("/z_dai4_l.png");
        this.m_imgObj[12] = createImage("/z_dai_la.png");
      } 
      if (this.zoneNumber == 4)
        this.m_imgObj[107] = createImage("/dai2_3.png"); 
      this.m_imgObj[44] = createImage("/gole.png");
      this.m_imgObj[60] = createImage("/tekyu.png");
      this.m_imgObj[45] = createImage("/bten.png");
    } catch (Throwable throwable) {}
  }
  
  private boolean ObjectMoveChk(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return ((paramInt3 == -1 || (0 <= paramInt1 - mapOxy[0] + paramInt3 && 240 >= paramInt1 - mapOxy[0] - paramInt3)) && (paramInt4 == -1 || (0 <= paramInt2 - mapOxy[1] + paramInt4 && 240 >= paramInt2 - mapOxy[1] - paramInt4)));
  }
  
  private void ring_sflag_ring_18_00_move_ikeshita(int paramInt) {
    byte b1 = 8;
    byte b2 = 8;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    int i = ObjectColChk2(objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    if (i >= 0) {
      if (objectData[5] == 0 && objectData[0] != -1) {
        objectData[5] = 1;
        objectData[10] = this.cpuTimer;
        ringcount++;
      } 
    } else if (objectData[12] != 0) {
      i = ObjectColChk2(objectData[2], objectData[12], objectData[6], objectData[12], b1, b2);
      if (i >= 0 && objectData[5] == 0 && objectData[0] != -1) {
        objectData[5] = 1;
        objectData[10] = this.cpuTimer;
        ringcount++;
      } 
    } 
    if (objectData[5] == 1 && this.cpuTimer - objectData[10] >= 20) {
      objectData[0] = -1;
      objectData[5] = 0;
    } 
  }
  
  private void ring_sflag_ring_00_18_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void buranko_nflag_move_ikeshita(int paramInt) {
    int i = dSin(this.animeTimer * 3) * 87;
    int j = dSin(objectData[6] * 3) * 87;
    objectData[6] = this.animeTimer;
    int m = objectData[4] + 1;
    if (objectData[4] == 5) {
      i = -i;
      j = -j;
    } else if (objectData[19] == 1) {
      i = -i;
      j = -j;
    } 
    int k = m;
    byte b1 = 24;
    byte b2 = 8;
    if (this.zoneNumber == 3) {
      b1 = 44;
      b2 = 24;
      k <<= 4;
      k += 8;
    } else if (this.zoneNumber == 5) {
      b1 = 24;
      b2 = 24;
      k <<= 4;
      k -= 24;
    } else {
      k <<= 4;
      k -= 8;
    } 
    byte b3 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b3 = 20; 
    int n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + dSin(180 + i / 100) * k / 100, objectData[3] + dCos(180 + i / 100) * k / 100, objectData[2] + dSin(180 + j / 100) * k / 100, objectData[3] + dCos(180 + j / 100) * k / 100, b1, b2);
    if (this.zoneNumber == 3) {
      if (n == 0) {
        PlayerParam[1] = objectData[3] + dCos(180 + i / 100) * k / 100 - b2 << 8;
        PlayerParam[0] = PlayerParam[0] + (dSin(180 + i / 100) * k - dSin(180 + j / 100) * k << 8) / 100;
        setRaidOnSize(objectData[2] + dSin(180 + i / 100) * k / 100, b1);
        playerRaidOn(objectData[22]);
      } else {
        n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + dSin(180 + i / 100) * k / 100, objectData[3] + dCos(180 + i / 100) * k / 100 - 4, objectData[2] + dSin(180 + j / 100) * k / 100, objectData[3] + dCos(180 + j / 100) * k / 100 - 4, 32, 20);
        if (n >= 0)
          if (n == 0) {
            PlayerParam[1] = objectData[3] + dCos(180 + i / 100) * k / 100 - b2 << 8;
            PlayerParam[0] = PlayerParam[0] + (dSin(180 + i / 100) * k - dSin(180 + j / 100) * k << 8) / 100;
            setRaidOnSize(objectData[2] + dSin(180 + i / 100) * k / 100, b1);
            playerRaidOn(objectData[22]);
          } else if (Math.abs(objectData[2] + dSin(180 + i / 100) * k / 100 - PlayerPosX()) < 44 && Math.abs(objectData[3] + dCos(180 + i / 100) * k / 100 - 4 - PlayerPosY() - b3 + 1) < b3 + 24) {
            playdamageset();
          }  
      } 
    } else if (this.zoneNumber != 5) {
      if (n != 0)
        n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + dSin(180 + i / 100) * k / 100, objectData[3] + dCos(180 + i / 100) * k / 100 + 5, objectData[2] + dSin(180 + j / 100) * k / 100, objectData[3] + dCos(180 + j / 100) * k / 100 + 5, b1, b2); 
      if (n == 0) {
        PlayerParam[1] = objectData[3] + dCos(180 + i / 100) * k / 100 - b2 << 8;
        PlayerParam[0] = PlayerParam[0] + (dSin(180 + i / 100) * k - dSin(180 + j / 100) * k << 8) / 100;
        setRaidOnSize(objectData[2] + dSin(180 + i / 100) * k / 100, b1);
        playerRaidOn(objectData[22]);
      } 
    } else if (n >= 0) {
      playdamageset();
    } else if (Math.abs(objectData[2] + dSin(180 + i / 100) * k / 100 - PlayerPosX()) < 12 + b1 && Math.abs(objectData[3] + dCos(180 + i / 100) * k / 100 - PlayerPosY() - b3) < b3 + b2) {
      playdamageset();
    } 
    if (raidOn && raidObjectNum == objectData[20] && n != 0)
      raidOn = false; 
  }
  
  private void hashi_nflag_move_ikeshita(int paramInt) {
    int i = 0;
    int j = 240;
    byte b3 = 8;
    byte b4 = 8;
    int k = 0;
    boolean bool = false;
    int m = 6;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    k = this.cpuTimer - objectData[14];
    objectData[14] = this.cpuTimer;
    byte b2 = 99;
    j = 999;
    for (byte b1 = 0; b1 < 12; b1++) {
      i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 4, 12, objectData[2] - 96 + (b1 << 4), objectData[3] + objectData[10], objectData[6] - 96 + (b1 << 4), objectData[7] + objectData[10], b3, b4);
      if (i >= 0 && i != 3) {
        bool = true;
        PlayerParam[1] = objectData[3] - b4 << 8;
        if (Math.abs(PlayerPosX() - objectData[2] - 8) < j) {
          b2 = b1;
          j = Math.abs(PlayerPosX() - objectData[2]);
        } 
      } 
    } 
    if (b2 != 99) {
      PlayerParam[1] = objectData[3] - b4 + objectData[10] << 8;
      setRaidOnSize(objectData[2], 96);
      playerRaidOn(objectData[22]);
      objectData[5] = b2;
      if (b2 <= 6) {
        m = b2;
      } else {
        m = 6 - b2 % 6;
      } 
      objectData[10] = objectData[10] + k;
      if (objectData[10] >= m * 2)
        objectData[10] = m * 2; 
      if (b2 == 0 || b2 == 11)
        objectData[10] = 0; 
    } else {
      objectData[10] = objectData[10] - k;
      if (objectData[10] <= 0)
        objectData[10] = 0; 
    } 
    if (bool)
      i = 0; 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void thashi_nflag_move_ikeshita(int paramInt) {
    byte b2 = 8;
    byte b3 = 12;
    int i = -1;
    int[] arrayOfInt = { -4, 4 };
    for (byte b1 = 0; b1 < 12; b1++) {
      if ((this.animeTimer / 10 + 12 - b1) % 7 == 2) {
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 96 + (b1 << 4), objectData[3], objectData[2] - 96 + (b1 << 4), objectData[3], b2, b3);
        if (i >= 0)
          playdamageset(); 
      } 
    } 
  }
  
  private void break_sflag_move_ikeshita(int paramInt) {
    int i = 48;
    byte b1 = 48;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = -1;
    int i1 = -99;
    byte b2 = 0;
    int i2 = 999;
    int i3 = 0;
    byte b = 0;
    if (objectData[4] != 0)
      b = -1; 
    m = objectData[2];
    if (objectData[5] == 0) {
      if (b == 0) {
        i3 = this.break_sflag_ike_yuka.length;
      } else {
        i3 = -this.break_sflag_ike_yuka.length;
      } 
      n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 12, objectData[2] - i3, objectData[3] - this.break_sflag_ike_yuka[0] + 8, objectData[2] - i3, objectData[3] - this.break_sflag_ike_yuka[0] + 8, 8, 8);
      if (n >= 0)
        PlayerParam[1] = objectData[3] - this.break_sflag_ike_yuka[0] + 8 - 8 << 8; 
    } 
    if (objectData[5] != 0) {
      for (byte b3 = 0; b3 < 36; b3++) {
        j = this.cpuTimer / 2 - objectData[10] - (b3 << 1) + b3;
        if (j < 0) {
          k = b3 / 6 << 3;
          break;
        } 
        if (b3 == 29)
          objectData[5] = 2; 
      } 
      if (k >= 48)
        objectData[5] = 2; 
      if (objectData[4] != 0) {
        m = objectData[2] - k;
      } else {
        m = objectData[2] + k;
      } 
    } 
    if (objectData[5] != 2) {
      i3 = 0;
      for (byte b3 = 0; b3 < this.break_sflag_ike_yuka.length; b3++) {
        if (b == 0) {
          i3 = b3;
        } else {
          i3 = this.break_sflag_ike_yuka.length - b3 - 1;
        } 
        if ((b == 0 && k <= b3) || (b != 0 && this.break_sflag_ike_yuka.length - k >= b3)) {
          n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 12, objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1), objectData[3] - this.break_sflag_ike_yuka[i3], objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1), objectData[3] - this.break_sflag_ike_yuka[i3], 1, 1);
          if (n >= 0 && n == 0 && i2 > Math.abs(PlayerPosX() - objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1))) {
            i2 = Math.abs(PlayerPosX() - objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1));
            i1 = i3;
            b2 = b3;
          } 
          if (i1 != -99 && 8 > Math.abs(PlayerPosX() - objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1)) && PlayerPosY() - objectData[3] - this.break_sflag_ike_yuka[i3] > 0 && PlayerPosY() - objectData[3] - this.break_sflag_ike_yuka[i3] <= 64 && i2 > Math.abs(PlayerPosX() - objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1))) {
            i2 = Math.abs(PlayerPosX() - objectData[2] - this.break_sflag_ike_yuka.length + (b3 << 1));
            i1 = i3;
            b2 = b3;
          } 
        } 
      } 
      n = -1;
      if (i2 != 999) {
        objectData[17] = i1;
        PlayerParam[1] = objectData[3] - this.break_sflag_ike_yuka[i1] - 1 << 8;
        if (objectData[5] == 0)
          objectData[10] = this.cpuTimer / 2; 
        objectData[5] = 1;
        if (b == 0) {
          i = k;
        } else {
          i = this.break_sflag_ike_yuka.length - k;
        } 
        setRaidOnSize(m, i);
        playerRaidOn(objectData[22]);
        n = 0;
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && n != 0)
      raidOn = false; 
  }
  
  private void yuka_nflag_move_ikeshita(int paramInt) {
    byte b1 = 0;
    int i = -1;
    byte b = -1;
    view_yuka(objectData[2], objectData[3], objectData[4]);
    if (objectData[4] == 21) {
      b1 = 2;
    } else if (objectData[4] == 1) {
      b1 = 0;
    } else {
      b1 = 1;
    } 
    boolean bool1 = false;
    int j = 0;
    boolean bool2 = false;
    byte b3 = 0;
    objectData[17] = objectData[2];
    objectData[18] = objectData[3];
    if (b1 != 2 && objectData[4] != 32) {
      objectData[5] = objectData[5] + 1;
      b3 = 32;
      if (objectData[4] == 35) {
        b3 = 32;
      } else if (objectData[4] == 1) {
        b3 = 16;
      } else if (objectData[4] == 41) {
        b3 = 16;
      } else if (objectData[4] == 34) {
        b3 = 24;
      } else if (objectData[4] == 43) {
        b3 = 32;
      } else if (objectData[4] == 33) {
        b3 = 16;
      } else if (objectData[4] == 42) {
        b3 = 24;
      } 
      if (objectData[4] == 3) {
        j = objectData[18] >> 8;
        objectData[3] = objectData[9] + j;
      } else if (objectData[4] == 7) {
        j = 0;
      } else if (objectData[4] == 1) {
        j = dSin(objectData[5]) * b3 / 100 - b3;
        objectData[3] = objectData[9] + 8 + j;
      } else {
        j = dSin(objectData[5]) * b3 / 100 - b3;
        objectData[3] = objectData[9] + j;
      } 
    } 
    if (b1 == 2)
      objectData[3] = objectData[9]; 
    if (b1 == 2 && objectData[13] != 0) {
      objectData[3] = objectData[9] + 15;
      if (objectData[16] == 0)
        objectData[16] = 1; 
    } 
    objectData[13] = 0;
    int k = 999;
    i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[17], objectData[18], this.yuka_nflag_yuka_w[b1], this.yuka_nflag_ike_yuka[b1][0]);
    if (i >= 0)
      if (i == 1) {
        PlayerParam[0] = objectData[2] - this.yuka_nflag_yuka_w[b1] - 12 << 8;
        PlayerParam[10] = 0;
        b = -99;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + this.yuka_nflag_yuka_w[b1] + 12 + 1 << 8;
        PlayerParam[10] = 0;
        b = -99;
        if (KeyPress[3])
          playerPushSet(); 
      }  
    byte b2;
    for (b2 = 0; b2 < (this.yuka_nflag_ike_yuka[b1]).length; b2++) {
      i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 13, objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1), objectData[3] - this.yuka_nflag_ike_yuka[b1][b2], objectData[17] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1), objectData[18] - this.yuka_nflag_ike_yuka[b1][b2], 1, 1);
      if (i >= 0 && i == 0 && k > Math.abs(PlayerPosX() - objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1))) {
        k = Math.abs(PlayerPosX() - objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1));
        b = b2;
      } 
      if (b != -99 && 8 >= Math.abs(PlayerPosX() - objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1)) && PlayerPosY() - objectData[3] - this.yuka_nflag_ike_yuka[b1][b2] >= -1 && PlayerPosY() - objectData[3] - this.yuka_nflag_ike_yuka[b1][b2] <= 64 && k > Math.abs(PlayerPosX() - objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1))) {
        k = Math.abs(PlayerPosX() - objectData[2] - (this.yuka_nflag_ike_yuka[b1]).length + (b2 << 1));
        b = b2;
      } 
    } 
    i = -1;
    if (k != 999) {
      PlayerParam[1] = objectData[3] - this.yuka_nflag_ike_yuka[b1][b] + 4 << 8;
      setRaidOnSize(objectData[2], (this.yuka_nflag_ike_yuka[b1]).length);
      playerRaidOn(objectData[22]);
      i = 0;
      if (b1 == 2)
        objectData[13] = 1; 
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
    i = -1;
    if (objectData[16] != 0) {
      objectData[16] = objectData[16] + 1;
      if (objectData[16] == 60)
        objectData[15] = this.animeTimer - 1; 
      if (objectData[16] >= 60) {
        int m = 0;
        int n = 0;
        for (b2 = 0; b2 < 8; b2++) {
          m = this.animeTimer - objectData[15];
          n = m - 1;
          if (n < 0)
            n = 0; 
          if (b2 << 3 < m) {
            m = b2 << 3;
            n = m;
          } 
          i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + (m << 1) - 56, objectData[3] - this.yuka_nflag_ike_yuka[b1][m], objectData[17] + (n << 1) - 56, objectData[18] - this.yuka_nflag_ike_yuka[b1][n], 6, 6);
          if (i >= 0) {
            playdamageset();
            break;
          } 
        } 
      } 
    } 
  }
  
  private void turi_nflag_move_ikeshita(int paramInt) {
    byte b1 = 56;
    byte b2 = 12;
    int i = -1;
    view_turi(objectData[8], objectData[9], 0);
    objectData[7] = objectData[3];
    if (objectData[4] == 128) {
      if (objectData[18] == 0)
        objectData[3] = objectData[9] + 112; 
      objectData[18] = 1;
      if (!switchflag[128] && !switchflag[129]) {
        if (objectData[3] < objectData[9] + 112) {
          objectData[3] = objectData[3] + 2;
          if (objectData[3] > objectData[9] + 112)
            objectData[3] = objectData[9] + 112; 
        } 
      } else if (objectData[3] > objectData[9]) {
        objectData[3] = objectData[3] - 1;
      } 
      int[][] arrayOfInt = searchObject(10, 0);
      for (byte b = 0; b < arrayOfInt.length; b++) {
        if (objectData[2] - 56 - arrayOfInt[b][2] - 16 <= 32 && objectData[2] - 56 - arrayOfInt[b][2] - 16 >= -112 && switchflag[128] && objectData[3] < objectData[9] + 16)
          objectData[3] = objectData[9] + 16; 
      } 
    } else {
      char c = ' ';
      if (objectData[4] == 17) {
        b1 = 48;
      } else if (objectData[4] == 2) {
        b1 = 56;
        c = 'P';
      } else if (objectData[4] == 35) {
        b1 = 16;
        c = 'x';
      } else if (objectData[4] == 18) {
        b1 = 48;
        c = 'P';
      } 
      if (objectData[5] == 0) {
        if (objectData[3] < objectData[9] + c) {
          objectData[3] = objectData[3] + 4;
          if (objectData[3] >= objectData[9] + c) {
            objectData[3] = objectData[9] + c;
            objectData[5] = 1;
          } 
        } 
      } else if (objectData[5] < 60) {
        objectData[5] = objectData[5] + 1;
      } else if (objectData[3] > objectData[9]) {
        objectData[3] = objectData[3] - 1;
        if (objectData[3] == objectData[9])
          objectData[5] = 0; 
      } 
    } 
    i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[7], b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        if (objectData[3] - 24 < objectData[9])
          setHeadHit(); 
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        setHeadHit();
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
    if (objectData[4] != 35) {
      b2 = 14;
      b1 = 40;
      i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + 32, objectData[2], objectData[7] + 32, b1, b2);
      if (i >= 0)
        playdamageset(); 
    } 
  }
  
  private void toge_nflag_move_ikeshita(int paramInt) {
    if (this.zoneNumber == 1 && this.stageNumber == 0 && objectData[4] == 64 && objectData[19] != 0)
      return; 
    byte b1 = 20;
    byte b2 = 14;
    boolean bool1 = false;
    objectData[7] = objectData[3];
    objectData[15] = objectData[2];
    if (objectData[4] == 64) {
      b1 = 64;
    } else if (objectData[4] == 16) {
      b1 = 16;
      b2 = 19;
    } 
    if (objectData[4] == 48) {
      b1 = 30;
    } else if (objectData[4] == 18) {
      b1 = 14;
      b2 = 20;
      objectData[6] = objectData[6] + 1;
      objectData[5] = objectData[6];
      if (objectData[6] < 60) {
        objectData[5] = 0;
      } else if (objectData[6] < 68) {
        objectData[5] = objectData[6] - 60;
      } else if (objectData[6] < 128) {
        objectData[5] = 8;
      } else if (objectData[6] < 136) {
        objectData[5] = 136 - objectData[6];
      } else {
        objectData[5] = 0;
        objectData[6] = 0;
      } 
      objectData[2] = objectData[8] - (objectData[5] << 2) + 32;
    } else if (objectData[4] == 32) {
      b1 = 8;
      b2 = 18;
    } else if (objectData[4] == 82) {
      b1 = 20;
      b2 = 6;
      objectData[6] = objectData[6] + 1;
      objectData[5] = objectData[6];
      if (objectData[6] < 60) {
        objectData[5] = 0;
      } else if (objectData[6] < 68) {
        objectData[5] = objectData[6] - 60;
      } else if (objectData[6] < 128) {
        objectData[5] = 8;
      } else if (objectData[6] < 136) {
        objectData[5] = 136 - objectData[6];
      } else {
        objectData[5] = 0;
        objectData[6] = 0;
      } 
      objectData[2] = objectData[8] - (objectData[5] << 2) + 32;
    } else if (objectData[4] == 1) {
      b1 = 20;
      objectData[6] = objectData[6] + 1;
      objectData[5] = objectData[6];
      if (objectData[6] < 60) {
        objectData[5] = 0;
      } else if (objectData[6] < 68) {
        objectData[5] = objectData[6] - 60;
      } else if (objectData[6] < 128) {
        objectData[5] = 8;
      } else if (objectData[6] < 136) {
        objectData[5] = 136 - objectData[6];
      } else {
        objectData[5] = 0;
        objectData[6] = 0;
      } 
      objectData[3] = objectData[9] - (objectData[5] << 2) + 32;
    } 
    boolean bool2 = false;
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[15], objectData[7], b1, b2);
    if (i >= 0) {
      if (objectData[4] == 82) {
        if (i == 1 || i == 2) {
          playdamageset();
          bool2 = true;
        } 
      } else if (objectData[4] == 18 || objectData[4] == 16) {
        if (i == 1 || i == 2) {
          playdamageset();
          bool2 = true;
        } 
      } else {
        if (i == 0 && objectData[19] == 0) {
          playdamageset2();
          bool2 = true;
        } else if (i == 3 && objectData[19] != 0) {
          playdamageset();
          bool2 = true;
        } 
        if (objectData[19] != 0 && this.zoneNumber == 1)
          if (i == 1) {
            PlayerParam[0] = objectData[2] - b1 - 12 << 8;
            PlayerParam[10] = 0;
            if (PlayerParam[3] > 0)
              PlayerParam[3] = 0; 
          } else if (i == 2) {
            PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (PlayerParam[3] < 0)
              PlayerParam[3] = 0; 
          }  
      } 
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        if (blockColChk2(PlayerPosX(), PlayerPosY() - 32))
          if (PlayerParam[12] == 0) {
            PlayerParam[0] = objectData[2] - b1 - 12 << 8;
          } else {
            PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          }  
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (!bool2 && KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (!bool2 && KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        if (PlayerParam[5] < 0)
          PlayerParam[5] = 0; 
        if (this.zoneNumber == 0)
          PlayerParam[1] = PlayerParam[1] + 4096; 
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void box_sflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    boolean bool1 = false;
    int i = 0;
    int j = -1;
    byte b = -1;
    boolean bool2 = false;
    objectData[15] = objectData[2];
    objectData[16] = objectData[3];
    if (objectData[4] == 129) {
      b1 = 64;
      b2 = 16;
    } 
    i = objectData[2];
    if (objectData[5] != 0 && objectData[10] == 0) {
      objectData[3] = objectData[3] + 2;
      if (blockColChk_Enemy(objectData[2] - b1 + 1, objectData[3] + b2) || blockColChk_Enemy(objectData[2] + b1 - 1, objectData[3] + b2)) {
        objectData[5] = 0;
        if (objectData[4] == 0)
          objectData[6] = 1; 
      } 
    } 
    byte b3;
    for (b3 = 0; b3 < 4; b3++) {
      if (objectData[8] == this.box_sflag_ike_def_X[b3] && objectData[9] == this.box_sflag_ike_def_Y[b3]) {
        b = b3;
        break;
      } 
    } 
    if (objectData[5] == 0 && b != -1 && objectData[10] == 0 && Math.abs(objectData[2] - this.box_sflag_ike_col_X[b]) <= 16 && Math.abs(objectData[3] - this.box_sflag_ike_col_Y[b]) <= 16)
      objectData[10] = 1; 
    if (objectData[17] == 0 && objectData[10] > 0 && objectData[3] < this.box_sflag_ike_col_Y[b]) {
      objectData[3] = objectData[3] + 2;
      if (objectData[3] > this.box_sflag_ike_col_Y[b])
        objectData[3] = this.box_sflag_ike_col_Y[b]; 
    } 
    if (objectData[17] == 0) {
      if (objectData[10] >= 1 && objectData[10] < 15) {
        objectData[10] = objectData[10] + 1;
      } else if (objectData[10] == 15) {
        objectData[2] = objectData[2] + this.box_sflag_ike_box_V[b];
      } else if (objectData[10] >= 16) {
        objectData[10] = objectData[10] + 1;
        if (objectData[10] >= 46 && objectData[10] - 48 <= 96 && objectData[10] % 2 == 0)
          objectData[3] = objectData[3] + 1; 
      } 
    } else if (this.box_sflag_ike_box_V[b] < 0) {
      if (objectData[18] < objectData[2])
        objectData[2] = objectData[2] + this.box_sflag_ike_box_V[b]; 
    } else if (objectData[18] > objectData[2]) {
      objectData[2] = objectData[2] + this.box_sflag_ike_box_V[b];
    } 
    objectData[17] = 0;
    if (objectData[4] != 129 && this.zoneNumber == 2) {
      if (this.zoneNumber == 2 && this.stageNumber == 0 && objectData[4] == 0) {
        int[][] arrayOfInt1 = searchObject(8, 128);
        for (b3 = 0; b3 < arrayOfInt1.length; b3++) {
          if (arrayOfInt1[b3][2] - 56 - objectData[2] - 16 <= 32 && arrayOfInt1[b3][2] - 56 - objectData[2] - 16 >= -112)
            objectData[3] = arrayOfInt1[b3][3] - 28; 
        } 
        int[][] arrayOfInt2 = searchObject(15, 128);
        switchflag[129] = false;
        for (b3 = 0; b3 < arrayOfInt2.length; b3++) {
          if (arrayOfInt2[b3][2] - 8 - objectData[2] - 16 <= 32 && arrayOfInt2[b3][2] - 8 - objectData[2] - 16 >= -32 && arrayOfInt2[b3][3] - 8 - objectData[3] - 16 <= 32 && arrayOfInt2[b3][3] - 8 - objectData[3] - 16 >= -32) {
            switchflag[129] = true;
            switchflag2[129] = true;
          } 
        } 
      } 
      if (objectData[4] != 0) {
        int[][] arrayOfInt = searchObject(9, 16);
        for (b3 = 0; b3 < arrayOfInt.length; b3++) {
          if (arrayOfInt[b3][2] - 20 - objectData[2] - 16 <= 32 && arrayOfInt[b3][2] - 20 - objectData[2] - 16 >= -40 && arrayOfInt[b3][3] - 20 - objectData[3] - 16 <= 32 && arrayOfInt[b3][3] - 20 - objectData[3] - 16 >= -40 && objectData[10] == 15)
            objectData[10] = 16; 
        } 
      } 
      if (objectData[4] == 2) {
        int[][] arrayOfInt = searchObject(77, 16);
        for (b3 = 0; b3 < arrayOfInt.length; b3++) {
          if (arrayOfInt[b3][2] - 20 - 64 - objectData[2] - 16 <= 32 && arrayOfInt[b3][2] - 20 - 64 - objectData[2] - 16 >= -112 && arrayOfInt[b3][3] - 48 + 16 - 16 - objectData[3] - 16 <= 32 && arrayOfInt[b3][3] - 48 + 16 - 16 - objectData[3] - 16 >= -32 && arrayOfInt[b3][5] > 1 && arrayOfInt[b3][5] / 4 < 26) {
            objectData[3] = arrayOfInt[b3][3] - 48 + 16 - 16;
            objectData[18] = arrayOfInt[b3][2] - 10 + 16;
            objectData[17] = 1;
            break;
          } 
        } 
      } 
    } 
    i -= objectData[2];
    j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[15], objectData[16], b1, b2);
    if (j >= 0) {
      if (j == 0) {
        int k = -1;
        k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] - b2, objectData[15], objectData[16] - b2, b1, 2);
        if (k == 1) {
          if (((KeyPress[4] && objectData[5] == 0) || objectData[4] == 129 || objectData[4] == 0) && objectData[10] == 0) {
            objectData[2] = objectData[2] + 1;
            if (blockColChk_Enemy(objectData[2] + b1, objectData[3] - b2))
              objectData[2] = objectData[2] - 1; 
          } 
        } else if (k == 2 && ((KeyPress[3] && objectData[5] == 0) || objectData[4] == 129 || objectData[4] == 0) && objectData[10] == 0) {
          objectData[2] = objectData[2] - 1;
          if (blockColChk_Enemy(objectData[2] - b1, objectData[3] - b2))
            objectData[2] = objectData[2] + 1; 
        } 
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        bool2 = true;
        PlayerParam[0] = PlayerParam[0] - (i << 8);
      } 
      if (j == 1) {
        if (KeyPress[4] && (objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0)) {
          if (objectData[10] == 0) {
            objectData[2] = objectData[2] + 1;
            objectData[7] = 1;
            if (blockColChk_Enemy(objectData[2] + b1, objectData[3] + b2 - 1))
              objectData[2] = objectData[2] - 1; 
            if (objectData[4] == 129 && objectData[2] > objectData[8] + 64)
              objectData[2] = objectData[8] + 64; 
          } 
          playerPushSet();
        } 
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
      } 
      if (j == 2) {
        if (KeyPress[3] && (objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0)) {
          if (objectData[10] == 0) {
            objectData[2] = objectData[2] - 1;
            objectData[7] = -1;
            if (blockColChk_Enemy(objectData[2] - b1, objectData[3] + b2 - 1))
              objectData[2] = objectData[2] + 1; 
          } 
          playerPushSet();
        } 
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
      } 
      if (j == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      } 
    } 
    if (!blockColChk_Enemy(objectData[2] - b1 + 1, objectData[3] + b2) && !blockColChk_Enemy(objectData[2] + b1 - 1, objectData[3] + b2))
      objectData[5] = objectData[5] + 1; 
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
    if (bool2) {
      view_box_ride(objectData[2], objectData[3], objectData[4]);
    } else {
      view_box(objectData[2], objectData[3], objectData[4]);
    } 
  }
  
  private void fblock_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    boolean bool = false;
    view_fblock(objectData[2], objectData[3], objectData[4]);
    if (objectData[4] == 2 || objectData[4] == 10) {
      if (objectData[5] == 2) {
        objectData[3] = objectData[11] + dSin(this.cpuTimer) * 6 / 100 + 6;
      } else if (objectData[5] == 1) {
        objectData[10] = objectData[10] + 1;
        objectData[3] = objectData[3] + objectData[10];
        if (blockColChk_Enemy(objectData[2] - b1 + 1, objectData[3] + b2) || blockColChk_Enemy(objectData[2] + b1 - 1, objectData[3] + b2)) {
          if ((objectData[8] == 4016 && objectData[9] == 1296) || (objectData[8] == 3920 && objectData[9] == 1328)) {
            objectData[5] = 0;
          } else {
            objectData[5] = 2;
          } 
          objectData[3] = objectData[3] - (objectData[3] + b2) % 16;
          objectData[11] = objectData[3];
        } 
      } else if (objectData[3] < PlayerPosY() && objectData[2] - 100 < PlayerPosX()) {
        objectData[10] = 1;
        objectData[5] = 1;
      } 
    } else if (objectData[4] == 1) {
      objectData[3] = objectData[9] + (dSin(objectData[5]) << 3) / 100 - 8;
      objectData[5] = objectData[5] + 6;
    } 
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      } else if (i == 4 && raidObjectNum != objectData[20]) {
        if (PlayerPosX() < objectData[2]) {
          PlayerParam[0] = objectData[2] - b1 - 12 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
        } else {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } 
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void dainfla_move_ikeshita(int paramInt) {
    byte b1 = 48;
    byte b2 = 16;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    boolean bool1 = false;
    int i = 0;
    boolean bool2 = false;
    if (objectData[4] == 57) {
      b1 = 63;
      b2 = 8;
    } else if (objectData[4] == 40) {
      b1 = 32;
      b2 = 8;
    } 
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[4] == 7 || objectData[4] == 4) {
      b1 = 16;
      b2 = 8;
      if (this.zoneNumber == 1 && this.stageNumber == 0 && switchflag[2])
        objectData[5] = 1; 
      if (this.zoneNumber == 1 && this.stageNumber == 3)
        objectData[5] = 1; 
      if (objectData[10] == 1) {
        i = objectData[2];
        objectData[2] = objectData[2] + 1;
        if (objectData[4] == 7) {
          if (blockColChk_Enemy(objectData[2] + b1, objectData[3] + b2 - 1 - 16)) {
            objectData[2] = objectData[2] - 1;
            objectData[10] = 2;
          } 
        } else if (Math.abs(objectData[2] - objectData[8]) >= 96) {
          objectData[2] = objectData[8] + 96;
          objectData[10] = 2;
        } 
        i -= objectData[2];
      } else if (objectData[10] == 2) {
        objectData[3] = objectData[3] + 5;
        if (blockColChk_Enemy(objectData[2] - b1 + 1, objectData[3] + b2) || blockColChk_Enemy(objectData[2] + b1 - 1, objectData[3] + b2)) {
          objectData[5] = 0;
          objectData[3] = objectData[3] - (objectData[3] + b2) % 16;
          objectData[10] = 3;
        } 
      } 
    } 
    if (objectData[4] == 1) {
      b1 = 16;
      b2 = 16;
      i = objectData[2];
      if (this.cpuTimer % 384 <= 144) {
        objectData[2] = objectData[8] - this.cpuTimer % 384 / 3;
      } else if (this.cpuTimer % 384 > 240) {
        objectData[2] = objectData[8] - 48 - this.cpuTimer % 384 / 3 - 80;
      } 
      i -= objectData[2];
    } else if (objectData[4] == 2) {
      b1 = 16;
      b2 = 16;
      objectData[3] = objectData[9] + 9;
      if (objectData[10] != 0) {
        i = objectData[2];
        objectData[2] = objectData[2] + 1;
        if (blockColChk_Enemy(objectData[2] + b1, objectData[3] + b2 - 1 - 16))
          objectData[2] = objectData[2] - 1; 
        i -= objectData[2];
      } 
    } else if (objectData[4] == 65) {
      i = objectData[2];
      b5 = 48;
      objectData[2] = objectData[8] + dSin(this.cpuTimer) * b5 / 100 - 32 - 48;
      objectData[3] = objectData[9] + 8;
      b3 = 32;
      b4 = 0;
      b1 = 47;
      i -= objectData[2];
    } else if (objectData[4] == 57) {
      if (objectData[10] != 0)
        objectData[10] = objectData[10] + 1; 
      if (objectData[10] > 5) {
        i = objectData[2];
        if (objectData[19] == 0) {
          if (this.cpuTimer - objectData[11] <= 32) {
            objectData[2] = objectData[8] + (this.cpuTimer - objectData[11] << 2);
          } else if (this.cpuTimer - objectData[11] >= 500 && this.cpuTimer - objectData[11] < 532) {
            objectData[2] = objectData[8] + 128 - (this.cpuTimer - objectData[11] - 500 << 2);
          } 
        } else if (this.cpuTimer - objectData[11] <= 32) {
          objectData[2] = objectData[8] - (this.cpuTimer - objectData[11] << 2);
        } else if (this.cpuTimer - objectData[11] >= 500 && this.cpuTimer - objectData[11] < 532) {
          objectData[2] = objectData[8] - 128 - (this.cpuTimer - objectData[11] - 500 << 2);
        } 
        if (this.cpuTimer - objectData[11] >= 532) {
          objectData[10] = 0;
          objectData[11] = 0;
          objectData[2] = objectData[8];
        } 
        i -= objectData[2];
      } else {
        objectData[2] = objectData[8];
      } 
    } else if (objectData[4] == 40) {
      if ((this.cpuTimer >> 1) % 256 < 128) {
        objectData[3] = objectData[9] - (this.cpuTimer >> 1) % 256;
      } else {
        objectData[3] = objectData[9] - 128 - (this.cpuTimer >>> 1) % 256 - 128;
      } 
    } 
    int j = -1;
    if ((objectData[4] != 7 && objectData[4] != 4) || objectData[5] != 0) {
      j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + b3, objectData[3] + b4, objectData[6] + b3, objectData[7] + b4, b1, b2);
      if (j >= 0 && j == 0) {
        PlayerParam[1] = objectData[3] - b2 + b4 << 8;
        if (objectData[4] == 2 || objectData[4] == 1 || objectData[4] == 65) {
          if (objectData[10] == 0) {
            objectData[10] = 1;
            objectData[11] = this.cpuTimer;
          } 
          PlayerParam[0] = PlayerParam[0] - (i << 8);
        } 
        if (objectData[4] == 57) {
          if (objectData[2] + b3 - b1 <= PlayerPosX() && PlayerPosX() <= objectData[2] + b3 + b1 && objectData[10] == 0) {
            objectData[10] = 1;
            objectData[11] = this.cpuTimer;
          } 
          PlayerParam[0] = PlayerParam[0] - (i << 8);
        } 
        if (objectData[4] == 7 || objectData[4] == 4) {
          if (objectData[5] == 1 && objectData[10] == 0)
            objectData[10] = 1; 
          PlayerParam[0] = PlayerParam[0] - (i << 8);
        } 
        setRaidOnSize(objectData[2] + b3, b1);
        playerRaidOn(objectData[22]);
        bool2 = true;
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
    if (bool2) {
      view_dai_ride(objectData[2], objectData[3], objectData[4]);
    } else {
      view_dai(objectData[2], objectData[3], objectData[4]);
    } 
  }
  
  private void yogan2_sflag_move_ikeshita(int paramInt) {
    int i = objectData[2];
    int j = objectData[5];
    if (objectData[5] == 0) {
      if (objectData[2] + 160 < PlayerPosX() && Math.abs(objectData[3] - PlayerPosY() + 12) < 43)
        objectData[5] = objectData[5] + 1; 
    } else {
      objectData[5] = objectData[5] + 2;
      if (objectData[5] > 1152)
        objectData[5] = 1152; 
      objectData[2] = objectData[5] + objectData[8];
    } 
    if (objectData[2] + 96 > PlayerPosX() && Math.abs(objectData[3] - PlayerPosY() + 12) < 43) {
      playdamageset();
      if (objectData[5] > 0 && PlayerPosX() + 300 < objectData[2])
        objectData[2] = PlayerPosX() + 300; 
    } 
    byte b1 = 120;
    byte b2 = 32;
    int k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 96, objectData[3], i - 96, objectData[3], b1, b2);
    if (k >= 0 && k != 0 && k != 1)
      if (k == 2) {
        PlayerParam[0] = objectData[2] + b1 - 96 + 12 + 1 << 8;
        PlayerParam[10] = 0;
      } else if (k == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        PlayerParam[5] = 0;
        setHeadHit();
      }  
  }
  
  private void myogan_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 32;
    objectData[5] = this.cpuTimer / 2 % 180;
    if (objectData[5] == 0)
      objectData[10] = 0; 
    objectData[2] = objectData[8];
    objectData[3] = objectData[9] - 356 + (objectData[5] << 3);
    if (Math.abs(PlayerPosX() - objectData[2]) < 44)
      if (objectData[3] < objectData[9]) {
        if (objectData[3] - 240 < PlayerPosY() && objectData[3] > PlayerPosY() - 12)
          playdamageset(); 
      } else if (objectData[3] - 240 < PlayerPosY() && objectData[9] > PlayerPosY() - 12) {
        playdamageset();
      }  
    if (objectData[10] == 0) {
      if (objectData[3] > objectData[9])
        objectData[10] = 1; 
    } else {
      objectData[10] = objectData[10] + 1;
    } 
  }
  
  private void switch2_nflag_move_ikeshita(int paramInt) {
    byte b1 = 14;
    byte b2 = 7;
    if (switchflag[objectData[4]])
      b2 = 0; 
    switchflag[objectData[4]] = false;
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        switchflag[objectData[4]] = true;
        switchflag2[objectData[4]] = true;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        if (this.zoneNumber == 1 && this.stageNumber == 2)
          if (objectData[4] == 15) {
            tempWorldMapData[2][6] = 7;
          } else {
            tempWorldMapData[2][6] = 75;
          }  
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void shima_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 8;
    int i = 0;
    int j = 0;
    int k = 0;
    byte b3 = 0;
    byte b4 = 0;
    int m = objectData[2];
    objectData[11] = objectData[2];
    objectData[7] = objectData[3] + objectData[14];
    if (this.zoneNumber == 4)
      b2 = 16; 
    if (objectData[4] == 5) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b3 = 64;
    } else if (objectData[4] == 1) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b3 = -64;
    } 
    if (objectData[4] == 12) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b4 = 48;
    } else if (objectData[4] == 11) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b4 = -48;
    } else if (objectData[4] == 6) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b4 = 64;
    } else if (objectData[4] == 2) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b4 = -64;
    } else if (objectData[4] == 16) {
      objectData[5] = this.cpuTimer * 15 / 10 % 360;
      k = objectData[5];
      b4 = -32;
    } 
    if (objectData[4] == 7) {
      if (switchflag[128] && objectData[5] <= 0)
        objectData[5] = objectData[5] + 1; 
      if (objectData[5] > 0) {
        objectData[5] = objectData[5] + 1;
        if (objectData[5] > 90) {
          objectData[3] = objectData[3] - 2;
          if (objectData[3] < 368)
            objectData[3] = 368; 
        } 
      } 
      k = objectData[5];
    } else if (objectData[4] == 3 && objectData[5] != 0) {
      objectData[5] = objectData[5] + 1;
      if (objectData[5] > 40) {
        objectData[6] = objectData[6] + 80;
        objectData[18] = objectData[18] + objectData[6];
      } 
      k = objectData[5];
    } 
    i = dSin(k) * b4 / 100;
    if (objectData[4] == 3) {
      j = objectData[18] >> 8;
      objectData[3] = objectData[9] + j;
    } else if (objectData[4] == 7) {
      j = 0;
    } else {
      j = dSin(k) * b4 / 100;
      objectData[3] = objectData[9] + j;
      i = dSin(k) * b3 / 100;
      objectData[2] = objectData[8] + i;
    } 
    objectData[14] = 0;
    if (objectData[13] != 0)
      objectData[14] = 4; 
    objectData[13] = 0;
    int n = -1;
    boolean bool = false;
    m -= objectData[2];
    n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + objectData[14] + 32, objectData[11], objectData[7] + 32, b1, 8);
    if (n >= 0 && n == 0) {
      PlayerParam[0] = PlayerPosX() - m << 8;
      PlayerParam[1] = objectData[3] - b2 + objectData[14] << 8;
      if (objectData[4] == 3 && k == 0)
        objectData[5] = 1; 
      setRaidOnSize(objectData[2], b1);
      playerRaidOn(objectData[22]);
      bool = true;
      objectData[13] = 1;
    } 
    if (raidOn && raidObjectNum == objectData[20] && n != 0)
      raidOn = false; 
    n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + objectData[14] - 4, objectData[11], objectData[7] - 4, b1, 4);
    if (n >= 0 && n != 3) {
      PlayerParam[0] = PlayerPosX() - m << 8;
      PlayerParam[1] = objectData[3] - b2 + objectData[14] << 8;
      if (objectData[4] == 3 && k == 0)
        objectData[5] = 1; 
      setRaidOnSize(objectData[2], b1);
      playerRaidOn(objectData[22]);
      objectData[13] = 1;
      bool = true;
    } 
    if (raidOn && raidObjectNum == objectData[20] && n != 0)
      raidOn = false; 
  }
  
  private void dai2_nflag_move_ikeshita(int paramInt) {
    dai2_sflag_move_ikeshita(paramInt);
  }
  
  private void brkabe_sflag_move_ikeshita(int paramInt) {
    byte b2 = 8;
    byte b3 = 32;
    int i = -1;
    boolean bool = false;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    for (byte b1 = 0; b1 < 2; b1++) {
      if (objectData[10 + b1] != 1) {
        i = -1;
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 8 + (b1 << 4), objectData[3], objectData[6] - 8 + (b1 << 4), objectData[7], b2, b3);
        if (i >= 0)
          if (i == 1) {
            if (!PlayerJump && PlayerBall) {
              if (PlayerParam[10] >= 300) {
                PlayerParam[10] = PlayerParam[10] - 10;
                objectData[10 + b1] = 1;
                if (this.zoneNumber == 0) {
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 330, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 300, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 320, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 310, 400, objectData[4]);
                } else {
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 330, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 300, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 320, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 310, 400, 0);
                } 
              } else if (PlayerParam[10] <= -300) {
                PlayerParam[10] = PlayerParam[10] + 10;
                objectData[10 + b1] = 1;
                if (this.zoneNumber == 0) {
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 390, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 420, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 400, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 410, 400, objectData[4]);
                } else {
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 390, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 420, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 400, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 410, 400, 0);
                } 
              } else {
                PlayerParam[10] = 0;
              } 
            } else {
              PlayerParam[10] = 0;
            } 
            if (PlayerParam[10] == 0) {
              PlayerParam[0] = objectData[2] - b2 - 12 - 8 + (b1 << 4) << 8;
              if (KeyPress[4])
                playerPushSet(); 
            } 
          } else if (i == 2) {
            if (!PlayerJump && PlayerBall) {
              if (PlayerParam[10] >= 300) {
                PlayerParam[10] = PlayerParam[10] - 10;
                objectData[10 + b1] = 1;
                if (this.zoneNumber == 0) {
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 330, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 300, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 320, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 310, 400, objectData[4]);
                } else {
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 330, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 300, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 320, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 310, 400, 0);
                } 
              } else if (PlayerParam[10] <= -300) {
                PlayerParam[10] = PlayerParam[10] + 10;
                objectData[10 + b1] = 1;
                if (this.zoneNumber == 0) {
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 390, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 420, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 400, 400, objectData[4]);
                  ShotObj2(26, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 410, 400, objectData[4]);
                } else {
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16, 390, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 16, 420, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 32, 400, 400, 0);
                  ShotObj2(24, objectData[2] - 8 + (b1 << 4), objectData[3] - 16 - 16 + 48, 410, 400, 0);
                } 
              } else {
                PlayerParam[10] = 0;
              } 
            } else {
              PlayerParam[10] = 0;
            } 
            if (PlayerParam[10] == 0) {
              PlayerParam[0] = objectData[2] + b2 + 12 + 1 - 8 + (b1 << 4) << 8;
              if (KeyPress[3])
                playerPushSet(); 
            } 
          } else if (i == 3) {
            PlayerParam[1] = objectData[3] + b3 + 12 + 12 + 1 << 8;
            setHeadHit();
          }  
      } 
    } 
    if (objectData[10] == 1 && objectData[11] == 1)
      objectData[0] = 0; 
    if (bool)
      i = 0; 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void pedal_nflag_move_ikeshita(int paramInt) {
    byte b1 = 24;
    byte b2 = 8;
    int i = dSin(this.animeTimer % 360 * 3 + objectData[4] * 90) * 80 / 100;
    int j = dCos(this.animeTimer % 360 * 3 + objectData[4] * 90) * 80 / 100;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[4] <= 3) {
      objectData[2] = objectData[8] + i;
      objectData[3] = objectData[9] - j;
    } else {
      objectData[2] = objectData[8] + i;
      objectData[3] = objectData[9] + j;
    } 
    int k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    if (k >= 0 && k == 0) {
      PlayerParam[1] = objectData[3] - b2 << 8;
      PlayerParam[0] = PlayerParam[0] + (objectData[2] - objectData[6] << 8);
      setRaidOnSize(objectData[2], b1);
      playerRaidOn(objectData[22]);
    } 
    if (raidOn && raidObjectNum == objectData[20] && k != 0)
      raidOn = false; 
  }
  
  private void break2_nflag_move_ikeshita(int paramInt) {
    byte b2 = 8;
    byte b3 = 8;
    int i = 0;
    int j = 0;
    boolean bool = false;
    objectData[10] = this.cpuTimer;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (this.zoneNumber == 3)
      b3 = 7; 
    for (byte b1 = 0; b1 < 8; b1++) {
      if (objectData[15] != 0) {
        i = this.cpuTimer - objectData[16] - this.break2_nflag_ike_brockTimeTable[b1];
        if (i < 0)
          i = 0; 
      } 
      j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + (this.break2_nflag_ike_brockTable[b1] % 4 << 4) - 16 - 8, objectData[3] + (this.break2_nflag_ike_brockTable[b1] >> 2 << 4) + i * i / 5, objectData[6] + (this.break2_nflag_ike_brockTable[b1] % 4 << 4) - 16 - 8, objectData[7] + (this.break2_nflag_ike_brockTable[b1] >> 2 << 4) + i * i / 5, b2, b3);
      if (i != 0)
        j = -1; 
      if (j >= 0) {
        if (this.zoneNumber == 3 && (j == 1 || j == 2) && PlayerPosY() < objectData[3] + (b3 >> 1))
          j = 0; 
        if (j == 0) {
          bool = true;
          PlayerParam[1] = objectData[3] + (this.break2_nflag_ike_brockTable[b1] >> 2 << 4) + i * 5 - b3 << 8;
          setRaidOnSize(objectData[2] + (this.break2_nflag_ike_brockTable[b1] % 4 << 4) - 16 - 8, b2);
          playerRaidOn(objectData[22]);
          if (objectData[15] == 0)
            objectData[16] = this.cpuTimer; 
          objectData[15] = 1;
        } else if (j == 1) {
          PlayerParam[0] = objectData[2] + (this.break2_nflag_ike_brockTable[b1] % 4 << 4) - 16 - 8 - b2 - 12 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
        } else if (j == 2) {
          PlayerParam[0] = objectData[2] + (this.break2_nflag_ike_brockTable[b1] % 4 << 4) - 16 - 8 + b2 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } 
      } 
    } 
    if (bool)
      j = 0; 
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
  }
  
  private void step_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    int i = 0;
    int j = 0;
    int k = 0;
    boolean bool1 = false;
    int m = objectData[5];
    byte b3 = 0;
    boolean bool2 = false;
    byte b4 = 30;
    int n = -1;
    if (Math.abs(PlayerPosX() - objectData[2]) > 320) {
      objectData[5] = 0;
      objectData[18] = 0;
    } 
    if (objectData[18] > 0)
      objectData[18] = objectData[18] + 1; 
    for (b3 = 0; b3 < 4; b3++) {
      if (objectData[10 + b3] == 0)
        objectData[10 + b3] = objectData[3]; 
      if (objectData[19] == 1) {
        if (objectData[5] > 0 && b3 == 0 && (objectData[18] > b4 || objectData[18] < 0)) {
          objectData[18] = -1;
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 188)
            objectData[5] = 188; 
        } 
        if (objectData[5] > 60) {
          j = b3 << 5;
          i = (objectData[5] - 60 >> 2) * (b3 + 1);
          k = objectData[10 + b3];
        } else {
          j = b3 << 5;
          i = 0;
          k = objectData[10 + b3];
        } 
      } else if (objectData[19] == 0) {
        if (objectData[5] > 0 && b3 == 0 && (objectData[18] > b4 || objectData[18] < 0)) {
          objectData[18] = -1;
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 188)
            objectData[5] = 188; 
        } 
        if (objectData[5] > 60) {
          j = b3 << 5;
          i = (objectData[5] - 60 >> 2) * (4 - b3);
          k = objectData[10 + b3];
        } else {
          j = b3 << 5;
          i = 0;
          k = objectData[10 + b3];
        } 
      } 
      objectData[10 + b3] = objectData[3] + i;
      n = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + j, objectData[3] + i, objectData[2] + j, k, b1, b2);
      if (n >= 0)
        if (n == 0) {
          PlayerParam[1] = objectData[3] - b2 + i << 8;
          if (objectData[4] == 0 && objectData[5] == 0) {
            objectData[5] = objectData[5] + 1;
            objectData[18] = -1;
          } 
          setRaidOnSize(objectData[2] + j, b1);
          playerRaidOn(objectData[22]);
          raidObjectNumSub = b3;
          bool2 = true;
        } else if (n == 1) {
          PlayerParam[0] = objectData[2] - b1 + j - 12 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
        } else if (n == 2) {
          PlayerParam[0] = objectData[2] + b1 + j + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } else if (n == 3) {
          PlayerParam[1] = objectData[3] + b2 + i + 12 + 12 + 1 << 8;
          setHeadHit();
          if (objectData[4] == 2 && objectData[5] == 0) {
            objectData[5] = 60;
            if (objectData[18] == 0)
              objectData[18] = 1; 
          } 
        }  
    } 
    if (bool2) {
      n = 0;
    } else {
      n = -1;
    } 
    if (raidOn && raidObjectNum == objectData[20] && n != 0)
      raidOn = false; 
  }
  
  private void fun_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 8;
    int i = 0;
    int j = 0;
    boolean bool1 = false;
    byte b3 = 0;
    int k = 0;
    boolean bool2 = false;
    boolean bool3 = false;
    i = objectData[2] - PlayerPosX();
    j = Math.abs(objectData[3] - 64 + 16 - PlayerPosY() - 24);
    if (objectData[2] > PlayerPosX()) {
      bool1 = false;
    } else {
      bool1 = true;
    } 
    if (objectData[4] != 2) {
      if (this.animeTimer / 30 % 2 == 0)
        objectData[5] = this.animeTimer; 
    } else {
      objectData[5] = this.animeTimer;
    } 
    if (objectData[4] == 0) {
      if (this.animeTimer / 30 % 2 == 0)
        if (objectData[19] != 0) {
          if (j <= 64) {
            if (i >= -64 && i <= 64)
              while (b3 < 16 && i >= -64 && i <= 64) {
                PlayerParam[0] = PlayerParam[0] + 256;
                i = objectData[2] - PlayerPosX();
                b3++;
              }  
            if (i <= -64 && i >= -128) {
              k = 4;
              if (PlayerParam[10] / 2 > 1024)
                k = PlayerParam[10] / 2 >> 8; 
              while (b3 < k && i <= -64 && i >= -128) {
                PlayerParam[0] = PlayerParam[0] + 256;
                i = objectData[2] - PlayerPosX();
                b3++;
              } 
            } 
          } 
        } else if (j <= 64) {
          if (i <= 64 && i >= -64)
            while (b3 < 16 && i <= 64 && i >= -64) {
              PlayerParam[0] = PlayerParam[0] - 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            }  
          if (i >= 64 && i <= 128) {
            k = 4;
            if (PlayerParam[10] / 2 > 1024)
              k = PlayerParam[10] / 2 >> 8; 
            while (b3 < k && i >= 64 && i <= 128) {
              PlayerParam[0] = PlayerParam[0] - 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            } 
          } 
        }  
    } else if (objectData[4] == 1) {
      if (this.animeTimer / 30 % 2 == 0)
        if (objectData[19] == 0) {
          if (j <= 64) {
            if (i >= -64 && i <= 64)
              while (b3 < 16 && i >= -64 && i <= 64) {
                PlayerParam[0] = PlayerParam[0] + 256;
                i = objectData[2] - PlayerPosX();
                b3++;
              }  
            if (i <= -64 && i >= -128) {
              k = 4;
              if (PlayerParam[10] / 2 > 1024)
                k = PlayerParam[10] / 2 >> 8; 
              while (b3 < k && i <= -64 && i >= -128) {
                PlayerParam[0] = PlayerParam[0] + 256;
                i = objectData[2] - PlayerPosX();
                b3++;
              } 
            } 
          } 
        } else if (j <= 64) {
          if (i <= 64 && i >= -64)
            while (b3 < 16 && i <= 64 && i >= -64) {
              PlayerParam[0] = PlayerParam[0] - 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            }  
          if (i >= 64 && i <= 128) {
            k = 4;
            if (PlayerParam[10] / 2 > 1024)
              k = PlayerParam[10] / 2 >> 8; 
            while (b3 < k && i >= 64 && i <= 128) {
              PlayerParam[0] = PlayerParam[0] - 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            } 
          } 
        }  
    } else if (objectData[4] == 2) {
      if (objectData[19] != 0) {
        if (j <= 64) {
          if (i >= -64 && i <= 64)
            while (b3 < 16 && i >= -64 && i <= 64) {
              PlayerParam[0] = PlayerParam[0] + 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            }  
          if (i <= -64 && i >= -128) {
            k = 4;
            if (PlayerParam[10] / 2 > 1024)
              k = PlayerParam[10] / 2 >> 8; 
            while (b3 < k && i <= -64 && i >= -128) {
              PlayerParam[0] = PlayerParam[0] + 256;
              i = objectData[2] - PlayerPosX();
              b3++;
            } 
          } 
        } 
      } else if (objectData[19] == 0 && j <= 64) {
        if (i <= 64 && i >= -64)
          while (b3 < 16 && i <= 64 && i >= -64) {
            PlayerParam[0] = PlayerParam[0] - 256;
            i = objectData[2] - PlayerPosX();
            b3++;
          }  
        if (i >= 64 && i <= 128) {
          k = 4;
          if (PlayerParam[10] / 2 > 1024)
            k = PlayerParam[10] / 2 >> 8; 
          while (b3 < k && i >= 64 && i <= 128) {
            PlayerParam[0] = PlayerParam[0] - 256;
            i = objectData[2] - PlayerPosX();
            b3++;
          } 
        } 
      } 
    } 
    rcol3();
    rcol2();
    lcol3();
    lcol2();
  }
  
  private void belt_nflag_move_ikeshita(int paramInt) {
    char c = '8';
    byte b = 16;
    byte b1 = 0;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[4] == 33 || objectData[4] == 225) {
      c = '8';
    } else {
      c = '';
    } 
    if (objectData[4] == 32 || objectData[4] == 33) {
      b1 = 2;
    } else if (objectData[4] == 224 || objectData[4] == 225) {
      b1 = -2;
    } else {
      b1 = 3;
    } 
    int i = 0;
    if (PlayerJump && olddir != 0)
      i += dSin(olddir) * 20 / 100; 
    int j = ObjectColChk(PlayerPosX() + i, PlayerPosY() - 12, ploldpos[0] + i, ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], c, b);
    if (j >= 0)
      if (j == 0) {
        PlayerParam[1] = objectData[3] - b << 8;
        setRaidOnSize(objectData[2], c);
        playerRaidOn(objectData[22]);
        if (Math.abs(objectData[2] - PlayerPosX()) < c - 12)
          PlayerParam[0] = PlayerParam[0] + (b1 << 8); 
      } else if (j == 1) {
        PlayerParam[0] = objectData[2] - c - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (j == 2) {
        PlayerParam[0] = objectData[2] + c + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (j == 3) {
        PlayerParam[1] = objectData[3] + b + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
  }
  
  private void pata_nflag_move_ikeshita(int paramInt) {
    if (objectData[4] != 1 && objectData[4] != 2) {
      byte b1 = 16;
      byte b2 = 7;
      int i = -1;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      byte b3 = 4;
      if (objectData[4] >= 144) {
        if (objectData[4] == 152) {
          objectData[14] = objectData[4];
        } else if (objectData[4] > 152) {
          objectData[14] = 152 - objectData[4] - 152;
        } else {
          objectData[14] = 152 - objectData[4] + 16 - 152;
        } 
        objectData[13] = this.cpuTimer / b3 + objectData[14];
        objectData[13] = objectData[13] % 32;
      } else {
        if (objectData[4] == 128) {
          objectData[14] = objectData[4];
        } else if (objectData[4] > 128) {
          objectData[14] = 128 - objectData[4] - 128;
        } 
        objectData[13] = this.cpuTimer / b3 + objectData[14];
        objectData[13] = objectData[13] % 20;
      } 
      if (objectData[13] == 0 || objectData[13] > 7) {
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
        if (i >= 0)
          if (i == 0) {
            PlayerParam[1] = objectData[3] - b2 << 8;
            setRaidOnSize(objectData[2], b1);
            playerRaidOn(objectData[22]);
          } else if (i == 1) {
            PlayerParam[0] = objectData[2] - b1 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4])
              playerPushSet(); 
          } else if (i == 2) {
            PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3])
              playerPushSet(); 
          } else if (i == 3) {
            PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
            setHeadHit();
          }  
      } 
      if (raidOn && raidObjectNum == objectData[20] && i != 0)
        raidOn = false; 
    } else {
      byte b1 = 64;
      byte b2 = 12;
      boolean bool = false;
      if (objectData[4] == 1 || objectData[4] == 2)
        if (this.cpuTimer / 30 / 4 % 2 == 0) {
          int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
          if (0 == i) {
            PlayerParam[1] = objectData[3] - b2 << 8;
            playerRaidOn(objectData[22]);
          } else if (3 == i) {
            PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
            setHeadHit();
          } 
          if (raidOn && raidObjectNum == objectData[20] && 0 != i)
            raidOn = false; 
        } else if (raidOn && raidObjectNum == objectData[20]) {
          raidOn = false;
        }  
    } 
  }
  
  private void fire6_nflag_move_ikeshita(int paramInt) {
    int i = -1;
    byte b1 = 0;
    byte b = 0;
    int j = 0;
    int k = 5;
    byte b3 = 12;
    if (!PlayerBall)
      b3 = 24; 
    objectData[5] = objectData[5] + 1;
    k = objectData[5] / 4;
    if (k % 30 < 6) {
      k %= 30;
    } else if (k % 30 > 20 && k % 30 <= 25) {
      k = 5 - (k - 20) % 30;
    } else if (k % 30 >= 25) {
      k = 0;
    } else {
      k = 5;
    } 
    if (objectData[19] != 2) {
      b1 = 0;
      b = 49;
    } else {
      b1 = 0;
      b = -42;
    } 
    for (byte b2 = 0; b2 < k; b2++) {
      j += this.fire6_nflag_ike_sizeTable2[4 - b2] - this.fire6_nflag_ike_posTable[4 - b2];
      if (objectData[19] != 2) {
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + b1, objectData[3] + b - j, objectData[2] + b1, objectData[3] + b - j, this.fire6_nflag_ike_sizeTable[4 - b2] / 2 - 4, this.fire6_nflag_ike_sizeTable[4 - b2] / 2 - 4);
      } else {
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + b1, objectData[3] + b + j, objectData[2] + b1, objectData[3] + b + j, this.fire6_nflag_ike_sizeTable[4 - b2] / 2 - 4, this.fire6_nflag_ike_sizeTable[4 - b2] / 2 - 4);
      } 
      if (i >= 0)
        playdamageset(); 
    } 
  }
  
  private void bryuka_nflag_move_ikeshita(int paramInt) {
    byte b1 = 14;
    byte b2 = 14;
    boolean bool = false;
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0)
      if (i == 0) {
        if (PlayerBall) {
          objectData[0] = 0;
          PlayerJump = true;
          PlayerDamage = false;
          PlayerBall = true;
          PlayerParam[5] = -1280;
          if (comboScore == 0) {
            comboScore = 100;
          } else if (comboScore == 100) {
            comboScore = 200;
          } else if (comboScore == 200) {
            comboScore = 500;
          } else if (comboScore == 500) {
            comboScore = 1000;
          } 
          addScoreCount(comboScore);
          ShotScore(objectData[2], objectData[3], comboScore);
          ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 330, 400, 0);
          ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 300, 400, 1);
          ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 390, 400, 2);
          ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 420, 400, 3);
          PlayerParam[1] = objectData[3] - b2 << 8;
        } else {
          PlayerParam[1] = objectData[3] - b2 << 8;
          if (olddir != 0) {
            PlayerParam[0] = PlayerPosX() + dSin(olddir + 90) * 24 / 100 << 8;
            olddir = 0;
          } 
          setRaidOnSize(objectData[2], b1);
          playerRaidOn(objectData[22]);
        } 
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void mawaru_nflag_move_ikeshita(int paramInt) {
    byte b1 = 45;
    byte b2 = 45;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    int i = 0;
    i = objectData[5] - this.cpuTimer;
    i = Math.abs(i);
    if (switchflag[objectData[4]]) {
      if (objectData[16] != 1) {
        objectData[16] = 1;
        if (objectData[17] == 1) {
          objectData[17] = 0;
        } else {
          objectData[17] = 1;
        } 
      } 
    } else {
      objectData[16] = 0;
    } 
    if (i != 1) {
      if (objectData[17] == 1) {
        objectData[13] = objectData[13] - i;
      } else {
        objectData[13] = objectData[13] + i;
      } 
    } else if (objectData[17] == 1) {
      objectData[13] = objectData[13] - 1;
    } else {
      objectData[13] = objectData[13] + 1;
    } 
    if (objectData[13] < 0)
      objectData[13] = 80 + objectData[13] % 80; 
    if (objectData[13] > 79)
      objectData[13] = objectData[13] % 80; 
    objectData[15] = objectData[13] / 10;
    objectData[5] = this.cpuTimer;
    objectData[10] = this.mawaru_nflag_ike_posx[objectData[15]];
    objectData[11] = this.mawaru_nflag_ike_posy[objectData[15]];
    int j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 1, objectData[3] - 1, objectData[6] - 1, objectData[7] - 1, b1, b2);
    if (j >= 0) {
      if (objectData[14] == 0)
        if (j == 0) {
          PlayerParam[1] = objectData[3] - b2 - 1 << 8;
          setRaidOnSize(objectData[2] - 1, b1);
          playerRaidOn(objectData[22]);
        } else if (j == 1) {
          PlayerParam[0] = objectData[2] - b1 - 12 - 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
          if (objectData[14] == 0 && objectData[15] == 1) {
            objectData[14] = 1;
            objectData[18] = 1;
          } 
        } else if (j == 2) {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 - 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
          if (objectData[14] == 0 && objectData[15] == 5) {
            objectData[14] = 1;
            objectData[18] = 5;
          } 
        } else if (j == 3) {
          PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 - 1 << 8;
        }  
    } else if (objectData[14] == 2) {
      objectData[14] = 0;
    } 
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
    if (objectData[14] == 1) {
      int[] arrayOfInt1 = { 0, -80, -100, -80 };
      int[] arrayOfInt2 = { -100, -80, 0, 80 };
      PlayerJump = true;
      PlayerDamage = false;
      PlayerBall = true;
      int k = arrayOfInt1[objectData[15] % 4] * 30 / 100;
      int m = arrayOfInt2[objectData[15] % 4] * 30 / 100;
      if (objectData[15] >= 4) {
        k *= -1;
        m *= -1;
      } 
      PlayerParam[0] = objectData[2] + k << 8;
      PlayerParam[1] = objectData[3] + m + 12 << 8;
      if (objectData[15] == 4 && objectData[18] != 4) {
        objectData[14] = 2;
        PlayerParam[5] = 4096;
      } 
      if (objectData[15] == 5 && objectData[18] != 5) {
        objectData[14] = 2;
        PlayerParam[10] = 2048;
        PlayerJump = false;
        PlayerBall = false;
      } 
    } 
  }
  
  private void yukai_nflag_move_ikeshita(int paramInt) {
    byte b1 = 96;
    byte b2 = 24;
    boolean bool = false;
    int i = 0;
    byte b3 = 48;
    byte b4 = 24;
    byte b5 = 96;
    int j = this.cpuTimer % (b5 + b3 + b5 + b3);
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    i = objectData[2];
    if (j < b5) {
      objectData[2] = objectData[8] + j;
    } else if (j < b5 + b3) {
      objectData[2] = objectData[8] + b5;
      objectData[3] = objectData[9] + j - b5;
    } else if (j < b5 + b3 + b5) {
      objectData[2] = objectData[8] + b5 - j - b5 - b3;
      objectData[3] = objectData[9] + b3 - 24 * (j - b5 - b3) / b5;
    } else if (j < b5 + b3 + b5 + b4) {
      objectData[2] = objectData[8];
      objectData[3] = objectData[9] + b3 - 24 - j - b5 - b3 - b5;
    } 
    i -= objectData[2];
    int k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    if (k >= 0)
      if (k == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        PlayerParam[0] = PlayerPosX() - i << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } else if (k == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (k == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (k == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && k != 0)
      raidOn = false; 
  }
  
  private void door_nflag_move_ikeshita(int paramInt) {
    byte b1 = 4;
    byte b2 = 32;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    objectData[15] = this.animeTimer / 5;
    objectData[5] = this.animeTimer / 10;
    if (i >= 0 && objectData[10] == 0)
      if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      }  
    if (objectData[19] == 0) {
      if (PlayerPosX() - 12 > objectData[2]) {
        objectData[10] = objectData[10] - 1;
        if (objectData[10] < 0)
          objectData[10] = 0; 
      } else if (PlayerPosX() > objectData[2] - 24 && PlayerPosY() - 12 < objectData[3] + 32 && PlayerPosY() - 12 > objectData[3] - 32) {
        objectData[10] = objectData[10] + 1;
        if (objectData[10] > 4)
          objectData[10] = 4; 
      } else {
        objectData[10] = objectData[10] - 1;
        if (objectData[10] < 0)
          objectData[10] = 0; 
      } 
    } else if (PlayerPosX() + 12 < objectData[2]) {
      objectData[10] = objectData[10] - 1;
      if (objectData[10] < 0)
        objectData[10] = 0; 
    } else if (PlayerPosX() < objectData[2] + 24 && PlayerPosY() - 12 <= objectData[3] + 32 && PlayerPosY() - 12 >= objectData[3] - 32) {
      objectData[10] = objectData[10] + 1;
      if (objectData[10] > 4)
        objectData[10] = 4; 
    } else {
      objectData[10] = objectData[10] - 1;
      if (objectData[10] < 0)
        objectData[10] = 0; 
    } 
  }
  
  private void yukae_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    boolean bool = false;
    int i = 16;
    int j = -1;
    objectData[5] = this.cpuTimer / 2 % 128;
    int k = (objectData[5] - objectData[4] / 2 + 256) % 128;
    if (k < 128)
      if (k < 16) {
        i = k;
      } else if (k > 64 && k < 80) {
        i = 80 - k;
      } else if (k >= 80) {
        i = -1;
      }  
    if (i > 0) {
      b1 = 16;
      if (i <= 8 && i > 4) {
        b1 = 8;
      } else if (i <= 4) {
        b1 = 0;
      } 
      if (b1 > 0) {
        j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + 8, objectData[2], objectData[3] + 8, b1, b2);
        if (j >= 0)
          if (j == 0) {
            PlayerParam[1] = objectData[3] + 8 - b2 << 8;
            setRaidOnSize(objectData[2], b1);
            playerRaidOn(objectData[22]);
          } else if (j == 1) {
            PlayerParam[0] = objectData[2] - b1 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4])
              playerPushSet(); 
          } else if (j == 2) {
            PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3])
              playerPushSet(); 
          }  
      } else {
        j = -1;
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
  }
  
  private void dai4_nflag_move_ikeshita(int paramInt) {
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    int i = 0;
    byte b1 = 0;
    char c = '>';
    byte b2 = 11;
    int j = 0;
    if (objectData[4] < 128) {
      c = '\034';
      b2 = 32;
    } 
    if (objectData[4] == 203) {
      c = '';
      b2 = 64;
      j = objectData[2];
      if (switchflag2[11] && objectData[18] == 0) {
        objectData[18] = 1;
        objectData[5] = 256;
        objectData[2] = 2432;
        objectData[3] = 320;
      } 
      objectData[18] = 1;
      if (objectData[5] == 0) {
        if (switchflag2[11]) {
          objectData[5] = 1;
        } else {
          objectData[2] = objectData[8];
          objectData[3] = objectData[9];
        } 
      } else {
        if (this.cpuTimer % 2 == 0)
          objectData[5] = objectData[5] + 1; 
        if (objectData[5] > 256)
          objectData[5] = 256; 
        objectData[2] = objectData[8] - objectData[5];
        objectData[3] = objectData[9] + objectData[5] / 2;
      } 
      j -= objectData[2];
    } else if (objectData[4] == 52) {
      b1 = 6;
      i = this.animeTimer % (30 + b1 * 2);
      if (i < 15) {
        objectData[3] = objectData[9];
      } else if (i < 15 + b1) {
        objectData[3] = objectData[9] + (i - 15) * 16;
      } else if (i < 15 + b1 + 15) {
        objectData[3] = objectData[9] + b1 * 16;
      } else if (i < 15 + b1 + 15 + b1) {
        objectData[3] = objectData[9] + b1 * 16 - (i - 15 + b1 + 15) * 16;
      } 
    } else if (objectData[4] == 36) {
      b1 = 4;
      i = this.animeTimer % (30 + b1 * 2);
      if (i < 15) {
        objectData[3] = objectData[9];
      } else if (i < 15 + b1) {
        objectData[3] = objectData[9] + (i - 15) * 16;
      } else if (i < 15 + b1 + 15) {
        objectData[3] = objectData[9] + b1 * 16;
      } else if (i < 15 + b1 + 15 + b1) {
        objectData[3] = objectData[9] + b1 * 16 - (i - 15 + b1 + 15) * 16;
      } 
    } else if (objectData[4] == 19) {
      b1 = 4;
      i = this.animeTimer % (30 + b1 * 2);
      if (i < 15) {
        objectData[3] = objectData[9] + 8;
        objectData[3] = objectData[3] - 16;
      } else if (i < 15 + b1) {
        objectData[3] = objectData[9] + (i - 15) * 16 + 8;
        objectData[3] = objectData[3] - 16;
      } else if (i < 15 + b1 + 15) {
        objectData[3] = objectData[9] + b1 * 16 + 8;
        objectData[3] = objectData[3] - 16;
      } else if (i < 15 + b1 + 15 + b1) {
        objectData[3] = objectData[9] + b1 * 16 - (i - 15 + b1 + 15) * 16 + 8;
        objectData[3] = objectData[3] - 16;
      } 
    } else if (objectData[4] >= 128) {
      j = objectData[2];
      if (switchflag[objectData[4] - 128]) {
        if (objectData[5] == 0) {
          objectData[10] = 0;
          objectData[11] = this.cpuTimer;
        } 
        objectData[5] = 1;
      } 
      if (objectData[5] != 0) {
        objectData[10] = this.cpuTimer - objectData[11];
        if (objectData[19] == 0) {
          if (objectData[10] < 128) {
            objectData[2] = objectData[8] - objectData[10];
          } else if (objectData[10] > 178) {
            objectData[2] = objectData[8] - 128 - objectData[10] - 128 - 50;
          } 
        } else if (objectData[10] < 128) {
          objectData[2] = objectData[8] + objectData[10] - 128;
        } else if (objectData[10] > 178) {
          objectData[2] = objectData[8] + 128 - objectData[10] - 128 - 50 - 128;
        } 
        if (objectData[10] > 306)
          objectData[5] = 0; 
      } 
      if (objectData[5] == 0)
        if (objectData[19] == 0) {
          objectData[2] = objectData[8];
        } else {
          objectData[2] = objectData[8] - 128;
        }  
      j -= objectData[2];
    } 
    boolean bool = false;
    int k = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], c, b2);
    if ((this.stageNumber != 3 || objectData[4] != 64) && k >= 0)
      if (k == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], c);
        playerRaidOn(objectData[22]);
        PlayerParam[0] = PlayerParam[0] - (j << 8);
      } else if (k == 1) {
        PlayerParam[0] = objectData[2] - c - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (k == 2) {
        PlayerParam[0] = objectData[2] + c + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (k == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && k != 0)
      raidOn = false; 
  }
  
  private void ele_nflag_move_ikeshita(int paramInt) {
    int i = 0;
    byte b = 1;
    int j = -1;
    byte b1 = 12;
    if (!PlayerBall)
      b1 = 24; 
    i = objectData[4] * 2;
    if (this.animeTimer % (i + 5) < i) {
      objectData[5] = 0;
    } else {
      objectData[5] = (this.animeTimer % (i + 5) - i) / 1 + 1;
    } 
    if (objectData[19] == 2)
      b = -1; 
    if (objectData[5] >= 2)
      for (byte b2 = 0; b2 <= 1; b2++) {
        if (this.ele_nflag_ike_anime[objectData[5] - 2][b2] != 0) {
          j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + 24 + b2 * 32 - 4, objectData[3] - 4 * b, objectData[2] + 24 + b2 * 32 - 4, objectData[3] - 4 * b, 16, 8);
          if (j >= 0) {
            playdamageset();
          } else if (24 >= Math.abs(PlayerPosX() - objectData[2] + 24 + b2 * 32) && 8 + b1 >= Math.abs(PlayerPosY() - b1 - objectData[3] - 4 * b)) {
            playdamageset();
          } 
          j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 24 - b2 * 32 + 4, objectData[3] - 4 * b, objectData[2] - 24 - b2 * 32 + 4, objectData[3] - 4 * b, 16, 8);
          if (j >= 0) {
            playdamageset();
          } else if (24 >= Math.abs(PlayerPosX() - objectData[2] - 24 - b2 * 32) && 8 + b1 >= Math.abs(PlayerPosY() - b1 - objectData[3] - 4 * b)) {
            playdamageset();
          } 
        } 
      }  
  }
  
  private void beltc_nflag_move_ikeshita(int paramInt) {
    byte b1 = 0;
    boolean bool = false;
    int i = -1;
    int j = 0;
    int[] arrayOfInt = new int[(this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 + 1];
    arrayOfInt[0] = 0;
    byte b2;
    for (b2 = 0; b2 < this.beltc_nflag_ike_defx.length; b2++) {
      if (objectData[9] == this.beltc_nflag_ike_defy[b2] && objectData[8] == this.beltc_nflag_ike_defx[b2])
        b1 = b2; 
    } 
    for (b2 = 0; b2 < (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b2++) {
      for (byte b = 0; b < 2; b++) {
        this.beltc_nflag_ike_startPos[b] = 0;
        this.beltc_nflag_ike_endPos[b] = 0;
      } 
      this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b2 * 2 + 0];
      this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b2 * 2 + 1];
      this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b2 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
      this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b2 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
      if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
        j += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
      } else {
        j += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
      } 
      arrayOfInt[b2 + 1] = j;
    } 
    int k = j / 69;
    int m = 0;
    byte b3 = 0;
    if (objectData[18] == 0)
      for (b2 = 0; b2 < (kassya_x[b1]).length; b2++) {
        kassya_x[b1][b2] = 0;
        kassya_y[b1][b2] = 0;
      }  
    objectData[18] = 1;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    for (b2 = 0; b2 < k; b2++) {
      n = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
      m = (b2 * 69 + this.cpuTimer) % j;
      byte b4;
      for (b4 = 0; b4 < (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b4++) {
        if (m < arrayOfInt[b4 + 1]) {
          i4 = m - arrayOfInt[b4 + 1];
          for (byte b = 0; b < 2; b++) {
            this.beltc_nflag_ike_startPos[b] = 0;
            this.beltc_nflag_ike_endPos[b] = 0;
          } 
          this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b4 * 2 + 0];
          this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b4 * 2 + 1];
          this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b4 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
          this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b4 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
          if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
            n = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * i4 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
            i1 = this.beltc_nflag_ike_endPos[1] + i4 * (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
            break;
          } 
          n = this.beltc_nflag_ike_endPos[0] + i4 * (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
          i1 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * i4 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
          break;
        } 
      } 
      if (kassya_x[b1][b2] == 0 && kassya_y[b1][b2] == 0) {
        kassya_x[b1][b2] = n;
        kassya_y[b1][b2] = i1;
      } 
      i2 = kassya_x[b1][b2];
      i3 = kassya_y[b1][b2];
      kassya_x[b1][b2] = n;
      kassya_y[b1][b2] = i1;
      for (b4 = 0; b4 < (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b4++) {
        if (m < arrayOfInt[b4 + 1]) {
          b3 = b4;
          break;
        } 
      } 
      byte b5 = 16;
      byte b6 = 7;
      if (!bool) {
        i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, n, i1, i2, i3, b5, b6);
        if (i >= 0 && b3 != 1 && b3 != 2 && i == 0) {
          PlayerParam[1] = i1 - b6 << 8;
          PlayerParam[0] = PlayerParam[0] - (i2 - n << 8);
          raidObjectNumSub = b2;
          setRaidOnSize(n, b5 + 4);
          playerRaidOn(objectData[22]);
          bool = true;
        } 
      } 
    } 
    if (bool)
      i = 0; 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void noko_nflag_move_ikeshita(int paramInt) {
    int i = 0;
    byte b1 = 30;
    byte b2 = 30;
    int j = -1;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    objectData[15] = this.animeTimer & 0x1;
    if (objectData[4] == 1) {
      objectData[10] = 1;
      if (this.cpuTimer / 2 % 192 < 96) {
        objectData[2] = objectData[8] - this.cpuTimer / 2 % 192;
      } else {
        objectData[2] = objectData[8] - 96 - this.cpuTimer / 2 % 192 - 96;
      } 
    } else if (objectData[4] == 2) {
      objectData[10] = 1;
      if (this.cpuTimer / 2 % 112 < 56) {
        objectData[3] = objectData[9] - this.cpuTimer / 2 % 112;
      } else {
        objectData[3] = objectData[9] - 56 - this.cpuTimer / 2 % 112 - 56;
      } 
    } else if (objectData[4] == 3) {
      if (objectData[10] == 0) {
        if (objectData[2] > PlayerPosX() - 180 && objectData[2] < PlayerPosX() - 150) {
          objectData[5] = this.cpuTimer;
          objectData[10] = 1;
        } 
      } else if (objectData[10] == 1) {
        objectData[2] = objectData[8] + (this.cpuTimer - objectData[5]) * 3;
        i = objectData[2] - PlayerPosX();
        if (i > 200) {
          objectData[5] = 0;
          objectData[10] = 0;
          objectData[2] = objectData[8];
        } 
      } 
    } 
    byte b3 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b3 = 20; 
    if (objectData[10] != 0) {
      j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
      if (j >= 0) {
        playdamageset();
      } else if (Math.abs(objectData[2] - PlayerPosX()) < 12 + b1 && Math.abs(objectData[3] - PlayerPosY() - b3) < b3 + b2) {
        playdamageset();
      } 
    } 
  }
  
  private void save_sflag_move_ikeshita(int paramInt) {
    byte b1 = 8;
    byte b2 = 32;
    boolean bool = false;
    if (objectData[5] == 0 && plsaveX == objectData[2] && plsaveY == objectData[3])
      objectData[5] = 32; 
    if (objectData[5] > 0) {
      objectData[5] = objectData[5] + 1;
      if (objectData[5] > 32)
        objectData[5] = 32; 
    } 
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] - 12, objectData[2], objectData[3] - 12, b1, b2);
    if (i >= 0 && objectData[5] == 0) {
      objectData[5] = 1;
      plsaveX = objectData[2];
      plsaveY = objectData[3];
      plsaveTime = timecount;
      plsaveTime2 = timecount2;
    } 
  }
  
  private void kageb_nflag_move_ikeshita(int paramInt) {
    byte b1 = 8;
    byte b2 = 32;
    boolean bool = false;
    if (objectData[4] < 16) {
      int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
      if (i >= 0)
        if (i == 1) {
          PlayerParam[0] = objectData[2] - b1 - 12 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
        } else if (i == 2) {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } else if (i == 3) {
          PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
          setHeadHit();
        }  
      if (raidOn && raidObjectNum == objectData[20] && i != 0)
        raidOn = false; 
    } 
  }
  
  private void item_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    boolean bool = false;
    if (objectData[4] >= 10)
      return; 
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[12] == 1) {
      int j = 0;
      j = (this.animeTimer - objectData[14]) * 4;
      objectData[14] = this.animeTimer;
      if (j > 0)
        for (int k = j; k >= 0; k--) {
          if (objectData[3] < objectData[7] + 1000)
            objectData[3] = objectData[3] + 2; 
          if (blockColChk_Enemy(objectData[2], objectData[3] + b1) && objectData[3] > objectData[7] - 1000) {
            objectData[3] = objectData[3] - 2;
            objectData[12] = 0;
            break;
          } 
          if (objectData[3] < 0)
            objectData[3] = 0; 
        }  
    } 
    int i = ObjectColChk2(objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    if (objectData[4] == 0)
      i = -1; 
    if (objectData[5] != 0) {
      objectData[7] = (this.animeTimer - objectData[13]) * 2;
      if (objectData[7] > 60)
        objectData[7] = 60; 
    } 
    if (i >= 0)
      if ((PlayerBall && i != 3) || (!PlayerJump && PlayerBall && i == 3)) {
        PlayerParam[5] = -768;
        SetObj2(1, objectData[2], objectData[3], 0, 0, 0, 0);
        objectData[5] = objectData[4];
        objectData[13] = this.animeTimer;
        getItem(objectData[4]);
        objectData[4] = 0;
      } else if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        if (PlayerParam[5] < 0)
          PlayerParam[5] = PlayerParam[5] * -1; 
        if (objectData[12] == 0) {
          objectData[3] = objectData[3] - 5;
          objectData[14] = this.animeTimer;
        } 
        objectData[12] = 1;
        if (!PlayerJump)
          if (PlayerPosX() < objectData[2]) {
            PlayerParam[0] = PlayerPosX() - 5 << 8;
          } else {
            PlayerParam[0] = PlayerPosX() + 5 << 8;
          }  
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void item_sflag_move_ikeshita(int paramInt) {
    item_nflag_move_ikeshita(paramInt);
  }
  
  private void gole_nflag_move_ikeshita(int paramInt) {
    byte b = 3;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[5] == 1) {
      objectData[10] = objectData[10] + 1;
      if (objectData[10] > 129) {
        objectData[10] = 129;
        objectData[5] = 2;
        initGoleStart();
      } 
    } else if (objectData[5] == 0 && PlayerPosX() > objectData[2] - b && objectData[3] + 48 > PlayerPosY()) {
      objectData[5] = 1;
      objectData[10] = 0;
      this.gole_on = true;
    } 
  }
  
  private void bten_nflag_move_ikeshita(int paramInt) {
    byte b1 = 12;
    byte b2 = 12;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[5] == 0) {
      int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
      if (i >= 0) {
        objectData[5] = 1;
        objectData[11] = 0;
        addScoreCount(this.bten_nflag_ike_score[objectData[4]]);
      } 
    } else if (objectData[5] == 1) {
      objectData[11] = objectData[11] + 1;
      if (objectData[11] > 120)
        objectData[5] = 2; 
    } 
  }
  
  private void bten_sflag_move_ikeshita(int paramInt) {
    bten_nflag_move_ikeshita(paramInt);
  }
  
  private void bigring_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 32;
    if (ringcount >= 50) {
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[5] == 0) {
        int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
        if (i >= 0) {
          objectData[5] = 1;
          objectData[0] = -1;
          objectData[11] = this.animeTimer;
        } 
      } else {
        objectData[10] = (this.animeTimer - objectData[11]) / 1;
        if (objectData[10] > 5)
          objectData[10] = 5; 
      } 
    } 
  }
  
  private void scoli_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0 && i != 0)
      if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4]);
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3]);
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void shooter_nflag_move_ikeshita(int paramInt) {
    byte b1 = 27;
    byte b2 = 44;
    int i = -1;
    b1 = 5;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[5] == 0) {
      i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
      if (i >= 0)
        if (i == 0) {
          PlayerParam[1] = objectData[3] - b2 << 8;
          setRaidOnSize(objectData[2], b1);
          playerRaidOn(objectData[22]);
        } else if (i == 1) {
          PlayerParam[0] = objectData[2] - b1 - 12 << 8;
          PlayerParam[10] = 0;
          if (objectData[19] == 0) {
            objectData[5] = 1;
            objectData[10] = 0;
          } else if (KeyPress[4]) {
            playerPushSet();
          } 
        } else if (i == 2) {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (objectData[19] != 0) {
            objectData[5] = 1;
            objectData[10] = 0;
          } else if (KeyPress[3]) {
            playerPushSet();
          } 
        } else if (i == 3) {
          PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
          setHeadHit();
        }  
    } 
    if (objectData[5] == 1) {
      PlayerJump = true;
      PlayerDamage = false;
      PlayerBall = true;
      PlayerNoCol = true;
      PlayerNoCtrl = true;
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
      objectData[10] = objectData[10] + 1;
      PlayerParam[0] = objectData[2] << 8;
      PlayerParam[1] = objectData[3] + 12 - this.shooter_nflag_ike_pos[objectData[10] / 5 % 12] << 8;
      if (objectData[10] / 5 % 12 == 11) {
        objectData[5] = 2;
        if (objectData[4] == 0 || objectData[4] == 6)
          objectData[5] = 4; 
        objectData[10] = 0;
        objectData[11] = 0;
        objectData[12] = 0;
        PlayerParam[3] = 0;
        PlayerParam[5] = 0;
        PlayerParam[10] = 0;
      } 
    } 
    if (objectData[5] == 4)
      if (objectData[4] == 0) {
        PlayerParam[1] = PlayerPosY() + 10 << 8;
        if (objectData[3] > PlayerPosY())
          objectData[5] = 2; 
      } else {
        PlayerParam[1] = PlayerPosY() - 10 << 8;
        if (158 < PlayerPosY())
          objectData[5] = 2; 
      }  
    if (objectData[5] == 2) {
      PlayerJump = true;
      PlayerDamage = false;
      PlayerBall = true;
      PlayerNoCol = true;
      PlayerNoCtrl = true;
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
      objectData[11] = 0;
      objectData[12] = 0;
      if (objectData[10] * 2 + 1 < (this.shooter_nflag_ike_objectPos[objectData[4]]).length) {
        if (PlayerPosX() > this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2]) {
          objectData[11] = -1;
        } else if (PlayerPosX() < this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2]) {
          objectData[11] = 1;
        } 
        if (PlayerPosY() > this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2 + 1]) {
          objectData[12] = -1;
        } else if (PlayerPosY() < this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2 + 1]) {
          objectData[12] = 1;
        } 
        objectData[10] = objectData[10] + 1;
        objectData[5] = 3;
      } else {
        objectData[5] = 0;
        PlayerNoCol = false;
        PlayerNoCtrl = false;
      } 
    } 
    if (objectData[5] == 3) {
      PlayerParam[11] = PlayerParam[11] + plmaxspd;
      PlayerJump = true;
      PlayerDamage = false;
      PlayerBall = true;
      PlayerNoCol = true;
      PlayerNoCtrl = true;
      if (objectData[11] != 0) {
        PlayerParam[0] = PlayerPosX() + objectData[11] * 10 << 8;
        if (objectData[11] == -1 && PlayerPosX() < this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2]) {
          PlayerParam[0] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2] << 8;
          objectData[11] = 0;
        } 
        if (objectData[11] == 1 && PlayerPosX() > this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2]) {
          PlayerParam[0] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2] << 8;
          objectData[11] = 0;
        } 
      } 
      if (objectData[12] != 0) {
        PlayerParam[1] = PlayerPosY() + objectData[12] * 10 << 8;
        if (objectData[12] == -1 && PlayerPosY() < this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1]) {
          PlayerParam[1] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1] << 8;
          objectData[12] = 0;
        } 
        if (objectData[12] == 1 && PlayerPosY() > this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1]) {
          PlayerParam[1] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1] << 8;
          objectData[12] = 0;
        } 
      } 
      if (objectData[11] == 0 && objectData[12] == 0) {
        PlayerParam[0] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2] << 8;
        PlayerParam[1] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1] << 8;
        objectData[5] = 2;
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void masin_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 28;
    byte b3 = 4;
    if (objectData[4] == 1) {
      b1 = 12;
      b2 = 8;
      b3 = 0;
    } 
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + b3, objectData[6], objectData[7] + b3, b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 + b3 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        if (objectData[4] == 1 && objectData[5] == 0) {
          this.gole_on = true;
          objectData[5] = 1;
          objectData[3] = objectData[3] + 8;
          objectData[10] = this.cpuTimer;
          this.m_bScrollLock = 2;
        } 
      } else if (!this.limitBreak) {
        if (i == 1) {
          PlayerParam[0] = objectData[2] - b1 - 12 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[4])
            playerPushSet(); 
        } else if (i == 2) {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } else if (i == 3) {
          PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 + b3 << 8;
        } 
      }  
    if (objectData[5] == 1) {
      if ((this.cpuTimer - objectData[10]) % 20 == 0 && this.masin_nflag_ike_x.length > (this.cpuTimer - objectData[10]) / 20)
        SetObj2(1, objectData[8] + this.masin_nflag_ike_x[(this.cpuTimer - objectData[10]) / 20], objectData[9] + this.masin_nflag_ike_y[(this.cpuTimer - objectData[10]) / 20], 0, 0, 0, 0); 
      if (this.cpuTimer - objectData[10] > 100) {
        objectData[5] = 2;
        objectData[10] = this.cpuTimer;
        ShotAnimal(objectData[8] + 4, objectData[9] + 27 + 16, this.zoneNumber);
      } 
    } else if (objectData[5] == 2 && this.cpuTimer - objectData[10] > 180) {
      initGoleStart();
      objectData[5] = objectData[5] + 1;
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void bobin_sflag_move_ikeshita(int paramInt) {
    byte b1 = 8;
    byte b2 = 8;
    boolean bool = false;
    int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0) {
      int j = Math.abs(PlayerPosX() - objectData[2]);
      int k = Math.abs(PlayerPosY() - 12 - objectData[3]);
      if (objectData[10] < 10) {
        ShotScore(objectData[2], objectData[3], 10);
        addScoreCount(10);
        objectData[10] = objectData[10] + 1;
      } 
      if (PlayerPosX() - objectData[2] > 0) {
        PlayerParam[3] = 1792 * j / (j + k);
      } else {
        PlayerParam[3] = -1792 * j / (j + k);
      } 
      if (PlayerPosY() - 12 - objectData[3] > 0) {
        PlayerParam[5] = 2048 * k / (j + k);
      } else {
        PlayerParam[5] = -2048 * k / (j + k);
      } 
      if (!PlayerJump) {
        PlayerParam[3] = PlayerParam[3] * 80 / 100;
        PlayerParam[5] = PlayerParam[5] - 1024;
      } 
      PlayerParam[10] = 0;
      PlayerParam[13] = 0;
      PlayerParam[14] = 0;
      PlayerJump = true;
      PlayerDamage = false;
      PlayerAir = true;
      raidOn = false;
    } 
  }
  
  private void jyama_nflag_move_ikeshita(int paramInt) {
    boolean bool1 = false;
    boolean bool2 = false;
    byte b1 = 16;
    byte b2 = 16;
    int i = ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        if (KeyPress[4])
          playerPushSet(); 
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        if (KeyPress[3])
          playerPushSet(); 
        PlayerParam[10] = 0;
        PlayerParam[13] = 0;
        PlayerParam[14] = 0;
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void fetama_nflag_move_ikeshita(int paramInt) {
    int i = this.animeTimer;
    int j = 0;
    byte b1 = 16;
    byte b2 = 16;
    byte b3 = 4;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[19] == 1) {
      i += 45;
    } else if (objectData[19] == 2) {
      i += 135;
    } else if (objectData[19] == 3) {
      i += 180;
    } 
    if (objectData[4] == 195 || objectData[4] == 179)
      b3 = 8; 
    if (objectData[4] == 243 || objectData[4] == 195 || objectData[4] == 227 || objectData[4] == 179) {
      vect((360 / b3 - i % 360 / b3) * b3, 16, 17);
      objectData[2] = objectData[8] + objectData[16] * 80 / 10000;
      objectData[3] = objectData[9] + objectData[17] * 80 / 10000;
    } else {
      b3 = 1;
      int k = this.animeTimer;
      if (objectData[19] == 1) {
        i += 24;
      } else if (objectData[19] == 2) {
        i += 48;
      } else if (objectData[19] == 3) {
        i += 72;
      } 
      if (objectData[4] == 2) {
        if (objectData[19] != 0) {
          if (k % 96 * 2 < 96) {
            objectData[3] = objectData[9] - 96 - k % 96 * 2 - 96 + 64;
          } else {
            objectData[3] = objectData[9] - k % 96 * 2 + 64;
          } 
        } else if (k % 96 * 2 < 96) {
          objectData[3] = objectData[9] - k % 96 * 2;
        } else {
          objectData[3] = objectData[9] - 96 - k % 96 * 2 - 96;
        } 
      } else if (objectData[4] == 1) {
        if (k % 96 * 2 < 96) {
          objectData[2] = objectData[8] - k % 96 * 2;
        } else {
          objectData[2] = objectData[8] - 96 - k % 96 * 2 - 96;
        } 
      } 
    } 
    byte b4 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b4 = 20; 
    j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
    if (j >= 0) {
      playdamageset();
    } else if (Math.abs(objectData[2] - PlayerPosX()) < 12 + b1 && Math.abs(objectData[3] - PlayerPosY() - b4) < b4 + b2) {
      playdamageset();
    } 
  }
  
  private void tekyu_nflag_move_ikeshita(int paramInt) {
    int i = 0;
    int j = 0;
    boolean bool = false;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b4 = 20; 
    if (objectData[18] == 0) {
      objectData[5] = this.animeTimer;
      objectData[18] = 1;
    } 
    i = this.animeTimer - objectData[5];
    j = i - 1;
    if (objectData[4] == 213 || objectData[4] == 181 || objectData[4] == 197 || objectData[4] == 101 || objectData[4] == 69 || objectData[4] == 53) {
      b2 = 5;
    } else if (objectData[4] == 212 || objectData[4] == 196 || objectData[4] == 84 || objectData[4] == 68 || objectData[4] == 52) {
      b2 = 4;
    } else if (objectData[4] == 38) {
      b2 = 6;
    } else if (objectData[4] == 195) {
      b2 = 3;
    } 
    if (objectData[4] == 181 || objectData[4] == 101) {
      b3 = 12;
    } else if (objectData[4] == 196 || objectData[4] == 197 || objectData[4] == 84 || objectData[4] == 195) {
      b3 = 10;
    } else if (objectData[4] == 213 || objectData[4] == 212 || objectData[4] == 69 || objectData[4] == 68) {
      b3 = 8;
    } else if (objectData[4] == 52 || objectData[4] == 53) {
      b3 = 6;
    } else if (objectData[4] == 38) {
      b3 = 4;
    } 
    if (objectData[4] != 69 && objectData[4] != 84 && objectData[4] != 101 && objectData[4] != 38 && objectData[4] != 68 && objectData[4] != 52 && objectData[4] != 53) {
      vect((360 / b3 - i % 360 / b3) * b3, 16, 17);
      vect((360 / b3 - j % 360 / b3) * b3, 14, 15);
    } else {
      vect(360 - (360 / b3 - i % 360 / b3) * b3 % 360, 16, 17);
      vect(360 - (360 / b3 - j % 360 / b3) * b3 % 360, 14, 15);
    } 
    if (objectData[4] == 84) {
      vect(i % 360 / b3 * b3, 16, 17);
      vect(j % 360 / b3 * b3, 14, 15);
    } else if (objectData[19] == 1) {
      vect(i % 360 / b3 * b3, 16, 17);
      vect(j % 360 / b3 * b3, 14, 15);
    } 
    int k = 0;
    byte b5 = 16;
    byte b6 = 16;
    if (this.zoneNumber == 4) {
      b5 = 8;
      b6 = 8;
    } 
    byte b1;
    for (b1 = 1; b1 < b2; b1++) {
      if (this.zoneNumber == 4) {
        b5 = 8;
        b6 = 8;
        k = ObjectColChk(PlayerPosX(), PlayerPosY() - b4, ploldpos[0], ploldpos[1] - b4, 12, b4, objectData[2] + b1 * objectData[16] * b5 * 2 / 10000, objectData[3] + b1 * objectData[17] * b5 * 2 / 10000, objectData[2] + b1 * objectData[14] * b5 * 2 / 10000, objectData[3] + b1 * objectData[15] * b5 * 2 / 10000, b5 - 2, b6 - 2);
        if (k >= 0)
          playdamageset(); 
      } 
    } 
    if (this.zoneNumber == 4) {
      k = ObjectColChk(PlayerPosX(), PlayerPosY() - b4, ploldpos[0], ploldpos[1] - b4, 12, b4, objectData[2] + b1 * objectData[16] * b5 * 2 / 10000, objectData[3] + b1 * objectData[17] * b5 * 2 / 10000, objectData[2] + b1 * objectData[14] * b5 * 2 / 10000, objectData[3] + b1 * objectData[15] * b5 * 2 / 10000, b5 - 2, b6 - 2);
    } else {
      k = ObjectColChk(PlayerPosX(), PlayerPosY() - b4, ploldpos[0], ploldpos[1] - b4, 12, b4, objectData[2] + b1 * objectData[16] * b5 / 10000, objectData[3] + b1 * objectData[17] * b5 / 10000, objectData[2] + b1 * objectData[14] * b5 / 10000, objectData[3] + b1 * objectData[15] * b5 / 10000, 6, 6);
    } 
    if (k >= 0)
      playdamageset(); 
  }
  
  private void dai2_sflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 0;
    boolean bool3 = false;
    int j = 0;
    boolean bool4 = false;
    boolean bool5 = false;
    objectData[6] = objectData[2];
    objectData[7] = objectData[3];
    if (objectData[4] == 248 && this.stageNumber == 2) {
      b1 = 64;
      b2 = 16;
      if (Math.abs(PlayerPosX() - objectData[8]) <= b1 && Math.abs(PlayerPosY() + 12 - objectData[9] - b2 * 2 + 8) <= b2 * 2)
        switchflag2[objectData[4] - 240] = true; 
      if (Math.abs(PlayerPosX() - objectData[8]) <= b1 && Math.abs(PlayerPosY() + 12 - objectData[9] + b2 * 3) <= b2 * 2)
        switchflag2[objectData[4] - 240] = false; 
      if (switchflag2[objectData[4] - 240] && objectData[3] > this.waterH2) {
        objectData[18] = 1;
        objectData[5] = b1 * 2;
        objectData[2] = objectData[8] + b1 * 2 - objectData[5];
      } else {
        objectData[18] = 1;
        objectData[5] = 0;
        objectData[2] = objectData[8] + b1 * 2 - objectData[5];
      } 
      b1--;
    } else if (objectData[4] == 241 && switchflag2[128] && this.zoneNumber == 1 && this.stageNumber == 3) {
      b1 = 64;
      b2 = 16;
      if (objectData[19] == 0) {
        if (switchflag2[128] && objectData[18] == 0) {
          objectData[18] = 1;
          objectData[5] = b1 * 2;
          objectData[2] = objectData[8] + b1 * 2 - objectData[5];
        } 
        objectData[18] = 1;
        if (objectData[5] == 0) {
          if (switchflag2[128]) {
            objectData[5] = 1;
          } else {
            objectData[2] = objectData[8] + b1 * 2;
          } 
        } else {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > b1 * 2)
            objectData[5] = b1 * 2; 
          objectData[2] = objectData[8] + b1 * 2 - objectData[5];
        } 
      } else {
        if (switchflag2[128] && objectData[18] == 0) {
          objectData[18] = 1;
          objectData[5] = b1 * 2;
          objectData[2] = objectData[8] + objectData[5];
        } 
        objectData[18] = 1;
        if (objectData[5] == 0) {
          if (switchflag2[128]) {
            objectData[5] = 1;
          } else {
            objectData[2] = objectData[8];
          } 
        } else {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > b1 * 2)
            objectData[5] = b1 * 2; 
          objectData[2] = objectData[8] + objectData[5];
        } 
      } 
      b1--;
    } else if (objectData[4] >= 240) {
      b1 = 64;
      b2 = 16;
      if (objectData[19] == 0) {
        if (switchflag2[objectData[4] - 240] && objectData[18] == 0) {
          objectData[18] = 1;
          objectData[5] = b1 * 2;
          objectData[2] = objectData[8] + b1 * 2 - objectData[5];
        } 
        objectData[18] = 1;
        if (objectData[5] == 0) {
          if (switchflag2[objectData[4] - 240]) {
            objectData[5] = 1;
          } else {
            objectData[2] = objectData[8] + b1 * 2;
          } 
        } else {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > b1 * 2)
            objectData[5] = b1 * 2; 
          objectData[2] = objectData[8] + b1 * 2 - objectData[5];
        } 
      } else {
        if (switchflag2[objectData[4] - 240] && objectData[18] == 0) {
          objectData[18] = 1;
          objectData[5] = b1 * 2;
          objectData[2] = objectData[8] + objectData[5];
        } 
        objectData[18] = 1;
        if (objectData[5] == 0) {
          if (switchflag2[objectData[4] - 240]) {
            objectData[5] = 1;
          } else {
            objectData[2] = objectData[8];
          } 
        } else {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > b1 * 2)
            objectData[5] = b1 * 2; 
          objectData[2] = objectData[8] + objectData[5];
        } 
      } 
      b1--;
    } else if (objectData[4] == 229 && this.zoneNumber == 1 && this.stageNumber == 0) {
      b1 = 8;
      b2 = 32;
      if (objectData[5] == 0) {
        if (switchflag[objectData[4] - 96]) {
          objectData[5] = 1;
          objectData[18] = 1;
        } else {
          objectData[3] = objectData[9] + b2 * 2;
        } 
      } else {
        if (objectData[18] != 0 && objectData[2] < PlayerPosX() - 12)
          objectData[18] = 0; 
        if (objectData[18] == 0) {
          objectData[5] = objectData[5] - 1;
          if (objectData[5] < 0)
            objectData[5] = 0; 
          objectData[3] = objectData[9] + b2 * 2 - objectData[5];
        } else {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > b2 * 2)
            objectData[5] = b2 * 2; 
          objectData[3] = objectData[9] + b2 * 2 - objectData[5];
        } 
      } 
      b1--;
    } else if (objectData[4] >= 224) {
      b1 = 8;
      b2 = 32;
      if (switchflag2[objectData[4] - 224] && objectData[18] == 0) {
        objectData[18] = 1;
        objectData[5] = b2 * 2;
        objectData[3] = objectData[9] + b2 * 2 - objectData[5];
      } 
      objectData[18] = 1;
      if (objectData[5] == 0) {
        if (switchflag2[objectData[4] - 224]) {
          objectData[5] = 1;
        } else {
          objectData[3] = objectData[9] + b2 * 2;
        } 
      } else {
        objectData[5] = objectData[5] + 1;
        if (objectData[5] > b2 * 2)
          objectData[5] = b2 * 2; 
        objectData[3] = objectData[9] + b2 * 2 - objectData[5];
      } 
      b1--;
    } else if (objectData[4] == 19) {
      b1 = 32;
      b2 = 32;
      objectData[5] = objectData[5] + 1;
      if (this.cpuTimer % 360 > 90 && this.cpuTimer % 360 < 270) {
        j = 33;
      } else {
        j = 32;
      } 
      if (objectData[19] == 0) {
        objectData[3] = objectData[9] + dSin(this.cpuTimer) * j / 100 - j;
      } else {
        objectData[3] = objectData[9] - dSin(this.cpuTimer) * j / 100 - j;
      } 
      bool5 = true;
    } else if (objectData[4] >= 0 && objectData[4] <= 2) {
      b1 = 16;
      b2 = 16;
      i = objectData[2];
      if (objectData[4] != 0) {
        objectData[5] = objectData[5] + 1;
        j = 32 * objectData[4];
        if (objectData[19] == 0) {
          objectData[2] = objectData[8] + dSin(this.cpuTimer) * j / 100 - j;
        } else {
          objectData[2] = objectData[8] - dSin(this.cpuTimer) * j / 100 - j;
        } 
      } 
      i -= objectData[2];
      bool4 = true;
    } else if (objectData[4] <= 91 && objectData[4] >= 88) {
      b1 = 16;
      b2 = 16;
      i = objectData[2];
      j = 32 * (objectData[4] - 88 + 1) - 16;
      if (this.cpuTimer % 720 < 180) {
        if (objectData[19] == 0) {
          objectData[3] = objectData[9] - dSin(90 + this.cpuTimer % 720) * j / 100;
          objectData[2] = objectData[8] - 16 + 32 * (objectData[4] - 88 + 1);
        } else {
          objectData[3] = objectData[9] + dSin(90 + this.cpuTimer % 720) * j / 100;
          objectData[2] = objectData[8] + 16 - 32 * (objectData[4] - 88 + 1);
        } 
      } else if (this.cpuTimer % 720 < 360) {
        if (objectData[19] == 0) {
          objectData[2] = objectData[8] - dSin(270 - this.cpuTimer % 720 - 180) * j / 100;
          objectData[3] = objectData[9] - 16 + 32 * (objectData[4] - 88 + 1);
        } else {
          objectData[2] = objectData[8] + dSin(270 - this.cpuTimer % 720 - 180) * j / 100;
          objectData[3] = objectData[9] + 16 - 32 * (objectData[4] - 88 + 1);
        } 
        bool4 = true;
      } else if (this.cpuTimer % 720 < 540) {
        if (objectData[19] == 0) {
          objectData[3] = objectData[9] + dSin(90 + this.cpuTimer % 720 - 360) * j / 100;
          objectData[2] = objectData[8] + 16 - 32 * (objectData[4] - 88 + 1);
        } else {
          objectData[3] = objectData[9] - dSin(90 + this.cpuTimer % 720 - 360) * j / 100;
          objectData[2] = objectData[8] - 16 + 32 * (objectData[4] - 88 + 1);
        } 
      } else if (this.cpuTimer % 720 < 720) {
        if (objectData[19] == 0) {
          objectData[2] = objectData[8] + dSin(270 - this.cpuTimer % 720 - 540) * j / 100;
          objectData[3] = objectData[9] + 16 - 32 * (objectData[4] - 88 + 1);
        } else {
          objectData[2] = objectData[8] - dSin(270 - this.cpuTimer % 720 - 540) * j / 100;
          objectData[3] = objectData[9] - 16 + 32 * (objectData[4] - 88 + 1);
        } 
        bool4 = true;
      } 
      i -= objectData[2];
    } else if (objectData[4] == 160) {
      b1 = 16;
      b2 = 32;
      if (switchflag2[0] && objectData[18] == 0) {
        objectData[18] = 1;
        objectData[3] = objectData[9];
      } 
      objectData[18] = 1;
      if (switchflag2[0]) {
        objectData[3] = objectData[3] - 1;
        if (objectData[3] < objectData[9])
          objectData[3] = objectData[9]; 
      } else if (this.stageNumber == 0) {
        objectData[3] = 688;
      } else {
        objectData[3] = objectData[9] + 64;
      } 
    } else if (objectData[4] == 55) {
      b1 = 32;
      b2 = 25;
      if (switchflag2[objectData[4] - 40] && objectData[18] == 0) {
        objectData[18] = 1;
        objectData[2] = 7992;
      } 
      objectData[18] = 1;
      if ((objectData[8] != 7992 || objectData[9] != 1353) && switchflag2[objectData[4] - 40]) {
        objectData[2] = objectData[2] + 1;
        if (objectData[2] > 7992)
          objectData[2] = 7992; 
      } 
    } 
    byte b3 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b3 = 16; 
    int k = -1;
    if (objectData[4] != 55 || objectData[8] != 7992 || objectData[9] != 1353) {
      k = ObjectColChk(PlayerPosX(), PlayerPosY() - b3, ploldpos[0], ploldpos[1] - b3, 12, b3, objectData[2], objectData[3], objectData[6], objectData[7], b1, b2);
      if (k >= 0)
        if (k == 0) {
          PlayerParam[1] = objectData[3] - b2 << 8;
          setRaidOnSize(objectData[2], b1);
          playerRaidOn(objectData[22]);
          PlayerParam[0] = PlayerParam[0] - (i << 8);
        } else if (k == 1) {
          if (!PlayerSWater || objectData[4] != 227) {
            PlayerParam[0] = objectData[2] - b1 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4])
              playerPushSet(); 
          } 
        } else if (k == 2) {
          PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
          PlayerParam[10] = 0;
          if (KeyPress[3])
            playerPushSet(); 
        } else if (k == 3) {
          PlayerParam[1] = objectData[3] + b2 + b3 + b3 + 1 << 8;
          setHeadHit();
        } else if (k == 4) {
          if (bool4)
            if (objectData[2] > PlayerPosX()) {
              if (!PlayerSWater || objectData[4] != 227) {
                PlayerParam[0] = objectData[2] - b1 - 12 << 8;
                PlayerParam[10] = 0;
                if (KeyPress[4])
                  playerPushSet(); 
              } 
            } else {
              PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
              PlayerParam[10] = 0;
              if (KeyPress[3])
                playerPushSet(); 
            }  
          if (bool5)
            if (objectData[3] > PlayerPosY() - b3) {
              PlayerParam[1] = objectData[3] - b2 << 8;
              setRaidOnSize(objectData[2], b1);
              playerRaidOn(objectData[22]);
              PlayerParam[0] = PlayerParam[0] - (i << 8);
              k = 0;
            } else {
              PlayerParam[1] = objectData[3] + b2 + b3 + b3 + 1 + 8 << 8;
              setHeadHit();
            }  
        }  
    } 
    if (raidOn && raidObjectNum == objectData[20] && k != 0)
      raidOn = false; 
  }
  
  private void ring_sflag_ring_m10_10_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_10_10_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_20_20_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_10_00_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_20_00_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_00_10_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_00_20_move_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_move_ikeshita(paramInt);
  }
  
  private void elev_nflag_80_move_ikeshita(int paramInt) {
    elev_nflag_move_ikeshita(paramInt);
  }
  
  private void elev_nflag_move_ikeshita(int paramInt) {
    byte b1 = 40;
    byte b2 = 8;
    int i = 0;
    int j = 0;
    int k = 0;
    byte b3 = 0;
    byte b4 = 0;
    int m = -1;
    objectData[7] = objectData[3];
    objectData[11] = objectData[2];
    if (objectData[4] == 1 || objectData[4] == 0) {
      if (Math.abs(PlayerPosX() - objectData[2]) > 320) {
        objectData[2] = objectData[8];
        objectData[3] = objectData[9];
        objectData[5] = 0;
      } 
    } else if (objectData[4] == 12 && Math.abs(PlayerPosX() - objectData[2]) > 320) {
      objectData[2] = objectData[8];
      objectData[3] = objectData[9];
      objectData[5] = 0;
    } 
    if (objectData[4] != 16) {
      if (objectData[5] != 0) {
        if (objectData[4] == 1) {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 128)
            objectData[5] = 128; 
          objectData[3] = objectData[9] - objectData[5] * 2;
        } else if (objectData[4] == 0) {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 64)
            objectData[5] = 64; 
          objectData[3] = objectData[9] - objectData[5] * 2;
        } else if (objectData[4] == 3) {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 64)
            objectData[5] = 64; 
          objectData[3] = objectData[9] + objectData[5] * 2;
        } else if (objectData[4] == 12) {
          objectData[5] = objectData[5] + 1;
          if (objectData[5] > 128)
            objectData[5] = 128; 
          k = objectData[2];
          objectData[2] = objectData[8] + objectData[5] * 2;
          objectData[3] = objectData[9] - objectData[5];
          k -= objectData[2];
        } 
      } else {
        j = dSin(objectData[5]) * b4 / 100;
        objectData[3] = objectData[9] + j;
        i = dSin(objectData[5]) * b3 / 100;
        objectData[2] = objectData[8] + i;
      } 
      m = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[11], objectData[7], b1, b2);
      if (m >= 0 && m == 0) {
        if (b3 != 0)
          PlayerParam[0] = PlayerParam[0] - (dSin(objectData[5] - 1) * b3 - dSin(objectData[5]) * b3 << 8) / 100; 
        PlayerParam[1] = objectData[3] - b2 << 8;
        PlayerParam[0] = PlayerParam[0] - (k << 8);
        if (objectData[5] == 0 && Math.abs(PlayerPosX() - objectData[2]) <= 48)
          objectData[5] = 1; 
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } 
    } else {
      int n = 0;
      int i1 = 0;
      objectData[5] = objectData[5] + 1;
      for (byte b = 0; b < 3; b++) {
        n = objectData[3] - (objectData[5] + 1 + b * 128) % 384;
        i1 = objectData[3] - (objectData[5] + b * 128) % 384;
        m = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], n, objectData[11], i1, b1, b2);
        if (m >= 0 && m == 0) {
          PlayerParam[1] = n - b2 << 8;
          setRaidOnSize(objectData[2], b1);
          playerRaidOn(objectData[22]);
        } 
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && m != 0)
      raidOn = false; 
  }
  
  private void mfire_nflag_move_ikeshita(int paramInt) {
    int i = 0;
    char c = Character.MIN_VALUE;
    int j = 0;
    i = objectData[4] / 16;
    if (objectData[18] == 0) {
      objectData[10] = this.cpuTimer;
      objectData[18] = 1;
    } 
    j = this.cpuTimer - objectData[10];
    if (j % i * 50 == 0 && objectData[2] - mapOxy[0] >= -16 && objectData[2] - mapOxy[0] <= 256 && objectData[3] - mapOxy[1] >= -16 && objectData[3] - mapOxy[1] <= 184)
      if (objectData[4] == 48 || objectData[4] == 66 || objectData[4] == 65 || objectData[4] == 49 || objectData[4] == 80 || objectData[4] == 64 || objectData[4] == 81) {
        if (objectData[4] == 48)
          c = '`'; 
        if (objectData[4] == 49)
          c = ''; 
        if (objectData[4] == 64)
          c = '`'; 
        if (objectData[4] == 65)
          c = ''; 
        if (objectData[4] == 66)
          c = 'Ä'; 
        if (objectData[4] == 80)
          c = 'H'; 
        if (objectData[4] == 81)
          c = ''; 
        SetObj2(20, objectData[2], objectData[3], 0, 0, 0, c);
      } else if (objectData[4] == 53 || objectData[4] == 37 || objectData[4] == 69) {
        SetObj2(16, objectData[2], objectData[3], 0, 200, 0, 0);
      } else if (objectData[4] == 55 || objectData[4] == 71 || objectData[4] == 23) {
        SetObj2(16, objectData[2], objectData[3], 200, 0, 0, 0);
      } else if (objectData[4] == 54) {
        SetObj2(16, objectData[2], objectData[3], -200, 0, 0, 0);
      }  
  }
  
  private void yoganc_nflag_move_ikeshita(int paramInt) {
    byte b = 0;
    if (this.stageNumber == 2)
      b = 1; 
    if (objectData[4] == 16) {
      int[][] arrayOfInt = searchObject(10, 2);
      byte b1;
      for (b1 = 0; b1 < arrayOfInt.length; b1++) {
        if (Math.abs(objectData[2] + 32 - 64 * b - arrayOfInt[b1][2]) < 2) {
          if (objectData[5] == 0) {
            objectData[5] = 1;
            objectData[10] = this.cpuTimer;
          } 
          objectData[7] = arrayOfInt[b1][20];
          break;
        } 
      } 
      if (objectData[7] > 0)
        for (b1 = 0; b1 < arrayOfInt.length; b1++) {
          if (arrayOfInt[b1][20] == objectData[7]) {
            objectData[6] = b1;
            break;
          } 
        }  
      if (objectData[10] != 0)
        objectData[5] = this.cpuTimer - objectData[10]; 
      if (objectData[5] > 0 && objectData[7] > 0) {
        if (objectData[5] / 4 > 44) {
          objectData[5] = 0;
          objectData[6] = 0;
          objectData[7] = 0;
          objectData[10] = 0;
        } else if (objectData[5] / 4 > 26) {
          objectData[3] = objectData[9] - 160 - (objectData[5] - 104) * 2;
        } else if (objectData[5] / 4 > 6) {
          objectData[3] = objectData[9] - (objectData[5] - 24) * 2;
        } 
        objectData[5] = objectData[5] + 1;
      } 
      if (objectData[4] == 16 && objectData[5] / 4 > 0 && objectData[2] - 20 - 32 <= PlayerPosX() && objectData[2] - 20 - 32 + 112 >= PlayerPosX() && objectData[3] <= PlayerPosY() && objectData[9] >= PlayerPosY())
        playdamageset(); 
    } 
  }
  
  private void ochi_nflag_move_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 72;
    boolean bool1 = false;
    int i = 0;
    boolean bool2 = false;
    byte b = 0;
    if (objectData[6] == 0 && objectData[7] == 0) {
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
    } 
    if (objectData[4] == 1) {
      objectData[5] = this.cpuTimer;
      b = 16;
      i = dSin(objectData[5]) * b / 100 - b;
      i *= 2;
      i += 2;
    } else if (objectData[4] == 2) {
      objectData[5] = this.cpuTimer;
      b = -16;
      i = dSin(objectData[5]) * b / 100 + b;
      i *= 2;
      i += 2;
    } else if (objectData[4] == 20) {
      b2 = 56;
      if (objectData[5] == 0) {
        if (switchflag[1]) {
          objectData[5] = 1;
          objectData[10] = this.cpuTimer;
        } else {
          objectData[3] = objectData[9] - 160;
        } 
      } else {
        objectData[5] = this.cpuTimer - objectData[10] - 1;
        if (objectData[5] > 72)
          objectData[5] = 72; 
        objectData[3] = objectData[9] - 160 + objectData[5] * 2;
      } 
      i = 16;
    } else if (objectData[4] == 4) {
      b2 = 56;
      if (switchflag[0] || objectData[5] != 0) {
        if (switchflag[0] && objectData[5] == 0)
          objectData[10] = this.cpuTimer; 
        objectData[5] = this.cpuTimer - objectData[10] - 1;
        if (objectData[5] > 72)
          objectData[5] = 72; 
        objectData[3] = objectData[9] - 160 + objectData[5] * 2;
      } else {
        objectData[3] = objectData[9] - 160;
      } 
      i = 16;
    } 
    int j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + i, objectData[6], objectData[7], b1, b2);
    if (j >= 0)
      if (j == 0) {
        PlayerParam[1] = objectData[3] + i - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
      } else if (j == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (j == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (j == 3) {
        PlayerParam[1] = objectData[3] + i + b2 + 12 + 12 + 1 << 8;
        if (objectData[4] == 1 && (objectData[5] % 360 < 90 || objectData[5] % 360 > 270)) {
          setHeadHit();
        } else if (objectData[4] == 2 && objectData[5] % 360 < 270 && objectData[5] % 360 > 90) {
          setHeadHit();
        } else if (objectData[4] != 1 && objectData[4] != 2) {
          setHeadHit();
        } 
      }  
    if (raidOn && raidObjectNum == objectData[20] && j != 0)
      raidOn = false; 
    objectData[7] = objectData[3] + i;
    objectData[6] = objectData[2];
  }
  
  private void yari_sflag_move_ikeshita(int paramInt) {
    int i = this.cpuTimer % 132;
    byte b1 = 4;
    byte b2 = 24;
    byte b3 = 0;
    if (objectData[13] == 0 && objectData[12] == 0) {
      objectData[13] = objectData[3];
      objectData[12] = objectData[2];
    } 
    objectData[6] = objectData[12];
    objectData[7] = objectData[13];
    if (i < 60) {
      objectData[5] = 0;
      b2 = 20;
    } else if (i < 64) {
      objectData[5] = 1;
      b2 = 12;
    } else if (i < 124) {
      objectData[5] = 2;
      b2 = 4;
    } else {
      objectData[5] = 3;
      b2 = 12;
    } 
    if (objectData[4] == 0) {
      if (objectData[19] == 0) {
        objectData[12] = objectData[2] + (this.yari_sflag_ike_PosTable[objectData[5]] >> 1) - 8;
      } else {
        objectData[12] = objectData[2] - (this.yari_sflag_ike_PosTable[objectData[5]] >> 1) + 8;
      } 
    } else if (objectData[19] == 0) {
      objectData[13] = objectData[3] - (this.yari_sflag_ike_PosTable[objectData[5]] >> 1) + 8;
    } else {
      objectData[13] = objectData[3] + (this.yari_sflag_ike_PosTable[objectData[5]] >> 1) - 8;
    } 
    if (objectData[4] == 0) {
      b3 = b2;
      b2 = b1;
      b1 = b3;
    } 
    byte b4 = 12;
    if (!PlayerBall && !PlayerCrouch)
      b4 = 20; 
    int j = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[12], objectData[13], objectData[6], objectData[7], b1, b2);
    if (j >= 0) {
      playdamageset();
    } else if (Math.abs(objectData[12] - PlayerPosX()) < 12 + b1 && Math.abs(objectData[13] - PlayerPosY() - b4) < b4 + b2) {
      playdamageset();
    } 
  }
  
  private void kazari_sflag_move_ikeshita(int paramInt) {
    if (this.cpuTimer % 120 == 0)
      if (objectData[19] == 1) {
        SetObj2(21, objectData[2] + 16, objectData[3] + 10, 260, 0, 0, 0);
      } else if (objectData[19] == 0) {
        SetObj2(21, objectData[2] - 16, objectData[3] + 10, -260, 0, 0, 0);
      }  
  }
  
  private void dai3_nflag_move_ikeshita(int paramInt) {
    byte b1 = 16;
    byte b2 = 16;
    objectData[7] = objectData[3];
    if (objectData[4] == 39) {
      if (objectData[3] < this.waterH2) {
        objectData[3] = objectData[3] + 8;
        if (blockColChk_Enemy(objectData[2], objectData[3] + b2)) {
          objectData[3] = objectData[3] - (objectData[3] + b2) % 16;
        } else if (objectData[3] > this.waterH2) {
          objectData[3] = this.waterH2;
        } 
      } else if (objectData[3] > this.waterH2) {
        objectData[3] = objectData[3] - 8;
        if (blockColChk_Enemy(objectData[2], objectData[3] - b2)) {
          objectData[3] = objectData[3] + (objectData[3] - b2) % 16;
        } else if (objectData[3] < this.waterH2) {
          objectData[3] = this.waterH2;
        } 
      } 
    } else if (objectData[4] == 19) {
      b1 = 32;
      b2 = 12;
      objectData[6] = this.cpuTimer;
      if (objectData[10] == 1) {
        if (objectData[11] % 4 == 0 && objectData[11] <= 16)
          objectData[3] = objectData[3] + 1; 
        objectData[11] = objectData[11] + 1;
        if (objectData[11] > 76) {
          objectData[11] = 0;
          objectData[10] = 2;
        } 
      } else if (objectData[10] == 2) {
        objectData[3] = objectData[3] - 2;
        int[][] arrayOfInt = searchObject(9, -1);
        for (byte b = 0; b < arrayOfInt.length; b++) {
          if (arrayOfInt[b][2] - 20 - objectData[2] - 16 <= 32 && arrayOfInt[b][2] - 20 - objectData[2] - 16 >= -40 && arrayOfInt[b][3] - 60 - objectData[3] - 16 <= 16 && arrayOfInt[b][3] - 60 - objectData[3] - 16 >= -40) {
            objectData[10] = 3;
            break;
          } 
        } 
      } 
    } else if (objectData[4] == 1) {
      b1 = 16;
      b2 = 16;
      if (objectData[10] != 0) {
        objectData[10] = objectData[10] + 1;
        if (objectData[10] > 20) {
          if (objectData[5] == 0) {
            objectData[3] = objectData[3] + 2;
            if (blockColChk_Enemy(objectData[2] - b1 + 1, objectData[3] + b2) || blockColChk_Enemy(objectData[2] + b1 - 1, objectData[3] + b2))
              objectData[5] = 1; 
          } 
        } else {
          objectData[3] = objectData[9] + 2;
        } 
      } 
      if (this.zoneNumber == 1 && this.stageNumber == 3) {
        int[][] arrayOfInt = searchObject(15, 1);
        switchflag[128] = false;
        for (byte b = 0; b < arrayOfInt.length; b++) {
          if (arrayOfInt[b][2] - 8 - objectData[2] - 16 <= 32 && arrayOfInt[b][2] - 8 - objectData[2] - 16 >= -32 && arrayOfInt[b][3] - 8 - objectData[3] - 16 <= 32 && arrayOfInt[b][3] - 8 - objectData[3] - 16 >= -32) {
            switchflag[128] = true;
            switchflag2[128] = true;
          } 
        } 
      } 
    } 
    int i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[7], b1, b2);
    if (i >= 0)
      if (i == 0) {
        PlayerParam[1] = objectData[3] - b2 << 8;
        setRaidOnSize(objectData[2], b1);
        playerRaidOn(objectData[22]);
        if ((objectData[4] == 19 || objectData[4] == 1) && objectData[10] == 0)
          objectData[10] = 1; 
      } else if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[4])
          playerPushSet(); 
      } else if (i == 2) {
        PlayerParam[0] = objectData[2] + b1 + 12 + 1 << 8;
        PlayerParam[10] = 0;
        if (KeyPress[3])
          playerPushSet(); 
      } else if (i == 3) {
        PlayerParam[1] = objectData[3] + b2 + 12 + 12 + 1 << 8;
        setHeadHit();
      }  
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void kassya_nflag_move_ikeshita(int paramInt) {
    int i = -1;
    boolean bool = false;
    if (objectData[4] != 127) {
      int j = 0;
      byte b2 = 0;
      int k = (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length >> 1;
      int[] arrayOfInt = new int[k + 1];
      arrayOfInt[0] = 0;
      if (this.stageNumber == 3)
        return; 
      byte b1;
      for (b1 = 0; b1 < (this.kassya_nflag_ike_defY[this.stageNumber]).length; b1++) {
        if (this.kassya_nflag_ike_defY[this.stageNumber][b1] == objectData[9] && this.kassya_nflag_ike_defX[this.stageNumber][b1] == objectData[8])
          b2 = b1; 
      } 
      for (b1 = 0; b1 < k; b1++) {
        for (byte b = 0; b < 2; b++) {
          this.beltc_nflag_ike_startPos[b] = 0;
          this.beltc_nflag_ike_endPos[b] = 0;
        } 
        this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b1 << 1];
        this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b1 << 1) + 1];
        this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b1 + 1) % k << 1];
        this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][((b1 + 1) % k << 1) + 1];
        if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
          j += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
        } else {
          j += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
        } 
        arrayOfInt[b1 + 1] = j;
      } 
      int m = j / 132;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      int i3 = 0;
      int i4 = 0;
      int i5 = 0;
      if (objectData[18] == 0)
        for (b1 = 0; b1 < (kassya_x[b2]).length; b1++) {
          kassya_x[b2][b1] = 0;
          kassya_y[b2][b1] = 0;
        }  
      objectData[18] = 1;
      for (b1 = 0; b1 < m; b1++) {
        n = (b1 * 132 + this.cpuTimer) % j;
        if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
          n = j - n;
          if (n == j)
            n = 0; 
        } 
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
        for (byte b3 = 0; b3 < k; b3++) {
          if (n < arrayOfInt[b3 + 1]) {
            i5 = n - arrayOfInt[b3 + 1];
            for (byte b = 0; b < 2; b++) {
              this.beltc_nflag_ike_startPos[b] = 0;
              this.beltc_nflag_ike_endPos[b] = 0;
            } 
            this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b3 << 1];
            this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b3 << 1) + 1];
            this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b3 + 1) % k << 1];
            this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][((b3 + 1) % k << 1) + 1];
            if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
              i1 = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * i5 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
              i2 = this.beltc_nflag_ike_endPos[1] + i5 * (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
              break;
            } 
            i1 = this.beltc_nflag_ike_endPos[0] + i5 * (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
            i2 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * i5 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
            break;
          } 
        } 
        if (kassya_x[b2][b1] == 0 && kassya_y[b2][b1] == 0) {
          kassya_x[b2][b1] = i1;
          kassya_y[b2][b1] = i2;
        } 
        i3 = kassya_x[b2][b1];
        i4 = kassya_y[b2][b1];
        kassya_x[b2][b1] = i1;
        kassya_y[b2][b1] = i2;
        byte b4 = 16;
        byte b5 = 8;
        if (!bool) {
          i = ObjectColChk(PlayerPosX(), PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, i1, i2, i3, i4, b4, b5);
          if (i >= 0 && i == 0) {
            PlayerParam[1] = i2 - b5 << 8;
            PlayerParam[0] = PlayerParam[0] - (i3 - i1 << 8);
            setRaidOnSize(i1, b4);
            playerRaidOn(objectData[22]);
            raidObjectNumSub = b1;
            bool = true;
          } 
        } 
      } 
    } 
    if (bool)
      i = 0; 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void shima2_nflag_move_ikeshita(int paramInt) {
    shima_nflag_move_ikeshita(paramInt);
  }
  
  private void bou_nflag_move_ikeshita(int paramInt) {
    byte b1 = 4;
    byte b2 = 32;
    int i = ObjectColChk(PlayerPosX() - 35, PlayerPosY() - 12, ploldpos[0] - 35, ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], b1, b2);
    if (objectData[10] >= 300)
      i = -1; 
    if (i >= 0) {
      if (i == 1) {
        PlayerParam[0] = objectData[2] - b1 - 12 + 35 << 8;
        PlayerBou = true;
        objectData[10] = objectData[10] + 1;
      } 
      if (objectData[3] - PlayerPosY() > 4) {
        PlayerParam[1] = objectData[3] - 4 << 8;
      } else if (objectData[3] - PlayerPosY() < -32) {
        PlayerParam[1] = objectData[3] + 32 << 8;
      } 
      if (KeyPress[0]) {
        PlayerParam[0] = PlayerParam[0] + 256;
        ploldpos[0] = PlayerPosX();
      } 
    } 
    if (raidOn && raidObjectNum == objectData[20] && i != 0)
      raidOn = false; 
  }
  
  private void ring_sflag_ring_18_00_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    int j = this.animeTimer % 4 * 16;
    if (this.animeTimer % 4 == 3) {
      i = TRANS_MIRROR;
      j = 16;
    } 
    if (objectData[5] == 0) {
      drawRegion(gg, this.m_imgObj[0], 0, j, 16, 16, rotNumTable[i], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8, 20);
    } else {
      drawRegion(gg, this.m_imgObj[0], 16, (this.cpuTimer - objectData[10]) / 5 % 4 * 16, 16, 16, rotNumTable[0], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8, 20);
    } 
    if (objectData[11] == 1)
      if (objectData[5] == 0) {
        drawRegion(gg, this.m_imgObj[0], 0, j, 16, 16, rotNumTable[i], objectData[2] - mapView[0] - 8, objectData[12] - mapView[1] - 8, 20);
      } else {
        drawRegion(gg, this.m_imgObj[0], 16, (this.cpuTimer - objectData[10]) / 5 % 4 * 16, 16, 16, rotNumTable[0], objectData[2] - mapView[0] - 8, objectData[12] - mapView[1] - 8, 20);
      }  
  }
  
  private void ring_sflag_ring_00_18_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void buranko_nflag_draw_ikeshita(int paramInt) {
    int i = 0;
    int j = dSin(this.animeTimer * 3) * 87;
    int k = objectData[4] + 1;
    if (objectData[4] == 5) {
      j = -j;
    } else if (objectData[19] == 1) {
      j = -j;
    } 
    for (i = 1; i < k; i++) {
      if (this.zoneNumber == 3) {
        drawRegion(gg, this.m_imgObj[3], 36, 48, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + i * dSin(180 + j / 100) * 16 / 100, objectData[3] - mapView[1] + i * dCos(180 + j / 100) * 16 / 100, 0x1 | 0x2);
      } else if (this.zoneNumber != 5) {
        drawRegion(gg, this.m_imgObj[3], 16, 32, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + i * dSin(180 + j / 100) * 16 / 100, objectData[3] - mapView[1] + i * dCos(180 + j / 100) * 16 / 100, 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[3], 0, 96, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + i * dSin(180 + j / 100) * 16 / 100, objectData[3] - mapView[1] + i * dCos(180 + j / 100) * 16 / 100, 0x1 | 0x2);
      } 
    } 
    if (this.zoneNumber == 3) {
      drawRegion(gg, this.m_imgObj[3], 36, 64, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (this.zoneNumber != 5) {
      drawRegion(gg, this.m_imgObj[3], 16, 16, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[3], 0, this.cpuTimer / 3 % 3 * 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
    if (this.zoneNumber == 3) {
      i *= 16;
      i += 8;
      drawRegion(gg, this.m_imgObj[3], 0, 0, 88, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + dSin(180 + j / 100) * i / 100, objectData[3] - mapView[1] + dCos(180 + j / 100) * i / 100, 0x1 | 0x2);
    } else if (this.zoneNumber != 5) {
      i *= 16;
      i -= 8;
      drawRegion(gg, this.m_imgObj[3], 0, 0, 48, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + dSin(180 + j / 100) * i / 100, objectData[3] - mapView[1] + dCos(180 + j / 100) * i / 100, 0x1 | 0x2);
    } else {
      i *= 16;
      i -= 24;
      drawRegion(gg, this.m_imgObj[60], 0, 0, 48, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + dSin(180 + j / 100) * i / 100, objectData[3] - mapView[1] + dCos(180 + j / 100) * i / 100, 0x1 | 0x2);
    } 
  }
  
  private void hashi_nflag_draw_ikeshita(int paramInt) {
    int i = 0;
    for (byte b = 0; b < 12; b++) {
      i = objectData[10] - Math.abs(objectData[5] - b);
      if (i < 0)
        i = 0; 
      if (b == 0 || b == 11)
        i = 0; 
      drawRegion(gg, this.m_imgObj[5], 32, 0, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96 + b * 16, objectData[3] - mapView[1] + i, 0x1 | 0x2);
    } 
  }
  
  private void thashi_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    int i = 0;
    int[] arrayOfInt = { -4, 4 };
    for (b = 0; b < 12; b++) {
      i = (this.animeTimer / 10 + 12 - b) % 7;
      drawRegion(gg, this.m_imgObj[4], 0, i * 24, 16, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96 + b * 16, objectData[3] - mapView[1] + arrayOfInt[i / 4], 0x1 | 0x2);
    } 
  }
  
  private void break_sflag_draw_ikeshita(int paramInt) {
    int i = rotNumTable[TRANS_NONE];
    int j = 0;
    int k = 0;
    int m = 0;
    byte b1 = 0;
    if (objectData[4] != 0) {
      i = rotNumTable[TRANS_MIRROR];
      b1 = -1;
    } 
    if (objectData[5] != 0)
      j = this.cpuTimer / 2 - objectData[10]; 
    for (byte b = 0; b < 36; b++) {
      k = j - b * 3;
      if (k < 0) {
        k = 0;
      } else {
        k *= j / 6;
      } 
      if (objectData[4] != 0) {
        m = 96 - b / 6 * 16 - 16;
      } else {
        m = b / 6 * 16;
      } 
      drawRegion(gg, this.m_imgObj[6], b / 6 * 16, 96 - (b % 6 + 1) * 16, 16, 16, i, objectData[2] - mapView[0] + m - 40, objectData[3] - mapView[1] + 96 - (b % 6 + 1) * 16 - 48 + k, 0x1 | 0x2);
    } 
  }
  
  private void yuka_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    if (objectData[4] == 21) {
      b = 2;
    } else if (objectData[4] == 1) {
      b = 0;
    } else {
      b = 1;
    } 
    if (objectData[4] == 21) {
      drawRegion(gg, this.m_imgObj[7], 16, 0, 128, 96, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
      if (objectData[16] >= 60) {
        int i = 0;
        for (byte b1 = 0; b1 < 8; b1++) {
          i = this.animeTimer - objectData[15];
          if (b1 << 3 < i)
            i = b1 << 3; 
          drawRegion(gg, this.m_imgObj[101], 0, (this.animeTimer + (b1 << 1)) % 4 << 5, 24, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + (i << 1) - 56, objectData[3] - mapView[1] - this.yuka_nflag_ike_yuka[b][i], 0x1 | 0x2);
        } 
      } 
    } else if (objectData[4] == 1) {
      drawRegion(gg, this.m_imgObj[7], 0, 96, 128, 80, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
    } else {
      for (byte b1 = 0; b1 < 4; b1++)
        drawRegion(gg, this.m_imgObj[7], 0, 0, 16, 96, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32 + (b1 << 4), objectData[3] - mapView[1] - 48, 20); 
    } 
  }
  
  private void turi_nflag_draw_ikeshita(int paramInt) {
    for (byte b = 0; b <= (objectData[3] - objectData[9]) / 16; b++)
      drawRegion(gg, this.m_imgObj[94], 0, 8, 32, 16, rotNumTable[TRANS_NONE], objectData[8] - mapView[0], objectData[9] - mapView[1] - 24 + b * 16 + (objectData[3] - objectData[9]) % 16, 0x1 | 0x2); 
    drawRegion(gg, this.m_imgObj[94], 0, 0, 32, 8, rotNumTable[TRANS_NONE], objectData[8] - mapView[0], objectData[9] - mapView[1] - 24, 0x1 | 0x2);
    if (objectData[4] == 128 || objectData[4] == 2) {
      drawRegion(gg, this.m_imgObj[8], 0, 24, 112, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] + 28, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[8], 0, 0, 112, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 35) {
      drawRegion(gg, this.m_imgObj[95], 0, 0, 32, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 17 || objectData[4] == 18) {
      drawRegion(gg, this.m_imgObj[8], 0, 24, 112, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] + 28, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[8], 0, 0, 48, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 48, objectData[3] - mapView[1] - 12, 20);
      drawRegion(gg, this.m_imgObj[8], 64, 0, 48, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 12, 20);
    } 
    drawRegion(gg, this.m_imgObj[94], 0, 24, 32, 8, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
  }
  
  private void toge_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    if (objectData[19] == 2)
      i = TRANS_ROT180; 
    if (objectData[4] == 48) {
      for (byte b = 0; b < 3; b++)
        drawRegion(gg, this.m_imgObj[9], 0, 0, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - 24 + b * 24, objectData[3] - mapView[1], 0x1 | 0x2); 
    } else if (objectData[4] == 64) {
      if (this.zoneNumber != 1 || this.stageNumber != 0 || objectData[4] != 64 || objectData[19] == 0)
        for (byte b = 0; b < 6; b++)
          drawRegion(gg, this.m_imgObj[9], 16, 0, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - 60 + b * 24, objectData[3] - mapView[1], 0x1 | 0x2);  
    } else if (objectData[4] == 18 || objectData[4] == 16) {
      if (objectData[19] == 0) {
        i = TRANS_ROT270;
      } else {
        i = TRANS_ROT90;
      } 
      drawRegion(gg, this.m_imgObj[9], 0, 0, 40, 32, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 82) {
      if (objectData[19] == 0) {
        drawRegion(gg, this.m_imgObj[9], 16, 0, 8, 32, rotNumTable[TRANS_MIRROR_ROT270], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[9], 16, 0, 8, 32, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } 
    } else if (objectData[4] == 32) {
      drawRegion(gg, this.m_imgObj[9], 16, 0, 8, 32, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[9], 0, 0, 40, 32, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  private void box_sflag_draw_ikeshita(int paramInt) {
    if (objectData[4] == 129) {
      for (byte b = 0; b < 4; b++)
        drawImage(gg, this.m_imgObj[54], objectData[2] - mapView[0] - 62 + 31 * b, objectData[3] - mapView[1] - 16, 20); 
    } else {
      drawImage(gg, this.m_imgObj[54], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4);
    } 
  }
  
  private void fblock_nflag_draw_ikeshita(int paramInt) {
    drawImage(gg, this.m_imgObj[11], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    if (objectData[4] == 2 || objectData[4] == 10)
      DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4); 
  }
  
  private void dainfla_draw_ikeshita(int paramInt) {
    if (objectData[4] == 2 || objectData[4] == 1) {
      drawRegion(gg, this.m_imgObj[54], 0, 0, 31, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      if (objectData[4] == 2)
        DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4); 
    } else if (objectData[4] == 65) {
      for (byte b = 0; b < 3; b++)
        drawImage(gg, this.m_imgObj[54], objectData[2] - mapView[0] + (b << 5), objectData[3] - mapView[1], 0x1 | 0x2); 
    } else if (objectData[4] == 57) {
      for (byte b = 1; b < 15; b++)
        drawRegion(gg, this.m_imgObj[12], 0, 24, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64 + (b << 3), objectData[3] - mapView[1] - 8, 20); 
      drawRegion(gg, this.m_imgObj[12], 0, 0, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64, objectData[3] - mapView[1] - 8, 20);
      drawRegion(gg, this.m_imgObj[12], 0, 0, 8, 24, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] + 56, objectData[3] - mapView[1] - 8, 20);
    } else if (objectData[4] == 40) {
      for (byte b = 0; b < 8; b++)
        drawRegion(gg, this.m_imgObj[12], 8, b % 2 * 24, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32 + (b << 3), objectData[3] - mapView[1] - 8, 20); 
    } else if ((objectData[4] == 7 || objectData[4] == 4) && objectData[5] != 0) {
      drawRegion(gg, this.m_imgObj[12], 0, 0, 32, 16, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  private void yogan2_sflag_draw_ikeshita(int paramInt) {
    byte b;
    for (b = 0; b < 2; b++)
      drawRegion(gg, this.m_imgObj[13], 0, (this.animeTimer + b) % 3 << 5, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + (b << 5) + 48, objectData[3] - mapView[1] + (b << 5) - 16, 0x1 | 0x2); 
    int i = 0;
    for (b = 0; b < 4; b++) {
      i = objectData[2] - mapView[0] + (b >> 1 << 5) + 16;
      for (byte b1 = 0; b1 < i / 32 + 2; b1++)
        drawRegion(gg, this.m_imgObj[99], 0, (this.animeTimer + b) % 3 * 16, 32, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + (b >> 1 << 5) + 16 - (b1 << 5), objectData[3] - mapView[1] + (b << 4) - 24, 0x1 | 0x2); 
    } 
  }
  
  private void myogan_nflag_draw_ikeshita(int paramInt) {
    if (objectData[5] / 4 != 0 && objectData[3] - 32 < objectData[9])
      drawRegion(gg, this.m_imgObj[98], 0, 32 * this.animeTimer % 2, 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20); 
    int i = objectData[5] / 4;
    if (i > 6)
      i = 6; 
    byte b;
    for (b = 1; b < i; b++) {
      if (objectData[3] - b * 32 - 32 + 32 < objectData[9]) {
        drawRegion(gg, this.m_imgObj[14], 0, 32 * b % 2, 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - b * 32 - 32, 20);
      } else if (objectData[3] - b * 32 - 32 < objectData[9]) {
        drawRegion(gg, this.m_imgObj[14], 0, 32 * b % 2, 64, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - b * 32 - 32, 20);
      } 
    } 
    if (objectData[5] / 4 != 0 && objectData[3] - b * 32 - 32 < objectData[9])
      drawRegion(gg, this.m_imgObj[98], 0, 64 + 32 * this.animeTimer % 2, 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - b * 32 - 32, 20); 
    if (objectData[10] > 0 && this.myogan_nflag_ike_ani.length > objectData[10] / 3)
      drawRegion(gg, this.m_imgObj[77], 0, 32 * this.myogan_nflag_ike_ani[objectData[10] / 3], 112, 32, rotNumTable[TRANS_NONE], objectData[8] - mapView[0], objectData[9] - mapView[1], 0x1 | 0x2); 
  }
  
  private void switch2_nflag_draw_ikeshita(int paramInt) {
    if (switchflag[objectData[4]]) {
      drawRegion(gg, this.m_imgObj[15], 0, 16, 32, 8, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[15], 0, 0, 32, 11, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 3, 0x1 | 0x2);
    } 
    drawRegion(gg, this.m_imgObj[15], 0, 11, 32, 5, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] + 5, 0x1 | 0x2);
  }
  
  private void shima_nflag_draw_ikeshita(int paramInt) {
    if (this.zoneNumber != 3) {
      if (objectData[4] != 16) {
        drawRegion(gg, this.m_imgObj[16], 0, 0, 64, 32, 0, objectData[2] - mapView[0], objectData[3] - mapView[1] + objectData[14], 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[16], 0, 32, 64, 48, 0, objectData[2] - mapView[0], objectData[3] - mapView[1] + 8 + objectData[14], 0x1 | 0x2);
        for (byte b = 5; b >= 0; b--)
          drawRegion(gg, this.m_imgObj[16], 0, 64, 64, 16, 0, objectData[2] - mapView[0], objectData[3] - mapView[1] + 8 + 48 - 16 + 16 * b + objectData[14], 0x1 | 0x2); 
      } 
    } else {
      int i = this.animeTimer / 4 % 6;
      if (i > 3)
        i = 6 - i; 
      for (byte b = 0; b < 4; b++) {
        byte b1;
        for (b1 = 0; b1 < 3; b1++)
          drawRegion(gg, this.m_imgObj[73], 0, b1 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b % 2], objectData[2] - mapView[0] - 40 + b * 16 + 8, objectData[3] - mapView[1] - 8 + b1 * 8 + objectData[14], 20); 
        drawRegion(gg, this.m_imgObj[73], 0, b1 * 8 + i * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b % 2], objectData[2] - mapView[0] - 40 + b * 16 + 8, objectData[3] - mapView[1] - 8 + b1 * 8 + objectData[14], 20);
      } 
    } 
  }
  
  private void dai2_nflag_draw_ikeshita(int paramInt) {
    dai2_sflag_draw_ikeshita(paramInt);
  }
  
  private void brkabe_sflag_draw_ikeshita(int paramInt) {
    byte b = 1;
    if (objectData[4] == 0) {
      b = 0;
    } else {
      b = 1;
    } 
    if (objectData[10] != 1)
      for (byte b1 = 0; b1 < 4; b1++)
        drawRegion(gg, this.m_imgObj[18], b * 16, 0, 16, 16, 0, objectData[2] - mapView[0] - 8 - 8, objectData[3] - mapView[1] - 16 - 16 + b1 * 16, 20);  
    if (objectData[4] == 2) {
      b = 2;
    } else {
      b = 1;
    } 
    if (objectData[11] != 1)
      for (byte b1 = 0; b1 < 4; b1++)
        drawRegion(gg, this.m_imgObj[18], b * 16, 0, 16, 16, 0, objectData[2] - mapView[0] - 8 - 8 + 16, objectData[3] - mapView[1] - 16 - 16 + b1 * 16, 20);  
  }
  
  private void pedal_nflag_draw_ikeshita(int paramInt) {
    drawImage(gg, this.m_imgObj[PEDAL], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void break2_nflag_draw_ikeshita(int paramInt) {
    int i = 27;
    int j = 0;
    if (objectData[5] < 129) {
      if (this.zoneNumber == 3)
        i = STEP; 
      for (byte b = 0; b < 8; b++) {
        int k = this.break2_nflag_ike_brockTable[b];
        if (this.break2_nflag_ike_brockTable[b] % 4 >= 2)
          k -= 2; 
        if (objectData[15] != 0) {
          j = this.cpuTimer - objectData[16] - this.break2_nflag_ike_brockTimeTable[b];
          if (j < 0)
            j = 0; 
        } 
        drawRegion(gg, this.m_imgObj[i], k % 4 * 16, k / 4 * 16, 16, 16, 0, objectData[2] - mapView[0] + this.break2_nflag_ike_brockTable[b] % 4 * 16 - 16 - 8, objectData[3] - mapView[1] + this.break2_nflag_ike_brockTable[b] / 4 * 16 + j * j / 5, 0x1 | 0x2);
      } 
    } 
  }
  
  private void step_nflag_draw_ikeshita(int paramInt) {
    byte b2 = 0;
    if (objectData[18] > 0)
      b2 = 1; 
    int i = this.animeTimer / 4 % 6;
    if (i > 3)
      i = 6 - i; 
    for (byte b1 = 0; b1 < 8; b1++) {
      int j = objectData[5];
      if (j > 60) {
        j -= 60;
      } else {
        j = 0;
      } 
      int k = j / 4 * (b1 + 2) / 2;
      if (objectData[19] == 0)
        k = j / 4 * (4 - b1 / 2); 
      byte b;
      for (b = 0; b < 3; b++)
        drawRegion(gg, this.m_imgObj[73], 0, b * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b1 % 2], objectData[2] - mapView[0] - 16 + b1 * 16, objectData[3] - mapView[1] - 16 + b * 8 + k + b2 * this.step_nflag_ike_gura[this.animeTimer % 2][b1 / 2 % 2], 20); 
      drawRegion(gg, this.m_imgObj[73], 0, b * 8 + i * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b1 % 2], objectData[2] - mapView[0] - 16 + b1 * 16, objectData[3] - mapView[1] - 16 + b * 8 + k + b2 * this.step_nflag_ike_gura[this.animeTimer % 2][b1 / 2 % 2], 20);
    } 
  }
  
  private void fun_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    int j = 0;
    if (objectData[19] != 0)
      i = TRANS_MIRROR; 
    j = objectData[2] - PlayerPosX();
    drawRegion(gg, this.m_imgObj[22], 0, objectData[5] % 3 * 32, 32, 32, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void pata_nflag_draw_ikeshita(int paramInt) {
    if ((((objectData[4] != 1) ? 1 : 0) & ((objectData[4] != 2) ? 1 : 0)) != 0) {
      byte b = 0;
      if (objectData[13] < 8) {
        switch (objectData[13] / 2) {
          default:
            b = 0;
            break;
          case 1:
            b = 5;
            break;
          case 2:
            b = 3;
            break;
          case 3:
            b = 6;
            break;
        } 
        if (objectData[13] % 2 == 0) {
          drawRegion(gg, this.m_imgObj[BELTC], 0, 1, 32, 14, b, objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
        } else {
          drawRegion(gg, this.m_imgObj[BELTC], 0, 17, 32, 30, b, objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
        } 
      } else {
        drawRegion(gg, this.m_imgObj[BELTC], 0, 1, 32, 14, b, objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] - 7, 20);
      } 
    } else {
      pata_draw(paramInt);
    } 
  }
  
  private void fire6_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    int j = 0;
    if (objectData[19] == 1) {
      i = TRANS_MIRROR;
    } else if (objectData[19] == 2) {
      i = TRANS_ROT180;
    } 
    if (objectData[19] == 0) {
      drawRegion(gg, this.m_imgObj[26], 24, 0, 8, 16, rotNumTable[i], objectData[2] - mapView[0] + 1 - 3 + 3 - 2, objectData[3] - mapView[1] - 8 + 52 + 8 + 4 - 8, 0x1 | 0x2);
    } else if (objectData[19] == 1) {
      drawRegion(gg, this.m_imgObj[26], 24, 0, 8, 16, rotNumTable[i], objectData[2] - mapView[0] + 1 - 3 + 3 - 2 + 2, objectData[3] - mapView[1] - 8 + 52 + 8 + 4 - 8, 0x1 | 0x2);
    } 
    int k = 5;
    k = objectData[5] / 4;
    if (k % 30 < 6) {
      k %= 30;
    } else if (k % 30 > 20 && k % 30 <= 25) {
      k = 5 - (k - 20) % 30;
    } else if (k % 30 >= 25) {
      k = 0;
    } else {
      k = 5;
    } 
    for (byte b = 0; b < k; b++) {
      j += this.fire6_nflag_ike_sizeTable2[4 - b] - this.fire6_nflag_ike_posTable[4 - b];
      if (objectData[19] != 2) {
        drawRegion(gg, this.m_imgObj[26], 0, this.fire6_nflag_ike_animeTable[4 - b], 24, this.fire6_nflag_ike_sizeTable[4 - b], rotNumTable[this.fire6_nflag_ike_rotTable[objectData[19]][this.animeTimer / 2 % 2]], objectData[2] - mapView[0] - 10 + 3 + 7, objectData[3] - mapView[1] - 8 + 52 - j + 5, 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[26], 0, this.fire6_nflag_ike_animeTable[4 - b], 24, this.fire6_nflag_ike_sizeTable[4 - b], rotNumTable[this.fire6_nflag_ike_rotTable[objectData[19]][this.animeTimer / 2 % 2]], objectData[2] - mapView[0] - 10 - 2 + 3 + 12, objectData[3] - mapView[1] - 8 - 52 + j + 18, 0x1 | 0x2);
      } 
    } 
    if (objectData[19] == 2) {
      drawRegion(gg, this.m_imgObj[26], 24, 32, 8, 16, rotNumTable[i], objectData[2] - mapView[0] + 4 + 3, objectData[3] - mapView[1] - 54 + 8 - 8 + 5, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[26], 24, 48, 8, 16, rotNumTable[i], objectData[2] - mapView[0] - 8 + 4 + 3, objectData[3] - mapView[1] - 54 + 8 - 8 + 5, 0x1 | 0x2);
    } 
  }
  
  private void bryuka_nflag_draw_ikeshita(int paramInt) {
    drawRegion(gg, this.m_imgObj[27], 0, 0, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void mawaru_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    drawRegion(gg, this.m_imgObj[28], 0, 0, 94, 94, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 1, objectData[3] - mapView[1] - 1, 0x1 | 0x2);
    switch (objectData[15] / 2) {
      default:
        b = 0;
        break;
      case 1:
        b = 6;
        break;
      case 2:
        b = 3;
        break;
      case 3:
        b = 5;
        break;
    } 
    if (objectData[15] % 2 == 0) {
      drawRegion(gg, this.m_imgObj[28], 25, 95, 48, 40, b, objectData[2] - mapView[0] + objectData[10] - 1, objectData[3] - mapView[1] + objectData[11] - 1, 20);
    } else {
      drawRegion(gg, this.m_imgObj[28], 24, 135, 47, 47, b, objectData[2] - mapView[0] + objectData[10] - 1, objectData[3] - mapView[1] + objectData[11] - 1, 20);
    } 
  }
  
  private void yukai_nflag_draw_ikeshita(int paramInt) {
    for (byte b = 1; b < 11; b++)
      drawRegion(gg, this.m_imgObj[29], 0, 48, 16, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96 + 16 * b, objectData[3] - mapView[1] - 24, 20); 
    drawRegion(gg, this.m_imgObj[29], 0, 0, 16, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96, objectData[3] - mapView[1] - 24, 20);
    drawRegion(gg, this.m_imgObj[29], 0, 0, 16, 48, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] - 96 + 176, objectData[3] - mapView[1] - 24, 20);
  }
  
  private void door_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    byte b = 0;
    if (objectData[19] != 0) {
      i = TRANS_MIRROR;
      b = 8;
    } 
    drawRegion(gg, this.m_imgObj[30], 0, objectData[15] % 4 * 64, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - 8 + b, objectData[3] - mapView[1] - 32 - objectData[10] * 8, 20);
    drawRegion(gg, this.m_imgObj[30], 0, 32 + objectData[15] % 4 * 64, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - 8 + b, objectData[3] - mapView[1] + objectData[10] * 8, 20);
    drawRegion(gg, this.m_imgObj[30], 8, objectData[5] % 4 * 64, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - b, objectData[3] - mapView[1] - 32 - objectData[10] * 8, 20);
    drawRegion(gg, this.m_imgObj[30], 8, 32 + objectData[5] % 4 * 64, 8, 32, rotNumTable[i], objectData[2] - mapView[0] - b, objectData[3] - mapView[1] + objectData[10] * 8, 20);
  }
  
  private void yukae_nflag_draw_ikeshita(int paramInt) {
    int i = 16;
    byte b = 0;
    int j = 0;
    j = (objectData[5] - objectData[4] / 2 + 256) % 128;
    if (j < 128) {
      if (j < 16) {
        i = j;
      } else if (j > 64 && j < 80) {
        i = 80 - j;
      } else if (j >= 80) {
        i = -1;
      } 
      if (i > 0) {
        b = 0;
        if (i <= 8 && i > 4) {
          b = 1;
        } else if (i <= 4) {
          b = 2;
        } 
        drawRegion(gg, this.m_imgObj[31], 0, b * 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 16, objectData[3] + 8 - mapView[1] - 16, 20);
      } 
    } 
  }
  
  private void dai4_nflag_draw_ikeshita(int paramInt) {
    if (objectData[4] == 203) {
      drawRegion(gg, this.m_imgObj[32], 0, 0, 256, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 64 + 16, 0x1 | 0x2);
      for (byte b = 0; b < 3; b++) {
        for (int i = 2 - b; i >= 0; i--) {
          if (i != 2 - b) {
            drawRegion(gg, this.m_imgObj[32], 256, 0, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + 64 * b - 128 + 16, objectData[3] - mapView[1] - 64 + 16 + 32 + i * 32, 0x1 | 0x2);
          } else {
            drawRegion(gg, this.m_imgObj[32], 288, 0, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + 64 * b - 128 + 16, objectData[3] - mapView[1] - 64 + 16 + 32 + i * 32, 0x1 | 0x2);
          } 
        } 
      } 
    } else if (objectData[4] != 64) {
      if (objectData[4] >= 128) {
        drawRegion(gg, this.m_imgObj[32], 0, 64, 64, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64, objectData[3] - mapView[1] - 12, 20);
        drawRegion(gg, this.m_imgObj[32], 0, 64, 64, 24, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0], objectData[3] - mapView[1] - 12, 20);
      } else {
        drawRegion(gg, this.m_imgObj[32], 0, 0, 56, 64, rotNumTable[TRANS_NONE + 6], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } 
    } 
  }
  
  private void ele_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    byte b = 1;
    if (objectData[19] == 2) {
      i = TRANS_ROT180;
      b = -1;
    } 
    drawRegion(gg, this.m_imgObj[33], 0, 88 + this.animeTimer % 3 * 8, 16, 8, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1] + 20 * b, 0x1 | 0x2);
    drawRegion(gg, this.m_imgObj[33], 0, 0, 16, 16, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1] + 8 * b, 0x1 | 0x2);
    if (objectData[5] == 1 || objectData[5] == 2) {
      drawRegion(gg, this.m_imgObj[33], 0, 40, 16, 16, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1] - 4 * b + 4 * b, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[33], 0, 16 + this.animeTimer % 3 * 8, 16, 8, rotNumTable[i], objectData[2] - mapView[0], objectData[3] - mapView[1] - 4 * b, 0x1 | 0x2);
    } 
    if (objectData[5] >= 2)
      for (byte b1 = 0; b1 <= 1; b1++) {
        if (this.ele_nflag_ike_anime[objectData[5] - 2][b1] != 0) {
          drawRegion(gg, this.m_imgObj[33], 0, 72, 16, 16, rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][0]], objectData[2] - mapView[0] + 16 + b1 * 32, objectData[3] - mapView[1] - 4 * b, 0x1 | 0x2);
          drawRegion(gg, this.m_imgObj[33], 0, 56, 16, 16, rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][0]], objectData[2] - mapView[0] + 32 + b1 * 32, objectData[3] - mapView[1] - 4 * b, 0x1 | 0x2);
          drawRegion(gg, this.m_imgObj[33], 0, 72, 16, 16, rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][1]], objectData[2] - mapView[0] - 16 - b1 * 32, objectData[3] - mapView[1] - 4 * b, 0x1 | 0x2);
          drawRegion(gg, this.m_imgObj[33], 0, 56, 16, 16, rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][1]], objectData[2] - mapView[0] - 32 - b1 * 32, objectData[3] - mapView[1] - 4 * b, 0x1 | 0x2);
        } 
      }  
  }
  
  private void beltc_nflag_draw_ikeshita(int paramInt) {
    int i = 0;
    boolean bool = false;
    int j = 0;
    byte b2 = 0;
    int[] arrayOfInt = new int[(this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 + 1];
    arrayOfInt[0] = 0;
    byte b1;
    for (b1 = 0; b1 < (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b1++) {
      for (byte b = 0; b < 2; b++) {
        this.beltc_nflag_ike_startPos[b] = 0;
        this.beltc_nflag_ike_endPos[b] = 0;
      } 
      this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b1 * 2 + 0];
      this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b1 * 2 + 1];
      this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b1 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
      this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b1 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
      if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
        i += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
      } else {
        i += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
      } 
      arrayOfInt[b1 + 1] = i;
    } 
    int k = i / 69;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    for (b1 = 0; b1 < k; b1++) {
      m = (b1 * 69 + this.cpuTimer) % i;
      for (byte b = 0; b < (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b++) {
        if (m < arrayOfInt[b + 1]) {
          n = 0;
          i1 = 0;
          i2 = m - arrayOfInt[b + 1];
          for (byte b3 = 0; b3 < 2; b3++) {
            this.beltc_nflag_ike_startPos[b3] = 0;
            this.beltc_nflag_ike_endPos[b3] = 0;
          } 
          this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b * 2 + 0];
          this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][b * 2 + 1];
          this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
          this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(b + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
          if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
            n = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * i2 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
            i1 = this.beltc_nflag_ike_endPos[1] + i2 * (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
          } else {
            n = this.beltc_nflag_ike_endPos[0] + i2 * (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
            i1 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * i2 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
          } 
          if (b == 0 || b == 3) {
            drawRegion(gg, this.m_imgObj[BELTC], 0, 1, 32, 14, 0, n - mapView[0], i1 - mapView[1], 0x1 | 0x2);
            break;
          } 
          j = 0;
          if (b == 1) {
            j = Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][3] - i1);
          } else {
            j = Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][3] - this.beltc_nflag_ike_objectPos[objectData[4] - 128][5]) + Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][2] - n);
          } 
          j = (j / 4 + 1) % 8;
          switch (j / 2) {
            default:
              b2 = 0;
              break;
            case 1:
              b2 = 5;
              break;
            case 2:
              b2 = 3;
              break;
            case 3:
              b2 = 6;
              break;
          } 
          if (j % 2 == 0) {
            drawRegion(gg, this.m_imgObj[BELTC], 0, 1, 32, 14, b2, n - mapView[0], i1 - mapView[1], 0x1 | 0x2);
            break;
          } 
          drawRegion(gg, this.m_imgObj[BELTC], 0, 17, 32, 30, b2, n - mapView[0], i1 - mapView[1], 0x1 | 0x2);
          break;
        } 
      } 
    } 
  }
  
  private void noko_nflag_draw_ikeshita(int paramInt) {
    if (objectData[10] != 0) {
      drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20);
      drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0], objectData[3] - mapView[1] - 32, 20);
      drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT270], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1], 20);
      drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1], 20);
      if (objectData[4] != 3)
        drawRegion(gg, this.m_imgObj[35], 32, 0, 8, 64, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - 62, 20); 
    } 
  }
  
  private void save_sflag_draw_ikeshita(int paramInt) {
    if (objectData[5] == 0) {
      drawRegion(gg, this.m_imgObj[36], 0, 16, 16, 16, rotNumTable[TRANS_NONE + 4], objectData[2] - mapView[0], objectData[3] - mapView[1] - 32 - 4, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[36], 0, 0, 16, 16, rotNumTable[TRANS_NONE + 4], objectData[2] - mapView[0] - dSin(90 * objectData[5] / 4) * 8 / 100, objectData[3] - mapView[1] - 24 + dCos(90 * objectData[5] / 4) * 8 / 100 - 4, 0x1 | 0x2);
    } 
    drawRegion(gg, this.m_imgObj[36], 0, 32, 16, 48, rotNumTable[TRANS_NONE + 4], objectData[2] - mapView[0], objectData[3] - mapView[1] - 4, 0x1 | 0x2);
  }
  
  private void kageb_nflag_draw_ikeshita(int paramInt) {
    byte b1 = 0;
    byte b2 = 64;
    if (objectData[4] == 1) {
      b1 = 1;
    } else if (objectData[4] == 2) {
      b1 = 2;
    } else if (objectData[4] == 16) {
      b1 = 3;
    } else if (objectData[4] == 17) {
      b1 = 4;
    } else if (objectData[4] == 18) {
      b1 = 5;
    } 
    drawRegion(gg, this.m_imgObj[37], b1 * 16, 0, 16, b2, 0, objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void item_nflag_draw_ikeshita(int paramInt) {
    int i;
    byte b1 = 32;
    byte b2 = 0;
    if (objectData[4] >= 7)
      return; 
    if (this.item_nflag_ike_itemTable[objectData[4]] == 8) {
      b1 = 16;
      b2 = 8;
    } 
    if (objectData[5] != 0 && objectData[7] < 60) {
      if (objectData[5] >= 7)
        return; 
      i = objectData[7];
      if (i >= 48)
        i = 48; 
      drawRegion(gg, this.m_imgObj[42], 8, this.item_nflag_ike_itemTable[objectData[5]] * 32 + 6, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - i, 0x1 | 0x2);
    } 
    if (this.item_nflag_ike_itemTable[objectData[4]] != 8) {
      if (this.animeTimer % 2 == 0) {
        i = this.item_nflag_ike_itemTable[objectData[4]] * 32;
      } else {
        i = (5 + this.animeTimer % 6 / 2) * 32;
      } 
    } else {
      i = this.item_nflag_ike_itemTable[objectData[4]] * 32;
    } 
    drawRegion(gg, this.m_imgObj[42], 0, i, 32, b1, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] + b2, 0x1 | 0x2);
  }
  
  private void item_sflag_draw_ikeshita(int paramInt) {
    item_nflag_draw_ikeshita(paramInt);
  }
  
  private void gole_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    if (objectData[10] / 3 > 22)
      b = 48; 
    if (objectData[5] != 2) {
      drawRegion(gg, this.m_imgObj[44], 0, b + objectData[10] / 3 % 4 * 48, 48, 48, 0, objectData[2] - mapView[0], objectData[3] - mapView[1] + 10, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[44], 0, 192, 48, 48, 0, objectData[2] - mapView[0], objectData[3] - mapView[1] + 10, 0x1 | 0x2);
    } 
    if (objectData[5] == 1)
      drawRegion(gg, this.m_imgObj[0], 16, objectData[10] / 4 % 4 * 16, 16, 16, this.gole_nflag_ike_rotTable[objectData[10] / 2 % 4], objectData[2] - mapView[0] + this.gole_nflag_ike_kiraTableX[objectData[10] / 4 % 10] - 24 - 8, objectData[3] - mapView[1] + 10 + this.gole_nflag_ike_kiraTableY[objectData[10] / 4 % 10] - 24 - 8, 20); 
  }
  
  private void bten_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    if (objectData[4] == 0 || objectData[4] == 3) {
      b = 0;
    } else if (objectData[4] == 1) {
      b = 48;
    } else {
      b = 24;
    } 
    if (objectData[5] == 1)
      drawRegion(gg, this.m_imgObj[45], 0, b, 32, 24, 0, objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2); 
  }
  
  private void bten_sflag_draw_ikeshita(int paramInt) {
    bten_nflag_draw_ikeshita(paramInt);
  }
  
  private void bigring_nflag_draw_ikeshita(int paramInt) {}
  
  private void masin_nflag_draw_ikeshita(int paramInt) {
    if (objectData[4] == 1)
      if (objectData[5] < 2) {
        drawRegion(gg, this.m_imgObj[55], this.animeTimer % 2 * 24, 96, 24, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
        drawRegion(gg, this.m_imgObj[55], 0, 0, 64, 64, rotNumTable[TRANS_NONE], objectData[8] - mapView[0], objectData[9] - mapView[1] + 37, 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[55], 0, 64, 64, 32, rotNumTable[TRANS_NONE], objectData[8] - mapView[0], objectData[9] - mapView[1] + 16 + 37, 0x1 | 0x2);
      }  
  }
  
  private void bobin_sflag_draw_ikeshita(int paramInt) {
    drawRegion(gg, this.m_imgObj[56], 0, 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void jyama_nflag_draw_ikeshita(int paramInt) {
    drawImage(gg, this.m_imgObj[58], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void fetama_nflag_draw_ikeshita(int paramInt) {
    drawRegion(gg, this.m_imgObj[60], 0, 0, 48, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private static void vect(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 < 0)
      paramInt1 += 360; 
    if (360 >= paramInt1)
      paramInt1 -= paramInt1 / 360 * 360; 
    if (paramInt1 <= 90) {
      objectData[paramInt2] = sinData[90 - paramInt1];
      objectData[paramInt3] = sinData[paramInt1];
    } 
    if (paramInt1 > 90 && paramInt1 <= 180) {
      objectData[paramInt2] = -sinData[90 - 180 - paramInt1];
      objectData[paramInt3] = sinData[180 - paramInt1];
    } 
    if (paramInt1 > 180 && paramInt1 <= 270) {
      objectData[paramInt2] = -sinData[90 - paramInt1 - 180];
      objectData[paramInt3] = -sinData[paramInt1 - 180];
    } 
    if (paramInt1 > 270 && paramInt1 < 360) {
      objectData[paramInt2] = sinData[90 - 360 - paramInt1];
      objectData[paramInt3] = -sinData[360 - paramInt1];
    } 
  }
  
  private void tekyu_nflag_draw_ikeshita(int paramInt) {
    int i = 0;
    byte b1 = 0;
    byte b2 = 0;
    i = this.animeTimer - objectData[5];
    if (objectData[4] == 213 || objectData[4] == 181 || objectData[4] == 197 || objectData[4] == 101 || objectData[4] == 69 || objectData[4] == 53) {
      b1 = 5;
    } else if (objectData[4] == 212 || objectData[4] == 196 || objectData[4] == 84 || objectData[4] == 68 || objectData[4] == 52) {
      b1 = 4;
    } else if (objectData[4] == 38) {
      b1 = 6;
    } else if (objectData[4] == 195) {
      b1 = 3;
    } 
    if (objectData[4] == 181 || objectData[4] == 101) {
      b2 = 12;
    } else if (objectData[4] == 196 || objectData[4] == 197 || objectData[4] == 84 || objectData[4] == 195) {
      b2 = 10;
    } else if (objectData[4] == 213 || objectData[4] == 212 || objectData[4] == 69 || objectData[4] == 68) {
      b2 = 8;
    } else if (objectData[4] == 52 || objectData[4] == 53) {
      b2 = 6;
    } else if (objectData[4] == 38) {
      b2 = 4;
    } 
    if (objectData[4] != 69 && objectData[4] != 84 && objectData[4] != 101 && objectData[4] != 38 && objectData[4] != 68 && objectData[4] != 52 && objectData[4] != 53) {
      vect((360 / b2 - i % 360 / b2) * b2, 16, 17);
    } else {
      vect(360 - (360 / b2 - i % 360 / b2) * b2 % 360, 16, 17);
    } 
    if (objectData[4] == 84) {
      vect(i % 360 / b2 * b2, 16, 17);
    } else if (objectData[19] == 1) {
      vect(i % 360 / b2 * b2, 16, 17);
    } 
    if (this.zoneNumber != 4) {
      byte b;
      for (b = 1; b < b1; b++)
        drawRegion(gg, this.m_imgObj[60], 0, 64, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + b * objectData[16] * 16 / 10000, objectData[3] - mapView[1] + b * objectData[17] * 16 / 10000, 0x1 | 0x2); 
      drawRegion(gg, this.m_imgObj[60], 0, 80, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[60], 16, 48, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + b * objectData[16] * 16 / 10000, objectData[3] - mapView[1] + b * objectData[17] * 16 / 10000, 0x1 | 0x2);
    } else {
      byte b;
      for (b = 1; b < b1; b++)
        drawRegion(gg, this.m_imgObj[60], 0, 48, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + b * objectData[16] * 16 / 10000, objectData[3] - mapView[1] + b * objectData[17] * 16 / 10000, 0x1 | 0x2); 
      drawRegion(gg, this.m_imgObj[60], 0, 48, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[60], 0, 48, 16, 16, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] + b * objectData[16] * 16 / 10000, objectData[3] - mapView[1] + b * objectData[17] * 16 / 10000, 0x1 | 0x2);
    } 
  }
  
  private void dai2_sflag_draw_ikeshita(int paramInt) {
    if (objectData[4] >= 240) {
      for (byte b = 0; b < 4; b++)
        drawImage(gg, this.m_imgObj[108], objectData[2] - mapView[0] - 48 + b * 32, objectData[3] - mapView[1], 0x1 | 0x2); 
    } else if (objectData[4] >= 224) {
      drawImage(gg, this.m_imgObj[107], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] <= 2) {
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] - 16, 20);
    } else if (objectData[4] == 19) {
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20);
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 0, objectData[3] - mapView[1] - 32, 20);
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 0, 20);
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 0, objectData[3] - mapView[1] - 0, 20);
    } else if (objectData[4] <= 91 && objectData[4] >= 88) {
      drawImage(gg, this.m_imgObj[STEP], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 160) {
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0], objectData[3] - mapView[1] - 16, 0x1 | 0x2);
      drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0], objectData[3] - mapView[1] + 16, 0x1 | 0x2);
    } else if (objectData[4] == 55 && (objectData[8] != 7992 || objectData[9] != 1353)) {
      drawImage(gg, this.m_imgObj[107], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  private void ring_sflag_ring_m10_10_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_10_10_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_20_20_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_10_00_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_20_00_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_00_10_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void ring_sflag_ring_00_20_draw_ikeshita(int paramInt) {
    ring_sflag_ring_18_00_draw_ikeshita(paramInt);
  }
  
  private void elev_nflag_80_draw_ikeshita(int paramInt) {
    elev_nflag_draw_ikeshita(paramInt);
  }
  
  private void elev_nflag_draw_ikeshita(int paramInt) {
    byte b1 = 40;
    byte b2 = 16;
    int i = this.animeTimer / 4 % 6;
    if (i > 3)
      i = 6 - i; 
    if (objectData[4] != 16) {
      for (byte b = 0; b < 5; b++) {
        byte b3;
        for (b3 = 0; b3 < 3; b3++)
          drawRegion(gg, this.m_imgObj[73], 16, b3 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b % 2], objectData[2] - mapView[0] - 40 + b * 16, objectData[3] - mapView[1] - 8 + b3 * 8, 20); 
        drawRegion(gg, this.m_imgObj[73], 16, b3 * 8 + i * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b % 2], objectData[2] - mapView[0] - 40 + b * 16, objectData[3] - mapView[1] - 8 + b3 * 8, 20);
      } 
    } else {
      int j = 0;
      for (byte b = 0; b < 3; b++) {
        j = objectData[3] - (objectData[5] + 1 + b * 128) % 384;
        for (byte b3 = 0; b3 < 5; b3++) {
          byte b4;
          for (b4 = 0; b4 < 3; b4++)
            drawRegion(gg, this.m_imgObj[73], 16, b4 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b3 % 2], objectData[2] - mapView[0] - 40 + b3 * 16, j - mapView[1] - 8 + b4 * 8, 20); 
          drawRegion(gg, this.m_imgObj[73], 16, b4 * 8 + i * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * b3 % 2], objectData[2] - mapView[0] - 40 + b3 * 16, j - mapView[1] - 8 + b4 * 8, 20);
        } 
      } 
    } 
  }
  
  private void mfire_nflag_draw_ikeshita(int paramInt) {
    int i = TRANS_NONE;
    byte b = -25;
    if (this.zoneNumber == 3) {
      if (objectData[4] == 54) {
        i = TRANS_MIRROR;
        b = 25;
      } 
      drawRegion(gg, this.m_imgObj[75], 0, 0, 16, 32, rotNumTable[i], objectData[2] - mapView[0] + b, objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  private void yoganc_nflag_draw_ikeshita(int paramInt) {
    int i = objectData[5] / 4;
    if (objectData[4] == 16 && i > 0) {
      if (i < 6) {
        i %= 2;
      } else {
        i = i % 2 + 2;
      } 
      byte b;
      for (b = 0; b < (objectData[9] - objectData[3]) / 32; b++)
        drawRegion(gg, this.m_imgObj[14], 0, 32 * b % 2, 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 + 24 - 32, objectData[3] - mapView[1] - 48 + b * 32 + 16, 20); 
      int j = (objectData[9] - objectData[3]) % 32;
      if (j > 0)
        drawRegion(gg, this.m_imgObj[14], 0, 32 * b % 2, 64, j, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 + 24 - 32, objectData[3] - mapView[1] - 48 + b * 32 + 16, 20); 
      drawRegion(gg, this.m_imgObj[77], 0, this.yoganc_nflag_ike_posY[i], 112, this.yoganc_nflag_ike_posY[i + 1] - this.yoganc_nflag_ike_posY[i], rotNumTable[TRANS_NONE], objectData[8] - mapView[0] - 20 - 32, objectData[9] - mapView[1] - 48, 20);
      drawRegion(gg, this.m_imgObj[77], 0, this.yoganc_nflag_ike_posY[i + 2], 112, this.yoganc_nflag_ike_posY[i + 1 + 2] - this.yoganc_nflag_ike_posY[i + 2], rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 - 32, objectData[3] - mapView[1] - 48, 20);
    } 
  }
  
  private void ochi_nflag_draw_ikeshita(int paramInt) {
    byte b1 = 32;
    byte b2 = 72;
    boolean bool1 = false;
    int i = 0;
    int j = 0;
    boolean bool2 = false;
    byte b = 0;
    if (objectData[4] == 1) {
      b = 16;
      i = dSin(objectData[5]) * b / 100 - b;
      b = -9;
      j = dSin(objectData[5]) * b / 100 + b;
      i *= 2;
      j *= 2;
      i += 2;
      j += 2;
    } else if (objectData[4] == 2) {
      b = -16;
      i = dSin(objectData[5]) * b / 100 + b;
      b = 9;
      j = dSin(objectData[5]) * b / 100 - b;
      i *= 2;
      j *= 2;
      i += 2;
      j += 2;
    } 
    if (objectData[4] == 20 || objectData[4] == 4) {
      b = 28;
      j = dSin(this.cpuTimer) * b / 100 - 28;
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 56, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] - 28 + 16, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 56, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] + 16, objectData[3] - mapView[1] - 28 + 16, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 56, rotNumTable[TRANS_MIRROR_ROT180], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] + 28 + 16, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 56, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0] + 16, objectData[3] - mapView[1] + 28 + 16, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 72, 32, 32, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1] + j + 28 + 16, 0x1 | 0x2);
    } else {
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 72, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] + i - 36, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 72, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] + 16, objectData[3] - mapView[1] + i - 36, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 72, rotNumTable[TRANS_MIRROR_ROT180], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] + i + 36, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 0, 32, 72, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0] + 16, objectData[3] - mapView[1] + i + 36, 0x1 | 0x2);
      drawRegion(gg, this.m_imgObj[79], 0, 72, 32, 32, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1] + j - 8 - 6, 0x1 | 0x2);
    } 
  }
  
  private void yari_sflag_draw_ikeshita(int paramInt) {
    if (objectData[4] == 2) {
      if (objectData[19] == 0) {
        drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8 + 8, 20);
      } else {
        drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 6], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - 8, 20);
      } 
    } else if (objectData[4] == 0) {
      if (objectData[19] == 0) {
        drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 1], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 4, 20);
      } else {
        drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 3], objectData[2] - mapView[0] + 8 - this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, objectData[3] - mapView[1] - 4, 20);
      } 
    } 
  }
  
  private void kazari_sflag_draw_ikeshita(int paramInt) {
    drawRegion(gg, this.m_imgObj[82], 0, 0, 32, 32, rotNumTable[TRANS_NONE + 4 - objectData[19] * 4], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
  }
  
  private void dai3_nflag_draw_ikeshita(int paramInt) {
    if (objectData[4] == 1) {
      drawImage(gg, this.m_imgObj[108], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 39) {
      drawImage(gg, this.m_imgObj[105], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else if (objectData[4] == 19) {
      drawImage(gg, this.m_imgObj[106], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } else {
      drawImage(gg, this.m_imgObj[83], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
    } 
  }
  
  private void kassya_nflag_draw_ikeshita(int paramInt) {
    if (objectData[4] != 127) {
      int i = 0;
      int[] arrayOfInt = new int[(this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2 + 1];
      arrayOfInt[0] = 0;
      byte b;
      for (b = 0; b < (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b++) {
        for (byte b1 = 0; b1 < 2; b1++) {
          this.beltc_nflag_ike_startPos[b1] = 0;
          this.beltc_nflag_ike_endPos[b1] = 0;
        } 
        this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b * 2 + 0];
        this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b * 2 + 1];
        this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
        this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
        if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
          i += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
        } else {
          i += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
        } 
        arrayOfInt[b + 1] = i;
      } 
      int j = i / 132;
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      for (b = 0; b < j; b++) {
        k = (b * 132 + this.cpuTimer) % i;
        if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
          k = i - k;
          if (k == i)
            k = 0; 
        } 
        for (byte b1 = 0; b1 < (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2; b1++) {
          if (k < arrayOfInt[b1 + 1]) {
            m = 0;
            n = 0;
            i1 = k - arrayOfInt[b1 + 1];
            for (byte b2 = 0; b2 < 2; b2++) {
              this.beltc_nflag_ike_startPos[b2] = 0;
              this.beltc_nflag_ike_endPos[b2] = 0;
            } 
            this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b1 * 2 + 0];
            this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][b1 * 2 + 1];
            this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b1 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 0];
            this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(b1 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128]).length / 2 * 2 + 1];
            if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
              m = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * i1 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
              n = this.beltc_nflag_ike_endPos[1] + i1 * (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
            } else {
              m = this.beltc_nflag_ike_endPos[0] + i1 * (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
              n = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * i1 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
            } 
            drawRegion(gg, this.m_imgObj[88], 0, 128, 32, 16, rotNumTable[TRANS_NONE], m - 16 - mapView[0], n - 8 - mapView[1], 20);
            break;
          } 
        } 
      } 
    } 
    if (objectData[4] == 127)
      if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
        drawRegion(gg, this.m_imgObj[88], 0, 96 - 32 * this.animeTimer % 4, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      } else {
        drawRegion(gg, this.m_imgObj[88], 0, 32 * this.animeTimer % 4, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1], 0x1 | 0x2);
      }  
  }
  
  private void shima2_nflag_draw_ikeshita(int paramInt) {
    shima_nflag_draw_ikeshita(paramInt);
  }
  
  private void bou_nflag_draw_ikeshita(int paramInt) {
    byte b = 0;
    if (objectData[10] >= 300)
      b = 1; 
    drawRegion(gg, this.m_imgObj[91], 0, b * 64, 8 + b * 8, 64, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - b * 4, objectData[3] - mapView[1], 0x1 | 0x2);
  }
}


/* Location:              /home/kilo/Downloads/Sonic 1 J2ME Decomp/!/MainCanvas.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */