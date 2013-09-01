//package edu; 
import processing.core.*;
import processing.data.*;

class LBit{
  int x;
  int y;
  int type;
//---------------------Bit created from World-----------------
  LBit(int in_iX,int in_iY, int in_iType){
    x = in_iX;
    y = in_iY;
    type = in_iType;
  }  
//---------------------Bit created from XML------------------
  LBit(XML oXML, int iPixDefaultSize){
       x=oXML.getInt("x")*(iPixDefaultSize);
       y=oXML.getInt("y")*(iPixDefaultSize);
       type=oXML.getInt("type");
  }
//---------------------Bit is written down--------------------       
  XML BitToXML(int iPixDefaultSize){
    XML xmlBitData = new XML("bit");
           xmlBitData.setString("x", Integer.toString((int)x/iPixDefaultSize));
           xmlBitData.setString("y", Integer.toString((int)y/iPixDefaultSize));
           xmlBitData.setString("type", Integer.toString(type));
    return xmlBitData;
  }
};

//-----------------------------------------------------------------
//---------------------------Form----------------------------------
class LForm{
  String sName = "a";
  int iType = 0;
  LBit[] arBits= new LBit[0];
  
//----------------------Form is seen----------------------
  LForm(PImage imgEye, int col,luc parent){
    imgEye.loadPixels();
    int j=0;int k=0;
    for (int i = 0; i < imgEye.pixels.length; i++) {
      if(j == imgEye.width){
        j=0;
        k++;
      }
      if (imgEye.pixels[i] == col) {
        LBit Pixel=new LBit(j*(parent.iPixDefaultSize),
                            k*(parent.iPixDefaultSize),
                            0);
        LBit[] NewarBits= (LBit[])luc.append(arBits, Pixel);
        arBits = NewarBits;
      }
      j++;
    }
  }
//----------------------Form is read----------------------  
  LForm(XML xmlFormData, int iPixDefaultSize){
         XML[] xmlBitsData = xmlFormData.getChildren("bit");
         arBits = new LBit[xmlBitsData.length];
         for(int j=0;j < xmlBitsData.length;j++){
           LBit NewBit = new LBit(xmlBitsData[j].getInt("x")*iPixDefaultSize,
                                  xmlBitsData[j].getInt("y")*iPixDefaultSize,
                                  xmlBitsData[j].getInt("type"));
           arBits[j]=NewBit;
         } 
  }
//----------------------Form is set----------------------   
/*  LForm(LFly[] arLFly){
     arBits = new LBit[arLFly.length];
     for (int i=0;i<arLFly.length;i++){
       LBit oNewBit = new LBit(arLFly[i].iX,arLFly[i].iY,0);
       arBits[i]=oNewBit;
     }   
  }*/
//----------------------Get bit----------------------     
    LBit At(int i){
      if (i > arBits.length)
        return arBits[i];
      else
        return null;
  }
//----------------------Form to write---------------------- 
  XML FormToXML(int iPixDefaultSize){
        XML xmlNewString = new XML("form");
         xmlNewString.setString("name", sName);
         xmlNewString.setString("type", Integer.toString(iType));
         for (int i=0;i<arBits.length;i++){
           XML xmlBitData = new XML("bit");
           xmlBitData.setString("x", Integer.toString((int)arBits[i].x/iPixDefaultSize));
           xmlBitData.setString("y", Integer.toString((int)arBits[i].y/iPixDefaultSize));
           xmlBitData.setString("type", Integer.toString(arBits[i].type));
           xmlNewString.addChild(xmlBitData);
         }   
         return xmlNewString;
  } 
  

};
