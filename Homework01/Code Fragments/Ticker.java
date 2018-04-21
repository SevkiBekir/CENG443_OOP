//***************************************************************************************************************************************************

public class Ticker extends Entity
{

  //=================================================================================================================================================

  private final String [] bist100 = { "AEFES" , "AFYON" , "AKBNK" , "AKENR" , "AKSA"  , "AKSEN" , "ALARK" , "ALCTL" , "ALGYO" , "ALKIM" ,
                                      "ANACM" , "ANELE" , "ARCLK" , "ASELS" , "AYGAZ" , "BAGFS" , "BANVT" , "BIMAS" , "BIZIM" , "BJKAS" ,
                                      "BRISA" , "CCOLA" , "CEMTS" , "CLEBI" , "CRFSA" , "DEVA"  , "DOAS"  , "DOHOL" , "ECILC" , "EGEEN" ,
                                      "EKGYO" , "ENKAI" , "ERBOS" , "EREGL" , "FENER" , "FROTO" , "GARAN" , "GLYHO" , "GOLTS" , "GOODY" ,
                                      "GOZDE" , "GSDHO" , "GSRAY" , "GUBRF" , "HALKB" , "HLGYO" , "ICBCT" , "IHLAS" , "IPEKE" , "ISCTR" ,
                                      "ISGYO" , "IZMDC" , "KARSN" , "KARTN" , "KCHOL" , "KIPA"  , "KLGYO" , "KONYA" , "KORDS" , "KOZAA" ,
                                      "KOZAL" , "KRDMD" , "MAVI"  , "METRO" , "MGROS" , "NETAS" , "NTTUR" , "ODAS"  , "OTKAR" , "PETKM" ,
                                      "PGSUS" , "PRKME" , "SAHOL" , "SASA"  , "SISE"  , "SKBNK" , "SODA"  , "TATGD" , "TAVHL" , "TCELL" ,
                                      "THYAO" , "TKFEN" , "TKNSA" , "TMSN"  , "TOASO" , "TRCAS" , "TRKCM" , "TSKB"  , "TSPOR" , "TTKOM" ,
                                      "TTRAK" , "TUPRS" , "ULKER" , "VAKBN" , "VESTL" , "VKGYO" , "YATAS" , "YAZIC" , "YKBNK" , "ZOREN" } ;

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  // Coordinates for polygons (up arrow, right arrow, down arrow)

  private final int [] xUp    = {  7 , 14 , 10 , 10 ,  4 ,  4 ,  0 } ;
  private final int [] yUp    = {  0 ,  7 ,  7 , 14 , 14 ,  7 ,  7 } ;

  private final int [] xRight = {  0 ,  7 ,  7 , 14 ,  7 ,  7 ,  0 } ;
  private final int [] yRight = {  4 ,  4 ,  0 ,  7 , 14 , 10 , 10 } ;

  private final int [] xDown  = {  4 , 10 , 10 , 14 ,  7 ,  0 ,  4 } ;
  private final int [] yDown  = {  0 ,  0 ,  7 ,  7 , 14 ,  7 ,  7 } ;

  //=================================================================================================================================================

}

//***************************************************************************************************************************************************
