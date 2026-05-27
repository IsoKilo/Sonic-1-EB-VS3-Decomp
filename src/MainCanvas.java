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
   private static int[][] objAwaData;
   private static int[][] objData;
   private static boolean initDisplay;
   private static boolean readStageObjectFlag;
   private static boolean raidOn;
   private static boolean[] switchflag;
   private static int raidObjectNum;
   private static int raidObjectNumSub;
   private static int PlayerH;
   private static int SONIC_N;
   private static int SONIC_S;
   private static int LOGO;
   private static int LOGOLINE;
   private static int SYSTXT;
   private static int WINDOW_RING;
   private static int WINDOW_TIME;
   private static int WINDOW_ZANKI;
   private static int WINDOU_SUUJI;
   private static int SYSSCORE;
   private static int SYSTXT2;
   private static int T_CUR1;
   private static int T_CUR2;
   private static int GAMEOVER;
   private static int TIMEOVER;
   private static int RING;
   private static int RING1;
   private static int SJUMP;
   private static int BURANKO;
   private static int HASHI;
   private static int TOGE_HASHI;
   private static int BREAK;
   private static int YUKA;
   private static int TURI;
   private static int TOGE;
   private static int BOX;
   private static int FBLOCK;
   private static int DAI;
   private static int YOGAN;
   private static int SWITCH2;
   private static int SHIMA;
   private static int DAI2;
   private static int BRKABE;
   private static int PEDAL;
   private static int BREAK2;
   private static int STEP;
   private static int FUN;
   private static int SISOO;
   private static int BELT;
   private static int PATA;
   private static int FIRE6;
   private static int SWITCH2_;
   private static int MAWARU;
   private static int YUKAI;
   private static int DOOR;
   private static int YUKAE;
   private static int DAI4;
   private static int ELE;
   private static int BELTC;
   private static int NOKO;
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
   private static int[] objectDrawList;
   private static int objectDrawCount;
   private static int MapW;
   private static int MapH;
   private static int mode;
   private static int BossFirst;
   private static int Target;
   private static int connCount;
   private static int connPos;
   private static int comSel;
   private static int Window;
   private static int[] mapOxy;
   private static int[] oldMapOxy;
   private static int[] mapView;
   private static int[] mapViewTarget;
   private static int[] mapOfs;
   private static int[] mapOfsTarget;
   private static byte[] mapData;
   private static byte[] mapFrontData;
   private static byte[] blockLinkTable;
   private static byte[] blockColTable;
   private static byte[][] zoneActTable;
   private static int[] zoneActTable2;
   private static byte[][] tempWorldMapData;
   private static final byte[][][][] worldMapData;
   private static byte[] scddirtbl;
   private static boolean pauseGame;
   private static int[] PlayerParam;
   private static boolean PlayerSJump;
   private static boolean PlayerDamage;
   private static boolean PlayerWater;
   private static boolean PlayerSWater;
   private static boolean PlayerBou;
   private static boolean PlayerJump;
   private static boolean PlayerAir;
   private static boolean PlayerBall;
   private static boolean PlayerDie;
   private static boolean PlayerCrouch;
   private static boolean PlayerLookUp;
   private static boolean PlayerNoCol;
   private static boolean PlayerNoCtrl;
   private static boolean TimerClear;
   private static boolean TimerStop;
   private static boolean PlayerDush;
   private boolean[] crushing = new boolean[4];
   private static final int[] sinData;
   private static byte[] scdtblwk;
   private static int FontPos;
   private static long getTime2;
   private static int MapEndCounter;
   private static boolean bossModeOn;
   private static boolean bossBreakOn;
   private static short[][] objectSizeTbl;
   private static int TRANS_NONE;
   private static int TRANS_ROT90;
   private static int TRANS_ROT180;
   private static int TRANS_ROT270;
   private static int TRANS_MIRROR;
   private static int TRANS_MIRROR_ROT90;
   private static int TRANS_MIRROR_ROT180;
   private static int TRANS_MIRROR_ROT270;
   public static final int[] rotNumTable;
   static final int[][] encZoneNumber;
   static final int[][] encStageNumber;
   private static int cutDrawVLine;
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
   public static String[] softKeys;
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
   int[][] kyuryuTable = new int[][]{{2688, 784, 3088, 912}, {3968, 272, 5136, 400}, {1120, 1040, 1808, 1168}, {2592, 1552, 5648, 1776}, {3200, 1568, 5072, 1680}};
   private boolean goleFlag = false;
   private int golecount;
   private int scoreGetcount;
   private int scoreGetcountMax;
   private int SysStringMax = 10;
   private int[][] SysString;
   private int SysCenter;
   private int SysCount;
   private int GREEN_HILL;
   private int FINAL;
   private int MARBLE;
   private int ZONE;
   private int ACT1;
   private int ACT2;
   private int ACT3;
   private int SPRING_YARD;
   private int ACT;
   private int SCRAP_BRAIN;
   private int DAEN_B;
   private int STAR_LIGHT;
   private int LABYRINTH;
   private int DAEN_Y;
   private int SONIC_HAS;
   private int PASSED;
   private int SPECIAL_STAGE;
   private int CHAOS_EMERALDS;
   int[][] SystxtTable;
   int[] zonetable;
   public boolean scoreMoveFlag;
   public boolean limitBreak;
   public int resultRing;
   public int resultTime;
   int blockColCount;
   int enemyBlock;
   public int pushCount;
   public int bressCount;
   public int CrouchCount;
   public int LookUpCount;
   boolean rhit;
   boolean lhit;
   int PlayerW;
   boolean playdamageYogan;
   int offSetPos;
   int[][][] limitTable;
   public byte m_bScrollLock;
   public short[][] m_aaScrollLockPos;
   int[] poslimit;
   int nofcolTimer;
   public boolean damageNow;
   public int damageMoveTimer;
   public boolean PlayerSub;
   boolean bressDie;
   boolean timeUpDie;
   boolean noTimeScore;
   private static boolean OttotoOn;
   private static int OttotoSide;
   private static int raidObjectW;
   private static int raidObjectX;
   public int playerStandCount;
   private boolean bressMusic;
   int objChkPoint;
   int objChkNum;
   int m_objMaxObject;
   boolean ChkVecR;
   boolean ChkVecL;
   int LSize;
   int RSize;
   private static int[] m_aAddObjectData;
   int noDataPointer;
   int listSub;
   int[][] objTempData;
   int objCount;
   boolean[] setDrawFlag;
   private int waterH;
   private int waterH2;
   private int waterH3;
   private byte water_flag;
   private byte water_flag2;
   private byte water_flag3;
   private byte water_flag4;
   int noLeverTimer;
   int[] awasintlb;
   int[] awaSize;
   int[] awaPos;
   public boolean endingModeOn;
   private static byte LANGUAGE_MAX;
   private static byte TITLE_MODE_LICENSE_SEGA;
   private static byte TITLE_MODE_LICENSE_SONICTEAM;
   private static byte TITLE_MODE_FIRST_SETUP;
   private static byte TITLE_MODE_TITLE;
   private static byte TITLE_MODE_TITLE_MENU;
   private static byte TITLE_MODE_TITLE_RANCKING;
   private static byte TITLE_MODE_TITLE_RANCKING_MENU;
   private static byte TITLE_MODE_TITLE_RANCKING_DEL;
   private static byte TITLE_MODE_TITLE_CONFIG_MENU;
   private static byte TITLE_MODE_TITLE_CONTINUE_MENU;
   private static byte TITLE_MODE_TITLE_HOWTO;
   private static byte m_nTitleMode;
   private static byte m_nPattern;
   private static byte m_nRingPattern;
   private static byte m_nSel;
   private static byte m_bDraw;
   private static byte m_bFirstSetUp;
   private static byte m_nTimer;
   private static byte[] m_nConfigValue;
   private static byte[] m_HowToPicIndexTbl;
   private static byte[][] m_aConfigTextOffset;
   private static Command[] cmd;
   private static int m_nMarqueePos;
   private static int m_nOnKey;
   private static int m_nPushedKey;
   private static int m_nLastKey;
   private static int[] m_nHiScore;
   private static int[] m_nDifficulty;
   private static boolean[] m_OnKeyFlag;
   private static Image[] m_imgImage;
   private static Font m_Font;
   private static short[][] m_HowToPicTbl;
   private static String[] m_strText;
   private static String[] m_strHowToText;
   private static String[] m_strMusicComposed;
   private static String m_strMarquee;
   private int MarqOfs;
   private static final int LocalType = 1;
   private static int comboScore;
   private boolean isObj2Debug;
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
   private boolean putNowLoading;
   private Display display;
   private int mapViewType;
   private int mapViewTypeTemp;
   private int mapViewCount;
   private int mapViewPri;
   private volatile InputStream is1;
   private volatile Player player1;
   private static volatile boolean bPauseMusic;
   private static volatile boolean bGoalMusic;
   private static volatile int musicCount;
   private static volatile int musicRetry;
   private static volatile int musicRequest;
   private static volatile int musicNum;
   private volatile boolean bDoPlay;
   private static final int MusicRetryInterval = 50;
   private static final byte[][] friendTbl;
   private static final byte[] sisootbl;
   private static final int KaniAttackCount = 360;
   private static final int ArumaRunCount = 94;
   private static final int ArumaSpeedX = 500;
   private static final int ArumaSpeedY = 400;
   private static final int ArumaStartOffsetX = 160;
   private static final byte[] batAnimTbl;
   private static final short[][] RectTblKamere;
   private static final short[][] RectTblHachi;
   private static final short[][] RectTblMusi;
   private static final short[][] RectTblImo;
   private static final short[][] RectTblBrobo;
   private static final short[][] RectTblButa;
   private static final short[][] RectTblKani;
   private static final short[][] RectTblAruma;
   private static final short[][] RectTblYado;
   private static final short[][] RectTblUni;
   private static final short[][] RectTblBat;
   private static final short[][] RectTblMogura;
   private static final short[][] RectTblFish;
   private static final short[][] RectTblFish2;
   private static final byte[] Boss6TamaAnmTbl;
   private static final byte[] Boss6TamaAnmTbl2;
   private static final byte[] Boss6TamaAnmTbl3;
   private static final short[][] RectTblBakuhatu;
   private static final short[][] RectTblKemuri;
   private static final short[][] RectTblTama;
   private static final short[][] RectTblDBlock;
   private static final short[][] RectTblBoss6Tama;
   private short[][] MoveAnimalTbl;
   private short[][] RectAnimalTbl;
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
   private static final int[] BossDeadLimitY;
   private static final int Boss1MoveWidth = 3200;
   private static final int Boss1Speed = 100;
   private static final int Boss1FurikoSpeed = 100;
   private static int boss1BallPosX;
   private static int boss1BallPosY;
   private static boolean boss1BallOn;
   private static final int[][] boss2MoveTbl;
   private static final int boss3AttackWidth = 104;
   private static final int boss3SpeedX = 150;
   private static final int boss3DownSpeed = 50;
   private static final int boss3FloatSpeed = 12;
   private static final int boss3AttackWait = 48;
   private static int boss3FireCount;
   private static short[][] boss4Sisoo;
   private static final int Boss4SisooOfs = 3500;
   private static final int Boss4ShootWait = 50;
   private static final int Boss4Speed = 80;
   private static final int Boss4HighPos = -400;
   private static final int Boss4LowPos = 4800;
   private static final int Boss4BakuhatuCount = 240;
   private static short[][] boss5Block;
   static final int Boss5BlockLine = 160;
   private static final int boss5AttackWidth = 120;
   private static final int boss5Speed = 70;
   private static int boss5AttackCount;
   private static int[] boss6Piston;
   private static int[][] boss6PistonXY;
   private static int boss6RideNum;
   private static int boss6PistonNum;
   private static int[] boss6TamaY;
   private static int boss6Lamp;
   private static int boss6Destroy;
   private static final short[][] boss6PistonPos;
   private static int nakaStep;
   private static int nakaLevel;
   private static int nakaCount;
   private static int endingEggStep;
   private static int endingEggAnim;
   private static int endingEggCount;
   private static final short[][] RectTblEndingB;
   private static int wipeCol;
   private static int wipeLevel;
   private static boolean wipeDir;
   private int endingStep;
   private int endingCount;
   private int endingAnim;
   private int endingType;
   private int endingLogoPosX;
   private int endingStringFadeLevel;
   private short[][] endingRectTbl;
   private short[][] RectBoss6LampTbll;
   private short[][] RectEggmanTbl;
   private short[][] RectBossTbl;
   private short[][] RectBoss2Tbl;
   private short[] RectBossBallTbl;
   private static int continueStep;
   private static int continueSonicPosX;
   private static int continueSonicPosY;
   private static int continueSonicAnim;
   private static int continueSonicAnim2;
   private static int continueCount;
   private static int continueResult;
   private static final int ContinueSonicCenterX = 120;
   private static final int ContinueSonicBottomY = 167;
   private static final short[][] ContinueSonicTbl;
   private static final short[][] ContinueSonicTbl2;
   int[] break_sflag_ike_yuka;
   int[][] yuka_nflag_ike_yuka;
   int[] yuka_nflag_yuka_w;
   int[] yuka_nflag_yuka_h;
   int[] box_sflag_ike_def_X;
   int[] box_sflag_ike_def_Y;
   int[] box_sflag_ike_col_X;
   int[] box_sflag_ike_col_Y;
   int[] box_sflag_ike_box_V;
   private static boolean[] switchflag2;
   int[] break2_nflag_ike_brockTable;
   int[] break2_nflag_ike_brockTimeTable;
   int[] fire6_nflag_ike_posTable;
   int[] fire6_nflag_ike_sizeTable;
   int[] fire6_nflag_ike_sizeTable2;
   int[] mawaru_nflag_ike_posx;
   int[] mawaru_nflag_ike_posy;
   int[][] ele_nflag_ike_anime;
   int[][] beltc_nflag_ike_objectPos;
   int[] beltc_nflag_ike_defx;
   int[] beltc_nflag_ike_defy;
   int[] beltc_nflag_ike_startPos;
   int[] beltc_nflag_ike_endPos;
   boolean gole_on;
   int[] bten_nflag_ike_score;
   int[][] shooter_nflag_ike_objectPos;
   int[] shooter_nflag_ike_pos;
   int[] masin_nflag_ike_x;
   int[] masin_nflag_ike_y;
   int[] yari_sflag_ike_PosTable;
   private static int[][] kassya_x;
   private static int[][] kassya_y;
   int[][] kassya_nflag_ike_objectPos;
   int[][] kassya_nflag_ike_defX;
   int[][] kassya_nflag_ike_defY;
   int[] myogan_nflag_ike_ani;
   int[][] step_nflag_ike_gura;
   int[] fire6_nflag_ike_animeTable;
   int[][] fire6_nflag_ike_rotTable;
   int[][] ele_nflag_ike_rotTable;
   int[] item_nflag_ike_itemTable;
   int[] gole_nflag_ike_rotTable;
   int[] gole_nflag_ike_kiraTableX;
   int[] gole_nflag_ike_kiraTableY;
   int[] yoganc_nflag_ike_posY;
   int[] yari_sflag_ike_drawPosTable;

   private void crushingDeathChk() {
      if (!debugFlag) {
         if (this.crushing[0] && this.crushing[3]) {
            this.playerDie();
         } else if ((!PlayerJump || raidOn) && this.crushing[3]) {
            this.playerDie();
         } else if (this.zoneNumber != 2 && this.zoneNumber != 4) {
            if (raidOn && (this.blockColChk2(this.PlayerPosX() - 8, this.PlayerPosY() - 24) || this.blockColChk2(this.PlayerPosX() + 8, this.PlayerPosY() - 24))) {
               this.playerDie();
            }
         } else if (raidOn && this.blockColChk2(this.PlayerPosX() - 8, this.PlayerPosY() - 24) && this.blockColChk2(this.PlayerPosX() + 8, this.PlayerPosY() - 24)) {
            this.playerDie();
         }

         this.crushing[0] = false;
         this.crushing[3] = false;
      }
   }

   MainCanvas(sonic var1) {
      super(false);
      this.SysString = new int[this.SysStringMax][15];
      this.GREEN_HILL = 0;
      this.FINAL = 1;
      this.MARBLE = 2;
      this.ZONE = 3;
      this.ACT1 = 4;
      this.ACT2 = 5;
      this.ACT3 = 6;
      this.SPRING_YARD = 7;
      this.ACT = 8;
      this.SCRAP_BRAIN = 9;
      this.DAEN_B = 10;
      this.STAR_LIGHT = 11;
      this.LABYRINTH = 12;
      this.DAEN_Y = 13;
      this.SONIC_HAS = 14;
      this.PASSED = 15;
      this.SPECIAL_STAGE = 16;
      this.CHAOS_EMERALDS = 17;
      this.SystxtTable = new int[][]{{0, 0, 112, 16}, {112, 0, 56, 16}, {0, 16, 80, 16}, {80, 16, 48, 16}, {128, 16, 8, 16}, {136, 16, 16, 16}, {152, 16, 16, 16}, {0, 32, 128, 16}, {128, 32, 24, 8}, {0, 48, 128, 16}, {128, 40, 40, 40}, {0, 64, 120, 16}, {0, 80, 104, 16}, {104, 80, 40, 40}, {0, 96, 104, 16}, {0, 112, 72, 16}, {0, 128, 152, 16}, {0, 144, 176, 16}};
      this.zonetable = new int[]{this.GREEN_HILL, this.MARBLE, this.SPRING_YARD, this.LABYRINTH, this.STAR_LIGHT, this.SCRAP_BRAIN, this.FINAL};
      this.scoreMoveFlag = false;
      this.limitBreak = false;
      this.resultRing = 0;
      this.resultTime = 0;
      this.pushCount = 0;
      this.bressCount = 2100;
      this.CrouchCount = 0;
      this.LookUpCount = 0;
      this.PlayerW = 10;
      this.offSetPos = 0;
      this.limitTable = new int[][][]{{{4, 0, 9407, 0, 768, 96}, {4, 0, 7871, 0, 768, 96}, {4, 0, 10592, 0, 768, 96}, {4, 0, 10943, 0, 768, 96}}, {{4, 0, 6591, 0, 1328, 96}, {4, 0, 4271, 0, 1824, 96}, {4, 0, 8239, 65280, 2048, 96}, {4, 0, 8383, 0, 1824, 96}}, {{4, 0, 6079, 0, 464, 96}, {4, 0, 6079, 0, 1312, 96}, {4, 0, 6144, 0, 1824, 96}, {4, 0, 5823, 0, 1824, 96}}, {{4, 0, 8127, 0, 1600, 96}, {4, 0, 8127, 0, 1600, 96}, {4, 0, 8192, 0, 1728, 96}, {4, 0, 16064, 0, 1824, 96}}, {{4, 0, 8896, 0, 1056, 96}, {4, 0, 10432, 0, 1312, 96}, {4, 0, 11264, 0, 1568, 96}, {4, 0, 11968, 0, 1568, 96}}, {{4, 0, 8640, 0, 1824, 96}, {4, 0, 7744, 65280, 2048, 96}, {4, 8320, 9312, 1296, 1296, 96}, {4, 0, 16064, 0, 1824, 96}}};
      this.m_aaScrollLockPos = new short[][]{{9312, 7776}, {6736, 4176, 8208}, {6224, 6224}, {8288, 8032}, {8800, 10336}, {8544, 7648}};
      this.poslimit = new int[4];
      this.nofcolTimer = 0;
      this.damageNow = false;
      this.damageMoveTimer = 0;
      this.playerStandCount = 0;
      this.bressMusic = false;
      this.objChkPoint = 0;
      this.objChkNum = 0;
      this.ChkVecR = false;
      this.ChkVecL = false;
      this.LSize = -240;
      this.RSize = 640;
      this.noDataPointer = 0;
      this.listSub = 0;
      this.objTempData = new int[30][25];
      this.setDrawFlag = new boolean[150];
      this.waterH = 0;
      this.waterH2 = 0;
      this.waterH3 = 0;
      this.water_flag = 0;
      this.water_flag2 = 0;
      this.water_flag3 = 0;
      this.water_flag4 = 0;
      this.noLeverTimer = 0;
      this.awasintlb = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 255, 255, 255, 255, 255, 254, 254, 254, 254, 254, 253, 253, 253, 253, 253, 253, 253, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 253, 253, 253, 253, 253, 253, 253, 254, 254, 254, 254, 254, 255, 255, 255, 255, 255};
      this.awaSize = new int[]{8, 8, 16, 16, 24, 32, 32, 32};
      this.awaPos = new int[]{0, 8, 16, 32, 48, 72, 104, 136};
      this.endingModeOn = false;
      this.isObj2Debug = false;
      this.putNowLoading = false;
      this.mapViewType = 0;
      this.mapViewTypeTemp = 0;
      this.mapViewCount = 0;
      this.mapViewPri = 0;
      this.is1 = null;
      this.player1 = null;
      this.bDoPlay = false;
      this.MoveAnimalTbl = new short[][]{{-250, -360}, {-80, -100}, {-160, -250}, {-200, -300}, {-120, -225}, {-150, -250}, {-100, -165}};
      this.RectAnimalTbl = new short[][]{{0, 0, 16, 24, 0}, {16, 0, 16, 16, 8}, {32, 0, 16, 16, 8}, {0, 24, 16, 24, 0}, {16, 16, 16, 16, 8}, {32, 16, 16, 16, 8}, {0, 48, 16, 24, 0}, {16, 32, 16, 16, 8}, {32, 32, 16, 16, 8}, {0, 72, 16, 24, 0}, {16, 48, 16, 24, 0}, {32, 48, 16, 24, 0}, {0, 96, 16, 24, 0}, {16, 72, 16, 24, 0}, {32, 72, 16, 24, 0}, {16, 96, 16, 24, 0}, {0, 120, 24, 16, 8}, {0, 136, 24, 16, 8}, {32, 96, 16, 24, 0}, {24, 120, 24, 16, 8}, {24, 136, 24, 16, 8}};
      this.endingRectTbl = new short[][]{{0, 0, 32, 40}, {0, 40, 48, 72}, {48, 0, 176, 136}};
      this.RectBoss6LampTbll = new short[][]{{0, 0, 48, 56}, {0, 56, 48, 56}};
      this.RectEggmanTbl = new short[][]{{0, 0, 48, 56}, {0, 56, 48, 56}, {0, 112, 48, 56}, {0, 168, 48, 56}, {48, 0, 64, 56}, {48, 56, 64, 56}, {48, 112, 64, 56}, {48, 168, 64, 56}};
      this.RectBossTbl = new short[][]{{0, 0, 56, 24, 0, -36}, {0, 24, 56, 24, 0, -36}, {0, 48, 56, 24, 0, -36}, {0, 72, 56, 24, 0, -36}, {0, 96, 56, 24, 0, -36}, {0, 120, 56, 24, 0, -36}, {0, 144, 56, 24, 0, -36}, {0, 168, 56, 24, 0, -36}, {56, 48, 64, 32, 4, -8}, {56, 80, 64, 32, 4, -8}, {56, 64, 64, 48, 4, -8}, {56, 112, 16, 16, 42, -10}, {56, 128, 16, 16, 42, -10}, {56, 144, 24, 16, 46, -10}, {56, 160, 32, 32, 52, -10}, {88, 112, 16, 16, 0, 16}, {88, 128, 16, 16, 0, 16}, {88, 144, 16, 16, 0, 0}, {104, 112, 16, 8, 18, -42}, {104, 128, 16, 16, 0, 10}, {104, 144, 16, 40, 0, 10}, {56, 0, 56, 24, 0, -36}, {56, 24, 56, 24, 0, -36}};
      this.RectBoss2Tbl = new short[][]{{0, 0, 64, 48, 4, 0}, {0, 48, 64, 40, 4, -4}, {0, 88, 64, 32, 4, -8}, {0, 120, 64, 32, 4, -8}};
      this.RectBossBallTbl = new short[]{0, 0, 48, 48, 0, 0};
      this.break_sflag_ike_yuka = new int[]{32, 32, 32, 32, 32, 32, 32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 39, 40, 40, 41, 41, 42, 42, 43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48};
      this.yuka_nflag_ike_yuka = new int[][]{{44, 44, 44, 44, 44, 44, 44, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 44, 44, 44, 44, 44, 44, 44}, {52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52}, {37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53}};
      this.yuka_nflag_yuka_w = new int[]{64, 32, 64};
      this.yuka_nflag_yuka_h = new int[]{40, 48, 48};
      this.box_sflag_ike_def_X = new int[]{1296, 3632, 752, 4880};
      this.box_sflag_ike_def_Y = new int[]{1168, 1104, 1680, 1360};
      this.box_sflag_ike_col_X = new int[]{1263, 3598, 785, 4845};
      this.box_sflag_ike_col_Y = new int[]{1216, 1136, 1728, 1392};
      this.box_sflag_ike_box_V = new int[]{-1, -1, 1, -1};
      this.break2_nflag_ike_brockTable = new int[]{4, 7, 5, 6, 0, 3, 2, 1};
      this.break2_nflag_ike_brockTimeTable = new int[]{0, 5, 8, 17, 20, 28, 32, 34};
      this.fire6_nflag_ike_posTable = new int[]{16, 16, 8, 8, 8};
      this.fire6_nflag_ike_sizeTable = new int[]{32, 32, 24, 24, 16};
      this.fire6_nflag_ike_sizeTable2 = new int[]{32, 32, 16, 24, 16};
      this.mawaru_nflag_ike_posx = new int[]{-24, -47, -47, -46, -23, 1, 9, 1};
      this.mawaru_nflag_ike_posy = new int[]{-47, -46, -24, 1, 8, 1, -23, -46};
      this.ele_nflag_ike_anime = new int[][]{{1, 0}, {1, 0}, {1, 1}, {0, 1}};
      this.beltc_nflag_ike_objectPos = new int[][]{{3604, 880, 3823, 770, 3823, 832, 3604, 942}, {3860, 736, 4079, 626, 4079, 688, 3860, 798}, {4116, 624, 4335, 514, 4335, 576, 4116, 686}, {3860, 1392, 4079, 1282, 4079, 1344, 3860, 1454}, {6932, 1648, 7151, 1538, 7151, 1600, 6932, 1710}, {7188, 1504, 7407, 1394, 7407, 1456, 7188, 1566}};
      this.beltc_nflag_ike_defx = new int[]{3712, 3968, 3968, 4224, 7040, 7296};
      this.beltc_nflag_ike_defy = new int[]{896, 768, 1280, 640, 1536, 1408};
      this.beltc_nflag_ike_startPos = new int[2];
      this.beltc_nflag_ike_endPos = new int[2];
      this.gole_on = false;
      this.bten_nflag_ike_score = new int[]{0, 10000, 1000, 100};
      this.shooter_nflag_ike_objectPos = new int[][]{{1940, 396}, {148, 920}, {1940, 756, 1956, 716, 2000, 696, 2136, 696, 2180, 676, 2196, 636, 2196, 412}, {2196, 1692}, {4500, 1148, 4484, 1188, 4440, 1208, 4048, 1208, 4004, 1228, 3988, 1268, 3988, 1436}, {4756, 1180}, {5524, 2040, 5508, 2000, 5472, 1980, 5328, 1980, 5284, 1960, 5268, 1920, 5268, 1440}, {2196, 144}};
      this.shooter_nflag_ike_pos = new int[]{2, 4, 6, 8, 10, 12, 12, 9, 7, 4, 1, -2};
      this.masin_nflag_ike_x = new int[]{-8, -16, -12, 12, 16, 8};
      this.masin_nflag_ike_y = new int[]{22, 25, 13, 22, 25, 13};
      this.yari_sflag_ike_PosTable = new int[]{48, 32, 16, 32};
      this.kassya_nflag_ike_objectPos = new int[][]{{4216, 538, 4286, 608, 4286, 915, 4236, 965, 4130, 912, 4130, 580}, {4734, 640, 4814, 720, 4814, 1134, 4658, 1056, 4658, 716}, {3362, 1154, 3362, 1502, 3502, 1502, 3502, 1154}, {3426, 930, 3566, 930, 3566, 1246, 3426, 1246}, {3244, 578, 3550, 578, 3550, 990, 3154, 990, 3154, 668}, {4690, 522, 5086, 522, 5086, 702, 4690, 702}};
      this.kassya_nflag_ike_defX = new int[][]{{4208, 4736}, {3432, 3488}, {4208, 4736}};
      this.kassya_nflag_ike_defY = new int[][]{{640, 768}, {1152, 896}, {640, 768}};
      this.myogan_nflag_ike_ani = new int[]{2, 3, 2, 3, 2, 3, 2, 3, 2, 2, 3, 0, 1, 0, 1};
      this.step_nflag_ike_gura = new int[][]{{-1, 1}, {1, -1}};
      this.fire6_nflag_ike_animeTable = new int[]{40, 40, 16, 16, 0};
      this.fire6_nflag_ike_rotTable = new int[][]{{TRANS_NONE, TRANS_MIRROR}, {TRANS_MIRROR, TRANS_NONE}, {TRANS_ROT180, TRANS_MIRROR_ROT180}, {0, 0}};
      this.ele_nflag_ike_rotTable = new int[][]{{TRANS_ROT180, TRANS_MIRROR_ROT180}, {TRANS_ROT180, TRANS_MIRROR_ROT180}, {TRANS_MIRROR, TRANS_NONE}, {TRANS_ROT180, TRANS_MIRROR_ROT180}};
      this.item_nflag_ike_itemTable = new int[]{8, 0, 4, 3, 2, 1, 0};
      this.gole_nflag_ike_rotTable = new int[]{rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR]};
      this.gole_nflag_ike_kiraTableX = new int[]{8, 0, 40, 24, 10, 40, 30, 24, 41, 6};
      this.gole_nflag_ike_kiraTableY = new int[]{8, 16, 8, 30, 23, 24, 18, 16, 18, 8};
      this.yoganc_nflag_ike_posY = new int[]{0, 32, 64, 96, 128, 168, 208};
      this.yari_sflag_ike_drawPosTable = new int[]{0, 6, 6, 4, 10, 2, 6, 4};
      h = var1;
   }

   public void keyPressed(int var1) {
      if (!PlayerNoCtrl || mode != MODE_FIELD) {
         if (!this.endingModeOn) {
            int var2 = this.getGameAction(var1);
            if (var2 != 8 && var1 != 53) {
               if (var2 != 6 && var1 != 56) {
                  if (var2 != 1 && var1 != 50) {
                     if (var2 != 2 && var1 != 52) {
                        if (var2 != 5 && var1 != 54) {
                           if (var1 == 48) {
                              KeyPress[7] = true;
                           } else if (var1 == 42) {
                              KeyPress[8] = true;
                           } else if (var1 == 35) {
                              KeyPress[9] = true;
                           }
                        } else {
                           KeyPress[4] = true;
                        }
                     } else {
                        KeyPress[3] = true;
                     }
                  } else {
                     KeyPress[2] = true;
                  }
               } else {
                  KeyPress[1] = true;
               }
            } else {
               KeyPress[0] = true;
            }

         }
      }
   }

   public void keyReleased(int var1) {
      int var2 = this.getGameAction(var1);
      if (var2 != 8 && var1 != 53) {
         if (var2 != 6 && var1 != 56) {
            if (var2 != 1 && var1 != 50) {
               if (var2 != 2 && var1 != 52) {
                  if (var2 != 5 && var1 != 54) {
                     if (var1 == 48) {
                        KeyPress[7] = false;
                     } else if (var1 == 42) {
                        KeyPress[8] = false;
                     } else if (var1 == 35) {
                        KeyPress[9] = false;
                     }
                  } else {
                     KeyPress[4] = false;
                  }
               } else {
                  KeyPress[3] = false;
               }
            } else {
               KeyPress[2] = false;
            }
         } else {
            KeyPress[1] = false;
         }
      } else {
         KeyPress[0] = false;
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
            gg = this.getGraphics();
         } catch (Throwable var5) {
         }

         mode = MODE_INIT;
         this.displayOffsetY = this.getHeight() - 240 >> 1;
         this.displayOffsetY2 = this.displayOffsetY + 36;
         gg.translate(0, this.displayOffsetY);
         this.DG();
         this.load_conf();
         this.TK_LoadTextset();
         this.DG();
         this.m_imgCmd[LOGO] = this.createImage("/logo.png");
         this.m_imgCmd[LOGOLINE] = this.createImage("/logoline.png");
         this.m_imgCmd[SYSTXT] = this.createImage("/Systxt.png");
         this.m_imgCmd[SYSTXT2] = this.createImage("/Systxt2.png");
         this.m_imgCmd[WINDOW_RING] = this.createImage("/windou_ring.png");
         this.m_imgCmd[WINDOW_TIME] = this.createImage("/windou_time.png");
         this.m_imgCmd[WINDOW_ZANKI] = this.createImage("/windou_zanki.png");
         this.m_imgCmd[WINDOU_SUUJI] = this.createImage("/windou_suuji.png");
         this.m_imgCmd[SYSSCORE] = this.createImage("/score.png");
         this.m_imgCmd[GAMEOVER] = this.createImage("/gameover.png");
         this.m_imgCmd[TIMEOVER] = this.createImage("/timeover.png");
         this.load_resu();
         this.load_hisc();
         FontPos = (20 - f.getHeight()) / 2;
         gg.setFont(f);
         this.m_imgCmd[T_CUR1] = this.createImage("/t_cur1.png");
         this.m_imgCmd[T_CUR2] = this.createImage("/t_cur2.png");
         initDisplay = true;
         this.DG();
         indata = new DataInputStream(this.getClass().getResourceAsStream("/scddirtbl.blt"));
         indata.read(scddirtbl);
         indata.close();
         mode = MODE_TITLE;
         this.SetSoftFlag = true;
         this.SetSoftCount = 10;
         this.InitObj2();
         DrawFlag = true;
         this.InitSound();
         this.TK_TitleInit(true);
         this.setCommandListener(this);
         ObjectListNum = 0;
      } catch (Throwable var6) {
      }

   }

   public void GameMain() {
      int var1 = 0;
      int var2 = 0;
      long var5 = 0L;
      long var3 = System.currentTimeMillis();

      while(true) {
         long var7 = System.currentTimeMillis();
         if (var5 > var7) {
            var5 = var7;
            var3 = 0L;
         } else {
            var5 = var7;
         }

         getTime2 = var5 - (var3 - (long)TIME_WAIT + 10L);
         if (var3 > var5) {
            try {
               Thread.sleep(var3 - var5);
            } catch (Exception var10) {
            }

            var5 = System.currentTimeMillis();
            var1 = 0;
         } else {
            ++var1;
            if (var1 > 1) {
               var1 = 0;
            } else if (var2 != 0) {
               var1 = 0;
            }
         }

         this.ProcessMain();
         if (var1 <= 0) {
            this.DG();
            var3 = var5 + (long)(TIME_WAIT - 10);
         } else if (mode == MODE_FIELD && !this.endingModeOn) {
            if (muteki2count > 0) {
               --muteki2count;
               if (muteki2count % 2 == 0) {
                  this.playerDraw = true;
               } else {
                  this.playerDraw = false;
               }
            } else {
               this.playerDraw = false;
            }

            ++this.animeTimer;
         } else {
            this.DG();
            var3 = var5 + (long)(TIME_WAIT - 10);
         }

         ++var2;
         if (var2 > 7) {
            var2 = 0;
         }
      }
   }

   public void save_conf() {
      this.SaveRecordStore(m_nConfigValue, "conf");
   }

   public void load_conf() {
      try {
         m_nConfigValue = this.LoadRecordStore("conf");
         if (m_nConfigValue[0] == 0) {
         }
      } catch (Throwable var2) {
         m_bFirstSetUp = 1;
         m_nConfigValue = new byte[4];
         m_nConfigValue[0] = 1;
         m_nConfigValue[1] = 3;
         m_nConfigValue[2] = 1;
         this.save_conf();
      }

   }

   public void save_resu() {
      byte[] savedata = new byte[7];
      savedata[0] = this.resumeStage;
      savedata[1] = this.resumeZanki;

      for(int var1 = 0; var1 < 4; ++var1) {
         savedata[2 + var1] = (byte)(this.resumeScore >> var1 * 8 % 256);
      }

      savedata[6] = this.clearStageData;
      this.SaveRecordStore(savedata, "resu");
   }

   public void load_resu() {
      try {
         byte[] savedata = this.LoadRecordStore("resu");
         this.resumeStage = savedata[0];
         this.resumeZanki = savedata[1];
         int var3 = 0;

         for(int var2 = 0; var2 < 4; ++var2) {
            var3 |= (savedata[2 + var2] & 255) << var2 * 8;
         }

         this.resumeScore = var3;
         this.clearStageData = savedata[6];
      } catch (Throwable var4) {
         this.save_resu();
      }

   }

   public void save_hisc() {
      byte[] savescores = new byte[25];

      for(int var1 = 0; var1 < 5; ++var1) {
         savescores[0 + var1 * 5] = (byte)m_nDifficulty[var1];

         for(int var2 = 0; var2 < 4; ++var2) {
            savescores[1 + var2 + var1 * 5] = (byte)(m_nHiScore[var1] >> var2 * 8 % 256);
         }
      }

      this.SaveRecordStore(savescores, "hisc");
   }

   public void load_hisc() {
      try {
         byte[] savescores = this.LoadRecordStore("hisc");

         for(int var1 = 0; var1 < 5; ++var1) {
            m_nDifficulty[var1] = savescores[0 + var1 * 5];
            int var4 = 0;

            for(int var2 = 0; var2 < 4; ++var2) {
               var4 |= (savescores[1 + var2 + var1 * 5] & 255) << var2 * 8;
            }

            m_nHiScore[var1] = var4;
         }
      } catch (Throwable var5) {
         this.save_hisc();
      }

   }

   public void SetSoftLabel() {
      if (mode == MODE_TITLE) {
         if (m_nTitleMode == TITLE_MODE_TITLE || m_nTitleMode == TITLE_MODE_TITLE_MENU) {
            if (m_nConfigValue[1] != 0) {
               this.SetSoftLabel(0, softKeys[14]);
            } else {
               this.SetSoftLabel(0, softKeys[13]);
            }
         }
      } else {
         if (mode != MODE_CONTINUE && mode != MODE_STARTSTAGE && mode != MODE_FIELD && mode != MODE_STAGESELECT) {
            if (m_nConfigValue[1] != 0) {
               this.SetSoftLabel(0, softKeys[14]);
            } else {
               this.SetSoftLabel(0, softKeys[13]);
            }
         } else {
            this.SetSoftLabel(0, "");
         }

         if (mode == MODE_FIELD) {
            if ((playercount > 0 || !PlayerDie) && !this.gole_on && !this.endingModeOn) {
               this.SetSoftLabel(1, softKeys[5]);
            } else {
               this.SetSoftLabel(1, "");
            }
         } else if (mode == this.MODE_FIELD_PAUSE) {
            this.SetSoftLabel(1, softKeys[6]);
         } else if (mode == MODE_CONTINUE) {
            this.SetSoftLabel(1, softKeys[11]);
         } else if (mode == MODE_STAGESELECT) {
            this.SetSoftLabel(1, softKeys[4]);
         } else if (mode == MODE_STARTSTAGE) {
            this.SetSoftLabel(1, "");
         }
      }

   }

   public boolean softKeyChk() {
      try {
         if (moveRsm && this.CheckSoftLabel(1, softKeys[5])) {
            mode = this.MODE_FIELD_PAUSE;
            this.SetSoftFlag = true;
            this.SetSoftCount = 10;
            this.pauseTimer = 0;
            this.pauseSelect = 0;
            moveRsm = false;
            this.PauseMusic();
            return true;
         }

         moveRsm = false;
         byte var1 = -1;
         if (KeyPress[5]) {
            KeyPress[5] = false;
            var1 = 0;
         } else if (KeyPress[6]) {
            if (mode == MODE_TITLE) {
               return false;
            }

            KeyPress[6] = false;
            var1 = 1;
         }

         if (var1 != -1) {
            if (this.CheckSoftLabel(var1, softKeys[4])) {
               if (mode == MODE_STAGESELECT) {
                  mode = MODE_TITLE;
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  this.ObjImageClear();
                  this.TK_TitleInit(false);
                  m_nTitleMode = TITLE_MODE_TITLE_MENU;
                  m_nSel = 1;
                  this.TK_SetMarquee(7 + m_nSel);
                  this.SetSoftKey(2);
               } else {
                  mode = MODE_TITLE;
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  this.ObjImageClear();
                  this.TK_TitleInit(false);
               }

               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[5])) {
               mode = this.MODE_FIELD_PAUSE;
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
               this.pauseTimer = 0;
               this.pauseSelect = 0;
               this.PauseMusic();
               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[6])) {
               this.save_conf();
               mode = MODE_FIELD;
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
               this.RestartMusic();
               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[11])) {
               mode = MODE_TITLE;
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
               this.ObjImageClear();
               this.StopMusic();
               this.TK_TitleInit(true);
               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[13])) {
               m_nConfigValue[1] = this.oldm_nConfigValue[1];
               if (m_nConfigValue[1] == 0) {
                  m_nConfigValue[1] = 3;
               }

               this.save_conf();
               this.MuteMusic(false);
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[14])) {
               this.oldm_nConfigValue[1] = m_nConfigValue[1];
               if (this.oldm_nConfigValue[1] == 0) {
                  this.oldm_nConfigValue[1] = 0;
               }

               m_nConfigValue[1] = 0;
               this.save_conf();
               this.MuteMusic(true);
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
               return true;
            }

            if (this.CheckSoftLabel(var1, softKeys[2])) {
               h.doExit();
               return true;
            }
         }
      } catch (Throwable var2) {
         moveRsm = false;
      }

      return false;
   }

   public void ProcessMain() {
      try {
         if (this.softKeyChk()) {
            this.clearKey();
            return;
         }

         if (mode == MODE_TITLE) {
            this.TK_TitleFactor();
         } else if (mode == this.MODE_FIELD_PAUSE) {
            this.playerStandCount = 0;
            if (KeyPress[0]) {
               this.save_conf();
               if (this.pauseSelect == 0) {
                  this.clearKey();
                  mode = MODE_FIELD;
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  this.RestartMusic();
               } else {
                  mode = MODE_TITLE;
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  this.ObjImageClear();
                  this.TK_TitleInit(true);
               }

               this.clearKey();
            } else if (KeyPress[1]) {
               this.clearKey();
               this.pauseSelect = this.pauseSelect + 1 & 1;
            } else if (KeyPress[2]) {
               this.clearKey();
               this.pauseSelect = this.pauseSelect + 1 & 1;
            }
         } else if (mode == MODE_STAGESELECT) {
            if (KeyPress[0]) {
               this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
               this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
               playercount = 3;
               scorecount = 0;
               readStageObjectFlag = true;
               this.initStageStart();
            } else {
               byte var4;
               if (KeyPress[4]) {
                  this.clearKey();
                  var4 = 18;
                  this.selectStageNumber = (this.selectStageNumber + 1) % 3;
                  if (this.selectStageNumber == 0) {
                     ++this.selectZoneNumber;
                  }

                  if (this.selectZoneNumber == var4 / 3) {
                     if (this.selectStageNumber > var4 % 3) {
                        this.selectZoneNumber = 0;
                        this.selectStageNumber = 0;
                     }
                  } else if (this.selectZoneNumber > var4 / 3) {
                     this.selectZoneNumber = 0;
                     this.selectStageNumber = 0;
                  }
               } else if (KeyPress[3]) {
                  this.clearKey();
                  var4 = 18;
                  this.selectStageNumber = (this.selectStageNumber + 2) % 3;
                  if (this.selectStageNumber == 2) {
                     --this.selectZoneNumber;
                     if (this.selectZoneNumber < 0) {
                        this.selectZoneNumber = var4 / 3;
                        this.selectStageNumber = var4 % 3;
                     }
                  }
               }
            }

            if (this.selectZoneNumber == 6) {
               this.selectStageNumber = 0;
            }
         } else if (mode == MODE_FIELD) {
            oldMapOxy[0] = mapOxy[0];
            oldMapOxy[1] = mapOxy[1];

            for(int var1 = 0; var1 < 4; ++var1) {
               oldringcount = ringcount;
               if (this.endingModeOn) {
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  this.clearKey();
                  this.moveEnding();
               }

               if (this.gole_on) {
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  TimerStop = true;
               }

               ploldpos[0] = this.PlayerPosX();
               ploldpos[1] = this.PlayerPosY() - 1;
               raidObjectW = 0;
               raidObjectX = 0;
               OttotoOn = false;
               this.damageNow = false;
               this.playdamageYogan = false;

               try {
                  this.playerAction();
                  if (!PlayerDie) {
                     this.addObjectChk();
                     this.objectAction();
                  }

                  if (this.zoneNumber == 1) {
                     this.objAwaData_move();
                  }

                  if (!PlayerDie) {
                     this.DriveObj2();
                  }

                  if (this.playdamageYogan) {
                     this.playdamageset();
                  }
               } catch (Throwable var5) {
               }

               this.moveObjData();
               this.crushingDeathChk();
               --mutekicount;
               --speedupcount;
               if (mutekicount == 0) {
                  this.PlayZoneBGML();
               }

               if (speedupcount <= 0) {
                  plmaxspd = 1536;
                  pladdspd = 12;
               }

               this.limitchk(true);
               this.ViewControl();
               ++this.cpuTimer;
               if (this.cpuTimer % 60 == 0 && !TimerStop) {
                  timecount = (timecount + 1) % 60;
                  if (timecount == 0) {
                     if (timecount2 == 9) {
                        timecount = 59;
                        this.playerDie();
                     } else {
                        ++timecount2;
                     }
                  }
               }

               if (TimerClear) {
                  timecount = 0;
                  timecount2 = 0;
               }

               if (!PlayerDamage && ringcount >= 100 && oldringcount < 100) {
                  this.PlayMusic(13);
                  ++playercount;
               }

               if (!PlayerDamage && ringcount >= 200 && oldringcount < 200) {
                  this.PlayMusic(13);
                  ++playercount;
               }

               if (!PlayerDamage && ringcount >= 300 && oldringcount < 300) {
                  this.PlayMusic(13);
                  ++playercount;
               }

               if (this.PlayerPosY() < 32 && PlayerSJump && this.stageNumber == 3 && this.zoneNumber == 1) {
                  this.limitBreak = false;
                  this.scoreMoveFlag = false;
                  this.gole_on = this.goleFlag = false;
                  plsaveX = 0;
                  plsaveY = 0;
                  plsaveTime = 0;
                  plsaveTime2 = 0;
                  this.noTimeScore = false;
                  this.selectStageNumber = (this.selectStageNumber + 1) % 3;
                  if (this.selectStageNumber == 0) {
                     ++this.selectZoneNumber;
                  }

                  this.resumeStage = (byte)(this.selectStageNumber + this.selectZoneNumber * 3);
                  this.resumeZanki = (byte)playercount;
                  this.resumeScore = scorecount;
                  if (this.clearStageData < this.resumeStage) {
                     this.clearStageData = this.resumeStage;
                  }

                  this.save_resu();
                  this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
                  this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
                  this.countClear();
                  readStageObjectFlag = true;
                  this.initStageStart();
               }

               if (mode != MODE_FIELD) {
                  break;
               }
            }
         } else if (mode == MODE_CONTINUE) {
            ++this.cpuTimer;
            this.moveContinue();
         }

         this.moveSysString();
      } catch (Throwable var6) {
      }

   }

   public void DG() {
      try {
         this.Draw();
         if (this.SetSoftFlag) {
            this.SetSoftLabel();
            if (this.SetSoftCount > 0) {
               --this.SetSoftCount;
            } else {
               this.SetSoftFlag = false;
            }
         }

         if (drawRsm) {
            if (this.drawRsmCount == 0) {
               this.drawRsmCount = 10;
            }

            if (this.drawRsmCount > 1) {
               --this.drawRsmCount;
            } else {
               this.drawRsmCount = 0;
               drawRsm = false;
            }
         }
      } catch (Throwable var2) {
      }

   }

   public void paint(Graphics var1) {
   }

   public void Draw() {
      int var1;
      try {
         if (mode == MODE_INIT) {
            gg.setClip(0, 0, 240, 240);
            gg.setColor(0);
            gg.fillRect(0, 0, 240, 320);
            this.TK_DrawStringC(m_strText[44], 120, 180, 16777215, 658170);
         } else if (mode == this.MODE_FIELD_PAUSE) {
            gg.setClip(0, 0, 240, 240);
            gg.setFont(m_Font);
            this.TK_DrawBelt(true, false);
            this.TK_DrawStringC(softKeys[5], 120, 6, 16777215, 0);
            this.drawField();
            gg.setColor(0);

            for(var1 = 0; var1 < 84; ++var1) {
               gg.fillRect(0, var1 * 2 + 36, 240, 1);
            }

            int[] var4 = new int[]{0, 0, 0, 2};
            int[] var5 = new int[]{0, 16, 32, 16};
            gg.drawRegion(this.m_imgObj[0], 0, var5[this.pauseTimer % 4], 16, 16, var4[this.pauseTimer % 4], 120 - m_Font.stringWidth(softKeys[15 + this.pauseSelect]) / 2 - 16 - 14, 94 + 30 * this.pauseSelect, 20);
            gg.drawRegion(this.m_imgObj[0], 0, var5[this.pauseTimer % 4], 16, 16, var4[this.pauseTimer % 4], 120 + m_Font.stringWidth(softKeys[15 + this.pauseSelect]) / 2 + 14, 94 + 30 * this.pauseSelect, 20);
            gg.setColor(16777215);
            gg.drawString(softKeys[15], 120 - m_Font.stringWidth(softKeys[15]) / 2, 93, 20);
            gg.drawString(softKeys[16], 120 - m_Font.stringWidth(softKeys[16]) / 2, 123, 20);
            ++this.pauseTimer;
            initDisplay = true;
         } else if (mode == MODE_TITLE) {
            this.TK_TitleDraw();
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
            ++this.animeTimer;
            this.drawContinue();
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
               this.drawZankiFlag = this.drawZankiFlag = this.drawRingFlag = true;
               this.drawSystemData();
               initDisplay = false;
            } else {
               this.drawSystemData();
            }

            if (muteki2count > 0) {
               --muteki2count;
               if (muteki2count % 2 == 0) {
                  this.playerDraw = true;
               } else {
                  this.playerDraw = false;
               }
            } else {
               this.playerDraw = false;
            }

            this.drawField();
            ++this.animeTimer;
         }
      } catch (Throwable var6) {
      }

      if (mode == MODE_STARTSTAGE || mode == MODE_FIELD) {
         int var2;
         if (this.outWipe) {
            gg.setColor(0);

            for(var1 = 0; var1 < 10; ++var1) {
               var2 = 24 - (this.wipeCount - var1) * 4;
               if (var2 <= 0) {
                  boolean var7 = false;
               } else {
                  if (var2 > 24) {
                     var2 = 24;
                  }

                  gg.fillRect(var1 * 24 + (24 - var2), 0, var2, 240);
               }
            }

            if (22 < this.wipeCount) {
               this.wipeCount = 0;
               this.outWipe = false;
            }

            ++this.wipeCount;
         }

         this.drawSysString();
         if (this.putWipe) {
            gg.setColor(0);

            for(var1 = 0; var1 < 10; ++var1) {
               if (this.wipeCount - var1 > 0) {
                  var2 = this.wipeCount - var1 << 2;
                  if (var2 > 24) {
                     var2 = 24;
                  }

                  gg.fillRect(var1 * 24, 0, var2, 240);
               }
            }

            if (22 < this.wipeCount) {
               this.wipeCount = 0;
               this.putWipe = false;
            }

            ++this.wipeCount;
         }

         if (this.putNowLoading) {
            this.TK_DrawStringC(m_strText[44], 120, 180, 16777215, 658170);
         }
      }

      if (!drawRsm && !this.drawZankiFlag && !this.drawTimeFlag && !this.drawRingFlag && mode == MODE_FIELD) {
         this.flushGraphics(0, this.displayOffsetY2, 240, 168);
      } else {
         this.drawZankiFlag = this.drawTimeFlag = this.drawRingFlag = false;
         this.flushGraphics(0, this.displayOffsetY, 240, 240);
      }

      this.serviceRepaints();
   }

   public void drawField() {
      gg.setClip(0, 36, 240, 168);
      if (!pauseGame) {
         DistantBg.paint(mapView[0], mapView[1]);
         if (this.zoneNumber != 1 && this.zoneNumber != 5) {
            this.DrawMap3(gg);
         } else {
            this.DrawMap(gg);
         }

         this.CallObjectDraw();
         this.DrawObj2();
         if (!PlayerDie) {
            this.drawPlayerImage(gg);
         }

         this.drawObjData();
         if (this.zoneNumber != 1 && this.zoneNumber != 5) {
            this.DrawMap4(gg);
         } else {
            this.DrawMap2(gg);
         }

         this.CallObjectDrawFront();
         if (this.zoneNumber == 1) {
            this.objAwaData_draw();
         }

         if (PlayerDie) {
            this.drawPlayerImage(gg);
         }

         int var3;
         if (this.zoneNumber == 1) {
            var3 = (168 - (this.waterH2 - mapView[1])) / 16 + 1;
            int var4 = (this.waterH2 - mapView[1]) % 2;
            if (var4 < 0) {
               var4 = 0;
            }

            int var2 = 0;

            while(true) {
               if (var2 >= var3) {
                  if (this.bressCount < 600 && this.bressCount / 60 % 2 == 1) {
                     if (this.bressMusic) {
                        this.PlayMusic(24);
                        this.bressMusic = false;
                     }

                     this.drawNumber(116, 80, this.bressCount / 60 / 2, 1);
                  }

                  if (this.bressCount >= 600) {
                     if (!this.bressMusic) {
                        this.PlayZoneBGML();
                     }

                     this.bressMusic = true;
                  }
                  break;
               }

               if (var2 == 0) {
                  for(int var1 = 0; var1 < 8; ++var1) {
                     this.drawRegion(gg, this.m_imgObj[111], 0, this.animeTimer % 3 * 16, 32, 16, rotNumTable[0], var1 * 32, this.waterH2 - mapView[1] - 8, 20);
                  }
               }

               if (var2 == 0) {
                  this.drawRegion(gg, this.m_imgObj[5], 0, var4, 240, 16 - var4, rotNumTable[0], 0, this.waterH2 - mapView[1] + var2 * 16, 20);
               } else {
                  this.drawRegion(gg, this.m_imgObj[5], 0, 0, 240, 16, rotNumTable[0], 0, this.waterH2 - mapView[1] + var2 * 16 - var4, 20);
               }

               ++var2;
            }
         }

         if (this.endingModeOn) {
            this.drawEnding();
         }

         if (playercount <= 0 && PlayerDie || this.timeUpDie && timecount == 59 && timecount2 == 9) {
            var3 = (660 - diecount) * 6;
            if (120 - var3 < 8) {
               var3 = 112;
            }

            if (timecount == 59 && timecount2 == 9) {
               gg.drawRegion(this.m_imgCmd[TIMEOVER], 0, 0, 64, 16, rotNumTable[TRANS_NONE], var3 - 64, 108, 20);
               gg.drawRegion(this.m_imgCmd[TIMEOVER], 0, 16, 64, 16, rotNumTable[TRANS_NONE], 240 - var3, 108, 20);
            } else {
               gg.drawRegion(this.m_imgCmd[GAMEOVER], 0, 0, 64, 16, rotNumTable[TRANS_NONE], var3 - 64, 108, 20);
               gg.drawRegion(this.m_imgCmd[GAMEOVER], 0, 16, 64, 16, rotNumTable[TRANS_NONE], 240 - var3, 108, 20);
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
      } catch (Exception var3) {
      }

   }

   public void StageDataTableClear() {
      zoneActTable[0] = null;
      zoneActTable[1] = null;
      zoneActTable[2] = null;
      zoneActTable[3] = null;

      try {
         System.gc();
         Thread.sleep(200L);
      } catch (Exception var2) {
      }

   }

   public void scroll(Graphics var1, int var2, int var3) {
   }

   public boolean CheckSoftLabel(int var1, String var2) {
      String[] var3 = new String[2];
      var3[var1] = cmd[var1].getLabel();
      return var3[var1].equals(var2);
   }

   public void SetSoftLabel(int var1, String var2) {
      String[] var3 = new String[2];

      try {
         var3[0] = cmd[0].getLabel();
         var3[1] = cmd[1].getLabel();
         if (var3[var1].equals(var2)) {
            return;
         }

         this.removeCommand(cmd[1]);
         this.removeCommand(cmd[0]);
         var3[var1] = var2;
         cmd[0] = new Command(var3[0], 1, 1);
         cmd[1] = new Command(var3[1], 1, 1);
         this.addCommand(cmd[0]);
         this.addCommand(cmd[1]);
      } catch (Throwable var5) {
      }

   }

   public String ZeroSup(int var1, int var2) {
      String var3 = var1 + "";

      for(int var4 = var3.length(); var4 < var2; ++var4) {
         var3 = 0 + var3;
      }

      return var3;
   }

   public String ZeroSup(String var1, int var2) {
      String var3 = var1;

      for(int var4 = var1.length(); var4 < var2; ++var4) {
         var3 = 0 + var3;
      }

      return var3;
   }

   public boolean DrawWaterMap(int var1, int var2, int var3, int var4) {
      if (this.zoneNumber == 1) {
         if (var1 > 60 && var1 < 100) {
            var1 -= 60;
            this.drawRegion(gg, this.m_imgObj[84], var1 % 10 << 4, (var1 / 10 << 4) + (this.animeTimer % 4 << 4 << 2), 16, 16, rotNumTable[var2], var3, var4, 20);
            return false;
         }

         if (var1 >= 186) {
            var1 -= 186;
            this.drawRegion(gg, this.m_imgObj[84], var1 % 10 << 4, (var1 / 10 << 4) + (this.animeTimer % 4 << 4) + 256, 16, 16, rotNumTable[var2], var3, var4, 20);
            return false;
         }
      } else if (this.zoneNumber == 5 && this.animeTimer % 3 != 0) {
         if (var1 >= 250 && var1 < 290) {
            var1 -= 250;
            this.drawRegion(gg, this.m_imgObj[84], var1 % 10 << 4, (var1 / 10 << 4) + (this.animeTimer % 3 - 1 << 4 << 2), 16, 16, rotNumTable[var2], var3, var4, 20);
            return false;
         }

         if (var1 >= 560 && var1 < 570) {
            var1 -= 560;
            this.drawRegion(gg, this.m_imgObj[84], var1 % 10 << 4, (var1 / 10 << 4) + (this.animeTimer % 3 - 1 << 4) + 128, 16, 16, rotNumTable[var2], var3, var4, 20);
            return false;
         }

         if (var1 >= 30 && var1 < 90) {
            var1 -= 30;
            this.drawRegion(gg, this.m_imgObj[84], var1 % 10 << 4, (var1 / 10 << 4) + (this.animeTimer % 3 - 1) * 96 + 160, 16, 16, rotNumTable[var2], var3, var4, 20);
            return false;
         }
      }

      return true;
   }

   public void setMapData() {
      for(int var1 = 0; var1 < mapData.length >> 1; ++var1) {
         int var2 = mapData[var1 << 1] & 255;
         this.hitChk[var1] = 0;
         this.hitChk2[var1] = 0;
         byte var3 = (byte)(var2 << 6);
         var3 = (byte)Math.abs(var3 >> 6);
         if (var2 >> 5 <= 1) {
            this.hitChk2[var1] = 1;
         }

         if ((var2 >> 5) % 2 == 0) {
            this.hitChk[var1] = 1;
         }

         this.rot[var1] = (byte)(var2 >> 3 & 3);
         this.imageOffset[var1] = 0;
         if ((var2 & 1) == 1) {
            this.imageOffset[var1] = 1;
         } else if ((var2 & 3) == 2) {
            this.imageOffset[var1] = 2;
         }
      }

   }

   public void DrawMapRegion(int var1, int var2, int var3, int var4) {
      boolean var5 = false;
      boolean var6 = false;
      boolean var9 = false;
      boolean var10 = false;

      for(int var13 = var1; var13 < var1 + var3; ++var13) {
         if (MapW << 4 > (mapView[0] >> 4) + var13) {
            for(int var14 = var2; var14 < var2 + var4; ++var14) {
               int var7 = (mapView[0] >> 4) + var13 >> 4;
               int var8 = (mapView[1] >> 4) + var14 >> 4;
               var8 %= MapH;

               try {
                  int var16 = (tempWorldMapData[var8][var7] << 9) + (((mapView[0] >> 4) + var13 & 15) + (((mapView[1] >> 4) + var14 & 15) << 4) << 1) + 1;
                  int var15 = mapData[var16] & 255;
                  var15 += this.imageOffset[var16 >> 1] << 8;
                  if (var15 != 0) {
                     if (this.zoneNumber == 2) {
                        if (var15 == 367 || var15 == 366) {
                           var15 = (var15 + this.animeTimer / 5 & 1) + 366;
                        }

                        if (var15 == 365 || var15 == 364) {
                           var15 = (var15 + this.animeTimer / 5 & 1) + 364;
                        }

                        if (var15 == 363 || var15 == 362) {
                           var15 = (var15 + this.animeTimer / 5 & 1) + 362;
                        }
                     }

                     int var11 = TRANS_NONE;
                     if (this.rot[var16 >> 1] != 0) {
                        if (this.rot[var16 >> 1] == 1) {
                           var11 = TRANS_MIRROR;
                        } else if (this.rot[var16 >> 1] == 2) {
                           var11 = TRANS_MIRROR_ROT180;
                        } else if (this.rot[var16 >> 1] == 3) {
                           var11 = TRANS_ROT180;
                        }
                     }

                     this.drawRegion(gg, m_imgMimg, var15 % 10 << 4, var15 / 10 << 4, 16, 16, rotNumTable[var11], (var13 << 4) - (mapView[0] & 15), (var14 << 4) - (mapView[1] & 15), 20);
                  }
               } catch (Throwable var12) {
               }
            }
         }
      }

   }

   public void DrawMap(Graphics var1) {
      int var3 = 0;
      boolean var6 = false;
      boolean var7 = false;

      for(int var2 = 0; var2 < 16; ++var2) {
         if (MapW << 4 <= (mapView[0] >> 4) + var2) {
            this.drawMapData[var2][var3][2] = 0;
         } else {
            for(var3 = 0; var3 < 12; ++var3) {
               int var4 = (mapView[0] >> 4) + var2 >> 4;
               int var5 = (mapView[1] >> 4) + var3 >> 4;
               var5 %= MapH;

               try {
                  int var11 = (tempWorldMapData[var5][var4] << 9) + (((mapView[0] >> 4) + var2 & 15) + (((mapView[1] >> 4) + var3 & 15) << 4) << 1) + 1;
                  int var10 = mapData[var11] & 255;
                  var10 += this.imageOffset[var11 >> 1] << 8;
                  this.drawMapData[var2][var3][0] = var10;
                  if (var10 != 0) {
                     int var8 = TRANS_NONE;
                     if (this.rot[var11 >> 1] != 0) {
                        if (this.rot[var11 >> 1] == 1) {
                           var8 = TRANS_MIRROR;
                        } else if (this.rot[var11 >> 1] == 2) {
                           var8 = TRANS_MIRROR_ROT180;
                        } else if (this.rot[var11 >> 1] == 3) {
                           var8 = TRANS_ROT180;
                        }
                     }

                     this.drawMapData[var2][var3][1] = var8;
                     this.drawMapData[var2][var3][2] = 0;
                     if (mapFrontData[var10] != 0) {
                        this.drawMapData[var2][var3][2] = 1;
                        if (mapFrontData[var10] == 2) {
                           gg.setColor(8738);
                           gg.fillRect((var2 << 4) - (mapView[0] & 15), (var3 << 4) - (mapView[1] & 15) + 36, 16, 16);
                        }
                     } else if (this.DrawWaterMap(var10, var8, (var2 << 4) - (mapView[0] & 15), (var3 << 4) - (mapView[1] & 15))) {
                        this.drawRegion(var1, m_imgMimg, var10 % 10 << 4, var10 / 10 << 4, 16, 16, rotNumTable[var8], (var2 << 4) - (mapView[0] & 15), (var3 << 4) - (mapView[1] & 15), 20);
                     }
                  }
               } catch (Throwable var9) {
               }
            }
         }
      }

   }

   public void DrawMap2(Graphics var1) {
      boolean var3 = false;

      for(int var2 = 0; var2 < 16; ++var2) {
         if (MapW << 4 > (mapView[0] >> 4) + var2) {
            for(int var6 = 0; var6 < 12; ++var6) {
               try {
                  if (this.drawMapData[var2][var6][2] == 1 && this.DrawWaterMap(this.drawMapData[var2][var6][0], this.drawMapData[var2][var6][1], (var2 << 4) - (mapView[0] & 15), (var6 << 4) - (mapView[1] & 15))) {
                     this.drawRegion(var1, m_imgMimg, this.drawMapData[var2][var6][0] % 10 << 4, this.drawMapData[var2][var6][0] / 10 << 4, 16, 16, rotNumTable[this.drawMapData[var2][var6][1]], (var2 << 4) - (mapView[0] & 15), (var6 << 4) - (mapView[1] & 15), 20);
                  }
               } catch (Throwable var5) {
               }
            }
         }
      }

   }

   public void DrawMap3(Graphics var1) {
      int var3 = 0;
      boolean var6 = false;
      boolean var7 = false;

      for(int var2 = 0; var2 < 16; ++var2) {
         if (MapW << 4 <= (mapView[0] >> 4) + var2) {
            this.drawMapData[var2][var3][2] = 0;
         } else {
            for(var3 = 0; var3 < 12; ++var3) {
               int var4 = (mapView[0] >> 4) + var2 >> 4;
               int var5 = (mapView[1] >> 4) + var3 >> 4;
               var5 %= MapH;

               try {
                  int var11 = (tempWorldMapData[var5][var4] << 9) + (((mapView[0] >> 4) + var2 & 15) + (((mapView[1] >> 4) + var3 & 15) << 4) << 1) + 1;
                  int var10 = mapData[var11] & 255;
                  var10 += this.imageOffset[var11 >> 1] << 8;
                  this.drawMapData[var2][var3][0] = var10;
                  if (var10 != 0) {
                     if (this.zoneNumber == 2) {
                        if (var10 == 367 || var10 == 366) {
                           var10 = (var10 + this.animeTimer / 5 & 1) + 366;
                        }

                        if (var10 == 365 || var10 == 364) {
                           var10 = (var10 + this.animeTimer / 5 & 1) + 364;
                        }

                        if (var10 == 363 || var10 == 362) {
                           var10 = (var10 + this.animeTimer / 5 & 1) + 362;
                        }
                     }

                     int var8 = TRANS_NONE;
                     if (this.rot[var11 >> 1] != 0) {
                        if (this.rot[var11 >> 1] == 1) {
                           var8 = TRANS_MIRROR;
                        } else if (this.rot[var11 >> 1] == 2) {
                           var8 = TRANS_MIRROR_ROT180;
                        } else if (this.rot[var11 >> 1] == 3) {
                           var8 = TRANS_ROT180;
                        }
                     }

                     this.drawMapData[var2][var3][1] = var8;
                     this.drawMapData[var2][var3][2] = 0;
                     if (mapFrontData[var10] != 0) {
                        this.drawMapData[var2][var3][2] = 1;
                     } else {
                        this.drawRegion(var1, m_imgMimg, var10 % 10 << 4, var10 / 10 << 4, 16, 16, rotNumTable[var8], (var2 << 4) - (mapView[0] & 15), (var3 << 4) - (mapView[1] & 15), 20);
                     }
                  }
               } catch (Throwable var9) {
               }
            }
         }
      }

   }

   public void DrawMap4(Graphics var1) {
      boolean var3 = false;

      for(int var2 = 0; var2 < 16; ++var2) {
         if (MapW << 4 > (mapView[0] >> 4) + var2) {
            for(int var6 = 0; var6 < 12; ++var6) {
               try {
                  if (this.drawMapData[var2][var6][2] == 1) {
                     this.drawRegion(var1, m_imgMimg, this.drawMapData[var2][var6][0] % 10 << 4, this.drawMapData[var2][var6][0] / 10 << 4, 16, 16, rotNumTable[this.drawMapData[var2][var6][1]], (var2 << 4) - (mapView[0] & 15), (var6 << 4) - (mapView[1] & 15), 20);
                  }
               } catch (Throwable var5) {
               }
            }
         }
      }

   }

   public void drawHitMap() {
   }

   public void drawChipPut(int var1, int var2, int var3) {
      int var5 = (blockLinkTable[var3] & 255) * 16;
      gg.setColor(16777215);

      for(int var4 = 0; var4 < 16; ++var4) {
         gg.fillRect(var1 + var4, var2 + 36 + 16 - Math.abs(scdtblwk[var5 + var4]), 1, Math.abs(scdtblwk[var5 + var4]));
         if (Math.abs(scdtblwk[var5 + var4 + 4096]) > 16) {
         }
      }

      gg.setColor(0);
      this.drawStringCenter(gg, f, var3 + "", var1, var2, true);
   }

   public void drawStringCenter(Graphics var1, Font var2, String var3, int var4, int var5, boolean var6) {
      if (var6) {
         this.drawString(var1, var3, var4 - var2.stringWidth(var3) / 2, var5);
      }

   }

   public void drawString(Graphics var1, String var2, int var3, int var4) {
      var4 += FontPos;
      var1.drawString(var2, var3 - 1, var4, 20);
      var1.drawString(var2, var3 + 1, var4, 20);
      var1.drawString(var2, var3, var4 + 1, 20);
      var1.drawString(var2, var3, var4 - 1, 20);
      var1.setColor(16777215);
      var1.drawString(var2, var3, var4, 20);
   }

   public int dSin(int var1) {
      int var2 = var1 % 360;
      if (var2 >= 0 && var2 <= 90) {
         return sinData[var2] / 100;
      } else if (var2 > 90 && var2 <= 180) {
         return sinData[90 - (var2 - 90)] / 100;
      } else if (var2 > 180 && var2 <= 270) {
         return -1 * (sinData[var2 - 180] / 100);
      } else {
         return var2 > 270 && var2 <= 359 ? -1 * (sinData[90 - (var2 - 270)] / 100) : 0;
      }
   }

   public int dCos(int var1) {
      int var2 = var1 % 360;
      if (var2 >= 0 && var2 < 90) {
         return -1 * sinData[89 - var2] / 100;
      } else if (var2 >= 90 && var2 < 180) {
         return sinData[var2 - 90] / 100;
      } else if (var2 >= 180 && var2 < 270) {
         return sinData[89 - (var2 - 180)] / 100;
      } else {
         return var2 >= 270 && var2 <= 359 ? -1 * (sinData[var2 - 270] / 100) : 0;
      }
   }

   private void addScoreCount(int var1) {
      if (99950000 > scorecount && scorecount % 50000 > (scorecount + var1) % 50000) {
         ++playercount;
         this.PlayMusic(13);
      }

      scorecount += var1;
      if (scorecount > 99999999) {
         scorecount = 99999999;
      }

   }

   private void addScoreCount(int var1, int var2) {
      if (99950000 > scorecount && scorecount % 50000 > (scorecount + var1) % 50000) {
         ++playercount;
         this.PlayMusic(30);
      }

      scorecount += var1;
      if (scorecount > 99999999) {
         scorecount = 99999999;
      }

   }

   public void drawSystemData() {
      gg.setClip(0, 0, 240, 240);
      if (this.oldRingCount == ringcount && this.oldScoreCount == scorecount) {
         if (ringcount == 0 && (this.animeTimer & 1) == 0) {
            this.drawRingFlag = true;
         }
      } else {
         this.drawRingFlag = true;
      }

      this.oldRingCount = ringcount;
      this.oldScoreCount = scorecount;
      if (this.drawRingFlag) {
         gg.drawRegion(this.m_imgCmd[LOGOLINE], 0, 0, 100, 32, rotNumTable[TRANS_NONE], 0, 0, 20);
         gg.drawRegion(this.m_imgCmd[WINDOW_RING], 0, 0, 55, 26, rotNumTable[TRANS_NONE], 4, 5, 20);
         if (ringcount != 0 || (this.animeTimer >> 1 & 1) == 0) {
            this.drawNumber(31, 3, ringcount, 3);
         }

         this.drawNumber(31, 18, scorecount, 8);
      }

      if (timecount == 0 || this.oldTimeCount != timecount2 * 100 + timecount) {
         this.drawTimeFlag = true;
      }

      this.oldTimeCount = timecount2 * 100 + timecount;
      if (this.drawTimeFlag) {
         gg.drawRegion(this.m_imgCmd[LOGOLINE], 192, 0, 48, 32, rotNumTable[TRANS_NONE], 192, 0, 20);
         gg.drawRegion(this.m_imgCmd[WINDOW_TIME], 0, 0, 44, 13, rotNumTable[TRANS_NONE], 192, 18, 20);
         this.drawNumber(200, 16, timecount2, 1);
         this.drawNumber(216, 16, timecount, 2);
      }

      if (playercount > 99) {
         playercount = 99;
      }

      if (this.oldZankiCount != playercount) {
         this.drawZankiFlag = true;
      }

      this.oldZankiCount = playercount;
      if (this.drawZankiFlag) {
         gg.drawRegion(this.m_imgCmd[LOGOLINE], 0, 0, 50, 32, rotNumTable[TRANS_NONE], 0, 204, 20);
         gg.drawRegion(this.m_imgCmd[WINDOW_ZANKI], 0, 0, 22, 20, rotNumTable[TRANS_NONE], 5, 213, 20);
         if (playercount > 9) {
            this.drawNumber(31, 219, playercount, 2);
         } else {
            this.drawNumber(31, 219, playercount, 1);
         }
      }

   }

   public void drawNumber(int var1, int var2, int var3, int var4) {
      int var7 = var3 % 10;
      gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], 7 * var7, 0, 7, 13, rotNumTable[TRANS_NONE], var1 + (var4 << 3) - 8, var2, 20);

      for(int var5 = 1; var5 < var4; ++var5) {
         int var8 = 1;

         for(int var6 = 0; var6 < var5; ++var6) {
            var8 *= 10;
         }

         var7 = var3 / var8 % 10;
         gg.drawRegion(this.m_imgCmd[WINDOU_SUUJI], 7 * var7, 0, 7, 13, rotNumTable[TRANS_NONE], var1 + (var4 << 3) - (var5 << 3) - 8, var2, 20);
      }

   }

   private boolean kyuryuchk() {
      if (this.zoneNumber == 1) {
         int[][] var1;
         if (this.stageNumber == 0) {
            try {
               var1 = this.searchObject(DAI2_SFLAG, 227);
               if (var1.length > 0 && var1[0][5] != 0 && this.kyuryuTable[0][0] <= this.PlayerPosX() && this.kyuryuTable[0][2] > this.PlayerPosX() && this.kyuryuTable[0][1] <= this.PlayerPosY() - 16 && this.kyuryuTable[0][3] > this.PlayerPosY() - 16) {
                  return true;
               }
            } catch (Throwable var5) {
            }

            if (this.kyuryuTable[1][0] <= this.PlayerPosX() && this.kyuryuTable[1][2] > this.PlayerPosX() && this.kyuryuTable[1][1] <= this.PlayerPosY() && this.kyuryuTable[1][3] > this.PlayerPosY()) {
               return true;
            }
         } else if (this.stageNumber == 1) {
            try {
               if (this.animeTimer % 60 < 30 && this.kyuryuTable[2][0] <= this.PlayerPosX() && this.kyuryuTable[2][2] > this.PlayerPosX() && this.kyuryuTable[2][1] <= this.PlayerPosY() && this.kyuryuTable[2][3] > this.PlayerPosY()) {
                  return true;
               }

               var1 = this.searchObject(BEN_SFLAG, -1);
               if (var1.length > 0) {
                  if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[2][0] <= this.PlayerPosX() && this.kyuryuTable[2][2] > this.PlayerPosX() && this.kyuryuTable[2][1] <= this.PlayerPosY() && this.kyuryuTable[2][3] > this.PlayerPosY()) {
                     return true;
                  }
               } else {
                  var1 = this.searchObject(BEN_NFLAG, -1);
                  if (var1.length > 0) {
                     if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[2][0] <= this.PlayerPosX() && this.kyuryuTable[2][2] > this.PlayerPosX() && this.kyuryuTable[2][1] <= this.PlayerPosY() && this.kyuryuTable[2][3] > this.PlayerPosY()) {
                        return true;
                     }
                  } else if (this.kyuryuTable[2][0] <= this.PlayerPosX() && this.kyuryuTable[2][2] > this.PlayerPosX() && this.kyuryuTable[2][1] <= this.PlayerPosY() && this.kyuryuTable[2][3] > this.PlayerPosY()) {
                     return true;
                  }
               }
            } catch (Throwable var4) {
            }
         } else if (this.stageNumber == 2) {
            try {
               if (this.animeTimer % 60 < 30 && this.kyuryuTable[3][0] <= this.PlayerPosX() && this.kyuryuTable[3][2] > this.PlayerPosX() && this.kyuryuTable[3][1] <= this.PlayerPosY() && this.kyuryuTable[3][3] > this.PlayerPosY()) {
                  return true;
               }

               var1 = this.searchObject(BEN_SFLAG, -1);
               if (var1.length > 0) {
                  if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[3][0] <= this.PlayerPosX() && this.kyuryuTable[3][2] > this.PlayerPosX() && this.kyuryuTable[3][1] <= this.PlayerPosY() && this.kyuryuTable[3][3] > this.PlayerPosY()) {
                     return true;
                  }
               } else {
                  var1 = this.searchObject(BEN_NFLAG, -1);
                  if (var1.length > 0) {
                     if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[3][0] <= this.PlayerPosX() && this.kyuryuTable[3][2] > this.PlayerPosX() && this.kyuryuTable[3][1] <= this.PlayerPosY() && this.kyuryuTable[3][3] > this.PlayerPosY()) {
                        return true;
                     }
                  } else if (this.kyuryuTable[3][0] <= this.PlayerPosX() && this.kyuryuTable[3][2] > this.PlayerPosX() && this.kyuryuTable[3][1] <= this.PlayerPosY() && this.kyuryuTable[3][3] > this.PlayerPosY()) {
                     return true;
                  }
               }
            } catch (Throwable var3) {
            }
         } else if (this.stageNumber == 3) {
            try {
               if (this.animeTimer % 60 < 30 && this.kyuryuTable[4][0] <= this.PlayerPosX() && this.kyuryuTable[4][2] > this.PlayerPosX() && this.kyuryuTable[4][1] <= this.PlayerPosY() && this.kyuryuTable[4][3] > this.PlayerPosY()) {
                  return true;
               }

               var1 = this.searchObject(BEN_SFLAG, -1);
               if (var1.length > 0) {
                  if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[4][0] <= this.PlayerPosX() && this.kyuryuTable[4][2] > this.PlayerPosX() && this.kyuryuTable[4][1] <= this.PlayerPosY() && this.kyuryuTable[4][3] > this.PlayerPosY()) {
                     return true;
                  }
               } else {
                  var1 = this.searchObject(BEN_NFLAG, -1);
                  if (var1.length > 0) {
                     if (var1[0][2] < this.PlayerPosX() && this.kyuryuTable[4][0] <= this.PlayerPosX() && this.kyuryuTable[4][2] > this.PlayerPosX() && this.kyuryuTable[4][1] <= this.PlayerPosY() && this.kyuryuTable[4][3] > this.PlayerPosY()) {
                        return true;
                     }
                  } else if (this.kyuryuTable[4][0] <= this.PlayerPosX() && this.kyuryuTable[4][2] > this.PlayerPosX() && this.kyuryuTable[4][1] <= this.PlayerPosY() && this.kyuryuTable[4][3] > this.PlayerPosY()) {
                     return true;
                  }
               }
            } catch (Throwable var2) {
            }
         }
      }

      return false;
   }

   public void initGoleStart() {
      this.SysString = new int[this.SysStringMax][15];
      this.PlayMusic(20);
      byte var1 = 0;
      this.SysCount = 0;
      this.golecount = 30;
      this.goleFlag = true;
      this.SysString[var1][0] = 1;
      this.SysString[var1][1] = this.DAEN_B;
      this.SysString[var1][2] = 240;
      this.SysString[var1][3] = 82;
      this.SysString[var1][8] = 1 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var1][9] = 0;
      int var3 = var1 + 1;
      this.SysString[var3][0] = 1;
      this.SysString[var3][1] = this.ACT1 + this.selectStageNumber;
      this.SysString[var3][2] = 240;
      this.SysString[var3][3] = 108;
      this.SysString[var3][8] = 25 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var3][9] = 3;
      ++var3;
      this.SysString[var3][0] = 1;
      this.SysString[var3][1] = this.ACT;
      this.SysString[var3][2] = 240;
      this.SysString[var3][3] = 116;
      this.SysString[var3][8] = -1 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var3][9] = 2;
      ++var3;
      this.SysString[var3][0] = 1;
      this.SysString[var3][1] = this.SONIC_HAS;
      this.SysString[var3][2] = 0 - this.SystxtTable[this.SysString[var3][1]][2];
      this.SysString[var3][3] = 84;
      this.SysString[var3][8] = -this.SystxtTable[this.SysString[var3][1]][2];
      this.SysString[var3][9] = 0;
      this.SysCenter = 120 + this.SystxtTable[this.SysString[var3][1]][2] / 2;
      ++var3;
      this.SysString[var3][0] = 1;
      this.SysString[var3][1] = this.PASSED;
      this.SysString[var3][2] = 0 - this.SystxtTable[this.SysString[var3][1]][2];
      this.SysString[var3][3] = 100;
      this.SysString[var3][8] = -this.SystxtTable[this.SysString[var3 - 1][1]][2] / 2 - this.SystxtTable[this.SysString[var3][1]][2] / 2;
      this.SysString[var3][9] = 1;
      ++var3;

      for(int var2 = 0; var2 < this.SysString.length; ++var2) {
         if (this.SysString[var2][0] == 1) {
            this.SysString[var2][4] = this.SystxtTable[this.SysString[var2][1]][0];
            this.SysString[var2][5] = this.SystxtTable[this.SysString[var2][1]][1];
            this.SysString[var2][6] = this.SystxtTable[this.SysString[var2][1]][2];
            this.SysString[var2][7] = this.SystxtTable[this.SysString[var2][1]][3];
         }
      }

   }

   public void initStageStart() {
      int var1;
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
         indata = new DataInputStream(this.getClass().getResourceAsStream("/zone" + (this.zoneNumber + 1) + ".bmd"));
         MapW = worldMapData[this.zoneNumber][this.stageNumber][0].length;
         MapH = worldMapData[this.zoneNumber][this.stageNumber].length;
         tempWorldMapData = new byte[MapH][MapW];

         for(var1 = 0; var1 < MapH; ++var1) {
            for(int var2 = 0; var2 < MapW; ++var2) {
               tempWorldMapData[var1][var2] = worldMapData[this.zoneNumber][this.stageNumber][var1][var2];
            }
         }

         indata.read(mapData);
         indata.close();
         this.setMapData();
      } catch (Throwable var4) {
      }

      this.InitObj2();
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
      this.ResetSound();
      this.PlayZoneBGM();
      byte var3 = 0;
      this.SysCount = 0;
      this.SysString[var3][0] = 1;
      this.SysString[var3][1] = this.DAEN_B;
      this.SysString[var3][2] = 240;
      this.SysString[var3][3] = 82;
      this.SysString[var3][8] = 1 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var3][9] = 0;
      int var5 = var3 + 1;
      this.SysString[var5][0] = 1;
      this.SysString[var5][1] = this.ACT1 + this.selectStageNumber;
      this.SysString[var5][2] = 240;
      this.SysString[var5][3] = 108;
      this.SysString[var5][8] = 25 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var5][9] = 3;
      ++var5;
      this.SysString[var5][0] = 1;
      this.SysString[var5][1] = this.ACT;
      this.SysString[var5][2] = 240;
      this.SysString[var5][3] = 116;
      this.SysString[var5][8] = -1 - this.SystxtTable[this.SysString[0][1]][2];
      this.SysString[var5][9] = 2;
      ++var5;
      this.SysString[var5][0] = 1;
      this.SysString[var5][1] = this.zonetable[this.selectZoneNumber];
      this.SysString[var5][2] = 0 - this.SystxtTable[this.SysString[var5][1]][2];
      this.SysString[var5][3] = 84;
      this.SysString[var5][8] = -this.SystxtTable[this.SysString[var5][1]][2];
      this.SysString[var5][9] = 0;
      this.SysCenter = 120 + this.SystxtTable[this.SysString[var5][1]][2] / 2;
      ++var5;
      if (this.selectZoneNumber == 6) {
         this.SysString[var5][0] = 1;
         this.SysString[var5][1] = this.ZONE;
         this.SysString[var5][2] = 0 - this.SystxtTable[this.SysString[var5][1]][2];
         this.SysString[var5][3] = 100;
         this.SysString[var5][8] = -48;
         this.SysString[var5][9] = 1;
      } else {
         this.SysString[var5][0] = 1;
         this.SysString[var5][1] = this.ZONE;
         this.SysString[var5][2] = 0 - this.SystxtTable[this.SysString[var5][1]][2];
         this.SysString[var5][3] = 100;
         this.SysString[var5][8] = -65;
         this.SysString[var5][9] = 1;
      }

      ++var5;

      for(var1 = 0; var1 < this.SysString.length; ++var1) {
         if (this.SysString[var1][0] == 1) {
            this.SysString[var1][4] = this.SystxtTable[this.SysString[var1][1]][0];
            this.SysString[var1][5] = this.SystxtTable[this.SysString[var1][1]][1];
            this.SysString[var1][6] = this.SystxtTable[this.SysString[var1][1]][2];
            this.SysString[var1][7] = this.SystxtTable[this.SysString[var1][1]][3];
         }
      }

   }

   public void drawNumber2(int var1, int var2, int var3) {
      int var6 = var3 % 10;
      int var4 = 1;

      while(true) {
         int var7 = 1;

         int var5;
         for(var5 = 0; var5 < var4; ++var5) {
            var7 *= 10;
         }

         if (var3 / var7 == 0) {
            var7 = var4;
            this.drawRegion(gg, this.m_imgCmd[SYSTXT2], 8 * var6, 48, 8, 16, rotNumTable[TRANS_NONE], var1 - 8, var2, 20);

            for(var4 = 1; var4 < var7; ++var4) {
               int var8 = 1;

               for(var5 = 0; var5 < var4; ++var5) {
                  var8 *= 10;
               }

               var6 = var3 / var8 % 10;
               this.drawRegion(gg, this.m_imgCmd[SYSTXT2], 8 * var6, 48, 8, 16, rotNumTable[TRANS_NONE], var1 - var4 * 8 - 8, var2, 20);
            }

            return;
         }

         ++var4;
      }
   }

   public void readStageObject() {
      try {
         this.ObjImageClear();
         this.StageDataTableClear();
         this.initStage(this.zoneNumber + 1);
         this.objectInit(this.stageNumber);
      } catch (Throwable var2) {
      }

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

      for(int var1 = 0; var1 < objData.length; ++var1) {
         objData[var1][0] = 0;
      }

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

      int var2;
      for(var2 = 0; var2 < 256; ++var2) {
         switchflag[var2] = false;
         switchflag2[var2] = false;
      }

      ObjectListNum = 0;
      this.noDataPointer = 0;

      for(var2 = 0; var2 < ObjectList.length; ++var2) {
         ObjectList[var2][24] = 0;
      }

   }

   public void endStageStart() {
      this.clearKey();
      this.drawZankiFlag = this.drawZankiFlag = this.drawRingFlag = true;
      this.countClear();
      if (this.stageNumber == 2 && this.zoneNumber == 4) {
         this.AddObjectData(150, 11424, 1394, 0, 0);
      }

      for(int var1 = 0; var1 < PlayerParam.length; ++var1) {
         PlayerParam[var1] = 0;
      }

      olddir = 0;
      plspeed[0] = 0;
      plspeed[1] = 0;
      int[][] var2 = new int[][]{{80, 944, 80, 252, 80, 944, 128, 168}, {96, 108, 80, 236, 80, 748, 2944, 0}, {48, 614, 48, 614, 48, 358, 128, 168}, {64, 716, 64, 332, 64, 332, 128, 168}, {48, 957, 48, 445, 48, 236, 128, 168}, {48, 1164, 48, 1868, 8512, 1452, 128, 168, 304, 168}, {1568, 363, 3808, 364, 128, 168, 128, 168}};
      mapOxy[0] = 0;
      mapOxy[1] = 0;
      if (this.zoneNumber == 5 && this.stageNumber == 3) {
         PlayerParam[0] = var2[this.zoneNumber][8] << 8;
         PlayerParam[1] = (var2[this.zoneNumber][9] << 8) + 5120;
      } else {
         PlayerParam[0] = var2[this.zoneNumber][this.stageNumber * 2 + 0] << 8;
         PlayerParam[1] = (var2[this.zoneNumber][this.stageNumber * 2 + 1] << 8) + 5120;
      }

      if (plsaveX != 0 || plsaveY != 0) {
         PlayerParam[0] = plsaveX << 8;
         PlayerParam[1] = plsaveY << 8;
         timecount = plsaveTime;
         timecount2 = plsaveTime2;
         this.water_flag = 0;
         if (this.zoneNumber == 1 && this.stageNumber == 2) {
            this.water_flag = 1;
            if (plsaveX > 4864) {
               this.water_flag = 2;
            }
         }
      }

      this.limitchk(false);
      this.InitViewControl();
      this.waterMove();
      this.waterH3 = this.waterH;
      this.waterMove();
      PlayerParam[8] = -1;
      PlayerParam[9] = 1;
      mode = MODE_FIELD;
      this.addObjectChk();
      this.SetSoftFlag = true;
      this.SetSoftCount = 10;
   }

   public void initStage(int var1) {
      this.LoadImages(var1);

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
            int[] var4 = new int[]{184, 808, 2304, 552};
            this.waterH = this.waterH2 = this.waterH3 = var4[this.stageNumber];
         }

         oldMapOxy[0] = mapOxy[0] = 0;
         oldMapOxy[1] = mapOxy[1] = 0;
         indata = new DataInputStream(this.getClass().getResourceAsStream("/zone" + var1 + ".blt"));
         indata.read(blockLinkTable);
         indata.close();
         indata = new DataInputStream(this.getClass().getResourceAsStream("/MapLzone" + var1 + ".blt"));
         indata.read(mapFrontData);
         indata.close();
         indata = new DataInputStream(this.getClass().getResourceAsStream("/ZONE" + var1 + "ACT.act"));
         zoneActTable[0] = new byte[indata.readShort()];
         zoneActTable[1] = new byte[indata.readShort()];
         zoneActTable[2] = new byte[indata.readShort()];
         zoneActTable[3] = new byte[indata.readShort()];
         indata.read(zoneActTable[0]);
         indata.read(zoneActTable[1]);
         indata.read(zoneActTable[2]);
         indata.read(zoneActTable[3]);
         indata.close();
         indata = new DataInputStream(this.getClass().getResourceAsStream("/scdtblwk.scd"));
         indata.read(scdtblwk);
         indata.close();
         indata = new DataInputStream(this.getClass().getResourceAsStream("/blkcol.bct"));
         indata.read(blockColTable);
         indata.close();
         this.m_imgObj[RING_SFLAG_RING_18_00] = this.createImage("/ring.png");
         this.m_imgObj[SAVE_SFLAG] = this.createImage("/save.png");
         this.m_imgObj[ITEM_NFLAG] = this.createImage("/item.png");
         this.m_imgObj[EFFECT] = this.createImage("/effect.png");
         this.m_imgObj[TOGE_NFLAG] = this.createImage("/toge.png");
         if (this.zoneNumber == 0) {
            this.m_imgObj[BURANKO_NFLAG] = this.createImage("/buranko.png");
         }

         if (this.zoneNumber != 5) {
            this.m_imgObj[MASIN_NFLAG] = this.createImage("/masin.png");
         }

         this.m_imgObj[SJUMP] = this.createImage("/sjump.png");
         this.m_imgObj[1] = this.createImage("/sjump2.png");
         if (this.zoneNumber == 4) {
            this.m_imgObj[SHIMA_NFLAG] = this.createImage("/shima5.png");
         } else if (this.zoneNumber == 0) {
            this.m_imgObj[SHIMA_NFLAG] = this.createImage("/shima.png");
         }

         if (this.zoneNumber == 0) {
            this.m_imgObj[HASHI_NFLAG] = this.createImage("/hashi.png");
            this.m_imgObj[JYAMA_NFLAG] = this.createImage("/jyama.png");
            this.m_imgObj[BREAK_SFLAG] = this.createImage("/break.png");
            this.m_imgObj[KAGEB_NFLAG] = this.createImage("/kageb.png");
         }

         if (this.zoneNumber == 1) {
            if (this.stageNumber < 3) {
               this.m_imgObj[DAI3_NFLAG] = this.createImage("/dai3.png");
               this.m_imgObj[DAI2_0xE0] = this.createImage("/dai2_0xE0.png");
               this.m_imgObj[DAI2_0xF0] = this.createImage("/dai2_0xF0.png");
               this.m_imgObj[KAZARI_SFLAG] = this.createImage("/kazari.png");
               this.m_imgObj[KASSYA_NFLAG] = this.createImage("/kassya.png");
               this.m_imgObj[MIZU_NFLAG] = this.createImage("/mizu.png");
               this.m_imgObj[MIZU_0x09] = this.createImage("/mizu_0x09.png");
               this.m_imgObj[YARI_SFLAG] = this.createImage("/yari.png");
               this.m_imgObj[AWA_NFLAG] = this.createImage("/awa.png");
               this.m_imgObj[OBJAWA] = this.createImage("/objawa.png");
               this.m_imgObj[DAI3_0x27] = this.createImage("/dai3_0x27.png");
               this.m_imgObj[DAI3_0x13] = this.createImage("/dai3_0x13.png");
               this.m_imgObj[BOU_NFLAG] = this.createImage("/bou.png");
               this.m_imgObj[BEN_NFLAG] = this.createImage("/ben.png");
               this.m_imgObj[5] = this.createImage("/water.png");
               this.m_imgObj[WATER2] = this.createImage("/water2.png");
            } else {
               this.m_imgObj[DAI3_NFLAG] = this.createImage("/z_dai3.png");
               this.m_imgObj[DAI3_0x27] = this.createImage("/z_dai3_0x27.png");
               this.m_imgObj[DAI3_0x13] = this.createImage("/z_dai3_0x13.png");
               this.m_imgObj[DAI2_0xE0] = this.createImage("/z_dai2_0xE0.png");
               this.m_imgObj[DAI2_0xF0] = this.createImage("/z_dai2_0xF0.png");
               this.m_imgObj[KAZARI_SFLAG] = this.createImage("/z_kazari.png");
               this.m_imgObj[KASSYA_NFLAG] = this.createImage("/kassya.png");
               this.m_imgObj[MIZU_NFLAG] = this.createImage("/z_mizu.png");
               this.m_imgObj[MIZU_0x09] = this.createImage("/mizu_0x09.png");
               this.m_imgObj[YARI_SFLAG] = this.createImage("/yari.png");
               this.m_imgObj[AWA_NFLAG] = this.createImage("/awa.png");
               this.m_imgObj[OBJAWA] = this.createImage("/objawa.png");
               this.m_imgObj[BOU_NFLAG] = this.createImage("/bou.png");
               this.m_imgObj[92] = this.createImage("/z_ben.png");
               this.m_imgObj[5] = this.createImage("/water.png");
               this.m_imgObj[WATER2] = this.createImage("/water2.png");
            }
         }

         if (this.zoneNumber == 2) {
            this.m_imgObj[OCHI_NFLAG] = this.createImage("/ochi.png");
            this.m_imgObj[DAINFLA] = this.createImage("/dai.png");
            this.m_imgObj[TURI_NFLAG] = this.createImage("/turi.png");
            this.m_imgObj[TURI2] = this.createImage("/turi2.png");
            this.m_imgObj[TURI3] = this.createImage("/turi3.png");
            this.m_imgObj[YOGAN2_SFLAG] = this.createImage("/yogan2.png");
            this.m_imgObj[YOGAN2] = this.createImage("/yogan22.png");
            this.m_imgObj[FBLOCK_NFLAG] = this.createImage("/fblock.png");
            this.m_imgObj[YOGANC_NFLAG] = this.createImage("/yoganc.png");
            this.m_imgObj[MYOGAN_NFLAG] = this.createImage("/myogan.png");
            this.m_imgObj[MYOGAN2] = this.createImage("/myogan2.png");
            this.m_imgObj[YUKA_NFLAG] = this.createImage("/yuka.png");
            this.m_imgObj[BRYUKA_NFLAG] = this.createImage("/bryuka.png");
         }

         this.m_imgObj[SWITCH2_NFLAG] = this.createImage("/switch.png");
         if (this.zoneNumber == 3) {
            this.m_imgObj[FUN_NFLAG] = this.createImage("/fun.png");
            this.m_imgObj[BRKABE] = this.createImage("/brkabe.png");
            this.m_imgObj[PEDAL] = this.createImage("/pedal.png");
            this.m_imgObj[STEP] = this.createImage("/step.png");
            this.m_imgObj[ELEV_NFLAG] = this.createImage("/elev.png");
            this.m_imgObj[SISOO_NFLAG] = this.createImage("/sisoo.png");
         }

         if (this.zoneNumber == 4) {
            this.m_imgObj[DAI2] = this.createImage("/dai2.png");
            this.m_imgObj[SIGNAL_NFLAG] = this.createImage("/signal.png");
            this.m_imgObj[BOBIN_SFLAG] = this.createImage("/bobin.png");
         }

         if (this.zoneNumber == 5 && this.stageNumber != 3) {
            this.m_imgObj[PATA_NFLAG] = this.createImage("/paka2.png");
            this.m_imgObj[ELE_NFLAG] = this.createImage("/ele.png");
            this.m_imgObj[MAWARU_NFLAG] = this.createImage("/mawaru.png");
            this.m_imgObj[YUKAI_NFLAG] = this.createImage("/yukai.png");
            this.m_imgObj[DAI4_NFLAG] = this.createImage("/dai4.png");
            this.m_imgObj[DAI_NFLAG] = this.createImage("/dai_.png");
         }

         this.m_imgObj[FIRE6_NFLAG] = this.createImage("/fire6.png");
         if (this.zoneNumber == 4) {
            this.m_imgObj[DAI3_NFLAG] = this.createImage("/dai4_.png");
         }

         this.m_imgObj[TAMA] = this.createImage("/tama.png");
         this.m_imgObj[BAKUHATU] = this.createImage("/bakuhatu.png");
         this.IkeshitaLoadStageImage(var1);
         this.AraiLoadStageImage(var1);
         DistantBg.setStage(this.zoneNumber, this.stageNumber);
      } catch (Throwable var5) {
      }

   }

   public void LoadImages(int var1) {
      try {
         m_imgMimg = null;
         System.gc();
         Thread.sleep(200L);
         if (var1 == 2 && this.stageNumber == 3) {
            m_imgMimg = this.createImage("/z_zone2.png");
         } else {
            m_imgMimg = this.createImage("/zone" + var1 + ".png");
         }

         if (this.m_imgCmd[SONIC_N] == null) {
            this.m_imgCmd[SONIC_N] = this.createImage("/sonic.png");
         }

         if (this.m_imgCmd[SONIC_S] == null) {
            this.m_imgCmd[SONIC_S] = this.createImage("/sonic_s.png");
         }
      } catch (Throwable var5) {
      }

   }

   public void playerAction() {
      this.waterMove();
      int[] var10000;
      if (debugFlag) {
         if (KeyPress[2]) {
            if (KeyPress[0]) {
               var10000 = PlayerParam;
               var10000[1] -= 128;
            } else {
               var10000 = PlayerParam;
               var10000[1] -= 2048;
            }
         }

         if (KeyPress[1]) {
            if (KeyPress[0]) {
               var10000 = PlayerParam;
               var10000[1] += 128;
            } else {
               var10000 = PlayerParam;
               var10000[1] += 2048;
            }
         } else if (KeyPress[3]) {
            if (KeyPress[0]) {
               var10000 = PlayerParam;
               var10000[0] -= 128;
            } else {
               var10000 = PlayerParam;
               var10000[0] -= 2048;
            }
         } else if (KeyPress[4]) {
            if (KeyPress[0]) {
               var10000 = PlayerParam;
               var10000[0] += 128;
            } else {
               var10000 = PlayerParam;
               var10000[0] += 2048;
            }
         }
      } else if (!PlayerNoCol && !PlayerNoCtrl) {
         if (PlayerDie) {
            var10000 = PlayerParam;
            var10000[1] += PlayerParam[5];
            var10000 = PlayerParam;
            var10000[5] += gravity;
         } else if (this.kyuryuchk()) {
            if (this.damageMoveTimer > 0) {
               --this.damageMoveTimer;
               var10000 = PlayerParam;
               var10000[1] -= PlayerParam[3] * 2;
               var10000 = PlayerParam;
               var10000[0] += PlayerParam[5];
               var10000 = PlayerParam;
               var10000[5] += gravity;
            } else {
               for(int var1 = 0; var1 < 4; ++var1) {
                  PlayerParam[3] = 512;
                  int var2;
                  if ((this.PlayerPosY() - 24) % 128 < 64) {
                     if (this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY() - 24)) {
                        var10000 = PlayerParam;
                        var10000[1] += 256;

                        for(var2 = 0; this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY() - 24) && var2 <= 24; var10000[0] -= 256) {
                           ++var2;
                           var10000 = PlayerParam;
                        }
                     } else if (this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY())) {
                        var10000 = PlayerParam;
                        var10000[1] -= 256;

                        for(var2 = 0; this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY()) && var2 <= 24; var10000[0] -= 256) {
                           ++var2;
                           var10000 = PlayerParam;
                        }
                     } else {
                        var10000 = PlayerParam;
                        var10000[0] += PlayerParam[3];
                     }
                  } else if (this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY())) {
                     var10000 = PlayerParam;
                     var10000[1] -= 256;

                     for(var2 = 0; this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY()) && var2 <= 24; var10000[0] -= 256) {
                        ++var2;
                        var10000 = PlayerParam;
                     }
                  } else if (this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY() - 24)) {
                     var10000 = PlayerParam;
                     var10000[1] += 256;

                     for(var2 = 0; this.blockColChk(this.PlayerPosX() + 24, this.PlayerPosY() - 24) && var2 <= 24; var10000[0] -= 256) {
                        ++var2;
                        var10000 = PlayerParam;
                     }
                  } else {
                     var10000 = PlayerParam;
                     var10000[0] += PlayerParam[3];
                  }

                  if (KeyPress[2] && var1 == 0) {
                     var10000 = PlayerParam;
                     var10000[1] -= 256;
                     if (this.blockColChk_Enemy(this.PlayerPosX() + 24, this.PlayerPosY() - 24)) {
                        var10000 = PlayerParam;
                        var10000[1] += 256;
                     }
                  }

                  if (KeyPress[1] && var1 == 0) {
                     var10000 = PlayerParam;
                     var10000[1] += 256;
                  }
               }

               PlayerJump = true;
               PlayerDamage = false;
               PlayerSWater = true;
               PlayerParam[3] = 2048;
            }
         } else if (this.ballchk()) {
            if (this.ball00walk()) {
               this.ball00jump();
            }
         } else if (this.play00walk()) {
            this.play00jump();
         }
      } else {
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
      }

   }

   public int rnd(int var1) {
      return Math.abs(this.rnd.nextInt()) % var1;
   }

   public boolean blockColChk(int var1, int var2) {
      try {
         if (var1 < 0) {
            var1 = 0;
         }

         if (var2 < 0) {
            var2 = 0;
         }

         ++this.blockColCount;
         int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
         if (this.hitChk[var3 >> 1] == 1) {
            return false;
         }

         int var4 = (blockLinkTable[(mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8)] & 255) << 5;
         if (this.rot[var3 >> 1] == 1) {
            if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
               return false;
            }
         } else if (this.rot[var3 >> 1] == 2) {
            if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
               return false;
            }
         } else if (this.rot[var3 >> 1] == 3) {
            if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
               return false;
            }
         } else if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
            return false;
         }

         var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
         PlayerParam[8] = var4;
         if (this.zoneNumber == 1) {
            if (var4 > 70 && var4 < 84) {
               PlayerWater = true;
               PlayerParam[12] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerParam[10] = 4096;
               if (this.rot[var3 >> 1] == 0) {
                  PlayerParam[12] = 1;
                  PlayerParam[13] = 0;
                  PlayerParam[14] = 0;
                  PlayerParam[10] = -4096;
               }
            } else {
               var2 -= 16;
               if (var1 < 0) {
                  var1 = 0;
               }

               if (var2 < 0) {
                  var2 = 0;
               }

               var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
               var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
               if (var4 != 83 && var4 != 186) {
                  var1 -= 16;
                  var2 += 16;
                  if (var1 < 0) {
                     var1 = 0;
                  }

                  if (var2 < 0) {
                     var2 = 0;
                  }

                  var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
                  var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
                  if (var4 != 71 && var4 != 72) {
                     PlayerWater = false;
                  } else {
                     PlayerWater = true;
                  }
               } else {
                  PlayerWater = true;
                  PlayerParam[12] = 0;
                  PlayerParam[13] = 0;
                  PlayerParam[14] = 0;
                  PlayerParam[10] = 4096;
                  if (this.rot[var3 >> 1] == 0) {
                     PlayerParam[12] = 1;
                     PlayerParam[13] = 0;
                     PlayerParam[14] = 0;
                     PlayerParam[10] = -4096;
                  }
               }
            }
         }
      } catch (Throwable var5) {
      }

      return true;
   }

   public boolean blockColChk2(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 < 0) {
         var2 = 0;
      }

      int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
      if (this.hitChk2[var3 >> 1] == 1) {
         return false;
      } else {
         int var4 = (blockLinkTable[(mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8)] & 255) << 5;
         if (this.rot[var3 >> 1] == 1) {
            if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
               return false;
            }
         } else if (this.rot[var3 >> 1] == 2) {
            if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
               return false;
            }
         } else if (this.rot[var3 >> 1] == 3) {
            if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
               return false;
            }
         } else if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
            return false;
         }

         var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
         PlayerParam[8] = var4;
         return true;
      }
   }

   public boolean blockColChk_easy(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 < 0) {
         var2 = 0;
      }

      int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
      return this.hitChk[var3 >> 1] != 1;
   }

   public boolean blockColChk_Enemy(int var1, int var2) {
      try {
         if (var1 < 0) {
            var1 = 0;
         }

         if (var2 < 0) {
            var2 = 0;
         }

         int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
         if (this.hitChk[var3 >> 1] == 1 && this.hitChk2[var3 >> 1] == 1) {
            return false;
         } else {
            int var4 = (blockLinkTable[(mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8)] & 255) << 5;
            if (this.rot[var3 >> 1] == 1) {
               if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
                  return false;
               }
            } else if (this.rot[var3 >> 1] == 2) {
               if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
                  return false;
               }
            } else if (this.rot[var3 >> 1] == 3) {
               if (0 == (blockColTable[var4 + (15 - (var1 & 15) << 1) + (15 - (var2 & 15) >> 3)] >> (var2 & 7) & 1)) {
                  return false;
               }
            } else if (0 == (blockColTable[var4 + ((var1 & 15) << 1) + ((var2 & 15) >> 3)] >> 7 - (var2 & 7) & 1)) {
               return false;
            }

            var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
            this.enemyBlock = var4;
            return true;
         }
      } catch (Throwable var5) {
         return true;
      }
   }

   public int blockdirChk(int var1) {
      return scddirtbl[blockLinkTable[var1] & 255] & 255;
   }

   public boolean rcol2() {
      if (!this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 12)) {
         return false;
      } else {
         byte var2 = 14;

         for(int var1 = 0; var1 < var2; ++var1) {
            int[] var10000 = PlayerParam;
            var10000[0] -= 256;
            if (!this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 12)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean lcol2() {
      if (!this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 12)) {
         return false;
      } else {
         byte var2 = 14;

         for(int var1 = 0; var1 < var2; ++var1) {
            int[] var10000 = PlayerParam;
            var10000[0] += 256;
            if (!this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 12)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean rcol3() {
      if (!this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 24)) {
         return false;
      } else {
         byte var2 = 14;

         for(int var1 = 0; var1 < var2; ++var1) {
            int[] var10000 = PlayerParam;
            var10000[0] -= 256;
            if (!this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 24)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean lcol3() {
      if (!this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 24)) {
         return false;
      } else {
         byte var2 = 14;

         for(int var1 = 0; var1 < var2; ++var1) {
            int[] var10000 = PlayerParam;
            var10000[0] += 256;
            if (!this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 24)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean rcol() {
      int var1;
      int var2;
      if (olddir > 22 && olddir < 338) {
         if (this.zoneNumber != 1 || olddir != 316) {
            return false;
         }

         var1 = this.PlayerPosX() + 12;
         var2 = this.PlayerPosY() - 36;
      } else {
         var1 = this.PlayerPosX() + 12;
         var2 = this.PlayerPosY() - 12;
      }

      if ((this.zoneNumber == 4 || this.zoneNumber == 3) && olddir != 0) {
         return false;
      } else if (!this.blockColChk2(var1, var2)) {
         return false;
      } else {
         for(int var3 = 0; var3 < 14; ++var3) {
            int[] var10000 = PlayerParam;
            var10000[0] -= 256;
            --var1;
            if (!this.blockColChk(var1, var2)) {
               break;
            }
         }

         return true;
      }
   }

   public boolean lcol() {
      if (olddir > 22 && olddir < 338) {
         return false;
      } else if ((this.zoneNumber == 4 || this.zoneNumber == 3) && olddir != 0) {
         return false;
      } else if (this.zoneNumber == 2 && olddir == 22) {
         return false;
      } else {
         int var1 = this.PlayerPosX() - 12;
         int var2 = this.PlayerPosY() - 12;
         if (!this.blockColChk2(var1, var2)) {
            return false;
         } else {
            for(int var3 = 0; var3 < 14; ++var3) {
               int[] var10000 = PlayerParam;
               var10000[0] += 256;
               ++var1;
               if (!this.blockColChk(var1, var2)) {
                  break;
               }
            }

            return true;
         }
      }
   }

   public boolean hcol() {
      try {
         byte var1 = 32;
         if (PlayerSJump) {
            var1 = 32;
         }

         int[] var10000;
         int var2;
         byte var3;
         if (this.blockColChk2(this.PlayerPosX(), this.PlayerPosY() - var1)) {
            var3 = 14;

            for(var2 = 0; var2 < var3; ++var2) {
               var10000 = PlayerParam;
               var10000[1] += 256;
               if (!this.blockColChk2(this.PlayerPosX(), this.PlayerPosY() - var1)) {
                  var10000 = PlayerParam;
                  var10000[1] -= 256;
                  break;
               }
            }

            return true;
         }

         var1 = 16;
         if (this.blockColChk2(this.PlayerPosX(), this.PlayerPosY() - var1)) {
            var3 = 14;

            for(var2 = 0; var2 < var3; ++var2) {
               var10000 = PlayerParam;
               var10000[1] += 256;
               if (!this.blockColChk2(this.PlayerPosX(), this.PlayerPosY() - var1)) {
                  var10000 = PlayerParam;
                  var10000[1] -= 256;
                  break;
               }
            }

            return true;
         }
      } catch (Throwable var4) {
      }

      return false;
   }

   public boolean fcol(int var1) {
      try {
         if (this.blockColChk(this.PlayerPosX(), this.PlayerPosY())) {
            byte var3 = 16;
            int var4 = olddir;
            if (var1 == -1) {
            }

            if (this.zoneNumber != 3 || olddir != 90 || PlayerParam[8] != 370 && PlayerParam[8] != 371) {
               if (var4 < 0) {
                  var4 = 0;
               }

               for(int var2 = 0; var2 < var3; ++var2) {
                  int[] var10000;
                  if (!PlayerJump) {
                     var10000 = PlayerParam;
                     var10000[0] -= (this.dSin(var4 + 180) << 8) / 100;
                  }

                  var10000 = PlayerParam;
                  var10000[1] -= this.dCos(var4 + 180) * 256 / 100;
                  if (!this.blockColChk(this.PlayerPosX(), this.PlayerPosY())) {
                     if (!PlayerJump) {
                        var10000 = PlayerParam;
                        var10000[0] += (this.dSin(var4 + 180) << 8) / 100;
                     }

                     var10000 = PlayerParam;
                     var10000[1] += (this.dCos(var4 + 180) << 8) / 100;
                     olddir = this.getPlayerArg(this.PlayerPosX(), this.PlayerPosY());
                     break;
                  }
               }

               return true;
            }

            return true;
         }
      } catch (Throwable var5) {
      }

      return false;
   }

   public void playerPushSet() {
      if (!PlayerJump && PlayerBall) {
         PlayerBall = false;
      }

      this.pushCount = 2;
   }

   public void playerBressChk() {
      if (this.zoneNumber == 1 && this.waterH2 < this.PlayerPosY() - 12) {
         --this.bressCount;
         if (this.bressCount < 0) {
            this.bressDie = true;
            this.playerDie();
            this.PlayMusic(29);
            PlayerParam[5] = 256;
         }
      } else {
         this.bressCount = 2100;
      }

   }

   public boolean jumpchk(int var1) {
      if (0 != var1) {
         return false;
      } else if (PlayerJump) {
         return true;
      } else if (this.limitBreak) {
         return false;
      } else if (this.zoneNumber != 0 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 31 && tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 32) {
         if (!KeyPress[2]) {
            return false;
         } else {
            int var2;
            if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
               PlayerJump = true;
               PlayerDamage = false;
               PlayerAir = false;
               raidOn = false;
               PlayerBall = true;
               var2 = this.getPlayerArg(this.PlayerPosX(), this.PlayerPosY());
               if (var2 < 0) {
                  var2 = 0;
               }

               PlayerParam[3] = this.dSin(var2) * (pljump + PlayerParam[10]) / 100 + PlayerParam[10];
               PlayerParam[5] = this.dCos(var2) * (pljump + PlayerParam[10]) / 100;
               return true;
            } else if (raidOn) {
               PlayerJump = true;
               PlayerDamage = false;
               PlayerAir = false;
               raidOn = false;
               PlayerBall = true;
               PlayerParam[3] = this.dSin(0) * pljump / 100 + PlayerParam[10];
               PlayerParam[5] = this.dCos(0) * pljump / 100;
               return true;
            } else {
               PlayerJump = true;
               PlayerDamage = false;
               PlayerAir = false;
               raidOn = false;
               PlayerBall = true;
               var2 = olddir;
               int var3 = this.PlayerPosX() / 16 / 16;
               int var4 = this.PlayerPosY() / 16 / 16;
               var4 %= MapH;
               int var5 = tempWorldMapData[var4][var3] * 512 + (this.PlayerPosX() / 16 % 16 + this.PlayerPosY() / 16 % 16 * 16) * 2 + 1;
               int var6 = 0;
               int var7 = mapData[var5 - 1] & 255;
               byte var8 = (byte)(var7 << 6);
               var8 = (byte)Math.abs(var8 >> 6);
               if (var8 % 4 != 0) {
                  var6 = 256 * (var8 % 4);
               }

               int var9 = (mapData[var5] & 255) + var6;
               byte var10 = (byte)this.blockdirChk(var9);
               if (olddir == 270) {
                  nocoltimer = 5;
                  PlayerParam[3] = -pljump;
                  PlayerParam[5] = -Math.abs(pljump);
               } else if (olddir == 90) {
                  nocoltimer = 5;
                  PlayerParam[3] = pljump;
                  PlayerParam[5] = -Math.abs(pljump);
               } else if (var10 != 0 && olddir <= 290 && olddir >= 70) {
                  PlayerParam[3] = this.dSin(var2) * pljump / 100;
                  PlayerParam[5] = this.dCos(var2) * pljump / 100;
               } else {
                  PlayerParam[3] = this.dSin(var2) * pljump / 100 + PlayerParam[10];
                  PlayerParam[5] = this.dCos(var2) * pljump / 100;
                  if (PlayerParam[5] > 0) {
                     this.nofcolTimer = 1;
                  }

                  if (Math.abs(PlayerParam[10]) > 2560 && this.zoneNumber == 4 && this.stageNumber == 1 && PlayerParam[0] > 9216) {
                     int[] var10000 = PlayerParam;
                     var10000[5] -= 768;
                     PlayerParam[3] = this.dSin(var2) * pljump / 100 + 2560;
                  }
               }

               return true;
            }
         }
      } else {
         KeyPress[2] = false;
         return false;
      }
   }

   public boolean ballchk() {
      if (this.CrouchCount > -1) {
         --this.CrouchCount;
      }

      if (this.LookUpCount > -1) {
         --this.LookUpCount;
      }

      if (PlayerBall) {
         return true;
      } else {
         if (!PlayerJump && !this.limitBreak) {
            if (KeyPress[1]) {
               if (Math.abs(PlayerParam[10]) > plretspd) {
                  PlayerBall = true;
                  return true;
               }

               if (!KeyPress[3] && !KeyPress[4]) {
                  PlayerCrouch = true;
                  this.CrouchCount += 2;
                  if (this.CrouchCount > 32) {
                     this.CrouchCount = 32;
                  }

                  return false;
               }
            } else if (KeyPress[0] && Math.abs(PlayerParam[10]) <= plretspd && !KeyPress[3] && !KeyPress[4]) {
               PlayerLookUp = true;
               this.LookUpCount += 2;
               if (this.LookUpCount > 24) {
                  this.LookUpCount = 24;
               }

               return false;
            }
         }

         return false;
      }
   }

   public boolean setPlayerPos() {
      this.rhit = false;
      this.lhit = false;
      int var2 = olddir;
      short var3 = 32;
      short var4 = 256;
      if (olddir != 22 && olddir != 338) {
         var3 = 256;
      }

      if (olddir >= 270 && olddir < 300) {
         var4 = 32;
      }

      if (olddir >= 60 && olddir < 90) {
         var4 = 32;
      }

      if (this.zoneNumber == 0 && (tempWorldMapData[this.PlayerPosY() >> 8][this.PlayerPosX() >> 8] == 31 || tempWorldMapData[this.PlayerPosY() >> 8][this.PlayerPosX() >> 8] == 32)) {
         var4 = 256;
      }

      int var5 = olddir;
      if (this.fcol()) {
         raidOn = false;
         if (this.zoneNumber == 4 && (var5 == 79 && olddir == 90 || var5 == 281 && olddir == 270)) {
            if (olddir == 90) {
               PlayerParam[10] = 640;
            } else {
               PlayerParam[10] = -640;
            }

            olddir = var5;
            this.noLeverTimer = 0;
            this.nofcolTimer = 3;
            return false;
         } else {
            return true;
         }
      } else {
         for(int var1 = 0; var1 < 14; ++var1) {
            int[] var10000 = PlayerParam;
            var10000[0] += this.dSin(var2 + 180) * var3 / 100;
            var10000 = PlayerParam;
            var10000[1] += this.dCos(var2 + 180) * var4 / 100;
            if (this.fcol()) {
               raidOn = false;
               if (this.zoneNumber == 4 && (var5 == 79 && olddir == 90 || var5 == 281 && olddir == 270)) {
                  if (olddir == 90) {
                     PlayerParam[10] = 640;
                  } else {
                     PlayerParam[10] = -640;
                  }

                  olddir = var5;
                  this.noLeverTimer = 0;
                  this.nofcolTimer = 3;
                  return false;
               }

               return true;
            }
         }

         return false;
      }
   }

   private boolean fcol() {
      if (this.zoneNumber == 5 && this.stageNumber != 2) {
         if (37 != PlayerParam[8] && 38 != PlayerParam[8] && 39 != PlayerParam[8] && 41 != PlayerParam[8] && 42 != PlayerParam[8] && 47 != PlayerParam[8] && 48 != PlayerParam[8] && 49 != PlayerParam[8] && 52 != PlayerParam[8] && 59 != PlayerParam[8] && 62 != PlayerParam[8] && 69 != PlayerParam[8] && 71 != PlayerParam[8] && 72 != PlayerParam[8] && 78 != PlayerParam[8] && 79 != PlayerParam[8] && 87 != PlayerParam[8] && 88 != PlayerParam[8] && 89 != PlayerParam[8]) {
            if (plspeed[0] >= 0) {
               this.rhit = this.fcol_r();
               if (!this.rhit) {
                  this.lhit = this.fcol_l();
               }
            }

            if (plspeed[0] < 0) {
               this.lhit = this.fcol_l();
               if (!this.lhit) {
                  this.rhit = this.fcol_r();
               }
            }

            return this.rhit || this.lhit;
         } else {
            return this.fcol(0);
         }
      } else if (this.zoneNumber == 0) {
         if (tempWorldMapData[this.PlayerPosY() >> 8][this.PlayerPosX() >> 8] != 31 && tempWorldMapData[this.PlayerPosY() >> 8][this.PlayerPosX() >> 8] != 32) {
            if (plspeed[0] >= 0) {
               this.rhit = this.fcol_r();
               if (!this.rhit) {
                  this.lhit = this.fcol_l();
               }
            }

            if (plspeed[0] < 0) {
               this.lhit = this.fcol_l();
               if (!this.lhit) {
                  this.rhit = this.fcol_r();
               }
            }

            return this.rhit || this.lhit;
         } else {
            return this.fcol(0);
         }
      } else if (this.zoneNumber == 3) {
         if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 42 && tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 43 && tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 52 && tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] != 53) {
            if (plspeed[0] >= 0) {
               this.rhit = this.fcol_r();
               if (!this.rhit) {
                  this.lhit = this.fcol_l();
               }
            }

            if (plspeed[0] < 0) {
               this.lhit = this.fcol_l();
               if (!this.lhit) {
                  this.rhit = this.fcol_r();
               }
            }

            return this.rhit || this.lhit;
         } else {
            return this.fcol(0);
         }
      } else {
         if (plspeed[0] >= 0) {
            this.rhit = this.fcol_r();
            if (!this.rhit) {
               this.lhit = this.fcol_l();
            }
         }

         if (plspeed[0] < 0) {
            this.lhit = this.fcol_l();
            if (!this.lhit) {
               this.rhit = this.fcol_r();
            }
         }

         return this.rhit || this.lhit;
      }
   }

   private boolean fcol_r() {
      try {
         int var1 = olddir;
         if (var1 < 0) {
            var1 = 0;
         }

         int var2 = this.PlayerW;
         int var3 = this.PlayerPosX() + this.dSin(var1 + 90) * var2 / 100;
         int var4 = this.PlayerPosY() + this.dCos(var1 + 90) * var2 / 100;
         if (this.blockColChk(var3, var4)) {
            this.getPlayerArg(var3, var4);
            if (this.zoneNumber == 3 && (PlayerParam[8] == 1 || PlayerParam[8] == 17 || PlayerParam[8] == 319) && (olddir >= 270 && olddir < 300 || olddir > 60 && olddir <= 90)) {
               if (olddir >= 270 && olddir < 300) {
                  olddir = 270;
               } else {
                  olddir = 90;
               }

               return true;
            }

            byte var7 = 16;

            for(int var5 = 0; var5 < var7; ++var5) {
               int var6 = this.getPlayerArg(var3, var4);
               int[] var10000;
               if (!PlayerJump) {
                  var10000 = PlayerParam;
                  var10000[0] -= (this.dSin(var1 + 180) << 8) / 100;
               }

               var10000 = PlayerParam;
               var10000[1] -= (this.dCos(var1 + 180) << 8) / 100;
               var3 = this.PlayerPosX() + this.dSin(var1 + 90) * var2 / 100;
               var4 = this.PlayerPosY() + this.dCos(var1 + 90) * var2 / 100;
               if (!this.blockColChk(var3, var4)) {
                  if (!PlayerJump) {
                     var10000 = PlayerParam;
                     var10000[0] += (this.dSin(var1 + 180) << 8) / 100;
                  }

                  var10000 = PlayerParam;
                  var10000[1] += (this.dCos(var1 + 180) << 8) / 100;
                  if (this.zoneNumber == 3 && var6 == 350) {
                     var6 = 0;
                  }

                  olddir = var6;
                  break;
               }
            }

            return true;
         }
      } catch (Throwable var8) {
      }

      return false;
   }

   private boolean fcol_l() {
      try {
         int var1 = olddir;
         if (var1 < 0) {
            var1 = 0;
         }

         int var2 = this.PlayerW;
         int var3 = this.PlayerPosX() + this.dSin(var1 + 270) * var2 / 100;
         int var4 = this.PlayerPosY() + this.dCos(var1 + 270) * var2 / 100;
         if (this.blockColChk(var3, var4)) {
            this.getPlayerArg(var3, var4);
            if (this.zoneNumber == 3 && (PlayerParam[8] == 1 || PlayerParam[8] == 17 || PlayerParam[8] == 319) && (olddir >= 270 && olddir < 300 || olddir > 60 && olddir <= 90)) {
               if (olddir >= 270 && olddir < 300) {
                  olddir = 270;
               } else {
                  olddir = 90;
               }

               return true;
            }

            byte var7 = 16;

            for(int var5 = 0; var5 < var7; ++var5) {
               int var6 = this.getPlayerArg(var3, var4);
               int[] var10000;
               if (!PlayerJump) {
                  var10000 = PlayerParam;
                  var10000[0] -= (this.dSin(var1 + 180) << 8) / 100;
               }

               var10000 = PlayerParam;
               var10000[1] -= (this.dCos(var1 + 180) << 8) / 100;
               var3 = this.PlayerPosX() + this.dSin(var1 + 270) * var2 / 100;
               var4 = this.PlayerPosY() + this.dCos(var1 + 270) * var2 / 100;
               if (!this.blockColChk(var3, var4)) {
                  if (!PlayerJump) {
                     var10000 = PlayerParam;
                     var10000[0] += (this.dSin(var1 + 180) << 8) / 100;
                  }

                  var10000 = PlayerParam;
                  var10000[1] += (this.dCos(var1 + 180) << 8) / 100;
                  if (this.zoneNumber == 3 && var6 == 350) {
                     var6 = 0;
                  }

                  olddir = var6;
                  break;
               }
            }

            return true;
         }
      } catch (Throwable var8) {
      }

      return false;
   }

   public boolean play00walk() {
      plspeed[0] = 0;
      plspeed[1] = 0;
      if (this.jumpchk(0)) {
         return true;
      } else {
         int var1 = Math.abs(PlayerParam[10]);
         this.keispd(0);
         this.levermove();
         if (var1 <= plmaxspd && Math.abs(PlayerParam[10]) > plmaxspd && var1 <= plmaxspd) {
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = -plmaxspd;
            } else {
               PlayerParam[10] = plmaxspd;
            }
         }

         int[] var10000;
         if ((olddir <= 22 || olddir >= 338) && (var1 > plmaxspd || !KeyPress[3] && !KeyPress[4] && PlayerParam[10] != 0) && PlayerParam[10] != 0) {
            if (PlayerParam[10] < 0) {
               var10000 = PlayerParam;
               var10000[10] += pladdspd;
               if (PlayerParam[10] > 0) {
                  PlayerParam[10] = 0;
                  PlayerParam[13] = 0;
                  PlayerParam[14] = 0;
               }
            } else {
               var10000 = PlayerParam;
               var10000[10] -= pladdspd;
               if (PlayerParam[10] < 0) {
                  PlayerParam[10] = 0;
                  PlayerParam[13] = 0;
                  PlayerParam[14] = 0;
               }
            }
         }

         if (Math.abs(PlayerParam[10]) > 4096) {
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = -4096;
            } else {
               PlayerParam[10] = 4096;
            }
         }

         if (this.ballchk()) {
            return false;
         } else {
            this.speedset(0);
            if (this.zoneNumber == 0 || this.zoneNumber == 3) {
               this.loopchange();
            }

            if (!PlayerNoCol) {
               boolean var3 = false;
               if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 31 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 32)) {
                  var3 = true;
                  PlayerBall = true;
                  var10000 = PlayerParam;
                  var10000[10] += 256;
                  if (PlayerParam[10] > 4096) {
                     PlayerParam[10] = 4096;
                  }
               }

               if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 54)) {
                  var3 = true;
               }

               if (this.zoneNumber == 3 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 42 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 43 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53)) {
                  var3 = true;
               }

               if (!var3) {
                  if (raidOn) {
                     if (this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 24) && this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 24)) {
                        if (this.rcol2()) {
                           PlayerParam[10] = 0;
                           PlayerParam[13] = 0;
                           PlayerParam[14] = 0;
                           if (KeyPress[4]) {
                              this.playerPushSet();
                           }
                        }

                        if (this.lcol2()) {
                           PlayerParam[10] = 0;
                           PlayerParam[13] = 0;
                           PlayerParam[14] = 0;
                           if (KeyPress[3]) {
                              this.playerPushSet();
                           }
                        }
                     } else {
                        if (this.rcol3() || this.rcol2()) {
                           PlayerParam[10] = 0;
                           PlayerParam[13] = 0;
                           PlayerParam[14] = 0;
                           if (KeyPress[4]) {
                              this.playerPushSet();
                           }
                        }

                        if (this.lcol3() || this.lcol2()) {
                           PlayerParam[10] = 0;
                           PlayerParam[13] = 0;
                           PlayerParam[14] = 0;
                           if (KeyPress[3]) {
                              this.playerPushSet();
                           }
                        }
                     }
                  } else {
                     if (this.rcol()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[4]) {
                           this.playerPushSet();
                        }
                     }

                     if (this.lcol()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[3]) {
                           this.playerPushSet();
                        }
                     }
                  }
               }

               int var4 = PlayerParam[0];
               int var5 = PlayerParam[1];
               boolean var6 = true;
               if (this.setPlayerPos()) {
                  this.crushing[0] = true;
                  var6 = false;
               }

               if (this.noLeverTimer > 0) {
                  if (var6) {
                     PlayerParam[0] = var4;
                     PlayerParam[1] = var5;
                  }
               } else if (!raidOn && var6) {
                  PlayerParam[0] = var4;
                  PlayerParam[1] = var5;
                  if (olddir == 22 && this.zoneNumber == 1 && this.stageNumber == 1) {
                     PlayerParam[3] = this.dSin(90) * PlayerParam[10] / 100;
                     PlayerParam[5] = this.dCos(90) * PlayerParam[10] / 100;
                  } else {
                     PlayerParam[3] = this.dSin(olddir + 90) * PlayerParam[10] / 100;
                     PlayerParam[5] = this.dCos(olddir + 90) * PlayerParam[10] / 100;
                  }

                  if ((olddir - 90) % 180 == 0) {
                     if (olddir == 90) {
                        var10000 = PlayerParam;
                        var10000[0] += 256;
                        if (this.zoneNumber == 4 && this.stageNumber != 0) {
                           PlayerParam[5] = 0;
                        }
                     } else {
                        var10000 = PlayerParam;
                        var10000[0] -= 256;
                        if (PlayerParam[10] > 2816) {
                           this.noLeverTimer = 15;
                        }
                     }
                  }

                  PlayerParam[10] = 0;
                  PlayerParam[13] = 0;
                  PlayerParam[14] = 0;
                  PlayerAir = true;
                  PlayerJump = true;
                  PlayerDamage = false;
                  raidOn = false;
                  if (this.hcol()) {
                     PlayerParam[5] = 0;
                  }
               } else {
                  var3 = false;
                  if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 31 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 32)) {
                     var3 = true;
                  }

                  if (this.zoneNumber == 2 && (PlayerParam[8] == 365 || PlayerParam[8] == 364 || PlayerParam[8] == 363 || PlayerParam[8] == 362)) {
                     this.playdamageset();
                  }

                  if (!var3) {
                     this.fallchk();
                  }
               }
            }

            return false;
         }
      }
   }

   public void play00jump() {
      int[] var10000;
      if (this.noLeverTimer > 0) {
         --this.noLeverTimer;
      } else if (KeyPress[3]) {
         PlayerParam[12] = 1;
         if (PlayerParam[3] == 0) {
            var10000 = PlayerParam;
            var10000[3] -= plretspd << 1;
         } else {
            var10000 = PlayerParam;
            var10000[3] -= pladdspd;
            if (PlayerParam[3] > 0) {
               var10000 = PlayerParam;
               var10000[3] -= pladdspd;
            }
         }
      } else if (KeyPress[4]) {
         PlayerParam[12] = 0;
         if (PlayerParam[3] == 0) {
            var10000 = PlayerParam;
            var10000[3] += plretspd << 1;
         } else {
            var10000 = PlayerParam;
            var10000[3] += pladdspd;
            if (PlayerParam[3] < 0) {
               var10000 = PlayerParam;
               var10000[3] += pladdspd;
            }
         }
      }

      if (Math.abs(PlayerParam[3]) > plmaxspd) {
         if (PlayerParam[3] < 0) {
            PlayerParam[3] = -plmaxspd;
         } else {
            PlayerParam[3] = plmaxspd;
         }
      }

      var10000 = plspeed;
      var10000[0] += PlayerParam[3];
      var10000 = plspeed;
      var10000[1] += PlayerParam[5];
      if (PlayerParam[5] > 0 && this.nofcolTimer <= 0) {
         if (olddir == 270) {
            var10000 = PlayerParam;
            var10000[0] -= 3072;
         } else if (olddir == 90) {
            var10000 = PlayerParam;
            var10000[0] += 3072;
         }

         olddir = 0;
      }

      this.jumpmove();
      this.speedset(1);
      if (this.zoneNumber == 0 || this.zoneNumber == 3) {
         this.loopchange();
      }

      if (PlayerJump && nocoltimer <= 0) {
         if (this.rcol2()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[3] = 0;
         }

         if (this.lcol2()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[3] = 0;
         }
      }

      this.jumpcolchk();
   }

   public boolean ball00walk() {
      plspeed[0] = 0;
      plspeed[1] = 0;
      if (this.jumpchk(0)) {
         return true;
      } else {
         int var1 = Math.abs(PlayerParam[10]);
         this.keispd(1);
         this.blevermove();
         this.speedset(0);
         if (Math.abs(PlayerParam[10]) > 4096) {
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = -4096;
            } else {
               PlayerParam[10] = 4096;
            }
         }

         if (Math.abs(PlayerParam[10]) > 3072) {
         }

         if (this.zoneNumber == 0 || this.zoneNumber == 3) {
            this.loopchange();
         }

         if (!PlayerNoCol) {
            boolean var3 = false;
            int[] var10000;
            if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 31 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 32)) {
               var3 = true;
               var10000 = PlayerParam;
               var10000[10] += 12;
               if (PlayerParam[10] > 4096) {
                  PlayerParam[10] = 4096;
               }
            }

            if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 54)) {
               var3 = true;
            }

            if (this.zoneNumber == 3 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 42 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 43 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53)) {
               var3 = true;
            }

            if (!var3) {
               if (raidOn) {
                  if (this.blockColChk2(this.PlayerPosX() - 12, this.PlayerPosY() - 24) && this.blockColChk2(this.PlayerPosX() + 12, this.PlayerPosY() - 24)) {
                     if (this.rcol2()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[4]) {
                           this.playerPushSet();
                        }
                     }

                     if (this.lcol2()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[3]) {
                           this.playerPushSet();
                        }
                     }
                  } else {
                     if (this.rcol3() || this.rcol2()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[4]) {
                           this.playerPushSet();
                        }
                     }

                     if (this.lcol3() || this.lcol2()) {
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (KeyPress[3]) {
                           this.playerPushSet();
                        }
                     }
                  }
               } else {
                  if (this.rcol()) {
                     PlayerParam[10] = 0;
                     PlayerParam[13] = 0;
                     PlayerParam[14] = 0;
                     if (KeyPress[4]) {
                        this.playerPushSet();
                     }
                  }

                  if (this.lcol()) {
                     PlayerParam[10] = 0;
                     PlayerParam[13] = 0;
                     PlayerParam[14] = 0;
                     if (KeyPress[3]) {
                        this.playerPushSet();
                     }
                  }
               }
            }

            int var4 = PlayerParam[0];
            int var5 = PlayerParam[1];
            boolean var6 = true;
            if (this.setPlayerPos()) {
               this.crushing[0] = true;
               var6 = false;
            }

            if (this.noLeverTimer > 0) {
               if (var6) {
                  PlayerParam[0] = var4;
                  PlayerParam[1] = var5;
               }
            } else if (!raidOn && var6 && !var3) {
               PlayerParam[0] = var4;
               PlayerParam[1] = var5;
               if (this.zoneNumber == 0 && Math.abs(PlayerParam[10]) > 2560) {
                  if (PlayerParam[10] < 0) {
                     PlayerParam[10] = -4224;
                  } else {
                     PlayerParam[10] = 4224;
                  }
               }

               PlayerParam[3] = this.dSin(olddir + 90) * PlayerParam[10] / 100;
               PlayerParam[5] = this.dCos(olddir + 90) * PlayerParam[10] / 100;
               if ((olddir - 90) % 180 == 0) {
                  if (olddir == 90) {
                     var10000 = PlayerParam;
                     var10000[0] += 256;
                     if (this.zoneNumber == 4 && this.stageNumber != 0) {
                        PlayerParam[5] = 0;
                     }
                  } else {
                     var10000 = PlayerParam;
                     var10000[0] -= 256;
                     if (PlayerParam[10] > 3072 && this.zoneNumber == 4) {
                        this.noLeverTimer = 15;
                     }
                  }
               }

               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerAir = true;
               PlayerJump = true;
               PlayerDamage = false;
               raidOn = false;
               if (this.hcol()) {
                  PlayerParam[5] = 0;
               }
            } else {
               var3 = false;
               if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 31 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 32)) {
                  var3 = true;
               }

               if (this.zoneNumber == 2 && (PlayerParam[8] == 365 || PlayerParam[8] == 364 || PlayerParam[8] == 363 || PlayerParam[8] == 362)) {
                  this.playdamageset();
               }

               if (!var3) {
                  this.fallchk();
               }
            }
         }

         return false;
      }
   }

   public void ball00jump() {
      int var1 = Math.abs(PlayerParam[3]);
      int[] var10000;
      if (this.noLeverTimer > 0) {
         --this.noLeverTimer;
      } else if (!this.limitBreak) {
         if (KeyPress[3]) {
            PlayerParam[12] = 1;
            if (PlayerParam[3] == 0) {
               var10000 = PlayerParam;
               var10000[3] -= plretspd << 1;
            } else {
               var10000 = PlayerParam;
               var10000[3] -= pladdspd;
               if (PlayerParam[3] > 0) {
                  var10000 = PlayerParam;
                  var10000[3] -= pladdspd;
               }
            }
         } else if (KeyPress[4]) {
            PlayerParam[12] = 0;
            if (PlayerParam[3] == 0) {
               var10000 = PlayerParam;
               var10000[3] += plretspd << 1;
            } else {
               var10000 = PlayerParam;
               var10000[3] += pladdspd;
               if (PlayerParam[3] < 0) {
                  var10000 = PlayerParam;
                  var10000[3] += pladdspd;
               }
            }
         }
      }

      if (Math.abs(PlayerParam[3]) > 4096) {
         if (PlayerParam[3] < 0) {
            PlayerParam[3] = -4096;
         } else {
            PlayerParam[3] = 4096;
         }
      }

      if (this.zoneNumber == 3 && Math.abs(PlayerParam[3]) > plmaxspd && var1 <= plmaxspd) {
         if (PlayerParam[3] < 0) {
            PlayerParam[3] = -plmaxspd;
         } else {
            PlayerParam[3] = plmaxspd;
         }
      }

      var10000 = plspeed;
      var10000[0] += PlayerParam[3];
      var10000 = plspeed;
      var10000[1] += PlayerParam[5];
      if (PlayerParam[5] > 0 && this.nofcolTimer <= 0) {
         if (olddir == 270) {
            var10000 = PlayerParam;
            var10000[0] -= 3072;
         } else if (olddir == 90) {
            var10000 = PlayerParam;
            var10000[0] += 3072;
         }

         olddir = 0;
      }

      this.jumpmove();
      this.speedset(1);
      if (this.zoneNumber == 0 || this.zoneNumber == 3) {
         this.loopchange();
      }

      --nocoltimer;
      if (PlayerJump && nocoltimer <= 0) {
         if (this.rcol2()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[3] = 0;
         }

         if (this.lcol2()) {
            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
            PlayerParam[3] = 0;
         }
      }

      this.jumpcolchk();
   }

   public void jumpmove() {
   }

   public void playerTyakuchi(int var1) {
      if (var1 == 0) {
         PlayerBall = false;
         comboScore = 0;
         PlayerJump = false;
         PlayerSJump = false;
         PlayerDamage = false;
      }

      int[] var10000;
      int var2;
      if (var1 == 0) {
         PlayerParam[10] = PlayerParam[3];
         var2 = this.getPlayerArg(this.PlayerPosX(), this.PlayerPosY());
         if (var2 < 0) {
            for(int var3 = 1; var3 < this.PlayerW + 1; ++var3) {
               var2 = this.getPlayerArg(this.PlayerPosX() - var3, this.PlayerPosY());
               if (var2 >= 0) {
                  break;
               }

               var2 = this.getPlayerArg(this.PlayerPosX() + var3, this.PlayerPosY());
               if (var2 >= 0) {
                  break;
               }
            }
         }

         olddir = var2;
         if (var2 <= 67 && var2 >= 44 || var2 >= 293 && var2 <= 316) {
            if (PlayerParam[5] > 3072) {
               PlayerParam[5] = 4096;
            }

            if (this.dCos(var2 + 90) > 0) {
               PlayerParam[10] = PlayerParam[5];
            } else {
               PlayerParam[10] = -PlayerParam[5];
            }
         } else if (var2 < 338 && var2 > 22) {
            var10000 = PlayerParam;
            var10000[10] += this.dCos(var2 + 90) * PlayerParam[5] / 100;
         }

         PlayerParam[5] = 0;
         PlayerParam[3] = 0;
         if (this.zoneNumber == 2 && (PlayerParam[8] == 365 || PlayerParam[8] == 364 || PlayerParam[8] == 363 || PlayerParam[8] == 362)) {
            this.playdamageYogan = true;
         }

         if (PlayerParam[10] < 0) {
            PlayerParam[13] = 1;
         }

         if (PlayerParam[10] > 0) {
            PlayerParam[13] = 2;
         }

         PlayerParam[14] = 0;
      } else if (PlayerSJump) {
         var2 = this.getPlayerArg(this.PlayerPosX(), this.PlayerPosY() - 32);
         if (var2 < 0) {
            var2 = olddir;
         }

         if (var2 % 90 == 0) {
            PlayerParam[5] = 0;
         } else {
            var10000 = PlayerParam;
            var10000[3] += -(this.dCos(var2 + 90) * PlayerParam[5]) / 100;
         }
      } else {
         PlayerParam[5] = 0;
      }

   }

   public void jumpcolchk() {
      if (this.nofcolTimer > 0) {
         --this.nofcolTimer;
      } else {
         if (-pljump_w <= PlayerParam[5]) {
            KeyPress[2] = false;
         } else if (!KeyPress[2] && !PlayerSJump && !PlayerAir) {
            PlayerParam[5] = -pljump_w;
         }

         if (PlayerParam[5] > 0) {
            PlayerSJump = false;
            if (this.fcol()) {
               if (olddir < 270 && olddir > 90) {
                  olddir = 0;
               } else {
                  this.playerTyakuchi(0);
               }
            }
         } else if (this.hcol()) {
            this.playerTyakuchi(1);
         }

      }
   }

   public void levermove() {
      int[] var10000;
      if (this.limitBreak) {
         PlayerParam[12] = 0;
         if (PlayerParam[13] == 0) {
            PlayerParam[13] = 2;
            PlayerParam[14] = 2;
         }

         this.plwalk(1);
      } else if (!PlayerWater) {
         if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
            var10000 = PlayerParam;
            var10000[10] += 256;
            if (1024 < PlayerParam[10]) {
               PlayerParam[10] = 1024;
            }

            PlayerParam[12] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
         } else if (this.noLeverTimer > 0) {
            --this.noLeverTimer;
         } else if (KeyPress[3]) {
            PlayerParam[12] = 1;
            if (PlayerParam[13] == 0) {
               PlayerParam[13] = 1;
            }

            if (PlayerParam[14] == 0) {
               PlayerParam[14] = 1;
            }

            this.plwalk(0);
         } else if (KeyPress[4]) {
            PlayerParam[12] = 0;
            if (PlayerParam[13] == 0) {
               PlayerParam[13] = 2;
            }

            if (PlayerParam[14] == 0) {
               PlayerParam[14] = 2;
            }

            this.plwalk(1);
         }
      }

      var10000 = plspeed;
      var10000[0] += this.dSin(olddir + 90) * PlayerParam[10] / 100;
      var10000 = plspeed;
      var10000[1] += this.dCos(olddir + 90) * PlayerParam[10] / 100;
   }

   public void plwalk(int var1) {
      boolean var2 = false;
      int var3 = Math.abs(PlayerParam[10]);
      int[] var10000;
      if (var1 == 1) {
         if (PlayerParam[10] < 0 && PlayerParam[13] == 1) {
            var10000 = PlayerParam;
            var10000[10] += plretspd;
            if (PlayerParam[10] > 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
            }
         }

         if (PlayerParam[10] == 0) {
            var10000 = PlayerParam;
            var10000[10] += pladdspd;
         }

         var10000 = PlayerParam;
         var10000[10] += pladdspd;
         if (PlayerParam[10] > 0 && PlayerParam[13] == 1) {
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
         }
      } else {
         if (PlayerParam[10] > 0 && PlayerParam[13] == 2) {
            var10000 = PlayerParam;
            var10000[10] -= plretspd;
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
            }
         }

         if (PlayerParam[10] == 0) {
            var10000 = PlayerParam;
            var10000[10] -= pladdspd;
         }

         var10000 = PlayerParam;
         var10000[10] -= pladdspd;
         if (PlayerParam[10] < 0 && PlayerParam[13] == 2) {
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
         }
      }

   }

   public void blevermove() {
      int[] var10000;
      if (this.limitBreak) {
         PlayerParam[12] = 0;
         if (PlayerParam[13] == 0) {
            PlayerParam[13] = 2;
            PlayerParam[14] = 2;
         }

         this.plwalk(1);
      } else if (this.zoneNumber == 5 && this.stageNumber != 2 && (37 == PlayerParam[8] || 38 == PlayerParam[8] || 39 == PlayerParam[8] || 41 == PlayerParam[8] || 42 == PlayerParam[8] || 47 == PlayerParam[8] || 48 == PlayerParam[8] || 49 == PlayerParam[8] || 52 == PlayerParam[8] || 59 == PlayerParam[8] || 62 == PlayerParam[8] || 69 == PlayerParam[8] || 71 == PlayerParam[8] || 72 == PlayerParam[8] || 78 == PlayerParam[8] || 79 == PlayerParam[8] || 87 == PlayerParam[8] || 88 == PlayerParam[8] || 89 == PlayerParam[8])) {
         var10000 = PlayerParam;
         var10000[10] += 256;
         if (2560 < PlayerParam[10]) {
            PlayerParam[10] = 2560;
         }
      } else if (this.noLeverTimer > 0) {
         --this.noLeverTimer;
      } else if (KeyPress[3]) {
         if (PlayerParam[13] == 0) {
            PlayerParam[13] = 1;
         }

         if (PlayerParam[14] == 0) {
            PlayerParam[14] = 1;
         }

         PlayerParam[12] = 1;
         this.plballwalk(0);
      } else if (KeyPress[4]) {
         if (PlayerParam[13] == 0) {
            PlayerParam[13] = 2;
         }

         if (PlayerParam[14] == 0) {
            PlayerParam[14] = 2;
         }

         PlayerParam[12] = 0;
         this.plballwalk(1);
      }

      var10000 = plspeed;
      var10000[0] += this.dSin(olddir + 90) * PlayerParam[10] / 100;
      var10000 = plspeed;
      var10000[1] += this.dCos(olddir + 90) * PlayerParam[10] / 100;
      if (PlayerParam[10] != 0 && PlayerParam[10] != 0) {
         if (PlayerParam[10] < 0) {
            var10000 = PlayerParam;
            var10000[10] += pladdspd;
            if (PlayerParam[10] >= 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerBall = false;
            }
         } else {
            var10000 = PlayerParam;
            var10000[10] -= pladdspd;
            if (PlayerParam[10] <= 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerBall = false;
            }
         }
      }

      if (PlayerParam[10] == 0) {
         PlayerBall = false;
         comboScore = 0;
      }

   }

   public void plballwalk(int var1) {
      boolean var2 = false;
      int[] var10000;
      if (var1 == 1) {
         var10000 = PlayerParam;
         var10000[10] += pladdspd >> 1;
         if (Math.abs(PlayerParam[10]) > 4096) {
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = -4096;
            } else {
               PlayerParam[10] = 4096;
            }
         }
      } else {
         var10000 = PlayerParam;
         var10000[10] -= pladdspd >> 1;
         if (Math.abs(PlayerParam[10]) > 4096) {
            if (PlayerParam[10] < 0) {
               PlayerParam[10] = -4096;
            } else {
               PlayerParam[10] = 4096;
            }
         }
      }

   }

   public void keispd(int var1) {
      if (!raidOn) {
         int[] var10000;
         int var2;
         if (0 == var1) {
            var2 = olddir;
            if (var2 < 0) {
               var2 = 0;
               if (olddir != 0 && !raidOn && (olddir <= 22 || olddir >= 338)) {
                  if (PlayerParam[10] > 0) {
                     var2 = 90;
                  } else if (PlayerParam[10] < 0) {
                     var2 = 270;
                  } else {
                     var2 = olddir;
                  }
               }
            }

            if (olddir > 22 && olddir < 338) {
               var10000 = PlayerParam;
               var10000[10] += this.dCos(var2 + 90) * 32 / 100;
            }
         } else {
            var2 = PlayerParam[10];
            int var3 = olddir;
            if (var3 < 0) {
               var3 = 0;
               if (olddir != 0 && !raidOn && (olddir <= 22 || olddir >= 338)) {
                  if (PlayerParam[10] > 0) {
                     var3 = 90;
                  } else if (PlayerParam[10] < 0) {
                     var3 = 270;
                  } else {
                     var3 = olddir;
                  }
               }
            }

            int var4 = (var3 + 135) % 360;
            if (var3 < 338 && var3 > 22) {
               if (this.dCos(var3 + 90) * 80 / 100 < 0 && PlayerParam[10] < 0) {
                  var10000 = PlayerParam;
                  var10000[10] += this.dCos(var3 + 90) * 80 / 100;
               }

               if (this.dCos(var3 + 90) * 80 / 100 > 0 && PlayerParam[10] > 0) {
                  var10000 = PlayerParam;
                  var10000[10] += this.dCos(var3 + 90) * 80 / 100;
               }
            } else if (olddir != 338 && olddir != 22) {
               var10000 = PlayerParam;
               var10000[10] += this.dCos(var3 + 90) * 80 / 100;
            } else {
               if ((this.dCos(var3 + 90) << 5) / 100 < 0 && PlayerParam[10] < 0) {
                  var10000 = PlayerParam;
                  var10000[10] += this.dCos(var3 + 90) * 80 / 100;
               }

               if ((this.dCos(var3 + 90) << 5) / 100 > 0 && PlayerParam[10] > 0) {
                  var10000 = PlayerParam;
                  var10000[10] += this.dCos(var3 + 90) * 80 / 100;
               }
            }

            if (PlayerParam[10] >= 0 && var2 <= 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerBall = false;
            }

            if (PlayerParam[10] <= 0 && var2 >= 0) {
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               PlayerBall = false;
            }
         }

      }
   }

   public void limitchk(boolean var1) {
      try {
         int var3 = 112;
         if (this.LookUpCount > 0) {
            var3 += this.LookUpCount << 1;
         }

         if (this.CrouchCount > 0) {
            var3 -= this.CrouchCount << 1;
         }

         int[] var10000;
         if (this.zoneNumber == 0 && this.stageNumber == 3) {
            this.poslimit[0] = 0;
            this.poslimit[2] = 0;
            this.poslimit[1] = 3840;
            this.poslimit[3] = 256;
            if (bossBreakOn) {
               var10000 = this.poslimit;
               var10000[1] += 768;
            }
         } else if (this.zoneNumber == 5 && this.stageNumber == 3) {
            this.poslimit[0] = 0;
            this.poslimit[2] = 0;
            this.poslimit[1] = 1440;
            this.poslimit[3] = 64 + var3;
            if (bossBreakOn) {
               var10000 = this.poslimit;
               var10000[1] += 768;
            }

            if (mapOxy[0] >= 1200) {
               if (bossModeOn) {
                  this.poslimit[0] = 1200;
                  this.poslimit[1] = 1440;
                  this.poslimit[2] = 32;
                  this.poslimit[3] = 32 + var3;
               }

               if (bossBreakOn) {
                  this.poslimit[0] = 1200;
               }

               if (mapOxy[0] >= 1808) {
                  this.poslimit[0] = 1808;
                  this.poslimit[1] = 2048;
                  this.poslimit[2] = 32;
                  this.poslimit[3] = 64 + var3;
               }
            }
         } else {
            this.poslimit[0] = 0;
            this.poslimit[2] = 0;
            this.poslimit[1] = this.limitTable[this.zoneNumber][this.stageNumber][2] + 320;
            this.poslimit[3] = this.hlimitget() + (240 - (184 - var3));
            if (this.m_bScrollLock == 2) {
               this.limitBreak = true;
            }

            if (this.stageNumber == 2 && this.zoneNumber == 1) {
               if (this.PlayerPosX() >= this.m_aaScrollLockPos[this.zoneNumber][this.stageNumber] || this.m_bScrollLock == 1) {
                  this.poslimit[0] = this.m_aaScrollLockPos[this.zoneNumber][this.stageNumber] - 144;
                  this.m_bScrollLock = 1;
               }
            } else if (this.stageNumber < 2) {
               if (this.PlayerPosX() >= this.m_aaScrollLockPos[this.zoneNumber][this.stageNumber] || this.m_bScrollLock == 1) {
                  this.poslimit[0] = this.m_aaScrollLockPos[this.zoneNumber][this.stageNumber] - 144;
                  this.m_bScrollLock = 1;
               }
            } else if (this.m_bScrollLock == 2 && this.zoneNumber != 1) {
               this.poslimit[0] = (short)(this.limitTable[this.zoneNumber][this.stageNumber][2] + 320);
            }

            if (this.zoneNumber == 1 && this.stageNumber == 2) {
               var10000 = this.poslimit;
               var10000[3] += 56;
            }

            if (this.zoneNumber == 5 && this.stageNumber == 1) {
               var10000 = this.poslimit;
               var10000[3] -= 168;
            }

            if (this.stageNumber == 2 && this.zoneNumber != 1 && bossBreakOn) {
               var10000 = this.poslimit;
               var10000[1] += 256;
            }

            if (bossModeOn) {
               if (this.zoneNumber == 0) {
                  this.poslimit[0] = 10632;
                  this.poslimit[1] = 10872;
                  this.poslimit[2] = 0;
                  this.poslimit[3] = 800 + var3;
               } else if (this.zoneNumber == 2) {
                  this.poslimit[0] = 6168;
                  this.poslimit[1] = 6440;
                  this.poslimit[2] = 560;
                  this.poslimit[3] = 568 + var3;
               } else if (this.zoneNumber == 4) {
                  this.poslimit[0] = 11304;
                  this.poslimit[1] = 11544;
                  this.poslimit[2] = 1232;
                  this.poslimit[3] = 1248 + var3;
               } else if (this.zoneNumber == 3) {
                  this.poslimit[0] = 8280;
                  this.poslimit[1] = 8520;
                  this.poslimit[2] = 528;
                  this.poslimit[3] = 576 + var3;
               }
            }

            if (bossBreakOn) {
               if (this.zoneNumber == 0) {
                  this.poslimit[0] = 10632;
               } else if (this.zoneNumber == 2) {
                  this.poslimit[0] = 6168;
               } else if (this.zoneNumber == 4) {
                  this.poslimit[0] = 11304;
               } else if (this.zoneNumber == 3) {
                  this.poslimit[0] = 8280;
               }
            }
         }

         if (PlayerDie) {
            this.checkDieCount();
            return;
         }

         for(int var2 = 0; var2 < 2; ++var2) {
            boolean var4 = false;
            int var8;
            if (var2 == 0) {
               var8 = 120;
            } else {
               var8 = var3;
            }

            boolean var5 = false;
            int var9;
            if (var2 == 0) {
               var9 = this.PlayerPosX();
            } else {
               var9 = this.PlayerPosY();
            }

            if (var2 == 0) {
               if (!bossModeOn && MapEndCounter == 0 && this.zoneNumber == 5 && this.stageNumber == 3) {
                  this.startBossMode();
               } else if (!bossModeOn && MapEndCounter == 0 && this.zoneNumber == 1 && this.stageNumber == 2 && this.PlayerPosX() >= 7488 && this.PlayerPosY() >= 1536) {
                  this.startBossMode();
               }

               if (bossModeOn && this.zoneNumber != 1) {
               }

               if (mapOxy[var2] >= this.poslimit[1] - 240 && !bossModeOn && MapEndCounter == 0 && this.stageNumber == 2) {
                  this.startBossMode();
               }
            }

            if (var2 == 0) {
               if (var9 - mapOxy[var2] != var8) {
                  if (var9 - mapOxy[var2] < var8) {
                     if (this.gole_on) {
                        if (mapOxy[var2] + 16 << 8 > PlayerParam[0]) {
                           PlayerParam[0] = mapOxy[var2] + 16 << 8;
                           if (PlayerParam[10] < 0) {
                              PlayerParam[10] = 0;
                              PlayerParam[13] = 0;
                              PlayerParam[14] = 0;
                           }

                           if (!PlayerJump && PlayerBall) {
                              PlayerBall = false;
                           }
                        }
                     } else {
                        this.ChkVecL = true;
                        var10000 = mapOxy;
                        var10000[var2] -= var8 - (var9 - mapOxy[var2]);
                        if (mapOxy[var2] < this.poslimit[var2 << 1]) {
                           mapOxy[var2] = this.poslimit[var2 << 1];
                        }

                        if (mapOxy[var2] + 16 << 8 > PlayerParam[0]) {
                           PlayerParam[0] = mapOxy[var2] + 16 << 8;
                           if (PlayerParam[10] < 0) {
                              PlayerParam[10] = 0;
                              PlayerParam[13] = 0;
                              PlayerParam[14] = 0;
                           }

                           if (!PlayerJump && PlayerBall) {
                              PlayerBall = false;
                           }

                           if (PlayerJump && PlayerParam[3] < 0) {
                              PlayerParam[3] = 0;
                           }
                        }
                     }
                  } else if (var9 - mapOxy[var2] > var8) {
                     this.ChkVecR = true;
                     var10000 = mapOxy;
                     var10000[var2] -= var8 - (var9 - mapOxy[var2]);
                     if (mapOxy[var2] + var8 * 2 > this.poslimit[(var2 << 1) + 1]) {
                        mapOxy[var2] = this.poslimit[(var2 << 1) + 1] - (var8 << 1);
                     }

                     if (this.limitBreak) {
                        if (mapOxy[var2] + 96 + 240 << 8 < PlayerParam[0]) {
                           PlayerParam[0] = mapOxy[var2] + 96 + 240 << 8;
                           PlayerParam[10] = 0;
                           PlayerParam[13] = 0;
                           PlayerParam[14] = 0;
                           if (!PlayerJump && PlayerBall) {
                              PlayerBall = false;
                           }
                        }
                     } else if (mapOxy[var2] - 16 + 240 << 8 < PlayerParam[0]) {
                        PlayerParam[0] = mapOxy[var2] - 16 + 240 << 8;
                        PlayerParam[10] = 0;
                        PlayerParam[13] = 0;
                        PlayerParam[14] = 0;
                        if (!PlayerJump && PlayerBall) {
                           PlayerBall = false;
                        }
                     }
                  }
               }
            } else {
               if (bossModeOn && (this.zoneNumber != 1 || this.stageNumber != 2) && mapOxy[var2] + var3 > this.poslimit[var2 * 2 + 1]) {
                  mapOxy[var2] = this.poslimit[var2 * 2 + 1] - var3;
               }

               if (var9 - mapOxy[var2] != var8) {
                  byte var6 = 0;
                  if (var9 - mapOxy[var2] < var8 - var6) {
                     var10000 = mapOxy;
                     var10000[var2] -= var8 - var6 - (var9 - mapOxy[var2]);
                     if ((this.zoneNumber == 1 && this.stageNumber == 2 && mapOxy[0] < 7936 || this.zoneNumber == 5 && this.stageNumber == 1) && this.LookUpCount <= 0 && this.CrouchCount <= 0) {
                        if (mapOxy[var2] < this.poslimit[var2 * 2]) {
                           PlayerParam[1] = this.poslimit[var2 * 2 + 1] + mapOxy[var2] + var8 << 8;
                           mapOxy[var2] += this.poslimit[var2 * 2 + 1];
                        }
                     } else if (mapOxy[var2] < this.poslimit[var2 * 2]) {
                        mapOxy[var2] = this.poslimit[var2 * 2];
                     }

                     if (this.zoneNumber == 1 && this.stageNumber == 0 && PlayerParam[1] < 8192) {
                        PlayerParam[1] = 8192;
                        if (PlayerParam[5] < 0) {
                           PlayerParam[5] = 0;
                        }
                     }

                     if (this.zoneNumber == 4 && PlayerParam[1] < 8192) {
                        PlayerParam[1] = 8192;
                        if (PlayerParam[5] < 0) {
                           PlayerParam[5] = 0;
                        }
                     }
                  } else if (var9 - mapOxy[var2] > var8) {
                     var10000 = mapOxy;
                     var10000[var2] -= var8 - (var9 - mapOxy[var2]);
                     if ((this.zoneNumber == 1 && this.stageNumber == 2 && mapOxy[0] < 7936 || this.zoneNumber == 5 && this.stageNumber == 1) && this.LookUpCount <= 0 && this.CrouchCount <= 0) {
                        if (mapOxy[var2] > this.poslimit[var2 * 2 + 1]) {
                           mapOxy[var2] %= this.poslimit[var2 * 2 + 1];
                           PlayerParam[1] %= this.poslimit[var2 * 2 + 1] << 8;
                        }
                     } else if (this.zoneNumber == 1 && this.stageNumber == 2 && mapOxy[0] < 7936 || this.zoneNumber == 5 && this.stageNumber == 1) {
                        if (this.CrouchCount <= 0 && mapOxy[var2] > this.poslimit[var2 * 2 + 1]) {
                           mapOxy[var2] = this.poslimit[var2 * 2 + 1];
                        }
                     } else if (mapOxy[var2] + var3 > this.poslimit[var2 * 2 + 1]) {
                        mapOxy[var2] = this.poslimit[var2 * 2 + 1] - var3;
                     }

                     if ((this.zoneNumber != 1 || this.stageNumber != 2) && (this.zoneNumber != 5 || this.stageNumber != 1)) {
                        if (this.zoneNumber == 3 && mapOxy[var2] + 168 << 8 < PlayerParam[var2]) {
                           if (!debugFlag && !PlayerDie) {
                              PlayerParam[var2] = mapOxy[var2] - 16 + 240 << 8;
                              this.playerDie();
                           }
                        } else if (mapOxy[var2] - 16 + 240 << 8 < PlayerParam[var2] && !debugFlag && !PlayerDie) {
                           PlayerParam[var2] = mapOxy[var2] - 16 + 240 << 8;
                           this.playerDie();
                        }
                     }
                  }
               }
            }

            if (mapOxy[var2] < 0) {
               mapOxy[var2] = 0;
            }
         }
      } catch (Throwable var7) {
      }

   }

   public int hlimitget() {
      int var1 = MapH * 256 - 232;
      switch(this.zoneNumber) {
      case 0:
         var1 = this.zone1((byte)this.stageNumber);
         break;
      case 1:
         var1 = 1824;
         break;
      case 2:
         var1 = this.zone3((byte)this.stageNumber);
         break;
      case 3:
         var1 = this.zone4((byte)this.stageNumber);
         break;
      case 4:
         var1 = this.zone5((byte)this.stageNumber);
         break;
      case 5:
         var1 = this.zone6((byte)this.stageNumber);
      }

      return var1;
   }

   public int zone1(byte var1) {
      short var2;
      switch(var1) {
      case 0:
         var2 = 1024;
         if (6016 > mapOxy[0]) {
            var2 = 768;
         }
         break;
      case 1:
         var2 = 768;
         if (3792 > mapOxy[0]) {
            var2 = 768;
         } else if (5632 > mapOxy[0]) {
            var2 = 512;
         } else if (7520 > mapOxy[0]) {
            var2 = 1024;
         }
         break;
      default:
         var2 = 1216;
         if (896 > mapOxy[0]) {
            var2 = 768;
         } else if (2400 > mapOxy[0]) {
            var2 = 784;
         } else if (640 > mapOxy[1]) {
            var2 = 768;
         } else if (4992 <= mapOxy[0]) {
            var2 = 1024;
            if (5888 <= mapOxy[0]) {
               if (mapOxy[1] > 880) {
                  if (6144 <= mapOxy[0]) {
                     this.poslimit[1] = 6384;
                     MapEndCounter = 1;
                  }
               } else {
                  var2 = 768;
               }
            }
         }
      }

      return var2;
   }

   public int zone3(byte var1) {
      short var2;
      switch(var1) {
      case 0:
         if ((plsaveX != 0 || plsaveY != 0) && mapOxy[0] == 0 && mapOxy[1] == 0) {
            var2 = 1280;
         } else {
            boolean var3 = true;
            if (this.PlayerPosX() > 3584 && this.PlayerPosX() < 4096 && this.PlayerPosY() > 1024 && this.PlayerPosY() < 1248) {
               var2 = 1280;
            } else if (3696 <= mapOxy[0]) {
               var2 = 528;
               if (5168 > mapOxy[0]) {
                  var2 = 1280;
               }
            } else if (1792 > mapOxy[0]) {
               var2 = 464;
            } else if (2400 <= mapOxy[0] && 2752 > mapOxy[0]) {
               var2 = 1280;
               if (728 > mapOxy[1]) {
                  var2 = 544;
               }
            } else {
               var2 = 1280;
               if (728 > mapOxy[1]) {
                  var2 = 832;
                  if (3328 > mapOxy[0]) {
                     var2 = 544;
                  }
               } else if (1176 > mapOxy[1] && 3792 > mapOxy[0]) {
                  var2 = 832;
               }
            }
         }
         break;
      case 1:
         var2 = 512;
         if (5888 > mapOxy[0]) {
            var2 = 1312;
         }
         break;
      default:
         var2 = 1824;
         if (5472 < mapOxy[0] && this.PlayerPosY() < 1280) {
            var2 = 528;
         }
      }

      return var2;
   }

   public int zone4(byte var1) {
      boolean var2 = true;
      int var4;
      switch(var1) {
      case 0:
         var4 = 1824;
         break;
      case 1:
         byte var3 = 112;
         var4 = (MapH << 8) - 72 - (240 - (184 - var3));
         break;
      default:
         var4 = 528;
         if (7936 > mapOxy[0]) {
            var4 = 1792;
         }
      }

      return var4;
   }

   public int zone5(byte var1) {
      short var2 = 1824;
      switch(var1) {
      case 0:
         break;
      case 1:
         var2 = 1312;
         if (9632 <= mapOxy[0] && 1248 > this.PlayerPosY()) {
            var2 = 1056;
         }
         break;
      default:
         var2 = 1228;
         if (11264 > mapOxy[0]) {
            var2 = 1824;
         }
      }

      return var2;
   }

   public int zone6(byte var1) {
      short var2;
      switch(var1) {
      case 0:
         var2 = 672;
         if (6272 > mapOxy[0]) {
            var2 = 1824;
         } else if (8192 > mapOxy[0]) {
            var2 = 1568;
         }
         break;
      case 1:
         var2 = 8032;
         if (6144 > mapOxy[0]) {
            var2 = 2048;
         } else if (7680 > mapOxy[0]) {
            var2 = 1464;
         }
         break;
      default:
         var2 = 1824;
      }

      return var2;
   }

   public void speedset(int var1) {
      this.playerBressChk();
      int[] var10000;
      if (this.zoneNumber == 1 && this.waterH2 < this.PlayerPosY() - 12) {
         var10000 = PlayerParam;
         var10000[0] += plspeed[0] >> 1;
         var10000 = PlayerParam;
         var10000[1] += plspeed[1] >> 1;
      } else {
         if (plspeed[0] > 4096) {
            plspeed[0] = 4096;
         }

         if (plspeed[0] < -4096) {
            plspeed[0] = -4096;
         }

         if (plspeed[1] > 4096) {
            plspeed[1] = 4096;
         }

         if (plspeed[1] < -4096) {
            plspeed[1] = -4096;
         }

         var10000 = PlayerParam;
         var10000[0] += plspeed[0];
         var10000 = PlayerParam;
         var10000[1] += plspeed[1];
      }

      if (var1 == 1) {
         if (falltimer <= 0) {
            if (this.zoneNumber == 1 && this.waterH2 < this.PlayerPosY() - 12) {
               var10000 = PlayerParam;
               var10000[5] += gravity / 2;
            } else {
               var10000 = PlayerParam;
               var10000[5] += gravity;
            }
         } else {
            --falltimer;
         }
      }

      if (var1 == 1 && PlayerBall) {
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
      } else if (Math.abs(plspeed[0]) + Math.abs(plspeed[1]) > plmaxspd) {
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
      } else {
         var10000 = PlayerParam;
         var10000[11] += Math.abs(plspeed[0]) + Math.abs(plspeed[1]);
      }

   }

   public boolean fallchk() {
      if (olddir <= 290 && olddir >= 70) {
         int var1;
         if (olddir >= 90 && olddir <= 270) {
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

               var1 = PlayerParam[10];
               PlayerParam[5] = this.dCos(olddir + 90) * var1 / 100;
               if (olddir > 90 && olddir < 270) {
                  PlayerParam[5] = 0;
               }

               PlayerParam[3] = this.dSin(olddir + 90) * var1 / 100;
               PlayerJump = true;
               PlayerDamage = false;
               raidOn = false;
               this.nofcolTimer = 15;
               if (olddir == 90 || olddir == 270) {
                  this.nofcolTimer = 0;
               }

               this.hcol();
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

            var1 = Math.abs(PlayerParam[10]);
            PlayerParam[5] = 280;
            PlayerParam[3] = -(this.dSin(olddir + 90) * var1) / 100;
            PlayerJump = true;
            PlayerDamage = false;
            raidOn = false;
            this.noLeverTimer = 30;
            this.hcol();
         }
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
      if (!debugFlag && !this.damageNow && mutekicount <= 0) {
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
               this.playerDie();
            } else {
               this.Vibrate(1000);
               muteki2count = 60;
               PlayerDamage = true;
               PlayerJump = true;
               if (this.kyuryuchk()) {
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
               this.ShotRing(this.PlayerPosX(), this.PlayerPosY() - 12, ringcount);
               ringcount = 0;
               this.damageNow = true;
            }
         }
      }

   }

   public void playdamageset2() {
      if (!debugFlag && !this.damageNow && mutekicount <= 0) {
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
               this.playerDie();
            } else {
               this.Vibrate(1000);
               muteki2count = 60;
               PlayerDamage = true;
               PlayerJump = true;
               if (this.kyuryuchk()) {
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
               this.ShotRing(this.PlayerPosX(), this.PlayerPosY() - 12, ringcount);
               ringcount = 0;
               this.damageNow = true;
            }
         }
      }

   }

   public void playerDie() {
      byte var1 = 0;
      if (PlayerDie) {
         this.SetSoftFlag = true;
         this.SetSoftCount = 10;
      } else {
         this.Vibrate(1000);
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
         PlayerParam[5] = this.dCos(var1) * pljump / 100;
         diecount = 120;
      }
   }

   public void checkDieCount() {
      if (PlayerDie) {
         PlayerParam[3] = 0;
         --diecount;
         if (mapOxy[1] - 16 + 240 << 8 < PlayerParam[1] && this.PlayerSub) {
            this.PlayerSub = false;
            --playercount;
            if (playercount > 0) {
               if (timecount == 59 && timecount2 == 9) {
                  diecount = 240;
                  this.timeUpDie = true;
                  this.SetSoftFlag = true;
                  this.SetSoftCount = 10;
                  plsaveTime = 0;
                  plsaveTime2 = 0;
                  this.noTimeScore = true;
               }
            } else {
               this.PlayMusic(21);
               diecount = 660;
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;

               for(int var1 = 0; var1 < m_nHiScore.length; ++var1) {
                  if (m_nHiScore[var1] < scorecount) {
                     for(int var2 = m_nHiScore.length - 1; var2 > var1; --var2) {
                        m_nHiScore[var2] = m_nHiScore[var2 - 1];
                        m_nDifficulty[var2] = m_nDifficulty[var2 - 1];
                     }

                     m_nHiScore[var1] = scorecount;
                     m_nDifficulty[var1] = m_nConfigValue[0];
                     this.save_hisc();
                     break;
                  }
               }

               scorecount = 0;
            }
         }

         if (diecount < 0) {
            if (playercount <= 0) {
               this.startContinue();
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;
            } else {
               this.initStageStart();
            }
         }
      }

   }

   public void playerRaidOn(int var1) {
      this.crushing[0] = true;
      if (!this.damageNow && PlayerParam[5] >= 0) {
         if (PlayerJump && PlayerBall && !PlayerAir) {
            PlayerBall = false;
         }

         int var2 = raidObjectW - 8;
         if (var2 < 0) {
            boolean var3 = false;
         } else if (Math.abs(this.PlayerPosX() - raidObjectX) > var2) {
            OttotoOn = true;
            OttotoSide = 0;
            if (this.PlayerPosX() - raidObjectX > 0) {
               OttotoSide = 1;
            }
         }

         this.playdamageYogan = false;
         PlayerParam[8] = 0;
         PlayerParam[5] = 0;
         if (PlayerJump) {
            PlayerParam[10] = PlayerParam[3];
            if (PlayerParam[10] < 0) {
               PlayerParam[13] = 1;
            }

            if (PlayerParam[10] > 0) {
               PlayerParam[13] = 2;
            }

            PlayerParam[14] = 0;
         }

         PlayerParam[3] = 0;
         raidOn = true;
         raidObjectNum = var1;
         olddir = 0;
         PlayerJump = false;
         PlayerSJump = false;
         PlayerWater = false;
         comboScore = 0;
      }
   }

   public byte getPlayerArg2(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 < 0) {
         var2 = 0;
      }

      int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
      if (this.hitChk[var3 >> 1] == 1) {
         return -1;
      } else {
         int var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
         return (byte)this.blockdirChk(var4);
      }
   }

   public int getPlayerArg(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 < 0) {
         var2 = 0;
      }

      int var3 = (tempWorldMapData[(var2 >> 4 >> 4) % MapH][var1 >> 4 >> 4] << 9) + ((var1 >> 4 & 15) + ((var2 >> 4 & 15) << 4) << 1) + 1;
      if (this.hitChk[var3 >> 1] == 1) {
         return -1;
      } else {
         int var4 = (mapData[var3] & 255) + (this.imageOffset[var3 >> 1] << 8);
         int var5 = this.blockdirChk(var4) * 360 / 255;
         if (this.rot[var3 >> 1] == 1) {
            var5 = 360 - var5;
         } else if (this.rot[var3 >> 1] == 2) {
            var5 = 540 - var5;
         } else if (this.rot[var3 >> 1] == 3) {
            var5 = 540 - var5;
            var5 %= 360;
            var5 = 360 - var5;
         }

         if (var5 % 90 == 0) {
            if (olddir == 62) {
               byte var6 = 90;
               return var6;
            }

            var5 = Math.abs((olddir - 22 + 45) / 90) * 90;
            if (olddir == 44) {
               var5 = 0;
            }
         }

         return var5 % 360;
      }
   }

   public void drawPlayerImage(Graphics var1) {
      if (!this.playerDraw) {
         int var2 = rotNumTable[TRANS_NONE];
         int var4 = (540 - olddir) % 360;
         if (PlayerParam[12] == 1) {
            var2 = rotNumTable[TRANS_MIRROR];
         }

         PlayerParam[11] %= 92160;
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;
         int var8 = 0;
         Image playergfx;
         int var10007;
         int xpos;
         int ypos;
         Graphics var10010;
         Graphics var10011;
         if (PlayerSJump) {
            playergfx = this.m_imgCmd[SONIC_N];
            xpos = this.PlayerPosX() - mapView[0];
            ypos = this.PlayerPosY() - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var1, playergfx, 240, 80, 40, 45, var2, xpos, ypos, 1 | 32);
         } else if (PlayerDamage && PlayerJump) {
            if (PlayerParam[12] == 1) {
               var2 = rotNumTable[TRANS_MIRROR - 4];
            } else {
               var2 = rotNumTable[TRANS_MIRROR];
            }

            playergfx = this.m_imgCmd[SONIC_N];
            xpos = this.PlayerPosX() - mapView[0];
            ypos = this.PlayerPosY() - mapView[1] - 5;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var1, playergfx, 160, 80, 40, 40, var2, xpos, ypos, 1 | 32);
         } else if (kokyutimer > 0) {
            playergfx = this.m_imgCmd[SONIC_N];
            xpos = this.PlayerPosX() - mapView[0];
            ypos = this.PlayerPosY() - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var1, playergfx, 352, 0, 40, 40, var2, xpos, ypos, 1 | 32);
            --kokyutimer;
         } else {
            int var10003;
            if (PlayerBou) {
               playergfx = this.m_imgCmd[SONIC_N];
               var10003 = 144 + 48 * ((this.animeTimer >> 1) % 2);
               var10007 = rotNumTable[TRANS_NONE + 4];
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, var10003, 128, 49, 32, var10007, xpos, ypos, 1 | 32);
               PlayerBou = false;
            } else if (PlayerSWater) {
               if ((this.animeTimer >> 1) % 5 < 3) {
                  playergfx = this.m_imgCmd[SONIC_N];
                  var10003 = 48 * ((this.animeTimer >> 1) % 5);
                  var10007 = rotNumTable[TRANS_NONE];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, var10003, 120, 45, 24, var10007, xpos, ypos, 1 | 32);
               } else {
                  playergfx = this.m_imgCmd[SONIC_N];
                  var10003 = 240 - 48 * ((this.animeTimer >> 1) % 5);
                  var10007 = rotNumTable[TRANS_NONE + 4];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, var10003, 120, 45, 24, var10007, xpos, ypos, 1 | 32);
               }

               PlayerSWater = false;
            } else if (PlayerBall && PlayerJump) {
               var5 = 0;
               if (PlayerJump && olddir == 270) {
                  var5 -= 16;
               }

               var7 = -24 + this.dSin(var4) * 24 / 100;
               var8 = -24 - this.dCos(var4) * 24 / 100;
               playergfx = this.m_imgCmd[SONIC_N];
               var10003 = 192 + PlayerParam[11] / plmaxspd / 4 % 5 * 32;
               xpos = this.PlayerPosX() - mapView[0] + var5;
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, var10003, 0, 32, 40, var2, xpos, ypos, 1 | 32);
            } else if (PlayerWater) {
               playergfx = this.m_imgCmd[SONIC_N];
               var10003 = 120 + 40 * (this.animeTimer / 2 % 2);
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1] - 5;
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, var10003, 80, 40, 40, var2, xpos, ypos, 1 | 32);
            } else if (PlayerDie) {
               if (this.bressDie) {
                  playergfx = this.m_imgCmd[SONIC_N];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, 280, 80, 40, 44, var2, xpos, ypos, 1 | 32);
               } else {
                  playergfx = this.m_imgCmd[SONIC_N];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, 200, 80, 40, 44, var2, xpos, ypos, 1 | 32);
               }
            } else if (PlayerBall && !PlayerJump) {
               var5 = -16 + this.dSin(var4) * 16 / 100;
               var6 = -15 - this.dCos(var4) * 15 / 100;
               var7 = -24 + this.dSin(var4) * 24 / 100;
               var8 = -24 - this.dCos(var4) * 24 / 100;
               this.drawRegion(var1, this.m_imgCmd[SONIC_N], 192 + PlayerParam[11] / plmaxspd / 4 % 5 * 32, 10, 32, 30, var2, this.PlayerPosX() - mapView[0] + var5, this.PlayerPosY() - mapView[1] + var6, 20);
            } else if (PlayerCrouch) {
               playergfx = this.m_imgCmd[SONIC_N];
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, 0, 80, 40, 40, var2, xpos, ypos, 1 | 32);
               PlayerCrouch = false;
            } else if (PlayerLookUp) {
               playergfx = this.m_imgCmd[SONIC_N];
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, 160, 0, 32, 40, var2, xpos, ypos, 1 | 32);
               PlayerLookUp = false;
            } else if (this.pushCount > 0) {
               playergfx = this.m_imgCmd[SONIC_N];
               var10003 = 288 + this.animeTimer / 4 % 4 * 32;
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, var10003, 120, 32, 40, var2, xpos, ypos, 1 | 32);
            } else if (PlayerParam[10] == 0 && !PlayerJump) {
               if (OttotoOn) {
                  if (OttotoSide == 1) {
                     playergfx = this.m_imgCmd[SONIC_N];
                     var10003 = 40 + this.playerStandCount / 8 % 2 * 40;
                     var10007 = rotNumTable[TRANS_NONE];
                     xpos = this.PlayerPosX() - mapView[0];
                     ypos = this.PlayerPosY() - mapView[1];
                     var10010 = gg;
                     var10011 = gg;
                     this.drawRegion(var1, playergfx, var10003, 80, 40, 40, var10007, xpos, ypos, 1 | 32);
                  } else {
                     playergfx = this.m_imgCmd[SONIC_N];
                     var10003 = 40 + this.playerStandCount / 8 % 2 * 40;
                     var10007 = rotNumTable[TRANS_MIRROR];
                     xpos = this.PlayerPosX() - mapView[0];
                     ypos = this.PlayerPosY() - mapView[1];
                     var10010 = gg;
                     var10011 = gg;
                     this.drawRegion(var1, playergfx, var10003, 80, 40, 40, var10007, xpos, ypos, 1 | 32);
                  }
               } else if (!raidOn && (olddir <= 22 || olddir >= 338) && (!this.blockColChk_easy(this.PlayerPosX() + 12, this.PlayerPosY()) && !this.blockColChk_easy(this.PlayerPosX() + 12, this.PlayerPosY() + 16) || !this.blockColChk_easy(this.PlayerPosX() - 12, this.PlayerPosY()) && !this.blockColChk_easy(this.PlayerPosX() - 12, this.PlayerPosY() + 16))) {
                  if (!this.blockColChk_easy(this.PlayerPosX() + 12, this.PlayerPosY()) && !this.blockColChk_easy(this.PlayerPosX() + 12, this.PlayerPosY() + 16)) {
                     var2 = rotNumTable[TRANS_NONE];
                  } else {
                     var2 = rotNumTable[TRANS_MIRROR];
                  }

                  playergfx = this.m_imgCmd[SONIC_N];
                  var10003 = 40 + this.playerStandCount / 8 % 2 * 40;
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, var10003, 80, 40, 40, var2, xpos, ypos, 1 | 32);
               } else if (this.playerStandCount < 75) {
                  playergfx = this.m_imgCmd[SONIC_N];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, 0, 0, 32, 40, var2, xpos, ypos, 1 | 32);
               } else if (this.playerStandCount < 90) {
                  playergfx = this.m_imgCmd[SONIC_N];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, 32, 0, 32, 40, var2, xpos, ypos, 1 | 32);
               } else if (this.playerStandCount < 105) {
                  playergfx = this.m_imgCmd[SONIC_N];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, 64, 0, 32, 40, var2, xpos, ypos, 1 | 32);
               } else {
                  playergfx = this.m_imgCmd[SONIC_N];
                  var10003 = 96 + this.playerStandCount / 8 % 2 * 32;
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var1, playergfx, var10003, 0, 32, 40, var2, xpos, ypos, 1 | 32);
               }
            } else if (PlayerJump || (PlayerParam[14] != 2 || PlayerParam[10] <= 0 || PlayerParam[12] != 1) && (PlayerParam[14] != 1 || PlayerParam[10] >= 0 || PlayerParam[12] != 0)) {
               int var3;
               boolean var9;
               int[] var10;
               int var11;
               if (Math.abs(PlayerParam[10]) < plmaxspd && !PlayerDush) {
                  var9 = false;
                  var5 = -20 + this.dSin(var4) * 20 / 100;
                  var6 = -20 - this.dCos(var4) * 20 / 100;
                  var7 = -24 + this.dSin(var4) * 24 / 100;
                  var8 = -24 - this.dCos(var4) * 24 / 100;
                  if (PlayerJump && olddir != 0) {
                     var5 += this.dCos(olddir) * 20 / 100;
                  }

                  if (PlayerParam[12] == 1) {
                     var2 = rotNumTable[TRANS_MIRROR];
                     var10 = new int[]{rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT180], rotNumTable[TRANS_MIRROR_ROT180]};

                     for(var3 = 0; var3 < 7 && (var3 * 45 + 23 >= var4 || (var3 + 1) * 45 + 23 < var4); ++var3) {
                     }

                     var2 = var10[var3];
                     var11 = var3 % 2;
                  } else {
                     var10 = new int[]{rotNumTable[TRANS_ROT180], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_NONE], rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT180]};

                     for(var3 = 0; var3 < 7 && (var3 * 45 + 23 >= var4 || (var3 + 1) * 45 + 23 < var4); ++var3) {
                     }

                     var2 = var10[var3];
                     var11 = var3 % 2;
                  }

                  if (var11 == 1) {
                     this.drawRegion(var1, this.m_imgCmd[SONIC_N], PlayerParam[11] / plmaxspd / 4 % 6 * 40, 40, 40, 40, var2, this.PlayerPosX() - mapView[0] + var5, this.PlayerPosY() - mapView[1] + var6, 20);
                  } else {
                     this.drawRegion(var1, this.m_imgCmd[SONIC_S], PlayerParam[11] / plmaxspd / 4 % 6 * 40, 0, 40, 42, var2, this.PlayerPosX() - mapView[0] + var5, this.PlayerPosY() - mapView[1] + var6, 20);
                  }
               } else {
                  var9 = false;
                  var5 = -20 + this.dSin(var4) * 20 / 100;
                  var6 = -20 - this.dCos(var4) * 20 / 100;
                  var7 = -24 + this.dSin(var4) * 24 / 100;
                  var8 = -24 - this.dCos(var4) * 24 / 100;
                  if (PlayerJump && olddir != 0) {
                     var5 += this.dCos(olddir) * 20 / 100;
                  }

                  if (PlayerParam[12] == 1) {
                     var2 = rotNumTable[TRANS_MIRROR];
                     var10 = new int[]{rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR_ROT90], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT270], rotNumTable[TRANS_MIRROR_ROT180], rotNumTable[TRANS_MIRROR_ROT180]};

                     for(var3 = 0; var3 < 7 && (var3 * 45 + 23 >= var4 || (var3 + 1) * 45 + 23 < var4); ++var3) {
                     }

                     var2 = var10[var3];
                     var11 = var3 % 2;
                  } else {
                     var10 = new int[]{rotNumTable[TRANS_ROT180], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_ROT90], rotNumTable[TRANS_NONE], rotNumTable[TRANS_NONE], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT270], rotNumTable[TRANS_ROT180]};

                     for(var3 = 0; var3 < 7 && (var3 * 45 + 23 >= var4 || (var3 + 1) * 45 + 23 < var4); ++var3) {
                     }

                     var2 = var10[var3];
                     var11 = var3 % 2;
                  }

                  if (var11 == 1) {
                     this.drawRegion(var1, this.m_imgCmd[SONIC_N], 240 + PlayerParam[11] / plmaxspd / 4 % 4 * 40, 40, 40, 40, var2, this.PlayerPosX() - mapView[0] + var5, this.PlayerPosY() - mapView[1] + var6, 20);
                  } else {
                     this.drawRegion(var1, this.m_imgCmd[SONIC_S], 240 + PlayerParam[11] / plmaxspd / 4 % 4 * 40, 0, 40, 42, var2, this.PlayerPosX() - mapView[0] + var5, this.PlayerPosY() - mapView[1] + var6, 20);
                  }
               }
            } else {
               var2 = rotNumTable[TRANS_NONE];
               if (PlayerParam[12] == 0) {
                  var2 = rotNumTable[TRANS_MIRROR];
               }

               playergfx = this.m_imgCmd[SONIC_N];
               var10003 = 360 + PlayerParam[11] / plmaxspd / 4 % 2 * 40;
               xpos = this.PlayerPosX() - mapView[0];
               ypos = this.PlayerPosY() - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(var1, playergfx, var10003, 80, 40, 40, var2, xpos, ypos, 1 | 32);
            }
         }

         PlayerDush = false;
         if (this.pushCount > 0) {
            --this.pushCount;
         }

         if (PlayerParam[10] == 0 && !PlayerJump) {
            ++this.playerStandCount;
         } else {
            this.playerStandCount = 0;
         }

         if (mutekicount > 0) {
            if (this.animeTimer % 2 == 0) {
               if (var5 == 0 && var6 == 0) {
                  this.setObjData(this.PlayerPosX(), this.PlayerPosY() - 18, 0);
               } else {
                  this.setObjData(this.PlayerPosX() + var7 + 24, this.PlayerPosY() + var8 + 24, 0);
               }
            }
         } else if (bariacount > 0) {
            if (var5 == 0 && var6 == 0) {
               Graphics var10001;
               if (this.animeTimer % 3 == 2) {
                  var10001 = gg;
                  playergfx = this.m_imgObj[109];
                  var10007 = rotNumTable[TRANS_NONE + 4];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - 18 - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var10001, playergfx, 48, 48, 48, 48, var10007, xpos, ypos, 1 | 2);
               } else {
                  var10001 = gg;
                  playergfx = this.m_imgObj[109];
                  int var10004 = 48 * (this.animeTimer % 3);
                  var10007 = rotNumTable[TRANS_NONE];
                  xpos = this.PlayerPosX() - mapView[0];
                  ypos = this.PlayerPosY() - 18 - mapView[1];
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(var10001, playergfx, 48, var10004, 48, 48, var10007, xpos, ypos, 1 | 2);
               }
            } else if (this.animeTimer % 3 == 2) {
               this.drawRegion(gg, this.m_imgObj[109], 48, 48, 48, 48, rotNumTable[TRANS_NONE + 4], this.PlayerPosX() - mapView[0] + var7, this.PlayerPosY() - mapView[1] + var8, 20);
            } else {
               this.drawRegion(gg, this.m_imgObj[109], 48, 48 * (this.animeTimer % 3), 48, 48, rotNumTable[TRANS_NONE], this.PlayerPosX() - mapView[0] + var7, this.PlayerPosY() - mapView[1] + var8, 20);
            }
         }

      }
   }

   private void setObjData(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < objData.length; ++var4) {
         if (objData[var4][0] == 0) {
            objData[var4][0] = 1;
            objData[var4][1] = var3;
            objData[var4][2] = var1;
            objData[var4][3] = var2;
            objData[var4][5] = 0;
            break;
         }
      }

   }

   private void moveObjData() {
      for(int var1 = 0; var1 < objData.length; ++var1) {
         if (objData[var1][0] == 1 && objData[var1][1] == 0) {
            int var10002 = objData[var1][5]++;
            if (objData[var1][5] > 60) {
               objData[var1][5] = 0;
               objData[var1][0] = 0;
            }
         }
      }

   }

   private void drawObjData() {
      for(int var1 = 0; var1 < objData.length; ++var1) {
         if (objData[var1][0] == 1 && objData[var1][1] == 0) {
            Image var10002 = this.m_imgObj[109];
            int var10004 = 48 * (objData[var1][5] / 4 % 2);
            int var10007 = rotNumTable[TRANS_NONE];
            int var10008 = objData[var1][2] - mapView[0];
            int var10009 = objData[var1][3] - mapView[1];
            Graphics var10010 = gg;
            Graphics var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 48, 48, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   public void objectInit(int var1) {
      int var2 = 0;
      if (var1 > 3) {
         var1 = 3;
      }

      try {
         boolean var4 = false;

         int var3;
         for(var3 = 0; var3 < zoneActTable[var1].length / 7; ++var3) {
            if (0 != zoneActTable[var1][var3 * 7 + 5] && 1 != zoneActTable[var1][var3 * 7 + 5] && 63 != zoneActTable[var1][var3 * 7 + 5] && 64 != zoneActTable[var1][var3 * 7 + 5] && 65 != zoneActTable[var1][var3 * 7 + 5] && 66 != zoneActTable[var1][var3 * 7 + 5] && 67 != zoneActTable[var1][var3 * 7 + 5] && 68 != zoneActTable[var1][var3 * 7 + 5] && 69 != zoneActTable[var1][var3 * 7 + 5]) {
               ++var2;
            } else {
               ++var2;
               var2 += zoneActTable[var1][var3 * 7 + 6];
            }
         }

         zoneActTable2 = new int[var2];
         ObjectAct = new boolean[var2 + 20];
         ObjectDead = new boolean[var2 + 20];
         var2 = 0;

         for(var3 = 0; var3 < zoneActTable[var1].length / 7; ++var3) {
            zoneActTable2[var3] = var2;
            if (0 != zoneActTable[var1][var3 * 7 + 5] && 1 != zoneActTable[var1][var3 * 7 + 5] && 63 != zoneActTable[var1][var3 * 7 + 5] && 64 != zoneActTable[var1][var3 * 7 + 5] && 65 != zoneActTable[var1][var3 * 7 + 5] && 66 != zoneActTable[var1][var3 * 7 + 5] && 67 != zoneActTable[var1][var3 * 7 + 5] && 68 != zoneActTable[var1][var3 * 7 + 5] && 69 != zoneActTable[var1][var3 * 7 + 5]) {
               ++var2;
            } else {
               for(int var6 = 0; var6 < zoneActTable[var1][var3 * 7 + 6] + 1; ++var6) {
                  ++var2;
               }
            }
         }
      } catch (Throwable var5) {
      }

   }

   public void addObjectChk() {
      if (!this.endingModeOn) {
         if (this.zoneNumber != 5 || this.stageNumber != 3) {
            try {
               int var2;
               if (this.ChkVecR) {
                  if (this.objChkPoint < this.objChkNum) {
                     this.objChkNum = this.objChkPoint;
                  } else {
                     this.objChkPoint = this.objChkNum;
                  }

                  while(true) {
                     var2 = (zoneActTable[this.stageNumber][this.objChkNum * 7 + 0] & 255) << 8 | zoneActTable[this.stageNumber][this.objChkNum * 7 + 1] & 255;
                     if (var2 - mapOxy[0] > this.RSize) {
                        this.ChkVecR = false;
                        break;
                     }

                     if (!ObjectAct[zoneActTable2[this.objChkNum]]) {
                        this.addObjectSet(this.objChkNum);
                     }

                     ++this.objChkNum;
                     if (this.objChkNum >= zoneActTable[this.stageNumber].length / 7) {
                        this.objChkNum = zoneActTable[this.stageNumber].length / 7 - 1;
                        this.ChkVecR = false;
                        break;
                     }
                  }

                  while(true) {
                     var2 = (zoneActTable[this.stageNumber][this.objChkPoint * 7 + 0] & 255) << 8 | zoneActTable[this.stageNumber][this.objChkPoint * 7 + 1] & 255;
                     if (var2 - mapOxy[0] >= this.LSize) {
                        break;
                     }

                     ++this.objChkPoint;
                     if (this.objChkPoint >= zoneActTable[this.stageNumber].length / 7) {
                        this.objChkPoint = zoneActTable[this.stageNumber].length / 7 - 1;
                        break;
                     }
                  }
               } else if (this.ChkVecL) {
                  if (this.objChkPoint > this.objChkNum) {
                     this.objChkNum = this.objChkPoint;
                  } else {
                     this.objChkPoint = this.objChkNum;
                  }

                  while(true) {
                     var2 = (zoneActTable[this.stageNumber][this.objChkNum * 7 + 0] & 255) << 8 | zoneActTable[this.stageNumber][this.objChkNum * 7 + 1] & 255;
                     if (var2 - mapOxy[0] < this.LSize) {
                        this.ChkVecL = false;
                        break;
                     }

                     if (!ObjectAct[zoneActTable2[this.objChkNum]]) {
                        this.addObjectSet(this.objChkNum);
                     }

                     --this.objChkNum;
                     if (this.objChkNum < 0) {
                        this.objChkNum = 0;
                        this.ChkVecL = false;
                        break;
                     }
                  }

                  while(true) {
                     var2 = (zoneActTable[this.stageNumber][this.objChkPoint * 7 + 0] & 255) << 8 | zoneActTable[this.stageNumber][this.objChkPoint * 7 + 1] & 255;
                     if (var2 - mapOxy[0] <= this.RSize) {
                        break;
                     }

                     --this.objChkPoint;
                     if (this.objChkPoint < 0) {
                        this.objChkPoint = 0;
                        break;
                     }
                  }
               }
            } catch (Throwable var4) {
               this.ChkVecR = false;
            }

         }
      }
   }

   public void addObjectSet(int var1) {
      int var2 = 1;
      int var3 = var1 * 7;
      int var4 = zoneActTable[this.stageNumber][var1 * 7 + 5] & 255;
      int var5;
      if (0 != var4 && 1 != var4 && 63 != var4 && 64 != var4 && 65 != var4 && 66 != var4 && 67 != var4 && 68 != var4 && 69 != var4) {
         if (var4 == 41 || var4 == 86 || var4 == 87 || var4 == 81 || var4 == 57 || var4 == 78 || var4 == 40 || var4 == 70 || var4 == 39 || var4 == 74 || var4 == 49 || var4 == 50 || var4 == 71 || var4 == 51 || var4 == 10 || var4 == 35) {
            var5 = (zoneActTable[this.stageNumber][var3 + 0] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 1] & 255;
            if (var5 - mapOxy[0] > -48 && var5 - mapOxy[0] < 288) {
               return;
            }
         }
      } else {
         var2 += zoneActTable[this.stageNumber][var3 + 6];
         var5 = (zoneActTable[this.stageNumber][var3 + 0] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 1] & 255;
         int var6 = (zoneActTable[this.stageNumber][var3 + 2] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 3] & 255;
         if (var2 != 1) {
            int var10000;
            switch(zoneActTable[this.stageNumber][var3 + 5]) {
            case RING_SFLAG_RING_18_00:
               var5 += (var2 - 1) * 24;
               break;
            case RING_SFLAG_RING_00_18:
               var10000 = var6 + (var2 - 1) * 24;
               break;
            case RING_SFLAG_RING_M10_10:
               var5 -= (var2 - 1) * 16;
               var10000 = var6 + (var2 - 1) * 16;
               break;
            case RING_SFLAG_RING_10_10:
               var5 += (var2 - 1) * 16;
               var10000 = var6 + (var2 - 1) * 16;
               break;
            case RING_SFLAG_RING_20_20:
               var5 += (var2 - 1) * 32;
               var10000 = var6 + (var2 - 1) * 32;
               break;
            case RING_SFLAG_RING_10_00:
               var5 += (var2 - 1) * 16;
               break;
            case RING_SFLAG_RING_20_00:
               var5 += (var2 - 1) * 32;
               break;
            case RING_SFLAG_RING_00_10:
               var10000 = var6 + (var2 - 1) * 16;
               break;
            case RING_SFLAG_RING_00_20:
               var10000 = var6 + (var2 - 1) * 32;
            }

            if (var5 - mapOxy[0] < this.LSize) {
               return;
            }

            if (var5 - mapOxy[0] > this.RSize) {
               return;
            }
         }
      }

      for(var5 = 0; var5 < var2; ++var5) {
         int[] var7 = new int[25];
         if (!ObjectDead[zoneActTable2[var1] + var5] && !ObjectAct[zoneActTable2[var1] + var5]) {
            var7[0] = 1;
            var7[1] = zoneActTable[this.stageNumber][var3 + 5] & 255;
            var7[2] = (zoneActTable[this.stageNumber][var3 + 0] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 1] & 255;
            var7[3] = (zoneActTable[this.stageNumber][var3 + 2] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 3] & 255;
            var7[8] = (zoneActTable[this.stageNumber][var3 + 0] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 1] & 255;
            var7[9] = (zoneActTable[this.stageNumber][var3 + 2] & 255) << 8 | zoneActTable[this.stageNumber][var3 + 3] & 255;
            if (var2 != 1) {
               switch(zoneActTable[this.stageNumber][var3 + 5]) {
               case RING_SFLAG_RING_18_00:
                  var7[2] += var5 * 24;
                  var7[8] += var5 * 24;
                  break;
               case RING_SFLAG_RING_00_18:
                  var7[3] += var5 * 24;
                  var7[9] += var5 * 24;
                  break;
               case RING_SFLAG_RING_M10_10:
                  var7[2] -= var5 * 16;
                  var7[8] -= var5 * 16;
                  var7[3] += var5 * 16;
                  var7[9] += var5 * 16;
                  break;
               case RING_SFLAG_RING_10_10:
                  var7[2] += var5 * 16;
                  var7[8] += var5 * 16;
                  var7[3] += var5 * 16;
                  var7[9] += var5 * 16;
                  break;
               case RING_SFLAG_RING_20_20:
                  var7[2] += var5 * 32;
                  var7[8] += var5 * 32;
                  var7[3] += var5 * 32;
                  var7[9] += var5 * 32;
                  break;
               case RING_SFLAG_RING_10_00:
                  var7[2] += var5 * 16;
                  var7[8] += var5 * 16;
                  break;
               case RING_SFLAG_RING_20_00:
                  var7[2] += var5 * 32;
                  var7[8] += var5 * 32;
                  break;
               case RING_SFLAG_RING_00_10:
                  var7[3] += var5 * 16;
                  var7[9] += var5 * 16;
                  break;
               case RING_SFLAG_RING_00_20:
                  var7[3] += var5 * 32;
                  var7[9] += var5 * 32;
               }
            }

            var7[4] = zoneActTable[this.stageNumber][var3 + 6] & 255;
            var7[19] = zoneActTable[this.stageNumber][var3 + 4] & 255;
            var7[20] = zoneActTable2[var1] + var5;
            var7[22] = zoneActTable2[var1];
            if (m_nConfigValue[0] == 0) {
               if (var7[1] == 41 || var7[1] == 86 || var7[1] == 87 || var7[1] == 81 || var7[1] == 57 || var7[1] == 78 || var7[1] == 40 || var7[1] == 70 || var7[1] == 39 || var7[1] == 74 || var7[1] == 49 || var7[1] == 50 || var7[1] == 71 || var7[1] == 51) {
                  return;
               }
            } else if (m_nConfigValue[0] == 1 && (var7[1] == 40 || var7[1] == 70 || var7[1] == 39 || var7[1] == 74 && var7[4] == 0 || var7[1] == 49 || var7[1] == 50 || var7[1] == 71)) {
               return;
            }

            ObjectAct[zoneActTable2[var1] + var5] = true;
            if (var7[1] == 0 && this.zoneNumber == 5 && this.stageNumber == 1 && 6144 > var7[2] && var7[3] < 256) {
               var7[11] = 1;
               var7[12] = var7[3] + 2048;
            }

            this.addObject(var7);
         }
      }

   }

   public void addObject(int[] var1) {
      try {
         int var2;
         if (var1[1] != 9 && var1[1] != 15 && var1[1] != 48 && var1[1] != 11 && var1[1] != 2 && var1[1] != 79 && 0 != var1[1] && 1 != var1[1] && 63 != var1[1] && 64 != var1[1] && 65 != var1[1] && 66 != var1[1] && 67 != var1[1] && 68 != var1[1] && 69 != var1[1]) {
            if (ObjectList[this.noDataPointer][24] == 0) {
               ObjectList[this.noDataPointer] = var1;
               ObjectList[this.noDataPointer][24] = 1;
               ++ObjectListNum;

               for(var2 = 0; var2 < ObjectList.length; ++var2) {
                  if (ObjectList[var2][24] == 0) {
                     this.noDataPointer = var2;
                     break;
                  }
               }
            } else {
               for(var2 = 0; var2 < ObjectList.length; ++var2) {
                  if (ObjectList[var2][24] == 0) {
                     this.noDataPointer = var2;
                     break;
                  }
               }

               ObjectList[this.noDataPointer] = var1;
               ObjectList[this.noDataPointer][24] = 1;
               ++ObjectListNum;

               for(var2 = 0; var2 < ObjectList.length; ++var2) {
                  if (ObjectList[var2][24] == 0) {
                     this.noDataPointer = var2;
                     break;
                  }
               }
            }
         } else {
            for(var2 = ObjectList.length - 1; var2 > 0; --var2) {
               if (ObjectList[var2][24] == 0) {
                  ObjectList[var2] = var1;
                  ObjectList[var2][24] = 1;
                  if (this.noDataPointer != var2) {
                     ++ObjectListNum;
                     break;
                  } else {
                     ++ObjectListNum;

                     for(var2 = 0; var2 < ObjectList.length; ++var2) {
                        if (ObjectList[var2][24] == 0) {
                           this.noDataPointer = var2;
                           return;
                        }
                     }

                     return;
                  }
               }
            }
         }
      } catch (Throwable var5) {
      }

   }

   public boolean removeObjectChk(int[] var1) {
      if (var1[2] - mapOxy[0] < this.LSize) {
         return true;
      } else {
         return var1[2] - mapOxy[0] > this.RSize;
      }
   }

   public boolean removeObjectChkDead(int[] var1) {
      return var1[0] <= 0;
   }

   public void removeObject(int var1) {
      this.noDataPointer = var1;
      ObjectList[var1][24] = 0;
      --this.listSub;
   }

   public void InsertObject(int[] var1, int var2) {
      ObjectList[var2] = var1;
   }

   public int[][] searchObject(int id, int var2) {
      this.objCount = 0;
      int var3 = 0;

      for(int objcount = 0; objcount < ObjectList.length && var3 < ObjectListNum; ++objcount) {
         if (ObjectList[objcount][24] == 1) {
            ++var3;
            if (ObjectList[objcount][1] == id && (var2 < 0 || var2 == ObjectList[objcount][4])) {
               this.objTempData[this.objCount] = ObjectList[objcount];
               this.objTempData[this.objCount][23] = objcount;
               ++this.objCount;
            }
         }
      }

      int[][] var5 = new int[this.objCount][25];
      System.arraycopy(this.objTempData, 0, var5, 0, var5.length);
      return var5;
   }

   public void objectAction() {
      this.listSub = 0;
      int var1 = 0;

      for(int var3 = 0; var3 < ObjectList.length && var1 < ObjectListNum; ++var3) {
         if (ObjectList[var3][24] == 1) {
            ++var1;
            objectData = ObjectList[var3];
            this.CallObjectMove(0);
            ObjectList[var3] = objectData;
            if (objectData[1] != 17 || objectData[4] != 55) {
               if (objectData[0] <= 0 && objectData[21] == 0) {
                  ObjectDead[objectData[20]] = true;
                  ObjectAct[objectData[20]] = false;
                  this.removeObject(var3);
               } else if (!ObjectAct[objectData[20]] && objectData[1] < 120 && objectData[1] != 42 && objectData[1] != 43) {
                  this.removeObject(var3);
               } else if (this.removeObjectChk(objectData) && objectData[21] == 0 && objectData[1] < 120 && objectData[1] != 42 && objectData[1] != 43) {
                  ObjectAct[objectData[20]] = false;
                  ObjectAct[objectData[22]] = false;
                  this.removeObject(var3);
               }
            }
         }
      }

      ObjectListNum += this.listSub;
   }

   public void CallObjectDraw() {
      try {
         int var1 = 0;

         for(int var3 = ObjectList.length - 1; var3 >= 0 && var1 < ObjectListNum; --var3) {
            if (ObjectList[var3][24] == 1) {
               ++var1;
               if (ObjectList[var3][1] != 45 && ObjectList[var3][1] != 45 && ObjectList[var3][1] != 26 && ObjectList[var3][1] != 53 && ObjectList[var3][1] != 10 && (ObjectList[var3][1] != 88 || ObjectList[var3][4] != 127)) {
                  objectData = ObjectList[var3];
                  this.CallObjectDraw(0);
               }
            }
         }
      } catch (Throwable var5) {
      }

   }

   public void CallObjectDrawFront() {
      try {
         int var1 = 0;

         for(int var3 = ObjectList.length - 1; var3 >= 0 && var1 < ObjectListNum; --var3) {
            if (ObjectList[var3][24] == 1) {
               ++var1;
               if (ObjectList[var3][1] == 45 || ObjectList[var3][1] == 45 || ObjectList[var3][1] == 26 || ObjectList[var3][1] == 53 || ObjectList[var3][1] == 10 || ObjectList[var3][1] == 88 && ObjectList[var3][4] == 127) {
                  objectData = ObjectList[var3];
                  this.CallObjectDraw(0);
               }
            }
         }
      } catch (Throwable var5) {
      }

   }

   public void loopchange() {
      if (noloopchecktimer-- <= 0) {
         boolean var1 = false;
         if (ploldpos[0] >> 8 < this.PlayerPosX() >> 8) {
            if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 54)) {
               tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 53;
            }

            if (this.zoneNumber == 3) {
               if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 42 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 43) {
                  tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 42;
               }

               if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53) {
                  tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 52;
               }
            }
         }

         if (ploldpos[0] >> 8 > this.PlayerPosX() >> 8) {
            if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 54)) {
               tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 54;
            }

            if (this.zoneNumber == 3) {
               if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 42 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 43) {
                  tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 43;
               }

               if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53) {
                  tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 53;
               }
            }
         }

         if (this.zoneNumber == 3 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53) && PlayerJump) {
            tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] = 52;
         }

         int var2 = tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] & 1;
         if (this.zoneNumber == 0 && (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 54)) {
            var1 = true;
         }

         if (this.zoneNumber == 3) {
            if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 42 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 43) {
               var1 = true;
               ++var2;
            }

            if (tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 52 || tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8] == 53) {
               var1 = true;
               ++var2;
            }
         }

         if (var1) {
            if (this.blockColChk(this.PlayerPosX(), this.PlayerPosY())) {
               if (var2 % 2 == 1 && this.PlayerPosX() / 16 % 16 < 9 && this.PlayerPosY() / 16 % 16 < 3) {
                  ++tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8];
                  noloopchecktimer = 60;
               } else if (var2 % 2 == 0 && this.PlayerPosX() / 16 % 16 >= 7 && this.PlayerPosY() / 16 % 16 < 3) {
                  --tempWorldMapData[(this.PlayerPosY() >> 8) % MapH][this.PlayerPosX() >> 8];
                  noloopchecktimer = 60;
               }
            }

         }
      }
   }

   public void waterMove() {
      if (this.waterH3 == 0) {
         this.waterH3 = this.waterH;
      }

      if (this.waterH3 < this.waterH) {
         ++this.waterH3;
         if (this.waterH3 > this.waterH) {
            this.waterH3 = this.waterH;
         }
      } else if (this.waterH3 > this.waterH) {
         --this.waterH3;
         if (this.waterH3 < this.waterH) {
            this.waterH3 = this.waterH;
         }
      }

      this.waterH2 = this.waterH3 + this.dSin(this.cpuTimer) * 8 / 100 + 8;
      if (this.zoneNumber != 1) {
         this.waterH = 16777215;
      } else {
         short var2;
         switch(this.stageNumber) {
         case 0:
            if (this.water_flag != 0) {
               if (this.water_flag - 1 == 0 && 736 > this.PlayerPosY()) {
                  this.waterH = 936;
                  if (4864 <= mapOxy[0]) {
                     this.waterH = 264;
                     this.water_flag = 2;
                     this.waterH = 264;
                  }
               }
            } else {
               this.waterH = 184;
               if (1536 <= mapOxy[0]) {
                  this.waterH = 264;
                  if (512 > this.PlayerPosY()) {
                     if (3200 <= mapOxy[0]) {
                        this.waterH = 232;
                        if (5376 <= mapOxy[0]) {
                           this.waterH = 264;
                        }
                     }
                  } else if (3072 <= mapOxy[0]) {
                     this.waterH = 792;
                     if (4224 <= mapOxy[0]) {
                        this.waterH = 1480;
                        if (4992 <= mapOxy[0]) {
                           this.waterH = 936;
                           if (this.waterH == this.waterH3) {
                              this.water_flag = 1;
                           }
                        }
                     }
                  }
               }
            }
            break;
         case 1:
            this.waterH = 808;
            if (1280 <= mapOxy[0]) {
               this.waterH = 968;
               if (2816 <= mapOxy[0]) {
                  this.waterH = 1064;
               }
            }
            break;
         case 2:
            int var1 = mapOxy[0];
            byte var3 = this.water_flag;
            if (this.water_flag != 0) {
               int var4 = var3 - 1;
               if (var4 != 0) {
                  --var4;
                  if (var4 != 0) {
                     --var4;
                     if (var4 != 0) {
                        if (7680 <= var1) {
                           this.waterH = 296;
                        }
                     } else {
                        var2 = 392;
                        if ((6896 <= var1 || mapOxy[1] >= 2001) && 6260 <= var1) {
                           var2 = 2304;
                           if (7147 > var1) {
                              this.waterH = var2;
                              this.waterH3 = var2;
                           } else {
                              this.water_flag = 4;
                              this.waterH = 1544;
                              this.waterH3 = 1920;
                           }
                        } else {
                           if (6868 <= var1 && mapOxy[1] > 1280) {
                              var2 = 2304;
                           }

                           this.waterH = var2;
                           this.waterH3 = var2;
                        }
                     }
                  } else {
                     var2 = 1288;
                     if (6240 > var1) {
                        this.waterH = var2;
                     } else {
                        var2 = 392;
                        if (6896 <= var1) {
                           this.water_flag = 3;
                           this.waterH = var2;
                        } else if (this.waterH3 != var2) {
                           this.waterH = var2;
                        } else {
                           this.water_flag = 3;
                           this.waterH = var2;
                        }
                     }
                  }
               } else {
                  var2 = 1224;
                  if (1936 > var1) {
                     this.waterH = var2;
                  } else {
                     var2 = 776;
                     if (5120 > var1) {
                        this.waterH = var2;
                     } else if (this.waterH == 1288) {
                        var2 = 1288;
                        this.waterH3 = var2;
                        if (6000 > var1) {
                           this.waterH = var2;
                        } else {
                           this.water_flag = 2;
                           this.waterH = var2;
                        }
                     } else if (1536 <= this.PlayerPosY()) {
                        var2 = 1288;
                        this.waterH3 = var2;
                        if (6000 > var1) {
                           this.waterH = var2;
                        } else {
                           this.water_flag = 2;
                           this.waterH = var2;
                        }
                     } else if (640 <= this.PlayerPosY()) {
                        this.waterH = var2;
                     } else {
                        var2 = 1288;
                        this.waterH3 = var2;
                        if (6000 > var1) {
                           this.waterH = var2;
                        } else {
                           this.water_flag = 2;
                           this.waterH = var2;
                        }
                     }
                  }
               }
            } else {
               var2 = 2304;
               if (1696 > var1) {
                  this.waterH = var2;
                  this.waterH3 = var2;
               } else if (992 > this.PlayerPosY()) {
                  this.waterH = var2;
                  this.waterH3 = var2;
               } else if (1536 <= this.PlayerPosY()) {
                  this.waterH = var2;
                  this.waterH3 = var2;
               } else {
                  var2 = 1224;
                  this.water_flag = 1;
                  this.waterH = var2;
                  this.waterH3 = var2;
               }
            }
            break;
         case 3:
            var2 = 552;
            if (3840 > mapOxy[0]) {
               this.waterH = var2;
            } else {
               var2 = 1224;
               this.waterH = var2;
            }
         }

      }
   }

   public void pata_draw(int var1) {
      if (objectData[4] == 1 || objectData[4] == 2) {
         if (this.cpuTimer / 30 / 4 % 2 == 0) {
            this.drawImage(gg, this.m_imgObj[PATA], objectData[2] - mapView[0] - this.m_imgObj[PATA].getWidth(), objectData[3] - mapView[1] - this.m_imgObj[PATA].getHeight() / 2, 20);
            this.drawImage(gg, this.m_imgObj[PATA], objectData[2] - mapView[0], objectData[3] - mapView[1] - this.m_imgObj[PATA].getHeight() / 2, 20);
         } else {
            this.drawRegion(gg, this.m_imgObj[PATA], 0, 0, 64, 24, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0] - this.m_imgObj[PATA].getWidth() - 12, objectData[3] - mapView[1], 20);
            this.drawRegion(gg, this.m_imgObj[PATA], 0, 0, 64, 24, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0] + this.m_imgObj[PATA].getWidth() - 12, objectData[3] - mapView[1], 20);
         }
      }

   }

   public void drawRegion(Graphics var1, Image var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      try {
         var9 += 36;
         var1.drawRegion(var2, var3, var4, var5, var6, var7, var8, var9, var10);
      } catch (Throwable var12) {
      }

   }

   public void drawImage(Graphics var1, Image var2, int var3, int var4, int var5) {
      try {
         var4 += 36;
         var1.drawImage(var2, var3, var4, var5);
      } catch (Throwable var7) {
      }

   }

   public Image createImage(String var1) {
      try {
         System.gc();
         if (drawRsm) {
            this.DG();
         }

         return Image.createImage(var1);
      } catch (Exception var3) {
         return this.createImage(var1);
      } catch (Throwable var4) {
         return null;
      }
   }

   public void getItem(int var1) {
      if (var1 == 2) {
         ++playercount;
         this.PlayMusic(13);
      } else if (var1 == 3) {
         speedupcount = 1200;
         plmaxspd = 3072;
         pladdspd = 24;
      } else if (var1 == 4) {
         bariacount = 1;
      } else if (var1 == 5) {
         mutekicount = 1200;
         this.PlayMusic(12);
      } else if (var1 == 6) {
         ringcount += 10;
      }

   }

   public void CallObjectMove(int var1) {
      try {
         switch(objectData[1]) {
         case RING_SFLAG_RING_18_00:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_18_00_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_00_18:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_00_18_move_ikeshita(var1);
            }
            break;
         case SJUMP_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 16, 16)) {
               this.sjump_nflag_move_sakaki(var1);
            }
            break;
         case BURANKO_NFLAG:
            this.buranko_nflag_move_ikeshita(var1);
            break;
         case THASHI_NFLAG:
            this.thashi_nflag_move_ikeshita(var1);
            break;
         case HASHI_NFLAG:
            this.hashi_nflag_move_ikeshita(var1);
            break;
         case BREAK_SFLAG:
            this.break_sflag_move_ikeshita(var1);
            break;
         case YUKA_NFLAG:
            this.yuka_nflag_move_ikeshita(var1);
            break;
         case TURI_NFLAG:
            this.turi_nflag_move_ikeshita(var1);
            break;
         case TOGE_NFLAG:
            this.toge_nflag_move_ikeshita(var1);
            break;
         case BOX_SFLAG:
            this.box_sflag_move_ikeshita(var1);
            break;
         case FBLOCK_NFLAG:
            this.fblock_nflag_move_ikeshita(var1);
         case DAI_NFLAG:
         case BGSPR_NFLAG:
         case BIGRING_NFLAG:
         case HAGURUMA_NFLAG:
         case SIGNAL_NFLAG:
         case HASHIRA_NFLAG:
         case TAKI_NFLAG:
         case TURI2:
         case TURI3:
         case TAMA:
         case BAKUHATU:
         case MYOGAN2:
         case YOGAN2:
         case ANIMAL:
         case _FIRE:
         case BLOCK:
         case 103:
         case OBJAWA:
         case DAI3_0x27:
         case DAI3_0x13:
         case DAI2_0xE0:
         case DAI2_0xF0:
         case EFFECT:
         case MIZU_0x09:
         case WATER2:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 121:
         case 122:
         case 123:
         case 124:
         case 126:
         case 127:
         case 128:
         case 129:
         case 131:
         case 132:
         case 133:
         case 134:
         case 136:
         case 137:
         case 138:
         case 139:
         case 141:
         case 142:
         case 143:
         case 144:
         case 146:
         case 147:
         case 148:
         case 149:
         default:
            break;
         case YOGAN2_SFLAG:
            this.yogan2_sflag_move_ikeshita(var1);
            break;
         case MYOGAN_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, -1)) {
               this.myogan_nflag_move_ikeshita(var1);
            }
            break;
         case SWITCH2_NFLAG:
            this.switch2_nflag_move_ikeshita(var1);
            break;
         case SHIMA_NFLAG:
            this.shima_nflag_move_ikeshita(var1);
            break;
         case DAI2_NFLAG:
            this.dai2_nflag_move_ikeshita(var1);
            break;
         case BRKABE_SFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 16, 32)) {
               this.brkabe_sflag_move_ikeshita(var1);
            }
            break;
         case PEDAL_NFLAG:
            this.pedal_nflag_move_ikeshita(var1);
            break;
         case BREAK2_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 16, -1)) {
               this.break2_nflag_move_ikeshita(var1);
            }
            break;
         case STEP_NFLAG:
            this.step_nflag_move_ikeshita(var1);
            break;
         case FUN_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 96, 64)) {
               this.fun_nflag_move_ikeshita(var1);
            }
            break;
         case SISOO_NFLAG:
            this.sisoo_nflag_move_arai(var1);
            break;
         case BELT_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 128, 16)) {
               this.belt_nflag_move_ikeshita(var1);
            }
            break;
         case PATA_NFLAG:
            this.pata_nflag_move_ikeshita(var1);
            break;
         case FIRE6_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 100)) {
               this.fire6_nflag_move_ikeshita(var1);
            }
            break;
         case BRYUKA_NFLAG:
            this.bryuka_nflag_move_ikeshita(var1);
            break;
         case MAWARU_NFLAG:
            this.mawaru_nflag_move_ikeshita(var1);
            break;
         case YUKAI_NFLAG:
            this.yukai_nflag_move_ikeshita(var1);
            break;
         case DOOR_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 64)) {
               this.door_nflag_move_ikeshita(var1);
            }
            break;
         case YUKAE_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 32)) {
               this.yukae_nflag_move_ikeshita(var1);
            }
            break;
         case DAI4_NFLAG:
            this.dai4_nflag_move_ikeshita(var1);
            break;
         case ELE_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 16)) {
               this.ele_nflag_move_ikeshita(var1);
            }
            break;
         case BELTC_NFLAG:
            this.beltc_nflag_move_ikeshita(var1);
            break;
         case NOKO_NFLAG:
            this.noko_nflag_move_ikeshita(var1);
            break;
         case SAVE_SFLAG:
            this.save_sflag_move_ikeshita(var1);
            break;
         case KAGEB_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, 32)) {
               this.kageb_nflag_move_ikeshita(var1);
            }
            break;
         case KAMERE_SFLAG:
            this.kamere_sflag_move_arai(var1);
            break;
         case HACHI_SFLAG:
            this.hachi_sflag_move_arai(var1);
            break;
         case MUSI_SFLAG:
            this.musi_sflag_move_arai(var1);
            break;
         case ITEM_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 16, 16)) {
               this.item_nflag_move_ikeshita(var1);
            }
            break;
         case ITEM_SFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 16, 16)) {
               this.item_sflag_move_ikeshita(var1);
            }
            break;
         case GOLE_NFLAG:
            this.gole_nflag_move_ikeshita(var1);
            break;
         case BTEN_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 32)) {
               this.bten_nflag_move_ikeshita(var1);
            }
            break;
         case BTEN_SFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 32)) {
               this.bten_sflag_move_ikeshita(var1);
            }
            break;
         case SCOLI_NFLAG:
            this.scoli_nflag_move_ikeshita(var1);
            break;
         case IMO_SFLAG:
            this.imo_sflag_move_arai(var1);
            break;
         case BROBO_SFLAG:
            this.brobo_sflag_move_arai(var1);
            break;
         case BUTA_SFLAG:
            this.buta_sflag_move_arai(var1);
            break;
         case SHOOTER_NFLAG:
            this.shooter_nflag_move_ikeshita(var1);
            break;
         case DAINFLA:
            this.dainfla_move_ikeshita(var1);
            break;
         case MASIN_NFLAG:
            this.masin_nflag_move_ikeshita(var1);
            break;
         case BOBIN_SFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 32)) {
               this.bobin_sflag_move_ikeshita(var1);
            }
            break;
         case KANI_SFLAG:
            this.kani_sflag_move_arai(var1);
            break;
         case JYAMA_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 24, 24)) {
               this.jyama_nflag_move_ikeshita(var1);
            }
            break;
         case FETAMA_NFLAG:
            this.fetama_nflag_move_ikeshita(var1);
            break;
         case TEKYU_NFLAG:
            this.tekyu_nflag_move_ikeshita(var1);
            break;
         case DAI2_SFLAG:
            this.dai2_sflag_move_ikeshita(var1);
            break;
         case RING_SFLAG_RING_M10_10:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_m10_10_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_10_10:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_10_10_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_20_20:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_20_20_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_10_00:
            this.ring_sflag_ring_10_00_move_ikeshita(var1);
            break;
         case RING_SFLAG_RING_20_00:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_20_00_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_00_10:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_00_10_move_ikeshita(var1);
            }
            break;
         case RING_SFLAG_RING_00_20:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 8, -1) || objectData[5] != 0) {
               this.ring_sflag_ring_00_20_move_ikeshita(var1);
            }
            break;
         case ARUMA_SFLAG:
            this.aruma_sflag_move_arai(var1);
            break;
         case YADO_SFLAG:
            this.yado_sflag_move_arai(var1);
            break;
         case ELEV_NFLAG_80:
            this.elev_nflag_80_move_ikeshita(var1);
            break;
         case ELEV_NFLAG:
            this.elev_nflag_move_ikeshita(var1);
            break;
         case UNI_SFLAG:
            this.uni_sflag_move_arai(var1);
            break;
         case MFIRE_NFLAG:
            this.mfire_nflag_move_ikeshita(var1);
            break;
         case YOGANC_NFLAG:
            this.yoganc_nflag_move_ikeshita(var1);
            break;
         case BAT_SFLAG:
            this.bat_sflag_move_arai(var1);
            break;
         case OCHI_NFLAG:
            this.ochi_nflag_move_ikeshita(var1);
            break;
         case YARI_SFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 24, 24)) {
               this.yari_sflag_move_ikeshita(var1);
            }
            break;
         case MOGURA_SFLAG:
            this.mogura_sflag_move_arai(var1);
            break;
         case KAZARI_SFLAG:
            this.kazari_sflag_move_ikeshita(var1);
            break;
         case DAI3_NFLAG:
            this.dai3_nflag_move_ikeshita(var1);
            break;
         case MIZU_NFLAG:
            this.mizu_nflag_move_sakaki(var1);
            break;
         case AWA_NFLAG:
            this.awa_nflag_move_sakaki(var1);
            break;
         case FISH_SFLAG:
            this.fish_sflag_move_arai(var1);
            break;
         case FISH2_SFLAG:
            this.fish2_sflag_move_arai(var1);
            break;
         case KASSYA_NFLAG:
            this.kassya_nflag_move_ikeshita(var1);
            break;
         case SHIMA2_NFLAG:
            this.shima2_nflag_move_ikeshita(var1);
            break;
         case BOU_NFLAG:
            if (this.ObjectMoveChk(objectData[2], objectData[3], 32, 42)) {
               this.bou_nflag_move_ikeshita(var1);
            }
            break;
         case BEN_NFLAG:
            this.ben_nflag_move_sakaki(var1);
            break;
         case BEN_SFLAG:
            this.ben_sflag_move_sakaki(var1);
            break;
         case BOSS1:
            this.boss1_move_arai(var1);
            break;
         case BOSS2:
            this.boss2_move_arai(var1);
            break;
         case BOSS3:
            this.boss3_move_arai(var1);
            break;
         case BOSS4:
            this.boss4_move_arai(var1);
            break;
         case BOSS5:
            this.boss5_move_arai(var1);
            break;
         case BOSS6:
            this.boss6_move_arai(var1);
            break;
         case BOSS5BLOCK:
            this.MoveBoss5Block(var1);
         }
      } catch (Throwable var3) {
      }

   }

   public void CallObjectDraw(int var1) {
      try {
         switch(objectData[1]) {
         case RING_SFLAG_RING_18_00:
            this.ring_sflag_ring_18_00_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_00_18:
            this.ring_sflag_ring_00_18_draw_ikeshita(var1);
            break;
         case SJUMP_NFLAG:
            this.sjump_nflag_draw_sakaki(var1);
            break;
         case BURANKO_NFLAG:
            this.buranko_nflag_draw_ikeshita(var1);
            break;
         case THASHI_NFLAG:
            this.thashi_nflag_draw_ikeshita(var1);
            break;
         case HASHI_NFLAG:
            this.hashi_nflag_draw_ikeshita(var1);
            break;
         case BREAK_SFLAG:
            this.break_sflag_draw_ikeshita(var1);
            break;
         case YUKA_NFLAG:
            this.yuka_nflag_draw_ikeshita(var1);
            break;
         case TURI_NFLAG:
            this.turi_nflag_draw_ikeshita(var1);
            break;
         case TOGE_NFLAG:
            this.toge_nflag_draw_ikeshita(var1);
            break;
         case BOX_SFLAG:
            this.box_sflag_draw_ikeshita(var1);
            break;
         case FBLOCK_NFLAG:
            this.fblock_nflag_draw_ikeshita(var1);
         case DAI_NFLAG:
         case BELT_NFLAG:
         case BIGRING_NFLAG:
         case SCOLI_NFLAG:
         case HAGURUMA_NFLAG:
         case SHOOTER_NFLAG:
         case HASHIRA_NFLAG:
         case TAKI_NFLAG:
         case TURI2:
         case TURI3:
         case TAMA:
         case BAKUHATU:
         case MYOGAN2:
         case YOGAN2:
         case ANIMAL:
         case _FIRE:
         case BLOCK:
         case 103:
         case OBJAWA:
         case DAI3_0x27:
         case DAI3_0x13:
         case DAI2_0xE0:
         case DAI2_0xF0:
         case EFFECT:
         case MIZU_0x09:
         case WATER2:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 121:
         case 122:
         case 123:
         case 124:
         case 126:
         case 127:
         case 128:
         case 129:
         case 131:
         case 132:
         case 133:
         case 134:
         case 136:
         case 137:
         case 138:
         case 139:
         case 141:
         case 142:
         case 143:
         case 144:
         case 146:
         case 147:
         case 148:
         case 149:
         default:
            break;
         case YOGAN2_SFLAG:
            this.yogan2_sflag_draw_ikeshita(var1);
            break;
         case MYOGAN_NFLAG:
            this.myogan_nflag_draw_ikeshita(var1);
            break;
         case SWITCH2_NFLAG:
            this.switch2_nflag_draw_ikeshita(var1);
            break;
         case SHIMA_NFLAG:
            this.shima_nflag_draw_ikeshita(var1);
            break;
         case DAI2_NFLAG:
            this.dai2_nflag_draw_ikeshita(var1);
            break;
         case BRKABE_SFLAG:
            this.brkabe_sflag_draw_ikeshita(var1);
            break;
         case PEDAL_NFLAG:
            this.pedal_nflag_draw_ikeshita(var1);
            break;
         case BREAK2_NFLAG:
            this.break2_nflag_draw_ikeshita(var1);
            break;
         case STEP_NFLAG:
            this.step_nflag_draw_ikeshita(var1);
            break;
         case FUN_NFLAG:
            this.fun_nflag_draw_ikeshita(var1);
            break;
         case SISOO_NFLAG:
            this.sisoo_nflag_draw_arai(var1);
            break;
         case PATA_NFLAG:
            this.pata_nflag_draw_ikeshita(var1);
            break;
         case FIRE6_NFLAG:
            this.fire6_nflag_draw_ikeshita(var1);
            break;
         case BRYUKA_NFLAG:
            this.bryuka_nflag_draw_ikeshita(var1);
            break;
         case MAWARU_NFLAG:
            this.mawaru_nflag_draw_ikeshita(var1);
            break;
         case YUKAI_NFLAG:
            this.yukai_nflag_draw_ikeshita(var1);
            break;
         case DOOR_NFLAG:
            this.door_nflag_draw_ikeshita(var1);
            break;
         case YUKAE_NFLAG:
            this.yukae_nflag_draw_ikeshita(var1);
            break;
         case DAI4_NFLAG:
            this.dai4_nflag_draw_ikeshita(var1);
            break;
         case ELE_NFLAG:
            this.ele_nflag_draw_ikeshita(var1);
            break;
         case BELTC_NFLAG:
            this.beltc_nflag_draw_ikeshita(var1);
            break;
         case NOKO_NFLAG:
            this.noko_nflag_draw_ikeshita(var1);
            break;
         case SAVE_SFLAG:
            this.save_sflag_draw_ikeshita(var1);
            break;
         case KAGEB_NFLAG:
            this.kageb_nflag_draw_ikeshita(var1);
            break;
         case BGSPR_NFLAG:
            this.bgspr_nflag_draw_sakaki(var1);
            break;
         case KAMERE_SFLAG:
            this.kamere_sflag_draw_arai(var1);
            break;
         case HACHI_SFLAG:
            this.hachi_sflag_draw_arai(var1);
            break;
         case MUSI_SFLAG:
            this.musi_sflag_draw_arai(var1);
            break;
         case ITEM_NFLAG:
            this.item_nflag_draw_ikeshita(var1);
            break;
         case ITEM_SFLAG:
            this.item_sflag_draw_ikeshita(var1);
            break;
         case GOLE_NFLAG:
            this.gole_nflag_draw_ikeshita(var1);
            break;
         case BTEN_NFLAG:
            this.bten_nflag_draw_ikeshita(var1);
            break;
         case BTEN_SFLAG:
            this.bten_sflag_draw_ikeshita(var1);
            break;
         case IMO_SFLAG:
            this.imo_sflag_draw_arai(var1);
            break;
         case BROBO_SFLAG:
            this.brobo_sflag_draw_arai(var1);
            break;
         case BUTA_SFLAG:
            this.buta_sflag_draw_arai(var1);
            break;
         case DAINFLA:
            this.dainfla_draw_ikeshita(var1);
            break;
         case MASIN_NFLAG:
            this.masin_nflag_draw_ikeshita(var1);
            break;
         case BOBIN_SFLAG:
            this.bobin_sflag_draw_ikeshita(var1);
            break;
         case KANI_SFLAG:
            this.kani_sflag_draw_arai(var1);
            break;
         case JYAMA_NFLAG:
            this.jyama_nflag_draw_ikeshita(var1);
            break;
         case FETAMA_NFLAG:
            this.fetama_nflag_draw_ikeshita(var1);
            break;
         case TEKYU_NFLAG:
            this.tekyu_nflag_draw_ikeshita(var1);
            break;
         case SIGNAL_NFLAG:
            this.signal_nflag_draw_sakaki(var1);
            break;
         case DAI2_SFLAG:
            this.dai2_sflag_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_M10_10:
            this.ring_sflag_ring_m10_10_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_10_10:
            this.ring_sflag_ring_10_10_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_20_20:
            this.ring_sflag_ring_20_20_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_10_00:
            this.ring_sflag_ring_10_00_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_20_00:
            this.ring_sflag_ring_20_00_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_00_10:
            this.ring_sflag_ring_00_10_draw_ikeshita(var1);
            break;
         case RING_SFLAG_RING_00_20:
            this.ring_sflag_ring_00_20_draw_ikeshita(var1);
            break;
         case ARUMA_SFLAG:
            this.aruma_sflag_draw_arai(var1);
            break;
         case YADO_SFLAG:
            this.yado_sflag_draw_arai(var1);
            break;
         case ELEV_NFLAG_80:
            this.elev_nflag_80_draw_ikeshita(var1);
            break;
         case ELEV_NFLAG:
            this.elev_nflag_draw_ikeshita(var1);
            break;
         case UNI_SFLAG:
            this.uni_sflag_draw_arai(var1);
            break;
         case MFIRE_NFLAG:
            this.mfire_nflag_draw_ikeshita(var1);
            break;
         case YOGANC_NFLAG:
            this.yoganc_nflag_draw_ikeshita(var1);
            break;
         case BAT_SFLAG:
            this.bat_sflag_draw_arai(var1);
            break;
         case OCHI_NFLAG:
            this.ochi_nflag_draw_ikeshita(var1);
            break;
         case YARI_SFLAG:
            this.yari_sflag_draw_ikeshita(var1);
            break;
         case MOGURA_SFLAG:
            this.mogura_sflag_draw_arai(var1);
            break;
         case KAZARI_SFLAG:
            this.kazari_sflag_draw_ikeshita(var1);
            break;
         case DAI3_NFLAG:
            this.dai3_nflag_draw_ikeshita(var1);
            break;
         case MIZU_NFLAG:
            this.mizu_nflag_draw_sakaki(var1);
            break;
         case AWA_NFLAG:
            this.awa_nflag_draw_sakaki(var1);
            break;
         case FISH_SFLAG:
            this.fish_sflag_draw_arai(var1);
            break;
         case FISH2_SFLAG :
            this.fish2_sflag_draw_arai(var1);
            break;
         case KASSYA_NFLAG:
            this.kassya_nflag_draw_ikeshita(var1);
            break;
         case SHIMA2_NFLAG:
            this.shima2_nflag_draw_ikeshita(var1);
            break;
         case BOU_NFLAG:
            this.bou_nflag_draw_ikeshita(var1);
            break;
         case BEN_NFLAG:
            this.ben_nflag_draw_sakaki(var1);
            break;
         case BEN_SFLAG:
            this.ben_sflag_draw_sakaki(var1);
            break;
         case BOSS1:
            this.boss1_draw_arai(var1);
            break;
         case BOSS2:
            this.boss2_draw_arai(var1);
            break;
         case BOSS3:
            this.boss3_draw_arai(var1);
            break;
         case BOSS4:
            this.boss4_draw_arai(var1);
            break;
         case BOSS5:
            this.boss5_draw_arai(var1);
            break;
         case BOSS6:
            this.boss6_draw_arai(var1);
            break;
         case BOSS5BLOCK:
            this.DrawBoss5Block(false);
         }
      } catch (Throwable var3) {
      }

   }

   public int ObjectColChk(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      if (!PlayerDie && !PlayerNoCol && !debugFlag) {
         if (var4 + var6 <= var10 - var12 && var2 + var6 >= var8 - var12 && var1 + var5 >= var7 - var11 && var1 - var5 <= var7 + var11) {
            return var1 + var5 > var7 - var11 && var1 - var5 < var7 + var11 ? 0 : -1;
         } else if (var3 + var5 <= var9 - var11 && var1 + var5 >= var7 - var11 && var2 + var6 >= var8 - var12 && var2 - var6 <= var8 + var12) {
            return 1;
         } else if (var3 - var5 >= var9 + var11 && var1 - var5 <= var7 + var11 && var2 + var6 >= var8 - var12 && var2 - var6 <= var8 + var12) {
            return 2;
         } else if (var4 - var6 >= var10 + var12 && var2 - var6 <= var8 + var12 && var1 + var5 >= var7 - var11 && var1 - var5 <= var7 + var11) {
            return 3;
         } else {
            return var1 + var5 >= var7 - var11 && var1 - var5 <= var7 + var11 && var2 + var6 >= var8 - var12 && var2 - var6 <= var8 + var12 ? 4 : -1;
         }
      } else {
         return -1;
      }
   }

   private void setRaidOnSize(int var1, int var2) {
      raidObjectW = var2;
      raidObjectX = var1;
   }

   private void setHeadHit() {
      this.crushing[3] = true;
      if (PlayerParam[5] < 0) {
         PlayerParam[5] = 0;
      }

   }

   public int getPlayerH() {
      byte var1 = 18;
      if (PlayerBall) {
         var1 = 12;
      }

      return var1;
   }

   public int ObjectColChk2(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (!PlayerDie && !PlayerNoCol && !debugFlag) {
         int var7 = (540 - olddir) % 360;
         if (0 > var7) {
            var7 = 0;
         }

         byte var8 = 12;
         if (!PlayerBall && !PlayerCrouch) {
            var8 = 18;
         }

         int var9 = this.dSin(var7) * var8 / 100;
         int var10 = -this.dCos(var7) * var8 / 100;
         int var11 = this.PlayerPosX() + var9;
         int var12 = this.PlayerPosY() + var10;
         int var13 = ploldpos[0] + var9;
         int var14 = ploldpos[1] + var10;
         byte var15 = 12;
         if (var14 + var8 <= var4 - var6 && var12 + var8 >= var2 - var6 && var11 + var15 >= var1 - var5 && var11 - var15 <= var1 + var5) {
            return 0;
         } else if (var13 + var15 <= var3 - var5 && var11 + var15 >= var1 - var5 && var12 + var8 >= var2 - var6 && var12 - var8 <= var2 + var6) {
            return 1;
         } else if (var13 - var15 >= var3 + var5 && var11 - var15 <= var1 + var5 && var12 + var8 >= var2 - var6 && var12 - var8 <= var2 + var6) {
            return 2;
         } else if (var14 - var8 >= var4 + var6 && var12 - var8 <= var2 + var6 && var11 + var15 >= var1 - var5 && var11 - var15 <= var1 + var5) {
            return 3;
         } else {
            return var11 + var15 >= var1 - var5 && var11 - var15 <= var1 + var5 && var12 + var8 >= var2 - var6 && var12 - var8 <= var2 + var6 ? 4 : -1;
         }
      } else {
         return -1;
      }
   }

   public int ObjectColChkPl(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (!PlayerDie && !PlayerNoCol && !debugFlag) {
         byte var7 = 12;
         if (!PlayerBall) {
            var7 = 16;
         }

         byte var8 = 0;
         int var9 = -var7;
         int var10 = this.PlayerPosX() + var8;
         int var11 = this.PlayerPosY() + var9;
         int var12 = ploldpos[0] + var8;
         int var13 = ploldpos[1] + var9;
         byte var14 = 12;
         if (var13 + var7 <= var4 - var6 && var11 + var7 >= var2 - var6 && var10 + var14 >= var1 - var5 && var10 - var14 <= var1 + var5) {
            return 0;
         } else if (var12 + var14 <= var3 - var5 && var10 + var14 >= var1 - var5 && var11 + var7 >= var2 - var6 && var11 - var7 <= var2 + var6) {
            return 1;
         } else if (var12 - var14 >= var3 + var5 && var10 - var14 <= var1 + var5 && var11 + var7 >= var2 - var6 && var11 - var7 <= var2 + var6) {
            return 2;
         } else if (var13 - var7 >= var4 + var6 && var11 - var7 <= var2 + var6 && var10 + var14 >= var1 - var5 && var10 - var14 <= var1 + var5) {
            return 3;
         } else {
            return var10 + var14 >= var1 - var5 && var10 - var14 <= var1 + var5 && var11 + var7 >= var2 - var6 && var11 - var7 <= var2 + var6 ? 4 : -1;
         }
      } else {
         return -1;
      }
   }

   public void sjump_nflag_move_sakaki(int var1) {
      byte var2 = 16;
      byte var3 = 8;
      if (objectData[4] == 16 || objectData[4] == 18) {
         var2 = 8;
         var3 = 16;
      }

      boolean var4 = false;
      int var10002;
      if (objectData[5] > 0) {
         var10002 = objectData[5]++;
         if (objectData[5] > 12) {
            objectData[5] = 0;
         }
      }

      int var5 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var5 >= 0) {
         short var6 = 4096;
         int var7 = objectData[4] / 16;
         if (objectData[4] % 16 == 2) {
            var6 = 2560;
         }

         if (var7 == 1) {
            var2 = 7;
         } else {
            var3 = 7;
         }

         if (var5 != 0 && var5 != 4) {
            if (var5 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 - 1 << 8;
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               if (var7 == 1 && objectData[19] == 1) {
                  this.noLeverTimer = 15;
                  PlayerParam[10] = -var6;
                  PlayerParam[12] = 1;
                  PlayerParam[13] = 1;
                  PlayerParam[14] = 1;
                  var10002 = objectData[5]++;
               }
            } else if (var5 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               PlayerParam[13] = 0;
               PlayerParam[14] = 0;
               if (var7 == 1 && objectData[19] == 0) {
                  this.noLeverTimer = 15;
                  PlayerParam[10] = var6;
                  PlayerParam[12] = 0;
                  PlayerParam[13] = 2;
                  PlayerParam[14] = 2;
                  var10002 = objectData[5]++;
               }
            } else if (var5 == 3) {
               if (var7 == 2) {
                  if (objectData[19] == 2) {
                     PlayerParam[5] = var6;
                     PlayerJump = true;
                     this.noLeverTimer = 30;
                  }

                  var10002 = objectData[5]++;
               }

               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            }
         } else {
            PlayerParam[1] = objectData[3] - var3 << 8;
            if (var7 == 0) {
               if (objectData[5] == 4) {
                  PlayerParam[1] = objectData[3] - 8 << 8;
                  PlayerJump = true;
                  PlayerDamage = false;
                  PlayerSJump = true;
                  PlayerBall = false;
                  comboScore = 0;
                  byte var8 = 0;
                  PlayerParam[5] = this.dCos(var8) * var6 / 100;
               }

               var10002 = objectData[5]++;
            } else {
               this.playerRaidOn(objectData[22]);
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

   }

   public void mizu_nflag_move_sakaki(int var1) {
      byte var2;
      byte var3;
      int var4;
      if (objectData[4] == 8) {
         var2 = 24;
         var3 = 8;
         var4 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
         if (var4 >= 0) {
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
         var2 = 8;
         var3 = 8;
         var4 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
         if (var4 >= 0) {
            PlayerWater = true;
         }
      }

   }

   public void awa_nflag_move_sakaki(int var1) {
      int var10002;
      if (objectData[5] % 256 == 0) {
         objectData[10] = this.rnd(4) + this.rnd(2) + this.rnd(2);
         objectData[11] = 1;
         var10002 = objectData[5]++;
      }

      if (objectData[10] == 0) {
         var10002 = objectData[5]++;
      } else if (this.cpuTimer % 16 == 0 && this.rnd(3) != 0) {
         var10002 = objectData[10]--;
         boolean var2;
         int var3;
         if (objectData[10] > 0) {
            var2 = false;
            if (objectData[11] != 0) {
               var3 = this.rnd(3);
            } else {
               var3 = this.rnd(2);
            }

            this.objAwaData_set(var3, objectData[8], objectData[9]);
            if (var3 == 2) {
               var10002 = objectData[11]--;
            }
         } else if (objectData[11] != 0) {
            var10002 = objectData[11]--;
            this.objAwaData_set(2, objectData[8], objectData[9]);
         } else {
            var2 = false;
            var3 = this.rnd(2);
            this.objAwaData_set(var3, objectData[8], objectData[9]);
         }
      }

   }

   public void objAwaData_set(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < objAwaData.length; ++var4) {
         if (objAwaData[var4][0] == 0) {
            for(int var5 = 0; var5 < objAwaData[var4].length; ++var5) {
               objAwaData[var4][var5] = 0;
            }

            objAwaData[var4][0] = 1;
            objAwaData[var4][1] = var1;
            objAwaData[var4][2] = objAwaData[var4][8] = var2 + this.rnd(16) - 8;
            objAwaData[var4][3] = objAwaData[var4][9] = var3;
            objAwaData[var4][6] = this.rnd(2) * 16 * 4;
            break;
         }
      }

   }

   public void objAwaData_move() {
      for(int var1 = 0; var1 < objAwaData.length; ++var1) {
         if (objAwaData[var1][0] > 0) {
            if (objAwaData[var1][3] - mapView[1] > -48 && objAwaData[var1][3] - mapView[1] < 288 && this.waterH3 < objAwaData[var1][3]) {
               int var2 = this.awasintlb[objAwaData[var1][6] % this.awasintlb.length];
               if (var2 > 128) {
                  var2 -= 255;
               }

               objAwaData[var1][10] = objAwaData[var1][2];
               objAwaData[var1][11] = objAwaData[var1][3];
               objAwaData[var1][2] = objAwaData[var1][8] + var2;
               objAwaData[var1][3] = objAwaData[var1][9] - objAwaData[var1][5] / 2;
               int var10002 = objAwaData[var1][5]++;
               var10002 = objAwaData[var1][6]++;
               var10002 = objAwaData[var1][7]++;
               if (objAwaData[var1][1] * 32 + 16 < objAwaData[var1][7]) {
                  objAwaData[var1][7] = objAwaData[var1][1] * 32 + 16;
                  if (objAwaData[var1][1] == 2) {
                     byte var3 = 16;
                     byte var4 = 16;
                     byte var5 = -1;
                     if (Math.abs(this.PlayerPosX() - objAwaData[var1][2]) < 12 + var3 && Math.abs(this.PlayerPosY() - 12 - objAwaData[var1][3]) < 12 + var4) {
                        var5 = 0;
                     }

                     if (var5 >= 0) {
                        objAwaData[var1][1] = 3;
                        kokyutimer = 8;
                        this.bressCount = 2100;
                        this.bressMusic = true;
                        PlayerBall = false;
                        this.PlayMusic(27);
                        if (PlayerJump && -pljump_w >> 1 > PlayerParam[5]) {
                           PlayerParam[5] = -pljump_w >> 1;
                        }
                     }
                  } else if (objAwaData[var1][1] == 3) {
                     objAwaData[var1][0] = 0;
                  }
               }
            } else {
               objAwaData[var1][0] = 0;
            }
         }
      }

   }

   public void objAwaData_draw() {
      for(int var1 = 0; var1 < objAwaData.length; ++var1) {
         if (objAwaData[var1][0] > 0) {
            int var2 = objAwaData[var1][7] / 16;
            Image var10002 = this.m_imgObj[104];
            int var10004 = this.awaPos[var2];
            int var10006 = this.awaSize[var2];
            int var10007 = rotNumTable[TRANS_NONE];
            int var10008 = objAwaData[var1][2] - mapView[0];
            int var10009 = objAwaData[var1][3] - mapView[1];
            Graphics var10010 = gg;
            Graphics var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 32, var10006, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   public void ben_nflag_move_sakaki(int var1) {
      if (this.animeTimer % 60 < 30) {
         if (raidOn && raidObjectNum == objectData[20]) {
            raidOn = false;
         }

      } else {
         byte var2 = 8;
         byte var3 = 32;
         int var4 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
         if (objectData[5] == 1) {
            var4 = -1;
         }

         if (var4 >= 0 && var4 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
         }

         if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
            raidOn = false;
         }

      }
   }

   public void ben_sflag_move_sakaki(int var1) {
      this.ben_nflag_move_sakaki(var1);
   }

   public void sjump_nflag_draw_sakaki(int var1) {
      int var2 = SJUMP;
      int var3 = objectData[4] / 16;
      if (objectData[4] % 16 == 2) {
         var2 = 1;
      }

      int[] var4 = new int[]{0, 0, 32, 16, 0, 0, 32, 8, 0, 24, 32, 32};
      byte var5 = 0;
      if (objectData[5] > 4) {
         var5 = 8;
      } else if (objectData[5] > 0) {
         var5 = 4;
      }

      Image var10002;
      int var10003;
      int var10004;
      int var10005;
      int var10006;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (var3 == 0) {
         if (var5 == 8) {
            var10002 = this.m_imgObj[var2];
            var10003 = var4[var5 + 0];
            var10004 = var4[var5 + 1];
            var10005 = var4[var5 + 2];
            var10006 = var4[var5 + 3];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1] - 8;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[var2];
            var10003 = var4[var5 + 0];
            var10004 = var4[var5 + 1];
            var10005 = var4[var5 + 2];
            var10006 = var4[var5 + 3];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
         }
      } else if (var3 == 1) {
         if (objectData[19] == 0) {
            if (var5 == 8) {
               var10002 = this.m_imgObj[var2];
               var10003 = var4[var5 + 0];
               var10004 = var4[var5 + 1];
               var10005 = var4[var5 + 2];
               var10006 = var4[var5 + 3];
               var10007 = rotNumTable[TRANS_ROT90];
               var10008 = objectData[2] - mapView[0] + 8;
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
            } else {
               var10002 = this.m_imgObj[var2];
               var10003 = var4[var5 + 0];
               var10004 = var4[var5 + 1];
               var10005 = var4[var5 + 2];
               var10006 = var4[var5 + 3];
               var10007 = rotNumTable[TRANS_ROT90];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
            }
         } else if (var5 == 8) {
            var10002 = this.m_imgObj[var2];
            var10003 = var4[var5 + 0];
            var10004 = var4[var5 + 1];
            var10005 = var4[var5 + 2];
            var10006 = var4[var5 + 3];
            var10007 = rotNumTable[TRANS_MIRROR_ROT270];
            var10008 = objectData[2] - mapView[0] - 8;
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[var2];
            var10003 = var4[var5 + 0];
            var10004 = var4[var5 + 1];
            var10005 = var4[var5 + 2];
            var10006 = var4[var5 + 3];
            var10007 = rotNumTable[TRANS_MIRROR_ROT270];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
         }
      } else if (var5 == 8) {
         var10002 = this.m_imgObj[var2];
         var10003 = var4[var5 + 0];
         var10004 = var4[var5 + 1];
         var10005 = var4[var5 + 2];
         var10006 = var4[var5 + 3];
         var10007 = rotNumTable[TRANS_MIRROR_ROT180];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + 8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[var2];
         var10003 = var4[var5 + 0];
         var10004 = var4[var5 + 1];
         var10005 = var4[var5 + 2];
         var10006 = var4[var5 + 3];
         var10007 = rotNumTable[TRANS_MIRROR_ROT180];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, 1 | 2);
      }

   }

   public void bgspr_nflag_draw_sakaki(int var1) {
      if (this.zoneNumber == 0) {
         int var2 = rotNumTable[TRANS_NONE];
         if (objectData[19] == 0) {
            var2 = rotNumTable[TRANS_MIRROR];
         }

         Image var10002 = this.m_imgObj[5];
         int var10008 = objectData[2] - mapView[0];
         int var10009 = objectData[3] - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 16, var2, var10008, var10009, 1 | 2);
      }
   }

   public void jyama_nflag_draw_sakaki(int var1) {
      Image var10002 = this.m_imgObj[58];
      int var10003 = objectData[2] - mapView[0];
      int var10004 = objectData[3] - mapView[1];
      Graphics var10005 = gg;
      Graphics var10006 = gg;
      this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
   }

   public void signal_nflag_draw_sakaki(int var1) {
      Image var10002 = this.m_imgObj[61];
      int var10004 = 16 * ((this.animeTimer >> 1) % 6);
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, var10004, 32, 16, var10007, var10008, var10009, 1 | 2);
   }

   public void mizu_nflag_draw_sakaki(int var1) {
      if (objectData[4] == 0) {
         this.DrawWaterMap(71, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 16);
         this.DrawWaterMap(72, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1]);
      } else if (objectData[4] == 7) {
         this.DrawWaterMap(95, TRANS_NONE, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 16);
         this.DrawWaterMap(73, TRANS_NONE + 0, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1]);
      } else if (objectData[4] == 8) {
         this.DrawWaterMap(74, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] + 8, objectData[3] - mapView[1] - 8);
         this.DrawWaterMap(75, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8);
         this.DrawWaterMap(76, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 24, objectData[3] - mapView[1] - 8);
      } else {
         Image var10002;
         int var10004;
         int var10007;
         int var10008;
         int var10009;
         Graphics var10010;
         Graphics var10011;
         if (objectData[4] == 73) {
            var10002 = this.m_imgObj[110];
            var10004 = this.animeTimer % 5 * 32;
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = this.waterH2 - 20 - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 40, 32, var10007, var10008, var10009, 1 | 2);
         } else if (objectData[4] != 9 && objectData[4] != 169) {
            if (objectData[4] <= 6) {
               this.DrawWaterMap(187, TRANS_NONE + objectData[19] * 4, objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8);
            }
         } else {
            var10002 = this.m_imgObj[110];
            var10004 = this.animeTimer % 5 * 32;
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 40, 32, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   public void awa_nflag_draw_sakaki(int var1) {
      if (this.waterH3 < objectData[3]) {
         Image var10002 = this.m_imgObj[85];
         int var10004 = 16 * (this.cpuTimer / 16 % 3);
         int var10007 = rotNumTable[TRANS_NONE];
         int var10008 = objectData[2] - mapView[0];
         int var10009 = objectData[3] - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   public void ben_nflag_draw_sakaki(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (this.animeTimer % 60 < 30) {
         var10002 = this.m_imgObj[92];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + 8;
         var10009 = objectData[3] - mapView[1] - 32;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 16, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[92];
         var10007 = rotNumTable[TRANS_NONE + 6];
         var10008 = objectData[2] - mapView[0] + 8;
         var10009 = objectData[3] - mapView[1] + 32;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 16, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[92];
         var10007 = rotNumTable[TRANS_NONE + 1];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 16, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[92];
         var10007 = rotNumTable[TRANS_NONE + 5];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   public void ben_sflag_draw_sakaki(int var1) {
      this.ben_nflag_draw_sakaki(var1);
   }

   public void resultContinue(boolean var1) {
      if (var1) {
         playercount = 3;
         scorecount = 0;
         diecount = 0;
         this.initStageStart();
         plsaveX = 0;
         plsaveY = 0;
         plsaveTime = 0;
         plsaveTime2 = 0;
         this.noTimeScore = false;
      } else {
         mode = MODE_TITLE;
         this.SetSoftFlag = true;
         this.SetSoftCount = 10;
         this.ObjImageClear();
         this.TK_TitleInit(true);
      }

   }

   public void setEnding() {
      this.selectZoneNumber = 6;
      this.selectStageNumber = 1;
      PlayerParam[0] = 978688;
      PlayerParam[1] = 32768;
      this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
      this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];

      int var1;
      int var2;
      try {
         indata = new DataInputStream(this.getClass().getResourceAsStream("/zone" + (this.zoneNumber + 1) + ".bmd"));
         MapW = worldMapData[this.zoneNumber][this.stageNumber][0].length;
         MapH = worldMapData[this.zoneNumber][this.stageNumber].length;
         tempWorldMapData = new byte[MapH][MapW];

         for(var1 = 0; var1 < MapH; ++var1) {
            for(var2 = 0; var2 < MapW; ++var2) {
               tempWorldMapData[var1][var2] = worldMapData[this.zoneNumber][this.stageNumber][var1][var2];
            }
         }

         indata.read(mapData);
         indata.close();
         this.setMapData();
      } catch (Throwable var4) {
      }

      for(var1 = 0; var1 < m_nHiScore.length; ++var1) {
         if (m_nHiScore[var1] < scorecount) {
            for(var2 = m_nHiScore.length - 1; var2 > var1; --var2) {
               m_nHiScore[var2] = m_nHiScore[var2 - 1];
               m_nDifficulty[var2] = m_nDifficulty[var2 - 1];
            }

            m_nHiScore[var1] = scorecount;
            m_nDifficulty[var1] = m_nConfigValue[0];
            this.save_hisc();
            break;
         }
      }

      this.readStageObject();
      this.objectInit(this.stageNumber);
      ObjectListNum = 0;
      this.noDataPointer = 0;

      for(var1 = 0; var1 < ObjectList.length; ++var1) {
         ObjectList[var1][24] = 0;
      }

      this.endingStep = 0;
      this.endingModeOn = true;
   }

   public String[] readStrings(String var1) {
      try {
         InputStream var5 = null;
         var5 = this.getClass().getResourceAsStream("/" + var1);
         int var3 = var5.read();
         var3 = var5.read();
         String[] var4 = new String[var3];

         for(int var2 = 0; var2 < var3; ++var2) {
            int var6 = var5.read() & 255;
            byte[] var7 = new byte[var6];
            var5.read(var7);
            var4[var2] = new String(var7);
         }

         var5.close();
         return var4;
      } catch (Throwable var8) {
         return null;
      }
   }

   private void TK_TitleFactor() {
      int var1;
      for(var1 = 0; var1 < 10; ++var1) {
         if (m_OnKeyFlag[var1]) {
            if (KeyPress[var1]) {
               KeyPress[var1] = false;
            } else {
               m_OnKeyFlag[var1] = false;
            }
         } else {
            m_OnKeyFlag[var1] = KeyPress[var1];
         }
      }

      if (m_bDraw > 0) {
         --m_bDraw;
      }

      m_nRingPattern = (byte)((m_nRingPattern + 1) % 6);
      if (m_nTitleMode == TITLE_MODE_LICENSE_SEGA) {
         if (m_nTimer == 17) {
            this.PlayMusic(26);
         }

         ++m_nTimer;
         if (m_nTimer > 75) {
            m_nTimer = 0;
            m_nTitleMode = TITLE_MODE_LICENSE_SONICTEAM;
         }
      } else if (m_nTitleMode == TITLE_MODE_LICENSE_SONICTEAM) {
         ++m_nTimer;
         if (m_nTimer > 10) {
            m_nTimer = 0;
            this.TK_TitleInit(false);
         }
      } else if (m_nTitleMode == TITLE_MODE_FIRST_SETUP) {
         if (KeyPress[4]) {
            m_nConfigValue[3] = (byte)((m_nConfigValue[3] + 1) % LANGUAGE_MAX);
            this.TK_LoadTextset();
         } else if (KeyPress[3]) {
            --m_nConfigValue[3];
            if (m_nConfigValue[3] < 0) {
               m_nConfigValue[3] = (byte)(LANGUAGE_MAX - 1);
            }

            this.TK_LoadTextset();
         } else if (KeyPress[0]) {
            this.save_conf();
            this.TK_TitleInit(false);
            this.SetSoftKey(0);
         }
      } else if (m_nTitleMode == TITLE_MODE_TITLE) {
         if (m_nPattern < 6) {
            ++m_nTimer;
            if (m_nTimer > 5) {
               ++m_nPattern;
            }
         } else {
            m_nTimer = (byte)((m_nTimer + 1) % 5);
         }

         if (KeyPress[0]) {
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            m_nSel = 0;
            this.TK_SetMarquee(7 + m_nSel);
            this.SetSoftKey(2);
         }

         if (KeyPress[6]) {
            h.doExit();
         }
      } else if (m_nTitleMode == TITLE_MODE_TITLE_MENU) {
         m_nTimer = (byte)((m_nTimer + 1) % 5);
         if (KeyPress[4]) {
            m_nSel = (byte)((m_nSel + 1) % 5);
            this.TK_SetMarquee(7 + m_nSel);
         }

         if (KeyPress[3]) {
            --m_nSel;
            if (m_nSel < 0) {
               m_nSel = 4;
            }

            this.TK_SetMarquee(7 + m_nSel);
         }

         if (KeyPress[0]) {
            if (m_nSel == 0) {
               m_nSel = 0;
               this.load_resu();
               if (this.resumeStage != 0) {
                  this.SetSoftKey(1);
                  m_nSel = 1;
                  this.TK_SetMarquee(45 + m_nSel);
                  m_nTitleMode = TITLE_MODE_TITLE_CONTINUE_MENU;
               } else {
                  this.clearKey();
                  initDisplay = true;

                  for(var1 = 0; var1 < m_imgImage.length; ++var1) {
                     m_imgImage[var1] = null;
                  }

                  playercount = 3;
                  scorecount = 0;
                  this.resumeStage = 0;
                  this.selectZoneNumber = this.zoneNumber = 0;
                  this.selectStageNumber = this.stageNumber = 0;
                  readStageObjectFlag = true;
                  this.initStageStart();
               }

               return;
            }

            if (m_nSel == 1) {
               m_nSel = 1;
               this.clearKey();
               this.selectZoneNumber = this.zoneNumber = 0;
               this.selectStageNumber = this.stageNumber = 0;
               initDisplay = true;
               mode = MODE_STAGESELECT;
               this.SetSoftFlag = true;
               this.SetSoftCount = 10;

               for(var1 = 0; var1 < m_imgImage.length; ++var1) {
                  m_imgImage[var1] = null;
               }

               return;
            }

            if (m_nSel == 2) {
               m_nSel = 0;
               m_nTitleMode = TITLE_MODE_TITLE_HOWTO;
               m_bDraw = 1;
               this.SetSoftKey(1);
            } else if (m_nSel == 3) {
               m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
               this.SetSoftKey(1);
            } else {
               m_nSel = 0;
               m_nTitleMode = TITLE_MODE_TITLE_CONFIG_MENU;
               this.TK_SetMarquee(47 + m_nSel);
               this.SetSoftKey(1);
               m_bDraw = 1;
            }
         }

         if (KeyPress[6]) {
            this.SetSoftKey(0);
            m_nTitleMode = TITLE_MODE_TITLE;
            return;
         }

         this.TK_MoveMarquee();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING) {
         if (KeyPress[6]) {
            m_nSel = 3;
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            this.SetSoftKey(2);
            this.TK_SetMarquee(7 + m_nSel);
         } else if (KeyPress[9]) {
            m_nSel = 1;
            m_nTitleMode = TITLE_MODE_TITLE_RANCKING_MENU;
         }
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_MENU) {
         if (KeyPress[6]) {
            m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
         } else if (!KeyPress[2] && !KeyPress[1]) {
            if (KeyPress[0]) {
               m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
               if (m_nSel == 0) {
                  m_nHiScore = new int[5];
                  m_nDifficulty = new int[5];
                  this.save_hisc();
                  m_nTitleMode = TITLE_MODE_TITLE_RANCKING_DEL;
               }
            }
         } else {
            m_nSel = (byte)((m_nSel + 1) % 2);
         }
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_DEL) {
         if (KeyPress[6] || KeyPress[0]) {
            m_nTitleMode = TITLE_MODE_TITLE_RANCKING;
         }
      } else if (m_nTitleMode == TITLE_MODE_TITLE_CONFIG_MENU) {
         if (!KeyPress[6] && !KeyPress[0]) {
            if (KeyPress[2]) {
               --m_nSel;
               if (m_nSel < 0) {
                  m_nSel = 3;
               }

               this.TK_SetMarquee(47 + m_nSel);
               m_bDraw = 1;
            } else if (KeyPress[1]) {
               m_nSel = (byte)((m_nSel + 1) % 4);
               this.TK_SetMarquee(47 + m_nSel);
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
                  this.TK_LoadTextset();
                  this.TK_SetMarquee(47 + m_nSel);
                  this.SetSoftLabel(1, softKeys[4]);
               }

               m_bDraw = 1;
            } else if (KeyPress[3]) {
               --m_nConfigValue[m_nSel];
               if (m_nConfigValue[m_nSel] < 0) {
                  if (m_nSel == 0) {
                     m_nConfigValue[0] = 2;
                  } else if (m_nSel == 1) {
                     m_nConfigValue[1] = 3;
                  } else if (m_nSel == 2) {
                     m_nConfigValue[2] = 1;
                  } else {
                     m_nConfigValue[3] = (byte)(LANGUAGE_MAX - 1);
                  }
               }

               if (m_nSel == 3) {
                  this.TK_LoadTextset();
                  this.TK_SetMarquee(47 + m_nSel);
                  this.SetSoftLabel(1, softKeys[4]);
               }

               m_bDraw = 1;
            }
         } else {
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            this.SetSoftKey(2);
            m_nSel = 4;
            this.TK_SetMarquee(7 + m_nSel);
            this.save_conf();
         }

         this.TK_MoveMarquee();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_CONTINUE_MENU) {
         if (KeyPress[6]) {
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            m_nSel = 0;
            this.TK_SetMarquee(7 + m_nSel);
            this.SetSoftKey(2);
         } else if (!KeyPress[2] && !KeyPress[1]) {
            if (KeyPress[0]) {
               if (m_nSel == 0) {
                  this.clearKey();
                  initDisplay = true;

                  for(var1 = 0; var1 < m_imgImage.length; ++var1) {
                     m_imgImage[var1] = null;
                  }

                  playercount = 3;
                  scorecount = 0;
                  this.resumeStage = 0;
                  this.selectZoneNumber = this.zoneNumber = 0;
                  this.selectStageNumber = this.stageNumber = 0;
                  readStageObjectFlag = true;
                  this.initStageStart();
               } else if (m_nSel == 1) {
                  this.clearKey();
                  initDisplay = true;

                  for(var1 = 0; var1 < m_imgImage.length; ++var1) {
                     m_imgImage[var1] = null;
                  }

                  playercount = this.resumeZanki;
                  scorecount = this.resumeScore;
                  this.selectZoneNumber = this.zoneNumber = this.resumeStage / 3;
                  this.selectStageNumber = this.stageNumber = this.resumeStage % 3;
                  this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
                  this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
                  readStageObjectFlag = true;
                  this.initStageStart();
               }
            }
         } else {
            m_nSel = (byte)((m_nSel + 1) % 2);
            this.TK_SetMarquee(45 + m_nSel);
         }

         this.TK_MoveMarquee();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_HOWTO) {
         if (KeyPress[4] && m_nSel < 25) {
            ++m_nSel;
            m_bDraw = 1;
         }

         if (KeyPress[3] && m_nSel > 0) {
            --m_nSel;
            m_bDraw = 1;
         }

         if (KeyPress[6]) {
            m_nSel = 2;
            m_nTitleMode = TITLE_MODE_TITLE_MENU;
            this.SetSoftKey(2);
         }
      }

      KeyPress[5] = false;
      KeyPress[6] = false;
   }

   private void TK_TitleDraw() {
      gg.setFont(m_Font);
      gg.setClip(0, 0, 240, 240);
      int var1;
      int var2;
      if (m_nTitleMode == TITLE_MODE_LICENSE_SEGA) {
         gg.setColor(16777215);
         gg.fillRect(0, 0, 240, 240);
         gg.drawImage(m_imgImage[0], 120, 120, 3);
         if (m_nTimer < 15) {
            var1 = 240 * m_nTimer / 15;
            gg.fillRect(var1 - 240 - 10, 0, 240, 240);
            gg.fillRect(var1 + 10, 0, 240, 240);
            var2 = var1 - 11;
            gg.drawLine(var2 + 2, 0, var2 + 2, 240);
            gg.drawLine(var2 + 3, 0, var2 + 3, 240);
            gg.drawLine(var2 + 5, 0, var2 + 5, 240);
            gg.drawLine(var2 + 8, 0, var2 + 8, 240);
            var2 = var1 + 10;
            gg.drawLine(var2 - 2, 0, var2 - 2, 240);
            gg.drawLine(var2 - 3, 0, var2 - 3, 240);
            gg.drawLine(var2 - 5, 0, var2 - 5, 240);
            gg.drawLine(var2 - 8, 0, var2 - 8, 240);
         }

         if (m_nTimer >= 60) {
            gg.setColor(0);
            var2 = (m_nTimer - 60) * 120 / 15;

            for(var1 = 0; var1 < var2; ++var1) {
               gg.drawLine(2 * var1, 0, 2 * var1, 240);
               gg.drawLine(239 - 2 * var1, 0, 239 - 2 * var1, 240);
            }
         }
      } else if (m_nTitleMode == TITLE_MODE_LICENSE_SONICTEAM) {
         gg.setColor(0);
         gg.fillRect(0, 0, 240, 240);
         gg.drawImage(m_imgImage[1], 120, 120, 3);
      } else if (m_nTitleMode == TITLE_MODE_FIRST_SETUP) {
         gg.setColor(0);
         gg.fillRect(0, 0, 240, 240);
         this.TK_DrawBelt(true, true);
         gg.setColor(16777215);
         byte var3 = 3;
         gg.drawString(m_strText[21 + var3], 11, 48 + var3 * 30, 20);
         gg.drawString(m_strText[m_aConfigTextOffset[var3][m_nConfigValue[var3]]], 212, 48 + var3 * 30, 24);
         gg.drawImage(m_imgImage[1], 217, 48 + var3 * 30 + 8, 20);
         var2 = m_Font.stringWidth(m_strText[m_aConfigTextOffset[var3][m_nConfigValue[var3]]]);
         gg.drawImage(m_imgImage[2], 212 - var2 - 10, 48 + var3 * 30 + 8, 20);
         this.TK_DrawStringC(m_strText[21 + var3], 120, 8, 16777215, 0);
      } else if (m_nTitleMode == TITLE_MODE_TITLE) {
         this.TK_DrawBg(false);
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
         this.TK_DrawStringC(m_strText[0], 120, 178, 16777215, 0);
         this.TK_DrawStringC(m_strText[1], 120, 204, 16777215, 0);
      } else if (m_nTitleMode == TITLE_MODE_TITLE_MENU) {
         gg.setColor(16777215);
         gg.fillRect(0, 0, 240, 240);
         this.TK_DrawBg(false);
         gg.drawRegion(m_imgImage[0], 0, 0, 190, 109, 0, 25, 56, 20);
         gg.drawRegion(m_imgImage[0], 127, 110, 35, 57, 0, 94, 54, 20);
         gg.drawRegion(m_imgImage[0], 135, 167, 26, 21, 0, 98, 67, 20);
         if (m_nTimer / 2 % 2 == 0) {
            gg.drawRegion(m_imgImage[0], 163, 167, 27, 58, 0, 129, 53, 20);
         } else {
            gg.drawRegion(m_imgImage[0], 163, 109, 27, 58, 0, 129, 53, 20);
         }

         gg.drawRegion(m_imgImage[0], 0, 224, 190, 56, 0, 25, 109, 20);
         this.TK_DrawBelt(true, true);
         gg.drawImage(m_imgImage[1], 233, 218, 20);
         gg.drawImage(m_imgImage[2], 2, 218, 20);
         this.TK_DrawStringC(m_strText[2 + m_nSel], 120, 212, 16777215, 16386570);
         this.TK_DrawMarqueeTop();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING) {
         this.TK_DrawBg(true);
         this.TK_DrawBelt(true, true);
         gg.setColor(16777215);

         for(var1 = 0; var1 < 5; ++var1) {
            gg.drawString("" + (var1 + 1), 32, 48 + 30 * var1, 24);
            gg.drawString("" + m_nHiScore[var1], 130, 48 + 30 * var1, 24);
            gg.drawString(m_strText[25 + m_nDifficulty[var1]], 170, 48 + 30 * var1, 17);
         }

         this.TK_DrawStringC(m_strText[5], 120, 8, 16777215, 0);
         this.TK_DrawStringC(m_strText[12], 120, 212, 16777215, 0);
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_MENU) {
         this.TK_DrawBg(true);
         this.TK_DrawBelt(true, true);
         gg.setColor(16777215);
         gg.drawString(m_strText[13], 120, 43, 17);
         gg.drawString(m_strText[14], 120, 69, 17);
         gg.drawString(m_strText[15], 120, 95, 17);
         gg.drawString(m_strText[16], 120, 121, 17);
         gg.drawString(m_strText[17], 120, 147, 17);
         gg.drawString(m_strText[18], 120, 173, 17);
         var1 = m_Font.stringWidth(m_strText[17 + m_nSel]);
         this.TK_DrawRing(120 - var1 / 2 - 18, 147 + m_nSel * 26);
         this.TK_DrawRing(120 + var1 / 2 + 2, 147 + m_nSel * 26);
      } else if (m_nTitleMode == TITLE_MODE_TITLE_RANCKING_DEL) {
         this.TK_DrawBg(true);
         this.TK_DrawBelt(true, true);
         gg.setColor(16777215);
         gg.drawString(m_strText[19], 120, 95, 17);
         gg.drawString(m_strText[20], 120, 122, 17);
      } else if (m_nTitleMode == TITLE_MODE_TITLE_CONFIG_MENU) {
         if (m_bDraw != 0) {
            this.TK_DrawBg(true);
            gg.setColor(16777215);

            for(var1 = 0; var1 < 4; ++var1) {
               gg.drawString(m_strText[21 + var1], 11, 48 + var1 * 30, 20);
               gg.drawString(m_strText[m_aConfigTextOffset[var1][m_nConfigValue[var1]]], 212, 48 + var1 * 30, 24);
            }

            gg.drawImage(m_imgImage[1], 217, 48 + m_nSel * 30 + 8, 20);
            var1 = m_Font.stringWidth(m_strText[m_aConfigTextOffset[m_nSel][m_nConfigValue[m_nSel]]]);
            gg.drawImage(m_imgImage[2], 212 - var1 - 10, 48 + m_nSel * 30 + 8, 20);
            gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
            gg.setColor(34, 115, 251);
            gg.fillRect(11, 5, 218, 26);
            gg.setColor(6, 66, 148);
            gg.drawLine(10, 30, 10, 4);
            gg.drawLine(10, 4, 229, 4);
            gg.setColor(129, 205, 242);
            gg.drawLine(10, 31, 229, 31);
            gg.drawLine(229, 31, 229, 5);
            this.TK_DrawStringC(m_strText[6], 120, 8, 16777215, 0);
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
         this.TK_DrawMarqueeBottom();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_CONTINUE_MENU) {
         this.TK_DrawBg(true);
         this.TK_DrawBelt(true, true);
         gg.setColor(16777215);
         gg.drawString(m_strText[42], 120, 95, 17);
         gg.drawString(m_strText[43], 120, 121, 17);
         var1 = m_Font.stringWidth(m_strText[42 + m_nSel]);
         this.TK_DrawRing(120 - var1 / 2 - 18, 95 + m_nSel * 26);
         this.TK_DrawRing(120 + var1 / 2 + 2, 95 + m_nSel * 26);
         this.TK_DrawMarqueeBottom();
      } else if (m_nTitleMode == TITLE_MODE_TITLE_HOWTO && m_bDraw != 0) {
         this.TK_DrawBg(true);
         this.TK_DrawBelt(true, false);
         if (m_HowToPicIndexTbl[m_nSel] >= 0) {
            gg.drawRegion(m_imgImage[6], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][0], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][1], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][2], m_HowToPicTbl[m_HowToPicIndexTbl[m_nSel]][3], 0, 233, 196, 40);
         }

         var2 = m_nSel * 7;
         if (m_nSel <= 0) {
            gg.drawRegion(m_imgImage[6], m_HowToPicTbl[13][0], m_HowToPicTbl[13][1], m_HowToPicTbl[13][2], m_HowToPicTbl[13][3], 0, 15, 43, 20);
            gg.drawRegion(m_imgImage[6], m_HowToPicTbl[15][0], m_HowToPicTbl[15][1], m_HowToPicTbl[15][2], m_HowToPicTbl[15][3], 0, 25, 69, 20);
            gg.drawRegion(m_imgImage[6], m_HowToPicTbl[14][0], m_HowToPicTbl[14][1], m_HowToPicTbl[14][2], m_HowToPicTbl[14][3], 0, 25, 95, 20);
            gg.setColor(16777215);
            gg.drawString(m_strHowToText[1 + var2 + 0], 65, 43, 20);
            gg.drawString(m_strHowToText[1 + var2 + 1], 65, 69, 20);
            gg.drawString(m_strHowToText[1 + var2 + 2], 65, 95, 20);
            gg.drawString(m_strHowToText[1 + var2 + 3], 15, 121, 20);
            gg.drawString(m_strHowToText[1 + var2 + 4], 15, 147, 20);

            for(var1 = 5; var1 < 6; ++var1) {
               gg.drawString(m_strHowToText[1 + var2 + var1], 120, 43 + 26 * var1, 17);
            }
         } else {
            gg.setColor(16777215);

            for(var1 = 0; var1 < 6; ++var1) {
               gg.drawString(m_strHowToText[1 + var2 + var1], 120, 43 + 26 * var1, 17);
            }

            this.TK_DrawStringC(m_strHowToText[var2], 120, 8, 16777215, 0);
         }

         this.TK_DrawStringC(m_strHowToText[var2], 120, 8, 16777215, 0);
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

   private void TK_DrawStringC(String var1, int var2, int var3, int var4, int var5) {
      gg.setColor(var5);
      gg.drawString(var1, var2 - 1, var3, 17);
      gg.drawString(var1, var2 + 1, var3, 17);
      gg.drawString(var1, var2, var3 + 1, 17);
      gg.drawString(var1, var2, var3 - 1, 17);
      gg.setColor(var4);
      gg.drawString(var1, var2, var3, 17);
   }

   private void TK_DrawBelt(boolean var1, boolean var2) {
      gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 0, 20);
      gg.drawImage(this.m_imgCmd[LOGOLINE], 0, 204, 20);
      if (var1) {
         gg.setColor(34, 115, 251);
         gg.fillRect(11, 5, 218, 26);
         gg.setColor(6, 66, 148);
         gg.drawLine(10, 30, 10, 4);
         gg.drawLine(10, 4, 229, 4);
         gg.setColor(129, 205, 242);
         gg.drawLine(10, 31, 229, 31);
         gg.drawLine(229, 31, 229, 5);
      }

      if (var2) {
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

   private void TK_DrawRing(int var1, int var2) {
      gg.drawRegion(m_imgImage[4], 0, m_nRingPattern / 2 * 16, 16, 16, 0, var1, var2, 20);
   }

   private void TK_SetMarquee(int var1) {
      m_strMarquee = m_strText[var1];
      m_nMarqueePos = 0;
   }

   private void TK_SetMarquee(String var1) {
      m_strMarquee = var1;
      m_nMarqueePos = 0;
   }

   private void TK_MoveMarquee() {
      int var1 = (m_Font.stringWidth(m_strMarquee) + 218) / 6;
      m_nMarqueePos = (m_nMarqueePos + 1) % var1;
   }

   private void TK_DrawMarqueeTop() {
      gg.setClip(12, 6, 216, 24);
      gg.setColor(255, 200, 200);
      this.MarqOfs = m_nConfigValue[3] == 1 ? -2 : 0;
      gg.drawString(m_strMarquee, 216 - m_nMarqueePos * 6, 8 + this.MarqOfs, 20);
      gg.setClip(0, 0, 240, 240);
   }

   private void TK_DrawMarqueeBottom() {
      gg.setClip(10, 208, 220, 28);
      gg.setColor(255, 200, 200);
      this.MarqOfs = m_nConfigValue[3] == 1 ? -2 : 0;
      gg.drawString(m_strMarquee, 216 - m_nMarqueePos * 6, 210 + this.MarqOfs, 20);
      gg.setClip(0, 0, 240, 240);
   }

   private void TK_DrawBg(boolean var1) {
      gg.drawImage(m_imgImage[5], 0, 0, 20);
      if (var1) {
         gg.setColor(0);

         for(int var2 = 0; var2 < 120; ++var2) {
            gg.drawLine(0, var2 * 2, 240, var2 * 2);
         }
      }

   }

   private void TK_TitleInit(boolean var1) {
      this.ObjImageClear();
      this.SetSoftKey(-1);
      this.TK_LoadTextset();
      if (var1) {
         m_imgImage[0] = this.createImage("/t_license1.png");
         m_imgImage[1] = this.createImage("/t_license2.png");
         m_nTitleMode = TITLE_MODE_LICENSE_SEGA;
      } else {
         m_imgImage[0] = this.createImage("/t_title.png");
         m_imgImage[1] = this.createImage("/t_cur1.png");
         m_imgImage[2] = this.createImage("/t_cur2.png");
         m_imgImage[4] = this.createImage("/ring.png");
         m_imgImage[5] = this.createImage("/title_bg.png");
         m_imgImage[6] = this.createImage("/t_matome.png");
         if (m_bFirstSetUp != 0) {
            m_bFirstSetUp = 0;
            m_nConfigValue[3] = 1;
            this.TK_LoadTextset();
            m_nTitleMode = TITLE_MODE_FIRST_SETUP;
         } else {
            m_nTitleMode = TITLE_MODE_TITLE;
            this.SetSoftKey(0);
            this.PlayMusic(15);
         }
      }

      m_nTimer = 0;

      for(int var2 = 0; var2 < 10; ++var2) {
         m_OnKeyFlag[var2] = false;
      }

      m_nRingPattern = 0;
   }

   private void TK_LoadTextset() {
      try {
         if (m_nConfigValue[3] == 0) {
            m_Font = Font.getFont(0, 0, 8);
         } else {
            m_Font = Font.getFont(0, 0, 16);
         }

         in = this.getClass().getResourceAsStream("/lang_" + m_nConfigValue[3] + ".txt");
         ByteArrayOutputStream var3 = new ByteArrayOutputStream(300);
         byte[] var4 = new byte[1];
         int var1 = 0;

         int var5;
         while((var5 = in.read(var4)) > 0) {
            if (var4[0] == 13) {
               in.read(var4);
               if (var1 < 51) {
                  m_strText[var1] = new String(var3.toByteArray(), "utf-8");
               } else {
                  softKeys[var1 - 51] = new String(var3.toByteArray(), "utf-8");
               }

               var3.reset();
               ++var1;
            } else {
               var3.write(var4, 0, var5);
            }
         }

         in.close();
         var3.close();
         var3 = new ByteArrayOutputStream(300);
         in = this.getClass().getResourceAsStream("/manual_" + m_nConfigValue[3] + ".txt");
         var1 = 0;

         while((var5 = in.read(var4)) > 0) {
            if (var4[0] == 13) {
               in.read(var4);
               m_strHowToText[var1] = new String(var3.toByteArray(), "utf-8");
               var3.reset();
               ++var1;
            } else {
               var3.write(var4, 0, var5);
            }
         }

         in.close();
         var3.close();
      } catch (Throwable var6) {
      }

   }

   public void SetSoftKey(int var1) {
      this.removeCommand(cmd[1]);
      this.removeCommand(cmd[0]);
      cmd[1] = null;
      cmd[0] = null;
      if (var1 == 0) {
         if (m_nConfigValue[1] == 0) {
            cmd[0] = new Command(m_strText[40], 1, 1);
         } else {
            cmd[0] = new Command(m_strText[41], 1, 1);
         }

         cmd[1] = new Command(m_strText[39], 1, 1);
         this.addCommand(cmd[0]);
         this.addCommand(cmd[1]);
      }

      if (var1 == 1) {
         cmd[0] = new Command("", 1, 1);
         cmd[1] = new Command(m_strText[38], 1, 1);
         this.addCommand(cmd[0]);
         this.addCommand(cmd[1]);
      }

      if (var1 == 2) {
         if (m_nConfigValue[1] == 0) {
            cmd[0] = new Command(m_strText[40], 1, 1);
         } else {
            cmd[0] = new Command(m_strText[41], 1, 1);
         }

         cmd[1] = new Command(m_strText[38], 1, 1);
         this.addCommand(cmd[0]);
         this.addCommand(cmd[1]);
      }

   }

   public void commandAction(Command var1, Displayable var2) {
      if (var1 == cmd[1]) {
         this.clearKey();
         KeyPress[6] = true;
      }

      if (var1 == cmd[0]) {
         this.clearKey();
         KeyPress[5] = true;
      }

   }

   private void Vibrate(int var1) {
      if (var1 == 0) {
         var1 = 100;
      }

      if (m_nConfigValue[2] == 1) {
         h.vibrate(var1);
      } else {
         h.vibrate(0);
      }

   }

   private void StopVibrate(int var1) {
      h.vibrate(0);
   }

   private void InitViewControl() {
      this.mapViewType = 0;
      this.mapViewTypeTemp = -1;
      this.mapViewCount = 0;
      this.mapViewPri = 0;

      for(int var1 = 0; var1 < 2; ++var1) {
         mapViewTarget[var1] = mapView[var1] = mapOxy[var1];
         mapOfsTarget[var1] = mapOfs[var1] = 0;
      }

   }

   private void ForceViewControl(int var1) {
      this.mapViewCount = 20;
      this.mapViewTypeTemp = var1;
   }

   private void view_yuka(int var1, int var2, int var3) {
      if (var3 == 1 || var3 == 21) {
         if (this.mapViewPri <= 1) {
            if (this.mapViewCount <= 1) {
               if (Math.abs(this.PlayerPosX() - var1) < 300 && Math.abs(this.PlayerPosY() + 40 - var2) < 120) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 10;
                  this.mapViewPri = 1;
               }

            }
         }
      }
   }

   private void view_turi(int var1, int var2, int var3) {
      if (this.mapViewPri <= 2) {
         if (this.mapViewPri != 2 || this.mapViewCount <= 1) {
            int var4 = objectData[4] == 35 ? 48 : 136;
            if (Math.abs(this.PlayerPosX() - var1) < var4 && this.PlayerPosY() + 40 > var2 && this.PlayerPosY() - 320 < var2) {
               this.mapViewCount = 20;
               this.mapViewTypeTemp = 8;
               this.mapViewPri = 2;
            }

         }
      }
   }

   private void view_fblock(int var1, int var2, int var3) {
      if (var3 == 2 || var3 == 10) {
         if (this.mapViewPri <= 3) {
            if (this.mapViewPri != 3 || this.mapViewCount <= 1) {
               if (this.mapViewCount < 2 && Math.abs(this.PlayerPosX() - var1) < 128 && Math.abs(this.PlayerPosY() - var2) < 96) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 7;
                  this.mapViewPri = 3;
               }

            }
         }
      }
   }

   private void view_dai_ride(int var1, int var2, int var3) {
      if (var3 == 2) {
         if (this.mapViewPri <= 4) {
            if (this.mapViewPri != 4 || this.mapViewCount <= 1) {
               if (Math.abs(this.PlayerPosX() - var1) < 96 && Math.abs(this.PlayerPosY() - var2) < 96) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 5;
                  this.mapViewPri = 4;
               }

            }
         }
      }
   }

   private void view_box_ride(int var1, int var2, int var3) {
      if (var3 == 1 || var3 == 2) {
         if (this.mapViewPri <= 4) {
            if (this.mapViewPri != 4 || this.mapViewCount <= 1) {
               if (Math.abs(this.PlayerPosX() - var1) < 96 && Math.abs(this.PlayerPosY() - var2) < 96) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 5;
                  this.mapViewPri = 4;
               }

            }
         }
      }
   }

   private void view_dai(int var1, int var2, int var3) {
      if (var3 == 2) {
         if (this.mapViewPri <= 5) {
            if (this.mapViewPri != 5 || this.mapViewCount <= 1) {
               if (Math.abs(this.PlayerPosX() - var1) < 96 && Math.abs(this.PlayerPosY() - var2) < 96) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 2;
                  this.mapViewPri = 5;
               }

            }
         }
      }
   }

   private void view_box(int var1, int var2, int var3) {
      if (var3 == 1 || var3 == 2) {
         if (this.mapViewPri <= 5) {
            if (this.mapViewPri != 5 || this.mapViewCount <= 1) {
               if (Math.abs(this.PlayerPosX() - var1) < 96 && Math.abs(this.PlayerPosY() - var2) < 96) {
                  this.mapViewCount = 20;
                  this.mapViewTypeTemp = 2;
                  this.mapViewPri = 5;
               }

            }
         }
      }
   }

   private void ViewControl() {
      int var1 = this.PlayerPosX();
      int var2 = this.PlayerPosY();
      if (this.mapViewCount > 0) {
         --this.mapViewCount;
         if (this.mapViewCount == 0) {
            this.mapViewTypeTemp = -1;
            this.mapViewPri = 0;
         }
      }

      if (this.mapViewTypeTemp >= 0) {
         this.mapViewType = this.mapViewTypeTemp;
      } else {
         this.mapViewPri = 0;
         if (!bossModeOn && !bossBreakOn) {
            label183:
            switch(this.zoneNumber) {
            case 0:
               this.mapViewType = 1;
               break;
            case 1:
               this.mapViewType = 0;
               break;
            case 2:
               this.mapViewType = 1;
               switch(this.stageNumber) {
               case 0:
                  if (Math.abs(4272 - var1) < 120 && Math.abs(1024 - var2) < 320) {
                     this.mapViewType = 8;
                  } else if (Math.abs(4672 - var1) < 80 && Math.abs(688 - var2) < 96) {
                     this.mapViewType = 9;
                  }
                  break label183;
               case 1:
                  if (Math.abs(2944 - var1) < 640 && Math.abs(384 - (var2 + 40)) < 384) {
                     this.mapViewType = 9;
                  }
                  break label183;
               case 2:
                  if (Math.abs(1552 - var1) < 160 && Math.abs(1120 - (var2 + 40)) < 96) {
                     this.mapViewType = 3;
                  } else if (Math.abs(5632 - var1) < 512 && Math.abs(688 - (var2 + 40)) < 96) {
                     this.mapViewType = 9;
                  }
               default:
                  break label183;
               }
            case 3:
               this.mapViewType = 1;
               break;
            case 4:
               this.mapViewType = 1;
               if (this.stageNumber == 1) {
                  if (Math.abs(var1 - 7296) < 240 && Math.abs(var2 - 640) < 80) {
                     this.mapViewType = 2;
                  } else if (Math.abs(var1 - 8464) < 240 && Math.abs(var2 - 640) < 80) {
                     this.mapViewType = 2;
                  }
               }
               break;
            case 5:
               this.mapViewType = 0;
            }
         } else if (this.zoneNumber == 5 && mapOxy[0] < 1024) {
            this.mapViewType = 1;
         } else {
            this.mapViewType = 6;
         }
      }

      switch(this.mapViewType) {
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
         } else {
            mapOfsTarget[0] = 0;
            mapOfsTarget[1] = 0;
         }
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
      default:
         mapView[0] = mapOxy[0];
         mapView[1] = mapOxy[1];
         return;
      }

      int var3 = Math.abs(mapOxy[0] - mapViewTarget[0]);
      int var4 = Math.abs(mapOxy[1] - mapViewTarget[1]);
      int[] var10000;
      if (this.mapViewType == 6) {
         if (var3 >= 4 && var3 <= 256) {
            var10000 = mapViewTarget;
            var10000[0] += mapOxy[0] > mapViewTarget[0] ? 4 : -4;
         } else {
            mapViewTarget[0] = mapOxy[0];
         }

         if (var4 >= 4 && var4 <= 256) {
            var10000 = mapViewTarget;
            var10000[1] += mapOxy[1] > mapViewTarget[1] ? 4 : -4;
         } else {
            mapViewTarget[1] = mapOxy[1];
         }
      } else {
         if (var3 >= 32 && var3 <= 256) {
            var10000 = mapViewTarget;
            var10000[0] += mapOxy[0] > mapViewTarget[0] ? 32 : -32;
         } else {
            mapViewTarget[0] = mapOxy[0];
         }

         if (var4 >= 16 && var4 <= 256) {
            if (var4 > 32) {
               mapViewTarget[1] = mapOxy[1] > mapViewTarget[1] ? mapViewTarget[1] + 32 : mapViewTarget[1] - 32;
            } else {
               var10000 = mapViewTarget;
               var10000[1] += mapOxy[1] > mapViewTarget[1] ? 16 : -16;
            }
         } else {
            mapViewTarget[1] = mapOxy[1];
         }
      }

      if (mapOfs[0] != mapOfsTarget[0]) {
         var10000 = mapOfs;
         var10000[0] += mapOfs[0] < mapOfsTarget[0] ? 1 : -1;
      }

      mapView[0] = mapViewTarget[0] + mapOfs[0];
      if (mapOfs[1] != mapOfsTarget[1]) {
         var10000 = mapOfs;
         var10000[1] += mapOfs[1] < mapOfsTarget[1] ? 1 : -1;
      }

      mapView[1] = mapViewTarget[1] + mapOfs[1];
      int var5 = 112;
      if (this.LookUpCount > 0) {
         var5 += this.LookUpCount << 1;
      }

      if (this.CrouchCount > 0) {
         var5 -= this.CrouchCount << 1;
      }

      int var6 = this.poslimit[3] - var5;
      if (mapView[1] > var6) {
         mapView[1] = var6;
      }

   }

   private void DoGc() {
      try {
         System.gc();
         Thread.sleep(200L);
      } catch (Throwable var2) {
      }

   }

   private void moveSysString() {
      if (this.goleFlag && this.golecount > 0) {
         --this.golecount;
      } else {
         int var1;
         if (this.scoreMoveFlag) {
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
               if (this.selectStageNumber == 0) {
                  ++this.selectZoneNumber;
               }

               this.resumeStage = (byte)(this.selectStageNumber + this.selectZoneNumber * 3);
               this.resumeZanki = (byte)playercount;
               this.resumeScore = scorecount;
               if (this.clearStageData < this.resumeStage) {
                  this.clearStageData = this.resumeStage;
               }

               this.save_resu();
               this.zoneNumber = encZoneNumber[this.selectZoneNumber][this.selectStageNumber];
               this.stageNumber = encStageNumber[this.selectZoneNumber][this.selectStageNumber];
               this.readStageObject();
               readStageObjectFlag = false;
               this.countClear();
               this.objectInit(this.stageNumber);
               this.initStageStart();
            } else {
               --this.scoreGetcount;
               if (this.scoreGetcount == 10) {
                  this.PlayMusic(28);
               }

               if (this.scoreGetcountMax - this.scoreGetcount > 15) {
                  for(var1 = 0; var1 < 40; ++var1) {
                     if (this.resultTime > 0) {
                        this.resultTime -= 10;
                        this.addScoreCount(10, 0);
                     }

                     if (this.resultRing > 0) {
                        this.resultRing -= 10;
                        this.addScoreCount(10, 0);
                     }
                  }
               }
            }
         }

         int[] var10000;
         if (this.goleFlag) {
            this.limitBreak = true;
            PlayerParam[12] = 0;
            var10000 = PlayerParam;
            var10000[10] += 128;
         }

         for(var1 = 0; var1 < this.SysString.length; ++var1) {
            if (this.SysString[var1][0] == 1 && this.SysCount >= this.SysString[var1][9]) {
               for(int var2 = 0; var2 < 24; ++var2) {
                  int var10002;
                  if (this.SysCenter + this.SysString[var1][8] > this.SysString[var1][2]) {
                     var10002 = this.SysString[var1][2]++;
                  } else {
                     if (this.SysCenter + this.SysString[var1][8] >= this.SysString[var1][2]) {
                        ++this.SysCount;
                        this.SysString[var1][0] = 2;
                        if (this.goleFlag) {
                           if (this.SysCount < 5) {
                              break;
                           }

                           this.DG();
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
                           } else {
                              this.scoreGetcountMax = this.scoreGetcount = this.resultRing / 240 + 30;
                           }
                           break;
                        }

                        if (this.SysCount >= 5) {
                           this.putNowLoading = true;
                           this.DG();
                           if (readStageObjectFlag) {
                              this.readStageObject();
                              readStageObjectFlag = false;
                              plsaveX = 0;
                              plsaveY = 0;
                              plsaveTime = 0;
                              plsaveTime2 = 0;
                              this.noTimeScore = false;
                              this.endStageStart();
                           } else {
                              this.objectInit(this.stageNumber);
                              this.endStageStart();
                           }

                           this.putNowLoading = false;
                        }
                        break;
                     }

                     var10002 = this.SysString[var1][2]--;
                  }
               }
            }
         }

         if (mode == MODE_FIELD && !this.goleFlag) {
            ++this.SysCount;
            if (this.SysCount >= 20) {
               if (this.SysCount < 30) {
                  var10000 = this.SysString[0];
                  var10000[2] += 48;
                  var10000 = this.SysString[1];
                  var10000[2] += 48;
                  var10000 = this.SysString[2];
                  var10000[2] += 48;
                  var10000 = this.SysString[3];
                  var10000[2] -= 48;
                  var10000 = this.SysString[4];
                  var10000[2] -= 48;
               } else {
                  this.SysString[0][0] = 0;
                  this.SysString[1][0] = 0;
                  this.SysString[2][0] = 0;
                  this.SysString[3][0] = 0;
                  this.SysString[4][0] = 0;
               }
            }
         }

      }
   }

   private void drawSysString() {
      int var1;
      for(var1 = 0; var1 < this.SysString.length; ++var1) {
         if (this.SysString[var1][0] >= 1 && (this.zoneNumber != 5 || this.stageNumber != 3 || this.SysString[var1][1] != this.ACT && this.SysString[var1][1] != this.ACT1)) {
            gg.drawRegion(this.m_imgCmd[SYSTXT], this.SysString[var1][4], this.SysString[var1][5], this.SysString[var1][6], this.SysString[var1][7], rotNumTable[TRANS_NONE], this.SysString[var1][2], this.SysString[var1][3], 20);
         }
      }

      if (this.scoreMoveFlag) {
         int[] var2 = new int[]{scorecount, this.resultTime, this.resultRing};

         for(var1 = 0; var1 < 3; ++var1) {
            int var3 = 240 - (this.scoreGetcountMax - this.scoreGetcount) * 24 + var1 * 12;
            if (var3 < 44) {
               var3 = 44;
            }

            gg.drawRegion(this.m_imgCmd[SYSTXT2], 0, var1 * 16, 84, 16, rotNumTable[TRANS_NONE], var3, 124 + 16 * var1, 20);
            this.drawNumber2(var3 + 152, 124 + 16 * var1 - 36, var2[var1]);
         }
      }

   }

   public void run() {
      long var1 = System.currentTimeMillis();

      while(true) {
         while(true) {
            try {
               if (bPauseMusic) {
                  if (this.player1 != null && this.player1.getState() == 400) {
                     try {
                        this.player1.stop();
                     } catch (Throwable var4) {
                     }
                  }
               } else if (musicRetry > 0) {
                  int var3 = musicRetry;
                  this.PlayMusic(musicRequest);
                  musicRetry = var3;
                  --musicRetry;
               } else if (this.bDoPlay) {
                  if (!this.scoreMoveFlag) {
                     switch(musicNum) {
                     case 1:
                        this.PlayMusic(2);
                     case 2:
                     case 4:
                     case 6:
                     case 8:
                     case 10:
                     case 11:
                     case 14:
                     case 15:
                     case 16:
                     case 17:
                     case 19:
                     case 20:
                     case 21:
                     case 22:
                     case 23:
                     case 26:
                     default:
                        break;
                     case 3:
                        this.PlayMusic(4);
                        break;
                     case 5:
                        this.PlayMusic(6);
                        break;
                     case 7:
                        this.PlayMusic(8);
                        break;
                     case 9:
                        this.PlayMusic(10);
                        break;
                     case 12:
                     case 13:
                     case 24:
                     case 25:
                     case 27:
                        this.PlayZoneBGML();
                        break;
                     case 18:
                        this.PlayMusic(19);
                     }
                  }

                  this.bDoPlay = false;
               }

               var1 = System.currentTimeMillis();
               Thread.sleep(50L);
            } catch (InterruptedException var5) {
            }
         }
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
      if (this.player1 != null) {
         try {
            this.player1.close();
         } catch (Throwable var3) {
         }
      }

      this.player1 = null;
      if (this.is1 != null) {
         try {
            this.is1.close();
         } catch (Throwable var2) {
         }
      }

      this.is1 = null;
   }

   private void InitSound() {
      this.ResetSound();
      Thread var1 = new Thread(this);
      var1.start();
      var1.setPriority(10);
   }

   public void playerUpdate(Player var1, String var2, Object var3) {
      switch(var1.getState()) {
      case 0:
         musicNum = -1;
      case 100:
      case 200:
      case 300:
      case 400:
      default:
         if (var2 == "endOfMedia") {
            switch(musicNum) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
            case 18:
               this.bDoPlay = true;
            case 2:
            case 4:
            case 6:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            }
         }

         if (var2 == "endOfMedia" && this.player1.getState() == 300) {
            this.bDoPlay = true;
         }

         if (var2 != "stopped" && var2 != "closed" && var2 == "endOfMedia") {
         }

         if (var2 == "volumeChanged") {
         }

      }
   }

   private boolean _playMusic(String var1, int var2) {
      boolean var3 = true;
      bPauseMusic = false;
      System.gc();

      try {
         if (this.is1 != null) {
            this.is1.close();
            this.is1 = null;
         }

         this.is1 = this.getClass().getResourceAsStream("/bgm/" + var1 + ".mmf");
         if (this.player1 != null) {
            this.player1.close();
            this.player1 = null;
         }

         this.player1 = Manager.createPlayer(this.is1, "application/x-smaf");
         this.player1.realize();
         this.player1.addPlayerListener(this);
         this.player1.setLoopCount(var2);
         this.VolumeMusic();
         this.player1.prefetch();
         this.player1.start();
         musicNum = musicRequest;
         musicRequest = -1;
         musicRetry = 0;
      } catch (MediaException var6) {
         if (this.player1 != null) {
            this.player1.close();
         }

         this.player1 = null;
         this.DoGc();
         var3 = false;
      } catch (IOException var7) {
         if (this.player1 != null) {
            this.player1.close();
         }

         this.player1 = null;
         this.DoGc();
         var3 = false;
      } catch (Throwable var8) {
         if (this.player1 != null) {
            this.player1.close();
         }

         this.player1 = null;
         this.DoGc();
         var3 = false;
      }

      if (this.is1 != null) {
         try {
            this.is1.close();
            this.is1 = null;
         } catch (Throwable var5) {
         }
      }

      return var3;
   }

   private void PlayMusic(int var1) {
      boolean var2 = false;
      if (var1 == 20) {
         bGoalMusic = true;
      }

      if (!bGoalMusic || var1 >= 12) {
         switch(var1) {
         case 1:
            var2 = this._playMusic("81_1", 1);
            break;
         case 2:
            var2 = this._playMusic("81_2", -1);
            break;
         case 3:
            var2 = this._playMusic("82_1", 1);
            break;
         case 4:
            var2 = this._playMusic("82_2", -1);
            break;
         case 5:
            var2 = this._playMusic("83_1", 1);
            break;
         case 6:
            var2 = this._playMusic("83_2", -1);
            break;
         case 7:
            var2 = this._playMusic("84_1", 1);
            break;
         case 8:
            var2 = this._playMusic("84_2", -1);
            break;
         case 9:
            var2 = this._playMusic("85_1", 1);
            break;
         case 10:
            var2 = this._playMusic("85_2", -1);
            break;
         case 11:
            var2 = this._playMusic("86", -1);
            break;
         case 12:
            var2 = this._playMusic("87", -1);
            break;
         case 13:
            var2 = this._playMusic("88", 1);
            break;
         case 14:
            var2 = this._playMusic("89", -1);
            break;
         case 15:
            var2 = this._playMusic("8a", 1);
            break;
         case 16:
            var2 = this._playMusic("8b", 1);
            break;
         case 17:
            var2 = this._playMusic("8c", -1);
            break;
         case 18:
            var2 = this._playMusic("8d_1", 1);
            break;
         case 19:
            var2 = this._playMusic("8d_2", -1);
            break;
         case 20:
            var2 = this._playMusic("8e", 1);
            break;
         case 21:
            var2 = this._playMusic("8f", 1);
            break;
         case 22:
            var2 = this._playMusic("90", 1);
            break;
         case 23:
            var2 = this._playMusic("91", 1);
            break;
         case 24:
            var2 = this._playMusic("92", -1);
            break;
         case 25:
            var2 = this._playMusic("93", 1);
            break;
         case 26:
            var2 = this._playMusic("SEGA", 1);
            break;
         case 27:
            var2 = this._playMusic("ad", 1);
            break;
         case 28:
            var2 = this._playMusic("c5", 1);
            break;
         case 29:
            var2 = this._playMusic("b2", 1);
            break;
         case 30:
            var2 = this._playMusic("88", 1);
            break;
         default:
            this.StopMusic();
            return;
         }

         if (var2) {
            musicNum = var1;
            musicRequest = -1;
            musicRetry = 0;
         } else {
            musicRequest = var1;
            musicRetry = 30;
         }

      }
   }

   private void PauseMusic() {
      try {
         this.player1.stop();
      } catch (MediaException var2) {
         var2.printStackTrace();
      }

      bPauseMusic = true;
   }

   private void RestartMusic() {
      bPauseMusic = false;
      if (this.player1 != null) {
         try {
            this.VolumeMusic();
            this.player1.start();
         } catch (MediaException var2) {
            var2.printStackTrace();
         }
      }

   }

   private void StopMusic() {
      if (this.player1 != null) {
         try {
            this.player1.close();
         } catch (Throwable var2) {
            var2.printStackTrace();
         }
      }

      this.player1 = null;
      musicNum = -1;
      musicCount = 0;
      musicRetry = 0;
      musicRequest = -1;
      bPauseMusic = false;
      this.bDoPlay = false;
   }

   private void _setMusicVol(int var1) {
      if (this.player1 != null && this.player1.getState() != 0) {
         try {
            VolumeControl var2 = (VolumeControl)this.player1.getControl("VolumeControl");
            if (var2 != null) {
               var2.setLevel(var1);
            }
         } catch (Throwable var4) {
            var4.printStackTrace();
         }
      }

   }

   private void VolumeMusic() {
      switch(m_nConfigValue[1]) {
      case 0:
         this._setMusicVol(0);
         break;
      case 1:
         this._setMusicVol(20);
         break;
      case 2:
         this._setMusicVol(36);
         break;
      case 3:
         this._setMusicVol(100);
      }

   }

   private void MuteMusic(boolean var1) {
      if (this.player1 != null && this.player1.getState() != 0) {
         try {
            VolumeControl var2 = (VolumeControl)this.player1.getControl("VolumeControl");
            if (var2 != null) {
               var2.setMute(var1);
            }

            if (!var1) {
               this.VolumeMusic();
            }
         } catch (Throwable var4) {
            var4.printStackTrace();
         }
      }

   }

   private int GetZoneBGMNum(boolean var1) {
      if (this.bressCount < 600) {
         return 24;
      } else if (mutekicount > 0) {
         return 12;
      } else if (bossModeOn) {
         return this.zoneNumber == 5 && this.stageNumber == 3 ? 18 : 17;
      } else if (var1) {
         switch(this.zoneNumber) {
         case 0:
            return 2;
         case 1:
            if (this.stageNumber == 3) {
               return 11;
            }

            return 4;
         case 2:
            return 6;
         case 3:
            return 8;
         case 4:
            return 10;
         case 5:
            if (this.stageNumber == 3) {
               return 18;
            }

            return 11;
         case 6:
            return 14;
         default:
            return 2;
         }
      } else {
         switch(this.zoneNumber) {
         case 0:
            return 1;
         case 1:
            if (this.stageNumber == 3) {
               return 11;
            }

            return 3;
         case 2:
            return 5;
         case 3:
            return 7;
         case 4:
            return 9;
         case 5:
            if (this.stageNumber == 3) {
               return 19;
            }

            return 11;
         case 6:
            return 14;
         default:
            return 2;
         }
      }
   }

   private void PlayZoneBGM() {
      this.PlayMusic(this.GetZoneBGMNum(false));
   }

   private void PlayZoneBGML() {
      this.PlayMusic(this.GetZoneBGMNum(true));
   }

   private void AraiLoadStageImage(int var1) {
      try {
         this.m_imgObj[ANIMAL] = this.createImage("/animal.png");
         switch(var1) {
         case 2:
            this.m_imgObj[_FIRE] = this.createImage("/fire.png");
            this.m_imgObj[MOGURA_SFLAG] = this.createImage("/mogura.png");
            this.m_imgObj[FISH2_SFLAG] = this.createImage("/fish2.png");
            this.m_imgObj[UNI_SFLAG] = this.createImage("/uni.png");
            break;
         case 3:
            this.m_imgObj[_FIRE] = this.createImage("/fire.png");
            this.m_imgObj[HACHI_SFLAG] = this.createImage("/hachi.png");
            this.m_imgObj[IMO_SFLAG] = this.createImage("/imo.png");
            this.m_imgObj[BAT_SFLAG] = this.createImage("/bat.png");
            break;
         case 4:
            this.m_imgObj[_FIRE] = this.createImage("/fire.png");
            this.m_imgObj[BROBO_SFLAG] = this.createImage("/brobo.png");
            this.m_imgObj[UNI_SFLAG] = this.createImage("/uni.png");
            break;
         case 5:
            this.m_imgObj[_FIRE] = this.createImage("/fire.png");
            this.m_imgObj[HACHI_SFLAG] = this.createImage("/hachi.png");
            this.m_imgObj[KANI_SFLAG] = this.createImage("/kani.png");
            this.m_imgObj[YADO_SFLAG] = this.createImage("/yado.png");
            this.m_imgObj[ARUMA_SFLAG] = this.createImage("/aruma.png");
            this.m_imgObj[BLOCK] = this.createImage("/block.png");
            break;
         case 6:
            if (this.stageNumber != 3) {
               this.m_imgObj[_FIRE] = this.createImage("/fire.png");
               this.m_imgObj[IMO_SFLAG] = this.createImage("/imo.png");
               this.m_imgObj[BROBO_SFLAG] = this.createImage("/brobo.png");
               this.m_imgObj[BUTA_SFLAG] = this.createImage("/buta.png");
            }
         case 7:
            break;
         default:
            this.m_imgObj[MUSI_SFLAG] = this.createImage("/musi.png");
            this.m_imgObj[HACHI_SFLAG] = this.createImage("/hachi.png");
            this.m_imgObj[FISH_SFLAG] = this.createImage("/fish.png");
            this.m_imgObj[KAMERE_SFLAG] = this.createImage("/kamere.png");
            this.m_imgObj[KANI_SFLAG] = this.createImage("/kani.png");
         }
      } catch (Throwable var3) {
      }

   }

   private boolean SaveRecordStore(byte[] var1, String var2) {
      RecordStore var3 = null;

      try {
         this.deleteRecordStore(var2);
         var3 = RecordStore.openRecordStore(var2, true);
         var3.addRecord(var1, 0, var1.length);
         var3.closeRecordStore();
         return true;
      } catch (Throwable var7) {
         try {
            var3.closeRecordStore();
         } catch (Throwable var6) {
         }

         return false;
      }
   }

   private void deleteRecordStore(String var1) {
      RecordStore var2 = null;

      try {
         try {
            var2 = RecordStore.openRecordStore(var1, false);
            var2.closeRecordStore();
            RecordStore.deleteRecordStore(var1);
         } catch (Throwable var7) {
            try {
               var2.closeRecordStore();
            } catch (Throwable var6) {
            }
         }

         var2.closeRecordStore();
      } catch (Throwable var8) {
         try {
            var2.closeRecordStore();
         } catch (Throwable var5) {
         }
      }

   }

   private byte[] LoadRecordStore(String var1) {
      RecordStore var2 = null;
      byte[] var3 = null;

      try {
         var2 = RecordStore.openRecordStore(var1, false);
         var3 = new byte[var2.getRecordSize(1)];
         var2.getRecord(1, var3, 0);
         var2.closeRecordStore();
      } catch (Throwable var7) {
         try {
            var2.closeRecordStore();
         } catch (Throwable var6) {
         }
      }

      return var3;
   }

   private int GetDrawRot(int var1) {
      switch(var1) {
      case 1:
         return 5;
      case 2:
         return 3;
      case 3:
         return 6;
      default:
         return 0;
      }
   }

   private boolean _CharaDefault(int[] var1) {
      int var10002;
      if (var1[14] == 0) {
         var1[5] = 0;
         var1[6] = 0;
         var1[7] = 0;
         var1[10] = 0;
         var1[11] = 0;
         var1[12] = var1[2] * 100;
         var1[13] = var1[3] * 100;
         var1[15] = -1;
         var1[16] = var1[2];
         var1[17] = var1[3];
         var10002 = var1[14]++;
      }

      if (var1[5] > 0) {
         var10002 = var1[5]--;
      }

      var10002 = var1[6]++;
      if (var1[7] > 0) {
         var10002 = var1[7]--;
      }

      return false;
   }

   private void AraiMoveStand(int[] var1) {
      this.AraiMoveStand(var1, (objectSizeTbl[var1[1]][1] >> 1) - 2);
   }

   private void AraiMoveStand(int[] var1, int var2) {
      int var3 = var1[3];
      int var4 = var3 + var2;
      if (var3 < 99999) {
         ++var3;
      }

      if (this.blockColChk_Enemy(var1[2], var4)) {
         var3 -= 2;
         int var10000 = var3 + var2;
      }

      if (var3 < 0) {
         var3 = 0;
      }

      var1[3] = var3;
   }

   private void AraiDirChangeX(int[] var1) {
      if (var1[19] == 0) {
         var1[19] = 1;
      } else {
         var1[19] = 0;
      }

   }

   private int GetEnemyFloorY(int var1, int var2, int var3) {
      int var5 = var2 + var3;
      int var4;
      if (this.blockColChk_Enemy(var1, var5)) {
         for(var4 = 0; var4 < 8; ++var4) {
            --var5;
            if (!this.blockColChk_Enemy(var1, var5)) {
               break;
            }
         }
      } else {
         for(var4 = 0; var4 < 8 && !this.blockColChk_Enemy(var1, var5 + 1); ++var4) {
            ++var5;
         }
      }

      var5 -= var3;
      if (var5 < 0) {
         var5 = 0;
      }

      return var5;
   }

   private boolean CheckSlide(int var1, int var2, int var3, int var4, int var5) {
      if (var5 == 0) {
         if (var1 - var3 - 1 < 0) {
            return true;
         }

         if (this.blockColChk_Enemy(var1 - var3 - 1, var2 - 12)) {
            return true;
         }

         if (!this.blockColChk_Enemy(var1 - var3 - 1, var2 + var4 + 10)) {
            return true;
         }
      } else {
         if (this.blockColChk_Enemy(var1 + var3 + 1, var2 - 12)) {
            return true;
         }

         if (!this.blockColChk_Enemy(var1 + var3 + 1, var2 + var4 + 10)) {
            return true;
         }
      }

      return false;
   }

   private boolean CheckSlideInverse(int var1, int var2, int var3, int var4, int var5) {
      if (var5 == 0) {
         if (var1 - var3 - 1 < 0) {
            return true;
         }

         if (this.blockColChk_Enemy(var1 - var3 - 1, var2 - 8)) {
            return true;
         }

         if (!this.blockColChk_Enemy(var1 - var3 - 1, var2 - var4 - 12)) {
            return true;
         }
      } else {
         if (this.blockColChk_Enemy(var1 + var3 + 1, var2 - 8)) {
            return true;
         }

         if (!this.blockColChk_Enemy(var1 + var3 + 1, var2 - var4 - 12)) {
            return true;
         }
      }

      return false;
   }

   boolean AraiCheckSlide(int[] var1) {
      int var2 = objectSizeTbl[var1[1]][0] >> 1;
      int var3 = objectSizeTbl[var1[1]][1] >> 1;
      return this.CheckSlide(var1[2], var1[3], var2, var3, var1[19] & 1);
   }

   boolean AraiCheckInside(int[] var1, int var2, int var3) {
      int var4 = this.PlayerPosX();
      int var5 = this.PlayerPosY() - (PlayerBall ? 16 : 20);
      if (var1[3] - var3 <= var5 && var5 <= var1[3] + var3) {
         switch(var1[19]) {
         case 0:
            if (var1[2] - var2 < var4 && var4 < var1[2]) {
               return true;
            }
            break;
         case 1:
            if (var1[2] < var4 && var4 < var1[2] + var2) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean IsFarDistance(int var1, int var2) {
      return Math.abs(var1 - this.PlayerPosX()) > 240 || Math.abs(var2 - this.PlayerPosY()) > 240;
   }

   private boolean IsDistance(int var1, int var2, int var3) {
      int var4 = var1 - this.PlayerPosX();
      int var5 = var2 - (this.PlayerPosY() - (PlayerBall ? 16 : 20));
      return var4 * var4 + var5 * var5 < var3 * var3;
   }

   private boolean DebugNearCheck(int var1, int var2) {
      return Math.abs(var1 - this.PlayerPosX()) < 160 && Math.abs(var2 - this.PlayerPosY()) < 120;
   }

   private int IsHitSonic(int var1, int var2, int var3, int var4, boolean var5) {
      if (PlayerJump && PlayerDamage) {
         return 0;
      } else {
         byte var6 = 12;
         int var7 = !PlayerBall && !PlayerCrouch ? 16 : 12;
         int var8 = this.PlayerPosX();
         int var9 = this.PlayerPosY() - var7;
         int var10 = (var3 >> 1) + var6;
         int var11 = (var4 >> 1) + var7;
         if (PlayerBall) {
            if (var8 - var10 < var1 && var1 < var8 + var10 && var9 - var11 < var2 && var2 < var9 + var11) {
               if (mutekicount > 0) {
                  return 1;
               }

               if (var5) {
                  return 1;
               }

               return 2;
            }
         } else if (var8 - var10 < var1 && var1 < var8 + var10 && var9 - var11 < var2 && var2 < var9 + var11) {
            if (mutekicount > 0) {
               return 1;
            }

            return 2;
         }

         return 0;
      }
   }

   private boolean AraiCharaHitCheck(int[] var1) {
      if (debugFlag) {
         return false;
      } else {
         short var2 = objectSizeTbl[var1[1]][0];
         short var3 = objectSizeTbl[var1[1]][1];
         int var4 = this.IsHitSonic(var1[2], var1[3], var2, var3, true);
         if (var1[1] == 71 && var4 == 1) {
            if (var1[3] - 4 > this.PlayerPosY() - 16) {
               var4 = 2;
            }
         } else if (var1[1] == 50 && var4 == 1) {
            var4 = 2;
         }

         if (var4 == 1) {
            if (PlayerParam[5] > 0) {
               PlayerParam[5] = PlayerParam[5] > 2560 ? -2560 : -PlayerParam[5];
            }

            if (comboScore == 0) {
               comboScore = 100;
            } else if (comboScore == 100) {
               comboScore = 200;
            } else if (comboScore == 200) {
               comboScore = 500;
            } else if (comboScore == 500) {
               comboScore = 1000;
            }

            this.addScoreCount(comboScore);
            this.ShotScore(var1[2], var1[3], comboScore);
            this.SetObj2(OBJ2_KEMURI, var1[2], var1[3], 0, 0, 0, 0);
            this.SetObj2(friendTbl[this.zoneNumber][this.rnd(2)], var1[2], var1[3], 0, -300, 0, 0);
            var1[0] = 0;
            return true;
         } else if (var4 == 2) {
            this.playdamageset();
            return false;
         } else {
            return false;
         }
      }
   }

   private void AraiMoveTest(int[] var1) {
      int var2 = var1[19] == 0 ? -1 : 1;
      if (!this._CharaDefault(var1)) {
         var1[15] = this.animeTimer;
         var1[2] += var2;
         this.AraiMoveStand(var1);
         if (this.AraiCheckSlide(var1) || var1[6] > 180) {
            this.AraiDirChangeX(var1);
            var1[6] = 0;
         }

      }
   }

   private void sisoo_shot_tama(int var1, int var2) {
      if (objectData[12] == 1) {
         if (var2 == 0) {
            if ((objectData[6] >> 8) - objectData[2] >= 0) {
               objectData[10] = -276;
            } else {
               objectData[10] = 276;
            }

            objectData[11] = -2072;
         } else if (var2 == 1) {
            if ((objectData[6] >> 8) - objectData[2] >= 0) {
               objectData[10] = -204;
            } else {
               objectData[10] = 204;
            }

            objectData[11] = -2800;
         } else if (var2 == 2) {
            if ((objectData[6] >> 8) - objectData[2] >= 0) {
               objectData[10] = -160;
            } else {
               objectData[10] = 160;
            }

            objectData[11] = -3584;
         }

         objectData[12] = 0;
         int[] var10000 = objectData;
         var10000[7] -= 9216;
         objectData[18] = 1;
      }

   }

   private void sleep(int var1) {
   }

   private void sisoo_nflag_move_arai(int var1) {
      byte var2 = 40;
      byte var3 = 21;
      boolean var4 = false;
      if (objectData[6] == 0 && objectData[7] == 0) {
         objectData[6] = objectData[8] + 32 << 8;
         objectData[7] = objectData[3] - var3 - 4 + 20 << 8;
         if (objectData[4] != 255) {
            objectData[12] = 1;
         }
      }

      int[] var5 = new int[]{objectData[6] >> 8, objectData[7] >> 8};
      if (objectData[4] == 255 && objectData[13] == 1) {
         if (objectData[14] > 0) {
            int var10002 = objectData[14]--;
         } else if (objectData[12] != 0) {
            if (objectData[18] == 0) {
               this.ShotBomb(objectData[6] >> 8, objectData[7] >> 8);
            } else {
               this.SetObj2(OBJ2_BAKUDAN, objectData[6] >> 8, objectData[7] >> 8, 0, 0, 0, 0);
            }

            objectData[13] = 0;
            objectData[12] = 0;
            objectData[18] = 0;
         }
      }

      int var6 = 47 - (Math.abs(this.PlayerPosX() - objectData[2]) >> 1);
      if (var6 < 0) {
         var6 = 0;
      }

      int var7 = objectData[3] + sisootbl[var6] - 16;
      if (objectData[5] == 1) {
         int var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 0, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2 + 12, var3);
         if (var8 >= 0) {
            if (Math.abs(this.PlayerPosX() - objectData[2]) < 8) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               this.sisoo_shot_tama(var1, 0);
               this.playerRaidOn(objectData[20]);
            } else if (this.PlayerPosX() > objectData[2]) {
               PlayerParam[1] = objectData[3] + sisootbl[var6] - 16 << 8;
               objectData[5] = 0;
               this.sisoo_shot_tama(var1, 0);
               this.playerRaidOn(objectData[20]);
            } else {
               PlayerParam[1] = objectData[3] + sisootbl[var6] - 16 << 8;
               objectData[5] = 2;
               this.sisoo_shot_tama(var1, 0);
               this.playerRaidOn(objectData[20]);
            }
         }

         if (raidOn && raidObjectNum == objectData[20] && var8 != 0) {
            raidOn = false;
         }
      } else if (raidOn && raidObjectNum == objectData[20]) {
         if (objectData[2] - var2 - 10 < this.PlayerPosX() && objectData[2] + var2 + 10 > this.PlayerPosX()) {
            if (Math.abs(this.PlayerPosX() - objectData[2]) < 8) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               if (objectData[5] != 1) {
                  this.sisoo_shot_tama(var1, 0);
               }

               objectData[5] = 1;
               this.playerRaidOn(objectData[20]);
            } else if (objectData[2] < this.PlayerPosX()) {
               PlayerParam[1] = objectData[3] - 16 + sisootbl[var6] << 8;
               if (objectData[5] != 0) {
                  if (PlayerParam[5] >= 2560) {
                     this.sisoo_shot_tama(var1, 2);
                  } else {
                     this.sisoo_shot_tama(var1, 1);
                  }
               }

               objectData[5] = 0;
               this.playerRaidOn(objectData[20]);
            } else {
               PlayerParam[1] = objectData[3] - 16 + sisootbl[var6] << 8;
               if (objectData[5] != 2) {
                  if (PlayerParam[5] >= 2560) {
                     this.sisoo_shot_tama(var1, 2);
                  } else {
                     this.sisoo_shot_tama(var1, 1);
                  }
               }

               objectData[5] = 2;
               this.playerRaidOn(objectData[20]);
            }
         } else {
            raidOn = false;
         }
      } else if (objectData[2] - var2 - 10 < this.PlayerPosX() && objectData[2] + var2 + 10 > this.PlayerPosX()) {
         if (Math.abs(this.PlayerPosX() - objectData[2]) < 8) {
            var7 = objectData[3] - var3;
            if (var7 > ploldpos[1] && var7 <= this.PlayerPosY()) {
               PlayerParam[1] = (var7 << 8) - 1;
               this.sisoo_shot_tama(var1, 0);
               objectData[5] = 1;
               this.playerRaidOn(objectData[20]);
            }
         } else if (objectData[2] < this.PlayerPosX()) {
            if (objectData[5] == 0) {
               var7 = objectData[3] - 16 + sisootbl[var6];
            } else if (objectData[5] == 2) {
               var7 = objectData[3] - 16 - sisootbl[var6];
            } else {
               var7 = objectData[3] - var3;
            }

            if (var7 >= ploldpos[1] && var7 <= this.PlayerPosY()) {
               PlayerParam[1] = objectData[3] - 16 + sisootbl[var6] << 8;
               if (objectData[5] != 0) {
                  if (PlayerParam[5] >= 2560) {
                     this.sisoo_shot_tama(var1, 2);
                  } else {
                     this.sisoo_shot_tama(var1, 1);
                  }
               }

               objectData[5] = 0;
               this.playerRaidOn(objectData[20]);
            }
         } else {
            if (objectData[5] == 0) {
               var7 = objectData[3] - 16 - sisootbl[var6];
            } else if (objectData[5] == 2) {
               var7 = objectData[3] - 16 + sisootbl[var6];
            } else {
               var7 = objectData[3] - var3;
            }

            if (var7 > ploldpos[1] && var7 <= this.PlayerPosY()) {
               PlayerParam[1] = objectData[3] - 16 + sisootbl[var6] << 8;
               if (objectData[5] != 2) {
                  if (PlayerParam[5] >= 2560) {
                     this.sisoo_shot_tama(var1, 2);
                  } else {
                     this.sisoo_shot_tama(var1, 1);
                  }

                  objectData[5] = 2;
               }

               this.playerRaidOn(objectData[20]);
            }
         }
      }

      if (objectData[4] != 255 || objectData[13] != 0) {
         int[] var9 = new int[2];
         if (objectData[12] == 0) {
            int[] var10000 = objectData;
            var10000[11] += gravity;
            if (objectData[2] - var2 << 8 >= objectData[6]) {
               objectData[6] = objectData[2] - var2 + 1 << 8;
               objectData[10] = 0;
            } else if (objectData[2] + var2 << 8 <= objectData[6]) {
               objectData[6] = objectData[2] + var2 - 1 << 8;
               objectData[10] = 0;
            }

            var10000 = objectData;
            var10000[6] += objectData[10];
            var10000 = objectData;
            var10000[7] += objectData[11];
         }

         var9[0] = objectData[6] >> 8;
         var9[1] = objectData[7] >> 8;
         var6 = 47 - (Math.abs(var9[0] - objectData[2]) >> 1);
         if (var6 < 0) {
            var6 = 0;
         }

         var7 = objectData[3] - sisootbl[var6] - 16;
         if (objectData[12] == 0 && objectData[11] > 0) {
            if (objectData[5] == 1) {
               if (objectData[2] - var2 < var9[0] && objectData[2] + var2 > var9[0] && objectData[3] - 16 <= var9[1]) {
                  if (var9[0] >= objectData[2]) {
                     objectData[5] = 0;
                  } else {
                     objectData[5] = 2;
                  }

                  objectData[7] = objectData[3] - var3 - 4 + 20 << 8;
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
            } else if (objectData[2] - var2 < var9[0] && objectData[2] + var2 > var9[0]) {
               if (objectData[2] < var9[0]) {
                  if (objectData[5] == 2) {
                     var7 = objectData[3] - 16 - sisootbl[var6];
                  } else {
                     var7 = objectData[3] - 16 + sisootbl[var6];
                  }

                  if (var7 <= var9[1]) {
                     objectData[7] = objectData[3] - var3 - 4 + 20 << 8;
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
                     var7 = objectData[3] - 16 - sisootbl[var6];
                  } else {
                     var7 = objectData[3] - 16 + sisootbl[var6];
                  }

                  if (var7 <= var9[1]) {
                     objectData[7] = objectData[3] - var3 - 4 + 20 << 8;
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
         }

         if (this.IsHitSonic(objectData[6] >> 8, objectData[7] >> 8, 12, 12, false) != 0) {
            if (raidOn && raidObjectNum == objectData[20]) {
               raidOn = false;
            }

            this.playdamageset();
         }

      }
   }

   private void kamere_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var5 = var2[19];
      int var6 = var5 == 0 ? -2 : 2;
      if (!this._CharaDefault(var2)) {
         int var3 = var2[2];
         int var4 = var2[3];
         int var10002;
         switch(var2[14]) {
         case 1:
            if (Math.abs(this.PlayerPosX() - var2[2]) < 50) {
               var2[5] = 30;
               var2[15] = 0;
               var10002 = var2[14]++;
            }
            break;
         case 2:
            if (this.PlayerPosX() > var2[2]) {
               var2[19] = 1;
            } else {
               var2[19] = 0;
            }

            if (var2[5] <= 0) {
               var2[5] = 48;
               var2[15] = 1;
               if (var2[4] == 1) {
                  var2[14] = 6;
               } else {
                  var10002 = var2[14]++;
               }
            }
            break;
         case 3:
            this.AraiMoveStand(var2);
            if (var2[5] <= 0) {
               var2[5] = 8;
               var2[15] = 2;
               var10002 = var2[14]++;
            }
            break;
         case 4:
            this.AraiMoveStand(var2);
            if (var2[5] <= 0) {
               var2[5] = 8;
               var2[15] = 3;
               var10002 = var2[14]++;
            }
            break;
         case 5:
            var2[2] += var6;
            this.AraiMoveStand(var2);
            var2[15] = 4 + (this.animeTimer & 1);
            break;
         case 6:
            if (var2[5] > 0) {
               break;
            }

            this.ShotObj2(7, var2[2], var2[3], var5 == 0 ? 270 : 90, 150, 0);
            var2[5] = 60;
            var10002 = var2[14]++;
         case 7:
            if (var2[5] > 0) {
               break;
            }

            var2[15] = 0;
            var2[5] = 10;
            var10002 = var2[14]++;
         case 8:
            if (var2[5] > 0) {
               break;
            }

            var10002 = var2[14]++;
         case 9:
            var2[15] = -1;
            break;
         default:
            var2[15] = -1;
            var2[14] = 1;
         }

         if (var2[14] >= 3 && var2[14] <= 7) {
            this.AraiCharaHitCheck(var2);
         }

      }
   }

   private void hachi_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var5 = var2[19];
      int var6 = var5 == 0 ? -2 : 2;
      if (!this._CharaDefault(var2)) {
         int var3 = var2[2];
         int var4 = var2[3];
         int var10002;
         switch(var2[14]) {
         case 1:
            var2[18] = 0;
            var10002 = var2[14]++;
         case 2:
            var2[18] = 0;
            if (var2[6] > 440) {
               if (var2[19] == 0) {
                  var2[19] = 1;
               } else {
                  var2[19] = 0;
               }

               var2[6] = 0;
            }

            if (this.AraiCheckInside(var2, 160, 100)) {
               var2[5] = 20;
               var2[14] = 3;
            }

            var2[2] += var6;
            var2[15] = this.animeTimer & 1;
            break;
         case 3:
            var10002 = var2[6]--;
            var2[15] = 2 + (this.animeTimer & 1);
            if (var2[5] > 0) {
               break;
            }

            var2[5] = 90;
            var10002 = var2[14]++;
         case 4:
            var10002 = var2[6]--;
            var2[15] = 4 + (this.animeTimer & 1);
            if (var2[5] == 16) {
               var2[18] = 1;
            } else if (var2[5] == 8) {
               var2[18] = 2;
            }

            if (var2[5] > 0) {
               break;
            }

            var2[18] = 0;
            this.ShotObj2(7, var2[2] + (var5 == 0 ? -18 : 18), var2[3] + 24, var5 == 0 ? 210 : 150, 150, 0);
            var2[5] = 30;
            var10002 = var2[14]++;
         case 5:
            var10002 = var2[6]--;
            if (var2[5] > 0) {
               break;
            }

            var10002 = var2[14]++;
         case 6:
            var2[2] += var6;
            var2[15] = this.animeTimer & 1;
            if (var2[6] > 480) {
               var2[14] = 2;
            }
            break;
         default:
            var2[14] = 1;
         }

         this.AraiCharaHitCheck(var2);
      }
   }

   private void musi_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var5 = var2[19];
      int var6 = var5 == 0 ? -1 : 1;
      boolean var7 = false;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 168) {
         if (!this._CharaDefault(var2)) {
            int var10002;
            switch(var2[14]) {
            case 3:
               if (var2[5] <= 0) {
                  this.AraiDirChangeX(var2);
                  var2[6] = 0;
                  var10002 = var2[14]--;
               }
               break;
            default:
               var2[14] = 1;
            case 1:
               var10002 = var2[14]++;
            case 2:
               var2[15] = (this.animeTimer >> 1) % 3;
               var2[2] += var6;
               this.AraiMoveStand(var2);
               if (this.AraiCheckSlide(var2)) {
                  var2[5] = 60;
                  var10002 = var2[14]++;
               }

               if ((this.animeTimer & 7) == 0) {
                  this.SetObj2(OBJ2_MUSI_KEMURI, var2[2] - var6 * 20, var2[3] - 2, 0, 0, 0, 0);
               }
            }

            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void imo_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 168) {
         int var10002 = var2[5]++;
         if (this.IsFarDistance(var2[2], var2[3])) {
            var2[18] = 0;
         } else {
            if (var2[18] == 3 || var2[18] == 4) {
               if (this.CheckSlide(var2[6] >> 8, var2[7] >> 8, 8, 8, var2[8] == -1 ? 0 : 1)) {
                  var2[8] = var2[8] == 1 ? -1 : 1;
               }

               if (this.CheckSlide(var2[9] >> 8, var2[10] >> 8, 8, 8, var2[11] == -1 ? 0 : 1)) {
                  var2[11] = var2[11] == 1 ? -1 : 1;
               }

               if (this.CheckSlide(var2[12] >> 8, var2[13] >> 8, 8, 8, var2[14] == -1 ? 0 : 1)) {
                  var2[14] = var2[14] == 1 ? -1 : 1;
               }

               if (this.CheckSlide(var2[15] >> 8, var2[16] >> 8, 8, 8, var2[17] == -1 ? 0 : 1)) {
                  var2[17] = var2[17] == 1 ? -1 : 1;
               }
            }

            switch(var2[18]) {
            case 3:
               var2[6] += 0 * var2[8];
               var2[9] += 32 * var2[11];
               var2[12] += 64 * var2[14];
               var2[15] += 96 * var2[17];
               if (var2[5] >= 32) {
                  var10002 = var2[18]++;
               }
               break;
            case 4:
               var2[6] += 96 * var2[8];
               var2[9] += 64 * var2[11];
               var2[12] += 32 * var2[14];
               var2[15] += 0 * var2[17];
               if (var2[5] >= 64) {
                  var10002 = var2[18]++;
               }
               break;
            case 5:
               if (var2[8] == var2[11] && var2[11] == var2[14] && var2[14] == var2[17]) {
                  var2[9] = var2[6] + (-var2[8] * 12 << 8);
                  var2[12] = var2[9] + (-var2[8] * 12 << 8);
                  var2[15] = var2[12] + (-var2[8] * 12 << 8);
               }

               var2[5] = 0;
               var2[18] = 3;
               break;
            default:
               var2[18] = 1;
            case 1:
               var2[5] = 0;
               var2[6] = var2[2] << 8;
               var2[7] = var2[3] << 8;
               var2[8] = var3;
               var2[9] = var2[6] + (-var3 * 12 << 8);
               var2[10] = var2[7];
               var2[11] = var3;
               var2[12] = var2[9] + (-var3 * 12 << 8);
               var2[13] = var2[10];
               var2[14] = var3;
               var2[15] = var2[12] + (-var3 * 12 << 8);
               var2[16] = var2[13];
               var2[17] = var3;
               var10002 = var2[18]++;
            case 2:
               var2[7] = this.GetEnemyFloorY(var2[6] >> 8, var2[7] >> 8, 8) << 8;
               var2[10] = this.GetEnemyFloorY(var2[9] >> 8, var2[10] >> 8, 8) << 8;
               var2[13] = this.GetEnemyFloorY(var2[12] >> 8, var2[13] >> 8, 8) << 8;
               var2[16] = this.GetEnemyFloorY(var2[15] >> 8, var2[16] >> 8, 8) << 8;
               if (var2[5] >= 8) {
                  var2[5] = 0;
                  var10002 = var2[18]++;
               }
            }

            var2[7] = this.GetEnemyFloorY(var2[6] >> 8, var2[7] >> 8, 8) << 8;
            var2[10] = this.GetEnemyFloorY(var2[9] >> 8, var2[10] >> 8, 8) << 8;
            var2[13] = this.GetEnemyFloorY(var2[12] >> 8, var2[13] >> 8, 8) << 8;
            var2[16] = this.GetEnemyFloorY(var2[15] >> 8, var2[16] >> 8, 8) << 8;
            if (var2[7] + 4096 < var2[10]) {
               var2[10] = var2[7];
            }

            if (Math.abs(var2[8] - var2[11]) > 3072) {
               var2[11] += var2[8] << 8;
            }

            if (var2[7] + 4096 < var2[13]) {
               var2[13] = var2[7];
            }

            if (Math.abs(var2[8] - var2[14]) > 3072) {
               var2[14] += var2[8] << 8;
            }

            if (var2[7] + 4096 < var2[16]) {
               var2[16] = var2[7];
            }

            if (Math.abs(var2[8] - var2[17]) > 3072) {
               var2[17] += var2[8] << 8;
            }

            var2[2] = var2[6] >> 8;
            var2[3] = var2[7] >> 8;
            if (!this.AraiCharaHitCheck(var2)) {
               boolean var4 = false;
               var4 = var4 || this.IsHitSonic(var2[12] >> 8, var2[13] >> 8, 22, 10, false) != 0;
               if (var4) {
                  this.ShotObj2(14, var2[2], var2[3], 30 * var3 + 360, 300, 0);
                  this.ShotObj2(14, var2[9] >> 8, var2[10] >> 8, 15 * var3 + 360, 300, 1);
                  this.ShotObj2(14, var2[12] >> 8, var2[13] >> 8, -15 * var3 + 360, 300, 1);
                  this.ShotObj2(14, var2[15] >> 8, var2[16] >> 8, -30 * var3 + 360, 300, 1);
                  var2[0] = 0;
                  this.playdamageset();
               }
            }

         }
      }
   }

   private void brobo_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 360 || Math.abs(var2[3] - this.PlayerPosY()) <= 360) {
         if (!this._CharaDefault(var2)) {
            int var10002;
            switch(var2[14]) {
            case 3:
               if (var2[5] <= 0) {
                  this.ShotObj2(OBJ2_BROBO_TAMA, var2[2], var2[3], 30, 300, 0);
                  this.ShotObj2(OBJ2_BROBO_TAMA, var2[2], var2[3], 30, 200, 0);
                  this.ShotObj2(OBJ2_BROBO_TAMA, var2[2], var2[3], 330, 300, 0);
                  this.ShotObj2(OBJ2_BROBO_TAMA, var2[2], var2[3], 330, 200, 0);
                  this.SetObj2(OBJ2_BAKUHATU, var2[2], var2[3], 0, 0, 0, 0);
                  var2[0] = 0;
               }
               break;
            default:
               var2[14] = 1;
            case 1:
               var2[5] = 121;
               var2[6] = 0;
               var2[18] = var2[19] == 2 ? 1 : 0;
               var10002 = var2[14]++;
            case 2:
               var2[5] = 121;
               var2[15] = this.animeTimer >> 3;
               if (var2[18] == 0) {
                  this.AraiMoveStand(var2, 18);
                  if ((this.cpuTimer & 15) == 0) {
                     var2[2] += var3;
                  }

                  if (this.AraiCheckSlide(var2) || var2[6] > 600) {
                     this.AraiDirChangeX(var2);
                     var2[6] = 0;
                  }
               } else {
                  if (this.blockColChk_Enemy(var2[2], var2[3] - 18)) {
                     var10002 = var2[3]++;
                  }

                  if (this.CheckSlideInverse(var2[2], var2[3], 12, 18, var2[19]) || var2[6] > 600) {
                     if (var2[19] == 0) {
                        var2[19] = 1;
                     } else {
                        var2[19] = 0;
                     }

                     var2[6] = 0;
                  }

                  if ((this.cpuTimer & 15) == 0) {
                     var2[2] += var3;
                  }
               }

               if (Math.abs(var2[2] - this.PlayerPosX()) < 100 && Math.abs(var2[3] - (this.PlayerPosY() - 12)) < 100) {
                  var10002 = var2[14]++;
               }
            }

            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void buta_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      boolean var3 = var2[19] == 0 ? true : true;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 168) {
         if (!this._CharaDefault(var2)) {
            int var10002;
            switch(var2[14]) {
            case 6:
               var2[15] = 3;
               if (var2[5] == 10) {
                  if (var2[19] == 0) {
                     this.ShotObj2(10, var2[2], var2[3], 345, 300, 0);
                  } else {
                     this.ShotObj2(10, var2[2], var2[3], 15, 300, 0);
                  }
               }

               if (var2[5] <= 0) {
                  var2[8] = 0;
                  var2[14] = 1;
               }
               break;
            default:
               var2[14] = 1;
            case 1:
               var2[8] = 0;
               var2[5] = 30;
               var10002 = var2[14]++;
            case 2:
               var2[15] = 0;
               if (var2[5] > 0) {
                  break;
               }

               var2[5] = 30;
               var10002 = var2[14]++;
            case 3:
               var2[15] = 1;
               var2[3] = var2[17] - 8;
               if (var2[5] > 0) {
                  break;
               }

               var2[5] = 30;
               var10002 = var2[14]++;
            case 4:
               var2[15] = 0;
               var2[3] = var2[17];
               if (var2[5] > 0) {
                  break;
               }

               var2[5] = 30;
               var10002 = var2[14]++;
            case 5:
               var2[15] = 2;
               if (var2[5] <= 0) {
                  var2[5] = 30;
                  if (++var2[8] > 3) {
                     var10002 = var2[14]++;
                  } else {
                     var2[14] = 2;
                  }
               }
            }

            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void kani_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 168) {
         if (!this._CharaDefault(var2)) {
            int var10002;
            switch(var2[14]) {
            case 4:
               this.AraiStand100x(var2, 16);
               if (var2[5] <= 0) {
                  var2[14] = 1;
               }
               break;
            default:
               var2[14] = 1;
            case 1:
               var2[10] = 0;
               var2[11] = 0;
               var2[5] = KaniAttackCount;
               var10002 = var2[14]++;
            case 2:
               var2[15] = (this.animeTimer >> 2) % 3;
               var2[10] = var3 * 20;
               var2[12] += var2[10];
               var2[13] += var2[11];
               this.AraiStand100x(var2, 16);
               var2[2] = var2[12] / 100;
               var2[3] = var2[13] / 100;
               if (this.AraiCheckSlide(var2) || var2[6] > 180) {
                  this.AraiDirChangeX(var2);
                  var2[6] = 0;
               }

               if (var2[5] > 0) {
                  break;
               }

               var2[15] = 1;
               var2[5] = 60;
               var10002 = var2[14]++;
            case 3:
               this.AraiStand100x(var2, 16);
               if (var2[5] < 12) {
                  var2[15] = 3;
               }

               if (var2[5] <= 0) {
                  this.ShotObj2(9, var2[2] + 16, var2[3] - 16, 15, 240, 0);
                  this.ShotObj2(9, var2[2] - 16, var2[3] - 16, 345, 240, 0);
                  var2[5] = 50;
                  var10002 = var2[14]++;
               }
            }

            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void aruma_sflag_move_arai(int var1) {
      int[] arumadata = objectData;
      if (!this._CharaDefault(arumadata)) {
         int var10002;
         switch(arumadata[14]) {
         default:
            arumadata[14] = 1;
         case 1:
            arumadata[15] = -1;
            arumadata[8] = 0;
            arumadata[9] = 0;
            arumadata[10] = 0;
            arumadata[11] = ArumaSpeedY;
            var10002 = arumadata[14]++;
         case 2:
            if (this.PlayerPosX() - ArumaStartOffsetX < arumadata[2]) {
               break;
            }

            var10002 = arumadata[14]++;
         case 3:
            arumadata[10] = ArumaSpeedX;
            arumadata[11] = ArumaSpeedY;
            arumadata[15] = 0;
            arumadata[5] = ArumaRunCount;
            var10002 = arumadata[14]++;
         case 4:
            arumadata[15] = this.animeTimer & 1;
            arumadata[10] = ArumaSpeedY;
            if (arumadata[9] == 0 && this.CheckSlide(arumadata[2], arumadata[3], 20, 20, 1)) {
               arumadata[9] = 1;
               arumadata[11] = -800;
               arumadata[5] = ArumaRunCount;
            }

            if (arumadata[5] > 0) {
               break;
            }

            arumadata[5] = ArumaRunCount;
            var10002 = arumadata[14]++;
         case 5:
            arumadata[10] = 0;
            arumadata[11] = 0;
            arumadata[15] = 1;
            arumadata[5] = 20;
            var10002 = arumadata[14]++;
         case 6:
            if (arumadata[5] > 0) {
               break;
            }

            arumadata[15] = 2;
            arumadata[5] = 20;
            var10002 = arumadata[14]++;
         case 7:
            if (arumadata[5] > 0) {
               break;
            }

            arumadata[15] = 3;
            arumadata[5] = 60;
            var10002 = arumadata[14]++;
         case 8:
            if (arumadata[5] > 0) {
               break;
            }

            arumadata[15] = 2;
            arumadata[5] = 20;
            var10002 = arumadata[14]++;
         case 9:
            if (arumadata[5] > 0) {
               break;
            }

            arumadata[15] = 1;
            arumadata[5] = 20;
            var10002 = arumadata[14]++;
         case 10:
            if (arumadata[5] <= 0) {
               arumadata[5] = ArumaRunCount;
               arumadata[14] = 3;
            }
         }

         arumadata[11] += 20;
         if (arumadata[11] > ArumaSpeedY) {
            arumadata[11] = ArumaSpeedY;
         }

         arumadata[12] += arumadata[10];
         arumadata[13] += arumadata[11];
         if (arumadata[11] > 0 && this.blockColChk_Enemy(arumadata[12] / 100, arumadata[13] / 100 + 12)) {
            arumadata[9] = 0;

            for(int var3 = 0; var3 < 4; ++var3) {
               if (this.blockColChk_Enemy(arumadata[12] / 100, arumadata[13] / 100 + 12)) {
               }

               arumadata[13] -= 100;
            }

            if (arumadata[13] < 0) {
               arumadata[13] = 0;
            }
         }

         arumadata[2] = arumadata[12] / 100;
         arumadata[3] = arumadata[13] / 100;
         if (arumadata[15] >= 0) {
            this.AraiCharaHitCheck(arumadata);
         }

      }
   }

   private void yado_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 168) {
         if (!this._CharaDefault(var2)) {
            switch(var2[14]) {
            case 2:
               break;
            default:
               var2[14] = 1;
            case 1:
               var2[10] = 0;
               var2[11] = 0;
               var2[5] = 120;
               int var10002 = var2[14]++;
            }

            var2[15] = (this.animeTimer >> 2) % 3;
            var2[10] = var3 * 25;
            var2[12] += var2[10];
            var2[13] += var2[11];
            var2[2] = var2[12] / 100;
            var2[3] = var2[13] / 100;
            this.AraiMoveStand(var2, 24);
            if (this.AraiCheckSlide(var2) || var2[6] > 240) {
               this.AraiDirChangeX(var2);
               var2[6] = 0;
            }

            if (var2[5] <= 0) {
               var2[15] = 1;
               var2[5] = 40;
            }

            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void uni_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      boolean var7 = var2[4] != 2;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 240) {
         if (!this._CharaDefault(var2)) {
            switch(var2[14]) {
            default:
               var2[14] = 1;
            case 1:
               var2[5] = 1800;
               var2[9] = 0;
               var2[10] = !var7 ? var3 * 20 : 0;
               var2[18] = 359;
               var2[11] = 0;
               var2[15] = !var7 ? 0 : 1;
               int var10002 = var2[14]++;
            case 2:
               if (var7) {
                  if ((var2[9] & 15) == 15) {
                     var2[10] = var3 * 25;
                  } else {
                     var2[10] = 0;
                  }
               }

               if (var2[5] <= 0) {
                  var2[5] = 1800;
               }

               var2[12] += var2[10];
               var2[13] += var2[11];
               var2[2] = var2[12] / 100;
               var2[3] = var2[13] / 100;
               if (var2[19] == 0) {
                  if (++var2[18] > 359) {
                     var2[18] -= 360;
                  }
               } else if (--var2[18] < 0) {
                  var2[18] += 360;
               }

               if (this.AraiCheckInside(var2, 100, 50) && var7) {
                  var2[15] = 3;
               }

               int var4;
               int var5;
               int var6;
               if ((var2[9] & 1) == 0) {
                  var6 = var2[18] % 360;
                  var4 = var2[2] + this.dSin(var6) * 16 / 100;
                  var5 = var2[3] + this.dCos(var6) * 16 / 100;
                  if (this.IsHitSonic(var4, var5, 16, 16, false) != 0) {
                     this.playdamageset();
                  }

                  if (var7 && Math.abs(180 - var6) < 4 && this.AraiCheckInside(var2, 100, 50)) {
                     var2[9] |= 1;
                     this.ShotObj2(12, var4, var5, var2[19] == 0 ? 270 : 90, 80, 0);
                  }
               }

               if ((var2[9] & 2) == 0) {
                  var6 = (var2[18] + 90) % 360;
                  var4 = var2[2] + this.dSin(var6) * 16 / 100;
                  var5 = var2[3] + this.dCos(var6) * 16 / 100;
                  if (this.IsHitSonic(var4, var5, 16, 16, false) != 0) {
                     this.playdamageset();
                  }

                  if (var7 && Math.abs(180 - var6) < 4 && this.AraiCheckInside(var2, 100, 50)) {
                     var2[9] |= 2;
                     this.ShotObj2(12, var4, var5, var2[19] == 0 ? 270 : 90, 80, 0);
                  }
               }

               if ((var2[9] & 4) == 0) {
                  var6 = (var2[18] + 180) % 360;
                  var4 = var2[2] + this.dSin(var6) * 16 / 100;
                  var5 = var2[3] + this.dCos(var6) * 16 / 100;
                  if (this.IsHitSonic(var4, var5, 16, 16, false) != 0) {
                     this.playdamageset();
                  }

                  if (var7 && Math.abs(180 - var6) < 4 && this.AraiCheckInside(var2, 100, 50)) {
                     var2[9] |= 4;
                     this.ShotObj2(12, var4, var5, var2[19] == 0 ? 270 : 90, 80, 0);
                  }
               }

               if ((var2[9] & 8) == 0) {
                  var6 = (var2[18] + 270) % 360;
                  var4 = var2[2] + this.dSin(var6) * 16 / 100;
                  var5 = var2[3] + this.dCos(var6) * 16 / 100;
                  if (this.IsHitSonic(var4, var5, 16, 16, false) != 0) {
                     this.playdamageset();
                  }

                  if (var7 && Math.abs(180 - var6) < 4 && this.AraiCheckInside(var2, 100, 50)) {
                     var2[9] |= 8;
                     this.ShotObj2(12, var4, var5, var2[19] == 0 ? 270 : 90, 80, 0);
                  }
               }

               this.AraiCharaHitCheck(var2);
            }
         }
      }
   }

   private void bat_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (!this._CharaDefault(var2)) {
         int var10002;
         switch(var2[14]) {
         case 2:
            var2[15] = 1;
            var2[3] += 2;
            if (var2[3] >= var2[9]) {
               var2[3] = var2[9];
               var10002 = var2[14]++;
            }
            break;
         case 3:
            var2[15] = batAnimTbl[this.animeTimer & 3];
            var2[2] += var3;
            if (Math.abs(var2[2] - this.PlayerPosX()) > 80) {
               var10002 = var2[14]++;
            }
            break;
         case 4:
            var2[15] = batAnimTbl[this.animeTimer & 3];
            var2[2] += var3;
            var2[3] -= 2;
            if (this.blockColChk_Enemy(var2[2], var2[3] - 16)) {
               var2[14] = 1;
            }
            break;
         default:
            var2[14] = 1;
         case 1:
            var2[15] = 0;
            if (this.PlayerPosX() > var2[2]) {
               var2[19] = 1;
            } else {
               var2[19] = 0;
            }

            if (var2[3] + 40 <= this.PlayerPosY() && var2[3] + 100 >= this.PlayerPosY() && Math.abs(var2[2] - this.PlayerPosX()) < 100) {
               var2[9] = this.PlayerPosY() - 16;
               var10002 = var2[14]++;
            }
         }

         this.AraiCharaHitCheck(var2);
      }
   }

   private boolean AraiStand100x(int[] var1, int var2) {
      int var3 = var1[13];
      int var4 = var3 / 100;
      int var5 = var4 + var2;
      int var6 = (var1[17] - 240) * 100;
      int var7 = (var1[17] + 240) * 100;
      boolean var8 = false;
      if (var3 < 9999900) {
         var3 += 100;
      }

      if (this.blockColChk_Enemy(var1[12] / 100, var5)) {
         var3 -= 200;
         var1[11] = 50;
         var8 = true;
      }

      if (var3 < 0) {
         var3 = 0;
      }

      var1[13] = var3;
      return var8;
   }

   private void mogura_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -8 : 8;
      if (Math.abs(var2[2] - this.PlayerPosX()) <= 240 || Math.abs(var2[3] - this.PlayerPosY()) <= 240) {
         if (!this._CharaDefault(var2)) {
            int var10002;
            switch(var2[14]) {
            case 1:
               if (this.PlayerPosX() > var2[2]) {
                  var2[19] = 1;
               } else {
                  var2[19] = 0;
               }

               var2[15] = 0;
               if (Math.abs(this.PlayerPosX() - var2[2]) < 100) {
                  var2[10] = var2[19] == 0 ? -50 : 50;
                  var2[11] = -300;
                  var10002 = var2[14]++;
               }
               break;
            case 2:
               var2[11] += 5;
               if (var2[11] > 0) {
                  var2[5] = 10;
                  var10002 = var2[14]++;
               }
               break;
            case 3:
               if (var2[5] > 16) {
                  var2[15] = 1;
               } else if (var2[5] <= 8 && !this.blockColChk_Enemy(var2[2] + var3, var2[3])) {
                  var2[15] = 3 + (this.animeTimer >> 1 & 1);
               } else {
                  var2[15] = 2;
               }

               if (var2[6] > 360) {
                  if (this.PlayerPosX() > var2[2]) {
                     var2[19] = 1;
                  } else {
                     var2[19] = 0;
                  }
               }

               this.AraiStand100x(var2, 16);
               var2[11] += 5;
               break;
            default:
               var2[10] = 0;
               var2[11] = 0;
               var2[15] = 0;
               var2[14] = 1;
            }

            var2[12] += var2[10];
            var2[13] += var2[11];
            var2[2] = var2[12] / 100;
            var2[3] = var2[13] / 100;
            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void fish_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      if (!this._CharaDefault(var2)) {
         var2[15] = this.animeTimer & 1;
         var2[3] = var2[17] - Math.abs(this.dCos(var2[6] % 180)) * 268 / 100;
         this.AraiCharaHitCheck(var2);
      }
   }

   private void fish2_sflag_move_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[19] == 0 ? -1 : 1;
      if (!this._CharaDefault(var2)) {
         switch(var2[14]) {
         default:
            var2[14] = 1;
         case 1:
            var2[5] = 480;
            var2[10] = var3 * 26;
            var2[11] = 0;
            int var10002 = var2[14]++;
         case 2:
            var2[10] = var3 * 26;
            var2[15] = this.animeTimer >> 1 & 3;
            if (var2[5] <= 0) {
               var2[19] = var2[19] == 0 ? 1 : 0;
               var2[5] = 480;
            }

            var2[12] += var2[10];
            var2[13] += var2[11];
            var2[2] = var2[12] / 100;
            var2[3] = var2[13] / 100;
            this.AraiCharaHitCheck(var2);
         }
      }
   }

   private void AraiDrawChara(int var1, short[][] var2, int var3) {
      if (var3 >= 0) {
         short var4 = var2[var3][0];
         short var5 = var2[var3][1];
         short var6 = var2[var3][2];
         short var7 = var2[var3][3];
         short var8 = var2[var3][4];
         byte var9;
         switch(objectData[19]) {
         case 1:
            var9 = 2;
            break;
         case 2:
            var9 = 1;
            break;
         case 3:
            var9 = 3;
            break;
         default:
            var9 = 0;
         }

         Image var10002 = this.m_imgObj[objectData[1]];
         int var10008 = objectData[2] - mapView[0];
         int var10009 = objectData[3] - mapView[1] + var8;
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var4, var5, var6, var7, var9, var10008, var10009, 1 | 2);
      }
   }

   private void AraiDrawChara100x(int var1, short[][] var2, int var3) {
      if (var3 >= 0) {
         short var4 = var2[var3][0];
         short var5 = var2[var3][1];
         short var6 = var2[var3][2];
         short var7 = var2[var3][3];
         short var8 = var2[var3][4];
         byte var9;
         switch(objectData[19]) {
         case 1:
            var9 = 2;
            break;
         case 2:
            var9 = 1;
            break;
         case 3:
            var9 = 3;
            break;
         default:
            var9 = 0;
         }

         Image var10002 = this.m_imgObj[objectData[1]];
         int var10008 = objectData[2] / 100 - mapView[0];
         int var10009 = objectData[3] / 100 - mapView[1] + var8;
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var4, var5, var6, var7, var9, var10008, var10009, 1 | 2);
      }
   }

   private void sisoo_nflag_draw_arai(int var1) {
      byte var2 = 0;
      if (objectData[4] == 255) {
         if (objectData[14] < 60) {
            if ((this.animeTimer & 1) == 0) {
               var2 = 24;
            }
         } else if (objectData[14] < 200 && (this.animeTimer >> 1 & 1) == 0) {
            var2 = 24;
         }
      }

      Image var10002;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] != 255 || objectData[13] == 1) {
         var10002 = this.m_imgObj[23];
         var10008 = (objectData[6] >> 8) - mapView[0];
         var10009 = (objectData[7] >> 8) - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var2, 80, 24, 24, 0, var10008, var10009, 1 | 2);
      }

      int var10007;
      if (objectData[5] == 1) {
         var10002 = this.m_imgObj[23];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 56, 96, 24, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[23];
         var10007 = rotNumTable[TRANS_NONE + (objectData[5] >> 1) * 4];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 96, 56, var10007, var10008, var10009, 1 | 2);
      }

      this.drawStringCenter(gg, f, "height:" + ((objectData[7] >> 8) - objectData[3]), objectData[2] - mapView[0] + 40, objectData[3] - mapView[1] + FontPos - 40, false);
   }

   private void kamere_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 6;
      this.AraiDrawChara(var1, RectTblKamere, var2);
   }

   private void hachi_sflag_draw_arai(int var1) {
      int[] var3 = objectData;
      int var4 = var3[19] == 0 ? -1 : 1;
      int var2 = var3[15] % 6;
      this.AraiDrawChara(var1, RectTblHachi, var2);
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (var3[18] == 1) {
         var10002 = this.m_imgObj[40];
         var10007 = var3[19] == 0 ? 0 : 2;
         var10008 = var3[2] - mapView[0] + var4 * 18;
         var10009 = var3[3] - mapView[1] + 28;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 184, 16, 16, var10007, var10008, var10009, 1 | 2);
      } else if (var3[18] == 2) {
         var10002 = this.m_imgObj[40];
         var10007 = var3[19] == 0 ? 0 : 2;
         var10008 = var3[2] - mapView[0] + var4 * 18;
         var10009 = var3[3] - mapView[1] + 28;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 184, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void musi_sflag_draw_arai(int var1) {
      boolean var2 = false;
      int var3 = objectData[15] % 3;
      this.AraiDrawChara(var1, RectTblMusi, var3);
   }

   private void imo_sflag_draw_arai(int var1) {
      int[] var2 = objectData;
      if (var2[18] >= 1) {
         byte var9 = 2;
         short var3 = RectTblImo[var9][0];
         short var4 = RectTblImo[var9][1];
         short var5 = RectTblImo[var9][2];
         short var6 = RectTblImo[var9][3];
         int var8 = var2[18] == 3 ? var2[5] >> 2 : 8 - (var2[5] - 30 >> 2);
         Image var10002 = this.m_imgObj[49];
         int var10007 = var2[17] == 1 ? 2 : 0;
         int var10008 = (var2[15] >> 8) - mapView[0];
         int var10009 = (var2[16] >> 8) - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[49];
         var10007 = var2[14] == 1 ? 2 : 0;
         var10008 = (var2[12] >> 8) - mapView[0];
         var10009 = (var2[13] >> 8) - mapView[1] - var8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[49];
         var10007 = var2[11] == 1 ? 2 : 0;
         var10008 = (var2[9] >> 8) - mapView[0];
         var10009 = (var2[10] >> 8) - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
         int var10 = var2[18] > 3 ? 1 : 0;
         var3 = RectTblImo[var10][0];
         var4 = RectTblImo[var10][1];
         var5 = RectTblImo[var10][2];
         var6 = RectTblImo[var10][3];
         short var7 = RectTblImo[var10][4];
         var10002 = this.m_imgObj[49];
         var10007 = var2[8] == 1 ? 2 : 0;
         var10008 = (var2[6] >> 8) - mapView[0];
         var10009 = (var2[7] >> 8) - mapView[1] + var7 - var8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
      }
   }

   private void brobo_sflag_draw_arai(int var1) {
      int[] var2 = objectData;
      int var8 = var2[15] % 5;
      short var3 = RectTblBrobo[var8][0];
      short var4 = RectTblBrobo[var8][1];
      short var5 = RectTblBrobo[var8][2];
      short var6 = RectTblBrobo[var8][3];
      int var9;
      if (var2[18] == 0) {
         var9 = var2[19] == 0 ? 0 : 2;
      } else {
         var9 = var2[19] == 0 ? 1 : 3;
      }

      Image var10002 = this.m_imgObj[var2[1]];
      int var10008 = var2[2] - mapView[0];
      int var10009 = var2[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, var9, var10008, var10009, 1 | 2);
      if (var2[5] < 119) {
         var8 = this.animeTimer & 1;
         var3 = RectTblTama[10 + var8][0];
         var4 = RectTblTama[10 + var8][1];
         var5 = RectTblTama[10 + var8][2];
         var6 = RectTblTama[10 + var8][3];
         int var7 = var2[18] == 0 ? -6 - var2[5] / 10 : 6 + var2[5] / 10;
         var10002 = this.m_imgObj[96];
         int var10007 = var2[18] == 0 ? 0 : 3;
         var10008 = var2[2] - mapView[0];
         var10009 = var2[3] - mapView[1] + var7;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void buta_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 4;
      this.AraiDrawChara(var1, RectTblButa, var2);
   }

   private void kani_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 4;
      this.AraiDrawChara(var1, RectTblKani, var2);
   }

   private void aruma_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 4;
      this.AraiDrawChara(var1, RectTblAruma, var2);
   }

   private void yado_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 3;
      this.AraiDrawChara(var1, RectTblYado, var2);
   }

   private void drawUniToge(int var1, int var2, int var3) {
      int var4 = var3 == 0 ? 6 : 7;
      short var5 = RectTblTama[var4][0];
      short var6 = RectTblTama[var4][1];
      short var7 = RectTblTama[var4][2];
      short var8 = RectTblTama[var4][3];
      Image var10002 = this.m_imgObj[96];
      int var10008 = var1 - mapView[0];
      int var10009 = var2 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var5, var6, var7, var8, 0, var10008, var10009, 1 | 2);
   }

   private void uni_sflag_draw_arai(int var1) {
      int[] var2 = objectData;
      int var5 = var2[4] == 2 ? 0 : 1;
      int var4;
      if ((var2[9] & 1) == 0) {
         var4 = var2[18] % 360;
         this.drawUniToge(var2[2] + this.dSin(var4) * 16 / 100, var2[3] + this.dCos(var4) * 16 / 100, var5);
      }

      if ((var2[9] & 2) == 0) {
         var4 = var2[18] + 90;
         this.drawUniToge(var2[2] + this.dSin(var4) * 16 / 100, var2[3] + this.dCos(var4) * 16 / 100, var5);
      }

      if ((var2[9] & 4) == 0) {
         var4 = var2[18] + 180;
         this.drawUniToge(var2[2] + this.dSin(var4) * 16 / 100, var2[3] + this.dCos(var4) * 16 / 100, var5);
      }

      if ((var2[9] & 8) == 0) {
         var4 = var2[18] + 270;
         this.drawUniToge(var2[2] + this.dSin(var4) * 16 / 100, var2[3] + this.dCos(var4) * 16 / 100, var5);
      }

      int var3 = var2[15] % 4;
      this.AraiDrawChara(var1, RectTblUni, var3);
   }

   private void bat_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 4;
      this.AraiDrawChara(var1, RectTblBat, var2);
   }

   private void ochi_nflag_draw_arai(int var1) {
   }

   private void yari_sflag_draw_arai(int var1) {
   }

   private void mogura_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 5;
      this.AraiDrawChara(var1, RectTblMogura, var2);
   }

   private void fish_sflag_draw_arai(int var1) {
      int[] var2 = objectData;
      int var3 = var2[15] % 2;
      this.AraiDrawChara(var1, RectTblFish, var3);
   }

   private void fish2_sflag_draw_arai(int var1) {
      int var2 = objectData[15] % 4;
      this.AraiDrawChara(var1, RectTblFish2, var2);
   }

   private void AddObjectData(int var1, int var2, int var3, int var4, int var5) {
      int[] var8 = new int[25];
      if (var1 >= 1) {
         var8[0] = 1;
         var8[1] = var1;
         var8[2] = var2;
         var8[3] = var3;
         var8[4] = var4;
         var8[19] = var5;
         var8[21] = 1;
         this.addObject(var8);
      }
   }

   private void InitObj2() {
      obj2Data = new int[OBJ2_MAX][20];

      for(int var1 = 0; var1 < OBJ2_MAX; ++var1) {
         obj2Data[var1][0] = 0;
      }

   }

   private void ClearObj2() {
      for(int var1 = 0; var1 < OBJ2_MAX; ++var1) {
         obj2Data[var1][0] = 0;
      }

   }

   private void SetObj2(int id, int xpos, int ypos, int var4, int var5, int var6, int var7) {
      if (id >= OBJ2_BAKUHATU) {
         for(int var9 = 0; var9 < OBJ2_MAX; ++var9) {
            int[] var8 = obj2Data[var9];
            if (var8[0] <= 0) {
               for(int var10 = 4; var10 < 20; ++var10) {
                  var8[var10] = 0;
               }

               var8[0] = 1;
               var8[1] = id;
               var8[2] = xpos * 100;
               var8[3] = ypos * 100;
               var8[8] = var7;
               var8[10] = var4;
               var8[11] = var5;
               var8[19] = var6;
               return;
            }
         }

      }
   }

   private void SetObj2Ex(int id, int xpos, int ypos, int var4, int var5, int var6, int var7, int var8, int var9) {
      if (id >= OBJ2_BAKUHATU) {
         for(int var11 = 0; var11 < OBJ2_MAX; ++var11) {
            int[] var10 = obj2Data[var11];
            if (var10[0] <= 0) {
               for(int var12 = 4; var12 < 20; ++var12) {
                  var10[var12] = 0;
               }

               var10[0] = 1;
               var10[1] = id;
               var10[2] = xpos * 100;
               var10[3] = ypos * 100;
               var10[5] = var9;
               var10[8] = var7;
               var10[9] = var8;
               var10[10] = var4;
               var10[11] = var5;
               var10[19] = var6;
               return;
            }
         }

      }
   }

   private void ShotObj2(int id, int xpos, int ypos, int var4, int var5, int var6) {
      if (id >= OBJ2_BAKUHATU) {
         for(int var8 = 0; var8 < OBJ2_MAX; ++var8) {
            int[] var7 = obj2Data[var8];
            if (var7[0] <= 0) {
               for(int var9 = 4; var9 < 20; ++var9) {
                  var7[var9] = 0;
               }

               var7[0] = 1;
               var7[1] = id;
               var7[2] = xpos * 100;
               var7[3] = ypos * 100;
               var7[8] = var6;
               var7[10] = this.dSin(var4) * var5 / 100;
               var7[11] = this.dCos(var4) * var5 / 100;
               return;
            }
         }

      }
   }

   private void ShotRing(int xpos, int ypos, int var3) {
      if (var3 > 0) {
         if (var3 > 32) {
            var3 = 32;
         }

         int var4;
         int var5;
         for(var4 = 0; var4 < (var3 > 16 ? 16 : var3); ++var4) {
            var5 = (var4 >> 1) * 2250 + 1125;
            if ((var4 & 1) == 1) {
               var5 = -var5 + 36000;
            }

            this.ShotObj2(OBJ2_RING, xpos, ypos, var5 / 100, 256, 0);
         }

         if (var3 > 16) {
            var3 -= 16;

            for(var4 = 0; var4 < (var3 > 16 ? 16 : var3); ++var4) {
               var5 = (var4 >> 1) * 2250 + 1125;
               if ((var4 & 1) == 1) {
                  var5 = -var5 + 36000;
               }

               this.ShotObj2(OBJ2_RING, xpos, ypos, var5 / 100, 128, 0);
            }
         }

      }
   }

   private void ShotAnimal(int var1, int var2, int var3) {
      int[] var7 = new int[24];
      if (var3 < 0 || var3 > 5) {
         var3 = 0;
      }

      int var4 = 0;

      int var6;
      for(var6 = 60; var4 < 24; ++var4) {
         var6 += 2 + this.rnd(18);
         var7[var4] = var6;
      }

      for(var4 = 0; var4 < 24; ++var4) {
         this.SetObj2(friendTbl[var3][this.rnd(2)], var1 + (var4 % 8 * 8 - 32), var2, 0, -350 + this.rnd(60), this.rnd(2), var6 - var7[var4]);
      }

   }

   private void ShotScore(int xpos, int ypos, int score) {
      if (score < 10) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 0);
      } else if (score < 50) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 1);
      } else if (score < 100) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 2);
      } else if (score < 200) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 3);
      } else if (score < 500) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 4);
      } else if (score < 1000) {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 5);
      } else {
         this.SetObj2(OBJ2_SCORE, xpos, ypos, 0, 0, 0, 6);
      }

   }

   private void ShotBomb(int xpos, int ypos) {
      this.ShotObj2(OBJ2_BROBO_TAMA, xpos, ypos, 30, 200, 0);
      this.ShotObj2(OBJ2_BROBO_TAMA, xpos, ypos, 30, 300, 0);
      this.ShotObj2(OBJ2_BROBO_TAMA, xpos, ypos, 330, 200, 0);
      this.ShotObj2(OBJ2_BROBO_TAMA, xpos, ypos, 330, 300, 0);
      this.SetObj2(OBJ2_BAKUHATU, xpos, ypos, 0, 0, 0, 0);
   }

   private void DebugRect(int var1, int var2, int var3, int var4, int var5) {
   }

   private void DriveObj2() {
      for(int var2 = 0; var2 < OBJ2_MAX; ++var2) {
         int[] var1 = obj2Data[var2];
         if (var1[0] > 0) {
            if (Math.abs(var1[2] / 100 - this.PlayerPosX()) <= 320 && Math.abs(var1[3] / 100 - this.PlayerPosY()) <= 260 && var1[6] <= 600) {
               this.CallObj2(var1);
               int var10002;
               if (var1[5] > 0) {
                  var10002 = var1[5]--;
               }

               var10002 = var1[6]++;
            } else {
               var1[0] = 0;
            }
         }
      }

   }

   private void CallObj2(int[] var1) {
      switch(var1[1]) {
      case OBJ2_BAKUHATU:
         this.MoveBakuhatu(var1);
         break;
      case OBJ2_KEMURI:
         this.MoveBakuhatu(var1);
         break;
      case OBJ2_BAKUDAN:
         this.MoveBakudan(var1);
         break;
      case OBJ2_RING:
         this.MoveRing(var1);
         break;
      case OBJ2_KIRA:
         this.MoveKira(var1);
         break;
      case OBJ2_SCORE:
         this.MoveScore(var1);
         break;
      case OBJ2_TAMA:
         this.MoveNormalTama(var1);
         break;
      case OBJ2_HACHI_TAMA:
         this.MoveHachiTama(var1);
         break;
      case OBJ2_KANI_TAMA:
         this.MoveKaniTama(var1);
         break;
      case OBJ2_BUTA_TAMA:
         this.MoveButaTama(var1);
         break;
      case OBJ2_UNI_TAMA:
         this.MoveUniTama(var1);
         break;
      case OBJ2_UNI2_TAMA:
         this.MoveUni2Tama(var1);
         break;
      case OBJ2_BROBO_TAMA:
         this.MoveBroboTama(var1);
         break;
      case OBJ2_IMO_TAMA:
         this.MoveImoTama(var1);
         break;
      case OBJ2_MUSI_KEMURI:
         this.MoveMusiKemuri(var1);
         break;
      case OBJ2_FIREBALL:
         this.MoveFireball(var1);
         break;
      case OBJ2_FIREBALL2:
         this.MoveFireball2(var1);
         break;
      case OBJ2_FIREBALL3:
         this.MoveFireball3(var1);
         break;
      case OBJ2_FIREBALL4:
         this.MoveFireball4(var1);
         break;
      case OBJ2_FIREBALL5:
         this.MoveFireball5(var1);
         break;
      case OBJ2_KAZARIFIRE:
         this.MoveKazarifire(var1);
         break;
      case OBJ2_DBLOCK:
         this.MoveDBlock(var1);
         break;
      case OBJ2_DBLOCK2:
         this.MoveDBlock(var1);
         break;
      case OBJ2_DBLOCK3:
         this.MoveDBlock(var1);
         break;
      case OBJ2_DBLOCK4:
         this.MoveDBlock(var1);
         break;
      case OBJ2_BRKABE_G:
         this.MoveDBlock(var1);
         break;
      case OBJ2_BOSS6_TAMA:
         this.MoveBoss6Tama(var1);
         break;
      case OBJ2_FRIC:
         this.MoveAnimal(var1);
         break;
      case OBJ2_AZARASI:
         this.MoveAnimal(var1);
         break;
      case OBJ2_NIWATORI:
         this.MoveAnimal(var1);
         break;
      case OBJ2_USAGI:
         this.MoveAnimal(var1);
         break;
      case OBJ2_PENGUIN:
         this.MoveAnimal(var1);
         break;
      case OBJ2_RISU:
         this.MoveAnimal(var1);
         break;
      case OBJ2_BUTA:
         this.MoveAnimal(var1);
         break;
      case OBJ2_DEBUG:
         this.MoveObj2Debug(var1);
      }

   }

   private void DrawObj2() {
      int var2 = 0;

      for(int var3 = 0; var3 < OBJ2_MAX; ++var3) {
         int[] var1 = obj2Data[var3];
         if (var1[0] > 0) {
            ++var2;
            switch(var1[1]) {
            case OBJ2_BAKUHATU:
               this.DrawBakuhatu(var1);
               break;
            case OBJ2_KEMURI:
               this.DrawBakuhatu(var1);
               break;
            case OBJ2_BAKUDAN:
               this.DrawBakuhatu(var1);
               break;
            case OBJ2_RING:
               this.DrawRing(var1);
               break;
            case OBJ2_KIRA:
               this.DrawKira(var1);
               break;
            case OBJ2_SCORE:
               this.DrawScore(var1);
               break;
            case OBJ2_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_HACHI_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_KANI_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_BUTA_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_UNI_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_UNI2_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_BROBO_TAMA:
               this.DrawTama(var1);
               break;
            case OBJ2_IMO_TAMA:
               this.DrawImoTama(var1);
               break;
            case OBJ2_MUSI_KEMURI:
               this.DrawMusiKemuri(var1);
               break;
            case OBJ2_FIREBALL:
               this.DrawFireball(var1);
               break;
            case OBJ2_FIREBALL2:
               this.DrawFireball(var1);
               break;
            case OBJ2_FIREBALL3:
               this.DrawFireball(var1);
               break;
            case OBJ2_FIREBALL4:
               this.DrawFireball(var1);
               break;
            case OBJ2_FIREBALL5:
               this.DrawFireball(var1);
               break;
            case OBJ2_KAZARIFIRE:
               this.DrawKazarifire(var1);
               break;
            case OBJ2_DBLOCK:
               this.DrawDBlock(var1);
               break;
            case OBJ2_DBLOCK2:
               this.DrawDBlock(var1);
               break;
            case OBJ2_DBLOCK3:
               this.DrawDBlock(var1);
               break;
            case OBJ2_DBLOCK4:
               this.DrawDBlock(var1);
               break;
            case OBJ2_BRKABE_G:
               this.DrawBrkabe(var1);
               break;
            case OBJ2_BOSS6_TAMA:
               this.DrawBoss6Tama(var1);
               break;
            case OBJ2_FRIC:
               this.DrawAnimal(var1);
               break;
            case OBJ2_AZARASI:
               this.DrawAnimal(var1);
               break;
            case OBJ2_NIWATORI:
               this.DrawAnimal(var1);
               break;
            case OBJ2_USAGI:
               this.DrawAnimal(var1);
               break;
            case OBJ2_PENGUIN:
               this.DrawAnimal(var1);
               break;
            case OBJ2_RISU:
               this.DrawAnimal(var1);
               break;
            case OBJ2_BUTA:
               this.DrawAnimal(var1);
               break;
            case OBJ2_DEBUG:
               this.DrawObj2Debug(var1);
            }
         }
      }

      if (var2 > 42) {
      }

   }

   private void MoveBakudan(int[] var1) {
      if (var1[6] > 4 && var1[6] < 20 && this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
         this.playdamageset();
      }

      if (var1[6] > 24) {
         var1[0] = 0;
      }

   }

   private void MoveBakuhatu(int[] var1) {
      if (var1[6] > 24) {
         var1[0] = 0;
      }

   }

   private void MoveRing(int[] var1) {
      var1[2] += var1[10];
      var1[3] += var1[11];
      var1[15] = this.animeTimer >> 1;
      int var2 = var1[2];
      int var3 = var1[3];
      int var4 = var1[11];
      int xpos = var1[2] / 100;
      int ypos = var1[3] / 100;
      if (this.IsHitSonic(xpos, ypos, 12, 12, false) != 0) {
         this.SetObj2(OBJ2_KIRA, xpos, ypos, 0, 0, 0, 0);
         ++ringcount;
         var1[0] = 0;
      } else {
         int var7;
         if (var4 > 0) {
            if (this.blockColChk_Enemy(xpos, ypos + 4)) {
               var7 = (blockLinkTable[this.enemyBlock] & 255) << 4;
               ypos = (ypos & -16) + (16 - Math.abs(scdtblwk[var7 + (xpos & 15)]));
               var4 = -(var4 >> 1);
            } else {
               var4 += 6;
               if (var4 > 500) {
                  var4 = 500;
               }
            }
         } else if (this.blockColChk_Enemy(xpos, ypos - 4)) {
            for(var7 = 0; var7 < 3; ++var7) {
               ++ypos;
               if (!this.blockColChk_Enemy(xpos, ypos - 4)) {
                  break;
               }
            }

            var4 = -(var4 >> 1);
         } else {
            var4 += 6;
            if (var4 > 500) {
               var4 = 500;
            }
         }

         if (ypos < 0) {
            ypos = 0;
         } else if (ypos > 99999) {
            var1[0] = 0;
         }

         var1[3] = ypos * 100;
         var1[11] = var4;
         if (var1[6] > 300) {
            var1[0] = 0;
         }

      }
   }

   private void MoveKira(int[] var1) {
      var1[15] = var1[6] / 8;
      if (var1[15] > 3) {
         var1[0] = 0;
      }

   }

   private void MoveScore(int[] var1) {
      var1[3] -= 100;
      if (var1[6] > 50) {
         var1[0] = 0;
      }

   }

   private void MoveNormalTama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 8, 8, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      var1[15] = 2 + (this.animeTimer & 1);
   }

   private void MoveHachiTama(int[] var1) {
      if (var1[6] > 10) {
         if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 10, 10, false) != 0) {
            this.playdamageset();
         }

         var1[2] += var1[10];
         var1[3] += var1[11];
      }

      var1[15] = 2 + (this.animeTimer & 1);
   }

   private void MoveKaniTama(int[] var1) {
      if (var1[6] > 5) {
         if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 10, 10, false) != 0) {
            this.playdamageset();
         }

         var1[2] += var1[10];
         var1[3] += var1[11];
         var1[11] += 10;
         if (var1[11] > 600) {
            var1[11] = 600;
         }
      }

      var1[15] = 4 + (this.animeTimer & 1);
   }

   private void MoveButaTama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
         this.playdamageset();
      }

      int var2 = var1[2];
      int var3 = var1[3];
      int var4 = var1[10];
      int var5 = var1[11];
      var5 += 10;
      if (var5 > 300) {
         var5 = 300;
      }

      var2 += var4;
      var3 += var5;
      if (var5 > 0 && this.blockColChk_Enemy(var2 / 100, var3 / 100 + 5)) {
         int var6;
         do {
            var6 = this.getPlayerArg(var2 / 100, var3 / 100 + 5);
            var3 -= 100;
         } while(this.blockColChk_Enemy(var2 / 100, var3 / 100 + 5));

         if (var6 > 270) {
            var4 = -Math.abs(var4);
         } else if (var6 > 15) {
            var4 = Math.abs(var4);
         }

         var5 = -var5;
      }

      if (var3 < 0) {
         var3 = 0;
      } else if (var3 > 999900) {
         var1[0] = 0;
      }

      var1[15] = 8 + (this.animeTimer & 1);
      var1[2] = var2;
      var1[3] = var3;
      var1[10] = var4;
      var1[11] = var5;
      if (var1[6] > 300) {
         this.SetObj2(1, var1[2] / 100, var1[3] / 100, 0, 0, 0, 0);
         var1[0] = 0;
      }

   }

   private void MoveBroboTama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 8, 8, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      var1[11] += 10;
      if (var1[11] > 800) {
         var1[11] = 800;
      }

      var1[15] = 12 + (this.animeTimer & 1);
   }

   private void MoveUniTama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
         this.playdamageset();
      }

      var1[15] = 6;
   }

   private void MoveUni2Tama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      var1[15] = 7;
   }

   private void MoveImoTama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
         this.playdamageset();
      }

      var1[15] = 11;
      int var2 = var1[2];
      int var3 = var1[3];
      int var4 = var1[10];
      int var5 = var1[11];
      var5 += 10;
      if (var5 > 400) {
         var5 = 400;
      }

      var2 += var4;
      var3 += var5;
      if (var5 > 0 && this.blockColChk_Enemy(var2 / 100, var3 / 100 + 4)) {
         do {
            var3 -= 100;
         } while(this.blockColChk_Enemy(var2 / 100, var3 / 100 + 4));

         var5 = -var5;
      }

      if (var3 < 0) {
         var3 = 0;
      } else if (var3 > 999900) {
         var1[0] = 0;
      }

      var1[2] = var2;
      var1[3] = var3;
      var1[10] = var4;
      var1[11] = var5;
      if (var1[6] > 180) {
         var1[0] = 0;
      }

   }

   private void MoveMusiKemuri(int[] var1) {
      if (var1[6] > 30) {
         var1[0] = 0;
      }

   }

   private void MoveFireball(int[] var1) {
      switch(var1[14]) {
      case 2:
         var1[15] = 4;
         var1[5] = 8;
         int var10002 = var1[14]++;
      case 3:
         if (var1[5] > 0) {
            return;
         }

         var1[0] = 0;
         return;
      default:
         if (var1[6] > 4) {
            int var2 = var1[2] / 100;
            int var3 = var1[3] / 100;
            if (var1[10] > 0) {
               var2 += 5;
            } else if (var1[10] < 0) {
               var2 -= 5;
            }

            if (var1[11] > 0) {
               var3 += 5;
            } else if (var1[11] < 0) {
               var3 -= 5;
            }

            if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0) {
               this.playdamageset();
            }

            var1[2] += var1[10];
            var1[3] += var1[11];
            if (var1[8] == 1) {
               var1[11] += 6;
               if (var1[11] > 1000) {
                  var1[11] = 1000;
               }
            }
         }

         if (var1[10] > 0) {
            var1[18] = 3;
            if (this.blockColChk_Enemy(var1[2] / 100 + 15, var1[3] / 100)) {
               var1[14] = 2;
            }
         } else if (var1[10] < 0) {
            var1[18] = 1;
            if (this.blockColChk_Enemy(var1[2] / 100 - 15, var1[3] / 100)) {
               var1[14] = 2;
            }
         } else if (var1[11] < 0) {
            var1[18] = 2;
         } else if (var1[11] > 0) {
            var1[18] = 0;
            if (this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 16)) {
               var1[14] = 2;
            }
         }

      }
   }

   private void MoveFireball2(int[] var1) {
      int var2 = var1[2] / 100;
      int var3 = var1[3] / 100 + 6;
      if (this.IsHitSonic(var2, var3, 10, 10, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      switch(var1[14]) {
      default:
         var1[10] = 0;
         var1[11] = 20;
         var1[18] = 0;
         var1[19] = 0;
         var1[14] = 1;
      case 1:
         var1[11] += 4;
         if (var1[11] > 300) {
            var1[11] = 300;
         }

         if (!this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 14)) {
            break;
         }

         this.SetObj2(OBJ2_FIREBALL3, var1[2] / 100, var1[3] / 100, 0, 0, 0, 0);
         this.SetObj2(OBJ2_FIREBALL3, var1[2] / 100, var1[3] / 100, 0, 0, 1, 0);
         var1[11] = 0;
         var1[5] = 13;
         int var10002 = var1[14]++;
      case 2:
         var1[15] = 4;
         if (var1[5] <= 0) {
            var1[0] = 0;
         }
      }

   }

   private void MoveFireball3(int[] var1) {
      int var2 = var1[2] / 100;
      int var3 = var1[3] / 100 + 6;
      if (this.IsHitSonic(var2, var3, 12, 12, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      switch(var1[14]) {
      default:
         var1[10] = var1[19] == 1 ? 64 : -64;
         var1[11] = 100;
         var1[5] = 100;
         switch(var1[8]) {
         case 1:
            var1[5] = 140;
            var1[9] = 8;
            break;
         default:
            var1[5] = 100;
            var1[9] = 5;
         }

         var1[18] = 0;
         var1[15] = 0;
         var1[14] = 1;
      case 1:
         if (this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 14)) {
            var1[3] -= 200;
         }

         int var10002;
         if (var1[6] % 24 == 0 && var1[9] > 0) {
            var10002 = var1[9]--;
            if (var1[8] == 0) {
               this.SetObj2(OBJ2_FIREBALL4, var1[2] / 100, var1[3] / 100, 0, 0, 0, 0);
            } else if (var1[8] == 1) {
               this.SetObj2(OBJ2_FIREBALL4, var1[2] / 100, var1[3] / 100, 0, 0, 0, 999);
            }
         }

         if (var1[5] > 0) {
            break;
         }

         var1[5] = 12;
         var10002 = var1[14]++;
      case 2:
         var1[15] = 4;
         if (var1[5] <= 0) {
            var1[0] = 0;
         }
      }

   }

   private void MoveFireball4(int[] var1) {
      int var2 = var1[2] / 100;
      int var3 = var1[3] / 100 + 6;
      if (this.IsHitSonic(var2, var3, 12, 12, false) != 0) {
         this.playdamageset();
      }

      switch(var1[14]) {
      default:
         var1[14] = 1;
      case 1:
         if (var1[8] == 0) {
            var1[8] = 100;
         }

         var1[5] = 0;
         var1[18] = 0;
         var1[15] = 0;
         var1[14] = 1;
      case 2:
         if (var1[8] > 998 || var1[6] < var1[8]) {
            break;
         }

         var1[15] = 4;
         var1[5] = 8;
         int var10002 = var1[14]++;
      case 3:
         if (var1[5] <= 0) {
            var1[0] = 0;
         }
      }

   }

   private void MoveFireball5(int[] var1) {
      int var3 = var1[2] / 100;
      int var4 = var1[3] / 100 + (var1[18] == 0 ? 6 : -6);
      if (this.IsHitSonic(var3, var4, 10, 12, false) != 0) {
         this.playdamageset();
      }

      switch(var1[14]) {
      default:
         var1[14] = 1;
      case 1:
         if (var1[8] == 0) {
            var1[8] = 64;
         }

         var1[5] = 0;
         var1[9] = 0;
         var1[18] = 0;
         var1[15] = 0;
         var1[17] = var1[3];
         int var10002 = var1[14]++;
      case 2:
         var1[9] += 140;
         var1[15] = this.animeTimer & 1;
         int var2 = var1[9] / 100 % 180;
         var1[3] = var1[17] - Math.abs(this.dSin(var2)) * var1[8];
         if (var2 < 90) {
            var1[18] = 2;
         } else {
            var1[18] = 0;
         }

         if (var1[9] >= 18000) {
            var1[0] = 0;
         }

      }
   }

   private void MoveKazarifire(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100 + 2, 10, 10, false) != 0) {
         this.playdamageset();
      }

      var1[2] += var1[10];
      var1[3] += var1[11];
      if (this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100)) {
         var1[0] = 0;
      }

   }

   private void MoveDBlock(int[] var1) {
      var1[15] = var1[8];
      var1[2] += var1[10];
      var1[3] += var1[11];
      var1[11] += 20;
      if (var1[11] > 800) {
         var1[11] = 800;
      }

   }

   private void MoveBoss6Tama(int[] var1) {
      if (this.IsHitSonic(var1[2] / 100, var1[3] / 100, 12, 12, false) != 0 && var1[14] == 4) {
         this.playdamageset();
      }

      if (var1[14] <= 2) {
         var1[15] = Boss6TamaAnmTbl[this.animeTimer & 3];
      } else if (var1[14] <= 3) {
         var1[15] = Boss6TamaAnmTbl2[this.animeTimer % 20];
      } else {
         var1[15] = Boss6TamaAnmTbl3[this.animeTimer & 3];
      }

      int var10002;
      switch(var1[14]) {
      default:
         var1[14] = 1;
      case 1:
         var1[10] = (var1[8] - var1[2]) / 20;
         var1[11] = 0;
         var1[15] = 1;
         var1[5] = 30;
         var10002 = var1[14]++;
      case 2:
         if (var1[2] > var1[8]) {
            var1[2] += var1[10];
            var1[3] += var1[11];
         }

         if (var1[5] > 0) {
            break;
         }

         var1[2] = var1[8];
         var1[5] = 90;
         var10002 = var1[14]++;
      case 3:
         if (var1[5] > 0) {
            break;
         }

         int var2 = this.PlayerPosX() * 100;
         char var3 = 39200;
         var1[10] = (var2 - var1[2]) / 256;
         var1[11] = (var3 - var1[3]) / 256;
         var1[5] = 150;
         var10002 = var1[14]++;
      case 4:
         var1[2] += var1[10];
         var1[3] += var1[11];
         if (var1[5] <= 0) {
            var1[0] = 0;
         }
      }

   }

   private void DrawBakuhatu(int[] var1) {
      int var2 = var1[6] / 4 % 5;
      short var3;
      short var4;
      short var5;
      short var6;
      if (var1[1] == 2) {
         var3 = RectTblKemuri[var2][0];
         var4 = RectTblKemuri[var2][1];
         var5 = RectTblKemuri[var2][2];
         var6 = RectTblKemuri[var2][3];
      } else {
         var3 = RectTblBakuhatu[var2][0];
         var4 = RectTblBakuhatu[var2][1];
         var5 = RectTblBakuhatu[var2][2];
         var6 = RectTblBakuhatu[var2][3];
      }

      Image var10002 = this.m_imgObj[97];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, 0, var10008, var10009, 1 | 2);
   }

   private void DrawRing(int[] var1) {
      int[] var2 = new int[]{0, 0, 0, 2};
      int[] var3 = new int[]{0, 16, 32, 16};
      Image var10002 = this.m_imgObj[0];
      int var10004 = var3[this.animeTimer & 3];
      int var10007 = var2[this.animeTimer & 3];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, var10004, 16, 16, var10007, var10008, var10009, 1 | 2);
   }

   private void DrawKira(int[] var1) {
      if (var1[15] >= 0 && var1[15] <= 3) {
         Image var10002 = this.m_imgObj[0];
         int var10004 = var1[15] * 16;
         int var10008 = var1[2] / 100 - mapView[0];
         int var10009 = var1[3] / 100 - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 16, var10004, 16, 16, 0, var10008, var10009, 1 | 2);
      }
   }

   private void DrawScore(int[] var1) {
      int var2 = var1[8];
      if (var2 >= 0) {
         if (var2 > 6) {
            var2 = 6;
         }

         byte var3;
         byte var4;
         byte var5;
         switch(var2) {
         case 1:
            var3 = 3;
            var4 = 0;
            var5 = 10;
            break;
         case 2:
            var3 = 3;
            var4 = 16;
            var5 = 10;
            break;
         case 3:
            var3 = 3;
            var4 = 0;
            var5 = 15;
            break;
         case 4:
            var3 = 3;
            var4 = 8;
            var5 = 15;
            break;
         case 5:
            var3 = 3;
            var4 = 16;
            var5 = 15;
            break;
         case 6:
            var3 = 3;
            var4 = 0;
            var5 = 20;
            break;
         default:
            var3 = 8;
            var4 = 0;
            var5 = 5;
         }

         byte var6 = 8;
         Image var10002 = this.m_imgCmd[SYSSCORE];
         int var10008 = var1[2] / 100 - mapView[0];
         int var10009 = var1[3] / 100 - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, 0, var10008, var10009, 1 | 2);
      }
   }

   private void DrawTama(int[] var1) {
      int var2 = var1[15] % 14;
      short var3 = RectTblTama[var2][0];
      short var4 = RectTblTama[var2][1];
      short var5 = RectTblTama[var2][2];
      short var6 = RectTblTama[var2][3];
      Image var10002 = this.m_imgObj[96];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, 0, var10008, var10009, 1 | 2);
   }

   private void DrawImoTama(int[] var1) {
      int var2 = var1[8] == 0 ? 0 : 2;
      short var3 = RectTblImo[var2][0];
      short var4 = RectTblImo[var2][1];
      short var5 = RectTblImo[var2][2];
      short var6 = RectTblImo[var2][3];
      Image var10002 = this.m_imgObj[49];
      int var10007 = var1[19] == 0 ? 0 : 2;
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
   }

   private void DrawMusiKemuri(int[] var1) {
      byte var2;
      if (var1[6] < 5) {
         var2 = 0;
      } else if (var1[6] < 10) {
         var2 = 8;
      } else {
         if (var1[6] >= 15) {
            return;
         }

         var2 = 16;
      }

      if ((this.animeTimer & 1) == 0) {
         Image var10002 = this.m_imgObj[41];
         int var10008 = var1[2] / 100 - mapView[0];
         int var10009 = var1[3] / 100 - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var2, 96, 8, 8, 0, var10008, var10009, 1 | 2);
      }

   }

   private void DrawFireball(int[] var1) {
      int var2 = var1[15] % 5;
      if (var2 == 0) {
         var2 += this.animeTimer & 3;
      }

      int var7 = var1[18];
      byte var3 = 0;
      int var4 = var2 * 32;
      byte var5 = 24;
      byte var6 = 32;
      Graphics var10001 = gg;
      Image var10002 = this.m_imgObj[101];
      int var10007 = this.GetDrawRot(var7);
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(var10001, var10002, var3, var4, var5, var6, var10007, var10008, var10009, 1 | 2);
   }

   private void DrawKazarifire(int[] var1) {
      int var2 = var1[15] % 1;
      if (var2 == 0) {
         var2 += this.animeTimer >> 1 & 1;
      }

      int var7 = var1[10] < 0 ? 0 : 2;
      byte var3 = 0;
      int var4 = var2 == 0 ? 160 : 168;
      byte var5 = 16;
      byte var6 = 8;
      Image var10002 = this.m_imgObj[101];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, var7, var10008, var10009, 1 | 2);
   }

   private void DrawDBlock(int[] var1) {
      int var2 = var1[15] & 3;
      int var7;
      if (var1[1] == 23) {
         var7 = 102;
      } else if (var1[1] == 24) {
         var7 = BRKABE;
      } else {
         var7 = 27;
      }

      short var3 = RectTblDBlock[var2][0];
      short var4 = RectTblDBlock[var2][1];
      byte var5 = 16;
      byte var6 = 16;
      Image var10002 = this.m_imgObj[var7];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, 0, var10008, var10009, 1 | 2);
   }

   private void DrawBrkabe(int[] var1) {
      byte var3;
      switch(var1[8]) {
      case 1:
         var3 = 16;
         break;
      case 2:
         var3 = 32;
         break;
      default:
         var3 = 0;
      }

      byte var4 = 0;
      byte var5 = 16;
      byte var6 = 16;
      Image var10002 = this.m_imgObj[18];
      int var10008 = var1[2] / 100 - mapView[0];
      int var10009 = var1[3] / 100 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var3, var4, var5, var6, 0, var10008, var10009, 1 | 2);
   }

   private void DrawBoss6Tama(int[] var1) {
      if (var1[15] >= 0) {
         int var2 = var1[15] % 5;
         short var3 = RectTblBoss6Tama[var2][0];
         short var4 = RectTblBoss6Tama[var2][1];
         short var5 = RectTblBoss6Tama[var2][2];
         short var6 = RectTblBoss6Tama[var2][3];
         byte var7;
         switch(this.cpuTimer & 3) {
         case 1:
            var7 = 2;
            break;
         case 2:
            var7 = 1;
            break;
         case 3:
            var7 = 3;
            break;
         default:
            var7 = 0;
         }

         Image var10002 = this.m_imgObj[145];
         int var10008 = var1[2] / 100 - mapView[0];
         int var10009 = var1[3] / 100 - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var3, var4, var5, var6, var7, var10008, var10009, 1 | 2);
      }
   }

   private void MoveAnimal(int[] var1) {
      int var2 = (var1[1] - 28) % 7;
      if (var1[14] > 2) {
         var1[2] += var1[10];
         var1[3] += var1[11];
         var1[11] += 10;
         if (var1[11] > 800) {
            var1[11] = 800;
         }
      }

      int var10002;
      switch(var1[14]) {
      default:
         var1[14] = 1;
      case 1:
         var1[10] = 0;
         var1[15] = 0;
         var1[5] = var1[8];
         var10002 = var1[14]++;
      case 2:
         if (var1[5] > 0) {
            break;
         }

         var10002 = var1[14]++;
      case 3:
         var1[15] = 0;
         if (!this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 8)) {
            break;
         }

         var1[10] = this.MoveAnimalTbl[var2][0];
         var1[11] = this.MoveAnimalTbl[var2][1];
         if (var1[19] == 1) {
            var1[10] = -var1[10];
         }

         var1[6] = 0;
         var10002 = var1[14]++;
      case 4:
         var1[15] = 1;
         if (this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 8)) {
            for(int var3 = 0; var3 < 3; ++var3) {
               var1[3] -= 200;
               if (!this.blockColChk_Enemy(var1[2] / 100, var1[3] / 100 + 8)) {
                  break;
               }
            }

            var1[11] = this.MoveAnimalTbl[var2][1];
         }
      }

      if (var1[3] < 0) {
         var1[3] = 0;
      } else if (var1[3] > 999900) {
         var1[0] = 0;
      }

   }

   private void DrawAnimal(int[] var1) {
      int var2 = var1[15] % 3;
      if (var2 >= 0) {
         if (var2 == 1 && var1[11] > 0) {
            ++var2;
         }

         int var3 = (var1[1] - 28) % 7;
         if (var3 < 0) {
            var3 = 0;
         }

         var2 += var3 * 3;
         short var4 = this.RectAnimalTbl[var2][0];
         short var5 = this.RectAnimalTbl[var2][1];
         short var6 = this.RectAnimalTbl[var2][2];
         short var7 = this.RectAnimalTbl[var2][3];
         short var8 = this.RectAnimalTbl[var2][4];
         byte var9;
         switch(var1[19]) {
         case 1:
            var9 = 2;
            break;
         case 2:
            var9 = 1;
            break;
         case 3:
            var9 = 3;
            break;
         default:
            var9 = 0;
         }

         Image var10002 = this.m_imgObj[100];
         int var10008 = var1[2] / 100 - mapView[0];
         int var10009 = var1[3] / 100 - mapView[1] + var8;
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var4, var5, var6, var7, var9, var10008, var10009, 1 | 2);
      }
   }

   private void MoveObj2Debug(int[] var1) {
      if (var1[6] > 0) {
         var1[0] = 0;
      }

   }

   private void DrawObj2Debug(int[] var1) {
   }

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
      if (this.zoneNumber != 3 && this.zoneNumber != 4) {
         switch(m_nConfigValue[0]) {
         case 1:
            this.bossHP = 6;
            break;
         case 2:
            this.bossHP = 8;
            break;
         default:
            this.bossHP = 4;
         }
      } else {
         switch(m_nConfigValue[0]) {
         case 1:
            this.bossHP = 5;
            break;
         case 2:
            this.bossHP = 6;
            break;
         default:
            this.bossHP = 4;
         }
      }

      if (this.zoneNumber < 5) {
         this.PlayMusic(17);
      }

   }

   private void initBossData(int zone) {
      try {
         int[] bossdata = new int[25];

         int var2;
         for(var2 = 0; var2 < bossdata.length; ++var2) {
            bossdata[var2] = 0;
         }

         this.InitBoss();
         if (zone == 0) {
            this.m_imgObj[BOSS1] = this.createImage("boss.png");
            this.m_imgObj[121] = this.createImage("bossball.png");
            bossdata[1] = BOSS1;
            this.bossOriginX = 10752;
            this.bossOriginY = 808;
         } else if (zone == 1) {
            this.m_imgObj[BOSS1] = this.createImage("boss.png");
            bossdata[1] = BOSS2;
            this.bossOriginX = 0;
            this.bossOriginY = 0;
            this.bossPosX = 769600;
            this.bossPosY = 147200;
         } else if (zone == 2) {
            this.m_imgObj[BOSS1] = this.createImage("boss.png");
            this.m_imgObj[131] = this.createImage("fire.png");
            bossdata[1] = BOSS3;
            this.bossOriginX = 6304;
            this.bossOriginY = 608;
            this.bossPosX = (6640 - this.bossOriginX) * 100;
            this.bossPosY = (544 - this.bossOriginY) * 100;
         } else if (zone == 3) {
            this.m_imgObj[BOSS1] = this.createImage("boss.png");
            bossdata[1] = BOSS4;
            this.bossOriginX = 8352;
            this.bossOriginY = 576;
            this.bossPosX = 0;
            this.bossPosY = 0;

            for(var2 = 0; var2 < 3; ++var2) {
               boss4Sisoo[var2][0] = 0;
            }

            boolean var3 = false;
            int[][] var5 = this.searchObject(SISOO_NFLAG, 255);

            for(var2 = 0; var2 < var5.length; ++var2) {
               ObjectAct[var5[var2][20]] = true;
               ObjectDead[var5[var2][20]] = false;
               byte var6;
               if (var5[var2][2] == 8352) {
                  var6 = 1;
               } else if (var5[var2][2] == 8452) {
                  var6 = 2;
               } else {
                  var6 = 0;
               }

               boss4Sisoo[var6][0] = (short)var5[var2][23];
               boss4Sisoo[var6][1] = (short)(var5[var2][2] - this.bossOriginX);
               boss4Sisoo[var6][2] = (short)(var6 == 2 ? 20 : -20);
               boss4Sisoo[var6][3] = 0;
            }
         } else if (zone == 4) {
            this.PreInitBoss5();
            this.m_imgObj[120] = this.createImage("boss.png");
            bossdata[1] = 140;
            this.bossPosX = 0;
            this.bossPosY = 0;
         } else if (zone == 5) {
            for(var2 = 0; var2 < 150; ++var2) {
               this.m_imgObj[var2] = null;
            }

            this.DoGc();
            this.m_imgObj[RING_SFLAG_RING_18_00] = this.createImage("/ring.png");
            this.m_imgObj[BAKUHATU] = this.createImage("/bakuhatu.png");
            this.m_imgObj[84] = this.createImage("/beltcon.png");
            this.m_imgObj[BOSS1] = this.createImage("boss.png");
            this.m_imgObj[BOSS6] = this.createImage("boss6.png");
            this.m_imgObj[146] = this.createImage("eggman.png");
            this.m_imgObj[147] = this.createImage("boss2.png");
            bossdata[1] = 145;
            this.bossOriginX = 1272;
            this.bossOriginY = 120;
            this.bossPosX = 0;
            this.bossPosY = 0;
         }

         bossdata[0] = 1;
         bossdata[2] = bossdata[16] = mapView[0] + 256 + 46;
         bossdata[3] = bossdata[17] = mapView[1] + 46;
         bossdata[4] = 0;
         bossdata[14] = 0;
         bossdata[20] = ObjectAct.length - 1;
         ObjectAct[bossdata[20]] = true;
         ObjectDead[bossdata[20]] = false;
         this.addObject(bossdata);
      } catch (Exception var7) {
      }

   }

   private void startBossMode() {
      bossModeOn = true;
      this.initBossData(this.zoneNumber);
   }

   private void endBossMode() {
      bossBreakOn = true;
      bossModeOn = false;
      MapEndCounter = 1;
      if (this.zoneNumber != 5) {
         this.PlayZoneBGM();
      }

   }

   private boolean _BossDefault(int[] var1) {
      var1[2] = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
      var1[3] = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY;
      if (this.bossFlash > 0) {
         --this.bossFlash;
      }

      if (this.bossFaceCount > 0 && --this.bossFaceCount == 0) {
         this.bossFace = 0;
      }

      if (this.bossStopCount > 0) {
         --this.bossStopCount;
         return true;
      } else {
         if (this.bossCount > 0) {
            --this.bossCount;
         }

         ++this.bossFrame;
         return false;
      }
   }

   private boolean _BossDead() {
      label53: {
         int var1 = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100;
         switch(this.bossStep) {
         case 100:
            this.bossAnim = 3;
            this.bossCount = 120;
            ++this.bossStep;
         case 101:
            if (this.bossParam1 > 0) {
               --this.bossParam1;
            }

            if (this.bossCount > 0) {
               break;
            }

            this.bossAnim = 4;
            this.bossCount = 80;
            ++this.bossStep;
         case 102:
            this.bossAnim = 4;
            this.bossPosY += 200;
            if (var1 < BossDeadLimitY[this.zoneNumber]) {
               break;
            }

            this.bossCount = 40;
            ++this.bossStep;
         case 103:
            if (this.bossCount > 0) {
               break;
            }

            this.endBossMode();
            this.bossCount = 24;
            ++this.bossStep;
         case 104:
            this.bossPosY -= 80;
            if (this.bossCount > 0) {
               break;
            }

            this.bossCount = 40;
            ++this.bossStep;
         case 105:
            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 106:
            this.bossAnim = 5;
            this.bossDir = 1;
            this.bossPosX += 100;
            this.bossPosY -= 50;
            if (this.bossCount > 0) {
               break;
            }

            this.bossCount = 120;
            ++this.bossStep;
         case 107:
            this.bossAnim = 6;
            this.bossDir = 1;
            this.bossPosX += 500;
            this.bossPosY -= 50;
            if (this.bossCount <= 0) {
               ++this.bossStep;
               break label53;
            }
            break;
         case 108:
            break label53;
         }

         return false;
      }

      this.bossAnim = -1;
      return true;
   }

   private void UpdateBossPos(int[] var1) {
      var1[2] = this.bossOriginX + (this.bossPosX + this.bossOfsX) / 100;
      var1[3] = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100;
   }

   private void ColliRect(int var1, int var2, int var3, int var4) {
      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, var1, var2, var1, var2, var3, var4);
      if (var5 >= 0) {
         if (var5 == 0) {
            PlayerParam[1] = var2 - var4 << 8;
            this.playerRaidOn(-1);
         } else if (var5 == 1) {
            PlayerParam[0] = var1 - var3 - 12 << 8;
            PlayerParam[10] = 0;
         } else if (var5 == 2) {
            PlayerParam[0] = var1 + var3 + 12 + 1 << 8;
            PlayerParam[10] = 0;
         } else if (var5 == 3) {
            PlayerParam[1] = var2 + var4 + 12 + 12 + 1 << 8;
         }
      }

      if (raidOn && var5 != 0) {
         raidOn = false;
      }

   }

   private boolean isHitBoss() {
      if (this.bossStep >= 50) {
         return false;
      } else if (PlayerJump && PlayerDamage) {
         return false;
      } else if (comboScore > 0) {
         return false;
      } else {
         int var1 = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
         int var2 = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
         int var3 = var1 - this.PlayerPosX();
         int var4 = var2 - (this.PlayerPosY() - 16);
         int var5 = var3 * var3 + var4 * var4;
         if (var5 < 1024) {
            if (PlayerBall) {
               comboScore = 1;
               this.Vibrate(100);
               return true;
            } else {
               if (mutekicount == 0) {
                  this.playdamageset();
               }

               comboScore = 1;
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private boolean isHitBoss6() {
      byte var1 = 6;
      byte var2 = -8;
      if (PlayerBall && this.bossFlash <= 0 && this.bossStep < 100) {
         int var3 = this.PlayerPosX();
         int var4 = this.PlayerPosY() - 16;
         int var5 = this.bossPosX / 100 + this.bossOriginX;
         int var6 = this.bossPosY / 100 + this.bossOriginY;
         if (var5 - 32 - 8 - var1 <= var3 && var3 <= var5 + 32 + 8 + var1 && var6 - 32 - var2 <= var4 && var4 <= var6 + 32 + var2) {
            this.Vibrate(100);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void boundBossHit(int var1, int var2, int var3) {
      int var5 = this.PlayerPosX();
      int var6 = this.PlayerPosY() - 16;
      short var4;
      if (var2 >= var6) {
         if (var1 <= var5) {
            var4 = 45;
         } else {
            var4 = 315;
         }
      } else if (var1 <= var5) {
         var4 = 135;
      } else {
         var4 = 225;
      }

      PlayerParam[3] = this.dSin(var4) * 8;
      PlayerParam[5] = this.dCos(var4) * 8;
   }

   private void boss1_move_arai(int var1) {
      int[] var2 = objectData;
      this.bossOfsX = 0;
      this.bossOfsY = this.dSin(this.bossAngle2 / 100) * 8;
      this._BossDefault(var2);
      if (this.isHitBoss()) {
         this.boundBossHit(var2[2], var2[3], 12);
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
         int var7;
         for(var7 = this.bossAngle / 100; var7 < 0; var7 += 360) {
         }

         int var8 = 180 + this.dSin(var7) * 90 / 100;
         int var3 = (this.bossPosX + this.bossOfsX) / 100 + this.dSin(var8) * this.bossParam1 / 100 + this.RectBossTbl[15][4];
         int var4 = (this.bossPosY + this.bossOfsY) / 100 + this.dCos(var8) * this.bossParam1 / 100 + this.RectBossTbl[15][5];
         if (this.IsDistance(this.bossOriginX + var3, this.bossOriginY + var4, 28)) {
            this.playdamageset();
            this.bossFace = 1;
            this.bossFaceCount = 60;
         }
      }

      switch(this.bossStep) {
      case 6:
         this.bossAnim = 2;
         this.bossPosX -= Boss1Speed;
         this.bossAngle += this.bossParam2;
         this.bossAngle2 += Boss1FurikoSpeed;
         if (this.bossPosX > -Boss1MoveWidth) {
            break;
         }

         this.bossPosX = -Boss1MoveWidth;
         ++this.bossStep;
      case 7:
         this.bossAnim = 0;
         this.bossDir = 1;
         this.bossAngle2 += Boss1FurikoSpeed;
         if (this.bossParam2 < 0) {
            this.bossAngle += this.bossParam2;
            if (this.bossAngle < -9000) {
               this.bossParam2 = -this.bossParam2;
               this.bossAngle = -9000;
            }
            break;
         } else {
            this.bossAngle += this.bossParam2;
            if (this.bossAngle < 9000) {
               break;
            }

            this.bossAngle = 9000;
            this.bossParam2 = -this.bossParam2;
            this.bossCount = 64;
            ++this.bossStep;
         }
      case 8:
         this.bossAnim = 2;
         this.bossPosX += Boss1Speed;
         this.bossAngle += this.bossParam2;
         this.bossAngle2 += Boss1FurikoSpeed;
         if (this.bossPosX < Boss1MoveWidth || this.bossCount > 0) {
            break;
         }

         this.bossPosX = Boss1MoveWidth;
         ++this.bossStep;
      case 9:
         this.bossAnim = 0;
         this.bossDir = 0;
         this.bossAngle2 += Boss1FurikoSpeed;
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
         break;
      case 100:
         this.bossAnim = 3;
         this.bossCount = 40;
         ++this.bossStep;
      case 101:
         if (this.bossCount > 0) {
            break;
         }

         this.bossCount = 180;
         ++this.bossStep;
      case 102:
         if (this.bossParam1 > 0) {
            --this.bossParam1;
         }

         if (this.bossCount > 0) {
            break;
         }

         this.bossAnim = 4;
         this.bossCount = 80;
         ++this.bossStep;
      case 103:
         this.bossAnim = 4;
         this.bossPosY += 200;
         this.UpdateBossPos(var2);
         if (var2[3] < BossDeadLimitY[this.zoneNumber]) {
            break;
         }

         this.bossCount = 40;
         ++this.bossStep;
      case 104:
         if (this.bossCount > 0) {
            break;
         }

         this.endBossMode();
         this.bossCount = 16;
         ++this.bossStep;
      case 105:
         this.bossAnim = 5;
         this.bossDir = 1;
         this.bossPosX += 200;
         this.bossPosY -= 50;
         if (this.bossCount > 0) {
            break;
         }

         this.bossCount = 120;
         ++this.bossStep;
      case 106:
         this.bossAnim = 6;
         this.bossDir = 1;
         this.bossPosX += 600;
         this.bossPosY -= 50;
         if (this.bossCount > 0) {
            break;
         }

         ++this.bossStep;
      case 107:
         this.bossAnim = -1;
         objectData[0] = 0;
         break;
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
         ++this.bossStep;
      case 2:
         this.bossAnim = 0;
         this.bossPosY += 80;
         if (this.bossPosY < 0) {
            break;
         }

         this.bossPosY = 0;
         this.bossCount = 100;
         ++this.bossStep;
      case 3:
         this.bossAnim = 2;
         this.bossPosX -= Boss1Speed;
         if (this.bossCount > 0) {
            break;
         }

         ++this.bossStep;
      case 4:
         this.bossAnim = 1;
         if (++this.bossParam1 < 96) {
            break;
         }

         this.bossCount = 32;
         ++this.bossStep;
      case 5:
         this.bossAnim = 2;
         this.bossPosX -= 50;
         this.bossAngle += this.bossParam2;
         this.bossAngle2 += Boss1FurikoSpeed;
         if (this.bossPosX <= -Boss1MoveWidth) {
            this.bossPosX = -Boss1MoveWidth;
            this.bossStep = 6;
         }
      }

   }

   private boolean moveBoss2Point(int xpos, int ypos, int xspeed, int yspeed) {
      if (Math.abs(xpos - this.bossPosX) > xspeed) {
         if (xpos > this.bossPosX) {
            this.bossPosX += xspeed;
         } else {
            this.bossPosX -= xspeed;
         }
      } else {
         this.bossPosX = xpos;
      }

      if (Math.abs(ypos - this.bossPosY) > yspeed) {
         if (ypos > this.bossPosY) {
            this.bossPosY += yspeed;
         } else {
            this.bossPosY -= yspeed;
         }
      } else {
         this.bossPosY = ypos;
      }

      return Math.abs(this.bossPosX - xpos) < 200 && Math.abs(this.bossPosY - ypos) < 200;
   }

   private void boss2_move_arai(int var1) {
      int[] bossdata = objectData;
      this._BossDefault(bossdata);
      this.bossAngle2 += 200;
      this.bossOfsX = 0;
      this.bossOfsY = this.dSin(this.bossAngle2 / 100) * 8;
      this.UpdateBossPos(bossdata);
      int xdistance = bossdata[2] - this.PlayerPosX();
      int ydistance = bossdata[3] - (this.PlayerPosY() - 16);
      int distance = xdistance * xdistance + ydistance * ydistance;
      if (this.isHitBoss()) {
         this.boundBossHit(bossdata[2], bossdata[3], 8);
         if (this.bossFlash == 0 && this.bossStep < 100) {
            this.bossFace = 3;
            this.bossFlash = this.bossFaceCount = 60;
         }
      }

      switch(this.bossStep) {
      case 4:
         if (distance > 4096 && bossdata[3] < this.PlayerPosY() - 16) {
            break;
         }

         ++this.bossStep;
      case 5:
         if (this.moveBoss2Point(boss2MoveTbl[2][0], boss2MoveTbl[2][1], 50, 80)) {
            ++this.bossStep;
         }
         break;
      case 6:
         if (distance > 6400 && bossdata[3] < this.PlayerPosY() - 16) {
            break;
         }

         ++this.bossStep;
      case 7:
         if (this.moveBoss2Point(boss2MoveTbl[3][0], boss2MoveTbl[3][1], 50, 80)) {
            ++this.bossStep;
         }
         break;
      case 8:
         if (distance > 6400 && bossdata[3] < this.PlayerPosY() - 16) {
            break;
         }

         ++this.bossStep;
      case 9:
         if (this.moveBoss2Point(boss2MoveTbl[4][0], boss2MoveTbl[4][1], 80, 80)) {
            ++this.bossStep;
         }
         break;
      case 10:
         if (distance > 6400) {
            break;
         }

         this.bossCount = 40;
         ++this.bossStep;
      case 11:
         if (this.bossCount <= 0) {
            this.bossStep = 100;
         }
         break;
      case 100:
         if (this.bossCount > 0) {
            break;
         }

         this.endBossMode();
         this.bossCount = 16;
         ++this.bossStep;
      case 101:
         this.bossAnim = 5;
         this.bossDir = 1;
         this.bossPosX += 100;
         this.bossPosY -= 30;
         if (this.bossCount > 0) {
            break;
         }

         this.bossCount = 180;
         ++this.bossStep;
      case 102:
         this.bossAnim = 6;
         this.bossDir = 1;
         this.bossPosX += 300;
         this.bossPosY -= 50;
         if (this.bossCount > 0) {
            break;
         }

         ++this.bossStep;
      case 103:
         this.bossAnim = -1;
         objectData[0] = 0;
         break;
      default:
         this.bossStep = 1;
      case 1:
         this.bossFace = 0;
         this.bossAnim = 2;
         this.bossPosX = boss2MoveTbl[0][0];
         this.bossPosY = boss2MoveTbl[0][1];
         this.bossAngle = 0;
         this.bossAngle2 = 0;
         ++this.bossStep;
         if (distance > 4096) {
            break;
         }

         ++this.bossStep;
      case 3:
         if (this.moveBoss2Point(boss2MoveTbl[1][0], boss2MoveTbl[1][1], 50, 100)) {
            ++this.bossStep;
         }
      }

   }

   private void boss3_move_arai(int var1) {
      int[] var2 = objectData;
      if (!this._BossDefault(objectData)) {
         this.bossAngle2 += 100;
         this.bossOfsX = 0;
         this.bossOfsY = this.dSin(this.bossAngle2 / 100) * 10;
         if (this.bossStep > 99) {
            if (this._BossDead()) {
               var2[0] = 0;
            }

         } else {
            if (this.isHitBoss() && this.bossStep > 3) {
               this.boundBossHit(var2[2], var2[3], 12);
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
               boss3FireCount = 150 + this.rnd(150);
               this.SetObj2(OBJ2_FIREBALL, this.bossOriginX + this.rnd(40) - 20, this.bossOriginY + 130, 0, -300, 0, 1);
            }

            switch(this.bossStep) {
            case 4:
               this.bossAnim = 2;
               this.bossDir = 0;
               ++this.bossStep;
            case 5:
               this.bossAnim = 2;
               this.bossDir = 0;
               this.bossPosX -= boss3SpeedX;
               this.bossPosY -= boss3FloatSpeed;
               if (this.bossPosX > -10400) {
                  break;
               }

               this.bossPosX = -10400;
               ++this.bossStep;
            case 6:
               this.bossPosY += boss3DownSpeed;
               if (this.bossPosY <= 1200) {
                  break;
               }

               this.bossPosY = 1200;
               ++this.bossStep;
            case 7:
               this.bossFace = 1;
               this.bossFaceCount = 60;
               this.bossCount = boss3AttackWait;
               ++this.bossStep;
            case 8:
               if (this.bossCount == 8) {
                  this.SetObj2(OBJ2_FIREBALL2, var2[2], var2[3] + 16, 0, 0, 0, 0);
               }

               if (this.bossCount <= 0) {
                  this.UpdateBossPos(var2);
                  ++this.bossStep;
               }
               break;
            case 9:
               this.bossAnim = 2;
               this.bossDir = 1;
               ++this.bossStep;
            case 10:
               this.bossAnim = 2;
               this.bossDir = 1;
               this.bossPosX += boss3SpeedX;
               this.bossPosY -= boss3FloatSpeed;
               if (this.bossPosX < 10400) {
                  break;
               }

               this.bossPosX = 10400;
               ++this.bossStep;
            case 11:
               this.bossPosY += boss3DownSpeed;
               if (this.bossPosY <= 1200) {
                  break;
               }

               this.bossPosY = 1200;
               ++this.bossStep;
            case 12:
               this.bossFace = 1;
               this.bossFaceCount = 60;
               this.bossCount = boss3AttackWait;
               ++this.bossStep;
            case 13:
               if (this.bossCount == 8) {
                  this.SetObj2(OBJ2_FIREBALL2, var2[2], var2[3] + 16, 0, 0, 0, 0);
               }

               if (this.bossCount > 0) {
                  break;
               }

               this.UpdateBossPos(var2);
               ++this.bossStep;
            case 14:
               this.bossPosY -= 100;
               if (this.bossPosY <= 0) {
                  this.bossPosY = 0;
                  this.bossStep = 4;
               }
               break;
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
               ++this.bossStep;
            case 2:
               if (this.bossCount > 0) {
                  break;
               }

               ++this.bossStep;
            case 3:
               this.bossAnim = 2;
               this.bossPosX -= boss3SpeedX;
               this.bossPosY -= 20;
               if (this.bossPosX <= 10400 && this.bossCount <= 0) {
                  this.bossPosX = 10400;
                  this.bossAnim = 0;
                  this.bossFace = 1;
                  this.bossFaceCount = 60;
                  this.bossCount = 60;
                  boss3FireCount = 120;
                  ++this.bossStep;
               }
            }

         }
      }
   }

   private void Boss4ShotTekkyu(int var1) {
      short var2 = boss4Sisoo[var1 % 3][0];
      int[] var3 = new int[25];

      for(int var4 = 0; var4 < 25; ++var4) {
         var3[var4] = ObjectList[var2][var4];
      }

      if (var3[13] == 0) {
         int var5 = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
         int var6 = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
         var3[6] = var5 << 8;
         var3[7] = var6 + 32 << 8;
         var3[10] = 0;
         var3[11] = 70;
         var3[12] = 0;
         var3[13] = 1;
         var3[14] = Boss4BakuhatuCount;
         this.InsertObject(var3, var2);
      }

   }

   private boolean Boss4HitTekkyu(int var1) {
      if (this.bossFlash <= 0 && this.bossStep < 100) {
         short var2 = boss4Sisoo[var1 % 3][0];
         int[] var3 = new int[25];

         for(int var4 = 0; var4 < 25; ++var4) {
            var3[var4] = ObjectList[var2][var4];
         }

         if (var3[13] == 1 && var3[14] < 210) {
            int var5 = var3[6] >> 8;
            int var6 = var3[7] >> 8;
            int var7 = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX;
            int var8 = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - 16;
            if (var7 - 24 <= var5 && var5 <= var7 + 24 && var8 - 24 <= var6 && var6 <= var8 + 24) {
               var3[13] = 0;
               var3[14] = 0;
               this.InsertObject(var3, var2);
               this.SetObj2(OBJ2_BAKUHATU, var5, var6, 0, 0, 0, 0);
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void boss4_move_arai(int var1) {
      int[] var2 = objectData;
      this._BossDefault(var2);
      if (this.bossStep > 99) {
         if (this._BossDead()) {
            var2[0] = 0;
         }

      } else {
         this.bossAngle2 += 400;
         this.bossOfsX = 0;
         this.bossOfsY = this.dSin(this.bossAngle2 / 100) * 8;
         int var5 = boss4Sisoo[0][1] * 100;
         int var6 = boss4Sisoo[1][1] * 100;
         int var7 = boss4Sisoo[2][1] * 100;
         this.UpdateBossPos(var2);
         if (this.bossStep > 3 && this.bossPosY > -Boss4HighPos) {
            this.bossPosY -= 240;
         }

         boolean var8 = false;
         var8 = var8 || this.Boss4HitTekkyu(1);
         var8 = var8 || this.Boss4HitTekkyu(2);
         if ((this.isHitBoss() || var8) && this.bossStep > 3) {
            this.boundBossHit(var2[2], var2[3], 12);
            if (this.bossFlash == 0 && this.bossStep < 100) {
               if (--this.bossHP <= 0) {
                  this.bossStep = 100;
                  return;
               }

               this.bossFace = 3;
               this.bossFlash = this.bossFaceCount = 60;
            }
         }

         switch(this.bossStep) {
         case 7:
            if (this.bossCount == 10) {
               this.Boss4ShotTekkyu(1);
            }

            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 8:
            this.bossDir = 1;
            this.bossAnim = 2;
            ++this.bossStep;
         case 9:
            this.bossDir = 1;
            this.bossAnim = 2;
            this.bossPosX += Boss4Speed;
            if (this.bossPosX < var6 + Boss4SisooOfs) {
               break;
            }

            this.bossPosX = var6 + Boss4SisooOfs;
            this.bossAnim = 0;
            this.bossFace = 1;
            this.bossFaceCount = Boss4ShootWait;
            this.bossCount = Boss4ShootWait;
            ++this.bossStep;
         case 10:
            if (this.bossCount == 10) {
               this.Boss4ShotTekkyu(1);
            }

            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 11:
            this.bossDir = 1;
            this.bossAnim = 2;
            this.bossPosX += Boss4Speed;
            if (this.bossPosX < var7 + Boss4SisooOfs) {
               break;
            }

            this.bossPosX = var7 + Boss4SisooOfs;
            this.bossAnim = 0;
            this.bossFace = 1;
            this.bossFaceCount = Boss4ShootWait;
            this.bossCount = Boss4ShootWait;
            ++this.bossStep;
         case 12:
            if (this.bossCount == 10) {
               this.Boss4ShotTekkyu(2);
            }

            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 13:
            this.bossDir = 1;
            this.bossAnim = 2;
            this.bossPosX += Boss4Speed;
            if (this.bossPosX < 16000) {
               break;
            }

            this.bossPosX = 16000;
            this.bossAnim = 0;
            this.bossFace = 1;
            this.bossFaceCount = Boss4ShootWait;
            this.bossCount = Boss4ShootWait;
            ++this.bossStep;
         case 14:
            this.bossDir = 0;
            this.bossStep = 4;
            break;
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
            ++this.bossStep;
         case 2:
            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 3:
            this.bossPosX -= Boss4Speed;
            if (this.bossPosX > var7 - Boss4SisooOfs + 10) {
               break;
            }

            ++this.bossStep;
         case 4:
            this.bossDir = 0;
            this.bossAnim = 2;
            this.bossPosX -= Boss4Speed;
            if (this.bossPosX > var7 - Boss4SisooOfs) {
               break;
            }

            this.bossPosX = var7 - Boss4SisooOfs;
            this.bossAnim = 0;
            this.bossFace = 1;
            this.bossFaceCount = Boss4ShootWait;
            this.bossCount = Boss4ShootWait;
            ++this.bossStep;
         case 5:
            if (this.bossCount == 10) {
               this.Boss4ShotTekkyu(2);
            }

            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 6:
            this.bossAnim = 2;
            this.bossDir = 0;
            this.bossPosX -= Boss4Speed;
            if (this.bossPosX <= var6 - Boss4SisooOfs) {
               this.bossPosX = var6 - Boss4SisooOfs;
               this.bossAnim = 0;
               this.bossFace = 1;
               this.bossFaceCount = Boss4ShootWait;
               this.bossCount = Boss4ShootWait;
               ++this.bossStep;
            }
         }

      }
   }

   private void PreInitBoss5() {
      this.bossOriginX = 11424;
      this.bossOriginY = 1232;

      for(int var1 = 0; var1 < 10; ++var1) {
         boss5Block[var1][0] = (short)(-160 + 32 * var1 + 16);
         boss5Block[var1][1] = 176;
         boss5Block[var1][2] = 0;
         boss5Block[var1][3] = 0;
      }

   }

   private void DestroyBoss5Block() {
      for(int var1 = 0; var1 < 10; ++var1) {
         if (boss5Block[var1][3] != 0) {
            this.ShotObj2(23, boss5Block[var1][0], boss5Block[var1][1], 330, 300, 0);
            this.ShotObj2(23, boss5Block[var1][0], boss5Block[var1][1], 30, 300, 1);
            this.ShotObj2(23, boss5Block[var1][0], boss5Block[var1][1], 300, 300, 2);
            this.ShotObj2(23, boss5Block[var1][0], boss5Block[var1][1], 60, 300, 3);
            boss5Block[var1][2] = 1;
         }
      }

   }

   private void MoveBoss5Block(int var1) {
      if (objectData[14] == 0) {
         this.PreInitBoss5();
         int var10002 = objectData[14]++;
      }

      int var2 = this.PlayerPosX();
      int var3 = this.PlayerPosY();

      for(int var6 = 0; var6 < 10; ++var6) {
         if (boss5Block[var6][2] == 0) {
            int var4;
            int var5;
            if (boss5Block[var6][3] == 0) {
               var4 = this.bossOriginX + boss5Block[var6][0];
               var5 = this.bossOriginY + boss5Block[var6][1];
            } else {
               var4 = this.bossOriginX + (this.bossPosX + this.bossOfsX) / 100;
               var5 = this.bossOriginY + (this.bossPosY + this.bossOfsY) / 100 + 24;
            }

            this.ColliRect(var4, var5, 16, 16);
         }
      }

   }

   private void DrawBoss5Block(boolean var1) {
      for(int var4 = 0; var4 < 10; ++var4) {
         if (boss5Block[var4][2] == 0) {
            int var2;
            int var3;
            if (boss5Block[var4][3] == 0) {
               var2 = boss5Block[var4][0];
               var3 = boss5Block[var4][1];
            } else {
               var2 = (this.bossPosX + this.bossOfsX) / 100;
               var3 = (this.bossPosY + this.bossOfsY) / 100 + 24;
            }

            if (var1) {
               short var5 = boss5Block[var4][0];
               int var6 = (this.bossPosX + this.bossOfsX) / 100;
               if (Math.abs(var5 - var6) > 32) {
                  continue;
               }
            }

            Image var10002 = this.m_imgObj[102];
            int var10008 = var2 + this.bossOriginX - mapView[0];
            int var10009 = var3 + this.bossOriginY - mapView[1];
            Graphics var10010 = gg;
            Graphics var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 32, 32, 0, var10008, var10009, 1 | 2);
         }
      }

   }

   private void boss5_move_arai(int var1) {
      int[] var2 = objectData;
      boolean var4 = false;
      boolean var9 = false;
      this._BossDefault(var2);
      if (this.bossStep > 99) {
         if (this._BossDead()) {
            var2[0] = 0;
         }

      } else {
         this.bossAngle2 += 200;
         this.bossOfsX = 0;
         this.bossOfsY = this.dSin(this.bossAngle2 / 100) * 12;
         int var5 = (this.PlayerPosX() - (this.bossOriginX - 160)) / 32;
         int var3 = (this.bossPosX + 16000) / 3200;
         int var10 = this.bossOriginX - 160 + var3 * 32 + 16;
         int var7 = Math.abs(this.bossPosX / 100 + 160 - (var3 * 32 + 16));
         if (var5 == var3 && var7 < 3 && boss5AttackCount == 0) {
            var9 = true;
         }

         if (boss5AttackCount > 0) {
            --boss5AttackCount;
         }

         if (this.bossParam1 > 16 && this.IsHitSonic(var2[2], var2[3] + 8 - 32 + this.bossParam1, 14, 32, false) != 0) {
            this.playdamageset();
         }

         if (this.isHitBoss() && this.bossStep > 3 && this.bossFlash == 0) {
            this.boundBossHit(var2[2], var2[3], 10);
            if (this.bossFlash == 0 && this.bossStep < 100) {
               if (--this.bossHP <= 0) {
                  this.DestroyBoss5Block();
                  this.bossStep = 100;
                  return;
               }

               this.bossFace = 3;
               this.bossFlash = this.bossFaceCount = 60;
            }
         }

         switch(this.bossStep) {
         case 7:
         case 8:
         case 9:
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
            var9 = false;
            ++this.bossStep;
         case 2:
            if (this.bossCount > 0) {
               break;
            }

            ++this.bossStep;
         case 3:
            this.bossAnim = 2;
            this.bossPosY -= 20;
            this.bossPosX -= 100;
            if (this.bossPosX > 6400) {
               break;
            }

            this.bossPosX = 6400;
            this.bossAnim = 0;
            this.bossFace = 1;
            this.bossFaceCount = 60;
            this.bossCount = 60;
            ++this.bossStep;
         case 4:
            if (var9) {
               this.bossStep = 10;
               break;
            } else {
               this.bossAnim = 2;
               this.bossDir = 0;
               this.bossPosX -= boss5Speed;
               if (this.bossPosX > -12000) {
                  break;
               }

               this.bossPosX = -12000;
               ++this.bossStep;
            }
         case 5:
            if (var9) {
               this.bossStep = 10;
               break;
            } else {
               this.bossAnim = 2;
               this.bossDir = 1;
               this.bossPosX += boss5Speed;
               if (this.bossPosX < 12000) {
                  break;
               }

               this.bossPosX = 12000;
               ++this.bossStep;
            }
         case 6:
            this.bossStep = 4;
            break;
         case 10:
            this.bossAnim = 0;
            this.bossFace = 1;
            ++this.bossStep;
         case 11:
            this.bossPosY += 120;
            if (this.bossParam1 < 32) {
               ++this.bossParam1;
            }

            if (this.bossPosY < 15400) {
               break;
            }

            this.bossPosY = 15400;
            this.bossCount = 48;
            boss5Block[var3][3] = 1;
            ++this.bossStep;
         case 12:
            this.bossOfsX = 0;
            this.bossOfsY = this.rnd(400) - 200;
            if (this.bossCount > 0) {
               break;
            }

            this.bossOfsX = 0;
            this.bossOfsY = 0;
            ++this.bossStep;
         case 13:
            this.bossPosY -= 300;
            if (this.bossPosY > 2400) {
               break;
            }

            this.bossPosY = 2400;
            this.bossCount = 50;
            ++this.bossStep;
         case 14:
            this.bossOfsX = 0;
            this.bossOfsY = this.rnd(400) - 200;
            if (this.bossParam1 > 0) {
               --this.bossParam1;
            }

            if (this.bossCount > 0) {
               break;
            }

            if (boss5Block[var3][2] == 0) {
               this.bossOfsX = 0;
               this.bossOfsY = 0;
               this.ShotObj2(23, var2[2], var2[3] + 24, 330, 300, 0);
               this.ShotObj2(23, var2[2], var2[3] + 24, 30, 300, 1);
               this.ShotObj2(23, var2[2], var2[3] + 24, 300, 300, 2);
               this.ShotObj2(23, var2[2], var2[3] + 24, 60, 300, 3);
               boss5Block[var3][2] = 1;
            }

            this.bossCount = 30;
            ++this.bossStep;
         case 15:
            if (this.bossCount <= 0) {
               boss5AttackCount = 120;
               this.bossStep = this.bossDir == 0 ? 4 : 5;
            }
         }

         this.UpdateBossPos(var2);
         if (this.bossStep == 4 || this.bossStep == 5) {
            if (Math.abs(var2[2] - this.PlayerPosX()) < 64) {
               if (this.bossPosY > 1000) {
                  this.bossPosY -= 160;
               }
            } else if (this.bossPosY < 8000) {
               this.bossPosY += 140;
            }
         }

      }
   }

   private void ColliRect2(int var1, int[] var2, int var3, int var4) {
      int var5 = var2[0];
      int var6 = var2[1];
      int var7 = var2[2] > -99999 ? var2[2] : var2[0];
      int var8 = var2[3] > -99999 ? var2[3] : var2[1];
      int var9 = var3 >> 1;
      int var10 = var4 >> 1;
      int var11 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, var5, var6, var7, var8, var9, var10);
      if (var11 >= 0) {
         if (var11 == 0) {
            PlayerParam[1] = var6 - var10 << 8;
            this.setRaidOnSize(var5, var9);
            this.playerRaidOn(var1);
         } else if (var11 == 1) {
            PlayerParam[0] = var5 - var9 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var11 == 2) {
            PlayerParam[0] = var5 + var9 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var11 == 3) {
            PlayerParam[1] = var6 + var10 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == var1 && var11 != 0) {
         raidOn = false;
      }

      var2[2] = var2[0];
      var2[3] = var2[1];
   }

   private void boss6_move_arai(int var1) {
      int[] var4 = objectData;
      if (this.bossStep <= 200) {
         if (PlayerParam[1] >> 8 > 192) {
            PlayerParam[1] = 49152;
         }

         if (PlayerParam[0] >> 8 >= 1952) {
            if (this.bossAnim != 11) {
               PlayerLookUp = true;
            }

            PlayerParam[0] = 499968;
         }

         this._BossDefault(var4);
         int[] var10000;
         int var10001;
         int var9;
         int var10;
         int var11;
         switch(this.bossStep) {
         case 3:
            var10000 = boss6Piston;
            var10001 = boss6RideNum;
            var10000[var10001] += 50;
            var10000 = boss6Piston;
            var10001 = boss6PistonNum;
            var10000[var10001] += 50;
            if (boss6Piston[boss6RideNum] >= 2000) {
               ++this.bossStep;
            }
            break;
         case 4:
            var10000 = boss6Piston;
            var10001 = boss6RideNum;
            var10000[var10001] += 180;
            var10000 = boss6Piston;
            var10001 = boss6PistonNum;
            var10000[var10001] += 180;
            if (boss6Piston[boss6RideNum] >= 16000) {
               boss6Piston[boss6RideNum] = 16000;
               boss6Piston[boss6PistonNum] = 16000;
               ++this.bossStep;
            }
            break;
         case 5:
            var10000 = boss6Piston;
            var10001 = boss6RideNum;
            var10000[var10001] -= 180;
            var10000 = boss6Piston;
            var10001 = boss6PistonNum;
            var10000[var10001] -= 180;
            if (boss6Piston[boss6RideNum] > 0) {
               break;
            }

            boss6Piston[boss6RideNum] = 0;
            boss6Piston[boss6PistonNum] = 0;
            this.bossFlash = this.bossFace = this.bossFaceCount = 0;
            ++this.bossStep;
         case 6:
            boss6Lamp = 2;
            this.SetObj2(OBJ2_BOSS6_TAMA, 1415, 60, 0, 0, 0, (this.bossOriginX + 64 + 32 - this.rnd(8) + 42) * 100);
            this.SetObj2(OBJ2_BOSS6_TAMA, 1415, 60, 0, 0, 0, (this.bossOriginX + 32 + this.rnd(46) - 23 + 42) * 100);
            this.SetObj2(OBJ2_BOSS6_TAMA, 1415, 60, 0, 0, 0, (this.bossOriginX - 32 + this.rnd(46) - 23 + 42) * 100);
            this.SetObj2(OBJ2_BOSS6_TAMA, 1415, 60, 0, 0, 0, (this.bossOriginX - 64 - 32 + this.rnd(24) + 42) * 100);
            this.bossCount = 300;
            ++this.bossStep;
         case 7:
            if (this.bossCount <= 0) {
               boss6RideNum = 1 + this.rnd(3);
               boss6PistonNum = (boss6RideNum + 1 + this.rnd(3)) % 4;
               boss6Lamp = 1;
               this.bossStep = 3;
               this.bossAnim = -1;
            }
            break;
         case 50:
            boss6Lamp = 2;
            boss6Destroy = 1;
            this.bossFace = 2;
            this.bossFlash = this.bossFaceCount = 999;
            TimerStop = true;
            ++this.bossStep;
         case 51:
            var10000 = boss6Piston;
            var10001 = boss6RideNum;
            var10000[var10001] -= 50;
            var10000 = boss6Piston;
            var10001 = boss6PistonNum;
            var10000[var10001] -= 50;
            if (boss6Piston[boss6RideNum] > 0) {
               break;
            }

            boss6Piston[boss6RideNum] = 0;
            boss6Piston[boss6PistonNum] = 0;
            this.bossFlash = this.bossFace = this.bossFaceCount = 0;
            this.bossCount = 60;
            boss6Destroy = 2;
            boss6Lamp = 3;
            ++this.bossStep;
         case 52:
            if (this.bossCount <= 0) {
               this.bossStep = 100;
            }
            break;
         case 100:
            boss6Lamp = 0;
            boss6Destroy = 0;
            this.bossOfsX = 0;
            this.bossOfsY = 0;
            this.bossPosX = (1472 - this.bossOriginX) * 100;
            this.bossPosY = (168 - this.bossOriginY) * 100;
            this.UpdateBossPos(var4);
            this.bossAnim = 7;
            TimerStop = true;
            this.endBossMode();
            ++this.bossStep;
         case 101:
            if (var4[2] - this.PlayerPosX() < 150) {
               this.bossPosX += 50;
            }

            if (var4[2] - this.PlayerPosX() < 110) {
               this.bossPosX += 400;
            }

            if (var4[2] - this.PlayerPosX() < 70) {
               this.bossPosX += 500;
            }

            if (var4[2] < 1696) {
               break;
            }

            var4[2] = 1696;
            var4[10] = 200;
            var4[11] = -300;
            ++this.bossStep;
         case 102:
            this.bossPosX += var4[10];
            this.bossPosY += var4[11];
            if ((var4[11] += 12) > 600) {
               var4[11] = 600;
            }

            if (var4[2] < 1760) {
               break;
            }

            this.bossPosX = (1760 - this.bossOriginX) * 100;
            this.bossPosY = (168 - this.bossOriginY) * 100;
            var4[10] = 0;
            var4[11] = -50;
            this.bossAnim = 8;
            this.bossCount = 60;
            ++this.bossStep;
         case 103:
            this.bossPosX += var4[10];
            this.bossPosY += var4[11];
            if (this.bossCount > 0) {
               break;
            }

            var4[10] = 25;
            var4[11] = -100;
            this.bossAnim = 9;
            this.bossCount = 30;
            ++this.bossStep;
         case 104:
            this.bossPosX += var4[10];
            this.bossPosY += var4[11];
            if (this.bossCount > 0) {
               break;
            }

            var4[10] = 200;
            var4[11] = 0;
            this.bossAnim = 10;
            ++this.bossStep;
         case 105:
            this.bossPosX += var4[10];
            this.bossPosY += var4[11];
            this.UpdateBossPos(var4);
            if (var4[2] > 2168) {
               this.bossCount = 120;
               this.bossStep = 107;
            } else {
               var9 = (this.bossPosX + this.bossOfsX) / 100 + this.bossOriginX - this.PlayerPosX();
               var10 = (this.bossPosY + this.bossOfsY) / 100 + this.bossOriginY - (this.PlayerPosY() - 16);
               var11 = var9 * var9 + var10 * var10;
               if (var11 < 1444 && PlayerBall) {
                  this.boundBossHit(var4[2], var4[3], 6);
                  var4[10] = 100;
                  var4[11] = 40;
                  this.bossAnim = 11;
                  ++this.bossStep;
               }
            }
            break;
         case 106:
            this.bossPosX += var4[10];
            this.bossPosY += var4[11];
            this.UpdateBossPos(var4);
            if (var4[2] >= 2168) {
               this.bossCount = 120;
               ++this.bossStep;
            }
            break;
         case 107:
            this.playerStandCount = 120;
            if (this.doWipe(true)) {
            }

            if (this.bossCount == 10) {
               this.putNowLoading = true;
            }

            if (this.bossCount <= 0) {
               this.setWipe(0, true, 8);
               this.bossStep = 200;
            }
            break;
         case 200:
            this.playerStandCount = 0;
            this.setEnding();
            this.setWipe(0, true, 8);
            this.putNowLoading = true;
            var4[0] = 0;
            ++this.bossStep;
            return;
         case 201:
            this.putNowLoading = true;
            this.setWipe(0, true, 8);
            break;
         default:
            this.bossStep = 1;
         case 1:
            this.bossFace = 0;
            this.bossAnim = 0;
            this.bossPosX = 0;
            this.bossPosY = 0;
            this.bossOfsX = 0;
            this.bossOfsY = 0;

            for(int var2 = 0; var2 < 4; ++var2) {
               boss6Piston[var2] = 0;
               boss6PistonXY[var2][2] = -99999;
               boss6PistonXY[var2][3] = -99999;
               boss6TamaY[var2] = 0;
            }

            boss6PistonNum = 2;
            boss6RideNum = 3;
            boss6Lamp = 1;
            boss6Destroy = 0;
            this.setWipe(0, true, 0);
            ++this.bossStep;
         case 2:
            if (this.PlayerPosX() >= 1328) {
               ++this.bossStep;
            }
         }

         this.AddObjectData(48, 1216, 16, 0, 0);
         this.AddObjectData(48, 1248, 16, 0, 0);
         this.AddObjectData(48, 1344, 16, 0, 0);
         this.AddObjectData(48, 1376, 16, 0, 0);
         if (this.bossStep < 100) {
            switch(boss6RideNum) {
            case 0:
               this.bossPosX = boss6PistonPos[0][0] * 100;
               this.bossPosY = boss6PistonPos[0][1] * 100 + boss6Piston[0] + -800;
               break;
            case 1:
               this.bossPosX = boss6PistonPos[1][0] * 100;
               this.bossPosY = boss6PistonPos[1][1] * 100 + boss6Piston[1] + -800;
               break;
            case 2:
               this.bossPosX = boss6PistonPos[2][0] * 100;
               this.bossPosY = boss6PistonPos[2][1] * 100 - boss6Piston[2] + 1600;
               break;
            case 3:
               this.bossPosX = boss6PistonPos[3][0] * 100;
               this.bossPosY = boss6PistonPos[3][1] * 100 - boss6Piston[3] + 1600;
               break;
            default:
               this.bossPosX = this.bossPosY = 0;
            }
         }

         boss6PistonXY[0][0] = boss6PistonPos[0][0] + this.bossOriginX;
         boss6PistonXY[0][1] = boss6PistonPos[0][1] + this.bossOriginY - 8 + boss6Piston[0] / 100;
         this.ColliRect2(46592, boss6PistonXY[0], 64, 160);
         boss6PistonXY[1][0] = boss6PistonPos[1][0] + this.bossOriginX;
         boss6PistonXY[1][1] = boss6PistonPos[1][1] + this.bossOriginY - 8 + boss6Piston[1] / 100;
         this.ColliRect2(46593, boss6PistonXY[1], 64, 160);
         boss6PistonXY[2][0] = boss6PistonPos[2][0] + this.bossOriginX;
         boss6PistonXY[2][1] = boss6PistonPos[2][1] + this.bossOriginY + 8 - boss6Piston[2] / 100;
         this.ColliRect2(46594, boss6PistonXY[2], 64, 160);
         boss6PistonXY[3][0] = boss6PistonPos[3][0] + this.bossOriginX;
         boss6PistonXY[3][1] = boss6PistonPos[3][1] + this.bossOriginY + 8 - boss6Piston[3] / 100;
         this.ColliRect2(46595, boss6PistonXY[3], 64, 160);
         if (boss6Destroy == 1) {
            if ((this.animeTimer & 3) == 0) {
               var9 = boss6PistonPos[boss6RideNum][0] + this.bossOriginX;
               var10 = boss6PistonPos[boss6RideNum][1] + this.bossOriginY;
               var11 = boss6Piston[boss6RideNum] / 100;
               if ((boss6RideNum & 2) != 0) {
                  var11 = -var11;
               }

               this.SetObj2(OBJ2_BAKUHATU, var9 + this.rnd(64) - 32, var10 + this.rnd(128) - 64 + var11, 0, 0, 0, 0);
            } else if ((this.animeTimer & 3) == 2 && boss6PistonNum != 0) {
               var9 = boss6PistonPos[boss6PistonNum][0] + this.bossOriginX;
               var10 = boss6PistonPos[boss6PistonNum][1] + this.bossOriginY;
               var11 = boss6Piston[boss6PistonNum] / 100;
               if ((boss6PistonNum & 2) != 0) {
                  var11 = -var11;
               }

               this.SetObj2(OBJ2_BAKUHATU, var9 + this.rnd(64) - 32, var10 + this.rnd(128) - 64 + var11, 0, 0, 0, 0);
            }
         } else if (boss6Destroy == 2 && (this.animeTimer & 7) == 0) {
            this.SetObj2(OBJ2_BAKUHATU, 1415 + this.rnd(16) - 8, 60 + this.rnd(16) - 8, 0, 0, 0, 0);
         }

         if (this.isHitBoss6() && this.bossStep < 50) {
            PlayerParam[3] = var4[2] < this.PlayerPosX() ? 768 : -768;
            PlayerParam[5] = -1536;
            if (--this.bossHP <= 0) {
               this.bossStep = 50;
               return;
            }

            this.bossFace = 1;
            this.bossFlash = this.bossFaceCount = 120;
            this.bossStopCount = 0;
         }

      }
   }

   private boolean moveNaka() {
      if (nakaCount > 0) {
         --nakaCount;
      }

      switch(nakaStep) {
      case 2:
         if ((nakaLevel += 4) >= 255) {
            nakaLevel = 255;
            nakaCount = 180;
            ++nakaStep;
         }
         break;
      case 3:
         if (nakaCount <= 0) {
            ++nakaStep;
         }
         break;
      case 4:
         if ((nakaLevel -= 4) <= 0) {
            nakaLevel = 0;
            ++nakaStep;
         }
         break;
      case 5:
         return true;
      default:
         nakaStep = 1;
      case 1:
         nakaLevel = 0;
         ++nakaStep;
      }

      return false;
   }

   private void drawNaka() {
      int var1 = nakaLevel;
      Font var4 = Font.getFont(0, 0, 8);
      var1 &= 255;
      gg.setColor(0);
      gg.fillRect(0, 0, 240, 240);
      gg.setFont(var4);
      int var2 = var1 << 16 | var1 << 8 | var1;
      boolean var5 = true;
      boolean var6 = true;
      Image var10002 = this.m_imgObj[124];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 54, 15, 0, 206, 152, 1 | 2);
      this.TK_DrawStringC(softKeys[25], 120, 96, var2, var1);
      this.TK_DrawStringC(softKeys[26], 120, 121, var2, var1);
   }

   private void drawEndingEggmanB() {
      int var1 = endingEggAnim % 3;
      gg.setColor(0);
      gg.fillRect(0, 0, 240, 240);
      short var2 = RectTblEndingB[var1][0];
      short var3 = RectTblEndingB[var1][1];
      short var4 = RectTblEndingB[var1][2];
      short var5 = RectTblEndingB[var1][3];
      Image var10002 = this.m_imgObj[123];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var2, var3, var4, var5, 0, 120, 90, 1 | 2);
   }

   private boolean moveEndingEggmanB() {
      if (endingEggCount > 0) {
         --endingEggCount;
      }

      endingEggAnim = (this.animeTimer >> 1) % 3;
      switch(endingEggStep) {
      case 3:
         return true;
      default:
         endingEggStep = 1;
      case 1:
         this.setWipe(0, true, 0);
         endingEggCount = 300;
         ++endingEggStep;
      case 2:
         if (endingEggCount <= 0) {
            ++endingEggStep;
         }

         return false;
      }
   }

   void setWipe(int var1, boolean var2, int var3) {
      wipeCol = var1;
      wipeDir = var2;
      wipeLevel = var3;
   }

   boolean doWipe(boolean var1) {
      if (var1) {
         if ((this.animeTimer & 3) == 0 && wipeLevel < 8) {
            ++wipeLevel;
         }

         return wipeLevel >= 8;
      } else {
         if ((this.animeTimer & 3) == 0 && wipeLevel > 0) {
            --wipeLevel;
         }

         return wipeLevel == 0;
      }
   }

   void drawWipe() {
      if (wipeLevel > 0) {
         gg.setColor(0);
         int var1;
         if (wipeDir) {
            for(var1 = 0; var1 < 240; var1 += 8) {
               gg.fillRect(0, var1, 240, wipeLevel);
            }
         } else {
            for(var1 = 0; var1 < 240; var1 += 8) {
               gg.fillRect(0, var1 + 7 - wipeLevel, 240, wipeLevel);
            }
         }
      }

   }

   private void moveEnding() {
      ringcount = 0;
      timecount = 0;
      timecount2 = 0;
      if (this.endingCount > 0) {
         --this.endingCount;
      }

      if (this.endingStep >= 4 && this.rnd(15) == 0) {
         if (this.rnd(2) == 0) {
            this.SetObj2(OBJ2_FRIC + this.rnd(7), -20, this.PlayerPosY() - (30 + this.rnd(120)), 0, 300, 1, 0);
         } else {
            this.SetObj2(OBJ2_FRIC + this.rnd(7), 260, this.PlayerPosY() - (30 + this.rnd(120)), 0, 300, 0, 0);
         }
      }

      switch(this.endingStep) {
      case 2:
         this.doWipe(false);
         PlayerParam[10] = -2048;
         PlayerParam[12] = 1;
         this.playerRaidOn(-1);
         if (this.rnd(12) == 0) {
            switch(this.rnd(3)) {
            case 1:
               this.SetObj2(OBJ2_USAGI, this.PlayerPosX() - 300, this.PlayerPosY() - (30 + this.rnd(120)), 0, 300, 0, 0);
               break;
            case 2:
               this.SetObj2(OBJ2_RISU, this.PlayerPosX() - 300, this.PlayerPosY() - (30 + this.rnd(120)), 0, 300, 0, 0);
               break;
            default:
               this.SetObj2(OBJ2_FRIC, this.PlayerPosX() - 300, this.PlayerPosY() - (30 + this.rnd(120)), 0, 300, 0, 0);
            }
         }

         if (this.PlayerPosX() <= 64) {
            ++this.endingStep;
         }
         break;
      case 3:
         if (PlayerParam[10] < 768) {
            int[] var10000 = PlayerParam;
            var10000[10] += 80;
         } else {
            PlayerParam[10] = 768;
         }

         PlayerParam[12] = 0;
         if (this.PlayerPosX() >= 160) {
            PlayerParam[0] = 40960;
            PlayerParam[10] = 0;
            this.endingCount = 265;
            ++this.endingStep;
         }
         break;
      case 4:
         this.playerStandCount = 120;
         if (this.endingCount > 0) {
            break;
         }

         ++this.endingStep;
      case 5:
         this.endingAnim = 0;
         this.endingLogoPosX += 8;
         if (this.endingLogoPosX >= 46) {
            this.endingCount = 48;
            ++this.endingStep;
         }
         break;
      case 6:
         muteki2count = 1;
         this.endingAnim = 1;
         if (this.endingCount > 0) {
            break;
         }

         this.endingCount = 10;
         ++this.endingStep;
      case 7:
         muteki2count = 1;
         this.endingAnim = 2;
         if (this.endingCount > 0) {
            break;
         }

         this.endingCount = 10;
         ++this.endingStep;
      case 8:
         muteki2count = 1;
         this.endingAnim = 3;
         if (this.endingCount > 0) {
            break;
         }

         this.endingCount = 300;
         ++this.endingStep;
      case 9:
         muteki2count = 1;
         if (this.endingCount > 0) {
            break;
         }

         this.endingCount = 34;
         ++this.endingStep;
      case 10:
         muteki2count = 1;
         this.doWipe(true);
         if (this.endingCount <= 0) {
            this.endingType = 1;
            endingEggStep = 0;
            ++this.endingStep;
         }
         break;
      case 11:
         muteki2count = 1;
         if (!this.moveEndingEggmanB()) {
            break;
         }

         this.endingType = 2;
         nakaStep = 0;
         ++this.endingStep;
      case 12:
         muteki2count = 1;
         if (this.moveNaka()) {
            this.endingStep = 100;
         }
         break;
      case 100:
         muteki2count = 1;
         this.endingModeOn = false;
         mode = MODE_TITLE;
         this.ObjImageClear();
         this.TK_TitleInit(false);
         break;
      default:
         this.endingStep = 1;
      case 1:
         try {
            for(int var1 = 0; var1 < 150; ++var1) {
               this.m_imgObj[var1] = null;
            }

            this.DoGc();
            this.m_imgObj[ANIMAL] = this.createImage("/animal.png");
            this.m_imgObj[121] = this.createImage("/ED_00.png");
            this.m_imgObj[122] = this.createImage("/ED3.png");
            this.m_imgObj[123] = this.createImage("/endegg_b.png");
            this.m_imgObj[124] = this.createImage("/t_license3.png");
         } catch (Throwable var2) {
         }

         this.countClear();
         this.endingCount = 180;
         this.endingLogoPosX = -102;
         this.endingAnim = -1;
         this.endingType = 0;
         this.endingStringFadeLevel = 0;
         this.setWipe(0, true, 8);
         TimerClear = true;
         TimerStop = true;
         this.putNowLoading = false;
         bossModeOn = false;
         bossBreakOn = false;
         this.PlayMusic(16);
         ++this.endingStep;
      }

   }

   private void drawEnding() {
      this._drawEnding();
      this.drawWipe();
   }

   private void _drawEnding() {
      if (this.endingStep >= 2) {
         if (this.endingType == 1) {
            this.drawEndingEggmanB();
         } else if (this.endingType == 2) {
            this.drawNaka();
         } else if (this.endingAnim >= 0) {
            Image var10002 = this.m_imgObj[121];
            Graphics var10010 = gg;
            Graphics var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 96, 32, 0, this.endingLogoPosX, 110, 1 | 2);
            switch(this.endingAnim) {
            case 1:
               var10002 = this.m_imgObj[122];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 0, 32, 40, 0, 120, 96, 1 | 2);
               break;
            case 2:
               var10002 = this.m_imgObj[122];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 40, 48, 72, 0, 120, 96, 1 | 2);
               break;
            case 3:
               var10002 = this.m_imgObj[122];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 48, 0, 176, 136, 0, 162, 96, 1 | 2);
            }

         }
      }
   }

   private void boss1_draw_arai(int var1) {
      this.AraiDrawBoss(objectData);
   }

   private void boss2_draw_arai(int var1) {
      this.AraiDrawBoss(objectData);
   }

   private void boss3_draw_arai(int var1) {
      this.AraiDrawBoss(objectData);
   }

   private void boss4_draw_arai(int var1) {
      this.AraiDrawBoss(objectData);
   }

   private void boss5_draw_arai(int var1) {
      this.AraiDrawBoss(objectData);
      this.DrawBoss5Block(true);
   }

   private void drawBoss6Piston(int var1, int var2, int var3, int var4) {
      int var10 = this.bossOriginX + var1 - mapView[0];
      int var11 = this.bossOriginY + var2 - mapView[1];
      int var5;
      byte var6;
      byte var7;
      byte var8;
      byte var9;
      if (var3 == 0) {
         var5 = var4;
         var6 = -84;
         var7 = -24;
         var8 = 24;
         var9 = 3;
      } else {
         var5 = -var4;
         var6 = 84;
         var7 = 24;
         var8 = -24;
         var9 = 0;
      }

      Image var10002 = this.m_imgObj[145];
      int var10009 = var11 + var5;
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 64, 144, var9, var10, var10009, 1 | 2);
      switch((this.animeTimer >> 1) % 3) {
      case 1:
         var10002 = this.m_imgObj[145];
         var10009 = var11 + var5 + var8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 64, 0, 16, 16, var9, var10, var10009, 1 | 2);
         break;
      case 2:
         var10002 = this.m_imgObj[145];
         var10009 = var11 + var5 + var8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 64, 16, 16, 16, var9, var10, var10009, 1 | 2);
      }

      if (var4 > 0) {
         var10002 = this.m_imgObj[145];
         var10009 = var11 + var5 + var6;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 120, 32, 24, var9, var10, var10009, 1 | 2);
         var10002 = this.m_imgObj[145];
         var10009 = var11 + var5 + var6 + var7;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 120, 32, 24, var9, var10, var10009, 1 | 2);
         var10002 = this.m_imgObj[145];
         var10009 = var11 + var5 + var6 + var7 + var7;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 120, 32, 24, var9, var10, var10009, 1 | 2);
      }

   }

   private void drawBoss6Lamp(int var1) {
      int var2 = 1415 - mapView[0];
      int var3 = 60 - mapView[1];
      Image var10002;
      Graphics var10010;
      Graphics var10011;
      switch(var1) {
      case 0:
         return;
      case 1:
      default:
         break;
      case 2:
         var10002 = this.m_imgObj[145];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 80, 0, 16, 16, 0, var2, var3, 1 | 2);
         break;
      case 3:
         var10002 = this.m_imgObj[145];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 80, 16, 16, 16, 0, var2, var3, 1 | 2);
         break;
      case 4:
         var10002 = this.m_imgObj[145];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 80, 32, 16, 16, 0, var2, var3, 1 | 2);
      }

   }

   private void drawEggman(int var1, int var2, int var3, int var4) {
      boolean var5 = false;
      int var11;
      switch(var1) {
      case 1:
         var11 = 2 + (this.animeTimer & 1);
         break;
      case 2:
         var11 = 4;
         break;
      case 3:
         var11 = 5 + (this.animeTimer >> 1) % 3;
         break;
      default:
         var11 = 0 + (this.animeTimer >> 1 & 1);
      }

      short var6 = this.RectEggmanTbl[var11][0];
      short var7 = this.RectEggmanTbl[var11][1];
      short var8 = this.RectEggmanTbl[var11][2];
      short var9 = this.RectEggmanTbl[var11][3];
      byte var10;
      switch(var4) {
      case 1:
         var10 = 2;
         break;
      case 2:
         var10 = 1;
         break;
      case 3:
         var10 = 3;
         break;
      default:
         var10 = 0;
      }

      Image var10002 = this.m_imgObj[146];
      int var10008 = var2 - mapView[0];
      int var10009 = var3 - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var6, var7, var8, var9, var10, var10008, var10009, 1 | 2);
   }

   private void boss6_draw_arai(int var1) {
      int[] var2 = objectData;
      if (this.bossStep <= 200) {
         this.UpdateBossPos(var2);
         if (this.bossStep < 100) {
            this.drawEggman(this.bossFace, var2[2], var2[3], 0);
         } else {
            this.AraiDrawBoss(var2);
         }

         this.drawBoss6Piston(boss6PistonPos[0][0], boss6PistonPos[0][1], 0, boss6Piston[0] / 100);
         this.drawBoss6Piston(boss6PistonPos[1][0], boss6PistonPos[1][1], 0, boss6Piston[1] / 100);
         this.drawBoss6Piston(boss6PistonPos[2][0], boss6PistonPos[2][1], 1, boss6Piston[2] / 100);
         this.drawBoss6Piston(boss6PistonPos[3][0], boss6PistonPos[3][1], 1, boss6Piston[3] / 100);
         switch(boss6Lamp) {
         case 0:
            this.drawBoss6Lamp(1);
            break;
         case 1:
         default:
            this.drawBoss6Lamp(2);
            break;
         case 2:
         case 3:
            this.drawBoss6Lamp(3 + (this.animeTimer & 1));
         }

         this.drawWipe();
      }
   }

   private void AraiOfsDraw(Image var1, int var2, int var3, int var4, short[] var5) {
      short var6 = var5[0];
      short var7 = var5[1];
      short var8 = var5[2];
      short var9 = var5[3];
      int var10 = var5[4];
      int var11 = var5[5];
      byte var12;
      switch(var4) {
      case 1:
         var12 = 2;
         var10 = -var10;
         break;
      case 2:
         var12 = 1;
         var11 = -var11;
         break;
      case 3:
         var12 = 3;
         var10 = -var10;
         var11 = -var11;
         break;
      default:
         var12 = 0;
      }

      int var10008 = var2 - mapView[0] + var10;
      int var10009 = var3 - mapView[1] + var11;
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var1, var6, var7, var8, var9, var12, var10008, var10009, 1 | 2);
   }

   private void DrawBossFace(int var1, int var2, int var3, int var4, int var5) {
      switch(var1) {
      case 0:
         if (var5 == 0) {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[1]);
         } else {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[2]);
         }
         break;
      case 1:
         if (var5 == 0) {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[3]);
         } else {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[4]);
         }
         break;
      case 2:
         if (var5 == 0) {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[1]);
         } else {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[6]);
         }
         break;
      case 3:
         this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[6]);
         break;
      case 4:
         this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[7]);
         break;
      case 5:
         if (var5 == 0) {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[21]);
         } else {
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[22]);
         }
         break;
      case 6:
         this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var4, this.RectBossTbl[0]);
      }

   }

   private void DrawBossPartsStage1(int var1, int var2, int var3, int var4) {
      int var7 = this.RectBossTbl[15][4];
      short var8 = this.RectBossTbl[15][5];
      int var9 = this.bossParam1;
      int var10 = this.bossAngle / 100;
      if (var3 == 1) {
         var7 = -var7;
      }

      while(var10 < 0) {
         var10 += 360;
      }

      int var11 = 180 + this.dSin(var10) * 90 / 100;
      if (var9 > 16) {
         this.AraiOfsDraw(this.m_imgObj[120], var1, var2, var3, this.RectBossTbl[15 + (this.animeTimer >> 2 & 1)]);
      }

      int var5;
      int var6;
      int var13;
      for(var13 = 0; var13 < 4; ++var13) {
         int var12 = (var13 + 1) * 16;
         var5 = var1 + this.dSin(var11) * var12 / 100 + var7;
         var6 = var2 + this.dCos(var11) * var12 / 100 + var8;
         if (var9 > 16 + var12) {
            this.AraiOfsDraw(this.m_imgObj[120], var5, var6, 0, this.RectBossTbl[17]);
         }

         if (var4 == 2 && Math.abs(var9 - (16 + var12)) < 4) {
            this.SetObj2(OBJ2_BAKUHATU, var5, var6, 0, 0, 0, 0);
         }
      }

      var5 = var1 + var7 + this.dSin(var11) * var9 / 100;
      var6 = var2 + var8 + this.dCos(var11) * var9 / 100;
      if (var4 == 2) {
         var13 = var1 + var7 + this.dSin(var11) * 96 / 100;
         int var14 = var2 + var8 + this.dCos(var11) * 96 / 100;
         this.AraiOfsDraw(this.m_imgObj[121], var13, var14, 0, this.RectBossBallTbl);
         if ((this.animeTimer & 7) == 4) {
            this.SetObj2(OBJ2_BAKUHATU, var13 + this.rnd(48) - 24, var14 + this.rnd(48) - 24, 0, 0, 0, 0);
         }
      } else {
         this.AraiOfsDraw(this.m_imgObj[121], var5, var6, 0, this.RectBossBallTbl);
      }

   }

   private void DrawBossPartsStage2(int var1, int var2, int var3, int var4) {
   }

   private void DrawBossPartsStage3(int var1, int var2, int var3, int var4) {
      this.AraiOfsDraw(this.m_imgObj[120], var1, var2, var3, this.RectBossTbl[19]);
   }

   private void DrawBossPartsStage4(int var1, int var2, int var3, int var4) {
      this.AraiOfsDraw(this.m_imgObj[120], var1, var2, var3, this.RectBossTbl[19]);
   }

   private void DrawBossPartsStage5(int var1, int var2, int var3, int var4) {
      this.AraiOfsDraw(this.m_imgObj[120], var1, var2 + 8 - 32 + this.bossParam1, var3, this.RectBossTbl[20]);
   }

   private void DrawBossPartsStage6(int var1, int var2, int var3, int var4) {
   }

   private void DrawBossParts(int var1, int var2, int var3) {
      switch(this.bossType) {
      case 0:
         this.DrawBossPartsStage1(var1, var2, var3, 0);
         break;
      case 1:
         this.DrawBossPartsStage2(var1, var2, var3, 0);
         break;
      case 2:
         this.DrawBossPartsStage3(var1, var2, var3, 0);
         break;
      case 3:
         this.DrawBossPartsStage4(var1, var2, var3, 0);
         break;
      case 4:
         this.DrawBossPartsStage5(var1, var2, var3, 0);
         break;
      case 5:
         this.DrawBossPartsStage6(var1, var2, var3, 0);
      }

   }

   private void DrawBossEnd(int var1, int var2, int var3) {
      switch(this.bossType) {
      case 0:
         this.DrawBossPartsStage1(var1, var2, var3, 2);
         break;
      case 1:
         this.DrawBossPartsStage2(var1, var2, var3, 2);
         break;
      case 2:
         this.DrawBossPartsStage3(var1, var2, var3, 2);
         break;
      case 3:
         this.DrawBossPartsStage4(var1, var2, var3, 2);
         break;
      case 4:
         this.DrawBossPartsStage5(var1, var2, var3, 2);
         break;
      case 5:
         this.DrawBossPartsStage6(var1, var2, var3, 2);
      }

   }

   private void AraiDrawBoss(int[] var1) {
      int var10 = this.animeTimer >> 3 & 1;
      int var11 = this.animeTimer >> 2 & 1;
      int var12 = this.animeTimer >> 1 & 1;
      int var13 = this.animeTimer & 1;
      if (this.bossStep >= 2) {
         int var4 = var1[1];
         this.UpdateBossPos(var1);
         int var2 = var1[2];
         int var3 = var1[3];
         int var5 = this.bossAnim;
         int var8 = this.bossDir;
         int var9 = (this.animeTimer & 1) == 0 && this.bossFlash > 0 ? 1 : 0;
         switch(var5) {
         case 0:
            this.DrawBossParts(var2, var3, var8);
            this.DrawBossFace(this.bossFace, var2, var3, var8, var11);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8 + var9]);
            break;
         case 1:
            this.DrawBossParts(var2, var3, var8);
            this.DrawBossFace(1, var2, var3, var8, var11);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8]);
            break;
         case 2:
            this.DrawBossParts(var2, var3, var8);
            this.DrawBossFace(this.bossFace, var2, var3, var8, var11);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[11 + var13]);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8 + var9]);
            break;
         case 3:
            this.DrawBossEnd(var2, var3, var8);
            this.DrawBossFace(2, var2, var3, var8, var10);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8]);
            if (var11 != 0) {
               this.SetObj2(OBJ2_BAKUHATU, var2 + this.rnd(32) - 16, var3 + this.rnd(32) - 16 - 8, 0, 0, 0, 0);
            }
            break;
         case 4:
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[7]);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8]);
            break;
         case 5:
            this.DrawBossFace(0, var2, var3, var8, var11);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8]);
            if (var12 == 1) {
               this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[18]);
            }

            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[13 + var11]);
            break;
         case 6:
            this.DrawBossFace(0, var2, var3, var8, var11);
            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[8]);
            if (var12 != 0) {
               this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[18]);
            }

            this.AraiOfsDraw(this.m_imgObj[120], var2, var3, var8, this.RectBossTbl[11 + var13]);
            break;
         case 7:
            this.DrawBossFace(6, 1760, 168, 1, 0);
            this.AraiOfsDraw(this.m_imgObj[147], 1760, 168, var8, this.RectBoss2Tbl[0]);
            this.drawEggman(3, var2, var3, 1);
            break;
         case 8:
            this.DrawBossFace(0, var2, var3, 1, var11);
            this.AraiOfsDraw(this.m_imgObj[147], var2, var3, 1, this.RectBoss2Tbl[0]);
            break;
         case 9:
            this.DrawBossFace(0, var2, var3, 1, var11);
            this.AraiOfsDraw(this.m_imgObj[147], var2, var3, 1, this.RectBoss2Tbl[1]);
            break;
         case 10:
            this.DrawBossFace(0, var2, var3, 1, var11);
            this.AraiOfsDraw(this.m_imgObj[147], var2, var3, 1, this.RectBoss2Tbl[2]);
            break;
         case 11:
            this.DrawBossFace(5, var2, var3, 1, var11);
            this.AraiOfsDraw(this.m_imgObj[147], var2, var3, 1, this.RectBoss2Tbl[3]);
            if (var11 != 0) {
               this.SetObj2(OBJ2_BAKUHATU, var2 + this.rnd(32) - 16, var3 + this.rnd(32) - 16 - 8, 0, 0, 0, 0);
            }
         }

      }
   }

   private void startContinue() {
      continueStep = 0;
      mode = MODE_CONTINUE;
   }

   private void moveContinue() {
      if (continueCount > 0 && continueStep < 4) {
         --continueCount;
      }

      switch(continueStep) {
      case 3:
         break;
      case 4:
         ++continueSonicAnim2;
         if (continueSonicAnim2 > 8) {
            ++continueSonicPosX;
         }

         if (continueSonicAnim2 > 12) {
            continueSonicPosX += 20;
         }

         if (continueSonicAnim2 >= 45) {
            ++continueStep;
         }

         return;
      case 5:
         this.StopMusic();
         this.resultContinue(true);
         continueStep = 999;
         return;
      case 999:
         return;
      default:
         continueStep = 1;
      case 1:
         continueSonicPosX = ContinueSonicCenterX;
         continueSonicPosY = -20;
         continueCount = 164;
         continueResult = 0;
         continueSonicAnim = -1;
         continueSonicAnim2 = -1;

         try {
            for(int var1 = 120; var1 < 150; ++var1) {
               this.m_imgObj[var1] = null;
            }

            this.DoGc();
            this.m_imgObj[121] = this.createImage("/continue.png");
         } catch (Throwable var2) {
         }

         this.PlayMusic(22);
         ++continueStep;
      case 2:
         continueSonicAnim = 0;
         continueSonicPosY += 15;
         if (continueSonicPosY < ContinueSonicBottomY) {
            return;
         }

         continueSonicPosY = ContinueSonicBottomY;
         continueSonicAnim = 1;
         ++continueStep;
      }

      if (continueCount == 0) {
         this.resultContinue(false);
         continueStep = 999;
      } else if (KeyPress[0]) {
         continueSonicAnim = 2;
         continueSonicAnim2 = 0;
         this.clearKey();
         ++continueStep;
      }

   }

   private void drawContinue() {
      int xpos = continueSonicPosX;
      int ypos = continueSonicPosY;
      byte xcenter = ContinueSonicCenterX;
      gg.setColor(0);
      gg.fillRect(0, 0, 240, 240);
      if (continueStep >= 2) {
         int var4;
         short var5;
         short var6;
         byte var7;
         byte var8;
         Image var10001;
         Graphics var10009;
         Graphics var10010;
         if (continueSonicAnim == 0) {
            var10001 = this.m_imgObj[121];
            var10009 = gg;
            var10010 = gg;
            gg.drawRegion(var10001, 0, 0, 48, 32, 0, xcenter, ContinueSonicBottomY, 1 | 2);
            var4 = this.animeTimer % 5;
            var5 = ContinueSonicTbl[var4][0];
            var6 = ContinueSonicTbl[var4][1];
            var7 = 48;
            var8 = 24;
            int var10 = ContinueSonicTbl[var4][2] == 0 ? 0 : 2;
            var10001 = this.m_imgCmd[SONIC_N];
            var10009 = gg;
            var10010 = gg;
            gg.drawRegion(var10001, var5, var6, var7, var8, var10, xpos, ypos, 1 | 2);
         } else if (continueSonicAnim == 1) {
            var4 = this.animeTimer >> 2 & 3;
            var5 = ContinueSonicTbl2[var4][0];
            var6 = ContinueSonicTbl2[var4][1];
            var7 = 48;
            var8 = 32;
            var10001 = this.m_imgObj[121];
            var10009 = gg;
            var10010 = gg;
            gg.drawRegion(var10001, var5, var6, var7, var8, 0, xcenter, ContinueSonicBottomY, 1 | 2);
         } else if (continueSonicAnim == 2) {
            var4 = continueSonicAnim2;
            if (continueSonicAnim2 < 0) {
               boolean var13 = false;
            }

            byte var9;
            int var14;
            byte var15;
            if (continueSonicAnim2 < 1) {
               var14 = 48;
               var15 = 120;
               var7 = 48;
               var8 = 24;
               var9 = -16;
            } else {
               if (continueSonicAnim2 < 12) {
                  var4 = (continueSonicAnim2 >> 1) % 6;
               } else {
                  var4 = 6 + (continueSonicAnim2 - 12) % 4;
               }

               var14 = var4 * 40;
               var15 = 40;
               var7 = 40;
               var8 = 40;
               var9 = -4;
            }

            var10001 = this.m_imgObj[121];
            var10009 = gg;
            var10010 = gg;
            gg.drawRegion(var10001, 0, 0, 48, 32, 0, xcenter, ContinueSonicBottomY, 1 | 2);
            var10001 = this.m_imgCmd[SONIC_N];
            int var10008 = ypos + var9;
            var10009 = gg;
            var10010 = gg;
            gg.drawRegion(var10001, var14, var15, var7, var8, 0, xpos, var10008, 1 | 2);
         }

         var10001 = this.m_imgObj[121];
         int var10007 = xcenter - 16;
         var10009 = gg;
         var10010 = gg;
         gg.drawRegion(var10001, 96, 0, 16, 16, 0, var10007, 128, 1 | 2);
         var10001 = this.m_imgObj[121];
         var10007 = xcenter + 16;
         var10009 = gg;
         var10010 = gg;
         gg.drawRegion(var10001, 96, 0, 16, 16, 0, var10007, 128, 1 | 2);
         int var11 = continueCount / 15 % 10;
         int var12 = continueCount / 15 / 10 % 10;
         var10001 = this.m_imgCmd[WINDOU_SUUJI];
         int var10002 = var12 * 7;
         var10007 = xcenter - 4;
         var10009 = gg;
         var10010 = gg;
         gg.drawRegion(var10001, var10002, 0, 7, 13, 0, var10007, 128, 1 | 2);
         var10001 = this.m_imgCmd[WINDOU_SUUJI];
         var10002 = var11 * 7;
         var10007 = xcenter + 4;
         var10009 = gg;
         var10010 = gg;
         gg.drawRegion(var10001, var10002, 0, 7, 13, 0, var10007, 128, 1 | 2);
         var10001 = this.m_imgObj[121];
         var10009 = gg;
         var10010 = gg;
         gg.drawRegion(var10001, 0, 64, 120, 16, 0, xcenter, 80, 1 | 2);
      }
   }

   private void IkeshitaLoadStageImage(int var1) {
      try {
         if (this.zoneNumber == 5) {
            this.m_imgObj[BURANKO_NFLAG] = this.createImage("/buranko_.png");
            this.m_imgObj[NOKO_NFLAG] = this.createImage("/noko.png");
            this.m_imgObj[DOOR_NFLAG] = this.createImage("/door.png");
            this.m_imgObj[BELTC_NFLAG] = this.createImage("/beltc.png");
            this.m_imgObj[YUKAE_NFLAG] = this.createImage("/yukae.png");
            this.m_imgObj[YUKAI_NFLAG] = this.createImage("/yukai.png");
            this.m_imgObj[84] = this.createImage("/beltcon.png");
            this.m_imgObj[BRYUKA_NFLAG] = this.createImage("/bryuka_sc.png");
         }

         if (this.zoneNumber == 0) {
            this.m_imgObj[THASHI_NFLAG] = this.createImage("/thashi.png");
            this.m_imgObj[BRKABE_SFLAG] = this.createImage("/brkabe_g.png");
         }

         if (this.zoneNumber == 2) {
            this.m_imgObj[BURANKO_NFLAG] = this.createImage("/buranko_m.png");
         }

         if (this.zoneNumber == 3) {
            this.m_imgObj[MFIRE_NFLAG] = this.createImage("/mfire.png");
            this.m_imgObj[BURANKO_NFLAG] = this.createImage("/buranko_s.png");
         }

         if (this.zoneNumber == 1 && this.stageNumber != 3) {
            this.m_imgObj[DAI_NFLAG] = this.createImage("/dai_la.png");
         }

         if (this.zoneNumber != 2) {
            this.m_imgObj[SWITCH2_NFLAG] = this.createImage("/switch2.png");
         }

         if (this.zoneNumber == 1 && this.stageNumber == 3) {
            this.m_imgObj[DAI4_NFLAG] = this.createImage("/z_dai4_l.png");
            this.m_imgObj[DAI_NFLAG] = this.createImage("/z_dai_la.png");
         }

         if (this.zoneNumber == 4) {
            this.m_imgObj[DAI2_0xE0] = this.createImage("/dai2_3.png");
         }

         this.m_imgObj[GOLE_NFLAG] = this.createImage("/gole.png");
         this.m_imgObj[TEKYU_NFLAG] = this.createImage("/tekyu.png");
         this.m_imgObj[BTEN_NFLAG ] = this.createImage("/bten.png");
      } catch (Throwable var3) {
      }

   }

   private boolean ObjectMoveChk(int var1, int var2, int var3, int var4) {
      return (var3 == -1 || 0 <= var1 - mapOxy[0] + var3 && 240 >= var1 - mapOxy[0] - var3) && (var4 == -1 || 0 <= var2 - mapOxy[1] + var4 && 240 >= var2 - mapOxy[1] - var4);
   }

   private void ring_sflag_ring_18_00_move_ikeshita(int var1) {
      byte var2 = 8;
      byte var3 = 8;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int var4 = this.ObjectColChk2(objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
      if (var4 >= 0) {
         if (objectData[5] == 0 && objectData[0] != -1) {
            objectData[5] = 1;
            objectData[10] = this.cpuTimer;
            ++ringcount;
         }
      } else if (objectData[12] != 0) {
         var4 = this.ObjectColChk2(objectData[2], objectData[12], objectData[6], objectData[12], var2, var3);
         if (var4 >= 0 && objectData[5] == 0 && objectData[0] != -1) {
            objectData[5] = 1;
            objectData[10] = this.cpuTimer;
            ++ringcount;
         }
      }

      if (objectData[5] == 1 && this.cpuTimer - objectData[10] >= 20) {
         objectData[0] = -1;
         objectData[5] = 0;
      }

   }

   private void ring_sflag_ring_00_18_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void buranko_nflag_move_ikeshita(int var1) {
      int var2 = this.dSin(this.animeTimer * 3) * 87;
      int var3 = this.dSin(objectData[6] * 3) * 87;
      objectData[6] = this.animeTimer;
      int var5 = objectData[4] + 1;
      if (objectData[4] == 5) {
         var2 = -var2;
         var3 = -var3;
      } else if (objectData[19] == 1) {
         var2 = -var2;
         var3 = -var3;
      }

      byte var6 = 24;
      byte var7 = 8;
      int var4;
      if (this.zoneNumber == 3) {
         var6 = 44;
         var7 = 24;
         var4 = var5 << 4;
         var4 += 8;
      } else if (this.zoneNumber == 5) {
         var6 = 24;
         var7 = 24;
         var4 = var5 << 4;
         var4 -= 24;
      } else {
         var4 = var5 << 4;
         var4 -= 8;
      }

      byte var8 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var8 = 20;
      }

      int var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100, objectData[2] + this.dSin(180 + var3 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var3 / 100) * var4 / 100, var6, var7);
      int[] var10000;
      if (this.zoneNumber == 3) {
         if (var9 == 0) {
            PlayerParam[1] = objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - var7 << 8;
            var10000 = PlayerParam;
            var10000[0] += (this.dSin(180 + var2 / 100) * var4 - this.dSin(180 + var3 / 100) * var4 << 8) / 100;
            this.setRaidOnSize(objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, var6);
            this.playerRaidOn(objectData[22]);
         } else {
            var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - 4, objectData[2] + this.dSin(180 + var3 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var3 / 100) * var4 / 100 - 4, 32, 20);
            if (var9 >= 0) {
               if (var9 == 0) {
                  PlayerParam[1] = objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - var7 << 8;
                  var10000 = PlayerParam;
                  var10000[0] += (this.dSin(180 + var2 / 100) * var4 - this.dSin(180 + var3 / 100) * var4 << 8) / 100;
                  this.setRaidOnSize(objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, var6);
                  this.playerRaidOn(objectData[22]);
               } else if (Math.abs(objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100 - this.PlayerPosX()) < 44 && Math.abs(objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - 4 - (this.PlayerPosY() - var8) + 1) < var8 + 24) {
                  this.playdamageset();
               }
            }
         }
      } else if (this.zoneNumber != 5) {
         if (var9 != 0) {
            var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 + 5, objectData[2] + this.dSin(180 + var3 / 100) * var4 / 100, objectData[3] + this.dCos(180 + var3 / 100) * var4 / 100 + 5, var6, var7);
         }

         if (var9 == 0) {
            PlayerParam[1] = objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - var7 << 8;
            var10000 = PlayerParam;
            var10000[0] += (this.dSin(180 + var2 / 100) * var4 - this.dSin(180 + var3 / 100) * var4 << 8) / 100;
            this.setRaidOnSize(objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100, var6);
            this.playerRaidOn(objectData[22]);
         }
      } else if (var9 >= 0) {
         this.playdamageset();
      } else if (Math.abs(objectData[2] + this.dSin(180 + var2 / 100) * var4 / 100 - this.PlayerPosX()) < 12 + var6 && Math.abs(objectData[3] + this.dCos(180 + var2 / 100) * var4 / 100 - (this.PlayerPosY() - var8)) < var8 + var7) {
         this.playdamageset();
      }

      if (raidOn && raidObjectNum == objectData[20] && var9 != 0) {
         raidOn = false;
      }

   }

   private void hashi_nflag_move_ikeshita(int var1) {
      int var2 = 0;
      boolean var4 = true;
      byte var6 = 8;
      byte var7 = 8;
      boolean var8 = false;
      boolean var9 = false;
      boolean var10 = true;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int var12 = this.cpuTimer - objectData[14];
      objectData[14] = this.cpuTimer;
      int var5 = 99;
      int var11 = 999;

      for(int var3 = 0; var3 < 12; ++var3) {
         var2 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 4, 12, objectData[2] - 96 + (var3 << 4), objectData[3] + objectData[10], objectData[6] - 96 + (var3 << 4), objectData[7] + objectData[10], var6, var7);
         if (var2 >= 0 && var2 != 3) {
            var9 = true;
            PlayerParam[1] = objectData[3] - var7 << 8;
            if (Math.abs(this.PlayerPosX() - (objectData[2] - 8)) < var11) {
               var5 = var3;
               var11 = Math.abs(this.PlayerPosX() - objectData[2]);
            }
         }
      }

      int[] var10000;
      if (var5 != 99) {
         PlayerParam[1] = objectData[3] - var7 + objectData[10] << 8;
         this.setRaidOnSize(objectData[2], 96);
         this.playerRaidOn(objectData[22]);
         objectData[5] = var5;
         int var13;
         if (var5 <= 6) {
            var13 = var5;
         } else {
            var13 = 6 - var5 % 6;
         }

         var10000 = objectData;
         var10000[10] += var12;
         if (objectData[10] >= var13 * 2) {
            objectData[10] = var13 * 2;
         }

         if (var5 == 0 || var5 == 11) {
            objectData[10] = 0;
         }
      } else {
         var10000 = objectData;
         var10000[10] -= var12;
         if (objectData[10] <= 0) {
            objectData[10] = 0;
         }
      }

      if (var9) {
         var2 = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var2 != 0) {
         raidOn = false;
      }

   }

   private void thashi_nflag_move_ikeshita(int var1) {
      byte var3 = 8;
      byte var4 = 12;
      boolean var5 = true;
      int[] var10000 = new int[]{-4, 4};

      for(int var2 = 0; var2 < 12; ++var2) {
         if ((this.animeTimer / 10 + (12 - var2)) % 7 == 2) {
            int var7 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 96 + (var2 << 4), objectData[3], objectData[2] - 96 + (var2 << 4), objectData[3], var3, var4);
            if (var7 >= 0) {
               this.playdamageset();
            }
         }
      }

   }

   private void break_sflag_move_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = true;
      boolean var5 = false;
      int var6 = 0;
      boolean var7 = false;
      int var8 = -1;
      int var9 = -99;
      boolean var10 = false;
      int var11 = 999;
      boolean var12 = false;
      byte var13 = 0;
      if (objectData[4] != 0) {
         var13 = -1;
      }

      int var16 = objectData[2];
      int var17;
      if (objectData[5] == 0) {
         if (var13 == 0) {
            var17 = this.break_sflag_ike_yuka.length;
         } else {
            var17 = -this.break_sflag_ike_yuka.length;
         }

         var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 12, objectData[2] - var17, objectData[3] - this.break_sflag_ike_yuka[0] + 8, objectData[2] - var17, objectData[3] - this.break_sflag_ike_yuka[0] + 8, 8, 8);
         if (var8 >= 0) {
            PlayerParam[1] = objectData[3] - this.break_sflag_ike_yuka[0] + 8 - 8 << 8;
         }
      }

      int var4;
      if (objectData[5] != 0) {
         for(var4 = 0; var4 < 36; ++var4) {
            int var15 = this.cpuTimer / 2 - objectData[10] - ((var4 << 1) + var4);
            if (var15 < 0) {
               var6 = var4 / 6 << 3;
               break;
            }

            if (var4 == 29) {
               objectData[5] = 2;
            }
         }

         if (var6 >= 48) {
            objectData[5] = 2;
         }

         if (objectData[4] != 0) {
            var16 = objectData[2] - var6;
         } else {
            var16 = objectData[2] + var6;
         }
      }

      if (objectData[5] != 2) {
         var12 = false;

         for(var4 = 0; var4 < this.break_sflag_ike_yuka.length; ++var4) {
            if (var13 == 0) {
               var17 = var4;
            } else {
               var17 = this.break_sflag_ike_yuka.length - var4 - 1;
            }

            if (var13 == 0 && var6 <= var4 || var13 != 0 && this.break_sflag_ike_yuka.length - var6 >= var4) {
               var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 12, objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1), objectData[3] - this.break_sflag_ike_yuka[var17], objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1), objectData[3] - this.break_sflag_ike_yuka[var17], 1, 1);
               if (var8 >= 0 && var8 == 0 && var11 > Math.abs(this.PlayerPosX() - (objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1)))) {
                  var11 = Math.abs(this.PlayerPosX() - (objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1)));
                  var9 = var17;
               }

               if (var9 != -99 && 8 > Math.abs(this.PlayerPosX() - (objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1))) && this.PlayerPosY() - (objectData[3] - this.break_sflag_ike_yuka[var17]) > 0 && this.PlayerPosY() - (objectData[3] - this.break_sflag_ike_yuka[var17]) <= 64 && var11 > Math.abs(this.PlayerPosX() - (objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1)))) {
                  var11 = Math.abs(this.PlayerPosX() - (objectData[2] - this.break_sflag_ike_yuka.length + (var4 << 1)));
                  var9 = var17;
               }
            }
         }

         var8 = -1;
         if (var11 != 999) {
            objectData[17] = var9;
            PlayerParam[1] = objectData[3] - this.break_sflag_ike_yuka[var9] - 1 << 8;
            if (objectData[5] == 0) {
               objectData[10] = this.cpuTimer / 2;
            }

            objectData[5] = 1;
            int var14;
            if (var13 == 0) {
               var14 = var6;
            } else {
               var14 = this.break_sflag_ike_yuka.length - var6;
            }

            this.setRaidOnSize(var16, var14);
            this.playerRaidOn(objectData[22]);
            var8 = 0;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var8 != 0) {
         raidOn = false;
      }

   }

   private void yuka_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      boolean var3 = true;
      int var4 = -1;
      this.view_yuka(objectData[2], objectData[3], objectData[4]);
      byte var13;
      if (objectData[4] == 21) {
         var13 = 2;
      } else if (objectData[4] == 1) {
         var13 = 0;
      } else {
         var13 = 1;
      }

      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      objectData[17] = objectData[2];
      objectData[18] = objectData[3];
      int var10002;
      if (var13 != 2 && objectData[4] != 32) {
         var10002 = objectData[5]++;
         byte var17 = 32;
         if (objectData[4] == 35) {
            var17 = 32;
         } else if (objectData[4] == 1) {
            var17 = 16;
         } else if (objectData[4] == 41) {
            var17 = 16;
         } else if (objectData[4] == 34) {
            var17 = 24;
         } else if (objectData[4] == 43) {
            var17 = 32;
         } else if (objectData[4] == 33) {
            var17 = 16;
         } else if (objectData[4] == 42) {
            var17 = 24;
         }

         int var16;
         if (objectData[4] == 3) {
            var16 = objectData[18] >> 8;
            objectData[3] = objectData[9] + var16;
         } else if (objectData[4] == 7) {
            var7 = false;
         } else if (objectData[4] == 1) {
            var16 = this.dSin(objectData[5]) * var17 / 100 - var17;
            objectData[3] = objectData[9] + 8 + var16;
         } else {
            var16 = this.dSin(objectData[5]) * var17 / 100 - var17;
            objectData[3] = objectData[9] + var16;
         }
      }

      if (var13 == 2) {
         objectData[3] = objectData[9];
      }

      if (var13 == 2 && objectData[13] != 0) {
         objectData[3] = objectData[9] + 15;
         if (objectData[16] == 0) {
            objectData[16] = 1;
         }
      }

      objectData[13] = 0;
      int var10 = 999;
      int var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[17], objectData[18], this.yuka_nflag_yuka_w[var13], this.yuka_nflag_ike_yuka[var13][0]);
      if (var14 >= 0) {
         if (var14 == 1) {
            PlayerParam[0] = objectData[2] - this.yuka_nflag_yuka_w[var13] - 12 << 8;
            PlayerParam[10] = 0;
            var4 = -99;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var14 == 2) {
            PlayerParam[0] = objectData[2] + this.yuka_nflag_yuka_w[var13] + 12 + 1 << 8;
            PlayerParam[10] = 0;
            var4 = -99;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         }
      }

      int var5;
      for(var5 = 0; var5 < this.yuka_nflag_ike_yuka[var13].length; ++var5) {
         var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 2, 13, objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1), objectData[3] - this.yuka_nflag_ike_yuka[var13][var5], objectData[17] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1), objectData[18] - this.yuka_nflag_ike_yuka[var13][var5], 1, 1);
         if (var14 >= 0 && var14 == 0 && var10 > Math.abs(this.PlayerPosX() - (objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1)))) {
            var10 = Math.abs(this.PlayerPosX() - (objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1)));
            var4 = var5;
         }

         if (var4 != -99 && 8 >= Math.abs(this.PlayerPosX() - (objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1))) && this.PlayerPosY() - (objectData[3] - this.yuka_nflag_ike_yuka[var13][var5]) >= -1 && this.PlayerPosY() - (objectData[3] - this.yuka_nflag_ike_yuka[var13][var5]) <= 64 && var10 > Math.abs(this.PlayerPosX() - (objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1)))) {
            var10 = Math.abs(this.PlayerPosX() - (objectData[2] - this.yuka_nflag_ike_yuka[var13].length + (var5 << 1)));
            var4 = var5;
         }
      }

      byte var15 = -1;
      if (var10 != 999) {
         PlayerParam[1] = objectData[3] - this.yuka_nflag_ike_yuka[var13][var4] + 4 << 8;
         this.setRaidOnSize(objectData[2], this.yuka_nflag_ike_yuka[var13].length);
         this.playerRaidOn(objectData[22]);
         var15 = 0;
         if (var13 == 2) {
            objectData[13] = 1;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var15 != 0) {
         raidOn = false;
      }

      var3 = true;
      if (objectData[16] != 0) {
         var10002 = objectData[16]++;
         if (objectData[16] == 60) {
            objectData[15] = this.animeTimer - 1;
         }

         if (objectData[16] >= 60) {
            boolean var11 = false;
            boolean var12 = false;

            for(var5 = 0; var5 < 8; ++var5) {
               int var18 = this.animeTimer - objectData[15];
               int var19 = var18 - 1;
               if (var19 < 0) {
                  var19 = 0;
               }

               if (var5 << 3 < var18) {
                  var18 = var5 << 3;
                  var19 = var18;
               }

               var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + (var18 << 1) - 56, objectData[3] - this.yuka_nflag_ike_yuka[var13][var18], objectData[17] + (var19 << 1) - 56, objectData[18] - this.yuka_nflag_ike_yuka[var13][var19], 6, 6);
               if (var14 >= 0) {
                  this.playdamageset();
                  break;
               }
            }
         }
      }

   }

   private void turi_nflag_move_ikeshita(int var1) {
      byte var3 = 56;
      byte var4 = 12;
      boolean var5 = true;
      this.view_turi(objectData[8], objectData[9], 0);
      objectData[7] = objectData[3];
      int[] var10000;
      int var10002;
      if (objectData[4] == 128) {
         if (objectData[18] == 0) {
            objectData[3] = objectData[9] + 112;
         }

         objectData[18] = 1;
         if (!switchflag[128] && !switchflag[129]) {
            if (objectData[3] < objectData[9] + 112) {
               var10000 = objectData;
               var10000[3] += 2;
               if (objectData[3] > objectData[9] + 112) {
                  objectData[3] = objectData[9] + 112;
               }
            }
         } else if (objectData[3] > objectData[9]) {
            var10002 = objectData[3]--;
         }

         int[][] var6 = this.searchObject(BOX_SFLAG, 0);

         for(int var2 = 0; var2 < var6.length; ++var2) {
            if (objectData[2] - 56 - (var6[var2][2] - 16) <= 32 && objectData[2] - 56 - (var6[var2][2] - 16) >= -112 && switchflag[128] && objectData[3] < objectData[9] + 16) {
               objectData[3] = objectData[9] + 16;
            }
         }
      } else {
         short var8 = 160;
         if (objectData[4] == 17) {
            var3 = 48;
         } else if (objectData[4] == 2) {
            var3 = 56;
            var8 = 80;
         } else if (objectData[4] == 35) {
            var3 = 16;
            var8 = 120;
         } else if (objectData[4] == 18) {
            var3 = 48;
            var8 = 80;
         }

         if (objectData[5] == 0) {
            if (objectData[3] < objectData[9] + var8) {
               var10000 = objectData;
               var10000[3] += 4;
               if (objectData[3] >= objectData[9] + var8) {
                  objectData[3] = objectData[9] + var8;
                  objectData[5] = 1;
               }
            }
         } else if (objectData[5] < 60) {
            var10002 = objectData[5]++;
         } else if (objectData[3] > objectData[9]) {
            var10002 = objectData[3]--;
            if (objectData[3] == objectData[9]) {
               objectData[5] = 0;
            }
         }
      }

      int var7 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[7], var3, var4);
      if (var7 >= 0) {
         if (var7 == 0) {
            PlayerParam[1] = objectData[3] - var4 << 8;
            this.setRaidOnSize(objectData[2], var3);
            this.playerRaidOn(objectData[22]);
            if (objectData[3] - 24 < objectData[9]) {
               this.setHeadHit();
            }
         } else if (var7 == 1) {
            PlayerParam[0] = objectData[2] - var3 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var7 == 2) {
            PlayerParam[0] = objectData[2] + var3 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var7 == 3) {
            this.setHeadHit();
            PlayerParam[1] = objectData[3] + var4 + 12 + 12 + 1 << 8;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var7 != 0) {
         raidOn = false;
      }

      if (objectData[4] != 35) {
         var4 = 14;
         var3 = 40;
         var7 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + 32, objectData[2], objectData[7] + 32, var3, var4);
         if (var7 >= 0) {
            this.playdamageset();
         }
      }

   }

   private void toge_nflag_move_ikeshita(int var1) {
      if (this.zoneNumber != 1 || this.stageNumber != 0 || objectData[4] != 64 || objectData[19] == 0) {
         byte var2 = 20;
         byte var3 = 14;
         boolean var4 = false;
         objectData[7] = objectData[3];
         objectData[15] = objectData[2];
         if (objectData[4] == 64) {
            var2 = 64;
         } else if (objectData[4] == 16) {
            var2 = 16;
            var3 = 19;
         }

         if (objectData[4] == 48) {
            var2 = 30;
         } else {
            int var10002;
            if (objectData[4] == 18) {
               var2 = 14;
               var3 = 20;
               var10002 = objectData[6]++;
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
               var2 = 8;
               var3 = 18;
            } else if (objectData[4] == 82) {
               var2 = 20;
               var3 = 6;
               var10002 = objectData[6]++;
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
               var2 = 20;
               var10002 = objectData[6]++;
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
         }

         boolean var5 = false;
         int var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[15], objectData[7], var2, var3);
         if (var6 >= 0) {
            if (objectData[4] == 82) {
               if (var6 == 1 || var6 == 2) {
                  this.playdamageset();
                  var5 = true;
               }
            } else if (objectData[4] != 18 && objectData[4] != 16) {
               if (var6 == 0 && objectData[19] == 0) {
                  this.playdamageset2();
                  var5 = true;
               } else if (var6 == 3 && objectData[19] != 0) {
                  this.playdamageset();
                  var5 = true;
               }

               if (objectData[19] != 0 && this.zoneNumber == 1) {
                  if (var6 == 1) {
                     PlayerParam[0] = objectData[2] - var2 - 12 << 8;
                     PlayerParam[10] = 0;
                     if (PlayerParam[3] > 0) {
                        PlayerParam[3] = 0;
                     }
                  } else if (var6 == 2) {
                     PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
                     PlayerParam[10] = 0;
                     if (PlayerParam[3] < 0) {
                        PlayerParam[3] = 0;
                     }
                  }
               }
            } else if (var6 == 1 || var6 == 2) {
               this.playdamageset();
               var5 = true;
            }

            if (var6 == 0) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               this.setRaidOnSize(objectData[2], var2);
               this.playerRaidOn(objectData[22]);
               if (this.blockColChk2(this.PlayerPosX(), this.PlayerPosY() - 32)) {
                  if (PlayerParam[12] == 0) {
                     PlayerParam[0] = objectData[2] - var2 - 12 << 8;
                  } else {
                     PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
                  }
               }
            } else if (var6 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 << 8;
               PlayerParam[10] = 0;
               if (!var5 && KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var6 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (!var5 && KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var6 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
               if (PlayerParam[5] < 0) {
                  PlayerParam[5] = 0;
               }

               if (this.zoneNumber == 0) {
                  int[] var10000 = PlayerParam;
                  var10000[1] += 4096;
               }
            }
         }

         if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
            raidOn = false;
         }

      }
   }

   private void box_sflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      boolean var4 = false;
      boolean var6 = false;
      boolean var7 = true;
      int var8 = -1;
      boolean var9 = false;
      objectData[15] = objectData[2];
      objectData[16] = objectData[3];
      if (objectData[4] == 129) {
         var2 = 64;
         var3 = 16;
      }

      int var12 = objectData[2];
      int[] var10000;
      if (objectData[5] != 0 && objectData[10] == 0) {
         var10000 = objectData;
         var10000[3] += 2;
         if (this.blockColChk_Enemy(objectData[2] - var2 + 1, objectData[3] + var3) || this.blockColChk_Enemy(objectData[2] + var2 - 1, objectData[3] + var3)) {
            objectData[5] = 0;
            if (objectData[4] == 0) {
               objectData[6] = 1;
            }
         }
      }

      int var5;
      for(var5 = 0; var5 < 4; ++var5) {
         if (objectData[8] == this.box_sflag_ike_def_X[var5] && objectData[9] == this.box_sflag_ike_def_Y[var5]) {
            var8 = var5;
            break;
         }
      }

      if (objectData[5] == 0 && var8 != -1 && objectData[10] == 0 && Math.abs(objectData[2] - this.box_sflag_ike_col_X[var8]) <= 16 && Math.abs(objectData[3] - this.box_sflag_ike_col_Y[var8]) <= 16) {
         objectData[10] = 1;
      }

      if (objectData[17] == 0 && objectData[10] > 0 && objectData[3] < this.box_sflag_ike_col_Y[var8]) {
         var10000 = objectData;
         var10000[3] += 2;
         if (objectData[3] > this.box_sflag_ike_col_Y[var8]) {
            objectData[3] = this.box_sflag_ike_col_Y[var8];
         }
      }

      int var10002;
      if (objectData[17] == 0) {
         if (objectData[10] >= 1 && objectData[10] < 15) {
            var10002 = objectData[10]++;
         } else if (objectData[10] == 15) {
            var10000 = objectData;
            var10000[2] += this.box_sflag_ike_box_V[var8];
         } else if (objectData[10] >= 16) {
            var10002 = objectData[10]++;
            if (objectData[10] >= 46 && objectData[10] - 48 <= 96 && objectData[10] % 2 == 0) {
               var10002 = objectData[3]++;
            }
         }
      } else if (this.box_sflag_ike_box_V[var8] < 0) {
         if (objectData[18] < objectData[2]) {
            var10000 = objectData;
            var10000[2] += this.box_sflag_ike_box_V[var8];
         }
      } else if (objectData[18] > objectData[2]) {
         var10000 = objectData;
         var10000[2] += this.box_sflag_ike_box_V[var8];
      }

      objectData[17] = 0;
      if (objectData[4] != 129 && this.zoneNumber == 2) {
         int[][] var10;
         if (this.zoneNumber == 2 && this.stageNumber == 0 && objectData[4] == 0) {
            var10 = this.searchObject(TURI_NFLAG, 128);

            for(var5 = 0; var5 < var10.length; ++var5) {
               if (var10[var5][2] - 56 - (objectData[2] - 16) <= 32 && var10[var5][2] - 56 - (objectData[2] - 16) >= -112) {
                  objectData[3] = var10[var5][3] - 28;
               }
            }

            int[][] var11 = this.searchObject(SWITCH2_NFLAG, 128);
            switchflag[129] = false;

            for(var5 = 0; var5 < var11.length; ++var5) {
               if (var11[var5][2] - 8 - (objectData[2] - 16) <= 32 && var11[var5][2] - 8 - (objectData[2] - 16) >= -32 && var11[var5][3] - 8 - (objectData[3] - 16) <= 32 && var11[var5][3] - 8 - (objectData[3] - 16) >= -32) {
                  switchflag[129] = true;
                  switchflag2[129] = true;
               }
            }
         }

         if (objectData[4] != 0) {
            var10 = this.searchObject(TOGE_NFLAG, 16);

            for(var5 = 0; var5 < var10.length; ++var5) {
               if (var10[var5][2] - 20 - (objectData[2] - 16) <= 32 && var10[var5][2] - 20 - (objectData[2] - 16) >= -40 && var10[var5][3] - 20 - (objectData[3] - 16) <= 32 && var10[var5][3] - 20 - (objectData[3] - 16) >= -40 && objectData[10] == 15) {
                  objectData[10] = 16;
               }
            }
         }

         if (objectData[4] == 2) {
            var10 = this.searchObject(YOGANC_NFLAG, 16);

            for(var5 = 0; var5 < var10.length; ++var5) {
               if (var10[var5][2] - 20 - 64 - (objectData[2] - 16) <= 32 && var10[var5][2] - 20 - 64 - (objectData[2] - 16) >= -112 && var10[var5][3] - 48 + 16 - 16 - (objectData[3] - 16) <= 32 && var10[var5][3] - 48 + 16 - 16 - (objectData[3] - 16) >= -32 && var10[var5][5] > 1 && var10[var5][5] / 4 < 26) {
                  objectData[3] = var10[var5][3] - 48 + 16 - 16;
                  objectData[18] = var10[var5][2] - 10 + 16;
                  objectData[17] = 1;
                  break;
               }
            }
         }
      }

      var12 -= objectData[2];
      int var13 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[15], objectData[16], var2, var3);
      if (var13 >= 0) {
         if (var13 == 0) {
            boolean var14 = true;
            int var15 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] - var3, objectData[15], objectData[16] - var3, var2, 2);
            if (var15 == 1) {
               if ((KeyPress[4] && objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0) && objectData[10] == 0) {
                  var10002 = objectData[2]++;
                  if (this.blockColChk_Enemy(objectData[2] + var2, objectData[3] - var3)) {
                     var10002 = objectData[2]--;
                  }
               }
            } else if (var15 == 2 && (KeyPress[3] && objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0) && objectData[10] == 0) {
               var10002 = objectData[2]--;
               if (this.blockColChk_Enemy(objectData[2] - var2, objectData[3] - var3)) {
                  var10002 = objectData[2]++;
               }
            }

            PlayerParam[1] = objectData[3] - var3 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
            var9 = true;
            var10000 = PlayerParam;
            var10000[0] -= var12 << 8;
         }

         if (var13 == 1) {
            if (KeyPress[4] && (objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0)) {
               if (objectData[10] == 0) {
                  var10002 = objectData[2]++;
                  objectData[7] = 1;
                  if (this.blockColChk_Enemy(objectData[2] + var2, objectData[3] + var3 - 1)) {
                     var10002 = objectData[2]--;
                  }

                  if (objectData[4] == 129 && objectData[2] > objectData[8] + 64) {
                     objectData[2] = objectData[8] + 64;
                  }
               }

               this.playerPushSet();
            }

            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
         }

         if (var13 == 2) {
            if (KeyPress[3] && (objectData[5] == 0 || objectData[4] == 129 || objectData[4] == 0)) {
               if (objectData[10] == 0) {
                  var10002 = objectData[2]--;
                  objectData[7] = -1;
                  if (this.blockColChk_Enemy(objectData[2] - var2, objectData[3] + var3 - 1)) {
                     var10002 = objectData[2]++;
                  }
               }

               this.playerPushSet();
            }

            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
         }

         if (var13 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (!this.blockColChk_Enemy(objectData[2] - var2 + 1, objectData[3] + var3) && !this.blockColChk_Enemy(objectData[2] + var2 - 1, objectData[3] + var3)) {
         var10002 = objectData[5]++;
      }

      if (raidOn && raidObjectNum == objectData[20] && var13 != 0) {
         raidOn = false;
      }

      if (var9) {
         this.view_box_ride(objectData[2], objectData[3], objectData[4]);
      } else {
         this.view_box(objectData[2], objectData[3], objectData[4]);
      }

   }

   private void fblock_nflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      boolean var4 = false;
      this.view_fblock(objectData[2], objectData[3], objectData[4]);
      int[] var10000;
      if (objectData[4] != 2 && objectData[4] != 10) {
         if (objectData[4] == 1) {
            objectData[3] = objectData[9] + (this.dSin(objectData[5]) << 3) / 100 - 8;
            var10000 = objectData;
            var10000[5] += 6;
         }
      } else if (objectData[5] == 2) {
         objectData[3] = objectData[11] + this.dSin(this.cpuTimer) * 6 / 100 + 6;
      } else if (objectData[5] == 1) {
         int var10002 = objectData[10]++;
         var10000 = objectData;
         var10000[3] += objectData[10];
         if (this.blockColChk_Enemy(objectData[2] - var2 + 1, objectData[3] + var3) || this.blockColChk_Enemy(objectData[2] + var2 - 1, objectData[3] + var3)) {
            if ((objectData[8] != 4016 || objectData[9] != 1296) && (objectData[8] != 3920 || objectData[9] != 1328)) {
               objectData[5] = 2;
            } else {
               objectData[5] = 0;
            }

            var10000 = objectData;
            var10000[3] -= (objectData[3] + var3) % 16;
            objectData[11] = objectData[3];
         }
      } else if (objectData[3] < this.PlayerPosY() && objectData[2] - 100 < this.PlayerPosX()) {
         objectData[10] = 1;
         objectData[5] = 1;
      }

      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var5 >= 0) {
         if (var5 == 0) {
            PlayerParam[1] = objectData[3] - var3 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
         } else if (var5 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var5 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var5 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         } else if (var5 == 4 && raidObjectNum != objectData[20]) {
            if (this.PlayerPosX() < objectData[2]) {
               PlayerParam[0] = objectData[2] - var2 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

   }

   private void dainfla_move_ikeshita(int var1) {
      byte var2 = 48;
      byte var3 = 16;
      byte var4 = 0;
      byte var5 = 0;
      boolean var6 = false;
      boolean var7 = false;
      int var8 = 0;
      boolean var9 = false;
      if (objectData[4] == 57) {
         var2 = 63;
         var3 = 8;
      } else if (objectData[4] == 40) {
         var2 = 32;
         var3 = 8;
      }

      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int[] var10000;
      int var10002;
      if (objectData[4] == 7 || objectData[4] == 4) {
         var2 = 16;
         var3 = 8;
         if (this.zoneNumber == 1 && this.stageNumber == 0 && switchflag[2]) {
            objectData[5] = 1;
         }

         if (this.zoneNumber == 1 && this.stageNumber == 3) {
            objectData[5] = 1;
         }

         if (objectData[10] == 1) {
            var8 = objectData[2];
            var10002 = objectData[2]++;
            if (objectData[4] == 7) {
               if (this.blockColChk_Enemy(objectData[2] + var2, objectData[3] + var3 - 1 - 16)) {
                  var10002 = objectData[2]--;
                  objectData[10] = 2;
               }
            } else if (Math.abs(objectData[2] - objectData[8]) >= 96) {
               objectData[2] = objectData[8] + 96;
               objectData[10] = 2;
            }

            var8 -= objectData[2];
         } else if (objectData[10] == 2) {
            var10000 = objectData;
            var10000[3] += 5;
            if (this.blockColChk_Enemy(objectData[2] - var2 + 1, objectData[3] + var3) || this.blockColChk_Enemy(objectData[2] + var2 - 1, objectData[3] + var3)) {
               objectData[5] = 0;
               var10000 = objectData;
               var10000[3] -= (objectData[3] + var3) % 16;
               objectData[10] = 3;
            }
         }
      }

      if (objectData[4] == 1) {
         var2 = 16;
         var3 = 16;
         var8 = objectData[2];
         if (this.cpuTimer % 384 <= 144) {
            objectData[2] = objectData[8] - this.cpuTimer % 384 / 3;
         } else if (this.cpuTimer % 384 > 240) {
            objectData[2] = objectData[8] - (48 - (this.cpuTimer % 384 / 3 - 80));
         }

         var8 -= objectData[2];
      } else if (objectData[4] == 2) {
         var2 = 16;
         var3 = 16;
         objectData[3] = objectData[9] + 9;
         if (objectData[10] != 0) {
            var8 = objectData[2];
            var10002 = objectData[2]++;
            if (this.blockColChk_Enemy(objectData[2] + var2, objectData[3] + var3 - 1 - 16)) {
               var10002 = objectData[2]--;
            }

            var8 -= objectData[2];
         }
      } else if (objectData[4] == 65) {
         var8 = objectData[2];
         byte var11 = 48;
         objectData[2] = objectData[8] + this.dSin(this.cpuTimer) * var11 / 100 - 32 - 48;
         objectData[3] = objectData[9] + 8;
         var4 = 32;
         var5 = 0;
         var2 = 47;
         var8 -= objectData[2];
      } else if (objectData[4] == 57) {
         if (objectData[10] != 0) {
            var10002 = objectData[10]++;
         }

         if (objectData[10] > 5) {
            var8 = objectData[2];
            if (objectData[19] == 0) {
               if (this.cpuTimer - objectData[11] <= 32) {
                  objectData[2] = objectData[8] + (this.cpuTimer - objectData[11] << 2);
               } else if (this.cpuTimer - objectData[11] >= 500 && this.cpuTimer - objectData[11] < 532) {
                  objectData[2] = objectData[8] + (128 - (this.cpuTimer - objectData[11] - 500 << 2));
               }
            } else if (this.cpuTimer - objectData[11] <= 32) {
               objectData[2] = objectData[8] - (this.cpuTimer - objectData[11] << 2);
            } else if (this.cpuTimer - objectData[11] >= 500 && this.cpuTimer - objectData[11] < 532) {
               objectData[2] = objectData[8] - (128 - (this.cpuTimer - objectData[11] - 500 << 2));
            }

            if (this.cpuTimer - objectData[11] >= 532) {
               objectData[10] = 0;
               objectData[11] = 0;
               objectData[2] = objectData[8];
            }

            var8 -= objectData[2];
         } else {
            objectData[2] = objectData[8];
         }
      } else if (objectData[4] == 40) {
         if ((this.cpuTimer >> 1) % 256 < 128) {
            objectData[3] = objectData[9] - (this.cpuTimer >> 1) % 256;
         } else {
            objectData[3] = objectData[9] - (128 - ((this.cpuTimer >>> 1) % 256 - 128));
         }
      }

      int var10 = -1;
      if (objectData[4] != 7 && objectData[4] != 4 || objectData[5] != 0) {
         var10 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + var4, objectData[3] + var5, objectData[6] + var4, objectData[7] + var5, var2, var3);
         if (var10 >= 0 && var10 == 0) {
            PlayerParam[1] = objectData[3] - var3 + var5 << 8;
            if (objectData[4] == 2 || objectData[4] == 1 || objectData[4] == 65) {
               if (objectData[10] == 0) {
                  objectData[10] = 1;
                  objectData[11] = this.cpuTimer;
               }

               var10000 = PlayerParam;
               var10000[0] -= var8 << 8;
            }

            if (objectData[4] == 57) {
               if (objectData[2] + var4 - var2 <= this.PlayerPosX() && this.PlayerPosX() <= objectData[2] + var4 + var2 && objectData[10] == 0) {
                  objectData[10] = 1;
                  objectData[11] = this.cpuTimer;
               }

               var10000 = PlayerParam;
               var10000[0] -= var8 << 8;
            }

            if (objectData[4] == 7 || objectData[4] == 4) {
               if (objectData[5] == 1 && objectData[10] == 0) {
                  objectData[10] = 1;
               }

               var10000 = PlayerParam;
               var10000[0] -= var8 << 8;
            }

            this.setRaidOnSize(objectData[2] + var4, var2);
            this.playerRaidOn(objectData[22]);
            var9 = true;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var10 != 0) {
         raidOn = false;
      }

      if (var9) {
         this.view_dai_ride(objectData[2], objectData[3], objectData[4]);
      } else {
         this.view_dai(objectData[2], objectData[3], objectData[4]);
      }

   }

   private void yogan2_sflag_move_ikeshita(int var1) {
      int var2 = objectData[2];
      int var3 = objectData[5];
      if (objectData[5] == 0) {
         if (objectData[2] + 160 < this.PlayerPosX() && Math.abs(objectData[3] - this.PlayerPosY() + 12) < 43) {
            int var10002 = objectData[5]++;
         }
      } else {
         int[] var10000 = objectData;
         var10000[5] += 2;
         if (objectData[5] > 1152) {
            objectData[5] = 1152;
         }

         objectData[2] = objectData[5] + objectData[8];
      }

      if (objectData[2] + 96 > this.PlayerPosX() && Math.abs(objectData[3] - this.PlayerPosY() + 12) < 43) {
         this.playdamageset();
         if (objectData[5] > 0 && this.PlayerPosX() + 300 < objectData[2]) {
            objectData[2] = this.PlayerPosX() + 300;
         }
      }

      byte var4 = 120;
      byte var5 = 32;
      int var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 96, objectData[3], var2 - 96, objectData[3], var4, var5);
      if (var6 >= 0 && var6 != 0 && var6 != 1) {
         if (var6 == 2) {
            PlayerParam[0] = objectData[2] + var4 - 96 + 12 + 1 << 8;
            PlayerParam[10] = 0;
         } else if (var6 == 3) {
            PlayerParam[1] = objectData[3] + var5 + 12 + 12 + 1 << 8;
            PlayerParam[5] = 0;
            this.setHeadHit();
         }
      }

   }

   private void myogan_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = true;
      objectData[5] = this.cpuTimer / 2 % 180;
      if (objectData[5] == 0) {
         objectData[10] = 0;
      }

      objectData[2] = objectData[8];
      objectData[3] = objectData[9] - 356 + (objectData[5] << 3);
      if (Math.abs(this.PlayerPosX() - objectData[2]) < 44) {
         if (objectData[3] < objectData[9]) {
            if (objectData[3] - 240 < this.PlayerPosY() && objectData[3] > this.PlayerPosY() - 12) {
               this.playdamageset();
            }
         } else if (objectData[3] - 240 < this.PlayerPosY() && objectData[9] > this.PlayerPosY() - 12) {
            this.playdamageset();
         }
      }

      if (objectData[10] == 0) {
         if (objectData[3] > objectData[9]) {
            objectData[10] = 1;
         }
      } else {
         int var10002 = objectData[10]++;
      }

   }

   private void switch2_nflag_move_ikeshita(int var1) {
      byte var2 = 14;
      byte var3 = 7;
      if (switchflag[objectData[4]]) {
         var3 = 0;
      }

      switchflag[objectData[4]] = false;
      int var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var4 >= 0) {
         if (var4 == 0) {
            PlayerParam[1] = objectData[3] - var3 << 8;
            switchflag[objectData[4]] = true;
            switchflag2[objectData[4]] = true;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
            if (this.zoneNumber == 1 && this.stageNumber == 2) {
               if (objectData[4] == 15) {
                  tempWorldMapData[2][6] = 7;
               } else {
                  tempWorldMapData[2][6] = 75;
               }
            }
         } else if (var4 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var4 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
         raidOn = false;
      }

   }

   private void shima_nflag_move_ikeshita(int var1) {
      byte var2 = 32;
      byte var3 = 8;
      boolean var4 = false;
      boolean var5 = false;
      int var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      int var9 = objectData[2];
      objectData[11] = objectData[2];
      objectData[7] = objectData[3] + objectData[14];
      if (this.zoneNumber == 4) {
         var3 = 16;
      }

      if (objectData[4] == 5) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var7 = 64;
      } else if (objectData[4] == 1) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var7 = -64;
      }

      if (objectData[4] == 12) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var8 = 48;
      } else if (objectData[4] == 11) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var8 = -48;
      } else if (objectData[4] == 6) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var8 = 64;
      } else if (objectData[4] == 2) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var8 = -64;
      } else if (objectData[4] == 16) {
         objectData[5] = this.cpuTimer * 15 / 10 % 360;
         var6 = objectData[5];
         var8 = -32;
      }

      int[] var10000;
      int var10002;
      if (objectData[4] == 7) {
         if (switchflag[128] && objectData[5] <= 0) {
            var10002 = objectData[5]++;
         }

         if (objectData[5] > 0) {
            var10002 = objectData[5]++;
            if (objectData[5] > 90) {
               var10000 = objectData;
               var10000[3] -= 2;
               if (objectData[3] < 368) {
                  objectData[3] = 368;
               }
            }
         }

         var6 = objectData[5];
      } else if (objectData[4] == 3 && objectData[5] != 0) {
         var10002 = objectData[5]++;
         if (objectData[5] > 40) {
            var10000 = objectData;
            var10000[6] += 80;
            var10000 = objectData;
            var10000[18] += objectData[6];
         }

         var6 = objectData[5];
      }

      int var12 = this.dSin(var6) * var8 / 100;
      int var13;
      if (objectData[4] == 3) {
         var13 = objectData[18] >> 8;
         objectData[3] = objectData[9] + var13;
      } else if (objectData[4] == 7) {
         var5 = false;
      } else {
         var13 = this.dSin(var6) * var8 / 100;
         objectData[3] = objectData[9] + var13;
         var12 = this.dSin(var6) * var7 / 100;
         objectData[2] = objectData[8] + var12;
      }

      objectData[14] = 0;
      if (objectData[13] != 0) {
         objectData[14] = 4;
      }

      objectData[13] = 0;
      boolean var10 = true;
      boolean var11 = false;
      var9 -= objectData[2];
      int var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + objectData[14] + 32, objectData[11], objectData[7] + 32, var2, 8);
      if (var14 >= 0 && var14 == 0) {
         PlayerParam[0] = this.PlayerPosX() - var9 << 8;
         PlayerParam[1] = objectData[3] - var3 + objectData[14] << 8;
         if (objectData[4] == 3 && var6 == 0) {
            objectData[5] = 1;
         }

         this.setRaidOnSize(objectData[2], var2);
         this.playerRaidOn(objectData[22]);
         var11 = true;
         objectData[13] = 1;
      }

      if (raidOn && raidObjectNum == objectData[20] && var14 != 0) {
         raidOn = false;
      }

      var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + objectData[14] - 4, objectData[11], objectData[7] - 4, var2, 4);
      if (var14 >= 0 && var14 != 3) {
         PlayerParam[0] = this.PlayerPosX() - var9 << 8;
         PlayerParam[1] = objectData[3] - var3 + objectData[14] << 8;
         if (objectData[4] == 3 && var6 == 0) {
            objectData[5] = 1;
         }

         this.setRaidOnSize(objectData[2], var2);
         this.playerRaidOn(objectData[22]);
         objectData[13] = 1;
         var11 = true;
      }

      if (raidOn && raidObjectNum == objectData[20] && var14 != 0) {
         raidOn = false;
      }

   }

   private void dai2_nflag_move_ikeshita(int var1) {
      this.dai2_sflag_move_ikeshita(var1);
   }

   private void brkabe_sflag_move_ikeshita(int var1) {
      byte var3 = 8;
      byte var4 = 32;
      int var5 = -1;
      boolean var6 = false;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];

      for(int var2 = 0; var2 < 2; ++var2) {
         if (objectData[10 + var2] != 1) {
            boolean var7 = true;
            var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 8 + (var2 << 4), objectData[3], objectData[6] - 8 + (var2 << 4), objectData[7], var3, var4);
            if (var5 >= 0) {
               int[] var10000;
               if (var5 == 1) {
                  if (!PlayerJump && PlayerBall) {
                     if (PlayerParam[10] >= 300) {
                        var10000 = PlayerParam;
                        var10000[10] -= 10;
                        objectData[10 + var2] = 1;
                        if (this.zoneNumber == 0) {
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 330, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 300, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 320, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 310, 400, objectData[4]);
                        } else {
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 330, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 300, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 320, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 310, 400, 0);
                        }
                     } else if (PlayerParam[10] <= -300) {
                        var10000 = PlayerParam;
                        var10000[10] += 10;
                        objectData[10 + var2] = 1;
                        if (this.zoneNumber == 0) {
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 390, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 420, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 400, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 410, 400, objectData[4]);
                        } else {
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 390, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 420, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 400, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 410, 400, 0);
                        }
                     } else {
                        PlayerParam[10] = 0;
                     }
                  } else {
                     PlayerParam[10] = 0;
                  }

                  if (PlayerParam[10] == 0) {
                     PlayerParam[0] = objectData[2] - var3 - 12 - 8 + (var2 << 4) << 8;
                     if (KeyPress[4]) {
                        this.playerPushSet();
                     }
                  }
               } else if (var5 != 2) {
                  if (var5 == 3) {
                     PlayerParam[1] = objectData[3] + var4 + 12 + 12 + 1 << 8;
                     this.setHeadHit();
                  }
               } else {
                  if (!PlayerJump && PlayerBall) {
                     if (PlayerParam[10] >= 300) {
                        var10000 = PlayerParam;
                        var10000[10] -= 10;
                        objectData[10 + var2] = 1;
                        if (this.zoneNumber == 0) {
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 330, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 300, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 320, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 310, 400, objectData[4]);
                        } else {
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 330, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 300, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 320, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 310, 400, 0);
                        }
                     } else if (PlayerParam[10] <= -300) {
                        var10000 = PlayerParam;
                        var10000[10] += 10;
                        objectData[10 + var2] = 1;
                        if (this.zoneNumber == 0) {
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 390, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 420, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 400, 400, objectData[4]);
                           this.ShotObj2(26, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 410, 400, objectData[4]);
                        } else {
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16, 390, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 16, 420, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 32, 400, 400, 0);
                           this.ShotObj2(24, objectData[2] - 8 + (var2 << 4), objectData[3] - 16 - 16 + 48, 410, 400, 0);
                        }
                     } else {
                        PlayerParam[10] = 0;
                     }
                  } else {
                     PlayerParam[10] = 0;
                  }

                  if (PlayerParam[10] == 0) {
                     PlayerParam[0] = objectData[2] + var3 + 12 + 1 - 8 + (var2 << 4) << 8;
                     if (KeyPress[3]) {
                        this.playerPushSet();
                     }
                  }
               }
            }
         }
      }

      if (objectData[10] == 1 && objectData[11] == 1) {
         objectData[0] = 0;
      }

      if (var6) {
         var5 = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

   }

   private void pedal_nflag_move_ikeshita(int var1) {
      byte var2 = 24;
      byte var3 = 8;
      int var4 = this.dSin(this.animeTimer % 360 * 3 + objectData[4] * 90) * 80 / 100;
      int var5 = this.dCos(this.animeTimer % 360 * 3 + objectData[4] * 90) * 80 / 100;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[4] <= 3) {
         objectData[2] = objectData[8] + var4;
         objectData[3] = objectData[9] - var5;
      } else {
         objectData[2] = objectData[8] + var4;
         objectData[3] = objectData[9] + var5;
      }

      int var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
      if (var6 >= 0 && var6 == 0) {
         PlayerParam[1] = objectData[3] - var3 << 8;
         int[] var10000 = PlayerParam;
         var10000[0] += objectData[2] - objectData[6] << 8;
         this.setRaidOnSize(objectData[2], var2);
         this.playerRaidOn(objectData[22]);
      }

      if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
         raidOn = false;
      }

   }

   private void break2_nflag_move_ikeshita(int var1) {
      byte var4 = 8;
      byte var5 = 8;
      int var6 = 0;
      int var7 = 0;
      boolean var8 = false;
      objectData[10] = this.cpuTimer;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (this.zoneNumber == 3) {
         var5 = 7;
      }

      for(int var2 = 0; var2 < 8; ++var2) {
         if (objectData[15] != 0) {
            var6 = this.cpuTimer - objectData[16] - this.break2_nflag_ike_brockTimeTable[var2];
            if (var6 < 0) {
               var6 = 0;
            }
         }

         var7 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + ((this.break2_nflag_ike_brockTable[var2] % 4 << 4) - 16 - 8), objectData[3] + (this.break2_nflag_ike_brockTable[var2] >> 2 << 4) + var6 * (var6 / 5), objectData[6] + ((this.break2_nflag_ike_brockTable[var2] % 4 << 4) - 16 - 8), objectData[7] + (this.break2_nflag_ike_brockTable[var2] >> 2 << 4) + var6 * (var6 / 5), var4, var5);
         if (var6 != 0) {
            var7 = -1;
         }

         if (var7 >= 0) {
            if (this.zoneNumber == 3 && (var7 == 1 || var7 == 2) && this.PlayerPosY() < objectData[3] + (var5 >> 1)) {
               var7 = 0;
            }

            if (var7 == 0) {
               var8 = true;
               PlayerParam[1] = objectData[3] + (this.break2_nflag_ike_brockTable[var2] >> 2 << 4) + var6 * 5 - var5 << 8;
               this.setRaidOnSize(objectData[2] + ((this.break2_nflag_ike_brockTable[var2] % 4 << 4) - 16 - 8), var4);
               this.playerRaidOn(objectData[22]);
               if (objectData[15] == 0) {
                  objectData[16] = this.cpuTimer;
               }

               objectData[15] = 1;
            } else if (var7 == 1) {
               PlayerParam[0] = objectData[2] + ((this.break2_nflag_ike_brockTable[var2] % 4 << 4) - 16 - 8) - var4 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var7 == 2) {
               PlayerParam[0] = objectData[2] + ((this.break2_nflag_ike_brockTable[var2] % 4 << 4) - 16 - 8) + var4 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            }
         }
      }

      if (var8) {
         var7 = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var7 != 0) {
         raidOn = false;
      }

   }

   private void step_nflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      boolean var7 = false;
      int var8 = objectData[5];
      boolean var9 = false;
      boolean var10 = false;
      byte var11 = 30;
      boolean var12 = true;
      if (Math.abs(this.PlayerPosX() - objectData[2]) > 320) {
         objectData[5] = 0;
         objectData[18] = 0;
      }

      int var10002;
      if (objectData[18] > 0) {
         var10002 = objectData[18]++;
      }

      for(int var13 = 0; var13 < 4; ++var13) {
         if (objectData[10 + var13] == 0) {
            objectData[10 + var13] = objectData[3];
         }

         if (objectData[19] == 1) {
            if (objectData[5] > 0 && var13 == 0 && (objectData[18] > var11 || objectData[18] < 0)) {
               objectData[18] = -1;
               var10002 = objectData[5]++;
               if (objectData[5] > 188) {
                  objectData[5] = 188;
               }
            }

            if (objectData[5] > 60) {
               var5 = var13 << 5;
               var4 = (objectData[5] - 60 >> 2) * (var13 + 1);
               var6 = objectData[10 + var13];
            } else {
               var5 = var13 << 5;
               var4 = 0;
               var6 = objectData[10 + var13];
            }
         } else if (objectData[19] == 0) {
            if (objectData[5] > 0 && var13 == 0 && (objectData[18] > var11 || objectData[18] < 0)) {
               objectData[18] = -1;
               var10002 = objectData[5]++;
               if (objectData[5] > 188) {
                  objectData[5] = 188;
               }
            }

            if (objectData[5] > 60) {
               var5 = var13 << 5;
               var4 = (objectData[5] - 60 >> 2) * (4 - var13);
               var6 = objectData[10 + var13];
            } else {
               var5 = var13 << 5;
               var4 = 0;
               var6 = objectData[10 + var13];
            }
         }

         objectData[10 + var13] = objectData[3] + var4;
         int var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + var5, objectData[3] + var4, objectData[2] + var5, var6, var2, var3);
         if (var14 >= 0) {
            if (var14 == 0) {
               PlayerParam[1] = objectData[3] - var3 + var4 << 8;
               if (objectData[4] == 0 && objectData[5] == 0) {
                  var10002 = objectData[5]++;
                  objectData[18] = -1;
               }

               this.setRaidOnSize(objectData[2] + var5, var2);
               this.playerRaidOn(objectData[22]);
               raidObjectNumSub = var13;
               var10 = true;
            } else if (var14 == 1) {
               PlayerParam[0] = objectData[2] - var2 + var5 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var14 == 2) {
               PlayerParam[0] = objectData[2] + var2 + var5 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var14 == 3) {
               PlayerParam[1] = objectData[3] + var3 + var4 + 12 + 12 + 1 << 8;
               this.setHeadHit();
               if (objectData[4] == 2 && objectData[5] == 0) {
                  objectData[5] = 60;
                  if (objectData[18] == 0) {
                     objectData[18] = 1;
                  }
               }
            }
         }
      }

      byte var15;
      if (var10) {
         var15 = 0;
      } else {
         var15 = -1;
      }

      if (raidOn && raidObjectNum == objectData[20] && var15 != 0) {
         raidOn = false;
      }

   }

   private void fun_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = true;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      int var7 = 0;
      boolean var8 = false;
      boolean var9 = false;
      boolean var10 = false;
      int var11 = objectData[2] - this.PlayerPosX();
      int var12 = Math.abs(objectData[3] - 64 + 16 - (this.PlayerPosY() - 24));
      if (objectData[2] > this.PlayerPosX()) {
         var6 = false;
      } else {
         var6 = true;
      }

      if (objectData[4] != 2) {
         if (this.animeTimer / 30 % 2 == 0) {
            objectData[5] = this.animeTimer;
         }
      } else {
         objectData[5] = this.animeTimer;
      }

      int var13;
      int[] var10000;
      if (objectData[4] == 0) {
         if (this.animeTimer / 30 % 2 == 0) {
            if (objectData[19] != 0) {
               if (var12 <= 64) {
                  if (var11 >= -64 && var11 <= 64) {
                     while(var7 < 16 && var11 >= -64 && var11 <= 64) {
                        var10000 = PlayerParam;
                        var10000[0] += 256;
                        var11 = objectData[2] - this.PlayerPosX();
                        ++var7;
                     }
                  }

                  if (var11 <= -64 && var11 >= -128) {
                     var13 = 4;
                     if (PlayerParam[10] / 2 > 1024) {
                        var13 = PlayerParam[10] / 2 >> 8;
                     }

                     while(var7 < var13 && var11 <= -64 && var11 >= -128) {
                        var10000 = PlayerParam;
                        var10000[0] += 256;
                        var11 = objectData[2] - this.PlayerPosX();
                        ++var7;
                     }
                  }
               }
            } else if (var12 <= 64) {
               if (var11 <= 64 && var11 >= -64) {
                  while(var7 < 16 && var11 <= 64 && var11 >= -64) {
                     var10000 = PlayerParam;
                     var10000[0] -= 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }

               if (var11 >= 64 && var11 <= 128) {
                  var13 = 4;
                  if (PlayerParam[10] / 2 > 1024) {
                     var13 = PlayerParam[10] / 2 >> 8;
                  }

                  while(var7 < var13 && var11 >= 64 && var11 <= 128) {
                     var10000 = PlayerParam;
                     var10000[0] -= 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }
            }
         }
      } else if (objectData[4] == 1) {
         if (this.animeTimer / 30 % 2 == 0) {
            if (objectData[19] == 0) {
               if (var12 <= 64) {
                  if (var11 >= -64 && var11 <= 64) {
                     while(var7 < 16 && var11 >= -64 && var11 <= 64) {
                        var10000 = PlayerParam;
                        var10000[0] += 256;
                        var11 = objectData[2] - this.PlayerPosX();
                        ++var7;
                     }
                  }

                  if (var11 <= -64 && var11 >= -128) {
                     var13 = 4;
                     if (PlayerParam[10] / 2 > 1024) {
                        var13 = PlayerParam[10] / 2 >> 8;
                     }

                     while(var7 < var13 && var11 <= -64 && var11 >= -128) {
                        var10000 = PlayerParam;
                        var10000[0] += 256;
                        var11 = objectData[2] - this.PlayerPosX();
                        ++var7;
                     }
                  }
               }
            } else if (var12 <= 64) {
               if (var11 <= 64 && var11 >= -64) {
                  while(var7 < 16 && var11 <= 64 && var11 >= -64) {
                     var10000 = PlayerParam;
                     var10000[0] -= 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }

               if (var11 >= 64 && var11 <= 128) {
                  var13 = 4;
                  if (PlayerParam[10] / 2 > 1024) {
                     var13 = PlayerParam[10] / 2 >> 8;
                  }

                  while(var7 < var13 && var11 >= 64 && var11 <= 128) {
                     var10000 = PlayerParam;
                     var10000[0] -= 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }
            }
         }
      } else if (objectData[4] == 2) {
         if (objectData[19] != 0) {
            if (var12 <= 64) {
               if (var11 >= -64 && var11 <= 64) {
                  while(var7 < 16 && var11 >= -64 && var11 <= 64) {
                     var10000 = PlayerParam;
                     var10000[0] += 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }

               if (var11 <= -64 && var11 >= -128) {
                  var13 = 4;
                  if (PlayerParam[10] / 2 > 1024) {
                     var13 = PlayerParam[10] / 2 >> 8;
                  }

                  while(var7 < var13 && var11 <= -64 && var11 >= -128) {
                     var10000 = PlayerParam;
                     var10000[0] += 256;
                     var11 = objectData[2] - this.PlayerPosX();
                     ++var7;
                  }
               }
            }
         } else if (objectData[19] == 0 && var12 <= 64) {
            if (var11 <= 64 && var11 >= -64) {
               while(var7 < 16 && var11 <= 64 && var11 >= -64) {
                  var10000 = PlayerParam;
                  var10000[0] -= 256;
                  var11 = objectData[2] - this.PlayerPosX();
                  ++var7;
               }
            }

            if (var11 >= 64 && var11 <= 128) {
               var13 = 4;
               if (PlayerParam[10] / 2 > 1024) {
                  var13 = PlayerParam[10] / 2 >> 8;
               }

               while(var7 < var13 && var11 >= 64 && var11 <= 128) {
                  var10000 = PlayerParam;
                  var10000[0] -= 256;
                  var11 = objectData[2] - this.PlayerPosX();
                  ++var7;
               }
            }
         }
      }

      this.rcol3();
      this.rcol2();
      this.lcol3();
      this.lcol2();
   }

   private void belt_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      byte var3 = 16;
      boolean var4 = false;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      short var7;
      if (objectData[4] != 33 && objectData[4] != 225) {
         var7 = 128;
      } else {
         var7 = 56;
      }

      byte var8;
      if (objectData[4] != 32 && objectData[4] != 33) {
         if (objectData[4] != 224 && objectData[4] != 225) {
            var8 = 3;
         } else {
            var8 = -2;
         }
      } else {
         var8 = 2;
      }

      int var5 = 0;
      if (PlayerJump && olddir != 0) {
         var5 += this.dSin(olddir) * 20 / 100;
      }

      int var6 = this.ObjectColChk(this.PlayerPosX() + var5, this.PlayerPosY() - 12, ploldpos[0] + var5, ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var7, var3);
      if (var6 >= 0) {
         if (var6 == 0) {
            PlayerParam[1] = objectData[3] - var3 << 8;
            this.setRaidOnSize(objectData[2], var7);
            this.playerRaidOn(objectData[22]);
            if (Math.abs(objectData[2] - this.PlayerPosX()) < var7 - 12) {
               int[] var10000 = PlayerParam;
               var10000[0] += var8 << 8;
            }
         } else if (var6 == 1) {
            PlayerParam[0] = objectData[2] - var7 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var6 == 2) {
            PlayerParam[0] = objectData[2] + var7 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var6 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
         raidOn = false;
      }

   }

   private void pata_nflag_move_ikeshita(int var1) {
      byte var2;
      byte var3;
      if (objectData[4] != 1 && objectData[4] != 2) {
         var2 = 16;
         var3 = 7;
         int var6 = -1;
         objectData[6] = objectData[2];
         objectData[7] = objectData[3];
         byte var7 = 4;
         if (objectData[4] >= 144) {
            if (objectData[4] == 152) {
               objectData[14] = objectData[4];
            } else if (objectData[4] > 152) {
               objectData[14] = 152 - (objectData[4] - 152);
            } else {
               objectData[14] = 152 - (objectData[4] + 16 - 152);
            }

            objectData[13] = this.cpuTimer / var7 + objectData[14];
            objectData[13] %= 32;
         } else {
            if (objectData[4] == 128) {
               objectData[14] = objectData[4];
            } else if (objectData[4] > 128) {
               objectData[14] = 128 - (objectData[4] - 128);
            }

            objectData[13] = this.cpuTimer / var7 + objectData[14];
            objectData[13] %= 20;
         }

         if (objectData[13] == 0 || objectData[13] > 7) {
            var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
            if (var6 >= 0) {
               if (var6 == 0) {
                  PlayerParam[1] = objectData[3] - var3 << 8;
                  this.setRaidOnSize(objectData[2], var2);
                  this.playerRaidOn(objectData[22]);
               } else if (var6 == 1) {
                  PlayerParam[0] = objectData[2] - var2 - 12 << 8;
                  PlayerParam[10] = 0;
                  if (KeyPress[4]) {
                     this.playerPushSet();
                  }
               } else if (var6 == 2) {
                  PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
                  PlayerParam[10] = 0;
                  if (KeyPress[3]) {
                     this.playerPushSet();
                  }
               } else if (var6 == 3) {
                  PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
                  this.setHeadHit();
               }
            }
         }

         if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
            raidOn = false;
         }
      } else {
         var2 = 64;
         var3 = 12;
         boolean var4 = false;
         if (objectData[4] == 1 || objectData[4] == 2) {
            if (this.cpuTimer / 30 / 4 % 2 == 0) {
               int var5 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
               if (0 == var5) {
                  PlayerParam[1] = objectData[3] - var3 << 8;
                  this.playerRaidOn(objectData[22]);
               } else if (3 == var5) {
                  PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
                  this.setHeadHit();
               }

               if (raidOn && raidObjectNum == objectData[20] && 0 != var5) {
                  raidOn = false;
               }
            } else if (raidOn && raidObjectNum == objectData[20]) {
               raidOn = false;
            }
         }
      }

   }

   private void fire6_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = false;
      boolean var4 = false;
      int var6 = 0;
      boolean var7 = true;
      boolean var8 = true;
      if (!PlayerBall) {
         var8 = true;
      }

      int var10002 = objectData[5]++;
      int var12 = objectData[5] / 4;
      if (var12 % 30 < 6) {
         var12 %= 30;
      } else if (var12 % 30 > 20 && var12 % 30 <= 25) {
         var12 = 5 - (var12 - 20) % 30;
      } else if (var12 % 30 >= 25) {
         var12 = 0;
      } else {
         var12 = 5;
      }

      byte var10;
      byte var11;
      if (objectData[19] != 2) {
         var10 = 0;
         var11 = 49;
      } else {
         var10 = 0;
         var11 = -42;
      }

      for(int var5 = 0; var5 < var12; ++var5) {
         var6 += this.fire6_nflag_ike_sizeTable2[4 - var5] - this.fire6_nflag_ike_posTable[4 - var5];
         int var9;
         if (objectData[19] != 2) {
            var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + var10, objectData[3] + var11 - var6, objectData[2] + var10, objectData[3] + var11 - var6, this.fire6_nflag_ike_sizeTable[4 - var5] / 2 - 4, this.fire6_nflag_ike_sizeTable[4 - var5] / 2 - 4);
         } else {
            var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + var10, objectData[3] + var11 + var6, objectData[2] + var10, objectData[3] + var11 + var6, this.fire6_nflag_ike_sizeTable[4 - var5] / 2 - 4, this.fire6_nflag_ike_sizeTable[4 - var5] / 2 - 4);
         }

         if (var9 >= 0) {
            this.playdamageset();
         }
      }

   }

   private void bryuka_nflag_move_ikeshita(int var1) {
      byte var2 = 14;
      byte var3 = 14;
      boolean var4 = false;
      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var5 >= 0) {
         if (var5 == 0) {
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

               this.addScoreCount(comboScore);
               this.ShotScore(objectData[2], objectData[3], comboScore);
               this.ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 330, 400, 0);
               this.ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 300, 400, 1);
               this.ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 390, 400, 2);
               this.ShotObj2(22, objectData[2] - 8, objectData[3] - 16, 420, 400, 3);
               PlayerParam[1] = objectData[3] - var3 << 8;
            } else {
               PlayerParam[1] = objectData[3] - var3 << 8;
               if (olddir != 0) {
                  PlayerParam[0] = this.PlayerPosX() + this.dSin(olddir + 90) * 24 / 100 << 8;
                  olddir = 0;
               }

               this.setRaidOnSize(objectData[2], var2);
               this.playerRaidOn(objectData[22]);
            }
         } else if (var5 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var5 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var5 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

   }

   private void mawaru_nflag_move_ikeshita(int var1) {
      byte var2 = 45;
      byte var3 = 45;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      boolean var4 = false;
      int var10 = objectData[5] - this.cpuTimer;
      var10 = Math.abs(var10);
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

      if (var10 != 1) {
         int[] var10000;
         if (objectData[17] == 1) {
            var10000 = objectData;
            var10000[13] -= var10;
         } else {
            var10000 = objectData;
            var10000[13] += var10;
         }
      } else {
         int var10002;
         if (objectData[17] == 1) {
            var10002 = objectData[13]--;
         } else {
            var10002 = objectData[13]++;
         }
      }

      if (objectData[13] < 0) {
         objectData[13] = 80 + objectData[13] % 80;
      }

      if (objectData[13] > 79) {
         objectData[13] %= 80;
      }

      objectData[15] = objectData[13] / 10;
      objectData[5] = this.cpuTimer;
      objectData[10] = this.mawaru_nflag_ike_posx[objectData[15]];
      objectData[11] = this.mawaru_nflag_ike_posy[objectData[15]];
      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 1, objectData[3] - 1, objectData[6] - 1, objectData[7] - 1, var2, var3);
      if (var5 >= 0) {
         if (objectData[14] == 0) {
            if (var5 == 0) {
               PlayerParam[1] = objectData[3] - var3 - 1 << 8;
               this.setRaidOnSize(objectData[2] - 1, var2);
               this.playerRaidOn(objectData[22]);
            } else if (var5 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 - 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }

               if (objectData[14] == 0 && objectData[15] == 1) {
                  objectData[14] = 1;
                  objectData[18] = 1;
               }
            } else if (var5 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 - 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }

               if (objectData[14] == 0 && objectData[15] == 5) {
                  objectData[14] = 1;
                  objectData[18] = 5;
               }
            } else if (var5 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 - 1 << 8;
            }
         }
      } else if (objectData[14] == 2) {
         objectData[14] = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

      if (objectData[14] == 1) {
         int[] var6 = new int[]{0, -80, -100, -80};
         int[] var7 = new int[]{-100, -80, 0, 80};
         PlayerJump = true;
         PlayerDamage = false;
         PlayerBall = true;
         int var8 = var6[objectData[15] % 4] * 30 / 100;
         int var9 = var7[objectData[15] % 4] * 30 / 100;
         if (objectData[15] >= 4) {
            var8 *= -1;
            var9 *= -1;
         }

         PlayerParam[0] = objectData[2] + var8 << 8;
         PlayerParam[1] = objectData[3] + var9 + 12 << 8;
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

   private void yukai_nflag_move_ikeshita(int var1) {
      byte var2 = 96;
      byte var3 = 24;
      boolean var4 = false;
      boolean var5 = false;
      byte var6 = 48;
      byte var7 = 24;
      byte var8 = 96;
      int var9 = this.cpuTimer % (var8 + var6 + var8 + var6);
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int var11 = objectData[2];
      if (var9 < var8) {
         objectData[2] = objectData[8] + var9;
      } else if (var9 < var8 + var6) {
         objectData[2] = objectData[8] + var8;
         objectData[3] = objectData[9] + (var9 - var8);
      } else if (var9 < var8 + var6 + var8) {
         objectData[2] = objectData[8] + var8 - (var9 - var8 - var6);
         objectData[3] = objectData[9] + var6 - 24 * (var9 - var8 - var6) / var8;
      } else if (var9 < var8 + var6 + var8 + var7) {
         objectData[2] = objectData[8];
         objectData[3] = objectData[9] + var6 - 24 - (var9 - var8 - var6 - var8);
      }

      var11 -= objectData[2];
      int var10 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
      if (var10 >= 0) {
         if (var10 == 0) {
            PlayerParam[1] = objectData[3] - var3 << 8;
            PlayerParam[0] = this.PlayerPosX() - var11 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
         } else if (var10 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var10 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var10 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var10 != 0) {
         raidOn = false;
      }

   }

   private void door_nflag_move_ikeshita(int var1) {
      byte var2 = 4;
      byte var3 = 32;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
      objectData[15] = this.animeTimer / 5;
      objectData[5] = this.animeTimer / 10;
      if (var4 >= 0 && objectData[10] == 0) {
         if (var4 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var4 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         }
      }

      int var10002;
      if (objectData[19] == 0) {
         if (this.PlayerPosX() - 12 > objectData[2]) {
            var10002 = objectData[10]--;
            if (objectData[10] < 0) {
               objectData[10] = 0;
            }
         } else if (this.PlayerPosX() > objectData[2] - 24 && this.PlayerPosY() - 12 < objectData[3] + 32 && this.PlayerPosY() - 12 > objectData[3] - 32) {
            var10002 = objectData[10]++;
            if (objectData[10] > 4) {
               objectData[10] = 4;
            }
         } else {
            var10002 = objectData[10]--;
            if (objectData[10] < 0) {
               objectData[10] = 0;
            }
         }
      } else if (this.PlayerPosX() + 12 < objectData[2]) {
         var10002 = objectData[10]--;
         if (objectData[10] < 0) {
            objectData[10] = 0;
         }
      } else if (this.PlayerPosX() < objectData[2] + 24 && this.PlayerPosY() - 12 <= objectData[3] + 32 && this.PlayerPosY() - 12 >= objectData[3] - 32) {
         var10002 = objectData[10]++;
         if (objectData[10] > 4) {
            objectData[10] = 4;
         }
      } else {
         var10002 = objectData[10]--;
         if (objectData[10] < 0) {
            objectData[10] = 0;
         }
      }

   }

   private void yukae_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      byte var3 = 16;
      boolean var4 = false;
      int var5 = 16;
      int var6 = -1;
      objectData[5] = this.cpuTimer / 2 % 128;
      int var7 = (objectData[5] - objectData[4] / 2 + 256) % 128;
      if (var7 < 128) {
         if (var7 < 16) {
            var5 = var7;
         } else if (var7 > 64 && var7 < 80) {
            var5 = 80 - var7;
         } else if (var7 >= 80) {
            var5 = -1;
         }
      }

      if (var5 > 0) {
         byte var8 = 16;
         if (var5 <= 8 && var5 > 4) {
            var8 = 8;
         } else if (var5 <= 4) {
            var8 = 0;
         }

         if (var8 > 0) {
            var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + 8, objectData[2], objectData[3] + 8, var8, var3);
            if (var6 >= 0) {
               if (var6 == 0) {
                  PlayerParam[1] = objectData[3] + 8 - var3 << 8;
                  this.setRaidOnSize(objectData[2], var8);
                  this.playerRaidOn(objectData[22]);
               } else if (var6 == 1) {
                  PlayerParam[0] = objectData[2] - var8 - 12 << 8;
                  PlayerParam[10] = 0;
                  if (KeyPress[4]) {
                     this.playerPushSet();
                  }
               } else if (var6 == 2) {
                  PlayerParam[0] = objectData[2] + var8 + 12 + 1 << 8;
                  PlayerParam[10] = 0;
                  if (KeyPress[3]) {
                     this.playerPushSet();
                  }
               }
            }
         } else {
            var6 = -1;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
         raidOn = false;
      }

   }

   private void dai4_nflag_move_ikeshita(int var1) {
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      boolean var2 = false;
      boolean var3 = false;
      short var4 = 62;
      byte var5 = 11;
      int var6 = 0;
      if (objectData[4] < 128) {
         var4 = 28;
         var5 = 32;
      }

      int[] var10000;
      if (objectData[4] == 203) {
         var4 = 128;
         var5 = 64;
         var6 = objectData[2];
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
            if (this.cpuTimer % 2 == 0) {
               int var10002 = objectData[5]++;
            }

            if (objectData[5] > 256) {
               objectData[5] = 256;
            }

            objectData[2] = objectData[8] - objectData[5];
            objectData[3] = objectData[9] + objectData[5] / 2;
         }

         var6 -= objectData[2];
      } else {
         int var9;
         byte var10;
         if (objectData[4] == 52) {
            var10 = 6;
            var9 = this.animeTimer % (30 + var10 * 2);
            if (var9 < 15) {
               objectData[3] = objectData[9];
            } else if (var9 < 15 + var10) {
               objectData[3] = objectData[9] + (var9 - 15) * 16;
            } else if (var9 < 15 + var10 + 15) {
               objectData[3] = objectData[9] + var10 * 16;
            } else if (var9 < 15 + var10 + 15 + var10) {
               objectData[3] = objectData[9] + var10 * 16 - (var9 - (15 + var10 + 15)) * 16;
            }
         } else if (objectData[4] == 36) {
            var10 = 4;
            var9 = this.animeTimer % (30 + var10 * 2);
            if (var9 < 15) {
               objectData[3] = objectData[9];
            } else if (var9 < 15 + var10) {
               objectData[3] = objectData[9] + (var9 - 15) * 16;
            } else if (var9 < 15 + var10 + 15) {
               objectData[3] = objectData[9] + var10 * 16;
            } else if (var9 < 15 + var10 + 15 + var10) {
               objectData[3] = objectData[9] + var10 * 16 - (var9 - (15 + var10 + 15)) * 16;
            }
         } else if (objectData[4] == 19) {
            var10 = 4;
            var9 = this.animeTimer % (30 + var10 * 2);
            if (var9 < 15) {
               objectData[3] = objectData[9] + 8;
               var10000 = objectData;
               var10000[3] -= 16;
            } else if (var9 < 15 + var10) {
               objectData[3] = objectData[9] + (var9 - 15) * 16 + 8;
               var10000 = objectData;
               var10000[3] -= 16;
            } else if (var9 < 15 + var10 + 15) {
               objectData[3] = objectData[9] + var10 * 16 + 8;
               var10000 = objectData;
               var10000[3] -= 16;
            } else if (var9 < 15 + var10 + 15 + var10) {
               objectData[3] = objectData[9] + var10 * 16 - (var9 - (15 + var10 + 15)) * 16 + 8;
               var10000 = objectData;
               var10000[3] -= 16;
            }
         } else if (objectData[4] >= 128) {
            var6 = objectData[2];
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
                     objectData[2] = objectData[8] - (128 - (objectData[10] - 128 - 50));
                  }
               } else if (objectData[10] < 128) {
                  objectData[2] = objectData[8] + objectData[10] - 128;
               } else if (objectData[10] > 178) {
                  objectData[2] = objectData[8] + (128 - (objectData[10] - 128 - 50)) - 128;
               }

               if (objectData[10] > 306) {
                  objectData[5] = 0;
               }
            }

            if (objectData[5] == 0) {
               if (objectData[19] == 0) {
                  objectData[2] = objectData[8];
               } else {
                  objectData[2] = objectData[8] - 128;
               }
            }

            var6 -= objectData[2];
         }
      }

      boolean var7 = false;
      int var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var4, var5);
      if ((this.stageNumber != 3 || objectData[4] != 64) && var8 >= 0) {
         if (var8 == 0) {
            PlayerParam[1] = objectData[3] - var5 << 8;
            this.setRaidOnSize(objectData[2], var4);
            this.playerRaidOn(objectData[22]);
            var10000 = PlayerParam;
            var10000[0] -= var6 << 8;
         } else if (var8 == 1) {
            PlayerParam[0] = objectData[2] - var4 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var8 == 2) {
            PlayerParam[0] = objectData[2] + var4 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var8 == 3) {
            PlayerParam[1] = objectData[3] + var5 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var8 != 0) {
         raidOn = false;
      }

   }

   private void ele_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      byte var4 = 1;
      boolean var5 = true;
      byte var6 = 12;
      if (!PlayerBall) {
         var6 = 24;
      }

      int var7 = objectData[4] * 2;
      if (this.animeTimer % (var7 + 5) < var7) {
         objectData[5] = 0;
      } else {
         objectData[5] = (this.animeTimer % (var7 + 5) - var7) / 1 + 1;
      }

      if (objectData[19] == 2) {
         var4 = -1;
      }

      if (objectData[5] >= 2) {
         for(int var3 = 0; var3 <= 1; ++var3) {
            if (this.ele_nflag_ike_anime[objectData[5] - 2][var3] != 0) {
               int var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] + 24 + var3 * 32 - 4, objectData[3] - 4 * var4, objectData[2] + 24 + var3 * 32 - 4, objectData[3] - 4 * var4, 16, 8);
               if (var8 >= 0) {
                  this.playdamageset();
               } else if (24 >= Math.abs(this.PlayerPosX() - (objectData[2] + 24 + var3 * 32)) && 8 + var6 >= Math.abs(this.PlayerPosY() - var6 - (objectData[3] - 4 * var4))) {
                  this.playdamageset();
               }

               var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2] - 24 - var3 * 32 + 4, objectData[3] - 4 * var4, objectData[2] - 24 - var3 * 32 + 4, objectData[3] - 4 * var4, 16, 8);
               if (var8 >= 0) {
                  this.playdamageset();
               } else if (24 >= Math.abs(this.PlayerPosX() - (objectData[2] - 24 - var3 * 32)) && 8 + var6 >= Math.abs(this.PlayerPosY() - var6 - (objectData[3] - 4 * var4))) {
                  this.playdamageset();
               }
            }
         }
      }

   }

   private void beltc_nflag_move_ikeshita(int var1) {
      int var2 = 0;
      boolean var3 = false;
      int var4 = -1;
      int var9 = 0;
      int[] var10 = new int[this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2 + 1];
      var10[0] = 0;

      int var5;
      for(var5 = 0; var5 < this.beltc_nflag_ike_defx.length; ++var5) {
         if (objectData[9] == this.beltc_nflag_ike_defy[var5] && objectData[8] == this.beltc_nflag_ike_defx[var5]) {
            var2 = var5;
         }
      }

      int var7;
      for(var5 = 0; var5 < this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var5) {
         for(var7 = 0; var7 < 2; ++var7) {
            this.beltc_nflag_ike_startPos[var7] = 0;
            this.beltc_nflag_ike_endPos[var7] = 0;
         }

         this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var5 * 2 + 0];
         this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var5 * 2 + 1];
         this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var5 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
         this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var5 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
         if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
            var9 += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
         } else {
            var9 += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
         }

         var10[var5 + 1] = var9;
      }

      int var11 = var9 / 69;
      boolean var12 = false;
      int var13 = 0;
      if (objectData[18] == 0) {
         for(var5 = 0; var5 < kassya_x[var2].length; ++var5) {
            kassya_x[var2][var5] = 0;
            kassya_y[var2][var5] = 0;
         }
      }

      objectData[18] = 1;
      boolean var14 = false;
      boolean var15 = false;
      boolean var16 = false;
      boolean var17 = false;
      boolean var18 = false;

      for(var5 = 0; var5 < var11; ++var5) {
         int var22 = 0;
         int var23 = 0;
         var16 = false;
         var17 = false;
         int var21 = (var5 * 69 + this.cpuTimer) % var9;

         int var6;
         for(var6 = 0; var6 < this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var6) {
            if (var21 < var10[var6 + 1]) {
               int var26 = var21 - var10[var6 + 1];

               for(var7 = 0; var7 < 2; ++var7) {
                  this.beltc_nflag_ike_startPos[var7] = 0;
                  this.beltc_nflag_ike_endPos[var7] = 0;
               }

               this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var6 * 2 + 0];
               this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var6 * 2 + 1];
               this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var6 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
               this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var6 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
               if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
                  var22 = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * var26 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
                  var23 = this.beltc_nflag_ike_endPos[1] + var26 * ((this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]));
               } else {
                  var22 = this.beltc_nflag_ike_endPos[0] + var26 * ((this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]));
                  var23 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * var26 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
               }
               break;
            }
         }

         if (kassya_x[var2][var5] == 0 && kassya_y[var2][var5] == 0) {
            kassya_x[var2][var5] = var22;
            kassya_y[var2][var5] = var23;
         }

         int var24 = kassya_x[var2][var5];
         int var25 = kassya_y[var2][var5];
         kassya_x[var2][var5] = var22;
         kassya_y[var2][var5] = var23;

         for(var6 = 0; var6 < this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var6) {
            if (var21 < var10[var6 + 1]) {
               var13 = var6;
               break;
            }
         }

         byte var19 = 16;
         byte var20 = 7;
         if (!var3) {
            var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, var22, var23, var24, var25, var19, var20);
            if (var4 >= 0 && var13 != 1 && var13 != 2 && var4 == 0) {
               PlayerParam[1] = var23 - var20 << 8;
               int[] var10000 = PlayerParam;
               var10000[0] -= var24 - var22 << 8;
               raidObjectNumSub = var5;
               this.setRaidOnSize(var22, var19 + 4);
               this.playerRaidOn(objectData[22]);
               var3 = true;
            }
         }
      }

      if (var3) {
         var4 = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
         raidOn = false;
      }

   }

   private void noko_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      byte var3 = 30;
      byte var4 = 30;
      boolean var5 = true;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      objectData[15] = this.animeTimer & 1;
      if (objectData[4] == 1) {
         objectData[10] = 1;
         if (this.cpuTimer / 2 % 192 < 96) {
            objectData[2] = objectData[8] - this.cpuTimer / 2 % 192;
         } else {
            objectData[2] = objectData[8] - (96 - (this.cpuTimer / 2 % 192 - 96));
         }
      } else if (objectData[4] == 2) {
         objectData[10] = 1;
         if (this.cpuTimer / 2 % 112 < 56) {
            objectData[3] = objectData[9] - this.cpuTimer / 2 % 112;
         } else {
            objectData[3] = objectData[9] - (56 - (this.cpuTimer / 2 % 112 - 56));
         }
      } else if (objectData[4] == 3) {
         if (objectData[10] == 0) {
            if (objectData[2] > this.PlayerPosX() - 180 && objectData[2] < this.PlayerPosX() - 150) {
               objectData[5] = this.cpuTimer;
               objectData[10] = 1;
            }
         } else if (objectData[10] == 1) {
            objectData[2] = objectData[8] + (this.cpuTimer - objectData[5]) * 3;
            int var7 = objectData[2] - this.PlayerPosX();
            if (var7 > 200) {
               objectData[5] = 0;
               objectData[10] = 0;
               objectData[2] = objectData[8];
            }
         }
      }

      byte var6 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var6 = 20;
      }

      if (objectData[10] != 0) {
         int var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var3, var4);
         if (var8 >= 0) {
            this.playdamageset();
         } else if (Math.abs(objectData[2] - this.PlayerPosX()) < 12 + var3 && Math.abs(objectData[3] - (this.PlayerPosY() - var6)) < var6 + var4) {
            this.playdamageset();
         }
      }

   }

   private void save_sflag_move_ikeshita(int var1) {
      byte var2 = 8;
      byte var3 = 32;
      boolean var4 = false;
      if (objectData[5] == 0 && plsaveX == objectData[2] && plsaveY == objectData[3]) {
         objectData[5] = 32;
      }

      if (objectData[5] > 0) {
         int var10002 = objectData[5]++;
         if (objectData[5] > 32) {
            objectData[5] = 32;
         }
      }

      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] - 12, objectData[2], objectData[3] - 12, var2, var3);
      if (var5 >= 0 && objectData[5] == 0) {
         objectData[5] = 1;
         plsaveX = objectData[2];
         plsaveY = objectData[3];
         plsaveTime = timecount;
         plsaveTime2 = timecount2;
      }

   }

   private void kageb_nflag_move_ikeshita(int var1) {
      byte var2 = 8;
      byte var3 = 32;
      boolean var4 = false;
      if (objectData[4] < 16) {
         int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
         if (var5 >= 0) {
            if (var5 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var5 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var5 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
               this.setHeadHit();
            }
         }

         if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
            raidOn = false;
         }
      }

   }

   private void item_nflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      boolean var5 = false;
      if (objectData[4] < 10) {
         objectData[6] = objectData[2];
         objectData[7] = objectData[3];
         int[] var10000;
         int var7;
         if (objectData[12] == 1) {
            boolean var6 = false;
            var7 = (this.animeTimer - objectData[14]) * 4;
            objectData[14] = this.animeTimer;
            if (var7 > 0) {
               for(int var4 = var7; var4 >= 0; --var4) {
                  if (objectData[3] < objectData[7] + 1000) {
                     var10000 = objectData;
                     var10000[3] += 2;
                  }

                  if (this.blockColChk_Enemy(objectData[2], objectData[3] + var2) && objectData[3] > objectData[7] - 1000) {
                     var10000 = objectData;
                     var10000[3] -= 2;
                     objectData[12] = 0;
                     break;
                  }

                  if (objectData[3] < 0) {
                     objectData[3] = 0;
                  }
               }
            }
         }

         var7 = this.ObjectColChk2(objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
         if (objectData[4] == 0) {
            var7 = -1;
         }

         if (objectData[5] != 0) {
            objectData[7] = (this.animeTimer - objectData[13]) * 2;
            if (objectData[7] > 60) {
               objectData[7] = 60;
            }
         }

         if (var7 >= 0) {
            if (PlayerBall && var7 != 3 || !PlayerJump && PlayerBall && var7 == 3) {
               PlayerParam[5] = -768;
               this.SetObj2(OBJ2_BAKUHATU, objectData[2], objectData[3], 0, 0, 0, 0);
               objectData[5] = objectData[4];
               objectData[13] = this.animeTimer;
               this.getItem(objectData[4]);
               objectData[4] = 0;
            } else if (var7 == 0) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               this.setRaidOnSize(objectData[2], var2);
               this.playerRaidOn(objectData[22]);
            } else if (var7 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var7 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var7 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
               if (PlayerParam[5] < 0) {
                  var10000 = PlayerParam;
                  var10000[5] *= -1;
               }

               if (objectData[12] == 0) {
                  var10000 = objectData;
                  var10000[3] -= 5;
                  objectData[14] = this.animeTimer;
               }

               objectData[12] = 1;
               if (!PlayerJump) {
                  if (this.PlayerPosX() < objectData[2]) {
                     PlayerParam[0] = this.PlayerPosX() - 5 << 8;
                  } else {
                     PlayerParam[0] = this.PlayerPosX() + 5 << 8;
                  }
               }
            }
         }

         if (raidOn && raidObjectNum == objectData[20] && var7 != 0) {
            raidOn = false;
         }

      }
   }

   private void item_sflag_move_ikeshita(int var1) {
      this.item_nflag_move_ikeshita(var1);
   }

   private void gole_nflag_move_ikeshita(int var1) {
      byte var2 = 3;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[5] == 1) {
         int var10002 = objectData[10]++;
         if (objectData[10] > 129) {
            objectData[10] = 129;
            objectData[5] = 2;
            this.initGoleStart();
         }
      } else if (objectData[5] == 0 && this.PlayerPosX() > objectData[2] - var2 && objectData[3] + 48 > this.PlayerPosY()) {
         objectData[5] = 1;
         objectData[10] = 0;
         this.gole_on = true;
      }

   }

   private void bten_nflag_move_ikeshita(int var1) {
      byte var2 = 12;
      byte var3 = 12;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[5] == 0) {
         int var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
         if (var4 >= 0) {
            objectData[5] = 1;
            objectData[11] = 0;
            this.addScoreCount(this.bten_nflag_ike_score[objectData[4]]);
         }
      } else if (objectData[5] == 1) {
         int var10002 = objectData[11]++;
         if (objectData[11] > 120) {
            objectData[5] = 2;
         }
      }

   }

   private void bten_sflag_move_ikeshita(int var1) {
      this.bten_nflag_move_ikeshita(var1);
   }

   private void bigring_nflag_move_ikeshita(int var1) {
      byte var2 = 32;
      byte var3 = 32;
      if (ringcount >= 50) {
         objectData[6] = objectData[2];
         objectData[7] = objectData[3];
         if (objectData[5] == 0) {
            int var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
            if (var4 >= 0) {
               objectData[5] = 1;
               objectData[0] = -1;
               objectData[11] = this.animeTimer;
            }
         } else {
            objectData[10] = (this.animeTimer - objectData[11]) / 1;
            if (objectData[10] > 5) {
               objectData[10] = 5;
            }
         }
      }

   }

   private void scoli_nflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      int var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var4 >= 0 && var4 != 0) {
         if (var4 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
            }
         } else if (var4 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
            }
         } else if (var4 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
         raidOn = false;
      }

   }

   private void shooter_nflag_move_ikeshita(int var1) {
      boolean var2 = true;
      byte var3 = 44;
      int var4 = -1;
      byte var5 = 5;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[5] == 0) {
         var4 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var5, var3);
         if (var4 >= 0) {
            if (var4 == 0) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               this.setRaidOnSize(objectData[2], var5);
               this.playerRaidOn(objectData[22]);
            } else if (var4 == 1) {
               PlayerParam[0] = objectData[2] - var5 - 12 << 8;
               PlayerParam[10] = 0;
               if (objectData[19] == 0) {
                  objectData[5] = 1;
                  objectData[10] = 0;
               } else if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var4 == 2) {
               PlayerParam[0] = objectData[2] + var5 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (objectData[19] != 0) {
                  objectData[5] = 1;
                  objectData[10] = 0;
               } else if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var4 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
               this.setHeadHit();
            }
         }
      }

      int[] var10000;
      int var10002;
      if (objectData[5] == 1) {
         PlayerJump = true;
         PlayerDamage = false;
         PlayerBall = true;
         PlayerNoCol = true;
         PlayerNoCtrl = true;
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
         var10002 = objectData[10]++;
         PlayerParam[0] = objectData[2] << 8;
         PlayerParam[1] = objectData[3] + 12 - this.shooter_nflag_ike_pos[objectData[10] / 5 % 12] << 8;
         if (objectData[10] / 5 % 12 == 11) {
            objectData[5] = 2;
            if (objectData[4] == 0 || objectData[4] == 6) {
               objectData[5] = 4;
            }

            objectData[10] = 0;
            objectData[11] = 0;
            objectData[12] = 0;
            PlayerParam[3] = 0;
            PlayerParam[5] = 0;
            PlayerParam[10] = 0;
         }
      }

      if (objectData[5] == 4) {
         if (objectData[4] == 0) {
            PlayerParam[1] = this.PlayerPosY() + 10 << 8;
            if (objectData[3] > this.PlayerPosY()) {
               objectData[5] = 2;
            }
         } else {
            PlayerParam[1] = this.PlayerPosY() - 10 << 8;
            if (158 < this.PlayerPosY()) {
               objectData[5] = 2;
            }
         }
      }

      if (objectData[5] == 2) {
         PlayerJump = true;
         PlayerDamage = false;
         PlayerBall = true;
         PlayerNoCol = true;
         PlayerNoCtrl = true;
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
         objectData[11] = 0;
         objectData[12] = 0;
         if (objectData[10] * 2 + 1 < this.shooter_nflag_ike_objectPos[objectData[4]].length) {
            if (this.PlayerPosX() > this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2]) {
               objectData[11] = -1;
            } else if (this.PlayerPosX() < this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2]) {
               objectData[11] = 1;
            }

            if (this.PlayerPosY() > this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2 + 1]) {
               objectData[12] = -1;
            } else if (this.PlayerPosY() < this.shooter_nflag_ike_objectPos[objectData[4]][objectData[10] * 2 + 1]) {
               objectData[12] = 1;
            }

            var10002 = objectData[10]++;
            objectData[5] = 3;
         } else {
            objectData[5] = 0;
            PlayerNoCol = false;
            PlayerNoCtrl = false;
         }
      }

      if (objectData[5] == 3) {
         var10000 = PlayerParam;
         var10000[11] += plmaxspd;
         PlayerJump = true;
         PlayerDamage = false;
         PlayerBall = true;
         PlayerNoCol = true;
         PlayerNoCtrl = true;
         if (objectData[11] != 0) {
            PlayerParam[0] = this.PlayerPosX() + objectData[11] * 10 << 8;
            if (objectData[11] == -1 && this.PlayerPosX() < this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2]) {
               PlayerParam[0] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2] << 8;
               objectData[11] = 0;
            }

            if (objectData[11] == 1 && this.PlayerPosX() > this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2]) {
               PlayerParam[0] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2] << 8;
               objectData[11] = 0;
            }
         }

         if (objectData[12] != 0) {
            PlayerParam[1] = this.PlayerPosY() + objectData[12] * 10 << 8;
            if (objectData[12] == -1 && this.PlayerPosY() < this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1]) {
               PlayerParam[1] = this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1] << 8;
               objectData[12] = 0;
            }

            if (objectData[12] == 1 && this.PlayerPosY() > this.shooter_nflag_ike_objectPos[objectData[4]][(objectData[10] - 1) * 2 + 1]) {
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

      if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
         raidOn = false;
      }

   }

   private void masin_nflag_move_ikeshita(int var1) {
      byte var2 = 32;
      byte var3 = 28;
      byte var4 = 4;
      if (objectData[4] == 1) {
         var2 = 12;
         var3 = 8;
         var4 = 0;
      }

      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      int var5 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + var4, objectData[6], objectData[7] + var4, var2, var3);
      if (var5 >= 0) {
         if (var5 == 0) {
            PlayerParam[1] = objectData[3] - var3 + var4 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
            if (objectData[4] == 1 && objectData[5] == 0) {
               this.gole_on = true;
               objectData[5] = 1;
               int[] var10000 = objectData;
               var10000[3] += 8;
               objectData[10] = this.cpuTimer;
               this.m_bScrollLock = 2;
            }
         } else if (!this.limitBreak) {
            if (var5 == 1) {
               PlayerParam[0] = objectData[2] - var2 - 12 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[4]) {
                  this.playerPushSet();
               }
            } else if (var5 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var5 == 3) {
               PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 + var4 << 8;
            }
         }
      }

      if (objectData[5] == 1) {
         if ((this.cpuTimer - objectData[10]) % 20 == 0 && this.masin_nflag_ike_x.length > (this.cpuTimer - objectData[10]) / 20) {
            this.SetObj2(OBJ2_BAKUHATU, objectData[8] + this.masin_nflag_ike_x[(this.cpuTimer - objectData[10]) / 20], objectData[9] + this.masin_nflag_ike_y[(this.cpuTimer - objectData[10]) / 20], 0, 0, 0, 0);
         }

         if (this.cpuTimer - objectData[10] > 100) {
            objectData[5] = 2;
            objectData[10] = this.cpuTimer;
            this.ShotAnimal(objectData[8] + 4, objectData[9] + 27 + 16, this.zoneNumber);
         }
      } else if (objectData[5] == 2 && this.cpuTimer - objectData[10] > 180) {
         this.initGoleStart();
         int var10002 = objectData[5]++;
      }

      if (raidOn && raidObjectNum == objectData[20] && var5 != 0) {
         raidOn = false;
      }

   }

   private void bobin_sflag_move_ikeshita(int var1) {
      byte var2 = 8;
      byte var3 = 8;
      boolean var4 = false;
      int var5 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (var5 >= 0) {
         int var6 = Math.abs(this.PlayerPosX() - objectData[2]);
         int var7 = Math.abs(this.PlayerPosY() - 12 - objectData[3]);
         if (objectData[10] < 10) {
            this.ShotScore(objectData[2], objectData[3], 10);
            this.addScoreCount(10);
            int var10002 = objectData[10]++;
         }

         if (this.PlayerPosX() - objectData[2] > 0) {
            PlayerParam[3] = 1792 * var6 / (var6 + var7);
         } else {
            PlayerParam[3] = -1792 * var6 / (var6 + var7);
         }

         if (this.PlayerPosY() - 12 - objectData[3] > 0) {
            PlayerParam[5] = 2048 * var7 / (var6 + var7);
         } else {
            PlayerParam[5] = -2048 * var7 / (var6 + var7);
         }

         if (!PlayerJump) {
            PlayerParam[3] = PlayerParam[3] * 80 / 100;
            int[] var10000 = PlayerParam;
            var10000[5] -= 1024;
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

   private void jyama_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      boolean var3 = false;
      byte var4 = 16;
      byte var5 = 16;
      int var6 = this.ObjectColChkPl(objectData[2], objectData[3], objectData[2], objectData[3], var4, var5);
      if (var6 >= 0) {
         if (var6 == 0) {
            PlayerParam[1] = objectData[3] - var5 << 8;
            this.setRaidOnSize(objectData[2], var4);
            this.playerRaidOn(objectData[22]);
         } else if (var6 == 1) {
            PlayerParam[0] = objectData[2] - var4 - 12 << 8;
            if (KeyPress[4]) {
               this.playerPushSet();
            }

            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
         } else if (var6 == 2) {
            PlayerParam[0] = objectData[2] + var4 + 12 + 1 << 8;
            if (KeyPress[3]) {
               this.playerPushSet();
            }

            PlayerParam[10] = 0;
            PlayerParam[13] = 0;
            PlayerParam[14] = 0;
         } else if (var6 == 3) {
            PlayerParam[1] = objectData[3] + var5 + 12 + 12 + 1 << 8;
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
         raidOn = false;
      }

   }

   private void fetama_nflag_move_ikeshita(int var1) {
      int var2 = this.animeTimer;
      boolean var3 = false;
      byte var4 = 16;
      byte var5 = 16;
      byte var7 = 4;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      if (objectData[19] == 1) {
         var2 += 45;
      } else if (objectData[19] == 2) {
         var2 += 135;
      } else if (objectData[19] == 3) {
         var2 += 180;
      }

      if (objectData[4] == 195 || objectData[4] == 179) {
         var7 = 8;
      }

      if (objectData[4] != 243 && objectData[4] != 195 && objectData[4] != 227 && objectData[4] != 179) {
         boolean var10 = true;
         int var8 = this.animeTimer;
         if (objectData[19] == 1) {
            var2 += 24;
         } else if (objectData[19] == 2) {
            var2 += 48;
         } else if (objectData[19] == 3) {
            var2 += 72;
         }

         if (objectData[4] == 2) {
            if (objectData[19] != 0) {
               if (var8 % 96 * 2 < 96) {
                  objectData[3] = objectData[9] - (96 - (var8 % 96 * 2 - 96)) + 64;
               } else {
                  objectData[3] = objectData[9] - var8 % 96 * 2 + 64;
               }
            } else if (var8 % 96 * 2 < 96) {
               objectData[3] = objectData[9] - var8 % 96 * 2;
            } else {
               objectData[3] = objectData[9] - (96 - (var8 % 96 * 2 - 96));
            }
         } else if (objectData[4] == 1) {
            if (var8 % 96 * 2 < 96) {
               objectData[2] = objectData[8] - var8 % 96 * 2;
            } else {
               objectData[2] = objectData[8] - (96 - (var8 % 96 * 2 - 96));
            }
         }
      } else {
         vect((360 / var7 - var2 % (360 / var7)) * var7, 16, 17);
         objectData[2] = objectData[8] + objectData[16] * 80 / 10000;
         objectData[3] = objectData[9] + objectData[17] * 80 / 10000;
      }

      byte var11 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var11 = 20;
      }

      int var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[6], objectData[7], var4, var5);
      if (var9 >= 0) {
         this.playdamageset();
      } else if (Math.abs(objectData[2] - this.PlayerPosX()) < 12 + var4 && Math.abs(objectData[3] - (this.PlayerPosY() - var11)) < var11 + var5) {
         this.playdamageset();
      }

   }

   private void tekyu_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      boolean var3 = false;
      boolean var5 = false;
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var8 = 20;
      }

      if (objectData[18] == 0) {
         objectData[5] = this.animeTimer;
         objectData[18] = 1;
      }

      int var12 = this.animeTimer - objectData[5];
      int var13 = var12 - 1;
      if (objectData[4] != 213 && objectData[4] != 181 && objectData[4] != 197 && objectData[4] != 101 && objectData[4] != 69 && objectData[4] != 53) {
         if (objectData[4] != 212 && objectData[4] != 196 && objectData[4] != 84 && objectData[4] != 68 && objectData[4] != 52) {
            if (objectData[4] == 38) {
               var6 = 6;
            } else if (objectData[4] == 195) {
               var6 = 3;
            }
         } else {
            var6 = 4;
         }
      } else {
         var6 = 5;
      }

      if (objectData[4] != 181 && objectData[4] != 101) {
         if (objectData[4] != 196 && objectData[4] != 197 && objectData[4] != 84 && objectData[4] != 195) {
            if (objectData[4] != 213 && objectData[4] != 212 && objectData[4] != 69 && objectData[4] != 68) {
               if (objectData[4] != 52 && objectData[4] != 53) {
                  if (objectData[4] == 38) {
                     var7 = 4;
                  }
               } else {
                  var7 = 6;
               }
            } else {
               var7 = 8;
            }
         } else {
            var7 = 10;
         }
      } else {
         var7 = 12;
      }

      if (objectData[4] != 69 && objectData[4] != 84 && objectData[4] != 101 && objectData[4] != 38 && objectData[4] != 68 && objectData[4] != 52 && objectData[4] != 53) {
         vect((360 / var7 - var12 % (360 / var7)) * var7, 16, 17);
         vect((360 / var7 - var13 % (360 / var7)) * var7, 14, 15);
      } else {
         vect(360 - (360 / var7 - var12 % (360 / var7)) * var7 % 360, 16, 17);
         vect(360 - (360 / var7 - var13 % (360 / var7)) * var7 % 360, 14, 15);
      }

      if (objectData[4] == 84) {
         vect(var12 % (360 / var7) * var7, 16, 17);
         vect(var13 % (360 / var7) * var7, 14, 15);
      } else if (objectData[19] == 1) {
         vect(var12 % (360 / var7) * var7, 16, 17);
         vect(var13 % (360 / var7) * var7, 14, 15);
      }

      boolean var9 = false;
      byte var10 = 16;
      byte var11 = 16;
      if (this.zoneNumber == 4) {
         var10 = 8;
         var11 = 8;
      }

      int var4;
      int var14;
      for(var4 = 1; var4 < var6; ++var4) {
         if (this.zoneNumber == 4) {
            var10 = 8;
            var11 = 8;
            var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - var8, ploldpos[0], ploldpos[1] - var8, 12, var8, objectData[2] + var4 * objectData[16] * var10 * 2 / 10000, objectData[3] + var4 * objectData[17] * var10 * 2 / 10000, objectData[2] + var4 * objectData[14] * var10 * 2 / 10000, objectData[3] + var4 * objectData[15] * var10 * 2 / 10000, var10 - 2, var11 - 2);
            if (var14 >= 0) {
               this.playdamageset();
            }
         }
      }

      if (this.zoneNumber == 4) {
         var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - var8, ploldpos[0], ploldpos[1] - var8, 12, var8, objectData[2] + var4 * objectData[16] * var10 * 2 / 10000, objectData[3] + var4 * objectData[17] * var10 * 2 / 10000, objectData[2] + var4 * objectData[14] * var10 * 2 / 10000, objectData[3] + var4 * objectData[15] * var10 * 2 / 10000, var10 - 2, var11 - 2);
      } else {
         var14 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - var8, ploldpos[0], ploldpos[1] - var8, 12, var8, objectData[2] + var4 * objectData[16] * var10 / 10000, objectData[3] + var4 * objectData[17] * var10 / 10000, objectData[2] + var4 * objectData[14] * var10 / 10000, objectData[3] + var4 * objectData[15] * var10 / 10000, 6, 6);
      }

      if (var14 >= 0) {
         this.playdamageset();
      }

   }

   private void dai2_sflag_move_ikeshita(int var1) {
      int var2 = 16;
      byte var3 = 16;
      boolean var4 = false;
      boolean var5 = false;
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      boolean var10 = false;
      objectData[6] = objectData[2];
      objectData[7] = objectData[3];
      byte var13;
      if (objectData[4] == 248 && this.stageNumber == 2) {
         var13 = 64;
         var3 = 16;
         if (Math.abs(this.PlayerPosX() - objectData[8]) <= var13 && Math.abs(this.PlayerPosY() + 12 - (objectData[9] - var3 * 2 + 8)) <= var3 * 2) {
            switchflag2[objectData[4] - 240] = true;
         }

         if (Math.abs(this.PlayerPosX() - objectData[8]) <= var13 && Math.abs(this.PlayerPosY() + 12 - (objectData[9] + var3 * 3)) <= var3 * 2) {
            switchflag2[objectData[4] - 240] = false;
         }

         if (switchflag2[objectData[4] - 240] && objectData[3] > this.waterH2) {
            objectData[18] = 1;
            objectData[5] = var13 * 2;
            objectData[2] = objectData[8] + var13 * 2 - objectData[5];
         } else {
            objectData[18] = 1;
            objectData[5] = 0;
            objectData[2] = objectData[8] + var13 * 2 - objectData[5];
         }

         var2 = var13 - 1;
      } else {
         int var10002;
         if (objectData[4] == 241 && switchflag2[128] && this.zoneNumber == 1 && this.stageNumber == 3) {
            var13 = 64;
            var3 = 16;
            if (objectData[19] == 0) {
               if (switchflag2[128] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[5] = var13 * 2;
                  objectData[2] = objectData[8] + var13 * 2 - objectData[5];
               }

               objectData[18] = 1;
               if (objectData[5] == 0) {
                  if (switchflag2[128]) {
                     objectData[5] = 1;
                  } else {
                     objectData[2] = objectData[8] + var13 * 2;
                  }
               } else {
                  var10002 = objectData[5]++;
                  if (objectData[5] > var13 * 2) {
                     objectData[5] = var13 * 2;
                  }

                  objectData[2] = objectData[8] + var13 * 2 - objectData[5];
               }
            } else {
               if (switchflag2[128] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[5] = var13 * 2;
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
                  var10002 = objectData[5]++;
                  if (objectData[5] > var13 * 2) {
                     objectData[5] = var13 * 2;
                  }

                  objectData[2] = objectData[8] + objectData[5];
               }
            }

            var2 = var13 - 1;
         } else if (objectData[4] >= 240) {
            var13 = 64;
            var3 = 16;
            if (objectData[19] == 0) {
               if (switchflag2[objectData[4] - 240] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[5] = var13 * 2;
                  objectData[2] = objectData[8] + var13 * 2 - objectData[5];
               }

               objectData[18] = 1;
               if (objectData[5] == 0) {
                  if (switchflag2[objectData[4] - 240]) {
                     objectData[5] = 1;
                  } else {
                     objectData[2] = objectData[8] + var13 * 2;
                  }
               } else {
                  var10002 = objectData[5]++;
                  if (objectData[5] > var13 * 2) {
                     objectData[5] = var13 * 2;
                  }

                  objectData[2] = objectData[8] + var13 * 2 - objectData[5];
               }
            } else {
               if (switchflag2[objectData[4] - 240] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[5] = var13 * 2;
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
                  var10002 = objectData[5]++;
                  if (objectData[5] > var13 * 2) {
                     objectData[5] = var13 * 2;
                  }

                  objectData[2] = objectData[8] + objectData[5];
               }
            }

            var2 = var13 - 1;
         } else if (objectData[4] == 229 && this.zoneNumber == 1 && this.stageNumber == 0) {
            var13 = 8;
            var3 = 32;
            if (objectData[5] == 0) {
               if (switchflag[objectData[4] - 96]) {
                  objectData[5] = 1;
                  objectData[18] = 1;
               } else {
                  objectData[3] = objectData[9] + var3 * 2;
               }
            } else {
               if (objectData[18] != 0 && objectData[2] < this.PlayerPosX() - 12) {
                  objectData[18] = 0;
               }

               if (objectData[18] == 0) {
                  var10002 = objectData[5]--;
                  if (objectData[5] < 0) {
                     objectData[5] = 0;
                  }

                  objectData[3] = objectData[9] + var3 * 2 - objectData[5];
               } else {
                  var10002 = objectData[5]++;
                  if (objectData[5] > var3 * 2) {
                     objectData[5] = var3 * 2;
                  }

                  objectData[3] = objectData[9] + var3 * 2 - objectData[5];
               }
            }

            var2 = var13 - 1;
         } else if (objectData[4] >= 224) {
            var13 = 8;
            var3 = 32;
            if (switchflag2[objectData[4] - 224] && objectData[18] == 0) {
               objectData[18] = 1;
               objectData[5] = var3 * 2;
               objectData[3] = objectData[9] + var3 * 2 - objectData[5];
            }

            objectData[18] = 1;
            if (objectData[5] == 0) {
               if (switchflag2[objectData[4] - 224]) {
                  objectData[5] = 1;
               } else {
                  objectData[3] = objectData[9] + var3 * 2;
               }
            } else {
               var10002 = objectData[5]++;
               if (objectData[5] > var3 * 2) {
                  objectData[5] = var3 * 2;
               }

               objectData[3] = objectData[9] + var3 * 2 - objectData[5];
            }

            var2 = var13 - 1;
         } else if (objectData[4] == 19) {
            var2 = 32;
            var3 = 32;
            var10002 = objectData[5]++;
            byte var14;
            if (this.cpuTimer % 360 > 90 && this.cpuTimer % 360 < 270) {
               var14 = 33;
            } else {
               var14 = 32;
            }

            if (objectData[19] == 0) {
               objectData[3] = objectData[9] + this.dSin(this.cpuTimer) * var14 / 100 - var14;
            } else {
               objectData[3] = objectData[9] - this.dSin(this.cpuTimer) * var14 / 100 - var14;
            }

            var10 = true;
         } else {
            int var15;
            if (objectData[4] >= 0 && objectData[4] <= 2) {
               var2 = 16;
               var3 = 16;
               var6 = objectData[2];
               if (objectData[4] != 0) {
                  var10002 = objectData[5]++;
                  var15 = 32 * objectData[4];
                  if (objectData[19] == 0) {
                     objectData[2] = objectData[8] + this.dSin(this.cpuTimer) * var15 / 100 - var15;
                  } else {
                     objectData[2] = objectData[8] - this.dSin(this.cpuTimer) * var15 / 100 - var15;
                  }
               }

               var6 -= objectData[2];
               var9 = true;
            } else if (objectData[4] <= 91 && objectData[4] >= 88) {
               var2 = 16;
               var3 = 16;
               var6 = objectData[2];
               var15 = 32 * (objectData[4] - 88 + 1) - 16;
               if (this.cpuTimer % 720 < 180) {
                  if (objectData[19] == 0) {
                     objectData[3] = objectData[9] - this.dSin(90 + this.cpuTimer % 720) * var15 / 100;
                     objectData[2] = objectData[8] - 16 + 32 * (objectData[4] - 88 + 1);
                  } else {
                     objectData[3] = objectData[9] + this.dSin(90 + this.cpuTimer % 720) * var15 / 100;
                     objectData[2] = objectData[8] + 16 - 32 * (objectData[4] - 88 + 1);
                  }
               } else if (this.cpuTimer % 720 < 360) {
                  if (objectData[19] == 0) {
                     objectData[2] = objectData[8] - this.dSin(270 - (this.cpuTimer % 720 - 180)) * var15 / 100;
                     objectData[3] = objectData[9] - 16 + 32 * (objectData[4] - 88 + 1);
                  } else {
                     objectData[2] = objectData[8] + this.dSin(270 - (this.cpuTimer % 720 - 180)) * var15 / 100;
                     objectData[3] = objectData[9] + 16 - 32 * (objectData[4] - 88 + 1);
                  }

                  var9 = true;
               } else if (this.cpuTimer % 720 < 540) {
                  if (objectData[19] == 0) {
                     objectData[3] = objectData[9] + this.dSin(90 + (this.cpuTimer % 720 - 360)) * var15 / 100;
                     objectData[2] = objectData[8] + 16 - 32 * (objectData[4] - 88 + 1);
                  } else {
                     objectData[3] = objectData[9] - this.dSin(90 + (this.cpuTimer % 720 - 360)) * var15 / 100;
                     objectData[2] = objectData[8] - 16 + 32 * (objectData[4] - 88 + 1);
                  }
               } else if (this.cpuTimer % 720 < 720) {
                  if (objectData[19] == 0) {
                     objectData[2] = objectData[8] + this.dSin(270 - (this.cpuTimer % 720 - 540)) * var15 / 100;
                     objectData[3] = objectData[9] + 16 - 32 * (objectData[4] - 88 + 1);
                  } else {
                     objectData[2] = objectData[8] - this.dSin(270 - (this.cpuTimer % 720 - 540)) * var15 / 100;
                     objectData[3] = objectData[9] - 16 + 32 * (objectData[4] - 88 + 1);
                  }

                  var9 = true;
               }

               var6 -= objectData[2];
            } else if (objectData[4] == 160) {
               var2 = 16;
               var3 = 32;
               if (switchflag2[0] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[3] = objectData[9];
               }

               objectData[18] = 1;
               if (switchflag2[0]) {
                  var10002 = objectData[3]--;
                  if (objectData[3] < objectData[9]) {
                     objectData[3] = objectData[9];
                  }
               } else if (this.stageNumber == 0) {
                  objectData[3] = 688;
               } else {
                  objectData[3] = objectData[9] + 64;
               }
            } else if (objectData[4] == 55) {
               var2 = 32;
               var3 = 25;
               if (switchflag2[objectData[4] - 40] && objectData[18] == 0) {
                  objectData[18] = 1;
                  objectData[2] = 7992;
               }

               objectData[18] = 1;
               if ((objectData[8] != 7992 || objectData[9] != 1353) && switchflag2[objectData[4] - 40]) {
                  var10002 = objectData[2]++;
                  if (objectData[2] > 7992) {
                     objectData[2] = 7992;
                  }
               }
            }
         }
      }

      byte var11 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var11 = 16;
      }

      int var12 = -1;
      if (objectData[4] != 55 || objectData[8] != 7992 || objectData[9] != 1353) {
         var12 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - var11, ploldpos[0], ploldpos[1] - var11, 12, var11, objectData[2], objectData[3], objectData[6], objectData[7], var2, var3);
         if (var12 >= 0) {
            int[] var10000;
            if (var12 == 0) {
               PlayerParam[1] = objectData[3] - var3 << 8;
               this.setRaidOnSize(objectData[2], var2);
               this.playerRaidOn(objectData[22]);
               var10000 = PlayerParam;
               var10000[0] -= var6 << 8;
            } else if (var12 == 1) {
               if (!PlayerSWater || objectData[4] != 227) {
                  PlayerParam[0] = objectData[2] - var2 - 12 << 8;
                  PlayerParam[10] = 0;
                  if (KeyPress[4]) {
                     this.playerPushSet();
                  }
               }
            } else if (var12 == 2) {
               PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
               PlayerParam[10] = 0;
               if (KeyPress[3]) {
                  this.playerPushSet();
               }
            } else if (var12 == 3) {
               PlayerParam[1] = objectData[3] + var3 + var11 + var11 + 1 << 8;
               this.setHeadHit();
            } else if (var12 == 4) {
               if (var9) {
                  if (objectData[2] > this.PlayerPosX()) {
                     if (!PlayerSWater || objectData[4] != 227) {
                        PlayerParam[0] = objectData[2] - var2 - 12 << 8;
                        PlayerParam[10] = 0;
                        if (KeyPress[4]) {
                           this.playerPushSet();
                        }
                     }
                  } else {
                     PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
                     PlayerParam[10] = 0;
                     if (KeyPress[3]) {
                        this.playerPushSet();
                     }
                  }
               }

               if (var10) {
                  if (objectData[3] > this.PlayerPosY() - var11) {
                     PlayerParam[1] = objectData[3] - var3 << 8;
                     this.setRaidOnSize(objectData[2], var2);
                     this.playerRaidOn(objectData[22]);
                     var10000 = PlayerParam;
                     var10000[0] -= var6 << 8;
                     var12 = 0;
                  } else {
                     PlayerParam[1] = objectData[3] + var3 + var11 + var11 + 1 + 8 << 8;
                     this.setHeadHit();
                  }
               }
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var12 != 0) {
         raidOn = false;
      }

   }

   private void ring_sflag_ring_m10_10_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_10_10_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_20_20_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_10_00_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_20_00_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_00_10_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void ring_sflag_ring_00_20_move_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_move_ikeshita(var1);
   }

   private void elev_nflag_80_move_ikeshita(int var1) {
      this.elev_nflag_move_ikeshita(var1);
   }

   private void elev_nflag_move_ikeshita(int var1) {
      byte var2 = 40;
      byte var3 = 8;
      boolean var4 = false;
      boolean var5 = false;
      int var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      int var9 = -1;
      objectData[7] = objectData[3];
      objectData[11] = objectData[2];
      if (objectData[4] != 1 && objectData[4] != 0) {
         if (objectData[4] == 12 && Math.abs(this.PlayerPosX() - objectData[2]) > 320) {
            objectData[2] = objectData[8];
            objectData[3] = objectData[9];
            objectData[5] = 0;
         }
      } else if (Math.abs(this.PlayerPosX() - objectData[2]) > 320) {
         objectData[2] = objectData[8];
         objectData[3] = objectData[9];
         objectData[5] = 0;
      }

      int var10002;
      if (objectData[4] != 16) {
         if (objectData[5] != 0) {
            if (objectData[4] == 1) {
               var10002 = objectData[5]++;
               if (objectData[5] > 128) {
                  objectData[5] = 128;
               }

               objectData[3] = objectData[9] - objectData[5] * 2;
            } else if (objectData[4] == 0) {
               var10002 = objectData[5]++;
               if (objectData[5] > 64) {
                  objectData[5] = 64;
               }

               objectData[3] = objectData[9] - objectData[5] * 2;
            } else if (objectData[4] == 3) {
               var10002 = objectData[5]++;
               if (objectData[5] > 64) {
                  objectData[5] = 64;
               }

               objectData[3] = objectData[9] + objectData[5] * 2;
            } else if (objectData[4] == 12) {
               var10002 = objectData[5]++;
               if (objectData[5] > 128) {
                  objectData[5] = 128;
               }

               var6 = objectData[2];
               objectData[2] = objectData[8] + objectData[5] * 2;
               objectData[3] = objectData[9] - objectData[5];
               var6 -= objectData[2];
            }
         } else {
            int var14 = this.dSin(objectData[5]) * var8 / 100;
            objectData[3] = objectData[9] + var14;
            int var13 = this.dSin(objectData[5]) * var7 / 100;
            objectData[2] = objectData[8] + var13;
         }

         var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[11], objectData[7], var2, var3);
         if (var9 >= 0 && var9 == 0) {
            int[] var10000;
            if (var7 != 0) {
               var10000 = PlayerParam;
               var10000[0] -= (this.dSin(objectData[5] - 1) * var7 - this.dSin(objectData[5]) * var7 << 8) / 100;
            }

            PlayerParam[1] = objectData[3] - var3 << 8;
            var10000 = PlayerParam;
            var10000[0] -= var6 << 8;
            if (objectData[5] == 0 && Math.abs(this.PlayerPosX() - objectData[2]) <= 48) {
               objectData[5] = 1;
            }

            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
         }
      } else {
         boolean var10 = false;
         boolean var11 = false;
         var10002 = objectData[5]++;

         for(int var12 = 0; var12 < 3; ++var12) {
            int var15 = objectData[3] - (objectData[5] + 1 + var12 * 128) % 384;
            int var16 = objectData[3] - (objectData[5] + var12 * 128) % 384;
            var9 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], var15, objectData[11], var16, var2, var3);
            if (var9 >= 0 && var9 == 0) {
               PlayerParam[1] = var15 - var3 << 8;
               this.setRaidOnSize(objectData[2], var2);
               this.playerRaidOn(objectData[22]);
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var9 != 0) {
         raidOn = false;
      }

   }

   private void mfire_nflag_move_ikeshita(int var1) {
      boolean var2 = false;
      short var3 = 0;
      boolean var4 = false;
      int var5 = objectData[4] / 16;
      if (objectData[18] == 0) {
         objectData[10] = this.cpuTimer;
         objectData[18] = 1;
      }

      int var6 = this.cpuTimer - objectData[10];
      if (var6 % (var5 * 50) == 0 && objectData[2] - mapOxy[0] >= -16 && objectData[2] - mapOxy[0] <= 256 && objectData[3] - mapOxy[1] >= -16 && objectData[3] - mapOxy[1] <= 184) {
         if (objectData[4] != 48 && objectData[4] != 66 && objectData[4] != 65 && objectData[4] != 49 && objectData[4] != 80 && objectData[4] != 64 && objectData[4] != 81) {
            if (objectData[4] != 53 && objectData[4] != 37 && objectData[4] != 69) {
               if (objectData[4] != 55 && objectData[4] != 71 && objectData[4] != 23) {
                  if (objectData[4] == 54) {
                     this.SetObj2(OBJ2_FIREBALL, objectData[2], objectData[3], -200, 0, 0, 0);
                  }
               } else {
                  this.SetObj2(OBJ2_FIREBALL, objectData[2], objectData[3], 200, 0, 0, 0);
               }
            } else {
               this.SetObj2(OBJ2_FIREBALL, objectData[2], objectData[3], 0, 200, 0, 0);
            }
         } else {
            if (objectData[4] == 48) {
               var3 = 96;
            }

            if (objectData[4] == 49) {
               var3 = 128;
            }

            if (objectData[4] == 64) {
               var3 = 96;
            }

            if (objectData[4] == 65) {
               var3 = 128;
            }

            if (objectData[4] == 66) {
               var3 = 196;
            }

            if (objectData[4] == 80) {
               var3 = 72;
            }

            if (objectData[4] == 81) {
               var3 = 136;
            }

            this.SetObj2(OBJ2_FIREBALL5, objectData[2], objectData[3], 0, 0, 0, var3);
         }
      }

   }

   private void yoganc_nflag_move_ikeshita(int var1) {
      byte var3 = 0;
      if (this.stageNumber == 2) {
         var3 = 1;
      }

      if (objectData[4] == 16) {
         int[][] var4 = this.searchObject(BOX_SFLAG, 2);

         int var2;
         for(var2 = 0; var2 < var4.length; ++var2) {
            if (Math.abs(objectData[2] + 32 - 64 * var3 - var4[var2][2]) < 2) {
               if (objectData[5] == 0) {
                  objectData[5] = 1;
                  objectData[10] = this.cpuTimer;
               }

               objectData[7] = var4[var2][20];
               break;
            }
         }

         if (objectData[7] > 0) {
            for(var2 = 0; var2 < var4.length; ++var2) {
               if (var4[var2][20] == objectData[7]) {
                  objectData[6] = var2;
                  break;
               }
            }
         }

         if (objectData[10] != 0) {
            objectData[5] = this.cpuTimer - objectData[10];
         }

         if (objectData[5] > 0 && objectData[7] > 0) {
            if (objectData[5] / 4 > 44) {
               objectData[5] = 0;
               objectData[6] = 0;
               objectData[7] = 0;
               objectData[10] = 0;
            } else if (objectData[5] / 4 > 26) {
               objectData[3] = objectData[9] - (160 - (objectData[5] - 104) * 2);
            } else if (objectData[5] / 4 > 6) {
               objectData[3] = objectData[9] - (objectData[5] - 24) * 2;
            }

            int var10002 = objectData[5]++;
         }

         if (objectData[4] == 16 && objectData[5] / 4 > 0 && objectData[2] - 20 - 32 <= this.PlayerPosX() && objectData[2] - 20 - 32 + 112 >= this.PlayerPosX() && objectData[3] <= this.PlayerPosY() && objectData[9] >= this.PlayerPosY()) {
            this.playdamageset();
         }
      }

   }

   private void ochi_nflag_move_ikeshita(int var1) {
      byte var2 = 32;
      byte var3 = 72;
      boolean var4 = false;
      int var5 = 0;
      boolean var6 = false;
      boolean var7 = false;
      if (objectData[6] == 0 && objectData[7] == 0) {
         objectData[6] = objectData[2];
         objectData[7] = objectData[3];
      }

      if (objectData[4] == 1) {
         objectData[5] = this.cpuTimer;
         byte var9 = 16;
         var5 = this.dSin(objectData[5]) * var9 / 100 - var9;
         var5 *= 2;
         var5 += 2;
      } else if (objectData[4] == 2) {
         objectData[5] = this.cpuTimer;
         byte var10 = -16;
         var5 = this.dSin(objectData[5]) * var10 / 100 + var10;
         var5 *= 2;
         var5 += 2;
      } else if (objectData[4] == 20) {
         var3 = 56;
         if (objectData[5] == 0) {
            if (switchflag[1]) {
               objectData[5] = 1;
               objectData[10] = this.cpuTimer;
            } else {
               objectData[3] = objectData[9] - 160;
            }
         } else {
            objectData[5] = this.cpuTimer - (objectData[10] - 1);
            if (objectData[5] > 72) {
               objectData[5] = 72;
            }

            objectData[3] = objectData[9] - 160 + objectData[5] * 2;
         }

         var5 = 16;
      } else if (objectData[4] == 4) {
         var3 = 56;
         if (!switchflag[0] && objectData[5] == 0) {
            objectData[3] = objectData[9] - 160;
         } else {
            if (switchflag[0] && objectData[5] == 0) {
               objectData[10] = this.cpuTimer;
            }

            objectData[5] = this.cpuTimer - (objectData[10] - 1);
            if (objectData[5] > 72) {
               objectData[5] = 72;
            }

            objectData[3] = objectData[9] - 160 + objectData[5] * 2;
         }

         var5 = 16;
      }

      int var8 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3] + var5, objectData[6], objectData[7], var2, var3);
      if (var8 >= 0) {
         if (var8 == 0) {
            PlayerParam[1] = objectData[3] + var5 - var3 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
         } else if (var8 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var8 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var8 == 3) {
            PlayerParam[1] = objectData[3] + var5 + var3 + 12 + 12 + 1 << 8;
            if (objectData[4] == 1 && (objectData[5] % 360 < 90 || objectData[5] % 360 > 270)) {
               this.setHeadHit();
            } else if (objectData[4] == 2 && objectData[5] % 360 < 270 && objectData[5] % 360 > 90) {
               this.setHeadHit();
            } else if (objectData[4] != 1 && objectData[4] != 2) {
               this.setHeadHit();
            }
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var8 != 0) {
         raidOn = false;
      }

      objectData[7] = objectData[3] + var5;
      objectData[6] = objectData[2];
   }

   private void yari_sflag_move_ikeshita(int var1) {
      int var2 = this.cpuTimer % 132;
      byte var3 = 4;
      boolean var4 = true;
      boolean var5 = false;
      if (objectData[13] == 0 && objectData[12] == 0) {
         objectData[13] = objectData[3];
         objectData[12] = objectData[2];
      }

      objectData[6] = objectData[12];
      objectData[7] = objectData[13];
      byte var8;
      if (var2 < 60) {
         objectData[5] = 0;
         var8 = 20;
      } else if (var2 < 64) {
         objectData[5] = 1;
         var8 = 12;
      } else if (var2 < 124) {
         objectData[5] = 2;
         var8 = 4;
      } else {
         objectData[5] = 3;
         var8 = 12;
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
         byte var9 = var8;
         var8 = var3;
         var3 = var9;
      }

      byte var6 = 12;
      if (!PlayerBall && !PlayerCrouch) {
         var6 = 20;
      }

      int var7 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[12], objectData[13], objectData[6], objectData[7], var3, var8);
      if (var7 >= 0) {
         this.playdamageset();
      } else if (Math.abs(objectData[12] - this.PlayerPosX()) < 12 + var3 && Math.abs(objectData[13] - (this.PlayerPosY() - var6)) < var6 + var8) {
         this.playdamageset();
      }

   }

   private void kazari_sflag_move_ikeshita(int var1) {
      if (this.cpuTimer % 120 == 0) {
         if (objectData[19] == 1) {
            this.SetObj2(OBJ2_KAZARIFIRE, objectData[2] + 16, objectData[3] + 10, 260, 0, 0, 0);
         } else if (objectData[19] == 0) {
            this.SetObj2(OBJ2_KAZARIFIRE, objectData[2] - 16, objectData[3] + 10, -260, 0, 0, 0);
         }
      }

   }

   private void dai3_nflag_move_ikeshita(int var1) {
      byte var2 = 16;
      byte var3 = 16;
      objectData[7] = objectData[3];
      int[] var10000;
      if (objectData[4] == 39) {
         if (objectData[3] < this.waterH2) {
            var10000 = objectData;
            var10000[3] += 8;
            if (this.blockColChk_Enemy(objectData[2], objectData[3] + var3)) {
               var10000 = objectData;
               var10000[3] -= (objectData[3] + var3) % 16;
            } else if (objectData[3] > this.waterH2) {
               objectData[3] = this.waterH2;
            }
         } else if (objectData[3] > this.waterH2) {
            var10000 = objectData;
            var10000[3] -= 8;
            if (this.blockColChk_Enemy(objectData[2], objectData[3] - var3)) {
               var10000 = objectData;
               var10000[3] += (objectData[3] - var3) % 16;
            } else if (objectData[3] < this.waterH2) {
               objectData[3] = this.waterH2;
            }
         }
      } else {
         int var10002;
         int var4;
         int[][] var5;
         if (objectData[4] == 19) {
            var2 = 32;
            var3 = 12;
            objectData[6] = this.cpuTimer;
            if (objectData[10] == 1) {
               if (objectData[11] % 4 == 0 && objectData[11] <= 16) {
                  var10002 = objectData[3]++;
               }

               var10002 = objectData[11]++;
               if (objectData[11] > 76) {
                  objectData[11] = 0;
                  objectData[10] = 2;
               }
            } else if (objectData[10] == 2) {
               var10000 = objectData;
               var10000[3] -= 2;
               var5 = this.searchObject(TOGE_NFLAG, -1);

               for(var4 = 0; var4 < var5.length; ++var4) {
                  if (var5[var4][2] - 20 - (objectData[2] - 16) <= 32 && var5[var4][2] - 20 - (objectData[2] - 16) >= -40 && var5[var4][3] - 60 - (objectData[3] - 16) <= 16 && var5[var4][3] - 60 - (objectData[3] - 16) >= -40) {
                     objectData[10] = 3;
                     break;
                  }
               }
            }
         } else if (objectData[4] == 1) {
            var2 = 16;
            var3 = 16;
            if (objectData[10] != 0) {
               var10002 = objectData[10]++;
               if (objectData[10] > 20) {
                  if (objectData[5] == 0) {
                     var10000 = objectData;
                     var10000[3] += 2;
                     if (this.blockColChk_Enemy(objectData[2] - var2 + 1, objectData[3] + var3) || this.blockColChk_Enemy(objectData[2] + var2 - 1, objectData[3] + var3)) {
                        objectData[5] = 1;
                     }
                  }
               } else {
                  objectData[3] = objectData[9] + 2;
               }
            }

            if (this.zoneNumber == 1 && this.stageNumber == 3) {
               var5 = this.searchObject(SWITCH2_NFLAG, 1);
               switchflag[128] = false;

               for(var4 = 0; var4 < var5.length; ++var4) {
                  if (var5[var4][2] - 8 - (objectData[2] - 16) <= 32 && var5[var4][2] - 8 - (objectData[2] - 16) >= -32 && var5[var4][3] - 8 - (objectData[3] - 16) <= 32 && var5[var4][3] - 8 - (objectData[3] - 16) >= -32) {
                     switchflag[128] = true;
                     switchflag2[128] = true;
                  }
               }
            }
         }
      }

      int var6 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[7], var2, var3);
      if (var6 >= 0) {
         if (var6 == 0) {
            PlayerParam[1] = objectData[3] - var3 << 8;
            this.setRaidOnSize(objectData[2], var2);
            this.playerRaidOn(objectData[22]);
            if ((objectData[4] == 19 || objectData[4] == 1) && objectData[10] == 0) {
               objectData[10] = 1;
            }
         } else if (var6 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[4]) {
               this.playerPushSet();
            }
         } else if (var6 == 2) {
            PlayerParam[0] = objectData[2] + var2 + 12 + 1 << 8;
            PlayerParam[10] = 0;
            if (KeyPress[3]) {
               this.playerPushSet();
            }
         } else if (var6 == 3) {
            PlayerParam[1] = objectData[3] + var3 + 12 + 12 + 1 << 8;
            this.setHeadHit();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var6 != 0) {
         raidOn = false;
      }

   }

   private void kassya_nflag_move_ikeshita(int var1) {
      int var2 = -1;
      boolean var3 = false;
      if (objectData[4] != 127) {
         int var8 = 0;
         int var9 = 0;
         int var10 = this.kassya_nflag_ike_objectPos[objectData[4] - 128].length >> 1;
         int[] var11 = new int[var10 + 1];
         var11[0] = 0;
         if (this.stageNumber == 3) {
            return;
         }

         int var4;
         for(var4 = 0; var4 < this.kassya_nflag_ike_defY[this.stageNumber].length; ++var4) {
            if (this.kassya_nflag_ike_defY[this.stageNumber][var4] == objectData[9] && this.kassya_nflag_ike_defX[this.stageNumber][var4] == objectData[8]) {
               var9 = var4;
            }
         }

         int var6;
         for(var4 = 0; var4 < var10; ++var4) {
            for(var6 = 0; var6 < 2; ++var6) {
               this.beltc_nflag_ike_startPos[var6] = 0;
               this.beltc_nflag_ike_endPos[var6] = 0;
            }

            this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var4 << 1];
            this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var4 << 1) + 1];
            this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var4 + 1) % var10 << 1];
            this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][((var4 + 1) % var10 << 1) + 1];
            if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
               var8 += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
            } else {
               var8 += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
            }

            var11[var4 + 1] = var8;
         }

         int var12 = var8 / 132;
         boolean var13 = false;
         boolean var14 = false;
         boolean var15 = false;
         boolean var16 = false;
         boolean var17 = false;
         boolean var18 = false;
         if (objectData[18] == 0) {
            for(var4 = 0; var4 < kassya_x[var9].length; ++var4) {
               kassya_x[var9][var4] = 0;
               kassya_y[var9][var4] = 0;
            }
         }

         objectData[18] = 1;

         for(var4 = 0; var4 < var12; ++var4) {
            int var21 = (var4 * 132 + this.cpuTimer) % var8;
            if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
               var21 = var8 - var21;
               if (var21 == var8) {
                  var21 = 0;
               }
            }

            int var22 = 0;
            int var23 = 0;
            var16 = false;
            var17 = false;

            for(int var5 = 0; var5 < var10; ++var5) {
               if (var21 < var11[var5 + 1]) {
                  int var26 = var21 - var11[var5 + 1];

                  for(var6 = 0; var6 < 2; ++var6) {
                     this.beltc_nflag_ike_startPos[var6] = 0;
                     this.beltc_nflag_ike_endPos[var6] = 0;
                  }

                  this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var5 << 1];
                  this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var5 << 1) + 1];
                  this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var5 + 1) % var10 << 1];
                  this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][((var5 + 1) % var10 << 1) + 1];
                  if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
                     var22 = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * var26 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
                     var23 = this.beltc_nflag_ike_endPos[1] + var26 * ((this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]));
                  } else {
                     var22 = this.beltc_nflag_ike_endPos[0] + var26 * ((this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]));
                     var23 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * var26 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
                  }
                  break;
               }
            }

            if (kassya_x[var9][var4] == 0 && kassya_y[var9][var4] == 0) {
               kassya_x[var9][var4] = var22;
               kassya_y[var9][var4] = var23;
            }

            int var24 = kassya_x[var9][var4];
            int var25 = kassya_y[var9][var4];
            kassya_x[var9][var4] = var22;
            kassya_y[var9][var4] = var23;
            byte var19 = 16;
            byte var20 = 8;
            if (!var3) {
               var2 = this.ObjectColChk(this.PlayerPosX(), this.PlayerPosY() - 12, ploldpos[0], ploldpos[1] - 12, 12, 12, var22, var23, var24, var25, var19, var20);
               if (var2 >= 0 && var2 == 0) {
                  PlayerParam[1] = var23 - var20 << 8;
                  int[] var10000 = PlayerParam;
                  var10000[0] -= var24 - var22 << 8;
                  this.setRaidOnSize(var22, var19);
                  this.playerRaidOn(objectData[22]);
                  raidObjectNumSub = var4;
                  var3 = true;
               }
            }
         }
      }

      if (var3) {
         var2 = 0;
      }

      if (raidOn && raidObjectNum == objectData[20] && var2 != 0) {
         raidOn = false;
      }

   }

   private void shima2_nflag_move_ikeshita(int var1) {
      this.shima_nflag_move_ikeshita(var1);
   }

   private void bou_nflag_move_ikeshita(int var1) {
      byte var2 = 4;
      byte var3 = 32;
      int var4 = this.ObjectColChk(this.PlayerPosX() - 35, this.PlayerPosY() - 12, ploldpos[0] - 35, ploldpos[1] - 12, 12, 12, objectData[2], objectData[3], objectData[2], objectData[3], var2, var3);
      if (objectData[10] >= 300) {
         var4 = -1;
      }

      if (var4 >= 0) {
         if (var4 == 1) {
            PlayerParam[0] = objectData[2] - var2 - 12 + 35 << 8;
            PlayerBou = true;
            int var10002 = objectData[10]++;
         }

         if (objectData[3] - this.PlayerPosY() > 4) {
            PlayerParam[1] = objectData[3] - 4 << 8;
         } else if (objectData[3] - this.PlayerPosY() < -32) {
            PlayerParam[1] = objectData[3] + 32 << 8;
         }

         if (KeyPress[0]) {
            int[] var10000 = PlayerParam;
            var10000[0] += 256;
            ploldpos[0] = this.PlayerPosX();
         }
      }

      if (raidOn && raidObjectNum == objectData[20] && var4 != 0) {
         raidOn = false;
      }

   }

   private void ring_sflag_ring_18_00_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      int var3 = this.animeTimer % 4 * 16;
      if (this.animeTimer % 4 == 3) {
         var2 = TRANS_MIRROR;
         var3 = 16;
      }

      if (objectData[5] == 0) {
         this.drawRegion(gg, this.m_imgObj[0], 0, var3, 16, 16, rotNumTable[var2], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8, 20);
      } else {
         this.drawRegion(gg, this.m_imgObj[0], 16, (this.cpuTimer - objectData[10]) / 5 % 4 * 16, 16, 16, rotNumTable[0], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 8, 20);
      }

      if (objectData[11] == 1) {
         if (objectData[5] == 0) {
            this.drawRegion(gg, this.m_imgObj[0], 0, var3, 16, 16, rotNumTable[var2], objectData[2] - mapView[0] - 8, objectData[12] - mapView[1] - 8, 20);
         } else {
            this.drawRegion(gg, this.m_imgObj[0], 16, (this.cpuTimer - objectData[10]) / 5 % 4 * 16, 16, 16, rotNumTable[0], objectData[2] - mapView[0] - 8, objectData[12] - mapView[1] - 8, 20);
         }
      }

   }

   private void ring_sflag_ring_00_18_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void buranko_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      int var3 = this.dSin(this.animeTimer * 3) * 87;
      int var4 = objectData[4] + 1;
      if (objectData[4] == 5) {
         var3 = -var3;
      } else if (objectData[19] == 1) {
         var3 = -var3;
      }

      int var5;
      Graphics var10001;
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      for(var5 = 1; var5 < var4; ++var5) {
         if (this.zoneNumber == 3) {
            var10001 = gg;
            var10002 = this.m_imgObj[3];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + var5 * this.dSin(180 + var3 / 100) * 16 / 100;
            var10009 = objectData[3] - mapView[1] + var5 * this.dCos(180 + var3 / 100) * 16 / 100;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var10001, var10002, 36, 48, 16, 16, var10007, var10008, var10009, 1 | 2);
         } else if (this.zoneNumber != 5) {
            var10001 = gg;
            var10002 = this.m_imgObj[3];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + var5 * this.dSin(180 + var3 / 100) * 16 / 100;
            var10009 = objectData[3] - mapView[1] + var5 * this.dCos(180 + var3 / 100) * 16 / 100;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var10001, var10002, 16, 32, 16, 16, var10007, var10008, var10009, 1 | 2);
         } else {
            var10001 = gg;
            var10002 = this.m_imgObj[3];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + var5 * this.dSin(180 + var3 / 100) * 16 / 100;
            var10009 = objectData[3] - mapView[1] + var5 * this.dCos(180 + var3 / 100) * 16 / 100;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(var10001, var10002, 0, 96, 16, 16, var10007, var10008, var10009, 1 | 2);
         }
      }

      if (this.zoneNumber == 3) {
         var10002 = this.m_imgObj[3];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 36, 64, 16, 16, var10007, var10008, var10009, 1 | 2);
      } else if (this.zoneNumber != 5) {
         var10002 = this.m_imgObj[3];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 16, 16, 16, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[3];
         int var10004 = this.cpuTimer / 3 % 3 * 32;
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 32, 32, var10007, var10008, var10009, 1 | 2);
      }

      if (this.zoneNumber == 3) {
         var5 *= 16;
         var5 += 8;
         var10001 = gg;
         var10002 = this.m_imgObj[3];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + this.dSin(180 + var3 / 100) * var5 / 100;
         var10009 = objectData[3] - mapView[1] + this.dCos(180 + var3 / 100) * var5 / 100;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(var10001, var10002, 0, 0, 88, 48, var10007, var10008, var10009, 1 | 2);
      } else if (this.zoneNumber != 5) {
         var5 *= 16;
         var5 -= 8;
         var10001 = gg;
         var10002 = this.m_imgObj[3];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + this.dSin(180 + var3 / 100) * var5 / 100;
         var10009 = objectData[3] - mapView[1] + this.dCos(180 + var3 / 100) * var5 / 100;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(var10001, var10002, 0, 0, 48, 16, var10007, var10008, var10009, 1 | 2);
      } else {
         var5 *= 16;
         var5 -= 24;
         var10001 = gg;
         var10002 = this.m_imgObj[60];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + this.dSin(180 + var3 / 100) * var5 / 100;
         var10009 = objectData[3] - mapView[1] + this.dCos(180 + var3 / 100) * var5 / 100;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(var10001, var10002, 0, 0, 48, 48, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void hashi_nflag_draw_ikeshita(int var1) {
      boolean var3 = false;

      for(int var2 = 0; var2 < 12; ++var2) {
         int var4 = objectData[10] - Math.abs(objectData[5] - var2);
         if (var4 < 0) {
            var4 = 0;
         }

         if (var2 == 0 || var2 == 11) {
            var4 = 0;
         }

         Image var10002 = this.m_imgObj[5];
         int var10007 = rotNumTable[TRANS_NONE];
         int var10008 = objectData[2] - mapView[0] - 96 + var2 * 16;
         int var10009 = objectData[3] - mapView[1] + var4;
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 32, 0, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void thashi_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      boolean var3 = false;
      int[] var4 = new int[]{-4, 4};

      for(int var5 = 0; var5 < 12; ++var5) {
         int var6 = (this.animeTimer / 10 + (12 - var5)) % 7;
         Image var10002 = this.m_imgObj[4];
         int var10004 = var6 * 24;
         int var10007 = rotNumTable[TRANS_NONE];
         int var10008 = objectData[2] - mapView[0] - 96 + var5 * 16;
         int var10009 = objectData[3] - mapView[1] + var4[var6 / 4];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 16, 24, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void break_sflag_draw_ikeshita(int var1) {
      int var2 = rotNumTable[TRANS_NONE];
      int var4 = 0;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      if (objectData[4] != 0) {
         var2 = rotNumTable[TRANS_MIRROR];
         var7 = true;
      }

      if (objectData[5] != 0) {
         var4 = this.cpuTimer / 2 - objectData[10];
      }

      for(int var3 = 0; var3 < 36; ++var3) {
         int var8 = var4 - var3 * 3;
         if (var8 < 0) {
            var8 = 0;
         } else {
            var8 *= var4 / 6;
         }

         int var9;
         if (objectData[4] != 0) {
            var9 = 96 - var3 / 6 * 16 - 16;
         } else {
            var9 = var3 / 6 * 16;
         }

         Image var10002 = this.m_imgObj[6];
         int var10003 = var3 / 6 * 16;
         int var10004 = 96 - (var3 % 6 + 1) * 16;
         int var10008 = objectData[2] - mapView[0] + var9 - 40;
         int var10009 = objectData[3] - mapView[1] + 96 - (var3 % 6 + 1) * 16 - 48 + var8;
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, var10003, var10004, 16, 16, var2, var10008, var10009, 1 | 2);
      }

   }

   private void yuka_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      byte var5;
      if (objectData[4] == 21) {
         var5 = 2;
      } else if (objectData[4] == 1) {
         var5 = 0;
      } else {
         var5 = 1;
      }

      int var3;
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] == 21) {
         var10002 = this.m_imgObj[7];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 0, 128, 96, var10007, var10008, var10009, 1 | 2);
         if (objectData[16] >= 60) {
            boolean var4 = false;

            for(var3 = 0; var3 < 8; ++var3) {
               int var6 = this.animeTimer - objectData[15];
               if (var3 << 3 < var6) {
                  var6 = var3 << 3;
               }

               var10002 = this.m_imgObj[101];
               int var10004 = (this.animeTimer + (var3 << 1)) % 4 << 5;
               var10007 = rotNumTable[TRANS_NONE];
               var10008 = objectData[2] - mapView[0] + (var6 << 1) - 56;
               var10009 = objectData[3] - mapView[1] - this.yuka_nflag_ike_yuka[var5][var6];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, var10004, 24, 32, var10007, var10008, var10009, 1 | 2);
            }
         }
      } else if (objectData[4] == 1) {
         var10002 = this.m_imgObj[7];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 96, 128, 80, var10007, var10008, var10009, 1 | 2);
      } else {
         for(var3 = 0; var3 < 4; ++var3) {
            this.drawRegion(gg, this.m_imgObj[7], 0, 0, 16, 96, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32 + (var3 << 4), objectData[3] - mapView[1] - 48, 20);
         }
      }

   }

   private void turi_nflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      for(int var2 = 0; var2 <= (objectData[3] - objectData[9]) / 16; ++var2) {
         var10002 = this.m_imgObj[94];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[8] - mapView[0];
         var10009 = objectData[9] - mapView[1] - 24 + var2 * 16 + (objectData[3] - objectData[9]) % 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 8, 32, 16, var10007, var10008, var10009, 1 | 2);
      }

      var10002 = this.m_imgObj[94];
      var10007 = rotNumTable[TRANS_NONE];
      var10008 = objectData[8] - mapView[0];
      var10009 = objectData[9] - mapView[1] - 24;
      var10010 = gg;
      var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 32, 8, var10007, var10008, var10009, 1 | 2);
      if (objectData[4] != 128 && objectData[4] != 2) {
         if (objectData[4] == 35) {
            var10002 = this.m_imgObj[95];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 32, 24, var10007, var10008, var10009, 1 | 2);
         } else if (objectData[4] == 17 || objectData[4] == 18) {
            var10002 = this.m_imgObj[8];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1] + 28;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 24, 112, 32, var10007, var10008, var10009, 1 | 2);
            this.drawRegion(gg, this.m_imgObj[8], 0, 0, 48, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 48, objectData[3] - mapView[1] - 12, 20);
            this.drawRegion(gg, this.m_imgObj[8], 64, 0, 48, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0], objectData[3] - mapView[1] - 12, 20);
         }
      } else {
         var10002 = this.m_imgObj[8];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + 28;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 24, 112, 32, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[8];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 112, 24, var10007, var10008, var10009, 1 | 2);
      }

      var10002 = this.m_imgObj[94];
      var10007 = rotNumTable[TRANS_NONE];
      var10008 = objectData[2] - mapView[0];
      var10009 = objectData[3] - mapView[1] - 16;
      var10010 = gg;
      var10011 = gg;
      this.drawRegion(gg, var10002, 0, 24, 32, 8, var10007, var10008, var10009, 1 | 2);
   }

   private void toge_nflag_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      if (objectData[19] == 2) {
         var2 = TRANS_ROT180;
      }

      Image var10002;
      int var3;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] == 48) {
         for(var3 = 0; var3 < 3; ++var3) {
            var10002 = this.m_imgObj[9];
            var10007 = rotNumTable[var2];
            var10008 = objectData[2] - mapView[0] - 24 + var3 * 24;
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 8, 32, var10007, var10008, var10009, 1 | 2);
         }
      } else if (objectData[4] == 64) {
         if (this.zoneNumber != 1 || this.stageNumber != 0 || objectData[4] != 64 || objectData[19] == 0) {
            for(var3 = 0; var3 < 6; ++var3) {
               var10002 = this.m_imgObj[9];
               var10007 = rotNumTable[var2];
               var10008 = objectData[2] - mapView[0] - 60 + var3 * 24;
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 16, 0, 8, 32, var10007, var10008, var10009, 1 | 2);
            }
         }
      } else if (objectData[4] != 18 && objectData[4] != 16) {
         if (objectData[4] == 82) {
            if (objectData[19] == 0) {
               var10002 = this.m_imgObj[9];
               var10007 = rotNumTable[TRANS_MIRROR_ROT270];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 16, 0, 8, 32, var10007, var10008, var10009, 1 | 2);
            } else {
               var10002 = this.m_imgObj[9];
               var10007 = rotNumTable[TRANS_ROT90];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 16, 0, 8, 32, var10007, var10008, var10009, 1 | 2);
            }
         } else if (objectData[4] == 32) {
            var10002 = this.m_imgObj[9];
            var10007 = rotNumTable[var2];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 16, 0, 8, 32, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[9];
            var10007 = rotNumTable[var2];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 40, 32, var10007, var10008, var10009, 1 | 2);
         }
      } else {
         if (objectData[19] == 0) {
            var2 = TRANS_ROT270;
         } else {
            var2 = TRANS_ROT90;
         }

         var10002 = this.m_imgObj[9];
         var10007 = rotNumTable[var2];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 40, 32, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void box_sflag_draw_ikeshita(int var1) {
      if (objectData[4] == 129) {
         for(int var2 = 0; var2 < 4; ++var2) {
            this.drawImage(gg, this.m_imgObj[54], objectData[2] - mapView[0] - 62 + 31 * var2, objectData[3] - mapView[1] - 16, 20);
         }
      } else {
         Image var10002 = this.m_imgObj[54];
         int var10003 = objectData[2] - mapView[0];
         int var10004 = objectData[3] - mapView[1];
         Graphics var10005 = gg;
         Graphics var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
         this.DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4);
      }

   }

   private void fblock_nflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[11];
      int var10003 = objectData[2] - mapView[0];
      int var10004 = objectData[3] - mapView[1];
      Graphics var10005 = gg;
      Graphics var10006 = gg;
      this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      if (objectData[4] == 2 || objectData[4] == 10) {
         this.DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4);
      }

   }

   private void dainfla_draw_ikeshita(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] != 2 && objectData[4] != 1) {
         if (objectData[4] == 65) {
            for(int var2 = 0; var2 < 3; ++var2) {
               var10002 = this.m_imgObj[54];
               int var10003 = objectData[2] - mapView[0] + (var2 << 5);
               int var10004 = objectData[3] - mapView[1];
               Graphics var10005 = gg;
               Graphics var10006 = gg;
               this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
            }
         } else {
            int var3;
            if (objectData[4] == 57) {
               for(var3 = 1; var3 < 15; ++var3) {
                  this.drawRegion(gg, this.m_imgObj[12], 0, 24, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64 + (var3 << 3), objectData[3] - mapView[1] - 8, 20);
               }

               this.drawRegion(gg, this.m_imgObj[12], 0, 0, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64, objectData[3] - mapView[1] - 8, 20);
               this.drawRegion(gg, this.m_imgObj[12], 0, 0, 8, 24, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] + 56, objectData[3] - mapView[1] - 8, 20);
            } else if (objectData[4] == 40) {
               for(var3 = 0; var3 < 8; ++var3) {
                  this.drawRegion(gg, this.m_imgObj[12], 8, var3 % 2 * 24, 8, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32 + (var3 << 3), objectData[3] - mapView[1] - 8, 20);
               }
            } else if ((objectData[4] == 7 || objectData[4] == 4) && objectData[5] != 0) {
               var10002 = this.m_imgObj[12];
               var10007 = rotNumTable[TRANS_MIRROR];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 0, 32, 16, var10007, var10008, var10009, 1 | 2);
            }
         }
      } else {
         var10002 = this.m_imgObj[54];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 31, 32, var10007, var10008, var10009, 1 | 2);
         if (objectData[4] == 2) {
            this.DrawMapRegion((objectData[2] - mapView[0] >> 4) - 1, (objectData[3] - mapView[1] >> 4) - 1, 4, 4);
         }
      }

   }

   private void yogan2_sflag_draw_ikeshita(int var1) {
      Image var10002;
      int var2;
      int var10004;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      for(var2 = 0; var2 < 2; ++var2) {
         var10002 = this.m_imgObj[13];
         var10004 = (this.animeTimer + var2) % 3 << 5;
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + (var2 << 5) + 48;
         var10009 = objectData[3] - mapView[1] + (var2 << 5) - 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 32, 32, var10007, var10008, var10009, 1 | 2);
      }

      boolean var4 = false;

      for(var2 = 0; var2 < 4; ++var2) {
         int var5 = objectData[2] - mapView[0] + (var2 >> 1 << 5) + 16;

         for(int var3 = 0; var3 < var5 / 32 + 2; ++var3) {
            var10002 = this.m_imgObj[99];
            var10004 = (this.animeTimer + var2) % 3 * 16;
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + (var2 >> 1 << 5) + 16 - (var3 << 5);
            var10009 = objectData[3] - mapView[1] + (var2 << 4) - 24;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 32, 16, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   private void myogan_nflag_draw_ikeshita(int var1) {
      if (objectData[5] / 4 != 0 && objectData[3] - 32 < objectData[9]) {
         this.drawRegion(gg, this.m_imgObj[98], 0, 32 * (this.animeTimer % 2), 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20);
      }

      int var3 = objectData[5] / 4;
      if (var3 > 6) {
         var3 = 6;
      }

      int var2;
      for(var2 = 1; var2 < var3; ++var2) {
         if (objectData[3] - var2 * 32 - 32 + 32 < objectData[9]) {
            this.drawRegion(gg, this.m_imgObj[14], 0, 32 * (var2 % 2), 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - var2 * 32 - 32, 20);
         } else if (objectData[3] - var2 * 32 - 32 < objectData[9]) {
            this.drawRegion(gg, this.m_imgObj[14], 0, 32 * (var2 % 2), 64, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - var2 * 32 - 32, 20);
         }
      }

      if (objectData[5] / 4 != 0 && objectData[3] - var2 * 32 - 32 < objectData[9]) {
         this.drawRegion(gg, this.m_imgObj[98], 0, 64 + 32 * (this.animeTimer % 2), 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - var2 * 32 - 32, 20);
      }

      if (objectData[10] > 0 && this.myogan_nflag_ike_ani.length > objectData[10] / 3) {
         Image var10002 = this.m_imgObj[77];
         int var10004 = 32 * this.myogan_nflag_ike_ani[objectData[10] / 3];
         int var10007 = rotNumTable[TRANS_NONE];
         int var10008 = objectData[8] - mapView[0];
         int var10009 = objectData[9] - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 112, 32, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void switch2_nflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (switchflag[objectData[4]]) {
         var10002 = this.m_imgObj[15];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 16, 32, 8, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[15];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 3;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 11, var10007, var10008, var10009, 1 | 2);
      }

      var10002 = this.m_imgObj[15];
      var10007 = rotNumTable[TRANS_NONE];
      var10008 = objectData[2] - mapView[0];
      var10009 = objectData[3] - mapView[1] + 5;
      var10010 = gg;
      var10011 = gg;
      this.drawRegion(gg, var10002, 0, 11, 32, 5, var10007, var10008, var10009, 1 | 2);
   }

   private void shima_nflag_draw_ikeshita(int var1) {
      int var2;
      if (this.zoneNumber != 3) {
         Image var10002;
         int var10008;
         int var10009;
         Graphics var10010;
         Graphics var10011;
         if (objectData[4] != 16) {
            var10002 = this.m_imgObj[16];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1] + objectData[14];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 64, 32, 0, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[16];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1] + 8 + objectData[14];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 32, 64, 48, 0, var10008, var10009, 1 | 2);

            for(var2 = 5; var2 >= 0; --var2) {
               var10002 = this.m_imgObj[16];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1] + 8 + 48 - 16 + 16 * var2 + objectData[14];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 64, 64, 16, 0, var10008, var10009, 1 | 2);
            }
         }
      } else {
         int var4 = this.animeTimer / 4 % 6;
         if (var4 > 3) {
            var4 = 6 - var4;
         }

         for(int var3 = 0; var3 < 4; ++var3) {
            for(var2 = 0; var2 < 3; ++var2) {
               this.drawRegion(gg, this.m_imgObj[73], 0, var2 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var3 % 2)], objectData[2] - mapView[0] - 40 + var3 * 16 + 8, objectData[3] - mapView[1] - 8 + var2 * 8 + objectData[14], 20);
            }

            this.drawRegion(gg, this.m_imgObj[73], 0, var2 * 8 + var4 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var3 % 2)], objectData[2] - mapView[0] - 40 + var3 * 16 + 8, objectData[3] - mapView[1] - 8 + var2 * 8 + objectData[14], 20);
         }
      }

   }

   private void dai2_nflag_draw_ikeshita(int var1) {
      this.dai2_sflag_draw_ikeshita(var1);
   }

   private void brkabe_sflag_draw_ikeshita(int var1) {
      boolean var3 = true;
      byte var4;
      if (objectData[4] == 0) {
         var4 = 0;
      } else {
         var4 = 1;
      }

      int var2;
      if (objectData[10] != 1) {
         for(var2 = 0; var2 < 4; ++var2) {
            this.drawRegion(gg, this.m_imgObj[18], var4 * 16, 0, 16, 16, 0, objectData[2] - mapView[0] - 8 - 8, objectData[3] - mapView[1] - 16 - 16 + var2 * 16, 20);
         }
      }

      if (objectData[4] == 2) {
         var4 = 2;
      } else {
         var4 = 1;
      }

      if (objectData[11] != 1) {
         for(var2 = 0; var2 < 4; ++var2) {
            this.drawRegion(gg, this.m_imgObj[18], var4 * 16, 0, 16, 16, 0, objectData[2] - mapView[0] - 8 - 8 + 16, objectData[3] - mapView[1] - 16 - 16 + var2 * 16, 20);
         }
      }

   }

   private void pedal_nflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[PEDAL];
      int var10003 = objectData[2] - mapView[0];
      int var10004 = objectData[3] - mapView[1];
      Graphics var10005 = gg;
      Graphics var10006 = gg;
      this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
   }

   private void break2_nflag_draw_ikeshita(int var1) {
      int var3 = 27;
      int var5 = 0;
      if (objectData[5] < 129) {
         if (this.zoneNumber == 3) {
            var3 = STEP;
         }

         for(int var2 = 0; var2 < 8; ++var2) {
            int var4 = this.break2_nflag_ike_brockTable[var2];
            if (this.break2_nflag_ike_brockTable[var2] % 4 >= 2) {
               var4 -= 2;
            }

            if (objectData[15] != 0) {
               var5 = this.cpuTimer - objectData[16] - this.break2_nflag_ike_brockTimeTable[var2];
               if (var5 < 0) {
                  var5 = 0;
               }
            }

            Image var10002 = this.m_imgObj[var3];
            int var10003 = var4 % 4 * 16;
            int var10004 = var4 / 4 * 16;
            int var10008 = objectData[2] - mapView[0] + this.break2_nflag_ike_brockTable[var2] % 4 * 16 - 16 - 8;
            int var10009 = objectData[3] - mapView[1] + this.break2_nflag_ike_brockTable[var2] / 4 * 16 + var5 * (var5 / 5);
            Graphics var10010 = gg;
            Graphics var10011 = gg;
            this.drawRegion(gg, var10002, var10003, var10004, 16, 16, 0, var10008, var10009, 1 | 2);
         }
      }

   }

   private void step_nflag_draw_ikeshita(int var1) {
      byte var4 = 0;
      if (objectData[18] > 0) {
         var4 = 1;
      }

      int var5 = this.animeTimer / 4 % 6;
      if (var5 > 3) {
         var5 = 6 - var5;
      }

      for(int var3 = 0; var3 < 8; ++var3) {
         int var6 = objectData[5];
         if (var6 > 60) {
            var6 -= 60;
         } else {
            var6 = 0;
         }

         int var7 = var6 / 4 * ((var3 + 2) / 2);
         if (objectData[19] == 0) {
            var7 = var6 / 4 * (4 - var3 / 2);
         }

         int var2;
         for(var2 = 0; var2 < 3; ++var2) {
            this.drawRegion(gg, this.m_imgObj[73], 0, var2 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var3 % 2)], objectData[2] - mapView[0] - 16 + var3 * 16, objectData[3] - mapView[1] - 16 + var2 * 8 + var7 + var4 * this.step_nflag_ike_gura[this.animeTimer % 2][var3 / 2 % 2], 20);
         }

         this.drawRegion(gg, this.m_imgObj[73], 0, var2 * 8 + var5 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var3 % 2)], objectData[2] - mapView[0] - 16 + var3 * 16, objectData[3] - mapView[1] - 16 + var2 * 8 + var7 + var4 * this.step_nflag_ike_gura[this.animeTimer % 2][var3 / 2 % 2], 20);
      }

   }

   private void fun_nflag_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      boolean var3 = false;
      if (objectData[19] != 0) {
         var2 = TRANS_MIRROR;
      }

      int var4 = objectData[2] - this.PlayerPosX();
      Image var10002 = this.m_imgObj[22];
      int var10004 = objectData[5] % 3 * 32;
      int var10007 = rotNumTable[var2];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, var10004, 32, 32, var10007, var10008, var10009, 1 | 2);
   }

   private void pata_nflag_draw_ikeshita(int var1) {
      if (objectData[4] != 1 & objectData[4] != 2) {
         byte var2 = 0;
         if (objectData[13] < 8) {
            switch(objectData[13] / 2) {
            case 1:
               var2 = 5;
               break;
            case 2:
               var2 = 3;
               break;
            case 3:
               var2 = 6;
               break;
            default:
               var2 = 0;
            }

            Image var10002;
            int var10008;
            int var10009;
            Graphics var10010;
            Graphics var10011;
            if (objectData[13] % 2 == 0) {
               var10002 = this.m_imgObj[BELTC];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 1, 32, 14, var2, var10008, var10009, 1 | 2);
            } else {
               var10002 = this.m_imgObj[BELTC];
               var10008 = objectData[2] - mapView[0];
               var10009 = objectData[3] - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 17, 32, 30, var2, var10008, var10009, 1 | 2);
            }
         } else {
            this.drawRegion(gg, this.m_imgObj[BELTC], 0, 1, 32, 14, var2, objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] - 7, 20);
         }
      } else {
         this.pata_draw(var1);
      }

   }

   private void fire6_nflag_draw_ikeshita(int var1) {
      int var3 = TRANS_NONE;
      int var4 = 0;
      if (objectData[19] == 1) {
         var3 = TRANS_MIRROR;
      } else if (objectData[19] == 2) {
         var3 = TRANS_ROT180;
      }

      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[19] == 0) {
         var10002 = this.m_imgObj[26];
         var10007 = rotNumTable[var3];
         var10008 = objectData[2] - mapView[0] + 1 - 3 + 3 - 2;
         var10009 = objectData[3] - mapView[1] - 8 + 52 + 8 + 4 - 8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 24, 0, 8, 16, var10007, var10008, var10009, 1 | 2);
      } else if (objectData[19] == 1) {
         var10002 = this.m_imgObj[26];
         var10007 = rotNumTable[var3];
         var10008 = objectData[2] - mapView[0] + 1 - 3 + 3 - 2 + 2;
         var10009 = objectData[3] - mapView[1] - 8 + 52 + 8 + 4 - 8;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 24, 0, 8, 16, var10007, var10008, var10009, 1 | 2);
      }

      boolean var5 = true;
      int var6 = objectData[5] / 4;
      if (var6 % 30 < 6) {
         var6 %= 30;
      } else if (var6 % 30 > 20 && var6 % 30 <= 25) {
         var6 = 5 - (var6 - 20) % 30;
      } else if (var6 % 30 >= 25) {
         var6 = 0;
      } else {
         var6 = 5;
      }

      for(int var2 = 0; var2 < var6; ++var2) {
         var4 += this.fire6_nflag_ike_sizeTable2[4 - var2] - this.fire6_nflag_ike_posTable[4 - var2];
         int var10004;
         int var10006;
         if (objectData[19] != 2) {
            var10002 = this.m_imgObj[26];
            var10004 = this.fire6_nflag_ike_animeTable[4 - var2];
            var10006 = this.fire6_nflag_ike_sizeTable[4 - var2];
            var10007 = rotNumTable[this.fire6_nflag_ike_rotTable[objectData[19]][this.animeTimer / 2 % 2]];
            var10008 = objectData[2] - mapView[0] - 10 + 3 + 7;
            var10009 = objectData[3] - mapView[1] - 8 + 52 - var4 + 5;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 24, var10006, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[26];
            var10004 = this.fire6_nflag_ike_animeTable[4 - var2];
            var10006 = this.fire6_nflag_ike_sizeTable[4 - var2];
            var10007 = rotNumTable[this.fire6_nflag_ike_rotTable[objectData[19]][this.animeTimer / 2 % 2]];
            var10008 = objectData[2] - mapView[0] - 10 - 2 + 3 + 12;
            var10009 = objectData[3] - mapView[1] - 8 - 52 + var4 + 18;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 24, var10006, var10007, var10008, var10009, 1 | 2);
         }
      }

      if (objectData[19] == 2) {
         var10002 = this.m_imgObj[26];
         var10007 = rotNumTable[var3];
         var10008 = objectData[2] - mapView[0] + 4 + 3;
         var10009 = objectData[3] - mapView[1] - 54 + 8 - 8 + 5;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 24, 32, 8, 16, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[26];
         var10007 = rotNumTable[var3];
         var10008 = objectData[2] - mapView[0] - 8 + 4 + 3;
         var10009 = objectData[3] - mapView[1] - 54 + 8 - 8 + 5;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 24, 48, 8, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void bryuka_nflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[27];
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 32, 32, var10007, var10008, var10009, 1 | 2);
   }

   private void mawaru_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      Image var10002 = this.m_imgObj[28];
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0] - 1;
      int var10009 = objectData[3] - mapView[1] - 1;
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 94, 94, var10007, var10008, var10009, 1 | 2);
      byte var3;
      switch(objectData[15] / 2) {
      case 1:
         var3 = 6;
         break;
      case 2:
         var3 = 3;
         break;
      case 3:
         var3 = 5;
         break;
      default:
         var3 = 0;
      }

      if (objectData[15] % 2 == 0) {
         this.drawRegion(gg, this.m_imgObj[28], 25, 95, 48, 40, var3, objectData[2] - mapView[0] + objectData[10] - 1, objectData[3] - mapView[1] + objectData[11] - 1, 20);
      } else {
         this.drawRegion(gg, this.m_imgObj[28], 24, 135, 47, 47, var3, objectData[2] - mapView[0] + objectData[10] - 1, objectData[3] - mapView[1] + objectData[11] - 1, 20);
      }

   }

   private void yukai_nflag_draw_ikeshita(int var1) {
      for(int var2 = 1; var2 < 11; ++var2) {
         this.drawRegion(gg, this.m_imgObj[29], 0, 48, 16, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96 + 16 * var2, objectData[3] - mapView[1] - 24, 20);
      }

      this.drawRegion(gg, this.m_imgObj[29], 0, 0, 16, 48, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 96, objectData[3] - mapView[1] - 24, 20);
      this.drawRegion(gg, this.m_imgObj[29], 0, 0, 16, 48, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0] - 96 + 176, objectData[3] - mapView[1] - 24, 20);
   }

   private void door_nflag_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      byte var3 = 0;
      if (objectData[19] != 0) {
         var2 = TRANS_MIRROR;
         var3 = 8;
      }

      this.drawRegion(gg, this.m_imgObj[30], 0, objectData[15] % 4 * 64, 8, 32, rotNumTable[var2], objectData[2] - mapView[0] - 8 + var3, objectData[3] - mapView[1] - 32 - objectData[10] * 8, 20);
      this.drawRegion(gg, this.m_imgObj[30], 0, 32 + objectData[15] % 4 * 64, 8, 32, rotNumTable[var2], objectData[2] - mapView[0] - 8 + var3, objectData[3] - mapView[1] + objectData[10] * 8, 20);
      this.drawRegion(gg, this.m_imgObj[30], 8, objectData[5] % 4 * 64, 8, 32, rotNumTable[var2], objectData[2] - mapView[0] - var3, objectData[3] - mapView[1] - 32 - objectData[10] * 8, 20);
      this.drawRegion(gg, this.m_imgObj[30], 8, 32 + objectData[5] % 4 * 64, 8, 32, rotNumTable[var2], objectData[2] - mapView[0] - var3, objectData[3] - mapView[1] + objectData[10] * 8, 20);
   }

   private void yukae_nflag_draw_ikeshita(int var1) {
      int var2 = 16;
      boolean var3 = false;
      boolean var4 = false;
      int var6 = (objectData[5] - objectData[4] / 2 + 256) % 128;
      if (var6 < 128) {
         if (var6 < 16) {
            var2 = var6;
         } else if (var6 > 64 && var6 < 80) {
            var2 = 80 - var6;
         } else if (var6 >= 80) {
            var2 = -1;
         }

         if (var2 > 0) {
            byte var5 = 0;
            if (var2 <= 8 && var2 > 4) {
               var5 = 1;
            } else if (var2 <= 4) {
               var5 = 2;
            }

            this.drawRegion(gg, this.m_imgObj[31], 0, var5 * 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 16, objectData[3] + 8 - mapView[1] - 16, 20);
         }
      }

   }

   private void dai4_nflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] == 203) {
         var10002 = this.m_imgObj[32];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 64 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 256, 32, var10007, var10008, var10009, 1 | 2);

         for(int var2 = 0; var2 < 3; ++var2) {
            for(int var3 = 2 - var2; var3 >= 0; --var3) {
               if (var3 != 2 - var2) {
                  var10002 = this.m_imgObj[32];
                  var10007 = rotNumTable[TRANS_NONE];
                  var10008 = objectData[2] - mapView[0] + 64 * var2 - 128 + 16;
                  var10009 = objectData[3] - mapView[1] - 64 + 16 + 32 + var3 * 32;
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(gg, var10002, 256, 0, 32, 32, var10007, var10008, var10009, 1 | 2);
               } else {
                  var10002 = this.m_imgObj[32];
                  var10007 = rotNumTable[TRANS_NONE];
                  var10008 = objectData[2] - mapView[0] + 64 * var2 - 128 + 16;
                  var10009 = objectData[3] - mapView[1] - 64 + 16 + 32 + var3 * 32;
                  var10010 = gg;
                  var10011 = gg;
                  this.drawRegion(gg, var10002, 288, 0, 32, 32, var10007, var10008, var10009, 1 | 2);
               }
            }
         }
      } else if (objectData[4] != 64) {
         if (objectData[4] >= 128) {
            this.drawRegion(gg, this.m_imgObj[32], 0, 64, 64, 24, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 64, objectData[3] - mapView[1] - 12, 20);
            this.drawRegion(gg, this.m_imgObj[32], 0, 64, 64, 24, rotNumTable[TRANS_MIRROR], objectData[2] - mapView[0], objectData[3] - mapView[1] - 12, 20);
         } else {
            var10002 = this.m_imgObj[32];
            var10007 = rotNumTable[TRANS_NONE + 6];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 56, 64, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   private void ele_nflag_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      byte var3 = 1;
      if (objectData[19] == 2) {
         var2 = TRANS_ROT180;
         var3 = -1;
      }

      Image var10002 = this.m_imgObj[33];
      int var10004 = 88 + this.animeTimer % 3 * 8;
      int var10007 = rotNumTable[var2];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1] + 20 * var3;
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, var10004, 16, 8, var10007, var10008, var10009, 1 | 2);
      var10002 = this.m_imgObj[33];
      var10007 = rotNumTable[var2];
      var10008 = objectData[2] - mapView[0];
      var10009 = objectData[3] - mapView[1] + 8 * var3;
      var10010 = gg;
      var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 16, 16, var10007, var10008, var10009, 1 | 2);
      if (objectData[5] != 1 && objectData[5] != 2) {
         var10002 = this.m_imgObj[33];
         var10004 = 16 + this.animeTimer % 3 * 8;
         var10007 = rotNumTable[var2];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 4 * var3;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 16, 8, var10007, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[33];
         var10007 = rotNumTable[var2];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 4 * var3 + 4 * var3;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 40, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

      if (objectData[5] >= 2) {
         for(int var4 = 0; var4 <= 1; ++var4) {
            if (this.ele_nflag_ike_anime[objectData[5] - 2][var4] != 0) {
               var10002 = this.m_imgObj[33];
               var10007 = rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][0]];
               var10008 = objectData[2] - mapView[0] + 16 + var4 * 32;
               var10009 = objectData[3] - mapView[1] - 4 * var3;
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 72, 16, 16, var10007, var10008, var10009, 1 | 2);
               var10002 = this.m_imgObj[33];
               var10007 = rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][0]];
               var10008 = objectData[2] - mapView[0] + 32 + var4 * 32;
               var10009 = objectData[3] - mapView[1] - 4 * var3;
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 56, 16, 16, var10007, var10008, var10009, 1 | 2);
               var10002 = this.m_imgObj[33];
               var10007 = rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][1]];
               var10008 = objectData[2] - mapView[0] - 16 - var4 * 32;
               var10009 = objectData[3] - mapView[1] - 4 * var3;
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 72, 16, 16, var10007, var10008, var10009, 1 | 2);
               var10002 = this.m_imgObj[33];
               var10007 = rotNumTable[this.ele_nflag_ike_rotTable[objectData[5] - 2][1]];
               var10008 = objectData[2] - mapView[0] - 32 - var4 * 32;
               var10009 = objectData[3] - mapView[1] - 4 * var3;
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 56, 16, 16, var10007, var10008, var10009, 1 | 2);
            }
         }
      }

   }

   private void beltc_nflag_draw_ikeshita(int var1) {
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      int[] var10 = new int[this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2 + 1];
      var10[0] = 0;

      int var2;
      int var4;
      for(var2 = 0; var2 < this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var2) {
         for(var4 = 0; var4 < 2; ++var4) {
            this.beltc_nflag_ike_startPos[var4] = 0;
            this.beltc_nflag_ike_endPos[var4] = 0;
         }

         this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var2 * 2 + 0];
         this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var2 * 2 + 1];
         this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var2 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
         this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var2 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
         if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
            var6 += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
         } else {
            var6 += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
         }

         var10[var2 + 1] = var6;
      }

      int var11 = var6 / 69;
      boolean var12 = false;
      boolean var13 = false;
      boolean var14 = false;
      boolean var15 = false;

      for(var2 = 0; var2 < var11; ++var2) {
         int var18 = (var2 * 69 + this.cpuTimer) % var6;

         for(int var3 = 0; var3 < this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var3) {
            if (var18 < var10[var3 + 1]) {
               var13 = false;
               var14 = false;
               int var21 = var18 - var10[var3 + 1];

               for(var4 = 0; var4 < 2; ++var4) {
                  this.beltc_nflag_ike_startPos[var4] = 0;
                  this.beltc_nflag_ike_endPos[var4] = 0;
               }

               this.beltc_nflag_ike_startPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var3 * 2 + 0];
               this.beltc_nflag_ike_startPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][var3 * 2 + 1];
               this.beltc_nflag_ike_endPos[0] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var3 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
               this.beltc_nflag_ike_endPos[1] = this.beltc_nflag_ike_objectPos[objectData[4] - 128][(var3 + 1) % (this.beltc_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
               int var19;
               int var20;
               if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
                  var19 = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * var21 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
                  var20 = this.beltc_nflag_ike_endPos[1] + var21 * ((this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]));
               } else {
                  var19 = this.beltc_nflag_ike_endPos[0] + var21 * ((this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]));
                  var20 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * var21 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
               }

               Image var10002;
               int var10008;
               int var10009;
               Graphics var10010;
               Graphics var10011;
               if (var3 != 0 && var3 != 3) {
                  var8 = false;
                  int var16;
                  if (var3 == 1) {
                     var16 = Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][3] - var20);
                  } else {
                     var16 = Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][3] - this.beltc_nflag_ike_objectPos[objectData[4] - 128][5]) + Math.abs(this.beltc_nflag_ike_objectPos[objectData[4] - 128][2] - var19);
                  }

                  var16 = (var16 / 4 + 1) % 8;
                  byte var17;
                  switch(var16 / 2) {
                  case 1:
                     var17 = 5;
                     break;
                  case 2:
                     var17 = 3;
                     break;
                  case 3:
                     var17 = 6;
                     break;
                  default:
                     var17 = 0;
                  }

                  if (var16 % 2 == 0) {
                     var10002 = this.m_imgObj[BELTC];
                     var10008 = var19 - mapView[0];
                     var10009 = var20 - mapView[1];
                     var10010 = gg;
                     var10011 = gg;
                     this.drawRegion(gg, var10002, 0, 1, 32, 14, var17, var10008, var10009, 1 | 2);
                  } else {
                     var10002 = this.m_imgObj[BELTC];
                     var10008 = var19 - mapView[0];
                     var10009 = var20 - mapView[1];
                     var10010 = gg;
                     var10011 = gg;
                     this.drawRegion(gg, var10002, 0, 17, 32, 30, var17, var10008, var10009, 1 | 2);
                  }
                  break;
               }

               var10002 = this.m_imgObj[BELTC];
               var10008 = var19 - mapView[0];
               var10009 = var20 - mapView[1];
               var10010 = gg;
               var10011 = gg;
               this.drawRegion(gg, var10002, 0, 1, 32, 14, 0, var10008, var10009, 1 | 2);
               break;
            }
         }
      }

   }

   private void noko_nflag_draw_ikeshita(int var1) {
      if (objectData[10] != 0) {
         this.drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20);
         this.drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT90], objectData[2] - mapView[0], objectData[3] - mapView[1] - 32, 20);
         this.drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT270], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1], 20);
         this.drawRegion(gg, this.m_imgObj[35], 0, objectData[15] * 32, 32, 32, rotNumTable[TRANS_ROT180], objectData[2] - mapView[0], objectData[3] - mapView[1], 20);
         if (objectData[4] != 3) {
            this.drawRegion(gg, this.m_imgObj[35], 32, 0, 8, 64, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - 62, 20);
         }
      }

   }

   private void save_sflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[5] == 0) {
         var10002 = this.m_imgObj[36];
         var10007 = rotNumTable[TRANS_NONE + 4];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] - 32 - 4;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 16, 16, 16, var10007, var10008, var10009, 1 | 2);
      } else {
         Graphics var10001 = gg;
         var10002 = this.m_imgObj[36];
         var10007 = rotNumTable[TRANS_NONE + 4];
         var10008 = objectData[2] - mapView[0] - this.dSin(90 * objectData[5] / 4) * 8 / 100;
         var10009 = objectData[3] - mapView[1] - 24 + this.dCos(90 * objectData[5] / 4) * 8 / 100 - 4;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(var10001, var10002, 0, 0, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

      var10002 = this.m_imgObj[36];
      var10007 = rotNumTable[TRANS_NONE + 4];
      var10008 = objectData[2] - mapView[0];
      var10009 = objectData[3] - mapView[1] - 4;
      var10010 = gg;
      var10011 = gg;
      this.drawRegion(gg, var10002, 0, 32, 16, 48, var10007, var10008, var10009, 1 | 2);
   }

   private void kageb_nflag_draw_ikeshita(int var1) {
      byte var2 = 0;
      byte var3 = 64;
      if (objectData[4] == 1) {
         var2 = 1;
      } else if (objectData[4] == 2) {
         var2 = 2;
      } else if (objectData[4] == 16) {
         var2 = 3;
      } else if (objectData[4] == 17) {
         var2 = 4;
      } else if (objectData[4] == 18) {
         var2 = 5;
      }

      Image var10002 = this.m_imgObj[37];
      int var10003 = var2 * 16;
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, var10003, 0, 16, var3, 0, var10008, var10009, 1 | 2);
   }

   private void item_nflag_draw_ikeshita(int var1) {
      byte var2 = 32;
      byte var3 = 0;
      if (objectData[4] < 7) {
         if (this.item_nflag_ike_itemTable[objectData[4]] == 8) {
            var2 = 16;
            var3 = 8;
         }

         Image var10002;
         int var4;
         int var10007;
         int var10008;
         int var10009;
         Graphics var10010;
         Graphics var10011;
         if (objectData[5] != 0 && objectData[7] < 60) {
            if (objectData[5] >= 7) {
               return;
            }

            var4 = objectData[7];
            if (var4 >= 48) {
               var4 = 48;
            }

            var10002 = this.m_imgObj[42];
            int var10004 = this.item_nflag_ike_itemTable[objectData[5]] * 32 + 6;
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1] - var4;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 8, var10004, 16, 16, var10007, var10008, var10009, 1 | 2);
         }

         if (this.item_nflag_ike_itemTable[objectData[4]] != 8) {
            if (this.animeTimer % 2 == 0) {
               var4 = this.item_nflag_ike_itemTable[objectData[4]] * 32;
            } else {
               var4 = (5 + this.animeTimer % 6 / 2) * 32;
            }
         } else {
            var4 = this.item_nflag_ike_itemTable[objectData[4]] * 32;
         }

         var10002 = this.m_imgObj[42];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + var3;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, var4, 32, var2, var10007, var10008, var10009, 1 | 2);
      }
   }

   private void item_sflag_draw_ikeshita(int var1) {
      this.item_nflag_draw_ikeshita(var1);
   }

   private void gole_nflag_draw_ikeshita(int var1) {
      byte var2 = 0;
      if (objectData[10] / 3 > 22) {
         var2 = 48;
      }

      Image var10002;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[5] != 2) {
         var10002 = this.m_imgObj[44];
         int var10004 = var2 + objectData[10] / 3 % 4 * 48;
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + 10;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, var10004, 48, 48, 0, var10008, var10009, 1 | 2);
      } else {
         var10002 = this.m_imgObj[44];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + 10;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 192, 48, 48, 0, var10008, var10009, 1 | 2);
      }

      if (objectData[5] == 1) {
         this.drawRegion(gg, this.m_imgObj[0], 16, objectData[10] / 4 % 4 * 16, 16, 16, this.gole_nflag_ike_rotTable[objectData[10] / 2 % 4], objectData[2] - mapView[0] + this.gole_nflag_ike_kiraTableX[objectData[10] / 4 % 10] - 24 - 8, objectData[3] - mapView[1] + 10 + this.gole_nflag_ike_kiraTableY[objectData[10] / 4 % 10] - 24 - 8, 20);
      }

   }

   private void bten_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      byte var3;
      if (objectData[4] != 0 && objectData[4] != 3) {
         if (objectData[4] == 1) {
            var3 = 48;
         } else {
            var3 = 24;
         }
      } else {
         var3 = 0;
      }

      if (objectData[5] == 1) {
         Image var10002 = this.m_imgObj[45];
         int var10008 = objectData[2] - mapView[0];
         int var10009 = objectData[3] - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, var3, 32, 24, 0, var10008, var10009, 1 | 2);
      }

   }

   private void bten_sflag_draw_ikeshita(int var1) {
      this.bten_nflag_draw_ikeshita(var1);
   }

   private void bigring_nflag_draw_ikeshita(int var1) {
   }

   private void masin_nflag_draw_ikeshita(int var1) {
      if (objectData[4] == 1) {
         Image var10002;
         int var10007;
         int var10008;
         int var10009;
         Graphics var10010;
         Graphics var10011;
         if (objectData[5] < 2) {
            var10002 = this.m_imgObj[55];
            int var10003 = this.animeTimer % 2 * 24;
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, var10003, 96, 24, 16, var10007, var10008, var10009, 1 | 2);
            var10002 = this.m_imgObj[55];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[8] - mapView[0];
            var10009 = objectData[9] - mapView[1] + 37;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 0, 64, 64, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[55];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[8] - mapView[0];
            var10009 = objectData[9] - mapView[1] + 16 + 37;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 64, 64, 32, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   private void bobin_sflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[56];
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 32, 32, 32, var10007, var10008, var10009, 1 | 2);
   }

   private void jyama_nflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[58];
      int var10003 = objectData[2] - mapView[0];
      int var10004 = objectData[3] - mapView[1];
      Graphics var10005 = gg;
      Graphics var10006 = gg;
      this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
   }

   private void fetama_nflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[60];
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 48, 48, var10007, var10008, var10009, 1 | 2);
   }

   private static void vect(int var0, int var1, int var2) {
      if (var0 < 0) {
         var0 += 360;
      }

      if (360 >= var0) {
         var0 -= var0 / 360 * 360;
      }

      if (var0 <= 90) {
         objectData[var1] = sinData[90 - var0];
         objectData[var2] = sinData[var0];
      }

      if (var0 > 90 && var0 <= 180) {
         objectData[var1] = -sinData[90 - (180 - var0)];
         objectData[var2] = sinData[180 - var0];
      }

      if (var0 > 180 && var0 <= 270) {
         objectData[var1] = -sinData[90 - (var0 - 180)];
         objectData[var2] = -sinData[var0 - 180];
      }

      if (var0 > 270 && var0 < 360) {
         objectData[var1] = sinData[90 - (360 - var0)];
         objectData[var2] = -sinData[360 - var0];
      }

   }

   private void tekyu_nflag_draw_ikeshita(int var1) {
      boolean var2 = false;
      byte var4 = 0;
      byte var5 = 0;
      int var6 = this.animeTimer - objectData[5];
      if (objectData[4] != 213 && objectData[4] != 181 && objectData[4] != 197 && objectData[4] != 101 && objectData[4] != 69 && objectData[4] != 53) {
         if (objectData[4] != 212 && objectData[4] != 196 && objectData[4] != 84 && objectData[4] != 68 && objectData[4] != 52) {
            if (objectData[4] == 38) {
               var4 = 6;
            } else if (objectData[4] == 195) {
               var4 = 3;
            }
         } else {
            var4 = 4;
         }
      } else {
         var4 = 5;
      }

      if (objectData[4] != 181 && objectData[4] != 101) {
         if (objectData[4] != 196 && objectData[4] != 197 && objectData[4] != 84 && objectData[4] != 195) {
            if (objectData[4] != 213 && objectData[4] != 212 && objectData[4] != 69 && objectData[4] != 68) {
               if (objectData[4] != 52 && objectData[4] != 53) {
                  if (objectData[4] == 38) {
                     var5 = 4;
                  }
               } else {
                  var5 = 6;
               }
            } else {
               var5 = 8;
            }
         } else {
            var5 = 10;
         }
      } else {
         var5 = 12;
      }

      if (objectData[4] != 69 && objectData[4] != 84 && objectData[4] != 101 && objectData[4] != 38 && objectData[4] != 68 && objectData[4] != 52 && objectData[4] != 53) {
         vect((360 / var5 - var6 % (360 / var5)) * var5, 16, 17);
      } else {
         vect(360 - (360 / var5 - var6 % (360 / var5)) * var5 % 360, 16, 17);
      }

      if (objectData[4] == 84) {
         vect(var6 % (360 / var5) * var5, 16, 17);
      } else if (objectData[19] == 1) {
         vect(var6 % (360 / var5) * var5, 16, 17);
      }

      Image var10002;
      int var3;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (this.zoneNumber != 4) {
         for(var3 = 1; var3 < var4; ++var3) {
            var10002 = this.m_imgObj[60];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + var3 * objectData[16] * 16 / 10000;
            var10009 = objectData[3] - mapView[1] + var3 * objectData[17] * 16 / 10000;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 64, 16, 16, var10007, var10008, var10009, 1 | 2);
         }

         var10002 = this.m_imgObj[60];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 80, 16, 16, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[60];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + var3 * objectData[16] * 16 / 10000;
         var10009 = objectData[3] - mapView[1] + var3 * objectData[17] * 16 / 10000;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 16, 48, 32, 32, var10007, var10008, var10009, 1 | 2);
      } else {
         for(var3 = 1; var3 < var4; ++var3) {
            var10002 = this.m_imgObj[60];
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0] + var3 * objectData[16] * 16 / 10000;
            var10009 = objectData[3] - mapView[1] + var3 * objectData[17] * 16 / 10000;
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, 48, 16, 16, var10007, var10008, var10009, 1 | 2);
         }

         var10002 = this.m_imgObj[60];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1];
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 48, 16, 16, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[60];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] + var3 * objectData[16] * 16 / 10000;
         var10009 = objectData[3] - mapView[1] + var3 * objectData[17] * 16 / 10000;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 48, 16, 16, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void dai2_sflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10003;
      int var10004;
      Graphics var10005;
      Graphics var10006;
      if (objectData[4] >= 240) {
         for(int var2 = 0; var2 < 4; ++var2) {
            var10002 = this.m_imgObj[108];
            var10003 = objectData[2] - mapView[0] - 48 + var2 * 32;
            var10004 = objectData[3] - mapView[1];
            var10005 = gg;
            var10006 = gg;
            this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
         }
      } else if (objectData[4] >= 224) {
         var10002 = this.m_imgObj[107];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else if (objectData[4] <= 2) {
         this.drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 16, objectData[3] - mapView[1] - 16, 20);
      } else if (objectData[4] == 19) {
         this.drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 32, 20);
         this.drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 0, objectData[3] - mapView[1] - 32, 20);
         this.drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 32, objectData[3] - mapView[1] - 0, 20);
         this.drawImage(gg, this.m_imgObj[DAI2], objectData[2] - mapView[0] - 0, objectData[3] - mapView[1] - 0, 20);
      } else if (objectData[4] <= 91 && objectData[4] >= 88) {
         var10002 = this.m_imgObj[STEP];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else if (objectData[4] == 160) {
         var10002 = this.m_imgObj[DAI2];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1] - 16;
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
         var10002 = this.m_imgObj[DAI2];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1] + 16;
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else if (objectData[4] == 55 && (objectData[8] != 7992 || objectData[9] != 1353)) {
         var10002 = this.m_imgObj[107];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      }

   }

   private void ring_sflag_ring_m10_10_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_10_10_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_20_20_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_10_00_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_20_00_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_00_10_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void ring_sflag_ring_00_20_draw_ikeshita(int var1) {
      this.ring_sflag_ring_18_00_draw_ikeshita(var1);
   }

   private void elev_nflag_80_draw_ikeshita(int var1) {
      this.elev_nflag_draw_ikeshita(var1);
   }

   private void elev_nflag_draw_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = true;
      int var6 = this.animeTimer / 4 % 6;
      if (var6 > 3) {
         var6 = 6 - var6;
      }

      int var4;
      int var5;
      if (objectData[4] != 16) {
         for(var5 = 0; var5 < 5; ++var5) {
            for(var4 = 0; var4 < 3; ++var4) {
               this.drawRegion(gg, this.m_imgObj[73], 16, var4 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var5 % 2)], objectData[2] - mapView[0] - 40 + var5 * 16, objectData[3] - mapView[1] - 8 + var4 * 8, 20);
            }

            this.drawRegion(gg, this.m_imgObj[73], 16, var4 * 8 + var6 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var5 % 2)], objectData[2] - mapView[0] - 40 + var5 * 16, objectData[3] - mapView[1] - 8 + var4 * 8, 20);
         }
      } else {
         boolean var7 = false;

         for(int var8 = 0; var8 < 3; ++var8) {
            int var9 = objectData[3] - (objectData[5] + 1 + var8 * 128) % 384;

            for(var5 = 0; var5 < 5; ++var5) {
               for(var4 = 0; var4 < 3; ++var4) {
                  this.drawRegion(gg, this.m_imgObj[73], 16, var4 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var5 % 2)], objectData[2] - mapView[0] - 40 + var5 * 16, var9 - mapView[1] - 8 + var4 * 8, 20);
               }

               this.drawRegion(gg, this.m_imgObj[73], 16, var4 * 8 + var6 * 8, 16, 8, rotNumTable[TRANS_NONE + 4 * (var5 % 2)], objectData[2] - mapView[0] - 40 + var5 * 16, var9 - mapView[1] - 8 + var4 * 8, 20);
            }
         }
      }

   }

   private void mfire_nflag_draw_ikeshita(int var1) {
      int var2 = TRANS_NONE;
      byte var3 = -25;
      if (this.zoneNumber == 3) {
         if (objectData[4] == 54) {
            var2 = TRANS_MIRROR;
            var3 = 25;
         }

         Image var10002 = this.m_imgObj[75];
         int var10007 = rotNumTable[var2];
         int var10008 = objectData[2] - mapView[0] + var3;
         int var10009 = objectData[3] - mapView[1];
         Graphics var10010 = gg;
         Graphics var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 16, 32, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void yoganc_nflag_draw_ikeshita(int var1) {
      int var2 = objectData[5] / 4;
      if (objectData[4] == 16 && var2 > 0) {
         if (var2 < 6) {
            var2 %= 2;
         } else {
            var2 = var2 % 2 + 2;
         }

         int var3;
         for(var3 = 0; var3 < (objectData[9] - objectData[3]) / 32; ++var3) {
            this.drawRegion(gg, this.m_imgObj[14], 0, 32 * (var3 % 2), 64, 32, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 + 24 - 32, objectData[3] - mapView[1] - 48 + var3 * 32 + 16, 20);
         }

         int var4 = (objectData[9] - objectData[3]) % 32;
         if (var4 > 0) {
            this.drawRegion(gg, this.m_imgObj[14], 0, 32 * (var3 % 2), 64, var4, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 + 24 - 32, objectData[3] - mapView[1] - 48 + var3 * 32 + 16, 20);
         }

         this.drawRegion(gg, this.m_imgObj[77], 0, this.yoganc_nflag_ike_posY[var2], 112, this.yoganc_nflag_ike_posY[var2 + 1] - this.yoganc_nflag_ike_posY[var2], rotNumTable[TRANS_NONE], objectData[8] - mapView[0] - 20 - 32, objectData[9] - mapView[1] - 48, 20);
         this.drawRegion(gg, this.m_imgObj[77], 0, this.yoganc_nflag_ike_posY[var2 + 2], 112, this.yoganc_nflag_ike_posY[var2 + 1 + 2] - this.yoganc_nflag_ike_posY[var2 + 2], rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 20 - 32, objectData[3] - mapView[1] - 48, 20);
      }

   }

   private void ochi_nflag_draw_ikeshita(int var1) {
      boolean var2 = true;
      boolean var3 = true;
      boolean var4 = false;
      int var5 = 0;
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;
      byte var9;
      byte var10;
      if (objectData[4] == 1) {
         var9 = 16;
         var5 = this.dSin(objectData[5]) * var9 / 100 - var9;
         var10 = -9;
         var6 = this.dSin(objectData[5]) * var10 / 100 + var10;
         var5 *= 2;
         var6 *= 2;
         var5 += 2;
         var6 += 2;
      } else if (objectData[4] == 2) {
         var10 = -16;
         var5 = this.dSin(objectData[5]) * var10 / 100 + var10;
         var9 = 9;
         var6 = this.dSin(objectData[5]) * var9 / 100 - var9;
         var5 *= 2;
         var6 *= 2;
         var5 += 2;
         var6 += 2;
      }

      Image var10002;
      int var10007;
      int var10008;
      int var10009;
      Graphics var10010;
      Graphics var10011;
      if (objectData[4] != 20 && objectData[4] != 4) {
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] - 16;
         var10009 = objectData[3] - mapView[1] + var5 - 36;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 72, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_MIRROR];
         var10008 = objectData[2] - mapView[0] + 16;
         var10009 = objectData[3] - mapView[1] + var5 - 36;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 72, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_MIRROR_ROT180];
         var10008 = objectData[2] - mapView[0] - 16;
         var10009 = objectData[3] - mapView[1] + var5 + 36;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 72, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_ROT180];
         var10008 = objectData[2] - mapView[0] + 16;
         var10009 = objectData[3] - mapView[1] + var5 + 36;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 72, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_ROT180];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + var6 - 8 - 6;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 72, 32, 32, var10007, var10008, var10009, 1 | 2);
      } else {
         var9 = 28;
         var6 = this.dSin(this.cpuTimer) * var9 / 100 - 28;
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_NONE];
         var10008 = objectData[2] - mapView[0] - 16;
         var10009 = objectData[3] - mapView[1] - 28 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 56, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_MIRROR];
         var10008 = objectData[2] - mapView[0] + 16;
         var10009 = objectData[3] - mapView[1] - 28 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 56, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_MIRROR_ROT180];
         var10008 = objectData[2] - mapView[0] - 16;
         var10009 = objectData[3] - mapView[1] + 28 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 56, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_ROT180];
         var10008 = objectData[2] - mapView[0] + 16;
         var10009 = objectData[3] - mapView[1] + 28 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 0, 32, 56, var10007, var10008, var10009, 1 | 2);
         var10002 = this.m_imgObj[79];
         var10007 = rotNumTable[TRANS_ROT180];
         var10008 = objectData[2] - mapView[0];
         var10009 = objectData[3] - mapView[1] + var6 + 28 + 16;
         var10010 = gg;
         var10011 = gg;
         this.drawRegion(gg, var10002, 0, 72, 32, 32, var10007, var10008, var10009, 1 | 2);
      }

   }

   private void yari_sflag_draw_ikeshita(int var1) {
      if (objectData[4] == 2) {
         if (objectData[19] == 0) {
            this.drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8 + 8, 20);
         } else {
            this.drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 6], objectData[2] - mapView[0] - 4, objectData[3] - mapView[1] - 8, 20);
         }
      } else if (objectData[4] == 0) {
         if (objectData[19] == 0) {
            this.drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 1], objectData[2] - mapView[0] - 8, objectData[3] - mapView[1] - 4, 20);
         } else {
            this.drawRegion(gg, this.m_imgObj[80], 0, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 0] * 8, 8, this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, rotNumTable[TRANS_NONE + 3], objectData[2] - mapView[0] + 8 - this.yari_sflag_ike_drawPosTable[objectData[5] * 2 + 1] * 8, objectData[3] - mapView[1] - 4, 20);
         }
      }

   }

   private void kazari_sflag_draw_ikeshita(int var1) {
      Image var10002 = this.m_imgObj[82];
      int var10007 = rotNumTable[TRANS_NONE + 4 - objectData[19] * 4];
      int var10008 = objectData[2] - mapView[0];
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, 0, 32, 32, var10007, var10008, var10009, 1 | 2);
   }

   private void dai3_nflag_draw_ikeshita(int var1) {
      Image var10002;
      int var10003;
      int var10004;
      Graphics var10005;
      Graphics var10006;
      if (objectData[4] == 1) {
         var10002 = this.m_imgObj[108];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else if (objectData[4] == 39) {
         var10002 = this.m_imgObj[105];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else if (objectData[4] == 19) {
         var10002 = this.m_imgObj[106];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      } else {
         var10002 = this.m_imgObj[83];
         var10003 = objectData[2] - mapView[0];
         var10004 = objectData[3] - mapView[1];
         var10005 = gg;
         var10006 = gg;
         this.drawImage(gg, var10002, var10003, var10004, 1 | 2);
      }

   }

   private void kassya_nflag_draw_ikeshita(int var1) {
      if (objectData[4] != 127) {
         int var6 = 0;
         int[] var7 = new int[this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2 + 1];
         var7[0] = 0;

         int var2;
         int var4;
         for(var2 = 0; var2 < this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var2) {
            for(var4 = 0; var4 < 2; ++var4) {
               this.beltc_nflag_ike_startPos[var4] = 0;
               this.beltc_nflag_ike_endPos[var4] = 0;
            }

            this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var2 * 2 + 0];
            this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var2 * 2 + 1];
            this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var2 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
            this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var2 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
            if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
               var6 += Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1]);
            } else {
               var6 += Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]);
            }

            var7[var2 + 1] = var6;
         }

         int var8 = var6 / 132;
         boolean var9 = false;
         boolean var10 = false;
         boolean var11 = false;
         boolean var12 = false;

         for(var2 = 0; var2 < var8; ++var2) {
            int var13 = (var2 * 132 + this.cpuTimer) % var6;
            if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
               var13 = var6 - var13;
               if (var13 == var6) {
                  var13 = 0;
               }
            }

            for(int var3 = 0; var3 < this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2; ++var3) {
               if (var13 < var7[var3 + 1]) {
                  var10 = false;
                  var11 = false;
                  int var16 = var13 - var7[var3 + 1];

                  for(var4 = 0; var4 < 2; ++var4) {
                     this.beltc_nflag_ike_startPos[var4] = 0;
                     this.beltc_nflag_ike_endPos[var4] = 0;
                  }

                  this.beltc_nflag_ike_startPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var3 * 2 + 0];
                  this.beltc_nflag_ike_startPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][var3 * 2 + 1];
                  this.beltc_nflag_ike_endPos[0] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var3 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 0];
                  this.beltc_nflag_ike_endPos[1] = this.kassya_nflag_ike_objectPos[objectData[4] - 128][(var3 + 1) % (this.kassya_nflag_ike_objectPos[objectData[4] - 128].length / 2) * 2 + 1];
                  int var14;
                  int var15;
                  if (Math.abs(this.beltc_nflag_ike_startPos[0] - this.beltc_nflag_ike_endPos[0]) < Math.abs(this.beltc_nflag_ike_startPos[1] - this.beltc_nflag_ike_endPos[1])) {
                     var14 = this.beltc_nflag_ike_endPos[0] + (this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) * var16 / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]);
                     var15 = this.beltc_nflag_ike_endPos[1] + var16 * ((this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) / Math.abs(this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]));
                  } else {
                     var14 = this.beltc_nflag_ike_endPos[0] + var16 * ((this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]) / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]));
                     var15 = this.beltc_nflag_ike_endPos[1] + (this.beltc_nflag_ike_endPos[1] - this.beltc_nflag_ike_startPos[1]) * var16 / Math.abs(this.beltc_nflag_ike_endPos[0] - this.beltc_nflag_ike_startPos[0]);
                  }

                  this.drawRegion(gg, this.m_imgObj[88], 0, 128, 32, 16, rotNumTable[TRANS_NONE], var14 - 16 - mapView[0], var15 - 8 - mapView[1], 20);
                  break;
               }
            }
         }
      }

      if (objectData[4] == 127) {
         Image var10002;
         int var10004;
         int var10007;
         int var10008;
         int var10009;
         Graphics var10010;
         Graphics var10011;
         if (switchflag2[14] && this.zoneNumber == 1 && this.stageNumber == 2) {
            var10002 = this.m_imgObj[88];
            var10004 = 96 - 32 * (this.animeTimer % 4);
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 32, 32, var10007, var10008, var10009, 1 | 2);
         } else {
            var10002 = this.m_imgObj[88];
            var10004 = 32 * (this.animeTimer % 4);
            var10007 = rotNumTable[TRANS_NONE];
            var10008 = objectData[2] - mapView[0];
            var10009 = objectData[3] - mapView[1];
            var10010 = gg;
            var10011 = gg;
            this.drawRegion(gg, var10002, 0, var10004, 32, 32, var10007, var10008, var10009, 1 | 2);
         }
      }

   }

   private void shima2_nflag_draw_ikeshita(int var1) {
      this.shima_nflag_draw_ikeshita(var1);
   }

   private void bou_nflag_draw_ikeshita(int var1) {
      byte var2 = 0;
      if (objectData[10] >= 300) {
         var2 = 1;
      }

      Image var10002 = this.m_imgObj[91];
      int var10004 = var2 * 64;
      int var10005 = 8 + var2 * 8;
      int var10007 = rotNumTable[TRANS_NONE];
      int var10008 = objectData[2] - mapView[0] - var2 * 4;
      int var10009 = objectData[3] - mapView[1];
      Graphics var10010 = gg;
      Graphics var10011 = gg;
      this.drawRegion(gg, var10002, 0, var10004, var10005, 64, var10007, var10008, var10009, 1 | 2);
   }

   static {
      objAwaData = new int[OBJA_MAX][20];
      objData = new int[OBJA_MAX][10];
      initDisplay = false;
      readStageObjectFlag = false;
      switchflag = new boolean[256];
      PlayerH = 32;
      SONIC_N = 1;
      SONIC_S = 2;
      LOGO = 3;
      LOGOLINE = 4;
      SYSTXT = 5;
      WINDOW_RING = 6;
      WINDOW_TIME = 7;
      WINDOW_ZANKI = 8;
      WINDOU_SUUJI = 9;
      SYSSCORE = 10;
      SYSTXT2 = 11;
      T_CUR1 = 12;
      T_CUR2 = 13;
      GAMEOVER = 14;
      TIMEOVER = 15;
      RING = 0;
      RING1 = 1;
      SJUMP = 2;
      BURANKO = 3;
      HASHI = 4;
      TOGE_HASHI = 5;
      BREAK = 6;
      YUKA = 7;
      TURI = 8;
      TOGE = 9;
      BOX = 10;
      FBLOCK = 11;
      DAI = 12;
      YOGAN = 14;
      SWITCH2 = 15;
      SHIMA = 16;
      DAI2 = 17;
      BRKABE = 18;
      PEDAL = 19;
      BREAK2 = 20;
      STEP = 21;
      FUN = 22;
      SISOO = 23;
      BELT = 24;
      PATA = 25;
      FIRE6 = 26;
      SWITCH2_ = 27;
      MAWARU = 28;
      YUKAI = 29;
      DOOR = 30;
      YUKAE = 31;
      DAI4 = 32;
      ELE = 33;
      BELTC = 34;
      NOKO = 35;
      objectDrawList = new int[200];
      objectDrawCount = 0;
      BossFirst = -1;
      Window = 1;
      mapOxy = new int[2];
      oldMapOxy = new int[2];
      mapView = new int[2];
      mapViewTarget = new int[2];
      mapOfs = new int[2];
      mapOfsTarget = new int[2];
      mapData = new byte[42496];
      mapFrontData = new byte[600];
      blockLinkTable = new byte[600];
      blockColTable = new byte[8192];
      zoneActTable = new byte[4][];
      worldMapData = new byte[][][][]{{{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 56, 1, 1, 1, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 56, 36, 0, 0, 33, 38, 17, 17, 31, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 45, 49, 36, 20, 56, 4, 35, 37, 45, 53, 38, 17, 31, 30, 32, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {45, 45, 3, 49, 36, 16, 2, 7, 4, 5, 43, 14, 30, 17, 37, 26, 38, 17, 8, 9, 10, 23, 30, 30, 32, 17, 31, 15, 0, 16, 5, 43, 22, 2, 3, 55, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 30, 32, 37, 7, 34, 12, 13, 21, 25, 17, 37, 45, 45, 45}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {14, 43, 22, 28, 5, 43, 22, 2, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 3, 0, 0, 0, 0, 33, 49, 36, 0, 0, 0}, {12, 13, 6, 12, 30, 13, 21, 17, 37, 50, 43, 11, 45, 53, 45, 7, 36, 18, 56, 36, 45, 38, 8, 5, 43, 11, 33, 38, 31, 15, 0, 0, 0}, {30, 30, 30, 30, 10, 23, 30, 30, 12, 12, 13, 25, 17, 8, 23, 30, 30, 30, 30, 30, 30, 30, 30, 24, 13, 8, 35, 17, 32, 37, 45, 45, 45}, {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 30, 30, 10, 23, 30, 17, 8, 29, 9, 30, 30, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 45, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 56, 24, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 19, 56, 36, 20, 0, 19, 56, 39, 2, 26, 38, 31, 15, 0, 0, 20, 33, 45, 0, 0, 0, 19, 51, 51, 16, 36, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {45, 55, 22, 27, 35, 37, 26, 3, 6, 38, 31, 30, 30, 30, 32, 37, 53, 49, 27, 35, 17, 51, 51, 2, 26, 52, 52, 25, 37, 6, 49, 39, 0, 22, 2, 7, 2, 55, 45, 45, 45, 60, 60, 45, 45}, {30, 15, 21, 30, 30, 30, 30, 30, 30, 30, 32, 31, 30, 30, 30, 30, 30, 30, 12, 17, 9, 52, 52, 17, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 32, 17, 8, 9, 10, 29, 9, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{40, 41, 42, 45, 49, 39, 2, 49, 34, 37, 3, 49, 2, 49, 39}}}, {{{25, 42, 39, 41, 26, 23, 14, 33, 23, 0, 0, 0, 39, 29, 36, 13, 14, 14, 14, 14, 14, 14, 33, 23, 23, 23, 23, 0, 0, 0, 0, 0}, {14, 14, 14, 14, 14, 37, 12, 34, 14, 20, 0, 0, 14, 14, 14, 2, 10, 9, 10, 9, 21, 12, 34, 31, 1, 14, 14, 14, 0, 0, 0, 0}, {0, 0, 0, 0, 14, 14, 14, 14, 14, 12, 45, 14, 14, 14, 14, 11, 58, 14, 60, 25, 29, 29, 29, 30, 14, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 14, 14, 24, 9, 45, 14, 17, 15, 59, 14, 61, 17, 27, 14, 14, 14, 14, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 14, 14, 46, 12, 15, 17, 15, 14, 62, 15, 16, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 14, 14, 14, 25, 26, 12, 11, 11, 15, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}}, {{23, 11, 23, 13, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 2, 3, 11, 14, 17, 0, 11, 23, 14, 0, 0, 0, 0, 0, 0, 0}, {14, 14, 14, 11, 76, 5, 20, 46, 45, 14, 19, 14, 14, 0, 0, 0, 0, 0, 0}, {33, 23, 12, 46, 14, 14, 14, 45, 46, 14, 15, 14, 14, 63, 28, 0, 0, 0, 0}, {47, 21, 12, 23, 34, 9, 10, 46, 15, 14, 19, 14, 14, 64, 14, 1, 14, 14, 14}, {14, 14, 14, 14, 14, 14, 14, 14, 17, 11, 15, 11, 12, 65, 14, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 14, 21, 12, 11, 15, 14, 14, 14, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 14, 14, 14, 14, 14, 14, 0, 0, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 14, 14, 33, 77, 14, 14, 14, 14, 14, 0, 0, 0, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 16, 14, 14, 0, 0, 0, 0, 0}, {14, 14, 14, 14, 14, 14, 32, 75, 14, 17, 11, 11, 11, 14, 14, 14, 0, 14, 17, 33, 0, 13, 14, 14, 14, 14, 23, 15, 14, 14, 49, 1, 14, 14, 14}, {11, 11, 13, 14, 12, 17, 75, 14, 17, 0, 46, 39, 66, 68, 39, 14, 0, 14, 70, 71, 14, 2, 3, 18, 14, 14, 16, 14, 14, 14, 49, 14, 0, 0, 0}, {14, 14, 2, 3, 18, 8, 20, 35, 36, 11, 23, 40, 67, 69, 34, 14, 14, 14, 20, 45, 23, 12, 23, 4, 18, 33, 15, 14, 0, 14, 49, 14, 0, 0, 0}, {0, 14, 14, 14, 4, 74, 12, 37, 14, 17, 22, 14, 36, 12, 34, 9, 10, 36, 12, 45, 45, 11, 22, 14, 72, 0, 14, 14, 14, 14, 49, 14, 0, 0, 0}, {0, 0, 0, 14, 14, 4, 18, 36, 23, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 43, 43, 44, 14, 14, 16, 14, 14, 14, 23, 23, 15, 14, 0, 0, 0}, {0, 0, 0, 0, 14, 14, 4, 18, 43, 34, 38, 10, 9, 10, 9, 41, 29, 41, 42, 29, 41, 42, 0, 11, 37, 14, 35, 14, 16, 14, 14, 14, 0, 0, 0}, {0, 0, 0, 0, 0, 14, 14, 4, 18, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 46, 15, 14, 14, 15, 33, 15, 14, 14, 14, 0, 0, 0}}, {{14, 14, 14, 14, 14, 17, 39, 41, 26, 17, 6, 73, 14, 14, 48, 14, 14, 14, 14, 14, 14, 14, 14}, {14, 33, 11, 18, 14, 22, 14, 11, 6, 5, 11, 17, 23, 18, 16, 14, 14, 14, 14, 14, 14, 14, 14}, {14, 31, 14, 47, 27, 35, 14, 20, 11, 12, 15, 1, 35, 43, 15, 14, 14, 14, 14, 14, 14, 14, 14}, {14, 43, 11, 14, 46, 15, 14, 14, 1, 43, 18, 14, 37, 14, 19, 14, 11, 11, 14, 14, 14, 14, 14}, {14, 45, 0, 17, 44, 19, 14, 12, 35, 17, 43, 14, 43, 34, 25, 18, 37, 24, 38, 41, 42, 23, 14}, {33, 45, 0, 46, 12, 46, 14, 12, 38, 31, 23, 12, 11, 14, 14, 47, 30, 14, 14, 14, 14, 16, 14}, {14, 45, 12, 34, 14, 43, 11, 11, 11, 23, 44, 14, 47, 10, 9, 10, 9, 10, 9, 27, 12, 15, 14}, {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}}}, {{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 6, 11, 8, 9, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 52, 2, 2, 3, 5, 18, 18, 10, 8, 11, 7, 7, 7, 9, 12, 32, 12, 11, 8, 3, 8, 11, 16, 16, 16}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 15, 15, 14, 14, 73, 13, 24, 22, 32, 32, 18, 18, 18, 18, 18, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 39, 40, 32, 32, 31, 49, 25, 32, 18, 18, 18, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 17, 33, 20, 20, 20, 69, 22, 32, 0, 0, 0, 0, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 32, 32, 0, 0, 0, 0, 6, 8, 53, 16, 9, 0, 0, 32, 32, 32, 32, 32, 32, 0, 0, 0}, {52, 8, 53, 7, 9, 32, 32, 16, 9, 11, 2, 5, 18, 18, 32, 10, 8, 9, 32, 32, 32, 32, 32, 12, 53, 53, 53}, {18, 51, 32, 43, 73, 17, 21, 18, 46, 30, 29, 42, 42, 42, 42, 32, 18, 46, 40, 32, 32, 42, 25, 22, 32, 32, 0}, {0, 18, 26, 44, 20, 23, 22, 42, 42, 31, 49, 48, 47, 47, 45, 32, 68, 30, 22, 32, 25, 22, 32, 32, 32, 0, 0}, {0, 18, 41, 27, 27, 27, 30, 17, 38, 36, 36, 36, 23, 17, 22, 32, 44, 20, 20, 23, 22, 32, 32, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 8, 53, 16, 53, 9, 0, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {32, 32, 32, 32, 70, 73, 8, 12, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 12, 11, 3, 71, 11, 72, 74, 53, 53}, {26, 33, 25, 17, 22, 32, 26, 22, 32, 32, 32, 32, 32, 32, 26, 69, 25, 25, 0, 22, 32, 32, 18, 32, 0, 0, 0, 0}, {21, 14, 15, 30, 36, 36, 22, 18, 32, 32, 32, 32, 32, 32, 39, 42, 0, 0, 0, 0, 42, 32, 32, 32, 0, 0, 0, 0}, {28, 34, 69, 22, 32, 37, 29, 14, 30, 37, 49, 31, 49, 49, 49, 22, 47, 47, 47, 22, 39, 42, 32, 0, 0, 0, 0, 0}, {32, 35, 30, 20, 20, 36, 13, 32, 26, 22, 32, 32, 32, 42, 42, 14, 14, 40, 43, 40, 29, 25, 32, 0, 0, 0, 0, 0}, {32, 32, 32, 32, 32, 32, 32, 32, 39, 30, 38, 20, 20, 23, 22, 32, 32, 33, 44, 44, 17, 22, 32, 0, 0, 0, 0, 0}}}, {{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 8, 10, 0, 0, 0, 0, 1, 24, 5, 1, 8, 0, 0, 0, 0, 51, 0, 0, 0, 0}, {0, 0, 0, 1, 10, 51, 51, 51, 51, 0, 0, 0, 15, 51, 6, 7, 24, 0, 0, 0, 0, 0, 0, 0, 6, 7, 42, 15, 24, 51, 0, 0, 0, 0}, {1, 1, 38, 51, 12, 5, 8, 63, 47, 5, 24, 3, 12, 4, 1, 51, 51, 1, 1, 14, 24, 4, 18, 5, 8, 51, 47, 3, 12, 18, 4, 18, 18, 18}, {51, 51, 29, 51, 0, 0, 6, 7, 46, 51, 51, 4, 3, 1, 1, 51, 51, 51, 35, 20, 51, 35, 20, 32, 6, 7, 46, 51, 32, 24, 0, 0, 0, 0}, {0, 51, 51, 51, 0, 0, 51, 51, 11, 51, 51, 0, 51, 51, 0, 1, 40, 3, 12, 19, 1, 24, 19, 31, 3, 12, 18, 24, 31, 18, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 51, 41, 8, 51, 0, 3, 12, 3, 12, 5, 40, 5, 0, 51, 0, 4, 0, 0, 0, 51, 51, 0, 4, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 51, 51, 6, 7, 1, 42, 40, 0, 3, 0, 12, 5, 24, 3, 0, 0, 0, 4, 3, 4, 5, 24, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{0, 0, 20, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 40, 5, 40, 5, 40, 5, 40, 4, 51, 51, 0, 51, 0, 1, 51, 0, 0, 0, 0}, {21, 21, 19, 51, 6, 7, 52, 0, 0, 0, 20, 1, 38, 3, 51, 20, 21, 5, 42, 42, 1, 24, 5, 15, 51, 0, 3, 0, 51, 51, 0, 0, 0, 0}, {0, 0, 0, 51, 51, 51, 41, 42, 40, 36, 37, 8, 21, 3, 12, 19, 0, 1, 40, 3, 0, 1, 4, 3, 12, 0, 51, 0, 5, 32, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 51, 51, 51, 0, 0, 0, 6, 7, 52, 51, 0, 0, 51, 0, 51, 0, 51, 0, 51, 0, 0, 15, 0, 15, 39, 24, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 51, 11, 0, 18, 40, 21, 5, 52, 51, 51, 20, 51, 0, 4, 29, 15, 29, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 51, 41, 24, 51, 51, 51, 51, 41, 8, 24, 19, 0, 4, 0, 15, 29, 29, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 51, 51, 51, 0, 0, 0, 51, 6, 7, 42, 24, 21, 0, 29, 29, 29, 0, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 1, 24, 5, 9, 5, 1, 24, 5, 20, 52, 51, 51, 0, 0, 14, 8, 0, 51, 29, 14, 24, 5, 8, 0, 51, 0, 0, 0, 0, 0}, {21, 21, 25, 23, 0, 18, 0, 0, 6, 7, 52, 51, 47, 19, 41, 52, 51, 35, 18, 10, 6, 7, 52, 29, 14, 10, 51, 6, 7, 52, 0, 0, 0, 0, 0}, {0, 0, 51, 26, 22, 0, 4, 14, 32, 51, 41, 1, 46, 51, 51, 41, 42, 40, 21, 10, 51, 51, 41, 24, 10, 4, 5, 10, 51, 41, 20, 1, 1, 1, 1}, {0, 0, 51, 2, 27, 0, 0, 0, 31, 5, 20, 51, 41, 40, 13, 22, 0, 0, 3, 12, 5, 14, 24, 5, 20, 38, 3, 12, 35, 18, 19, 51, 0, 0, 0}, {0, 0, 0, 51, 28, 0, 20, 14, 24, 18, 19, 0, 0, 0, 2, 27, 0, 0, 51, 0, 14, 0, 3, 12, 19, 3, 12, 18, 24, 3, 12, 51, 0, 0, 0}, {0, 0, 0, 51, 30, 21, 19, 10, 51, 51, 14, 0, 0, 15, 63, 28, 20, 33, 51, 1, 10, 21, 51, 1, 0, 0, 0, 1, 20, 51, 0, 0, 0, 0, 0}, {0, 0, 0, 51, 0, 0, 4, 3, 12, 3, 10, 0, 0, 29, 10, 30, 19, 10, 0, 20, 14, 0, 5, 24, 3, 12, 4, 4, 19, 51, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 10, 0, 0, 29, 21, 4, 3, 12, 4, 19, 10, 0, 0, 51, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}}, {{{36, 47, 36, 66, 12, 36, 56, 71, 1, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 2, 36, 2, 71, 1, 36, 0}, {36, 36, 36, 66, 10, 73, 1, 71, 1, 1, 43, 36, 36, 4, 14, 19, 30, 55, 25, 28, 6, 36, 2, 4, 36, 36, 28, 73, 36, 36, 1, 36, 1, 71, 1, 36, 0}, {36, 36, 36, 8, 9, 45, 1, 14, 15, 17, 1, 36, 36, 1, 1, 20, 22, 23, 23, 29, 1, 36, 1, 1, 71, 74, 29, 43, 43, 2, 1, 2, 11, 2, 2, 2, 2}, {6, 2, 7, 1, 1, 1, 1, 1, 16, 18, 5, 33, 2, 15, 17, 1, 1, 1, 1, 1, 1, 2, 34, 37, 70, 57, 67, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 16, 18, 21, 27, 21, 24, 21, 27, 21, 35, 38, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}}, {{36, 36, 38, 36, 36, 40, 47, 47, 36, 36, 56, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {2, 2, 44, 36, 36, 36, 36, 36, 36, 36, 1, 71, 1, 36, 49, 3, 50, 48, 36, 36, 36, 36, 36, 2, 6, 36, 36, 1, 36, 36, 36, 36, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 36, 36, 36, 28, 73, 36, 56, 1, 71, 1, 56, 40, 1, 52, 51, 50, 48, 36, 36, 28, 15, 17, 4, 13, 4, 36, 36, 25, 36, 36, 36, 36, 38, 36, 36, 1, 36, 0, 0, 0}, {1, 1, 1, 36, 2, 26, 29, 43, 43, 1, 1, 71, 1, 1, 1, 1, 1, 1, 52, 31, 36, 4, 29, 16, 18, 21, 41, 5, 33, 26, 23, 14, 6, 73, 32, 44, 36, 36, 1, 36, 36, 36, 0}, {1, 1, 1, 36, 46, 17, 1, 1, 1, 1, 1, 71, 1, 1, 1, 1, 1, 1, 1, 1, 56, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 40, 45, 1, 1, 36, 36, 58, 32, 33, 2, 2}, {1, 1, 1, 45, 16, 18, 21, 27, 21, 24, 5, 70, 57, 67, 57, 68, 68, 68, 68, 68, 69, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 53, 2, 54, 32, 33, 2, 2}}, {{56, 36, 36, 36, 36, 36, 36, 0, 0, 0, 0, 1, 36, 36, 25, 36, 2, 36, 36, 36, 36, 6, 6, 44, 36, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 49, 3, 50, 48, 36, 36, 36, 36, 36, 36, 1, 36, 36, 23, 4, 1, 36, 36, 36, 4, 71, 68, 1, 36, 1, 1, 1, 1, 36, 36, 36, 36, 36, 36, 71, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 52, 51, 50, 48, 36, 36, 2, 6, 1, 36, 6, 42, 21, 5, 33, 73, 36, 54, 71, 1, 1, 36, 1, 1, 1, 1, 36, 36, 56, 36, 36, 36, 71, 1, 1, 36, 36, 47, 47, 40, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 52, 31, 36, 2, 15, 17, 1, 4, 71, 1, 1, 1, 1, 43, 43, 1, 70, 40, 1, 36, 40, 1, 1, 1, 36, 36, 1, 43, 43, 43, 71, 1, 12, 36, 36, 36, 36, 11, 36, 36, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 36, 1, 16, 18, 27, 5, 71, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 36, 46, 17, 1, 1, 4, 13, 1, 1, 1, 1, 71, 1, 10, 36, 56, 36, 36, 36, 36, 36, 36, 36, 36, 0}, {0, 0, 0, 0, 0, 0, 1, 4, 1, 1, 1, 1, 1, 4, 57, 67, 11, 1, 1, 1, 1, 1, 1, 1, 36, 16, 18, 24, 39, 39, 41, 24, 21, 27, 5, 72, 8, 9, 36, 1, 36, 36, 36, 4, 36, 64, 4, 4, 4}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 45, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 36, 36, 36, 1, 36, 66, 1, 1, 0}}}, {{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 22, 1, 14, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 34, 2, 34, 0, 17, 16, 12, 3, 16, 16, 16, 0, 0, 16, 16, 16, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 34, 0, 0, 0, 0, 46, 45, 16, 0, 16, 44, 16, 13, 3, 16, 16, 5, 16, 16, 16, 30, 15, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 6, 0, 18, 19, 17, 16, 0, 0, 9, 45, 29, 0, 15, 0, 16, 12, 7, 8, 5, 16, 16, 4, 75, 47, 26, 42, 43, 24, 20, 18, 18}, {1, 9, 22, 22, 34, 2, 1, 7, 3, 16, 16, 0, 0, 15, 0, 0, 0, 22, 22, 1, 14, 16, 16, 4, 75, 31, 47, 33, 33, 26, 42, 43, 16, 16, 16, 0}, {16, 16, 17, 17, 16, 12, 3, 16, 5, 16, 16, 27, 24, 20, 18, 45, 0, 17, 17, 16, 12, 7, 9, 33, 33, 28, 16, 0, 46, 0, 32, 43, 16, 16, 16, 0}, {16, 16, 17, 17, 16, 16, 5, 16, 4, 75, 47, 28, 16, 16, 16, 17, 16, 16, 30, 33, 1, 21, 21, 9, 34, 18, 18, 45, 29, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 16, 4, 75, 7, 21, 21, 21, 9, 75, 47, 75, 47, 34, 25, 17, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{16, 16, 16, 16, 30, 30, 30, 44, 11, 1, 47, 33, 33, 33, 33, 33, 75, 50, 36, 50, 9, 10, 16, 30, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {11, 1, 36, 47, 34, 0, 0, 10, 11, 75, 31, 47, 34, 49, 48, 48, 15, 30, 16, 0, 33, 26, 42, 43, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {44, 16, 16, 16, 16, 16, 16, 13, 8, 16, 28, 30, 16, 9, 1, 23, 24, 20, 18, 19, 17, 26, 42, 43, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {11, 1, 36, 36, 47, 33, 33, 10, 16, 30, 15, 41, 75, 36, 50, 36, 47, 10, 16, 16, 16, 26, 42, 43, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0}, {16, 16, 16, 15, 15, 33, 33, 34, 18, 19, 40, 40, 16, 16, 16, 13, 7, 8, 11, 1, 47, 26, 42, 43, 15, 15, 15, 15, 30, 30, 30, 16, 16, 0, 0, 0, 0, 0, 0, 0}, {16, 15, 0, 41, 41, 17, 17, 16, 11, 75, 47, 75, 36, 36, 9, 10, 16, 16, 44, 16, 11, 34, 32, 43, 24, 20, 37, 38, 1, 9, 1, 68, 69, 70, 68, 68, 71, 72, 73, 74}, {16, 0, 0, 40, 22, 1, 9, 10, 11, 1, 9, 0, 0, 1, 36, 50, 36, 47, 10, 16, 44, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0}, {18, 19, 39, 40, 17, 16, 16, 44, 16, 16, 16, 0, 0, 16, 16, 15, 15, 15, 16, 16, 12, 14, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0}}, {new byte[0]}, {{69, 70, 68, 68, 71, 72, 73, 74}}}};
      scddirtbl = new byte[512];
      pauseGame = false;
      PlayerParam = new int[26];
      PlayerSJump = false;
      PlayerDamage = false;
      PlayerWater = false;
      PlayerSWater = false;
      PlayerBou = false;
      PlayerJump = false;
      PlayerAir = false;
      PlayerBall = false;
      PlayerDie = false;
      PlayerCrouch = false;
      PlayerLookUp = false;
      PlayerNoCol = false;
      PlayerNoCtrl = false;
      TimerClear = false;
      TimerStop = false;
      sinData = new int[]{0, 175, 349, 523, 698, 872, 1045, 1219, 1392, 1564, 1736, 1908, 2079, 2249, 2419, 2588, 2756, 2924, 3090, 3256, 3420, 3584, 3746, 3907, 4067, 4226, 4384, 4540, 4695, 4848, 5000, 5150, 5299, 5446, 5592, 5736, 5878, 6018, 6156, 6293, 6428, 6560, 6691, 6820, 6946, 7071, 7193, 7313, 7431, 7547, 7660, 7771, 7880, 7986, 8090, 8191, 8290, 8387, 8480, 8572, 8660, 8746, 8829, 8910, 8988, 9063, 9135, 9205, 9272, 9336, 9397, 9455, 9510, 9563, 9613, 9659, 9703, 9744, 9781, 9816, 9848, 9877, 9903, 9925, 9945, 9962, 9976, 9986, 9994, 9998, 10000};
      scdtblwk = new byte[8192];
      MapEndCounter = 0;
      bossModeOn = false;
      bossBreakOn = false;
      objectSizeTbl = new short[][]{{48, 48}, {48, 48}, {48, 48}, {160, 160}, {192, 48}, {192, 48}, {480, 480}, {128, 96}, {48, 48}, {48, 48}, {32, 32}, {32, 32}, {240, 24}, {240, 48}, {64, 240}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {240, 48}, {64, 24}, {48, 48}, {48, 48}, {94, 94}, {192, 48}, {16, 32}, {48, 48}, {480, 480}, {48, 48}, {480, 480}, {240, 48}, {48, 48}, {48, 48}, {48, 48}, {40, 32}, {32, 32}, {32, 24}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {64, 64}, {48, 48}, {16, 16}, {24, 24}, {24, 40}, {48, 48}, {-1, -1}, {48, 48}, {48, 48}, {48, 48}, {40, 24}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {-1, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {32, 32}, {28, 20}, {48, 48}, {48, 48}, {16, 16}, {48, 48}, {48, 48}, {240, 240}, {32, 24}, {48, 48}, {48, 48}, {24, 40}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {24, 32}, {40, 32}, {48, 480}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {48, 48}, {128, 128}, {128, 128}, {32, 32}, {32, 32}, {128, 128}, {128, 128}, {40, 40}, {32, 40}};
      TRANS_NONE = 0;
      TRANS_ROT90 = 1;
      TRANS_ROT180 = 2;
      TRANS_ROT270 = 3;
      TRANS_MIRROR = 4;
      TRANS_MIRROR_ROT90 = 5;
      TRANS_MIRROR_ROT180 = 6;
      TRANS_MIRROR_ROT270 = 7;
      rotNumTable = new int[]{0, 5, 3, 6, 2, 7, 1, 4};
      encZoneNumber = new int[][]{{0, 0, 0}, {2, 2, 2}, {4, 4, 4}, {1, 1, 1}, {3, 3, 3}, {5, 5, 1}, {5, 0}};
      encStageNumber = new int[][]{{0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 3}, {3, 3}};
      cutDrawVLine = 0;
      softKeys = new String[33];
      raidObjectW = 0;
      raidObjectX = 0;
      m_aAddObjectData = new int[25];
      LANGUAGE_MAX = 2;
      TITLE_MODE_LICENSE_SEGA = 0;
      TITLE_MODE_LICENSE_SONICTEAM = 1;
      TITLE_MODE_FIRST_SETUP = 2;
      TITLE_MODE_TITLE = 3;
      TITLE_MODE_TITLE_MENU = 4;
      TITLE_MODE_TITLE_RANCKING = 5;
      TITLE_MODE_TITLE_RANCKING_MENU = 6;
      TITLE_MODE_TITLE_RANCKING_DEL = 7;
      TITLE_MODE_TITLE_CONFIG_MENU = 8;
      TITLE_MODE_TITLE_CONTINUE_MENU = 9;
      TITLE_MODE_TITLE_HOWTO = 10;
      m_bFirstSetUp = 0;
      m_nConfigValue = new byte[4];
      m_HowToPicIndexTbl = new byte[]{-1, 0, -1, -1, -1, -1, -1, 1, 1, 2, 3, 4, 5, 6, 7, -1, 8, 9, -1, -1, -1, 10, 11, 12, -1, -1};
      m_aConfigTextOffset = new byte[][]{{25, 26, 27}, {29, 30, 31, 32}, {29, 28}, {33, 34, 35, 36, 37}};
      cmd = new Command[2];
      m_nHiScore = new int[]{10000, 8000, 6000, 4000, 2000};
      m_nDifficulty = new int[]{0, 1, 2, 1, 0};
      m_OnKeyFlag = new boolean[10];
      m_imgImage = new Image[10];
      m_Font = Font.getFont(0, 0, 8);
      m_HowToPicTbl = new short[][]{{0, 0, 32, 32}, {32, 0, 32, 40}, {64, 0, 32, 32}, {96, 0, 32, 32}, {128, 0, 32, 32}, {160, 0, 32, 32}, {192, 0, 32, 32}, {224, 0, 32, 32}, {0, 40, 40, 32}, {40, 40, 40, 48}, {80, 40, 40, 48}, {120, 32, 32, 48}, {160, 32, 16, 64}, {176, 32, 40, 20}, {176, 52, 20, 20}, {196, 52, 20, 20}};
      m_strText = new String[51];
      m_strHowToText = new String[182];
      m_strMusicComposed = new String[]{"MUSIC COMPOSED", "BY MASATO", "NAKAMURA"};
      comboScore = 0;
      bPauseMusic = false;
      bGoalMusic = false;
      musicCount = 0;
      musicRetry = 0;
      musicRequest = -1;
      musicNum = -1;
      friendTbl = new byte[][]{{OBJ2_FRIC, OBJ2_USAGI}, {OBJ2_PENGUIN, OBJ2_AZARASI}, {OBJ2_RISU, OBJ2_AZARASI}, {OBJ2_FRIC, OBJ2_BUTA}, {OBJ2_BUTA, OBJ2_NIWATORI}, {OBJ2_USAGI, OBJ2_NIWATORI}, {OBJ2_RISU, OBJ2_RISU}, {OBJ2_USAGI, OBJ2_USAGI}};
      sisootbl = new byte[]{36, 36, 38, 40, 42, 44, 42, 40, 38, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 2, 2, 2, 2};
      batAnimTbl = new byte[]{1, 2, 3, 2};
      RectTblKamere = new short[][]{{0, 0, 56, 40, 0}, {0, 40, 56, 40, 0}, {0, 80, 56, 40, 0}, {0, 120, 56, 24, 0}, {0, 144, 56, 16, 0}, {0, 160, 56, 16, 0}};
      RectTblHachi = new short[][]{{0, 0, 48, 32, 0}, {0, 32, 48, 24, 3}, {0, 56, 48, 32, 0}, {0, 88, 48, 24, 3}, {0, 112, 48, 40, 4}, {0, 152, 48, 32, 7}};
      RectTblMusi = new short[][]{{0, 0, 40, 32, 0}, {0, 32, 40, 32, 0}, {0, 64, 40, 32, 0}};
      RectTblImo = new short[][]{{0, 0, 16, 24, -4}, {0, 24, 16, 24, -4}, {0, 48, 16, 16, 0}};
      RectTblBrobo = new short[][]{{0, 0, 24, 40, 0}, {0, 40, 24, 40, 0}, {0, 80, 24, 40, 0}, {0, 120, 24, 40, 0}, {0, 160, 24, 40, 0}};
      RectTblButa = new short[][]{{0, 0, 24, 40, 0}, {0, 40, 24, 40, 0}, {0, 80, 24, 40, 0}, {0, 120, 24, 40, 0}};
      RectTblKani = new short[][]{{0, 0, 48, 32, 0}, {0, 32, 48, 32, 0}, {0, 64, 48, 32, 0}, {0, 96, 48, 32, 0}};
      RectTblAruma = new short[][]{{0, 0, 32, 32, 0}, {0, 32, 32, 32, 0}, {0, 64, 32, 40, -5}, {0, 104, 32, 48, -8}};
      RectTblYado = new short[][]{{0, 0, 40, 40, 0}, {0, 40, 40, 40, 0}, {0, 80, 40, 40, 0}};
      RectTblUni = new short[][]{{0, 0, 24, 24, 0}, {0, 24, 24, 24, 0}, {0, 48, 24, 24, 0}, {0, 72, 24, 24, 0}};
      RectTblBat = new short[][]{{0, 0, 40, 24, 0}, {0, 24, 40, 32, 0}, {0, 56, 40, 32, 0}, {0, 88, 40, 32, 0}};
      RectTblMogura = new short[][]{{0, 0, 32, 48, 0}, {0, 48, 32, 48, 0}, {0, 96, 32, 48, 0}, {0, 144, 32, 40, 0}, {0, 184, 32, 40, 0}};
      RectTblFish = new short[][]{{0, 0, 32, 32, 0}, {0, 32, 32, 32, 0}};
      RectTblFish2 = new short[][]{{0, 0, 48, 24, 0}, {0, 24, 48, 24, 0}, {0, 48, 48, 24, 0}, {0, 72, 48, 24, 0}};
      Boss6TamaAnmTbl = new byte[]{1, -1, -1};
      Boss6TamaAnmTbl2 = new byte[]{1, -1, 0, -1, 2, -1, 3, -1, 4, -1, 1, -1, 0, -1, 2, -1, 3, -1, 4, -1};
      Boss6TamaAnmTbl3 = new byte[]{0, 4, 1, 4};
      RectTblBakuhatu = new short[][]{{8, 0, 24, 16}, {0, 16, 40, 32}, {0, 48, 40, 32}, {0, 80, 40, 40}, {0, 120, 40, 40}};
      RectTblKemuri = new short[][]{{8, 0, 24, 16}, {0, 160, 40, 32}, {0, 192, 40, 32}, {0, 80, 40, 40}, {0, 120, 40, 40}};
      RectTblTama = new short[][]{{0, 0, 16, 16}, {0, 16, 16, 16}, {0, 32, 16, 16}, {0, 48, 16, 16}, {0, 64, 16, 16}, {0, 80, 16, 16}, {0, 96, 16, 16}, {0, 112, 16, 16}, {0, 128, 16, 16}, {0, 144, 16, 16}, {8, 160, 8, 8}, {8, 168, 8, 8}, {0, 160, 8, 8}, {0, 168, 8, 8}};
      RectTblDBlock = new short[][]{{0, 0}, {16, 0}, {0, 16}, {16, 16}};
      RectTblBoss6Tama = new short[][]{{88, 56, 16, 16}, {64, 56, 24, 24}, {88, 72, 16, 16}, {88, 88, 16, 16}, {64, 80, 24, 24}};
      BossDeadLimitY = new int[]{912, 224, 656, 672, 1360, -16};
      boss2MoveTbl = new int[][]{{769600, 147200}, {775200, 128000}, {779200, 121600}, {779200, 25600}, {801200, 19200}};
      boss4Sisoo = new short[3][4];
      boss5Block = new short[10][4];
      boss5AttackCount = 0;
      boss6Piston = new int[4];
      boss6PistonXY = new int[4][4];
      boss6TamaY = new int[4];
      boss6PistonPos = new short[][]{{-104, -159}, {24, -159}, {-40, 144}, {88, 144}};
      endingEggStep = 0;
      endingEggAnim = 0;
      endingEggCount = 0;
      RectTblEndingB = new short[][]{{0, 0, 64, 96}, {64, 0, 64, 96}, {128, 0, 64, 96}};
      wipeCol = 0;
      wipeLevel = 0;
      wipeDir = false;
      continueStep = 0;
      continueResult = 0;
      ContinueSonicTbl = new short[][]{{0, 120, 0}, {48, 120, 0}, {96, 120, 0}, {96, 120, 1}, {48, 120, 1}};
      ContinueSonicTbl2 = new short[][]{{48, 0}, {0, 32}, {48, 32}, {0, 32}};
      switchflag2 = new boolean[256];
      kassya_x = new int[6][20];
      kassya_y = new int[6][20];
   }
}
