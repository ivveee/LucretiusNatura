//package edu; 


import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

class LWood extends LBasicBody {
    //float stability;
LWood(int pX, int pY,luc in_body){
    super(in_body);
      rotter = MaxRotter;
    //stability = 50000;//random(500,1500);
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    //bd.linearDamping = 0.1f;
    PhBody = parent.box2d.createBody(bd);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 1.1f;
    fixtureDef.friction = 10.0f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0004;
    CreateBody(bd,fixtureDef,pX,pY);
    PhBody.setUserData(this);
    if (stability>0)
            PhBody.setActive(false);
}

void Display(){
    parent.noStroke();
    parent.fill(parent.color(parent.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    parent.rect(vecPosition.x ,vecPosition.y,vecSize.x,vecSize.y);
}


  void ApplyForce() {
      setForcesFromBodiesAffect();
  }
  
}
