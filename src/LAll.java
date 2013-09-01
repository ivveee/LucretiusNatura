//package edu; 
import java.util.ArrayList;
import java.util.Iterator;

class LAll{
  
  private ArrayList<LBody> arBody = new ArrayList<>();
  private ArrayList<LBody> arBodiesToAdd = new ArrayList<>();
  private ArrayList<LBody> arBodiesToRemove = new ArrayList<>();
  private  Iterator<LBody> activeIt = null;// = arBody.iterator();
  private LBody currentBody = null;
  
  LAll(){}
  
void startIteration(){//Starts iteration
    arBodiesToAdd.clear();
    arBodiesToRemove.clear();
    activeIt = arBody.iterator();  
  }
  
 void addBody(LBody Body){
   if(activeIt == null)
     arBody.add(Body);
   else 
     arBodiesToAdd.add(Body);
 }
 //Collection<? extends E> c
 void addBodies(ArrayList<? extends LBody> in_arBody){
   if(activeIt == null){
     arBody.addAll(in_arBody);
   }
   else 
     arBodiesToAdd.addAll(arBody);
 }  
  
  
 void removeBody(LBody Body){
   if(activeIt == null)
        arBody.remove(Body);
   else{  
     if(Body == currentBody){
       activeIt.remove();
       Body.destroy();
     }
     else
       arBodiesToRemove.add(Body);
   }
 } 
  
 LBody getNext(){
   if(activeIt!=null){
     if(activeIt.hasNext())
       currentBody = activeIt.next();
     else{
       // Iteration finished
       currentBody = null;
       activeIt =   null;
     }   
     return currentBody;
   }
   else
     return null;
 }
  
 boolean hasNext(){
 if(activeIt != null){
   if(activeIt.hasNext()){
     return true;
   }
   else{
     activeIt = null;
     return false;
   }
 }
 else
   return false;
 }
 
 boolean flush(){
   if(activeIt != null)
     return false;
     arBody.addAll(arBodiesToAdd);
     arBody.removeAll(arBodiesToRemove);
     for(LBody body : arBodiesToRemove){
       body.destroy();
     }
     arBodiesToAdd.clear();
     arBodiesToRemove.clear();
   //activeIt = null;
   return true;
 }
  
//Iterator<LBody> iterator(){
//  return activeIt;
//}
  
  
  
}
