/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package edu; 
import java.util.ArrayList;
import org.jbox2d.common.*;



class LBody {
    luc parent; 
    LBody(luc in_parent){parent = in_parent;}
  ArrayList <LBody> arBodiesAffect = new ArrayList<>();
    
  Vec2 getPosition(){return null;}
  
  void ApplyForce(){;}

  Vec2 GetForce(Vec2 input){return null;}
  Vec2 GetImpulse(Vec2 input){return null;}
  
  void Display(){};
  
  void destroy(){};
}

