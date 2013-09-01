//package edu; 

import org.jbox2d.common.*;



class LEarth extends LBody {
    LEarth(luc in_parent){super(in_parent);}
  Vec2 g = new Vec2(0,-9);
  
  Vec2 GetForce(Vec2 vecPoint){
    return g;
    
  }
}

